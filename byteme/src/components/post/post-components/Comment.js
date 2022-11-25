import ProfilePicture from "../../user_page/ProfilePicture";
import {FaTimes} from "react-icons/fa";


export const deleteComment = async (id) => {
    await fetch(`/comment/delete/${id}`, {
        method: 'DELETE',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify(id)
    }).catch(console.error)
}

export const createComment = async (input) => {
    const res = await fetch(`/comment/add`, {
        method: 'POST',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify(input)
    })
    return await res.json();
}

const Comment = ({commentId, name, body, profilePictureID, onDeleteCom, loggedInUserId, userId}) => {


    return (
        <div className={'comments-container'} style={{color: 'white'}}>
            <ProfilePicture profilePictureId={profilePictureID} placement={"post"} userId={userId}/>
            <p>{name}</p>
            <p>{body}</p>
            {userId === loggedInUserId ?
                <FaTimes className={'comments-delete-link'} onClick={() => onDeleteCom(commentId)}/> : ""}
        </div>
    )
}

export default Comment
