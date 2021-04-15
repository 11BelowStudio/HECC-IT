package utilities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * This basically reads the image assets from the assets/imageAssets folder so they can be included in the jar.
 * <p>
 * Partially based on sample code provided by Dr. Dimitri Ognibene as part of the
 * CE218 Computer Games Programming module I took last year.
 *
 * @author Rachel Lowe
 */
public class ImageManager {


    /**
     * The location of the directory holding the images within /src.
     */
    private final static String path = "/assets/imageAssets/";
    /**
     * We're using .png files.
     */
    private final static String ext = ".png";

    /**
     * Loads an image from a given file and also puts it on the map
     *
     * @param fname the name of the image file
     * @return that image
     * @throws IOException if something goes wrong.
     */
    public static Image loadImage(String fname) throws IOException {
        return loadBufferedImage(fname);
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
        return ImageIO.read(Objects.requireNonNull(ImageManager.class.getResource(path + fname + ext)));
    }

    /**
     * Holds the icons used by OH-HECC's windows.
     */
    private static final List<Image> OH_HECC_IMAGES = new ArrayList<>();
    /**
     * Holds the icons used by HECC-UP's windows.
     */
    private static final List<Image> HECC_UP_IMAGES = new ArrayList<>();
    /**
     * Holds the icons used by HECC-IT's windows.
     */
    private static final List<Image> HECC_IT_IMAGES = new ArrayList<>();

    /**
     * Returns the list of icons used by OH-HECC.
     *
     * @return the list of icons used by OH-HECC.
     */
    public static List<Image> getOhHeccIcons() {
        return OH_HECC_IMAGES;
    }

    /**
     * Returns the list of icons used by HECC-UP.
     *
     * @return the list of icons used by HECC-UP.
     */
    public static List<Image> getHeccUpIcons() {
        return HECC_UP_IMAGES;
    }

    /**
     * Returns the list of icons used by HECC-IT.
     *
     * @return the list of icons used by HECC-IT.
     */
    public static List<Image> getHeccItIcons() {
        return HECC_IT_IMAGES;
    }


    //and here we attempt to load some images.
    static {
        try {
            final Image heccIt = ImageManager.loadImage("HECC-IT icon");
            final Image heccUp = ImageManager.loadImage("HECC-UP icon");
            final Image ohHecc = ImageManager.loadImage("OH-HECC icon");
            final Image smallHecc = ImageManager.loadImage("HECC small");
            final Image heccIt64 = ImageManager.loadImage("HECC-IT 64");
            final Image heccUp64 = ImageManager.loadImage("HECC-UP 64");
            final Image ohHecc64 = ImageManager.loadImage("OH-HECC 64");
            final Image heccItBig = ImageManager.loadImage("HECC-IT large");
            final Image heccUpBig = ImageManager.loadImage("HECC-UP large");
            final Image ohHeccBig = ImageManager.loadImage("OH-HECC large");

            //puts the icons into the appropriate lists.
            OH_HECC_IMAGES.add(smallHecc);
            OH_HECC_IMAGES.add(ohHecc);
            OH_HECC_IMAGES.add(ohHecc64);
            OH_HECC_IMAGES.add(ohHeccBig);

            HECC_UP_IMAGES.add(smallHecc);
            HECC_UP_IMAGES.add(heccUp);
            HECC_UP_IMAGES.add(heccUp64);
            HECC_UP_IMAGES.add(heccUpBig);

            HECC_IT_IMAGES.add(smallHecc);
            HECC_IT_IMAGES.add(heccIt);
            HECC_IT_IMAGES.add(heccIt64);
            HECC_IT_IMAGES.add(heccItBig);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
