import PostBody from './post-components/PostBody'
import PostHeader from "./post-components/PostHeader";
import InteractionBar from "./post-components/InteractionBar";
import {useState} from "react";
import Comment, { deleteComment, createComment } from "./post-components/Comment";
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
    const [comments, setComments] = useState([])

    const deleteCommentEvent = async (id) => {
        console.log(id)
        await deleteComment(id);
    }

    const createCommentEvent = async (input) => {
        post.comments.push(input)
        await createComment(post)
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
            {showComments && post.comments?.map((comment, index) => (
                <Comment key={index}
                         postId={comment.id}
                         name={comment.user.name}
                         body={comment.body}
                         profilePictureID={comment.user.profilePictureId}

                         last={null}
                         onDeleteCom={deleteCommentEvent}



                         userId={comment.user.id}

                />
            ))}
            {showComments ? (
                <CreateComment onAdd={createCommentEvent}/>
            ) : null}

        </div>
    )
}

export default Post