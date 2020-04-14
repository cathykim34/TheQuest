import java.util.*;

public class BoardDriver {
    public BoardDriver() {
    }

    //returns board
    public Board createBoard(){
        Board board = new Board(8, 8);
        System.out.println("THE QUEST GAME BOARD:");
        System.out.println(board);
        System.out.println("H1, H2, and H3 represent where your heroes currently are on the map.");
        System.out.println("A tile filled with I's represents a wall means that is an inaccessible tile on the board.");
        System.out.println("Your characters can buy and sell items in your Nexus, the tile outlined by N's.");
        System.out.println("\n");
        return board;
    }


    public void takePotion(Hero h){
        if(h.getBP().getPotions().isEmpty()){
            System.out.println("Currently do not own any potions!");
        }
        else {
            FightDriver FD = new FightDriver();
            FD.usePotion(h);
        }

    }


    //enter board game to start playing
    public void enterTheWorld(HeroTeam team, Board board, QuestDriver QD){

        Scanner input = new Scanner(System.in);
        //prepare the market items
        QD.setUpMarket();
        MonsterTeam monsters = QD.createMonsterTeam(team, board);
        boolean enterQuit = false;
        do{
            System.out.println("To move around the board, enter W to move up, A to move left, S to move down and D to move right");
            System.out.println("To quit the game, enter Q");
            System.out.println("To show information about your heroes, enter I");
            System.out.println("To make changes armor, weapon, take a potion or cast a spell, press C");
            System.out.println("To teleport to your team's nexus, enter B");
            System.out.println("To teleport to another lane, enter T");
            System.out.println(board);
            System.out.println("(Tiles with I's around them are walls, so users cannot move there.");

            //each hero gets a turn
            for(Hero hero: team.getTeam()) {
                boolean makeMove = true;
                do {
                    try {
                        System.out.println("What would you like " + hero.getName() + " to do?");
                        String in = input.next();
                        String currentInput = in.toUpperCase();
                        if (currentInput.equals("Q")) {
                            System.out.println("Quitting game...");
                            makeMove= false;
                            enterQuit = true;
                        } else if (currentInput.equals("I")) {
                            System.out.println(hero);

                        } else if (currentInput.equals("C")) {
                            String response = QD.giveOptions();
                            if (response.equals("P")) {
                                if(hero.potionsExist()){
                                    hero.takePotion();
                                    makeMove= false;
                                }else{
                                    System.out.println("Currently do not own any potions! Please try another action.");
                                }
                            }
                            if (response.equals("S")) {
                                if(hero.enemyNear(board)){
                                    if(hero.useSpell(monsters.nearestMonster(hero.getLane()))){
                                        makeMove = false;
                                    }
                                }
                                else{
                                    System.out.println("No enemies nearby to use spell on!");
                                }
                            } else if (response.equals("C")) {
                                if (hero.changeNotPossible()) {
                                    System.out.println("Currently do not own any armor or weapons.");
                                } else {
                                    change(hero);
                                    makeMove= false;
                                }
                            }
                        } else if(currentInput.equals("F")) {
                            if(hero.enemyNear(board)){
                                hero.attack(monsters.nearestMonster(hero.getLane()));
                                makeMove= false;
                            }else{
                                System.out.println("No enemies nearby!");
                            }
                        }else if (currentInput.equals("W") || currentInput.equals("A") || currentInput.equals("S") || currentInput.equals("D")) {
                            boolean possible = hero.makeMove(board, currentInput);
                            if (!possible) {
                                System.out.println("Oops! This move is not possible, please try again.");
                            }
                            else{
                                makeMove= false;
                            }
                        } else if (currentInput.equals("B")) {
                            // Teleport to own nexus
                            board.teleportToNexus(hero);
                            makeMove= false;
                        } else if (currentInput.equals("T")) {
                            System.out.println("Enter the lane number that you would like to teleport to [0-2]:");
                            int laneNum = input.nextInt();
                            if (laneNum > board.lanes.length - 1 || laneNum < 0) {
                                System.out.println("Lane does not exist");
                            } else {
                                board.teleport(hero, laneNum);
                                makeMove= false;
                            }
                        } else {
                            System.out.println("Invalid input, please try again.");
                        }

                    } catch (Exception e) {
                        System.out.println("Invalid move, please try again.");
                    }
                }while(makeMove);
                if(enterQuit) {
                    break;
                }
                for (Monster m : monsters.getTeam()) {
                    m.makeMove(board);
                    if(QD.monstersWon(monsters)){
                        System.out.println("Oh no a monster reached the your nexus! Game over.");
                        enterQuit = true;
                    }
                }
                MonsterTeam.increaseRound();
                if (MonsterTeam.getRounds() == 8) {
                    nextRound(monsters, team, board);
                }
                team.roundHealthIncrease();
            }
        }while(!enterQuit);
        System.out.println("Thanks for playing the Quest, hope you a had a great time!");
    }

    //resets round counter, spawns new monsters
    public void nextRound(MonsterTeam monsters, HeroTeam team, Board board){
        MonsterTeam.setRounds(1);
        monsters.addMonsters(board, team);
    }

    //changing clothes/weapon
    public void change(Hero h){
        h.changeWorA();
        Scanner input = new Scanner(System.in);
        boolean keepChanging = true;
        do{
            System.out.println("Would you like to keep changing? Enter Y for yes or N for no");
            try{
                String in = input.next();
                if(in.toUpperCase().equals("Y")){
                    h.changeWorA();
                }
                else if (in.toUpperCase().equals("N")){
                    keepChanging = false;
                }
                else{
                    System.out.println("Invalid input, please try again");
                }
            }catch (Exception e){
                System.out.println("Invalid, please try again");
            }
        }while(keepChanging);
    }


}
