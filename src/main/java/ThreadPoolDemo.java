import org.bytedeco.javacv.FFmpegFrameGrabber;
import redis.clients.jedis.Jedis;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo {
    public static final String SINGLE_QUEUE_NAME = "queue:single";
    private String threadName ;
    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 10, 10L, TimeUnit.SECONDS, new LinkedBlockingQueue(100));
    public static void main(String[] args) {
        // 一个连接池循环创建线程插入任务
        for(int i=0;i<8;i++){
            ThreadPoolDemo threadPoolDemo = new ThreadPoolDemo();
            threadPoolDemo.setThreadName(i+"_");
            threadPoolExecutor.execute(new Runnable() {
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
                            File outPut = new File("D:\\imges\\" + threadPoolDemo.getThreadName() +i + ".jpeg");
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
            });
        }

    }
    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }
}
