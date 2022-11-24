import ProfilePicture from "./ProfilePicture";
import EditButton from "./EditButton";
import Loading from "../common/Loading";

const EditProfileModal = ({loggedInUser, setLoggedInUser}) => {

    // Close Modal
    function closeModal() {
        const modal = document.querySelector("#myModal")
        modal.style.display = "none";
    }

    // Save changes
    function saveProfileChanges() {
        const modal = document.querySelector(".modal");
        const userId = modal.dataset.id;

        const savedUserName = document.querySelector(".user-data-name").textContent;
        const savedUserAge = document.querySelector(".user-data-age").textContent;
        const updateUser = {"id": userId, "name": savedUserName, "age": savedUserAge};
        setLoggedInUser({...loggedInUser, "name": savedUserName, "age": savedUserAge});

        const saveUserData = async (user) => {
            const resp = fetch('http://localhost:8080/user/update', {
                method: "PUT",
                headers: {
                    'Content-type': 'application/json'
                },
                body: JSON.stringify(user)
            });
            return (await resp).json();
        };
        saveUserData(updateUser).catch(console.error);
        closeModal();
    }

    if (!loggedInUser) {
        return (<Loading/>)
    } else {
        return (
            <div id="myModal" className="modal" data-id={loggedInUser.id}>
                <div className="modal-content">
                    <div className="modal-header">
                        <span className="close" onClick={closeModal}>&times;</span>
                        <h3 id="modal-title">Edit your profile</h3>
                    </div>
                    <div className="modal-body">
                        <div id="user-profile-picture">
                            <p className="datas-to-edit">Profile Picture</p>
                            <ProfilePicture profilePictureId={loggedInUser.profilePictureId} userId={loggedInUser.id}/>
                        </div>
                        <div id="user-name" className="edit-user-details">
                            <p className="datas-to-edit">Name</p>
                            <p className="user-data-name">{loggedInUser.name}</p>
                            <EditButton dataset="name"/>
                        </div>
                        <div id="user-age" className="edit-user-details">
                            <p className="datas-to-edit">Age</p>
                            <p className="user-data-age">{loggedInUser.age}</p>
                            <EditButton dataset="age"/>
                        </div>
                    </div>
                    <div className="modal-footer">
                        <button type="button" className="button button-dark" id="close-modal-btn" data-dismiss="modal"
                                onClick={closeModal}>Close
                        </button>
                        <button type="button" className="button button-light" id="save-changes-btn"
                                onClick={saveProfileChanges}>Save changes
                        </button>
                    </div>
                </div>
            </div>
        );
    }
};

export default EditProfileModal;