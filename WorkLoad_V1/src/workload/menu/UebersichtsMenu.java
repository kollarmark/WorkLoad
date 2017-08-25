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
import java.util.Calendar;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import workload.eintrag.Eintrag;
import workload.eintrag.EintragsVerwaltung;
import workload.planung.Planung;
import workload.planung.PlanungsVerwaltung;
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
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 603, 446);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
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
			buttonPane.setBounds(206, 446, 387, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
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
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 446, 172, 33);
		getContentPane().add(panel);
		
		JButton btnVisualisieren = new JButton("Visualisieren");
		btnVisualisieren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Hier Diagramm
				
				EintragsVerwaltung ev = EintragsVerwaltung.getInstance();
				PlanungsVerwaltung pv = PlanungsVerwaltung.getInstance();
				
				// Einträge u P sollten iwie sortiert werden
				// hier gibts ne sortierte array von den einträgen
				// bei übersichtselementen einfach separat in array nehmen, nullwerte auch
				
				Eintrag[] tempEintragArray = new Eintrag[EintragsVerwaltung.getInstance().getEintraege().size()];
				
				for (int i = 0; i < tempEintragArray.length; i++) {
					
					tempEintragArray[i] = EintragsVerwaltung.getInstance().getEintraege().get(i);
							
				}
				
				Comparator<Eintrag> eintragComparator = new Comparator<Eintrag>(){

					@Override
					public int compare(Eintrag o1, Eintrag o2) {
						if (o1.getVergleichsDatum()> o2.getVergleichsDatum())
							return 1;
						else
							return -1;
					}
				};
				
				Arrays.sort(tempEintragArray, eintragComparator);
				
				Planung[] tempPlanungArray = new Planung[PlanungsVerwaltung.getInstance().getPlanungen().size()];
				
				for (int i = 0; i < tempPlanungArray.length; i++) {
					tempPlanungArray[i] = PlanungsVerwaltung.getInstance().getPlanungen().get(i);
				}
				
				Arrays.sort(tempPlanungArray, eintragComparator);
				
				
				
				DefaultCategoryDataset dataset = new DefaultCategoryDataset();
				
				for (int i = 0; i < tempEintragArray.length; i++) {
					dataset.addValue(tempEintragArray[i].getBetrag(), "Einträge", tempEintragArray[i].getKal().getTime().toString().substring(0, 10));
				}
				
				for (int i = 0; i < tempPlanungArray.length; i++) {
					dataset.addValue(tempPlanungArray[i].getBetrag(), "Planung", tempPlanungArray[i].getKal().getTime().toString().substring(0, 10));
				}
				
				
				  /*dataset.addValue( 15 , "schools" , "1970" );
			      dataset.addValue( 30 , "schools" , "1980" );
			      dataset.addValue( 60 , "schools" , "1990" );
			      dataset.addValue( 120 , "schools" , "2000" );
			      dataset.addValue( 240 , "schools" , "2010" ); 
			      dataset.addValue( 300 , "schools" , "2014" );*/

			      JFreeChart lineChartObject = ChartFactory.createLineChart(
			         "Aufwandsdiagramm","Datum",
			         "Aufwand",
			         dataset,PlotOrientation.VERTICAL,
			         true,true,false);
			      
			      ChartPanel chartPanel = new ChartPanel( lineChartObject );
			      //chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
			      
			      JDialog diagramm = new JDialog();
			      diagramm.setContentPane(chartPanel);
			      diagramm.setBounds(100, 100, 800, 600);
			      diagramm.setLocationRelativeTo(getParent());
			      diagramm.setVisible(true);
				
			}
		});
		panel.add(btnVisualisieren);
	}
}
