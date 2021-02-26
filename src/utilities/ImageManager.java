package utilities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * This basically reads the image assets from the assets/imageAssets folder so they can be included in the jar.
 */
public class ImageManager {


    public final static String path = "/assets/imageAssets/";
    public final static String ext = ".png";

    /**
     * A map holding all the images, by name.
     */
    private static final Map<String, Image> _images = new HashMap<>();

    /**
     * Obtains a copy of that map of all the images.
     *
     * @return a map of images and their filenames
     */
    public static Map<String, Image> getImages() {
        return Collections.unmodifiableMap(_images);
    }

    /**
     * Get an image from the image map by name.
     *
     * @param imName the name of the image to get.
     * @return that image.
     */
    public static Image getImage(String imName) {
        return _images.get(imName);
    }

    /**
     * Loads an image from a given file and also puts it on the map
     *
     * @param fname the name of the image file
     * @return that image
     * @throws IOException if something goes wrong.
     */
    public static Image loadImage(String fname) throws IOException {
        BufferedImage img = loadBufferedImage(fname);
        _images.put(fname, img);
        return img;
    }

    /**
     * Basically it gets the image from the filename as a BufferedImage.
     * easier than using loadImage to just cast the Image back to a BufferedImage, y'know?
     *
     * @param fname filename of the image to load
     * @return that image as a bufferedImage
     * @throws IOException if something goes wrong.
     */
    public static BufferedImage loadBufferedImage(String fname) throws IOException {
        //return ImageIO.read(new File(path + fname + ext));
        return ImageIO.read(ImageManager.class.getResource(path + fname + ext));
    }

    /**
     * Like the other loadImage but storing it on the map with a different name to its filename
     *
     * @param imName name to give the image in the map
     * @param fname  filename of the image
     * @return the image
     * @throws IOException if it couldn't be loaded
     */
    public static Image loadImage(String imName, String fname) throws IOException {
        BufferedImage img = loadBufferedImage(fname);
        _images.put(imName, img);
        return img;
    }

    /**
     * like loadImage but for an array of filenames
     *
     * @param fNames an array of filenames for images to read
     * @throws IOException if they couldn't be read
     */
    public static void loadImages(String[] fNames) throws IOException {
        for (String s : fNames) {
            loadImage(s);
        }
    }

    /**
     * like loadImage but for an iterable of filenames
     *
     * @param fNames an array of filenames for images to read
     * @throws IOException if they couldn't be read
     */
    public static void loadImages(Iterable<String> fNames) throws IOException {
        for (String s : fNames) {
            loadImage(s);
        }
    }


    //and here we attempt to load some images.
    static {
        try {
            ImageManager.loadImage("HECC-IT icon");
            ImageManager.loadImage("HECC-UP icon");
            ImageManager.loadImage("OH-HECC icon");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
