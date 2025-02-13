package com.paul.subham.queue.operation;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

class LFU {
    private final Map<Integer, CacheNode> cache;
    private final Map<Integer, LinkedHashSet<Integer>> freqMap;
    private final int capacity;
    private int minFrequency;

    public LFU(int capacity) {
        this.capacity = capacity;
        this.minFrequency = 0;
        cache = new HashMap<>();
        freqMap = new HashMap<>();
    }

    public int get(int key) {
        if(!cache.containsKey(key)) {
            return -1;
        }
        CacheNode node = cache.get(key);
        Set<Integer> keys = freqMap.get(node.frequency);
        keys.remove(key);
        if(keys.isEmpty()) {
            freqMap.remove(node.frequency);
            if(minFrequency == node.frequency) {
                minFrequency++;
            }
        }
        node.frequency++;
        updateFrequency(node.frequency, key);
        return node.value;
    }

    public void put(int key, int value) {
        CacheNode node = cache.get(key);
        if(node != null) {
            node.value = value;
            get(key);
            return;
        }
        if(capacity == cache.size()){
            int rKey = freqMap.get(minFrequency).iterator().next();
            cache.remove(rKey);
            freqMap.get(minFrequency).remove(rKey);
            if(freqMap.get(minFrequency).isEmpty()) {
                freqMap.remove(minFrequency);
            }
        }
        node = new CacheNode(key, value, 1);
        cache.put(key, node);
        updateFrequency(1, key);
        minFrequency = 1;
    }

    private void updateFrequency(int frequency, int key) {
        freqMap.putIfAbsent(frequency, new LinkedHashSet<>());
        freqMap.get(frequency).add(key);
    }
}

class CacheNode {
    int key;
    int value;
    int frequency;
    CacheNode(int key, int value, int frequency) {
        this.key = key;
        this.value = value;
        this.frequency = frequency;
    }
}
