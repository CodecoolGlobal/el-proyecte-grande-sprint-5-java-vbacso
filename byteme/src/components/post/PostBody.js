import { FaTimes } from 'react-icons/fa'

const PostBody = ({ id,title,postbody, onDelete}) => {

  return (
    
    <div>
        <h3 className='post-title'>{title}<FaTimes onClick={()=>onDelete(id)} className='delete-icon' cursor='pointer'/></h3>
        <p className='post-body'>{postbody}</p>
    </div>
  )
}

export default PostBody