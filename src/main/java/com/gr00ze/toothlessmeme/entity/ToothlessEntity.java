package com.gr00ze.toothlessmeme.entity;

import com.gr00ze.toothlessmeme.client.sounds.Sounds;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.gr00ze.toothlessmeme.ToothlessMemeMod.MOD_ID;


public class ToothlessEntity extends Cat {

    private static final int
            MAX_HEALTH = 1,
            MOVEMENT_SPEED = 1;

    private boolean isDancing = false;
    private int cooldownDance = -1;


    protected ToothlessEntity(EntityType<? extends Cat> p_21368_, Level p_21369_) {
        super(p_21368_, p_21369_);
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
        //this.playSound(Sounds.DANCE.get());
        //float volume = 1,pitch = 1F;
        //this.level().playLocalSound(this.getX(),this.getY(),this.getZ(), Sounds.DANCE.get(),this.getSoundSource(),volume,pitch,false);
    }

    @Override
    public @NotNull InteractionResult mobInteract(@NotNull Player p_28153_, @NotNull InteractionHand p_28154_) {
        ItemStack itemstack = p_28153_.getItemInHand(p_28154_);
        if (this.isFood(itemstack) && !isDancing){
            this.playSound(Sounds.DANCE.get());

            isDancing = true;
        }

        return super.mobInteract(p_28153_, p_28154_);
    }


    @Nullable
    @Override
    public ToothlessEntity getBreedOffspring(@NotNull ServerLevel serverLevel, @NotNull AgeableMob p_148871_) {
        ToothlessEntity toothlessEntity = EntityInit.TOOTHLESS_ENTITY.get().create(serverLevel);
        return toothlessEntity;
    }
}
