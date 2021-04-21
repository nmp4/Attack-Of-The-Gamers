/**
*  This class is the main class of the "Attack of the Gamers" application. 
*  "Attack of the Gamers" is a very simple, text based adventure game.  Users 
*  can walk around some scenery. That's all. It should really be extended 
*  to make it more interesting!
* 
*  To play this game, create an instance of this class and call the "play"
*  method.
* 
*  This main class creates and initialises all the others: it creates all
*  rooms, creates the parser and starts the game.  It also evaluates and
*  executes the commands that the parser returns.
* 
* @author  Natasha Muthoni Pregetter
* @version 02/09/2020
*/
    
    public class Game {
    private Parser parser;
    private Player player;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createPlayer();
        createRooms();
        parser = new Parser();
    }
    
    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room entrance, bigHallS1,bigHallS2, bigHallS3, bigHallS4, photoStudio, artistStands, faveArtist, secretBoss;
        Item n64Controller, mikuFigurine, yuGiOhCards, dndDice, studioLight, finalComic, mysteriousPen;
        
        //Create the items
        n64Controller = new Item("controller","An old beat up n64 Controller", 0.3, true);
        mikuFigurine = new Item("figurine","A figurine of the classic hatsune miku design", 0.48, true);
        yuGiOhCards = new Item("cards","A deck of YuGiOh playing cards", 0.99, true);
        dndDice = new Item("die","A single Dungeons and Dragons die, it looks like a prism", 0.041, true);
        studioLight = new Item("light","A huge Studio Light used for photoshoots", 2.42, false);
        finalComic = new Item("comic","Your (former)favourite artists newest comic", 0.17, true);
        mysteriousPen = new Item("pen","A pen that has a nudie of an anime girl on it", 0.5, true);
        
      
        // create the rooms
        entrance = new Room("at the entrance to the convention");
        bigHallS1 = new Room("inside section 1 of the big hall");
        bigHallS2 = new Room("inside section 2 of the big hall");
        bigHallS3 = new Room("inside section 3 of the big hall");
        bigHallS4 = new Room("inside section 4 of the big hall");
        photoStudio = new Room("in the cosplay photo studio");
        artistStands = new Room("in the hall with artist stands");
        faveArtist = new Room("at your favourite artists stand");
        secretBoss = new Room("in a corner of the artist hall");
        
        // Add the items to the rooms
        bigHallS1.addItem(n64Controller);
        bigHallS2.addItem(mikuFigurine);
        bigHallS3.addItem(yuGiOhCards);
        bigHallS4.addItem(dndDice);
        photoStudio.addItem(studioLight);
        faveArtist.addItem(finalComic);
    
        // initialise room exits
        entrance.setExits("south",bigHallS1);
        bigHallS1.setExits("east",bigHallS2);
        bigHallS1.setExits("south",bigHallS4);
        bigHallS2.setExits("south",bigHallS3);
        bigHallS2.setExits("west",bigHallS1);
        bigHallS3.setExits("north",bigHallS2);
        bigHallS3.setExits("west",bigHallS4);
        bigHallS4.setExits("north",bigHallS1);
        bigHallS4.setExits("east",bigHallS3);
        bigHallS4.setExits("west",photoStudio);
        photoStudio.setExits("east",bigHallS4);
        photoStudio.setExits("south",artistStands);
        artistStands.setExits("north",photoStudio);
        artistStands.setExits("west",faveArtist);
        faveArtist.setExits("east",artistStands);
        secretBoss.setExits("upstairs",faveArtist);
    
        player.setCurrentRoom(entrance);  // start game outside
    }
    
    /**
     * Creates the player object along with its inventory.
     */
    private void createPlayer(){
        Inventory inventory = new Inventory(3);
        Player player = new Player("Player", inventory, null, null);
    }
    
    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();
    
        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }
    
    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the Attack of the Gamers!");
        System.out.println("Attack of the Gamers is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printLocationInfo();
    }
    
    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
        private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;
        boolean wantToGoBack = false;
    
        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }
    
        String commandWord = command.getCommandWord();
        switch(commandWord){
            case "help":
                printHelp();
                break;
            case "go":
                goRoom(command);
                break;
            case "quit":
                wantToQuit = quit(command);
                break;
            case "look":
                look();
                break;
            case "sit":
                sit();
                break;
            case "back":
                back(command);
                break;
            case "take":
                take(command);
                break;
            case "drop":
                drop(command);
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the convention.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room
        
        Room nextRoom = player.getCurrentRoom().getExit(direction);
        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            player.setPreviousRoom(player.getCurrentRoom());
            player.setCurrentRoom(nextRoom);
            printLocationInfo();
        }
    }
    
    /**
     * Prints the location info of the player
     */
    public void printLocationInfo(){
        System.out.println(player.getCurrentRoom().getLongDescription());
        System.out.println();
    }
    

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command){
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    /**
     * "look" was entered, gives the description of where you are and what 
     * the exits of the room are.
     */
    private void look(){
        System.out.println(player.getCurrentRoom().getLongDescription());
    }
    
    /**
     * Restores players energy (in theory rn)
     */
    private void sit(){
        System.out.println("You have rested and regained your HP and MP. Time to push onwards!");
    }
    
    /**
     * Makes the player move back to the previous room that they were in.
     */
    private void back(Command command){
        if(command.hasSecondWord()){
            System.out.println("You can only go back to the room you were in previously.");
            System.out.println("Please only type back once to go backwards.");
        }
        else{
            player.setCurrentRoom(player.getPreviousRoom());
            printLocationInfo();
        }
    }
    
    /**
    * Makes the player pick up the specified item in the room if they have enough slots.
    */
    private void take(Command command){
        if(!command.hasSecondWord()){
            System.out.println("Take what?");
        }
        // if there is no second word we dont know what to take.
        String item = command.getSecondWord();
        if(item.equals(player.getCurrentRoom().checkIfItemIsPresent(item).getItemName())){
                System.out.println("You added the item to your inventory");
                player.addToPlayerInventory(player.getCurrentRoom().checkIfItemIsPresent(item));
        }
        else{
                System.out.println("That item is not present in this room, please try again");
        }
    }
    
    /**
     * Removes a specific item from the players inventory
     */
    private void drop(Command command){
        if(!command.hasSecondWord()){
            System.out.println("Drop what?");
        }
        
        String item = command.getSecondWord();
        
        if(item.equals(player.getCurrentRoom().checkIfItemIsPresent(item).getItemName())){
            player.removeFromPlayerInv(player.getCurrentRoom().checkIfItemIsPresent(item));
        }
        else{
            System.out.println("That item is not present in this room, please try again.");
        }
    }
}
