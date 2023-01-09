import Post, {deletePost} from "../post/Post";
import CreatePost from "../post/CreatePost";
import ProfilePicture from "../user_page/ProfilePicture";
import CoverPhoto from "./CoverPhoto";
import {useEffect, useState} from "react";

const ArrayPageRightContainer = ({showGroup}) => {
    const posts = showGroup.posts;
    const image = showGroup.image;
    const imageId = posts !== undefined && image !== null ? image.id : null;

    const [groupPosts, setGroupPosts] = useState([]);

    useEffect(() => {
        const getGroupPosts = async () => {
            const postsFromServer = await fetchGroupPosts();
            setGroupPosts(postsFromServer);
        };
        if (showGroup.id !== undefined) {
            getGroupPosts().catch(console.error);
        }
    }, [showGroup.id]);

    const fetchGroupPosts = async () => {
        const groupPostsFromServer = await fetch(`http://localhost:8080/post/group/${showGroup.id}`);
        return (await groupPostsFromServer.json()).sort((a, b) => new Date(b.created) - new Date(a.created));
    };

    //Create Post to corresponding Group
    const createGroupPost = async (input) => {
        const res = await fetch(`http://localhost:8080/post/group/add/${showGroup.id}`, {
            method: 'POST',
            headers: {
                'Content-type': 'application/json'
            },
            body: JSON.stringify(input)
        });
        return await res.json();
    };

    // Delete Post from corresponding Group
    const deletePostEvent = async (id) => {
        await deletePost(id);
        setGroupPosts(groupPosts.filter((p) => p.id !== id))
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
                <CreatePost onAdd={createGroupPostEvent} showGroupId={showGroup.id} placement="group"/>
            </div> : ""}
            {groupPosts !== undefined ? groupPosts.map(post => <Post key={post.id} post={post} onDelete={deletePostEvent}/>) : ""}
        </div>
    );
};

export default ArrayPageRightContainer;
