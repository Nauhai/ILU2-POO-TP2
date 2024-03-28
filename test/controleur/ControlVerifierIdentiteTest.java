package controleur;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Village;

class ControlVerifierIdentiteTest {
	private Village village;
	private Chef chef;
	private ControlVerifierIdentite control;
	
	@BeforeEach
	public void initialisationSituation() {
		village = new Village("le village des irréductibles", 10, 5);
		chef = new Chef("Abraracourcix", 10, village);
		village.setChef(chef);
		control = new ControlVerifierIdentite(village);	
	}

	@Test
	void testControlVerifierIdentite() {
		assertNotNull(control);
	}

	@Test
	void testVerifierIdentite() {
		assertTrue(control.verifierIdentite("Abraracourcix"));
		assertFalse(control.verifierIdentite("Bonemine"));
		village.ajouterHabitant(new Gaulois("Bonemine", 10));
		assertTrue(control.verifierIdentite("Bonemine"));
		assertFalse(control.verifierIdentite("Panoramix"));
		village.ajouterHabitant(new Druide("Panoramix", 10, 1, 5));
		assertTrue(control.verifierIdentite("Panoramix"));
	}

}
