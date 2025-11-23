package dev.wetfrogs.tadpole.util.registrar;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ItemRegistrar extends Registrar<Item> {
    public ItemRegistrar(String modid) {
        super(modid);
    }

    public Item register(String name, Function<Item.Settings, Item> factory) {
        Identifier id = Identifier.of(modid, name);
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id);

        Item item = Registry.register(Registries.ITEM, id,
                factory.apply(new Item.Settings().registryKey(key)));

        put(name, item);

        return item;
    }
}
