import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.OpenCVFrameConverter;

        import javax.swing.*;
public class demo {
}
class Video {
    public static void main(String[] args) {
        FFmpegFrameGrabber grabber = null;
        try {
            String file = "rtsp://admin:sdlj1234@192.168.0.126:554/cam/realmonitor?channel=1&subtype=0";
            grabber = FFmpegFrameGrabber.createDefault(file);
            grabber.setOption("rtsp_transport", "tcp"); // 使用tcp的方式，不然会丢包很严重
            grabber.setImageWidth(1280);
            grabber.setImageHeight(720);
            System.out.println("grabber start");
            grabber.start();

//1.播放视频

            CanvasFrame canvasFrame = new CanvasFrame("摄像机");
            canvasFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            canvasFrame.setAlwaysOnTop(true);
            OpenCVFrameConverter.ToMat converter = new OpenCVFrameConverter.ToMat();
            while (true) {
                Frame frame = grabber.grabImage();
                canvasFrame.showImage(frame);
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {

        }
    }
}

