import Post from "../post/Post";
import CreatePost from "../post/CreatePost";
import ProfilePicture from "../user_page/ProfilePicture";
import CoverPhoto from "./CoverPhoto";

const ArrayPageRightContainer = ({showGroup}) => {
    const posts = showGroup.posts;

    const image = showGroup.image;
    const imageId = posts !== undefined && image !== null ? image.id : null;

    return (
        <div className="user-page-right-container justify-content-center">
            {Object.keys(showGroup).length > 0 ? <div>
                <div className="">
                    {posts !== undefined && image !== null ? <CoverPhoto photoId={imageId}/> : <div></div>}
                </div>
                <div className="d-flex justify-content-between align-items-center">
                    <span className="fs-4 align-content-center">{showGroup.name}</span>
                </div>
                <div className="friend-list-container">
                    <p>Members</p>
                    {<div className="friend-pics-container flex-box">
                        {showGroup.members.map(member => <ProfilePicture key={member.id}
                                                                         profilePictureId={member.profilePictureId}
                                                                         userId={member.id} placement="post"/>)}
                    </div>}
                </div>
                <CreatePost/>
            </div> : ""}
            {posts !== undefined ? posts.map(post => <Post key={post.id} post={post}/>) : ""}
        </div>
    );
};

export default ArrayPageRightContainer;