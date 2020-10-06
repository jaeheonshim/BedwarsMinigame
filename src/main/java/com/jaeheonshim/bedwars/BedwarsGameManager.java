package com.jaeheonshim.bedwars;

import java.util.ArrayList;
import java.util.List;

public class BedwarsGameManager {
    private static BedwarsGameManager instance;

    private List<BedwarsGame> bedwarsGames = new ArrayList<>();

    private BedwarsGameManager() {
        bedwarsGames.add(new BedwarsGame());
    }

    public void tick(long delta) {
        for(BedwarsGame game : bedwarsGames) {
            game.tick(delta);
        }
    }

    public static BedwarsGameManager getInstance() {
        if(instance == null) {
            instance = new BedwarsGameManager();
        }

        return instance;
    }
}
