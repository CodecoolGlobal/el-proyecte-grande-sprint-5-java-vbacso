import React from 'react';
import {FaTimes} from "react-icons/fa";
import ProfilePicture from "../../user_page/ProfilePicture";

const PostHeader = ({loggedInUser, postId, userName, created, title, userId, onDelete, profilePictureId}) => {
    return (
        <div className='post-header'>
            <ProfilePicture placement={"post"} profilePictureId={profilePictureId} userId={userId}/>
            <h6 className='username-in-post'>
                {userName + ' - '
                    + new Date(created).toLocaleTimeString('en', {
                        year: 'numeric',
                        month: 'long',
                        day: 'numeric',
                        hour12: false
                    })}
            </h6>
            <h4 className='post-title'>{title}</h4>
            {loggedInUser.id === userId ?
                <FaTimes onClick={() => onDelete(postId)} className='delete-icon' cursor='pointer'/> : ''}
        </div>
    );
};

export default PostHeader;
