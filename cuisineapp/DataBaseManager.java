/*************************************************************************************************************************
 * Class Name: DataBaseManager.java                                                                                        *
 *                                                                                                                       *
 * Purpose: All database operations are defined in this class.                                                                                           *
 *                                                                                                                       *
 *************************************************************************************************************************/

package edu.niu.android.cuisineapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DataBaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "CUISINEMAP";

    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_US = "United_States";
    private static final String TABLE_REST = "location";

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String STATE = "state";
    private static final String COUNTRY = "country";
    private static final String DESCRIPTION = "description";
    private static final String INGREDIENTS = "ingredients";
    private static final String RECIPE = "recipe";
    private static final String NUTRITION = "nutrition";
    private static final String RESTAURANTS = "restaurants";
    private static final String TASTE = "taste";
    private static final String VEG = "veg";
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";

    public DataBaseManager(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlCreateUs = "CREATE TABLE if NOT EXISTS "+TABLE_US+"("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME + " TEXT,"
                + STATE + " TEXT,"
                + COUNTRY +" TEXT,"
                + DESCRIPTION + " TEXT,"
                + INGREDIENTS + " TEXT,"
                + RECIPE + " TEXT,"
                + NUTRITION +" TEXT,"
                + RESTAURANTS + " TEXT,"
                + TASTE + " TEXT,"
                + VEG + " TEXT"
                + ")";

        String sqlCreateRest = "CREATE TABLE if NOT EXISTS "+TABLE_REST+"("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME + " TEXT,"
                + LATITUDE + " TEXT,"
                + LONGITUDE + " TEXT"
                +")";


        db.execSQL(sqlCreateUs);
        db.execSQL(sqlCreateRest);

    }

    public void insert(Dish dish) {
        Log.d("hope it is going", "insert: ");
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = null;
        if (dish.getCountry().equalsIgnoreCase("US")) {

            ContentValues values = new ContentValues();
            values.put(NAME, dish.getName());
            values.put(STATE, dish.getState());
            values.put(COUNTRY, dish.getCountry());
            values.put(DESCRIPTION, dish.getDescription());
            values.put(INGREDIENTS, dish.getIngredients());
            values.put(RECIPE, dish.getRecipe());
            values.put(NUTRITION, dish.getNutrition());
            values.put(RESTAURANTS, dish.getRestaurants());
            values.put(TASTE, dish.getTaste());
            values.put(VEG, dish.getVeg());

            Log.d("inserted data", "insert: ");
            long newRowId = db.insert("United_States", null, values);

            db.execSQL(sqlInsert);
        }
        db.close();
    }
    public void insert_rest(Location location) {
        Log.d("hope it is going", "insert: ");
        SQLiteDatabase db = this.getWritableDatabase();
       // String sqlInsert = null;


            ContentValues values = new ContentValues();
            values.put(NAME, location.getName());
            values.put(LATITUDE, location.getLatitude());
            values.put(LONGITUDE, location.getLongitude());

            Log.d("inserted data IN REST", "insert: ");
            long newRowId = db.insert(TABLE_REST, null, values);

            //db.execSQL(sqlInsert);

        db.close();
    }
    public ArrayList<Dish> selectByStateinUS(String state){
        String sqlQuery = "select * from " + TABLE_US;
        sqlQuery += " where " + STATE + " = '" + state+ "\n'";

        Log.d("selectByStateinUS: ", sqlQuery);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<Dish> dish = new ArrayList<Dish>();

        while(cursor.moveToNext()) {
            Dish currentDish = new Dish(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10) );
            dish.add(currentDish);
        }
        db.close();
        return dish;
    }





    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+ TABLE_US);
        db.execSQL("drop table if exists " + TABLE_REST);

        onCreate(db);
    }

    public ArrayList<Dish> selectAllinUS(){

        String sqlQuery = "select * from " + TABLE_US;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<Dish> dish = new ArrayList<Dish>();

        while(cursor.moveToNext()){
            Dish currentDish = new Dish(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10));
            dish.add(currentDish);
        }

        db.close();
        return dish;
    }




    public ArrayList<Dish> selectByNameUS(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlQuery = "SELECT * FROM " + TABLE_US + " WHERE " + NAME +" = ?";
        Cursor cursor = db.rawQuery(sqlQuery, new String[]{name});
        ArrayList<Dish> dishes = new ArrayList<Dish>();
        while(cursor.moveToNext()){
            Dish currentDish = new Dish(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10));

        }
        db.close();
        return dishes;
    }

    public Dish selectDishById(int id){
        Dish dish = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlQuery = "SELECT * FROM " + TABLE_US + " WHERE " + ID +" = ?";

        String[] selectionArgs = {String.valueOf(id)};

        Cursor cursor = db.rawQuery(sqlQuery, selectionArgs);

        if(cursor != null && cursor.moveToFirst()){
            dish = new Dish(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8),cursor.getString(9), cursor.getString(10));

        }
        db.close();
        return dish;
    }


    public boolean isStringInDatabase(String searchString) {
        // Execute a query to check if the string exists in the database
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlQuery = "SELECT 1 FROM " + TABLE_US + " WHERE " + NAME +" = ?";

        Cursor cursor = db.rawQuery(sqlQuery, new String[]{searchString});

        boolean exists = cursor.getCount() > 0;

        cursor.close(); // Don't forget to close the cursor
        return exists;
    }

    public boolean isRestInDatabase(String searchString) {
        // Execute a query to check if the string exists in the database
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlQuery = "SELECT 1 FROM " + TABLE_REST + " WHERE " + NAME +" = ?";

        Cursor cursor = db.rawQuery(sqlQuery, new String[]{searchString});

        boolean exists = cursor.getCount() > 0;

        cursor.close(); // Don't forget to close the cursor
        return exists;
    }

    public Location getLocationFromDatabase(String restaurantName) {
        SQLiteDatabase db = this.getWritableDatabase();
        Location currentlocation = null;
        String sqlQuery = "SELECT * FROM " + TABLE_REST + " WHERE " + NAME +" = ?";
        Cursor cursor = db.rawQuery(sqlQuery, new String[]{restaurantName.trim()});
        ArrayList<Dish> dishes = new ArrayList<Dish>();
        while(cursor.moveToNext()){
            currentlocation = new Location(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3));

        }
        db.close();

        return currentlocation;
    }
    public ArrayList<Dish> selectSweetDishes() {
        ArrayList<Dish> sweetDishes = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_US + " WHERE " + TASTE + " = ?", new String[]{"Sweet"});
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Dish dish = new Dish(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10));
                sweetDishes.add(dish);
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return sweetDishes;
    }

    // Method to select savory dishes
    public ArrayList<Dish> selectSavoryDishes() {
        ArrayList<Dish> savoryDishes = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_US + " WHERE " + TASTE + " = ?", new String[]{"Savory"});
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Dish dish = new Dish(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10));
                savoryDishes.add(dish);
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return savoryDishes;
    }

    // Method to select vegetarian dishes
    public ArrayList<Dish> selectVegetarianDishes() {
        ArrayList<Dish> vegetarianDishes = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_US + " WHERE " + VEG + " = ?", new String[]{"Vegetarian"});
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Dish dish = new Dish(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10));
                vegetarianDishes.add(dish);
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return vegetarianDishes;
    }

    // Method to select non-vegetarian dishes
    public ArrayList<Dish> selectNonVegetarianDishes() {
        ArrayList<Dish> nonVegetarianDishes = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_US + " WHERE " + VEG + " = ?", new String[]{"Non-Veg"});
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Dish dish = new Dish(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10));
                nonVegetarianDishes.add(dish);
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return nonVegetarianDishes;
    }
}
