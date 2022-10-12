package tarea2;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

public class Tarea2 {
	
	static Scanner s = new Scanner(System.in);

	public static void main(String[] args) {

		Hashtable<String, double[]> productos = new Hashtable<String, double[]>();
		
		agregarCarro(productos);
		
		double totalAPagar = mostrarProductos(productos);
		
		pagarCompra(totalAPagar);

	}
	
	
	public static void agregarCarro( Hashtable<String, double[]> prods) {
		
		boolean finBucle = true;
		
		
		do {
			
			System.out.println("Que producto quieres comprar? (Escribe 'fin' para terminar)");
			String nombreProd = s.next();
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
			} else {
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
