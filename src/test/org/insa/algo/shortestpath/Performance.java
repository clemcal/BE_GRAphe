package org.insa.algo.shortestpath;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;

import org.insa.algo.ArcInspector;
import org.insa.algo.ArcInspectorFactory;

import org.insa.graph.Graph;

import org.insa.graph.Path;

import org.insa.graph.io.BinaryGraphReader;
import org.insa.graph.io.GraphReader;
import org.junit.BeforeClass;
import org.junit.Test;


public class Performance {
	
	
	static DijkstraAlgorithm D, D2, D3, D4, D5, D6, D7, D8, D9, D10 ;
    static BellmanFordAlgorithm B, B2, B3, B4, B5, B6, B7, B8, B9, B10 ;  
    static AStarAlgorithm A, A2, A3, A4, A5, A6, A7, A8, A9, A10 ;
    
    
	

    @BeforeClass
    public static void initAll() throws IOException {
    	  // Visit these directory to see the list of available files on Commetud.
        String mapName1 = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/carre-dense.mapgr";
        String mapName2 = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/haute-garonne.mapgr";
        String mapName3 = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/toulouse.mapgr";
        

        // Create a graph reader.
        GraphReader reader = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(mapName1))));
        Graph graph = reader.read();
        GraphReader reader2 = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(mapName2))));
        Graph graph2 = reader2.read();
        GraphReader reader3 = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(mapName3))));
        Graph graph3 = reader3.read();
        
        
        ArcInspector arcInspectorDijkstra0 = new ArcInspectorFactory().getAllFilters().get(0);
        //chemin distance perf non routiere
		ShortestPathData data = new ShortestPathData(graph, graph.get(194361),graph.get(242320), arcInspectorDijkstra0);
		B = new BellmanFordAlgorithm(data) ;
		D = new DijkstraAlgorithm(data) ;
		A = new AStarAlgorithm(data) ; 
		//haute garonne
		ShortestPathData data3 = new ShortestPathData(graph2, graph2.get(90921),graph2.get(55448), arcInspectorDijkstra0);
		B3 = new BellmanFordAlgorithm(data) ;
		D3 = new DijkstraAlgorithm(data) ;
		A3 = new AStarAlgorithm(data) ; 
		//toulouse
		ShortestPathData data5 = new ShortestPathData(graph3, graph3.get(38895),graph3.get(13168), arcInspectorDijkstra0);
		B5 = new BellmanFordAlgorithm(data) ;
		D5 = new DijkstraAlgorithm(data) ;
		A5 = new AStarAlgorithm(data) ; 
		
		ArcInspector arcInspectorDijkstra1 = new ArcInspectorFactory().getAllFilters().get(2);
        //chemin temps perf non routiere
		ShortestPathData data2 = new ShortestPathData(graph, graph.get(194361),graph.get(242320), arcInspectorDijkstra1);
		B2 = new BellmanFordAlgorithm(data) ;
		D2 = new DijkstraAlgorithm(data) ;
		A2 = new AStarAlgorithm(data) ; 
		//haute garonne
		ShortestPathData data4 = new ShortestPathData(graph2, graph2.get(90921),graph2.get(55448), arcInspectorDijkstra0);
		B4 = new BellmanFordAlgorithm(data) ;
		D4 = new DijkstraAlgorithm(data) ;
		A4 = new AStarAlgorithm(data) ; 
		//toulouse
		ShortestPathData data6 = new ShortestPathData(graph3, graph3.get(38895),graph3.get(13168), arcInspectorDijkstra0);
		B6 = new BellmanFordAlgorithm(data) ;
		D6 = new DijkstraAlgorithm(data) ;
		A6 = new AStarAlgorithm(data) ; 
		
		
    }
    
    //nombre de nodes marquees : retourne toujours le meme, que ca soit avec le get ou avec l'attribut direct ?????
    
    
    @Test
    // performance distance carre dense
    public void DistanceCarre() {
    	double tD ; 
    	double tB ; 
    	double tA ; 
    	double tDeb ; 
    	double tFin ;
    	
    	System.out.println("Performance en distance : carre dense");
    	System.out.println("");
    	
    	// Pour Bellman 
    	tDeb = System.nanoTime() ; 
    	B.doRun() ;
    	tFin = System.nanoTime() ;
    	tB = tFin-tDeb ; 
    	
    	// Pour Dijkstra 
    	tDeb = System.nanoTime() ; 
    	D.doRun() ;
    	tFin = System.nanoTime() ;
    	tD = tFin-tDeb ; 
    	System.out.println("Nombre de nodes marquées pour Dijkstra : " + D.nombreNodesMarquees);
    	
    	
    	
    	// Pour AStar 
    	tDeb = System.nanoTime() ; 
    	A.doRun() ;
    	System.out.println("Nombre de nodes marquées pour A* : " + A.nombreNodesMarquees);
    	tFin = System.nanoTime() ;
    	tA = tFin-tDeb ; 
    	
    	System.out.println("");
    	//System.out.println("Bellman solving time :" + tB/1000000000 + "secondes");  
    	System.out.println("Dijkstra solving time :" + tD/1000000000 + "secondes");  
    	System.out.println("A Star solving time :" + tA/1000000000 + "secondes");  
    	assertTrue(true);
    	System.out.println("");
    	System.out.println("------------------------------------------------------------------------------------------------------------------");
    	System.out.println("");
    }
    
    @Test
 // performance temps carre dense
    public void TempsCarre() {
    	double tD ; 
    	double tB ; 
    	double tA ; 
    	double tDeb ; 
    	double tFin ;
    	
    	System.out.println("Performance en temps : carre dense");
    	System.out.println("");
    	
    	// Pour Dijkstra 
    	tDeb = System.nanoTime() ; 
    	D2.doRun() ;
    	tFin = System.nanoTime() ;
    	tD = tFin-tDeb ; 
    	System.out.println("Nombre de nodes marquées pour Dijkstra : " + D2.nombreNodesMarquees);
    	
    	// Pour Bellman 
    	tDeb = System.nanoTime() ; 
    	B2.doRun() ;
    	tFin = System.nanoTime() ;
    	tB = tFin-tDeb ; 
    	
    	// Pour AStar 
    	tDeb = System.nanoTime() ; 
    	A2.doRun() ;
    	tFin = System.nanoTime() ;
    	tA = tFin-tDeb ; 
    	System.out.println("Nombre de nodes marquées pour A* : " + A2.nombreNodesMarquees);
    	
    	System.out.println("");
    	System.out.println("Bellman solving time :" + tB/1000000000 + "secondes");  
    	System.out.println("Dijkstra solving time :" + tD/1000000000 + "secondes");  
    	System.out.println("A Star solving time :" + tA/1000000000 + "secondes");  
    	assertTrue(true);
    	System.out.println("");
    	System.out.println("------------------------------------------------------------------------------------------------------------------");
    	System.out.println("");
    }
    
    //HAUTE GARONNE
    
    @Test
    // performance distance haute garonne
    public void DistanceHG() {
    	double tD ; 
    	double tB ; 
    	double tA ; 
    	double tDeb ; 
    	double tFin ;
    	
    	System.out.println("Performance en distance : haute garonne");
    	System.out.println("");
    	// Pour Dijkstra 
    	tDeb = System.nanoTime() ; 
    	D3.doRun() ;
    	tFin = System.nanoTime() ;
    	tD = tFin-tDeb ; 
    	System.out.println("Nombre de nodes marquées pour Dijkstra : " + D3.getNbNodesMarquees());
    	
    	// Pour Bellman 
    	tDeb = System.nanoTime() ; 
    	B3.doRun() ;
    	tFin = System.nanoTime() ;
    	tB = tFin-tDeb ; 
    	
    	// Pour AStar 
    	tDeb = System.nanoTime() ; 
    	A3.doRun() ;
    	tFin = System.nanoTime() ;
    	tA = tFin-tDeb ; 
    	System.out.println("Nombre de nodes marquées pour A* : " + A3.getNbNodesMarquees());
    	
    	System.out.println("");
    	System.out.println("Bellman solving time :" + tB/1000000000 + "secondes");  
    	System.out.println("Dijkstra solving time :" + tD/1000000000 + "secondes");  
    	System.out.println("A Star solving time :" + tA/1000000000 + "secondes");  
    	assertTrue(true);
    	System.out.println("");
    	System.out.println("------------------------------------------------------------------------------------------------------------------");
    	System.out.println("");
    	
    }
    
    @Test
 // performance temps carre dense
    public void TempsHG() {
    	double tD ; 
    	double tB ; 
    	double tA ; 
    	double tDeb ; 
    	double tFin ;
    	
    	System.out.println("Performance en temps : haute garonne");
    	System.out.println("");
    	// Pour Dijkstra 
    	tDeb = System.nanoTime() ; 
    	D4.doRun() ;
    	tFin = System.nanoTime() ;
    	tD = tFin-tDeb ; 
    	System.out.println("Nombre de nodes marquées pour Dijkstra : " + D4.nombreNodesMarquees);
    	
    	// Pour Bellman 
    	tDeb = System.nanoTime() ; 
    	B4.doRun() ;
    	tFin = System.nanoTime() ;
    	tB = tFin-tDeb ; 
    	
    	// Pour AStar 
    	tDeb = System.nanoTime() ; 
    	A4.doRun() ;
    	tFin = System.nanoTime() ;
    	tA = tFin-tDeb ; 
    	System.out.println("Nombre de nodes marquées pour A* : " + A4.nombreNodesMarquees);
    	
    	System.out.println("");
    	System.out.println("Bellman solving time :" + tB/1000000000 + "secondes");  
    	System.out.println("Dijkstra solving time :" + tD/1000000000 + "secondes");  
    	System.out.println("A Star solving time :" + tA/1000000000 + "secondes");  
    	assertTrue(true);
    	System.out.println("");
    	System.out.println("------------------------------------------------------------------------------------------------------------------");
    	System.out.println("");
    	
    }
    
    //TOULOUSE
    
    
    @Test
    // performance distance toulouse
    public void DistanceToulouse() {
    	double tD ; 
    	double tB ; 
    	double tA ; 
    	double tDeb ; 
    	double tFin ;
    	
    	System.out.println("Performance en distance : Toulouse");
    	System.out.println("");
    	// Pour Dijkstra 
    	tDeb = System.nanoTime() ; 
    	D5.doRun() ;
    	tFin = System.nanoTime() ;
    	tD = tFin-tDeb ; 
    	System.out.println("Nombre de nodes marquées pour Dijkstra : " + D5.nombreNodesMarquees);
    	
    	// Pour Bellman 
    	tDeb = System.nanoTime() ; 
    	B5.doRun() ;
    	tFin = System.nanoTime() ;
    	tB = tFin-tDeb ; 
    	
    	// Pour AStar 
    	tDeb = System.nanoTime() ; 
    	A5.doRun() ;
    	tFin = System.nanoTime() ;
    	tA = tFin-tDeb ; 
    	System.out.println("Nombre de nodes marquées pour A* : " + A5.nombreNodesMarquees);
    	
    	System.out.println("");
    	System.out.println("Bellman solving time :" + tB/1000000000 + "secondes");  
    	System.out.println("Dijkstra solving time :" + tD/1000000000 + "secondes");  
    	System.out.println("A Star solving time :" + tA/1000000000 + "secondes");  
    	assertTrue(true);
    	System.out.println("");
    	System.out.println("------------------------------------------------------------------------------------------------------------------");
    	System.out.println("");
    	
    }
    
    @Test
 // performance temps toulouse
    public void TempsToulouse() {
    	double tD ; 
    	double tB ; 
    	double tA ; 
    	double tDeb ; 
    	double tFin ;
    	
    	System.out.println("Performance en temps : Toulouse");
    	System.out.println("");
    	// Pour Dijkstra 
    	tDeb = System.nanoTime() ; 
    	D6.doRun() ;
    	tFin = System.nanoTime() ;
    	tD = tFin-tDeb ; 
    	System.out.println("Nombre de nodes marquées pour Dijkstra : " + D6.nombreNodesMarquees);
    	
    	// Pour Bellman 
    	tDeb = System.nanoTime() ; 
    	B6.doRun() ;
    	tFin = System.nanoTime() ;
    	tB = tFin-tDeb ; 
    	
    	// Pour AStar 
    	tDeb = System.nanoTime() ; 
    	A6.doRun() ;
    	tFin = System.nanoTime() ;
    	tA = tFin-tDeb ; 
    	System.out.println("Nombre de nodes marquées pour A* : " + A6.nombreNodesMarquees);
    	
    	System.out.println("");
    	System.out.println("Bellman solving time :" + tB/1000000000 + "secondes");  
    	System.out.println("Dijkstra solving time :" + tD/1000000000 + "secondes");  
    	System.out.println("A Star solving time :" + tA/1000000000 + "secondes");    
    	assertTrue(true);
    	System.out.println("");
    	System.out.println("------------------------------------------------------------------------------------------------------------------");
    	System.out.println("");
    	
    }
    
    

}
