const EditProfileButton = ({loadEditProfile}) => {
    return (
        <div className="edit-profile-btn-container">
            <button className="button-dark button" onClick={loadEditProfile}>Edit profile</button>
        </div>
    );
};

export default EditProfileButton;