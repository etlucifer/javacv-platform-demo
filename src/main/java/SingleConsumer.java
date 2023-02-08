import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class SingleConsumer {
    public static final String BLOCK_QUEUE_NAME = "queue:single";

    public static void main(String[] args) throws InterruptedException {
        Jedis jedis = new Jedis();
        while (true) {
            // 超时时间为1s
            List<String> messageList = jedis.brpop(2, BLOCK_QUEUE_NAME);
            if (null != messageList && !messageList.isEmpty()) {
                System.out.println(messageList);
            }
        }
    }
}
