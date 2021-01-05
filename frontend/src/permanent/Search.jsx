import React, { useState } from "react";
import "./Search.scss";
import "react-date-range/dist/styles.css";
import "react-date-range/dist/theme/default.css";
import { DateRange, DateRangePicker } from "react-date-range";
import {isMobile} from 'react-device-detect';
import MediaQuery from 'react-responsive'


function Search() {
    const [startDate, setStartDate] = useState(new Date());
    const [endDate, setEndDate] = useState(new Date());


    const selectionRange = {
        startDate: startDate,
        endDate: endDate,
        key: "selection",
    };

    function handleSelect(ranges) {
        setStartDate(ranges.selection.startDate);
        setEndDate(ranges.selection.endDate);
         console.log(isMobile);
    }
        return( 

        <div className="search">
            <MediaQuery maxDeviceWidth={414}>
                <DateRange
                    ranges={[selectionRange]}
                    onChange={handleSelect}
                    months={1}
                    rangeColors={["rgb(240,242,247)"]}
                    moveRangeOnFirstSelection={false}
                    editableDateInputs={true}
                    direction="horizontal"
                />
            </MediaQuery>

            <MediaQuery minDeviceWidth={415}>
                <DateRange
                    ranges={[selectionRange]}
                    onChange={handleSelect}
                    months={2}
                    rangeColors={["rgb(240,242,247)"]}
                    moveRangeOnFirstSelection={false}
                    editableDateInputs={true}
                    direction="horizontal"/>
            </MediaQuery>
        </div>
            )
        }
       


export default Search;
