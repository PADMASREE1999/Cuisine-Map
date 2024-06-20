/*************************************************************************************************************************
 * Class Name: InsertData.java                                                                                        *
 *                                                                                                                       *
 * Purpose: Data to be inserted in the table. There are two datasets in this project, one for the locations of the restaurants and one for the dishes
 * this method reads both the datasets and inserts into the database.*
 *                                                                                                                       *
 *************************************************************************************************************************/


package edu.niu.android.cuisineapp;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InsertData {

    DataBaseManager dataBaseManager;
    public void data(Context context) throws IOException {

        dataBaseManager = new DataBaseManager(context);

        AssetManager assetManager = context.getAssets();
        InputStream inputStream = assetManager.open("data.txt");

        String data = convertToString(inputStream);
        String name = null  ;

        String state = null;
        String country = null;
        String disc =null;


        String taste = null;
        String veg = null;

        String[] food_items = data.split("\n");
        String[] dish_separation = new String[0];
        for(String item: food_items){
            dish_separation = item.split("\\|");
            Log.d("dish number", String.valueOf(dish_separation[0]));
            Log.d("length", String.valueOf(dish_separation.length));
            String ingredients = "";
            String recipe = "";

            String nutrition = "";

            String rest ="" ;
            if(!dataBaseManager.isStringInDatabase(dish_separation[1].trim())) {
                name = dish_separation[1].trim();
                state = dish_separation[2].trim();
                country = dish_separation[3].trim();
                disc = dish_separation[4];

                String[] ingredient_separation = dish_separation[5].split("\\$");
                for(String s: ingredient_separation){
                    ingredients = ingredients + s + "\n";
                }
                //ingredients = ingredients.trim();

                String[] recipe_separation = dish_separation[6].split("\\$");
                for(String s: recipe_separation){
                    recipe = recipe + s + "\n";
                }
                //recipe = recipe.trim();

                String[] nutri_separation = dish_separation[7].split("\\$");
                for(String s: nutri_separation){
                    nutrition = nutrition + s + "\n";
                }
                // nutrition = nutrition.trim();

                String[] rest_separation = dish_separation[8].split("\\$");
                for(String s: rest_separation){
                    rest = rest + s + "\n";
                }
                // rest = rest.trim();
                Log.d("dddddddd", ingredient_separation[0]);

                taste = dish_separation[9].trim();
                veg = dish_separation[10].trim();

                try{
                    Dish dish  = new Dish(0,name,state,country,disc, ingredients,recipe,nutrition,rest,taste,veg);
                    //    Log.d("data", "data: ");
                    dataBaseManager.insert(dish);
                }catch(Exception e){

                }
            }
        }

        Log.d("food : ) ", food_items[0]);




    }

    public void location(Context context) throws IOException {

        dataBaseManager = new DataBaseManager(context);

        AssetManager assetManager = context.getAssets();
        InputStream inputStream = assetManager.open("rest_dest.txt");

        String data = convertToString(inputStream);
        String name = null  ;

        String latitude = null;
        String longitude = null;




        String[] restaurants = data.split("\n");
        String[] restaurant = new String[0];
        for(String item: restaurants){
            restaurant = item.split("\\|");
            Log.d("dish number", String.valueOf(restaurant[0]));
            Log.d("length", String.valueOf(restaurant.length));

            if(!dataBaseManager.isRestInDatabase(restaurant[1].trim())) {
                name = restaurant[1].trim();
                latitude = restaurant[2].trim();
                longitude = restaurant[3].trim();




                try{
                   // Dish dish  = new Dish(0,name,state,country,disc, ingredients,recipe,nutrition,rest);
                    //    Log.d("data", "data: ");
                    Location loc = new Location(0, name, latitude, longitude);
                    dataBaseManager.insert_rest(loc);
                }catch(Exception e){

                }
            }
        }

       // Log.d("food : ) ", food_items[0]);




    }
    public static String convertToString(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line).append("\n");
        }

        reader.close();
        return stringBuilder.toString();
    }

}
