import {useEffect, useState} from "react";
import CreatePost from "../post/CreatePost";
import Post, {createPost, deletePost, createCommentEvent} from "../post/Post";

const FeedPage = ({loggedInUser}) => {

    const [posts, setPosts] = useState([])

    useEffect(() => {
        const getPosts = async () => {
            const resp = await fetch(`http://localhost:8080/post/feed/${loggedInUser.id}`);
            if (resp.ok) {
                const fetchedPosts = (await resp.json()).sort((a, b) => new Date(b.created) - new Date(a.created));
                setPosts(fetchedPosts);
            }
        }
        getPosts().catch(console.error);
    }, []);

    const createPostEvent = async (input) => {
        const newPost = await createPost(input);
        setPosts([newPost, ...posts]);
    }

    const deletePostEvent = async (id) => {
        await deletePost(id);
        setPosts(posts.filter((p) => p.id !== id))
    }

    return (<div className="post-container">
        <CreatePost onAdd={createPostEvent}/>
        {posts?.map((post) => (<Post key={post.id} post={post} onDelete={deletePostEvent}/>))}
    </div>);
};

export default FeedPage;