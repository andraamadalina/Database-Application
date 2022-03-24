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

public class InserareCat {

	private JFrame frame;
	private JTextField id;
	private JTextField nume;
	private JTextField nota;
	private JTextField data;
	
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
	public static void InsCat() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InserareCat nt = new InserareCat();
					nt.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InserareCat() {
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
		
		id = new JTextField();
		id.setColumns(10);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		nume = new JTextField();
		nume.setColumns(10);
		
		JLabel lblNumeStudent = new JLabel("Nume Student");
		lblNumeStudent.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		nota = new JTextField();
		nota.setColumns(10);
		
		JLabel lblNotadin = new JLabel("Nota (din 10)");
		lblNotadin.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		data = new JTextField();
		data.setColumns(10);
		
		JLabel lblData = new JLabel("Data");
		lblData.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		JButton btnNewButton = new JButton("Salvare nota");
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
					statement.executeUpdate ("INSERT INTO Catalog (ID_Catalog , Nume_Student , Nota , Data) VALUES "
							+ "('"+id.getText()+"' , '"+nume.getText()+"' , '"+nota.getText()+"' , '"+data.getText()+"')");
					JOptionPane.showMessageDialog(null,"Nota studentului a fost adaugata in catalog.");
					statement.close();
					connection.close();
				} 
				catch (SQLException g) 
				{
					JOptionPane.showMessageDialog(null,g);
				}
			}
		});
		
		JLabel lblNewLabel = new JLabel("Completati pentru a adauga nota studentului");
		lblNewLabel.setForeground(new Color(153, 50, 204));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(29)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNumeStudent)
								.addComponent(lblNotadin)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(64)
							.addComponent(lblData))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(70)
							.addComponent(lblId)))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(nota, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(nume, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
									.addComponent(btnNewButton)
									.addGap(33))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(data, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addContainerGap(182, Short.MAX_VALUE))))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(17)
							.addComponent(id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(183, Short.MAX_VALUE))))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(21)
					.addComponent(lblNewLabel)
					.addContainerGap(355, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(30)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblId)
						.addComponent(id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNumeStudent)
						.addComponent(nume, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNotadin)
						.addComponent(nota, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblData)
						.addComponent(data, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(52, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(97)
					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(106))
		);
		panel.setLayout(gl_panel);
		
		
		
	}
}
