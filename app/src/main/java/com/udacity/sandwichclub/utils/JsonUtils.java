package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) throws JSONException {
        JSONObject jsonSandwich = new JSONObject(json);
        Sandwich sandwich = new Sandwich();

        String mainName = "Unknown name";
        List<String> alsoKnownAs = new ArrayList<String>();
        String placeOfOrigin = "Narnia";
        String description = "No description provided";
        String image = "";
        List<String> ingredients = new ArrayList<String>();

        if(jsonSandwich.has("name")) {
            JSONObject jsonName = jsonSandwich.getJSONObject("name");

            if(jsonName.has("mainName")){
                mainName = jsonName.getString("mainName");
            }

            if(jsonName.has("alsoKnownAs") && jsonName.getJSONArray("alsoKnownAs").length() != 0 ){
                JSONArray jsonAlsoKnownAs = jsonName.getJSONArray("alsoKnownAs");

                for(int i = 0; i < jsonAlsoKnownAs.length(); i++){
                    alsoKnownAs.add(jsonAlsoKnownAs.getString(i));
                }
            } else {
                alsoKnownAs.add("No other known names");
            }
        }

        if(jsonSandwich.has("placeOfOrigin") && !jsonSandwich.getString("placeOfOrigin").equals("")){
            placeOfOrigin = jsonSandwich.getString("placeOfOrigin");
        }

        if(jsonSandwich.has("description") && !jsonSandwich.getString("description").equals("")){
            description = jsonSandwich.getString("description");
        }

        if(jsonSandwich.has("image")){
            image = jsonSandwich.getString("image");
        }

        if(jsonSandwich.has("ingredients") && jsonSandwich.getJSONArray("ingredients").length() != 0 ){
            JSONArray jsonIngredients = jsonSandwich.getJSONArray("ingredients");

            for(int i = 0; i < jsonIngredients.length(); i++){
                ingredients.add(jsonIngredients.getString(i));
            }
        } else {
            ingredients.add("No ingredients to show");
        }

        sandwich.setMainName(mainName);
        sandwich.setAlsoKnownAs(alsoKnownAs);
        sandwich.setPlaceOfOrigin(placeOfOrigin);
        sandwich.setDescription(description);
        sandwich.setImage(image);
        sandwich.setIngredients(ingredients);

        return sandwich;
    }
}
