import java.util.*;


class Graph {
    private Map<String, Map<String, Integer>> adjList;

    public Graph() {
        adjList = new HashMap<>();
    }

    
    public void addEdge(String source, String destination, int weight) {
        adjList.putIfAbsent(source, new HashMap<>());
        adjList.get(source).put(destination, weight);
        adjList.putIfAbsent(destination, new HashMap<>()); 
    }

    
    public List<String> shortestPath(String start, String end) {
        Map<String, Integer> distance = new HashMap<>();
        Map<String, String> predecessor = new HashMap<>();
        PriorityQueue<String> pq = new PriorityQueue<>(Comparator.comparingInt(distance::get));

        distance.put(start, 0);
        pq.offer(start);

        
        for (String vertex : adjList.keySet()) {
            if (!vertex.equals(start)) {
                distance.put(vertex, Integer.MAX_VALUE);
                predecessor.put(vertex, null);
            }
        }

        
        while (!pq.isEmpty()) {
            String current = pq.poll();
            if (current.equals(end)) {
                break; // Reached the destination
            }
            if (adjList.containsKey(current)) {
                for (String neighbor : adjList.get(current).keySet()) {
                    int newDistance = distance.get(current) + adjList.get(current).get(neighbor);
                    if (newDistance < distance.get(neighbor)) {
                        distance.put(neighbor, newDistance);
                        predecessor.put(neighbor, current);
                        pq.offer(neighbor);
                    }
                }
            }
        }

        
        List<String> path = new ArrayList<>();
        String current = end;
        while (current != null) {
            path.add(current);
            current = predecessor.get(current);
        }
        Collections.reverse(path);
        return path;
    }
}

public class JourneyPlanner {
    public static void main(String[] args) {
        
        Graph graph = new Graph();
        graph.addEdge("A", "B", 10);
        graph.addEdge("A", "C", 15);
        graph.addEdge("B", "D", 12);
        graph.addEdge("B", "F", 15);
        graph.addEdge("C", "E", 10);
        graph.addEdge("D", "E", 2);
        graph.addEdge("D", "F", 1);
        graph.addEdge("F", "E", 5);

        
        List<String> shortestPath = graph.shortestPath("A", "E");
        if (shortestPath.isEmpty()) {
            System.out.println("No path exists.");
        } else {
            System.out.println("Shortest path from A to E: " + shortestPath);
        }
    }
}
