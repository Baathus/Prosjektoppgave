/*
 * Skrevet av Thomas Nordegnen den 11 april 2011
 */



import java.awt.*;
	
	import java.awt.event.*;
import javax.swing.*;
	
public class StedListeTest extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
		private JTextArea utskrift;
		private JButton skrivut;
		private JButton leggtilny;
		private JTextField sted;
		private JTextField fylke;
		
		private String fylke2;
		private String sted2;
		private StedListe stedliste;
		private Sted nytsted;
		
		public StedListeTest()
		{
			super("StedListeTest");
			Container c = getContentPane();
			c.setLayout(new FlowLayout());
			
			c.add(new JLabel("fylke"));
			fylke = new JTextField(15);
			c.add(fylke);
			c.add(new JLabel("sted"));
			sted = new JTextField(15);
			c.add(sted);

			leggtilny = new JButton("Registrer Nytt Fylke");
			leggtilny.addActionListener(this);
			c.add(leggtilny);
			
			skrivut = new JButton("skriv ut");
			skrivut.addActionListener(this);
			c.add(skrivut);

			utskrift = new JTextArea(40, 40);
			c.add(new JScrollPane(utskrift));

			setSize(500, 500);
			setVisible(true);
			
			stedliste = new StedListe();
		}
		
		public void melding(String m)
		{
			JOptionPane.showMessageDialog(null, m);
		}
		
		public boolean getFylke()
		{
			try
			{
				fylke2 = fylke.getText(); 
				
			}
			catch(Exception e)
			{
				melding ("ugyldig fylkesverdi!");
				return false;
			}
			try
			{
				sted2 = sted.getText();
			}
			catch(Exception e)
			{
				melding ("Ugyldig stedsverdi!");
			}
			return true;	
		}
		

		public void actionPerformed(ActionEvent event) 
		{
			if(event.getSource() == skrivut)
			{
				if( stedliste.tomListe())
					utskrift.setText("ingen data i systemet!");
				else
					utskrift.setText(stedliste.toString() );
			}
			if(event.getSource() == leggtilny)
			{	
				try
				{
					//lager en ny node med dataen
					nytsted = new Sted(sted2, fylke2);
					stedliste.setInnFylke(nytsted);
				}	
				catch(Exception ex)
				{
					melding("Feil ved innsetting av nytt sted!");
				}	
			}
		}

		public static void main( String[] args )
		{
			StedListeTest vindu = new StedListeTest();
		    vindu.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		}
	}
