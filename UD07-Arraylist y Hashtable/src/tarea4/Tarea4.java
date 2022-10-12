package tarea4;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

public class Tarea4 {

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
		
		estoc.put("Arroz", new double[] {0.80, 12});
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
		System.out.println("4.Comprar");
		System.out.println("5.Salir");
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
				
				Hashtable<String, double[]> productos = new Hashtable<String, double[]>();
				
				agregarCarro(productos, estoc); //ahora la llamada a agregarCarro contiene el hashtable de productos que compraremos y los que hay en stock
				
				double totalAPagar = mostrarProductos(productos);
				
				pagarCompra(totalAPagar);
				break;
				
			case 5:
				
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
	
	public static void agregarCarro( Hashtable<String, double[]> prods, Hashtable<String, double[]> estoc) {
		
		boolean finBucle = true;
		
		
		do {
				
			System.out.println("Que producto quieres comprar? (Escribe 'fin' para terminar)");
			String nombreProd = s.next();

			
			estoc.forEach((clave, valor) -> {	//por cada elemento del diccionario
				
				if(nombreProd.equals(clave)) {	//compruebo si el nombre del producto a comprar existe
					//System.out.println("Iguales");
					double[] articulo = new double[4];
					
					articulo[0] = estoc.get(nombreProd)[0]; //Precio: llamamos a la funcion get con el nombre del producto del hash estoc y cogemos la posicion 0 del array de double, donde esta el precio
					
					if(articulo[0] < 25)
						articulo[1] = 0.21;						// IVA: si el precio del producto es menor a 25 € sera del 21%, sino será del 4%
					else articulo[1] = 0.04;					
					
					System.out.println("Cuantas unidades quieres?");
					articulo[2] = s.nextInt(); // Cantidad
					estoc.get(nombreProd)[1] = estoc.get(nombreProd)[1] - articulo[2];	//Restamos la cantidad al estoc
					
					articulo[3] = (articulo[0]*articulo[1])+articulo[0]; //Precio + IVA

					prods.put(nombreProd, articulo);
				} 
				
			});	
			
			/*
			if(!nombreProd.equals("fin")) {
				double[] articulo = new double[4];
				
				articulo[0] = (Math.random() * (50 - 1)) + 1; //Precio
				
				if(articulo[0] < 25)
					articulo[1] = 0.21;						// IVA: si el precio del producto es menor a 25 € sera del 21%, sino será del 4%
				else articulo[1] = 0.04;
				
				System.out.println("Cuantas unidades quieres?");
				articulo[2] = s.nextInt(); // Cantidad
				
				articulo[3] = (articulo[0]*articulo[1])+articulo[0]; //Precio + IVA

				prods.put(nombreProd, articulo);
			} */
			
			if(nombreProd.equals("fin")){
				finBucle = false;
			}
			
		}while(finBucle);
		

	}
	
	
	public static double mostrarProductos( Hashtable<String, double[]> listaProductos ) {
		Enumeration<String> nombreProd = listaProductos.keys();		//Usamos una enumeracion con la funcion keys para devolver las claves del hashtable (los nombres de los productos)
		System.out.println("Lista de productos: ");
		double precioTotal = 0.0;
		
		while(nombreProd.hasMoreElements()) {
			
			String clave = nombreProd.nextElement();
			
			double[] articulo = listaProductos.get(clave);
			double precioSinIVA = articulo[0];
			double IVA = articulo[1];
			double cantidad = articulo[2];
			double precio =  articulo[3];
			
			precioTotal = precioTotal+(precio*cantidad); //Calculo del precio total de la compra sumando el valor de este + precio de cada articulo por la cantidad adquirida
			
			System.out.println("El producto: " + clave + " tiene un precio bruto de " + precioSinIVA + "€, su IVA aplicable es del " + (int)(IVA*100) + "%, por tanto su precio final es de " + precio + "€");
			if(cantidad>1)
				System.out.println("Como usted ha adquirido " + (int)cantidad + " unidades, el precio es de " + (precio*cantidad) + "€");
		}
		return precioTotal;
	}
	
	
	public static void pagarCompra(double precioAPagar) {
		
		int pagado = 0;
		
		do {
			
			precioAPagar=precioAPagar-pagado;
			System.out.println("Usted debe: "+precioAPagar + "€");
			System.out.println("Introduzca dinero:");
			pagado = s.nextInt();
								
			
		}while(precioAPagar-pagado > 0);

		
		System.out.println("Cambio: "+Math.round((pagado-precioAPagar)*100.0)/100.0);
		
	}

}
