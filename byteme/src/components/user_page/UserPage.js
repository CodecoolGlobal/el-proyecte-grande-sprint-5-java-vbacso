import '../../App.css';
import {useEffect, useState} from 'react'
import Post, {createPost, deletePost} from '../post/Post'
import CreatePost from '../post/CreatePost';
import EditProfileButton from "./EditProfileButton";
import ProfilePicture from "./ProfilePicture";

const UserPage = ({loadEditProfile}) => {

    const [posts, setPosts] = useState([]);
    const [user, setUser] = useState(null);

    // Get user
    useEffect(() => {
        const getUser = async () => {
            const res = await fetchUser();
            setUser(res);
        }
        getUser().catch(console.error);
    }, [])

    // Fetch user
    const fetchUser = async () => {
        const res = await fetch(`http://localhost:8080/user/findById/${loggedInUserId}`);
        return (await res.json());
    }

    // Get user posts
    useEffect(() => {
        const getUserPosts = async () => {
            const postsFromServer = await fetchUserPosts();
            setPosts(postsFromServer);
        }
        getUserPosts().catch(console.error);
    }, [])

    const loggedInUserId = JSON.parse(localStorage.getItem("loggedInUser")).id;

    // Fetch user posts
    const fetchUserPosts = async () => {
        const res = await fetch(`http://localhost:8080/post/user/${loggedInUserId}`)
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
        console.log("loading...")
    } else {
        return (<div className="user-page-container">
            <CreatePost onAdd={createPostEvent}/>
            {posts.map((post) => (<Post key={post.id} post={post} onDelete={deletePostEvent}/>))}
            <EditProfileButton loadEditProfile={loadEditProfile}/>
            <ProfilePicture image={user.profilePic}/>
        </div>)
    }
}

export default UserPage