import React from 'react';
import {getAuthenticationToken} from "../../util";

const NoCoverPhoto = ({showGroup, setShowGroup}) => {

    const uploadFile = async () => {
        let formData = new FormData();
        const input = document.querySelector("#fileupload");
        const file = input.files[0];
        formData.append("file", file);

        const imageResponse = await fetch(`/image/upload`, {
            method: "POST",
            headers: {
                "Authorization": getAuthenticationToken()
            },
            body: formData
        });

        if (imageResponse.status === 200) {
            openModal();
            setTimeout(async () => {
                const image = await imageResponse.json();
                showGroup.image = image;
                const groupResponse = await fetch(`/group/edit`, {
                    method: 'PUT',
                    headers: {
                        'Content-type': 'application/json',
                        "Authorization": getAuthenticationToken()
                    },
                    body: JSON.stringify(showGroup)
                });
                const newGroup = await groupResponse.json();
                setShowGroup(newGroup);
            }, 2000);
        }
    };

    const openModal = () => {
        const modal = document.querySelector("#successfully-uploaded-modal");
        modal.style.display = "block";
    };

    return (
        <div className="no-cover-photo">
            <div id="successfully-uploaded-modal" className="modal">
                <div className="modal-content">
                    <p>File successfully uploaded.</p>
                </div>
            </div>
            <h3>No cover photo...</h3>
            <input id="fileupload" type="file" accept="image/jpeg, image/png, image/jpg" name="fileupload"/>
            <button id="upload-button" onClick={uploadFile}> Upload</button>
        </div>
    );
};

export default NoCoverPhoto;
