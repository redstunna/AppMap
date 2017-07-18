package com.example.dmitry.emptyapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void pressButtonChange(View v){
        TextView t = (TextView) findViewById(R.id.textView1);
        t.setText("This happens!");
    }

    public void pressButtonVis(View v){
        Intent intent = new Intent(this, bottomActivity.class);
        startActivity(intent);
    }

    public void pressButtonItem(View v){
        Intent intent = new Intent(this, ItemDetailActivity.class);
        startActivity(intent);
    }

    public void pressButtonMaps(View v){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
}
