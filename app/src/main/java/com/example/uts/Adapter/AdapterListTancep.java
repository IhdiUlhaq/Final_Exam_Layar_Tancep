package com.example.uts.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uts.DetailLayarTancep;
import com.example.uts.model.LayarTancep;
import com.example.uts.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterListTancep extends RecyclerView.Adapter<AdapterListTancep.ViewHolder> {
    public AdapterListTancep(Context context, List<LayarTancep> viewItems) {
        this.context = context;
        this.viewItems = viewItems;
    }

    Context context;
    List<LayarTancep> viewItems;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_film_layout, null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String poster_path = viewItems.get(position).getPosterPath();
        Picasso.get().load(poster_path).fit().centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.icon);

        holder.txtId.setText(viewItems.get(position).getTitle());
        holder.txtName.setText(viewItems.get(position).getOverview());
        holder.txtName2.setText(viewItems.get(position).getOriginalTitle());
        holder.txtName3.setText(viewItems.get(position).getReleaseDate());





        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //view.getContext().startActivity(new Intent(context, DetailLayarTancep.class));
                Intent intent = new Intent(view.getContext(), DetailLayarTancep.class);
                intent.putExtra("txtTitle", viewItems.get(position).getTitle());
                intent.putExtra("txtOverview", viewItems.get(position).getOverview());
                intent.putExtra("txtDate", viewItems.get(position).getReleaseDate());
                intent.putExtra("txtOriginalTitle", viewItems.get(position).getOriginalTitle());
                intent.putExtra("picture", viewItems.get(position).getPosterPath());
                context.startActivity(intent);
                Toast.makeText(context, viewItems.get(position).getTitle(), Toast.LENGTH_SHORT).show();





            }
        });

    }

    @Override
    public int getItemCount() {
        return viewItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView icon;
        TextView txtId, txtName, txtName2, txtName3;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.icon);
            txtId = itemView.findViewById(R.id.txtId);
            txtName = itemView.findViewById(R.id.txtName);
            txtName2 = itemView.findViewById(R.id.txtName2);
            txtName3 = itemView.findViewById(R.id.txtName3);
        }
    }
}
