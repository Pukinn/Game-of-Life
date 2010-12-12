import java.util.Scanner;





public class gameOfLive
{

	public static GUI myGUI;
	
	
	
	public static void main(String args[])
	{
		
		myGUI = new GUI();
		
	// ASK FOR MODE
		Scanner myScanner = new Scanner(System.in);
		System.out.println("Startfläche setzen:\nZufällig (0)\nGleiter(1)");
		int iSetrandom = myScanner.nextInt();
		
	// CREATE FIELD
		myGUI.createFrame();
		myGUI.createGameField();
		//	myGUI.menu();
		
		
		if (iSetrandom == 0)
		{
			myGUI.myArea.setRandom(0.45, 0.55, 0.45, 0.55, 0.3);
		}
		else if (iSetrandom == 1)
		{
			myGUI.myArea.drawGlider();
		}
		else
		{
			System.out.println("Fehler: Keine gültige Spielfläche!");
		}
		
		
		myGUI.show();
		
		
		
		

		try { Thread.sleep(1000); }
		catch (InterruptedException e) {}
		
		
		while (true)
		{
			try { Thread.sleep(30);}
			catch (InterruptedException e) {}
			
			myGUI.myArea.nextGeneration(0);	
		}
	
		
		
		
	}
	


}