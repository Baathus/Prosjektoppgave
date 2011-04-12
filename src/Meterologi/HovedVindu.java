package Meterologi;

////////////////////////////////////////
///////HovedVindu-gui skrevet av Nam Le
////// tab1.java "Registrer et nytt sted"
////// tab2.java "Regsitrer ny data"
////// tab3.java "Vis Data"
////// tab4.java "Statistikk"
////////////////////////////////////////

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

public class HovedVindu extends JFrame
{

	tab1 t1 = new tab1(); //tab 1 = "Registrer et nytt sted"
	RegistrerData regdata= new RegistrerData(); //tab 2 = "Regsitrer ny data"
	tab3 t3 = new tab3(); //tab 3 = "Vis Data"
	tab4 t4 = new tab4(); //tab 4 = "Statistikk"

	public HovedVindu()
	{

		JFrame frame = new JFrame("Hovedvindu");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTabbedPane tabs = new JTabbedPane(); // tabs

		JPanel p1 = t1.tab1();
		JPanel p2 = regdata.ByggPanel(); //<--- her henter jeg return; FUNKER IKKE!"#�%&/(
		JPanel p3 = t3.tab3();
		JPanel p4 = t4.tab4();

		tabs.addTab("Registrer et nytt sted",null,p1, "funker");
		tabs.addTab("Regsitrer ny Data",null,p2, "funker");
	    tabs.addTab("Vis Data",null,p3, "funker");
	    tabs.addTab("Statistikk",null,p4, "funker");


		setLayout(new FlowLayout());
	    frame.setPreferredSize( new java.awt.Dimension(800,600) );
		frame.add(tabs);
		frame.setSize(800,600);
		frame.setVisible(true);
	}

	public static void main(String [] args)
	{
		HovedVindu m = new HovedVindu();

    }
}

