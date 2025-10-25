package de.jonitown.grafik;

import de.jonitown.Game;

public class Render3D extends Render {
    public double[] zBuffer;
    public double renderDistance = 5000;

    public Render3D(int width, int height) {

        super(width, height);
        zBuffer = new double[width * height];
    }



    public void floor(Game game) {
        double floorPosition = 10.0;
        double ceilingPosition = 1000.0;
        double forward = game.controller.z;
        double right = game.controller.x;

        double rotation =game.controller.rotation;
        double cos = Math.cos(rotation);
        double sin = Math.sin(rotation);

        for (int y = 0; y < height; y++) {
            double ceilling =(y - height / 2.0) / height;

            double z = floorPosition/ ceilling;

            if (ceilling < 0) {
                z  = ceilingPosition /-ceilling;
            }




            for (int x = 0; x < width; x++) {
                double Depth = (x - width/2.0) / height;
                Depth *= z;
                double xx =Depth * cos +z*sin;
                double yy =z * cos - Depth * sin;
                int xPix =(int) (xx + right);
                int yPix =(int) (yy + forward);
                zBuffer[x+y*width] = z;
                if (z < 300) {
                    pixels[x + y * width] = ((xPix&15)*16) | ((yPix&15)*16) << 8;
                }




            }
        }
    }
    public void renderDistanceLimiter() {
        for (int i = 0; i < width*height; i++) {
            int color = pixels[i];
            int brightness = (int) (renderDistance/ (zBuffer[i]));

            if(brightness < 0 ) {
                brightness = 0;
            }
            if(brightness > 255) {
                brightness = 255;
            }
            int r = (color >> 16) & 0xFF;
            int g = (color >> 8) & 0xFF;
            int b = (color) & 0xFF;

            r = r*brightness/255;
            g = g*brightness/255;
            b = b*brightness/255;

            pixels[i] = r << 16 | g << 8 | b;
        }
    }
}