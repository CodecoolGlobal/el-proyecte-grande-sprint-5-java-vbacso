import {BiSearchAlt2} from "react-icons/bi";
import {useState} from "react";

const SearchBar = ({onSetUserId}) => {

    const [searchResultUsers, setSearchResultUsers] = useState();

    const fetchSearchResult = async (param) => {
        console.log("search: " + param);
        const resp = await fetch(`http://localhost:8080/user/search/${param}`)
        if (resp.ok) {
            const results = await resp.json()
            console.log(await results)
            setSearchResultUsers(results);
        }
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

    const onClickSearchResult = (e) => {
        e.preventDefault();
        onSetUserId(e.target.dataset.userId)
        setSearchResultUsers();
        document.querySelector("#search-text").value = "";
    };

    return (
        <div className="searchbar-position-fixer">
            <div className="searchbar-container">
                <div className="input-group input-group-sm searchbar-input">
                    <input id="search-text" type="text" className="form-control" placeholder="Search on byte.me"
                           onChange={onChangeEvent}/>
                    <button className="btn btn-sm button-search"><BiSearchAlt2/></button>
                </div>
                <div className="searchbar-results-container">
                    <ul className="searchbar-results-list">
                        {searchResultUsers?.map((entry) => (
                            <a key={entry.id} data-user-id={entry.id} onClick={onClickSearchResult}>
                                <li data-user-id={entry.id}
                                    className="searchbar-results-item">{entry.name}
                                </li>
                            </a>
                        ))}
                    </ul>
                </div>
            </div>
        </div>
    );
};

export default SearchBar;
