
public class Biome {

	String name;
	byte spawnrates[] = new byte [4];
	String HostileName [] = new String [4];

	public Biome(byte tile){
		
		// beach only place left
		this.name= ("Beach");
		
		if (tile ==82||tile ==83||tile ==84|| tile == 72 || tile ==74 || tile == 62 ||tile == 64){
			this.name= ("mountian");
		}		

		for (int count = 12 ; count < 53; count += 10 ){
			if (tile == count){
				this.name= ("West plains"); 
			}
		}

		// this next double for loop is alot of fun 
		// it checks for every number between 13-16, 23-26, and 33-36
		// it dosen't include the death chasm as if the user moved to the death chasm 
		// the method would have returned a value and exited

		for (int count2= 10; count2 < 31; count2 += 10){

			for (int count= 3 + count2; count < 8+count2; count ++ ){
				if (count == tile){
					this.name= ("South forest");
				}
			}// south forest small for loop 

		}//south forest large for loop 


		// central plain 
		for (int count =54; count < 60; count ++ ){
			if (count == tile){
				this.name= ("Central plains");
			}
		}// Central plain 

		// eastern plain 
		for (int count2 = 10; count2 < 41; count2 += 10){

			for (int count= 8 + count2; count < 10 + count2; count++  ){
				if (count == tile){
					this.name= ("Eastern plain");
				}
			}

		}// eastern plain 

		// north forest 
		for (int count2 = 10; count2 < 31; count2 += 10){

			for (int count =55 + count2; count < 60 + count2; count++ ){
				if (count == tile){
					this.name= ("North Forest");
				}
			}
		}
		// north forest 

		if (tile == 7){
			this.name= ("Start");
		}

		if (tile == 24){
			this.name= ("Death Chasm");
		}

		if (tile == 73){
			this.name= ("Castle");
		}

		if (tile == 78){
			this.name= ("Strange Temple");
		}

		if (tile == 47){
			this.name= ("Town");
		}
		
		if (tile ==17 || tile == 27 || tile == 37||tile == 43 || tile == 44 ||tile == 45 || tile == 46|| tile == 53|| tile == 63){
			this.name= ("Road");
		}
	}

	public byte[] Spawnrates (){
		if (this.name.equalsIgnoreCase("road")||this.name.equalsIgnoreCase("Start")|| this.name.equalsIgnoreCase("Town")){
			spawnrates [0] = 0;
			spawnrates [1] = 0; 
			spawnrates [2]= 0;
			spawnrates [3]= 0; 
		}// places where hostiles can not spawn

		else{
			spawnrates [0] = 70;
			spawnrates [1] = 85; // 10% as will look for upper one first
			spawnrates [2]= 95;
			spawnrates [3]= 100; 
			// make different spawnrates for different biomes 
		}
		return spawnrates;
	}

	public String[] Hostiles (){
		String HostileName [] = new String [4];
		
		if (this.name.equalsIgnoreCase("Beach"))
		{
			HostileName[0]= "fish";
			HostileName[1]= "giant crab";
			HostileName[2]= "shark";
			HostileName[3]=	"whale";
		}
		
		if (this.name.equalsIgnoreCase("mountian"))
		{
			HostileName[0]= "bear";
			HostileName[1]="dragon";
			HostileName[2]="troll";
			HostileName[3]=	"internet troll";
		}
		
		if (this.name.equalsIgnoreCase("West plains"))
		{
			HostileName[0]= "buffalo";
			HostileName[1]= "deer";
			HostileName[2]= "knight";
			HostileName[3]=	"giant";
		}
		
		if (this.name.equalsIgnoreCase("South forest"))
		{
			HostileName[0]= "noob";
			HostileName[1]="goblin";
			HostileName[2]="goblin";
			HostileName[3]=	"fighter";
		}
		
		if (this.name.equalsIgnoreCase("Central plains"))
		{
			HostileName[0]= "buffalo";
			HostileName[1]= "deer";
			HostileName[2]= "knight";
			HostileName[3]=	"giant";
		}
		
		if (this.name.equalsIgnoreCase("Eastern plain"))
		{
			HostileName[0]= "buffalo";
			HostileName[1]= "deer";
			HostileName[2]= "knight";
			HostileName[3]=	"giant";
		}
		
		if (this.name.equalsIgnoreCase("North Forest"))
		{
			HostileName[0]= "deer";
			HostileName[1]= "zombie";
			HostileName[2]= "skeleton";
			HostileName[3]=	"snake";
		}
		
		if (this.name.equalsIgnoreCase("Death Chasm"))
		{
			HostileName[0]= "zombie";
			HostileName[1]="ghost";
			HostileName[2]="undead rider";
			HostileName[3]=	"destroyer of worlds";
		}
		
		if (this.name.equalsIgnoreCase("Castle"))
		{
			HostileName[0]= "boss";
			HostileName[1]= "boss";
			HostileName[2]= "boss";
			HostileName[3]=	"lama";
		}
		
		if (this.name.equalsIgnoreCase("Strange Temple"))
		{
			HostileName[0]= "zebi";
			HostileName[1]= "4chan";
			HostileName[2]= "op";
			HostileName[3]=	"bin";
		}		

		// Make different hostiles for different plains

		return HostileName;
	}
}