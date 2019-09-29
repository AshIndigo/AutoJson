package com.ashindigo.autojson.api;

import com.google.common.collect.ArrayListMultimap;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import java.util.HashMap;

/**
 * Contains various methods for adding automated blocks/items
 */
public class AutoJsonApi {

    private static final HashMap<Identifier, AutoConfig> jsonMap = new HashMap<>();
    private static final ArrayListMultimap<String, SoundEvent> soundMap = ArrayListMultimap.create();

    /**
     * Adds a block to be auto generated, also handles the item block
     * @param identifier The registry value for the entry
     * @param mode The texture mode
     */
    public static void addEntry(Identifier identifier, AutoConfig mode) {
        if (mode == null) {
            throw new IllegalArgumentException("AutoConfig cannot be null!");
        }
        jsonMap.put(identifier, mode);
    }

    /**
     * @return The internal entry map
     */
    public static HashMap<Identifier, AutoConfig> getMap() {
        return jsonMap;
    }

    /**
     * @return The internal sound map
     */
    public static ArrayListMultimap<String, SoundEvent> getSoundMap() {
        return soundMap;
    }

    /**
     * Will assume it's external otherwise you'd be using your own sounds.json
     * @param soundEvent ID for the sound
     */
    public static void addSoundEntry(Identifier id, SoundEvent soundEvent) {
        soundMap.put(id.getNamespace(), soundEvent);
    }
    
}
