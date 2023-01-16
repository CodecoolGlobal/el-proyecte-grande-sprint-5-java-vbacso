import React, {useEffect, useState} from 'react';
import ChatBox from "./ChatBox";
import SockJS from "sockjs-client";
import {over} from "stompjs";
import Loading from "../common/Loading";

let stompClient = null;

const Chat = ({loggedInUser}) => {
    const [friends, setFriends] = useState();
    const [privateChats, setPrivateChats] = useState(new Map());
    const [connectionEstablished, setConnectionEstablished] = useState(false);

    useEffect(() => {
        setFriends(loggedInUser.friendList)
    }, [loggedInUser.friendList]);

    const connect = () => {
        let Sock = new SockJS('http://localhost:8080/websocket');
        stompClient = over(Sock);
        stompClient.connect({userId:loggedInUser.id}, onConnected, onError);
    }

    const onConnected = () => {
        setConnectionEstablished(true);
        stompClient.unsubscribe(loggedInUser.id);
        stompClient.subscribe('/user/' + loggedInUser.id + '/private', onPrivateMessage, {id: loggedInUser.id});
    }

    const onError = (err) => {
        console.log(err);
    }

    const onPrivateMessage = (payload) => {
        const payloadData = JSON.parse(payload.body);
        if (privateChats.get(payloadData.sender.id)) {
            privateChats.get(payloadData.sender.id).push(payloadData);
            setPrivateChats(new Map(privateChats));
        } else {
            let list = [];
            list.push(payloadData);
            privateChats.set(payloadData.sender.id, list);
            setPrivateChats(new Map(privateChats));
        }
        alertIfHidden(payloadData.sender.id);
    }

    const alertIfHidden = (senderId) => {
        const isHidden = document.querySelector(`#chat-box-${senderId}`).classList.contains("hidden")
        if (isHidden) {
            document.querySelector(`#message-alert-${senderId}`).classList.remove("hide")
        }
    };

    const copySelfMessage = (msg) => {
        if (privateChats.get(msg.receiver.id)) {
            privateChats.get(msg.receiver.id).push(msg);
            setPrivateChats(new Map(privateChats));
        } else {
            let list = [];
            list.push(msg);
            privateChats.set(msg.receiver.id, list);
            setPrivateChats(new Map(privateChats));
        }
    };

    if (!connectionEstablished) {
        connect();
    }

    return (
        <div className="chat-panel d-flex flex-column me-3">
            {connectionEstablished ?
                friends?.map(friend => (
                    <ChatBox key={friend.id}
                             stompClient={stompClient}
                             loggedInUser={loggedInUser}
                             receiverUser={friend}
                             privateChat={privateChats.get(friend.id)}
                             copySelfMessage={copySelfMessage}
                    />))
                :
                <Loading/>}

        </div>

    );
};

export default Chat;
