package com.example.a2_1155145472.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.a2_1155145472.domain.Message;
import com.example.a2_1155145472.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MessgaeListAdapter extends RecyclerView.Adapter<MessgaeListAdapter.InnerHolder> {

    private List<Message.DataBean.MessagesBean> data = new ArrayList<>();

    // 把最后一次拿到的消息存到静态， 哪来做测试吧， 懒得构造数据了
    public static Message TEST_DATA = null;

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item, parent, false);
        return new InnerHolder(itemView);
    }

    //绑定每个消息的内容
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        Message.DataBean.MessagesBean dataBean = data.get(position);
        holder.messgaeName.setText(dataBean.name);
        holder.messgaeContent.setText(dataBean.message);
        holder.messgaeDate.setText(dataBean.message_time);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    //自动刷新数据
    public void setData(Message msg) {
        data.clear();
        List<Message.DataBean.MessagesBean> messages = msg.data.messages;
        Collections.reverse(messages);
        data.addAll(messages);
        notifyDataSetChanged();
        TEST_DATA = msg;
    }

    //自动刷新数据
    public void addBefore(Message msg) {
        List<Message.DataBean.MessagesBean> messages = msg.data.messages;
        Collections.reverse(messages);
        data.addAll(0, messages);
        notifyDataSetChanged();
        TEST_DATA = msg;
    }

    public void addInLast(Message.DataBean.MessagesBean msg) {
        this.data.add(msg);
        notifyDataSetChanged();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        TextView messgaeName;
        TextView messgaeContent;
        TextView messgaeDate;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            messgaeName = itemView.findViewById(R.id.message_name);
            messgaeContent = itemView.findViewById(R.id.message_content);
            messgaeDate = itemView.findViewById(R.id.message_date);
        }

    }
}
