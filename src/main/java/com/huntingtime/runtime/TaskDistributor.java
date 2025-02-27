package com.huntingtime.runtime;

import com.huntingtime.GameController;
import com.huntingtime.depends.TeamItem;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.server.MinecraftServer;
import net.minecraft.scoreboard.Team;

import java.util.*;

public class TaskDistributor {
    public static ArrayList<TeamItem> teamItems = new ArrayList<>();

    private static final ArrayList<String> itemList = new ArrayList<>(Arrays.asList(
            "allay",
            "axolotl",
            "bat",
            "bee",
            "blaze",
            "camel",
            "cat",
            "cave_spider",
            "chicken",
            "cod",
            "cow",
            "creeper",
            "dolphin",
            "donkey",
            "drowned",
            "elder_guardian",
            "ender_dragon",
            "enderman",
            "endermite",
            "evoker",
            "fox",
            "frog",
            "ghast",
            "glow_squid",
            "goat",
            "guardian",
            "hoglin",
            "horse",
            "husk",
            "iron_golem",
            "llama",
            "magma_cube",
            "mooshroom",
            "mule",
            "ocelot",
            "panda",
            "parrot",
            "phantom",
            "pig",
            "piglin",
            "piglin_brute",
            "pillager",
            "polar_bear",
            "pufferfish",
            "rabbit",
            "ravager",
            "salmon",
            "sheep",
            "shulker",
            "silverfish",
            "skeleton",
            "skeleton_horse",
            "slime",
            "sniffer",
            "snow_golem",
            "spider",
            "squid",
            "stray",
            "strider",
            "tadpole",
            "trader_llama",
            "tropical_fish",
            "turtle",
            "vex",
            "villager",
            "vindicator",
            "wandering_trader",
            "warden",
            "witch",
            "wither",
            "wither_skeleton",
            "wolf",
            "zoglin",
            "zombie",
//            "zombie_horse",
            "zombie_villager",
            "zombified_piglin",
            "player"
    ));

    private static final Map<String, String> entityTranslations = new LinkedHashMap<>() {{
        put("allay", "悦灵");
        put("axolotl", "美西螈");
        put("bat", "蝙蝠");
        put("bee", "蜜蜂");
        put("blaze", "烈焰人");
        put("camel", "骆驼");
        put("cat", "猫");
        put("cave_spider", "洞穴蜘蛛");
        put("chicken", "鸡");
        put("cod", "鳕鱼");
        put("cow", "牛");
        put("creeper", "苦力怕");
        put("dolphin", "海豚");
        put("donkey", "驴");
        put("drowned", "溺尸");
        put("elder_guardian", "远古守卫者");
        put("ender_dragon", "末影龙");
        put("enderman", "末影人");
        put("endermite", "末影螨");
        put("evoker", "唤魔者");
        put("fox", "狐狸");
        put("frog", "青蛙");
        put("ghast", "恶魂");
        put("glow_squid", "发光鱿鱼");
        put("goat", "山羊");
        put("guardian", "守卫者");
        put("hoglin", "疣猪兽");
        put("horse", "马");
        put("husk", "尸壳");
        put("iron_golem", "铁傀儡");
        put("llama", "羊驼");
        put("magma_cube", "岩浆怪");
        put("mooshroom", "蘑菇牛");
        put("mule", "骡");
        put("ocelot", "豹猫");
        put("panda", "熊猫");
        put("parrot", "鹦鹉");
        put("phantom", "幻翼");
        put("pig", "猪");
        put("piglin", "猪灵");
        put("piglin_brute", "猪灵蛮兵");
        put("pillager", "掠夺者");
        put("polar_bear", "北极熊");
        put("pufferfish", "河豚");
        put("rabbit", "兔子");
        put("ravager", "劫掠兽");
        put("salmon", "鲑鱼");
        put("sheep", "绵羊");
        put("shulker", "潜影贝");
        put("silverfish", "蠹虫");
        put("skeleton", "骷髅");
        put("skeleton_horse", "骷髅马");
        put("slime", "史莱姆");
        put("sniffer", "嗅探兽");
        put("snow_golem", "雪傀儡");
        put("spider", "蜘蛛");
        put("squid", "鱿鱼");
        put("stray", "流浪者");
        put("strider", "炽足兽");
        put("tadpole", "蝌蚪");
        put("trader_llama", "行商羊驼");
        put("tropical_fish", "热带鱼");
        put("turtle", "海龟");
        put("vex", "恼鬼");
        put("villager", "村民");
        put("vindicator", "卫道士");
        put("wandering_trader", "流浪商人");
        put("warden", "监守者");
        put("witch", "女巫");
        put("wither", "凋灵");
        put("wither_skeleton", "凋灵骷髅");
        put("wolf", "狼");
        put("zoglin", "僵尸疣猪兽");
        put("zombie", "僵尸");
//        put("zombie_horse", "僵尸马");
        put("zombie_villager", "僵尸村民");
        put("zombified_piglin", "僵尸猪灵");
        put("player", "玩家");
    }};

    private static int Target = 10;

    public static void initialize() {
        teamItems.clear();

        System.out.println(itemList);
    }

    public static void generateItems(int target, MinecraftServer minecraftServer) {
        setTarget(target);

        System.out.println("Target: " + target);

        for (Team team : minecraftServer.getScoreboard().getTeams()) {
//            System.out.println(123);
            setTeamItems(team);
            System.out.println(team.getDisplayName().getString());
        }

        showSideBar(minecraftServer);
    }

    private static void setTarget(int target) {
        Target = target;
    }

    private static void setTeamItems(Team player) {
        Random random = new Random();

        ArrayList<String> items = new ArrayList<>();

        for (int i = 0; i < Target; i ++) {
            String item = itemList.get(random.nextInt(itemList.size()));
//            while (itemList.contains(item)) { item = itemList.get(random.nextInt(itemList.size())); }
            items.add(item);
//            System.out.println(item);
        }

        System.out.println("List for team " + player.getDisplayName().getString() + " generated: \n" + items);

        teamItems.add(new TeamItem(player, items));
    }

    public static void showSideBar(MinecraftServer minecraftServer) {
        // 获取所有计分项
        Collection<ScoreboardObjective> objectives = minecraftServer.getScoreboard().getObjectives();

        // 删除计分项
        for (ScoreboardObjective objective : objectives) {
            String command = "scoreboard objectives remove " + objective.getName();
            minecraftServer.getCommandManager().execute(minecraftServer.getCommandManager().getDispatcher().parse(command, minecraftServer.getCommandSource()),
                    command
            );
//            System.out.println(command + "Succeeded! Name: " + objective.getName());
        }

        // 建立积分 计分项
        String command = "scoreboard objectives add temp_rank dummy \"任务\"";
        minecraftServer.getCommandManager().execute(minecraftServer.getCommandManager().getDispatcher().parse(command, minecraftServer.getCommandSource()),
                command
        );

        command = "scoreboard objectives setdisplay sidebar temp_rank";
        minecraftServer.getCommandManager().execute(minecraftServer.getCommandManager().getDispatcher().parse(command, minecraftServer.getCommandSource()),
                command
        );

        command = "scoreboard objectives modify temp_rank numberformat blank";
        minecraftServer.getCommandManager().execute(minecraftServer.getCommandManager().getDispatcher().parse(command, minecraftServer.getCommandSource()),
                command
        );

        updateSidebar(minecraftServer);
    }

    public static void updateSidebar(MinecraftServer minecraftServer) {
        int i = 10000;
        Collection<Team> teams = minecraftServer.getScoreboard().getTeams();
        for (TeamItem teamItem : teamItems) {
//            System.out.println(teamItem.getTeamName().getDisplayName().toString());
//            Matcher matcher = pattern.matcher(teamItem.getTeamName().getDisplayName().toString());
////            assert matcher.find();
//            System.out.println(matcher.group(1));
//            String command = "team join " + teamItem.getTeamName().getName() + " " + teamItem.getTeamName().getDisplayName().getString();
//            minecraftServer.getCommandManager().execute(minecraftServer.getCommandManager().getDispatcher().parse(command, minecraftServer.getCommandSource()),
//                    command
//            );
//            command = "scoreboard players set " + teamItem.getTeamName().getDisplayName().getString() + " temp_rank " + i --;
////            System.out.println(command);
//            minecraftServer.getCommandManager().execute(minecraftServer.getCommandManager().getDispatcher().parse(command, minecraftServer.getCommandSource()),
//                    command
//            );

            String command = "team join " + teamItem.getTeamName().getName() + " " + teamItem.getTeamName().getDisplayName().getString() + "，当前分数：" + teamItem.score;
            minecraftServer.getCommandManager().execute(minecraftServer.getCommandManager().getDispatcher().parse(command, minecraftServer.getCommandSource()),
                    command
            );

            command = "scoreboard players set " + teamItem.getTeamName().getDisplayName().getString() + "，当前分数：" + teamItem.score + " temp_rank " + i --;
            minecraftServer.getCommandManager().execute(minecraftServer.getCommandManager().getDispatcher().parse(command, minecraftServer.getCommandSource()),
                    command
            );

            for (String task : teamItem.getItemList().subList(0, Math.min(GameController.getWindow(),  teamItem.getItemList().size()))) {
                command =  "scoreboard players set " + (10000 - i) + entityTranslations.get(task) + " temp_rank " + i --;
                minecraftServer.getCommandManager().execute(minecraftServer.getCommandManager().getDispatcher().parse(command, minecraftServer.getCommandSource()),
                        command
                );
            }
        }
    }

    public static String getTranslation(String key) {
        return entityTranslations.get(key);
    }
}
