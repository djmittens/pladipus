package me.ngrid.misc;

import java.util.*;

/**
 * There is a bus dispatch company that signs up to pickup passengers at bus stops.
 * Each bus has a capacity and will traverse all stops in order.  Only one bus can stop at a stop at any time.
 * and cannot change up the order of the stops it makes.
 *
 * The goal of this algorithm is to figure out the smallest amount of buses that is required to service each of the
 * stops.
 */

public class BusRoutes {
    private int busCapacity;
    private TreeMap<Integer, Station> stations;

    public BusRoutes() {
        busCapacity = 2;
        stations = new TreeMap<>();
    }

    public void addRider(int source, int target){
        if(stations.containsKey(source)) {
            stations.get(source).departing++;
        }
        else {
            stations.put(source, new Station(source, 0, 1));
        }

        if(stations.containsKey(target)) {
            stations.get(target).arriving++;
        }
        else {
            stations.put(target, new Station(target, 1, 0));
        }
    }

    public int getMinBusses () {
        boolean tooFull = true;
        int count = 0;
        while(tooFull) {
            tooFull = false;
            ++count;
            int capacity = this.busCapacity;
            for(Station s : stations.values()) {
                capacity -= s.departing;
                capacity += s.arriving;
                if(capacity < 0) {
                    tooFull = true;
                    s.departing += capacity;
                    s.arriving = 0;
                    capacity = 0;
                }
                else {
                    s.arriving = 0;
                    s.departing = 0;
                }
            }
        }

        return count;
    }

    public void setBusCapacity(int busCapacity) {
        this.busCapacity = busCapacity;
    }

    private class Station {
        private int id, arriving, departing;

        public Station() {
            this.arriving = 0;
            this.departing = 0;
        }

        public Station(int id, int arriving, int departing) {
            this.id = id;
            this.arriving = arriving;
            this.departing = departing;
        }
    }
}
