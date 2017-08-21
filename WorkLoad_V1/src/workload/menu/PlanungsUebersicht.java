package workload.menu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import workload.eintrag.Eintrag;
import workload.eintrag.EintragsVerwaltung;
import workload.planung.Planung;
import workload.planung.PlanungsVerwaltung;

public class PlanungsUebersicht extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PlanungsUebersicht dialog = new PlanungsUebersicht();
			
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PlanungsUebersicht() {
		setTitle("Planungen");
		setBounds(100, 100, 500, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			PlanungsVerwaltung.getInstance().readPlanungen();
			
			String[][] data = new String[PlanungsVerwaltung.getInstance().getPlanungen().size()][3];
			String[] headers = { "Datum" , "Modul", "Betrag" };
			
			ArrayList<Planung> plan = PlanungsVerwaltung.getInstance().getPlanungen();
			Planung[] temp = new Planung[plan.size()];
			
			for (int i = 0; i < temp.length; i++) {
				temp[i] = plan.get(i);
			}
			
			Comparator<Planung> planungComparator = new Comparator<Planung>(){

				@Override
				public int compare(Planung o1, Planung o2) {
					if (o1.getVergleichsDatum() > o2.getVergleichsDatum())
						return 1;
					else
						return -1;
				}
			};
			
			Arrays.sort(temp, planungComparator);
			
			for (int i = 0; i < temp.length; i++) {
				System.out.println(temp[i].getVergleichsDatum());
			}
			
			
			
			
			for (int i = 0; i < PlanungsVerwaltung.getInstance().getPlanungen().size(); i++) {
				data[i][0] = temp[i].getKal().getTime().toString().substring(0, 11);
				data[i][1] = temp[i].getModul().toString();
				data[i][2] = temp[i].getBetragString();
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
