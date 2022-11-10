import Comments from './Comments'

const InteractionBar = ({ toggle, status }) => {

    return (
        <div className={status ? 'int-container' : 'int-container last'}>
            <div className='comment-box'>
                <p onClick={toggle}>Comments</p>
            </div>
        </div>
            )
}

export default InteractionBar