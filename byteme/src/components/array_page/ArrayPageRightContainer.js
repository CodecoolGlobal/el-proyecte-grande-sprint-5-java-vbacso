import Post from "../post/Post";
import CreatePost from "../post/CreatePost";
import ProfilePicture from "../user_page/ProfilePicture";

const ArrayPageRightContainer = ({showGroup}) => {
    const image = showGroup.image;
    const posts = showGroup.posts;
    return (
        <div className="user-page-right-container">
            {Object.keys(showGroup).length > 0 ? <div>
                <div className="d-flex justify-content-between align-items-center">
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