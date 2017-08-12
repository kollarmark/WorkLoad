package workload.menu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

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
		setBounds(100, 100, 500, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			PlanungsVerwaltung.getInstance().readPlanungen();
			
			String[][] data = new String[PlanungsVerwaltung.getInstance().getPlanungen().size()][3];
			String[] headers = { "Datum" , "Modul", "Betrag" };
			
			for (int i = 0; i < PlanungsVerwaltung.getInstance().getPlanungen().size(); i++) {
				data[i][0] = PlanungsVerwaltung.getInstance().getPlanungen().get(i).getDatum().toString();
				data[i][1] = PlanungsVerwaltung.getInstance().getPlanungen().get(i).getModul().toString();
				data[i][2] = PlanungsVerwaltung.getInstance().getPlanungen().get(i).getBetragString();
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
