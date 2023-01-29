package com.example.planit;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planit.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    public ArrayList<User> mItemList;
    Context context;
    StorageReference storageReference;

//    public RecyclerViewAdapter(ArrayList<User> itemList) {
    public RecyclerViewAdapter(Context context,ArrayList<User> itemList ) {

        mItemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
            return new ItemViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        if (viewHolder instanceof ItemViewHolder) {

            populateItemRows((ItemViewHolder) viewHolder, position);
        } else if (viewHolder instanceof LoadingViewHolder) {
            showLoadingView((LoadingViewHolder) viewHolder, position);
        }

    }

    @Override
    public int getItemCount() {
        return mItemList == null ? 0 : mItemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mItemList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView name,streak;
        ImageView pfp;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            streak=itemView.findViewById(R.id.streak);
            pfp=itemView.findViewById(R.id.pfp);
        }
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {

        ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }

    private void showLoadingView(LoadingViewHolder viewHolder, int position) {
        // ProgressBar would be displayed

    }

    private void populateItemRows(ItemViewHolder viewHolder, int position) {
        User userdb = mItemList.get(position);
//        String item = mItemList.get(position);
        String item=userdb.name;
        Integer streak=userdb.streak;
//        Toast.makeText(context, "Image loaded "+ userdb.userid, Toast.LENGTH_SHORT).show();
//        Log.d(TAG, "Error getting documents: "+userdb.userid, null);

        storageReference = FirebaseStorage.getInstance().getReference();

        StorageReference profileRef2 = storageReference.child("users/"+userdb.userid+"/profile.jpg");
        profileRef2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(viewHolder.pfp);
                Toast.makeText(context, "Image loaded "+ uri, Toast.LENGTH_SHORT).show();

            }

        }
        ).addOnFailureListener(e -> Toast.makeText(context, "Image not loaded "+ userdb.email , Toast.LENGTH_SHORT).show())
        ;
//        String pfp=userdb.pfp;
        viewHolder.name.setText(item);
        viewHolder.streak.setText(userdb.streak.toString());


    }

}
