package com.example.pmf2019;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskCardAdapter extends RecyclerView.Adapter<TaskCardAdapter.TaskCardHolder> {
    private ArrayList<Task> mTasks;
    public static class TaskCardHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public TextView description;

        public TaskCardHolder(View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            description=itemView.findViewById(R.id.description);
        }
    }

    public TaskCardAdapter(ArrayList<Task> tasks){
        mTasks = tasks;
    }

    @Override
    public TaskCardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_card,parent,false);
        TaskCardHolder tch = new TaskCardHolder(v);
        return tch;
    }

    @Override
    public void onBindViewHolder(TaskCardHolder holder, int position) {
        Task current= mTasks.get(position);
        holder.title.setText(current.title);
        holder.description.setText(current.description);
    }

    @Override
    public int getItemCount() {
        return mTasks.size();
    }

}
