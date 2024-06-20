/*************************************************************************************************************************
 * Class Name: Dish.java                                                                                        *
 *                                                                                                                       *
 * Purpose: Model for dishes.                                                                                           *
 *                                                                                                                       *
 *************************************************************************************************************************/


package edu.niu.android.cuisineapp;

public class Dish {

    private int id;
    private String name;
    private String state;
    private String country;
    private String description;
    private String ingredients;
    private String recipe;
    private String restaurants;

    private String nutrition;

    private String taste;
    private String veg;

    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    public String getVeg() {
        return veg;
    }

    public void setVeg(String veg) {
        this.veg = veg;
    }

    public Dish(int id, String name, String state, String country, String description, String ingredients, String recipe, String nutrition, String restaurants, String taste, String veg){
        this.id = id;
        this.name=name;
        this.state=state;
        this.country = country;
        this.description=description;
        this.ingredients=ingredients;
        this.recipe=recipe;
        this.nutrition=nutrition;
        this.restaurants=restaurants;
        this.taste = taste;
        this.veg = veg;
    }

    public Dish(String state){
        this.state = state;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    public String getCountry(){
        return country;
    }
    public void setCountry(String country){
        this.country = country;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public String getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(String restaurants) {
        this.restaurants = restaurants;
    }

    public String getNutrition() {
        return nutrition;
    }

    public void setNutrition(String nutrition) {
        this.nutrition = nutrition;
    }
}
