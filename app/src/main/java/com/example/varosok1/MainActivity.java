package com.example.varosok1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import kotlin.jvm.internal.Ref;

public class MainActivity extends AppCompatActivity {
    Button buttonLista,buttonFelvetel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        onClick();
    }

    private void init() {
        buttonLista = findViewById(R.id.buttonLista);
        buttonFelvetel = findViewById(R.id.buttonFelvetel);
    }

    public void onClick(){
        buttonLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ListActivity.class);
                startActivity(intent);
                finish();
            }
        });
        buttonFelvetel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this,InsertActivity.class);
                startActivity(intent1);
                finish();
            }
        });
    }


}