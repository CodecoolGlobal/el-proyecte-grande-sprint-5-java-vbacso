import {BiSearchAlt2} from "react-icons/bi";
import {useEffect, useState} from "react";

const SearchBar = () => {

    const [searchResultUsers, setSearchResultUsers] = useState();
    useEffect(() => {

    });
    const fetchSearchResult = async (param) => {
        console.log("search: " + param)
    };

    const onChangeEvent = (e) => {
        e.preventDefault();
        const text = e.target.value.trim();
        if (text) {
            fetchSearchResult(text).catch(console.error)
        }
    };

    return (
        <div className="input-group input-group-sm searchbar">
            <input type="text" className="form-control" placeholder="Write here to search" onChange={onChangeEvent}/>
            <button className="btn btn-sm button-search"><BiSearchAlt2/></button>
        </div>
    );
};

export default SearchBar;
