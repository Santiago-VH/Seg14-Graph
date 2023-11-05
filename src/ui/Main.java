package ui;

import java.util.List;
import java.util.Scanner;

import model.Graph;
import model.Vertex;

public class Main {

	public static void main(String[] args) {

		Graph<Integer> graph = new Graph<>();
        Scanner scanner = new Scanner(System.in);
        
        while(true) {
        	System.out.println("Seleccione una opción:");
            System.out.println("1. Agregar vértice");
            System.out.println("2. Agregar arista");
            System.out.println("3. Imprimir grafo");
            System.out.println("4. Verificar si es fuertemente conexo");
            System.out.println("5. Realizar e imprimir recorrido DFS");
            System.out.println("6. Salir");
            
            int option=scanner.nextInt();
            
            switch(option) {
            	case 1:
            		System.out.println("Ingrese el valor del vértice:");
                    int valorVertice = scanner.nextInt();
                    Vertex<Integer> vertex = new Vertex<>(valorVertice);
                    graph.addVertex(vertex);
                    break;
            		
            	case 2:
            		System.out.println("Ingrese el valor del vértice de inicio:");
                    int start = scanner.nextInt();
                    System.out.println("Ingrese el valor del vértice de fin:");
                    int end = scanner.nextInt();

                    Vertex<Integer> startVertex = graph.findVertexByValue(start);
                    Vertex<Integer> endVertex = graph.findVertexByValue(end);
                    
                    if (startVertex != null && endVertex != null) {
                        graph.addEdge(startVertex, endVertex);
                    } else {
                        System.out.println("Uno o ambos vértices no existen.");
                    }
                    break;

            	case 3:
            		graph.printGraph();
                    break;
            		
            	case 4:
            		boolean isStronglyConnected = graph.isStronglyConnected(graph);
                    System.out.println("El grafo es fuertemente conexo: " + isStronglyConnected);
                    break;
            		
            	case 5:
            		List<Vertex<Integer>> dfsOrder = graph.dfs(graph);
                    System.out.println("Recorrido DFS:");
                    for(int i = 0; i < dfsOrder.size(); i++) {
                        Vertex<Integer> vertex1 = dfsOrder.get(i);
                        System.out.print(vertex1.getValue() + " ");
                    }
                    System.out.println();
                    break;
            		
            		
            	case 6:
            		scanner.close();
                    System.exit(0);
                    break;
            		
            	default:
            		System.out.println("Opcion no valida, intente denuevo.");
            }
        }
	}

}
