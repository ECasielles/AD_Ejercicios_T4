package com.example.usuario.ad_ejercicios_t4;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.usuario.ad_ejercicios_t4.model.Repository;

import java.util.ArrayList;

/**
 * Created by usuario on 6/02/18.
 */

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.ViewHolder> {

    ArrayList<Repository> repositories;

    public RepositoryAdapter(ArrayList<Repository> repositories) {
        this.repositories = repositories;
    }

    public void setRepository(ArrayList<Repository> repositories) {
        this.repositories = repositories;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View contactView = inflater.inflate(R.layout.item_repository, parent, false);
        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txvName.setText(repositories.get(position).getName());
        holder.txvDescription.setText(repositories.get(position).getDescription().toString());
        holder.txvCreatedAt.setText(repositories.get(position).getCreatedAt());
    }

    @Override
    public int getItemCount() {
        if (repositories != null)
            return repositories.size();
        else
            return 0;
    }

    public Repository get(int position) {
        return repositories.get(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        public TextView txvName, txvDescription, txvCreatedAt;

        public ViewHolder(View itemView) {
            super(itemView);
            txvName = itemView.findViewById(R.id.txvName);
            txvDescription = itemView.findViewById(R.id.txvDescription);
            txvCreatedAt = itemView.findViewById(R.id.txvCreatedAt);
        }
    }
}
