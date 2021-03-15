package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import entity.Utilisateur;
import global.variableGlobal;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Main2 extends variableGlobal {

	private JFrame frame;
	private JMenuBar menuBar;
	private JButton RendezVousButton;
	private JButton MedicButton;
	private JButton NewMedButton;
	private JButton profilBtn;
	private JButton deconnexionButton;
	private JButton UserButton;
	private JButton modificationButton;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main2 window = new Main2(new JPanel());
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
	public Main2(JPanel panel_1) {
		initialize(panel_1);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(JPanel panel_1) {
		frame = new JFrame();
		frame.setBackground(new Color(72, 209, 204));
		frame.setBounds(100, 100, 859, 648);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		panel_1.setBounds(5, 11, 800, 763);
		frame.getContentPane().add(panel_1);
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		RendezVousButton = new JButton("Rendez-Vous");
		RendezVousButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("check user");
				Utilisateur user = getUser();
				String roleUser = user.getRole_user();
				if(roleUser.equals("admin") || roleUser.equals("medecin")) {
					frame.setVisible(false);
					Main2 window;
					try {
						window = new Main2(new RdvView());
						window.frame.setVisible(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		
		MedicButton = new JButton("M\u00E9dicament");
		MedicButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("check user");
				Utilisateur user = getUser();
				String roleUser = user.getRole_user();
				if(roleUser.equals("admin") || roleUser.equals("gestion")) {
					try {
						frame.setVisible(false);
						Main2 window = new Main2(new MedicamentTable());
						window.frame.setVisible(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		
		NewMedButton = new JButton("Nouveau M\u00E9dicament");
		NewMedButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("check user");
				Utilisateur user = getUser();
				String roleUser = user.getRole_user();
				if(roleUser.equals("admin") || roleUser.equals("gestion")) {
					frame.setVisible(false);
					Main2 window = new Main2(new MedicamentNew());
					window.frame.setVisible(true);
				}
			}
		});
		
		
		profilBtn = new JButton("Profils");
		profilBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("check user");
				Utilisateur user = getUser();
				String roleUser = user.getRole_user();
				if(roleUser.equals("admin") ) {

					System.out.println("admin");
				
					frame.setVisible(false);
					Main2 window;
					try {
						window = new Main2(new newProfil());
						window.frame.setVisible(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		
		
		deconnexionButton = new JButton("D\u00E9connexion");
		deconnexionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				setUser(null);
				Connexion window = new Connexion();
				window.setVisible(true);
				
			}
		});
		
		
		UserButton = new JButton("ShowUser");
		UserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("check user");
				Utilisateur user = getUser();
				String roleUser = user.getRole_user();
				System.out.println(getUser());
				
			}
		});
		
		menuBar.add(RendezVousButton);
		menuBar.add(deconnexionButton);
		menuBar.add(profilBtn);
		menuBar.add(UserButton);
		menuBar.add(NewMedButton);
		menuBar.add(MedicButton);
		
		modificationButton = new JButton("Modification");
		modificationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Main2 window;
				window = new Main2(new Modification(getUser()));
				window.frame.setVisible(true);
			}
		});
		menuBar.add(modificationButton);
		//menuBar.removeAll();
		
	}
	

}
