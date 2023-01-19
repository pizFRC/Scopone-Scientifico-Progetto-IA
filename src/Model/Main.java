package Model;

import java.util.Scanner;

public class Main {
public static void main(String[] args) {
	
	
   System.out.println("Inserire il punteggio da raggiungere");
    Scanner in=new Scanner(System.in);
   int score= in.nextInt();
   if(score < 11 || score > 50)
	   score=11;
   
	
   System.out.println("Inserire il numero di giocatori umani");
    
   int numberHumanPlayer= in.nextInt();
   if(numberHumanPlayer<1 || numberHumanPlayer > 4) {
	   numberHumanPlayer=1;
	   System.out.println("UN SOLO GIOCATORE UMANO");
   }
   
   System.out.println("players umani inseriti:"+numberHumanPlayer);
   Game g=Game.getIstance();
   g.setNumHumanPlayer(numberHumanPlayer);
   g.initGame();
   
   g.setScoreTheshhold(score);
	while(!g.isGameFinished()) {
		System.out.println("\n///////////////////////////////////////////////////////////////////////////////////////");
		System.out.println("\t \t \tGAME STARTED -  COPONE SCIENTIFICO \t \t \t");

		System.out.println("///////////////////////////////////////////////////////////////////////////////////////");
		
	while(!g.isMatchFinished()) {
		g.playTurn();
	}
	
	//point passa situazione attuale e poi reset
	
	
	g.resetGame();
	
	
	}
	
	
}

}