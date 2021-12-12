// Dependencies
import * as types from "../actionTypes";

// Init
const initialState = {
  foodentry: {}
};

// Reducer
export default function FoodEntryEditReducer(state = initialState, action) {
  switch (action.type) { 
    
    // Insert here your custom reducers


    // START REDUCERS
    case types.CREATE_FOODENTRY_SUCCESS:
      return { ...state, foodentry: action.payload };
    case types.UPDATE_FOODENTRY_SUCCESS:
      return { ...state, foodentry: action.payload };
    case types.GET_FOODENTRY_SUCCESS:
      return { ...state, foodentry: action.payload };
    case types.LIST_USER_SUCCESS:
      return { ...state, listUser: action.payload };
     // END REDUCERS
    
    default:
      return state;
  }
}