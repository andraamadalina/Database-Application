package net.codejava.sql;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import java.awt.CardLayout;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Proiect extends JFrame {
	
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
	

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Proiect frame = new Proiect();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public Proiect() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 479, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("Studenti");
		menuBar.add(menu);
		
		JButton btnNewButton_1 = new JButton("Inserare");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inserare std = new Inserare();
				std.Stud();
			}
		});
		JButton btnNewButton = new JButton("Afisare");
		menu.add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				Afisare afs = new Afisare();
				afs.PagNoua();
				
			}
		});
		menu.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Update");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Update up =  new Update();
				up.Up();
			}
		});
		menu.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Stergere");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Stergere stg =  new Stergere();
				stg.Stg();
			}
		});
		menu.add(btnNewButton_3);
		
		JMenu mnCatalog = new JMenu("Catalog");
		menuBar.add(mnCatalog);
		
		JButton btnNewButton_4 = new JButton("Afisare");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AfisareCat catalog = new AfisareCat();
				catalog.PagCat();
			}
		});
		mnCatalog.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Inserare");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InserareCat not = new InserareCat();
				not.InsCat();
			}
		});
		mnCatalog.add(btnNewButton_5);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateCat upcat =  new UpdateCat();
				upcat.Upda();
			}
		});
		mnCatalog.add(btnUpdate);
		
		JButton btnStergere = new JButton("Stergere");
		btnStergere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StergereCat stnota =  new StergereCat();
				stnota.Stcat();
			}
		});
		mnCatalog.add(btnStergere);
		
		JMenu mnMaterii = new JMenu("Materii");
		menuBar.add(mnMaterii);
		
		JMenu mnCadreDidactice = new JMenu("Cadre Didactice");
		menuBar.add(mnCadreDidactice);
		
		JMenu mnDepartamente = new JMenu("Departamente");
		menuBar.add(mnDepartamente);
		
		//contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{panel, textArea, btnNewButton}));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(218, 197, 231));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(218, 197, 231));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(218, 197, 231));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
		);
		panel_1.setLayout(null);
		
		//JLabel lblNewLabel = new JLabel("New label");
		JLabel label = new JLabel("");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(171,0,253,217);
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(this.getClass().getResource("/FAC_Andra.png")).getImage().getScaledInstance(170, 100, Image.SCALE_DEFAULT));
        label.setIcon(imageIcon);
        panel_1.add(label);
        
        JButton btnNewButton_6 = new JButton("Cautare simpla");
        btnNewButton_6.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JOIN1 j1 =  new JOIN1();
				j1.join1();
        		
        	}
        });
        btnNewButton_6.setBounds(24, 73, 147, 25);
        panel_1.add(btnNewButton_6);
        
        JButton btnCautareComplexa = new JButton("Cautare complexa");
        btnCautareComplexa.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		JOIN2 j2 =  new JOIN2();
				j2.join2();
        	}
        });
        btnCautareComplexa.setBounds(24, 98, 147, 25);
        panel_1.add(btnCautareComplexa);
		//lblNewLabel.setIcon(new ImageIcon(Proiect.class.getResource("/FAC_Andra.png")));
		//lblNewLabel.setBounds(0, 0, 328, 145);
		//panel_1.add(lblNewLabel);
		panel.setLayout(gl_panel);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
