import {useState} from 'react'

const CreatePost = ({onAdd}) => {

    const userId = JSON.parse(localStorage.getItem("loggedInUser")).id
    const [title, setName] = useState('');
    const [body, setPostBody] = useState('');
    
    const onSubmit = (e) => {
        e.preventDefault()

        if (!body) {
            alert("hey!")
            return
        }
        onAdd({body,title,userId})
        setName('')
        setPostBody('')
    }

    return (
        <div className="create-new-post-container">
            <form onSubmit={onSubmit}>
                <div className="mb-3">
                    <br/>
                    <label className="form-label"></label>
                    <input type="text" className="form-control" placeholder="Title"
                           value={title} onChange={(e) => setName(e.target.value)}/>
                </div>
                <div className="mb-3">
                    <label htmlFor="exampleFormControlTextarea1" className="form-label"></label>
                    <textarea className="form-control" rows="3" value={body}
                              onChange={(e) => setPostBody(e.target.value)} placeholder="What's in your byte today?"/>
                </div>
                <button type='submit' value='Create Post' className='button button-light'> add new post</button>
            </form>
        </div>
    )
}

export default CreatePost