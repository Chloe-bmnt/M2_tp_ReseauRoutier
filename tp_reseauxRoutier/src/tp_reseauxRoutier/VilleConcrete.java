package tp_reseauxRoutier;

public class VilleConcrete implements Ville {

	private String nom;
	private int population;

	public String toString() {
		String resultat = new String();
		resultat += this.nom();
		resultat += " (" + this.population() + " habitants)";
		return resultat;
	}
	
	public VilleConcrete(String nom, int population) {
		this.nom = nom;
		this.population = population;
	}

	//retourne le nom de la ville
	@Override
	public String nom() {
		
		return nom;
	}

	//retourne le nombre d'habitants de la ville
	@Override
	public int population() {
		
		return population;
	}

	//Retourne vrai si les villes this et v sont les mêmes (même nom et même nombre d'habitants), faux sinon
	@Override
	public boolean memeVille(Ville v) {
		if (this.nom() == v.nom() && this.population() == v.population()) {
			return true;
		}
		else {
			return false;
		}
	}

	

}

