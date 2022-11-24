import Post from "../post/Post";

const ArrayPageRightContainer = ({showGroup}) => {
    const posts = showGroup.posts;
    return (
        <div className="user-page-right-container">
            {posts !== undefined ? posts.map(post => <Post key={post.id} post={post}/>) : ""}
        </div>
    );
};

export default ArrayPageRightContainer;