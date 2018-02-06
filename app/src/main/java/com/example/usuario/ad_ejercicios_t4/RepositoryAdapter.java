package com.example.usuario.ad_ejercicios_t4;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.usuario.ad_ejercicios_t4.model.Git;

import java.util.ArrayList;

/**
 * Created by usuario on 6/02/18.
 */

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.ViewHolder> {

    ArrayList<Git> repos;

    public RepositoryAdapter() {
        this.repos = new ArrayList<>();
    }

    public void setRepsitory(ArrayList<Git> repos) {
        this.repos = repos;
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
        holder.txvName.setText(repos.get(position).getName());
        holder.txvDescription.setText(repos.get(position).getDescription().toString());
        holder.txvCreatedAt.setText(repos.get(position).getCreatedAt());
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    public Git get(int position) {
        return repos.get(position);
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
