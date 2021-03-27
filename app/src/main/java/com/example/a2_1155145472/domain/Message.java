package com.example.a2_1155145472.domain;

import java.util.List;

public class Message {
    /**
     * status : OK
     * data : {"current_page":"1","messages":[{"id":1161,"chatroom_id":1,"user_id":1155147499,"name":"Huang","message":"hello","message_time":"2021-03-01 13:25:19"},{"id":1160,"chatroom_id":1,"user_id":1155150494,"name":"Siqin Li","message":"test","message_time":"2021-03-01 13:15:14"},{"id":1158,"chatroom_id":1,"user_id":1155152636,"name":"CHEN Jiaxian","message":"good luck","message_time":"2021-03-01 12:52:42"},{"id":1154,"chatroom_id":1,"user_id":1155155009,"name":"Junjie Liang","message":"hi","message_time":"2021-03-01 11:15:04"},{"id":1153,"chatroom_id":1,"user_id":1155155009,"name":"Junjie Liang","message":"hiiiii","message_time":"2021-03-01 11:09:21"}],"total_pages":144}
     */

    public String status;
    public DataBean data;

    public static class DataBean {
        /**
         * current_page : 1
         * messages : [{"id":1161,"chatroom_id":1,"user_id":1155147499,"name":"Huang","message":"hello","message_time":"2021-03-01 13:25:19"},{"id":1160,"chatroom_id":1,"user_id":1155150494,"name":"Siqin Li","message":"test","message_time":"2021-03-01 13:15:14"},{"id":1158,"chatroom_id":1,"user_id":1155152636,"name":"CHEN Jiaxian","message":"good luck","message_time":"2021-03-01 12:52:42"},{"id":1154,"chatroom_id":1,"user_id":1155155009,"name":"Junjie Liang","message":"hi","message_time":"2021-03-01 11:15:04"},{"id":1153,"chatroom_id":1,"user_id":1155155009,"name":"Junjie Liang","message":"hiiiii","message_time":"2021-03-01 11:09:21"}]
         * total_pages : 144
         */

        public String current_page;
        public int total_pages;
        public List<MessagesBean> messages;

        public static class MessagesBean {
            /**
             * id : 1161
             * chatroom_id : 1
             * user_id : 1155147499
             * name : Huang
             * message : hello
             * message_time : 2021-03-01 13:25:19
             */

            public int id;
            public int chatroom_id;
            public int user_id;
            public String name;
            public String message;
            public String message_time;
        }
    }
}
