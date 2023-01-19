package Model;

import java.util.ArrayList;
import java.util.Collections;

import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.base.OptionDescriptor;
import it.unical.mat.embasp.base.Output;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.ASPMapper;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import it.unical.mat.embasp.languages.asp.AnswerSets;
import it.unical.mat.embasp.languages.asp.SymbolicConstant;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.specializations.dlv.desktop.DLVDesktopService;
import it.unical.mat.embasp.specializations.dlv2.desktop.DLV2DesktopService;


public class CardChoser {
public static CardChoser cc;

private static String prendiCarte="encodings/PrendiCorretto.dl";
private static String buttaCartaSenzaPrendere="encodings/ButtaCartaNessunaPossibilePresa.dl";

private static Handler handler;
private static Handler handler2;
private InputProgram  standardProgram;
private InputProgram buttaSenzaPrendereProgram;
InputProgram facts;
	private CardChoser() {
		init();
	}
	
	public static CardChoser getInstance() {
		if(cc==null) {
			cc=new CardChoser();
		    
		}
		return cc;
	}
	
	
	private void init() {
	
		//Creazione dell'oggetto handler che si occuper� di gestire l'invocazione 
				//del sistema ASP da utilizzare. Dal momento che in questa demo si fa uso 
				//di DLV si noti che viene utilizzato un DLVDesktopService.
				
				//Se si esegue la demo su Windows 64bit decommentare una delle seguenti istruzioni:
				//Per usare DLV
				//handler = new DesktopHandler(new DLVDesktopService("lib/dlv.mingw.exe"));
				//Per usare DLV2
				//handler = new DesktopHandler(new DLV2DesktopService("lib/dlv2.exe"));	
				
				//Se si esegue la demo su Linux 64bit decommentare una delle seguenti istruzioni:
				//Per usare DLV
				//handler = new DesktopHandler(new DLVDesktopService("lib/dlv"));
				//Per usare DLV2
	
				handler = new DesktopHandler(new DLV2DesktopService("lib/dlv2"));	
				handler2 = new DesktopHandler(new DLV2DesktopService("lib/dlv2"));	
				//Se si esegue la demo su MacOS 64bit decommentare una delle seguenti istruzioni:
				//Per usare DLV
				//handler = new DesktopHandler(new DLVDesktopService("lib/dlv.i386-apple-darwin.bin"));
				//Per usare DLV2
				//handler = new DesktopHandler(new DLV2DesktopService("lib/dlv2-macOS-64bit.mac_5"));	
						
				//In alternativa, eseguire i seguenti step e decommentare la seguente istruzione:
				// - aggiungere nella cartella lib l'eseguibile di DLV/DLV2 appropriato in base al proprio sistema:
				// - sostituire a "nome_exe" il nome dell'eseguibile nella seguente istruzione 
				// - se si fa uso di DLhandlerV nella seguente istruzione usare DLVDesktopService
				// - se si fa uso di DLV2 nella seguente istruzione usare DLVDesktopService
				//handler = new DesktopHandler(new DLV/DLV2DesktopService("lib/nome_exe"));	
				
				//Se si fa uso di DLV2 e si vogliono ottenere tutti gli answerset, 
				//decommentare le seguenti linee che aggiungono l'opzione -n 0
				OptionDescriptor option=new OptionDescriptor("-n 0");
				handler.addOption(option);
				handler2.addOption(option);
				//Specifichiamo encoding (ovvero il programma logico) e i fatti tramite file.
				standardProgram = new ASPInputProgram();
				buttaSenzaPrendereProgram=new ASPInputProgram();
				facts=new InputProgram();
				//program.addFilesPath(instanceResource);
				
				//In alternativa, possiamo fornire encoding e i fatti tramite 
				//le stringhe encoding e instance dichiarate e inizializzate sopra.
				//program.addProgram(encoding);
				//program.addProgram(instance);
				
				//Specifichiamo che il nodo 1 deve essere colorato in rosso.
				//Si noti che la classe Col viene prima registrata all'ASPMapper.
				try {
					ASPMapper.getInstance().registerClass(Card.class);
					ASPMapper.getInstance().registerClass(CartaTerra.class);
					ASPMapper.getInstance().registerClass(CartaDaPrendere.class);
					ASPMapper.getInstance().registerClass(CartaMano.class);
					ASPMapper.getInstance().registerClass(CartaScelta.class);
					ASPMapper.getInstance().registerClass(Scopa.class);
					//program.addObjectInput(new Col(1,new SymbolicConstant("red")));
				} catch (Exception e) {
					e.printStackTrace();
				}
				//Aggiungiamo all'handler il programma logico comprensivo di encoding e fatti.
		
				standardProgram.addFilesPath(prendiCarte);
				buttaSenzaPrendereProgram.addFilesPath(buttaCartaSenzaPrendere);
				
				
				handler.addProgram(standardProgram);
		        handler.addProgram(facts);
				handler2.addProgram(buttaSenzaPrendereProgram);
				handler2.addProgram(facts);
				
	}
	
	public ArrayList<Card> choseCardHuman(Player p,Card chosen) {
		facts.clearAll();
		 ArrayList<Card> cards=new  ArrayList<Card> ();	
		Card chosenCard=chosen;
		
		
		
		try {
			CartaMano cm=new CartaMano(chosen.getSeed(),chosen.getValue());
			facts.addProgram(cm.toString()+".");
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		for(Card c:Game.getIstance().getCardFromTableForCheck()) {
			try {
				CartaTerra ct=new CartaTerra(c.getSeed(),c.getValue());
				facts.addProgram(ct.toString()+".");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		Output o =  handler.startSync();
		
		//Stampiamo su standard output ogni answerset ottenuto.
		AnswerSets answers = (AnswerSets) o;
		
		if(!answers.getAnswersets().isEmpty()) {
		
				
				
		AnswerSet a=answers.getAnswersets().get(answers.getAnswersets().size()-1);
				
						

				
				try {

				
					for(Object obj:a.getAtoms()){
					
							//System.out.print(obj);
							if(obj instanceof CartaScelta)  {
								CartaScelta c = (CartaScelta) obj;
								
								
							//	cards.add(new Card(c.getSeed(),c.getValue()));
								
								//System.out.println("È il mio turno: "+p.getID()+" gioco:"+c.toString());
								//cards.add(chosenCard);
								System.out.println("carta settata");
							
							}
							/*if(obj instanceof CartaScelta)  {
								CartaScelta c = (CartaScelta) obj;
								///AGGIUNGERE SCOPA
								
							//	cards.add(new Card(c.getSeed(),c.getValue()));
								p.setNextCard(new Card(c.getSeed(),c.getValue()));
								cards.add(p.getNextCard());
							}*/
							if(obj instanceof CartaDaPrendere)  {
								CartaDaPrendere c = (CartaDaPrendere) obj;
							   
								cards.add(new Card(c.getSeed(),c.getValue()));
								
							}
							if(obj instanceof Scopa)  {
								Scopa c = (Scopa) obj;
							   
								Game.getIstance().addScopa(p.getTeamNumber());
								System.out.println("\n-----------------------");
								System.out.println("SCOPA:"+c.toString());
								System.out.println("-----------------------");
								
								//ragionare se trovata la scopa devo averla anche nell array cards
							}
						
					}
					System.out.println();
				} catch (Exception e) {
					e.printStackTrace();
				} 			
			
			
		}
			
			
		p.setNextCard(chosen);
	
		return Game.getIstance().getCardFromTable(cards);
		
	}
	
	public ArrayList<Card> choseCardIA(Player p) {
		 ArrayList<Card> cards=new  ArrayList<Card> ();	
		facts.clearAll();
		for(Card c:p.getHand()) {
			try {
				CartaMano cm=new CartaMano(c.getSeed(),c.getValue());
				facts.addProgram(cm.toString()+".");
				
				//System.out.println(facts.getPrograms());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for(Card c:Game.getIstance().getCardFromTableForCheck()) {
			try {
				CartaTerra ct=new CartaTerra(c.getSeed(),c.getValue());
				facts.addProgram(ct.toString()+".");
				//System.out.println(facts.getPrograms());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	
		Output o =  handler.startSync();
		
		//Stampiamo su standard output ogni answerset ottenuto.
		AnswerSets answers = (AnswerSets) o;
		//TUTTE LE POSSIBILI PRESE
		if(!answers.getAnswersets().isEmpty()) {
			
				
			
		
			AnswerSet a=answers.getAnswersets().get(answers.getAnswersets().size()-1);
				
						

				
				try {

				
					for(Object obj:a.getAtoms()){
					
							
							if(obj instanceof CartaScelta)  {
								CartaScelta c = (CartaScelta) obj;
								
								
							//	cards.add(new Card(c.getSeed(),c.getValue()));
								p.setNextCard(new Card(c.getSeed(),c.getValue()));
								//System.out.println("È il mio turno: "+p.getID()+" gioco:"+c.toString());
								//cards.add(p.getNextCard());
							}
							/*if(obj instanceof CartaScelta)  {
								CartaScelta c = (CartaScelta) obj;
								///AGGIUNGERE SCOPA
								
							//	cards.add(new Card(c.getSeed(),c.getValue()));
								p.setNextCard(new Card(c.getSeed(),c.getValue()));
								cards.add(p.getNextCard());
							}*/
							if(obj instanceof CartaDaPrendere)  {
								CartaDaPrendere c = (CartaDaPrendere) obj;
							
								cards.add(new Card(c.getSeed(),c.getValue()));
							}
							
							if(obj instanceof Scopa)  {
								Scopa c = (Scopa) obj;
							   
								Game.getIstance().addScopa(p.getTeamNumber());
								System.out.println("\n-----------------------");
								System.out.println("SCOPA:"+c.toString());
								System.out.println("-----------------------");
							}
						
					}
					System.out.println();
				} catch (Exception e) {
					e.printStackTrace();
				} 			
				
			
			
		
		
			
			
		}	else {
		
			//non posso prendere niente rimuovo lo standard aggiungo l'altro programma
			
			
			Output o2 =  handler2.startSync();
			int  n=0;
			//Stampiamo su standard output ogni answerset ottenuto.
			AnswerSets answers2 = (AnswerSets) o2;
			if(!answers2.getAnswersets().isEmpty()) {
				
			AnswerSet a2=	answers2.getAnswersets().get(0);
			
				try {

				
					for(Object obj:a2.getAtoms()){
					
						
							if(obj instanceof CartaScelta)  {
								CartaScelta c = (CartaScelta) obj;
								
								
							//	cards.add(new Card(c.getSeed(),c.getValue()));
								p.setNextCard(new Card(c.getSeed(),c.getValue()));
								
								
							//	Game.getIstance().addCardToTable(p.getNextCard());
								//cards.add(p.getNextCard());
							}
						
							
						
					}
					System.out.println();
				} catch (Exception e) {
					e.printStackTrace();
				} 			
			
		
			
		}
		}
		
		return Game.getIstance().getCardFromTable(cards);
	
	}


	
}
