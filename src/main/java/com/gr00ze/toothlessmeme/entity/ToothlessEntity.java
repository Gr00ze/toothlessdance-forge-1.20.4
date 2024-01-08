package com.gr00ze.toothlessmeme.entity;

import com.gr00ze.toothlessmeme.client.sounds.Sounds;
import net.minecraft.core.BlockPos;

import net.minecraft.server.level.ServerLevel;

import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.gr00ze.toothlessmeme.client.model.ToothlessModel.danceState;
import static com.gr00ze.toothlessmeme.client.model.ToothlessModel.eatingState;


public class ToothlessEntity extends Cat {

    private static final double
            MAX_HEALTH = 1,
            MOVEMENT_SPEED = 0.01,
            ATTACKDAMAGE = 0;

    private boolean isDancing = false;
    private int coolDownDance = -1;


    protected ToothlessEntity(EntityType<? extends Cat> type, Level level) {
        super(type, level);
    }


    public static AttributeSupplier getMobAttributes(){
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, MAX_HEALTH)
                .add(Attributes.MOVEMENT_SPEED, MOVEMENT_SPEED)
                .add(Attributes.ATTACK_DAMAGE, ATTACKDAMAGE)
                .build();
    }


    @Override
    public void tick() {
        super.tick();

        if (coolDownDance <= -1 && isDancing){
            coolDownDance = 20 * 20; //sec * tick for sec
        }
        coolDownDance--;

        if (coolDownDance == 0){
            isDancing = false;
            coolDownDance = -1;
        }
    }

    @Override
    public @NotNull InteractionResult mobInteract(@NotNull Player player, @NotNull InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (itemstack.isEmpty())
            player.startRiding(this);
        if (this.isFood(itemstack)){
            if  (isTame() ){
                System.out.println(danceState.isStarted());
                System.out.println(danceState.getAccumulatedTime());
                float volume = 1, pitch = 1;
                if (isBaby())
                    pitch = 1.2F;
                level().playSound(null, this, Sounds.DANCE.get(), this.getSoundSource(), volume, pitch);
                danceState.start(this.tickCount);


            }
            else if (!isTame()){
                float volume = 1, pitch = 1;
                if (isBaby())
                    pitch = 1.2F;
                level().playSound(null, this, Sounds.EATING.get(), this.getSoundSource(), volume, pitch);
                eatingState.start(this.tickCount);

            }
        }

        return super.mobInteract(player, hand);
    }


    @Nullable
    @Override
    public ToothlessEntity getBreedOffspring(@NotNull ServerLevel serverLevel, @NotNull AgeableMob p_148871_) {
        ToothlessEntity toothlessEntity = EntityInit.TOOTHLESS_ENTITY.get().create(serverLevel);
        return toothlessEntity;
    }

    public static boolean canSpawn(EntityType<ToothlessEntity> type, ServerLevelAccessor serverLevelAccessor, MobSpawnType spawnType, BlockPos blockPos, RandomSource randomSource) {
    return true;
    }
}
