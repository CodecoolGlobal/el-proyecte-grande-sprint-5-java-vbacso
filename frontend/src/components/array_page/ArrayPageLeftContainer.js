import {TiGroupOutline} from "react-icons/ti";
import {BiPlus, BiSearchAlt2} from "react-icons/bi";
import Array from "./Array";
import CreateNewGroup from "./CreateNewGroup";

const ArrayPageLeftContainer = ({groups, setGroups, loggedInUser, setShowGroup}) => {

    // Open Create new Group Modal
    const createNewGroup = () => {
        const modal = document.querySelector("#myModal")
        modal.style.display = "block";
    };

    if (groups.length >= 0) {
        return (
            <div className="user-page-left-container">
                <div className="arrays-container d-flex flex-column flex-shrink-0 p-3 text-white bg-dark side-bar">
                    <div className="d-flex justify-content-between align-items-center">
                        <span className="fs-4">Arrays</span>
                        <TiGroupOutline/>
                    </div>
                    <hr/>
                    <ul className="nav nav-pills flex-column mb-auto">
                        <li className="nav-item">
                            <a onClick={createNewGroup} className="nav-link text-white"><BiPlus/>Create new group</a>
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
                        <div className="nav nav-pills flex-column mb-auto">
                            <div className="nav-item">
                                <h5>Groups managed by you</h5>
                            </div>
                            {groups.map(group => <Array key={group.id} group={group} loggedInUser={loggedInUser}
                                                        placement="owner" setShowGroup={setShowGroup}/>)}
                        </div>
                    </div>
                    <hr/>
                    <div className="d-flex">
                        <ul className="nav nav-pills flex-column mb-auto">
                            <li className="nav-item">
                                <h5>Groups you have joined</h5>
                            </li>
                            {groups.map(group => <Array key={group.id} group={group} loggedInUser={loggedInUser}
                                                        placement="member" setShowGroup={setShowGroup}/>)}
                        </ul>
                    </div>
                </div>
                <CreateNewGroup loggedInUser={loggedInUser} groups={groups} setGroups={setGroups}/>
            </div>
        );
    }
};

export default ArrayPageLeftContainer;
