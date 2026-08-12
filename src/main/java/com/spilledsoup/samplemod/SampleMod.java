package com.spilledsoup.samplemod;

import com.spilledsoup.umapi.UMAPI;
import com.spilledsoup.umapi.UMAPIMod;

public final class SampleMod implements UMAPIMod {

    @Override
    public void initialise() {
        UMAPI.logger().info("SampleMod initialised.");

        UMAPI.events().onPlayerJoin(player -> {
            String playerName = player.getName();

            UMAPI.logger().info("Welcoming " + playerName + ".");
            player.sendMessage("Welcome, " + playerName + "!");
        });
    }
}
