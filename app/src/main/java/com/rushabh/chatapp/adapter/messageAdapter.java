package com.rushabh.chatapp.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rushabh.chatapp.R;
import com.rushabh.chatapp.dataObject.message;

import java.util.ArrayList;

/**
 * Created by Rushabh on 1/18/2017.
 */

public class messageAdapter extends RecyclerView.Adapter<messageAdapter.PersonViewHolder> {
    LinearLayout MainLayout;
    private ArrayList<message> messages;
    private Context context;

    public messageAdapter(ArrayList<message> messages, Context context) {
        this.messages = messages;
        this.context = context;
    }

    @Override
    public messageAdapter.PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(messageAdapter.PersonViewHolder holder, int position) {
        message message = messages.get(position);

        holder.name.setText(message.getName());
        holder.message.setText(message.getMessage());

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class PersonViewHolder extends RecyclerView.ViewHolder {
        TextView name, message;
        CardView cv;
        LinearLayout cardViewLayout;

        public PersonViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            message = (TextView) itemView.findViewById(R.id.message);
            cv = (CardView) itemView.findViewById(R.id.cv);
//            cardViewLayout = (LinearLayout) itemView.findViewById(R.id.cardViewLayout);
        }
    }
}
