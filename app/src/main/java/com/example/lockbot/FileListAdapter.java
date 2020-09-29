package com.example.lockbot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FileListAdapter extends RecyclerView.Adapter<FileListAdapter.FileViewHolder> {
    private Context context;
    private ArrayList<FileModel> filesList;
    private RecycleBin recycleBin;

    public FileListAdapter(Context context, ArrayList<FileModel> filesList) {
        this.context = context;
        this.recycleBin = (RecycleBin)context;
        this.filesList = filesList;
    }

    @NonNull
    @Override
    public FileViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_list_item,viewGroup,false);
        return new FileViewHolder(view, this.recycleBin);
    }

    @Override
    public void onBindViewHolder(@NonNull FileViewHolder fileViewHolder, final int position) {

        fileViewHolder.fileName.setText(filesList.get(position).getFileName());
        fileViewHolder.fileDate.setText(filesList.get(position).getDate());

        if(recycleBin.position == position){
            fileViewHolder.checkBox.setChecked(true);
            recycleBin.position = -1;
        }

        if(recycleBin.isActionMode){
            CheckBoxAnimation anim = new CheckBoxAnimation(150,fileViewHolder.linearLayout);
            anim.setDuration(300);
            fileViewHolder.linearLayout.setAnimation(anim);
        }
        else {
            CheckBoxAnimation anim = new CheckBoxAnimation(0,fileViewHolder.linearLayout);
            anim.setDuration(300);
            fileViewHolder.linearLayout.setAnimation(anim);
            fileViewHolder.checkBox.setChecked(false);
        }

        fileViewHolder.fileItemWrapper.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String x = String.valueOf(recycleBin.isActionMode);
                recycleBin.startSelection(position);
                return true;
            }
        });

        fileViewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recycleBin.checkFiles(v, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return filesList.size();
    }

    class FileViewHolder extends RecyclerView.ViewHolder{

        private TextView fileName;
        private TextView fileDate;
        private RecycleBin recycleBin;
        private LinearLayout linearLayout;
        private RelativeLayout fileItemWrapper;
        private CheckBox checkBox;


        public FileViewHolder(@NonNull View itemView, RecycleBin recycleBin) {
            super(itemView);

            fileName = itemView.findViewById(R.id.fileName);
            fileDate = itemView.findViewById(R.id.fileDate);
            checkBox = itemView.findViewById(R.id.fileCheckBox);
            this.recycleBin = recycleBin;
            linearLayout = itemView.findViewById(R.id.fileCheckBoxLayout);
            fileItemWrapper = itemView.findViewById(R.id.fileItemWrapper);
        }

        private void setFileData(String fileName, String fileDate){
            this.fileName.setText(fileName);
            this.fileDate.setText(fileDate);
        }
    }

    class CheckBoxAnimation extends Animation {
        private int width;
        private int startWidth;
        private View view;

        public CheckBoxAnimation(int width, View view){
            this.width = width;
            this.view = view;
            this.startWidth = view.getWidth();
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {

            int newWidth = startWidth + (int) ((width-startWidth) * interpolatedTime);
            view.getLayoutParams().width = newWidth;
            view.requestLayout();

            super.applyTransformation(interpolatedTime, t);
        }

        @Override
        public boolean willChangeBounds() {
            return true;
        }
    }
}
