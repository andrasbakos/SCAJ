import actionsFunction from "./generated/FoodEntryActionsGenerated";

// You can customize the base actions overriding the object "actionsFunction" as shown in the example below:
/** 
 // EXAMPLE:
 
 import FoodEntryApi from "../../api/FoodEntryApi";
 
 actionsFunction.loadFoodEntryList = function() {
   return function(dispatch) {
     console.log("This is my custom function");
     return FoodEntryApi
     .getFoodEntryList()
     .then(list => {
       dispatch(actionsFunction.loadFoodEntrySuccess(list));
      })
      .catch(error => {
        throw error;
      });
    };
  };
  
*/

export default actionsFunction;
