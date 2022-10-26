import Postbody from './Postbody'

const Post = ({ user, onDelete }) => {
  return (
    <div className='container'>
            <Postbody 
              key={user.id}
              name={user.name}
              title={user.posts.map((post)=>{return post.title})}
              postbody={user.posts.map((post)=>{return post.body})}
              id={user.id}
              onDelete={''}
            />

    </div>
  )
}

export default Post