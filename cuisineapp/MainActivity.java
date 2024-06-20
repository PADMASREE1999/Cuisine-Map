/*************************************************************************************************************************
 *                                                                                                                       *
 * CSCI 428/524 Graduate and Honors Project Spring2024                                                                    *
 *                                                                                                                       *
 * App Name: CuisineApp                                                                                                  *
 *                                                                                                                       *
 * Class Name: MainActivity.java                                                                                         *
 *                                                                                                                       *
 * Developer(s): Sai Padmasree Tummala(z1981141) and Sree Krishnapriya Kanagala(z1974984)                                *
 *                                                                                                                       *
 * Due Date: 05/03/24                                                                                                    *
 *                                                                                                                       *
 * Purpose: This is main class. It redirects to the United States Map Activity with locations.                           *
 * It also calls the method to read the dataset and update the database as necessary .                                   *
 *                                                                                                                       *
 *************************************************************************************************************************/

package edu.niu.android.cuisineapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button us;
    private Button india;
    private Button australia;
    private DataBaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DataBaseManager(this);
        SQLiteDatabase db = dbManager.getWritableDatabase();
        dbManager.onUpgrade(db,0,1);
        setContentView(R.layout.activity_main);
        // reading data and updating database
        InsertData insertData = new InsertData();
        try {
            insertData.data(this);
            insertData.location(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



        //redirecting to United States activity on button click
        us = (Button)findViewById(R.id.us);
        us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, USActivity.class));
            }
        });


    }
}