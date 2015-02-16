import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


/**
 * 
 * @author Nicholas A Stuff to do 
 * // armory (inventory) 
 * // static vars 
 * // overflow scanner 
 * // spells effect enemy intel speed power accurace and your accuracy 
 * // make lots of methods 
 * // put certian items under certian directories with the get absolute path!!!!!!!!!
 * // make 3 good luck files (thanks phil)
 * // statis efects (phily willy)(make it a varriable)
 *  // make turn variable 
 *  // remove 1,2,3 save file restrictions 
 *  // fix negative mana
 */

public class MainClass {

	static int gold;

	static Scanner in = new Scanner(System.in);

	public static void main(String[] args)throws IOException {

		boolean hurt = (true),validmove;

		byte save = 0, Lv = 0, howmany = 0, count = 0, SlistL,tile; 

		short hp = 0, mana = 0, xp = 0, power = 0, intel = 0, speed = 0, attack = 0, damage, nxp, acc,rand,Ilist;
		short Hhp, Hattack,Hpower, Hspeed, Hintel, Thp, hitChance, Tmana, Hxp, Hgold, HpP, ManaP, armourPiece = 0;
		short manaSeff, hpSeff, Sdamage, Spow, Sintel, Sspeed, crit = 0, Hacc, powR = 0, intelR = 0, speedR = 0;

		String name, filename, inventoryFile, ans, clas = "", convert, Hname,direction,enemyName=null;
		String Hfile = "", choice, spell, lvup, witch1, Snul, spellfile, bodypart;

		char any;

		byte defence[] = new byte[4];
		byte Hdefence[] = new byte[4];
		byte spawnrate [] = new byte [4];
		String hostiles [] = new String [4];

		BufferedReader savefile;
		BufferedReader enemy;
		BufferedReader inventory;
		BufferedReader listospells;
		BufferedReader spelleff;

		PrintWriter Inventory;

		while (true) {

			System.out.println("Select a save file (1,2,3)");
			convert = in.nextLine(); // try to display some info on save files

			try {
				save = Byte.parseByte(convert); // tries to convert to an int
				if (save == 1 || save == 2 || save == 3) // error trap
				{
					break;
				}
				System.out.println("Not a save file");

			} catch (NumberFormatException nfe) {// if not a number
				System.out.println("Not a number");
			}
		}// end of error trap

		filename = "Save" + save + ".txt";
		inventoryFile = "Save" + save + "i.txt"; // sets up all the files
		spellfile = "Save" + save + "s.txt";

		savefile = new BufferedReader(new FileReader("C:/Program Files/AtYourCommand/Source Files//H dragon.txt"));// just to give it a value 

		for (count=0 ;count < 2 ; count++){
			// dose this twice so that file will open

			try{
				savefile.close();
				savefile = new BufferedReader(new FileReader("C:/Program Files/AtYourCommand/Source Files/"+filename));
			}

			catch (FileNotFoundException fe){// makes new save files

				PrintWriter	Makesavefile = new PrintWriter("C:/Program Files/AtYourCommand/Source Files/"+filename, "UTF-8");
				Makesavefile.close();

				Makesavefile = new PrintWriter("C:/Program Files/AtYourCommand/Source Files/"+inventoryFile, "UTF-8");
				Makesavefile.close();

				Makesavefile = new PrintWriter("C:/Program Files/AtYourCommand/Source Files/"+spellfile, "UTF-8");
				Makesavefile.close();
			}// catch
		}// end of for loop

		convert = savefile.readLine();

		if (convert == null) {
			NewGame(filename, inventoryFile, spellfile);
			// new game method
		} // if new save file

		savefile.close(); // fixs a loading bug (how it reads name to check if
		// the file is null)
		savefile = new BufferedReader(new FileReader("C:/Program Files/AtYourCommand/Source Files/"+filename));

		convert = savefile.readLine ();
		tile = Byte.parseByte (convert);

		name = savefile.readLine();
		clas = savefile.readLine(); // -----------------------------------------------------------------------------------------------------------
		convert = savefile.readLine();
		hp = Short.parseShort(convert); // loads data

		convert = savefile.readLine();
		mana = Short.parseShort(convert);

		convert = savefile.readLine();
		power = Short.parseShort(convert);

		convert = savefile.readLine();
		intel = Short.parseShort(convert);

		convert = savefile.readLine();
		speed = Short.parseShort(convert);

		convert = savefile.readLine();
		acc = Short.parseShort(convert);

		convert = savefile.readLine();
		gold = Short.parseShort(convert);

		convert = savefile.readLine();
		xp = Short.parseShort(convert);

		convert = savefile.readLine();
		attack = Short.parseShort(convert);

		for (count = 0; count < 4; count++) {
			convert = savefile.readLine();

			defence[count] = (byte) Short.parseShort(convert); // gets
			// defensicive
			// values
			// from file

		}
		convert = savefile.readLine();
		Lv = Byte.parseByte(convert);

		convert = savefile.readLine();
		HpP = Short.parseShort(convert);
		convert = savefile.readLine();
		ManaP = Short.parseShort(convert);

		savefile.close();

		inventory = new BufferedReader(new FileReader("C:/Program Files/AtYourCommand/Source Files/"+inventoryFile));
		count = 0;
		Snul = inventory.readLine();
		while (Snul != null) {
			Snul = inventory.readLine(); // sees how long the inventory is
			count++;
		}
		Ilist = count;
		inventory.close();

		// loads inventory array 
		inventory = new BufferedReader(new FileReader("C:/Program Files/AtYourCommand/Source Files/"+inventoryFile));
		String inventoryA[] = new String[count];
		for(byte count2=0;count2<count;count2++){
			inventoryA[count2] = inventory.readLine();
		}
		inventory.close();

		listospells = new BufferedReader(new FileReader("C:/Program Files/AtYourCommand/Source Files/"+spellfile));
		count = 0;
		Snul = listospells.readLine();
		while (Snul != null) {
			Snul = listospells.readLine(); // sees how long the list of spells
			// is
			count++;
		}
		SlistL = count;
		listospells.close();


		while (true){// movement loop 

			while (true){// error trap 

				System.out.println("Up, down, left or right?(s to save and exit)");
				direction = in.nextLine();

				if (direction.equalsIgnoreCase ("S")){
					SaveGame (tile,filename, name, clas, hp, mana, power, intel, speed, acc, xp, attack, defence, Lv, HpP, ManaP);
				}

				validmove = Validmove(direction,tile);

				if (validmove==true){
					break;
				}	

				System.out.println ("Not a valid move!!");				

			}//error trap 

			if (direction.equalsIgnoreCase("up")){
				tile += 10;
			}

			if (direction.equalsIgnoreCase("down")){
				tile -= 10;
			}

			if (direction.equalsIgnoreCase("left")){
				tile -= 1;
			}

			if (direction.equalsIgnoreCase("right")){
				tile += 1;
			}

			Biome biome = new Biome(tile);

			System.out.println ("You are now in/on " + biome.name);

			if (biome.name.equalsIgnoreCase("Town")){
				System.out.println ("To go to the general store enter (G) to move out of town enter the direction you want to go");

				direction = in.nextLine ();

				if (direction.equalsIgnoreCase("G")){
					while (true) {
						System.out
						.println("Do you want to buy health poitons or mana poition?(H/M) or buy spells? (S) or exit store? (Q)");
						ans = in.nextLine();

						if (ans.equalsIgnoreCase("H")) {
							System.out
							.println("How many do you want? (50 gold each)");
							System.out.println("you currentely have " + gold
									+ " gold");
							System.out.println("You currentely have " + HpP
									+ " hp potions ");
							convert = in.nextLine();

							try {
								howmany = Byte.parseByte(convert);
							}

							catch (NumberFormatException nfe) {// if not a
								// number
								System.out.println("Not a number");
							}
						}

						if (gold - 50 * howmany < 0) {
							System.out.println("You don't have enough money");
						} else {
							gold = (short) (gold - 50 * howmany);
							HpP = (short) (HpP + howmany);

						}

						if (ans.equalsIgnoreCase("M")) {
							System.out
							.println("How many do you want? (50 gold each)");
							System.out.println("you currentely have " + gold
									+ "gold");
							System.out.println("You currentely have " + ManaP
									+ " mana potions ");
							convert = in.nextLine();

							try {
								howmany = Byte.parseByte(convert);
							}

							catch (NumberFormatException nfe) {// if not a
								// number
								System.out.println("Not a number");
							}

							if (gold - 50 * howmany < 0) {
								System.out.println("You don't have enough money");
							} else {
								gold = (short) (gold - 50 * howmany);
								ManaP = (short) (ManaP + howmany);
							}

						}
						if (ans.equalsIgnoreCase("Q")) {
							break;
						}

						if (ans.equalsIgnoreCase("S")) {
							SpellShop(spellfile);

							// this is here to try and fix the problem of spells not coming up 
							// right after you buy them
							listospells = new BufferedReader(new FileReader("C:/Program Files/AtYourCommand/Source Files/"+spellfile));
							count = 0;
							Snul = listospells.readLine();
							while (Snul != null) {
								Snul = listospells.readLine(); // sees how long the list of spells
								// is
								count++;
							}
							SlistL = count;
							listospells.close();
						}
					} // general store
				}
			}

			rand = (byte) ((int)(Math.random() * 100)+1);

			if (rand <= 66)	{
				rand = (byte) ((int) (Math.random() * 100) + 1); // makes a number between 1 and 100

				spawnrate = (biome.Spawnrates());
				hostiles = (biome.Hostiles());

				enemyName = null;

				for (byte countB = 0; countB <4; countB++){
					if (spawnrate[countB] >= rand){	
						enemyName = hostiles[countB];
						break;
					}
				}// gets spawnrate and checks with coresponding hostile

				try {					

					enemyName = ("H "+ enemyName +".txt");

					enemy = new BufferedReader(new FileReader("C:/Program Files/AtYourCommand/Source Files/"+enemyName));

					Hname = enemy.readLine();

					convert = enemy.readLine();
					Hhp = Short.parseShort(convert);

					convert = enemy.readLine();
					Hattack = Short.parseShort(convert);

					convert = enemy.readLine();
					Hpower = Short.parseShort(convert);

					convert = enemy.readLine();
					Hspeed = Short.parseShort(convert);

					convert = enemy.readLine();
					Hintel = Short.parseShort(convert);

					convert = enemy.readLine();
					Hacc = Short.parseShort(convert);

					for (count = 0; count < 4; count++) {
						convert = enemy.readLine();

						Hdefence[count] = (byte) Short.parseShort(convert);
						// defense
					}

					convert = enemy.readLine();
					Hxp = Short.parseShort(convert);

					convert = enemy.readLine();
					Hgold = Short.parseShort(convert);

					System.out.println("You will be going against a " + Hname);

					enemy.close();

					Thp = hp;
					Tmana = mana; // so that there will be something to show your
					// total stats and how much you have left

					try {
						Thread.sleep(1500);
					} catch (InterruptedException ex) {
						Thread.currentThread().interrupt();
					}
					System.out.flush();

					while (true) // combat engine
					{
						while (true) // error trap
						{
							System.out
							.println("Would you like to attack, use a spell, use a poiton or give up(A/S/P/G)?"); // items
							// once
							// inventory
							// is
							// done
							choice = in.nextLine();
							choice = (choice.substring(0));

							if (choice.equalsIgnoreCase("A")
									|| choice.equalsIgnoreCase("S")
									|| choice.equalsIgnoreCase("G")
									|| choice.equalsIgnoreCase("P")) {
								break;
							}

						} // error trap

						if (choice.equalsIgnoreCase("P")) {
							System.out.print("Mana or health poiton ? (M/H)");
							witch1 = in.nextLine();

							if (witch1.equalsIgnoreCase("H")) // health poition
							{
								if (HpP >= 1) {
									hp = (short) (hp + 50);
									HpP = (short) (HpP - 1);
								}
							}

							if (witch1.equalsIgnoreCase("M")) // Mana potion
							{
								if (ManaP - 1 >= 1) {
									mana = (short) (mana + 50);
									ManaP = (short) (ManaP - 1);
								}
							}
						}

						if (choice.equalsIgnoreCase("A")) {
							hitChance = (short) (acc - Hspeed);

							while (true) {
								System.out.println("Would you like to attack : ");
								System.out
								.println("Only enter first letter (ex enter H for head )");
								System.out.println("Head   " + (hitChance - 30));
								System.out.println("Torso  " + hitChance);
								System.out.println("Arms   " + (hitChance - 10));
								System.out.println("Legs   " + (hitChance - 10));
								bodypart = in.nextLine();

								if (bodypart.equalsIgnoreCase("H")
										|| bodypart.equalsIgnoreCase("T")
										|| bodypart.equalsIgnoreCase("A")
										|| bodypart.equalsIgnoreCase("L")) {
									break;
								}
							} // body part pick

							if (bodypart.equalsIgnoreCase("T")) {
								armourPiece = 0;
								crit = 5;
							}

							if (bodypart.equalsIgnoreCase("H")) {
								armourPiece = 1;
								hitChance = (short) (hitChance - 50);
								crit = 50;
							}

							if (bodypart.equalsIgnoreCase("A")) {
								armourPiece = 2;
								hitChance = (short) (hitChance - 20);
								crit = 20;
							}

							if (bodypart.equalsIgnoreCase("L")) {
								armourPiece = 3;
								hitChance = (short) (hitChance - 20);
								crit = 20;
							}
							damage = (short) (DamageDelt(armourPiece, hitChance, attack,
									power, crit));

							if (damage <= 0) {
								System.out.println("You missed!");
							} else {
								Hhp = (short) (Hhp - damage);
								System.out.println("Hit!");
								System.out.println("You did " + damage + " points of damage");
							}
						}

						if (choice.equalsIgnoreCase("S")) {
							while (true) {
								System.out
								.println("Which spell would you like to use?");
								listospells = new BufferedReader(new FileReader("C:/Program Files/AtYourCommand/Source Files/"+spellfile));
								for (count = 0; count < SlistL; count++) { // reads
									// list
									// of
									// spells
									Snul = listospells.readLine();
									System.out.println(Snul); // prints list of
									// spells
								}
								spell = in.nextLine();

								try {
									Snul = "M " + spell + ".txt";
									spelleff = new BufferedReader(new FileReader("C:/Program Files/AtYourCommand/Source Files/"+Snul));

									convert = spelleff.readLine();
									manaSeff = Short.parseShort(convert);

									convert = spelleff.readLine();
									hpSeff = Short.parseShort(convert);

									convert = spelleff.readLine();
									Sdamage = Short.parseShort(convert);

									convert = spelleff.readLine();
									Spow = Short.parseShort(convert);

									convert = spelleff.readLine();
									Sintel = Short.parseShort(convert);

									convert = spelleff.readLine();
									Sspeed = Short.parseShort(convert);

									break;
								}

								catch (FileNotFoundException fe) {
									System.out.println("Not a spell");
								}

							} // error trap

							spelleff.close();

							// applies effects
							mana = (short) (mana + manaSeff);
							hp = (short) (hp + hpSeff);
							Hhp = (short) (Hhp - Sdamage);
							power = (short) (power + Spow);
							intel = (short) (Sintel + intel);
							speed = (short) (speed + Sspeed);

							// displays effects only if not null
							if (manaSeff != 0) {
								System.out.println("Mana cost: " + manaSeff);
							}

							if (hpSeff != 0) {
								System.out.println("Hp effect: " + hpSeff);
							}

							if (Sdamage != 0) {
								System.out.println("Damage delt: " + Sdamage);
							}

							if (Spow != 0) {
								System.out.println("Power effect: " + Spow);
								powR = (short) (powR + Spow);// work here
							}

							if (Sintel != 0) {
								System.out.println("Intel effect: " + Sintel);
								intelR = (short) (intelR + Sintel);
							}

							if (Sspeed != 0) {
								System.out.println("Speed effect: " + Sspeed);
								speedR = (short) (speedR + Sspeed);
							}

						} // end of player turn if player picked spell

						if (choice.equalsIgnoreCase("G")) {
							System.out
							.println("sometimes giving up is the best thing to do");
							hurt = true;
							break;
						} // breaks if player surrenders

						if (Hhp <= 0) {
							System.out.println("You won !!");
							hurt = false;
							break;
						}

						// Enemy attack
						// engine**********************************************************
						// put in a body part randomizer

						rand = (short) ((int) (Math.random() * 4) + 1);

						hitChance = (short) (Hacc - speed);

						if (rand == 1) {
							armourPiece = 0;
							crit = 5;
							// torso
						}

						if (rand == 2) {
							armourPiece = 1;
							hitChance = (short) (hitChance - 50);
							crit = 50;
							// head
						}

						if (rand == 3) {
							armourPiece = 2;
							hitChance = (short) (hitChance - 20);
							crit = 20;
							// arms
						}

						if (rand == 4) {
							armourPiece = 3;
							hitChance = (short) (hitChance - 20);
							crit = 20;
							// legs
						}
						damage = (short) (DamageDelt(armourPiece, hitChance, Hattack,
								Hpower, crit));

						if (damage <= 0) {
							System.out.println("Hostile missed!");
						} else {
							hp = (short) (hp - damage);
							System.out.println(Hname + " hit you!");
							System.out.println("It did " + damage
									+ " points of damage");

						} // enemy attack

						System.out.println("Hp: " + hp + "/" + Thp);
						System.out.println("Mana: " + mana + "/" + Tmana);
						System.out.println("Hostile hp: " + Hhp);

						if (hp <= 0) {
							System.out.println("You lost");
							hurt = (true);
							break;
						}

					} // combat engine

					hp = Thp;
					mana = Tmana;
					power= (short) (power - powR);// work here
					intel = (short) (intel-intelR);
					speed=(short) (speed-speedR);
					
					powR=0;
					intelR=0;
					speedR=0;

					if (hurt == true) {
						System.out
						.println("You got lucky, you escaped with your life");
					}

					if (hurt == false) {
						gold = (short) (gold + Hgold);
						nxp = (short) (Lv * 10 + 100);
						xp = (short) (xp + Hxp);

						if (xp >= nxp) {
							System.out.println("Level up !!");
							xp = (short) (xp - nxp);
							Lv = (byte) (Lv + 1);

							while (true) {
								System.out
								.println("Do you want to increase your health or mana?");
								lvup = in.nextLine();
								if (lvup.equalsIgnoreCase("health")
										|| lvup.equalsIgnoreCase("mana")) {
									break;
								}
							} // error trap for stat upgrade pick

							if (lvup.equalsIgnoreCase("health")) {
								hp = (short) (hp + 20);
							}

							if (lvup.equalsIgnoreCase("mana")) {
								mana = (short) (mana + 20);
							}

							while (true) {
								System.out
								.println("Now do you want to increase your speed, inteligance or power?");
								lvup = in.nextLine();
								if (lvup.equalsIgnoreCase("speed")
										|| lvup.equalsIgnoreCase("inteligance")
										|| lvup.equalsIgnoreCase("power")) {
									break;
								}
							} // error trap for atribuite pick

							if (lvup.equalsIgnoreCase("speed")) {
								speed = (short) (speed + 2);
							}

							if (lvup.equalsIgnoreCase("inteligance")) {
								intel = (short) (intel + 2);
							}

							if (lvup.equalsIgnoreCase("power")) {
								power = (short) (power + 2);
							}
							acc = (short) (acc + 5);

						} // lv up

					} // if you win the fight

				} // if you tried to fight
				catch (FileNotFoundException fe){
					// so it just goes back to movement loop 
				}
			}// if the random chance of fighting came back true 

		}// movement loop 

	}// main method 

	// Spell shop
	public static void SpellShop(String spellFile)throws IOException {

		String snul,convert,buy;
		byte count=0,SlistL;
		BufferedReader spellshop;

		PrintWriter listospells;

		listospells = new PrintWriter (new FileWriter("C:/Program Files/AtYourCommand/Source Files/"+spellFile,true));
		spellshop = new BufferedReader(new FileReader("C:/Program Files/AtYourCommand/Source Files/Spell shop.txt"));

		snul = spellshop.readLine();
		while (snul != null) {

			snul = spellshop.readLine(); 
			// sees how long the shop list is
			count++;
		}
		SlistL = (byte) (count/2);
		spellshop.close();

		// Declares arays 
		int Cost[] = new int[SlistL];
		String spell[] = new String[SlistL];

		spellshop = new BufferedReader(new FileReader("C:/Program Files/AtYourCommand/Source Files/Spell shop.txt"));
		// re-opens spell shop txt file 

		for (count = 0; count < SlistL; count++){
			spell[count]= spellshop.readLine();// error here 
			// fills arays 
			convert = spellshop.readLine();
			Cost[count]= Short.parseShort(convert);
		}	

		System.out.println ("You have "+ gold+ " gold \n"+ "Spell/gold");

		// displays cost and spells
		for (count = 0; count < SlistL; count++){
			System.out.println (spell[count]+"/"+Cost[count]);
		}

		while (true){
			System.out.println("What spell do you want to buy?(Q) to exit");
			buy=in.nextLine();

			if (buy.equalsIgnoreCase("Q")){
				break;
			}

			for (count=0; count < SlistL; count++){
				if (spell[count].equalsIgnoreCase(buy)){

					if (gold - Cost[count] >=0){
						listospells.println(spell[count]);
						System.out.println(spell[count]+" was bought");
						break;
					}//if they can afford the spell

					else{
						System.out.println ("You dont have enough gold!");
					}

				}// if spell exists and they want to but it 

			}// goes through all spells in store

			System.out.println ("Spell dose not exist");

		}// error trap 

		listospells.close();
	}

	// saves game
	public static void SaveGame (byte tile,String filename, String name, String clas, short hp, short mana, short power, short intel,short speed, short acc, short xp, short attack, byte [] defence ,byte Lv, short HpP, short ManaP) throws IOException{
		// put this where you want to save :
		//SaveGame(filename, name, clas, hp, mana, power, intel, speed, acc, xp, attack, defence[], Lv, HpP, ManaP);

		PrintWriter save;
		save = new PrintWriter(new FileWriter("C:/Program Files/AtYourCommand/Source Files/"+filename)); // saves data
		save.println (tile);
		save.println(name);
		save.println(clas);
		save.println(hp);
		save.println(mana);
		save.println(power);
		save.println(intel);
		save.println(speed);
		save.println(acc);
		save.println(gold);
		save.println(xp);
		save.println(attack);
		save.println(defence[0]);
		save.println(defence[1]);
		save.println(defence[2]);
		save.println(defence[3]);
		save.println(Lv);

		save.println(HpP);
		save.println(ManaP);

		save.close(); // so everything is written in the file
		System.exit(0);
	}

	// attack damage method
	public static int DamageDelt(short armour, short hitChance, short attack,
			short power, short crit) {

		short damage = 0;

		short rand = (short) ((int) (Math.random() * 100) + 1); // makes a number between 1
		// and 100

		if (rand < hitChance) {
			damage = (short) (attack + power - armour);
			rand = (short) ((int) (Math.random() * 10) + 1);
			// creates +- 5 damage
			// thanks Brandon
			damage = (short) (damage + rand - 5);

			if (damage < 0) {
				damage = 0; // so you can't heal an enemy with an attack
			}

			rand = (short) ((int) (Math.random() * 100) + 1); // crit hit

			if (rand <= crit) {
				System.out.println("Critical hit!");
				damage = (short) (damage * 2);
			}
		}

		return damage;
	}

	// hostiles
	public static String[] Hostiles (String biome){
		String HostileName [] = new String [4];
		HostileName[0]= "noob";
		HostileName[1]="fighter";
		HostileName[2]="knight";
		HostileName[3]=	"zombie";
		// Make different hostiles for different environments

		return HostileName;
	}

	// finds biome
	public static String WhichBiome (byte tile){

		if (tile == 7){
			return ("Start");
		}

		if (tile == 24){
			return ("Death Chasm");
		}

		if (tile == 73){
			return ("Castle");
		}

		if (tile == 73){
			return ("Strange Temple");
		}

		if (tile == 47){
			return ("Town");
		}

		if (tile ==17 || tile == 27 || tile == 37||tile == 43 || tile == 44 ||tile == 45 || tile == 46|| tile == 53|| tile == 63){
			return ("Road");
		}

		if (tile ==82||tile ==83||tile ==84|| tile == 72 || tile ==74 || tile == 62 ||tile == 64){
			return ("mountian");
		}		

		for (int count = 12 ; count < 53; count += 10 ){
			if (tile == count){
				return ("West plains"); 
			}
		}

		// this next double for loop is alot of fun 
		// it checks for every number between 13-16, 23-26, and 33-36
		// it dosen't include the death chasm as if the user moved to the death chasm 
		// the method would have returned a value and exited

		for (int count2= 10; count2 < 31; count2 += 10){

			for (int count= 3 + count2; count < 8+count2; count ++ ){
				if (count == tile){
					return ("South forest");
				}
			}// south forest small for loop 

		}//south forest large for loop 


		// central plain 
		for (int count =54; count < 60; count ++ ){
			if (count == tile){
				return ("Central plains");
			}
		}// Central plain 

		// eastern plain 
		for (int count2 = 10; count2 < 41; count2 += 10){

			for (int count= 8 + count2; count < 10 + count2; count++  ){
				if (count == tile){
					return ("Eastern plain");
				}
			}

		}// eastern plain 

		// north forest 
		for (int count2 = 10; count2 < 31; count2 += 10){

			for (int count =55 + count2; count < 60 + count2; count++ ){
				if (count == tile){
					return ("North Forest");
				}
			}
		}
		// north forest 

		// beach only place left
		return ("Beach");

	}

	// valid move method
	public static boolean Validmove(String move, int tile){

		boolean checkL=true, checkR=true, checkU=true, checkD=true;

		for (int count = 1; count < 100; count+= 10){
			if(count == tile && move.equalsIgnoreCase("left")){
				checkL= false;
			}// if trying to go off left side of world
		}

		for (int count = 10; count < 101; count+= 10){
			if(count == tile && move.equalsIgnoreCase("right")){
				checkR= false;
			}// if trying to go off right side of world
		}

		for (int count = 91; count < 101; count++){
			if(count == tile && move.equalsIgnoreCase("up")){
				checkU= false;
			}// if trying to go off top of world
		}

		for (int count = 10; count > 0; count--){
			if(count == tile && move.equalsIgnoreCase("down")){
				checkD= false;
			}// if trying to go off bottom of world
		}

		if (checkL==true && checkR==true && checkU==true && checkD==true){
			return true;
		}
		else{
			return false;
		}

	}// valid move method

	// New game method
	public static void NewGame(String filename, String inventoryFile,

			String spellfile) throws IOException {

		BufferedReader weapon;
		BufferedReader armour;

		PrintWriter Spells;
		PrintWriter Inventory;
		PrintWriter nsave;

		String name, clas, ans, Ascii = "", convert, Wfile, Afile;

		char leeter = ' ', letter = ' ', any;

		short len, ascii, intel = 0, power = 0, mana = 0, speed = 0, hp = 0, attack, Epower, Espeed, Eintel, Emana, Ehp, acc,xp = 0;
		byte Lv = 0;

		byte defence[] = new byte[4];		

		System.out.flush();
		System.out
		.println("It apears that you have not started this save file");

		while (true) {
			System.out.print("Enter your name: ");
			name = in.nextLine();

			System.out.println("Conver to leetspeak?(Change some letters with numbers)(y/n)");
			ans = in.nextLine();
			if (ans.equalsIgnoreCase("y")) {
				len = (short) name.length();

				for (int leetcount = 0; leetcount < 7; leetcount++) {

					if (leetcount == 0) {
						letter = 'a';
						leeter = '4';
					}
					if (leetcount == 1) {
						letter = 'e';
						leeter = '3';
					}
					if (leetcount == 2) { // this double for loop is a bit
						// of programing engering that
						// im really pround of
						letter = 'g'; // the idea is that it goes through
						// the players name 7 times
						leeter = '9'; // each time checking for a certian
						// letter
					} // first time it checks for a and replaces it with 4
					if (leetcount == 3) { // then it goes through the entire
						// name again looking for e and
						// replacing it with a 3
						letter = 'i'; // ect...
						leeter = '1';
					}
					if (leetcount == 4) {
						letter = 'o';
						leeter = '0';
					}
					if (leetcount == 5) {
						letter = 's';
						leeter = '5';
					}
					if (leetcount == 6) {
						letter = 't';
						leeter = '7';
					}

					for (int count = 0; count < len; count++) // goes through
						// every letter
					{

						if (name.charAt(count) == letter) // checks if char
							// is equal to
							// letter
						{
							name = changeCharInPosition(count, leeter, name); // changes
							// letter
							// with
							// leeter
						}

					} // replaces letter with leeter
				}// leetcount

				System.out.println(name);

			}// if they want leeter name

			System.out.println("convert name so ASCII?(y/n)");
			ans = in.nextLine();
			if (ans.equalsIgnoreCase("y")) {
				len = (short) name.length();

				for (int count = 0; count < len; count++) // goes through every
					// letter
				{
					ascii = (byte) name.charAt(count); // converts char to
					// int
					Ascii = Ascii + " " + (String.valueOf(ascii));
				}
				name = Ascii;
				System.out.println("Your name: " + name);
			} // end of name ascii generator

			while (true) {
				System.out.flush();
				System.out.println("Enter the class you want:");
				System.out.println("Warrior");
				System.out.println("Mage");
				System.out.println("Theif");
				if (name.equalsIgnoreCase("Olivia")) // all for you hun <3
				{
					System.out.println("Fairy Warrior *special class*");
				}

				if (name.equalsIgnoreCase("Sebi")
						|| name.equalsIgnoreCase("Olivia")) {
					System.out.println("Alpha Tester *special class*");
				}
				// make your self a class
				// you are making the game after all

				clas = in.nextLine();

				if (clas.equalsIgnoreCase("Warrior")
						|| clas.equalsIgnoreCase("Mage")
						|| clas.equalsIgnoreCase("Theif")
						|| clas.equalsIgnoreCase("Fairy Warrior")
						&& name.equalsIgnoreCase("Olivia")) {
					System.out.flush();
					break;
				}

				if (name.equalsIgnoreCase("Sebi")
						&& clas.equalsIgnoreCase("Alpha Tester")
						|| name.equalsIgnoreCase("Olivia")
						&& clas.equalsIgnoreCase("Alpha Tester")) {
					System.out.flush();
					break;
				}

			} // class error trap

			if (clas.equalsIgnoreCase("Fairy Warrior")) {
				clas = "Fairy Warrior";
				hp = 160;
				mana = 160;
				power = 12;
				intel = 12;
				speed = 12;
				System.out.println("Fairy Warrior");
				System.out.println("HP = 150");
				System.out.println("Mana = 150");
				System.out.println("Power = 10");
				System.out.println("Inteligance = 10");
				System.out.println("Speed = 10");
			}

			if (clas.equalsIgnoreCase("Warrior")) {
				clas = "Warrior";
				hp = 150;
				mana = 50;
				power = 10;
				intel = 5;
				speed = 5;
				System.out.println("Warrior");
				System.out.println("HP = 150");
				System.out.println("Mana = 80");
				System.out.println("Power = 10");
				System.out.println("Inteligance = 5"); // asighns all
				// veriables and
				// shows user stats
				System.out.println("Speed = 5");
			}

			if (clas.equalsIgnoreCase("Mage")) {
				clas = "Mage";
				hp = 80;
				mana = 150;
				power = 5;
				intel = 10;
				speed = 5;
				System.out.println("Mage");
				System.out.println("HP = 80");
				System.out.println("Mana = 150");
				System.out.println("Power = 5");
				System.out.println("Inteligance = 10");
				System.out.println("Speed = 5");
			}

			if (clas.equalsIgnoreCase("Theif")) {
				clas = "Theif";
				hp = 100;
				mana = 100;
				power = 6;
				intel = 8;
				speed = 10;
				System.out.println("Theif");
				System.out.println("HP = 100");
				System.out.println("Mana = 100");
				System.out.println("Power = 6");
				System.out.println("Inteligance = 8");
				System.out.println("Speed = 10");
			}

			if (clas.equalsIgnoreCase("Alpha Tester")) {
				clas = "Alpha Tester";
				hp = 130;
				mana = 130;
				power = 9;
				intel = 9;
				speed = 9;
				System.out.println("Alpha Tester");
				System.out.println("HP = 130");
				System.out.println("Mana = 130");
				System.out.println("Power = 9");
				System.out.println("Inteligance = 9");
				System.out.println("Speed = 9");
			}

			System.out.println("Your name is " + name);
			System.out.println("And you want to be a " + clas
					+ "? (y/n)(Just the letter)"); // confermation prompt
			ans = in.nextLine();
			if ((ans.indexOf("y")) != -1) {
				break;
			}

		} // gets data from user for save file

		System.out.flush();

		System.out.println("Welcome to At Your Command!!");

		System.out
		.println("The game currelently runs as your character fighting against");
		System.out
		.println("randomely picked enimies in an arena style combate");
		System.out.println("(I'm hoping to generate a world at some point)");
		System.out.println(" ");

		System.out
		.println("As it currelently stands the fallowing things are useless:");
		System.out.println("inventory");
		System.out.println(" ");

		System.out
		.println("Right now Im just trying to make it so that you can start swinging");
		System.out.println("So you should probably get a sword");

		System.out
		.println("Press any key to continue to armory and get weapon and armor");
		any = in.next(".").charAt(0);
		System.out.flush();

		System.out.println("Welcome to the armory"); // find out how to do
		// inventory

		while (true) {
			System.out
			.println("What weapon would you like to get? (you only get one weapon for now but its free)");
			System.out.println("Name     Attack      Effect(s)");
			System.out.println("Sword    15          Power +1");
			System.out.println("Dagger   10          Speed +1");
			System.out.println("Staff    7           Inteligance +1");

			System.out.print("Which weapon do you want?: ");
			String Sany = in.nextLine ();
			convert = in.nextLine();

			if (convert.equalsIgnoreCase("Sword")
					|| convert.equalsIgnoreCase("Dagger")
					|| convert.equalsIgnoreCase("Staff")) {
				break;
			} else {
				System.out.println("Invalid input");
			}

		} // weapon pick error trap

		convert = convert.toLowerCase();

		Wfile = "W " + convert + ".txt";

		weapon = new BufferedReader(new FileReader("C:/Program Files/AtYourCommand/Source Files/"+Wfile));

		convert = weapon.readLine();
		attack = Short.parseShort(convert); // sets attack

		convert = weapon.readLine();
		Epower = Short.parseShort(convert);

		convert = weapon.readLine();
		Espeed = Short.parseShort(convert); // applies all the
		// effects of the
		// weapon

		convert = weapon.readLine();
		Eintel = Short.parseShort(convert);

		power = (short) (power + Epower);
		speed = (short) (speed + Espeed);// aplies all the effects to the character
		intel = (short) (intel + Eintel);

		weapon.close();

		System.out.flush();

		while (true) // gives armour
		{

			System.out
			.println("What armor would you like to get? (you only get one weapon for now but its free)");
			System.out.println("Name     total protection      Effect(s)");
			System.out.println("Iron     25                    HP +20");
			System.out
			.println("Leather  17                    HP +10 Mana +10");
			System.out.println("Robes    10                    Mana +20");

			System.out.print("Which armor do you want?: ");
			convert = in.nextLine();

			if (convert.equalsIgnoreCase("Iron")
					|| convert.equalsIgnoreCase("Leather")
					|| convert.equalsIgnoreCase("Robes")) {
				break;
			} else {
				System.out.println("Invalid input");
			}

		} // armor pick error trap

		convert = convert.toLowerCase();

		Afile = "P " + convert + ".txt"; // gets file name

		armour = new BufferedReader(new FileReader("C:/Program Files/AtYourCommand/Source Files/"+Afile));

		for (int count = 0; count < 4; count++) {
			convert = armour.readLine();

			defence[count] = Byte.parseByte(convert);
			// gets defensicive values from file
			// for order what value covers where see protective setup.txt
		}

		convert = armour.readLine();
		Emana = Short.parseShort(convert);

		convert = armour.readLine(); // applies hp and mana effects
		Ehp = Short.parseShort(convert);

		mana = (short) (mana + Emana); // adds hp and mana effects
		hp = (short) (hp + Ehp);

		armour.close();

		System.out.flush();

		System.out.println("All characters start with a healing spell");
		System.out
		.println("Mage and all special classes also get a fire spell");
		System.out.println("Press any key to continue");
		Spells = new PrintWriter(new FileWriter("C:/Program Files/AtYourCommand/Source Files/"+spellfile));
		Spells.println("Heal");
		if (clas.equalsIgnoreCase("Mage")
				|| clas.equalsIgnoreCase("Fairy Warrior")
				|| clas.equalsIgnoreCase("Alpha Tester")) // special
			// spells for
			// special class
		{
			Spells.println("Fire");
		}
		Spells.close();
		any = in.next(".").charAt(0);// < find out what this dose

		acc = 50;
		byte tile = 7;

		System.out.println("Saving...");		

		nsave = new PrintWriter(new FileWriter("C:/Program Files/AtYourCommand/Source Files/"+filename)); // saves data
		nsave.println(tile);
		nsave.println(name);
		nsave.println(clas);
		nsave.println(hp);
		nsave.println(mana);
		nsave.println(power);
		nsave.println(intel);
		nsave.println(speed);
		nsave.println(acc);
		nsave.println(gold);
		nsave.println(xp);
		nsave.println(attack);
		nsave.println(defence[0]);
		nsave.println(defence[1]);
		nsave.println(defence[2]);
		nsave.println(defence[3]);
		nsave.println(Lv);

		nsave.println(3);// hp potion
		nsave.println(2);// mant poition 

		nsave.close(); // so everything is written in the file

		Inventory = new PrintWriter(new FileWriter("C:/Program Files/AtYourCommand/Source Files/"+inventoryFile));

		Inventory.close();

		System.out.flush();
		System.out.println("game saved");
		
		String Sany = in.nextLine ();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}

		System.out.flush();
	}

	// change char in position method
	public static String changeCharInPosition(int position, char ch, String str) {

		char[] charArray = str.toCharArray();
		charArray[position] = ch;
		return new String(charArray);
	}

}//end of class
