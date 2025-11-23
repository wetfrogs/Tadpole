package dev.wetfrogs.tadpole.util.registrar;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class BlockRegistrar extends Registrar<Block> {

    public BlockRegistrar(String modid) {
        super(modid);
    }

    public Block register(String name, Function<AbstractBlock.Settings, Block> factory) {
        return register(name, factory, true);
    }

    public Block registerNoItem(String name, Function<AbstractBlock.Settings, Block> factory) {
        return register(name, factory, false);
    }

    private Block register(String name, Function<AbstractBlock.Settings, Block> factory, boolean withItem) {
        Identifier id = id(name);
        RegistryKey<Block> key = blockKey(name);

        Block block = factory.apply(AbstractBlock.Settings.create().registryKey(key));

        Block registeredBlock = Registry.register(Registries.BLOCK, id, block);

        if (withItem) registerBlockItem(name, block);

        put(name, registeredBlock);
        return registeredBlock;
    }

    private void registerBlockItem(String name, Block block) {
        Identifier id = id(name);
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id);

        Item item = new BlockItem(
                block,
                new Item.Settings()
                        .useBlockPrefixedTranslationKey()
                        .registryKey(key)
        );

        Registry.register(Registries.ITEM, id, item);
    }

    private Identifier id(String name) {
        return Identifier.of(modid, name);
    }

    private RegistryKey<Block> blockKey(String name) {
        return RegistryKey.of(RegistryKeys.BLOCK, id(name));
    }
}
