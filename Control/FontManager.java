package Control;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Ruitian Yang
 * @author YuRong He
 * @author Guo Yu
 * @date 2023/05/25
 *       class to load custom fonts
 */
public class FontManager {

    /**
     * @param path     the path of the font file
     * @param fontSize the size of the font
     * @return Font the font
     */
    public static Font getCustomFont(String path, float fontSize) {
        try {
            // create the font to use. Specify the size!
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(fontSize);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            // register the font
            ge.registerFont(customFont);
            return customFont;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Font getLatoLight(float fontSize) {
        return getCustomFont("Fonts\\Lato\\Lato-Light.ttf", fontSize);
    }

    public static Font getLatoBold(float fontSize) {
        return getCustomFont("Fonts\\Lato\\Lato-Bold.ttf", fontSize);
    }

    public static Font getIBSCartooning(float fontSize) {
        return getCustomFont("Fonts\\Lato\\IBS Cartooning.ttf", fontSize);
    }

    public static Font getLatoRegular(float fontSize) {
        return getCustomFont("Fonts\\Lato\\Lato-Regular.ttf", fontSize);
    }
}
