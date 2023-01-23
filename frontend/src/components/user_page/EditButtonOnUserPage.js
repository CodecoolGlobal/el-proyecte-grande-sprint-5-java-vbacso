import EditProfileModal from "./EditProfileModal";

const EditButtonOnUserPage = ({loggedInUser, setLoggedInUser, showedUser, setShowedUser}) => {

    function openModal() {
        const modal = document.querySelector("#myModal")
        modal.style.display = "block";
    }

    return (
        <div className="edit-profile-btn-container">
            <button className="button button-light" id="myBtn" onClick={openModal}>Edit Profile</button>
            <EditProfileModal loggedInUser={loggedInUser} setLoggedInUser={setLoggedInUser} showedUser={showedUser}
                              setShowedUser={setShowedUser}/>
        </div>
    );
};

export default EditButtonOnUserPage;
