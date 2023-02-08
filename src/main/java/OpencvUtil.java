import org.bytedeco.javacv.Java2DFrameConverter;

import java.awt.image.BufferedImage;

public class OpencvUtil {

    public static BufferedImage FrameToBufferedImage(org.bytedeco.javacv.Frame frame) {
        Java2DFrameConverter converter = new Java2DFrameConverter();
        BufferedImage bufferedImage = converter.getBufferedImage(frame);
//      bufferedImage=rotateClockwise90(bufferedImage);
        return bufferedImage;
    }

    /**
     * 处理图片，将图片旋转90度。
     */
    public static BufferedImage rotateClockwise90(BufferedImage bi) {
        int width = bi.getWidth();
        int height = bi.getHeight();
        BufferedImage bufferedImage = new BufferedImage(height, width, bi.getType());
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                bufferedImage.setRGB(j, i, bi.getRGB(i, j));
        return bufferedImage;
    }
}
