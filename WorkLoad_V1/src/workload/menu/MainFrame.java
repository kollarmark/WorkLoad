package workload.menu;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import workload.modul.Modules;

public class MainFrame extends JFrame{
	
	public MainFrame(String title) {
		super(title);
		
		setLayout(new BorderLayout());
		
		File modulliste = new File("modulliste.txt");
		int c;
		String test= "";
		String text= "";
		try {
			FileReader fr = new FileReader(modulliste);
			BufferedReader bf = new BufferedReader(fr);
			
			while ((test = bf.readLine()) != null) {
				text += test + " ";
			}
			//myFrame.add(new JLabel(text));
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*try {
			FileReader fr = new FileReader(modulliste);
			while ((c = fr.read()) != -1) {
				test = test + (char)c;
				JLabel jl = new JLabel(test);
				myFrame.add(jl);
				//System.out.println((char)c);
			}
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		JComboBox combo1 = new JComboBox();
		
		Modules m = new Modules();
		m.readModules();
		
		for (int i = 0; i < m.getLoadedModules().length; i++) {
			combo1.addItem(m.getLoadedModules()[i].getName());
		}
		
		combo1.addActionListener( new ActionListener() {
			  @Override public void actionPerformed( ActionEvent e )
			  {
			    System.out.println( e );
			    JComboBox selectedChoice = (JComboBox) e.getSource();
			    if ( "Ende".equals( selectedChoice.getSelectedItem() ) )
			      System.exit( 0 );
			  }
			} );
		combo1.setSize(50, 50);
		Container cont = getContentPane();
		cont.add(combo1, BorderLayout.NORTH);		
	}

}
