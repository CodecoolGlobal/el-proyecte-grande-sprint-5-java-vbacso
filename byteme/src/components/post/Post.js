import PostBody from './PostBody'

const Post = ({post, onDelete}) => {
    return (
        <div className="post-card"  >
            <PostBody
                key={post.id+'-body'}
                name={post.username}
                title={post.title}
                postbody={post.body}
                id={post.id}
                userId={post.userId}
                onDelete={onDelete}
            />
            {post.comments.map((comment) => (
                <p key={comment.id}>{comment.body}</p>
            ))}
        </div>
    )
}

export default Post