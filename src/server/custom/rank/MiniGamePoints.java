package server.custom.rank;

import client.BuddyEntry;

public class MiniGamePoints implements Comparable {

    private int characterid;
    private String name;
    private BuddyEntry buddyEntry;
    private int wins;
    private int ties;
    private int losses;

    public int getCharacterid() {
        return characterid;
    }

    public void setCharacterid(int characterid) {
        this.characterid = characterid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BuddyEntry getBuddyEntry() {
        return buddyEntry;
    }

    public void setBuddyEntry(BuddyEntry buddyEntry) {
        this.buddyEntry = buddyEntry;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getTies() {
        return ties;
    }

    public void setTies(int ties) {
        this.ties = ties;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getScore() {
        //TODO: Fix formula
        int score = 2000;

        if (wins + ties + losses > 0) {
            score += wins * 2;
            score += ties;
            score -= losses * 2;
        }
        return score;
    }

    @Override
    public int compareTo(Object o) {

        MiniGamePoints mgp = (MiniGamePoints) o;
        int otherScore = mgp==null?0:mgp.getScore();
        return otherScore-this.getScore() ;
    }

}
