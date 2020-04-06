public class Wallet {
    public int dollars;
    public Wallet(int money){
        this.dollars = money;
    }
    public String toString(){
        return("wallet has $" + this.dollars);
    }

}
