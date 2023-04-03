package com.example.todo;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.todo.databases.TodoDatabase;
import com.example.todo.pojos.Todo;
import com.example.todo.pojos.TodoAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.URIResolver;

public class MainActivity extends AppCompatActivity {
    public Context context;
    public static final String KEY_LIST = "key_list";
    public String Content;
    public RecyclerView TodoList;

    @Override
    protected void onStart() {
        super.onStart();
        TodoAsyncTask todoAsyncTasks = new TodoAsyncTask();
        todoAsyncTasks.execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();

        TodoList = findViewById(R.id.todoList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        TodoList.setHasFixedSize(true);
        TodoList.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addTodo:
                Context context = getApplicationContext();
                Intent intent = new Intent(context, AddTodoActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState() called");
        outState.putString(KEY_LIST, Content);
    }

    public class TodoAsyncTask extends AsyncTask<Nullable, Nullable, List<Todo>> {
        @Override
        protected List<Todo> doInBackground(Nullable... nullables) {
            List<Todo> todos = TodoDatabase.getDb(context).todoDAO().list();

            return todos;
        }
        @Override
        protected void onPostExecute(List<Todo> todos) {
            TodoAdapter todoAdapter = new TodoAdapter(todos);
            TodoList.setAdapter(todoAdapter);
        }
    }
}
