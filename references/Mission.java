package com.battle.battlepass.battlepass.references;

import com.battle.battlepass.battlepass.enums.MissionType;

public class Mission {
    private MissionType type;
    private String name;

    public Mission() {}

    public Mission(MissionType type, String name) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public MissionType getType() {
        return type;
    }

}
