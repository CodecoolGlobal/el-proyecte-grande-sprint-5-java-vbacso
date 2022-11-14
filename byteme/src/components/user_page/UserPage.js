import '../../App.css';
import {useEffect, useState} from 'react'
import Post, {createPost, deletePost} from '../post/Post'
import CreatePost from '../post/CreatePost';
import EditProfileButton from "./EditProfileButton";
import ProfilePicture from "./ProfilePicture";
import UserDetails from "./UserDetails";

const UserPage = ({loggedInUser, setLoggedInUser,showedUser}) => {

    const [posts, setPosts] = useState([]);

    // Get user posts
    useEffect(() => {
        const getUserPosts = async () => {
            const postsFromServer = await fetchUserPosts();
            setPosts(postsFromServer);
        }
        getUserPosts().catch(console.error);
    }, [showedUser.id])

    // Fetch user posts
    const fetchUserPosts = async () => {
        const res = await fetch(`http://localhost:8080/post/user/${showedUser.id}`)
        return (await res.json()).sort((a, b) => new Date(b.created) - new Date(a.created));
    }

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
        return(<div className="main-container">Loading...</div>)
    } else {
        return (<div>
            <div className="user-page-left-container">
                <ProfilePicture profilePictureId={showedUser.profilePictureId}/>
                <UserDetails showedUser={showedUser}/>
                {loggedInUser.id===showedUser.id?<EditProfileButton loggedInUser={loggedInUser} setLoggedInUser={setLoggedInUser}/>:""}
            </div>
            <div className="user-page-right-container">
                {loggedInUser.id===showedUser.id?<CreatePost onAdd={createPostEvent}/>:""}
                {posts.map((post) => (<Post key={post.id} post={post} onDelete={deletePostEvent}/>))}
            </div>
        </div>)
    }
}

export default UserPage