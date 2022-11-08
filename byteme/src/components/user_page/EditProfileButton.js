const EditProfileButton = ({loadEditProfile}) => {
    return (
        <div>
            <button className="button-dark button" onClick={loadEditProfile}>Edit profile</button>
        </div>
    );
};

export default EditProfileButton;