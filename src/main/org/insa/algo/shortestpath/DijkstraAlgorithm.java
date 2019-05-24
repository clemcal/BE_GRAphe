package org.insa.algo.shortestpath;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import org.insa.algo.AbstractSolution.Status;
import org.insa.algo.utils.*;
import org.insa.graph.Arc;
import org.insa.graph.Node;
import org.insa.graph.Path; 

public class DijkstraAlgorithm extends ShortestPathAlgorithm {
	protected int nombreNodesMarquees ;
	
    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
        this.nombreNodesMarquees = 0 ;
    }
    

    
    

    @Override
    protected ShortestPathSolution doRun() {
    	boolean trouve = false ; 
        ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
//        ArrayList<Label> tabLabel = new ArrayList<Label>(); 
        ArrayList<Label> tabLabel = new ArrayList<Label>(data.getGraph().size()); 
        BinaryHeap<Label> tas = new BinaryHeap<Label>() ; 
        this.nombreNodesMarquees = 0 ;
        // TODO:
        
        //initialisation
      for (int j =0 ; j<data.getGraph().size(); j++) {
       	tabLabel.add(j, null) ; 
       }
      
      Label origine = newLabel(data.getOrigin().getId(),false,0,null,false,data) ; 
//        
        tabLabel.set(data.getOrigin().getId(), origine) ;
        notifyOriginProcessed(data.getOrigin());
        
        tas.insert(tabLabel.get(data.getOrigin().getId()));
        
        //itérations 
        while (!tas.isEmpty() && !(trouve)  ) {
        	
        	 
        	
        	Label actuelle = tas.deleteMin() ; 
//        	System.out.println("tab "+ actuelle.getCost()); 
//        	System.out.println("tab2 "+ actuelle.getTotalCost()); 
        	actuelle.setMark(true) ; 
        	notifyNodeMarked(data.getGraph().get(actuelle.getSom()));
        	this.nombreNodesMarquees +=1 ;
//        	tabLabel.add(actuelle) ; 
        
          if (actuelle.getSom() == data.getDestination().getId()) {
          trouve = true ; 
          notifyDestinationReached(data.getDestination());
          }
          
          
          Node actu = data.getGraph().get(actuelle.getSom()) ; 
          Iterator <Arc> arc = actu.getSuccessors().iterator();
          while (arc.hasNext()) {
            Arc arcIter = arc.next() ;
            Node succes = arcIter.getDestination();
            Label labSucces = tabLabel.get(succes.getId()) ;
            
            if (!data.isAllowed(arcIter)) {
                continue;
            }
            
            if (labSucces == null) {
              labSucces = newLabel(succes.getId(), false, Double.POSITIVE_INFINITY, null, false, data);
              tabLabel.set(succes.getId(), labSucces);
              notifyNodeReached(data.getGraph().get(labSucces.getSom()));
            }
             
            if (!labSucces.getMark()) {
              if ((labSucces.getTotalCost() > (actuelle.getCost() + data.getCost(arcIter)+ labSucces.getTotalCost()-labSucces.getCost()))
              || (labSucces.getCost()==Double.POSITIVE_INFINITY)) {
                
                
                if (labSucces.getinTas()) {
                  tas.remove(labSucces) ;  
                }
                else {
                  labSucces.setinTas(true) ;
                }
                labSucces.setCost(actuelle.getCost() + data.getCost(arcIter)) ;
                //System.out.println(labSucces.getCost());
                labSucces.setPere(arcIter);
                tas.insert(labSucces) ; //comme ça il est dans ajouté au bon endroit
              }
              
            
            }
          }
        }

        // Destination has no predecessor, the solution is infeasible...
        if (tabLabel.get(data.getDestination().getId()) == null) {
            solution = new ShortestPathSolution(data, Status.INFEASIBLE);
        }
        else {

            // The destination has been found, notify the observers.
            notifyDestinationReached(data.getDestination());

            // Create the path from the array of predecessors...
            ArrayList<Arc> arcs = new ArrayList<>();
            Arc arc = tabLabel.get(data.getDestination().getId()).getPere() ;
            while (arc != null) {
            	//System.out.println(arc.getDestination().getId());
                arcs.add(arc);
                arc = tabLabel.get(arc.getOrigin().getId()).getPere() ; 
            }

            // Reverse the path...
            Collections.reverse(arcs);

            // Create the final solution.
            solution = new ShortestPathSolution(data, Status.OPTIMAL, new Path(data.getGraph(), arcs));
        }
          
         
         
        return solution;
    }
    
    
    protected Label newLabel(int som, boolean mar, double cost, Arc per, boolean inT, ShortestPathData data) {
		return new Label(som,mar,cost,per,inT) ; 
	}
	
    public int getNbNodesMarquees() {
    	return this.nombreNodesMarquees ;
    }
}
    
    
