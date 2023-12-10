package com.jardeu.moments.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jardeu.moments.R;
import com.jardeu.moments.model.Category;
import com.jardeu.moments.model.Memory;

import java.util.ArrayList;
import java.util.List;

public class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.CategoryViewHolder> {

    private List<Category> categories;
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

        holder.btnDelete.setOnClickListener(view -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Apagar categoria");
            builder.setMessage("Tem certeza que deseja apagar essa categoria? " +
                    "As memórias relacionadas a esta categoria não serão apagadas.");
            builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    for (Memory mem: Memory.memoriesList) {
                        if (mem.getCategory_id() == category.getId()){
                            mem.setCategory_id(-1);
                        }
                    }
                    Category.categoriesList.remove(category);

                    notifyItemRemoved(holder.getAdapterPosition());
                }
            });

            builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private ImageButton btnDelete;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tvName);
            btnDelete = itemView.findViewById(R.id.btnDelete2);
        };

    }
}
