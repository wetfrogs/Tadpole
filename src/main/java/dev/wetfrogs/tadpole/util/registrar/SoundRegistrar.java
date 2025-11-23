package dev.wetfrogs.tadpole.util.registrar;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class SoundRegistrar extends Registrar<SoundEvent> {
    public SoundRegistrar(String modid) {
        super(modid);
    }

    public SoundEvent register(String name) {
        Identifier id = Identifier.of(this.modid, name);
        SoundEvent sound = Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
        this.put(name, sound);
        return sound;
    }
}
