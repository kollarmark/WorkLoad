package workload.menu;

/**
 * @author MK - MNr 611396
 */

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import workload.eintrag.Eintrag;
import workload.eintrag.EintragsVerwaltung;
import workload.planung.PlanungsVerwaltung;

public class EintragsUebersicht extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EintragsUebersicht dialog = new EintragsUebersicht();
			
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EintragsUebersicht() {
		setTitle("Einträge");
		setBounds(100, 100, 500, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			
			String[][] data = { {"" , "" , ""} };
			String[] headers = { "Datum" , "Modul", "Betrag" };
			
			try {
				EintragsVerwaltung.getInstance().readEintraege();
				data = new String[EintragsVerwaltung.getInstance().getEintraege().size()][3];
				
				
				ArrayList<Eintrag> eint = EintragsVerwaltung.getInstance().getEintraege();
				Eintrag[] temp = new Eintrag[eint.size()];
				
				for (int i = 0; i < temp.length; i++) {
					temp[i] = eint.get(i);
				}
				
				// Komparator für das Vergleichen von Eintragsobjekten (nach Datum)
				Comparator<Eintrag> eintragComparator = new Comparator<Eintrag>(){

					@Override
					public int compare(Eintrag o1, Eintrag o2) {
						if (o1.getVergleichsDatum() > o2.getVergleichsDatum())
							return 1;
						else
							return -1;
					}
				};
				
				
				Arrays.sort(temp, eintragComparator);
				
				for (int i = 0; i < temp.length; i++) {
					System.out.println(temp[i].getVergleichsDatum());
				}
				
				for (int i = 0; i < EintragsVerwaltung.getInstance().getEintraege().size(); i++) {
					data[i][0] = temp[i].getKal().getTime().toString().substring(0, 11);
					data[i][1] = temp[i].getModul().toString();
					data[i][2] = temp[i].getBetragString();
				}
			} catch (Exception e) {
				
				dispose();
				
			}
			
			
			table = new JTable(data, headers);
			contentPanel.add(table);
			contentPanel.add(new JScrollPane(table));
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
