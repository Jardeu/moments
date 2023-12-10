package com.jardeu.moments.adapter;

import static androidx.core.app.ActivityCompat.startActivityForResult;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jardeu.moments.R;
import com.jardeu.moments.activity.EditMemoryActivity;
import com.jardeu.moments.activity.HomeActivity;
import com.jardeu.moments.model.Category;
import com.jardeu.moments.model.Memory;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private Context mContext;
    private List<Memory> memories;

    public Adapter(Context context, List<Memory> memories) {
        mContext = context;
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

        holder.btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, EditMemoryActivity.class);
            intent.putExtra("memory_id", memory);
            mContext.startActivity(intent);
        });

        holder.btnDelete.setOnClickListener(view -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Apagar memória");
            builder.setMessage("Tem certeza que deseja apagar essa memória especial?");
            builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Memory.memoriesList.remove(memory);

                    notifyItemRemoved(holder.getAdapterPosition());
                }
            }).setNegativeButton("Não", new DialogInterface.OnClickListener() {
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
        return memories.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView description;
        private TextView date;
        private ImageView image;

        private Button btnEdit, btnDelete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.tvTitle);
            description = itemView.findViewById(R.id.tvDescription);
            date = itemView.findViewById(R.id.tvDate);
            image = itemView.findViewById(R.id.imageMemory);

            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        };

    }
}