package com.example.alias;

import java.util.ArrayList;

public class Team {
    String mName;
    int mPoints;

    public Team(String name,int points){
        mName=name;
        mPoints=points;
    }

    public String getName(){return mName;}
    public int getPoints(){return mPoints;}

    public String pointsToString() {
        return ":    "+mPoints;
    }
    public String nameToString() {
        return ""+mName;
    }

    public static ArrayList<Team> createTeamList(int numTeams){
        ArrayList<Team> teams = new ArrayList<Team>();
        int lastTeamId=0;
        for(int i=0; i<=numTeams;i++)
            teams.add(new Team("Team "+ ++lastTeamId, 0));
            return teams;
    }
}
