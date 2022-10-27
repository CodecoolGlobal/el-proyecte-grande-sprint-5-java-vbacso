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
            {new Date(post.created).toLocaleTimeString('en', {
                year: 'numeric',
                month: 'long',
                day: 'numeric',
                hour12: false
            })}
            <PostHeader userName={post.username}
                        title={post.title}
                        postId={post.id}
                        userId={post.userId}
                        onDelete={onDelete}
            />
            <PostBody
                postBody={post.body}
            />
            {post.comments.map((comment) => (
                <p key={comment.id}>{comment.body}</p>
            ))}
        </div>
    )
}

export default Post