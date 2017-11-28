package com.example.acer.testasyntask;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ProgressDialog pd;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pd=new ProgressDialog(this);
        pd.setMessage("Loading..");
        pd.setCancelable(false);
        Button btnload=findViewById(R.id.btntext);
        tv=findViewById(R.id.textview);
        btnload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MyAsynTask().execute("https://jsonplaceholder.typicode.com/posts/100");
            }
        });
    }
    class MyAsynTask extends AsyncTask<String,Integer,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd.show();}
        @Override
        protected String doInBackground(String... strings) {
            String str="";
           try {
               URL url=new URL(strings[0]);
               HttpURLConnection connection= (HttpURLConnection) url.openConnection();
               connection.setRequestMethod("GET");
               InputStreamReader inputStreamReader=new InputStreamReader(connection.getInputStream());
               BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
               int c;
               while ((c= bufferedReader.read())!=-1){
                   str+=String.valueOf((char)c);}
               return str;
           }catch (MalformedURLException e){
               e.printStackTrace();
           } catch (ProtocolException e) {
               e.printStackTrace();
           } catch (IOException e) {
               e.printStackTrace();}
            return null;}
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.dismiss();
            tv.setText(s);
            Log.e("aaaa",s);}
    }
}
