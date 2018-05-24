package com.rksn.smarth.dogFetcher;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by smarth on 17/05/18.
 */

public class fetchData extends AsyncTask<Void,Void,Void> {
    String data = "";
    String oneRec = "";
    String pars = "";
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://api.myjson.com/bins/j5f6b");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }
            JSONArray jsonArray = new JSONArray(data);
            for(int i = 0 ; i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                oneRec = "Name:" + jsonObject.getString("name") + "\n" +  "Pass:"+ jsonObject.getString("password")+ "\n"+"Cont:"+ jsonObject.getString("contact") + "\n"+ "Contry:"+ jsonObject.getString("country")+ "\n";
                pars = pars + oneRec;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
//        MainActivity.txtVw.setText(pars);
    }
}
