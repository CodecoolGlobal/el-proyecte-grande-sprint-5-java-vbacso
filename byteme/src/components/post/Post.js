import PostBody from './post-components/PostBody'
import PostHeader from "./post-components/PostHeader";


export const deletePost = async (id) => {
    await fetch(`http://localhost:8080/post/delete/${id}`, {
        method: 'DELETE',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify(id)
    }).catch(console.error)
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
            <PostHeader userName={post.user.name}
                        created={post.created}
                        title={post.title}
                        postId={post.id}
                        userId={post.userId}
                        onDelete={onDelete}
            />
            <PostBody
                postBody={post.body}
            />
            {post.comments?.map((comment) => (
                <div className="comment-container" key={comment.id}>{comment.user.name + ": " + comment.body}</div>
            ))}
        </div>
    )
}

export default Post