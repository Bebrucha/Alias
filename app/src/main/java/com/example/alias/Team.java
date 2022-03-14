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

    static int lastTeamId=0;

    public static ArrayList<Team> createTeamList(int numTeams){
        ArrayList<Team> teams = new ArrayList<Team>();

        for(int i=0; i<=numTeams;i++)
            teams.add(new Team("Team name"+ ++lastTeamId, 0));
            return teams;
    }
}
