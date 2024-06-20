/*************************************************************************************************************************
 * Class Name: USActivity.java                                                                                        *
 *                                                                                                                       *
 * Purpose: This has a map with some selected states with buttons to redirect to the StateActivity.                                                                                           *
 *                                                                                                                       *
 *************************************************************************************************************************/


package edu.niu.android.cuisineapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.LongDef;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class USActivity extends AppCompatActivity {

    private DataBaseManager dbManager;
    private Button newYork;
    private Button hawaii;
    private Button Cal;
    private Button fl;
    private Button tx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DataBaseManager(this);
        //inserting data
//        InsertData insertData = new InsertData();
//        insertData.data(this);


        setContentView(R.layout.activity_us);

        //redirecting to state activity
        newYork = (Button)findViewById(R.id.newyork);
        hawaii = (Button)findViewById(R.id.hawaii);
        Cal = (Button)findViewById(R.id.califonia);
        tx = (Button)findViewById(R.id.texas);
        fl = (Button)findViewById(R.id.florida);

        newYork.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
               Dish dish = new Dish("New York");
                Intent i = new Intent(USActivity.this, StateActivity.class);
                i.putExtra("state", "New York");
                startActivity(i);
            }
        });


        hawaii.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Dish dish = new Dish("Hawaii");
                Intent i = new Intent(USActivity.this, StateActivity.class);
                i.putExtra("state", "Hawaii");
                startActivity(i);
            }
        });

        fl.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Dish dish = new Dish("Florida");
                Intent i = new Intent(USActivity.this, StateActivity.class);
                i.putExtra("state", "Florida");
                startActivity(i);
            }
        });

        Cal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Dish dish = new Dish("California");
                Intent i = new Intent(USActivity.this, StateActivity.class);
                i.putExtra("state", "California");
                startActivity(i);
            }
        });

        tx.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Dish dish = new Dish("Texas");
                Intent i = new Intent(USActivity.this, StateActivity.class);
                i.putExtra("state", "Texas");
                startActivity(i);
            }
        });


    }


}
