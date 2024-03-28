package controleur;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Village;

class ControlAfficherVillageTest {
	private Village village;
	private Chef chef;
	private ControlAfficherVillage control;
	
	@BeforeEach
	public void initialiserSituation() {
		village = new Village("le village des irréductibles", 10, 5);
		chef = new Chef("Abraracourcix", 10, village);
		village.setChef(chef);
		control = new ControlAfficherVillage(village);
	}

	@Test
	void testControlAfficherVillage() {
		assertNotNull(control);
	}

	@Test
	void testDonnerNomsVillageois() {
		assertArrayEquals(control.donnerNomsVillageois(), new String[]{"Abraracourcix"});
		village.ajouterHabitant(new Gaulois("Bonemine", 10));
		assertArrayEquals(control.donnerNomsVillageois(), new String[]{"Abraracourcix", "Bonemine"});
		village.ajouterHabitant(new Druide("Panoramix", 10, 1, 5));
		assertArrayEquals(control.donnerNomsVillageois(), new String[] {"Abraracourcix", "Bonemine", "le druide Panoramix"});
	}

	@Test
	void testDonnerNomVillage() {
		assertEquals(control.donnerNomVillage(), "le village des irréductibles");
	}

	@Test
	void testDonnerNbEtals() {
		assertEquals(control.donnerNbEtals(), 5);
	}

}
