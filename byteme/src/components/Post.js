import Postbody from './Postbody'

const Post = ({ user, onDelete }) => {
  return (
    <div className='container'>
        {user.posts.map((p, index) => (
            <Postbody 
              key={index}
              name={user.name}
              title={p.title}
              postbody={p.body}
              id={user.id}
              onDelete={''}
            />
        ))}
    </div>
  )
}

export default Post