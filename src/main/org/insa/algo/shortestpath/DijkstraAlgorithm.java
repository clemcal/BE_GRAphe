package org.insa.algo.shortestpath;
import java.util.ArrayList;

import org.insa.algo.utils.BinaryHeap;
import org.insa.algo.utils.Label; 

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
    	boolean trouve = false ; 
        ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        ArrayList<Label> tabLabel = new ArrayList<Label>(); 
        BinaryHeap<Label> tas = new BinaryHeap<Label>() ; 
        // TODO:
        
        //initialisation
        for (int j =0 ; j<data.getGraph().size(); j++) {
        	tabLabel.add(j, new Label(j, false , Double.POSITIVE_INFINITY  , null , false)) ; 
        }
        
        tabLabel.get(data.getOrigin().getId()).setCost(0) ;
        tas.insert(tabLabel.get(data.getOrigin().getId()));
        
        
        //itérations 
        while (!(tas.isEmpty()) || (!(trouve))  ) {
        	deleteMin(Tas) ; 
        }
        
        
        
        
        
        return solution;
    }

}
