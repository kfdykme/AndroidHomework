package com.example.a2_1155145472.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import com.example.a2_1155145472.AppGlobals;
import com.example.a2_1155145472.R;
import com.example.a2_1155145472.SpacesItemDecoration;
import com.example.a2_1155145472.adapter.MessgaeListAdapter;
import com.example.a2_1155145472.domain.Message;
import com.example.a2_1155145472.network.API;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ChatPage1 extends AppCompatActivity {

    private RecyclerView msgListView;
    private EditText inputText;
    private Button send;
    private MessgaeListAdapter mAdapter;
    private List<Message.DataBean.MessagesBean> messagesBeanList = new ArrayList<Message.DataBean.MessagesBean>();
    private SimpleDateFormat df = new SimpleDateFormat("mm:ss", Locale.CHINA);
    private int mRoomId;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://3.135.234.121/")
            .build();


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.chatpage);
            //拿到当前的chatromid
            mRoomId = getIntent().getIntExtra("roomid", 1);
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setHomeButtonEnabled(true);
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setTitle(getIntent().getStringExtra("name") + mRoomId);
            }
            msgListView = findViewById(R.id.message_recycle);
            inputText = (EditText) findViewById(R.id.input_text);
            send = (Button) findViewById(R.id.send);

            //点击发送message
            send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String content = inputText.getText().toString();
                    if (!"".equals(content)) {
                        API api = retrofit.create(API.class);
                        Call<ResponseBody> task = api.sendMessage(mRoomId, "1155145472", "CHENG YUQIN", content);
                        task.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if (response.code() == HttpURLConnection.HTTP_OK) {
                                    inputText.setText("");
                                    Toast.makeText(AppGlobals.getApplication(),"ok",Toast.LENGTH_SHORT).show();
                                    try {
                                        String result = response.body().string();
                                        android.util.Log.e("TAG2", "result=x" + result);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    initMsgs();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Log.d("TAG2", "onFailure..." + t.toString());
                                Toast.makeText(AppGlobals.getApplication(),"fail:" + t.toString(),Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });
            initMessageList();
            initMsgs();
        }

        //初始化 recyclerView
        public void initMessageList() {
            mAdapter = new MessgaeListAdapter();
            //设置垂直布局
            msgListView.setLayoutManager(new LinearLayoutManager(this));
            //设置item的间距
            msgListView.addItemDecoration(new SpacesItemDecoration(30));
            msgListView.setAdapter(mAdapter);
        }

        //http://3.135.234.121/api/a2/get_messages?chatroom_id=1&page=0
        //加载第一页消息
        private void initMsgs() {
            API api = retrofit.create(API.class);
            Call<ResponseBody> task = api.getMessages(mRoomId, 1);
            task.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.code() == HttpURLConnection.HTTP_OK) {
                        try {
                            String result = response.body().string();
                            Gson gson = new Gson();
                            Message message = gson.fromJson(result, Message.class);
                            mAdapter.setData(message);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.d("TAG2", "onFailure..." + t.toString());
                }
            });
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_chat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
            case R.id.action_refresh:
                initMsgs();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

