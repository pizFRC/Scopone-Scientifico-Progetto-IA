package Model;

import java.util.ArrayList;

public abstract class Player {
	
	
	private Integer ID;
	private ArrayList<Card>hand;
	private Card nextCardToPlay ;
	private Integer teamNumber;
	public Player() {
		
	}
	public Player(Integer id,ArrayList<Card>hand,Integer teamNumber) {
		this.ID=id;
		this.hand=hand;
		this.teamNumber=teamNumber;
	}
	public Player(Integer id,Integer teamNumber) {
		this.ID=id;
		hand=new ArrayList<Card>();
		this.teamNumber=teamNumber;
	}
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public ArrayList<Card> getHand() {
		return hand;
	}
	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}
	public void addCard(Card c) {
		hand.add(c);
	}
	public void removeCard(Card c) {
		hand.remove(c);
	}
	public Card getNextCard() {
		Card tmp=nextCardToPlay;
		this.getHand().remove(nextCardToPlay);
		nextCardToPlay=null;
		return tmp;
		
	}
	public boolean fullHand() {
		return hand.size()>=10;
	}
	public void setNextCard(Card card) {
		nextCardToPlay = card;
	}
	public int getTeamNumber() {
		return teamNumber;
	}
	public void setTeamNumber(int teamNumber) {
		this.teamNumber = teamNumber;
	}
	public abstract void play(ArrayList<Card> c) ;
	
	public abstract ArrayList<Card> choseCard();
	public boolean haveChosenCard() {
		return nextCardToPlay !=null;
	}
	
	
	
}
