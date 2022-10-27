import PostBody from './PostBody'

const Post = ({post, onDelete}) => {
    return (
        <div className='container'>
            <PostBody
                key={post.id+'-body'}
                name={post.username}
                title={post.title}
                postbody={post.body}
                id={post.id}
                onDelete={onDelete}
            />
        </div>
    )
}

export default Post