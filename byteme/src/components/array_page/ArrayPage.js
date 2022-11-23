import {BiSearchAlt2} from "react-icons/bi";
import {TiGroupOutline} from "react-icons/ti";
import {BiPlus} from "react-icons/bi";
import {useEffect, useState} from "react";
import Array from "./Array";

const ArrayPage = ({loggedInUser, showedUser}) => {

    const [groups, setGroups] = useState([]);

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
            <div className="d-flex flex-column flex-shrink-0 p-3 text-white bg-dark side-bar">
                <div className="d-flex justify-content-between align-items-center">
                    <span className="fs-4">Arrays</span>
                    <TiGroupOutline/>
                </div>
                <hr/>
                <ul className="nav nav-pills flex-column mb-auto">
                    <li className="nav-item">
                        <a className="nav-link text-white"><BiPlus/>Create new group</a>
                    </li>
                    <li className="nav-item">
                        <div className="group-search-container">
                            <div className="input-group input-group-sm searchbar-input">
                                <input id="search-text" type="text" className="form-control"
                                       placeholder="Search on byte.Array"/>
                                <button className="btn btn-sm button-search"><BiSearchAlt2/></button>
                            </div>
                        </div>
                    </li>
                </ul>
                <hr/>
                <div className="d-flex justify-content-between align-items-center">
                    <ul className="nav nav-pills flex-column mb-auto">
                        <li className="nav-item">
                            <h5>Groups managed by you</h5>
                        </li>
                        {groups.map(group => <Array key={group.id} group={group} showedUser={showedUser}
                                                    placement="owner"/>)}
                    </ul>
                </div>
                <hr/>
                <div className="d-flex">
                    <ul className="nav nav-pills flex-column mb-auto">
                        <li className="nav-item">
                            <h5>Groups you have joined</h5>
                        </li>
                        {groups.map(group => <Array key={group.id} group={group} showedUser={showedUser}
                                                    placement="member"/>)}
                    </ul>
                </div>
            </div>
        </div>
    );
}

export default ArrayPage;