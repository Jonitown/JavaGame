package de.jonitown.grafik;

import de.jonitown.Game;

public class Render3D extends Render {

    public Render3D(int width, int height) {
        super(width, height);
    }



    public void floor(Game game) {
        double floorPosition = 10.0;
        double ceilingPosition = 100.0;
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
                pixels[x + y * width] = ((xPix&15)*16) | ((yPix&15)*16) << 8;

            }
        }
    }
}
