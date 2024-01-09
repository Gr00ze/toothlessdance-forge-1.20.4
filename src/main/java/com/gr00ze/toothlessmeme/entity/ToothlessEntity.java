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

import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.gr00ze.toothlessmeme.client.model.ToothlessModel.danceState;
import static com.gr00ze.toothlessmeme.client.model.ToothlessModel.eatingState;


public class ToothlessEntity extends Cat {

    private static final double
            MAX_HEALTH = 1,
            MOVEMENT_SPEED = 0.01,
            ATTACKDAMAGE = 0;

    private Boolean isDancing = false;
    private Integer coolDownDance = -1;


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
        handleCoolSown(this.coolDownDance,isDancing, 20);


        if (this.isTame())
            getMountedIfPlayerWantIt();
    }

    private static void handleCoolSown(Integer coolDownDance, Boolean isDancing, Integer soundDuration) {
        if (coolDownDance <= -1 && isDancing){
            coolDownDance = soundDuration * 20; //sec * tick for sec
        }
        coolDownDance--;

        if (coolDownDance == 0){
            isDancing = false;
            coolDownDance = -1;
        }
    }

    private void getMountedIfPlayerWantIt() {
        List<Entity> list = this.level().getEntities(this, this.getBoundingBox().inflate(0,-2,0).move(0, 2, 0), EntitySelector.notRiding(this));
        if (!list.isEmpty()) {
            for (Entity entity : list) {
                System.out.println(entity+ " sopra toothless");
                if (entity instanceof Player player && !player.isShiftKeyDown() ) {
                    player.startRiding(this);
                    break;
                }

            }
        }
    }

    @Override
    public @NotNull InteractionResult mobInteract(@NotNull Player player, @NotNull InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (itemstack.isEmpty() && isTame() && !isDancing) {
            float volume = 1, pitch = 1;
            if (isBaby())
                pitch = 1.2F;
            level().playSound(null, this, Sounds.DANCE.get(), this.getSoundSource(), volume, pitch);
            danceState.start(this.tickCount);
            isDancing = true;
        }
        if (this.isFood(itemstack) && !isTame() ){
                float volume = 1, pitch = 1;
                if (isBaby())
                    pitch = 1.2F;
                level().playSound(null, this, Sounds.EATING.get(), this.getSoundSource(), volume, pitch);
                eatingState.start(this.tickCount);
        }

        return super.mobInteract(player, hand);
    }


    @Nullable
    @Override
    public ToothlessEntity getBreedOffspring(@NotNull ServerLevel serverLevel, @NotNull AgeableMob p_148871_) {
        return EntityInit.TOOTHLESS_ENTITY.get().create(serverLevel);
    }

    public static boolean canSpawn(EntityType<ToothlessEntity> type, ServerLevelAccessor serverLevelAccessor, MobSpawnType spawnType, BlockPos blockPos, RandomSource randomSource) {
        Player player = serverLevelAccessor.getLevel().getNearestPlayer(TargetingConditions.forNonCombat(),blockPos.getX(),blockPos.getY(),blockPos.getZ());

        if (player == null) {
            //System.out.println("Player nullo");
            return false;
        }
        //System.out.println("Player non nullo");
        ItemStack itemStack = player.getMainHandItem();
        //System.out.println("Pesce in mano?: "+ (itemStack.is(Items.COD) || itemStack.is(Items.SALMON)));
        return itemStack.is(Items.COD) || itemStack.is(Items.SALMON);
    }
}
