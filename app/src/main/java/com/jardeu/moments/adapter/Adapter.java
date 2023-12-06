package com.jardeu.moments.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jardeu.moments.R;
import com.jardeu.moments.model.Memory;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<Memory> memories = new ArrayList<>();
    public Adapter(List<Memory> memories) {
        this.memories = memories;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View listItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_adapter, parent, false);

        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Memory memory = memories.get(position);
        Bitmap bitmap = BitmapFactory.decodeFile(memory.getImage());
        holder.title.setText( memory.getTitle() );
        holder.description.setText( memory.getDescription() );
        holder.date.setText( memory.getDate() );
        holder.image.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return memories.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView description;
        private TextView date;
        private ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.tvTitle);
            description = itemView.findViewById(R.id.tvDescription);
            date = itemView.findViewById(R.id.tvDate);
            image = itemView.findViewById(R.id.imageMemory);
        };

    }
}