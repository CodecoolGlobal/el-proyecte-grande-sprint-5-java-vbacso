import {useEffect, useState} from "react";
import Loading from "../common/Loading";
import {getAuthenticationToken} from "../../util";

const CoverPhoto = ({photoId}) => {
    const [image, setImage] = useState();

    useEffect(() => {
        const fetchImage = async () => {
            const resp = await fetch(`/image/${photoId}`, {
                headers: {
                    "Authorization": getAuthenticationToken()
                }
            });
            const imageBlob = await resp.blob();
            const imageObjectURL = URL.createObjectURL(imageBlob);
            setImage(imageObjectURL);
        };
        if (photoId) {
            fetchImage().catch(console.error);
        } else {
            setImage("/img.png")
        }
    }, [photoId]);
    if (!image) {
        return (<Loading/>)
    } else {
        return (<>
            <img src={image} alt="Cover Picture"/>
        </>)
    }
};

export default CoverPhoto;