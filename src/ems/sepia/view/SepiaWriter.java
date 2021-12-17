package ems.sepia.view;

import java.awt.AWTKeyStroke;
import java.awt.EventQueue;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.*;



import ems.Siel.model.sielManagement;
import ems.management.*;
import ems.sepia.model.*;
import compiler.sepia.ast.Cause;
import compiler.sepia.ast.Explanation;
import compiler.sepia.ast.Explanation2;
import compiler.sepia.ast.SourceCode;

import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class SepiaWriter {

	public JFrame frame;
	public JTextArea txtSource;
	public JTextArea txtExplanation;
	public JTextArea txtCause;
	public String Filenameis;

	public String getFileName() {
		return Filenameis;
	}

	public void setFileName2(String fileName) {
		Filenameis = fileName;
	}

	public JComboBox cmbErrorList;
	public JTextField txtCauselabel;
	public JTextField txtFileName;
	public JList<String> jlistSource;
	public JButton btnDelete;
	public JButton btnSave;
	public JButton btnUpdate;
	public JList jlParameters;
	public int Focus;
	public DefaultListModel<String> lista = new DefaultListModel();
	public DefaultListModel parametersModel = new DefaultListModel();
	public JCheckBox chckbxUseThisError;
	private JScrollPane barra = new JScrollPane(txtSource);

	sepiaWriter objsepia = new sepiaWriter();
	sepiaManagement objel = new sepiaManagement();
	sielManagement objsiel = new sielManagement();
	ArrayList<SourceCode> sourceCodeArray = new ArrayList<SourceCode>();
	ArrayList<Explanation> explanationArray = new ArrayList<Explanation>();
	ArrayList<Explanation2> explanationArray2 = new ArrayList<Explanation2>();
	ArrayList<Cause> causeArray = new ArrayList<Cause>();
	ArrayList<String> parametersArray = new ArrayList<String>();

	ErrorList list = new ErrorList();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SepiaWriter window = new SepiaWriter();
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
	public SepiaWriter() {
		File file = new File(System.getProperty("user.dir")
				+ "/data/Configuration.conf");
		if (file.exists()) {

			initialize();

			// Colocando enter para pular de campo
			HashSet conj = new HashSet(
					frame.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
			conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_TAB, 0));
			conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
			frame.setFocusTraversalKeys(
					KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

		} else {
			Configuration frEdit = new Configuration();

			frEdit.frame.setVisible(true);

		}

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("sepia");
		frame.setBounds(100, 100, 854, 591);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 911, 484);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblError = new JLabel("Cause Label");
		lblError.setBounds(10, 184, 99, 21);
		panel.add(lblError);

		JLabel lblSource = new JLabel("Example");
		lblSource.setBounds(10, 339, 99, 21);
		panel.add(lblSource);

		txtSource = new JTextArea();
		txtSource.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				Focus = 3;
			}
		});
		txtSource.setBounds(10, 362, 406, 97);
		// txtSource.setFocusTraversalKeys(
		// KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
		// txtSource.setFocusTraversalKeys(
		// KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);

		txtSource.setLineWrap(true);
		JScrollPane j = new JScrollPane(txtSource);
		j.setBounds(10, 362, 406, 97);
		panel.add(j);

		JLabel lblExplanation = new JLabel("Explanation");
		lblExplanation.setBounds(10, 71, 99, 21);
		panel.add(lblExplanation);

		txtExplanation = new JTextArea();
		txtExplanation.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				Focus = 1;
			}
		});
		txtExplanation.setBounds(10, 96, 406, 77);
		// txtExplanation.setFocusTraversalKeys(
		// KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
		// txtExplanation.setFocusTraversalKeys(
		// KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);

		txtExplanation.setLineWrap(true);
		JScrollPane p = new JScrollPane(txtExplanation);
		p.setBounds(10, 96, 406, 77);
		panel.add(p);

		jlistSource = new JList<String>();
		jlistSource.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JList list = (JList) e.getSource();
				if (e.getClickCount() == 2) {
					txtSource.setText(jlistSource.getSelectedValue());
				}
			}
		});
		jlistSource.setModel(lista);
		jlistSource
				.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		jlistSource.setBounds(474, 362, 354, 97);
		panel.add(jlistSource);

		JLabel lblSources = new JLabel("Examples");
		lblSources.setBounds(474, 340, 163, 18);
		panel.add(lblSources);

		ImageIcon imgIcon = new ImageIcon("D:\\novo1.png");
		JButton btnAdd = new JButton(imgIcon);
		btnAdd.setBounds(426, 387, 34, 23);
		panel.add(btnAdd);

		ImageIcon imgIco = new ImageIcon("D:\\excluir1.png");
		JButton btnDel = new JButton(imgIco);
		btnDel.setBounds(426, 421, 34, 23);
		panel.add(btnDel);

		btnSave = new JButton("Save");
		btnSave.setBounds(642, 523, 89, 23);
		panel.add(btnSave);

		cmbErrorList = new JComboBox(list.returnError().toArray());
		cmbErrorList.setBounds(10, 40, 406, 20);
		cmbErrorList.setSelectedIndex(-1);
		panel.add(cmbErrorList);

		JLabel lblerrorName = new JLabel("Error name");
		lblerrorName.setBounds(10, 15, 89, 14);
		panel.add(lblerrorName);

		btnDelete = new JButton("Delete");
		btnDelete.setVisible(false);
		btnDelete.setBounds(543, 523, 89, 23);
		panel.add(btnDelete);

		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(739, 523, 89, 23);
		panel.add(btnExit);

		btnUpdate = new JButton("Update");
		btnUpdate.setVisible(false);
		btnUpdate.setBounds(642, 523, 89, 23);
		panel.add(btnUpdate);

		txtCauselabel = new JTextField();
		txtCauselabel.setBounds(10, 205, 287, 20);
		panel.add(txtCauselabel);
		txtCauselabel.setColumns(10);

		txtCause = new JTextArea();
		txtCause.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				Focus = 2;
			}
		});
		txtCause.setLineWrap(true);
		txtCause.setBounds(10, 251, 406, 77);
		JScrollPane c = new JScrollPane(txtCause);
		c.setBounds(10, 251, 406, 77);
		panel.add(c);

		JLabel lblParameters = new JLabel("Parameters");
		lblParameters.setBounds(474, 16, 146, 14);
		panel.add(lblParameters);

		jlParameters = new JList(parametersModel);
		jlParameters.setBounds(474, 42, 354, 287);
		panel.add(jlParameters);

		chckbxUseThisError = new JCheckBox("Use this error message");
		chckbxUseThisError.setBounds(476, 490, 226, 23);
		panel.add(chckbxUseThisError);

		JLabel lblCause = new JLabel("Cause");
		lblCause.setBounds(10, 236, 46, 14);
		panel.add(lblCause);

		JLabel jblFileName = new JLabel("File Name");
		jblFileName.setBounds(10, 470, 99, 21);
		panel.add(jblFileName);

		txtFileName = new JTextField();
		txtFileName.setColumns(10);
		txtFileName.setBounds(10, 491, 406, 20);
		panel.add(txtFileName);

		// ACTIONS
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				objel.Delete(cmbErrorList.getSelectedItem().toString(),
						getFileName());

				Clear();
			}

		});

		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});

		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				objel.Delete(cmbErrorList.getSelectedItem().toString(),
						getFileName());

				Save();
			}
		});

		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lista.addElement(txtSource.getText());
				txtSource.setText("");
			}
		});

		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (jlistSource.getSelectedIndex() > -1) {

					if (JOptionPane.showConfirmDialog(null,
							"Are you sure you want to delete this example?",
							"Confirm", JOptionPane.YES_NO_OPTION) == 0) {
						lista.removeElement(jlistSource.getSelectedValue());
					}
				}

				else {
					JOptionPane.showMessageDialog(frame,
							"You must to select an Example");
				}
			}
		});

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Save();

			}

		});

		jlParameters.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JList list = (JList) e.getSource();
				if (e.getClickCount() == 2) {

					addParameter();
				}

			}
		});

		cmbErrorList.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				loadParameters();

				if (cmbErrorList.getSelectedIndex() > -1)
					SetFileName();
			}
		});
	}

	public void Save() {
		if (Validate()) {
			int tamanho = jlistSource.getModel().getSize();
			int size = jlParameters.getModel().getSize();

			// Verifica se existe mais de um exemplo, se houver add
			// todos ao array, senão add o que está na cx de texto

			if (tamanho > 0) {
				for (int i = 0; i < tamanho; i++) {
					sourceCodeArray.add(new SourceCode(jlistSource.getModel()
							.getElementAt(i).toCharArray()));
				}
			} else
				sourceCodeArray.add(new SourceCode(txtSource.getText()
						.toCharArray()));

			if (size > 0) {
				for (int i = 0; i < size; i++) {
					parametersArray.add(new String(jlParameters.getModel()
							.getElementAt(i).toString()));
				}
			}

			causeArray.add(new Cause(txtCause.getText().toCharArray()));

			explanationArray.add(new Explanation(txtExplanation.getText()
					.toCharArray()));
			explanationArray2.add(new Explanation2(txtCause.getText()
					.toCharArray()));

			objsepia.setCauseLabel(txtCauselabel.getText().toString());
			objsepia.setCause(causeArray);
			objsepia.setText(explanationArray);
			objsepia.setSourceCodeArray(sourceCodeArray);
			objsepia.setParametersArray(parametersArray);
			objsepia.setUseThis(Boolean.valueOf(chckbxUseThisError.isSelected()));
			objsepia.setFileName(txtFileName.getText());

			objsepia.setExplanation2(explanationArray2);

			objsepia.Save2(cmbErrorList.getSelectedItem().toString(), objsepia);

			Clear();

		}

	}

	public void addParameter() {

		String Parameter = " #"
				+ parametersModel.getElementAt(jlParameters.getSelectedIndex())
						.toString();
		Parameter = Parameter.replace("\n", "");

		switch (Focus) {

		case 1:
			txtExplanation.setText(txtExplanation.getText() + Parameter);
			break;
		case 2:
			txtCause.setText(txtCause.getText() + Parameter);
			break;
		case 3:
			txtSource.setText(txtSource.getText() + Parameter);
			break;
		}

	}

	public void loadParameters() {
		// limpa list

		parametersModel.removeAllElements();

		if (cmbErrorList.getSelectedIndex() > -1) {

			ArrayList<String> content = null;
			try {
				content = objsiel.openFile(cmbErrorList.getSelectedItem()
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

	public void SetFileName() {
		try {
			String ErrorName;
			if (cmbErrorList.getSelectedItem().toString().length() > 30) {
				ErrorName = cmbErrorList.getSelectedItem().toString()
						.substring(0, 30).replace("'", "").replace("´", "")
						.replace(" ", "_");
			} else {
				ErrorName = cmbErrorList.getSelectedItem().toString()
						.replace("'", "").replace("´", "").replace(" ", "_");
			}

			txtFileName.setText(ErrorName
					+ InetAddress.getLocalHost().getHostName());
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void Clear() {
		txtExplanation.setText("");
		txtCauselabel.setText("");
		txtCause.setText("");
		txtSource.setText("");
		cmbErrorList.setSelectedIndex(-1);
		lista.removeAllElements();
		txtFileName.setText("");
		chckbxUseThisError.setSelected(false);
	}

	private boolean Validate() {
		boolean value = true;

		if (txtCauselabel.getText().equals("")
				|| txtCause.getText().equals("")
				|| txtExplanation.getText().equals("")
				|| cmbErrorList.getSelectedItem().equals("")
				|| (jlistSource.getModel().getSize() <= 0 && txtSource
						.getText().equals(""))

		) {

			JOptionPane.showMessageDialog(frame, "All fields are required.",
					"Warning", JOptionPane.WARNING_MESSAGE);

			value = false;
		}

		return value;

	}
}
