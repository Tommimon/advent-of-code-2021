package Gonduls.d12;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Crawler {
    static HashMap<String, List<String>> connections;
    static Integer result1 = 0;
    static Integer result2 = 0;

    public Crawler(HashMap<String, List<String>> connections) {
        Crawler.connections = connections;
    }

    public int getResult1() {
        HashMap<String, Integer> start = new HashMap<>();
        start.put("start", 1);
        CrawlThread crawlThread = new CrawlThread("start", start);
        crawlThread.start();
        try {
            crawlThread.join();
        } catch (Exception e) {
            System.out.println("Houston, abbiamo un problema");
        }
        return result1;
    }

    public int getResult2() {
        //if(result2 == 0)
        //    getResult1();
        return result2;
    }
}



