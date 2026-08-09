package com.spilledsoup.samplemod;

import com.spilledsoup.umapi.UMAPI;
import com.spilledsoup.umapi.UMAPIMod;

public final class SampleMod implements UMAPIMod {

    @Override
    public void initialise() {
        UMAPI.events().onPlayerJoin(player -> {
            player.sendMessage("Welcome, " + player.getName() + "!");
        });
    }
}