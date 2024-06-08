/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package nba;

/**
 *
 * @author Ziyua
 */
public class ContractPlayer implements Comparable<ContractPlayer> {
    private String id;
    private String name;
    private int ranking;
    private double compositeScore;

    public ContractPlayer(String id, String name, int ranking, double compositeScore) {
        this.id = id;
        this.name = name;
        this.ranking = ranking;
        this.compositeScore = compositeScore;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRanking() {
        return ranking;
    }

    public double getCompositeScore() {
        return compositeScore;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    @Override
    public int compareTo(ContractPlayer other) {
        return Integer.compare(this.ranking, other.ranking);
    }
}

