package Model;

import java.util.ArrayList;

public class IAPlayer extends Player {
	public void play(ArrayList<Card> c) {
		Card card=this.getNextCard();
		
		if(!c.isEmpty()) {
			c.add(card);
			System.out.println("Player["+this.getID() + "] butto -> "+card +" prendo : "+c);
			System.out.println("\n------------------FINE TURNO PLAYER "+this.getID() +" ------------------------\n\n\n\n");

			if(this.getTeamNumber()==1) {
			
				
				Game.getIstance().addCardsTeamOne(c);
			
		}else
		{
			
			Game.getIstance().addCardsTeamTwo(c);
		}
		
		
		return;
		}
		System.out.println("Player["+this.getID() + "] butto -> "+card +" ma non prendo niente");
		try {
			  Thread.sleep(1000);
			} catch (InterruptedException e) {
			  Thread.currentThread().interrupt();
			}
		
		System.out.println("\n------------------FINE TURNO PLAYER "+this.getID() +" ------------------------\n\n\n\n");
		Game.getIstance().addCardToTable(card);
	

		
	  
	 
	}
	public IAPlayer() {
		super();
	}
	public IAPlayer(Integer id,Integer teamNumber) {
		super(id,teamNumber);
	}
	public IAPlayer(Integer id,ArrayList<Card>hand,Integer teamNumber) {
		super(id,hand,teamNumber);
	}
	@Override
	public ArrayList<Card> choseCard() {
		
		if(this.getHand().isEmpty()) {
			System.out.println(this.getID()+" carte finite");
			return null;

		
	}
		
		return CardChoser.getInstance().choseCardIA(this);
	
		  
		
	}
}
