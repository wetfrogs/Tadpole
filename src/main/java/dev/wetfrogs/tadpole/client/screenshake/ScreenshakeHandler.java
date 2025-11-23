package dev.wetfrogs.tadpole.client.screenshake;

import net.minecraft.util.math.Vec3d;

public class ScreenshakeHandler {
    public static float trauma = 0f;
    public static float decay = 1.5f;
    public static float maxAngle = 2f;
    public static double maxOffset = 0.05;
    public static double maxOscAmplitude = 0.02;
    public static double freqX = 2.0;
    public static double freqY = 3.0;
    public static double freqZ = 2.5;

    private static double time = 0.0;

    public static void tick(double partialTicks) {
        if (trauma > 0f) {
            trauma -= (float) (decay * (partialTicks / 20.0));
            if (trauma < 0f) trauma = 0f;
        }
        time += partialTicks;
    }

    private static double hash(double x) {
        double v = Math.sin(x * 12.9898 + time * 4.12) * 43758.5453;
        return v - Math.floor(v);
    }

    private static double shake() {
        return trauma * trauma;
    }

    public static float getYawOffset() {
        double n = hash(1.0);
        return (float)((n * 2.0 - 1.0) * maxAngle * shake());
    }

    public static float getPitchOffset() {
        double n = hash(2.0);
        return (float)((n * 2.0 - 1.0) * maxAngle * shake());
    }

    public static Vec3d getPosOffset() {
        double s = shake();
        double ox = (hash(3.0) * 2.0 - 1.0) * maxOffset * s;
        double oy = (hash(4.0) * 2.0 - 1.0) * maxOffset * s;
        double oz = (hash(5.0) * 2.0 - 1.0) * maxOffset * s;
        return new Vec3d(ox, oy, oz);
    }

    public static Vec3d getOscillationOffset() {
        double s = shake();
        double t = time;

        double x = Math.sin(t * freqX) * maxOscAmplitude * s;
        double y = Math.sin(t * freqY) * maxOscAmplitude * s;
        double z = Math.cos(t * freqZ) * maxOscAmplitude * s;

        return new Vec3d(x, y, z);
    }

    public static boolean isShaking() {
        return trauma > 0.001f;
    }
}
