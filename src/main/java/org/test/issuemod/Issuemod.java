package org.test.issuemod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.test.issuemod.effect.FabricationEffect;

import static org.test.issuemod.command.Commands.printEffects;

public class Issuemod implements ModInitializer {
    /**
     * Runs the mod initializer.
     */

    public static final String MOD_ID = "issuemod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
                    printEffects(dispatcher);
                }
        );
        Registry.register(Registries.STATUS_EFFECT, new Identifier(MOD_ID, "fabrication"), new FabricationEffect(StatusEffectCategory.HARMFUL, 0xffffff));
        LOGGER.info("issuemod loaded successfully!");
    }
}
