import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Resizer implements Runnable {

    private File[] files;
    private String path;
    private int width;
    private int height;

    public Resizer(File[] files, String path, int width, int height) {
        this.files = files;
        this.path = path;
        this.width = width;
        this.height = height;
    }

    @Override
    public void run() {
        try {
            for (File file : files) {
                BufferedImage image = ImageIO.read(file);
                if (image == null) {
                    continue;
                }
                BufferedImage newImage = Scalr.resize(image, width, height);
                File newFile = new File(path + "/" + file.getName());
                ImageIO.write(newImage, "jpg", newFile);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
