package tarea1;

import java.util.Hashtable;
import java.util.Scanner;

public class Tarea1 {
	
	public static void main(String[] args) {	//main de la aplicacion, llamada a la funcion notas y notaMedia
		
		int[][] listaNotas = notas();
		
		notaMedia(listaNotas);
	}
	
	public static int[][] notas() {				//funcion notas, pide el numero de alumnos y examenes con sus respectivas notas
		
		Scanner s = new Scanner(System.in);
		System.out.println("Introduce la cantidad de alumnos: ");
		int alumnos = s.nextInt();
		System.out.println("Introduce la cantidad de examenes del curso: ");
		int examenes = s.nextInt();
		
		int[][] lista = new int[alumnos][examenes];	//estas notas se almacenan en un array bidimensional, donde cada alumno es un array con sus notas
		
		for(int i=0; i<alumnos; i++) {		//en este bucle se pide al usuario que escriba las notas de cada alumno en cada examen
			
			for(int j=0; j<examenes; j++) {
				System.out.println("Indica la nota del alumno " + (i+1) + " en el examen " +(j+1));
				lista[i][j] = s.nextInt();
			}
			
		}
		
		s.close();
		return lista;
	}
	
	public static void notaMedia(int listaNotas[][]) {	//funcion que calcula la media de cada alumno
		
		double media = 0;
		double resultado;
 
		Hashtable <Integer, Double> notaMedia = new Hashtable <Integer, Double>();	//se crea un hashtable 
		
		for (int i=0; i<listaNotas.length; i++) {		//bucles anidados para recorrer el hashtable
			
			for (int j=0; j<listaNotas[i].length; j++) {
				
				media = media + listaNotas[i][j];		//la variable media se obtiene sumando las notas de cada alumno (iterando dentro del bucle anidado)
				
			}
 
			resultado = media/(listaNotas[0].length);	//el resultado de la media se hace diviendo la suma de las notas entre el numero de examenes (longitud de la array contenida en cada posición del primer array, por eso hacemos listaNotas[0].length)

			notaMedia.put(i, resultado);				//usamos el metodo put para añadir la media del alumno al hashtable
 
			media=0;									//reiniciamos el valor de la media para el siguiente alumno
		}
 
		for (int i=0; i<listaNotas.length; i++) {
			System.out.println("La nota media del alumno "+(i+1)+" es: "+notaMedia.get(i));
		}
 
	}

}
