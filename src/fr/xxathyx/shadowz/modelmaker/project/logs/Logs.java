package fr.xxathyx.shadowz.modelmaker.project.logs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Logs {
	
	private File logsFile;
	
	public Logs(File logsFile) {
		this.logsFile = logsFile;
	}
	
	public File getFile() {
		return logsFile;
	}
	
	public String getLastAction() throws FileNotFoundException {
		
		String brute = getLogs().get(getLogs().size());
		
		for(int i = 0; i < brute.length(); i++) {
			if(i < 18) {
				brute = brute.replace(brute.charAt(i), ' ');
			}
		}
		
		brute.replace(" ", "");
		
		return brute;
	}
	
	public ArrayList<String> getLogs() throws FileNotFoundException {
		
		ArrayList<String> logs = new ArrayList<String>();
		
	    Scanner scanner = new Scanner(logsFile);

	    while (scanner.hasNextLine()) {
	        String line = scanner.nextLine();
	        logs.add(line);
	    }
	    scanner.close();
	    
	    return logs;
	}
	
	public void write(ActionType actionType, String complements) throws IOException {
		
		Date date = new Date();
		DateFormat shortDateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
		
		FileWriter fw = new FileWriter(logsFile, true);
		
	    fw.write("[" + shortDateFormat.format(date) + "]: " + actionType.toString() + " " + complements + "\n");
	    fw.close();
	}
	
	public void write(String action) throws IOException {
		
		Date date = new Date();
		DateFormat shortDateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
		
		FileWriter fw = new FileWriter(logsFile, true);
		
	    fw.write("[" + shortDateFormat.format(date) + "]: " + action + "\n");
	    fw.close();
	}	
}