package com.example.alias;
import java.util.Random;
import java.util.ArrayList;

public class Team {
    /// CLASS VARIABLES ----------------------------------------------------------------------------
    private String mName;
    private int mPoints;
    private int mPointsReceivedInThisRound;
    private int mId;

    /// TEAM NAME ARRAYS ---------------------------------------------------------------------------

    String[] adj_en=Main.game.getAdjectives_en();
    String[] nou_en=Main.game.getNouns_en();

    String[] adj_lt=Main.game.getAdjectives_lt();
    String[] nou_lt=Main.game.getNouns_lt();


    /// CONSTRUCTORS -------------------------------------------------------------------------------
    public Team(int id){
        //this.mName = "Team " + id;
        this.mName=generateTeamName();
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


    public String generateTeamName()
    {
        String fullName="a";

            if(Main.game.getIsEnglish())
            {
                if(Main.game.adjectives_enn.isEmpty()) {
                    addWordsToArrayList(adj_en, Main.game.adjectives_enn);
                    addWordsToArrayList(nou_en, Main.game.nouns_enn);

                }

                Random random = new Random();
                int randomA = random.nextInt(Main.game.adjectives_enn.size());
                int randomN = random.nextInt(Main.game.nouns_enn.size());

                fullName = Main.game.adjectives_enn.get(randomA)+" "+Main.game.nouns_enn.get(randomN);
                Main.game.adjectives_enn.remove(randomA);
                Main.game.nouns_enn.remove(randomN);

            }
            else
            {
                if(Main.game.adjectives_ltt.isEmpty()) {
                    addWordsToArrayList(adj_lt, Main.game.adjectives_ltt);
                    addWordsToArrayList(nou_lt, Main.game.nouns_ltt);
                }

                Random random = new Random();
                int randomA = random.nextInt(Main.game.adjectives_ltt.size());
                int randomN = random.nextInt(Main.game.nouns_ltt.size());

                fullName = Main.game.adjectives_ltt.get(randomA)+" "+Main.game.nouns_ltt.get(randomN);
                Main.game.adjectives_ltt.remove(randomA);
                Main.game.nouns_ltt.remove(randomN);

            }

        return fullName;
    }

    static void addWordsToArrayList(String[]words,ArrayList<String>nameWords)
    {
        for(int i = 0; i < words.length; i++)
        {
            nameWords.add(words[i]);
        }
    }

}
