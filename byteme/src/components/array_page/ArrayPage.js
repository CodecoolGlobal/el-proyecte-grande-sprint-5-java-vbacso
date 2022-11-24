import {useEffect, useState} from "react";
import ArrayPageLeftContainer from "./ArrayPageLeftContainer";
import ArrayPageRightContainer from "./ArrayPageRightContainer";

const ArrayPage = ({loggedInUser, showedUser}) => {

    const [groups, setGroups] = useState([]);

    const [showGroup, setShowGroup] = useState([]);

    useEffect(() => {
        const getGroups = async () => {
            const resp = await fetch(`http://localhost:8080/group/user/${loggedInUser.id}`);
            if (resp.ok) {
                setGroups(await resp.json());
            }
        };
        getGroups().catch(console.error);
    }, []);

    return (
        <div className="group-main-container">
            <ArrayPageLeftContainer groups={groups} showedUser={showedUser} setShowGroup={setShowGroup}/>
            {showGroup ? <ArrayPageRightContainer showGroup={showGroup}/> : ""}
        </div>
    );
}

export default ArrayPage;