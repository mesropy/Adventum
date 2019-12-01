package com.example.ourgame.Statistics;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Class for sorting the leader board by user ranking.
 */
public class SortRankingStrategy implements SortStrategy {

    private List<Map.Entry<String, String>> data;

    SortRankingStrategy(List<Map.Entry<String, String>> data) {

        this.data = data;

    }

    @Override
    public void sort() {

        Collections.sort(
                data
                , new Comparator<Map.Entry<String, String>>() {
                    public int compare(Map.Entry<String, String> a, Map.Entry<String, String> b) {

                        if (b.getValue().equals(a.getValue())) {
                            return 0;
                        } else if (b.getValue().equalsIgnoreCase("Gold")) {
                            return 1;
                        } else if (a.getValue().equalsIgnoreCase("Gold")) {
                            return -1;
                        } else if (b.getValue().equalsIgnoreCase("Silver")) {
                            return 1;
                        } else {
                            return -1;
                        }

                    }
                }
        );

    }

    @Override
    public List getData() {
        return data;
    }

}
