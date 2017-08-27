package workload.planung;

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

import workload.eintrag.Eintrag;

public class PlanungsVerwaltung {
	
	private ArrayList<Planung> planungen = new ArrayList<Planung>();
	private File f = new File("planungsliste01.dat");
	
	private PlanungsVerwaltung() {}
	
	private static PlanungsVerwaltung pv;
	
	// Singleton
	public static PlanungsVerwaltung getInstance() {
		if (pv == null) {
			pv = new PlanungsVerwaltung();
			return pv;
		}
		
		return pv;
	}
	
public void readPlanungen() {
		
		// Auslesen aus der Datei
		
		try {
			
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			
			try {
				planungen.clear();	// Um einen "frischen" Stand zu erhalten.
				
				Planung[] tmp = (Planung[]) ois.readObject();
				
				for (int i = 0; i < tmp.length; i++) {
					planungen.add(tmp[i]);
				}
				
				ois.close();
				
				// Test
				
				/*System.out.println("Planungen");
				System.out.println("---");
				for (int i = 0; i < planungen.size(); i++) {
					
					System.out.println(planungen.get(i));
					
				}
				System.out.println("---");*/
				
			} catch (ClassNotFoundException e) {
				
				System.out.println("Die Datei für die Speicherung von Planungen ist fehlerhaft.");
				
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			
			System.out.println("Die Datei für die Speicherung von Planungen konnte nicht gefunden werden.");
			
			e.printStackTrace();
		} catch (IOException e) {
			
		}
				
	}
	
	public void savePlanungen() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
			
			Planung[] e = new Planung[planungen.size()];
			for (int i = 0; i < e.length; i++) {
				e[i] = planungen.get(i);
			}
			oos.writeObject(e);
			
			oos.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Die Datei für die Speicherung von Planungen konnte nicht gefunden werden.");
			
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deletePlanungen() throws IOException {
		//Löschen von allen Planungen
		
		f.delete();
		planungen.clear();
		
		f.createNewFile();
		
	}

	public ArrayList<Planung> getPlanungen() {
		return planungen;
	}

	public void setPlanungen(ArrayList<Planung> planungen) {
		this.planungen = planungen;
	}
	
	

}
