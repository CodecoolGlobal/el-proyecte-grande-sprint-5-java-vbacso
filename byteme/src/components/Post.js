import Postbody from './Postbody'

const Post = ({ postsMain, onDelete }) => {
  return (
    <div className='container'>
        {postsMain.map((post, index) => (
            <Postbody 
              key={index}
              title={post.name}
              postbody={post.posts.map((p) => {return p.body})}
              id={post.id}
              onDelete={onDelete}
            />
        ))}
    </div>
  )
}

export default Post