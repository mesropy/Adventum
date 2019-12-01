package com.example.ourgame.Statistics;

import java.util.List;

/**
 * Interface for a sorting strategy, used to sort the leader board in a variety of ways.
 */
public interface SortStrategy {

    void sort();

    List getData();
}
