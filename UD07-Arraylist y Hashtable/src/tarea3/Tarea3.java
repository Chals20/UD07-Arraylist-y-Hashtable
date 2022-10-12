package tarea3;

import java.util.Hashtable;
import java.util.Scanner;

public class Tarea3 {

	static Scanner s = new Scanner(System.in);

	public static void main(String[] args) {

		Hashtable<String, double[]> estoc = new Hashtable<String, double[]>();
		
		Productos(estoc);
		
		boolean continuar = true;
		
		do {
		
			continuar = menuGestion(estoc);;
			
		} while (continuar);
		
	}
	
	public static void Productos(Hashtable<String, double[]> estoc) {
		
		estoc.put("Arroz", new double[] {0.80, 2});
		estoc.put("Agua", new double[] {1.05, 20});
		estoc.put("Boniato", new double[] {1.49, 15});
		estoc.put("Canelones", new double[] {3.55, 30});
		estoc.put("Danone", new double[] {2.33, 13});
		estoc.put("Fresas", new double[] {4.80, 29});
		estoc.put("Guisantes", new double[] {1.20, 24});
		estoc.put("Huevos", new double[] {1.99, 17});
		estoc.put("Jamon", new double[] {7.79, 15});
		estoc.put("Lechuga", new double[] {2.21, 31});
		
	}
	
	public static boolean menuGestion(Hashtable<String, double[]> estoc) {
		
		int seleccion = 0;
		
		
		do {
		
		System.out.println("\n1.Ver todos los productos");
		System.out.println("2.Ver un producto");
		System.out.println("3.Añadir un producto");
		System.out.println("4.Salir");
		System.out.println("Elige una opción:");
		seleccion = s.nextInt();
		
		} while (seleccion > 5 || seleccion < 0);
		
			
			switch(seleccion) {
			
			case 1:
				
				verProductos(estoc); //ver todos los productos del diccionario
				break;
				
			case 2:
				
				
				System.out.println("Introduce el nombre del producto a mostrar:"); //mostrar un producto a traves de su clave (nombre)
				String nombreProducto = s.next();
				mostrarProductoPorNombre(estoc, nombreProducto);
				break;
				
			case 3:
				
				System.out.println("Introduce el nombre del producto:");	//pedimos al usuario el nombre, precio y cantidad del producto
				String nombre = s.next();
				System.out.println("Introduce su precio:");
				double precio = s.nextDouble();
				System.out.println("Introduce la cantidad disponible:");
				double cantidad = s.nextDouble();
				estoc.put(nombre, new double[] {precio, cantidad});			//y lo almacenamos en el diccionario con el metodo put(clave, valor)
				break;
				
			case 4:
				
				return false;	//si el usuario elige salir, terminamos el bucle retornando false
			
		}
		

		return true;
	}
	
	
	public static void verProductos(Hashtable<String, double[]> estoc) {	//se muestran todos los productos
		
		System.out.println("Listado de productos");
		estoc.forEach((clave, valor) -> {	//por cada elemento del diccionario
			
			System.out.println("El producto: " + clave + " tiene un precio de " + valor[0] + "€ y tenemos disponibles " + (int)valor[1] + " unidades.");	//mostramos el nombre (clave), el precio (primer valor del array) y la cantidad (segundo valor del array)
			
		});	
	}
	

	public static void mostrarProductoPorNombre(Hashtable<String, double[]> estoc, String clave) {	//mostramos el producto con el nombre indicado (clave)
		
		double[] articulos = estoc.get(clave);	//obtenemos el array donde se almacena el precio y la cantidad con el metodo get(clave)
		
		System.out.println("El producto: " + clave + " tiene un precio de " + articulos[0] + "€ y tenemos disponibles " + (int)articulos[1] + " unidades.");


	}

}
