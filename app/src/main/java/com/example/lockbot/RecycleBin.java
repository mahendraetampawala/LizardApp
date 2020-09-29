package com.example.lockbot;


import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleBin extends AppCompatActivity{

    private static final String TAG = "eee" ;
    private ListView rec_file_list;
    private ActionMode recycleActionMode;
    private ArrayList<FileModel> allFileList= new ArrayList<>();
    private ArrayList<FileModel> docFileList= new ArrayList<>();
    private ArrayList<FileModel> imageFileList= new ArrayList<>();
    private ArrayList<FileModel> videoFileList= new ArrayList<>();
    private ArrayList<FileModel> selectedFiles= new ArrayList<>();
    private int counter = 0;
    private Toolbar headingBar;
    private FileListAdapter fileListAdapter;
    private RecyclerView recyclerView;
    public boolean isActionMode = false;
    public int position = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_bin);
        headingBar = findViewById(R.id.headingRecycle);
        setSupportActionBar(headingBar);
        setTitle(R.string.headingRecycle);

        recyclerView = findViewById(R.id.recycleFileList);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        DatabaseHelper dbHelper = new DatabaseHelper(this);

        showAllFiles(dbHelper);


        headingBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FileModel fileModel;

                try {
                    fileModel = new FileModel(-1,"recycle image file name","20-10-2025","Image",false);
                }catch (Exception e){
                    fileModel = new FileModel(-1,"Error recycle file","31-05-2020","Document",true);
                }

                DatabaseHelper dbHelper = new DatabaseHelper(RecycleBin.this);

                Log.d(TAG, "onCreate: fileModel"+ fileModel);

                boolean insertSuccess = dbHelper.insertFile(fileModel);

                Toast.makeText(RecycleBin.this,"Success"+ insertSuccess, Toast.LENGTH_SHORT).show();
            }
        });

        dbHelper.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.recycle_filter_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        switch (item.getItemId()){
            case R.id.allFiles:
                showAllFiles(dbHelper);
                return true;
            case R.id.filterFilesByDocs:
                showDocFiles(dbHelper);
                return true;
            case R.id.filterFilesByImages:
                showImageFiles(dbHelper);
                return true;
            case R.id.filterFilesByVids:
                showVideoFiles(dbHelper);
                return true;
            default:
                dbHelper.close();
                return super.onOptionsItemSelected(item);
        }

    }

    public void startSelection(int index){
        if(!isActionMode){
            isActionMode = true;
            recycleActionMode = startSupportActionMode(recycleActionCallBack);
            selectedFiles.add(allFileList.get(index));
            counter ++;
            updateActionBarText(counter, recycleActionMode);
            position = index;
            fileListAdapter.notifyDataSetChanged();

        }
    }

    public void checkFiles(View v, int index){
        if(((CheckBox)v).isChecked()){
            selectedFiles.add(allFileList.get(index));
            counter++;
        }
        else {
            selectedFiles.remove(allFileList.get(index));
            counter--;
        }
        updateActionBarText(counter,recycleActionMode);
    }

    private void updateActionBarText(int counter, ActionMode recycleActionMode){
        if(counter==1){
            recycleActionMode.setTitle("1 item selected");
        }
        else{
            recycleActionMode.setTitle(counter+" items selected");
        }
    }

    private void showAllFiles(DatabaseHelper dbHelper) {
        allFileList = dbHelper.getAllFiles();
        fileListAdapter = new FileListAdapter(this, allFileList);
        recyclerView.setAdapter(fileListAdapter);

    }
    private void showDocFiles(DatabaseHelper dbHelper) {
        docFileList = dbHelper.getDocFiles();
        fileListAdapter = new FileListAdapter(this, docFileList);
        recyclerView.setAdapter(fileListAdapter);
    }
    private void showImageFiles(DatabaseHelper dbHelper) {
        imageFileList = dbHelper.getImageFiles();
        fileListAdapter = new FileListAdapter(this, imageFileList);
        recyclerView.setAdapter(fileListAdapter);
    }
    private void showVideoFiles(DatabaseHelper dbHelper) {
//        fileNameArrayAdapter = new ArrayAdapter<FileModel>(this, android.R.layout.simple_list_item_1, dbHelper.getVideoFiles());
//        rec_file_list.setAdapter(fileNameArrayAdapter);
        videoFileList = dbHelper.getVideoFiles();
        fileListAdapter = new FileListAdapter(this, videoFileList);
        recyclerView.setAdapter(fileListAdapter);
    }

    private ActionMode.Callback recycleActionCallBack = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.recycle_context_menu,menu);
            mode.setTitle("Choose your option");
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

            if(item.getItemId()== R.id.delete_4ever && selectedFiles.size()>0){
                AlertDialog.Builder builder = new AlertDialog.Builder(RecycleBin.this);
                builder.setMessage("Delete "+selectedFiles.size()+" item(s)?");
                builder.setTitle("Confirm");
                builder.setPositiveButton("Delete",new  DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseHelper dbHelper = new DatabaseHelper(RecycleBin.this);
                        for(FileModel fileModel : selectedFiles){
                            dbHelper.deleteFiles(fileModel.getId());
                        }
                        selectedFiles.clear();
                        position = -1;
                        fileListAdapter.notifyDataSetChanged();
                        updateActionBarText(0,recycleActionMode);
                        showAllFiles(dbHelper);
                        dbHelper.close();
                    }

                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
            if(item.getItemId()== R.id.restore_files && selectedFiles.size()>0){
                AlertDialog.Builder builder = new AlertDialog.Builder(RecycleBin.this);
                builder.setMessage("Restore "+selectedFiles.size()+" item(s)?");
                builder.setTitle("Confirm");
                builder.setPositiveButton("Restore",new  DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseHelper dbHelper = new DatabaseHelper(RecycleBin.this);
                        for(FileModel fileModel : selectedFiles){
                            dbHelper.restoreFiles(fileModel.getId());
                        }
                        selectedFiles.clear();
                        position = -1;
                        fileListAdapter.notifyDataSetChanged();
                        updateActionBarText(0,recycleActionMode);
                        showAllFiles(dbHelper);
                    }

                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }

            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            isActionMode = false;
            selectedFiles.clear();
            counter = 0;
            position = -1;
            fileListAdapter.notifyDataSetChanged();
            recycleActionMode =null;
        }
    };

}
