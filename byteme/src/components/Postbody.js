import { FaTimes } from 'react-icons/fa'

const Postbody = ({ id,title,postbody, onDelete }) => {

  

  

  return (
    
    <div>
        <h3 key={id}>{title}<FaTimes onClick={()=>onDelete(id)} color="red" cursor='pointer'/></h3>
        <p>{postbody}</p>
    </div>
  )
}

export default Postbody