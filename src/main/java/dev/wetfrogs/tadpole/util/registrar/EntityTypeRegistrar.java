package dev.wetfrogs.tadpole.util.registrar;


import net.minecraft.entity.EntityType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class EntityTypeRegistrar extends Registrar<RegistryKey<EntityType<?>>> {
    public EntityTypeRegistrar(String modid) {
        super(modid);
    }

    public RegistryKey<EntityType<?>> register(String name) {
        RegistryKey<EntityType<?>> key = RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(this.modid, name));
        this.put(name, key);
        return key;
    }
}
