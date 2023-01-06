import {useState} from "react";

const CreateComment = ({loggedInUser, post, onAdd}) => {

    const [commentBody, setCommentBody] = useState('')

    const onSubmit = (e) => {
        e.preventDefault()
        if (!commentBody) {
            alert("hey!")
            return
        }
        const newComment = {
            'body': commentBody,
            'user': loggedInUser,
            'post': {'id': post.id}
        }
        onAdd(newComment)
        setCommentBody('')
    }

    return (
        <div className="createComment-container">
            <form onSubmit={onSubmit}>
                <div>
                    <label htmlFor="exampleFormControlTextarea1" className="form-label"></label>
                    <textarea className="form-control" rows="3" value={commentBody}
                              onChange={(e) => setCommentBody(e.target.value)}
                              placeholder="What's in your byte today?"/>
                </div>
                <button type={"submit"} className='btn-sm button-sm button-light ms-auto'>Add Comment</button>
            </form>
        </div>
    )
}

export default CreateComment