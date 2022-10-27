import Postbody from './Postbody'

const Poster = ({ posts }) => {
  return (
    <div>
        {posts.map((post) => (
            <Postbody 
              title={post.title}
              postbody={post.body}
            />
        ))}
    </div>
  )
}

export default Comment