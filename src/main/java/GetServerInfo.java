import cn.hutool.core.util.RuntimeUtil;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

public class GetServerInfo {


    public static void main(String[] args) throws InterruptedException {
        Jedis jedis = new Jedis();
        Map<String,String> hashMap = new HashMap<String,String>();
        boolean flag = true;

        while (flag){
            String num = "";
            double res = 0.0;
            if(1==1){
                String command1 = "top -b -n 1 | grep Cpu";
                //执行该命令 截取到Cpu信息这一行的数据
                String[] command = { "/bin/sh", "-c", command1 };
                String s = RuntimeUtil.execForStr(command);  //hutool工具对执行命令的封装
                //不想用可以自己写jdk自带的方法，多几行代码罢了
                num = s.substring(s.indexOf(":")+1,s.indexOf("us"));
                num = num.replace(" ", "");
                System.out.println("cpuInfo = " + num);
            }
            if(2==2){
                String command1 = "top -b -n 1 | grep Mem";
                String[] command = { "/bin/sh", "-c", command1 };
                String s = RuntimeUtil.execForStr(command);
                String total = s.substring(s.indexOf(":") + 1, s.indexOf("total")).replace(" ","");
                Double totalNum = Double.parseDouble(total);
                String used = s.substring(s.indexOf("free,") + 5, s.indexOf("used,")).replace(" ","");
                Double usedNum = Double.parseDouble(used);
                res = usedNum / totalNum;
                System.out.println("memoryInfo = " + res);
            }
            hashMap.put("192.168.0.130",num+","+res+"");
            jedis.hmset("serverCpu",hashMap);
            Thread.sleep(1000);
        }

    }

}
