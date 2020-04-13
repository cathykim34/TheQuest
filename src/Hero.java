import java.util.*;
public abstract class Hero extends Characters{
    protected String type = "Hero";
    protected String name;
    protected String nickname;
    protected int level;
    protected int HP;
    protected Wallet wallet;
    protected int mana;
    protected int strength;
    protected int agility;
    protected int dexterity;
    protected Weaponry weapon;
    protected Armory armor;
    protected Backpack backpack;
    protected String favoredSkills;
    protected int row;
    protected int column;
    protected int lane;

    //just to use as menu lists
    protected static ArrayList<String> allHeroes = new ArrayList<>();
    public static void allHeroTypes(){
        Hero.allHeroes.add("Paladin");
        Hero.allHeroes.add("Warrior");
        Hero.allHeroes.add("Sorcerer");
    }

    public abstract int walletAmt();
    public abstract void decreaseWalletAmt(int i);
    public abstract void increaseWalletAmt(int i);
    public abstract Backpack getBP();
    public abstract boolean changeNotPossible();
    public abstract void skillSet();
    public abstract void currentStats();
    public abstract void finishRound();
    public abstract void setHP(int i);
    public int getStrength() {
        return strength;
    }
    public abstract void setStrength(int i);
    public abstract void setMana(int i);
    public abstract void useMana(double i);
    public int getDexterity() {
        return dexterity;
    }
    public abstract void setDexterity(int i);
    public int getAgility() {
        return agility;
    }
    public abstract void setAgility(int i);
    public abstract void levelUp();
    public abstract String getName();
    public abstract void revive();
    public abstract double getMana();
    public abstract int getLevel();
    public abstract boolean armorOn();
    public abstract boolean handsUsed();
    public abstract void equip(Armory a);
    public abstract void unequip(Armory a);
    public abstract void equip(Weaponry a);
    public abstract void unequip(Weaponry a);
    public abstract void getAttacked(Monster m);
    public abstract boolean checkFainted();
    public abstract String getBackpack();
    public abstract void addXP(int i);
    public abstract void makeMove(Monster m);
    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public int getColumn() {
        return column;
    }
    public void setColumn(int column) {
        this.column = column;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getLane() {
        return lane;
    }

    public void setLane(int lane) {
        this.lane = lane;
    }

    public static ArrayList<String> listGetter(){
        return Hero.allHeroes;
    }

}
