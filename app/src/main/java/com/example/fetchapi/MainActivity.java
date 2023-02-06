package com.example.fetchapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {

    String url="https://fetch-hiring.s3.amazonaws.com/hiring.json";
    ParentItemAdapter parentAdapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.parent_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        GetData();
    }

    List<ParentItem> parentList = new ArrayList<>();
    private void GetData() {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                SortedMap<String, List<ChildItem>> map = new TreeMap<>();
                for(int i=0;i<=response.length();i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        if (jsonObject.getString("name").length() > 0 && !jsonObject.getString("name").equals("null")) {
//                            postList.add(new PostModel(
//                                    jsonObject.getInt("id"),
//                                    jsonObject.getString("listId"),
//                                    jsonObject.getString("name")
//                            ));
                            String curListId = jsonObject.getString("listId");
                            if (map.containsKey(curListId)) {
                                map.get(curListId).add(new ChildItem(
                                        jsonObject.getInt("id"),
                                        jsonObject.getString("listId"),
                                        jsonObject.getString("name")
                                ));
                            }
                            else {
                                ArrayList<ChildItem> emptyList = new ArrayList<ChildItem>();
                                emptyList.add(new ChildItem(
                                        jsonObject.getInt("id"),
                                        jsonObject.getString("listId"),
                                        jsonObject.getString("name")
                                ));
                                map.put(curListId, emptyList);
                            }
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                }
                for (Map.Entry mapElement : map.entrySet()) {
                    String key = (String) mapElement.getKey();
                    List<ChildItem> childList = (List<ChildItem>) mapElement.getValue();
                    Collections.sort(childList, Comparator.comparing(ChildItem::getName));
                    parentList.add(new ParentItem(key, childList));
                }
                parentAdapter = new ParentItemAdapter(getApplicationContext(), parentList);
                recyclerView.setAdapter(parentAdapter);
                recyclerView.setLayoutManager(recyclerView.getLayoutManager());
                parentAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
//                Collections.sort(postList, Comparator.comparing(PostModel::getListId)
//                        .thenComparing(PostModel::getName));
//                adapter=new PostAdapter(getApplicationContext(),postList);
//                recyclerView.setAdapter(adapter);
//                adapter.notifyDataSetChanged();
//                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}