import { FavoriteBorder, StarBorder } from "@material-ui/icons";
import React from "react";
import MediaQuery from "react-responsive";
import "./SearchResult.scss";
import { useHistory } from "react-router-dom";


function SearchResult({
    img,
    location,
    title,
    description,
    star,
    price,
    total,
    id,
}) 

{
    const history = useHistory();
    return (
        <div className="searchResult" onClick={(e)=>{
            history.push(`/accommodation/${id}`)
            // console.log(e.metaKey)
        }}>
            <MediaQuery maxDeviceWidth={414}>
            <img src={img} alt="" />
            <FavoriteBorder className="searchResult__heart" />
            <div className="searchResult__info">
                <div className="searchResult__infoTop">
                    <p>{location}</p>
                    <h3>{title}</h3>
                </div>
                <div className="searchResult__infoBottom">
                    <div className="searchResult__stars">
                        <StarBorder className="searchResult__star" />
                        <p>
                            <strong>{star}</strong>
                        </p>
                    </div>
                </div>
            </div>
            </MediaQuery>
            <MediaQuery minDeviceWidth={415}>
            <img src={img} alt="" />
            <FavoriteBorder className="searchResult__heart" />
            <div className="searchResult__info">
                <div className="searchResult__infoTop">
                    <p>{location}</p>
                    <h3>{title}</h3>
                    <p>___</p>
                    <p>{description}</p>
                </div>
                <div className="searchResult__infoBottom">
                    <div className="searchResult__stars">
                        <StarBorder className="searchResult__star" />
                        <p>
                            <strong>{star}</strong>
                        </p>
                    </div>
                    <div className="searchResult__price">
                        <h2>{price}</h2>
                        <p>{total}</p>
                    </div>
                </div>
            </div>
            </MediaQuery>
        </div>
    );
}

export default SearchResult;
