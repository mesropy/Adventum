package com.example.ourgame.Statistics;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Class for sorting the leader board by numbers.
 */
public class SortNumberStrategy implements SortStrategy {

    private List<Map.Entry<String, Integer>> data;

    SortNumberStrategy(List<Map.Entry<String, Integer>> data) {
        this.data = data;

    }

    @Override
    public void sort() {

        Collections.sort(
                data
                , new Comparator<Map.Entry<String, Integer>>() {
                    public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
                        return Integer.compare(b.getValue(), a.getValue());
                    }
                }
        );

    }

    @Override
    public List getData() {
        return data;
    }

}
