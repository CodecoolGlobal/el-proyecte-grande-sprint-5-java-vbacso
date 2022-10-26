import { useState } from 'react'

const CreatePost = ({ onAdd }) => {

    const [title, setName] = useState();
    const [body, setPostBody] = useState();
    
    const onSubmit = (e) => {
        e.preventDefault()

        if(!body){
            alert("hey!")
            return
        }
        onAdd({body,title})
        setName('')
        setPostBody('')
    }

  return (
    <form onSubmit={onSubmit}>
        <button type='submit' value='Create Post' className='btn btn-primary'>Create Post</button>
        <div className="mb-3">
            <br />
            <label className="form-label">Title</label>
            <input type="text" className="form-control" placeholder="Title"
            value={title} onChange={(e)=> setName(e.target.value)}/>
        </div>
        <div className="mb-3">
            <label htmlFor="exampleFormControlTextarea1" className="form-label"></label>
            <textarea className="form-control" rows="3" value={body}
            onChange={(e)=> setPostBody(e.target.value)}/>
        </div>
    </form>
  )
}

export default CreatePost