package com.example.pmf2019;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

public class TaskDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME = "TaskDB";
    private static final String TABLE_NAME = "Tasks";
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "tittle";
    private static final String KEY_DESCRIPTION = "description";
    private static final String[] COLUMNS = {KEY_ID,KEY_TITLE,KEY_DESCRIPTION};

    public TaskDBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String  CREATE_TABLE="CREATE TABLE "+TABLE_NAME+" ("+KEY_ID+"INTEGER PRIMARY KEY AUTOINCREMENT, "+KEY_TITLE+" TEXT, "+KEY_DESCRIPTION+" TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        db.close();
    }

    public void deleteTask(Task task){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME,KEY_ID+" = ?", new String[] {String.valueOf(task.id)});
        db.close();
    }

    public void addTask(Task task){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(KEY_ID,task.id);
        values.put(KEY_TITLE,task.tittle);
        values.put(KEY_DESCRIPTION,task.description);
        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public int updateTask(Task task){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(KEY_ID,task.id);
        values.put(KEY_TITLE,task.tittle);
        values.put(KEY_DESCRIPTION,task.description);
        int i = db.update(TABLE_NAME,values,KEY_ID+" = ?",new String[] {String.valueOf(task.id)});
        db.close();
        return i;
    }

    public Task getTask(int id){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                COLUMNS,
                KEY_ID+" = ?",
                new String[] {String.valueOf(id)},
                null,
                null,
                null,
                null
        );
        if(cursor!=null){
            cursor.moveToFirst();
        }

        Task task = new Task(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2));

        return task;
    }

    public List<Task> allTasks(){
        SQLiteDatabase db=this.getReadableDatabase();
        String query= "SELECT * FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        List<Task> tasks = new LinkedList<>();
        Task task=null;

        if(cursor.moveToFirst()){
            do {
                task= new Task(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2));
                tasks.add(task);
            }while(cursor.moveToNext());
        }
        return tasks;

    }

}
