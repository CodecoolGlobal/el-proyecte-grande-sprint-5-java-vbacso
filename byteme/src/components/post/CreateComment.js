const createComment = () => {
    return (
        <div className="createComment-container">
            <form>
                <textarea className="text" rows="3"
                       placeholder="What's in your byte today?"/>
                <button className='btn-sm button-sm button-light ms-auto'>Add Comment</button>
            </form>
        </div>
    )
}

export default createComment