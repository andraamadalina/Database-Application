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

public class StergereCat {

	private JFrame frame;
	private JTextField Matricol;
	private JTextField nume;
	
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
	public static void Stcat() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StergereCat str = new StergereCat();
					str.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StergereCat() {
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
		
		JLabel lblNewLabel = new JLabel("Introduceti numele studentului:");
		lblNewLabel.setForeground(new Color(128, 0, 128));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 21));
		
		nume = new JTextField();
		nume.setColumns(10);
		
		JButton btnNewButton = new JButton("Stergeti datele studentului");
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
					statement.executeUpdate ("DELETE FROM Catalog WHERE Nume_Student = '"+nume.getText()+"'");
					JOptionPane.showMessageDialog(null,"Datele studentului au fost sterse din catalog.");
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
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(156)
					.addComponent(nume, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(160, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(87, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addGap(69))
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addGap(111)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(120, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(30)
					.addComponent(lblNewLabel)
					.addGap(27)
					.addComponent(nume, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(39)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(58, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
				
		
	}
}
