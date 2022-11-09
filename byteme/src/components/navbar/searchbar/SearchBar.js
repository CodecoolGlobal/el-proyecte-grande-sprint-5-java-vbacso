import {BiSearchAlt2} from "react-icons/bi";
import {useEffect, useState} from "react";

const SearchBar = () => {

    const [searchResultUsers, setSearchResultUsers] = useState();
    useEffect(() => {
    });
    const fetchSearchResult = async (param) => {
        console.log("search: " + param);
        setSearchResultUsers([
            {"id": 1, "name": "result1"},
            {"id": 2, "name": "result2"}
        ]);
    };

    const onChangeEvent = (e) => {
        e.preventDefault();
        const text = e.target.value.trim();
        if (text) {
            fetchSearchResult(text).catch(console.error);
        } else {
            setSearchResultUsers();
        }
    };

    return (
        <div className="searchbar-position-fixer">
        <div className="searchbar-container">
            <div className="input-group input-group-sm searchbar-input">
                <input type="text" className="form-control" placeholder="Search on byte.me" onChange={onChangeEvent}/>
                <button className="btn btn-sm button-search"><BiSearchAlt2/></button>
            </div>
                <div className="searchbar-results-container">
                    <ul className="searchbar-results-list">
                        {searchResultUsers?.map((entry) => (
                            <li key={entry.id} className="searchbar-results-item">{entry.name}</li>))}
                    </ul>
                </div>
            </div>
        </div>
    );
};

export default SearchBar;
