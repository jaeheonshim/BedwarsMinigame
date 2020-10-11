package com.jaeheonshim.bedwars.domain;

public enum BaseGenLevel {
    NONE(BaseGenLevel.DEFAULT_IRON, BaseGenLevel.DEFAULT_GOLD),
    IRON_FORGE(BaseGenLevel.DEFAULT_IRON * 1.25, BaseGenLevel.DEFAULT_GOLD * 1.25),
    GOLD_FORGE(BaseGenLevel.DEFAULT_IRON * 1.5, BaseGenLevel.DEFAULT_GOLD * 1.5),
    EMERALD_FORGE(BaseGenLevel.DEFAULT_IRON * 1.5, BaseGenLevel.DEFAULT_GOLD * 1.5),
    MOLTEN_FORGE(BaseGenLevel.DEFAULT_IRON * 2, BaseGenLevel.DEFAULT_GOLD * 2);

    public static final long DEFAULT_GOLD = 18000;
    public static final long DEFAULT_IRON = 6000;
    private long iron;
    private long gold;

    private BaseGenLevel(double iron, double gold) {
        this.iron = Math.round(iron);
        this.gold = Math.round(gold);
    }

    public long getIron() {
        return iron;
    }

    public long getGold() {
        return gold;
    }
}
