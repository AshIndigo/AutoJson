package com.ashindigo.autojson;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;

public class AutoJsonClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MinecraftClient.getInstance().getResourcePackManager().registerProvider(new AutoJsonResourcePackCreator());
    }
}
