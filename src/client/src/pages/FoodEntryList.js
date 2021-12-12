// Dependencies
import React, { Component } from "react";
import { Link } from "react-router-dom";
import DialogDelete from "../components/DialogDelete";

// Redux
import PropTypes from "prop-types";
import { bindActionCreators } from "redux";
import { connect } from "react-redux";

// Material UI
import Button from "@material-ui/core/Button";
// import Table from "@material-ui/core/Table";
// import TableBody from "@material-ui/core/TableBody";
// import TableCell from "@material-ui/core/TableCell";
// import TableHead from "@material-ui/core/TableHead";
// import TableRow from "@material-ui/core/TableRow";

// Table
import EnhancedTable from "../components/EnhancedTable";

// Custom Actions


// START IMPORT ACTIONS
import FoodEntryActions from "../redux/actions/FoodEntryActions";

// END IMPORT ACTIONS

/** APIs

* actionsFoodEntry.delete
*	@description CRUD ACTION delete
*	@param ObjectId id - Id
*
* actionsFoodEntry.list
*	@description CRUD ACTION list
*

**/


class FoodEntryList extends Component {
  // Init component
  constructor(props) {
    super(props);
    this.state = {
      openDialogDelete: false
    };
  }

  // Load data on start
  componentWillMount() {
    this.props.actionsFoodEntry.loadFoodEntryList();
  }

  // Delete data
  delete(id) {
    this.setState({ openDialogDelete: true, idDelete: id });
  }

  closeDialogDelete() {
    this.setState({ openDialogDelete: false, idDelete: null });
  }

  confirmDialogDelete(id) {
    this.props.actionsFoodEntry.deleteFoodEntry(this.state.idDelete).then(data => {
      this.props.actionsFoodEntry.loadFoodEntryList();
      this.setState({ openDialogDelete: false, idDelete: null });
    });
  }

  // Show content
  render() {
    const columns = [ 
      {
        id: "Calorie",
        type: "integer",
        label: "Calorie"
      }, 
      {
        id: "Date",
        type: "date",
        label: "Date"
      }, 
      {
        id: "Name",
        type: "string",
        label: "Name"
      },
    ];
    const link = "/foodentrys/";

    return (
      <div>
        <h1>FoodEntry List</h1>

        <EnhancedTable
          data={this.props.list}
          columns={columns}
          link={link}
          onDelete={this.delete.bind(this)}
        />

        <DialogDelete
          open={this.state.openDialogDelete}
          onClose={this.closeDialogDelete.bind(this)}
          onConfirm={this.confirmDialogDelete.bind(this)}
        />

        {/*
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>ID</TableCell>
              <TableCell align="right">Calorie</TableCell>
              <TableCell align="right">Date</TableCell>
              <TableCell align="right">Name</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {this.props.list.map(row => (
              <TableRow key={row._id}>
                <TableCell component="th" scope="row">
                  <Link to={"/foodentrys/" + row._id} key={row._id}>
                    {row._id}
                  </Link>
                </TableCell>
                <TableCell align="right">{ row.Calorie }</TableCell>
                <TableCell align="right">{ row.Date }</TableCell>
                <TableCell align="right">{ row.Name }</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
        */}

        <div className="footer-card">
          <Link to="/foodentrys/new">
            <Button variant="contained" color="primary">
              Add
            </Button>
          </Link>
        </div>
      </div>
    );
  }
}

// Store actions
const mapDispatchToProps = function(dispatch) {
  return { 
    actionsFoodEntry: bindActionCreators(FoodEntryActions, dispatch),
  };
};

// Validate types
FoodEntryList.propTypes = { 
  actionsFoodEntry: PropTypes.object.isRequired,
};

// Get props from state
function mapStateToProps(state, ownProps) {
  return {
    list: state.FoodEntryListReducer.listFoodEntry
  };
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(FoodEntryList);
