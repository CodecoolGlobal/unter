import React,{useState,useEffect} from 'react';
// import Pagination from '@material-ui/lab/Pagination';
import { MemoryRouter, Route } from 'react-router';
import PaginationItem from '@material-ui/lab/PaginationItem';
import { Link, useHistory } from "react-router-dom";
import queryString from "query-string";
import ReactDOM from "react-dom";
import Pagination from 'react-bootstrap-4-pagination';
import './Pagination.scss'
// require("bootstrap/less/bootstrap.less");




function PaginationComponent({postPerPage,totalPosts,paginate}) {
  const pageNumbers=Math.ceil(totalPosts/postPerPage);
  const history = useHistory();
  const windowLocation = window.location.search;
  const [activepage,setActivePage] = useState();
  let parsed = queryString.parse(window.location.search);
  const handlePageChange = async (pageNumber,callback)=>  {
    setActivePage(pageNumber)
    callback(pageNumber)
    console.log(pageNumber+"PAGEN_uMBER")
  }
 

  function changePage (pageNumber){
    history.push(`search?city=${parsed.city}&latitude=${parsed.latitude}&longitude=${parsed.longitude}&radius=1&${pageNumber === 1 ?`page= ${1}` : `page=${pageNumber}`}`)

  }
  const paginationConfig = {
    totalPages: pageNumbers,
    currentPage: Number(parsed.page) ,
    showMax: 5,
    color:'black',
    size: "lg",
    disabeldColor:'black',
    activeBgColor:'black',
    activeColor:'white',
    circle:true,
    threeDots: false,
    prevNext: true,
    onClick: function (page) {
      
      handlePageChange(page,changePage)
   
    }
  }
      return (
        <div className="Pagination">
          <Pagination {...paginationConfig} />
        </div>
      );
    
}
export default PaginationComponent
