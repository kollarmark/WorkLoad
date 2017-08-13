package workload.uebersicht;

import workload.eintrag.Eintrag;
import workload.planung.Planung;

public class UebersichtsElement {
	
	
	private String datum;
	private String modul;
	private String geplant;
	private String gemacht;
	private String diff;
	private int datumSort;
	private boolean matched;
	
	public UebersichtsElement(String datum, String modul, String geplant, String gemacht, String diff, int sort) {
		this.datum = datum;
		this.modul = modul;
		this.geplant = geplant;
		this.gemacht = gemacht;
		this.diff = diff;
		this.datumSort = sort;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public String getModul() {
		return modul;
	}

	public void setModul(String modul) {
		this.modul = modul;
	}

	public String getGeplant() {
		return geplant;
	}

	public void setGeplant(String geplant) {
		this.geplant = geplant;
	}

	public String getGemacht() {
		return gemacht;
	}

	public void setGemacht(String gemacht) {
		this.gemacht = gemacht;
	}

	public String getDiff() {
		return diff;
	}

	public void setDiff(String diff) {
		this.diff = diff;
	}

	public boolean isMatched() {
		return matched;
	}

	public void setMatched(boolean matched) {
		this.matched = matched;
	}

	public int getDatumSort() {
		return datumSort;
	}

	public void setDatumSort(int datumSort) {
		this.datumSort = datumSort;
	}
	
	

}
