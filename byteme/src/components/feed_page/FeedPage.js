import Feed from "./Feed";
import {useEffect, useState} from "react";
import CreatePost from "../post/CreatePost";
import Post from "../post/Post";
import createPost from "../post/CreatePost";

const FeedPage = ({loggedInUserId}) => {
    const [posts, setPosts] = useState([]);
    useEffect(() => {
        const getPosts = async () => {
            const resp = await fetch(`http://localhost:8080/post/friends/${loggedInUserId}`);
            if (resp.ok) {
                const fetchedPosts = await resp.json();
                setPosts(fetchedPosts);
            }
        }
        getPosts().catch(console.error);
    }, []);

    let deletePost;
    return (
        <div>
            <CreatePost onAdd={createPost}/>
            {posts.map((post)=>(
                <Post key={post.id} post={post} onDelete={deletePost}/>
            ))}
            <Feed/>
        </div>
    );
};

export default FeedPage;