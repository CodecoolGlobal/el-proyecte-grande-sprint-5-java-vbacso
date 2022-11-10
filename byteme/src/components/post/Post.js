import PostBody from './post-components/PostBody'
import PostHeader from "./post-components/PostHeader";
import InteractionBar from "./post-components/InteractionBar";
import {useState} from "react";
import Comment from "./post-components/Comment";
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


const Post = ({post, onDelete}) => {

    const [showComments, setShowComments] = useState(false);

    return (
        <div className={showComments ? 'post-card open' : 'post-card'}>
            <PostHeader userName={post.user.name}
                        created={post.created}
                        title={post.title}
                        postId={post.id}
                        userId={post.user.id}
                        onDelete={onDelete}
            />
            <PostBody
                postBody={post.body}
            />

            <InteractionBar
                toggle={()=>setShowComments(!showComments)}
                status={post.comments == null ? null : showComments}
            />
            {showComments && post.comments?.map((comment,index) => (
                <Comment key={index}
                         name={comment.user.name}
                         body={comment.body}
                         last={post.comments.slice(-1).map((lll) => lll.id).toString() !== comment.id.toString()}

                />
            ))}
            {showComments ? (
                <CreateComment />
            ):null}

        </div>
    )
}

export default Post