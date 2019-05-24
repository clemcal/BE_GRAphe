package org.insa.algo.utils;

import org.insa.algo.AbstractInputData.Mode;
import org.insa.algo.shortestpath.ShortestPathData;
import org.insa.graph.Arc;

import org.insa.graph.Point;

public class LabelStar extends Label implements Comparable<Label>{
	private double heuris; 
	
	public LabelStar(int som, boolean mar, double cost, Arc per, boolean inT, ShortestPathData data) {
		super(som,mar,cost,per,inT) ;
		
		
		if (data.getMode()==Mode.LENGTH) {
			this.heuris = (double)Point.distance(data.getGraph().get(som).getPoint(), data.getDestination().getPoint()) ; 
		}
		else { //if (data.getMode()==Mode.TIME)
			double maxSpeed = Double.POSITIVE_INFINITY ;
			if ((data.getMaximumSpeed()!=-1) && (data.getGraph().getGraphInformation().getMaximumSpeed() != -1)) {
			maxSpeed = Math.min(data.getMaximumSpeed(), data.getGraph().getGraphInformation().getMaximumSpeed()) ;
			}
			else if (data.getMaximumSpeed()==-1) {
				maxSpeed = data.getGraph().getGraphInformation().getMaximumSpeed() ;
			}
			else {
				maxSpeed = data.getMaximumSpeed() ;
			}
			this.heuris = (((double)Point.distance(data.getGraph().get(som).getPoint(), data.getDestination().getPoint()))*3600/(maxSpeed*1000)) ;
			
		}
		
	}
	
	@Override
	//On renvoit un nouveau cout calculé avec le cout d'un Label + cout à 
	//vol d'oiseau entre le noeud et le noeud de destination
	public double getTotalCost() {
		return this.heuris+this.cout; 
	}

	
}
