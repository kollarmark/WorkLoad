package workload.menu;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.io.File;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import workload.eintrag.Eintrag;
import workload.eintrag.EintragsVerwaltung;
import workload.modul.*;

public class EintragMenu extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EintragMenu dialog = new EintragMenu();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EintragMenu() {
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
		dateChooser.setDate(new Date());
		
		
		
		JLabel lblNewLabel = new JLabel("Betrag");
		contentPanel.add(lblNewLabel);
		
		TextField BetragTextField = new TextField();
		contentPanel.add(BetragTextField);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(114, 357, 434, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						
						// Verifizieren
						
						if ( BetragTextField.getText().equals("")) {
							JOptionPane.showMessageDialog(rootPane, "Bitte Betrag eingeben.");
						} else {
							
							Eintrag ein = new Eintrag((Modul)modulComboBox.getSelectedItem(), dateChooser.getDate(), Integer.parseInt(BetragTextField.getText()));
							
							// Bei keiner Eingabe Eintrag nicht speichern
							
							if (!(BetragTextField.getText().equals(""))) {
								EintragsVerwaltung.getInstance().getEintraege().add(ein);
								
								// Serialisierung des Objektes
								
								EintragsVerwaltung.getInstance().saveEintraege();
								
								JOptionPane.showMessageDialog(rootPane, ein.getModul() + " " + ein.getKal().getTime() + " " + ein.getBetrag());
								dispose();
							}
							
						}
						
						
						
						
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
