package com.paul.subham.heap.operations;


import java.util.*;

/**
 * Author: the_odd_human
 * Date: 20/02/25
 */
public class Twitter {
    private Map<Integer, Set<Integer>> followMap;
    private Map<Integer, Tweet> tweetMap;
    private int timestamp;

    public Twitter() {
        followMap = new HashMap<>();
        tweetMap = new HashMap<>();
        timestamp = 0;
    }

    public void postTweet(int userId, int tweetId) {
        Tweet newTweet = new Tweet(tweetId, timestamp++);
        newTweet.next = tweetMap.get(userId);
        tweetMap.put(userId, newTweet);
    }

    public List<Integer> getNewsFeed(int userId) {
        PriorityQueue<Tweet> priorityQueue = new PriorityQueue<>((a,b) -> b.timestamp - a.timestamp);
        Set<Integer> followees = followMap.get(userId);
        if(tweetMap.get(userId) != null) {
            priorityQueue.add(tweetMap.get(userId));
        }
        if (followees != null && !followees.isEmpty()) {
            for (Integer followee : followees) {
                if(tweetMap.get(followee) != null) {
                    priorityQueue.add(tweetMap.get(followee));
                }
            }
        }
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i<10 && !priorityQueue.isEmpty(); i++) {
            Tweet current = priorityQueue.remove();
            res.add(current.tweetId);
            if(current.next != null) {
                priorityQueue.add(current.next);
            }
        }
        return res;
    }

    public void follow(int followerId, int followeeId) {
        followMap.putIfAbsent(followerId, new LinkedHashSet<>());
        followMap.get(followerId).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        if(!followMap.containsKey(followerId)) {
            return;
        }
        followMap.get(followerId).remove(followeeId);
    }
}

class Tweet {
    int tweetId;
    int timestamp;
    Tweet next;

    Tweet(int tweetId, int timestamp) {
        this.tweetId = tweetId;
        this.timestamp = timestamp;
        this.next = null;
    }
}
