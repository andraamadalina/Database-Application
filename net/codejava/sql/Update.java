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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Update {

	private JFrame frame;
	private JTextField nrmat;
	private JTextField modifsp;
	
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
	public static void Up() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Update tab = new Update();
					tab.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Update() {
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
		panel.setBackground(new Color(232, 225, 239));
		
		JLabel lblIntroduceti = new JLabel("Nr. matricol al studentului ce va suferi modificari\r\n");
		lblIntroduceti.setForeground(new Color(128, 0, 128));
		lblIntroduceti.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		nrmat = new JTextField();
		nrmat.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Modificati specializarea aleasa");
		lblNewLabel.setForeground(new Color(128, 0, 128));
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		modifsp = new JTextField();
		modifsp.setColumns(10);
		
		JButton btnNewButton = new JButton("Modifica");
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
					statement.executeUpdate ("UPDATE Student SET Specializare = '"+modifsp.getText()+"'"
							+ " WHERE Nr_Matricol = '"+nrmat.getText()+"'");
					JOptionPane.showMessageDialog(null,"Datele studentului au fost modificate.");
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
							.addGap(136)
							.addComponent(nrmat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(20)
							.addComponent(lblIntroduceti))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(70)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(10)
									.addComponent(modifsp, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(168)
							.addComponent(btnNewButton)))
					.addContainerGap(20, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(22)
					.addComponent(lblIntroduceti)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(nrmat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(modifsp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addComponent(btnNewButton)
					.addContainerGap(46, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		
	}
}
