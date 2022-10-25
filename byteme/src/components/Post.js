import Postbody from './Postbody'

const Post = ({ posts, onDelete }) => {
  return (
    <div className='container'>
        {posts.map((post) => (
            <Postbody 
              title={post.title}
              postbody={post.body}
              id={post.id}
              onDelete={onDelete}
            />
        ))}
    </div>
  )
}

export default Post