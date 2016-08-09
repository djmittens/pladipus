package me.ngrid.hackerrank.domino;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 *
 */
public class Election {
    static String electionWinner(String[] votes) {
        Map<String, Integer> participants = new HashMap<>();
        for(int i = 0; i < votes.length; i ++) {
            Integer v = participants.getOrDefault( votes[i], 0);
            participants.put(votes[i], v + 1);
        }

        PriorityQueue<Map.Entry<String, Integer>> sorted = new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                int out = o2.getValue().compareTo(o1.getValue());
                if(out == 0) {
                    out = o1.getKey().compareToIgnoreCase(o2.getKey());
                }
                return out;
            }
        }
        );

        participants.entrySet().forEach(sorted::add);

        return String.valueOf(sorted.poll().getKey());
    }
    public static class LinkedListNode{
        int val;
        LinkedListNode next;

        LinkedListNode(int node_value) {
            val = node_value;
            next = null;
        }
    };

    public static LinkedListNode _insert_node_into_singlylinkedlist(LinkedListNode head, LinkedListNode tail, int val){
        if(head == null) {
            head = new LinkedListNode(val);
            tail = head;
        }
        else {
            tail.next = new LinkedListNode(val);
            tail = tail.next;
        }
        return tail;
    }

    static LinkedListNode removeNodes(LinkedListNode list, int x) {
        LinkedListNode prev = null;
        LinkedListNode head = list;
        LinkedListNode curr = list;

        while(curr != null) {
            if(curr.val > x) {
                if(prev == null) {
                    head = curr.next;
                    curr = curr.next;
                } else {
                    prev.next = curr.next;
                    curr = curr.next;
                }
            } else {
                prev = curr;
                curr = curr.next;
            }
        }

        return head;
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
//        final String fileName = System.getenv("OUTPUT_PATH");
//        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        LinkedListNode res;

        int _list_size = 0;
        _list_size = Integer.parseInt(in.nextLine());
        int _list_i;
        int _list_item;
        LinkedListNode _list = null;
        LinkedListNode _list_tail = null;
        for(_list_i = 0; _list_i < _list_size; _list_i++) {
            _list_item = Integer.parseInt(in.nextLine().trim());
            if(_list_i == 0) {
                _list = _insert_node_into_singlylinkedlist(_list, _list_tail, _list_item);
                _list_tail = _list;
            }
            else {
                _list_tail = _insert_node_into_singlylinkedlist(_list, _list_tail, _list_item);
            }
        }

        int _x;
        _x = Integer.parseInt(in.nextLine().trim());

        res = removeNodes(_list, _x);
    }
}
