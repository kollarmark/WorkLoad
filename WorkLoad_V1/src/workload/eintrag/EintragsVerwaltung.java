package workload.eintrag;

/**
 * @author MK - MNr 611396
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import workload.modul.Modul;

public class EintragsVerwaltung {
	
	private ArrayList<Eintrag> eintraege = new ArrayList<Eintrag>();
	private File f = new File("eintragsliste01.dat");
	
	private EintragsVerwaltung() {}
	
	private static EintragsVerwaltung einzigartigeEintragsVerwaltung;
	
	
	// Singleton
	public static EintragsVerwaltung getInstance() {
		if (einzigartigeEintragsVerwaltung == null) {
			einzigartigeEintragsVerwaltung = new EintragsVerwaltung();
			return einzigartigeEintragsVerwaltung;
		}
		
		return einzigartigeEintragsVerwaltung;
	}
	
	public void readEintraege() {
		
		// Auslesen aus der Datei

		
		try {
			
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			
			
			try {
				eintraege.clear();  // Um einen "frischen" Stand zu erhalten.
				
				Eintrag[] tmp = (Eintrag[]) ois.readObject();
				
				
				for (int i = 0; i < tmp.length; i++) {
					eintraege.add(tmp[i]);
				}
				
				ois.close();
				
				// Test
				/*System.out.println("Eintr�ge");
				System.out.println("---");
				for (int i = 0; i < eintraege.size(); i++) {
					
					System.out.println(eintraege.get(i));
					
				}
				System.out.println("---");*/
				
			} catch (ClassNotFoundException e) {
				System.out.println("Die Datei f�r die Speicherung von Eintr�gen ist fehlerhaft.");
				
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			System.out.println("Die Datei f�r die Speicherung von Eintr�gen konnte nicht gefunden werden.");
			e.printStackTrace();
		} catch (IOException e) {
			
			
		}
				
	}
	
	public void saveEintraege() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
			
			Eintrag[] e = new Eintrag[eintraege.size()];
			for (int i = 0; i < e.length; i++) {
				e[i] = eintraege.get(i);
			}
			oos.writeObject(e);
			
			oos.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Die Datei f�r die Speicherung von Eintr�gen konnte nicht gefunden werden.");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteEintraege() throws IOException {
		// Das L�schen von allen Eintr�gen
		
		f.delete();
		eintraege.clear();
		
		f.createNewFile();
		
		
	}
	

	public ArrayList<Eintrag> getEintraege() {
		return eintraege;
	}

	public void setEintraege(ArrayList<Eintrag> eintraege) {
		this.eintraege = eintraege;
	}
	
	
	

}


