package workload.eintrag;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import workload.modul.Modul;

public class Eintrag implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8242019000092877709L;
	private Modul modul;
	private Calendar kal = Calendar.getInstance();
	private int betrag;
	private Date datum = kal.getTime();
	private boolean hasBeenMatched = false; 
	private int vergleichsDatum;
	
	
	public Eintrag(Modul modul, Date d, int betrag) {
		this.modul = modul;
		this.kal.setTime(d);
		this.betrag = betrag;
	}
	public Eintrag() {
		// TODO Auto-generated constructor stub
	}
	public Modul getModul() {
		return modul;
	}
	public void setModul(Modul modul) {
		this.modul = modul;
	}
	
	public Calendar getKal() {
		return kal;
	}
	public void setKal(Calendar kal) {
		this.kal = kal;
	}
	public int getBetrag() {
		return betrag;
	}
	
	public String getBetragString() {
		return "" + betrag;
	}
	public void setBetrag(int betrag) {
		this.betrag = betrag;
	}
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	
	public String toString() {
		return modul.toString();
	}
	public boolean hasBeenMatched() {
		return hasBeenMatched;
	}
	public void setHasBeenMatched(boolean hasBeenMatched) {
		this.hasBeenMatched = hasBeenMatched;
	}
	
	public int getVergleichsDatum() {
		int monat = this.getKal().get(Calendar.MONTH)+1;
		int tag = this.getKal().get(Calendar.DAY_OF_MONTH);
		
		int concat = Integer.valueOf(String.valueOf(monat) + String.valueOf(tag)); // Für den Vergleich nach Datum zusammensetzung von Monat und Tag z.B. 12. Aug = 812
		
		return concat;
	}

}
