package Model;

import java.util.ArrayList;

public class Seed {
public static final String SPADE="spade";
public static final String DENARI="denari";
public static final String COPPE="coppe";
public static final String BASTONI="bastoni";
public static ArrayList<String> getSeeds() {
	
	 ArrayList<String> seeds=new ArrayList<String>();
	seeds.add(COPPE);
	seeds.add(DENARI);
	seeds.add(SPADE);
	seeds.add(BASTONI);
	
	
	
	return seeds;
	
}

}
