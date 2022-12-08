const ChatMessage = ({content, className}) => {
    return (
        <div className={className}>
            {content}
        </div>
    );
};

export default ChatMessage;
