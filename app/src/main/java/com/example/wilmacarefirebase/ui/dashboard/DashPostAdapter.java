package com.example.wilmacarefirebase.ui.dashboard;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wilmacarefirebase.R;
import com.example.wilmacarefirebase.models.DashboardPost;

import java.util.ArrayList;
import java.util.List;

public class DashPostAdapter extends RecyclerView.Adapter<DashPostAdapter.ViewHolder> {


    private List<DashboardPost> postItemList;

    public DashPostAdapter(List<DashboardPost> postItemList) {
        this.postItemList = postItemList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.user_display_post, parent, false);
        return new ViewHolder(view);
    }

    //TODO: fiks så du kan få billeder ind gennem cloud
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setTag(postItemList.get(position));
//      holder.user_image.setText(postItemList.get(position).getImage());
        holder.username.setText(postItemList.get(position).getUsername());
        holder.post.setText(postItemList.get(position).getMessage());

    }

    public void setPost(List<DashboardPost> notes) {
        this.postItemList = (ArrayList<DashboardPost>) notes;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return postItemList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView user_image, username, post;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

//            user_image = itemView.findViewById(R.id.users_profile_image);
            username = itemView.findViewById(R.id.user_profile_name);
            post = itemView.findViewById(R.id.user_message);
        }
    }

    public interface OnListItemClickListener
    {
        void onClick(int position);
    }
}
