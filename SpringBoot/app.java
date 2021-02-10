package com.example.demo;

import java.util.*;
public class app {
    static class Graph {
        HashMap<Object, LinkedList<Object>> adjList = new HashMap();
        public Graph(ArrayList<Object> vertices) {
            for (int i = 0; i < vertices.size(); i++) {
                Object vertex = vertices.get(i);
                LinkedList<Object> list = new LinkedList<>();
                adjList.put(vertex, list);
            }
        }

        public void addEdge(Object source, Object destination) {
            LinkedList<Object> list;
            list = adjList.get(source);
            list.addFirst(destination);
            adjList.put(source, list);
        }

        public void printGraph() {
            Set<Object> set = adjList.keySet();
            Iterator<Object> iterator = set.iterator();

            while (iterator.hasNext()) {
                Object vertex = iterator.next();
                System.out.print("Vertex " + vertex + " is connected to: ");
                LinkedList<Object> list = adjList.get(vertex);
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(list.get(i) + " ");
                }
                System.out.println();
            }
        }
    }
    static HashMap<Object, HashSet<Object>>  merge(Graph graph1,Graph graph2)
    {
        Set<Object> set = graph1.adjList.keySet();
        int count=0;
        Iterator<Object> iterator = set.iterator();
        ArrayList<Object> vertices = new ArrayList<>();
        HashSet<Object> helper = new HashSet<Object>();
        HashMap<Object, HashSet<Object>> temp = new HashMap();
        while (iterator.hasNext())
        {
            Object vertex = iterator.next();
            boolean found= graph2.adjList.containsKey(vertex);
            if(found)
            {
                count=1;
                HashSet<Object> help = new HashSet<Object>();
                LinkedList<Object> list1 = graph1.adjList.get(vertex);
                for (int i = 0; i < list1.size(); i++) {
                    help.add(list1.get(i));
                }
                LinkedList<Object> list2 = graph2.adjList.get(vertex);
                for (int i = 0; i < list2.size(); i++) {
                    help.add(list2.get(i));
                }
                temp.put(vertex,help);
            }
        }
        if(count==1)
        {
            Set<Object> set3 = graph1.adjList.keySet();
            Iterator<Object> iterator3 = set.iterator();

            while (iterator3.hasNext()) {
                Object vertex = iterator3.next();
                if(temp.containsKey(vertex))
                    continue;
                LinkedList<Object> list = graph1.adjList.get(vertex);
                HashSet<Object> help = new HashSet<Object>();
                for (int i = 0; i < list.size(); i++) {

                    help.add(list.get(i));
                }
                temp.put(vertex,help);
            }
            Set<Object> set4 = graph2.adjList.keySet();
            Iterator<Object> iterator4 = set4.iterator();
            while (iterator4.hasNext()) {
                Object vertex = iterator4.next();
                if(temp.containsKey(vertex))
                    continue;
                LinkedList<Object> list = graph2.adjList.get(vertex);
                HashSet<Object> help = new HashSet<Object>();
                for (int i = 0; i < list.size(); i++) {

                    help.add(list.get(i));
                }
                temp.put(vertex,help);
            }


        }
        return temp;

    }

    public static void main(String[] args)
    {
        ArrayList<Object> vertices1 = new ArrayList<>();
        vertices1.add('A');
        vertices1.add('B');
        vertices1.add('C');
        vertices1.add('D');
        Graph graph1 = new Graph(vertices1);
        graph1.addEdge('A', 'B');
        graph1.addEdge('B', 'C');
        graph1.addEdge('C', 'D');

        ArrayList<Object> vertices2 = new ArrayList<>();
        vertices2.add('P');
        vertices2.add('B');
        vertices2.add('R');
        vertices2.add('S');
        Graph graph2 = new Graph(vertices2);
        graph2.addEdge('P', 'B');
        graph2.addEdge('B', 'R');
        graph2.addEdge('R', 'S');

        HashMap<Object, HashSet<Object>> ans= merge(graph1,graph2);
        if (ans.isEmpty())
        {
            graph1.printGraph();
            graph2.printGraph();
        }
        else
        {
            System.out.println(ans);
        }
    }
}

