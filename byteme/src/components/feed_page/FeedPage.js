import Feed from "./Feed";
import {useEffect, useState} from "react";

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

    return (
        <div>
            {posts.map((post) => (
                <div className="post" key={post.id}>
                    <p>{post.username}</p>
                    <p>{post.title}</p>
                </div>
            ))}
            <Feed/>
        </div>
    );
};

export default FeedPage;