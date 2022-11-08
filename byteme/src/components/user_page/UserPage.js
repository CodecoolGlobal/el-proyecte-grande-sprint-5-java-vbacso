import '../../App.css';
import {useEffect, useState} from 'react'
import Post, {createPost, deletePost} from '../post/Post'
import CreatePost from '../post/CreatePost';

const UserPage = () => {

    const [posts, setPosts] = useState([])

    // Fetch user
    useEffect(() => {
        const getUserPosts = async () => {
            const postsFromServer = await fetchUserPosts();
            setPosts(postsFromServer);
        }
        getUserPosts().catch(console.error);
    }, [])

    const loggedInUserId = JSON.parse(localStorage.getItem("loggedInUser")).id;

    // Fetch user
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

    if (!posts) {
        console.log("loading...")
    } else {

        return (<div className="user-page-container">
            <CreatePost onAdd={createPostEvent}/>
            {posts.map((post) => (<Post key={post.id} post={post} onDelete={deletePostEvent}/>))}
        </div>)
    }
}

export default UserPage