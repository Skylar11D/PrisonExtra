package xyz.sk1.bukkit.prisonextra.utils;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.EntityTypes;
import net.minecraft.server.v1_8_R3.MinecraftKey;
import net.minecraft.server.v1_8_R3.RegistryMaterials;
import xyz.sk1.bukkit.prisonextra.entity.minion.Minion;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="https://github.com/skylar11d">skylar</a>
 */

@SuppressWarnings("all")
public class CustomEntityRegistry {

    private static CustomEntityRegistry instance = null;

    private final BiMap<MinecraftKey, Class<? extends Entity>> customEntities = HashBiMap.create();
    private final BiMap<Class<? extends Entity>, MinecraftKey> customEntityClasses = this.customEntities.inverse();
    private final Map<Class<? extends Entity>, Integer> customEntityIds = new HashMap<>();

    private final RegistryMaterials wrapped;

    private CustomEntityRegistry(RegistryMaterials original) {
        this.wrapped = original;
    }

    public static CustomEntityRegistry getInstance() {
        if (instance != null) {
            return instance;
        }

        instance = new CustomEntityRegistry((RegistryMaterials) EntityTypes.b());

        try {
            //TODO: Update name on version change (RegistryMaterials)
            Field registryMaterialsField = EntityTypes.class.getDeclaredField("b");
            registryMaterialsField.setAccessible(true);

            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(registryMaterialsField, registryMaterialsField.getModifiers() & ~Modifier.FINAL);

            registryMaterialsField.set(null, instance);
        } catch (Exception e) {
            instance = null;

            throw new RuntimeException("Unable to override the old entity RegistryMaterials", e);
        }

        return instance;
    }

    public static void registerCustomEntity(int entityId, String entityName, Class<? extends Entity> entityClass) {
        getInstance().putCustomEntity(entityId, entityName, entityClass);
    }

    public void putCustomEntity(int entityId, String entityName, Class<? extends Entity> entityClass) {
        MinecraftKey minecraftKey = new MinecraftKey(entityName);

        this.customEntities.put(minecraftKey, entityClass);
        this.customEntityIds.put(entityClass, entityId);
    }

    @SuppressWarnings("unchecked")
    public Class<? extends Entity> get(Object key) {
        if (this.customEntities.containsKey(key)) {
            return this.customEntities.get(key);
        }

        return (Class<? extends Entity>) wrapped.get(key);
    }

    @SuppressWarnings("unchecked")
    public int a(Object key) { //TODO: Update name on version change (getId)
        if (this.customEntityIds.containsKey(key)) {
            return this.customEntityIds.get(key);
        }

        return (int) this.wrapped.a((Integer) key);
    }

    @SuppressWarnings("unchecked")
    public MinecraftKey b(Object value) { //TODO: Update name on version change (getKey)
        if (this.customEntityClasses.containsKey(value)) {
            return this.customEntityClasses.get(value);
        }

        return null;
    }

    public static void registerCustomEntity(String name, int id, Class<? extends Entity> customClass) {
        try {

            Field entityTypeField = EntityTypes.class.getDeclaredField("c");
            Field entityClassField = EntityTypes.class.getDeclaredField("d");
            Field entityIdField = EntityTypes.class.getDeclaredField("f");

            entityTypeField.setAccessible(true);
            entityClassField.setAccessible(true);
            entityIdField.setAccessible(true);

            Map<String, Class<? extends Entity>> nameToClassMap = (Map<String, Class<? extends Entity>>) entityTypeField.get(null);
            Map<Class<? extends Entity>, String> classToNameMap = (Map<Class<? extends Entity>, String>) entityClassField.get(null);
            Map<Integer, Class<? extends Entity>> idToClassMap = (Map<Integer, Class<? extends Entity>>) entityIdField.get(null);

            nameToClassMap.put(name, customClass);
            classToNameMap.put(customClass, name);
            idToClassMap.put(id, customClass);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
