import Post, {deletePost} from "../post/Post";
import CreatePost from "../post/CreatePost";
import ProfilePicture from "../user_page/ProfilePicture";
import CoverPhoto from "./CoverPhoto";
import {useEffect, useState} from "react";
import NoCoverPhoto from "./NoCoverPhoto";
import {MdOutlineDelete} from "react-icons/md";
import React from "react";

const ArrayPageRightContainer = ({showGroup, setShowGroup, loggedInUser}) => {

    const posts = showGroup.posts;
    const image = showGroup.image;
    const imageId = posts !== undefined && image !== null ? image.id : null;
    let ownerId = null;
    const loggedInUserId = loggedInUser.id;

    if (!Array.isArray(showGroup)) {
        ownerId = showGroup.owner.id;
    }

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

    const openModal = () => {
        const modal = document.querySelector("#remove-group-modal");
        modal.style.display = "block";
    };

    const closeModal = () => {
        const modal = document.querySelector("#remove-group-modal")
        modal.style.display = "none";
    };

    // Remove Group
    const removeGroup = async () => {
        await fetch(`http://localhost:8080/group/delete/${showGroup.id}`, {
            method: 'DELETE'
        });
        closeModal();
        setShowGroup();
    };

    return (
        <div className="user-page-right-container justify-content-center">
            <div id="remove-group-modal" className="modal">
                <div className="modal-content">
                    <p>Are you sure you want to delete this group?</p>
                    <button onClick={closeModal} id="no-remove" className="button button-dark">No</button>
                    <button onClick={removeGroup} id="yes-remove" className="button button-dark">Yes</button>
                </div>
            </div>
            {Object.keys(showGroup).length > 0 ? <div>
                {ownerId === loggedInUserId ?
                    < div className="remove-group">
                        <MdOutlineDelete onClick={openModal} size={35}/>
                    </div> : ""}
                <div className="cover=photo">
                    {posts !== undefined && image !== null ? <CoverPhoto photoId={imageId}/> : image === null ?
                        <NoCoverPhoto showGroup={showGroup} setShowGroup={setShowGroup}/> : <div></div>}
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
            {groupPosts !== undefined ? groupPosts.map(post => <Post key={post.id} post={post}
                                                                     onDelete={deletePostEvent}/>) : ""}
        </div>
    );
};

export default ArrayPageRightContainer;
