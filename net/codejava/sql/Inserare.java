package net.codejava.sql;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Inserare {

	private JFrame frame;
	private JTextField textField;
	private JTextField NrMatricol;
	private JTextField Nume;
	private JTextField Prenume;
	private JTextField Cnp;
	private JLabel lblDataNasterii;
	private JTextField DataNasterii;
	private JLabel lblAdresa;
	private JTextField Adresa;
	private JLabel lblOras;
	private JTextField Oras;
	private JLabel lblJudet;
	private JTextField Judet;
	private JLabel lblFormaDeFinantare;
	private JTextField Finantare;
	private JLabel lblSpecializare;
	private JTextField Specializare;
	private JButton btnNewButton;
	private JComboBox Sex;
	
	public String citire(String comanda, int nr)
	{
		String URL = "jdbc:sqlserver://DESKTOP-ST670A5\\SQLEXPRESS;databaseName=tabele";
		String username = "sa";
		String password = "12345";
		String out = "";
		
		try 
		{
			Connection connection = DriverManager.getConnection(URL, username, password);
			Statement statement = connection.createStatement() ;
			ResultSet result = statement.executeQuery(comanda);
			int i;
			while (result.next()) 
			{
				for(i = 1; i <= nr; i++)
				{
					out = out+result.getString(i)+" ";
				}
				out=out+"\n";
			}
			connection.close();
			
		} 
		catch (SQLException e) 
		{
			System.out.println("Eroare!");
			e.printStackTrace();
		}
		return out;
	}
	
	/**
	 * Launch the application.
	 */
	public static void Stud() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inserare pag = new Inserare();
					pag.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Inserare() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(218, 197, 231));
		panel.setBounds(0, 0, 432, 253);
		frame.getContentPane().add(panel);
		
		textField = new JTextField("Adaugare student nou");
		textField.setForeground(new Color(148, 0, 211));
		textField.setFont(new Font("Times New Roman", Font.BOLD, 17));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setColumns(10);
		
		NrMatricol = new JTextField();
		NrMatricol.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nr. Matricol");
		
		JLabel lblNume = new JLabel("Nume");
		
		Nume = new JTextField();
		Nume.setColumns(10);
		
		JLabel lblPrenume = new JLabel("Prenume");
		
		Prenume = new JTextField();
		Prenume.setColumns(10);
		
		JLabel lblCnp = new JLabel("CNP");
		
		Cnp = new JTextField();
		Cnp.setColumns(10);
		
		JLabel lblSex = new JLabel("Sex");
		
		JList list = new JList();
		
		lblDataNasterii = new JLabel("Data Nasterii");
		
		DataNasterii = new JTextField();
		DataNasterii.setColumns(10);
		
		lblAdresa = new JLabel("Adresa");
		
		Adresa = new JTextField();
		Adresa.setColumns(10);
		
		lblOras = new JLabel("Oras");
		
		Oras = new JTextField();
		Oras.setColumns(10);
		
		lblJudet = new JLabel("Judet");
		
		Judet = new JTextField();
		Judet.setColumns(10);
		
		lblFormaDeFinantare = new JLabel("Forma De Finantare");
		
		Finantare = new JTextField();
		Finantare.setColumns(10);
		
		lblSpecializare = new JLabel("Specializare");
		
		Specializare = new JTextField();
		Specializare.setColumns(10);
		
		btnNewButton = new JButton("Adaugare");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				String URL = "jdbc:sqlserver://DESKTOP-ST670A5\\SQLEXPRESS;databaseName=tabele";
				String username = "sa";
				String password = "12345";
				String out = "";
				try 
				{
					Connection connection = DriverManager.getConnection(URL, username, password);
					Statement statement = connection.createStatement() ;
					statement.executeUpdate ("INSERT INTO Student (Nr_Matricol , Nume , Prenume , CNP , Adresa , Oras , Judet , "
							+ "Sex , Data_Nasterii , Forma_De_Finantare , Specializare) VALUES ('"+NrMatricol.getText()+"' , "
									+ "'"+Nume.getText()+"' , '"+Prenume.getText()+"' , '"+Cnp.getText()+"' , "
											+ "'"+Adresa.getText()+"' , '"+Oras.getText()+"' , '"+Judet.getText()+"' , "
													+ "'"+Sex.getSelectedItem()+"' , '"+DataNasterii.getText()+"' , "
															+ "'"+Finantare.getText()+"' , '"+Specializare.getText()+"')");
					JOptionPane.showMessageDialog(null,"Studentul a fost adaugat cu succes in baza de date.");
					statement.close();
					connection.close();
				} 
				catch (SQLException e) 
				{
					JOptionPane.showMessageDialog(null,e);
				}
					
			}
		});
		
		Sex = new JComboBox();
		Sex.setModel(new DefaultComboBoxModel(new String[] {"M", "F"}));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblFormaDeFinantare)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(Finantare, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblSpecializare)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(Specializare, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblAdresa, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(Adresa, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblOras, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(Oras, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblJudet, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(Judet, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(102)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(NrMatricol, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNume)
								.addComponent(lblCnp, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(Cnp, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
									.addGap(36)
									.addComponent(lblDataNasterii, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(DataNasterii, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(Nume, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblPrenume)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(Prenume, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblSex, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(Sex, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
					.addGap(31))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(166)
					.addComponent(btnNewButton)
					.addContainerGap(182, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(textField, GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
					.addGap(2))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(textField, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
					.addGap(10)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(NrMatricol, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNume)
						.addComponent(Nume, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPrenume)
						.addComponent(Prenume, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSex)
						.addComponent(Sex, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCnp)
								.addComponent(Cnp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblDataNasterii)
							.addComponent(DataNasterii, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAdresa)
						.addComponent(Adresa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblOras)
						.addComponent(Oras, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblJudet)
						.addComponent(Judet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFormaDeFinantare)
						.addComponent(Finantare, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSpecializare)
						.addComponent(Specializare, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnNewButton)
					.addGap(8))
		);
		panel.setLayout(gl_panel);
		
	}
}
