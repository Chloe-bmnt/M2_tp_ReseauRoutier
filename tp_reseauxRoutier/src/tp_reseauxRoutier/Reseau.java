package tp_reseauxRoutier;

import java.util.List;

public interface Reseau extends Iterable<Route> {
	
	int taille();
	
	List<Route> routes();
	
	List<Route> routes(Ville v);
	
	Route route(int i);
	
	void ajouterRoute(Route r);
	
	boolean estDans(Route r);
	
	boolean bonneRoute(Route r);
	
	Reseau unionReseaux(Reseau res);
	
	Reseau produitRoutes(Reseau res);
	
	Reseau chemins3();
	
	Reseau chemins();
	

}
