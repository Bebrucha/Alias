package com.example.alias;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TeamAdapter extends
        RecyclerView.Adapter<TeamAdapter.ViewHolder>{

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView nameTextView;
        public TextView pointsTextView;

        public ViewHolder(View itemView){
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.team_name);
            pointsTextView=(TextView) itemView.findViewById(R.id.team_points);
        }
    }
    private List<Team> mTeams;

    public TeamAdapter(List<Team>teams){
        mTeams=teams;
    }

    @Override
    public TeamAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View teamView = inflater.inflate(R.layout.item_team, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(teamView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TeamAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        Team team = mTeams.get(position);

        // Set item views based on your views and data model
        TextView textView = holder.nameTextView;
        textView.setText(team.getName());

        TextView textView2 = holder.pointsTextView;
        textView2.setText(team.getPoints());

    }

    @Override
    public int getItemCount() {
        return mTeams.size();
    }
}
