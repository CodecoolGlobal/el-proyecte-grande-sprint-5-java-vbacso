import ProfilePicture from "../user_page/ProfilePicture";
import {useState} from "react";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faCircle} from "@fortawesome/free-solid-svg-icons";
import ChatBoxBody from "./ChatBoxBody";

const ChatBox = ({loggedInUser, receiverUser, stompClient, privateChat, copySelfMessage}) => {

    const [message, setMessage] = useState("");

    const toggleChatBoxBody = () => {
        const chatBoxBodyContainer = document.querySelector(".chat-box-body-container")
        const chatBoxBody = document.querySelector(`#chat-box-${receiverUser.id}`)
        chatBoxBody.classList.toggle("hidden")
        chatBoxBodyContainer.appendChild(chatBoxBody)
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
            <FontAwesomeIcon icon={faCircle} data-receiver-id={receiverUser.id} className="online-marker-off"/>
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
                     onToggle={toggleChatBoxBody}/>
    </div>);
};

export default ChatBox;
