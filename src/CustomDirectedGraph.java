
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User01
 */
public class CustomDirectedGraph {
    
    private static DefaultDirectedGraph<String, DefaultEdge> directedGraph = new DefaultDirectedGraph<>(DefaultEdge.class);
    
    
    public static DefaultDirectedGraph<String, DefaultEdge> getDefaultEdges(){
        addVertex();
        addEdges();
        return directedGraph;
    }
    
    private static void addVertex(){
    
        directedGraph.addVertex("L4H");
        directedGraph.addVertex("L2");
        directedGraph.addVertex("L1");
        directedGraph.addVertex("L2toL4");
        directedGraph.addVertex("L4V");
        directedGraph.addVertex("L3toL1");
        directedGraph.addVertex("L3V");
        directedGraph.addVertex("L3H");
        directedGraph.addVertex("L2toL17");
        directedGraph.addVertex("L17");
        directedGraph.addVertex("L17toL19");
        directedGraph.addVertex("L19");
        directedGraph.addVertex("L19toL10");
        directedGraph.addVertex("L10");
        directedGraph.addVertex("L18toL1");
        directedGraph.addVertex("L18");
        directedGraph.addVertex("L20toL18");
        directedGraph.addVertex("L20");
        directedGraph.addVertex("L9toL20");
        directedGraph.addVertex("L9");
        directedGraph.addVertex("L7toL3");
        directedGraph.addVertex("L7H");
        directedGraph.addVertex("L7V");
        directedGraph.addVertex("L4toL8");
        directedGraph.addVertex("L8H");
        directedGraph.addVertex("L8V");
        directedGraph.addVertex("L9toL7");
        directedGraph.addVertex("L8toL10");
        directedGraph.addVertex("L23");
        directedGraph.addVertex("L24");
        directedGraph.addVertex("L24toL8");
        directedGraph.addVertex("L17toL24");
        directedGraph.addVertex("L5toL3");
        directedGraph.addVertex("L23toL19");
        directedGraph.addVertex("L5");
        directedGraph.addVertex("L6");
        directedGraph.addVertex("L7");
        directedGraph.addVertex("L4toL23");
        directedGraph.addVertex("L4toL6");
        directedGraph.addVertex("L7toL6");
        directedGraph.addVertex("L5toL8");
        directedGraph.addVertex("L5toL23");
        directedGraph.addVertex("L24toL6");
        directedGraph.addVertex("L23toL18");
        directedGraph.addVertex("L20toL24");
        directedGraph.addVertex("L19toL17");
        directedGraph.addVertex("L8toL20");
        directedGraph.addVertex("L12V");
        directedGraph.addVertex("L11V");
        directedGraph.addVertex("L11H");
        directedGraph.addVertex("L15H");
        directedGraph.addVertex("L14");
        directedGraph.addVertex("L13");
        directedGraph.addVertex("L15V");
        directedGraph.addVertex("L22");
        directedGraph.addVertex("L16H");
        directedGraph.addVertex("L12H");
        directedGraph.addVertex("L16H");
        directedGraph.addVertex("L21");
        directedGraph.addVertex("L21toL16");
        directedGraph.addVertex("L15toL22");
        directedGraph.addVertex("L21toL14");
        directedGraph.addVertex("L13toL22");
        directedGraph.addVertex("L12toL22");
        directedGraph.addVertex("L21toL11");
        directedGraph.addVertex("L15toL11");
        directedGraph.addVertex("L12toL16");
        directedGraph.addVertex("L22toL18");
        directedGraph.addVertex("L22toL19");
        directedGraph.addVertex("L17toL21");
        directedGraph.addVertex("L23toL21");
        directedGraph.addVertex("L22toL24");
        directedGraph.addVertex("L20toL21");
        directedGraph.addVertex("L11toL20");
        directedGraph.addVertex("L9toL12");
        directedGraph.addVertex("L19toL12");
        directedGraph.addVertex("L11toL10");
        directedGraph.addVertex("L13toL16");
        directedGraph.addVertex("L12toL14");
        directedGraph.addVertex("L15toL14");
        directedGraph.addVertex("L13toL11");
        directedGraph.addVertex("L23toL3");
        directedGraph.addVertex("L7toL23");
        directedGraph.addVertex("L18toL15");
        directedGraph.addVertex("L16toL17");
        directedGraph.addVertex("L3toL17");
        directedGraph.addVertex("L16toL1");
        directedGraph.addVertex("L18toL4");
        directedGraph.addVertex("L2toL15");
        directedGraph.addVertex("L8toL12");
        directedGraph.addVertex("L11toL7");
        directedGraph.addVertex("L16toL4");
        directedGraph.addVertex("L3toL15");

    }
    
    private static void addEdges(){
    directedGraph.addEdge("L2", "L2toL15");
        directedGraph.addEdge("L2", "L2toL4");
        directedGraph.addEdge("L2", "L2toL17");
        directedGraph.addEdge("L2toL15", "L15V");
        directedGraph.addEdge("L15V", "L15H");
        directedGraph.addEdge("L15H", "L15toL11");
        directedGraph.addEdge("L15toL11", "L11H");
        directedGraph.addEdge("L11H", "L11V");
        directedGraph.addEdge("L11V", "L11toL20");
        directedGraph.addEdge("L11V", "L11toL10");
        directedGraph.addEdge("L11toL10", "L10");

        directedGraph.addEdge("L11V", "L11toL7");
        directedGraph.addEdge("L7V", "L7H");
        // directedGraph.addEdge("L7", "L7H");

        directedGraph.addEdge("L7H", "L7toL23");
        directedGraph.addEdge("L7H", "L7toL3");
        directedGraph.addEdge("L7toL3", "L3H");
        directedGraph.addEdge("L3H", "L3V");
        directedGraph.addEdge("L3V", "L3toL1");
        directedGraph.addEdge("L3toL1", "L1");

        directedGraph.addEdge("L7H", "L7toL6");
        directedGraph.addEdge("L15H", "L15toL14");
        directedGraph.addEdge("L15H", "L15toL22");
        directedGraph.addEdge("L5", "L5toL3");
        directedGraph.addEdge("L5toL3", "L3H");
        directedGraph.addEdge("L5", "L5toL8");
        directedGraph.addEdge("L5", "L5toL23");
        directedGraph.addEdge("L5toL23", "L23");
        directedGraph.addEdge("L23", "L23toL19");
        directedGraph.addEdge("L23", "L23toL18");
        directedGraph.addEdge("L23", "L23toL21");
        directedGraph.addEdge("L21", "L21toL16");
        directedGraph.addEdge("L21", "L21toL14");
        directedGraph.addEdge("L21", "L21toL11");
        directedGraph.addEdge("L21toL14", "L14");

        directedGraph.addEdge("L13", "L13toL11");
        directedGraph.addEdge("L13", "L13toL16");
        directedGraph.addEdge("L13", "L13toL22");
        directedGraph.addEdge("L13toL22", "L22");
        directedGraph.addEdge("L22", "L22toL18");
        directedGraph.addEdge("L22", "L22toL19");
        directedGraph.addEdge("L22", "L22toL24");

        directedGraph.addEdge("L19toL10", "L10");
        directedGraph.addEdge("L17toL19", "L19");
        directedGraph.addEdge("L2toL17", "L17");
        directedGraph.addEdge("L9", "L9toL7");
        directedGraph.addEdge("L9toL7", "L7V");

        directedGraph.addEdge("L9", "L9toL20");
        directedGraph.addEdge("L9toL20", "L20");

        directedGraph.addEdge("L20", "L20toL24");
        directedGraph.addEdge("L20", "L20toL21");

        directedGraph.addEdge("L20", "L20toL18");
        directedGraph.addEdge("L18", "L18toL1");
        directedGraph.addEdge("L18toL1", "L1");
        directedGraph.addEdge("L18", "L18toL15");
        directedGraph.addEdge("L18toL15", "L15V");
        directedGraph.addEdge("L18", "L18toL4");
        directedGraph.addEdge("L18toL4", "L4V");
        directedGraph.addEdge("L4V", "L4H");
        directedGraph.addEdge("L4H", "L4toL23");
        directedGraph.addEdge("L4toL23", "L23");

        directedGraph.addEdge("L4H", "L4toL6");
        directedGraph.addEdge("L4toL6", "L6");
        directedGraph.addEdge("L4H", "L4toL8");
        directedGraph.addEdge("L4toL8", "L8H");
        directedGraph.addEdge("L8H", "L8V");
        directedGraph.addEdge("L8V", "L8toL10");
        directedGraph.addEdge("L8toL10", "L10");
        directedGraph.addEdge("L8V", "L8toL20");
        directedGraph.addEdge("L8toL20", "L20");

        directedGraph.addEdge("L8V", "L8toL12");
        directedGraph.addEdge("L8toL12", "L12V");
        directedGraph.addEdge("L12V", "L12H");
        directedGraph.addEdge("L12H", "L12toL14");
        directedGraph.addEdge("L12toL14", "L14");
        directedGraph.addEdge("L12H", "L12toL22");

        directedGraph.addEdge("L12H", "L12toL16");
        directedGraph.addEdge("L12toL16", "L16H");
        // directedGraph.addEdge("L16H", "L16V");

        directedGraph.addEdge("L9", "L9toL12");

        // directedGraph.addEdge("L16V", "L16toL1");
        directedGraph.addEdge("L16toL1", "L1");
        // directedGraph.addEdge("L16V", "L16toL17");
        directedGraph.addEdge("L16toL17", "L17");
        directedGraph.addEdge("L17", "L17toL21");
        directedGraph.addEdge("L17toL21", "L21");

        directedGraph.addEdge("L9", "L9toL7");
        directedGraph.addEdge("L9toL7", "L7V");
        directedGraph.addEdge("L9toL20", "L20");
        directedGraph.addEdge("L9", "L9toL12");
        directedGraph.addEdge("L9toL12", "L12V");
        directedGraph.addEdge("L12V", "L12H");
        directedGraph.addEdge("L12H", "L12toL14");
        directedGraph.addEdge("L12toL14", "L14");
        directedGraph.addEdge("L17", "L17toL24");
        directedGraph.addEdge("L17toL24", "L24");
        // directedGraph.addEdge("L24", "L24toL3");
        // directedGraph.addEdge("L24toL3", "L3");
        // directedGraph.addEdge("L24", "L24toL18");
        // directedGraph.addEdge("L24toL18", "L18");
        // directedGraph.addEdge("L5", "L5toL23");
        directedGraph.addEdge("L23toL21", "L21");
        directedGraph.addEdge("L22", "L22toL24");
        directedGraph.addEdge("L22toL24", "L24");
        directedGraph.addEdge("L24", "L24toL6");
        directedGraph.addEdge("L24toL6", "L6");
        directedGraph.addEdge("L23", "L23toL21");
        directedGraph.addEdge("L23toL21", "L21");
        directedGraph.addEdge("L23", "L23toL19");
        directedGraph.addEdge("L23toL19", "L19");
        directedGraph.addEdge("L19", "L19toL10");
        // directedGraph.addEdge("", "");
        directedGraph.addEdge("L22", "L22toL18");
        directedGraph.addEdge("L22toL18", "L18");
        directedGraph.addEdge("L18", "L18toL1");
        directedGraph.addEdge("L18toL1", "L1");
        // directedGraph.addEdge("", "");
        directedGraph.addEdge("L7toL6", "L6");
        directedGraph.addEdge("L7H", "L7toL6");
        directedGraph.addEdge("L9", "L7V");

    }
}
