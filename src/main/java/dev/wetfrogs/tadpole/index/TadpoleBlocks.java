package dev.wetfrogs.tadpole.index;

import dev.wetfrogs.tadpole.Tadpole;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;

public interface TadpoleBlocks {
    Map<Block, Identifier> BLOCKS = new LinkedHashMap<>();

    static <T extends Block> T create(String name, T block) {
        BLOCKS.put(block, Tadpole.id(name));
        return block;
    }

    static <T extends Block> T createWithItem(String name, T block) {
        create(name, block);
        Identifier id = Tadpole.id(name);
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id);

        Item item = new BlockItem(
                block,
                new Item.Settings()
                        .useBlockPrefixedTranslationKey()
                        .registryKey(key)
        );

        Registry.register(Registries.ITEM, id, item);
        return block;
    }

    static void initialize() {
        BLOCKS.forEach((block, id) -> Registry.register(Registries.BLOCK, id, block));
    }
}
