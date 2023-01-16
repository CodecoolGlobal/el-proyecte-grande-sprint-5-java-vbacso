import React from 'react';
import ChatMessage from "./ChatMessage";
import ProfilePicture from "../user_page/ProfilePicture";
import {FaTimes} from "react-icons/fa";

const ChatBoxBody = ({loggedInUser, receiverUser, handleMessage, sendValue, privateChat, message, onToggle}) => {
    return (
        <div id={"chat-box-" + receiverUser.id} className="chat-box-body align-self-end rounded p-1 hidden">
            <div className="chat-box-body-header d-flex mb-1 gap-1">
                <ProfilePicture profilePictureId={receiverUser.profilePictureId} userId={receiverUser.id}
                                placement="chat"/>
                {receiverUser.name}
                <FaTimes onClick={onToggle} className='ms-auto' cursor='pointer'/>
            </div>
            {privateChat ?
                <div className="chat-message-container d-flex flex-column gap-1 p-1 rounded">
                    {privateChat?.map(msg => (<ChatMessage key={msg.created}
                                                           className={msg.sender.id === loggedInUser.id ? "chat-message-sended" : "chat-message-received"}
                                                           picture={msg.sender.id === loggedInUser.id ? "" :
                                                               <ProfilePicture
                                                                   profilePictureId={receiverUser.profilePictureId}
                                                                   userId={receiverUser.id}
                                                                   placement="chat-message"/>}
                                                           content={msg.content}/>))}
                </div> : ""}
            <div className="send-message input-group input-group-sm float-end mt-1">
                <input type="text" className="input-message form-control" placeholder="enter the message"
                       value={message}
                       onChange={handleMessage}/>
                <button type="button" className="send-button btn btn-sm" onClick={sendValue}>send</button>

            </div>
        </div>
    );
};

export default ChatBoxBody;