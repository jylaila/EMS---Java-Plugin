package ems.royal.view;

import java.awt.*;
import java.awt.color.CMMException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ems.management.Configuration;
import ems.management.ErrorList;
import ems.management.FileManagement;
import ems.royal.model.royalManagement;
import ems.royal.model.royalWriter;
import ems.sepia.view.SepiaWriter;
import compiler.royal.main.*;

public class RoyalManagement {

	public static JFrame frame;
	private JList<String> jlErrors;
	private JList<String> jlFiles;
	private DefaultListModel<String> errorModel = new DefaultListModel<String>();
	public DefaultListModel<String> filesModel = new DefaultListModel<String>();

	FileManagement objDir = new FileManagement();
	ErrorList errorList = new ErrorList();
	royalManagement objexl = new royalManagement();
	RoyalWriter frEdit = new RoyalWriter();
	Main compiler = new Main();
	royalWriter objwriter = new royalWriter();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RoyalManagement window = new RoyalManagement();
					window.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws FileNotFoundException
	 */
	public RoyalManagement() throws FileNotFoundException {

		File file = new File(System.getProperty("user.dir")
				+ "/data/Configuration.conf");
		if (file.exists()) {

			initialize();
			
		} else {
			Configuration frEdit = new Configuration();
			
			frEdit.frame.setVisible(true);
			
		}
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws FileNotFoundException
	 */
	private void initialize() throws FileNotFoundException {

		frame = new JFrame("RoyalManagement");
		frame.setBounds(100, 100, 716, 431);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 700, 393);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		// DefaultListModel lmResumo = new DefaultListModel();
		jlErrors = new JList<String>(errorModel);
		jlErrors.setBounds(10, 36, 300, 293);
		jlErrors.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jlErrors.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		jlErrors.setSelectedIndex(0);
		jlErrors.setLayoutOrientation(JList.VERTICAL);
		panel.add(jlErrors);

		JLabel lblParameters = new JLabel("Files");
		lblParameters.setBounds(341, 14, 155, 14);
		panel.add(lblParameters);

		jlFiles = new JList<String>(filesModel);
		jlFiles.setBounds(341, 42, 349, 287);
		panel.add(jlFiles);

		// JScrollPane pnResumo = new JScrollPane(jlErrors);
		// pnResumo.setBounds(10, 36, 300, 209);
		// pnResumo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		// panel.add(pnResumo);

		JLabel lblGerenciarMensagens = new JLabel("Errors");
		lblGerenciarMensagens.setBounds(10, 11, 266, 14);
		panel.add(lblGerenciarMensagens);

		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(407, 356, 89, 23);
		panel.add(btnEdit);

		JButton btnAddNew = new JButton("Add New");
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RoyalWriter frEdit = new RoyalWriter();
				frEdit.frame.setVisible(true);
			}
		});
		btnAddNew.setBounds(502, 356, 89, 23);
		panel.add(btnAddNew);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnExit.setBounds(601, 356, 89, 23);
		panel.add(btnExit);

		// carrega jlist
		errorList();

		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (jlFiles.getSelectedIndex() > -1) {
					try {

						String returnPath = objexl.FindFolder(jlErrors
								.getSelectedValue(), jlFiles.getSelectedValue()
								.toString());

						compiler.main(returnPath, jlErrors.getSelectedValue(),
								jlFiles.getSelectedValue().toString());

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else
					JOptionPane.showMessageDialog(frame,
							"You must to select a File");

			}
		});

		jlErrors.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				loadFiles();
			}
		});

	}

	private void setValues(ArrayList<String> content) {
		int indice = content.get(0).indexOf("=") + 2;
		String errorName = jlErrors.getSelectedValue();

		frEdit.chckbxUseThisMessage.setSelected(Boolean.valueOf(content.get(0)
				.substring(indice, content.get(0).length())));

		indice = content.get(1).indexOf(":=") + 2;
		frEdit.txtLanguage.setText(content.get(1).substring(indice,
				content.get(1).length()));

		indice = content.get(2).indexOf(":=") + 2;
		frEdit.txtlanguageinEnglish.setText(content.get(2).substring(indice,
				content.get(2).length()));

		indice = content.get(3).indexOf(":=") + 2;
		frEdit.txtErrorDescription.setText(content.get(3).substring(indice,
				content.get(3).length()));

		indice = content.get(4).indexOf(":=") + 2;
		frEdit.txtExplanation.setText(content.get(4).substring(indice,
				content.get(4).length()));

		frEdit.cmbErrorList.setSelectedItem(errorName);
		frEdit.cmbErrorList.setEnabled(false);

		frEdit.btnSave.setVisible(false);
		frEdit.btnUpdate.setVisible(true);
		frEdit.btnDelete.setVisible(true);

		if (content.size() == 5) {
			indice = content.get(5).indexOf(":=") + 2;
			frEdit.txtSource.setText(content.get(5).substring(indice,
					content.get(5).length()));
		} else {
			for (int i = 5; i < content.size(); i++) {
				indice = content.get(i).indexOf(":=") + 2;
				frEdit.lista.addElement(content.get(i).substring(indice,
						content.get(i).length()));

			}
		}

	}

	private void errorList() {
		// Lista nome dos erros referente as pastas
		String[] list;
		String name = null;

		// Lista as pastas com nome do erro
		list = FileManagement.listDirectory(objDir.DefaultPath());

		for (int i = 0; i < list.length; i++) {
			name = errorList.returnerrorName(list[i].toString());
			if (name != null) {

				// Procura por arquivos dentro do diretório
				for (String path : objDir.FilePath(list[i].toString()).list()) {

					if (path.endsWith(".royal")) {
						errorModel.addElement(name);
						break;
					}
				}

			}
		}

		if (errorModel.size() >= 0) {
			// btnEdit.setEnabled(true);
			// btnDeleteError.setEnabled(true);
		}
	}

	public void loadFiles() {
		// Lista nome dos erros referente as pastas

		String folderName = null;
		String[] fileName;

		filesModel.removeAllElements();

		folderName = errorList.returnPath(jlErrors.getSelectedValue()
				.toString());

		// Procura os arquivos dentro do diretório
		for (String file : objDir.FilePath(folderName).list()) {

			if (file.endsWith(".royal")) {

				fileName = file.split(".royal");
				filesModel.addElement(fileName[0].toString());

			}
		}

	}
}