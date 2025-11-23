package dev.wetfrogs.tadpole.util.registrar;

import net.minecraft.particle.ParticleType;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ParticleTypeRegistrar extends Registrar<SimpleParticleType> {
    public ParticleTypeRegistrar(String modid) {
        super(modid);
    }

    public SimpleParticleType register(String name, SimpleParticleType particleType) {
        SimpleParticleType particle = Registry.register(Registries.PARTICLE_TYPE, Identifier.of(this.modid, name), particleType);
        this.put(name, particle);
        return particle;
    }
}
