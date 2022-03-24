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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class JOIN1 {

	private JFrame frame;
	private JTextField cuvcheie;
	
	
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
					out = out+result.getString(i)+"	";
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
	public static void join1() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JOIN1 j1 = new JOIN1();
					j1.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JOIN1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 505, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(218, 197, 231));
		panel.setBounds(0, 0, 487, 253);
		frame.getContentPane().add(panel);
		
		cuvcheie = new JTextField();
		cuvcheie.setColumns(10);
		
		JButton btnNewButton = new JButton("Cauta");
		JTextArea textArea = new JTextArea();
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String cuv_cheie = cuvcheie.getText();
				switch(cuv_cheie) 
				{
				case "Arhitectura calculatoarelor":
					{
						//Selectati numele si prenumele studentilor care au materia Arhitectura calculatoarelor
						String query1 = citire("SELECT S.Nume, S.Prenume from [tabele].[dbo].[Student] S "
								+ "INNER JOIN Inrolare_Student I ON S.Nr_Matricol = I.Nr_Matricol "
								+ "WHERE I.Nume_Materie = 'Arhitectura calculatoarelor'",2);
						textArea.setText(query1);
						break;
					}
				case ">5":
					{
						//Afisati numele si prenumele studentilor cu note peste 5, cat si nota acestora 
						//la materia respectiva
						String query2 = citire("SELECT S.Nume, S.Prenume, C.Nota, M.Nume_Materie "
								+ "from [tabele].[dbo].[Student] S INNER JOIN Inrolare_Student I "
								+ "ON (S.Nr_Matricol = I.Nr_Matricol) INNER JOIN Materie M "
								+ "ON (I.ID_Materie = M.ID_Materie) INNER JOIN Catalog C "
								+ "ON (M.ID_Catalog = C.ID_Catalog) GROUP BY S.Nume, S.Prenume, "
								+ "C.Nota, M.Nume_Materie HAVING SUM(Nota) > 5",4);
						textArea.setText(query2);
						break;
					}
				case "334AB":
					{
						//In ce sala are grupa 334AB cursul de Modelare si simulare?
						String query3 = citire("SELECT I.Grupa, C.Sala_Curs "
								+ "from [tabele].[dbo].[Inrolare_Student] I INNER JOIN Materie M "
								+ "ON (I.ID_Materie = M.ID_Materie) INNER JOIN Curs C "
								+ "ON (M.ID_Materie = C.ID_Materie) WHERE I.Grupa = '334AB'",2);
						textArea.setText(query3);
						break;
					}
				case ">24":
					{
						//Afisati numele profesorilor de la curs pentru care suma orelor saptamanale 
						//desfasurate este > 24, lista profesorilor fiind ordonata descrescator dupa 
						//numarul orelor
						String query4 = citire("SELECT C.Nume_Profesor from [tabele].[dbo].[Curs] C "
								+ "INNER JOIN Cadru_Didactic CD ON C.Nume_Profesor =  CD.Nume_Profesor "
								+ "GROUP BY C.Nume_Profesor HAVING SUM(Nr_Ore) > 24 ORDER BY SUM(Nr_Ore)DESC",1);
						textArea.setText(query4);
						break;
					}
				case "Ion Andreea":
					{
						//Afisati notele studentei Ion Andreea
						String query5 = citire("SELECT S.Nume, S.Prenume, C.Nota "
								+ "from [tabele].[dbo].[Student] S INNER JOIN Inrolare_Student I "
								+ "ON (S.Nr_Matricol = I.Nr_Matricol) INNER JOIN Materie M "
								+ "ON (I.ID_Materie = M.ID_Materie) INNER JOIN Catalog C "
								+ "ON (M.ID_Catalog = C.ID_Catalog) WHERE Nume_Student = 'Ion Andreea'",3);
						textArea.setText(query5);
						break;
					}
				case "Modelare si simulare":
					{
						//Afisati din ce departament face parte profesorul de la cursul de Modelare si simulare
						String query6 = citire("SELECT C.Nume_Profesor, CD.Nume_Departament "
								+ "from [tabele].[dbo].[Materie] M INNER JOIN Curs C "
								+ "ON (M.ID_Materie = C.ID_Materie) INNER JOIN Cadru_Didactic CD "
								+ "ON (C.Nume_Profesor = CD.Nume_Profesor) "
								+ "WHERE Nume_Materie = 'Modelare si simulare'",3);
						textArea.setText(query6);
						break;
					}
				}
					
			}
		});
		
		JLabel lblCautatiCuvinteCheie = new JLabel("Cautati dupa cuvinte cheie!");
		lblCautatiCuvinteCheie.setForeground(new Color(186, 85, 211));
		lblCautatiCuvinteCheie.setFont(new Font("Times New Roman", Font.BOLD, 21));
		

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addGap(95)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(cuvcheie, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnNewButton))
						.addComponent(lblCautatiCuvinteCheie))
					.addContainerGap(103, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCautatiCuvinteCheie)
					.addPreferredGap(ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cuvcheie, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addGap(18)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
	}
}
