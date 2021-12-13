package Gonduls.d12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class CrawlThread extends Thread{
    private final HashMap<String, Integer> visited;
    private final String position;
    private final static Object obj = new Object();
    private final static Object obj2 = new Object();
    private final List<CrawlThread> sons = new ArrayList<>();

    public CrawlThread(String start, HashMap<String, Integer> visited){
        this.visited = visited;
        position = start;
    }

    public void run(){
        if(!Crawler.connections.containsKey(position))
            return;

        System.out.println(position);
        for(String cave : Crawler.connections.get(position)){
            //System.out.println(position + "->" + cave);
            if(cave.equals("start"))
                continue;

            if(cave.equals("end")){

                if(visited.values().stream().allMatch(c -> c ==1))
                    synchronized (obj) {
                        Crawler.result1++;
                    }

                if(visited.values().stream().filter(c -> c== 2).count() <= 1)
                    synchronized (obj2) {
                        Crawler.result1++;
                    }

                continue;
            }

            if(visited.containsKey(cave) && visited.get(cave) == 2)
                continue;

            int doubles = 0;
            HashMap<String, Integer> copy = new HashMap<>();
            for(String key : visited.keySet()){
                if(key.equals(cave)){
                    copy.put(key, 2);
                    continue;
                }

                copy.put(key, visited.get(key));

            }

            CrawlThread newCrawler = new CrawlThread(cave, copy);
            sons.add(newCrawler);

            if(cave.charAt(0) <= 'z' && cave.charAt(0) >= 'a') {
                if(visited.containsKey(cave)){
                    visited.replace(cave, 2);
                }
                else
                    newCrawler.visited.put(cave, 1);
            }

            //System.out.println("Starting new from " + position + " to " + cave);
            newCrawler.run();
        }

        if(sons.isEmpty())
            return;

        for (CrawlThread son : sons){
            try {
                //System.out.println("son position " + son.position + " from " + position);
                son.join();
            }
            catch(Exception e){
                System.out.println("Houston, abbiamo un problema pt2");
            }
        }
    }
}
