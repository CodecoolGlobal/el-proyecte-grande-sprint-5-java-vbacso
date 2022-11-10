import '../../App.css';
import {useEffect, useState} from 'react'
import Post, {createPost, deletePost} from '../post/Post'
import CreatePost from '../post/CreatePost';
import EditProfileButton from "./EditProfileButton";
import ProfilePicture from "./ProfilePicture";
import UserDetails from "./UserDetails";

const UserPage = ({userId}) => {

    const loggedInUserId = JSON.parse(localStorage.getItem("loggedInUser")).id;
    const [posts, setPosts] = useState([]);
    const [user, setUser] = useState(null);

    // Get user
    useEffect(() => {
        const getUser = async () => {
            const res = await fetchUser();
            setUser(res);
        }
        getUser().catch(console.error);
    }, [userId])

    // Fetch user
    const fetchUser = async () => {
        const res = await fetch(`http://localhost:8080/user/findById/${userId}`);
        return (await res.json());
    }

    // Get user posts
    useEffect(() => {
        const getUserPosts = async () => {
            const postsFromServer = await fetchUserPosts();
            setPosts(postsFromServer);
        }
        getUserPosts().catch(console.error);
    }, [userId])

    // Fetch user posts
    const fetchUserPosts = async () => {
        const res = await fetch(`http://localhost:8080/post/user/${userId}`)
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

    if (!posts || !user) {
        return(<div className="main-container">Loading...</div>)
    } else {
        return (<div>
            <div className="user-page-left-container">
                <ProfilePicture profilePictureId={user.profilePictureId}/>
                <UserDetails userId={userId}/>
                {loggedInUserId===userId?<EditProfileButton/>:""}
            </div>
            <div className="user-page-right-container">
                {loggedInUserId===userId?<CreatePost onAdd={createPostEvent}/>:""}
                {posts.map((post) => (<Post key={post.id} post={post} onDelete={deletePostEvent}/>))}
            </div>
        </div>)
    }
}

export default UserPage