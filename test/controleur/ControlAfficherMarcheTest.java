package controleur;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Village;

class ControlAfficherMarcheTest {
	private Village village;
	private Chef chef;
	private ControlAfficherMarche control;
	
	@BeforeEach
	public void initialiserSituation() {
		village = new Village("le village des irréductibles", 10, 5);
		chef = new Chef("Abraracourcix", 10, village);
		village.setChef(chef);
		control = new ControlAfficherMarche(village);	
	}

	@Test
	void testControlAfficherMarche() {
		assertNotNull(control);
	}

	@Test
	void testDonnerInfosMarche() {
		assertArrayEquals(control.donnerInfosMarche(), new String[] {});
		village.installerVendeur(new Gaulois("Bonemine", 10), "fleurs", 10);
		assertArrayEquals(control.donnerInfosMarche(), new String[] {"Bonemine", "10", "fleurs"});
		village.installerVendeur(new Druide("Panoramix", 10, 1, 5), "pains", 5);
		assertArrayEquals(control.donnerInfosMarche(), new String[] {"Bonemine", "10", "fleurs", "Panoramix", "5", "pains"});
	}

}
