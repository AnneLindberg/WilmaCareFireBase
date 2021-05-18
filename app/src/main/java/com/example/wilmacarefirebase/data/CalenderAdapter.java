package com.example.wilmacarefirebase.data;

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

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calender_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CalenderAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(calenderPosts.get(position));
        holder.title.setText(calenderPosts.get(position).getTitle());
        holder.description.setText(calenderPosts.get(position).getDescription());
        holder.date.setText(calenderPosts.get(position).getDate().toString());
    }

    @Override
    public int getItemCount() {
        if (calenderPosts == null) {
            return 0;
        } else {
            return calenderPosts.size();
        }
    }

    public void setCalenderPosts(List<CalenderPost> calenderPostList){
        this.calenderPosts = calenderPostList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView date, title, description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.calenderDate);
            title = itemView.findViewById(R.id.calender_title);
            description = itemView.findViewById(R.id.calender_description);
        }
    }

}
