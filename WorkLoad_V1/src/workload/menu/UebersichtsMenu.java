package workload.menu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import workload.eintrag.Eintrag;
import workload.uebersicht.Uebersicht;
import workload.uebersicht.UebersichtsElement;

public class UebersichtsMenu extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UebersichtsMenu dialog = new UebersichtsMenu();
			
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.pack();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public UebersichtsMenu() throws EOFException {
		setTitle("Übersicht");
		setBounds(100, 100, 619, 518);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			
			String [][] data = { {"", "", "", "", ""} };
			
			//File f = new File("eintragsliste01.dat");
			//if(f.exists() && !f.isDirectory()) { 
			
			
			
			Uebersicht u = new Uebersicht();
			u.generiereUebersichtsElemente();
			
			data = new String[u.getUebersichtsElemente().size()][5];
			
			UebersichtsElement[] temp = new UebersichtsElement[u.getUebersichtsElemente().size()];
			
			for (int i = 0; i < temp.length; i++) {
				temp[i] = u.getUebersichtsElemente().get(i);
			}
			
			Comparator<UebersichtsElement> ueEComparator = new Comparator<UebersichtsElement>(){

				@Override
				public int compare(UebersichtsElement o1, UebersichtsElement o2) {
					if (o1.getDatumSort() > o2.getDatumSort())
						return 1;
					else
						return -1;
				}
			};
			
			Arrays.sort(temp, ueEComparator);
			
			for (int i = 0; i < temp.length; i++) {
				System.out.println(temp[i].getDatumSort());
			}
			
			for (int i = 0; i < u.getUebersichtsElemente().size(); i++) {
				
				data[i][0] = temp[i].getDatum();
				data[i][1] = temp[i].getModul();
				data[i][2] = temp[i].getGeplant();
				data[i][3] = temp[i].getGemacht();
				data[i][4] = temp[i].getDiff();
				
			}
			
			String[] headers = { "Datum" , "Modul", "Geplant", "Gemacht", "Diff" };
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
						
						//System.getProperty("user.dir");
						
						/*File f = new File("");
						if(f.exists() && !f.isDirectory()) { 
						    // do something
						}*/
						
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
