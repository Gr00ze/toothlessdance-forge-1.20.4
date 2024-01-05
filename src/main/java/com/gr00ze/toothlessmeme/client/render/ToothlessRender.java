package com.gr00ze.toothlessmeme.client.render;

import com.gr00ze.toothlessmeme.client.model.ToothlessModel;
import com.gr00ze.toothlessmeme.entity.ToothlessEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.gr00ze.toothlessmeme.ToothlessMemeMod.MOD_ID;

public class ToothlessRender extends MobRenderer<ToothlessEntity, ToothlessModel> {

    private static final String TEXTURE_FILE_PATH = "textures/entities/toothless.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(MOD_ID,TEXTURE_FILE_PATH);

    public ToothlessRender(EntityRendererProvider.Context ctx) {
        super(ctx, new ToothlessModel(ctx.bakeLayer(ToothlessModel.MODEL_LAYER_LOCATION)), 0 );
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull ToothlessEntity toothlessEntity) {
        return TEXTURE;
    }
}
