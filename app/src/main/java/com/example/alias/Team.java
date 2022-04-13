package com.example.alias;

import java.util.ArrayList;

public class Team {
    /// CLASS VARIABLES ----------------------------------------------------------------------------
    private String mName;
    private int mPoints;
    private int mPointsReceivedInThisRound;
    private int mId;

    /// CONSTRUCTORS -------------------------------------------------------------------------------
    public Team(int id){
        this.mName = "Team " + id;
        this.mPoints = 0;
        this.mPointsReceivedInThisRound = 0;
        this.mId = id;
    }

    public Team(String name, int id, int points, int roundPoints){
        this.mName = name;
        this.mPoints = points;
        this.mPointsReceivedInThisRound = roundPoints;
        this.mId = id;
    }

    /// GET methods --------------------------------------------------------------------------------
    public String getName(){return mName;}
    public int getTotalPoints(){return mPoints;}
    public int getRoundPoints(){return mPointsReceivedInThisRound;}
    public int getId() { return this.mId; }


    /// SET methods --------------------------------------------------------------------------------
    public void setmName(String name) { this.mName = name; }
    public void setmPoints(int points) { this.mPoints = points; }
    //public void setmPointsReceivedInThisRound(int points) { this.mPointsReceivedInThisRound = points; }


    /// ADDITIONAL methods -------------------------------------------------------------------------
    public String pointsToString() {
        return ":    " + this.mPoints;
    }
    public String roundPointsToString() {
        return ":    " + this.mPointsReceivedInThisRound;
    }
    public String nameToString() {
        return "" + this.mName;
    }

    /**
     * Updates points when increase or decrease is required
     * @param difference int: can be either negative or positive number
     */
    public void updatePoints(int difference) {
        mPointsReceivedInThisRound += difference;
        this.mPoints += difference;
    }

    public void newRoundIsStarted() { mPointsReceivedInThisRound = 0; }

}
