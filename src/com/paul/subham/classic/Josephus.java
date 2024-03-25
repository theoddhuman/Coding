package com.paul.subham.classic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author subham.paul
 *
 * There are N people standing in a circle waiting to be executed.
 * The counting out begins at some point in the circle and proceeds around the circle in a fixed direction.
 * In each step, a certain number of people are skipped and the next person is executed.
 * The elimination proceeds around the circle (which is becoming smaller and smaller as the executed people are removed),
 * until only the last person remains, who is given freedom.
 *
 * Given the total number of persons N and a number k which indicates that k-1 persons are skipped and the kth person is killed in a circle.
 * The task is to choose the person in the initial circle that survives.
 *
 * 1. Josephus problem (Recursive)
 * 2. Josephus Problem (Iterative)
 * 3. Josephus problem (Using queue)
 * 4. Josephus problem (Linear - Iterative)
 * 5. Josephus problem (Linear - Recursive)
 *
 */
public class Josephus {
    public static void main(String[] args) {
        System.out.println(joshQueue(5, 2));
    }

    /**
     * Josephus problem (Recursive)
     *
     * TC: O(n^2)
     * SC: O(n)
     */
    public static int joshRecursive(int n, int k) {
        List<Integer> persons = new ArrayList<>();
        for(int i=1; i<=n; i++) {
            persons.add(i);
        }
        return joshRecUtil(persons, k-1, 0);
    }

    private static int joshRecUtil(List<Integer> persons, int skip, int index) {
        if(persons.size() == 1) {
            return persons.get(0);
        }
        index = (index + skip) % persons.size();
        persons.remove(index);
        return joshRecUtil(persons, skip, index);
    }

    /**
     * Josephus problem (Iterative)
     *
     * TC: O(n^2)
     * SC: O(n)
     */
    public static int joshIterative(int n, int k) {
        List<Integer> persons = new ArrayList<>();
        for(int i=1; i<=n; i++) {
            persons.add(i);
        }
        k--;
        int index = 0;
        while(persons.size() > 1) {
            index = ( index + k) % persons.size();
            persons.remove(index);
            index %= persons.size();
        }
        return persons.get(0);
    }

    /**
     * Josephus problem (Using queue)
     *
     * TC: O(n*k)
     * SC: O(n)
     */
    public static int joshQueue(int n, int k) {
        Queue<Integer> queue = new LinkedList<>();
        for(int i=1; i<=n; i++) {
            queue.add(i);
        }
        k--;
        while(queue.size() != 1) {
            for(int i=0; i<k; i++) {
                int temp = queue.remove();
                queue.add(temp);
            }
            queue.remove();
        }
        return queue.peek();
    }

    /**
     * Josephus problem (Linear - Iterative)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static int joshLinearIterative(int n, int k) {
        int ans = 0;
        for(int i=1; i<=n; i++) {
            ans = (ans+k)%i;
        }
        return ans+1;
    }

    /**
     * Josephus problem (Linear - Recursive)
     *
     * The problem has the following recursive structure. josephus(n, k) = (josephus(n – 1, k) + k-1) % n + 1 and josephus(1, k) = 1
     * After the first person (kth from the beginning) is killed, n-1 persons are left.
     * Make recursive call for Josephus(n – 1, k) to get the position with n-1 persons.
     * But the position returned by Josephus(n – 1, k) will consider the position starting from k%n + 1.
     * So make adjustments to the position returned by Josephus(n – 1, k).
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int joshLinearRecursive(int n, int k) {
        if(n==1) {
            return 1;
        }
        return (joshLinearRecursive(n-1, k) + k-1) % n + 1;
    }
}
