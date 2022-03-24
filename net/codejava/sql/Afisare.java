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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Afisare {

	private JFrame frame;
	private JTextField txtAfisareaStudentilor;
	private JTextField textField;
	
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
	public static void PagNoua() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Afisare window = new Afisare();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Afisare() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 481, 349);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(218, 197, 231));
		panel.setBounds(0, 0, 539, 302);
		frame.getContentPane().add(panel);
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(new Color(246, 241, 249));
		String salvare = citire("SELECT Nr_Matricol, Nume, Prenume, Specializare from [tabele].[dbo].[Student]",4);
		textArea.setText(salvare);
		
		txtAfisareaStudentilor = new JTextField();
		txtAfisareaStudentilor.setForeground(new Color(153, 50, 204));
		txtAfisareaStudentilor.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txtAfisareaStudentilor.setBackground(new Color(255, 255, 255));
		txtAfisareaStudentilor.setText("Afisarea studentilor");
		txtAfisareaStudentilor.setColumns(10);
		
		textField = new JTextField("Nr. Matricol	Nume	Prenume	Specilizarea aleasa");
		textField.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(128)
							.addComponent(txtAfisareaStudentilor, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
								.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE))))
					.addGap(85))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(34)
					.addComponent(txtAfisareaStudentilor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(2)
					.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
	}
}
