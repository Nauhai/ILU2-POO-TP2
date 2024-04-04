package controleur;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Village;

class ControlAcheterProduitTest {
	private Village village;
	private Chef chef;
	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;
	private ControlVerifierIdentite controlVerifierIdentite;
	private ControlAcheterProduit controlAcheterProduit;
	
	@BeforeEach
	public void initialisationSituation() {
		village = new Village("le village des irréductibles", 10, 5);
		chef = new Chef("Abraracourcix", 10, village);
		village.setChef(chef);
		controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
		controlVerifierIdentite = new ControlVerifierIdentite(village);
		controlAcheterProduit = new ControlAcheterProduit(controlVerifierIdentite, controlTrouverEtalVendeur, village);
	}

	@Test
	void testControlAcheterProduit() {
		assertNotNull(controlAcheterProduit);
	}

	@Test
	void testVerifierIdentite() {
		assertTrue(controlAcheterProduit.verifierIdentite("Abraracourcix"));
		assertFalse(controlAcheterProduit.verifierIdentite("Bonemine"));
		village.ajouterHabitant(new Gaulois("Bonemine", 10));
		assertTrue(controlAcheterProduit.verifierIdentite("Bonemine"));
		assertFalse(controlAcheterProduit.verifierIdentite("Panoramix"));
		village.ajouterHabitant(new Druide("Panoramix", 10, 1, 5));
		assertTrue(controlAcheterProduit.verifierIdentite("Panoramix"));
	}

	@Test
	void testRechercherVendeursProduit() {
		assertNull(controlAcheterProduit.rechercherVendeursProduit("fleurs"));
		Gaulois bonemine = new Gaulois("Bonemine", 10);
		village.ajouterHabitant(bonemine);
		village.installerVendeur(bonemine, "fleurs", 10);
		assertArrayEquals(controlAcheterProduit.rechercherVendeursProduit("fleurs"), new Gaulois[] {bonemine});
		Druide panoramix = new Druide("Panoramix", 10, 1, 5);
		village.ajouterHabitant(panoramix);
		village.installerVendeur(panoramix, "fleurs", 5);
		assertArrayEquals(controlAcheterProduit.rechercherVendeursProduit("fleurs"), new Gaulois[] {bonemine, panoramix});
		assertNull(controlAcheterProduit.rechercherVendeursProduit("pains"));
	}

	@Test
	void testAcheterProduit() {
		Gaulois bonemine = new Gaulois("Bonemine", 10);
		village.ajouterHabitant(bonemine);
		village.installerVendeur(bonemine, "fleurs", 10);
		assertEquals(controlAcheterProduit.acheterProduit("Bonemine", 5), 5);
		assertEquals(controlAcheterProduit.acheterProduit("Bonemine", 10), 5);
		assertEquals(controlAcheterProduit.acheterProduit("Bonemine", 5), 0);
	}

}
