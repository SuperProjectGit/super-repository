package com.common.algorithm;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 整体思路：利用动态规划算法拆分问题，最后将问题结果集合并得到想要的结果。
 * 动态规划(深度优先BFS和广度优先DFS)
 * note:注意全局变量线程安全问题。
 * 可以在init()方法中初始图数据，在main()方法中输入想要计算的结果
 * 环境：jdk8以上
 * TODO 矩阵图方式解决此问题
 * @author super
 * @create 2019-11-17 11:23
 **/
public class Trains {

    /**
     * 数据图，使用线程安全的map
     */
    public Map<String, TownNode> towns = new ConcurrentHashMap<>();

    /**
     * 标记节点集合
     */
    public List<TownNode> markList = new ArrayList<>();

    /**
     * 初始化数据集
     */
    public void init() {
        List<String> list = Arrays.asList("AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7");
        list.forEach(t -> {
            String currentTownName = t.substring(0,1);
            String nextTownName = t.substring(1,2);
            String distance = t.substring(2, 3);
            Integer intDistance = Integer.valueOf(distance);
            if (intDistance < 0) {
                return;
            }
            Route route = new Route(currentTownName);
            route.setNextNode(new Route(nextTownName));
            route.setDistance(intDistance);
            add(route);
        });
    }

    /**
     * 添加路线，旧节点数据会被最新的数据覆盖
     * @param route
     */
    public void add(Route route) {
        if(null == route || null == route.getNextNode()) {
            return;
        }
        String townName = route.getCurrentNode();
        TownNode currentTown = towns.get(townName);
        if (null == currentTown) {
            currentTown = new TownNode(townName);
        }
        if (null == currentTown.getDirectTown()) {
            currentTown.setDirectTown(new HashMap<>());
        }
        String nextTownName = route.getNextNode().getCurrentNode();
        TownNode nextTown = towns.get(nextTownName);
        if (null == nextTown) {
            nextTown = new TownNode(nextTownName);
            towns.put(nextTownName, nextTown);
        }
        currentTown.getDirectTown().put(nextTown, route.distance);
        towns.put(townName, currentTown);
    }

    /**
     * 获取路线距离，使用链表遍历
     * 时间复杂度O(n)
     * @param route
     * @return
     */
    public String getDistance(Route route) {
        if (null == route) {
            return "NO SUCH ROUTE";
        }
        Integer resultDistance = 0;
        while (null != route) {
            TownNode currentTown = towns.get(route.getCurrentNode());
            if (null == currentTown) {
                break;
            }
            if (null != route.getNextNode()) {
                TownNode nextTown = towns.get(route.getNextNode().getCurrentNode());
                Map directTowns = currentTown.getDirectTown();
                if (null == directTowns) {
                    return "NO SUCH ROUTE";
                }
                Integer distance = currentTown.getDirectTown().get(nextTown);
                if (null == distance) {
                    return "NO SUCH ROUTE";
                }
                resultDistance += distance;
            }
            route = route.nextNode;
        }
        return resultDistance.toString();
    }

    /**
     * 使用深度优先遍历(递归方式，数据量大的情况会出现栈溢出)
     * TODO 迭代
     * @param startTown
     * @param endTown
     * @param maxStop
     * @return
     */
    public Integer getTripsByMaxStop(String startTown, String endTown, Integer maxStop) {
        TownNode startTownNode = towns.get(startTown);
        Map<TownNode, Integer> directTown = startTownNode.getDirectTown();
        Integer[] nums = {0};
        if (null == directTown || directTown.size() == 0) {
            return nums[0];
        }
        if (maxStop >0) {
            directTown.forEach((k,v) -> {
                if (k.getName().equals(endTown)) {
                    nums[0] = nums[0] + 1;
                }
            });
        }
        final Integer tempStop = maxStop-1;
        if (tempStop > 0) {
            directTown.forEach((k,v) -> nums[0] += getTripsByMaxStop(k.getName(), endTown, tempStop));
            return nums[0];
        }
        return nums[0];
    }

    /**
     * 深度遍历 递归
     * TODO 迭代
     * @param startTown
     * @param endTown
     * @param exactlyStop
     * @return
     */
    public Integer getTripsByExactlyStop(String startTown, String endTown, Integer exactlyStop) {
        if (exactlyStop <= 0) {
            return 0;
        }
        TownNode startTownNode = towns.get(startTown);
        Map<TownNode, Integer> directTown = startTownNode.getDirectTown();
        Integer[] nums = {0};
        if (null == directTown || directTown.size() == 0) {
            return nums[0];
        }
        if (exactlyStop == 1) {
            directTown.forEach((k,v) -> {
                if (k.getName().equals(endTown)) {
                    nums[0] = nums[0] + 1;
                }
            });
            return nums[0];
        }
        final Integer tempStop = exactlyStop - 1;
        if (tempStop > 0) {
            directTown.forEach((k,v) -> nums[0] += getTripsByExactlyStop(k.getName(), endTown, tempStop));
        }
        return nums[0];
    }

    /**
     * 深度遍历，增加遍历节点标记
     * @param startTown
     * @param endTown
     * @return
     */
    public Integer shortestRoute(String startTown, String endTown) {
        TownNode startTownNode = towns.get(startTown);
        Map<TownNode, Integer> directTown = startTownNode.getDirectTown();
        Integer[] nums = {0};
        if (null == directTown || directTown.size() == 0) {
            nums[0] = -1;
            return nums[0];
        }
        if (markList.contains(startTownNode)) {
            nums[0] = -1;
            return nums[0];
        }
        markList.add(startTownNode);
        if (directTown.containsKey(new TownNode(endTown))) {
            Integer temp = directTown.get(new TownNode(endTown));
            if (nums[0] == 0 || (nums[0] > temp && nums[0] > -1)) {
                nums[0] = temp;
            }
            markList.remove(startTownNode);
            return nums[0];
        }
        int[] flag = {-1};
        directTown.forEach((k,v) -> {
            Integer temp = shortestRoute(k.getName(), endTown);
            if (-1 == temp) {
                return;
            }
            flag[0] = 1;
            if (nums[0] == 0 || nums[0] > (temp + v)) {
                nums[0] = temp + v;
            }
        });
        if (flag[0] == -1) {
            return -1;
        }
        return nums[0];
    }

    /**
     * TODO 链表环问题未解决，待解决
     * @param startTown
     * @param endTown
     * @param maxDistance
     * @return
     */
    public Integer getTripsByMaxDistance(String startTown, String endTown, Integer maxDistance) {
        if (maxDistance <= 0) {
            return 0;
        }
        TownNode startTownNode = towns.get(startTown);
        Map<TownNode, Integer> directTown = startTownNode.getDirectTown();
        Integer[] nums = {0};
        if (null == directTown || directTown.size() == 0) {
            nums[0] = -1;
            return nums[0];
        }
        if (directTown.containsKey(new TownNode(endTown))) {
            Integer temp = directTown.get(new TownNode(endTown));
            if (temp == maxDistance) {
                nums[0] = nums[0] + 1;
                return nums[0];
            }
        }
        final Integer tempDistance = maxDistance - 1;
        directTown.forEach((k,v) -> {
            Integer temp = getTripsByMaxDistance(k.getName(), endTown, tempDistance);
            nums[0] = nums[0] + temp;
        });
        return nums[0];
    }

    /**
     * 统一封装最短路径方式，计算完一次之后，状态节点清空，增加锁，防止多线程并发
     * @param startTown
     * @param endTown
     * @return
     */
    public Integer commonShortestLength(String startTown, String endTown) {
        Lock lock = new ReentrantLock();// 使用非公平锁，性能更好
        try {
            lock.lock();
            Integer temp = shortestRoute(startTown, endTown);
            markList.clear();
            return temp;
        } finally {
            lock.unlock();
        }

    }

    /**
     * main 函数，程序执行入口
     * @param args
     */
    public static void main(String[] args) {
        Trains trains = new Trains();
        trains.init();
        Route ABC = trains.generate("A-B-C");
        System.out.println(trains.getDistance(ABC));
        Route AD = trains.generate("A-D");
        System.out.println(trains.getDistance(AD));
        Route ADC = trains.generate("A-D-C");
        System.out.println(trains.getDistance(ADC));
        Route AEBCD = trains.generate("A-E-B-C-D");
        System.out.println(trains.getDistance(AEBCD));
        Route AED = trains.generate("A-E-D");
        System.out.println(trains.getDistance(AED));
        System.out.println("============================");
        System.out.println(trains.getTripsByMaxStop("C", "C", 3));
        System.out.println(trains.getTripsByExactlyStop("A", "C", 4));
        System.out.println(trains.commonShortestLength("A", "C"));
        System.out.println(trains.commonShortestLength("B", "B"));
        System.out.println(trains.getTripsByMaxDistance("C", "C", 30));
    }

    /**
     * 生成路线链表
     * @param route (A-B-C)
     * @return
     */
    public Route generate(String route) {
        String[] nodes = route.split("-");
        Route resultRoute = new Route(nodes[0]);
        Route temp = resultRoute;
        for(int i = 0; i < nodes.length; i++) {
            if (i == 0) {
                continue;
            }
            Route nextRoute = new Route(nodes[i]);
            temp.setNextNode(nextRoute);
            temp = nextRoute;
        }
        return resultRoute;
    }

    /**
     * 节点数据
     * 重写hashcode 和 equals方法，方便后续对象比较
     */
    public static class TownNode {
        /**
         * 节点名称
         */
        private String name;
        /**
         * 直接指向 节点集
         */
        private Map<TownNode, Integer> directTown;
        public TownNode(String townName) {
            this.name = townName;
        }

        @Override
        public boolean equals(Object obj) {
            return this.name.equals(((TownNode)obj).getName());
        }

        @Override
        public int hashCode() {
            return this.name.hashCode();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Map<TownNode, Integer> getDirectTown() {
            return directTown;
        }

        public void setDirectTown(Map<TownNode, Integer> directTown) {
            this.directTown = directTown;
        }
    }

    /**
     * 路线节点类
     */
    public static class Route {
        /**
         * 当前节点名称
         */
        public String currentNode;
        /**
         * 后驱节点
         */
        public Route nextNode;
        /**
         * 节点距离
         */
        public Integer distance;

        public Route(String currentNode) {
            this.currentNode = currentNode;
        }

        public String getCurrentNode() {
            return currentNode;
        }

        public void setCurrentNode(String currentNode) {
            this.currentNode = currentNode;
        }

        public Route getNextNode() {
            return nextNode;
        }

        public void setNextNode(Route nextNode) {
            this.nextNode = nextNode;
        }

        public Integer getDistance() {
            return distance;
        }

        public void setDistance(Integer distance) {
            this.distance = distance;
        }
    }
}
