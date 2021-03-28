package com.example.a2_1155145472.network;


import okhttp3.ResponseBody;
import  retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface API {
    @GET("api/assign3/get_chatrooms")
    Call<ResponseBody> getJson();

    @GET("api/assign3/get_messages")
    Call<ResponseBody> getMessages(@Query("chatroom_id") Integer chatroom_id, @Query("page") Integer page);

    @FormUrlEncoded
    @POST("api/assign3/send_message")
    Call<ResponseBody> sendMessage(@Field("chatroom_id") Integer chatroom_id,
                                   @Field("user_id") String user_id,
                                   @Field("name") String name,
                                   @Field("message") String message);
}
