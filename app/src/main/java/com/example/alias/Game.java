package com.example.alias;

import android.media.MediaPlayer;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;

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
    private ArrayList<String> game_words; // copy of words, so that already seen could be removed

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
    public void setGameWords(String[] gameWords) {
        this.game_words = new ArrayList<String>(Arrays.asList(gameWords)); }

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
    public String getGameWord(int index) { return this.game_words.get(index); }
    public int getGameWordsCount() { return this.game_words.size(); }

    /// ADDITIONAL methods -------------------------------------------------------------------------
    public void removeGameWord(int index) { this.game_words.remove(index); }
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
                                "Žandas",           "Atletas",    "Filmas",       "Karštis",
                                "Padavėja",         "Dėdė",      "Brokolis",    "Pelė",
                                "Kavinė",        "Kramtyti",       "Druska",       "Skrudinta duona",
                                "Akiniai",           "Knyga",       "Sraigė",       "Dubuo",
                                "Supynės",            "Varlė",      "Gyvas",        "Monstras",
                                "Aitvaras",             "Sapnas",     "Vandenynas",        "Kalėjimas",
                                "Kablys",              "Laikrodis",    "Apskritimas",       "Jūrų vėžlys",
                                "Avis",            "Žuvis",     "Krabas",         "Kaminas",
                                "Sraigtasparnis",       "Mėnulis",     "Kvadratas",       "Muzika",
                                "Žvaigždė",             "Kiaulė",      "Ausis",          "Debesis",

                                "Šuo", "Lydeka", "Veršelis", "Voverė",
                                "Tinginys", "Papūga", "Raganosis", "Boružėlė",
                                "Vilkas", "Kupranugaris", "Ponis", "Strutis",
                                "Lama", "Medūza", "Gepardas", "Briedis",
                                "Elnias", "Paukštis", "Kurmis", "Kengūra",
                                "Svirplys", "Pelikanas", "Žiogas", "Ruonis",
                                "Vikšras", "Žuvėdra", "Baltasis lokys", "Zebras",
                                "Omaras", "Jautis", "Katė", "Žiurkėnas",
                                "Šeškas", "Bitė", "Aligatorius", "Chameleonas",
                                "Voras", "Liūtas", "Asilas", "Krokodilas",
                                "Šikšnosparnis", "Žąsis", "Nuotrauka", "Spintelė",
                                "Vonia", "Klimėlis", "Dangtis", "Butelis",
                                "Durys", "Namas", "Audinys", "Popierius",
                                "Garažas", "Lėkštė", "Daržovės", "Kūdikis",
                                "Virtuvė", "Žolė", "Mygtukas", "Mama",
                                "Tėtis", "Stiklas", "Fortepijonas", "Gitara",
                                "Būgnai", "Augalas", "Kempinėlė", "Langas",
                                "Naminis gyvūnas", "Lova", "Kompiuteris", "Skėtis",

                                "Skaidrė","Medis","Kablys","Višta","Kaminas",
                                "Vyšnia","Sniego žmogus","Kalėjimas","Lietus","Hipopotamas",
                                "Kompiuteris","Pelėda","Jūra","Žolė","Banginis",
                                "Apskritimas","Skruzdelė","Plyšys","Valtis","Kuprinė",
                                "Musė","Rankinė","Kreidelė","Pica","Sausainis",
                                "Kalnas","Juros vėžlys","Stiklainis","Keksiukas","Nagas",
                                "Balionas","Puodelis","Blokai","Lapas","Varlė",
                                "Vandenynas","Vėžlys","Kubas","Sapnas","Tualetas",
                                "Dinozauras","Ledai","Šaka","Pledas","Šukos",
                                "Plunksna","Motoroleris","Snaigė","Zigzagas","Sala",

                                "Bučinys", "Šaltis", "Pusryčiai", "Niežulys", "Apkabinimas",
                                "Kelėnas", "Šnabždesys", "Žiūronai", "Diskoteka", "Pertrauka",
                                "Skaičiavimas", "Skonis", "Liūdesys", "Irklas", "Beldimas",
                                "Berniukas", "Siurprizas", "Riešas", "Plojimas", "Šuolis",
                                "Pačiūža", "Lūpa", "Plaukimas", "Žiovulys", "Akis",
                                "Pėda", "Vakarienė", "Trauka", "Eilė", "Žiedas",
                                "Pabudimas", "Stūmimas", "Liftas", "Malda", "Triušis", "Krepšinis"
    };

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
                                  "Star",             "Pig",      "Ear",          "Cloud",

                                "Dog","Pike","Calf","Squirrel",
                                "Sloth","Parrot","Rhino","Ladybug",
                                "Wolf","Camel","Pony","Ostrich",
                                "Llama","Jellyfish","Cheetah","Moose",
                                "Reindeer","Bird","Mole","Kangaroo",
                                "Cricket","Pelican","Grasshopper","Seal",
                                "Caterpillar","Seagull","Polar bear","Zebra",
                                "Lobster","Ox","Cat","Hamster",
                                "Skunk","Bee","Alligator","Chameleon",
                                "Spider","Lion","Donkey","Crocodile",
                                "Bat","Goose","Photograph","Cabinet",
                                "Bath","Mat","Lid","Bottle",
                                "Door","Home","Tissue","Paper",
                                "Garage","Plate","Vegetable","Baby",
                                "Kitchen","Grass","Button","Mom",
                                "Dad","Glass","Piano","Guitar",
                                "Drums","Plant","Sponge","Window",
                                "Pet","Bed","Computer","Umbrella",

                                "Slide","Tree","Hook","Chicken","Chimney",
                                "Cherry","Snowman","Jail","Rain","Hippo",
                                "Computer","Owl","Sea","Grass","Whale",
                                "Circle","Ant","Crack","Boat","Backpack",
                                "Fly","Purse","Crayon","Pizza","Cookie",
                                "Mountain","Sea turtle","Jar","Cupcake","Nail",
                                "Baloon","Cup","Blocks","Leaf","Frog",
                                "Ocean","Turtle","Cube","Dream","Bathroom",
                                "Dinosaur","Popsicle","Branch","Blanket","Comb",
                                "Feather","Motorcycle","Snowflake","Zigzag","Island",

                                "Kiss", "Cold", "Breakfast", "Itch", "Hug",
                                "Knee", "Whisper", "Binoculars", "Disco", "Break",
                                "Count", "Taste", "Sad", "Paddle", "Knock",
                                "Boy", "Surprise", "Wrist", "Clap", "Hop",
                                "Skate", "Lip", "Swim", "Yawn", "Eye",
                                "Feet", "Dinner", "Pull", "Row", "Ring",
                                "Awake", "Push", "Lift", "Beg", "Prayer",
                                "Rabbit", "Basketball"

    };



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
                                "Sidabro dirbiniai",       "Ratas",              "Durų rankena",

                                "Avarija", "Pradėti", "Kreivė", "Miestas",
                                "Tvenkinys", "Kopėčios", "Sušiai", "Nuotėkis",
                                "Mikčiojimas", "Stadionas", "Naujienlaiškis", "Cukrus",
                                "Valanda", "Limitas", "Žaislų parduotuvė", "Prailgintojas",
                                "Kostiumas", "Gamykla", "Lygis", "Skalbimo mašina",
                                "Gydymas", "Puslapis", "Vidurnaktis", "Teta",
                                "Kaubojus", "Šildytuvas", "Leidėjas", "Šaknis",
                                "Mitas", "Legenda", "Šalis", "Alus",
                                "Užtrauktukas", "Chronometras", "Žentas", "Dukra",
                                "Uošvė", "Uošvis", "Sūnėnas", "Dukterėčia",
                                "Gervė", "Istorija", "Pakrantė", "Pirmininkas",
                                "Prisijungti", "Komanda", "Partneris", "Ugniagesys",

                                "Geležinkelis","Grilius","Šaknis","Matyti","Padanga",
                                "Avis","Kaukė","Supynės","Tinklas","Pižama",
                                "Pavasaris","Varlytė","Oro uostas","iPad","Puma",
                                "Vainikas","Jūrų arkliukas","Daržovė","Spyna","Lempos jungtukas",
                                "Bokštas","Kriketas","Kojinė","Vamzdis","Kepėjas",
                                "Burė","Gryni pinigai","Vaškas","Kasetė","Tarakonas",
                                "Vaflis","Būsena","Vertėjas","Princas","Žymeklis",
                                "Riešutas","Diagrama","Ketvertukas","Platuma","Virtuvė",
                                "Snieglenčių sportas","Švilpukas","Kastuvas","Salieras","Sandalas",
                                "Geležis","Slidinėti","Bičių avilys","Greitkelis","Įeiti",

    };

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
                              "Silverware",       "Lap",              "Doorknob",

                            "Shipwreck","Start","Curve","City",
                            "Pond","Ladder","Sushi","Leak",
                            "Stutter","Stadium","Newsletter","Sugar",
                            "Hour","Limit","Toy store","Extension cord",
                            "Suit","Factory","Level","Washing machine",
                            "Cure","Page","Midnight","Aunt",
                            "Cowboy","Heater","Publisher","Blueprint",
                            "Myth","Legend","Country","Tin",
                            "Zipper","Stopwatch","Son-in-law","Daughter-in-law",
                            "Brother-in-law","Sister-in-law","Father-in-law","Mother-in-law",
                            "Crane","Story","Coastline","Chairman",
                            "Log-in","Team","Partner","Firefighter",

                            "Railroad","Grill","Root","See","Tire",
                            "Sheep","Mask","Swing","Net","Pajamas",
                            "Spring","Bowtie","Airport","iPad","Cougar",
                            "Wreath","Seahorse","Vegetable","Lock","Light switch",
                            "Tower","Cricket","Stocking","Pipe","Baker",
                            "Sail","Cash","Wax","Tape","Cockroach",
                            "Waffle","State","Dump truck","Prince","Marker",
                            "Nut","Chart","Quadruplets","Latitude","Kitchen",
                            "Snowboarding","Whistle","Shovel","Celery","Sandal",
                            "Iron","Ski","Beehive","Highway","Enter",
    };

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
                                "Laiko zona",            "Nepažįstamas",          "Pelkės",

                                "Linksmas", "Gylis", "Protestantas", "Šlamštas",
                                "Stalčius", "Siesta", "Mirtingas", "Nemirtingas",
                                "Fragmentas", "Kilometras", "Mįslė", "Susikerta",
                                "Pavadinimas", "Eketė", "Gimnazistas", "Išsklaidyti",
                                "Dužo", "Chaosas", "Harmonija", "Įstatymas",
                                "Budrus", "Veteranas", "Pareiškimas", "Psichologas",
                                "Pasitikėti", "Atradimas", "Pasididžiavimas", "Dispečeris",
                                "Brunetė", "Turtas", "Indeksas", "Infekcija",
                                "Sauja", "Skrydis", "Keliamieji metai", "Priešas",
                                "Sugriauti", "Ironija", "Pokštas", "Bausmė",
                                "Gyventojas", "Teismas", "Nuomonė", "Tvarkaraštis",
                                "Proto bokštas", "Sužavėti", "Kūrėjas", "Reakcija",

                                "Griaustinis","Meras","Ašarotas","Tureklai","Orbita",
                                "Mikrobas","Taika","Dailidė","Ikrai","Išnykti",
                                "Automobilių prekyba","Klijų pistoletas","Snausti","Krovinys","Konkurencija",
                                "Valdžia","Ping pong'as","Mažmeninė prekyba","Visaėdis","Vandens ciklas",
                                "Kostiumas","Kraštovaizdis","Katalogas","Tarnas","Pristatymas",
                                "Koala","Trauma","Kelionė","Hipsteris","Lašelinė",
                                "Gaminti","Lenta","Rasa","Bagažas","Šiukšlės",
                                "Važiuojamoji dalis","Gamykla","Nardymas","Išrasti","Šonkaulis",
                                "Apsiaustas","Barbershop'as","Stipri liūtis","Apskleidimas","Kaiminystė",
                                "Nutekėjimas","Tankumas","Pirštų galiukai","Trūkumas","Aplodismentai",
                                "Mimika","Vilkikas","Varnų lizdas","Animacinis filmas","Leidėjas",


    };

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
                                          "Time zone",            "Stranger",          "Wetlands",

                                        "Fun","Depth","Protestant","Junk",
                                        "Drawer","Siesta","Mortal","Immortal",
                                        "Fragment","Acre","Riddle","Intersect",
                                        "Title","Ice fishing","Gymnast","Scatter",
                                        "Shatter","Chaos","Harmony","Law",
                                        "Vigilante","Veteran","aftermath","Psychologist",
                                        "Confident","Discovery","Pride","Dispatcher",
                                        "Brunette","Wealth","Index","Infection",
                                        "Handful","Flight","Leap year","Enemy",
                                        "Destruction","Ironic","Joke","Punishment",
                                        "Population","Court","Opinion","Schedule",
                                        "Brainstorm","Admire","Creator","Reaction",

                                        "Thunder","Mayor","Tearful","Banister","Orbit",
                                        "Germ","Peace","Carpenter","Caviar","Granpa",
                                        "Car dealership","Glue gun","Nap","Cargo","Competition",
                                        "Government","Ping pong","Retail","Omnivore","Water cycle",
                                        "Suit","Landscape","Catalog","Servant","Deliver",
                                        "Koala","Injury","Trip","Hipster","Drip",
                                        "Produce","Plank","Dew","Baggage","Junk",
                                        "Driveway","Factory","Scuba diving","Invent","Rib",
                                        "Cape","Barbershop","Downpour","Reveal","Neighborhood",
                                        "Leak","Density","Tiptoe","Drawback","Applause",
                                        "Mime","Tow truck","Crow's nest","Cartoon","Publisher",
    };


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
