package Gonduls.d12;

import java.util.HashMap;

// I first implemented it as a thread, but found out it was much much slower, and ultimately did not work
public class CrawlThread{
    private final HashMap<String, Integer> visited;
    private final String position;

    public CrawlThread(String start, HashMap<String, Integer> visited){
        this.visited = visited;
        position = start;
    }

    public void crawl(){
        for(String nextCave : Crawler.connections.get(position)){

            if(visited.values().stream().filter(c -> c== 2).count() > 1)
                return;

            // Don't consider start cave as a valid nextCave
            if(nextCave.equals("start"))
                continue;

            if(nextCave.equals("end")){
                // It is bad practice accessing and modifying an attribute from another class,
                // it would require too much work to avoid doing it
                if(visited.values().stream().allMatch(c -> c ==1))
                    Crawler.result1++;

                Crawler.result2++;
                continue;
            }

            // If I have already been in this (small) nextCave twice: don't crawl there
            if(visited.containsKey(nextCave) && visited.get(nextCave) == 2)
                continue;

            // by default, generator copies keys and values instead of giving a reference to the old HashMap,
            // if clone is implemented by key and value types (I assume)
            HashMap<String, Integer> copy = new HashMap<>(visited);
            CrawlThread newCrawler = new CrawlThread(nextCave, copy);

            // If nextCave is a small cave:
            if(nextCave.charAt(0) <= 'z' && nextCave.charAt(0) >= 'a') {
                if(visited.containsKey(nextCave)){
                    newCrawler.visited.replace(nextCave, 2);
                }
                else
                    newCrawler.visited.put(nextCave, 1);
            }
            newCrawler.crawl();
        }
    }
}
