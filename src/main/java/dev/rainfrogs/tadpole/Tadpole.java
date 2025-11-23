package dev.rainfrogs.tadpole;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Tadpole implements ModInitializer {
	public static final String MOD_ID = "playground";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, access, environment) -> {
        });
	}

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }
}