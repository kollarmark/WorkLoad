package workload.planung;

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
	
	private PlanungsVerwaltung() {}
	
	private static PlanungsVerwaltung pv;
	
	public static PlanungsVerwaltung getInstance() {
		if (pv == null) {
			pv = new PlanungsVerwaltung();
		}
		
		
		// Generierung der Datei
			/*File f = new File("planungsliste01.dat");
			if(f.exists() && !f.isDirectory()) { 
				
			} else {
				try {
					f.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}*/
		
		return pv;
	}
	
public void readPlanungen() {
		
		// Auslesen aus der Datei

		
		try {
			
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("planungsliste01.dat"));
			
			try {
				planungen.clear();
				
				Planung[] tmp = (Planung[]) ois.readObject();
				
				for (int i = 0; i < tmp.length; i++) {
					planungen.add(tmp[i]);
				}
				
				//planungen = (ArrayList<Eintrag>) Arrays.asList(tmp);
				ois.close();
				
				// Test
				
				System.out.println("Planungen");
				System.out.println("---");
				for (int i = 0; i < planungen.size(); i++) {
					
					System.out.println(planungen.get(i));
					
				}
				System.out.println("---");
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
	public void savePlanungen() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("planungsliste01.dat"));
			
			Planung[] e = new Planung[planungen.size()];
			for (int i = 0; i < e.length; i++) {
				e[i] = planungen.get(i);
			}
			oos.writeObject(e);
			
			oos.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Planung> getPlanungen() {
		return planungen;
	}

	public void setPlanungen(ArrayList<Planung> planungen) {
		this.planungen = planungen;
	}
	
	

}
