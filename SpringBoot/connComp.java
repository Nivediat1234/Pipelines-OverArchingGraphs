package com.example.demo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class connComp
{
    static class Graph {

        Map<String, List<String>> map = new HashMap<>();

        public void addVertex(String s) {
            map.put(s, new LinkedList<String>());
        }

        public void addEdge(String source,
                            String destination,
                            boolean bidirectional) {

            if (!map.containsKey(source))
                addVertex(source);

            if (!map.containsKey(destination))
                addVertex(destination);

            map.get(source).add(destination);
            if (bidirectional == true) {
                map.get(destination).add(source);
            }
        }


        public void graphPrint() {
            Set<String> set = map.keySet();
            Iterator<String> iterator = set.iterator();

            while (iterator.hasNext()) {
                String vertex = iterator.next();
                System.out.print("Vertex " + vertex + " is connected to: ");
                List<String> list = map.get(vertex);
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(list.get(i) + " ");
                }
                System.out.println();
            }
        }

    }
    static HashMap<String, List<String>> getAnsMap(Graph g[],int n)
    {
        HashMap<String, List<String>> temp = new HashMap();
        for(int i=0;i<n;i++)
        {
            Set<String> set = g[i].map.keySet();
            Iterator<String> iterator = set.iterator();
            while (iterator.hasNext()) {
                String vertex = iterator.next();
                if (temp.containsKey(vertex)) {
                    List<String> inter = temp.get(vertex);
                    List<String> list = g[i].map.get(vertex);
                    for (int q = 0; q < list.size(); q++) {
                        String x=list.get(q);
                        if(!(inter.contains(x)))
                            inter.add(x);
                    }
                    temp.put(vertex,inter);
                }
                else
                {
                    List<String> initial = new ArrayList<String>();
                    List<String> list = g[i].map.get(vertex);
                    for (int q = 0; q < list.size(); q++) {
                        String x=list.get(q);
                        initial.add(x);
                    }
                    temp.put(vertex,initial);
                }

            }
        }
        return temp;
    }

    static HashMap<Integer, List<String>> getComp(HashMap<String, List<String>> mergeMap)
    {
        Map<String,Boolean> visited=new HashMap<>();
        HashMap<Integer, List<String>> comp=new HashMap<>();
        Set<String> set = mergeMap.keySet();
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext())
        {
            String vertex = iterator.next();
            visited.put(vertex,false);
        }
        // System.out.println(visited);
        int n=visited.size();
        //System.out.println(n);
        int count=1;
        Iterator hmIterator = visited.entrySet().iterator();
        while (hmIterator.hasNext())
        {
            Map.Entry mapElement = (Map.Entry)hmIterator.next();
            Object vertex=mapElement.getKey();
            String fvertex=vertex.toString();

            if (visited.get(fvertex)==false)
            {
                List<String> co=new ArrayList<>();
                DFSUtil(fvertex,visited,mergeMap,co);
                comp.put(count,co);
                count++;
            }
        }
        return comp;
    }
    static void DFSUtil(String vertex,Map<String,Boolean> visited,HashMap<String,List<String>> mergeMap,List<String> co)
    {
        visited.put(vertex,true);
        co.add(vertex);
        List<String>inter=mergeMap.get(vertex);
        ListIterator<String>iterator = inter.listIterator();
        while (iterator.hasNext()) {
            String x=iterator.next();
            if (visited.get(x)==false)
            {
                DFSUtil(x,visited,mergeMap,co);
            }
        }
    }

    static void toJson(HashMap<String,List<String>> actualMap)
    {
        JSONArray array = new JSONArray();
        Iterator hmIterator = actualMap.entrySet().iterator();
        while (hmIterator.hasNext())
        {
            Map.Entry mapElement = (Map.Entry)hmIterator.next();
            Object vertex=mapElement.getKey();
            String fvertex=vertex.toString();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",fvertex);
            jsonObject.put("group",1);
            array.add(jsonObject);
        }
        JSONObject jsonObjectans = new JSONObject();
        jsonObjectans.put("nodes",array);
        JSONArray array1 = new JSONArray();
        Iterator mIterator = actualMap.entrySet().iterator();
        while (mIterator.hasNext())
        {
            Map.Entry mapElement = (Map.Entry)mIterator.next();
            Object vertex=mapElement.getKey();
            String fvertex=vertex.toString();
            List<String>inter=actualMap.get(fvertex);
            ListIterator<String>iterator = inter.listIterator();
            while (iterator.hasNext()) {
                String x = iterator.next();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("source",fvertex);
                jsonObject.put("target",x);
                array1.add(jsonObject);
            }
        }
        jsonObjectans.put("links",array1);
        System.out.println("\n");
        System.out.println("JSON file created: "+jsonObjectans);
        try{
            FileWriter file = new FileWriter("C:/Users/OASIS/OG-master/OG-master/src/assets/graph.json");
            file.write(jsonObjectans.toJSONString());
            file.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    static void findTopo(List<String> comp1,HashMap<String, List<String>> actualMap,int num)
    {
        Stack<String> stack = new Stack<String>();
        Map<String,Boolean> visited=new HashMap<>();
        for(int i=0;i<comp1.size();i++)
        {
            visited.put(comp1.get(i),false);
        }
        Iterator hmIterator = visited.entrySet().iterator();
        while (hmIterator.hasNext())
        {
            Map.Entry mapElement = (Map.Entry)hmIterator.next();
            Object vertex=mapElement.getKey();
            String fvertex=vertex.toString();
            //  System.out.print(fvertex);
            if (visited.get(fvertex)==false)
            {
                topologicalSortUtil(fvertex, visited, stack,actualMap);
            }
        }
        System.out.print("Topological order for component " +num +" :" );
        while (stack.empty()==false)
            System.out.print(stack.pop() + " ");
        System.out.print("\n");
    }
    static void topologicalSortUtil(String vertex,Map<String,Boolean> visited,Stack stack,HashMap<String, List<String>> actualMap)
    {
        visited.put(vertex,true);
        List<String>inter=actualMap.get(vertex);
        ListIterator<String>iterator = inter.listIterator();
        while (iterator.hasNext()) {
            String x=iterator.next();
            if (visited.get(x)==false)
            {
                topologicalSortUtil(x,visited,stack,actualMap);
            }
        }
        stack.push(new String(vertex));
    }




}
