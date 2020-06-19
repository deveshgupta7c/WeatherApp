package com.example.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.weatherapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        String url="https://api.openweathermap.org/data/2.5/weather?q=",api="&appid=5d8825b0929273be44e77bbd91db11cf";
        Button button=(Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url="https://api.openweathermap.org/data/2.5/weather?q=",api="&appid=5d8825b0929273be44e77bbd91db11cf";
                EditText editText=(EditText)findViewById(R.id.editText2);
                String city=editText.getText().toString();
                Log.i("show :","abc");
                if(city.length()>0)
                {
                    final String myUrl=url+city+api;

                    final RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                    final JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.GET,myUrl , null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {


                            String info = null;
                            try {
                                info = response.getString("weather");


                                JSONArray  jsonArray =new JSONArray(info);

                                String main =jsonArray.getString(0);

                                JSONObject jsonObject=new JSONObject(main);

                                String maininfo=jsonObject.getString("main");

                                TextView textView=(TextView) findViewById(R.id.textView);

                                textView.setText("Weather : "+maininfo);

                                String Main =response.getString("main");

                                JSONObject jsonObject1 =new JSONObject(Main);



                                String temp =jsonObject1.getString("temp");

                                TextView textView1=(TextView) findViewById(R.id.textView2);

                                float f=Float.parseFloat(temp);

                                float fin=f-(float) 273;

                                String temp1="Temperature : "+Float.toString(fin)+" Celsius";

                                textView1.setText(temp1);




                                Log.i("temp","temp: "+temp);

                            } catch (JSONException e) {
                                Toast.makeText(MainActivity.this,"City Not Found",Toast.LENGTH_LONG).show();
                                e.printStackTrace();
                            }


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this,"City Not Found",Toast.LENGTH_LONG).show();


                        }
                    });
                    requestQueue.add(jsonObjectRequest);
                }
                else
                {
                    editText.setError("Empty!!! Must be filled");

                }
            }

        });

    }
}
