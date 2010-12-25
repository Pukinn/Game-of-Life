import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Ruleset {
	
	public int iFieldsize;
	public int iAreaWidth;
	public int iAreaHeight;
	public int iSpeed;
	public int iRule;
	
	public boolean bRun;
	
	public Ruleset(){
		readRules();
		bRun = false;
	}
	
	public void readRules(){
		File file = new File("./data/rulesets");
	    FileInputStream fis = null;
	    BufferedInputStream bis = null;
	    DataInputStream dis = null;
		
		try {
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			dis = new DataInputStream(bis);
			
			int line = 1;
			while (dis.available() != 0){
				
				switch (line){
				case 1: iFieldsize = Integer.parseInt(dis.readLine()); break;
				case 2: iAreaWidth = Integer.parseInt(dis.readLine()); break;
				case 3: iAreaHeight = Integer.parseInt(dis.readLine()); break;
				case 4: iSpeed = Integer.parseInt(dis.readLine()); break;
				case 5: iRule = Integer.parseInt(dis.readLine()); break;
				}
				
				line++;
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
		
		
	}
}
