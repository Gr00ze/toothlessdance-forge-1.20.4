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
import net.minecraft.world.phys.Vec3;
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

    private boolean isDancing = false;
    private int coolDownDance = -1, coolDownMount = 0;


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
        if (this.isTame())
            getMountedIfPlayerWantIt();


    }

    private void getMountedIfPlayerWantIt() {
//        List<Entity> passengers = this.getPassengers();
//        System.out.println("HP "+ !passengers.isEmpty() + " "+ this.level().isClientSide());
//        if (!passengers.isEmpty()){
//            if (passengers.get(0) instanceof Player player && player.isShiftKeyDown()){
//                player.stopRiding();
//                //coolDownMount = 2 * 20;
//            }
//        }
        //if (coolDownMount > 0)return;
        List<Entity> list = this.level().getEntities(this, this.getBoundingBox().inflate(0,-2,0).move(0, 2, 0), EntitySelector.notRiding(this));
        if (list.isEmpty() || this.level().isClientSide()) return;
        for (Entity entity : list) {
            if (entity instanceof Player player && !player.isShiftKeyDown() )  {
                    player.startRiding(this);
                    break;

                //System.out.println(!player.isShiftKeyDown());



            }


        }

    }

    @Override
    public @NotNull Vec3 getDismountLocationForPassenger(@NotNull LivingEntity livingEntity) {
        return new Vec3(this.getX()+3, this.getY(), this.getZ());
    }

    @Override
    public boolean canRiderInteract() {
        return true;
    }

    @Override
    public @NotNull InteractionResult mobInteract(@NotNull Player player, @NotNull InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (itemstack.isEmpty() && isTame() && !isDancing) {
            float volume = 1, pitch = 1;
            if (isBaby())
                pitch = 1.2F;
            level().playSound(null, this, Sounds.DANCE.get(), this.getSoundSource(), volume, pitch);
            if (this.level().isClientSide()){
                danceState.start(this.tickCount);
            }
            isDancing = true;
        }
        if (this.isFood(itemstack) && !isTame() ){
            float volume = 1, pitch = 1;
            if (isBaby())
                pitch = 1.2F;
            level().playSound(null, this, Sounds.EATING.get(), this.getSoundSource(), volume, pitch);
            if (this.level().isClientSide()){
                eatingState.start(this.tickCount);
            }
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
