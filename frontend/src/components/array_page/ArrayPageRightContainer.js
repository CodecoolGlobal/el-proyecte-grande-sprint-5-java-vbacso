import Post, {deletePost} from "../post/Post";
import CreatePost from "../post/CreatePost";
import ProfilePicture from "../user_page/ProfilePicture";
import CoverPhoto from "./CoverPhoto";
import React, {useEffect, useState} from "react";
import NoCoverPhoto from "./NoCoverPhoto";
import {MdOutlineDelete} from "react-icons/md";
import {getAuthenticationToken} from "../../util";

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
        const groupPostsFromServer = await fetch(`/post/group/${showGroup.id}`, {
            headers: {
                "Authorization": getAuthenticationToken()
            }
        });
        return (await groupPostsFromServer.json()).sort((a, b) => new Date(b.created) - new Date(a.created));
    };

    //Create Post to corresponding Group
    const createGroupPost = async (input) => {
        const res = await fetch(`/post/group/add/${showGroup.id}`, {
            method: 'POST',
            headers: {
                "Authorization": getAuthenticationToken(),
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
        await fetch(`/group/delete/${showGroup.id}`, {
            method: 'DELETE',
            headers: {
                "Authorization": getAuthenticationToken()
            }

        });
        closeModal();
        setShowGroup();
    };

    return (
        <div className="user-page-right-container">
            <div id="remove-group-modal" className="modal">
                <div className="modal-content">
                    <p>Are you sure you want to delete this group?</p>
                    <button onClick={closeModal} id="no-remove" className="button button-dark">No</button>
                    <button onClick={removeGroup} id="yes-remove" className="button button-dark">Yes</button>
                </div>
            </div>
            {Object.keys(showGroup).length > 0 ? <div className="group-page-pic-post-container">
                {ownerId === loggedInUserId ?
                    < div className="remove-group">
                        <MdOutlineDelete onClick={openModal} size={35}/>
                    </div> : ""}
                <div className="cover-photo-container">
                    {posts !== undefined && image !== null ? <CoverPhoto photoId={imageId}/> : image === null ?
                        <NoCoverPhoto showGroup={showGroup} setShowGroup={setShowGroup}/> : <div></div>}
                </div>
                <div className="d-flex justify-content-between align-items-center">
                    <span className="group-name fs-4">{showGroup.name}</span>
                </div>
                <div className="friend-list-container">
                    <p className="fs-5">Members</p>
                    {<div className="friend-pics-container-array flex-box">
                        {showGroup.members.map(member => <ProfilePicture key={member.id}
                                                                         profilePictureId={member.profilePictureId}
                                                                         userId={member.id} placement="array-member"/>)}
                    </div>}
                </div>
            <CreatePost loggedInUser={loggedInUser} onAdd={createGroupPostEvent} showGroupId={showGroup.id} placement="group"/>
            </div> : ""}
            {groupPosts !== undefined ? groupPosts?.map(post => <Post loggedInUser={loggedInUser} key={post.id} post={post}
                                                                     onDelete={deletePostEvent}/>) : ""}
        </div>
    );
};

export default ArrayPageRightContainer;
