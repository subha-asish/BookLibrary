package com.example.booklibrary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;



public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "BookLibrary.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_library";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "book_title";
    private static final String COLUMN_AUTHOR = "book_author";
    private static final String COLUMN_PAGES = "book_pages";

     MyDatabaseHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       String query="CREATE TABLE " + TABLE_NAME +" (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
               COLUMN_TITLE + " TEXT, " +
               COLUMN_AUTHOR + " TEXT, " +
               COLUMN_PAGES + " INTEGER);";
       db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
       onCreate(db);
    }
    void addBook(String title,String author ,String pages){
        //what this method basically do is : When this method is called then 3 parameters are passed(title,author,pages)
        SQLiteDatabase db = this.getWritableDatabase(); //getWritableDatabase() is used because we want to write into our db
        ContentValues cv = new ContentValues(); //This is created to store all our data from our application and pass it to our tables

        /** For The first time you need to write the below command cv.put(COLUMN_ID ,1) **/
        //Otherwise error will come.
        //After adding 1 data then we can comment this out
        //cv.put(COLUMN_ID , 1);
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_AUTHOR, author);
        cv.put(COLUMN_PAGES, pages);
        // COLUMN_ID Is auto Increment

        //All data sre added to cv and then cv is being added to the database table.
        long result = db.insert(TABLE_NAME,null,cv);
        //db.insert method returns an digit , WHICH IS USEFUL IN MANY CASES ,
        if(result==-1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    }

    /** From Here you can memorise or copy paste the CURSOR section, because we are going to cover it later **/
    // JUST Remember that cursor is used to carry/takeout data from database
    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        //In object-oriented programming, this refers to the current
        // instance of the class. In the context of Android development,
        // this typically refers to the current instance of the class that
        // is using the getReadableDatabase() or getWritableDatabase() methods.

        //in Super method we had called to our Database , so this.getReadableDatabase refer to our current databases.

        //getReadableDatabase() method returns a SQLiteDatabase object that allows read-only access to the database.
        // This method is useful when you only need to retrieve data from the database.

        //getWritableDatabase() method returns a SQLiteDatabase object that allows both read and write access to the database.
        // This method is useful when you need to modify the contents of the database.


        Cursor cursor = null;
        if(db != null){ // db is NOT NULL => we have some data in our database
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }
    void updateData(String row_id,String title, String author,String pages){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE,title);
        cv.put(COLUMN_AUTHOR,author);
        cv.put(COLUMN_PAGES,pages);

        long result =db.update(TABLE_NAME, cv, "_id=?",new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed To Update", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully updated", Toast.LENGTH_SHORT).show();
        }
    }
    void deleteOneRow(String row_id){
        SQLiteDatabase db =this.getWritableDatabase();
        long result =db.delete(TABLE_NAME,"_id=?",new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed To Delete", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted", Toast.LENGTH_SHORT).show();
        }
    }
}
