const Comment = ({  name,body, last }) => {
    return (
        <div className={last ? 'comments-container' : 'comments-container last'} style={{color:'white'}}>
            <p>{name}</p>
            <p>{body}</p>
        </div>
    )
}

export default Comment