package com.example.lockbot;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.widget.Toast;

import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper {

    //Recycle bin related parameters
    public static final String FILE_DATABASE_NAME = "lockFile.db";
    public static final String FILE_TABLE_NAME = "locked_files";
    public static final String FILE_COLUMN_ID = "id";
    public static final String FILE_COLUMN_FILE_NAME = "fileName";
    public static final String FILE_COLUMN_DATE = "date";
    public static final String FILE_COLUMN_FILE_TYPE = "fileType";
    public static final String FILE_COLUMN_VISIBILITY = "visibility";


    public static final String DATABASE_NAME="newLogin.db";
    public static final String TABLE_NAME="LogingDetails";
    public static final String col1="userName";
   //public static final String col2="passwords";
   private static final String DATABASE_PATH = "/data/data/com.example.lockbot/databases/newLogin.db";
    SQLiteDatabase db;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + FILE_TABLE_NAME + "(" + FILE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ FILE_COLUMN_FILE_NAME +" TEXT, "+ FILE_COLUMN_DATE +" TEXT, "+ FILE_COLUMN_FILE_TYPE +" TEXT, "+ FILE_COLUMN_VISIBILITY +" BOOL)";

        db.execSQL(createTableStatement);
        db.execSQL("Create table LoginDetails(userName text primary key,passwords text) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("Drop table if exists LoginDetails");
        onCreate(db);
    }
    //inserting in database
    public boolean insert(String username,String password){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("userName",username);
        contentValues.put("passwords",password);
        long ins=db.insert("LoginDetails",null,contentValues);
        if (ins==-1){
            return false;
        }else{
            return true;
        }
    }
//insert calculator answers
  /*  public boolean insertans(String answer){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("answers",answer);


        long ins=db.insert("History",null,contentValues);
        if (ins==-1){
            return false;
        }else{
            return true;
        }
    }
*/
   /* public Cursor viewdata(String s){
      //  Cursor cursor = this.database.query(SQLiteHelper.LoginDetails, new String[]{SQLiteHelper._ID, SQLiteHelper.NAME, SQLiteHelper.AGE}, null, null, null, null, null);
        //if (cursor != null) {
          //  cursor.moveToFirst();
        //}
        //return cursor;

       /* SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select userName form" +TABLE_NAME, null);
        return cursor;
       SQLiteDatabase db=this.getWritableDatabase();
       String qry="select userName from "+TABLE_NAME+"";

       Cursor crs=db.rawQuery(qry,null);

        return crs;
    }*/
   //retrieve name form the database
    public Cursor showname(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from LoginDetails",null);
        return cursor;
    }
    public Cursor showtot(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from History",null);
        return cursor;
    }

    public boolean login(String password){
        String sql="select count(*) from LoginDetails where passwords='"+password+"'";
        SQLiteStatement statement=getReadableDatabase().compileStatement(sql);
        long l=statement.simpleQueryForLong();
        statement.close();
        if (l==1){
            return true;
        }else{
            return false;
        }
    }
    public boolean update(String password,String username){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cValues=new ContentValues();
        cValues.put("passwords",password);
        cValues.put("userName",username);
        //long updatevalues=db.update("LoginDetails",null,cValues);
            long updatevalues= db.update("LoginDetails",cValues,"userName=?",new String[]{String.valueOf(username)});

        if (updatevalues==-1){
            return false;
        }else{
            return true;
        }
    }
    public boolean checking(String username){
        String sql="select count(*) from LoginDetails where userName='"+username+"'";
        SQLiteStatement statement=getReadableDatabase().compileStatement(sql);
        long l=statement.simpleQueryForLong();
        statement.close();
        if (l==1){
            return true;
        }else{
            return false;
        }

    }
    private SQLiteDatabase openDatabase(){
        String path = DATABASE_PATH + DATABASE_NAME;
        db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
        return db;
    }

    public boolean checkUserExist(String username){
        String[] columns = {"userName"};
        db = openDatabase();

        String selection = "userName=?";
        String[] selectionArgs = {username};

        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();

        cursor.close();
        close();

        if(count > 0){
            return true;
        } else {
            return false;
        }
    }

    // Recycle bin related db query requirements

    public boolean insertFile (FileModel fileModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

//        contentValues.put(FILE_COLUMN_ID, fileModel.getId());
        contentValues.put(FILE_COLUMN_FILE_NAME, fileModel.getFileName());
        contentValues.put(FILE_COLUMN_DATE, fileModel.getDate());
        contentValues.put(FILE_COLUMN_FILE_TYPE, fileModel.getFileType());
        contentValues.put(FILE_COLUMN_VISIBILITY, fileModel.getVisibility());

        long insert = db.insert(FILE_TABLE_NAME, null, contentValues);

        db.close();

        return insert != -1;
    }

    public Integer deleteFiles (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(FILE_TABLE_NAME,
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public Integer restoreFiles (Integer id){
        ContentValues cv = new ContentValues();
        cv.put(FILE_COLUMN_VISIBILITY,1);
        SQLiteDatabase db = this.getWritableDatabase();
        return db.update(FILE_TABLE_NAME,cv,"id = "+id,null);
    }

    public ArrayList<FileModel> getAllFiles() {
        ArrayList<FileModel> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM "+ FILE_TABLE_NAME +" WHERE "+ FILE_COLUMN_VISIBILITY +"=0";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result =  db.rawQuery(queryString,null);

        if(result.moveToFirst()){
            do{
                int fileID = result.getInt(0);
                String fileName = result.getString(1);
                String fileDate = result.getString(2);
                String fileType = result.getString(3);
                boolean fileVisibility = result.getInt(4) == 1;

                FileModel fileModel = new FileModel(fileID, fileName, fileDate, fileType, fileVisibility);
                returnList.add(fileModel);

            }while(result.moveToNext());
        }
        else {
            //define what should appear if the list is empty
        };

        result.close();
        db.close();
        return returnList;
    }

    public ArrayList<FileModel> getDocFiles() {
        ArrayList<FileModel> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM "+ FILE_TABLE_NAME +" WHERE "+ FILE_COLUMN_FILE_TYPE + "=\"Document\" AND "+ FILE_COLUMN_VISIBILITY +"=0";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result =  db.rawQuery(queryString,null);

        if(result.moveToFirst()){
            do{
                int fileID = result.getInt(0);
                String fileName = result.getString(1);
                String fileDate = result.getString(2);
                String fileType = result.getString(3);
                boolean fileVisibility = result.getInt(4) == 1;

                FileModel fileModel = new FileModel(fileID, fileName, fileDate, fileType, fileVisibility);
                returnList.add(fileModel);

            }while(result.moveToNext());
        }
        else {
            //define what should appear if the list is empty
        };

        result.close();
        db.close();

        return returnList;
    }

    public ArrayList<FileModel> getImageFiles() {
        ArrayList<FileModel> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM "+ FILE_TABLE_NAME +" WHERE "+ FILE_COLUMN_FILE_TYPE + "=\"Image\" AND "+ FILE_COLUMN_VISIBILITY +"=0";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result =  db.rawQuery(queryString,null);

        if(result.moveToFirst()){
            do{
                int fileID = result.getInt(0);
                String fileName = result.getString(1);
                String fileDate = result.getString(2);
                String fileType = result.getString(3);
                boolean fileVisibility = result.getInt(4) == 1;

                FileModel fileModel = new FileModel(fileID, fileName, fileDate, fileType, fileVisibility);
                returnList.add(fileModel);

            }while(result.moveToNext());
        }
        else {
            //define what should appear if the list is empty
        };

        result.close();
        db.close();

        return returnList;
    }

    public ArrayList<FileModel> getVideoFiles() {
        ArrayList<FileModel> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM "+ FILE_TABLE_NAME +" WHERE "+ FILE_COLUMN_FILE_TYPE + "=\"Video\" AND "+ FILE_COLUMN_VISIBILITY +"=0";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result =  db.rawQuery(queryString,null);

        if(result.moveToFirst()){
            do{
                int fileID = result.getInt(0);
                String fileName = result.getString(1);
                String fileDate = result.getString(2);
                String fileType = result.getString(3);
                boolean fileVisibility = result.getInt(4) == 1;

                FileModel fileModel = new FileModel(fileID, fileName, fileDate, fileType, fileVisibility);
                returnList.add(fileModel);

            }while(result.moveToNext());
        }
        else {
            //define what should appear if the list is empty
        };

        result.close();
        db.close();

        return returnList;
    }

}
