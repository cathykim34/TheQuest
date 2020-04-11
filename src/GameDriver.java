public class GameDriver {
    public static void main(String[] args){
        System.out.println("Welcome to the Quest!!");

        //create board
        BoardDriver BD = new BoardDriver();
        Board board = BD.createBoard();

        //create team of heroes and begin playing on the board
        QuestDriver QD = new QuestDriver();
        QD.createHeroes();

        //playing game
        BD.enterTheWorld(QD.createHeroTeam(board), board, QD);


    }
}
