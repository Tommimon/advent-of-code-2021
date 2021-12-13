package Gonduls.d12;

import java.util.HashMap;
import java.util.List;

// singleton pattern could be applied here
public class Crawler {
    static HashMap<String, List<String>> connections;
    static protected Integer result1;
    static protected Integer result2;

    public Crawler(HashMap<String, List<String>> connections) {
        Crawler.connections = connections;
    }

    // It will set result2 as well
    public int getResult1() {
        result2 = 0;
        result1 = 0;
        HashMap<String, Integer> start = new HashMap<>();
        start.put("start", 1);
        CrawlThread crawlThread = new CrawlThread("start", start);
        crawlThread.crawl();

        return result1;
    }

    public int getResult2() {
        if(result2 == 0)
            getResult1();
        return result2;
    }
}



