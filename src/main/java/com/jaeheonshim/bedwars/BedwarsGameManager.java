package com.jaeheonshim.bedwars;

import com.jaeheonshim.bedwars.domain.BedwarsGame;
import com.jaeheonshim.bedwars.domain.BedwarsPlayer;
import com.jaeheonshim.bedwars.domain.BedwarsTeam;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;

public class BedwarsGameManager implements Disposable {
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

    public BedwarsGame getGameOfPlayer(String uuid) {
        for(BedwarsGame game : bedwarsGames) {
            for(BedwarsTeam team : game.getTeams()) {
                if (team.getTeamPlayers().containsKey(uuid)) {
                    return game;
                }
            }
        }

        return null;
    }

    public BedwarsPlayer getBedwarsPlayer(String uuid) {
        BedwarsGame game = getGameOfPlayer(uuid);
        if(game == null) {
            return null;
        }

        for(BedwarsTeam team : game.getTeams()) {
            if(team.getTeamPlayers().containsKey(uuid)) {
                return team.getTeamPlayers().get(uuid);
            }
        }

        return null;
    }

    public BedwarsGame getBedwarsGame(World world) {
        for(BedwarsGame game : bedwarsGames) {
            if(game.getWorld().equals(world)) {
                return game;
            }
        }

        return null;
    }

    public static BedwarsGameManager getInstance() {
        if(instance == null) {
            instance = new BedwarsGameManager();
        }

        return instance;
    }

    @Override
    public void dispose() {
        for(BedwarsGame game : bedwarsGames) {
            game.dispose();
        }
    }
}
