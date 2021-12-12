// Dependencies
import React, { Component } from "react";
import { Link } from "react-router-dom";
import Utils from "../utils/utils";

// Redux
import PropTypes from "prop-types";
import { bindActionCreators } from "redux";
import { connect } from "react-redux";

// Material UI
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import { DateTimePicker } from "material-ui-pickers";
import Select from "@material-ui/core/Select";
import MenuItem from "@material-ui/core/MenuItem";
import InputLabel from "@material-ui/core/InputLabel";
import FormControl from "@material-ui/core/FormControl";

// Custom Actions


// START IMPORT ACTIONS
import FoodEntryActions from "../redux/actions/FoodEntryActions";

// END IMPORT ACTIONS

/** APIs

* actionsFoodEntry.create
*	@description CRUD ACTION create
*
* actionsFoodEntry.update
*	@description CRUD ACTION update
*	@param ObjectId id - Id
*
* actionsFoodEntry.get
*	@description CRUD ACTION get
*	@param ObjectId id - Id resource
*
* actionsUser.list
*	@description CRUD ACTION list
*

**/

class FoodEntryEdit extends Component {
  // Init foodentry
  constructor(props) {
    super(props);
    this.state = {
      foodentry: {}
    };
  }

  // Load data on start
  componentDidMount() {
    if (this.props.match.params.id !== "new") {
      this.props.actionsFoodEntry.loadFoodEntry(this.props.match.params.id);
    }
    
    this.props.actionsUser.loadUserList();
  }

  // Insert props foodentry in state
  componentWillReceiveProps(props) {
    this.setState(...this.state, {
      foodentry: props.foodentry
    });
  }

  // Save data
  save(event) {
    event.preventDefault();
    if (this.state.foodentry._id) {
      this.props.actionsFoodEntry.saveFoodEntry(this.state.foodentry).then(data => {
        this.props.history.push("/foodentrys/");
      });
    } else {
      this.props.actionsFoodEntry.createFoodEntry(this.state.foodentry).then(data => {
        this.props.history.push("/foodentrys/");
      });
    }
  }

  // Show content
  render() {
    return (
      <div>
        <h1>FoodEntry Edit</h1>
        <form className="myForm" onSubmit={this.save.bind(this)}>

          
          <TextField
            id="Calorie"
            label="Calorie"
            value={this.state.foodentry.Calorie || ""}
            onChange={Utils.handleChange.bind(this, "foodentry")}
            type="number"
            type="decimal"
            margin="normal"
            fullWidth
            required
            {...(!this.state.foodentry.Calorie && this.state.foodentry.Calorie === ""
              ? { error: true }
              : {})}
          />
          
          <DateTimePicker
            id="Date"
            label="Date"
            className="mt-20 mb-20"
            ampm={false}
            value={
              this.state.foodentry.Date
                ? new Date(this.state.foodentry.Date)
                : null
            }
            onChange={Utils.handleChangeDate.bind(this, "foodentry", "Date")}
            fullWidth
            autoOk
            disableFuture
            required
            {...(!this.state.foodentry.Date && this.state.foodentry.Date === ""
              ? { error: true }
              : {})}
          />
          
          
          <TextField
            id="Name"
            label="Name"
            value={this.state.foodentry.Name || ""}
            onChange={Utils.handleChange.bind(this, "foodentry")}
            margin="normal"
            fullWidth
            required
            {...(!this.state.foodentry.Name && this.state.foodentry.Name === ""
              ? { error: true }
              : {})}
          />
          
          {/* RELATIONS */}

          <h2 className="mb-20">Relations</h2>
          
          {/* Relation 1:m _user with User */}
          
          <FormControl fullWidth className="mb-20">
            <InputLabel shrink htmlFor="_user">
              _user
            </InputLabel>
            <Select
              value={this.state.foodentry._user || ""}
              onChange={Utils.handleChangeSelect.bind(this, "foodentry")}
              inputProps={{
                id: "_user",
                name: "_user"
              }}
              fullWidth
            >
              <MenuItem value="">
                <em>None</em>
              </MenuItem>
              {this.props.listUser && this.props.listUser.map(row => (
                <MenuItem value={row._id} key={row._id}>
                  {row._id}
                </MenuItem>
              ))}
            </Select>
          </FormControl>
          
          
          {/* Footer */}
          <div className="footer-card">
            <Link to="/foodentrys/">Back to list</Link>

            <Button type="submit" variant="contained" color="primary">
              Save
            </Button>
          </div>
        </form>
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
FoodEntryEdit.propTypes = { 
  actionsFoodEntry: PropTypes.object.isRequired,
};

// Get props from state
function mapStateToProps(state, ownProps) {
  return {
    foodentry: state.FoodEntryEditReducer.foodentry,
    listUser: state.FoodEntryEditReducer.listUser
  };
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(FoodEntryEdit);
