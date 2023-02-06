package com.example.fetchapi;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChildItemAdapter extends RecyclerView
        .Adapter<ChildItemAdapter.ChildViewHolder> {

    private List<ChildItem> childList;
    public ChildItemAdapter(List<ChildItem> childList) {
        this.childList=childList;
    }
    @NonNull
    @Override
    public ChildItemAdapter.ChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.child_item,parent,false);
        return  new ChildItemAdapter.ChildViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ChildViewHolder holder, int position) {
        ChildItem childItem = childList.get(position);
        holder.id.setText("id: " + childList.get(position).getId());
        holder.listId.setText("listId: " + childList.get(position).getListId());
        holder.name.setText("name: " + childList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return childList.size();
    }
    public class ChildViewHolder extends RecyclerView.ViewHolder {
        TextView listId,name,id;
        public ChildViewHolder(@NonNull View itemView) {
            super(itemView);
            listId=itemView.findViewById(R.id.ListId);
            name=itemView.findViewById(R.id.Name);
            id=itemView.findViewById(R.id.Id);
        }
    }
}
