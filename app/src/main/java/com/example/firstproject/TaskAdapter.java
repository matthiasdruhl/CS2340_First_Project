package com.example.firstproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private List<Task> taskList;
    private OnEditClickListener editClickListener;

    public TaskAdapter(List<Task> taskList) {
    }

    // Interface for edit button click listener
    public interface OnEditClickListener {
        void onEditClick(int position);
    }

    public TaskAdapter(List<Task> taskList, OnEditClickListener editClickListener) {
        this.taskList = taskList;
        this.editClickListener = editClickListener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.taskName.setText(task.getName());
        holder.taskCheckBox.setChecked(task.isCompleted());

        // Set a listener for checkbox state change
        holder.taskCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                task.setCompleted(isChecked);
            }
        });

        // Set a click listener for the delete button
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteTask(position);
            }
        });

        // Set a click listener for the edit button
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editClickListener.onEditClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView taskName;
        CheckBox taskCheckBox;
        ImageButton deleteButton;
        ImageButton editButton;

        TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            taskName = itemView.findViewById(R.id.taskName);
            taskCheckBox = itemView.findViewById(R.id.taskCheckBox);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            editButton = itemView.findViewById(R.id.editButton);
        }
    }

    // Method to delete a task
    private void deleteTask(int position) {
        taskList.remove(position);
        notifyItemRemoved(position);
    }
}
