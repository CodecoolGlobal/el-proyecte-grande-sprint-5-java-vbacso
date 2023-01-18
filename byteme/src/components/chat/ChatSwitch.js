import React from 'react';

const ChatSwitch = ({selfStatus, onConnect, onDisconnect}) => {
    const toggle = () => {
        if (selfStatus === "ONLINE") {
            onDisconnect();
        } else if (selfStatus === "OFFLINE") {
            onConnect();
        } else {
            console.error("Invalid state")
        }
    }


    return (
        <>
            <div className="form-check form-switch">
                <input className="form-check-input" type="checkbox" role="switch" id="chat-toggle"
                       checked={selfStatus === "ONLINE"} onChange={toggle}/>
                <label className="form-check-label" htmlFor="chat-toggle"
                       id="chat-toggle-label">You are {selfStatus === "ONLINE" ? "Online" : "Offline"}</label>
            </div>
        </>
    );
};

export default ChatSwitch;