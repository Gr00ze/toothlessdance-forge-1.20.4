package com.gr00ze.toothlessmeme.client.animations;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;
import org.joml.Vector3f;


public class Animations {
    public static final AnimationDefinition TOOTHLESS_DANCE = AnimationDefinition.Builder.withLength(18)
            .addAnimation("root", new AnimationChannel(
                    AnimationChannel.Targets.ROTATION,
                    new Keyframe(0,KeyframeAnimations.degreeVec(0F,0F,0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(6*3,KeyframeAnimations.degreeVec(0F,6*360F,0F), AnimationChannel.Interpolations.LINEAR)))
            .build();
    public static final AnimationDefinition TOOTHLESS_EATING = AnimationDefinition.Builder.withLength(3).build();

}
