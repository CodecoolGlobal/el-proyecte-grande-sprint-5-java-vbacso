import ProfilePicture from "../../user_page/ProfilePicture";

const Comment = ({name, body, last, profilePictureID}) => {
    return (
        <div className={last ? 'comments-container' : 'comments-container last'} style={{color: 'white'}}>
            <ProfilePicture profilePictureId={profilePictureID} placement={"post"}/>
            <p>{name}</p>
            <p>{body}</p>
        </div>
    )
}

export default Comment