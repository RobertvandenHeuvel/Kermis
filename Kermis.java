import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Kermis {

	public static void main(String[] args) {
		Admin admin = new Admin();
		Kassa kassa = new Kassa();
		BelastingInspecteur belastingInspecteur = new BelastingInspecteur();
		boolean kermisOpen = true;
		Scanner scanner = new Scanner(System.in);
		Random random = new Random();

		// make the park
		Botsautos botsautos = admin.maakBotsautos();
		Spin spin = admin.maakSpin();
		Spiegelpaleis spiegelpaleis = admin.maakSpiegelpaleis();
		Spookhuis spookhuis = admin.maakSpookhuis();
		Hawaii hawaii = admin.maakHawaii();
		Ladderklimmen ladderklimmen = admin.maakLadderklimmen();
		ArrayList<Attractie> attractieArrayList = new ArrayList();
		attractieArrayList.add(botsautos);
		attractieArrayList.add(spin);
		attractieArrayList.add(spiegelpaleis);
		attractieArrayList.add(spookhuis);
		attractieArrayList.add(hawaii);
		attractieArrayList.add(ladderklimmen);

		// Open the park
		admin.welkom();
		while (kermisOpen == true) {
			int taxman = random.nextInt(15);
			if (taxman == 4) {
				System.out.println("De belastinginspecteur komt langs");
				kassa.aantalBIBezoeken++;
				for (Attractie a : attractieArrayList) {
					belastingInspecteur.checkenEnBetalen(a);
				} // end for loop
				kassa.omzetTotaal = botsautos.omzet + spin.omzet + spiegelpaleis.omzet + spookhuis.omzet + hawaii.omzet
						+ ladderklimmen.omzet;
				System.out.println("De belasting is betaald.");
			} // end if statement
			String invoer = scanner.nextLine();
			switch (invoer) {
			case "1":
				System.out.println("De botsauto's draait.");
				botsautos.kaartjes++;
				botsautos.omzet += botsautos.prijs;
				break;
			case "2":
				try {
					spin.teVaakGedraaid();
					spin.kaartjes++;
					spin.aantalDraaienSindsKeuring++;
					spin.omzet += spin.prijs;
					System.out.println("De spin draait.");
				} //end try statement
				catch(OnderhoudException o){
					System.out.println("Druk (m) om de monteur te bellen.");
					String invoer2 = scanner.nextLine();
					while (! invoer2.contentEquals("m")) {
						System.out.println("Verkeerde invoer.");
						invoer2 = scanner.nextLine();
					} //end while loop
					if (invoer2.contentEquals("m")) {
						spin.opstellingsKeuring();
					} //end if statement
				} //end catch statement
				break;
			case "3":
				System.out.println("Het spiegelpaleis draait.");
				spiegelpaleis.kaartjes++;
				spiegelpaleis.omzet += spiegelpaleis.prijs;
				break;
			case "4":
				System.out.println("Het spookhuis draait.");
				spookhuis.kaartjes++;
				spookhuis.omzet += spookhuis.prijs;
				break;
			case "5":
				try {
					hawaii.teVaakGedraaid();
					hawaii.kaartjes++;
					hawaii.aantalDraaienSindsKeuring++;
					hawaii.omzet += hawaii.prijs;
					System.out.println("De hawaii draait.");
				} //end try statement
				catch(OnderhoudException o){
					System.out.println("Druk (m) om de monteur te bellen.");
					String invoer2 = scanner.nextLine();
					while (! invoer2.contentEquals("m")) {
						System.out.println("Verkeerde invoer.");
						invoer2 = scanner.nextLine();
					} //end while loop
					if (invoer2.contentEquals("m")) {
						hawaii.opstellingsKeuring();
					} //end if statement
				} //end catch statement
				break;
			case "6":
				System.out.println("Het ladderklimmen draait.");
				ladderklimmen.kaartjes++;
				ladderklimmen.omzet = ladderklimmen.prijs * ladderklimmen.kaartjes;
				break;
			case "q":
				System.out.println("De kermis is gesloten");
				System.exit(0);
				break;
			case "o":
				System.out.println("Omzet:");
				System.out.println("Botsauto's: " + botsautos.omzet);
				System.out.println("Spin: " + spin.omzet);
				System.out.println("Spiegelpaleis: " + spiegelpaleis.omzet);
				System.out.println("Spookhuis: " + spookhuis.omzet);
				System.out.println("Hawaii: " + hawaii.omzet);
				System.out.println("Ladderklimmen: " + ladderklimmen.omzet);
				System.out.println("Totaal: " + kassa.omzetTotaal);
				break;
			case "k":
				System.out.println("Kaartverkoop:");
				System.out.println("Botsauto's: " + botsautos.kaartjes);
				System.out.println("Spin: " + spin.kaartjes);
				System.out.println("Spiegelpaleis: " + spiegelpaleis.kaartjes);
				System.out.println("Spookhuis: " + spookhuis.kaartjes);
				System.out.println("Hawaii: " + hawaii.kaartjes);
				System.out.println("Ladderklimmen: " + ladderklimmen.kaartjes);
				System.out.println("Totaal: " + kassa.kaartjesTotaal);
				break;
			case "m":
				System.out.println("De monteur wordt gebeld. Druk (s) voor spin of (h) voor hawaii.");
				String invoer3 = scanner.nextLine();
				switch (invoer3) {
				case "s":
					spin.opstellingsKeuring();
					break;
				case "h":
					hawaii.opstellingsKeuring();
					break;
				default:
					System.out.println("Verkeerde invoer.");
					break;
				} // end inside switch
				break;
			default:
				System.out.println("Verkeerde invoer.");
				break;
			} // end outside switch
			kassa.kaartjesTotaal = botsautos.kaartjes + spin.kaartjes + spiegelpaleis.kaartjes + spookhuis.kaartjes
					+ hawaii.kaartjes + ladderklimmen.kaartjes;
			kassa.omzetTotaal = botsautos.omzet + spin.omzet + spiegelpaleis.omzet + spookhuis.omzet + hawaii.omzet
					+ ladderklimmen.omzet;
		} // end while loop

	} // end psvm

} // end class Kermis

abstract class Attractie {
	String naam;
	double prijs;
	double omzet;
	int oppervlakte;
	int kaartjes;
} // end class Attractie

abstract class RisicoRijkeAttractie extends Attractie {
	int aantalDraaienSindsKeuring;
	int draaiLimiet;

	void teVaakGedraaid() throws OnderhoudException {
		if (aantalDraaienSindsKeuring == draaiLimiet) {
			throw new OnderhoudException();
		} // end if statement
	} // end method teVaakGedraaid

	void opstellingsKeuring() {
		System.out.println("De monteur is aan de slag. Hierna zal de attractie weer draaien.");
		aantalDraaienSindsKeuring = 0;
	} // end method opstellingsKeuring
} // end class RisicoRijkeAttractie

class Botsautos extends Attractie {
} // end class Botsautos

class Spin extends RisicoRijkeAttractie implements GokAttractie {
	public void kansSpelBelastingBetalen() {
		omzet *= 0.7;
	} // end method kansSpelBelastingBetalen
}// end class Attractie

class Spiegelpaleis extends Attractie {
}// end class Spiegelpaleis

class Spookhuis extends Attractie {
} // end class Spookhuis

class Hawaii extends RisicoRijkeAttractie {
} // end class Hawaii

class Ladderklimmen extends Attractie implements GokAttractie {
	public void kansSpelBelastingBetalen() {
		omzet *= 0.7;
	} // end method kansSpelBelastingBetalen
} // end class Ladderklimmen

class Admin {
	void welkom() {
		System.out.println("Welkom bij de kermis. Kies een getal tussen 1 en 6 voor een attractie.");
		System.out.println("Kies (1) voor botsauto's.");
		System.out.println("Kies (2) voor de spin.");
		System.out.println("Kies (3) voor het spiegelpaleis.");
		System.out.println("Kies (4) voor het spookhuis.");
		System.out.println("Kies (5) voor de hawaii.");
		System.out.println("Kies (6) voor het ladderklimmen.");
		System.out.println("Kies (o) om een overzicht van de omzet te zien.");
		System.out.println("Kies (k) om een overzicht van de kaartverkoop te zien.");
		System.out.println("Kies (m) om de monteur te bellen.");
		System.out.println("Kies (q) om de kermis te sluiten.");
	} // end method welkom

	Botsautos maakBotsautos() {
		Botsautos botsautos = new Botsautos();
		botsautos.prijs = 2.50;
		return botsautos;
	} // end method maakBotsautos

	Spin maakSpin() {
		Spin spin = new Spin();
		spin.prijs = 2.25;
		spin.draaiLimiet = 5;
		return spin;
	} // end method maakSpin

	Spiegelpaleis maakSpiegelpaleis() {
		Spiegelpaleis spiegelpaleis = new Spiegelpaleis();
		spiegelpaleis.prijs = 2.75;
		return spiegelpaleis;
	} // end method maakSpiegelpaleis

	Spookhuis maakSpookhuis() {
		Spookhuis spookhuis = new Spookhuis();
		spookhuis.prijs = 3.20;
		return spookhuis;
	} // end method maakSpookhuis

	Hawaii maakHawaii() {
		Hawaii hawaii = new Hawaii();
		hawaii.prijs = 2.90;
		hawaii.draaiLimiet = 10;
		return hawaii;
	} // end method maakHawaii

	Ladderklimmen maakLadderklimmen() {
		Ladderklimmen ladderklimmen = new Ladderklimmen();
		ladderklimmen.prijs = 5.00;
		return ladderklimmen;
	} // end method maakLadderKlimmen
} // end class admin

class Kassa {
	double omzetTotaal;
	int kaartjesTotaal;
	int aantalBIBezoeken;
} // end class Kassa

interface GokAttractie {
	void kansSpelBelastingBetalen();
} // end method kansSpelBelastingBetalen

class BelastingInspecteur {

	void checkenEnBetalen(Attractie a) {
		if (a instanceof GokAttractie) {
			((GokAttractie) a).kansSpelBelastingBetalen();
		} // end if statement
	} // end method checkenEnBetalen
} // end class BelastingInspecteur

class OnderhoudException extends Exception {
	OnderhoudException() {
		System.out.println("OnderhoudException");
	}
}