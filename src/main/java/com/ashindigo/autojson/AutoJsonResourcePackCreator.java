package com.ashindigo.autojson;

import net.minecraft.resource.ResourcePackContainer;
import net.minecraft.resource.ResourcePackCreator;

import java.util.Map;

public class AutoJsonResourcePackCreator implements ResourcePackCreator {
    @Override
    public <T extends ResourcePackContainer> void registerContainer(Map<String, T> map, ResourcePackContainer.Factory<T> factory) {
        map.put("autojson", ResourcePackContainer.of("autojson/autojson", false, AutoJsonResourcePack::new, factory, ResourcePackContainer.InsertionPosition.BOTTOM));
    }
}
