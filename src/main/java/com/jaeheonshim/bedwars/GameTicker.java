package com.jaeheonshim.bedwars;

public class GameTicker implements Runnable {
    long lastTickTime = -1;

    @Override
    public void run() {
        if(lastTickTime == -1) {
            lastTickTime = System.currentTimeMillis();
            return;
        }

        long delta = System.currentTimeMillis() - lastTickTime;
        BedwarsGameManager manager = BedwarsGameManager.getInstance();
        manager.tick(delta);

        lastTickTime = System.currentTimeMillis();
    }
}
