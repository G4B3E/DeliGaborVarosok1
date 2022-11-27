package com.example.varosok1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PerformanceHintManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class InsertActivity extends AppCompatActivity {
    EditText editTextNev,editTextOrszag,editTextLakossag;
    Button buttonFelvesz,buttonVissza;
    String data = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        init();
        onClick();
        buttonFelvesz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nev = editTextNev.getText().toString().trim();
                String orszag = editTextOrszag.getText().toString().trim();
                String lakossag = editTextLakossag.getText().toString().trim();
                String json = "{ \"Nev\" : \""+nev+"\", \"Orszag\" : \""+orszag+"\", \"Lakossag\" : \""+lakossag+"\" }";

                if (nev.equals("") & orszag.equals("") & lakossag.equals("")){
                    Toast.makeText(InsertActivity.this,"Ne hagyjon uresen mezot",Toast.LENGTH_SHORT).show();
                }else{
                    PerformNetworkRequest request = new PerformNetworkRequest("POST", json);
                    request.execute();
                    Toast.makeText(InsertActivity.this,"A felvétel sikeres volt",Toast.LENGTH_SHORT).show();
                }



            }
        });


    }
    private class PerformNetworkRequest extends AsyncTask<Void,Void,String> {
        String method;
        String content;

        public PerformNetworkRequest(String method){
            this.method = method;
            this.content = "";
        }
        public PerformNetworkRequest(String method, String content){
            this.method = method;
            this.content = content;
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                if (method.equals("POST")){
                    data = Request.postData(content);

                }
                else{
                    data = Request.getData();

                }
            }catch (IOException e){
                data = e.getMessage();
            }
            return data;
        }
    }

    public void init(){
        editTextNev = findViewById(R.id.editTextNev);
        editTextOrszag = findViewById(R.id.editTextOrszag);
        editTextLakossag = findViewById(R.id.editTextLakossag);
        buttonFelvesz = findViewById(R.id.buttonFelvesz);
        buttonVissza = findViewById(R.id.buttonVissza);
    }
    public void onClick(){
        buttonVissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InsertActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}