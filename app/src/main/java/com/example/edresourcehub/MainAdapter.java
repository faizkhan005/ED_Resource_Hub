package com.example.edresourcehub;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;



public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    public MainAdapter(List<String> fileList, MainActivity mContext) {
        this.fileList = fileList;
        this.mContext = mContext;
    }

    List<String> fileList;
    MainActivity mContext;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_folder,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int num = position;
        holder.fileName.setText(fileList.get(position));
        holder.fileImage.setImageResource(R.drawable.folder);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,StudentFileDisplay.class);
                intent.putExtra("fileName",fileList.get(num));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return fileList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView fileName;
        private ImageView fileImage;
        private CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            fileName = itemView.findViewById(R.id.textViewFileName);
            fileImage = itemView.findViewById(R.id.imageViewFolder);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
