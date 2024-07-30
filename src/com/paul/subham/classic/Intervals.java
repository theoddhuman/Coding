package com.paul.subham.classic;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

/**
 * @author subham.paul
 * 
 * 1. Merge overlapping intervals (Using stack)
 * 2. Merge overlapping intervals
 */
public class Intervals {
    public static void main(String[] args) {
        Interval[] intervals = {new Interval(1,3), new Interval(2,4), new Interval(6,8), new Interval(9,10)};
        mergeIntervals(intervals);
    }

    /**
     * Merge overlapping intervals (Using stack)
     *
     * TC: O(n^2)
     * SC: O(n)
     */
    public static void mergeIntervalsStack(Interval[] intervals) {
        Stack<Interval> stack = new Stack<>();
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval.start));
        stack.push(intervals[0]);
        for(int i=1; i<intervals.length; i++) {
            Interval top = stack.peek();
            Interval current = intervals[i];
            if(top.end < current.start) {
                stack.push(current);
            } else if (top.end < current.end) {
                top.end = current.end;
            }
        }
        while(!stack.isEmpty()) {
            Interval top = stack.pop();
            System.out.println(top.start + " " + top.end);
        }
    }

    /**
     * Merge overlapping intervals
     *
     * TC: O(n^2)
     * SC: O(1)
     */
    public static void mergeIntervals(Interval[] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval.start));
        int index = 0;
        for(int i=1; i<intervals.length; i++) {
            Interval top = intervals[index];
            Interval current = intervals[i];
            if(top.end < current.start) {
                index++;
                intervals[index] = intervals[i];
            } else {
                top.end = Math.max(top.end, current.end);
            }
        }
        for(int i=0; i<=index; i++) {
            System.out.println(intervals[i].start + " " + intervals[i].end);
        }
    }
}

class Interval {
    int start;
    int end;
    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
