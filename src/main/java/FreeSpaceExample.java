
import java.io.File;

public class FreeSpaceExample {
    public static void main(String[] args) {
        // 我们创建一个File实例来代表一个分区
        //我们的文件系统。例如，在这里我们使用了驱动器D：
        // 与Windows操作系统一样。
        File file = new File("D:");

        // 使用getTotalSpace()我们可以获得以下信息：
        // 分区的实际大小，然后将其转换为
        // 兆字节。
        long totalSpace = file.getTotalSpace() / (1024 * 1024);

        // 接下来，我们将可用磁盘空间作为名称
        // 方法显示给我们，并且还获得以兆字节为单位的大小。
        long freeSpace = file.getFreeSpace() / (1024 * 1024);

        // 只需打印出值即可。
        System.out.println("Total Space = " + totalSpace + " Mega Bytes");
        System.out.println("Free Space = " + freeSpace + " Mega Bytes");
    }
}
