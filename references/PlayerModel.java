package com.battle.battlepass.battlepass.references;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerModel {
    private Player player;
    private int exp;
    private List<Mission> missions = new ArrayList<>();
    private String uuid;
    private String tier;

    public PlayerModel(Player player, int exp, List<Mission> missions, String tier) {
        this.player= player;
        this.exp = exp;
        this.missions = missions;
        this.tier = tier;
    }

    public PlayerModel(String uuid, int exp, List<Mission> missions, String tier) {
        this.uuid = uuid;
        this.exp = exp;
        this.missions = missions;
        this.tier = tier;
    }

    public PlayerModel(String uuid, int exp, String tier) {
        this.uuid = uuid;
        this.exp = exp;
        this.tier = tier;
    }

    public int getExp() {
        return exp;
    }

    public Player getPlayer() {
        if (player == null) {
            try {
                return Bukkit.getPlayer(uuid);
            } catch (Exception err) {
                return null;
            }
        }
        return player;
    }

    public List<Mission> getMissions() {
        return missions;
    }

    public String getTier() {
        return tier;
    }
}
