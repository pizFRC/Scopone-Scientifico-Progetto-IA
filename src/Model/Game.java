package Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

public class Game {
	
	private static Game game=null;
	private ArrayList<Player> players;
	private Player currentPlayer;
	private Player lastTaken;
	private ArrayList<Card> takenHandsTeamOne;
	private ArrayList<Card> takenHandsTeamTwo;
	private ArrayList<Card> tableCards;
	private String ultimaPresa="";
	private int humanPlayer=1;
	private PointsCounter pc;

	
	private Game() {
		pc=new PointsCounter(11);
		players=new ArrayList<Player>();
		takenHandsTeamOne=new ArrayList<Card> ();
		takenHandsTeamTwo=new ArrayList<Card> ();
		tableCards=new ArrayList<Card> ();
		
		
		
	}
	public void setScoreTheshhold(int s) {
		pc.setThesholdPoint(s);
	}
	public void addCardsTeamOne(ArrayList<Card>c) {
		this.takenHandsTeamOne.addAll(c);
		this.ultimaPresa="Team1:"+c.toString();
	}
public void addCardsTeamTwo(ArrayList<Card>c) {
	this.takenHandsTeamTwo.addAll(c);
	this.ultimaPresa="Team2:"+c.toString();
	}
	 public static Game getIstance() {
		  if(game==null)
			  game=new Game();
		  return game;
	  }
	
	public void setNextPlayer() {
	
		int indexOfNextCurrentPlayer=(players.indexOf(currentPlayer)+1)%4;
		currentPlayer.setNextCard(null);
		this.currentPlayer=players.get(indexOfNextCurrentPlayer);
	}
	
	
	
	public static final String ANSI_RESET = "\u001B[0m";
    // Declaring the background color
    public static final String ANSI_RED_BACKGROUND
        = "\u001B[41m";
  
   
     
     
	public void playTurn() {
		//view.stampaTavolo();
		    System.out.println("\n------------------TURNO Player "+currentPlayer.getID()+  " team:"+ currentPlayer.getTeamNumber()+  " --------------------------");
		    System.out.print('|');
		   System.out.print("Tavolo: "+ tableCards.toString());
		   	
                   
		  //view.stampaUltimapresa
                
		System.out.println("\nULTIMA PRESA:"+ultimaPresa);
		try {
			  Thread.sleep(2000);
			} catch (InterruptedException e) {
			  Thread.currentThread().interrupt();
			}

	ArrayList<Card>cards=currentPlayer.choseCard();
		
	if (cards == null) {
		setNextPlayer();
	}
	
		if(!currentPlayer.haveChosenCard()) {
			//view.stampa();
			System.out.println("THE PLAYER "+currentPlayer.getID()+"has to CHOSE THE Card");
			
			return;
		}
	
		
		currentPlayer.play(cards);
	
		
		setNextPlayer();
	}
	
	public ArrayList<Card> getCardFromTableForCheck() {
		return tableCards;
		
	}
	
	public ArrayList<Card> getCardFromTable(ArrayList<Card> chosenCards) {
		ArrayList<Card> tmp=new ArrayList<Card> ();
		if(chosenCards.isEmpty())
			return 	tmp;
		
		for(Card c:chosenCards) {
			if(tableCards.contains(c)) {
				int indexOfCard=tableCards.indexOf(c);
				tmp.add(tableCards.remove(indexOfCard));
			}
		}
		lastTaken=currentPlayer;
		return tmp;

	}
	
	public void addCardToTable(Card card) {
		
		tableCards.add(card);
	}
	public boolean isGameFinished() {
		
		return pc.isGameFinished();
	}
	public boolean isMatchFinished() {
		int counter=0;
		for(Player p:players) {
			if(p.getHand().size()==0) {
				counter+=1;
			}
		}
		boolean finished=counter>3;
		if(finished && !tableCards.isEmpty()) {
			
			if(lastTaken.getTeamNumber() == 1) {
				takenHandsTeamOne.addAll(tableCards);
			}else {
				takenHandsTeamTwo.addAll(tableCards);
			}
			tableCards.clear();
		}
		return finished;
	}
	
	private void initHands() {
		ArrayList<Card>deck=new ArrayList<Card>();
		//1)assegno le carte a ogni giocatore
		for(String s:Seed.getSeeds()) {
			
			for(int j=1;j<11;j++) {
				Card c=new Card(s,j);
				deck.add(c);
			}
		}
		Collections.shuffle(deck);
		
		
		System.out.println(deck.size());
		
		//2)aggiungo le carte al tavolo
		//3)set current player
	    int index=0;
		for(Card c:deck) {
			if(players.get(index).fullHand()) {
				index++;
			}
			players.get(index).addCard(c);
		}
	}
	
	
	public void resetGame() {
	
		pc.countPoint(takenHandsTeamOne, takenHandsTeamTwo);
		pc.print();
		takenHandsTeamOne.clear();
		takenHandsTeamTwo.clear();
		tableCards.clear();
		ultimaPresa="";
		Random r=new Random();
		if(!players.isEmpty()) {
		currentPlayer=players.get(r.nextInt(4));
		lastTaken=players.get(r.nextInt(4));
		
		}
		for(Player p :players) {
			if(!p.getHand().isEmpty())
				p.getHand().clear();
		}
		initHands();
		pc.setScopeTeamOne(0);
		pc.setScopeTeamTwo(0);
	}
	
	public void initGame() {
		if(!players.isEmpty())
			players.clear();
	
		int teamNum=1;
		int numPlayerForTeamOne=1;
		System.out.println("human:"+humanPlayer);
	for(int i=0;i<this.humanPlayer;i++) {
		
		
		Player p1=new HumanPlayer(i,teamNum);
		
        System.out.println("GIOCATORE UMANO PLAYER NUM:"+p1.getID()+" team num: "+ p1.getTeamNumber());
		players.add(p1);
		numPlayerForTeamOne++;
		if(numPlayerForTeamOne>2)
			teamNum=2;
	
		
	}
	System.out.println("player size:"+players.size());
	for(int j=players.size();j<4;j++) {
		Player p2=new IAPlayer(j,teamNum);
		players.add(p2);
		System.out.println("GIOCATORE IA PLAYER NUM:"+p2.getID()+" team num: "+ p2.getTeamNumber());
		
	}
	
	for(Player p : players)
		System.out.println("id:"+p.getID()+ " team :"+p.getTeamNumber());
	
	
	   Collections.swap(players, 1, 2);
	   for(Player p : players)
		System.out.println("id:"+p.getID()+ " team :"+p.getTeamNumber());

			initHands();
		
		
		
	 
		this.lastTaken=players.get(0);
		currentPlayer=players.get(0);
		
		
		
	}
	public Player getLastTaken() {
		return lastTaken;
	}
	public void setLastTaken(Player lastTaken) {
		this.lastTaken = lastTaken;
	}
	public void addScopa(int teamNumber) {
		switch (teamNumber) {
		case 1:
			pc.setScopeTeamOne(pc.getScopeTeamOne()+1);
			break;
		case 2:
			pc.setScopeTeamTwo(pc.getScopeTeamTwo()+1);
			break;

		default:
			break;
		}
		
	}

	

	public ArrayList<Card> getTakenHandsTeamOne() {
		return takenHandsTeamOne;
	}
	public ArrayList<Card> getTakenHandsTeamTwo() {
		return takenHandsTeamTwo;
	}
	public void setNumHumanPlayer(int numberHumanPlayer) {
		this.humanPlayer=numberHumanPlayer;
		
	}
	
	
	
}
