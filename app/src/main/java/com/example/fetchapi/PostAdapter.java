package com.example.fetchapi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    List<PostModel> postList;
    public PostAdapter(Context context, List<PostModel> postList) {
        this.postList=postList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);
        return new ViewHolder(view);
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.id.setText("id: " + postList.get(position).getId());
        holder.listId.setText("listId: " + postList.get(position).getListId());
        holder.name.setText("name: " + postList.get(position).getName());
    }
    @Override
    public int getItemCount() {
        return postList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView listId,name,id;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            listId=itemView.findViewById(R.id.ListId);
            name=itemView.findViewById(R.id.Name);
            id=itemView.findViewById(R.id.Id);
        }
    }
}
