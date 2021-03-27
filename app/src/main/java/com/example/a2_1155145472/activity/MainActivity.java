package com.example.a2_1155145472.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.a2_1155145472.R;
import com.example.a2_1155145472.adapter.JsonResultListAdapter;
import com.example.a2_1155145472.domain.JsonResult;
import com.example.a2_1155145472.network.API;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import  retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private RecyclerView mResultList;
    private JsonResultListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    private void initView() {
        mResultList = findViewById(R.id.result_list);
        mResultList.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new JsonResultListAdapter();
        mResultList.setAdapter(mAdapter);
    }

    public void getRequest(View view){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://3.135.234.121/")
                .build();
        API api = retrofit.create(API.class);
        Call<ResponseBody> task = api.getJson();
        task.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(TAG,"onResponse --- >" + response.code());
                if (response.code() == HttpURLConnection.HTTP_OK){
                    try {
                        String result = response.body().string();
                        Gson gson = new Gson();
                        JsonResult jsonResult = gson.fromJson(result,JsonResult.class);
                        updateList(jsonResult);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG,"onFailure..." + t.toString());
            }
        });
    }

    private void updateList(JsonResult jsonResult) {
        mAdapter.setData(jsonResult);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}