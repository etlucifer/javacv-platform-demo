import redis.clients.jedis.Jedis;

import java.io.*;
import java.util.*;

public class SaveCamreaRelation {
//    public static final String BLOCK_QUEUE_NAME = "queue:single";

    public static void main(String[] args) throws InterruptedException {
        Jedis jedis = new Jedis();
        Map<String,String> hashMap = new HashMap<String,String>();
//        String camera1 = "";
//        for(int i=0;i<4;i++){
//            camera1+=",";
//        }
        boolean flag = true;
        while (flag){
            hashMap.put("192.168.0.130","camera1,camera2,camera3,camera7");
            hashMap.put("192.168.0.132","camera5,camera6");
            jedis.hmset("info",hashMap);
        }

    }
}
