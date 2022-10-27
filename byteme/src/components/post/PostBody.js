import { FaTimes } from 'react-icons/fa'

const PostBody = ({ id,title,postbody, onDelete,userId}) => {
const loggedInUserId=JSON.parse(localStorage.getItem("loggedInUser")).id
  return (
    
    <div>
        <h3 className='post-title'>{title}{loggedInUserId===userId ? <FaTimes onClick={()=>onDelete(id)} className='delete-icon' cursor='pointer'/>:''}</h3>
        <p className='post-body'>{postbody}</p>
    </div>
  )
}

export default PostBody