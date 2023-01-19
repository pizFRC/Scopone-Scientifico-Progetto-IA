package Model;

import java.util.ArrayList;
import java.util.Collections;


public class PointsCounter {


public void setThesholdPoint(Integer thesholdPoint) {
		this.thesholdPoint = thesholdPoint;
	}

private int pointTeamOne;
private int pointTeamTwo;
private int scopeTeamOne;
private int scopeTeamTwo;

private Integer thesholdPoint;



public PointsCounter(Integer thesholdPoint)
{
	if(thesholdPoint<10) {
		this.thesholdPoint=11;
	}
	else
		this.thesholdPoint=thesholdPoint;
}

public void resetTeamPoint() {
	scopeTeamOne=0;
	scopeTeamTwo=0;
	
}
public void countPoint(ArrayList<Card>cardT1,ArrayList<Card>cardT2) {
	int team1=1,team2=2,denariT1=0,denariT2=0,numSetteT1=0,numSetteT2=0;
	//CARTE,SETTE,PRIMERA,ORO
	System.out.println("/////////////////////////////////////");
	System.out.println("\t \t \t \t POINT \t \t \t \t");
	
	int []points= {0,0,0,0};
	if(cardT1.size()>cardT2.size()) {
		points[0]=team1;
		System.out.println("CARTE : T1");
	}else if(cardT2.size()>cardT1.size()) {
		points[0]=team2;
		System.out.println("CARTE : T2");
	}
	if(cardT1.contains(new Card(Seed.DENARI,7))) {
		points[1]=team1;
		System.out.println("7 DENARI : T1");
	}else {
		points[1]=team2;
		System.out.println("7 DENARI : T2");
	}
	for(Card c : cardT1) {
		if(c.getSeed()=="denari")
			denariT1+=1;
	}
	for(Card c : cardT2) {
		if(c.getSeed()=="denari")
			denariT2+=1;
	}
	if(denariT1>denariT2) {
		points[3]=1;
		System.out.println("DENARI:T1"); 
	}else if(denariT2>denariT1) {
		points[3]=2;
		System.out.println("DENARI:T2"); 
	}
	int primera;
	primera=primera(cardT1,cardT2,7);
	System.out.println("primera:"+primera);
	if(primera==1) {
		points[3]=1;
	}else if(primera==2) {
		points[3]=2;
	}
	for(int x:points) {
		if(x==1) {
			this.pointTeamOne+=1;
		}else if(x==2) {
			this.pointTeamTwo+=1;
		}
	}
	
	this.pointTeamOne+=this.scopeTeamOne;

	this.pointTeamTwo+=this.scopeTeamTwo;

	
		
			
}
public boolean isGameFinished() {
	boolean T1=this.pointTeamOne >= this.thesholdPoint;
	boolean T2=this.pointTeamTwo >= this.thesholdPoint;
			if(T1)
				System.out.println("WINNER T1");
			else if(T2)
				System.out.println("WINNER T2");
	return (T1) || (T2);
	
	
}

public int primera(ArrayList<Card>cardT1,ArrayList<Card>cardT2,int value) {
	
	if(value==0)
		return 0;
	
	ArrayList<Card>tmpT1=new ArrayList<Card>();
	ArrayList<Card>tmpT2=new ArrayList<Card>();
	for(Card c:cardT1 ) {
		if(c.getValue()==value)
			tmpT1.add(c);
	}
	for(Card c:cardT2 ) {
		if(c.getValue()==value)
			tmpT2.add(c);
	}
	
	if(tmpT1.size()>tmpT2.size()) {
		
		return 1;
	}else if(tmpT2.size()>tmpT1.size()) {
	
		return 2;
	}else {
		
		return primera(cardT1,cardT2,value-1);
	}
	
	
	
	
}
public int getPointTeamOne() {
	return pointTeamOne;
}

public void setPointTeamOne(int pointTeamOne) {
	this.pointTeamOne = pointTeamOne;
}

public int getPointTeamTwo() {
	return pointTeamTwo;
}

public void setPointTeamTwo(int pointTeamTwo) {
	this.pointTeamTwo = pointTeamTwo;
}

public int getScopeTeamOne() {
	return scopeTeamOne;
}

public void setScopeTeamOne(int scopeTeamOne) {
	this.scopeTeamOne = scopeTeamOne;
}

public int getScopeTeamTwo() {
	return scopeTeamTwo;
}

public void setScopeTeamTwo(int scopeTeamTwo) {
	this.scopeTeamTwo = scopeTeamTwo;
}

public Integer getThesholdPoint() {
	return thesholdPoint;
}

public void print() {
	System.out.println("PUNTI TEAM 1:"+this.pointTeamOne);
	System.out.println("SCOPE TEAM 1:"+this.scopeTeamOne);
	System.out.println("PUNTI TEAM 2:"+this.pointTeamTwo);
	System.out.println("SCOPE TEAM 2:"+this.scopeTeamTwo);
	System.out.println("/////////////////////////////////////");
	resetTeamPoint();
}


}
