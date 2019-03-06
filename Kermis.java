import java.util.Scanner;

public class Kermis {

	public static void main(String[] args) {
		Admin admin = new Admin();
		Kassa kassa = new Kassa();
		boolean kermisOpen = true;
		Scanner scanner = new Scanner(System.in);
		//make the park
		Botsautos botsautos = admin.maakBotsautos();
		Spin spin = admin.maakSpin();
		Spiegelpaleis spiegelpaleis = admin.maakSpiegelpaleis();
		Spookhuis spookhuis = admin.maakSpookhuis();
		Hawaii hawaii = admin.maakHawaii();
		Ladderklimmen ladderklimmen = admin.maakLadderklimmen();
		//Open the park
		admin.welkom();
		while(kermisOpen == true) {
			String invoer = scanner.nextLine();
			Attractie.draaien(invoer);
		} //end while loop

	} //end psvm

} //end class Kermis

abstract class Attractie{
	String naam;
	double prijs;
	int oppervlakte;
	static void draaien(String a) {
		switch(a) {
		case "1":
			System.out.println("De botsauto's draait.");
			break;
		case "2":
			System.out.println("De spin draait.");
			break;
		case "3":
			System.out.println("Het spiegelpaleis draait.");
			break;
		case "4":
			System.out.println("Het spookhuis draait.");
			break;
		case "5":
			System.out.println("De hawaii draait.");
			break;
		case "6":
			System.out.println("Het ladderklimmen draait.");
			break;
		case "q":
			System.out.println("De kermis is gesloten");
			System.exit(0);
			break;
		default:
			System.out.println("Verkeerde invoer. Kies een getal tussen 1 en 6.");
			break;
		} //end switch
	} //end method draaien
} //end class Attractie

class Botsautos extends Attractie{
} // end class Botsautos
class Spin extends Attractie{
}// end class Attractie
class Spiegelpaleis extends Attractie{
}// end class Spiegelpaleis
class Spookhuis extends Attractie{
} //end class Spookhuis
class Hawaii extends Attractie{
} //end class Hawaii
class Ladderklimmen extends Attractie{
} //end class Ladderklimmen
class Admin{
	void welkom() {
		System.out.println("Welkom bij de kermis. Kies een getal tussen 1 en 6 voor een attractie.");
		System.out.println("Kies (1) voor botsauto's.");
		System.out.println("Kies (2) voor de spin.");
		System.out.println("Kies (3) voor het spiegelpaleis.");
		System.out.println("Kies (4) voor het spookhuis.");
		System.out.println("Kies (5) voor de hawaii.");
		System.out.println("Kies (6) voor het ladderklimmen.");
		System.out.println("Kies (q) om de kermis te sluiten.");
	} //end method welkom
	Botsautos maakBotsautos() {
		Botsautos botsautos = new Botsautos();
		botsautos.prijs = 2.50;
		return botsautos;
	} //end method maakBotsautos
	Spin maakSpin() {
		Spin spin = new Spin();
		spin.prijs = 2.25;
		return spin;
	} //end method maakSpin
	Spiegelpaleis maakSpiegelpaleis() {
		Spiegelpaleis spiegelpaleis = new Spiegelpaleis();
		spiegelpaleis.prijs = 2.75;
		return spiegelpaleis;
	} //end method maakSpiegelpaleis
	Spookhuis maakSpookhuis() {
		Spookhuis spookhuis = new Spookhuis();
		spookhuis.prijs = 3.20;
		return spookhuis;
	} //end method maakSpookhuis
	Hawaii maakHawaii() {
		Hawaii hawaii = new Hawaii();
		hawaii.prijs = 2.90;
		return hawaii;
	} //end method maakHawaii
	Ladderklimmen maakLadderklimmen() {
		Ladderklimmen ladderklimmen = new Ladderklimmen();
		ladderklimmen.prijs = 5.00;
		return ladderklimmen;
	} //end method maakLadderKlimmen
} //end class admin

class Kassa{
	double omzet;
} //end class Kassa