package ems.royal.view;

import java.awt.AWTKeyStroke;
import java.awt.EventQueue;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



import compiler.royal.ast.SourceCode;

import ems.management.ErrorList;
import ems.royal.model.royalManagement;
import ems.royal.model.royalWriter;

public class RoyalWriter {

	public JFrame frame;
	public JTextField txtLanguage;
	public JTextField txtlanguageinEnglish;
	public JTextArea txtSource;
	public JTextArea txtErrorDescription;
	public JTextArea txtExplanation;
	public JComboBox cmbErrorList;
	public JList<String> jlistSource;
	public DefaultListModel<String> lista = new DefaultListModel();
	public JButton btnDelete;
	public JButton btnSave;
	public JButton btnUpdate;
	public JCheckBox chckbxUseThisMessage;

	public String Filenameis;

	public String getFileName() {
		return Filenameis;
	}

	public void setFileName2(String fileName) {
		Filenameis = fileName;
	}

	ArrayList<SourceCode> sourceCodeArray = new ArrayList<SourceCode>();
	ErrorList list = new ErrorList();
	royalWriter objexE = new royalWriter();
	royalManagement objexl = new royalManagement();
	private JTextField txtFileName;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RoyalWriter window = new RoyalWriter();
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
	public RoyalWriter() {

		initialize();

		// Colocando enter para pular de campo
		HashSet conj = new HashSet(
				frame.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
		conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_TAB, 0));
		conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
		frame.setFocusTraversalKeys(
				KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("royal");
		frame.setBounds(100, 100, 827, 571);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 811, 533);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblLanguage = new JLabel("Language");
		lblLanguage.setBounds(10, 71, 110, 21);
		panel.add(lblLanguage);

		txtLanguage = new JTextField();
		txtLanguage.setBounds(10, 96, 201, 20);
		panel.add(txtLanguage);
		txtLanguage.setColumns(10);

		JLabel label = new JLabel("Language in English");
		label.setBounds(230, 71, 186, 21);
		panel.add(label);

		txtlanguageinEnglish = new JTextField();
		txtlanguageinEnglish.setColumns(10);
		txtlanguageinEnglish.setBounds(230, 96, 186, 20);
		panel.add(txtlanguageinEnglish);

		JLabel lblError = new JLabel("Short Description");
		lblError.setBounds(10, 126, 163, 21);
		panel.add(lblError);

		txtErrorDescription = new JTextArea();
		txtErrorDescription.setBounds(10, 148, 406, 99);
		txtErrorDescription.setLineWrap(true);
		JScrollPane c = new JScrollPane(txtErrorDescription);
		c.setBounds(10, 148, 406, 99);
		panel.add(c);

		JLabel lblSource = new JLabel("Example");
		lblSource.setBounds(10, 259, 139, 21);
		panel.add(lblSource);

		txtSource = new JTextArea();
		txtSource.setBounds(10, 288, 406, 151);

		txtSource.setLineWrap(true);
		JScrollPane x = new JScrollPane(txtSource);
		x.setBounds(10, 288, 406, 151);
		panel.add(x);

		JLabel lblExplanation = new JLabel("Explanation");
		lblExplanation.setBounds(454, 13, 99, 21);
		panel.add(lblExplanation);

		txtExplanation = new JTextArea();
		txtExplanation.setBounds(464, 39, 338, 210);

		txtExplanation.setLineWrap(true);
		JScrollPane j = new JScrollPane(txtExplanation);
		j.setBounds(454, 39, 338, 210);
		panel.add(j);

		jlistSource = new JList<String>();
		jlistSource.setModel(lista);
		jlistSource
				.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		jlistSource.setBounds(464, 288, 338, 151);
		panel.add(jlistSource);

		JLabel lblSources = new JLabel("Examples");
		lblSources.setBounds(464, 260, 163, 18);
		panel.add(lblSources);

		ImageIcon imgIcon = new ImageIcon("D:\\novo1.png");
		JButton btnAdd = new JButton(imgIcon);
		btnAdd.setBounds(422, 357, 34, 23);
		panel.add(btnAdd);

		ImageIcon imgIco = new ImageIcon("D:\\excluir1.png");
		JButton btnDel = new JButton(imgIco);
		btnDel.setBounds(422, 389, 34, 23);
		panel.add(btnDel);

		btnSave = new JButton("Save");
		btnSave.setBounds(616, 499, 89, 23);
		panel.add(btnSave);

		cmbErrorList = new JComboBox(list.returnError().toArray());
		cmbErrorList.setBounds(10, 40, 406, 20);
		cmbErrorList.setSelectedIndex(-1);
		panel.add(cmbErrorList);

		JLabel lblerrorName = new JLabel("Error name");
		lblerrorName.setBounds(10, 16, 89, 14);
		panel.add(lblerrorName);

		btnDelete = new JButton("Delete");
		btnDelete.setVisible(false);
		btnDelete.setBounds(517, 499, 89, 23);
		panel.add(btnDelete);

		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(713, 499, 89, 23);
		panel.add(btnExit);

		btnUpdate = new JButton("Update");
		btnUpdate.setVisible(false);
		btnUpdate.setBounds(616, 499, 89, 23);
		panel.add(btnUpdate);

		chckbxUseThisMessage = new JCheckBox("Use this error message");
		chckbxUseThisMessage.setBounds(464, 469, 210, 23);
		panel.add(chckbxUseThisMessage);

		JLabel lblFileName = new JLabel("File Name");
		lblFileName.setBounds(10, 450, 99, 21);
		panel.add(lblFileName);

		txtFileName = new JTextField();
		txtFileName.setColumns(10);
		txtFileName.setBounds(10, 472, 406, 20);
		panel.add(txtFileName);

		// ACTIONS

		cmbErrorList.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (cmbErrorList.getSelectedIndex() > -1)
					SetFileName();

			}
		});

		jlistSource.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JList list = (JList) e.getSource();
				if (e.getClickCount() == 2) {
					txtSource.setText(jlistSource.getSelectedValue());
				}
			}
		});

		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				objexl.Delete(cmbErrorList.getSelectedItem().toString(),
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

				objexl.Delete(cmbErrorList.getSelectedItem().toString(),
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

		Load();

	}

	public void Save() {
		if (Validate()) {
			int tamanho = jlistSource.getModel().getSize();

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

			objexE.setLanguage(txtLanguage.getText());
			objexE.setLanguageInEnglish(txtlanguageinEnglish.getText());
			objexE.setErrorDescription(txtErrorDescription.getText().toString());
			objexE.setText(txtExplanation.getText().toString());
			objexE.setSourceCodeArray(sourceCodeArray);
			objexE.setUseThis(chckbxUseThisMessage.isSelected());
			objexE.setFileName(txtFileName.getText());

			objexE.Save(cmbErrorList.getSelectedItem().toString(), objexE);

			Clear();

		}

	}

	public void Load() {
		txtLanguage.setText(objexE.getLanguage());
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
		txtErrorDescription.setText("");
		txtExplanation.setText("");
		txtLanguage.setText("");
		txtlanguageinEnglish.setText("");
		txtSource.setText("");
		cmbErrorList.setSelectedIndex(-1);
		lista.removeAllElements();
		txtFileName.setText("");
		chckbxUseThisMessage.setSelected(false);
	}

	private boolean Validate() {
		boolean value = true;

		if (txtErrorDescription.getText().equals("")
				|| txtExplanation.getText().equals("")
				|| txtLanguage.getText().equals("")
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
