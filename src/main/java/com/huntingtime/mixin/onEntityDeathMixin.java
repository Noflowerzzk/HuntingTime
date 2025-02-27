package com.huntingtime.mixin;

import com.huntingtime.GameController;
import com.huntingtime.depends.TeamItem;
import com.huntingtime.runtime.TaskDistributor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(LivingEntity.class)
public abstract class onEntityDeathMixin {
    @Shadow protected abstract void attackLivingEntity(LivingEntity target);

    @Shadow public abstract boolean teleport(double x, double y, double z, boolean particleEffects);

    @Inject(at = @At("HEAD"), method = "onDeath")
    private void onDeath(DamageSource source, CallbackInfo ci) {
        Entity attacker = source.getAttacker();
        LivingEntity _this = (LivingEntity) (Object) this;

        String type = _this.getType().toString();

//        System.out.println(type);

        type = type.substring(type.lastIndexOf('.') + 1);

//        assert type != null;

        if (attacker instanceof PlayerEntity playerEntity) {
            Team _team = attacker.getScoreboardTeam();
            if (_team == null) {
                return;
            }
            System.out.println(playerEntity.getName().getString() + " in Team " + _team.getDisplayName() + " kills a/an " + type);

            for (TeamItem team : TaskDistributor.teamItems) {
                if (team.getTeamName().getName().equals(_team.getName())) {
                    if (team.getItemList().subList(0, Math.min(GameController.getWindow(), team.getItemList().size())).contains(type)) {
                        team.getItemList().remove(type);
                        System.out.println("Removed team item " + type + " in team " + _team.getDisplayName());
                        Objects.requireNonNull(_this.getServer()).getPlayerManager().broadcast(
                                Text.literal("").append(_team.getDisplayName())
                                        .append(Text.literal(" 的 ").setStyle(Style.EMPTY.withColor(Formatting.WHITE)))
                                        .append(playerEntity.getDisplayName().copy().setStyle(_team.getDisplayName().getStyle()))
                                        .append(Text.literal(" 杀死了 " + TaskDistributor.getTranslation(type))).setStyle(Style.EMPTY.withColor(Formatting.WHITE)),
                                false
                        );

                        team.score ++;
                        TaskDistributor.showSideBar(Objects.requireNonNull(attacker.getServer()));

                        if (team.getItemList().isEmpty()) {
                            attacker.getServer().getPlayerManager().broadcast(
                                    Text.literal("游戏结束！\n").setStyle(Style.EMPTY.withColor(Formatting.GREEN))
                                            .append(team.getTeamName().getDisplayName())
                                            .append(Text.literal(" 获胜！").setStyle(Style.EMPTY.withColor(Formatting.RED))),
                                    false
                            );
                            GameController.stopGame();
                            return;
                        }
                    }
                }
            }
        }
    }
}
