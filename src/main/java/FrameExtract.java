import org.bytedeco.javacv.FFmpegFrameGrabber;

import javax.imageio.ImageIO;
import java.io.File;

public class FrameExtract  extends Thread {
    private String threadName ;
    @Override
    public void run() {

        FFmpegFrameGrabber grabber = null;
        try {
            String file = "rtsp://192.168.0.131:8554/live/test";
            grabber = FFmpegFrameGrabber.createDefault(file);
            grabber.setOption("rtsp_transport", "tcp"); // 使用tcp的方式，不然会丢包很严重
            grabber.setImageWidth(1280);
            grabber.setImageHeight(720);
            System.out.println("grabber start");
            grabber.start();

//2.帧截图
            int i = 0;
            while (i < 1000) {
                File outPut = new File("D:\\imges\\" + threadName +i + ".jpeg");
                org.bytedeco.javacv.Frame frame = grabber.grabImage();
                if (frame != null) {
                    ImageIO.write(OpencvUtil.FrameToBufferedImage(frame), "jpeg", outPut);
                    System.out.println("图片已保存");
                    Thread.sleep(200);
                    i++;
                }

            }
            grabber.stop();
            grabber.release();
        } catch (Exception e) {
            System.out.println(e);
        } finally {

        }



    }

    public static void main(String[] args) {
        for (int i = 0; i <20 ; i++) {
            FrameExtract fe=new FrameExtract();
                    fe.setThreadName(i+"_");
                    fe.start();
        }



    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }
}
