package workload.menu;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import workload.eintrag.EintragsVerwaltung;
import workload.modul.Modul;
import workload.modul.Modules;
import workload.planung.Planung;
import workload.planung.PlanungsVerwaltung;

public class PlanungMenu extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PlanungMenu dialog = new PlanungMenu();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PlanungMenu() {
		setBounds(100, 100, 574, 440);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 548, 346);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(new GridLayout(3, 2, 5, 5));
		
		JLabel ModulLabel = new JLabel("Modul");
		contentPanel.add(ModulLabel);
		
		Modules m = new Modules();
		m.readModules();
		
		JComboBox modulComboBox = new JComboBox();
		
		for (int i = 0; i < m.getLoadedModules().length; i++) {
			modulComboBox.addItem(m.getLoadedModules()[i]);
		}
		contentPanel.add(modulComboBox);
		
		JLabel DatumLabel = new JLabel("Datum");
		contentPanel.add(DatumLabel);
		
		
		//System.out.println(cal.get(Calendar.MONTH));
		
		JDateChooser dateChooser = new JDateChooser();
		contentPanel.add(dateChooser);
		//dateChooser.setDate(new Date());
		
		
		JLabel lblNewLabel = new JLabel("Betrag");
		contentPanel.add(lblNewLabel);
		
		TextField BetragTextField = new TextField();
		contentPanel.add(BetragTextField);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 357, 278, 33);
		getContentPane().add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JButton btnPlanungsbersicht = new JButton("Planungs\u00FCbersicht");
		btnPlanungsbersicht.setHorizontalAlignment(SwingConstants.LEFT);
		btnPlanungsbersicht.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlanungsUebersicht pu = new PlanungsUebersicht();
				
				pu.setLocationRelativeTo(getParent());
				pu.setVisible(true);
			}
		});
		panel.add(btnPlanungsbersicht);
		
		JButton btnAlleLschen = new JButton("Alle l\u00F6schen");
		btnAlleLschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					PlanungsVerwaltung.getInstance().deletePlanungen();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		panel.add(btnAlleLschen);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(298, 357, 250, 33);
			FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.RIGHT);
			buttonPane.setLayout(fl_buttonPane);
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// Verifizieren
						
						Planung pla = new Planung((Modul)modulComboBox.getSelectedItem(), dateChooser.getDate(), Integer.parseInt(BetragTextField.getText()));
						
						PlanungsVerwaltung.getInstance().getPlanungen().add(pla);
						
						/*ein.setModul((Modul)modulComboBox.getSelectedItem());
						
						ein.getKal().setTime(dateChooser.getDate());
						
						ein.setBetrag(Integer.parseInt(BetragTextField.getText()));*/
						
						// Serialisierung des Objektes
						
						PlanungsVerwaltung.getInstance().savePlanungen();
						
						JOptionPane.showMessageDialog(rootPane, pla.getModul() + " " + pla.getKal().getTime() + " " + pla.getBetrag()/*ein.getKal().get(Calendar.DAY_OF_MONTH) + " " + ein.getKal().get(Calendar.MONTH) + " " + ein.getKal().get(Calendar.YEAR) ein.getKal().getTime()*/);
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
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
