package com.example.a2_1155145472.websocket;

import android.util.Log;

import com.example.a2_1155145472.adapter.MessgaeListAdapter;
import com.example.a2_1155145472.domain.Message;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

class OthersMessage {

}

public class WebSocketThread extends Thread {


    int SEND_TESET_MESSAGE_PER_SECOND = 2;

    String TAG = "WebSocketThread";



    //
    WebSocketView view = null;
    WebSocket ws = null;
    public WebSocketThread(WebSocketView view) {
        this.view = view;
        String url = "ws://43.128.7.176:8002/real_data";
        Request request = new Request.Builder().get().url(url).build();
        OkHttpClient mClient = new OkHttpClient.Builder()
                .build();
        ws = mClient.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                super.onOpen(webSocket, response);
                //连接成功...
                Log.i(TAG, "open" + response);
            }

            @Override
            public void onMessage(WebSocket webSocket, String text) {
                super.onMessage(webSocket, text);
                //收到消息...（一般是这里处理json）
                Log.i(TAG, "onMessage" + text);

                try {
                    Message.DataBean.MessagesBean bean = new Gson().fromJson(text, Message.DataBean.MessagesBean.class);
                    if (bean != null) {
                        if (bean.chatroom_id == view.getClassRoomId()) {
                            view.onWebSocketRender(bean);
                        }
                    }
                } catch (Exception e) {

                }

            }


            @Override
            public void onClosed(WebSocket webSocket, int code, String reason) {
                super.onClosed(webSocket, code, reason);
                //连接关闭...
                Log.i(TAG, "onClosed" + reason);
            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable throwable, Response response) {
                super.onFailure(webSocket, throwable, response);
                //连接失败...
                Log.i(TAG, "onFailure" + response);
                Log.i(TAG, "onFailure" + throwable.getMessage());
            }
        });
    }

    @Override
    public void run() {
        super.run();


        ws.send(view.getClassRoom());


    }

}
