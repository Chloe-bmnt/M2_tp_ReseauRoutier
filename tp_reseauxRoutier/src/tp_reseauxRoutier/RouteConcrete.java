package tp_reseauxRoutier;

public class RouteConcrete implements Route {
	

	private double distance;
	private Ville ville1;
	private Ville ville2;

	public String toString() {
		String resultat = new String();
		resultat += this.ville1.toString();
		resultat += " - ";
		resultat += this.ville2.toString();
		resultat += " : ";
		resultat += this.distance;
		resultat += " km\n";
		return resultat;
	}
	public RouteConcrete(Ville v, Ville w, double distance) {
		this.ville1 = v;
		this.ville2 = w;
		this.distance = distance;
	}
	
	public RouteConcrete(Route r, Route s) {
		if (!s.appartient(r.ville1())) {
			this.ville1 = r.ville1();
		}else
		{
			this.ville1 = r.ville2();	
		}
		if (!r.appartient(s.ville1())) {
			this.ville2 = s.ville1();
		}else
		{
			this.ville2 = s.ville2();
		}
		this.distance = r.distance() + s.distance();
		
	}
	
	//retourne la première ville de la route 
	@Override
	public Ville ville1() {
		return ville1;
	}

	//retourne la deuxième ville de la route
	@Override
	public Ville ville2() {
		return ville2;
	}

	//retourne la distance entre ville1 et ville2
	@Override
	public double distance() {
		return distance;
	}

	//retourne vrai si la ville v est une des deux villes de la route, faux sinon
	@Override
	public boolean appartient(Ville v) {	
		if (v.memeVille(this.ville1()) || v.memeVille(this.ville2())) {
			return true;
		}
		else
			{
			return false;
			}
	}

	//retourn vrai si les routes r et this relient les mêmes villes, faux sinon
	@Override
	public boolean ontMemesVilles(Route r) {
		if (this.appartient(r.ville1()) && this.appartient(r.ville2())){
			return true;
		}
		else {
			return false;
		}
	}

	//retourne vrai si les routes r et this sont les mêmes, faux sinon
	@Override
	public boolean memeRoute(Route r) {
		if (this.ontMemesVilles(r) && this.distance() == r.distance()) {
			return true;
		}
		else {
			return false;
		}
	}

	//retourne vrai si la route r est strictement plus courte que la route this, faux sinon
	@Override
	public boolean meilleur(Route r) {
		if (r.distance() < this.distance()) {
			return true;
		}
		else{
			return false;
		}
	}

	//retourne vrai si les routes r et this sont différentes et reliées par une même ville
	@Override
	public boolean seSuivent(Route r) {
		if(!this.ontMemesVilles(r)) {
			if (this.appartient(r.ville1()) || this.appartient(r.ville2())){
				return true;
			}
			else {
				return false;
			}
		}else {
		return false;
		}
	}

}
