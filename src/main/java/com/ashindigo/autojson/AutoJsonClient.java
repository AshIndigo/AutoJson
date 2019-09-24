package com.ashindigo.autojson;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.ResourcePackCreator;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class AutoJsonClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
//        try {
//            Field resourcePackContainerManager = MinecraftClient.class.getDeclaredField("resourcePackContainerManager");
//            resourcePackContainerManager.setAccessible(true);
//            resourcePackContainerManager.get(MinecraftClient.getInstance()).getClass().getDeclaredMethod("addCreator", new Class[]{ResourcePackCreator.class}).invoke(resourcePackContainerManager.get(MinecraftClient.getInstance()), new AutoJsonResourcePackCreator());
//        } catch (NoSuchFieldException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
//            e.printStackTrace();
//        }
    }
}
