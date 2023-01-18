import React, {useCallback, useEffect, useState} from 'react';
import ChatBox from "./ChatBox";
import SockJS from "sockjs-client";
import {over} from "stompjs"
import Loading from "../common/Loading";
import ChatSwitch from "./ChatSwitch";

let sockJs;
let stompClient;

const Chat = ({loggedInUser}) => {
    const [friends, setFriends] = useState();
    const [selfStatus, setSelfStatus] = useState("ONLINE");
    const [privateChats, setPrivateChats] = useState(new Map());
    const [connectionEstablished, setConnectionEstablished] = useState(false);

    const init = () => {

        if (!connectionEstablished && selfStatus === "ONLINE") {
            connect();
        }
        window.onbeforeunload = onDisconnect;
    };

    const onDisconnect = useCallback(() => {
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
        sockJs = undefined;
        stompClient = undefined;
    },[loggedInUser.id]);

    useEffect(() => {
        setFriends(loggedInUser.friendList);
        const logoutButton = document.querySelector(`#logout`);
        logoutButton.addEventListener("click", onDisconnect);
        return () => {
            logoutButton.removeEventListener("click", onDisconnect);
        };
    }, [loggedInUser.friendList, onDisconnect]);

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
        stompClient.unsubscribe(loggedInUser.id);
        stompClient.subscribe('/user/' + loggedInUser.id + '/private', onMessageReceived, {id: loggedInUser.id});
        const loginMessage = {
            sender: {
                "id": loggedInUser.id,
            },
            status: "ONLINE"
        };
        stompClient.send("/app/login", {}, JSON.stringify(loginMessage));
        setSelfStatus("ONLINE");
        setConnectionEstablished(true);
    }

    const onError = (err) => {
        console.log(err);
        setTimeout(() => {
            connect();
        }, 5000);
    }

    const onMessageReceived = (payload) => {
        const payloadData = JSON.parse(payload.body);
        if (payloadData.status === "MESSAGE") {
            handlePrivateMessage(payloadData);
        } else if (payloadData.status === "ONLINE") {
            handleOnlineStatus(payloadData);
        } else if (payloadData.status === "PING") {
            handlePing(payloadData);
        } else if (payloadData.status === "OFFLINE") {
            handleOfflineStatus(payloadData);
        }
    }

    const handlePrivateMessage = (payloadData) => {
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
    };

    const handleOnlineStatus = (payloadData) => {
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
    };

    const handleOfflineStatus = (payloadData) => {
        const marker = document.querySelector(`#online-marker-${payloadData.content}`)
        marker.classList.remove("online-marker-on")
        marker.classList.add("online-marker-off")
    };

    const handlePing = (payloadData) => {
        const marker = document.querySelector(`#online-marker-${payloadData.content}`)
        marker.classList.remove("online-marker-off")
        marker.classList.add("online-marker-on")
    };

    const setAllOnlineMarkerOff = () => {
        const markers = document.querySelectorAll('.online-marker-on');
        Array.from(markers)?.forEach(marker => {
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

    init();
    return (
        <div className="chat-panel d-flex flex-column me-3">
            <button onClick={onDisconnect}>logout</button>
            <ChatSwitch/>
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
