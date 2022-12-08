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
        console.log(privateChats)
    }, [loggedInUser.friendList]);


    const toggleChat = () => {
        const friendContainer = document.querySelector(".chat-friend-container");
        friendContainer.classList.toggle("hidden");
    };

    const connect = () => {
        let Sock = new SockJS('http://localhost:8080/websocket');
        stompClient = over(Sock);
        stompClient.connect({}, onConnected, onError);
    }

    const onConnected = () => {
        setConnectionEstablished(true);
        stompClient.unsubscribe(loggedInUser.id);
        stompClient.subscribe('/user/' + loggedInUser.id + '/private', onPrivateMessage,{id:loggedInUser.id});
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
    }

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

    return (<div>
            {connectionEstablished ?
                <div className="chat-container">
                    <div className="chat-friend-container hidden">
                        {friends?.map(friend => (
                            <ChatBox key={friend.id}
                                     stompClient={stompClient}
                                     loggedInUser={loggedInUser}
                                     receiverUser={friend}
                                     privateChat={privateChats.get(friend.id)}
                                     copySelfMessage={copySelfMessage}
                            />))}
                    </div>
                    <button className="button button-light button-chat" onClick={toggleChat}>Chat</button>
                </div> : <Loading/>
            }</div>
    );
};

export default Chat;
