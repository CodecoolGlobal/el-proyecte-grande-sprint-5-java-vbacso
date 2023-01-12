import '../../App.css';
import {useEffect, useState} from 'react'
import Post, {createPost, deletePost} from '../post/Post'
import CreatePost from '../post/CreatePost';
import EditButtonOnUserPage from "./EditButtonOnUserPage";
import ProfilePicture from "./ProfilePicture";
import UserDetails from "./UserDetails";
import Loading from "../common/Loading";
import Friend from "./Friend";
import {useParams} from "react-router-dom";
import {getAuthenticationToken} from "../../util";

const UserPage = ({loggedInUser, setLoggedInUser, showedUser, setShowedUser}) => {

    const [posts, setPosts] = useState([]);
    const params = useParams();

    // Get user posts
    useEffect(() => {
        const getShowedUser = async () => {
            const res = await fetch(`/user/findById/${params.userId}`,
                {
                    headers: {
                        "Authorization": getAuthenticationToken()
                    }
                });
            setShowedUser(await res.json())
        }

        // Fetch user posts
        const fetchUserPosts = async () => {
            const res = await fetch(`/post/user/${showedUser.id}`,
                {
                    headers: {
                        "Authorization": getAuthenticationToken()
                    }
                })
            return (await res.json()).sort((a, b) => new Date(b.created) - new Date(a.created));
        }

        const getUserPosts = async () => {
            const postsFromServer = await fetchUserPosts();
            setPosts(postsFromServer);
        }

        getShowedUser().catch(console.error).then(() => {
            getUserPosts().catch(console.error);
        });
    }, [setShowedUser, params.userId, showedUser.id])

    // Delete Post
    const deletePostEvent = async (id) => {
        await deletePost(id);
        setPosts(posts.filter((p) => p.id !== id))
    }

    // Create Post
    const createPostEvent = async (input) => {
        const newPost = await createPost(input);
        setPosts([newPost, ...posts]);
    }

    if (!posts || !showedUser) {
        return (<Loading/>)
    } else {
        return (<div>
            <div className="user-page-left-container">
                <ProfilePicture profilePictureId={showedUser.profilePictureId} userId={showedUser.id}/>
                <UserDetails showedUser={showedUser} setShowedUser={setShowedUser}/>
                {loggedInUser.id === showedUser.id ?
                    <EditButtonOnUserPage loggedInUser={loggedInUser} setLoggedInUser={setLoggedInUser}
                                          showedUser={showedUser}
                                          setShowedUser={setShowedUser}/> : ""}
                <Friend loggedInUser={loggedInUser} setLoggedInUser={setLoggedInUser} showedUser={showedUser}
                        setShowedUser={setShowedUser}/>
            </div>
            <div className="user-page-right-container">
                {loggedInUser.id === showedUser.id ?
                    <CreatePost onAdd={createPostEvent} loggedInUser={loggedInUser}/> : ""}
                {posts.map((post) => (
                    <Post key={post.id} loggedInUser={loggedInUser} post={post} onDelete={deletePostEvent}/>))}
            </div>
        </div>)
    }
}

export default UserPage
