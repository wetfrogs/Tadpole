package dev.rainfrogs.tadpole.mixin.client.render;

import dev.rainfrogs.tadpole.client.screenshake.ScreenshakeHandler;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Camera.class)
public class CameraMixin {

    @Inject(method = "update", at = @At("RETURN"))
    private void playground$screenshake(BlockView area, Entity focusedEntity, boolean thirdPerson, boolean inverseView, float tickDelta, CallbackInfo ci) {
        ScreenshakeHandler.tick(tickDelta);

        if (!ScreenshakeHandler.isShaking()) {
            return;
        }

        Camera cam = (Camera) (Object) this;

        float yawOffset = ScreenshakeHandler.getYawOffset();
        float pitchOffset = ScreenshakeHandler.getPitchOffset();
        cam.setRotation(cam.getYaw() + yawOffset, cam.getPitch() + pitchOffset);

        Vec3d basePos = cam.getPos();
        Vec3d shakeOff = ScreenshakeHandler.getPosOffset();
        Vec3d oscOff   = ScreenshakeHandler.getOscillationOffset();
        cam.setPos(basePos.add(shakeOff).add(oscOff));
    }
}
