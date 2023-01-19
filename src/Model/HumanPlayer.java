package Model;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HumanPlayer extends Player {

	public void play(ArrayList<Card> c) {
		Card card=this.getNextCard();
	
		if(!c.isEmpty()) {
			c.add(card);
			System.out.println("Player["+this.getID() + "] butto -> "+card +" prendo : "+c);
			if(this.getTeamNumber()==1) {
			
				
				Game.getIstance().addCardsTeamOne(c);
			
		}else
		{
			
			Game.getIstance().addCardsTeamTwo(c);
		}
		
		System.out.println("\n------------------FINE TURNO PLAYER "+this.getID() +" ------------------------\n\n\n\n");
		return;
		}
		
		System.out.println("Player["+this.getID() + "] butto -> "+card +" ma non prendo niente");
		System.out.println("\n------------------FINE TURNO PLAYER "+this.getID() +" ------------------------\n\n\n\n");

		Game.getIstance().addCardToTable(card);
	

		
	}
	public HumanPlayer() {
		
	}
	public HumanPlayer(Integer id,ArrayList<Card>hand,Integer teamNumber) {
		super(id,hand,teamNumber);
	}
	public HumanPlayer(Integer id,Integer teamNumber) {
		super(id,teamNumber);
	}
	@Override
	public ArrayList<Card> choseCard() {
	
		if(this.getHand().isEmpty()) {
		System.out.println(this.getID()+" carte finite");
			return null;
		
			
			
		}
		Card c=new Card("",0);
		while(!this.getHand().contains(c)) {
		Scanner in=new Scanner(System.in);
		System.out.println("\n\t \t \t \t TOCCA A TE");
		System.out.println("--------------------------Scegli una carta--------------------------\n"+this.getHand());
		String cardString=in.nextLine();
	
	    while(!testCardString(cardString) ) {//fino a quando la carta in formato stringa non è stata scelta correttamente
	    	System.out.println("INSERISCI LA CARTA");
	    	cardString=in.nextLine();
	    }
	 
	    	if(cardString.startsWith(" ")) {
	    		
	    		cardString=cardString.trim();
	    	}
	    	
	    String[] tmp=cardString.split("\s");
	 
	    
	    c =new Card(tmp[1].toLowerCase(),Integer.parseInt(tmp[0]));
	
		}
		try {
			  Thread.sleep(1000);
			} catch (InterruptedException e) {
			  Thread.currentThread().interrupt();
			}
		

	    return CardChoser.getInstance().choseCardHuman(this, c);
	     
	   
		
	}
	
	private boolean testCardString(String card) {
		Pattern pattern = Pattern.compile("([1-9]{1}|10)\s(coppe|denari|bastoni|spade)", Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(card);
	    boolean matchFound = matcher.find();
	
	    return matchFound;
	}
}
