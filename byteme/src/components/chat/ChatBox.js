import ProfilePicture from "../user_page/ProfilePicture";
import {useState} from "react";
import ChatMessage from "./ChatMessage";

const ChatBox = ({loggedInUser, receiverUser, stompClient, privateChat, copySelfMessage}) => {

    const [message, setMessage] = useState("");

    const toggleChatBoxBody = () => {
        const chatBoxBody = document.querySelector(`#chat-box-${receiverUser.id}`)
        chatBoxBody.classList.toggle("hidden")
    };

    const handleMessage = (event) => {
        const text = event.target.value;
        setMessage(text);
    }

    const sendValue = () => {
        if (stompClient) {
            const chatMessage = {
                sender: {
                    "id": loggedInUser.id,
                    "name": loggedInUser.name
                },
                receiver: {
                    "id": receiverUser.id,
                    "name": receiverUser.name
                },
                content: message,
                created: new Date(),
                status: "MESSAGE"
            };
            console.log(chatMessage);
            stompClient.send("/app/message", {}, JSON.stringify(chatMessage));
            copySelfMessage(chatMessage);
            setMessage("");
        }
    }

    return (
        <div className="chat-box">
            <div className="chat-box-header d-flex justify-content-end">
                <ProfilePicture profilePictureId={receiverUser.profilePictureId} userId={receiverUser.id}
                                placement="chat"/>
                <a onClick={toggleChatBoxBody}>{receiverUser.name}</a>
            </div>
            <div id={"chat-box-" + receiverUser.id} className="chat-box-body hidden">
                {privateChat?.map(msg => (
                    <ChatMessage key={msg.created}
                                 className={msg.sender.id === loggedInUser.id ? "chat-message-sended" : "chat-message-received"}
                                 content={msg.content} />
                ))}
                <div className="send-message">
                    <input type="text" className="input-message" placeholder="enter the message" value={message}
                           onChange={handleMessage}/>
                    <button type="button" className="send-button" onClick={sendValue}>send</button>
                </div>
            </div>
        </div>
    );
};

export default ChatBox;
