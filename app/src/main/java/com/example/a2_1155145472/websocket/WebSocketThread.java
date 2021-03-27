package com.example.a2_1155145472.websocket;

import com.example.a2_1155145472.adapter.MessgaeListAdapter;
import com.example.a2_1155145472.domain.Message;

import java.util.function.Predicate;
import java.util.stream.Collectors;

public class WebSocketThread extends Thread {


    int SEND_TESET_MESSAGE_PER_SECOND = 2;

    //
    WebSocketView view = null;

    public WebSocketThread(WebSocketView view) {
        this.view = view;
    }

    @Override
    public void run() {
        super.run();



        while (true) {
            try {
                Thread.sleep(1000 * 2);


                if (MessgaeListAdapter.TEST_DATA != null) {
                    //过滤一下 一个就够了
                    MessgaeListAdapter.TEST_DATA.data.messages = MessgaeListAdapter.TEST_DATA.data.messages.subList(0, 1);
                    view.onWebSocketRender(  MessgaeListAdapter.TEST_DATA);
                } else {
                    Message testdata = new Message();
                    testdata.data = new Message.DataBean();
                    // 把你拿到的新消息塞进去就好了，
                    view.onWebSocketRender(testdata);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
