package org.test.issuemod.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import static net.minecraft.server.command.CommandManager.literal;
import static org.test.issuemod.Issuemod.MOD_ID;

public class Commands {
    public static void printEffects(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal(MOD_ID)
                .then(
                        literal("printEffects")
                                .executes(ctx -> {
                                    ServerPlayerEntity player = ctx.getSource().getPlayer();
                                    if (player != null) {
                                        ctx.getSource().sendFeedback(() -> Text.literal("Your Effects:"), false);
                                        for (StatusEffectInstance instance : player.getStatusEffects()) {
                                            String name = instance.getEffectType().getName().getString();
                                            int amplifier = instance.getAmplifier();
                                            String out = "%s: %d".formatted(name, amplifier);
                                            ctx.getSource().sendFeedback(() -> Text.literal(out), false);
                                        }
                                    } else {
                                        ctx.getSource().sendFeedback(() -> Text.literal("You are not a player"), false);
                                    }
                                    return Command.SINGLE_SUCCESS;
                                })
                )
        );
    }


    public static void registerCommands() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
                    printEffects(dispatcher);
                }
        );
    }
}
