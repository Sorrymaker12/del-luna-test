package com.example.labux;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class rvAdapter extends RecyclerView.Adapter<rvAdapter.ViewHolder> {

    Context context;
    String[] pPrice;
    String[] pName;
    int[] pImage;


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView pPrice;
        ImageView pImage;

        public ViewHolder (@NonNull View itemView) {
            super(itemView);
            pPrice = itemView.findViewById(R.id.pPrice);
            pImage = itemView.findViewById(R.id.pImage);
        }
    }

    public rvAdapter (Context context, String[] pPrice, int[] pImage) {
        this.context = context;
        this.pPrice = pPrice;
        this.pImage = pImage;
    }

    @NonNull
    @Override
    public rvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull rvAdapter.ViewHolder holder, int position) {
        holder.pPrice.setText(pPrice[position]);
        holder.pImage.setImageResource(pImage[position]);
    }

    @Override
    public int getItemCount() {
        return pPrice.length;
    }
}
