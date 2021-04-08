package utilities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * This basically reads the image assets from the assets/imageAssets folder so they can be included in the jar.
 *
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
        return ImageIO.read(Objects.requireNonNull(ImageManager.class.getResource(path + fname + ext)));
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
            Image heccIt = ImageManager.loadImage("HECC-IT icon");
            Image heccUp = ImageManager.loadImage("HECC-UP icon");
            Image ohHecc = ImageManager.loadImage("OH-HECC icon");
            Image smallHecc = ImageManager.loadImage("HECC small");
            Image heccIt64 = ImageManager.loadImage("HECC-IT 64");
            Image heccUp64 = ImageManager.loadImage("HECC-UP 64");
            Image ohHecc64 = ImageManager.loadImage("OH-HECC 64");
            Image heccItBig = ImageManager.loadImage("HECC-IT large");
            Image heccUpBig = ImageManager.loadImage("HECC-UP large");
            Image ohHeccBig = ImageManager.loadImage("OH-HECC large");

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
