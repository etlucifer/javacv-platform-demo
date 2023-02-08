import redis.clients.jedis.Jedis;

public class SingleProducer {
    public static final String SINGLE_QUEUE_NAME = "queue:single";

    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        for (int i = 0; i < 100; i++) {
            jedis.lpush(SINGLE_QUEUE_NAME, "hello " + i);
        }
        jedis.close();
    }
}
