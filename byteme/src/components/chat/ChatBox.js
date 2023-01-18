import ProfilePicture from "../user_page/ProfilePicture";
import {useState} from "react";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faCircle, faMessage} from "@fortawesome/free-solid-svg-icons";
import ChatBoxBody from "./ChatBoxBody";

const ChatBox = ({loggedInUser, receiverUser, stompClient, privateChat, copySelfMessage, selfState}) => {

    const [message, setMessage] = useState("");

    const toggleChatBoxBody = () => {
        if (selfState==="ONLINE") {
            const chatBoxBodyContainer = document.querySelector(".chat-box-body-container")
            const chatBoxBody = document.querySelector(`#chat-box-${receiverUser.id}`)
            chatBoxBody.classList.toggle("hidden")
            document.querySelector(`#message-alert-${receiverUser.id}`).classList.add("hide")
            chatBoxBodyContainer.appendChild(chatBoxBody)
        }
    };

    const handleMessage = (event) => {
        const text = event.target.value;
        setMessage(text);
    }

    const sendValue = () => {
        if (stompClient && message) {
            const chatMessage = {
                sender: {
                    "id": loggedInUser.id,
                    "name": loggedInUser.name
                },
                receiver: {
                    "id": receiverUser.id,
                    "name": receiverUser.name
                }, content: message,
                created: new Date(),
                status: "MESSAGE"
            };
            stompClient.send("/app/message", {}, JSON.stringify(chatMessage));
            copySelfMessage(chatMessage);
            setMessage("");
        }
    }

    return (<div className="chat-box">
        <div className="chat-box-header d-flex justify-content-start align-items-center gap-1">
            <FontAwesomeIcon icon={faMessage} id={"message-alert-" + receiverUser.id}
                             className="message-alert-icon hide fa-flip-horizontal"/>
            <FontAwesomeIcon icon={faCircle} id={"online-marker-" + receiverUser.id} className="online-marker-off"/>
            <ProfilePicture profilePictureId={receiverUser.profilePictureId} userId={receiverUser.id}
                            placement="chat"/>
            <a onClick={toggleChatBoxBody}>{receiverUser.name}</a>
        </div>
        <ChatBoxBody loggedInUser={loggedInUser}
                     receiverUser={receiverUser}
                     privateChat={privateChat}
                     handleMessage={handleMessage}
                     sendValue={sendValue}
                     message={message}
                     onToggle={toggleChatBoxBody}
                     selfState={selfState}/>
    </div>);
};

export default ChatBox;
