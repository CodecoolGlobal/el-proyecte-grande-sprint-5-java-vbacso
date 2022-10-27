import {useEffect, useState} from "react";
import CreatePost from "../post/CreatePost";
import Post from "../post/Post";

const FeedPage = ({loggedInUserId}) => {

    const [posts, setPosts] = useState([])

    useEffect(() => {
        const getPosts = async () => {
            const resp = await fetch(`http://localhost:8080/post/feed/${loggedInUserId}`);
            if (resp.ok) {
                const fetchedPosts = await resp.json();
                setPosts(fetchedPosts);
            }
        }
        getPosts().catch(console.error);
    }, []);

    const createPost = async (input) => {
        const res = await fetch(`http://localhost:8080/post/add`, {
            method: 'POST',
            headers: {
                'Content-type': 'application/json'
            },
            body: JSON.stringify(input)
        })
        const newPost = await res.json();
        setPosts([...posts,newPost]);
    }

    let deletePost;
    return (
        <div>
            <CreatePost onAdd={createPost}/>
            {posts.map((post) => (
                <Post key={post.id} post={post} onDelete={deletePost}/>
            ))}
        </div>
    );
};

export default FeedPage;