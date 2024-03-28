package controleur;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import villagegaulois.Village;

class ControlEmmenagerTest {
	private Village village;
	private Chef chef;
	private ControlEmmenager control;
	
	@BeforeEach
	public void initialiserSituation() {
		village = new Village("le village des irréductibles", 10, 5);
		chef = new Chef("Abraracourcix", 10, village);
		village.setChef(chef);
		control = new ControlEmmenager(village);
	}

	@Test
	void testControlEmmenager() {
		assertNotNull(control);
	}

	@Test
	void testIsHabitant() {
		control.ajouterGaulois("Bonemine", 10);
		assertTrue(control.isHabitant("Bonemine"));
		assertFalse(control.isHabitant("mqsdlfkjsdq"));
		
		control.ajouterDruide("Panoramix", 10, 1, 5);
		assertTrue(control.isHabitant("Panoramix"));
	}

	@Test
	void testAjouterDruide() {
		control.ajouterDruide("Panoramix", 10, 1, 5);
	}

	@Test
	void testAjouterGaulois() {
		control.ajouterGaulois("Bonemine", 10);
	}

}
