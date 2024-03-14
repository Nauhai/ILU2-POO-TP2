package frontiere;

import controleur.ControlAfficherMarche;

public class BoundaryAfficherMarche {
	private ControlAfficherMarche controlAfficherMarche;

	public BoundaryAfficherMarche(ControlAfficherMarche controlAfficherMarche) {
		this.controlAfficherMarche = controlAfficherMarche;
	}

	public void afficherMarche(String nomAcheteur) {
		String[] infosMarche = controlAfficherMarche.donnerInfosMarche();
		if (infosMarche.length == 0) {
			System.out.println("Le marché est vide, revenez plus tard.");
		} else {
			StringBuilder builder = new StringBuilder(nomAcheteur + ", vous trouverez au marché :");
			int i = 0;
			while (i < infosMarche.length) {
				String vendeur = infosMarche[i++];
				String quantite = infosMarche[i++];
				String produit = infosMarche[i++];
				builder.append("\n- " + vendeur + " qui vend " + quantite + " " + produit + "");
			}
			System.out.println(builder.toString());
		}
	}
}
