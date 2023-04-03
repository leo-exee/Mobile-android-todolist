package com.example.todo.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todo.pojos.Todo;

import java.util.List;

@Dao
public interface TodoDAO {
    @Query("SELECT * FROM todo WHERE id = :id")
    public Todo find(Long id);

    @Query("SELECT * FROM todo")
    public List<Todo> list();

    @Insert
    public void add(Todo... todos);

    @Update
    public void update(Todo... todos);

    @Delete
    public void delete(Todo... todos);
}
