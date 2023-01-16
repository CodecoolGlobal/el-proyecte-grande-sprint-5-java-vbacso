const ChatMessage = ({content, className, picture}) => {
    return (
        <div className={className}>
            {picture}
            {content}
        </div>
    );
};

export default ChatMessage;
