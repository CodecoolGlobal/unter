import React, { useState } from "react";
import "./Search.scss";
import "react-date-range/dist/styles.css";
import "react-date-range/dist/theme/default.css";
import { DateRange, DateRangePicker } from "react-date-range";

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
    }

    return (
        <div className="search">
            <DateRange
                ranges={[selectionRange]}
                onChange={handleSelect}
                months={2}
                rangeColors={["rgb(240,242,247)"]}
                moveRangeOnFirstSelection={false}
                editableDateInputs={true}
                direction="horizontal"
            />
        </div>
    );
}

export default Search;
