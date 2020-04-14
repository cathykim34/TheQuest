public abstract class Characters {
    public abstract void currentStats();
    protected String type = "Character";
    protected String name;
    protected int row;
    protected int column;
    protected Lane lane;
    protected String nickname;
    protected Lane nexus;

    protected double damage;

    //checks if there is a monster or hero in front of a character
    public abstract boolean enemyNear(Board board);


    //getters and setters of attributes
    public String getNickname() {
        return nickname;
    }
    public String getType() {
        return type;
    }
    public Lane getNexus() {
        return nexus;
    }
    public void setNexus(Lane nexus) {
        this.nexus = nexus;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
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
    public Lane getLane() {
        return lane;
    }
    public void setLane(Lane lane) {
        this.lane = lane;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }
    public abstract void getAttackedByMonster(Monster m, Board board);
    public abstract void getAttackedByHero(Hero h, Board board);
}
