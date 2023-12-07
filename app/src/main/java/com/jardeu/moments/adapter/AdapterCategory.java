package com.jardeu.moments.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jardeu.moments.R;
import com.jardeu.moments.model.Category;

import java.util.ArrayList;
import java.util.List;

public class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.CategoryViewHolder> {

    private List<Category> categories = new ArrayList<>();
    public AdapterCategory(List<Category> categories) {
        this.categories = categories;
    }

    @NonNull
    @Override
    public AdapterCategory.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View listItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_adapter_category, parent, false);

        return new AdapterCategory.CategoryViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCategory.CategoryViewHolder holder, int position) {

        Category category = categories.get(position);
        holder.name.setText( category.getName() );
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{
        private TextView name;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tvName);
        };

    }
}
