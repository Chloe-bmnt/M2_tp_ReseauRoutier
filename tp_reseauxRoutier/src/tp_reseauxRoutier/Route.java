package tp_reseauxRoutier;

public interface Route {
	Ville ville1();
	
	Ville ville2();
	
	double distance();
	
	boolean appartient(Ville v);
	
	boolean ontMemesVilles(Route r);
	
	boolean memeRoute(Route r);
	
	boolean meilleur(Route r);
	
	boolean seSuivent(Route r);

}