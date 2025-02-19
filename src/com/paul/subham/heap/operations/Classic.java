package com.paul.subham.heap.operations;

import java.util.*;

/**
 * 1. Replace elements by its rank in the array
 * 2. Task Scheduler (Using Priority Queue)
 * 3. Task Scheduler (Filling slots and sorting)
 * 4. Task Scheduler (Greedy)
 * 5. Task Scheduler (Mathematics)
 * 6. Hand of straights (using map)
 * 7. Hand of straights (using map and start queue)
 * 8. Hand of straights (using map - optimized)
 */
public class Classic {
    public static void main(String[] args) {

    }

    /**
     * Replace elements by its rank in the array
     *
     * Input:
     * arr = [20, 15, 26, 2, 98, 6]
     * Output:
     * 4, 3, 5, 1, 6, 2
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int[] replaceWithRank(int a[], int n) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(x -> a[x]));
        for(int i=0; i<n; i++) {
            pq.add(i);
        }
        int[] res = new int[n];
        int rank = 0;
        int last = 0;
        while(!pq.isEmpty()) {
            int current = pq.remove();
            if(a[current] > last) {
                res[current] = ++rank;
                last = a[current];
            } else {
                res[current] = rank;
            }
        }
        return res;
    }

    /**
     * Task Scheduler (Using Priority Queue)
     *
     * You are given an array of CPU tasks, each labeled with a letter from A to Z, and a number n.
     * Each CPU interval can be idle or allow the completion of one task. Tasks can be completed in any order,
     * but there's a constraint: there has to be a gap of at least n intervals between two tasks with the same label.
     *
     * Return the minimum number of CPU intervals required to complete all tasks.
     *
     * TC: O(n*logk), k = 26 so, O(n)
     * SC: O(k) = O(1)
     */
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        for(int i=0; i<tasks.length; i++) {
            freq[tasks[i] - 'A']++;
        }
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());
        for(int i=0; i<26; i++) {
            if(freq[i] > 0) {
                priorityQueue.add(freq[i]);
            }
        }
        int time = 0;
        while(!priorityQueue.isEmpty()) {
            int cycle = n+1;
            List<Integer> store = new ArrayList<>();
            int taskCount = 0;
            while(cycle-- > 0 && !priorityQueue.isEmpty()) {
                int currentFreq = priorityQueue.remove();
                if(currentFreq > 1) {
                    store.add(currentFreq - 1);
                }
                taskCount++;
            }
            priorityQueue.addAll(store);
            time += (priorityQueue.isEmpty()? taskCount : n+1);
        }
        return time;
    }

    /**
     * Task Scheduler (Filling slots and sorting)
     *
     * TC: O(n + klogk), k = 26 so, O(n)
     * SC: O(k) = O(1)
     */
    public int leastIntervalFillslots(char[] tasks, int n) {
        int[] freq = new int[26];
        for(int i=0; i<tasks.length; i++) {
            freq[tasks[i] - 'A']++;
        }
        Arrays.sort(freq);
        int maxFreq = freq[25] - 1;
        int idleSlots = maxFreq * n;
        for(int i=24; i>=0 && freq[i] > 0; i--) {
            idleSlots -= Math.min(freq[i], maxFreq);
        }
        return idleSlots > 0 ? idleSlots + tasks.length : tasks.length;
    }

    /**
     * Task Scheduler (Greedy)
     *
     * TC: O(n)
     * SC: O(k) = O(1)
     */
    public static int leastIntervalGreedy(char[] tasks, int n) {
        int[] freq = new int[26];
        int maxFreq = 0;
        int maxCount = 0;
        for(int i=0; i<tasks.length; i++) {
            freq[tasks[i] - 'A']++;
            if(maxFreq == freq[tasks[i] - 'A']) {
                maxCount++;
            } else if (maxFreq < freq[tasks[i] - 'A']) {
                maxFreq = freq[tasks[i]-'A'];
                maxCount = 1;
            }
        }
        int partCount = maxFreq - 1;
        int partLength = n - maxCount + 1;
        int emptySlots = partCount * partLength;
        int availableTasks = tasks.length - (maxCount * maxFreq);
        int idleSlots = Math.max(0, emptySlots - availableTasks);
        return tasks.length + idleSlots;
    }

    /**
     * Task Scheduler (Mathematics)
     *
     * TC: O(n)
     * SC: O(k) = O(1)
     */
    public static int leastIntervalMathematics(char[] tasks, int n) {
        int[] freq = new int[26];
        int maxFreq = 0;
        for(int i=0; i<tasks.length; i++) {
            freq[tasks[i] - 'A']++;
            maxFreq = Math.max(maxFreq, freq[tasks[i]-'A']);
        }
        int time = (maxFreq-1)*(n+1);
        for(int i=0; i<freq.length; i++) {
            if(freq[i] == maxFreq) {
                time++;
            }
        }
        return Math.max(time, tasks.length);
    }

    /**
     * Hand of straights (using map)
     *
     * Alice has some number of cards and she wants to rearrange the cards into groups so that each group is of size groupSize,
     * and consists of groupSize consecutive cards.
     * Given an integer array hand where hand[i] is the value written on the ith card and an integer groupSize,
     * return true if she can rearrange the cards, or false otherwise.
     *
     * TC: O(nlogn + nk)
     * SC: O(n)
     */
    public static boolean isNStraightHand(int[] hand, int groupSize) {
        if(hand.length % groupSize != 0) {
            return false;
        }
        Map<Integer, Integer> cardCountMap = new TreeMap<>();
        for(int i=0; i<hand.length; i++) {
            cardCountMap.put(hand[i], cardCountMap.getOrDefault(hand[i],0)+1);
        }
        while(!cardCountMap.isEmpty()) {
            int currentCard = cardCountMap.entrySet().iterator().next().getKey();
            for(int i=0; i<groupSize; i++) {
                if(!cardCountMap.containsKey(currentCard + i)) {
                    return false;
                }
                cardCountMap.put(currentCard + i, cardCountMap.get(currentCard+i)-1);
                if(cardCountMap.get(currentCard + i) == 0) {
                    cardCountMap.remove(currentCard+i);
                }
            }
        }
        return true;
    }

    /**
     * Hand of straights (using map and start queue)
     *
     * TC: O(nlogn + n)
     * SC: O(n)
     */
    public boolean isNStraightHandMapStartQueue(int[] hand, int groupSize) {
        Map<Integer, Integer> cardCountMap = new TreeMap<>();
        for (int card : hand) {
            cardCountMap.put(card, cardCountMap.getOrDefault(card, 0) + 1);
        }

//         Queue to keep track of the number of new groups
//         starting with each card value
        Queue<Integer> groupStartQueue = new LinkedList<>();
        int lastCard = -1, currentOpenGroups = 0;
        for (Map.Entry<Integer, Integer> entry : cardCountMap.entrySet()) {
            int currentCard = entry.getKey();
            int currentCardCount = entry.getValue();
            // Check if there are any discrepancies in the sequence
            // or more groups are required than available cards
            if ((currentOpenGroups > 0 && currentCard > lastCard + 1) || currentOpenGroups > currentCardCount) {
                return false;
            }
            // Calculate the number of new groups starting with the current card
            groupStartQueue.add(currentCardCount - currentOpenGroups);
            lastCard = currentCard;
            currentOpenGroups = currentCardCount;
            // Maintain the queue size to be equal to groupSize
            if (groupStartQueue.size() == groupSize) {
                currentOpenGroups -= groupStartQueue.poll();
            }
        }
        // All groups should be completed by the end
        return currentOpenGroups == 0;
    }

    /**
     * Hand of straights (using map - optimized)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public boolean isNStraightHandOptimal(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) {
            return false;
        }
        HashMap<Integer, Integer> cardCountMap = new HashMap<>();
        for (int card : hand) {
            int count = cardCountMap.getOrDefault(card, 0);
            cardCountMap.put(card, count + 1);
        }
        for (int card : hand) {
            int startCard = card;
            // Find the start of the potential straight sequence
            while (cardCountMap.getOrDefault(startCard - 1, 0) > 0) {
                startCard--;
            }
            // Process the sequence starting from startCard
            while (startCard <= card) {
                while (cardCountMap.getOrDefault(startCard, 0) > 0) {
                    // Check if we can form a consecutive sequence
                    // of groupSize cards
                    for (int i = startCard; i < startCard + groupSize; i++) {
                        if (cardCountMap.getOrDefault(i, 0) == 0) {
                            return false;
                        }
                        cardCountMap.put(i, cardCountMap.get(i) - 1);
                    }
                }
                startCard++;
            }
        }
        return true;
    }
}
