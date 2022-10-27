import PostBody from './post-components/PostBody'
import PostHeader from "./post-components/PostHeader";


export const deletePost = async (id) => {
    const res = await fetch(`http://localhost:8080/post/delete/${id}`, {
        method: 'DELETE',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify(id)
    })
}

export const createPost = async (input) => {
    const res = await fetch(`http://localhost:8080/post/add`, {
        method: 'POST',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify(input)
    })
    return await res.json();
}


const Post = ({post, onDelete}) => {
    return (
        <div className="post-card">
            <PostHeader userName={post.username}
                        created={post.created}
                        title={post.title}
                        postId={post.id}
                        userId={post.userId}
                        onDelete={onDelete}
            />
            <PostBody
                postBody={post.body}
            />
            {post.comments.map((comment) => (
                <div className="comment-container" key={comment.id}>{comment.username + ": " + comment.body}</div>
            ))}
        </div>
    )
}

export default Post