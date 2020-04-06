public interface Fight {
    //make attack against monster
    void makeMove(Monster m);

    //whether attack was dodged
    boolean attackDodged();

    //reaction to getting attacked
    void getAttacked(Monster m);


}
