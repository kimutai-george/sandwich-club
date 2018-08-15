package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static JSONObject sandwichJSON;

    public static Sandwich parseSandwichJson(String json) {
        try {
            sandwichJSON = new JSONObject(json);


            return new Sandwich(getMainName(),getOtherNames(),getPlaceOfOrigin(),getDescription(),getImage(),getIngredients());
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
    // Extracts the main name from json data
    private static String getMainName() throws JSONException {
        return sandwichJSON.getJSONObject("name").getString("mainName");
    }
    // Extracts and returns the other names from array in our json data
    // Returns the Array
    // Lists all the data available in other names
    private static List<String> getOtherNames() throws JSONException{
        JSONArray otherNamesJSON = sandwichJSON.getJSONObject("name").getJSONArray("alsoKnownAs");
        List<String> alsoKnownAs = new ArrayList<>();
        for(int i= 0; i < otherNamesJSON.length(); i++){
            alsoKnownAs.add(otherNamesJSON.getString(i));
        }
       return alsoKnownAs;
    }
    // Extracts the place of origin from json data
    private static String getPlaceOfOrigin() throws JSONException{
        return sandwichJSON.getString("placeOfOrigin");

    }
    // Extracts and return the description from json data
    private static String getDescription() throws JSONException{
        return sandwichJSON.getString("description");

    }
    // Extracts and return the image from array
    private static String getImage() throws JSONException{
        return sandwichJSON.getString("image");

    }
    // Extracts and returns the ingredients from array in our json data
    // Returns the Array
    // Lists all the data available in ingredients
    private static List<String> getIngredients() throws JSONException{
        JSONArray ingredientsJSON = sandwichJSON.getJSONArray("ingredients");
        List<String> ingredients = new ArrayList<>();
        for(int i= 0; i < ingredientsJSON.length(); i++){
            ingredients.add(ingredientsJSON.getString(i));
        }
        return ingredients;

    }



    }
