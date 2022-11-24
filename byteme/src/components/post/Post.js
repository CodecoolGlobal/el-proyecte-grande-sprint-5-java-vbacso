import PostBody from './post-components/PostBody'
import PostHeader from "./post-components/PostHeader";
import InteractionBar from "./post-components/InteractionBar";
import {useState} from "react";
import Comment, {createComment, deleteComment} from "./post-components/Comment";
import CreateComment from "./CreateComment";


export const deletePost = async (id) => {
    await fetch(`http://localhost:8080/post/delete/${id}`, {
        method: 'DELETE',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify(id)
    }).catch(console.error)
}

export const createPost = async (input) => {
    const res = await fetch(`http://localhost:8080/post/add`, {
        method: 'POST',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify(input)
    })
    return await res.json();
}


const Post = ({loggedInUser, post, onDelete}) => {


    const [showComments, setShowComments] = useState(false);
    const [comments, setComments] = useState(post.comments ? post.comments : [])

    const deleteCommentEvent = async (id) => {
        await deleteComment(id);
        setComments(comments.filter((c) => c.id !== id))
    }

    const createCommentEvent = async (input) => {
        const newComment = await createComment(input)
        setComments([...comments, newComment])
    }
    return (
        <div className={showComments ? 'post-card open' : 'post-card'}>
            <PostHeader userName={post.user.name}
                        created={post.created}
                        title={post.title}
                        postId={post.id}
                        userId={post.user.id}
                        onDelete={onDelete}
                        profilePictureId={post.user.profilePictureId}
                        loggedInUser={loggedInUser}
            />
            <PostBody
                postBody={post.body}
            />

            <InteractionBar
                toggle={() => setShowComments(!showComments)}
                status={post.comments < 1 ? null : showComments}
            />
            <div className='comments-wrapper'>
                {showComments && comments?.map((comment, index) => (
                    <Comment key={index}
                             commentId={comment.id}
                             name={comment.user.name}
                             body={comment.body}
                             profilePictureID={comment.user.profilePictureId}
                             onDeleteCom={deleteCommentEvent}
                             loggedInUserId={loggedInUser.id}
                             userId={comment.user.id}
                    />
                ))}
            </div>
            {showComments ? (
                <CreateComment loggedInUser={loggedInUser} post={post} onAdd={createCommentEvent}/>
            ) : null}

        </div>
    )
}

export default Post