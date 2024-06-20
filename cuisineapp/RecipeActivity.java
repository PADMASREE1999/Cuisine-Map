/*************************************************************************************************************************
 * Class Name: RecipeActivity.java                                                                                        *
 *                                                                                                                       *
 * Purpose: This activity displays information like description, recipe, nutritional facts and some restaurants.         *
 *                                                                                                                       *
 *************************************************************************************************************************/


package edu.niu.android.cuisineapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RecipeActivity extends AppCompatActivity {
    private LinearLayout layout;
    private ScrollView scrollView;
    private String id;
    private DataBaseManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        scrollView = new ScrollView(this);
        id = getIntent().getStringExtra("id");
        dbManager = new DataBaseManager(this);
        layout= new LinearLayout(this);
        layout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);

        scrollView.addView(layout);
        setContentView(scrollView);
        updateView();
    }

    public void updateView() {
        Dish dish = dbManager.selectDishById(Integer.parseInt(id));

        ImageView image = new ImageView(this);
        image.setLayoutParams(new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        switch (dish.getName().trim()) {
            case "New York-style pizza":
                image.setImageResource(R.drawable.new_york_style_pizza);
                break;
            case "Black and White Cookie":
                image.setImageResource(R.drawable.black_and_white_cookie);
                break;
            case "New York Cheesecake":
                image.setImageResource(R.drawable.new_york_cheesecake);
                break;
            case "Bagels with Lox":
                image.setImageResource(R.drawable.bagels_with_lox);
                break;
            case "Pastrami on Rye":
                image.setImageResource(R.drawable.pastrami_on_rye);
                break;
            case "Ahi Poke":
                image.setImageResource(R.drawable.ahi_poke);
                break;
            case "Laulau":
                image.setImageResource(R.drawable.laulau);
                break;
            case "Loco Moco":
                image.setImageResource(R.drawable.loco_moco);
                break;
            case "Haupia":
                image.setImageResource(R.drawable.haupia);
                break;
            case "Malasadas":
                image.setImageResource(R.drawable.malasadas);
                break;
            case "Key Lime Pie":
                image.setImageResource(R.drawable.key_lime_pie);
                break;
            case "Cuban Sandwich":
                image.setImageResource(R.drawable.cuban_sandwich);
                break;
            case "Gator Tail":
                image.setImageResource(R.drawable.gator_tail);
                break;
            case "Florida Spiny Lobster":
                image.setImageResource(R.drawable.florida_spiny_lobster);
                break;
            case "Florida Orange Juice Biscuits":
                image.setImageResource(R.drawable.florida_orange_juice_biscuits);
                break;
            case "Fish Tacos":
                image.setImageResource(R.drawable.fish_tacos);
                break;
            case "California Cobb Salad":
                image.setImageResource(R.drawable.california_cobb_salad);
                break;
            case "In-N-Out Burger":
                image.setImageResource(R.drawable.in_n_out_burger);
                break;
            case "Cioppino (Seafood Stew)":
                image.setImageResource(R.drawable.cioppino__seafood_stew_);
                break;
            case "Avocado Toast":
                image.setImageResource(R.drawable.avocado_toast);
                break;
            case "Barbecue Brisket":
                image.setImageResource(R.drawable.barbecue_brisket);
                break;
            case "Chili Con Carne":
                image.setImageResource(R.drawable.chili_con_carne);
                break;
            case "Tex-Mex Enchiladas":
                image.setImageResource(R.drawable.tex_mex_enchiladas);
                break;
            case "Chicken Fried Steak":
                image.setImageResource(R.drawable.chicken_fried_steak);
                break;
            case "Texas Sheet Cake":
                image.setImageResource(R.drawable.texas_sheet_cake);
                break;
        }
        TextView name = new TextView(this);
        name.setLayoutParams(new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        name.setText(dish.getName());
        name.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
        name.setTypeface(Typeface.DEFAULT_BOLD);
        name.setTextSize(28);

        TextView description = new TextView(this);
        description.setLayoutParams(new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        description.setText(dish.getDescription());
        description.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        description.setTypeface(Typeface.SERIF);
        description.setTextSize(20);

        TextView IngredientsHeading = new TextView(this);
        IngredientsHeading.setLayoutParams(new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        IngredientsHeading.setText("INGREDIENTS:");
        IngredientsHeading.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        IngredientsHeading.setTypeface(Typeface.DEFAULT_BOLD);
        IngredientsHeading.setTextSize(24);

        TextView Ingredients = new TextView(this);
        Ingredients.setLayoutParams(new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        Ingredients.setText(dish.getIngredients());
        Ingredients.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        Ingredients.setTypeface(Typeface.SERIF);
        Ingredients.setTextSize(20);

        TextView directionsHeading = new TextView(this);
        directionsHeading.setLayoutParams(new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        directionsHeading.setText("DIRECTIONS:");
        directionsHeading.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        directionsHeading.setTypeface(Typeface.DEFAULT_BOLD);
        directionsHeading.setTextSize(24);

        TextView directions = new TextView(this);
        directions.setLayoutParams(new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        directions.setText(dish.getRecipe());
        directions.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        directions.setTypeface(Typeface.SERIF);
        directions.setTextSize(20);

        TextView NutritionHeading = new TextView(this);
        NutritionHeading.setLayoutParams(new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        NutritionHeading.setText("NUTRITIONAL FACTS:");
        NutritionHeading.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        NutritionHeading.setTypeface(Typeface.DEFAULT_BOLD);
        NutritionHeading.setTextSize(24);

        TextView Nutrition = new TextView(this);
        Nutrition.setLayoutParams(new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        Nutrition.setText(dish.getNutrition());
        Nutrition.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        Nutrition.setTypeface(Typeface.SERIF);
        Nutrition.setTextSize(20);

        TextView RestaurantsHeading = new TextView(this);
        RestaurantsHeading.setLayoutParams(new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        RestaurantsHeading.setText("RESTAURANTS:");
        RestaurantsHeading.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        RestaurantsHeading.setTypeface(Typeface.DEFAULT_BOLD);
        RestaurantsHeading.setTextSize(24);

        LinearLayout frame = new LinearLayout(this);
        String[] str = dish.getRestaurants().split("\n");

        for (String s : str) {
            TextView restaurant = new TextView(this);

            frame.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            frame.setOrientation(LinearLayout.VERTICAL);

            restaurant.setLayoutParams(new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));


            restaurant.setText(s);
            restaurant.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            restaurant.setTypeface(Typeface.SERIF);
            restaurant.setTextSize(20);
            restaurant.setTextColor(Color.BLUE);
            restaurant.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent i = new Intent(RecipeActivity.this, MapsActivity.class);
                    i.putExtra("name", s);
                    startActivity(i);

                    Toast.makeText(getApplicationContext(), "more data loading...", Toast.LENGTH_SHORT).show();
                }
            });

            frame.addView(restaurant);

        }
/*
        restaurant.setLayoutParams(new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));


        restaurant.setText(dish.getRestaurants());
        restaurant.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        restaurant.setTypeface(Typeface.SERIF);
        restaurant.setTextSize(20);

*/
        frame.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        layout.addView(name);
        layout.addView(image);
        layout.addView(description);
        layout.addView(IngredientsHeading);
        layout.addView(Ingredients);
        layout.addView(directionsHeading);
        layout.addView(directions);
        layout.addView(NutritionHeading);
        layout.addView(Nutrition);
        layout.addView(RestaurantsHeading);
        layout.addView(frame);
    }
}
