package com.camilachamblas.controllers;
import java.util.HashMap;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController


public class controladorPeliculas {

	private static HashMap<String, String> listaPeliculas = new HashMap<String, String>();
	

	public controladorPeliculas() {
		listaPeliculas.put("Winnie the Pooh", "Don Hall");	
		listaPeliculas.put("El zorro y el sabueso", "Ted Berman");
		listaPeliculas.put("Tarzán", "Kevin Lima");		
		listaPeliculas.put("Mulán", "Barry Cook");
		listaPeliculas.put("Oliver", "Kevin Lima");	
		listaPeliculas.put("Big Hero 6", "Don Hall");	
	}
	
	@GetMapping("/peliculas")
	public String obtenerTodasLasPeliculas() {
		String pelist = ""; 
		for(String pelicula : listaPeliculas.keySet()) {
			String director = listaPeliculas.get(pelicula);
			pelist += "Pelicula: " + pelicula + "- Director: " + director + "<br>";
		}
		return pelist;
	}
	
	@GetMapping("/peliculas/{nombre}")
	public String obtenerPeliculaPorNombre(@PathVariable String nombre) {
		String director = listaPeliculas.get(nombre);
        if (director != null) {
            return "Película: " + nombre + " - Director: " + director;
        } else {
            return "La película no se encuentra en nuestra lista.";
        }
	}

    @GetMapping("/peliculas/director/{nombre}")
    public String obtenerPeliculasPorDirector(@PathVariable String nombre) {
        ArrayList<String> peliDirector = new ArrayList<>();

        for (String pelicula : listaPeliculas.keySet()) {
            if (listaPeliculas.get(pelicula).equalsIgnoreCase(nombre)) {  
                peliDirector.add(pelicula); 
            }
        }
        
        if (peliDirector.isEmpty()) {
            return "No contamos con películas con ese director en nuestra lista";
        }
        
        String listaMovie = "Películas de " + nombre + ":<br>";
        for (String pelicula : peliDirector) {
            listaMovie += "- " + pelicula + "<br>";
        }
        
        return listaMovie;
    }
	
	
}

//http://localhost:8080/peliculas
//http://localhost:8080/peliculas/Winnie%20the%20Pooh
// http://localhost:8080/peliculas/director/Don%20Hall