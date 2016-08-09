package me.ngrid.hackerrank.graphtheory;

import java.util.*;

public class Dijkstras {
    static int[] shortestPaths(int numNodes, Node s) {
        int[] output = new int[numNodes];
        Arrays.fill(output, Integer.MAX_VALUE);
        PriorityQueue<Node> nodes = new PriorityQueue<>(numNodes, (o1, o2) -> Integer.compare(o1.weight, o2.weight));
        nodes.add(s);
        s.weight = 0;
        while(!nodes.isEmpty()) {
            Node n = nodes.poll();
            for(Edge e : n.edges) {
                e.otherNode.weight = Math.min(n.weight + e.weight, e.otherNode.weight);

                if(nodes.contains(e.otherNode)) nodes.remove(e.otherNode);
                if(!e.otherNode.locked) nodes.add(e.otherNode);
            }
            output[n.id - 1] = Math.min(output[n.id - 1], n.weight);
            n.locked = true;
        }

        return output;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        sc.nextLine();
        for(int c = 0; c < cases; c++) {
            int numNodes = sc.nextInt();
            int numEdges = sc.nextInt();
            sc.nextLine();

            HashMap<Integer, Node> nodes = new HashMap<>();
            for(int e = 0; e < numEdges; e++) {
                int n1 = sc.nextInt();
                int n2 = sc.nextInt();
                int w = sc.nextInt();
                sc.nextLine();

                Node node1 = nodes.get(n1);
                Node node2 = nodes.get(n2);
                node1 = node1 == null ? new Node(n1): node1;
                node2 = node2 == null ? new Node(n2): node2;

                Edge edge1 = new Edge();
                edge1.weight = w;
                edge1.otherNode = node2;

                Edge edge2 = new Edge();
                edge2.weight = w;
                edge2.otherNode = node1;

                node1.edges.add(edge1);
                node2.edges.add(edge2);

                nodes.put(n1, node1);
                nodes.put(n2, node2);
            }

            int s = sc.nextInt();
            int[] out = shortestPaths(numNodes, nodes.get(s));

            for(int i = 0; i < numNodes; i ++) {
                if(out[i] == Integer.MAX_VALUE) {
                    System.out.print("-1 ");
                } else if(out[i] != 0) {
                    System.out.print(out[i] + " ");
                }
            }
            System.out.print("\n");
        }
    }

    private static class Node {
        int id;
        List<Edge> edges = new ArrayList<>();
        int weight = Integer.MAX_VALUE;
        boolean locked = false;

        Node(int id) {
            this.id = id;
        }
    }

    private static class Edge {
        int weight;
        Node otherNode;
    }
}
