package com.example.wilmacarefirebase.data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.wilmacarefirebase.R;
import com.example.wilmacarefirebase.models.DashboardPost;
import java.util.List;

public class DashPostAdapter extends RecyclerView.Adapter<DashPostAdapter.ViewHolder> {

    private List<DashboardPost> postItemList;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.user_display_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setTag(postItemList.get(position));
        holder.title.setText(postItemList.get(position).getTitle());
        holder.username.setText(postItemList.get(position).getUsername());
        holder.description.setText(postItemList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        if (postItemList == null) {
            return 0;
        } else {
            return postItemList.size();
        }
    }

    public void setDashPost(List<DashboardPost> dashboardPostList) {
        this.postItemList = dashboardPostList;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView username, title, description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.user_profile_name);
            description = itemView.findViewById(R.id.user_message);
            title = itemView.findViewById(R.id.user_title);

        }
    }

}
