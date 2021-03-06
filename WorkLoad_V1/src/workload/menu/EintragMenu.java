package workload.menu;

/**
 * @author MK - MNr 611396
 */

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
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
import javax.swing.SwingConstants;

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
		setTitle("Eintr�ge");
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
		
		JDateChooser dateChooser = new JDateChooser();
		contentPanel.add(dateChooser);
		
		JLabel lblNewLabel = new JLabel("Betrag");
		contentPanel.add(lblNewLabel);
		
		TextField BetragTextField = new TextField();
		contentPanel.add(BetragTextField);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(298, 357, 250, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						
						// Verifizierung der Eingabe
						
						if ( BetragTextField.getText().equals("")) {
							JOptionPane.showMessageDialog(rootPane, "Bitte Betrag eingeben.");
						} else {
							
							Eintrag ein = new Eintrag((Modul)modulComboBox.getSelectedItem(), dateChooser.getDate(), Integer.parseInt(BetragTextField.getText()));
							
							// Bei keiner Eingabe Eintrag nicht speichern
							
							EintragsVerwaltung.getInstance().getEintraege().add(ein);
							
							// Speichern des Eintrags
							
							EintragsVerwaltung.getInstance().saveEintraege();
							
							JOptionPane.showMessageDialog(rootPane, "Eintrag gespeichert!");
							dispose();
							
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
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 357, 266, 33);
		getContentPane().add(panel);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		
		JButton btnNewButton = new JButton("Eintrags\u00FCbersicht");
		btnNewButton.setHorizontalAlignment(SwingConstants.RIGHT);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				EintragsUebersicht eu = new EintragsUebersicht();
				
				eu.setLocationRelativeTo(getParent());
				eu.setVisible(true);
				
			}
		});
		panel.add(btnNewButton);
		
		JButton btnAlleLschen = new JButton("Alle l\u00F6schen");
		btnAlleLschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					EintragsVerwaltung.getInstance().deleteEintraege();
					JOptionPane.showMessageDialog(rootPane, "Eintr�ge gel�scht!");
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(rootPane, "Fehler beim L�schen von Eintr�gen!");
					e1.printStackTrace();
				}
				
			}
		});
		panel.add(btnAlleLschen);
	}
}
