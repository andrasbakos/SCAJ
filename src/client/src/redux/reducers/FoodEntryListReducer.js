// Dependencies
import * as types from "../actionTypes";

// Init
const initialState = {
  list: []
};

// Reducer
export default function FoodEntryListReducer(state = initialState, action) {
  switch (action.type) {
    
    // Insert here your custom reducers


    // START REDUCERS
    case types.DELETE_FOODENTRY_SUCCESS:
      return { ...state, foodentry: action.payload };
    case types.LIST_FOODENTRY_SUCCESS:
      return { ...state, listFoodEntry: action.payload };
     // END REDUCERS
    
    default:
      return state;
  }
}