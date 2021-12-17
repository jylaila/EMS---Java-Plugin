package ems.management;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class Configuration {

	public static JFrame frame;
	private JTextField txtFolder;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Configuration window = new Configuration();
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
	public Configuration() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame("EMS - Preferences");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblFolder = new JLabel("Default Folder");
		lblFolder.setBounds(10, 11, 146, 25);
		frame.getContentPane().add(lblFolder);

		txtFolder = new JTextField();
		txtFolder.setBounds(10, 38, 375, 20);
		frame.getContentPane().add(txtFolder);
		txtFolder.setColumns(10);

		txtFolder.setText(CheckFile());
		if (txtFolder.equals("")) {
			txtFolder.setText( "../data/");
		}

		JButton btnChooseFile = new JButton("Choose File");
		btnChooseFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser abrir = new JFileChooser();
				abrir.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int retorno = abrir.showOpenDialog(null);
				if (retorno == JFileChooser.APPROVE_OPTION)
					txtFolder.setText(abrir.getSelectedFile().getPath());
			}
		});

		btnChooseFile.setBounds(395, 37, 29, 23);
		frame.getContentPane().add(btnChooseFile);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (txtFolder.getText().equals("")) {
					Warning();

				} else {
					Save();
					txtFolder.setText(null);

				}
			}
		});

		btnSave.setBounds(242, 228, 89, 23);
		frame.getContentPane().add(btnSave);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnExit.setBounds(335, 228, 89, 23);
		frame.getContentPane().add(btnExit);

	}

	public void Save() {
		File diretorio = new File(System.getProperty("user.dir") + "/data/");

		if (!diretorio.exists()) {

			diretorio.mkdirs();
		} else {

			FileManagement fileman = new FileManagement();
			fileman.DeleteFile(System.getProperty("user.dir")
					+ "/data/Configuration.conf");
		}

		File filename = new File(diretorio + "/" + "Configuration.conf");

		FileWriter x;
		try {
			x = new FileWriter(filename, true);
			x.write(txtFolder.getText() + "/");
			x.close();
			Confirm();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			Error();
			e.printStackTrace();

		}

	}

	public static void Confirm() {
		Icon icon = new ImageIcon("icons/success.png");
		JLabel Sucess = new JLabel("Data updated successfully!");
		Sucess.setBounds(10, 150, 414, 40);
		frame.getContentPane().add(Sucess);
		Sucess.setBorder(BorderFactory.createLineBorder(new java.awt.Color(81,
				135, 50)));
		Sucess.setOpaque(true); // Deixa a label opaca.
		Sucess.setIcon(icon);
		Sucess.setForeground(new java.awt.Color(81, 135, 50));
		Sucess.setBackground(new java.awt.Color(231, 251, 217));

	}

	public static void Error() {
		Icon icon = new ImageIcon("icons/error.png");
		JLabel Sucess = new JLabel("There was an error. Please try again!");
		Sucess.setBounds(10, 150, 414, 40);
		frame.getContentPane().add(Sucess);
		Sucess.setBorder(BorderFactory.createLineBorder(new java.awt.Color(188,
				30, 86)));
		Sucess.setOpaque(true);
		Sucess.setIcon(icon);
		Sucess.setForeground(new java.awt.Color(188, 30, 86));
		Sucess.setBackground(new java.awt.Color(246, 179, 190));

	}

	public static void Warning() {
		Icon icon = new ImageIcon("icons/warning.png");
		JLabel Sucess = new JLabel("All fields are required.");
		Sucess.setBounds(10, 150, 414, 40);
		frame.getContentPane().add(Sucess);
		Sucess.setBorder(BorderFactory.createLineBorder(new java.awt.Color(198,
				198, 60)));
		Sucess.setOpaque(true);
		Sucess.setIcon(icon);
		Sucess.setForeground(new java.awt.Color(198, 198, 60));
		Sucess.setBackground(new java.awt.Color(255, 255, 204));

	}

	public String CheckFile() {

		FileManagement fileman = new FileManagement();

		String folder = null;
		File file = new File(System.getProperty("user.dir")
				+ "/data/Configuration.conf");
		if (file.exists()) {
			try {
				ArrayList<String> directory = fileman.ReadFile(file);
				folder = directory.get(0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return folder;

	}

}
