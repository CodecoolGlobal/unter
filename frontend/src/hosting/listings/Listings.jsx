import React, { useState, useEffect } from "react";
import PropTypes from "prop-types";
import { makeStyles } from "@material-ui/core/styles";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableContainer from "@material-ui/core/TableContainer";
import TableHead from "@material-ui/core/TableHead";
import TablePagination from "@material-ui/core/TablePagination";
import TableRow from "@material-ui/core/TableRow";
import TableSortLabel from "@material-ui/core/TableSortLabel";
import Typography from "@material-ui/core/Typography";
import ActionsButton from "./ActionsButton";
import "./Listings.scss";
import axios from "axios";
import { useHistory } from "react-router-dom";

function descendingComparator(a, b, orderBy) {
  if (b[orderBy] < a[orderBy]) {
    return -1;
  }
  if (b[orderBy] > a[orderBy]) {
    return 1;
  }
  return 0;
}

function getComparator(order, orderBy) {
  return order === "desc"
    ? (a, b) => descendingComparator(a, b, orderBy)
    : (a, b) => -descendingComparator(a, b, orderBy);
}

function stableSort(array, comparator) {
  const stabilizedThis = array.map((el, index) => [el, index]);
  stabilizedThis.sort((a, b) => {
    const order = comparator(a[0], b[0]);
    if (order !== 0) return order;
    return a[1] - b[1];
  });
  return stabilizedThis.map((el) => el[0]);
}

const headCells = [
  {
    id: "name",
    numeric: false,
    disablePadding: true,
    label: "Listing",
  },
  { id: "bedrooms", numeric: true, disablePadding: false, label: "Bedrooms" },
  { id: "beds", numeric: true, disablePadding: false, label: "Beds" },
  { id: "bathrooms", numeric: true, disablePadding: false, label: "Baths" },
  { id: "location", numeric: true, disablePadding: false, label: "Location" },
];

function EnhancedTableHead(props) {
  const { classes, order, orderBy, onRequestSort } = props;
  const createSortHandler = (property) => (event) => {
    onRequestSort(event, property);
  };

  return (
    <TableHead>
      <TableRow key="x">
        {headCells.map((headCell) => (
          <TableCell
            key={headCell.id}
            align="center"
            padding={headCell.disablePadding ? "none" : "default"}
            sortDirection={orderBy === headCell.id ? order : false}
          >
            <TableSortLabel
              active={orderBy === headCell.id}
              direction={orderBy === headCell.id ? order : "asc"}
              onClick={createSortHandler(headCell.id)}
            >
              {headCell.label}
              {orderBy === headCell.id ? (
                <span className={classes.visuallyHidden}>
                  {order === "desc" ? "sorted descending" : "sorted ascending"}
                </span>
              ) : null}
            </TableSortLabel>
          </TableCell>
        ))}
        <TableCell align="center">
          <i className="fas fa-cog" />
        </TableCell>
      </TableRow>
    </TableHead>
  );
}

EnhancedTableHead.propTypes = {
  classes: PropTypes.object.isRequired,
  onRequestSort: PropTypes.func.isRequired,
  order: PropTypes.oneOf(["asc", "desc"]).isRequired,
  orderBy: PropTypes.string.isRequired,
  rowCount: PropTypes.number.isRequired,
};

const useStyles = makeStyles((theme) => ({
  root: {
    width: "100%",
  },
  table: {
    minWidth: 750,
  },
  visuallyHidden: {
    border: 0,
    clip: "rect(0 0 0 0)",
    height: 1,
    margin: -1,
    overflow: "hidden",
    padding: 0,
    position: "absolute",
    top: 20,
    width: 1,
  },
}));

function Listings() {
  const history = useHistory();
  const classes = useStyles();
  const [order, setOrder] = useState("asc");
  const [orderBy, setOrderBy] = useState("calories");
  const [selected, setSelected] = useState([]);
  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(10);
  const [rows, setRows] = useState([]);

  /*if (localStorage.getItem("user") === null) {
    history.push("/");
  }*/

  console.log(localStorage.getItem("id"));
  //const hostId = localStorage.getItem("user").id;

  const hostId = 1;
  useEffect(() => {
    axios
      .get(`http://localhost:8762/acc/host-id/${hostId}`, {
        withCredentials: true,
      })
      .then((response) => {
        setRows(response.data);
      });
  }, [setRows]);

  const handleRequestSort = (event, property) => {
    const isAsc = orderBy === property && order === "asc";
    setOrder(isAsc ? "desc" : "asc");
    setOrderBy(property);
  };

  const handleClick = (event, name) => {
    const selectedIndex = selected.indexOf(name);
    let newSelected = [];

    if (selectedIndex === -1) {
      newSelected = newSelected.concat(selected, name);
    } else if (selectedIndex === 0) {
      newSelected = newSelected.concat(selected.slice(1));
    } else if (selectedIndex === selected.length - 1) {
      newSelected = newSelected.concat(selected.slice(0, -1));
    } else if (selectedIndex > 0) {
      newSelected = newSelected.concat(
        selected.slice(0, selectedIndex),
        selected.slice(selectedIndex + 1)
      );
    }

    setSelected(newSelected);
  };

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  const noImage = (
    <div className="no-image">
      <i className="fas fa-camera" />
    </div>
  );

  const listingImage = (row) => (
    <div className="listing-image">
      <img src={row.pictures[0]} alt="" />
    </div>
  );

  const imageBox = (row) => {
    if (row.pictures !== undefined) {
      return row.pictures[0] ? listingImage(row) : noImage;
    }
    return noImage;
  };

  const checkIfExist = (value) => (value ? value : `-`);

  return (
    <div className={classes.root}>
      <Typography
        className={classes.title}
        variant="h6"
        id="tableTitle"
        component="div"
      >
        <h3>
          {rows.length} {rows.length <= 1 ? "listing" : "listings"}
        </h3>
      </Typography>
      <TableContainer>
        <Table
          className={classes.table}
          aria-labelledby="tableTitle"
          size="medium"
          aria-label="enhanced table"
        >
          <EnhancedTableHead
            classes={classes}
            order={order}
            orderBy={orderBy}
            onRequestSort={handleRequestSort}
            rowCount={rows.length}
            key="100"
          />
          <TableBody>
            {stableSort(rows, getComparator(order, orderBy))
              .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
              .map((row, index) => {
                return (
                  <TableRow
                    hover
                    onClick={(event) => handleClick(event, row.id)}
                    tabIndex={-1}
                    key={row.id}
                  >
                    <TableCell
                      component="th"
                      scope="row"
                      padding="none"
                      className="cell"
                      align="left"
                    >
                      <div className="side-by-side">
                        <div className="row">
                          <div
                            style={{
                              display: "inline-block",
                              height: "47px !important",
                              width: "71px",
                            }}
                          >
                            {imageBox(row)}
                          </div>
                          <div className="listing-name">
                            {checkIfExist(row.name)}
                          </div>
                        </div>
                      </div>
                    </TableCell>
                    <TableCell className="cell" align="center">
                      {checkIfExist(row.bedrooms)}
                    </TableCell>
                    <TableCell className="cell" align="center">
                      {checkIfExist(row.beds)}
                    </TableCell>
                    <TableCell className="cell" align="center">
                      {checkIfExist(row.bathrooms)}
                    </TableCell>
                    <TableCell className="cell" align="center">
                      {row.address.city
                        ? `${row.address.city}, ${row.address.country}`
                        : `-`}
                    </TableCell>
                    <TableCell className="cell" align="center">
                      <ActionsButton />
                    </TableCell>
                  </TableRow>
                );
              })}
          </TableBody>
        </Table>
      </TableContainer>
      <TablePagination
        rowsPerPageOptions={[5, 10, 25]}
        component="div"
        count={rows.length}
        rowsPerPage={rowsPerPage}
        page={page}
        onChangePage={handleChangePage}
        onChangeRowsPerPage={handleChangeRowsPerPage}
      />
    </div>
  );
}

export default Listings;
