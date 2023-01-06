import Post from "../post/Post";
import CreatePost from "../post/CreatePost";
import ProfilePicture from "../user_page/ProfilePicture";
import CoverPhoto from "./CoverPhoto";
import {useState} from "react";

const ArrayPageRightContainer = ({showGroup}) => {
    const posts = showGroup.posts;

    const image = showGroup.image;
    const imageId = posts !== undefined && image !== null ? image.id : null;

    const [groupPosts, setGroupPosts] = useState([]);

    const createGroupPost = async (input) => {
        console.log(input)
        console.log(showGroup.id)
        const res = await fetch(`http://localhost:8080/group/post/add/${showGroup.id}`, {
            method: 'POST',
            headers: {
                'Content-type': 'application/json'
            },
            body: JSON.stringify(input)
        })
        return await res.json();
    };

    const createGroupPostEvent = async (input) => {
        const newPost = await createGroupPost(input);
        setGroupPosts([newPost, ...groupPosts]);
    };

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
                <CreatePost onAdd={createGroupPostEvent} placement="group"/>
            </div> : ""}
            {posts !== undefined ? posts.map(post => <Post key={post.id} post={post}/>) : ""}
        </div>
    );
};

export default ArrayPageRightContainer;