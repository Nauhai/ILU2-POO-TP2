package controleur;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.Village;

class ControlLibererEtalTest {
	private Village village;
	private Chef chef;
	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;
	private ControlLibererEtal controlLibererEtal;
	
	@BeforeEach
	public void initialisationSituation() {
		village = new Village("le village des irréductibles", 10, 2);
		chef = new Chef("Abraracourcix", 10, village);
		village.setChef(chef);
		controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
		controlLibererEtal = new ControlLibererEtal(controlTrouverEtalVendeur, village);
	}

	@Test
	void testControlLibererEtal() {
		assertNotNull(controlLibererEtal);
	}

	@Test
	void testIsVendeur() {
		Gaulois gaulois = new Gaulois("Bonemine", 10);
		assertFalse(controlLibererEtal.isVendeur("Bonemine"));
		village.ajouterHabitant(gaulois);
		assertFalse(controlLibererEtal.isVendeur("Bonemine"));
		village.installerVendeur(gaulois, "fleurs", 10);
		assertTrue(controlLibererEtal.isVendeur("Bonemine"));
		assertFalse(controlLibererEtal.isVendeur("Panoramix"));
	}

	@Test
	void testLibererEtal() {
		Gaulois gaulois = new Gaulois("Bonemine", 10);
		village.ajouterHabitant(gaulois);
		village.installerVendeur(gaulois, "fleurs", 10);
		assertArrayEquals(controlLibererEtal.libererEtal("Bonemine"), new String[] {"true", "Bonemine", "fleurs", "10", "0"});
		assertArrayEquals(village.donnerEtatMarche(), new String[] {});
	}

}
