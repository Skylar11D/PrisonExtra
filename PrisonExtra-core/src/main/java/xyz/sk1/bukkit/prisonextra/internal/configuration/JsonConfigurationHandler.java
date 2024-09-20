package xyz.sk1.bukkit.prisonextra.internal.configuration;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import xyz.sk1.bukkit.prisonextra.Core;

import java.io.*;

public class JsonConfigurationHandler implements ConfigurationHandler<JsonObject> {

    private final File file;
    private JsonObject object;

    public JsonConfigurationHandler(File file) {
        this.file = file;
        this.load();

    }

    @Override
    public void load() {

        if(!file.exists())
            Core.getInstance().saveResource(file.getName()+".json", false);

        JsonParser parser = new JsonParser();

        try {
            this.object = parser.parse(new FileReader(file)).getAsJsonObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void save() {
        try(FileWriter writer = new FileWriter(file)){
            writer.write(object.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JsonObject get(String path) {
        return object.getAsJsonObject(path);
    }

    @Override
    public void set(String path, JsonObject victim) {
        object.add(path, new JsonPrimitive(victim.toString()));
    }

}
