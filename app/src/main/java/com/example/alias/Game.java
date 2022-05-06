package com.example.alias;

import android.media.MediaPlayer;

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
    private boolean game_created_for_the_first_time;
    private int id_currently_playing_team;
    private ArrayList<Team> all_teams;
    private ArrayList<Team> all_teams_current_round;
    private MediaPlayer music_effect;
    private Boolean visited_fragment_6=false;

    /// CONSTRUCTORS -------------------------------------------------------------------------------
    Game() {
        this(0);
    }

    Game(int numTeams){
        this.setTeams(numTeams);
        this.num_of_teams = numTeams;
        this.difficulty = 1;
        this.is_english = false;
        this.current_round_num = 1;
        this.time_of_one_round = 10;
        this.max_points_to_win_game = 10;
        this.is_skip_penalty = false;
        this.id_currently_playing_team = 0;
        this.is_sound = false;
        this.is_music = false;
        this.game_created_for_the_first_time = true;
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

    public void setMusic(MediaPlayer music_effect) { this.music_effect = music_effect; }
    public void setNumOfTeams(int num) { this.num_of_teams = num ;}
    public void setDifficulty(int level) { this.difficulty = level; }
    public void setLanguage(boolean isEnglish) { this.is_english = isEnglish; }
    public void setIsSound(boolean isSound) { this.is_sound = isSound; }
    public void setIsMusic(boolean isMusic) { this.is_music = isMusic; }
    public void setTimeOfOneRound (int timeOfOneRound) { this.time_of_one_round = timeOfOneRound; }
    public void setMaxPointsToWinGame(int maxPointsToWinGame) { this.max_points_to_win_game =
            maxPointsToWinGame; }
    public void setIsSkipPenalty(boolean isSkipPenalty) { this.is_skip_penalty = isSkipPenalty; }
    public void FRAGMENT6_WAS_VISITED(boolean VISIT) { this.visited_fragment_6 = VISIT; }
    public void setGameCreatedForTheFirstTime(boolean gameCreatedForTheFirstTime) {
        this.game_created_for_the_first_time = gameCreatedForTheFirstTime;
    }

    /// GET methods --------------------------------------------------------------------------------
    public Team getTeam(int id) { return this.all_teams.get(id); }
    /***
     * Be veeeeeeery careful with usage of this method
     * @return
     */
    public ArrayList<Team> getAll_teams() { return this.all_teams; }
    //public ArrayList<Team> getAll_teams_current_round() { return this.all_teams_current_round; }
    public int getNumOfTeams() { return this.num_of_teams; }
    public int getCurrentRoundNum() { return this.current_round_num; }
    public boolean getIsEnglish() { return this.is_english; }
    public boolean Visit_of_fragment_6() { return this.visited_fragment_6; }
    public int getIdCurrentlyPlayingTeam() { return this.id_currently_playing_team; }
    public Team getCurrentlyPlayingTeam() { return this.all_teams.get(this.id_currently_playing_team); }
    public boolean getIsSound() { return this.is_sound; }
    public boolean getIsMusic() { return this.is_music; }
    public MediaPlayer getMusic() { return this.music_effect; }
    /**
     * Difficulty is described accordingly: 1-junior; 2-middle; 3-senior
     * @return int: level of difficulty (1,2 or 3)
     */
    public int getDifficulty() { return this.difficulty; }
    public int getTimeOfOneRound() { return this.time_of_one_round; }
    public int getMaxPointsToWinGame() { return this.max_points_to_win_game; }
    public boolean getIsSkipPenalty() { return this.is_skip_penalty; }
    public boolean getGameCreatedForTheFirstTime() { return this.game_created_for_the_first_time; }

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

    /**
     * Verifies that every team had a chance to play in the current round
     * @return boolean: every team had a chance to play in the current round
     */
    public boolean everyTeamPlayedInThisRound() {
        if (this.current_round_num % this.num_of_teams == 0)
            return true;
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

    public void sortTeamsByPoints(){

        for(int i = 0; i < this.num_of_teams - 1; i++){

            for (int j = 0; j < this.num_of_teams - i - 1; j++){

                if(this.all_teams.get(j).getTotalPoints() < this.all_teams.get(j+1).getTotalPoints()){
                    Team temp = this.all_teams.get(j);
                    this.all_teams.set(j, this.all_teams.get(j+1));
                    this.all_teams.set(j+1, temp);
                }
            }

        }
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
                                "Linija",       "Svetainė",     "Pietūs",       "Gyvatė",

                                "Meilė",         "Plaukai",      "Saldainis",     "Tušinukas",
                                "Robotas",        "Mašina",       "Tiltas",       "Kukurūzas",
                                "Stalas",         "Futbolas",      "Garbana",      "Kalnai",
                                "Snaigė",         "Vyšnia",      "Zigzagas",       "Vėliava",
                                "Kumštinė pirštinė",  "Morka",      "Šviesa",       "Skruzdelės",
                                "Žandas",           "Atletas",    "Filmas",       "Karšta",
                                "Padavėja",         "Dėdė",      "Brokolis",    "Pelė",
                                "Kavinė",        "Kramtyti",       "Druska",       "Skrudinta duona",
                                "Akiniai",           "Knyga",       "Sraigė",       "Dubuo",
                                "Supynės",            "Varlė",      "Gyvas",        "Monstras",
                                "Aitvaras",             "Sapnas",     "Vandenynas",        "Kalėjimas",
                                "Kablys",              "Laikrodis",    "Apskritimas",       "Jūrų vėžlys",
                                "Avis",            "Žuvis",     "Krabas",         "Kaminas",
                                "Sraigtasparnis",       "Mėnulis",     "Kvadratas",       "Muzika",
                                "Žvaigždė",             "Kiaulė",      "Ausis",          "Debesis"};

    private String[] words_junior_en = {"Mask",         "Lemon",        "Embankment",   "Honey",
                                "Cow",          "Handwriting",  "Neck",         "Teapot",
                                "Watermelon",   "Footstep",     "Instruction",  "Honeymoon",
                                "Classroom",    "Engineer",     "Garden",       "Lid",
                                "Reflection",   "Event",        "Bone",         "Device",
                                "Hammer",       "Chocolate",    "List",         "Volleyball",
                                "Galaxy",       "Onion",        "Babysitter",   "Order",
                                "Judge",        "Escape",       "Menu",         "Engine",
                                "Tongue",       "Turtle",       "Glove",        "Rabbit",
                                "Dragon",       "Mountain",     "Owl",          "Box",
                                "Chair",        "Dawn",         "Snack",        "Adult",
                                "Future",       "Fan",          "Circus",       "Mushroom",
                                "Stress",       "Club",         "Lunch",        "Pillow",
                                "Queue",        "Plan",         "Castle",       "Code",
                                 "Line",         "Web",         "Breathing",    "Snake",

                               "Love",         "Hair",         "Candy",        "Pen",
                                "Robot",        "Car",          "Bridge",       "Corn",
                                "Desk",         "Football",      "Curl",         "Mountains",
                               "Snowflake",     "Cherry",      "Zigzag",       "Flag",
                               "Mitten",         "Carrot",      "Light",       "Ants",
                               "Cheek",           "Athlete",    "Movie",       "Hot",
                                "Waitress",         "Uncle",      "Broccoli",    "Mouse",
                                "Cafeteria",        "Chew",       "Salt",       "Toast",
                                 "Glasses",           "Book",       "Snail",       "Bowl",
                                "Swing",            "Frog",      "Alive",        "Monster",
                                 "Kite",             "Dream",     "Ocean",        "Jail",
                                 "Hook",              "Clock",    "Circle",       "Sea turtle",
                                  "Sheep",            "Fish",     "Crab",         "Chimney",
                                  "Helicopter",       "Moon",     "Square",       "Music",
                                  "Star",             "Pig",      "Ear",          "Cloud"};



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
                                "Angelas",          "Atsarginė kopija", "Grąžtas",

                                "Naujagimis",          "Kamera",           "Rašalas",
                                "Ritė",             "Kino teatras",    "Apskritas langas",
                                "Ledinukas",         "Smegenys",            "Juosta",
                                "Skrandis",          "Banano žievė",     "Plukė",
                                "Maikė",          "Hot-dogas",          "Randas",
                                "Durys",             "Rytai",             "Menininkas",
                                "Žadintuvas",      "Guminis kamuoliukas",          "Organas",
                                "Savivartis ",       "Tarakonas",        "Tualetinis popierius",
                                "Ryklys",            "Skerdikas",          "Varpas",
                                "Avilys",          "Mokytoja",          "Ūkininkas",
                                "Viršelis",            "Buožgalvis",          "Kepėjas",
                                "Suknelė",            "Mokyklinis autobusas",       "Šukos",
                                "Letena",             "Užuolaidos",         "Nykštys",
                                "Pagalvės užvalkalas",       "Eskalatorius",        "Uždaryta",
                                "Lietus",             "Colis",             "Tramplynas",
                                "Sidabro dirbiniai",       "Ratas",              "Durų rankena"};

    private String[] words_medium_en = {"Butler",           "Navigator",        "Betrayal",
                                "Burden",           "Migration",        "Turkey",
                                "Repetition",       "Rubber",           "Mantle",
                                "Mattress",         "Disorder",       "Relation",
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
                                "Angel",            "Backup",           "Drill",

                              "Newborn",          "Camera",           "Ink",
                              "Coil",             "Movie theater",    "Porthole",
                              "Popsicle",         "Brain",            "Tape",
                               "Stomach",          "Banana split",     "Anemone",
                               "T-shirt",          "Hot dog",          "Scar",
                               "Door",             "East",             "Artist",
                               "Alarm clock",      "Gumball",          "Organ",
                              "Dump truck",       "Cockroach",        "Toilet paper",
                               "Shark",            "Butcher",          "Bell",
                              "Beehive",          "Teacher",          "Farmer",
                             "Cover",            "Tadpole",          "Baker",
                              "Dress",            "School bus",       "Hairbrush",
                              "Claw",             "Curtains",         "Thumb",
                              "Pillowcase",       "Escalator",        "Closed",
                              "Rain",             "Inch",             "Trampoline",
                              "Silverware",       "Lap",              "Doorknob"};

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
                                "Prižiūrėtojas",     "Frakas",          "Ekscelencija",

                                "Nardymas nuo uolų",      "Krūminiai dantys",           "Kasykla",
                                "Grobis",              "Vestuvinis tortas",     "Pabaiga",
                                "Medžiaga",            "Džiazas",             "Žemės drebėjimas",
                                "Savininkas",          "Maisto parduotuvė",    "Svetainė",
                                "Optometristas",       "Vištidė",      "Keltuvas",
                                "Džiunglės",            "Filosofas",             "Kolonėlės",
                                "Kapitonas",           "Įstrižainė",          "Kvidičas",
                                "Teisingas",           "Maistas išsivežimui",      "Mokamas kelias",
                                "Bobslėjus",           "Trynys",                "Geras pirkinys",
                                "Kalvis",        "Jausmas",             "Kartografija",
                                "Išsilavinimas",          "Atstovas",      "Sieksnis",
                                "Sąvartynas",            "Vertimas",        "Ironija",
                                "Laiko zona",            "Nepažįstamas",          "Pelkės"};

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
                                            "Caretaker",        "Tailcoat",         "Excellency",

                                           "Cliff diving",      "Molar teeth",           "Mine",
                                           "Prey",              "Wedding cake",     "End zone",
                                           "Fabric",            "Jazz",             "Earthquake",
                                          "Landlord",          "Grocery store",    "Living room",
                                         "Optometrist",       "Chicken coop",      "Ski lift",
                                          "Jungle",            "Philosopher",             "Speakers",
                                          "Captain",           "Diagonal",          "Quidditch",
                                          "Correct",           "Drive-through",      "Toll road",
                                           "Bobsled",           "Yolk",                "Bargain",
                                          "Blacksmith",        "Feeling",             "Cartography",
                                          "Education",          "Representative",      "Fathom",
                                          "Landfill",            "Translation",        "Irony",
                                          "Time zone",            "Stranger",          "Wetlands"};


    //---------------Team Names---------------------

    public String[] getAdjectives_en()  { return this.adjectives_en; }
    public String[] getNouns_en()       { return this.nouns_en; }
    public String[] getAdjectives_lt()  { return this.adjectives_lt; }
    public String[] getNouns_lt()       { return this.nouns_lt; }
    private String[] adjectives_en ={"Mad",      "Beautiful",     "Crazy",       "Lazy",       "Reckless",
                                     "Fast",     "Dead",          "Happy",       "Slow",       "Big",
                                     "Small",    "Dumb",          "Smelly",      "Lithuanian", "Scary",
                                     "Boring",   "Smart",         "Heavy",       "Chubby",     "Skinny",
                                     "Lame",     "Upset",         "Green",       "Bored",      "Red",
                                     "Yellow",   "Blue",          "Drunk",       "Pink",       "Tall",
                                     "Short",    "Imaginary",     "Real",        "Fluffy",     "Wicked",
                                     "Dirty",    "Clean",         "Edgy",        "Teenage",    "Bad",
                                     "Lonely"};

    private String[] nouns_en =     {"Dogs",         "Cats",      "Horses",      "Cows",       "Mice",
                                     "Pigs",         "Fishes",    "Bears",       "Pitbulls",   "Labradors",
                                     "Chickens",      "Trees",     "Goats",       "Cars",       "Flowers",
                                     "Trains",       "Planes",    "Candies",     "Boxes",      "Spoons",
                                     "Bananas",      "Tomatoes",  "Oranges",     "Shoes",      "Forks",
                                     "Hairs",        "Houses",    "Pebbles",     "Terriers",   "Pizzas",
                                     "Salmons",      "Eggs",      "Bushes",      "Guns",       "Skunks",
                                     "Toes",         "Fingers",   "Noses",       "Friends",    "Enemies",
                                     "Students"};

    private String[] adjectives_lt= {"Pikti",       "Gražūs",     "Pamišę",       "Tingūs",    "Greiti",
                                     "Mirę",        "Laimingi",   "Lėti",         "Dideli",    "Maži",
                                     "Kvaili",      "Dvokūs",     "Lietuviški",   "Baisūs",    "Nuobodūs",
                                     "Nusiminę",    "Protingi",   "Sunkūs",       "Žali",      "Raudoni",
                                     "Geltoni",     "Mėlyni",     "Girti",        "Rožiniai",  "Aukšti",
                                     "Žemi",        "Tikri",      "Putlūs",       "Nesveiki",  "Purvini",
                                     "Švarūs",      "Mandrūs",    "Jauni",        "Blogi",     "Du",
                                     "Vieniši"};

    private String[] nouns_lt=      {"Šunys",       "Katinai",    "Arkliai",      "Jaučiai",   "Šeškai",
                                     "Žiurkėnai",   "Šernai",     "Ešeriai",      "Medžiai",   "Grizliai",
                                     "Labradorai",  "Gaidžiai",   "Viščiukai",    "Ožiai",     "Šaukštai",
                                     "Bananai",     "Pomidorai",  "Motociklai",   "Apelsinai", "Batai",
                                     "Cepelinai",   "Kugeliai",   "Plaukai",      "Namai",     "Akmenys",
                                     "Kiaušiai",    "Krūmai",     "Šautuvai",     "Pirštai",   "Draugai",
                                     "Priešai",     "Studentai"};


    /// TEAM NAME ARRAYLISTS -----------------------------------------------------------------------

    ArrayList<String> adjectives_enn = new ArrayList<String>();
    ArrayList<String> adjectives_ltt = new ArrayList<String>();

    ArrayList<String> nouns_enn = new ArrayList<String>();
    ArrayList<String> nouns_ltt = new ArrayList<String>();


}
