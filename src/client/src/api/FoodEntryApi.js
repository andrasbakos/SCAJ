import FoodEntryApiGenerated from "./generated/FoodEntryApiGenerated";

// Dependencies
//import axios from "axios";
//import { properties } from "../config/properties";

class FoodEntryApi extends FoodEntryApiGenerated {
  // You can customize the base actions overriding the object "actionsFunction" as shown in the example below:
  /** 
  // EXAMPLE:
 
  // Get FoodEntry List
  static getFoodEntryList() {
    console.log("This is my custom API");

    return fetch("http://localhost:3000/api/foodentrys")
      .then(response => {
        return response.json();
      })
      .catch(error => {
        throw error;
      });
  }
  */

}

export default FoodEntryApi;