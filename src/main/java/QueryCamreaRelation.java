import redis.clients.jedis.Jedis;

import java.io.*;
import java.util.*;

public class QueryCamreaRelation {
    public static final String BLOCK_QUEUE_NAME = "queue:single";

    public static void main(String[] args) throws InterruptedException {
        Jedis jedis = new Jedis();
        List<Map.Entry<String,String>> list= new ArrayList<>(jedis.hgetAll("info").entrySet());
//        while (true){
//            Iterator<String> iter=jedis.hkeys("info").iterator();
//            while(iter.hasNext()){
//                String key = iter.next();
//                System.out.println(key+":"+jedis.hmget("info",key));
//            }
//        }
        Iterator<String> iter=jedis.hkeys("serviceInfo").iterator();
        while(iter.hasNext()){
            String key = iter.next();
            System.out.println(key+":"+jedis.hmget("serviceInfo",key));
        }
    }
}
