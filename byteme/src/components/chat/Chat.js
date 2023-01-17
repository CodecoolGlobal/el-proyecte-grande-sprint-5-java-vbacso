import React, {useEffect, useState} from 'react';
import ChatBox from "./ChatBox";
import SockJS from "sockjs-client";
import {over} from "stompjs"
import Loading from "../common/Loading";

let sockJs;
let stompClient;

const Chat = ({loggedInUser}) => {
    const [friends, setFriends] = useState();
    const [selfStatus, setSelfStatus] = useState("OFFLINE");
    const [privateChats, setPrivateChats] = useState(new Map());
    const [connectionEstablished, setConnectionEstablished] = useState(false);

    useEffect(() => {
        setFriends(loggedInUser.friendList)
    }, [loggedInUser.friendList]);

    const connect = () => {
        if (sockJs === undefined) {
            sockJs = new SockJS('http://localhost:8080/websocket');
        }
        stompClient = over(sockJs);
        // stompClient.debug = null
        stompClient.connect({
                "userId": loggedInUser.id
            },
            onConnected,
            onError);
    }

    const onConnected = () => {
        setConnectionEstablished(true);
        stompClient.unsubscribe(loggedInUser.id);
        stompClient.subscribe('/user/' + loggedInUser.id + '/private', onPrivateMessage, {id: loggedInUser.id});
        const loginMessage = {
            sender: {
                "id": loggedInUser.id,
            },
            status: "ONLINE"
        };
        stompClient.send("/app/login", {}, JSON.stringify(loginMessage));
        setSelfStatus("ONLINE")
    }

    const onError = (err) => {
        console.log(err);
        sockJs = undefined;
        setTimeout(() => {
            connect();
        }, 2000);
    }

    const onDisconnect = () => {
        const logoutMessage = {
            sender: {
                "id": loggedInUser.id,
            },
            status: "OFFLINE"
        };
        stompClient.send("/app/logout", {}, JSON.stringify(logoutMessage));
        stompClient.disconnect();
        setAllOnlineMarkerOff();
        setSelfStatus("OFFLINE");
    };

    const onPrivateMessage = (payload) => {
        const payloadData = JSON.parse(payload.body);
        if (payloadData.status === "MESSAGE") {
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
        } else {
            if (payloadData.status === "ONLINE") {
                const marker = document.querySelector(`#online-marker-${payloadData.content}`)
                marker.classList.remove("online-marker-off")
                marker.classList.add("online-marker-on")
                const chatMessage = {
                    receiver: {
                        "id": payloadData.content,
                    }, content: loggedInUser.id,
                    status: "PING"
                };
                stompClient.send("/app/message", {}, JSON.stringify(chatMessage));
            } else if (payloadData.status === "PING") {
                const marker = document.querySelector(`#online-marker-${payloadData.content}`)
                marker.classList.remove("online-marker-off")
                marker.classList.add("online-marker-on")
            } else if (payloadData.status === "OFFLINE") {
                const marker = document.querySelector(`#online-marker-${payloadData.content}`)
                marker.classList.remove("online-marker-on")
                marker.classList.add("online-marker-off")
            }
        }
    }

    const setAllOnlineMarkerOff = () => {
        const markers = document.querySelectorAll('.online-marker-on');
        Array.from(markers)?.map(marker => {
            marker.classList.remove("online-marker-on")
            marker.classList.add("online-marker-off")
        })
    };

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

    if (!connectionEstablished&&selfStatus==="OFFLINE") {
        connect();
    }

    return (
        <div className="chat-panel d-flex flex-column me-3">
            <a onClick={onDisconnect}>logout</a>
            {connectionEstablished ?
                friends?.map(friend => (
                    <ChatBox key={friend.id}
                             stompClient={stompClient}
                             loggedInUser={loggedInUser}
                             receiverUser={friend}
                             privateChat={privateChats.get(friend.id)}
                             copySelfMessage={copySelfMessage}
                             selfState={selfStatus}
                    />))
                :
                <Loading/>}
        </div>

    );
};

export default Chat;
