package org.insa.algo.shortestpath;

import org.insa.algo.utils.Label;
import org.insa.algo.utils.LabelStar;
import org.insa.graph.Arc;

public class AStarAlgorithm extends DijkstraAlgorithm {

    public AStarAlgorithm(ShortestPathData data) {
        super(data);
        
    }
    
    
   
    @Override
    protected Label newLabel(int som, boolean mar, double cost, Arc per, boolean inT, ShortestPathData data) {
    	return new LabelStar(som, mar, cost, per, inT,data);
    }

}
