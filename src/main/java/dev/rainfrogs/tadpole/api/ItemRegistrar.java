package dev.rainfrogs.tadpole.api;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ItemRegistrar {
    private static String MOD_ID;

    public ItemRegistrar(String MOD_ID) {
        ItemRegistrar.MOD_ID = MOD_ID;
    }

    public Item add(String name, Function<Item.Settings, Item> function) {
        Identifier id = Identifier.of(MOD_ID, name);

        return Registry.register(Registries.ITEM, id,
                function.apply(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, id))));
    }
}
