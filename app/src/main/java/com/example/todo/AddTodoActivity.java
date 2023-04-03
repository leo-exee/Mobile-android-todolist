package com.example.todo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.todo.databases.TodoDatabase;
import com.example.todo.pojos.Todo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddTodoActivity extends AppCompatActivity {

    private Context context;
    private Button Add;
    private Button Cancel;
    private Spinner Urgency;
    private EditText Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);
        context = getApplicationContext();

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        Urgency = findViewById(R.id.urgency);

        String[] plants = new String[]{
                "Low",
                "Medium",
                "High"
        };

        List<String> plantsList = new ArrayList<>
                (Arrays.asList(plants));

        ArrayAdapter<String> spinnerArrayAdapter
                = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                plantsList
        );

        spinnerArrayAdapter.setDropDownViewResource(
                android.R.layout.simple_dropdown_item_1line
        );
        Urgency.setAdapter(spinnerArrayAdapter);

        Name = findViewById(R.id.name);
        Add = findViewById(R.id.add);
        Cancel = findViewById(R.id.cancel);

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Todo todo = new Todo();
                todo.setName(Name.getText().toString());
                todo.setUrgency(Urgency.getSelectedItem().toString());
                TodoDatabase.getDb(context).todoDAO().add(todo);
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}