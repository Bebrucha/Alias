package com.example.alias;

import java.util.ArrayList;

public class Game {
    /// CONTAINER VARIABLES ------------------------------------------------------------------------
    private boolean is_english;
    private int difficulty; //1- junior; 2- medium; 3-godlike
    private int num_of_teams;
    private int current_round_num;
    private int time_of_one_round;
    private int max_points_to_win_game;
    private boolean is_skip_penalty;
    private boolean is_sound;
    private boolean is_music;
    private int id_currently_playing_team;
    private ArrayList<Team> all_teams;

    /// CONSTRUCTORS -------------------------------------------------------------------------------
    Game() {
        this(0);
    }

    Game(int numTeams){
        this.setTeams(numTeams);
        this.num_of_teams = numTeams;
        this.difficulty = 1;
        this.is_english = true;
        this.current_round_num = 0;
        this.time_of_one_round = 10;
        this.max_points_to_win_game = 10;
        this.is_skip_penalty = false;
        this.id_currently_playing_team = 0;
        this.is_sound = false;
        this.is_music = false;
    }

    Game(int numTeams, boolean isEnglish, int level, int timeOfOneRound,
         int maxPointsToWinGame, boolean isSkipPenalty, boolean isSound,
         boolean isMusic) {
        this(numTeams);

        this.is_english = isEnglish;
        this.difficulty = level;
        this.time_of_one_round = timeOfOneRound;
        this.max_points_to_win_game = maxPointsToWinGame;
        this.is_skip_penalty = isSkipPenalty;
        this.is_sound = isSound;
        this.is_music = isMusic;
    }

    /// SET methods --------------------------------------------------------------------------------
    public void setTeams(int numOfTeams) {
        this.num_of_teams = numOfTeams;

        all_teams = new ArrayList<Team>();
        int lastTeamId=0;
        for(int i = 0; i < this.num_of_teams; i++)
        {
            all_teams.add(new Team(lastTeamId));
            lastTeamId++;
        }
    }

    public void setNumOfTeams(int num) { this.num_of_teams = num ;}
    public void setDifficulty(int level) { this.difficulty = level; }
    public void setLanguage(boolean isEnglish) { this.is_english = isEnglish; }
    public void setIsSound(boolean isSound) { this.is_sound = isSound; }
    public void setIsMusic(boolean isMusic) { this.is_music = isMusic; }
    public void setTimeOfOneRound (int timeOfOneRound) { this.time_of_one_round = timeOfOneRound; }
    public void setMaxPointsToWinGame(int maxPointsToWinGame) { this.max_points_to_win_game =
            maxPointsToWinGame; }
    public void setIsSkipPenalty(boolean isSkipPenalty) { this.is_skip_penalty = isSkipPenalty; }

    /// GET methods --------------------------------------------------------------------------------
    public Team getTeam(int id) { return this.all_teams.get(id); }
    /***
     * Be veeeeeeery careful with usage of this method
     * @return
     */
    public ArrayList<Team> getAll_teams() { return this.all_teams; }
    public int getNumOfTeams() { return this.num_of_teams; }
    public int getCurrentRoundNum() { return this.current_round_num; }
    public boolean getIsEnglish() { return this.is_english; }
    public int getIdCurrentlyPlayingTeam() { return this.id_currently_playing_team; }
    public Team getCurrentlyPlayingTeam() { return this.all_teams.get(this.id_currently_playing_team); }
    public boolean getIsSound() { return this.is_sound; }
    public boolean getIsMusic() { return this.is_music; }
    /**
     * Difficulty is described accordingly: 1-junior; 2-middle; 3-senior
     * @return int: level of difficulty (1,2 or 3)
     */
    public int getDifficulty() { return this.difficulty; }
    public int getTimeOfOneRound() { return this.time_of_one_round; }
    public int getMaxPointsToWinGame() { return this.max_points_to_win_game; }
    public boolean getIsSkipPenalty() { return this.is_skip_penalty; }

    /// ADDITIONAL methods -------------------------------------------------------------------------
    public void updateCurrentRoundNum()
    {
        this.current_round_num++;

        for (int i = 0; i < this.num_of_teams; i++)
            all_teams.get(i).newRoundIsStarted();

        if (this.current_round_num % this.num_of_teams == 0)
            this.id_currently_playing_team = 0;
        else
            this.id_currently_playing_team++;
    }

    /***
     * Verifies that at least one team have reached required amount of points
     * to win
     * @return boolean: at least one team have reached required amount of points
     */
    public boolean oneTeamWon() {
        for (int i = 0; i < this.num_of_teams; i++)
        {
            if(all_teams.get(i).getTotalPoints() >= this.max_points_to_win_game)
                return true;
        }
        return false;
    }

    public Team getWinnerTeam(){
        int max = all_teams.get(0).getTotalPoints();
        int maxId = all_teams.get(0).getId();

        if (oneTeamWon()){

            for(int i = 1; i < this.num_of_teams; i++){
                if (this.all_teams.get(i).getTotalPoints() > max)
                {
                    max = this.all_teams.get(i).getTotalPoints();
                    maxId = this.all_teams.get(i).getId();
                }

            }
        }
        return this.all_teams.get(maxId);
    }

    /// WORDS' LISTS -------------------------------------------------------------------------------
    public String[] getWordsJunior_lt() { return this.words_junior_lt; }
    public String[] getWordsJunior_en() { return this.words_junior_en; }
    public String[] getWordsMedium_lt() { return this.words_medium_lt; }
    public String[] getWordsMedium_en() { return this.words_medium_en; }
    public String[] getWordsSenior_lt() { return this.words_senior_lt; }
    public String[] getWordsSenior_en() { return this.words_senior_en; }
    private String[] words_junior_lt = {"Kaukė",        "Citrina",      "Užtvanka",     "Medus",
                                "Kiaulė",       "Rašysena",     "Kaklas",       "Arbatinukas",
                                "Arbūzas",      "Žingsnis",     "Instrukcija",  "Mėdaus mėnuo",
                                "Klasė",        "Inžinierius",  "Sodas",        "Dangtelis",
                                "Atspindys",    "Renginys",     "Pilis",        "Prietaisas",
                                "Platukas",     "Šokoladas",    "Kvėpavimas",   "Tinklinis",
                                "Galaktika",    "Svogūnas",     "Kaulas",       "Užsakymas",
                                "Kritika",      "Pabėgti",      "Sąrašas",      "Variklis",
                                "Liežuvis",     "Vėžlys",       "Auklė",        "Triušis",
                                "Drakonas",     "Kalnas",       "Valgiaraštis", "Dėžė",
                                "Kėdė",         "Aušra",        "Pirštinė",     "Pilnametis",
                                "Ateitis",      "Gerbėjas",     "Pelėda",       "Grybas",
                                "Stresas",      "Klubas",       "Užkandis",     "Pagalvė",
                                "Eilė",         "Planas",       "Cirkas",       "Kodas",
                                "Linija",       "Svetainė",     "Pietūs",       "Gyvatė"};

    private String[] words_junior_en = {"Mask",         "Lemon",        "Embankment",   "Honey",
                                "Cow",          "Handwriting",  "Neck",         "Teapot",
                                "Watermelon",   "Footstep",     "Instruction",  "Honeymoon",
                                "Classroom",    "Engineer",     "Garden",       "Lid",
                                "Reflection",   "Event",        "Bone",         "Device",
                                "Hammer",       "Chocolate",    "List",         "Voleyball",
                                "Galaxy",       "Onion",        "Babysitter",   "Order",
                                "Judge",        "Escape",       "Menu",         "Engine",
                                "Tangue",       "Turtle",       "Glove",        "Rabbit",
                                "Dragon",       "Mountain",     "Owl",          "Box",
                                "Chair",        "Dawn",         "Snack",        "Adult",
                                "Future",       "Fan",          "Circus",       "Mushroom",
                                "Stress",       "Club",         "Lunch",        "Pillow",
                                "Queue",        "Plan",         "Castle",       "Code",
                                 "Line",         "Web",         "Breathing",    "Snake"};

    private String[] words_medium_lt = {"Liokajus",         "Šturmanas",        "Išdavystė",
                                "Našta",            "Migracija",        "Kalakutas",
                                "Pakartojimas",     "Guma",             "Mantija",
                                "Čiužinys",         "Sutrikimas",       "Santykis",
                                "Dokumencija",      "Mineralas",        "Įlanka",
                                "Korespondentas",   "Tardymas",         "Nekantrumas",
                                "Katedra",          "Pratarmė",         "Prevencija",
                                "Budrumas",         "Gerovė",           "Intelektas",
                                "Rūkas",            "Vėjas",            "Mėlynė",
                                "Gidas",            "Švyturys",         "Pasiūlymas",
                                "Fleita",           "Alergija",         "Riba",
                                "Teisingumas",      "Sąsaga",           "Kalorija",
                                "Nuostaba",         "Įprotis",          "Šimpanzė",
                                "Klestėjimas",      "Lankomumas",       "Sudėtingumas",
                                "Diagrama",         "Nelaimė",          "Vaiduoklis",
                                "Angelas",          "Atsarginė kopija", "Grąžtas"};

    private String[] words_medium_en = {"Butler",           "Navigator",        "Betrayal",
                                "Burden",           "Migration",        "Turkey",
                                "Repetition",       "Rubber",           "Mantle",
                                "Mattress",         "Sutrikimas",       "Relation",
                                "Documentary",      "Mineral",          "Gulf",
                                "Correspondent",    "Interrogation",    "Impatience",
                                "Cathedral",        "Preface",          "Prevention",
                                "Vigilance",        "Well",             "Intelligence",
                                "Mist",             "Breeze",           "Blueberry",
                                "Guide",            "Lighthouse",       "Proposition",
                                "Flute",            "Allergy",          "Boundary",
                                "Justice",          "Cufflink",         "Calorie",
                                "Amazement",        "Habit",            "Chimpanzee",
                                "Prosperity",       "Attendance",       "Complexity",
                                "Chart",            "Disaster",         "Ghost",
                                "Angel",            "Backup",           "Drill"};

    private String[] words_senior_lt = {"Vikrumas",          "Saugotojas",      "Filharmonija",
                                "Atrologija",        "Valda",           "Terariumas",
                                "Gynėjas",           "Antena",          "Pramoga",
                                "Poliglotas",        "Vaisingumas",     "Samurajus",
                                "Finansai",          "Elektronas",      "Jakas",
                                "Respublika",        "Karūnavimas",     "Klubas",
                                "Šurmulys",          "Įvertinimas",     "Vanagas",
                                "Gausa",             "Vartininkas",     "Grizlis",
                                "Neigimas",          "Kvota",           "Klarnetas",
                                "Ginčas",            "Tvarumas",        "Sagtis",
                                "Ekvilibristas",     "Gyvybingumas",    "Puma",
                                "Lokatorius",        "Sąjunga",         "Eržilas",
                                "Prižiūrėtojas",     "Frakas",          "Ekscelencija"};

    private String[] words_senior_en = {"Dexterity",        "Protector",        "Philharmonic",
                                            "Astrology",        "Possession",       "Terrarium",
                                            "Defender",         "Antenna",          "Pastime",
                                            "Polyglot",         "Fruitfulness",     "Samurai",
                                            "Finance",          "Electron",         "Yak",
                                            "Republic",         "Coronation",       "Hip",
                                            "Commotion",        "Appreciation",     "Hawk",
                                            "Abundance",        "Gatekeeper",       "Grizzly",
                                            "Denial",           "Quota",            "Clarinet",
                                            "Altercation",      "Sustainability",   "Clasp",
                                            "Equilibrist",      "Vitality",         "Cougar",
                                            "Locator",          "Conjunction",      "Stallion",
                                            "Caretaker",        "Tailcoat",         "Excellency"};


}
