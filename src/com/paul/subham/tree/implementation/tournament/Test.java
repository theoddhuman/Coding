package com.paul.subham.tree.implementation.tournament;

/**
 * @author subham.paul
 */
public class Test {
    public static void main(String[] args) {
        int[] a = {1,2,3,4,5,6};
        TournamentTree tournamentTree = new TournamentTree();
        tournamentTree.insertArray(a);
        tournamentTree.levelOrder(a);
    }
}
