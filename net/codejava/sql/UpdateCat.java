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

public class UpdateCat {

	private JFrame frame;
	private JTextField nume;
	private JTextField notanoua;
	
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
	public static void Upda() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateCat up = new UpdateCat();
					up.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UpdateCat() {
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
		
		JLabel lblNewLabel = new JLabel("Numele studentului");
		lblNewLabel.setForeground(new Color(153, 50, 204));
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		nume = new JTextField();
		nume.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Introduceti noua nota a studentului");
		lblNewLabel_1.setForeground(new Color(153, 50, 204));
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		notanoua = new JTextField();
		notanoua.setColumns(10);
		
		JButton btnModifica = new JButton("Modifica");
		btnModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String URL = "jdbc:sqlserver://DESKTOP-ST670A5\\SQLEXPRESS;databaseName=tabele";
				String username = "sa";
				String password = "12345";
				String out = "";
				try 
				{
					Connection connection = DriverManager.getConnection(URL, username, password);
					Statement statement = connection.createStatement() ;
					statement.executeUpdate ("UPDATE Catalog SET Nota = '"+notanoua.getText()+"'"
							+ " WHERE Nume_Student = '"+nume.getText()+"'");
					JOptionPane.showMessageDialog(null,"Nota studentului a fost modificata.");
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
							.addContainerGap()
							.addComponent(lblNewLabel)
							.addGap(33)
							.addComponent(nume, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(165)
							.addComponent(btnModifica))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_1)
							.addGap(18)
							.addComponent(notanoua, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(34, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(65)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(nume, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(notanoua, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(38)
					.addComponent(btnModifica)
					.addGap(56))
		);
		panel.setLayout(gl_panel);
		
		
	}
}
