package com.example.fetchapi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ParentItemAdapter extends RecyclerView
        .Adapter<ParentItemAdapter.ParentViewHolder> {

    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private List<ParentItem> parentList;
    public ParentItemAdapter(Context context, List<ParentItem> parentList) {
        this.parentList=parentList;
    }

    @NonNull
    @Override
    public ParentItemAdapter.ParentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_item,parent,false);
        return  new ParentItemAdapter.ParentViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ParentItemAdapter.ParentViewHolder parentViewHolder, int position) {
        ParentItem parentItem = parentList.get(position);
        parentViewHolder.ParentListId.setText("List Group: " + parentItem.getParentListId() + ", scroll right for more items");
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                parentViewHolder.ChildRecyclerView.getContext(),
                LinearLayoutManager.HORIZONTAL, false
        );
        layoutManager.setInitialPrefetchItemCount(parentItem.getChildItemList().size());
        ChildItemAdapter childItemAdapter = new ChildItemAdapter(parentItem.getChildItemList());
        parentViewHolder.ChildRecyclerView.setLayoutManager(layoutManager);
        parentViewHolder.ChildRecyclerView.setAdapter(childItemAdapter);
        parentViewHolder.ChildRecyclerView.setRecycledViewPool(viewPool);
    }

    @Override
    public int getItemCount() {
        return parentList.size();
    }

    public class ParentViewHolder extends RecyclerView.ViewHolder {
        private TextView ParentListId;
        private RecyclerView ChildRecyclerView;
        public ParentViewHolder(@NonNull View itemView) {
            super(itemView);
            ParentListId = itemView.findViewById(R.id.parent_list_id);
            ChildRecyclerView = itemView.findViewById(R.id.child_recyclerview);
        }
    }
}
