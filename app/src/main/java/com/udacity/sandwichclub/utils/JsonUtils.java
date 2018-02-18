package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);
        Sandwich sandwich = null;
        List<String> knowAs = new ArrayList<>();
        List<String> ingredients = new ArrayList<>();
        if (jsonObject != null) {
            sandwich = new Sandwich();

            JSONObject name = jsonObject.optJSONObject(SandwichParam.NAME);
            if (name != null) {
                sandwich.setMainName(name.optString(SandwichParam.MAIN_NAME));
                JSONArray alsoKnownAsArray = name.optJSONArray(SandwichParam.ALSO_KNOWN_AS);
                for (int i = 0; i < alsoKnownAsArray.length(); i++) {
                    knowAs.add(alsoKnownAsArray.getString(i));
                }
                if (knowAs.size() > 0) {
                    sandwich.setAlsoKnownAs(knowAs);

                }
            }

            sandwich.setPlaceOfOrigin(jsonObject.optString(SandwichParam.PLACE_OF_ORIGIN));

            sandwich.setDescription(jsonObject.optString(SandwichParam.DESCRIPTION));

            sandwich.setImage(jsonObject.optString(SandwichParam.IMAGE));

            JSONArray ingredientsJson = jsonObject.getJSONArray(SandwichParam.INGREDIENTS);
            for (int i = 0; i < ingredientsJson.length(); i++) {
                ingredients.add(ingredientsJson.getString(i));
            }
            if (ingredients.size() > 0) {
                sandwich.setIngredients(ingredients);

            }
        }
        return sandwich;
    }

    /**
     * Returns string representation of List of String values
     *
     * @param stringList - values list
     * @return - String
     */
    public static String getListAsString(List<String> stringList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < stringList.size(); i++) {
            stringBuilder.append(stringList.get(i));
            if (i < stringList.size() - 1) {
                stringBuilder.append(", ");
            }
        }
        return stringBuilder.toString();
    }
}
