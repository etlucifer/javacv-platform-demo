import redis.clients.jedis.Jedis;

import java.math.BigDecimal;
import java.util.*;

public class DispenseTest {
    public static final String BLOCK_QUEUE_NAME = "queue:single";

    public static void main(String[] args) throws InterruptedException {
        Jedis jedis = new Jedis();
        List<Map.Entry<String,String>> list= new ArrayList<>(jedis.hgetAll("serverCpu").entrySet());
//        while (true){
            Iterator<String> iter=jedis.hkeys("serverCpu").iterator();
            List cpuNumList = new ArrayList();
            while(iter.hasNext()){
                Map hashMap = new HashMap();
                String key = iter.next();
                System.out.println(key+":"+jedis.hmget("serverCpu",key));
                String value = jedis.hmget("serverCpu",key)+"";
                String[] values = value.split(",");
                int cpu = Integer.parseInt(values[0]);
                double men = Double.parseDouble(values[1]);
                BigDecimal bigDecimal = new BigDecimal(cpu).divide(new BigDecimal(men));
                BigDecimal surplus = bigDecimal.subtract(new BigDecimal(cpu));
                hashMap.put(key,surplus);
                cpuNumList.add(surplus);
            }

//        }

    }
}
