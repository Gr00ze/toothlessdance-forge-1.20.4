package com.gr00ze.toothlessmeme.client.model;

import com.gr00ze.toothlessmeme.client.animations.Animations;
import com.gr00ze.toothlessmeme.entity.ToothlessEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.AnimationState;
import org.jetbrains.annotations.NotNull;

import static com.gr00ze.toothlessmeme.client.render.ToothlessRender.TEXTURE;

public class ToothlessModel extends HierarchicalModel<ToothlessEntity> {

    public static final ModelLayerLocation MODEL_LAYER_LOCATION = new ModelLayerLocation(TEXTURE,"main");
    private final ModelPart root;

    public static AnimationState danceState,eatingState;

    public ToothlessModel(ModelPart root){
        this.root = root;

        danceState = new AnimationState();
        eatingState = new AnimationState();
    }

    @Override
    public @NotNull ModelPart root() {
        return root;
    }

    public static LayerDefinition getParts(){
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition cosciadx = root.addOrReplaceChild("cosciadx", CubeListBuilder.create().texOffs(50, 61).addBox(-3.0F, -7.0F, -1.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -5.0F));

        PartDefinition gambadx = cosciadx.addOrReplaceChild("gambadx", CubeListBuilder.create().texOffs(64, 0).addBox(-3.0F, -3.0F, -1.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cosciasx = root.addOrReplaceChild("cosciasx", CubeListBuilder.create().texOffs(18, 57).addBox(-3.0F, -7.0F, 7.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -5.0F));

        PartDefinition gambasx = cosciasx.addOrReplaceChild("gambasx", CubeListBuilder.create().texOffs(58, 13).addBox(-3.0F, -3.0F, 7.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition Corpo = root.addOrReplaceChild("Corpo", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -39.0F, -4.0F, 8.0F, 32.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 40).addBox(-8.0F, -37.0F, -1.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(32, 18).addBox(-8.0F, -32.0F, -1.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(32, 13).addBox(-8.0F, -27.0F, -1.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

        PartDefinition Testa = root.addOrReplaceChild("Testa", CubeListBuilder.create().texOffs(32, 0).addBox(16.0F, -15.0F, -5.0F, 11.0F, 3.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(22, 30).addBox(16.0F, -12.0F, -5.0F, 12.0F, 5.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(32, 13).addBox(28.0F, -12.0F, -5.0F, 8.0F, 3.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(10, 40).addBox(13.0F, -15.0F, -5.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(38, 27).addBox(13.0F, -15.0F, 3.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(32, 8).addBox(14.0F, -15.0F, 1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(14.0F, -15.0F, -2.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-22.0F, -33.0F, 0.0F));

        PartDefinition Occhi = Testa.addOrReplaceChild("Occhi", CubeListBuilder.create().texOffs(26, 45).addBox(5.0F, -48.0F, -4.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(24, 2).addBox(6.0F, -47.0F, -3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(24, 0).addBox(6.0F, -47.0F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 45).addBox(5.0F, -48.0F, 1.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(22.0F, 33.0F, 0.0F));

        PartDefinition mandibola = Testa.addOrReplaceChild("mandibola", CubeListBuilder.create(), PartPose.offset(28.0F, -7.0F, 0.0F));

        PartDefinition lingua_r1 = mandibola.addOrReplaceChild("lingua_r1", CubeListBuilder.create().texOffs(-6, -6).addBox(1.0F, -1.5F, -4.0F, 6.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.9F, 0.0F, 0.0F, 0.0F, 0.2182F));

        PartDefinition mandibola_r1 = mandibola.addOrReplaceChild("mandibola_r1", CubeListBuilder.create().texOffs(0, 45).addBox(0.0F, -2.0F, -5.0F, 8.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2182F));

        PartDefinition bracciosx2 = root.addOrReplaceChild("bracciosx2", CubeListBuilder.create().texOffs(24, 0).addBox(18.0F, -1.0F, 4.0F, 4.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-21.0F, -33.0F, 0.0F));

        PartDefinition avambracciosx = bracciosx2.addOrReplaceChild("avambracciosx", CubeListBuilder.create(), PartPose.offset(20.0F, 3.5F, -6.5F));

        PartDefinition avambraccio_r1 = avambracciosx.addOrReplaceChild("avambraccio_r1", CubeListBuilder.create().texOffs(50, 52).addBox(-1.0F, 15.0F, -14.5F, 4.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -4.5F, -3.0F, -1.0036F, 3.1416F, 0.0F));

        PartDefinition Bracciodx = root.addOrReplaceChild("Bracciodx", CubeListBuilder.create().texOffs(0, 57).addBox(18.0F, -1.0F, -9.0F, 4.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-21.0F, -33.0F, 0.0F));

        PartDefinition avambracciodx = Bracciodx.addOrReplaceChild("avambracciodx", CubeListBuilder.create(), PartPose.offset(20.0F, 3.5F, -6.5F));

        PartDefinition avambracciodx_r1 = avambracciodx.addOrReplaceChild("avambracciodx_r1", CubeListBuilder.create().texOffs(56, 26).addBox(-3.0F, -1.0F, -4.5F, 4.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -4.5F, -3.0F, -0.9599F, 0.0F, 0.0F));

        PartDefinition Coda = root.addOrReplaceChild("Coda", CubeListBuilder.create().texOffs(50, 45).addBox(2.0F, 22.0F, -2.0F, 14.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-21.0F, -33.0F, 0.0F));

        PartDefinition Coda2 = Coda.addOrReplaceChild("Coda2", CubeListBuilder.create(), PartPose.offset(16.0F, 24.0F, 0.0F));

        PartDefinition ala2_r1 = Coda2.addOrReplaceChild("ala2_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, 3.0F, -6.0F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-5.0F, 2.0F, -5.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(32, 22).addBox(-5.0F, -1.0F, -6.0F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.0F, -24.0F, 0.0F, 0.0F, 0.0F, -0.3054F));

        PartDefinition coda2_r1 = Coda2.addOrReplaceChild("coda2_r1", CubeListBuilder.create().texOffs(36, 45).addBox(-1.5F, -23.5F, -2.0F, 3.0F, 26.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-14.5F, -2.5F, 0.0F, 0.0F, 0.0F, -0.3054F));

        //PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(-15, -15).addBox(-8.0F, -48.0F, 14.0F, 16.0F, 48.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }
    @Override
    public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
    @Override
    public void setupAnim(@NotNull ToothlessEntity toothlessEntity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.animate(danceState, Animations.TOOTHLESS_DANCE,ageInTicks);
        this.animate(eatingState, Animations.TOOTHLESS_EATING,ageInTicks);
    }
}
