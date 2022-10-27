import React from 'react';
import {FaTimes} from "react-icons/fa";

const PostHeader = ({postId, userName, title, userId, onDelete}) => {
    const loggedInUserId = JSON.parse(localStorage.getItem("loggedInUser")).id;
    return (
        <div className='post-header'>
            <h6 className='username-in-post'>{userName}</h6>
            <h4 className='post-title'>{title}</h4>{loggedInUserId === userId ?
            <FaTimes onClick={() => onDelete(postId)} className='delete-icon' cursor='pointer'/> : ''}

        </div>
    );
};

export default PostHeader;
