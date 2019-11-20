package com.ashindigo.autojson;

import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.resource.ResourcePackProvider;

import java.util.Map;

public class AutoJsonResourcePackCreator implements ResourcePackProvider {
    @Override
    public <T extends ResourcePackProfile> void register(Map<String, T> map, ResourcePackProfile.Factory<T> factory) {
        map.put("autojson", ResourcePackProfile.of("autojson/autojson", false, AutoJsonResourcePack::new, factory, ResourcePackProfile.InsertionPosition.BOTTOM));
    }
}
