import React ,{useState}from 'react'
import './AccommodationCalendar.scss'
import { DateRange } from "react-date-range";
import MediaQuery from 'react-responsive'

function AccommodationCalendar() {
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
        <div className="calendar">
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
    )
}

export default AccommodationCalendar;
