import { FaTimes } from 'react-icons/fa'

const Postbody = ({ id,title,postbody, onDelete, key }) => {

  

  

  return (
    
    <div>
        <h3 key={key}>{title}<FaTimes onClick={()=>onDelete(id)} color="red" cursor='pointer'/></h3>
        <p>{postbody}</p>
    </div>
  )
}

export default Postbody