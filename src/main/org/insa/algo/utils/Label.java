package org.insa.algo.utils;

import org.insa.graph.Arc;

public class Label implements Comparable<Label>{
	
	int sommet ;
	boolean marque ;
	double cout ;
	Arc pere ;
	boolean inTas ;
	
	
	
	public Label( int som, boolean mar, double cost, Arc per, boolean inT) {
		this.sommet = som ;
		this.marque = mar ;
		
		this.cout = cost;
		this.pere = per ;
		this.inTas = inT ; 
		
	}
	public void setCost(double x) {
		this.cout = x ; 
	}
	
	public void setMark(boolean y ) {
		this.marque = y ; 
	}
	
	public void setinTas(boolean w ) {
		this.inTas = w ; 
	}
	
	public void setPere(Arc a) {
		this.pere = a ;
	}
	
	public boolean getMark() {
		return this.marque ;
	}
	
	public boolean getinTas() {
		return this.inTas ;
	}
	
	public double getCost() {
		return this.cout ;
	}
	public int getSom() {
		return this.sommet ; 
	}
	
	public Arc getPere() {
		return this.pere ;
	}
	
	public double getTotalCost() {
		return this.cout ; 
	}
	
	
	//Si le cout de L1 est plus grand que L2 on renvoit TRUE
	public int compareTo (Label L1) { 
		if ((this.getTotalCost()-L1.getTotalCost())<0){
			return -1 ; 
		}
		else if  ((this.getTotalCost()-L1.getTotalCost())==0) {
			return 0 ; 
		}
		else {
			return 1 ; 
		}
	}
	
	

}
