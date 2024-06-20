/*************************************************************************************************************************
 * Class Name: StateActivity.java                                                                                        *
 *                                                                                                                       *
 * Purpose: This activity displays the selected state's all traditional and famous dishes with a short description       *
 * about them. This also allows us to search through state's dishes based on sweet, savory, vegetarian and non-vegetarian                                                                *
 *                                                                                                                       *
 *************************************************************************************************************************/


package edu.niu.android.cuisineapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;

import java.util.ArrayList;

public class StateActivity extends AppCompatActivity {

    private DataBaseManager dbManager;
    private String state;
    private LinearLayout layout;
    private ScrollView scrollView;
    private ArrayList<Dish> allDishes;
    private ArrayList<Dish> filteredDishes;

    private SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create a parent LinearLayout
        LinearLayout parentLayout = new LinearLayout(this);
        parentLayout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));
        parentLayout.setOrientation(LinearLayout.VERTICAL);
        setContentView(parentLayout);

        // Add search view dynamically
        searchView = new SearchView(this);
        LinearLayout.LayoutParams searchParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        searchView.setLayoutParams(searchParams);
        searchView.setQueryHint("sweet, savory, veg or non-veg");
        parentLayout.addView(searchView);

        // Create ScrollView and LinearLayout for content
        scrollView = new ScrollView(this);
        layout = new LinearLayout(this);
        layout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        scrollView.addView(layout);
        parentLayout.addView(scrollView);

        // Initialize other components
        state = getIntent().getStringExtra("state");
        dbManager = new DataBaseManager(this);
        allDishes = dbManager.selectAllinUS();
        filteredDishes = new ArrayList<>(allDishes);

        // Set search view listener
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterDishes(newText);
                return true;
            }
        });

        // Update view with all dishes
        updateView();

    }

    private void addSearchBar() {
        searchView = new SearchView(this);
        searchView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        searchView.setQueryHint("Search dishes...");
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterDishes(newText);

                return true;
            }
        });

    
    }

    private void filterDishes(String query) {


        if (TextUtils.isEmpty(query)) {
            filteredDishes = null; // Reset filteredDishes

        } else {

            filteredDishes = new ArrayList<>();
            if (query.equalsIgnoreCase("sweet")) {
                filteredDishes.addAll(dbManager.selectSweetDishes());
            } else if (query.equalsIgnoreCase("savory")) {
                filteredDishes.addAll(dbManager.selectSavoryDishes());
            } else if (query.equalsIgnoreCase("vegetarian") || query.equalsIgnoreCase("veg")) {
                filteredDishes.addAll(dbManager.selectVegetarianDishes());
            } else if (query.equalsIgnoreCase("non-vegetarian") || query.equalsIgnoreCase("non-veg")) {
                filteredDishes.addAll(dbManager.selectNonVegetarianDishes());
            }
            // Update the view to display the filtered dishes


        }
        updateView();

    }


    public void updateView(){
        layout.removeAllViews();
        //layout.addView(searchView);
        ArrayList<Dish> dishes = filteredDishes != null ? filteredDishes : dbManager.selectAllinUS();

        Log.d("state", state);

        int size = dishes.size();

        Log.d("SIZE OF THE ARRAY", ""+size);
        int count = 0;
        for(Dish dish: dishes){

            if(dish.getState().trim().equalsIgnoreCase(state)) {
                Log.d("Inside if", "updateView: ");
                LinearLayout frame = new LinearLayout(this);
                frame.setLayoutParams(new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                ));
                frame.setOrientation(LinearLayout.VERTICAL);
                ImageView image = new ImageView(this);
                image.setLayoutParams(new FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                ));
                switch(dish.getName().trim()){
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
                TextView textView1 = new TextView(this);
                textView1.setLayoutParams(new FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                ));
                textView1.setText(dish.getName());
                textView1.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                textView1.setTypeface(Typeface.DEFAULT_BOLD);
                textView1.setTextSize(24);


                TextView textView2 = new TextView(this);
                textView2.setLayoutParams(new FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                ));

                TextView textView3 = new TextView(this);
                textView3.setLayoutParams(new FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                ));
                textView3.setText("more");
                textView3.setTextColor(Color.BLUE);
                textView3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String id = String.valueOf(dish.getId());
                        Intent i = new Intent(StateActivity.this, RecipeActivity.class);
                        i.putExtra("id", id);
                        startActivity(i);

                        Toast.makeText(getApplicationContext(), "more data loading...", Toast.LENGTH_SHORT).show();
                    }
                });


                textView2.setText(dish.getDescription());
                textView2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                textView2.setTypeface(Typeface.SERIF);
                textView2.setTextSize(20);

                frame.addView(textView1);
                frame.addView(image);

                frame.addView(textView2);
                frame.addView(textView3);
                layout.addView(frame);

            }
        }

    }
}
