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

    public void change(Hero h){
        FightDriver FD = new FightDriver();
        FD.changeWorA(h);
        Scanner input = new Scanner(System.in);
        boolean keepChanging = true;
        do{
            System.out.println("Would you like to keep changing? Enter Y for yes or N for no");
            try{
                String in = input.next();
                if(in.toUpperCase().equals("Y")){
                    FD.changeWorA(h);
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

    //enter board game to start playing
    public void enterTheWorld(HeroTeam team, Board board, QuestDriver QD){

        Scanner input = new Scanner(System.in);
        //prepare the market items
        QD.setUpMarket();
        QD.createMonsterTeam(team);
        boolean enterQuit = false;
        do{
            System.out.println("To move around the board, enter W to move up, A to move left, S to move down and D to move right");
            System.out.println("To quit the game, enter Q");
            System.out.println("To show information about your heroes and perform changes on weapons/armories or take a potion, enter I");
            System.out.println(board);
            System.out.println("(Tiles with a line through them are walls, so users cannot move there. Tiles with an M contain a market)");
            try{
                String in = input.next();
                String currentInput = in.toUpperCase();
                if(currentInput.equals("Q")){
                    System.out.println("Quitting game...");
                    enterQuit = true;
                }
                else if(currentInput.equals("I")){
                    for(Hero hero: team.getTeam()){
                        System.out.println(hero);
                        String response = QD.giveOptions();
                        if(response.equals("P")){
                            takePotion(hero);
                        }
                        else if(response.equals("C")){
                            if(hero.changeNotPossible()){
                                System.out.println("Currently do not own any armor or weapons.");
                            }
                            else{
                                change(hero);
                            }
                        }

                    }
                }
                else if(currentInput.equals("W")||currentInput.equals("A") || currentInput.equals("S")|| currentInput.equals("D")){
                    boolean possible = board.makeMove(team, currentInput);
                    String currentPlace = board.boardArray[Team.getCurRow()][Team.getCurCol()].getType();
                    //check if current place is a common square
                    if(!possible){
                        System.out.println("Try moving somewhere else!");
                    }
                    else if(currentPlace.equals("C")){
                        QD.checkForBattle();
                    }
                    //check if current place is a market
                    else if(currentPlace.equals("M")){
                        QD.market();
                    }
                }
                else{
                    System.out.println("Invalid input, please try again.");
                }
            }catch(Exception e){
                System.out.println("Invalid move, please try again.");
            }

        }while(!enterQuit);
        System.out.println("Thanks for playing the Quest, hope you a had a great time!");
    }
}
