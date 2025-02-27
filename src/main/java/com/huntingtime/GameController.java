package com.huntingtime;

import com.huntingtime.depends.TeamItem;

import com.huntingtime.runtime.TaskDistributor;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registries;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class GameController {

    private static int Target = 10;
    private static int Window = 3;
    private static boolean isStarted = false;

    public static int startGame(CommandContext<ServerCommandSource> context) {
        isStarted = true;
        context.getSource().getServer().getPlayerManager().broadcast(Text.literal("游戏开始！"), false);

        TaskDistributor.initialize();
        TaskDistributor.generateItems(Target, context.getSource().getServer());

        return 0;
    }

    public static int setNum(CommandContext<ServerCommandSource> context) {
        if (isStarted) {
            context.getSource().getServer().getPlayerManager().broadcast(Text.literal("游戏已开始，无法设置！"), false);
            return 1;
        }
        int arg = IntegerArgumentType.getInteger(context, "number");
        Target = arg;
        context.getSource().getServer().getPlayerManager().broadcast(Text.literal("目标数量已被设置为 " + arg), false);
        return 0;
    }

    public static int getNum(CommandContext<ServerCommandSource> context) {
        context.getSource().getServer().getPlayerManager().broadcast(Text.literal("目标数量为 " + Target), false);
        return 0;
    }

    public static void stopGame() {
        isStarted = false;
    }

    public static int setWindow(CommandContext<ServerCommandSource> context) {
        if (isStarted) {
            context.getSource().getServer().getPlayerManager().broadcast(Text.literal("游戏已开始，无法设置！"), false);
            return 1;
        }
        int arg = IntegerArgumentType.getInteger(context, "number");
        Window = arg;
        context.getSource().getServer().getPlayerManager().broadcast(Text.literal("每次展示数量已被设置为 " + arg), false);
        return 0;
    }

    public static int getWindow(CommandContext<ServerCommandSource> context) {
        context.getSource().getServer().getPlayerManager().broadcast(Text.literal("每次展示数量为 " + Target), false);
        return 0;
    }

    public static int getWindow() {
        return Window;
    }
}
