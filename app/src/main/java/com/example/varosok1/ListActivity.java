package com.example.varosok1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class ListActivity extends AppCompatActivity {
    TextView textViewList;
    Button buttonVissza;
    String data = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        init();
        onClick();
        textViewList.setMovementMethod(new ScrollingMovementMethod());
        listaz();

    }
    private void frissitMuvelet(){
        this.runOnUiThread(frissites);
    }
    private Runnable frissites = new Runnable() {
        @Override
        public void run() {
            textViewList.setText(data);
        }
    };

    private void listaz(){
        PerformNetworkRequest request = new PerformNetworkRequest("GET");
        request.execute();
    }

    public void init(){
        textViewList = findViewById(R.id.textViewList);
        buttonVissza = findViewById(R.id.buttonVissza);
    }
    public void onClick(){
        buttonVissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private class PerformNetworkRequest extends AsyncTask<Void,Void,String>{
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
                if (method.equals("GET")){
                    data = Request.getData();
                }
                else{
                    data = Request.postData(content);
                }
            }catch (IOException e){
                data = e.getMessage();
            }
            frissitMuvelet();
            return data;
        }
    }
}