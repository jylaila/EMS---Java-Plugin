package ems.Siel.view;

import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



import ems.Siel.model.sielManagement;
import ems.management.Configuration;
import ems.management.ErrorList;
import ems.management.FileManagement;

public class SielManagement {

	public JFrame Managementframe;
	private DefaultListModel errorModel = new DefaultListModel();
	public DefaultListModel parametersModel = new DefaultListModel();
	public JList jlParameters, jlFiles;
	public JButton btnEdit;
	public JButton btnDeleteError;

	FileManagement objDir = new FileManagement();
	ErrorList errorList = new ErrorList();
	SielWriter frEdit = new SielWriter();
	sielManagement objsiel = new sielManagement();
	private JButton btnExit;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SielManagement window = new SielManagement();
					window.Managementframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SielManagement() {
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
	 */
	public void initialize() {
		Managementframe = new JFrame("Siel Management");
		Managementframe.setBounds(100, 100, 588, 421);
		Managementframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Managementframe.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(-20, 11, 592, 383);
		Managementframe.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblerrors = new JLabel("Errors");
		lblerrors.setBounds(30, 11, 112, 20);
		panel.add(lblerrors);

		jlFiles = new JList(errorModel);
		jlFiles.setAutoscrolls(true);
		jlFiles.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		jlFiles.setBounds(30, 42, 240, 287);
		panel.add(jlFiles);

		JLabel lblParameters = new JLabel("Parameters");
		lblParameters.setBounds(295, 14, 155, 14);
		panel.add(lblParameters);

		jlParameters = new JList(parametersModel);
		jlParameters.setBounds(295, 42, 287, 287);
		panel.add(jlParameters);

		btnEdit = new JButton("Edit");
		btnEdit.setBounds(394, 340, 89, 23);
		btnEdit.setEnabled(false);
		panel.add(btnEdit);

		btnDeleteError = new JButton("Delete Error");
		btnDeleteError.setEnabled(false);
		btnDeleteError.setBounds(115, 340, 155, 23);
		panel.add(btnDeleteError);

		JButton btnAddNew = new JButton("Add new");
		btnAddNew.setBounds(295, 340, 89, 23);
		panel.add(btnAddNew);

		btnExit = new JButton("Exit");
		btnExit.setBounds(493, 340, 89, 23);
		panel.add(btnExit);

		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Managementframe.dispose();
			}
		});

		jlFiles.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				loadParameters();
			}
		});

		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frEdit.frame.setVisible(true);
			}
		});

		btnDeleteError.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (jlFiles.getSelectedIndex() > -1) {
					if (JOptionPane.showConfirmDialog(null,
							"Are you sure you want to delete "
									+ jlFiles.getSelectedValue().toString()
									+ " and all parameters?", "Confirm",
							JOptionPane.YES_NO_OPTION) == 0) {
						objsiel.deleteError(jlFiles.getSelectedValue()
								.toString());

						// Recarrega List
						errorModel.removeAllElements();
						parametersModel.removeAllElements();
						errorList();

					}
				} else
					JOptionPane.showMessageDialog(Managementframe,
							"You must to select an Error");

			}
		});

		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setValues();
			}
		});

		// Carrega lista de erros
		errorList();
	}

	public void loadParameters() {
		// limpa list
		parametersModel.removeAllElements();

		if (jlFiles.getSelectedIndex() > -1) {

			ArrayList<String> content = null;
			try {
				content = objsiel.openFile(jlFiles.getSelectedValue()
						.toString());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (int i = 0; i < content.size(); i++) {
				parametersModel.addElement(content.get(i));
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

				File path = objDir.FilePath(list[i].toString()
						+ "/Parameters.siel");

				if (path.exists()) {
					errorModel.addElement(name);
				}
			}
		}

		if (errorModel.size() >= 0) {
			btnEdit.setEnabled(true);
			btnDeleteError.setEnabled(true);
		}

	}

	private void setValues() {

		if (jlParameters.getSelectedIndex() > -1) {

			setVisibility();
			String[] variable = jlParameters.getSelectedValue().toString()
					.split(" ");

			frEdit.txtErrorName.setText(jlFiles.getSelectedValue().toString());
			frEdit.txtParameter.setText(variable[0]);

		} else
			JOptionPane.showMessageDialog(Managementframe,
					"You must to select an Error and a Parameter");
	}

	private void setVisibility() {

		frEdit.frame.setVisible(true);
		frEdit.txtErrorName.setVisible(true);
		frEdit.cmbErrorList.setVisible(false);
		frEdit.txtErrorName.setEnabled(false);
		frEdit.txtParameter.setEnabled(false);
		frEdit.btnSave.setVisible(false);
		frEdit.btnDelete.setVisible(true);
		frEdit.btnUpdate.setVisible(true);

	}
};
