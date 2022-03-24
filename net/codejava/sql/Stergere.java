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

public class Stergere {

	private JFrame frame;
	private JTextField Matricol;
	
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
	public static void Stg() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Stergere st = new Stergere();
					st.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Stergere() {
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
		
		JLabel lblNewLabel = new JLabel("Introduceti nr. matricol al studentului pentru a il sterge din baza de date:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel.setForeground(new Color(139, 0, 139));
		
		Matricol = new JTextField();
		Matricol.setColumns(10);
		
		JButton btnNewButton = new JButton("Sterge");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String URL = "jdbc:sqlserver://DESKTOP-ST670A5\\SQLEXPRESS;databaseName=tabele";
				String username = "sa";
				String password = "12345";
				String out = "";
				try 
				{
					Connection connection = DriverManager.getConnection(URL, username, password);
					Statement statement = connection.createStatement() ;
					statement.executeUpdate ("DELETE FROM Student WHERE Nr_Matricol = '"+Matricol.getText()+"'");
					JOptionPane.showMessageDialog(null,"Studentul a fost sters in baza de date.");
					statement.close();
					connection.close();
				} 
				catch (SQLException f) 
				{
					JOptionPane.showMessageDialog(null,f);
				}
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(109)
							.addComponent(Matricol, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(173)
							.addComponent(btnNewButton)))
					.addContainerGap(18, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(50)
					.addComponent(lblNewLabel)
					.addGap(18)
					.addComponent(Matricol, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(33)
					.addComponent(btnNewButton)
					.addContainerGap(89, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		
	}
}
