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

public class JOIN2 {

	private JFrame frame;
	private JTextField cheie;
	
	
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
	public static void join2() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JOIN2 j2 = new JOIN2();
					j2.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JOIN2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 532, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(218, 197, 231));
		panel.setBounds(0, 0, 514, 253);
		frame.getContentPane().add(panel);
		
		JTextArea textArea = new JTextArea();
		
		cheie = new JTextField();
		cheie.setColumns(10);
		
		JButton btnNewButton = new JButton("Cauta");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cuvcheie = cheie.getText();
				switch(cuvcheie) 
				{
				case "studenti ordonati desc dupa nota":
					{
						//Studentii vor fi ordinati descrescator in urma notelor obtinute trecute in catalog
						String query1 = citire("SELECT S.Nume + S.Prenume AS Nume, J.Nota from [tabele].[dbo].[Student] S "
								+ "INNER JOIN (SELECT S.Nr_Matricol, C.Nota from Catalog C INNER JOIN Materie M "
								+ "ON M.ID_Catalog = C.ID_Catalog INNER JOIN Inrolare_Student I ON I.ID_Materie = M.ID_Materie "
								+ "INNER JOIN Student S ON S.Nr_Matricol = I.Nr_Matricol) J ON J.Nr_Matricol = S.Nr_Matricol "
								+ "ORDER BY J.Nota DESC",2);
						textArea.setText(query1);
						break;
					}
				case "specializare cu cele mai multe puncte credit":
					{
						//La ce specializare e materia cu cele mai multe puncte credit?
						String query2 = citire("SELECT I.Specializare, M.Nume_Materie, M.Puncte_Credit "
								+ "from [tabele].[dbo].[Inrolare_Student] I, [tabele].[dbo].[Materie] M "
								+ "WHERE M.Puncte_Credit = (SELECT MAX(M.Puncte_Credit) from Materie M) "
								+ "and M.ID_Materie = I.ID_Materie",3);
						textArea.setText(query2);
						break;
					}
				case "studenti cu nota pe data cea mai recenta":
					{
						//Ce studenti au primit nota pe data cea mai recenta?
						String query3 = citire("SELECT C.Data, S.Nume, S.Prenume from [tabele].[dbo].[Catalog] C,"
								+ "[tabele].[dbo].[Student] S,[tabele].[dbo].[Materie] M ,[tabele].[dbo].[Inrolare_Student] I "
								+ "WHERE C.Data = (SELECT MAX(C.Data) FROM Catalog C) and C.ID_Catalog = M.ID_Catalog "
								+ "and M.ID_Materie = I.ID_Materie and I.Nr_Matricol = S.Nr_Matricol",3);
						textArea.setText(query3);
						break;
					}
				case "media notelor pe specializari":
					{
						//Media notelor studentilor pe fiecare specializare
						String query4 = citire("SELECT I.Specializare, AVG(C.Nota) AS Medie from [tabele].[dbo].[Inrolare_Student] I "
								+ "INNER JOIN Materie M ON I.ID_Materie = M.ID_Materie INNER JOIN Catalog C "
								+ "ON M.ID_Catalog = C.ID_Catalog GROUP BY I.Specializare ORDER BY AVG(C.Nota) DESC",2);
						textArea.setText(query4);
						break;
					}
				}
			}
		});
		
		JLabel lblNewLabel = new JLabel("Cautati dupa cuvinte cheie!");
		lblNewLabel.setForeground(new Color(186, 85, 211));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 21));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(113)
					.addComponent(cheie, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnNewButton)
					.addGap(60))
				.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(166, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addGap(153))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(20)
					.addComponent(lblNewLabel)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(cheie, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
				
	}
}
