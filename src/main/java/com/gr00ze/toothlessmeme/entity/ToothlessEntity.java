package com.gr00ze.toothlessmeme.entity;

import com.gr00ze.toothlessmeme.client.sounds.Sounds;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;




public class ToothlessEntity extends Cat {

    private static final int
            MAX_HEALTH = 1,
            MOVEMENT_SPEED = 1;

    private boolean isDancing = false;
    private int cooldownDance = -1;


    protected ToothlessEntity(EntityType<? extends Cat> type, Level level) {
        super(type, level);
    }


    public static AttributeSupplier getMobAttributes(){
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, MAX_HEALTH)
                .add(Attributes.MOVEMENT_SPEED, MOVEMENT_SPEED)
                .build();
    }


    @Override
    public void tick() {
        super.tick();

        if (cooldownDance <= -1 && isDancing){
            cooldownDance = 20 * 20; //sec * tick for sec
        }
        cooldownDance--;

        if (cooldownDance == 0){
            isDancing = false;
            cooldownDance = -1;
        }
    }

    @Override
    public @NotNull InteractionResult mobInteract(@NotNull Player player, @NotNull InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (itemstack.isEmpty())
            player.startRiding(this);
        if (this.isFood(itemstack) && isTame() && !isDancing){
            if (!isBaby())

                level().playSound(null, this, Sounds.DANCE.get(), this.getSoundSource(), 1, 1);
            else
                this.playSound(Sounds.DANCE.get(),1,2);


            isDancing = true;
        }

        return super.mobInteract(player, hand);
    }


    @Nullable
    @Override
    public ToothlessEntity getBreedOffspring(@NotNull ServerLevel serverLevel, @NotNull AgeableMob p_148871_) {
        ToothlessEntity toothlessEntity = EntityInit.TOOTHLESS_ENTITY.get().create(serverLevel);
        return toothlessEntity;
    }

    public static boolean canSpawn(EntityType<ToothlessEntity> toothlessEntityEntityType, ServerLevelAccessor serverLevelAccessor, MobSpawnType mobSpawnType, BlockPos blockPos, RandomSource randomSource) {
    return true;
    }
}
