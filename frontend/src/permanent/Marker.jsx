import React from 'react';
// import './Marker.css';

function Marker  ({
    text
}) {
    return (
      <div className="marker"
        title={text}
      />
    );
  };

  export default Marker;