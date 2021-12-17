package ems.sepia.view;

import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;



import compiler.sepia.main.Main;

import ems.management.ErrorList;
import ems.management.FileManagement;
import ems.royal.view.RoyalWriter;
import ems.sepia.model.sepiaManagement;
import ems.sepia.model.sepiaWriter;


public class SepiaManagement {

	public JFrame frame;
	private JList<String> jlErrors;
	private JList<String> jlFiles;
	private JButton btnEdit ;
	private DefaultListModel<String> errorModel = new DefaultListModel<String>();
	public DefaultListModel<String> filesModel = new DefaultListModel<String>();

	FileManagement objDir = new FileManagement();
	ErrorList errorList = new ErrorList();
	SepiaWriter frEdit = new SepiaWriter();
	sepiaWriter objSepia = new sepiaWriter();
	sepiaManagement objsepia = new sepiaManagement();
	Main compiler = new Main();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SepiaManagement window = new SepiaManagement();
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
	public SepiaManagement() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("SepiaManagement");
		frame.setBounds(100, 100, 716, 431);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
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

		btnEdit = new JButton("Edit");
		btnEdit.setBounds(407, 356, 89, 23);
		panel.add(btnEdit);

		JButton btnAddNew = new JButton("Add New");
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
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
					
						String returnPath = objsepia.FindFolder(jlErrors
										.getSelectedValue(), jlFiles.getSelectedValue()
										.toString());
								
						
						compiler.main(returnPath, jlErrors.getSelectedValue(),jlFiles.getSelectedValue()
								.toString());		
					
						
				
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

//	private void setValues(ArrayList<String> content) {
//		int indice = content.get(0).indexOf(":=") + 3;
//		String errorName = jlErrors.getSelectedValue();
//		
//		frEdit.chckbxUseThisError.setSelected(Boolean.valueOf(content.get(0).substring(indice,
//				content.get(0).length())));
//			
//		indice = content.get(1).indexOf(":=") + 2;
//		frEdit.txtExplanation.setText(content.get(1).substring(indice,
//				content.get(1).length()));
//		
//		indice = content.get(2).indexOf(":=") + 2;
//		frEdit.txtCauselabel.setText(content.get(2).substring(indice,
//				content.get(2).length()));
//
//		indice = content.get(3).indexOf(":=") + 2;
//		frEdit.txtCause.setText(content.get(3).substring(indice,
//				content.get(3).length()));
//
//		frEdit.cmbErrorList.setSelectedItem(errorName);
//		frEdit.cmbErrorList.setEnabled(false);
//		frEdit.btnSave.setVisible(false);		
//		frEdit.btnUpdate.setVisible(true);
//		frEdit.btnDelete.setVisible(true);
//
//		if (content.size() == 4) {
//			indice = content.get(4).indexOf(":=") + 2;
//			frEdit.txtSource.setText(content.get(4).substring(indice,
//					content.get(4).length()));
//		} else {
//			for (int i = 4; i < content.size(); i++) {
//				indice = content.get(i).indexOf(":=") + 2;
//				frEdit.lista.addElement(content.get(i).substring(indice,
//						content.get(i).length()));
//
//			}
//		}
//
//	}

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

					if (path.endsWith(".sepia")) {
						errorModel.addElement(name);
						break;
					}
				}

			}
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

			if (file.endsWith(".sepia")) {

				fileName = file.split(".sepia");

				filesModel.addElement(fileName[0].toString());
			
			}
		}
		if (filesModel.size() >= 0) {
			btnEdit.setEnabled(true);
		}



	}

}
