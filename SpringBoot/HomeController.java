package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

import static com.example.demo.connComp.*;

@RestController
public class HomeController {
    ArrayList<Graph> graphs=new ArrayList<>();
    ArrayList<Graph> output=new ArrayList<>();
    private static FileWriter file;
    @Autowired
    ServletContext context;
    @CrossOrigin(origins = "*")
@PostMapping(value="/")
    public String registerUser(@RequestParam("file") MultipartFile[] files) throws IOException, JSONException {
        boolean isExist=new File(context.getRealPath("/userprofile/")).exists();
        if(!isExist){
            new File(context.getRealPath("/userprofile/")).mkdir();
        }
        for(int i=0;i<files.length;i++) {
            MultipartFile file=files[i];
            String modifiedFileName = files[i].getOriginalFilename();
            File serverfile = new File(context.getRealPath("/userprofile" + File.separator + modifiedFileName));
            try {
                System.out.println(file);
                FileUtils.writeByteArrayToFile(serverfile, file.getBytes());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

home(files);
        return "UploadedFile";
    }
    static HashMap<String,Integer> getInter(connComp.Graph l[],int n)
    {
        HashMap<String,Integer> temp = new HashMap();
        for(int i=0;i<n;i++)
        {
            Set<String> set = l[i].map.keySet();
            Iterator<String> iterator = set.iterator();
            while (iterator.hasNext()) {
                String vertex = iterator.next();
                if (temp.containsKey(vertex)) {
                    Integer m= temp.get(vertex);
                    m=m+1;
                    temp.put(vertex,m);
                }
                else
                {
                    temp.put(vertex,1);
                }

            }
        }
        Set<String> set = temp.keySet();
        Iterator<String> iterator = set.iterator();
        Integer count=2;
        HashMap<String,Integer> tempf = new HashMap();
        while (iterator.hasNext())
        {
            String vertex = iterator.next();
            Integer t=temp.get(vertex);
            if(t!=1)
            {
                tempf.put(vertex,count);
                count++;
            }
        }
        return tempf;
    }
    public void home(MultipartFile[] files) throws IOException {

        System.out.println("hii");
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
for(int i=0;i<files.length;i++) {
    String file="src/main/webapp/userprofile/"+files[i].getOriginalFilename();
    Graph graph = mapper.readValue(new File(file), Graph.class);

  graphs.add(graph);

    System.out.println(graph.getNodes());
    //System.out.println(graph.getEdges());
    //System.out.println(graph.getEdge());
}

for(int i=0;i<graphs.size();i++)
{
    System.out.println(graphs.get(i).getNodes());
}
        int n = graphs.size();
        connComp.Graph g[] = new connComp.Graph[n];
        connComp.Graph l[] = new connComp.Graph[n];
        for (int i = 0; i < n; i++)
        {
            g[i]=new connComp.Graph();
            l[i]=new connComp.Graph();
            //System.out.print("Enter the number of edges for graph" + (i + 1));

            int e = graphs.get(i).getEdges().size();
            for (int m = 0; m < e; m++)
            {
                //Scanner ec = new Scanner(System.in);
                //System.out.print("Enter source: ");
                String source = graphs.get(i).getEdges().get(m);
                System.out.print("Enter dest: ");
                String dest = graphs.get(i).getEdge().get(m);
                g[i].addEdge(source, dest, true);
                l[i].addEdge(source, dest, false);
            }
        }


        HashMap<String,Integer> im=getInter(l,n);

        System.out.println(im);


       JSONObject obj=new JSONObject();
       JSONArray nodes=new JSONArray();

for(int i=0;i<files.length;i++)
{
    MultipartFile file=files[i];
    int group=i;
    for(int j=0;j<graphs.get(i).getNodes().size();j++)
    {
        JSONObject temp=new JSONObject();
        int x=i+1;
        int count=1;
        String node=graphs.get(i).getNodes().get(j);
        temp.put("id",graphs.get(i).getNodes().get(j)+x);
             if(im.containsKey(node)) {

                 int color = im.get(node);
                 temp.put("group", color);
             }
else
    temp.put("group",count);
        nodes.add(temp);
    }

}
obj.put("nodes",nodes);
JSONArray links=new JSONArray();
        for(int i=0;i<files.length;i++)
        {
            MultipartFile file=files[i];
            for(int j=0;j<graphs.get(i).getEdges().size();j++)
            {
                JSONObject temp=new JSONObject();
                int x=i+1;
                temp.put("source",graphs.get(i).getEdges().get(j)+x);
                temp.put("target",graphs.get(i).getEdge().get(j)+x);
                links.add(temp);
            }

        }
        obj.put("links",links);
System.out.println(obj);
        try {

            // Constructs a FileWriter given a file name, using the platform's default charset
            file = new FileWriter("C:/Users/OASIS/OG-master/OG-master/src/assets/miserables.json");
            file.write(obj.toJSONString());
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + obj);

        } catch (IOException e) {
            e.printStackTrace();

        }finally {

            try {
                file.flush();
                file.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

       // Scanner sc = new Scanner(System.in);
        //System.out.print("Enter the no. of graphs");

        HashMap<String, List<String>> mergeMap=getAnsMap(g,n);
        HashMap<String, List<String>> actualMap=getAnsMap(l,n);


        HashMap<Integer, List<String>> comp=getComp(mergeMap);
        /*for(int i=0;i<n;i++)
        {
            g[i].graphPrint();
        }*/


        System.out.print("printing disconnected graph : " +actualMap);
        System.out.print("\n");
        System.out.print("printing components : " +comp);
        toJson(actualMap);
        int num=1;
        Iterator hmIterator = comp.entrySet().iterator();
        while (hmIterator.hasNext()) {
            Map.Entry mapElement = (Map.Entry) hmIterator.next();
            Object vertex = mapElement.getKey();
            List<String> comp1 = comp.get(vertex);
            //  System.out.print(comp1);
            findTopo(comp1, actualMap,num);
            num++;
        }






        int q=comp.size();
        forYaml y[] = new forYaml[q];
        for(int i=0;i<q;i++)
        {
            y[i]=new forYaml();
            y[i].nodes=comp.get(i+1);
            List<String> temp=comp.get(i+1);
            ListIterator<String> iterator = temp.listIterator();
            while (iterator.hasNext())
            {
                String x=iterator.next();
                List<String> temp4=actualMap.get(x);
                if(temp4.size()==0)
                    continue;
                // y[i].edge.add(x);
                ListIterator<String> iterator4 = temp4.listIterator();
                while (iterator4.hasNext())
                {
                    String des=iterator4.next();
                    y[i].edge.add(x);
                    y[i].edges.add(des);
                }

            }
        }
           System.out.print(y[0].nodes);
          System.out.print(y[0].edge);
           System.out.print(y[0].edges);

        List<GraphYaml> graph1= new ArrayList<GraphYaml>();
        for(int i=0;i<q;i++) {
            int x=i+1;
           String graph="graph"+x;
            graph1.add(new GraphYaml(graph, y[i].nodes, y[i].edge, y[i].edges));


        }
        GraphAns graphs = new GraphAns(graph1);
        ObjectMapper om=new ObjectMapper(new YAMLFactory());
        om.writeValue(new File("src/main/resources/application.yml"), graphs);

    }


}
