package com.ashindigo.autojson;

import com.ashindigo.autojson.api.AutoConfig;
import com.ashindigo.autojson.api.AutoJsonApi;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.AbstractFileResourcePack;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This will automatically generate json files as required
 * Textures will be either passed on as normal or if specified will attempt to get the texture from config/modid/name.png
 */
public class AutoJsonResourcePack extends AbstractFileResourcePack {

    public AutoJsonResourcePack() {
        super(new File("autojson"));
    }

    @Override
    protected InputStream openFile(String name) throws FileNotFoundException {
        String[] names = name.split("/");
        if (name.equals("pack.mcmeta")) {
            return new ByteArrayInputStream(("{\n \"pack\": {\n   \"description\": \"AutoJsonLib's internal pack\",\n   \"pack_format\": 4\n}\n}").getBytes(StandardCharsets.UTF_8));
        } else if (name.equals("pack.png")) {
            return new ByteArrayInputStream("".getBytes());
        } else  if (names.length > 1) {
            Identifier id = new Identifier(names[1], FilenameUtils.removeExtension(names[names.length - 1]));
            if (name.equals("assets/" + names[1] + "/sounds.json")) {
                return JsonGenerator.getSoundsJson(names[1]);
            } else if(name.endsWith("/en_us.json")) {
                return JsonGenerator.getLangFile();
            } else if (name.endsWith(".ogg")) {
                if (!new File(MinecraftClient.getInstance().runDirectory, "config/" + names[1] + "/" + names[names.length - 1]).exists()) {
                    Logger.getLogger("autojson").log(Level.WARNING, "Music file not found! Missing File: " + new File(MinecraftClient.getInstance().runDirectory, "config/" + names[1] + "/" + names[names.length - 1]).toString());
                }
                return new FileInputStream(new File(MinecraftClient.getInstance().runDirectory, "config/" + names[1] + "/" + names[names.length - 1]));
            } else if (names[2].equals("textures")) {
                if (AutoJsonApi.getMap().get(id).getTextureMode() == AutoConfig.AutoConfigTextureMode.EXTERNAL) {
                    if (!new File(MinecraftClient.getInstance().runDirectory, "config/" + names[1]).exists()) {
                        new File(MinecraftClient.getInstance().runDirectory, "config/" + names[1]).mkdirs();
                    }
                    return new FileInputStream(new File(MinecraftClient.getInstance().runDirectory, "config/" + names[1] + "/" + names[names.length - 1]));
                }
            } else {
                if (AutoJsonApi.getMap().containsKey(id)) {
                    switch (AutoJsonApi.getMap().get(id).getType()) {
                        case ITEM:
                            return JsonGenerator.getItemJson(Objects.requireNonNull(id));
                        case BLOCK:
                            return JsonGenerator.getBlockJson(name, id);
                        default:
                            return new ByteArrayInputStream("".getBytes());
                    }
                }
            }
        }
        return new ByteArrayInputStream("".getBytes()); // Could be bad
    }

        @Override
        public boolean contains(ResourceType var1, Identifier var2){
            if (var1 == ResourceType.SERVER_DATA) {
                return false;
            }
            if (var2.getPath().split("/").length > 2) {
                return AutoJsonApi.getMap().containsKey(new Identifier(var2.getNamespace(), FilenameUtils.removeExtension(var2.getPath().split("/")[2])));
            }
            return var2.getPath().equals("sounds.json") || var2.getPath().equals("pack.mcmeta") || var2.getPath().equals("lang/en_us.json");
        }

        @Override
        protected boolean containsFile (String var1){
            return false; // NO-OP
        }

        @Override
        public Collection<Identifier> findResources(ResourceType type, String path, int depth, Predicate<String> predicate) {
            return Collections.emptyList();
        }

        @Override
        public Set<String> getNamespaces (ResourceType var1){
            HashSet<String> set = new HashSet<>();
            for (Identifier id : AutoJsonApi.getMap().keySet()) {
                set.add(id.getNamespace());
            }
            return set;
        }

        @Override
        public void close () {

        }
    }
