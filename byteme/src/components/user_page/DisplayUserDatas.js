import React, {useEffect} from 'react';

const DisplayUserDatas = ({showedUser, setShowedUser}) => {
    useEffect(() => {

    }, [setShowedUser]);

    return (
        <div className="user-details-container">
            <div className="details">Name: {showedUser.name}</div>
            <div className="details">Age: {showedUser.age}</div>
        </div>
    );
};

export default DisplayUserDatas;
