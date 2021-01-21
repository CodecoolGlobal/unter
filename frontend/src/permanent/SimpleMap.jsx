import React, { useContext } from 'react';
import GoogleMapReact from 'google-map-react';
import Marker from './Marker'
import {AccommodationNumberContext} from '../context/AccommodationNumber'


function SimpleMap ({
    center,
    defaultZoom,
}) 
{
    const [accommodations,setAccommodations] = useContext(AccommodationNumberContext)
    return (
        // <div className="googleMap" >
        <GoogleMapReact 
          bootstrapURLKeys={{ key: '' }}
          center={center}
          defaultZoom={defaultZoom}
        >
            {accommodations.map((accommodation) => {
                return(
                <Marker
                key={accommodation.id}
                  lat={Number(accommodation.coordinates.latitude)}
                  lng={Number(accommodation.coordinates.longitude)}
                  text={accommodation.accommodationName}
                />)
            })}
        </GoogleMapReact>
        // </div>
        
    );
}

export default SimpleMap;