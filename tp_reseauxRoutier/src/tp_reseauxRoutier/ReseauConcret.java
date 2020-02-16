package tp_reseauxRoutier;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ReseauConcret implements Reseau {

	private List<Route> listRoute;
	
	
	

	public String toString() {
		String resultatRoutes = new String();
		for(Route route:routes()) {
			resultatRoutes += route.toString();
			
		}
		return resultatRoutes;
	}
	
	public ReseauConcret() {
		this.listRoute = new ArrayList<Route>();
		
	}
	
	public ReseauConcret(List<Route> listAutre) {
		this.listRoute = new ArrayList<Route>();
		
		for(Route route:listAutre) {
			this.ajouterRoute(route);
			
		}
	}

	//retourne le nombre de routes du réseau
	@Override
	public int taille() {
		return this.listRoute.size();
	}

	//retourne la liste des routes du réseau
	@Override
	public List<Route> routes() {
		return this.listRoute;
	}

	//retourne les routes du réseau this qui partent de la ville v
	@Override
	public List<Route> routes(Ville v) {
		Reseau reseauVille = new ReseauConcret();
		for (Route route:routes()) {
			if (route.appartient(v)) {
				reseauVille.ajouterRoute(route);
			}
		}
		return reseauVille.routes();
	}

	//retourne la i-ème route du réseau
	@Override
	public Route route(int i) {
		return listRoute.get(i);
	}

	//ajoute une route au réseau
	@Override
	public void ajouterRoute(Route r) {
		listRoute.add(r);
		
	}

	//retourne vrai si la route r est la même qu'une des routes du réseau, faux sinon
	@Override
	public boolean estDans(Route r) {
		for(Route route : this.listRoute) {
			if(route.memeRoute(r)==true) {
				return true;
			}
		}
		return false;
	}

	//retourne vrai si la route r relie deux villes qui ne sont pas reliées par une meilleure route dans le réseau, faux sinon
	@Override
	public boolean bonneRoute(Route r) {
		for(Route route:this.routes()) {
			if(route.ontMemesVilles(r)==true) {
				return (route.meilleur(r));
			}
		}
		return true;
	}
	
	//retourne les meilleurs routes de res et this (plus courtes routes reliant les mêmes villes)
	//on parcoure les routes du réseau res
	//si la route de res n'est pas dans le réseau qui contient les routes this 
	//si la route de res est la meilleur route du réseau
		//on parcoure les routes this
		//si la route this a les mêmes villes que la route du réseau res
		//on enleve la route this des routes du réseau unionReseau
	//on ajoute la route de res au réseau unionReseau
	//on retourne le réseau union réseau
	@Override
	public Reseau unionReseaux(Reseau res) {
		Reseau unionReseau = new ReseauConcret(this.routes());

		for(Route routeRes:res.routes()){
			if(!unionReseau.estDans(routeRes)){
				if(unionReseau.bonneRoute(routeRes)){
					for(Route routeThis:this.routes()){
						if(routeThis.ontMemesVilles(routeRes)){
							unionReseau.routes().remove(routeThis);
						}
					}
					unionReseau.ajouterRoute(routeRes);
				}
			}
		}
		return unionReseau;
	}
	
	//retourne les meilleures routes de res et de this
	//on parcoure les routes de this et les routes de res
	//si la route de res et la route this se suivent 
	//on creer une nouvelle route qui met bout à bout la route res et la route this
	//si la nouvelle route est la meilleure du reseau produitReseau et qu'elle n'est pas déjà dedans
		//on parcoure les routes du réseau produitReseau
		//si la nouvelle route a les même ville que la ville du réseau produitReseau
		//on ajoute la route du réseau produitReseau au reseauPlus
	//on ajoute la nouvelle route au réseau produitReseau
	//on parcoure le réseau reseauPlus
	//on enlève les routes du réseauPlus au réseau produitReseau
	//on retourne le réseau produitReseau
	@Override
	public Reseau produitRoutes(Reseau res) {
		Reseau produitReseau = new ReseauConcret();
		Reseau reseauPlus = new ReseauConcret();
		
		for(Route routeThis:this.routes()){
			for(Route routeRes:res.routes()){
				if(routeRes.seSuivent(routeThis)){
					Route newR = new RouteConcrete(routeRes, routeThis);
					if(produitReseau.bonneRoute(newR) && !produitReseau.estDans(newR)){
						for(Route produitRoute:produitReseau){
							if(produitRoute.ontMemesVilles(newR)){
								reseauPlus.ajouterRoute(produitRoute);
							}
						}
						produitReseau.ajouterRoute(newR);
					}
				}
			}
		}
		for(Route route:reseauPlus.routes()){
			produitReseau.routes().remove(route);
		}
		return produitReseau;
	}

	//retourne le réseau des route les plus courtes qu'il est possible de prendre à partir de n'impote quelle ville vers n'importe quelle autre ville en traversant au maximum trois routes de this
	@Override
	public Reseau chemins3() {
		Reseau reseau = new ReseauConcret();
		Reseau chemins3 = new ReseauConcret(this.routes());
		for(int i=0; i<2; i++) {
			reseau = this.produitRoutes(chemins3);
			chemins3 = chemins3.unionReseaux(reseau);
		}
		return chemins3;
		
		
	}
	
	//retourne le réseau des routes les plus courtes qu'il est possible de prendre à partir de n'importe quelle ville vers n'importe quelle autre ville traversant autant de ville qu'on désire 
	@Override
	public Reseau chemins() {
		Reseau reseau = new ReseauConcret();
		Reseau chemins = new ReseauConcret(this.routes());
		for(int i=0; i<this.taille(); i++) {
			reseau = this.produitRoutes(chemins);
			chemins = chemins.unionReseaux(reseau);
		}
		return chemins;
		
	}

	@Override
	public Iterator<Route> iterator() {
		
		return this.routes().iterator();
	}
	
	public static void main(String[] args) {
        Ville arras = new VilleConcrete("Arras", 40721);
        Ville nantes = new VilleConcrete("Nantes", 303382);
        Ville strasbourg = new VilleConcrete("Strasbourg", 277270);
        Ville paris = new VilleConcrete("Paris", 2220445);
        Ville brest = new VilleConcrete("Brest", 139163);
        Ville lyon = new VilleConcrete("Lyon", 513275);
        Ville poitiers = new VilleConcrete("Poitiers", 87918);
        Ville bordeaux = new VilleConcrete("Bordeaux", 249712);
        Ville montpellier = new VilleConcrete("Montpellier", 277639);
        Ville marseille = new VilleConcrete("Marseille", 861635);
        
        Route ArSt = new RouteConcrete(arras, strasbourg, 522);
        Route ArNa = new RouteConcrete(arras, nantes, 561);
        Route ArPa = new RouteConcrete(arras, paris, 185);
        Route NaBr = new RouteConcrete(nantes, brest, 298);
        Route NaPa = new RouteConcrete(nantes, paris, 386);
        Route BrPa = new RouteConcrete(brest, paris, 593);
        Route StLy = new RouteConcrete(strasbourg, lyon, 494);
        Route PaLy = new RouteConcrete(paris, lyon, 465);
        Route NaBo = new RouteConcrete(nantes, bordeaux, 334);
        Route PaPo = new RouteConcrete(paris, poitiers, 338);
        Route BoPo = new RouteConcrete(bordeaux, poitiers, 237);
        Route PoMo = new RouteConcrete(poitiers, montpellier, 557);
        Route MoLy = new RouteConcrete(montpellier, lyon, 303);
        Route MoMa = new RouteConcrete(montpellier, marseille, 171);
        Route MoSt = new RouteConcrete(montpellier, strasbourg, 797);
        Route MaSt = new RouteConcrete(marseille, strasbourg, 809);
        Route LyMa = new RouteConcrete(lyon, marseille, 315);
        
        Reseau res = new ReseauConcret();
        res.ajouterRoute(ArSt);
        res.ajouterRoute(ArNa);
        res.ajouterRoute(ArPa);
        res.ajouterRoute(NaBr);
        res.ajouterRoute(NaPa);
        res.ajouterRoute(BrPa);
        res.ajouterRoute(StLy);
        res.ajouterRoute(PaLy);
        res.ajouterRoute(NaBo);
        res.ajouterRoute(PaPo);
        res.ajouterRoute(BoPo);
        res.ajouterRoute(PoMo);
        res.ajouterRoute(MoLy);
        res.ajouterRoute(MoMa);
        res.ajouterRoute(MoSt);
        res.ajouterRoute(MaSt);
        res.ajouterRoute(LyMa);
        
        System.out.println(res.chemins().toString());
        System.out.println(res.chemins().taille());

    }
}


