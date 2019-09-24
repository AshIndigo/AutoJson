package com.ashindigo.autojson.api;


import net.minecraft.util.Identifier;

/**
 * Config class used to configure an entry
 *
 * @see AutoJsonApi#addEntry(Identifier, AutoConfig)
 */
public class AutoConfig {

    private final AutoConfigTextureMode textureMode;
    private final AutoConfigType type;
    private String name = "";

    /**
     * Constructr to set the texture mode and config type
     * @param textureMode The texture mode
     * @param type An AutoConfigType
     */
    public AutoConfig(AutoConfigTextureMode textureMode, AutoConfigType type) {
        this.textureMode = textureMode;
        this.type = type;
    }

    /**
     * Gets the entries texture mode
     * @return Current TextureMode
     */
    public AutoConfigTextureMode getTextureMode() {
        return textureMode;
    }

    /**
     * Get the current type of entry
     * @return An AutoConfigType
     */
    public AutoConfigType getType() {
        return type;
    }

    /**
     * Sets the lang name of the block or item
     * <br> Note: This only supports en_us
     * @param name The translated name of the entry
     * @return This object
     */
    public AutoConfig setLangName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Gets this entries lang name
     * @return The current lang name
     */
    public String getLangName() {
        return name;
    }

    /**
     * Determines whether or not the texture should be loaded from the external folder
     */
    public enum AutoConfigTextureMode {
        INTERNAL,
        EXTERNAL
    }

    /**
     * Is the entry in question an item or block
     */
    public enum AutoConfigType {
        BLOCK,
        ITEM
    }
}
