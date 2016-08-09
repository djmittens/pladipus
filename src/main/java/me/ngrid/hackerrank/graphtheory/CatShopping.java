package me.ngrid.hackerrank.graphtheory;

import java.util.*;

public class CatShopping {

    static int findShortestTimeToFish(Set<Integer> shoppingList, Market start, Market end, Graph g) {
        Queue<PathNode> forward = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.weight, o2.weight));
        Queue<PathNode> backward = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.weight, o2.weight));

        forward.offer(new PathNode(0, start));
        backward.offer(new PathNode(0, start));

        while(!forward.isEmpty() && !backward.isEmpty()) {
            PathNode f = forward.poll();
            for(Road road : f.node.roads) {
                PathNode r = new PathNode(f.weight + road.length, road.otherMarket);
                r.fish.addAll(f.fish);
                forward.remove(r);
                forward.offer(r);
            }
            PathNode b = backward.poll();
        }
        return -1;
    }

    public static void main(String[] args) {
        Graph g = new Graph();
        Scanner sc = new Scanner(System.in);
        g.numMarkets = sc.nextInt();
        g.numRoads = sc.nextInt();
        g.numFish = sc.nextInt();
        sc.nextLine();

        for(int i = 1; i <= g.numMarkets; i ++ ) {
            List<Integer> fishies = new ArrayList<>();
            int n = sc.nextInt();
            for(int k = 0; k < n; k++) {
                fishies.add(sc.nextInt());
            }
            g.addMarket(i, fishies);
            sc.nextLine();
        }

        for(int i = 0; i < g.numRoads; i++) {
            g.addRoad(sc.nextInt(), sc.nextInt(), sc.nextInt());
            sc.nextLine();
        }

//        System.out.println(findShortestTimeToFish(g.getMarket(1), g.getMarket(g.numMarkets), g));
    }
}

class Graph {
    int numMarkets;
    int numRoads;
    int numFish;

    private Map<Integer, Market> markets = new HashMap<>();

    void addMarket(int id, List<Integer> fishies) {
        markets.put(id, new Market(id, fishies));
    }

    void addRoad(int a, int b, int w) {
        Market m1 = markets.get(a);
        Market m2 = markets.get(b);

        Road e1 = new Road(w, m2);
        Road e2 = new Road(w, m1);
        m1.roads.add(e1);
        m2.roads.add(e2);
    }

    Market getMarket(int i) {
        return markets.get(i);
    }
}

class PathNode {
    int weight;
    Market node;
    Set<Integer> fish = new HashSet<>();

    public PathNode(int weight, Market node) {
        this.weight = weight;
        this.node = node;
        fish.addAll(node.fish);
    }

    @Override
    public boolean equals(Object obj) {
        return node.equals(obj);
    }
}

class Market {
    int id;
    List<Integer> fish;
    List<Road> roads;

    Market(int id, List<Integer> fish) {
        this.id = id;
        this.fish = fish;
        this.roads = new ArrayList<>();
    }
}

class Road {
    int length;
    Market otherMarket;

    Road(int length, Market otherMarket) {
        this.length = length;
        this.otherMarket = otherMarket;
    }
}
