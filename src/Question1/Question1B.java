package Question1;

import java.util.*;

public class Question1B {
    public static void main(String[] args) {
        int[][] edges = {{0,1}, {0,2}, {1,3}, {1,6}, {2,4}, {4,6}, {4,5}, {5,7}};
        int target = 4;
        Set<Integer> impacted = findImpactedDevices(edges, target);
        System.out.println("Nodes directly connected to target node " + impacted);
        System.out.println("Nodes set:");
        int[][] newEdges = removeImpactedNode(edges, target);
        List<Set<Integer>> components = findConnectedComponents(newEdges);
        for (Set<Integer> component : components) {
            System.out.println(component);
        }
    }


    public static Set<Integer> findImpactedDevices(int[][] edges, int target) {
        Set<Integer> visited = new HashSet<>();
        visited.add(target);
        Set<Integer> impacted = new HashSet<>();
        for (int[] edge : edges) {
            if (edge[0] == target && !visited.contains(edge[1])) {
                impacted.add(edge[1]);
            } else if (edge[1] == target && !visited.contains(edge[0])) {
                impacted.add(edge[0]);
            }
        }
        return impacted;
    }

    public static int[][] removeImpactedNode(int[][] edges, int target) {
        List<int[]> newEdges = new ArrayList<>();
        for (int[] edge : edges) {
            if (edge[0] != target && edge[1] != target) {
                newEdges.add(edge);
            }
        }
        int[][] result = new int[newEdges.size()][2];
        for (int i = 0; i < newEdges.size(); i++) {
            result[i] = newEdges.get(i);
        }
        return result;
    }

    public static List<Set<Integer>> findConnectedComponents(int[][] edges) {
        Map<Integer, Set<Integer>> graph = buildGraph(edges);
        List<Set<Integer>> components = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        for (int node : graph.keySet()) {
            if (!visited.contains(node)) {
                Set<Integer> component = new HashSet<>();
                dfs(node, graph, visited, component);
                components.add(component);
            }
        }
        return components;
    }

    public static Map<Integer, Set<Integer>> buildGraph(int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            graph.putIfAbsent(u, new HashSet<>());
            graph.putIfAbsent(v, new HashSet<>());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        return graph;
    }

    public static void dfs(int node, Map<Integer, Set<Integer>> graph, Set<Integer> visited, Set<Integer> component) {
        visited.add(node);
        component.add(node);
        for (int neighbor : graph.get(node)) {
            if (!visited.contains(neighbor)) {
                dfs(neighbor, graph, visited, component);
            }
        }
    }
}
