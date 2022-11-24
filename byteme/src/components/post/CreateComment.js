import {useState} from "react";

const CreateComment = ({onAdd}) => {

    const user = JSON.parse(localStorage.getItem("loggedInUser"))
    const [body, setCommentBody] = useState('')
    const onSubmit = (e) => {
        e.preventDefault()

        if (!body) {
            alert("hey!")
            return
        }
        const id = Math.floor(Math.random() * 2000)
        const key = Math.floor(Math.random() * 2000)
        console.log(user)
        onAdd({body, user, id, key})
        setCommentBody('')
    }

    return (
        <div className="createComment-container">
            <form onSubmit={onSubmit}>
                <div>
                    <label htmlFor="exampleFormControlTextarea1" className="form-label"></label>
                    <textarea className="form-control" rows="3" value={body}
                              onChange={(e) => setCommentBody(e.target.value)}
                              placeholder="What's in your byte today?"/>
                </div>
                <button type={"submit"} className='btn-sm button-sm button-light ms-auto'>Add Comment</button>
            </form>
        </div>
    )
}

export default CreateComment