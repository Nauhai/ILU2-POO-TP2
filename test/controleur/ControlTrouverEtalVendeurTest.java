package controleur;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

class ControlTrouverEtalVendeurTest {
	private Village village;
	private Chef chef;
	private ControlTrouverEtalVendeur control;
	
	@BeforeEach
	public void initialisationSituation() {
		village = new Village("le village des irréductibles", 10, 5);
		chef = new Chef("Abraracourcix", 10, village);
		village.setChef(chef);
		control = new ControlTrouverEtalVendeur(village);
	}

	@Test
	void testControlTrouverEtalVendeur() {
		assertNotNull(control);
	}

	@Test
	void testTrouverEtalVendeur() {
		assertNull(control.trouverEtalVendeur("Abraracourcix"));
		assertNull(control.trouverEtalVendeur("Bonemine"));
		Gaulois vendeur = new Gaulois("Bonemine", 10);
		village.ajouterHabitant(vendeur);
		village.installerVendeur(vendeur, "fleurs", 10);
		Etal etal = control.trouverEtalVendeur("Bonemine");
		assertNotNull(etal);
		assertEquals(etal.getVendeur(), vendeur);
		assertEquals(etal.getProduit(), "fleurs");
		assertEquals(etal.getQuantite(), 10);
	}

}
