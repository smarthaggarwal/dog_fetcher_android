package com.rksn.smarth.dogFetcher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    Button btnGetData;
    //public static TextView txtVw;
//     TextView txtVw;
     RequestQueue mQ;
     ImageView imgVW;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGetData = (Button)this.findViewById(R.id.btnGetData);
//        txtVw = (TextView)findViewById(R.id.txtVw);
        imgVW = (ImageView)findViewById(R.id.imgVW);
        mQ = Volley.newRequestQueue(this);
        btnGetData.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View v){
//                fetchData getDat= new fetchData();
//                getDat.execute();
                jsonParse();

            }
        });
    }

    private  void jsonParse(){
//        String url = "https://api.myjson.com/bins/kp9wz";
        String url = "https://dog.ceo/api/breeds/image/random";

//        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try {
//                    JSONArray jsonArray = response.getJSONArray("employees");
//                    for(int i = 0;i<jsonArray.length();i++){
//                        JSONObject jsonObject = jsonArray.getJSONObject(i);
//                        String nam = jsonObject.getString("firstname");
//                        int ag = jsonObject.getInt("age");
//                        String ema = jsonObject.getString("mail");
//                        txtVw.append(nam+","+String.valueOf(ag)+","+ema+"\n\n");
//
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//            }
//        }){
//            @Override protected Map<String,String> getParams() throws AuthFailureError{
//                Map<String ,String > params = new HashMap<String ,String>();
//                params.put("","");
//                params.put("","");
//                return params;
//            }
//        };
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String imgURL = response.getString("message");
                    loadImageURL(imgURL);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mQ.add(req);
    }

    private void loadImageURL(String imgURL) {
        Picasso.with(this).load(imgURL).placeholder(R.mipmap.ic_launcher_round).error(R.mipmap.ic_launcher_round).into(imgVW,new com.squareup.picasso.Callback(){

            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {

            }
        });
    }
}
