public abstract class Characters {
    public abstract void currentStats();
    protected String name;
    protected int row;
    protected int column;
    protected Lane lane;
    protected String nickname;
    protected Lane nexus;

    public String getNickname() {
        return nickname;
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
    public abstract boolean checkFainted();

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


}
