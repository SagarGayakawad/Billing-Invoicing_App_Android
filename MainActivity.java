package com.example.mahe.lab5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mahe.lab5.DatabaseHelper;


public class MainActivity extends Activity {
    DatabaseHelper db;
    Button b1,b2,b3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=new DatabaseHelper(this);
        b1=(Button)findViewById(R.id.additem);
        b2=(Button)findViewById(R.id.itemdb);
        b3=(Button)findViewById(R.id.main4);
        add();
        database();
        loadspinner();
    }
    public  void add()
    {
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(add);
            }
        });
    }

    public void database()
    {
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent db=new Intent(MainActivity.this,Main3Activity.class);
                startActivity(db);
            }
        });
    }

    public  void loadspinner()
    {
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ls=new Intent(MainActivity.this,Main4Activity.class);
                startActivity(ls);
            }
        });
    }
}
