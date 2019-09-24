package com.ashindigo.autojson;

import com.ashindigo.autojson.api.AutoConfig;
import com.ashindigo.autojson.api.AutoJsonApi;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import java.io.ByteArrayInputStream;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.ashindigo.autojson.api.AutoJsonApi.getSoundMap;

public class JsonGenerator {

    public static ByteArrayInputStream getItemJson(Identifier identifier) {
        JsonObject file = new JsonObject();
        file.addProperty("forge_marker", 1);
        file.addProperty("parent", "item/generated");
        JsonObject texture = new JsonObject();
        texture.addProperty("layer0", identifier.getNamespace() + ":items/" + identifier.getPath());
        file.add("textures", texture);
        return new ByteArrayInputStream(file.toString().getBytes());
    }

    public static ByteArrayInputStream getBlockJson(String name, Identifier id) {
        switch (name.split("/")[2]) {
            case "blockstates": return getBlockstateJson(id);
            case "models": return name.split("/")[3].equals("block") ? getBlockModelJson(id) : getItemJson(id);
            default:  Logger.getLogger("AutoJson").log(Level.WARNING, "Could not create Block JSON"); return new ByteArrayInputStream("".getBytes());
        }
    }

    public static ByteArrayInputStream getBlockModelJson(Identifier id) {
        JsonObject file = new JsonObject();
        file.addProperty("parent", "block/cube_all");
        JsonObject texture = new JsonObject();
        texture.addProperty("all", id.getNamespace() + ":block/" + id.getPath());
        file.add("textures", texture);
        return new ByteArrayInputStream(file.toString().getBytes());
    }

    public static ByteArrayInputStream getBlockstateJson(Identifier id) {
        JsonObject file = new JsonObject();
        JsonObject variants = new JsonObject();
        JsonObject model = new JsonObject();
        model.addProperty("model",id.getNamespace() + ":block/" + id.getPath());
        variants.add("", model);
        file.add("variants", variants);
        return new ByteArrayInputStream(file.toString().getBytes());
    }

    public static ByteArrayInputStream getSoundsJson(String name) {
        JsonObject file = new JsonObject();
        for (SoundEvent sound : getSoundMap().get(name)) {
            JsonObject soundInfo = new JsonObject();
            soundInfo.addProperty("category", "records");
            JsonObject trackInfo = new JsonObject();
            trackInfo.addProperty("name", sound.getId().getNamespace() + ":music/" + Objects.requireNonNull(sound.getId()).getPath());
            trackInfo.addProperty("stream", true);
            JsonArray array = new JsonArray();
            array.add(trackInfo);
            soundInfo.add("sounds", array);
            file.add(sound.getId().getPath(), soundInfo);
        }
        return new ByteArrayInputStream(file.toString().getBytes());
    }

    public static ByteArrayInputStream getLangFile() {
        JsonObject file = new JsonObject();
        for (Identifier id : AutoJsonApi.getList().keySet()) {
            file.addProperty((AutoJsonApi.getList().get(id).getType() == AutoConfig.AutoConfigType.ITEM ? "item." : "tile.") + id.getNamespace() + "." + id.getPath(), AutoJsonApi.getList().get(id).getLangName());
        }
        return new ByteArrayInputStream(file.toString().getBytes());
    }
}
