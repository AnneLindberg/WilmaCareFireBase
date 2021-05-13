package com.example.wilmacarefirebase.ui.calender;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wilmacarefirebase.R;
import com.example.wilmacarefirebase.models.CalenderPost;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CalenderAdapter extends RecyclerView.Adapter<CalenderAdapter.ViewHolder> {

    private List<CalenderPost> calenderPosts;

    public CalenderAdapter(List<CalenderPost> calenderPostList) {
        this.calenderPosts=calenderPostList;
    }


    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calender_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CalenderAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(calenderPosts.get(position));
//      holder.user_image.setText(postItemList.get(position).getImage());
        holder.title.setText(calenderPosts.get(position).getTitle());
        holder.post.setText(calenderPosts.get(position).getMessage());
    }

    @Override
    public int getItemCount() {
        return calenderPosts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView user_image, title, post;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

//            user_image = itemView.findViewById(R.id.users_profile_image);
            title = itemView.findViewById(R.id.calender_title);
            post = itemView.findViewById(R.id.calender_description);
        }
    }

    public interface OnListItemClickListener {
        void onClick(int position);
    }
}
