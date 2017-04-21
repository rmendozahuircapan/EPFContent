
package Herramienta;

import static Herramienta.Herramienta.*;
import WorkFlow.*;


public class ShowWorkFlow {
    
    public static void resumeWorkFlow() {
        System.out.println("-------------------------------------");
        System.out.println("Nodes: "+Nodes.size());
        System.out.println("Edges: "+Edges.size());
        System.out.println("WorkFlows: "+WorkFlows.size());
        System.out.println("-------------------------------------");        
    }
    
    public static void showWorkFlow() {
        System.out.println("-------------------------------------");
        for (WorkFlow wf : WorkFlows) {
            System.out.println("name: "+wf.getName());
            System.out.println(wf.getNodes().size()+" nodes "+wf.getEdges().size()+" edges");
            System.out.println("");
            for (Edge e : wf.getEdges()) {
                System.out.println(e.getSource().getName()+" -> "+e.getTarget().getName()+" ["+e.getName()+"]");
            }
            System.out.println("---");
        }
        
    }
    
}
