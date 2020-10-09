package com.jaeheonshim.bedwars.domain;

public enum ArmorLevel {
    LEATHER(1),
    CHAINMAIL(2),
    IRON(3),
    DIAMOND(4);

    int level;
    ArmorLevel(int l) {
        level = l;
    }

    public int getLevel() {
        return level;
    }
}
