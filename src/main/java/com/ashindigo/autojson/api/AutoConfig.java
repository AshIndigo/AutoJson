package com.ashindigo.autojson.api;


// Lets you tune the various settings for auto generation
public class AutoConfig {

    private final AutoConfigTextureMode textureMode;
    private final AutoConfigType type;
    private String name = "";

    public AutoConfig(AutoConfigTextureMode textureMode, AutoConfigType type) {

        this.textureMode = textureMode;
        this.type = type;
    }

    public AutoConfigTextureMode getTextureMode() {
        return textureMode;
    }

    public AutoConfigType getType() {
        return type;
    }

    public AutoConfig setLangName(String name) {
        this.name = name;
        return this;
    }

    public String getLangName() {
        return name;
    }

    // Is the texture in the mod jar or is it outside in the config folder
    public enum AutoConfigTextureMode {
        INTERNAL,
        EXTERNAL
    }

    // Is it a block or item, used for generation
    public enum AutoConfigType {
        BLOCK,
        ITEM
    }
}
