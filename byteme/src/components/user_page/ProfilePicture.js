const ProfilePicture = (image) => {
    return (
        <div className="profile-pic-user-page">
            <img src={image.image} alt="Profile Picture"/>
        </div>
    );
};

export default ProfilePicture;