import {useState} from "react";

const CreateComment = ({postId, setComments, comments}) => {

    const user = JSON.parse(localStorage.getItem("loggedInUser"));
    const [body, setCommentBody] = useState('');

    const fetchToServer = async () => {
        const input = {body, user, postId};
        const res = await fetch(`http://localhost:8080/comment/add/${postId}`, {
            method: 'POST',
            headers: {
                'Content-type': 'application/json'
            },
            body: JSON.stringify(input)
        });
        return await res.json();
    };

    const onSubmit = async (e) => {
        e.preventDefault();
        const newComment = await fetchToServer();
        setComments([newComment, ...comments]);
    };


    return (
        <div className="createComment-container">
            <form onSubmit={onSubmit}>
                <textarea className="text" rows="3"
                          placeholder="What's in your byte today?" value={body}
                          onChange={(e) => setCommentBody(e.target.value)}/>
                <button className='btn-sm button-sm button-light ms-auto'>Add Comment</button>
            </form>
        </div>
    )
}

export default CreateComment;
