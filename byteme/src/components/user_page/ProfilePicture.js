import {useEffect, useState} from "react";
import Loading from "../common/Loading";
import {useNavigate} from "react-router-dom";

const ProfilePicture = ({userId, profilePictureId, placement}) => {
    const [image, setImage] = useState();
    const navigate = useNavigate();
    useEffect(() => {
        const fetchImage = async () => {
            const resp = await fetch(`/image/${profilePictureId}`)
            const imageBlob = await resp.blob();
            const imageObjectURL = URL.createObjectURL(imageBlob);
            setImage(imageObjectURL);
        };
        if (profilePictureId) {
            fetchImage().catch(console.error);
        } else {
            setImage("/img.png")
        }
    }, [profilePictureId]);

    const onLoadUserPage = async (e) => {
        e.preventDefault();
        const userId = e.target.dataset.userId;
        navigate("/user/" + userId)
    }

    if (!image) {
        return (<Loading/>)
    } else {
        return (<div onClick={onLoadUserPage}
                     className={(placement === "post") ? "profile-pic-in-post" : "profile-pic-user-page"}>
            <img className="rounded-circle ratio" data-user-id={userId} src={image} alt="Profile Picture"/>
        </div>)
    }
}


export default ProfilePicture;
