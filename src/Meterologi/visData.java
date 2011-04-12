/*
 * Re-edit av Nam le
 * Oppdatert: 11.4.2011
 * Denne klassen skal bygge gui, samt metoder og lytter for registrering av visData
 * og legges til i Hovedvindu
 * .java
 */

package Meterologi;

import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;

import Meterologi.Lister.Data;
import Meterologi.Lister.DataListe;
import Meterologi.Lister.Sted;

public class visData implements ActionListener{

	private static final long serialVersionUID = 1L;

	private JTextArea utskrift;
	
	private JComboBox fylkeboks;
	private JComboBox stedboks;
	private JComboBox dagboks;
	private JComboBox m�nedboks;
	private JTextField �rfelt;
	private JButton visData;
	
	private int dag;
	private int m�ned;
	private int �r;

	//lager pekere til dataliste og data, og valgt sted.
	private DataListe dataliste;
	private Data nydata;
	private Sted valgtSted; 
	//valgtSted skal peke p� stedet man velger i comboboksene.
	//det er dette stedet man skal lagre ny data p� sted.nyData.(Data d);
	
	//array over registrerte fylker og steder. samt pekere til valgt fylke og sted
	private String fylke;
	private final String[] fylker = {"Akershus", "Aust-Agder", "Buskerud", "Finnmark",
									"Hedmark","Hordaland","M�re og Romsdal",
									"Nordland","Nord-Tr�ndelag","Oppland","Oslo","Rogaland",
									"Sogn og Fjordane","S�r-Tr�ndelag","Telemark",
									"Troms","Vest-Agder","Vestfold","�stfold"};
	private String sted;
	private final String[] steder = {"Sted 1","Sted 2","Sted 3"};
	private final String[] dager = {"1","2","3","4","5","6","7","8","9","10",
									"11","12","13","14","15","16","17","18","19",
									"20","21","22","23","24","25","26","27","28","29","30","31"};
	private final String[] m�neder= {"1","2","3","4","5","6","7","8","9","10","11","12"};
	//skal egentlig bruke stedsliste.getRegistrerteSteder() og stedsliste.getRegistrerteFylker()
	//som skal returnere en String[] med registrerte fylker, og en annen med steder

	public JPanel ByggPanel()
	{	//bygger GUI p� parameter panelet
		JPanel panelet = new JPanel();
		panelet.setLayout(new FlowLayout());
		
		//panel for alt utenom utksiftsfelt
		JPanel toppanel = new JPanel();
		toppanel.setLayout(new GridLayout(3,0));
		//dropdown for valg av fylke og sted
		JPanel stedpanel = new JPanel();
		stedpanel.add(new JLabel("Fylke:"));
		fylkeboks = new JComboBox(fylker);
		fylkeboks.addActionListener(this);
		stedpanel.add(fylkeboks);
		stedpanel.add(new JLabel("Sted:"));
		stedboks = new JComboBox(steder);
		stedboks.addActionListener(this);
		stedpanel.add(stedboks);
		toppanel.add(stedpanel);
		//innputfeltet for dato
		JPanel datopanel = new JPanel();
		datopanel.add(new JLabel("�r"));
		�rfelt = new JTextField(4);
		datopanel.add(�rfelt);
		datopanel.add(new JLabel("M�ned"));
		m�nedboks = new JComboBox(m�neder);
		datopanel.add(m�nedboks);
		datopanel.add(new JLabel("Dag"));
		dagboks = new JComboBox(dager);
		datopanel.add(dagboks);				
		toppanel.add(datopanel);
		//knapper
		JPanel knappepanel = new JPanel();
		visData = new JButton("Vis Data");
		visData.addActionListener(this);
		knappepanel.add(visData);
		toppanel.add(knappepanel);
		//legger til toppanelet
		panelet.add(toppanel);
		
		//utskriftsvindu
		utskrift = new JTextArea(20, 50);
		panelet.add(new JScrollPane(utskrift));
		panelet.setVisible(true);
		
		//Initialiserer listen med steder og data  (for�vring bare datalisten)
		dataliste = new DataListe();
		
		return panelet;
	}//end of byggPanel()
	
	public void melding(String m)
	{
		JOptionPane.showMessageDialog(null,m, "OBS!", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public boolean getStedVerdier()
	{
		try{
		fylke = (String) fylkeboks.getSelectedItem();
		sted = (String) stedboks.getSelectedItem();
		}catch(Exception e)
		{melding("det oppstod en feil med valg av fylke og sted");return false;}
		return true;
	}
	
	public boolean getDatoVerdier()
	{
		dag = Integer.parseInt((String) dagboks.getSelectedItem());
		m�ned =Integer.parseInt((String) m�nedboks.getSelectedItem());
		�r = Integer.parseInt(�rfelt.getText());
		if(dag <= 0 || dag > 31)
		{	melding("ugyldig dag");
			return false; 
		}
		if(m�ned == 0 || m�ned >12 || m�ned < 1)
		{	melding("ugyldig m�ned");
			return false;
		}
		if(�r < 1970)
		{	melding("ugyldig �rstall");
			return false;
		}
		if(�r > 3000)
		{	melding("Morsom eller.\n�r "+�r+" :P");
			return false;
		}
		//m� lage test p� registrering av datoer som ikke har v�rt enn�.
		return true;
	}//end of getDatoVerdier()
	
	

	public void actionPerformed(ActionEvent event) {
		
		
		if(�rfelt.getText().length() == 0 || �rfelt.getText().length() != 4)
		{
			JOptionPane.showMessageDialog(null, "Skriv inn i feltet �r igjen!");
			return;
		}
	
		
		if(event.getSource() == visData)
		{
			if( dataliste.tomListe() )
				utskrift.setText("ingen data i systemet!");
			else
				utskrift.setText(dataliste.skrivUtListe() );
		}
		if(event.getSource() == visData)
		{	
			try{
				if(!getStedVerdier())//henter valg fra sted og fylkesinput, returnerer false ved feil
					return;
				//henter dato input
				if(!getDatoVerdier())
					return;
				//lagrer dato som calendar objekt
				Calendar dato = Calendar.getInstance(); 
				dato.setTimeInMillis(0); //hadde v�rt lettere med Date(�r, m�ned, dato)
				dato.set(�r,m�ned-1,dag);/*m�ned-1 fordi Calendar.set() er teit*/
				Calendar n� = Calendar.getInstance();
				if(n�.before(dato))
				{
					melding("innskrevet dato har ikke intruffet enn�");
					return;
				}
				
			
			}
			catch(Exception ex){melding("Feil ved innsetting av data!");};
		}
	}//end of actionPerformed()
}//End of registrerData