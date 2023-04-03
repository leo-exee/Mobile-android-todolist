package com.example.todo.pojos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.R;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {
    private final List<Todo> todos;
    public class TodoViewHolder extends RecyclerView.ViewHolder {
        public TextView Name;
        public TextView Urgency;

        public TodoViewHolder(View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.name);
            Urgency = itemView.findViewById(R.id.urgency);
        }
    }

    public TodoAdapter(List<Todo> todos) {
        this.todos = todos;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_item, parent, false);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TodoViewHolder holder, int position) {
        Todo todo = todos.get(position);
        holder.Name.setText(todo.getName());
        holder.Urgency.setText(todo.getUrgency());
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }
}
