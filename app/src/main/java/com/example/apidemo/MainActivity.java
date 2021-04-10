package com.example.apidemo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apidemo.dto.itemModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    ListView listView;
    ArrayList<itemModel> arrayList;
//    SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
//        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
//
        arrayList = new ArrayList<>();
        new fetchData().execute();
//        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                new fetchData().execute();
//            }
//        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemModel itemModel = arrayList.get(position);
                Log.d(TAG, "onItemClick_itemModel: " + itemModel);
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("id", itemModel.getId()); // getText() SHOULD NOT be static!!!
                intent.putExtra("name", itemModel.getName());
                intent.putExtra("email", itemModel.getEmail());
                intent.putExtra("url", itemModel.getAvt_url());
                startActivity(intent);
            }
        });
    }

    public class fetchData extends AsyncTask<String, String, String> {

        @Override
        public void onPreExecute() {
            super .onPreExecute();
//            swipeRefresh.setRefreshing(true);
        }

        @Override
        protected String doInBackground(String... params) {
            arrayList.clear();
            String result = null;
            try {
                Log.d(TAG, "doInBackground: ");
                URL url = new URL("https://reqres.in/api/users?page=2");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.connect();

                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStreamReader inputStreamReader = new InputStreamReader(conn.getInputStream());
                    BufferedReader reader = new BufferedReader(inputStreamReader);
                    StringBuilder stringBuilder = new StringBuilder();
                    String temp;

                    while ((temp = reader.readLine()) != null) {
                        stringBuilder.append(temp);
                    }
                    result = stringBuilder.toString();
                }else  {
                    result = "error";
                }

            } catch (Exception  e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        public void onPostExecute(String s) {
            super .onPostExecute(s);
            Log.d(TAG, "onPostExecute: ");
//            swipeRefresh.setRefreshing(false);
            try {
                JSONObject object = new JSONObject(s);
                Log.d(TAG, "onPostExecute_object: " + object);
                JSONArray array = object.getJSONArray("data");

                for (int i = 0; i < array.length(); i++) {

//

                    JSONObject jsonObject = array.getJSONObject(i);
                    String id = jsonObject.getString("id");
                    String first_name = jsonObject.getString("first_name");
                    String last_name = jsonObject.getString("last_name");
                    String email = jsonObject.getString("email");
                    String avt_url = jsonObject.getString("avatar");


                    itemModel model = new itemModel();
                    model.setId(id);
                    model.setName(first_name + " " + last_name);
                    model.setEmail(email);
                    model.setAvt_url(avt_url);

                    arrayList.add(model);
                }
            } catch (JSONException  e) {
                e.printStackTrace();
            }

            CustomAdapter adapter = new CustomAdapter(MainActivity.this, arrayList);
            listView.setAdapter(adapter);

        }
    }
}