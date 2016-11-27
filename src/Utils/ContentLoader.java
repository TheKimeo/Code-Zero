package Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ContentLoader {
    public static BufferedImage[] animationSprites(ArrayList<String> filePaths) {
        BufferedImage[] imageArray = new BufferedImage[ filePaths.size()];
        for (int i = 0; i < filePaths.size(); ++i) {
            try {
                imageArray[i] = ImageIO.read(new FileInputStream(filePaths.get(i)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return imageArray;
    }
}
