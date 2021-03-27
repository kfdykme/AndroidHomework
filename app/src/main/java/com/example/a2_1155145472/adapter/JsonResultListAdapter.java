package com.example.a2_1155145472.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2_1155145472.AppGlobals;
import com.example.a2_1155145472.R;
import com.example.a2_1155145472.activity.ChatPage1;
import com.example.a2_1155145472.domain.JsonResult;

import java.util.ArrayList;
import java.util.List;

public class JsonResultListAdapter extends RecyclerView.Adapter<JsonResultListAdapter.InnerHolder> {

    private List<JsonResult.DataBean> data = new ArrayList<>();

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chatroomandid,parent,false);
        return new InnerHolder(itemView);
    }


    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
//绑定数据

        JsonResult.DataBean dataBean = data.get(position);
        holder.roomid.setText(dataBean.getId() + " " + dataBean.getName());
        holder.roomid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(AppGlobals.getApplication(), ChatPage1.class);
                intent.putExtra("roomid",dataBean.getId());
                intent.putExtra("name",dataBean.getName());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                AppGlobals.getApplication().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(JsonResult jsonResult) {
        data.clear();
        data.addAll(jsonResult.getData());
        notifyDataSetChanged();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        Button roomid;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            roomid = itemView.findViewById(R.id.chatroom);

        }

    }
}
