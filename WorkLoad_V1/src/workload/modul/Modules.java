package workload.modul;

import java.io.*;
import java.util.ArrayList;

import javax.swing.JLabel;

public class Modules {
	
	private Modul[] loadedModules;
	
	public void readModules() {
		
		File f = new File("modulliste01.csv");
		String line = "";
		String csvSplitBy = ",";
		String[] module = null;
		ArrayList<Modul> modules = new ArrayList<Modul>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(f))) {
			
			while ((line = br.readLine()) != null) {

                // use comma as separator
                module = line.split(csvSplitBy);
                
                String sem;
                String mod;
                String cred;

                for (int i = 0; i < module.length; i+=4) {
					sem = module[i+1];
					mod = module[i+2];
					cred = module[i+3];
					
					modules.add(new Modul(sem, mod, cred));
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		for (int i = 0; i < modules.size(); i++) {
			//System.out.println(modules.get(i));
		}
		
		try {
			ObjectOutputStream  oos = new ObjectOutputStream(new FileOutputStream("modulelist.dat"));
			Modul[] moduleArray = new Modul[modules.size()];
			moduleArray = modules.toArray(moduleArray);
			oos.writeObject(moduleArray);
			oos.close();
			
			// Test
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("modulelist.dat"));
			loadedModules = (Modul[])ois.readObject();
			ois.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Modul[] getLoadedModules() {
		return loadedModules;
	}

	public void setLoadedModules(Modul[] loadedModules) {
		this.loadedModules = loadedModules;
	}
	
	
}
