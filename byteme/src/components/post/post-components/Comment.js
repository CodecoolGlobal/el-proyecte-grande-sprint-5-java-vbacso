import ProfilePicture from "../../user_page/ProfilePicture";
import {FaTimes} from "react-icons/fa";

export const deleteComment = async (id) => {
    await fetch(`http://localhost:8080/comment/delete/${id}`, {
        method: 'DELETE',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify(id)
    }).catch(console.error)
}

export const createComment = async (input) => {
    const res = await fetch(`http://localhost:8080/comment/add`, {
        method: 'POST',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify(input)
    })
    return await res.json();
}

const Comment = ({postId,name, body, last, profilePictureID,onDeleteCom}) => {


    return (
        <div className={last ? 'comments-container' : 'comments-container last'} style={{color: 'white'}}>
            <ProfilePicture profilePictureId={profilePictureID} placement={"post"} userId={userId}/>
            <p>{name}</p>
            <p>{body}</p>
            <a onClick={() => onDeleteCom(postId)} >DELETE</a>

        </div>
    )
}

export default Comment