package org.insa.algo.shortestpath;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;


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

public class DijkstraTest {
	
	
	
	
	
	static DijkstraAlgorithm D, D1, D2, D3, D4, D5, D6, D7, D8, D9, D10 ;
    static BellmanFordAlgorithm B, B2, B4, B5, B6, B7, B8, B9, B10 ;  
    static AStarAlgorithm A, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10 ;
    
    
	

    @BeforeClass
    public static void initAll() throws IOException {
    	  // Visit these directory to see the list of available files on Commetud.
        String mapName = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/insa.mapgr";
        String mapName2 = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/carre-dense.mapgr";
        String mapName3 = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/haute-garonne.mapgr";
        

        // Create a graph reader.
        GraphReader reader = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));
        Graph graph = reader.read();
        GraphReader reader2 = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(mapName2))));
        Graph graph2 = reader2.read();
        GraphReader reader3 = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(mapName3))));
        Graph graph3 = reader3.read();
        
        
        
        
        ArcInspector arcInspectorDijkstra0 = new ArcInspectorFactory().getAllFilters().get(0);
        //chemin normal
		ShortestPathData data = new ShortestPathData(graph, graph.get(611),graph.get(134), arcInspectorDijkstra0);
		B = new BellmanFordAlgorithm(data) ;
		D = new DijkstraAlgorithm(data) ;
		A = new AStarAlgorithm(data) ;
		//chemin infaisable
		ShortestPathData data2 = new ShortestPathData(graph, graph.get(735),graph.get(331), arcInspectorDijkstra0);
		D2 = new DijkstraAlgorithm(data2) ;
        B2 = new BellmanFordAlgorithm(data2) ;
        A2 = new AStarAlgorithm(data2) ; 
		//chemin vide
		ShortestPathData datanull3 = new ShortestPathData(graph, graph.get(735),graph.get(735), arcInspectorDijkstra0);
		D3 = new DijkstraAlgorithm(datanull3) ;
		A3 = new AStarAlgorithm(datanull3) ; 
		//chemin carre dense
		ShortestPathData dataNonRoutiere = new ShortestPathData(graph2, graph2.get(335704),graph2.get(333901), arcInspectorDijkstra0);
		B4 = new BellmanFordAlgorithm(dataNonRoutiere) ;
		D4 = new DijkstraAlgorithm(dataNonRoutiere) ;
		A4 = new AStarAlgorithm(dataNonRoutiere) ; 
		 
		 
		 ArcInspector arcInspectorDijkstra1 = new ArcInspectorFactory().getAllFilters().get(1);
		//chemin voiture
		ShortestPathData dataVoiture = new ShortestPathData(graph, graph.get(611),graph.get(134), arcInspectorDijkstra1);
		B5 = new BellmanFordAlgorithm(dataVoiture) ;
		D5 = new DijkstraAlgorithm(dataVoiture) ; 
		A5 = new AStarAlgorithm(dataVoiture) ; 
		//chemin voiture infaisable
		ShortestPathData dataInfVoiture = new ShortestPathData(graph, graph.get(479),graph.get(735), arcInspectorDijkstra1);
		B6 = new BellmanFordAlgorithm(dataInfVoiture) ;
		D6 = new DijkstraAlgorithm(dataInfVoiture) ; 
		A6 = new AStarAlgorithm(dataInfVoiture) ; 
		
		
		
		ArcInspector arcInspectorDijkstra2 = new ArcInspectorFactory().getAllFilters().get(2);
		//chemin carre dense
		ShortestPathData dataNonRoutiereT = new ShortestPathData(graph2, graph2.get(335704),graph2.get(333901), arcInspectorDijkstra2);
		B8 = new BellmanFordAlgorithm(dataNonRoutiereT) ;
		D8 = new DijkstraAlgorithm(dataNonRoutiereT) ; 
		A8 = new AStarAlgorithm(dataNonRoutiereT) ; 
		
		
		//chemin temps et toutes les routes
		ShortestPathData dataTemps = new ShortestPathData(graph3, graph3.get(131844),graph3.get(118322), arcInspectorDijkstra2);
		B7 = new BellmanFordAlgorithm(dataTemps) ;
		D7 = new DijkstraAlgorithm(dataTemps) ; 
		A7 = new AStarAlgorithm(dataTemps) ; 
		
		ArcInspector arcInspectorDijkstra4 = new ArcInspectorFactory().getAllFilters().get(4);
		//temps et à pied et vélos chemin le plus rapide
		ShortestPathData dataPiedTemps = new ShortestPathData(graph, graph.get(454),graph.get(692), arcInspectorDijkstra4);
		B9 = new BellmanFordAlgorithm(dataPiedTemps) ;
		D9 = new DijkstraAlgorithm(dataPiedTemps) ; 
		A9 = new AStarAlgorithm(dataPiedTemps) ; 
		//pas accessible pied
		ShortestPathData datainfPiedTemps = new ShortestPathData(graph3, graph3.get(135287),graph3.get(152777), arcInspectorDijkstra4);
		B10 = new BellmanFordAlgorithm(datainfPiedTemps) ;
		D10 = new DijkstraAlgorithm(datainfPiedTemps) ; 
		A10 = new AStarAlgorithm(datainfPiedTemps) ; 
    }
    

// TESTS DE VALIDITE
    
    @Test
    // chemin nul (origine=destination)
    public void CheminVide() {
    	assertTrue(D3.doRun().getPath().isEmpty());
    }
    
    @Test
    public void CheminValide() {
    	assertTrue(B.doRun().getPath().isValid()) ;
    }
    
 
    
    
    
    
    
    
// TESTS D'OPTIMALITE
    
    //pour le filtre 0 : distance et toutes les routes
    
    @Test
    // plus court chemin entre 2 nodes ( 611 , 134 ) ;
    public void TestPlusCourtChemin() {
    	Path p1 = B.doRun().getPath();
    	Path p2 = D.doRun().getPath();
    	assertEquals(p1.getLength(),p2.getLength(),0.01) ;  
    }
    
    @Test
    // pas de chemin
    public void CheminInfaisable() {
    	assertEquals(D2.doRun().getStatus(),B2.doRun().getStatus());
    }
   
    
    
    
    @Test
    //carte non routière
    public void DistanceNonRoutiere() {
    	assertEquals(B4.doRun().getPath().getLength(), D4.doRun().getPath().getLength(), 0.01);
    }
    
    
    //pour le filtre 1 : distance et voitures
    
    @Test
    //plus court chemin
    public void PlusCourtVoiture() {
    	assertEquals(B5.doRun().getPath().getLength(), D5.doRun().getPath().getLength(), 0.01) ;  
    }
    
    @Test
    //chemin inexistant si pas accessible aux voitures
    public void PasFaisableVoiture() {
    	assertEquals(D6.doRun().getStatus(),B6.doRun().getStatus());
    }
    
    
    //pour le filtre 2 : temps et toutes les routes
    @Test
    //carte non routière
    public void TempsNonRoutiere() {
    	assertEquals(B8.doRun().getPath().getMinimumTravelTime(), D8.doRun().getPath().getMinimumTravelTime(), 0.01);
    }
    @Test
    //chemin le plus rapide
    public void PlusRapide() {
    	assertEquals(B7.doRun().getPath().getMinimumTravelTime(), D7.doRun().getPath().getMinimumTravelTime(), 0.01) ;  
    }

    
    //pour le filtre 4 : temps et à pied et vélos
    @Test
    //chemin le plus rapide
    public void PlusRapidePied() {
    	assertEquals(B9.doRun().getPath().getMinimumTravelTime(), D9.doRun().getPath().getMinimumTravelTime(), 0.01) ;  
    }
    
    //inexistant si pas accessible à eux
    @Test
    //chemin le plus rapide
    public void InnacPied() {
    	assertEquals(B10.doRun().getStatus(), D10.doRun().getStatus()) ;  
    }
   
    
    
    
/*--------------------------------------------------------------------------------------------------------------------
 * --------------------------------------------------POUR A Star -----------------------------------------------------
 ---------------------------------------------------------------------------------------------------------------------*/
    
    
 
// TESTS DE VALIDITE
    
    @Test
    // chemin nul (origine=destination)
    public void CheminVideA() {
    	assertTrue(A3.doRun().getPath().isEmpty());
    }
    
    @Test
    public void CheminValideA() {
    	assertTrue(A.doRun().getPath().isValid()) ;
    }
    
//TESTS OPTIMALITE       
   
   
//pour le filtre 0 : distance et toutes les routes
    
    @Test
    // plus court chemin entre 2 nodes ( 611 , 134 ) ;
    public void TestPlusCourtCheminA() {
    	Path p1 = B.doRun().getPath();
    	Path p2 = A.doRun().getPath();
    	assertEquals(p1.getLength(),p2.getLength(),0.01) ;  
    }
    
    @Test
    // pas de chemin
    public void CheminInfaisableA() {
    	assertEquals(A2.doRun().getStatus(),B2.doRun().getStatus());
    }
    
    
    
    @Test
    //carte non routière
    public void DistanceNonRoutiereA() {
    	assertEquals(B4.doRun().getPath().getLength(), A4.doRun().getPath().getLength(), 0.01);
    }
    
    
    //pour le filtre 1 : distance et voitures
    
    @Test
    //plus court chemin
    public void PlusCourtVoitureA() {
    	assertEquals(B5.doRun().getPath().getLength(), A5.doRun().getPath().getLength(), 0.01) ;  
    }
    
    @Test
    //chemin inexistant si pas accessible aux voitures
    public void PasFaisableVoitureA() {
    	assertEquals(A6.doRun().getStatus(),B6.doRun().getStatus());
    }
    
    
    //pour le filtre 2 : temps et toutes les routes
    @Test
    //carte non routière
    public void TempsNonRoutiereA() {
    	assertEquals(B8.doRun().getPath().getMinimumTravelTime(), A8.doRun().getPath().getMinimumTravelTime(), 0.01);
    }
    @Test
    //chemin le plus rapide
    public void PlusRapideA() {
    	assertEquals(B7.doRun().getPath().getMinimumTravelTime(), A7.doRun().getPath().getMinimumTravelTime(), 0.01) ;  
    }

    
    //pour le filtre 4 : temps et à pied et vélos
    @Test
    //chemin le plus rapide
    public void PlusRapidePiedA() {
    	assertEquals(B9.doRun().getPath().getMinimumTravelTime(), A9.doRun().getPath().getMinimumTravelTime(), 0.01) ;  
    }
    
    //inexistant si pas accessible à eux
    @Test
    //chemin le plus rapide
    public void InnacPiedA() {
    	assertEquals(B10.doRun().getStatus(), A10.doRun().getStatus()) ;  
    }
    


}
