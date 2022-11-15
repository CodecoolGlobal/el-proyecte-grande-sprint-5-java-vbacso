const InteractionBar = ({toggle, status}) => {

    return (
        <div className={status ? 'int-container d-flex' : 'int-container d-flex last'}>
            <button className='btn-sm button-sm button-light ms-auto'>
                <p onClick={toggle}>Comments</p>
            </button>
        </div>
    )
}

export default InteractionBar