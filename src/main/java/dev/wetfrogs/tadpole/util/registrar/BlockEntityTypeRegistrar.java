package dev.wetfrogs.tadpole.util.registrar;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class BlockEntityTypeRegistrar extends Registrar<BlockEntityType<?>> {
    public BlockEntityTypeRegistrar(String modid) {
        super(modid);
    }

    public <T extends BlockEntity> BlockEntityType<T> register(
            String name,
            FabricBlockEntityTypeBuilder.Factory<T> factory,
            Block... blocks
    ) {
        Identifier id = Identifier.of(modid, name);

        BlockEntityType<T> type = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                id,
                FabricBlockEntityTypeBuilder.create(factory, blocks).build()
        );

        put(name, type);
        return type;
    }
}
