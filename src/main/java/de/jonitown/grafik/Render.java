package de.jonitown.grafik;

public class Render {
    public final int width;
    public final int height;
    public final int[] pixels;

    public Render(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
    }
    public void draw(Render render, int xOffset, int yOffset) {
        for (int y = 0; y < render.height; y++) {
            int yPixel = y + yOffset;
            if (yPixel < 0 || yPixel >= this.height) continue;
            for (int x = 0; x < render.width; x++) {
                int xPixel = x + xOffset;
                if (xPixel < 0 || xPixel >= this.width) continue;


                int alpha = render.pixels[x+y*render.width];
                if (alpha > 0 ) {
                    pixels[xPixel+yPixel*width] = alpha;
                }

            }
        }
    }
}
