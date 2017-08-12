package workload.planung;

import java.io.Serializable;
import java.util.Date;

import workload.eintrag.Eintrag;
import workload.modul.Modul;

public class Planung extends Eintrag implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1393001198385654040L;

	public Planung(Modul modul, Date d, int betrag) {
		super(modul, d, betrag);
	}

}
