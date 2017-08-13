package workload.menu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

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
		setBounds(100, 100, 500, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			EintragsVerwaltung.getInstance().readEintraege();
			
			String[][] data = new String[EintragsVerwaltung.getInstance().getEintraege().size()][3];
			String[] headers = { "Datum" , "Modul", "Betrag" };
			
			ArrayList<Eintrag> eint = EintragsVerwaltung.getInstance().getEintraege();
			Eintrag[] temp = new Eintrag[eint.size()];
			
			for (int i = 0; i < temp.length; i++) {
				temp[i] = eint.get(i);
			}
			
			int monat = 0;
			int tag = 0;
			int concat = 0;
			for (int i = 0; i < temp.length; i++) {
				monat = temp[i].getKal().get(Calendar.MONTH)+1;
				tag = temp[i].getKal().get(Calendar.DAY_OF_MONTH);
				
				concat = Integer.valueOf(String.valueOf(monat) + String.valueOf(tag)); // Für den Vergleich nach Datum zusammensetzung von Monat und Tag z.B. 12. Aug = 812
				
				System.out.println(concat);
			}
			
			for (int i = 0; i < EintragsVerwaltung.getInstance().getEintraege().size(); i++) {
				data[i][0] = EintragsVerwaltung.getInstance().getEintraege().get(i).getKal().getTime().toString();
				data[i][1] = EintragsVerwaltung.getInstance().getEintraege().get(i).getModul().toString();
				data[i][2] = EintragsVerwaltung.getInstance().getEintraege().get(i).getBetragString();
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
