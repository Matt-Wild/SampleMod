package com.spilledsoup.samplemod;

import com.spilledsoup.umapi.UMAPI;
import com.spilledsoup.umapi.UMAPIMod;
import com.spilledsoup.umapi.content.ContentRegistry;
import com.spilledsoup.umapi.content.ItemContent;

public final class SampleMod implements UMAPIMod {

    @Override
    public void defineContent(ContentRegistry content) {
        ItemContent rawZinc = content.item("raw_zinc", "Raw Zinc");
        rawZinc.texture("textures/item/raw_zinc.png");
    }

    @Override
    public void initialise() {
        UMAPI.logger().info("SampleMod initialised.");

        UMAPI.logger().info("SampleMod target: " + UMAPI.environment() + ".");
        UMAPI.logger().info("SampleMod content items: " + UMAPI.content().itemCount() + ".");

        UMAPI.events().onPlayerJoin(player -> {
            String playerName = player.getName();

            UMAPI.logger().info("Welcoming " + playerName + ".");
            player.sendMessage("Welcome, " + playerName + "!");
        });
    }
}
