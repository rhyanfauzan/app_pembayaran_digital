package com.ash.mobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.ash.mobile.R;
import com.ash.mobile.decoration.model;

import java.util.ArrayList;

public class RecylerViewAdapter extends RecyclerView.Adapter<RecylerViewAdapter.ViewHolder> {


    ArrayList<model> models = new ArrayList<>();
    Context context;

    public boolean isShimmer;
    int ShimmerNumber = 5;

    public RecylerViewAdapter(ArrayList<model> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (isShimmer) {
            holder.shimmerFrameLayout.startShimmer();
        }else {
            holder.shimmerFrameLayout.stopShimmer();
            holder.shimmerFrameLayout.setShimmer(null);

        }
    }

    @Override
    public int getItemCount() {
        return isShimmer?ShimmerNumber:models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

       ShimmerFrameLayout shimmerFrameLayout;

       public ViewHolder(@NonNull View itemView) {
           super(itemView);

           shimmerFrameLayout = itemView.findViewById(R.id.shimmerFrameLayout);
       }
   }
}
