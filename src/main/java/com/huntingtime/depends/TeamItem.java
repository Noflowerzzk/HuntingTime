package com.huntingtime.depends;

import net.minecraft.scoreboard.Team;

import java.util.ArrayList;

public class TeamItem {
    private ArrayList<String> itemList = new ArrayList<>();

    private Team teamName;

    public int score = 0;

    public TeamItem(Team teamName,  ArrayList<String> itemList) {
        this.teamName = teamName;
        this.itemList = itemList;
    }

    public Team getTeamName() { return this.teamName; }
    public ArrayList<String> getItemList() { return this.itemList; }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        TeamItem teamItem = (TeamItem) o;
//    }
}
