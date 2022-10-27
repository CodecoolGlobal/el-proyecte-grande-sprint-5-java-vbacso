import { FaTimes } from 'react-icons/fa'

const PostBody = ({ id,title,postbody, onDelete}) => {

  

  

  return (
    
    <div>
        <h3>{title}<FaTimes onClick={()=>onDelete(id)} color="red" cursor='pointer'/></h3>
        <p>{postbody}</p>
    </div>
  )
}

export default PostBody