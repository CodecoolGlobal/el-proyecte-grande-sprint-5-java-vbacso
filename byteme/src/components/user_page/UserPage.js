import '../../App.css';
import {useEffect, useState} from 'react'
import Post from '../post/Post'
import CreatePost from '../post/CreatePost';

const UserPage = () => {

    const [posts, setPosts] = useState([])

    // Fetch user
    useEffect(() => {
        const getUserPosts = async () => {
            const postsFromServer = await fetchUserPosts()
            setPosts(postsFromServer)
        }

        getUserPosts().catch(console.error)
    }, [])

    const loggedInUserId = JSON.parse(localStorage.getItem("loggedInUser")).id;

    // Fetch user
    const fetchUserPosts = async () => {
        const res = await fetch(`http://localhost:8080/post/user/${loggedInUserId}`)
        return await res.json()
    }

    // Delete Post
    const deletePost = async (id) => {
        const res = await fetch(`http://localhost:8080/user/findById/${loggedInUserId}/delete`, {
            method: 'DELETE',
            headers: {
                'Content-type': 'application/json'
            },
            body: JSON.stringify(id)
        })

        setPosts(posts.filter((p) => p.id !== id))
    }

    // Create Post
    const createPost = async (input) => {
        const res = await fetch(`http://localhost:8080/user/findById/${loggedInUserId}/add`, {
            method: 'POST',
            headers: {
                'Content-type': 'application/json'
            },
            body: JSON.stringify(input)
        })
        const data = await res.json();
        setPosts(data);
    }

    if (!posts) {
        console.log("loading...")
    } else {

        return (
            <div className="user-page-container">
                <CreatePost onAdd={createPost}/>
                <div>
                    {posts.map((post) => (
                        <Post key={post.id} post={post} onDelete={deletePost}/>
                    ))}
                </div>
            </div>
        )
    }
}

export default UserPage