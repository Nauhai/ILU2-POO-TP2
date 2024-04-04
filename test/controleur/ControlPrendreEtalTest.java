package controleur;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Village;

class ControlPrendreEtalTest {
	private Village village;
	private Chef chef;
	private ControlVerifierIdentite controlVerifierIdentite;
	private ControlPrendreEtal controlPrendreEtal;
	
	@BeforeEach
	public void initialisationSituation() {
		village = new Village("le village des irréductibles", 10, 2);
		Chef chef = new Chef("Abraracourcix", 10, village);
		village.setChef(chef);
		controlVerifierIdentite = new ControlVerifierIdentite(village);
		controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite, village);
	}

	@Test
	void testControlPrendreEtal() {
		assertNotNull(controlPrendreEtal);
	}

	@Test
	void testResteEtals() {
		assertTrue(controlPrendreEtal.resteEtals());
		village.installerVendeur(new Gaulois("Bonemine", 10), "fleurs", 10);
		assertTrue(controlPrendreEtal.resteEtals());
		village.installerVendeur(new Gaulois("Panoramix", 10), "pains", 10);
		assertFalse(controlPrendreEtal.resteEtals());
		
	}

	@Test
	void testPrendreEtal() {
		Gaulois gaulois = new Gaulois("Bonemine", 10);
		village.ajouterHabitant(gaulois);
		assertNull(village.rechercherEtal(gaulois));
		controlPrendreEtal.prendreEtal("Bonemine", "fleurs", 10);
		assertNotNull(village.rechercherEtal(gaulois));
		assertArrayEquals(village.donnerEtatMarche(), new String[] {"Bonemine", "10", "fleurs"});
	}

	@Test
	void testVerifierIdentite() {
		assertTrue(controlPrendreEtal.verifierIdentite("Abraracourcix"));
		assertFalse(controlPrendreEtal.verifierIdentite("Bonemine"));
		village.ajouterHabitant(new Gaulois("Bonemine", 10));
		assertTrue(controlPrendreEtal.verifierIdentite("Bonemine"));
		assertFalse(controlPrendreEtal.verifierIdentite("Panoramix"));
		village.ajouterHabitant(new Druide("Panoramix", 10, 1, 5));
		assertTrue(controlPrendreEtal.verifierIdentite("Panoramix"));
	}

}
