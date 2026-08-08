package com.spilledsoup.samplemod;

import com.spilledsoup.umapi.UMAPI;

public final class SampleMod {

    private SampleMod() {
    }

    public static void main(String[] args) {
        System.out.println(
                "SampleMod successfully connected to UMAPI " +
                        UMAPI.getVersion()
        );
    }
}