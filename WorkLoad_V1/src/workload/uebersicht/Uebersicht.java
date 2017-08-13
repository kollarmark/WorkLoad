package workload.uebersicht;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import workload.eintrag.*;
import workload.modul.Modul;
import workload.planung.PlanungsVerwaltung;

public class Uebersicht {
    
    // Zusammenf�hrung von EintragsVerwaltung und PlanungsVerwaltung
    
    // Wiedergibt eine Liste von der Summe von Eintr�gen und Planungen abh�ngig von dem Datum
    
    // Beim anklicken der �bersicht muss das neue Auslesen der Eintr�ge geforcet werden
    
    private ArrayList<UebersichtsElement> uebersichtsElemente = new ArrayList<UebersichtsElement>();
    
    // Standardkonstruktur, alle Eintr�ge und Planungen enthalten (Cross-Join)
    
    public Uebersicht() {
   	 generateUebersichtsElemente();
    }
    
    public String[] toStringArray() {
   	 
   	 return (String[])uebersichtsElemente.toArray();
    }
    
    public void load() {
   	 
    }
    public void save() {
    	EintragsVerwaltung.getInstance().saveEintraege();
    }

    public ArrayList<UebersichtsElement> getUebersichtsElemente() {
   	 return uebersichtsElemente;
    }

    public void setUebersichtsElemente(ArrayList<UebersichtsElement> uebersichtsElemente) {
   	 this.uebersichtsElemente = uebersichtsElemente;
    }
    
    public void generateUebersichtsElemente() {
   	 
   	 EintragsVerwaltung ev = EintragsVerwaltung.getInstance();
   	 PlanungsVerwaltung pv = PlanungsVerwaltung.getInstance();
   	 
   	 ev.readEintraege();
   	 pv.readPlanungen();
   	 
   	 String datum, modul, geplant, gemacht, diff;
   	 
   	 for (int i = 0; i < ev.getEintraege().size(); i++) {
   		 for (int j = 0; j < pv.getPlanungen().size(); j++) {
   			 
   			 
   			 // F�r jeden Eintrag �berpr�fen, ob eine Planung f�r dasselbe Datum & f�r das selbe Modul vorhanden
   			 if ( (ev.getEintraege().get(i).getKal().get(Calendar.YEAR) == pv.getPlanungen().get(j).getKal().get(Calendar.YEAR)) &&			// Jahr stimmt �berein
   					 (ev.getEintraege().get(i).getKal().get(Calendar.MONTH) == pv.getPlanungen().get(j).getKal().get(Calendar.MONTH)) &&	// Monat stimmt �berein
   					 (ev.getEintraege().get(i).getKal().get(Calendar.DATE) == pv.getPlanungen().get(j).getKal().get(Calendar.DATE)) &&		// Tag stimmt �berein
   					 ev.getEintraege().get(i).getModul().toString().equals(pv.getPlanungen().get(j).getModul().toString()) ) {				// Modul stimmt �berein
   				 
   				 datum = ev.getEintraege().get(i).getKal().getTime().toString();
   				 modul = ev.getEintraege().get(i).getModul().toString();
   				 geplant = pv.getPlanungen().get(j).getBetragString();
   				 gemacht = ev.getEintraege().get(i).getBetragString();
   				 diff = ""+((pv.getPlanungen().get(j).getBetrag()) - (ev.getEintraege().get(i).getBetrag()));
   				 
   				 uebersichtsElemente.add(new UebersichtsElement(datum, modul, geplant, gemacht, diff));
   				 
   				 ev.getEintraege().get(i).setHasBeenMatched(true);
   				 pv.getPlanungen().get(j).setHasBeenMatched(true);
   				 
   				 System.out.println("Dieses Element wurde gematcht:");
   				 System.out.println(ev.getEintraege().get(i).toString() + " " + ev.getEintraege().get(i).hasBeenMatched());
   				 System.out.println(pv.getPlanungen().get(j).toString() + " " + pv.getPlanungen().get(j).hasBeenMatched());
   				 
   			 }
   		 }
   	 }
   	 
   	 
   	 
   	 // F�r alle nicht gematchten Elemente
   	 
   	 for (int i = 0; i < ev.getEintraege().size(); i++) {
   		 if (!(ev.getEintraege().get(i).hasBeenMatched())) {
   			 datum = ev.getEintraege().get(i).getKal().getTime().toString();
   			 modul = ev.getEintraege().get(i).getModul().toString();
   			 geplant = "0";
   			 gemacht = ev.getEintraege().get(i).getBetragString();
   			 diff = ev.getEintraege().get(i).getBetragString();
   			 
   			 uebersichtsElemente.add(new UebersichtsElement(datum, modul, geplant, gemacht, diff));
   		 }
   	 }
   	 
   	 for (int i = 0; i < pv.getPlanungen().size(); i++) {
   		 if (!(pv.getPlanungen().get(i).hasBeenMatched())) {
   			 datum = pv.getPlanungen().get(i).getKal().getTime().toString();
   			 modul = pv.getPlanungen().get(i).getModul().toString();
   			 geplant = pv.getPlanungen().get(i).getBetragString();
   			 gemacht = "" + 0;
   			 diff = "-" + pv.getPlanungen().get(i).getBetragString();
   			 
   			 uebersichtsElemente.add(new UebersichtsElement(datum, modul, geplant, gemacht, diff));
   		 }
   	 }
   	 
    }

}
