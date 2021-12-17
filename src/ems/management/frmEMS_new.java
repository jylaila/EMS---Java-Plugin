package ems.management;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.text.BadLocationException;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.eclipse.core.resources.ResourcesPlugin;

import compiler.sepia.ast.Cause;
import compiler.sepia.ast.Explanation;
import compiler.sepia.ast.Explanation2;
import compiler.sepia.ast.SourceCode;
import compiler.sepia.main.Main;

import ems.Siel.model.sielManagement;
import ems.Siel.model.sielWriter;
import ems.royal.model.royalManagement;
import ems.royal.model.royalWriter;
import ems.sepia.model.sepiaManagement;
import ems.sepia.model.sepiaWriter;

import java.awt.Toolkit;
import javax.swing.DefaultComboBoxModel;

public class frmEMS_new {

	public JFrame frame;
	JTree tree;
	JLabel lblTitle;
	private JSeparator separator_1;
	private JSeparator separator_3;
	private JLabel lblHelp;
	private JLabel lblLblhelp;

	// Configuration
	private JTextField txtFolder_Config;
	JPanel pnlConfiguration = new JPanel();
	JButton btnSave_Config;

	// Siel Management
	private DefaultListModel errorModel_Siel = new DefaultListModel();
	private DefaultListModel parametersModel_Siel = new DefaultListModel();
	private JList jlParameters_Siel, jlParameters_Siel2, jlFiles_Sepia;
	JPanel pnlSielManagement = new JPanel();
	JButton btnEdit_Siel = new JButton("Edit");
	JButton btnDelete_Siel = new JButton("Delete");
	String filename_Siel;
	JList jlFiles_Siel;

	// Siel writer
	private JTextArea txtParameter_Siel;
	private JTextField txtErrorName_Siel;
	private JComboBox cmbErrorList_Siel;
	JPanel pnlSielWriter = new JPanel();
	JButton btnUpdate_Siel = new JButton("Update");
	JButton btnSave_Siel = new JButton("Save");

	// Sepia - sepia Management
	private JTabbedPane tbSepia;
	private JList<String> jlErrors_Sepia;
	private JButton btnEdit_Sepia;
	private DefaultListModel<String> errorModel_Sepia = new DefaultListModel<String>();
	private DefaultListModel<String> filesModel_Sepia = new DefaultListModel<String>();
	JPanel pnlSepiaManagement = new JPanel();
	static String errorName;
	static Boolean useThis;
	static String fileName;
	static ArrayList<SourceCode> sourceCodeArray;
	static ArrayList<Cause> causeArray;
	static ArrayList<Explanation> explanationArray;
	static ArrayList<Explanation2> explanationArray2;

	// Sepia - sepia writer
	private JTextArea txtSource_Sepia;
	private JTextArea txtExplanation_Sepia;
	private JTextArea txtCause_Sepia;
	private String fileName_Sepia;
	public JComboBox cmbErrorList_Sepia;
	private JTextField txtCauselabel_Sepia;
	private JTextField txtFileName_Sepia;
	private JList<String> jlistSource_Sepia;
	private JList jlParameters_Sepia1;
	private JList jlParameters_Sepia2;
	private JList jlParameters_Sepia3;
	private JList<String> jlistCauses_Sepia;
	private int Focus_Sepia;
	private DefaultListModel<String> list_Sepia = new DefaultListModel();
	private DefaultListModel<String> list_Sepia2 = new DefaultListModel();
	private DefaultListModel parametersModel_Sepia = new DefaultListModel();
	public JCheckBox chckbxUse_Sepia;
	private ArrayList<SourceCode> sourceCodeArray_Sepia = new ArrayList<SourceCode>();
	private ArrayList<Explanation> explanationArray_Sepia = new ArrayList<Explanation>();
	private ArrayList<Explanation2> explanationArray2_Sepia = new ArrayList<Explanation2>();
	private ArrayList<Cause> causeArray_Sepia = new ArrayList<Cause>();
	private ArrayList<String> parametersArray_Sepia = new ArrayList<String>();
	JButton btnSave_Sepia = new JButton("Save");
	JButton btnUpdate_Sepia = new JButton("Update");
	JButton btnDelete_Sepia = new JButton("Delete");
	Panel pnlSepiaWriter = new Panel();
	private JPanel pnlExplanation;

	private String getFileName_Sepia() {
		return fileName_Sepia;
	}

	// Royal - royal Management
	private JList<String> jlErrors_Royal;
	private JList<String> jlFiles_Royal;
	private DefaultListModel<String> errorModel_Royal = new DefaultListModel<String>();
	private DefaultListModel<String> filesModel_Royal = new DefaultListModel<String>();
	JPanel pnlRoyalManagement = new JPanel();
	JButton btnEdit_Royal = new JButton("Edit");
	JButton btnDelete_Royal = new JButton("Delete");
	JButton btnSave_Royal = new JButton("Save");
	JButton btnUpdate_Royal = new JButton("Update");

	// Royal -royal Writer
	private JTextField txtLanguage_Royal;
	private JTextField txtlanguageinEnglish_Royal;
	private JTextArea txtSource_Royal;
	private JTextArea txtErrorDescription_Royal;
	private JTextArea txtExplanation_Royal;
	private JComboBox cmbErrorList_Royal;
	private JList<String> jlistSource_Royal;
	private JList<String> jlistExplanation_Royal;
	private DefaultListModel<String> list_Royal = new DefaultListModel();
	private DefaultListModel<String> list_ExRoyal = new DefaultListModel();
	private JCheckBox chckbxUseThis_Royal;
	ArrayList<compiler.royal.ast.SourceCode> sourceCodeArray_Royal;
	// = new ArrayList<compiler.royal.ast.SourceCode>();
	ArrayList<compiler.royal.ast.Explanation> ExplanationArray_Royal;
	// = new ArrayList<compiler.royal.ast.Explanation>();
	private JTextField txtFileName_Royal;
	static String fileName_Royal;
	JPanel pnlRoyalWriter = new JPanel();

	static String errorName_Royal;
	static String language, languageInEnglish, errorDescription, explanation;
	static boolean useThis_Royal;
	static ArrayList<compiler.royal.ast.SourceCode> sourceCodeArrayRoyal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

		} catch (Exception e) {
			System.out.println("Error setting native LAF: " + e);
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					frmEMS_new window = new frmEMS_new();
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
	public frmEMS_new() {

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("EMS - Error Message System");
		frame.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(frmEMS_new.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				close();
			}
		});
		// frame.setIconImage(Toolkit.getDefaultToolkit().getImage(
		// frmEMS_new.class.getResource("/icons/full/etool16/new_wiz.gif")));
		frame.setFont(new Font("Arial", Font.PLAIN, 12));
		frame.setBounds(100, 100, 729, 558);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		lblHelp = new JLabel("");
		// lblHelp.setIcon(new
		// ImageIcon(frmEMS_new.class.getResource("/org/eclipse/jface/dialogs/images/help.gif")));
		lblHelp.setBounds(7, 482, 34, 38);
		frame.getContentPane().add(lblHelp);

		JButton btnCancel = new JButton("Exit");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();

			}
		});
		btnCancel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnCancel.setBounds(614, 490, 89, 23);
		frame.getContentPane().add(btnCancel);

		JSeparator separator = new JSeparator();
		separator.setBounds(166, 36, 544, 2);
		frame.getContentPane().add(separator);

		lblTitle = new JLabel("EMS Plugin");
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblTitle.setBounds(178, 0, 228, 38);
		frame.getContentPane().add(lblTitle);

		separator_1 = new JSeparator();
		separator_1.setBounds(-3, 477, 713, 2);
		frame.getContentPane().add(separator_1);

		separator_3 = new JSeparator();
		separator_3.setBounds(175, 0, 1, 430);
		frame.getContentPane().add(separator_3);

		// Sepia
		frame.getContentPane().add(pnlSepiaWriter);

		pnlSepiaWriter.setBounds(174, 41, 529, 430);
		pnlSepiaWriter.setLayout(null);

		tbSepia = new JTabbedPane(JTabbedPane.TOP);
		tbSepia.setBounds(0, 0, 529, 390);
		pnlSepiaWriter.add(tbSepia);

		pnlExplanation = new JPanel();
		pnlExplanation.setLayout(null);

		JPanel pnlCause = new JPanel();
		pnlCause.setLayout(null);

		JPanel pnlExample = new JPanel();
		pnlExample.setLayout(null);

		JPanel pnlConfigurationSepia = new JPanel();
		pnlConfigurationSepia.setLayout(null);

		tbSepia.setFont(new Font("Segoe UI", Font.PLAIN, 12));

		tbSepia.addTab("Explanation", pnlExplanation);
		tbSepia.addTab("Cause", pnlCause);
		tbSepia.addTab("Example", pnlExample);
		tbSepia.addTab("Preferences", pnlConfigurationSepia);

		JLabel lblError = new JLabel("Cause Label");
		lblError.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblError.setBounds(10, 15, 89, 16);
		pnlCause.add(lblError);

		JLabel lblSource = new JLabel("Example");
		lblSource.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblSource.setBounds(10, 15, 89, 16);
		pnlExample.add(lblSource);

		JScrollPane j = new JScrollPane();
		j.setBounds(10, 40, 287, 180);
		pnlExample.add(j);

		txtSource_Sepia = new JTextArea();
		txtSource_Sepia.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtSource_Sepia.setWrapStyleWord(true);
		j.setViewportView(txtSource_Sepia);
		txtSource_Sepia.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				Focus_Sepia = 3;
			}
		});
		txtSource_Sepia.setLineWrap(true);

		JLabel lblExplanation = new JLabel("Explanation");
		lblExplanation.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblExplanation.setBounds(10, 64, 99, 21);
		pnlExplanation.add(lblExplanation);

		txtExplanation_Sepia = new JTextArea();
		txtExplanation_Sepia.setWrapStyleWord(true);
		txtExplanation_Sepia.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtExplanation_Sepia.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				Focus_Sepia = 1;
			}
		});
		txtExplanation_Sepia.setBounds(10, 92, 202, 257);
		txtExplanation_Sepia.setLineWrap(true);
		JScrollPane p = new JScrollPane(txtExplanation_Sepia);
		p.setBounds(10, 92, 287, 257);
		pnlExplanation.add(p);

		jlistSource_Sepia = new JList<String>();
		jlistSource_Sepia.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		jlistSource_Sepia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					txtSource_Sepia.setText(jlistSource_Sepia.getSelectedValue());
				}
			}
		});
		jlistSource_Sepia.setModel(list_Sepia);
		jlistSource_Sepia.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		jlistSource_Sepia.setBounds(10, 265, 504, 77);
		pnlExample.add(jlistSource_Sepia);

		JLabel lblSources = new JLabel("Examples");
		lblSources.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblSources.setBounds(10, 236, 163, 18);
		pnlExample.add(lblSources);

		// ImageIcon imgIcon = new ImageIcon("D:\\novo1.png");
		JButton btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnAdd.setBounds(431, 231, 83, 23);
		pnlExample.add(btnAdd);

		// ImageIcon imgIco = new ImageIcon("D:\\excluir1.png");
		JButton btnDel = new JButton("Remove");
		btnDel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnDel.setBounds(338, 231, 83, 23);
		pnlExample.add(btnDel);

		jlParameters_Sepia3 = new JList(parametersModel_Sepia);
		jlParameters_Sepia3.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		jlParameters_Sepia3.setBounds(312, 40, 202, 180);
		pnlExample.add(jlParameters_Sepia3);

		JLabel label_2 = new JLabel("Parameters");
		label_2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		label_2.setBounds(312, 15, 146, 16);
		pnlExample.add(label_2);

		btnSave_Sepia.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnSave_Sepia.setBounds(346, 395, 89, 23);
		pnlSepiaWriter.add(btnSave_Sepia);

		ErrorList list1 = new ErrorList();
		cmbErrorList_Sepia = new JComboBox(list1.returnError().toArray());
		cmbErrorList_Sepia.setBackground(Color.WHITE);
		cmbErrorList_Sepia.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		cmbErrorList_Sepia.setBounds(10, 36, 504, 20);
		cmbErrorList_Sepia.setSelectedIndex(-1);
		pnlExplanation.add(cmbErrorList_Sepia);

		JLabel lblerrorName = new JLabel("Error name");
		lblerrorName.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblerrorName.setBounds(10, 11, 89, 16);
		pnlExplanation.add(lblerrorName);

		btnDelete_Sepia.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnDelete_Sepia.setVisible(false);
		btnDelete_Sepia.setBounds(247, 395, 89, 23);
		pnlSepiaWriter.add(btnDelete_Sepia);

		btnUpdate_Sepia.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnUpdate_Sepia.setVisible(false);
		btnUpdate_Sepia.setBounds(346, 395, 89, 23);
		pnlSepiaWriter.add(btnUpdate_Sepia);

		txtCauselabel_Sepia = new JTextField();
		txtCauselabel_Sepia.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtCauselabel_Sepia.setBounds(10, 40, 287, 20);
		pnlCause.add(txtCauselabel_Sepia);
		txtCauselabel_Sepia.setColumns(10);

		txtCause_Sepia = new JTextArea();
		txtCause_Sepia.setWrapStyleWord(true);
		txtCause_Sepia.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtCause_Sepia.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				Focus_Sepia = 2;
			}
		});
		txtCause_Sepia.setLineWrap(true);
		txtCause_Sepia.setBounds(10, 95, 287, 140);
		JScrollPane c = new JScrollPane(txtCause_Sepia);
		c.setBounds(10, 95, 287, 140);
		pnlCause.add(c);

		JButton btnAdd_Sepia = new JButton("Add");
		btnAdd_Sepia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				list_Sepia2.addElement(txtCauselabel_Sepia.getText() + "-" + txtCause_Sepia.getText());

				txtCauselabel_Sepia.setText("");
				txtCause_Sepia.setText("");

			}
		});
		btnAdd_Sepia.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnAdd_Sepia.setVisible(true);
		btnAdd_Sepia.setBounds(421, 238, 89, 23);
		pnlCause.add(btnAdd_Sepia);

		JLabel lblParametersSepia = new JLabel("Parameters");
		lblParametersSepia.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblParametersSepia.setBounds(307, 67, 146, 16);
		pnlExplanation.add(lblParametersSepia);

		jlParameters_Sepia1 = new JList(parametersModel_Sepia);
		jlParameters_Sepia1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		jlParameters_Sepia1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jlParameters_Sepia1.setBounds(312, 92, 202, 257);
		pnlExplanation.add(jlParameters_Sepia1);

		jlParameters_Sepia2 = new JList(parametersModel_Sepia);
		jlParameters_Sepia2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		jlParameters_Sepia2.setBounds(312, 40, 202, 190);
		pnlCause.add(jlParameters_Sepia2);

		chckbxUse_Sepia = new JCheckBox("Use this error message");
		chckbxUse_Sepia.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		chckbxUse_Sepia.setBounds(10, 71, 176, 21);
		pnlConfigurationSepia.add(chckbxUse_Sepia);

		JLabel lblCause = new JLabel("Cause Explanation");
		lblCause.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblCause.setBounds(10, 68, 99, 21);
		pnlCause.add(lblCause);

		JLabel label = new JLabel("Parameters");
		label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		label.setBounds(312, 15, 146, 16);
		pnlCause.add(label);

		jlistCauses_Sepia = new JList<String>(list_Sepia2);
		jlistCauses_Sepia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent c) {
				if (c.getClickCount() == 2) {
					int indice = jlistCauses_Sepia.getSelectedValue().indexOf("-");
					txtCauselabel_Sepia.setText(jlistCauses_Sepia.getSelectedValue().substring(0, indice));
					txtCause_Sepia.setText(jlistCauses_Sepia.getSelectedValue().substring(indice + 1,
							jlistCauses_Sepia.getSelectedValue().length()));
				}
			}
		});
		jlistCauses_Sepia.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jlistCauses_Sepia.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		jlistCauses_Sepia.setBounds(10, 267, 504, 82);
		pnlCause.add(jlistCauses_Sepia);

		JLabel label_1 = new JLabel("Examples");
		label_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		label_1.setBounds(10, 196, 163, 18);
		pnlCause.add(label_1);

		JButton btnRemove_sepia = new JButton("Remove");
		btnRemove_sepia.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnRemove_sepia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (jlistCauses_Sepia.getSelectedIndex() > -1) {

					if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this cause?", "Confirm",
							JOptionPane.YES_NO_OPTION) == 0) {
						list_Sepia2.removeElement(jlistCauses_Sepia.getSelectedValue());
					}
				}

				else {
					JOptionPane.showMessageDialog(null, "You must to select an Cause.", "Required",
							JOptionPane.WARNING_MESSAGE);
				}
			}

		});

		btnRemove_sepia.setBounds(322, 238, 89, 23);
		pnlCause.add(btnRemove_sepia);

		JLabel lblCauses = new JLabel("Causes");
		lblCauses.setBounds(10, 242, 46, 16);
		pnlCause.add(lblCauses);

		JLabel jblFileName2 = new JLabel("File Name");
		jblFileName2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		jblFileName2.setBounds(10, 15, 89, 16);
		pnlConfigurationSepia.add(jblFileName2);

		txtFileName_Sepia = new JTextField();
		txtFileName_Sepia.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtFileName_Sepia.setColumns(10);
		txtFileName_Sepia.setBounds(10, 40, 504, 20);
		pnlConfigurationSepia.add(txtFileName_Sepia);

		JButton btncancelsepia = new JButton("Cancel");
		btncancelsepia.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btncancelsepia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadSepiaManagement();
			}
		});
		btncancelsepia.setBounds(440, 395, 89, 23);
		pnlSepiaWriter.add(btncancelsepia);

		// ACTIONS
		btnDelete_Sepia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sepiaManagement objel = new sepiaManagement();
				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this error Message?",
						"Confirm", JOptionPane.YES_NO_OPTION) == 0) {
					objel.Delete(errorName, fileName);

					filesModel_Sepia.removeAllElements();
					ClearSepia();
					loadSepiaManagement();
				}
			}

		});

		btnUpdate_Sepia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sepiaManagement objel = new sepiaManagement();

				objel.Delete(errorName, fileName);

				SaveSepia();
				loadSepiaManagement();
				btnUpdate_Sepia.setEnabled(false);

			}
		});

		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list_Sepia.addElement(txtSource_Sepia.getText());
				txtSource_Sepia.setText("");
			}
		});

		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (jlistSource_Sepia.getSelectedIndex() > -1) {

					if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this example?", "Confirm",
							JOptionPane.YES_NO_OPTION) == 0) {
						list_Sepia.removeElement(jlistSource_Sepia.getSelectedValue());
					}
				}

				else {
					JOptionPane.showMessageDialog(null, "You must to select an Example.", "Required",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		btnSave_Sepia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				SaveSepia();

			}

		});

		jlParameters_Sepia1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {

					addParameter();
				}

			}
		});

		jlParameters_Sepia2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {

					addParameter();
				}

			}
		});

		jlParameters_Sepia3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {

					addParameter();
				}

			}
		});

		cmbErrorList_Sepia.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				loadParametersSepia();

				if (cmbErrorList_Sepia.getSelectedIndex() > -1)
					SetFileName(cmbErrorList_Sepia, txtFileName_Sepia);
			}
		});

		// Sepia MANAGEMENT
		pnlSepiaManagement.setBounds(174, 41, 529, 430);
		frame.getContentPane().add(pnlSepiaManagement);
		pnlSepiaManagement.setLayout(null);

		jlErrors_Sepia = new JList<String>(errorModel_Sepia);
		jlErrors_Sepia.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		jlErrors_Sepia.setBounds(10, 36, 253, 330);
		jlErrors_Sepia.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jlErrors_Sepia.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		jlErrors_Sepia.setSelectedIndex(0);
		jlErrors_Sepia.setLayoutOrientation(JList.VERTICAL);
		jlErrors_Sepia.setAutoscrolls(true);
		pnlSepiaManagement.add(jlErrors_Sepia);

		JScrollPane jpsiSepia = new JScrollPane(jlErrors_Sepia);
		jpsiSepia.setBounds(10, 36, 253, 330);
		pnlSepiaManagement.add(jpsiSepia);

		JLabel lblParameters = new JLabel("Error Messages");
		lblParameters.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblParameters.setBounds(273, 11, 155, 16);
		pnlSepiaManagement.add(lblParameters);

		jlFiles_Sepia = new JList<String>(filesModel_Sepia);
		jlFiles_Sepia.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jlFiles_Sepia.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		jlFiles_Sepia.setBounds(273, 36, 246, 330);
		pnlSepiaManagement.add(jlFiles_Sepia);

		JScrollPane jpsiSepia2 = new JScrollPane(jlFiles_Sepia);
		jpsiSepia2.setBounds(273, 36, 246, 330);
		pnlSepiaManagement.add(jpsiSepia2);

		JLabel lblGerenciarMensagens = new JLabel("Errors");
		lblGerenciarMensagens.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblGerenciarMensagens.setBounds(10, 11, 253, 16);
		pnlSepiaManagement.add(lblGerenciarMensagens);

		btnEdit_Sepia = new JButton("Edit");
		btnEdit_Sepia.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnEdit_Sepia.setBounds(430, 395, 89, 23);
		pnlSepiaManagement.add(btnEdit_Sepia);

		JButton btnAddNew = new JButton("Add New");
		btnAddNew.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ClearSepia();
				loadSepiaWriter();
			}
		});
		btnAddNew.setBounds(334, 395, 89, 23);
		pnlSepiaManagement.add(btnAddNew);

		btnEdit_Sepia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sepiaManagement objsepia = new sepiaManagement();
				Main compiler = new Main();

				if (jlFiles_Sepia.getSelectedIndex() > -1) {
					try {
						loadSepiaWriter();

						String returnPath = objsepia.FindFolder(jlErrors_Sepia.getSelectedValue(),
								jlFiles_Sepia.getSelectedValue().toString());

						Main.main(returnPath, jlErrors_Sepia.getSelectedValue(),
								jlFiles_Sepia.getSelectedValue().toString());

						setvalues_Sepia();
						btnUpdate_Sepia.setEnabled(true);

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else
					JOptionPane.showMessageDialog(null, "You must to select an Error Message.", "Required",
							JOptionPane.WARNING_MESSAGE);

			}
		});

		jlErrors_Sepia.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				loadFiles_Sepia();
			}
		});

		// ROYAL WRITER
		pnlRoyalWriter.setBounds(174, 41, 529, 430);
		frame.getContentPane().add(pnlRoyalWriter);

		pnlRoyalWriter.setLayout(null);
		pnlRoyalWriter.setFont(new Font("Segoe UI", Font.PLAIN, 12));

		JTabbedPane tbRoyal = new JTabbedPane(JTabbedPane.TOP);
		tbRoyal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		tbRoyal.setBounds(0, 0, 529, 390);
		pnlRoyalWriter.add(tbRoyal);

		Panel pnlDescription = new Panel();
		pnlDescription.setBackground(SystemColor.control);
		pnlDescription.setLayout(null);

		Panel pnlExplanation1 = new Panel();
		pnlExplanation1.setBackground(SystemColor.control);
		pnlExplanation1.setLayout(null);

		Panel pnlExample1 = new Panel();
		pnlExample1.setBackground(SystemColor.control);
		pnlExample1.setLayout(null);

		Panel pnlConfiguration1 = new Panel();
		pnlConfiguration1.setBackground(SystemColor.control);
		pnlConfiguration1.setLayout(null);

		tbRoyal.addTab("Description", pnlDescription);
		tbRoyal.addTab("Explanation", pnlExplanation1);
		tbRoyal.addTab("Example", pnlExample1);
		tbRoyal.addTab("Preferences", pnlConfiguration1);

		// Royal writer
		JLabel lblLanguage = new JLabel("Language");
		lblLanguage.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblLanguage.setBounds(10, 71, 110, 21);
		pnlDescription.add(lblLanguage);

		txtLanguage_Royal = new JTextField();
		txtLanguage_Royal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtLanguage_Royal.setBounds(10, 96, 251, 22);
		pnlDescription.add(txtLanguage_Royal);
		txtLanguage_Royal.setColumns(10);

		JLabel label1 = new JLabel("Language in English");
		label1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		label1.setBounds(270, 71, 186, 21);
		pnlDescription.add(label1);

		txtlanguageinEnglish_Royal = new JTextField();
		txtlanguageinEnglish_Royal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtlanguageinEnglish_Royal.setColumns(10);
		txtlanguageinEnglish_Royal.setBounds(271, 96, 243, 22);
		pnlDescription.add(txtlanguageinEnglish_Royal);

		JLabel lblError1 = new JLabel("Error Description");
		lblError1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblError1.setBounds(10, 126, 163, 21);
		pnlDescription.add(lblError1);

		txtErrorDescription_Royal = new JTextArea();
		txtErrorDescription_Royal.setWrapStyleWord(true);
		txtErrorDescription_Royal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtErrorDescription_Royal.setBounds(10, 148, 504, 200);
		txtErrorDescription_Royal.setLineWrap(true);
		JScrollPane c1 = new JScrollPane(txtErrorDescription_Royal);
		c1.setBounds(10, 148, 504, 200);
		pnlDescription.add(c1);

		JLabel lblSource1 = new JLabel("Example");
		lblSource1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblSource1.setBounds(10, 15, 89, 16);
		pnlExample1.add(lblSource1);

		txtSource_Royal = new JTextArea();
		txtSource_Royal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtSource_Royal.setBounds(10, 40, 504, 130);

		txtSource_Royal.setLineWrap(true);
		JScrollPane x = new JScrollPane(txtSource_Royal);
		x.setBounds(10, 40, 504, 130);
		pnlExample1.add(x);

		JLabel lblExplanation2 = new JLabel("Explanation");
		lblExplanation2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblExplanation2.setBounds(10, 15, 89, 16);
		pnlExplanation1.add(lblExplanation2);

		txtExplanation_Royal = new JTextArea();
		txtExplanation_Royal.setWrapStyleWord(true);
		txtExplanation_Royal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtExplanation_Royal.setBounds(10, 40, 504, 130);
		txtExplanation_Royal.setLineWrap(true);
		JScrollPane j1 = new JScrollPane(txtExplanation_Royal);
		j1.setBounds(10, 40, 504, 130);
		pnlExplanation1.add(j1);

		// ImageIcon imgIcon = new ImageIcon("D:\\novo1.png");
		JButton btnAddExRoyal = new JButton("Add");
		btnAddExRoyal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnAddExRoyal.setBounds(431, 173, 83, 23);
		pnlExplanation1.add(btnAddExRoyal);

		// ImageIcon imgIco = new ImageIcon("D:\\excluir1.png");
		JButton btnDelExRoyal = new JButton("Remove");
		btnDelExRoyal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnDelExRoyal.setBounds(338, 173, 83, 23);
		pnlExplanation1.add(btnDelExRoyal);

		jlistExplanation_Royal = new JList<String>();
		jlistExplanation_Royal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		jlistExplanation_Royal.setModel(list_ExRoyal);
		jlistExplanation_Royal.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		jlistExplanation_Royal.setBounds(10, 207, 504, 135);
		pnlExplanation1.add(jlistExplanation_Royal);

		jlistSource_Royal = new JList<String>();
		jlistSource_Royal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		jlistSource_Royal.setModel(list_Royal);
		jlistSource_Royal.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		jlistSource_Royal.setBounds(10, 207, 504, 135);
		pnlExample1.add(jlistSource_Royal);

		JLabel lblSources2 = new JLabel("Examples");
		lblSources2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblSources2.setBounds(10, 178, 163, 18);
		pnlExample1.add(lblSources2);

		JLabel lblSources3 = new JLabel("Explanations");
		lblSources3.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblSources3.setBounds(10, 178, 163, 18);
		pnlExplanation1.add(lblSources3);

		// ImageIcon imgIcon = new ImageIcon("D:\\novo1.png");
		JButton btnAddRoyal = new JButton("Add");
		btnAddRoyal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnAddRoyal.setBounds(431, 173, 83, 23);
		pnlExample1.add(btnAddRoyal);

		// ImageIcon imgIco = new ImageIcon("D:\\excluir1.png");
		JButton btnDelRoyal = new JButton("Remove");
		btnDelRoyal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnDelRoyal.setBounds(338, 173, 83, 23);
		pnlExample1.add(btnDelRoyal);

		JLabel jblFileName = new JLabel("File Name");
		jblFileName.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		jblFileName.setBounds(10, 15, 89, 16);
		pnlConfiguration1.add(jblFileName);

		btnSave_Royal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnSave_Royal.setBounds(346, 395, 89, 23);
		pnlRoyalWriter.add(btnSave_Royal);

		JButton btnCancelRoyal = new JButton("Cancel");
		btnCancelRoyal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnCancelRoyal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadRoyalManagement();
			}
		});
		btnCancelRoyal.setBounds(440, 395, 89, 23);
		pnlRoyalWriter.add(btnCancelRoyal);

		ErrorList listRoyal = new ErrorList();
		cmbErrorList_Royal = new JComboBox(listRoyal.returnError().toArray());
		cmbErrorList_Royal.setBackground(Color.WHITE);
		cmbErrorList_Royal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		cmbErrorList_Royal.setBounds(10, 40, 504, 20);
		cmbErrorList_Royal.setSelectedIndex(-1);
		pnlDescription.add(cmbErrorList_Royal);

		JLabel lblerrorName2 = new JLabel("Error name");
		lblerrorName2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblerrorName2.setBounds(10, 15, 89, 16);
		pnlDescription.add(lblerrorName2);

		btnDelete_Royal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnDelete_Royal.setVisible(false);
		btnDelete_Royal.setBounds(247, 395, 89, 23);
		pnlRoyalWriter.add(btnDelete_Royal);

		btnUpdate_Royal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnUpdate_Royal.setVisible(false);
		btnUpdate_Royal.setBounds(346, 395, 89, 23);
		pnlRoyalWriter.add(btnUpdate_Royal);

		chckbxUseThis_Royal = new JCheckBox("Use this error message");
		chckbxUseThis_Royal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		chckbxUseThis_Royal.setBounds(10, 71, 176, 21);
		pnlConfiguration1.add(chckbxUseThis_Royal);

		JLabel lblFileName = new JLabel("File Name");
		lblFileName.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblFileName.setBounds(10, 15, 89, 16);
		pnlConfiguration.add(lblFileName);

		txtFileName_Royal = new JTextField();
		txtFileName_Royal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtFileName_Royal.setColumns(10);
		txtFileName_Royal.setBounds(10, 40, 504, 20);
		pnlConfiguration1.add(txtFileName_Royal);

		// ACTIONS

		cmbErrorList_Royal.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (cmbErrorList_Royal.getSelectedIndex() > -1)
					SetFileName(cmbErrorList_Royal, txtFileName_Royal);

			}
		});

		jlistSource_Royal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					txtSource_Royal.setText(jlistSource_Royal.getSelectedValue());
				}
			}
		});

		jlistExplanation_Royal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					txtExplanation_Royal.setText(jlistExplanation_Royal.getSelectedValue());
				}
			}
		});
		btnDelete_Royal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				royalManagement objexl = new royalManagement();
				if (jlFiles_Royal.getSelectedIndex() > -1) {

					if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this message?", "Confirm",
							JOptionPane.YES_NO_OPTION) == 0) {
						objexl.Delete(errorName_Royal, fileName_Royal);
						ClearRoyal();
						filesModel_Royal.removeAllElements();
						loadRoyalManagement();
					}
				}
			}

		});

		btnUpdate_Royal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				royalManagement objexl = new royalManagement();

				objexl.Delete(errorName_Royal, fileName_Royal);
				SaveRoyal();
				loadRoyalManagement();
			}

		});

		btnAddRoyal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list_Royal.addElement(txtSource_Royal.getText());
				txtSource_Royal.setText("");
			}
		});

		btnAddExRoyal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list_ExRoyal.addElement(txtExplanation_Royal.getText());
				txtExplanation_Royal.setText("");
			}
		});

		btnDelExRoyal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (jlistExplanation_Royal.getSelectedIndex() > -1) {

					if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this explanation?",
							"Confirm", JOptionPane.YES_NO_OPTION) == 0) {
						list_ExRoyal.removeElement(jlistExplanation_Royal.getSelectedValue());
					}
				}

				else {
					JOptionPane.showMessageDialog(null, "You must to select an Explanation.", "Required",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		btnDelRoyal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (jlistSource_Royal.getSelectedIndex() > -1) {

					if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this example?", "Confirm",
							JOptionPane.YES_NO_OPTION) == 0) {
						list_Royal.removeElement(jlistSource_Royal.getSelectedValue());
					}
				}

				else {
					JOptionPane.showMessageDialog(null, "You must to select an Example.", "Required",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		btnSave_Royal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaveRoyal();
			}

		});

		// ROYAL MANAGEMENT
		pnlRoyalManagement.setBounds(174, 41, 529, 430);
		frame.getContentPane().add(pnlRoyalManagement);
		pnlRoyalManagement.setLayout(null);

		jlErrors_Royal = new JList<String>(errorModel_Royal);
		jlErrors_Royal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		jlErrors_Royal.setBounds(10, 36, 253, 330);
		jlErrors_Royal.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jlErrors_Royal.setAutoscrolls(true);
		jlErrors_Royal.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		jlErrors_Royal.setSelectedIndex(0);

		pnlRoyalManagement.add(jlErrors_Royal);

		JScrollPane jpsiRoyal2 = new JScrollPane(jlErrors_Royal);
		jpsiRoyal2.setBounds(10, 36, 253, 330);
		pnlRoyalManagement.add(jpsiRoyal2);

		JLabel lblParameters1 = new JLabel("Error Messages");
		lblParameters1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblParameters1.setBounds(273, 11, 155, 16);
		pnlRoyalManagement.add(lblParameters1);

		jlFiles_Royal = new JList<String>(filesModel_Royal);
		jlFiles_Royal.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jlFiles_Royal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		jlFiles_Royal.setBounds(273, 36, 246, 330);
		jlFiles_Royal.setAutoscrolls(true);
		pnlRoyalManagement.add(jlFiles_Royal);

		JScrollPane jpsiRoyal = new JScrollPane(jlFiles_Royal);
		jpsiRoyal.setBounds(273, 36, 246, 330);
		pnlRoyalManagement.add(jpsiRoyal);

		JLabel lblGerenciarMensagens1 = new JLabel("Errors");
		lblGerenciarMensagens1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblGerenciarMensagens1.setBounds(10, 11, 253, 16);
		pnlRoyalManagement.add(lblGerenciarMensagens1);

		btnEdit_Royal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnEdit_Royal.setBounds(430, 395, 89, 23);
		pnlRoyalManagement.add(btnEdit_Royal);

		JButton btnAddNewRoyal = new JButton("Add New");
		btnAddNewRoyal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnAddNewRoyal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClearRoyal();
				loadRoyalWriter();
			}
		});
		btnAddNewRoyal.setBounds(334, 395, 89, 23);
		pnlRoyalManagement.add(btnAddNewRoyal);

		btnEdit_Royal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				royalManagement objexl = new royalManagement();

				if (jlFiles_Royal.getSelectedIndex() > -1) {
					try {
						loadRoyalWriter();

						String returnPath = objexl.FindFolder(jlErrors_Royal.getSelectedValue(),
								jlFiles_Royal.getSelectedValue().toString());

						compiler.royal.main.Main.main(returnPath, jlErrors_Royal.getSelectedValue(),
								jlFiles_Royal.getSelectedValue().toString());

						setValues_Royal();

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else
					JOptionPane.showMessageDialog(null, "You must to select an Error Message.", "Required",
							JOptionPane.WARNING_MESSAGE);

			}
		});

		jlErrors_Royal.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if (jlErrors_Royal.getSelectedIndex() > -1)
					loadFiles_Royal();
			}
		});

		// SIEL WRITER
		pnlSielWriter.setLayout(null);
		pnlSielWriter.setBounds(174, 41, 529, 412);

		frame.getContentPane().add(pnlSielWriter);

		errorList_Siel();

		ErrorList list = new ErrorList();

		JLabel lblParameter = new JLabel("Parameters * (separe then with ',')");
		lblParameter.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblParameter.setBounds(10, 71, 221, 16);
		pnlSielWriter.add(lblParameter);

		txtParameter_Siel = new JTextArea();
		txtParameter_Siel.setWrapStyleWord(true);
		txtParameter_Siel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtParameter_Siel.setText("");
		txtParameter_Siel.setBounds(10, 96, 504, 249);
		pnlSielWriter.add(txtParameter_Siel);
		txtParameter_Siel.setColumns(10);

		btnSave_Siel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnSave_Siel.setBounds(326, 356, 89, 23);
		pnlSielWriter.add(btnSave_Siel);

		cmbErrorList_Siel = new JComboBox(list.returnError().toArray());
		cmbErrorList_Siel.setBackground(Color.WHITE);
		cmbErrorList_Siel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		cmbErrorList_Siel.setBounds(10, 40, 504, 20);
		cmbErrorList_Siel.setSelectedIndex(-1);
		pnlSielWriter.add(cmbErrorList_Siel);

		JLabel lblErrorName = new JLabel("Error name *");
		lblErrorName.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblErrorName.setBounds(10, 15, 89, 16);
		pnlSielWriter.add(lblErrorName);

		txtErrorName_Siel = new JTextField();
		txtErrorName_Siel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtErrorName_Siel.setBounds(10, 40, 504, 20);
		pnlSielWriter.add(txtErrorName_Siel);
		txtErrorName_Siel.setColumns(10);
		txtErrorName_Siel.setVisible(false);

		btnUpdate_Siel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnUpdate_Siel.setVisible(false);
		btnUpdate_Siel.setBounds(326, 356, 89, 23);
		pnlSielWriter.add(btnUpdate_Siel);

		btnSave_Siel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save_Siel();
			}
		});

		btnUpdate_Siel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				sielManagement objSielMan = new sielManagement();
				objSielMan.deleteError(filename_Siel);

				save_Siel();
				errorList_Siel();

			}
		});

		JButton btncancelSiel = new JButton("Cancel");
		btncancelSiel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btncancelSiel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadSielManagement();
			}
		});
		btncancelSiel.setBounds(425, 356, 89, 23);
		pnlSielWriter.add(btncancelSiel);

		// SIEL MANAGEMENT
		pnlSielManagement.setLayout(null);
		pnlSielManagement.setBounds(174, 41, 529, 430);

		frame.getContentPane().add(pnlSielManagement);

		JLabel lblerrors = new JLabel("Errors");
		lblerrors.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblerrors.setBounds(10, 11, 89, 16);
		pnlSielManagement.add(lblerrors);

		jlFiles_Siel = new JList(errorModel_Siel);
		jlFiles_Siel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		jlFiles_Siel.setAutoscrolls(true);
		jlFiles_Siel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		jlFiles_Siel.setBounds(10, 36, 253, 370);
		JScrollPane jpsi = new JScrollPane(jlFiles_Siel);
		jpsi.setBounds(10, 36, 253, 370);
		pnlSielManagement.add(jpsi);

		JLabel lblParameters2 = new JLabel("Parameters");
		lblParameters2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblParameters2.setBounds(273, 11, 155, 16);
		pnlSielManagement.add(lblParameters2);

		jlParameters_Siel = new JList(parametersModel_Siel);
		jlParameters_Siel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		jlParameters_Siel.setBounds(273, 36, 246, 370);
		pnlSielManagement.add(jlParameters_Siel);

		btnEdit_Siel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnEdit_Siel.setBounds(430, 356, 89, 23);
		btnEdit_Siel.setEnabled(false);
		// pnlSielManagement.add(btnEdit_Siel);

		btnDelete_Siel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnDelete_Siel.setEnabled(false);
		btnDelete_Siel.setBounds(339, 356, 89, 23);
		// pnlSielManagement.add(btnDelete_Siel);

		JButton btnAddNewSiel = new JButton("Add new");
		btnAddNewSiel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnAddNewSiel.setBounds(245, 356, 89, 23);
		// pnlSielManagement.add(btnAddNewSiel);

		jlFiles_Siel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				loadParameters_Siel();
			}
		});

		btnAddNewSiel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadSielWriter();
			}
		});

		btnDelete_Siel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (jlFiles_Siel.getSelectedIndex() > -1) {
					if (JOptionPane
							.showConfirmDialog(
									null, "Are you sure you want to delete "
											+ jlFiles_Sepia.getSelectedValue().toString() + " and all parameters?",
									"Confirm", JOptionPane.YES_NO_OPTION) == 0) {

						delete_Siel();
					}
				} else
					JOptionPane.showMessageDialog(null, "You must to select an Error.", "Required",
							JOptionPane.WARNING_MESSAGE);

			}
		});

		btnEdit_Siel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (jlFiles_Siel.getSelectedIndex() > -1) {
					SetValues_Siel();
					btnSave_Siel.setVisible(false);
					btnUpdate_Siel.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "You must to select an Error.", "Required",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});

		errorList_Siel();

		// CONFIGURATION
		pnlConfiguration = new JPanel();
		pnlConfiguration.setBounds(174, 41, 529, 430);
		frame.getContentPane().add(pnlConfiguration);
		pnlConfiguration.setLayout(null);
		pnlConfiguration.setFont(new Font("Segoe UI", Font.PLAIN, 12));

		txtFolder_Config = new JTextField();
		txtFolder_Config.setText((String) null);
		txtFolder_Config.setColumns(10);
		txtFolder_Config.setBounds(10, 40, 470, 25);
		txtFolder_Config.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		pnlConfiguration.add(txtFolder_Config);

		JLabel lblfolder = new JLabel("Default Folder:");
		lblfolder.setBounds(10, 15, 89, 20);
		lblfolder.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		pnlConfiguration.add(lblfolder);

		JLabel lblNewLabel = new JLabel("Messages Language: ");
		lblNewLabel.setBounds(10, 85, 200, 20);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		pnlConfiguration.add(lblNewLabel);

		JComboBox cbxLanguage = new JComboBox();
		cbxLanguage.setModel(new DefaultComboBoxModel(new String[] { "English", "Portuguese" }));
		cbxLanguage.setBounds(10, 105, 470, 25);
		cbxLanguage.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		pnlConfiguration.add(cbxLanguage);

		btnSave_Config = new JButton("Save");
		btnSave_Config.setBounds(430, 395, 89, 23);
		btnSave_Config.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		pnlConfiguration.add(btnSave_Config);

		JButton btnChooseFile = new JButton("");
		btnChooseFile.setIcon(
				new ImageIcon(frmEMS_new.class.getResource("/javax/swing/plaf/metal/icons/ocean/newFolder.gif")));

		btnChooseFile.setToolTipText("Open");
		btnChooseFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser jfcOpen = new JFileChooser();
				jfcOpen.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				jfcOpen.setDialogTitle("Please select a folder:");
				jfcOpen.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int retorno = jfcOpen.showOpenDialog(null);
				if (retorno == JFileChooser.APPROVE_OPTION)
					txtFolder_Config.setText(jfcOpen.getSelectedFile().getPath());
			}
		});

		txtFolder_Config.setText(CheckFile_Config());
		if (txtFolder_Config.equals("")) {
			txtFolder_Config.setText("../data/");
		}

		btnChooseFile.setBounds(490, 38, 29, 23);

		pnlConfiguration.add(btnChooseFile);

		btnSave_Config.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (txtFolder_Config.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please select a default folder.", "Required",
							JOptionPane.WARNING_MESSAGE);

				} else {
					Save_Config();

				}
			}
		});

		createTree();

		loadConfigurations();
	}

	void doMouseClicked(MouseEvent me) {
		TreePath tp = tree.getPathForLocation(me.getX(), me.getY());

		if (tp != null && tp.getLastPathComponent().toString().equals("Sepia")) {
			loadSepiaManagement();

		}

		else if (tp != null && tp.getLastPathComponent().toString().equals("Royal")) {
			loadRoyalManagement();

		} else if (tp != null && tp.getLastPathComponent().toString().equals("Siel")) {
			loadSielManagement();

		} else if (tp != null && tp.getLastPathComponent().toString().equals("Configuration")) {
			loadConfigurations();

		}
	}

	/**
	 * Creates a tree with the EMS components
	 */
	void close() {
		if (JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "EMS - Exit",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
			frame.dispose();
		}
	}

	void createTree() {

		DefaultMutableTreeNode EMS = new DefaultMutableTreeNode("EMS");
		DefaultTreeModel tmodel = new DefaultTreeModel(EMS);

		tree = new JTree(tmodel);
		tree.setShowsRootHandles(true);
		tree.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		tree.setBounds(0, 0, 166, 471);
		frame.getContentPane().add(tree);

		// change default icons
		DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) tree.getCellRenderer();
		Icon closedIcon = new ImageIcon("/icons/closed.png");
		Icon openIcon = new ImageIcon("/icons/open.png");
		Icon leafIcon = new ImageIcon("/icons/leaf.png");
		renderer.setClosedIcon(closedIcon);
		renderer.setOpenIcon(openIcon);
		renderer.setLeafIcon(leafIcon);

		// create nodes

		DefaultMutableTreeNode Messages = new DefaultMutableTreeNode("Messages");

		DefaultMutableTreeNode Siel = new DefaultMutableTreeNode("Siel");

		DefaultMutableTreeNode Royal = new DefaultMutableTreeNode("Royal");

		DefaultMutableTreeNode Sepia = new DefaultMutableTreeNode("Sepia");

		DefaultMutableTreeNode Configuration = new DefaultMutableTreeNode("Configuration");

		// Add nodes to tree

		tmodel.insertNodeInto(Messages, EMS, 0);

		tmodel.insertNodeInto(Siel, Messages, 0);

		tmodel.insertNodeInto(Royal, Messages, 0);

		tmodel.insertNodeInto(Sepia, Messages, 0);

		tmodel.insertNodeInto(Configuration, EMS, 0);

		tree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				doMouseClicked(e);
			}
		});

		expandTree(tree);

		lblLblhelp = new JLabel("");
		lblLblhelp.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblLblhelp.setBounds(38, 482, 432, 38);
		frame.getContentPane().add(lblLblhelp);

	}

	void expandTree(JTree tree) {
		TreePath tp = tree.getPathForLocation(tree.getX(), tree.getY());

		try {
			for (int row = 0; row <= tree.getRowCount(); row++) {
				tree.expandRow(row);

			}
			tree.addSelectionRow(1);
		} catch (Exception e) {
			// tratar erro
		}
	}

	public void loadSepiaWriter() {

		pnlSepiaManagement.setVisible(false);
		pnlSepiaWriter.setVisible(true);
		pnlConfiguration.setVisible(false);
		pnlRoyalManagement.setVisible(false);
		pnlRoyalWriter.setVisible(false);
		pnlSielWriter.setVisible(false);
		pnlSielManagement.setVisible(false);
		lblTitle.setText("Sepia");
		lblLblhelp.setText("<html> Sepia allows you to configure a specific message in the error context.</html>");

	}

	void loadSepiaManagement() {// sepia
		// carrega jlist
		errorList_Sepia();
		pnlSepiaManagement.setVisible(true);
		pnlSepiaWriter.setVisible(false);
		pnlConfiguration.setVisible(false);
		pnlRoyalManagement.setVisible(false);
		pnlRoyalWriter.setVisible(false);
		pnlSielWriter.setVisible(false);
		pnlSielManagement.setVisible(false);

		lblTitle.setText("Sepia");
		lblLblhelp.setText("<html> Sepia allows you to configure a specific message in the error context.</html>");

	}

	void loadRoyalWriter() {
		pnlSepiaManagement.setVisible(false);
		pnlSepiaWriter.setVisible(false);
		pnlConfiguration.setVisible(false);
		pnlRoyalManagement.setVisible(false);
		pnlRoyalWriter.setVisible(true);
		pnlSielWriter.setVisible(false);
		pnlSielManagement.setVisible(false);

		lblTitle.setText("Royal");
		lblLblhelp.setText(
				"<html> Royal allows you to configure a short message that describe the error occurred.</html>");

	}

	void loadRoyalManagement() {// royal
		// carrega jlist
		errorList_Royal();
		pnlSepiaManagement.setVisible(false);
		pnlSepiaWriter.setVisible(false);
		pnlConfiguration.setVisible(false);
		pnlRoyalManagement.setVisible(true);
		pnlRoyalWriter.setVisible(false);
		pnlSielWriter.setVisible(false);
		pnlSielManagement.setVisible(false);

		lblTitle.setText("Royal");
		lblLblhelp.setText(
				"<html>Royal allows you to configure a short message that describe the error occurred.</html>");

	}

	void loadSielWriter() {

		pnlSepiaManagement.setVisible(false);
		pnlSepiaWriter.setVisible(false);
		pnlConfiguration.setVisible(false);
		pnlRoyalManagement.setVisible(false);
		pnlRoyalWriter.setVisible(false);
		pnlSielWriter.setVisible(true);
		pnlSielManagement.setVisible(false);

		lblTitle.setText("Siel");
		lblLblhelp.setText("<html> Siel defines the parameters for each error.</html>");

	}

	void save_Siel() {
		sielWriter objsiel = new sielWriter();

		if (txtParameter_Siel.getText().equals("") || cmbErrorList_Siel.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(frame, "All fields are required.", "Required", JOptionPane.WARNING_MESSAGE);

		} else {
			objsiel.setKey(txtParameter_Siel.getText());

			if (objsiel.checkParameter(cmbErrorList_Siel.getSelectedItem().toString(), objsiel)) {
				objsiel.Save(cmbErrorList_Siel.getSelectedItem().toString(), objsiel);

				errorList_Siel();

				cmbErrorList_Siel.setSelectedIndex(-1);
				txtErrorName_Siel.setText("");
				txtParameter_Siel.setText("");

				btnSave_Siel.setEnabled(false);
			}

		}
	}

	void loadSielManagement() {

		pnlSielManagement.setVisible(true);
		pnlSepiaManagement.setVisible(false);
		pnlSepiaWriter.setVisible(false);
		pnlConfiguration.setVisible(false);
		pnlRoyalManagement.setVisible(false);
		pnlRoyalWriter.setVisible(false);
		pnlSielWriter.setVisible(false);

		lblTitle.setText("Siel");
		lblLblhelp.setText("<html> Siel defines the parameters for each error.</html>");
	}

	void loadConfigurations() {

		pnlConfiguration.setVisible(true);
		pnlSepiaWriter.setVisible(false);
		pnlRoyalManagement.setVisible(false);
		pnlRoyalWriter.setVisible(false);
		pnlSepiaManagement.setVisible(false);
		pnlSielWriter.setVisible(false);
		pnlSielManagement.setVisible(false);

		lblTitle.setText("Preferences");

		lblLblhelp.setText("<html> You must to choose a default folder to save EMS file messages.</html>");

	}

	void Save_Config() {
		File diretorio = new File(System.getProperty("user.home") + "/data/");

		if (!diretorio.exists()) {

			diretorio.mkdirs();
		} else {

			FileManagement fileman = new FileManagement();
			fileman.DeleteFile(System.getProperty("user.home") + "/data/Configuration.conf");
		
		}

		File filename = new File(diretorio + "/" + "Configuration.conf");

		FileWriter x;
		try {
			x = new FileWriter(filename, true);
			x.write(txtFolder_Config.getText() + "/");
			x.close();

			btnSave_Config.setEnabled(false);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

			e.printStackTrace();

		}

	}

	public String CheckFile_Config() {

		FileManagement fileman = new FileManagement();

		String folder = null;
		File file = new File(System.getProperty("user.home") + "/data/Configuration.conf");

		if (file.exists()) {
			try {
				ArrayList<String> directory = fileman.ReadFile(file);
				folder = directory.get(0);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return folder.replace("/", "").replace("  ", "");
		} else {
			File diretorio = new File(System.getProperty("user.home") + "/data/");

			diretorio.mkdirs();
		}
		return null;

	}

	public void loadParameters_Siel() {
		sielManagement objSielMan = new sielManagement();

		if (jlFiles_Siel.getSelectedIndex() > -1) {

			parametersModel_Siel.removeAllElements();

			ArrayList<String> content = null;
			try {
				content = objSielMan.openFile(jlFiles_Siel.getSelectedValue().toString());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (int i = 0; i < content.size(); i++) {

				parametersModel_Siel.addElement(content.get(i).trim());

			}
		}

	}

	private void errorList_Sepia() {
		FileManagement objDir = new FileManagement();
		ErrorList errorList = new ErrorList();

		// Lista nome dos erros referente as pastas
		String[] list;
		String name = null;

		errorModel_Sepia.removeAllElements();

		// Lista as pastas com nome do erro
		list = FileManagement.listDirectory(objDir.DefaultPath());

		if (list != null) {
			for (int i = 0; i < list.length; i++) {

				name = errorList.returnerrorName(list[i].toString());
				if (name != null) {

					// Procura por arquivos dentro do diretório
					for (String path : objDir.FilePath(list[i].toString()).list()) {

						if (path.endsWith(".sepia")) {

							errorModel_Sepia.addElement(name);
							break;
						}
					}
				}

			}
		}

		if (errorModel_Sepia.size() > 0) {
			btnEdit_Sepia.setEnabled(true);
		} else {
			btnEdit_Sepia.setEnabled(false);
		}
	}

	private void errorList_Siel() {
		// Lista nome dos erros referente as pastas
		ErrorList errorList = new ErrorList();
		FileManagement objDir = new FileManagement();

		String[] list = null;
		String name = null;

		errorModel_Siel.removeAllElements();

		// Lista as pastas com nome do erro
		list = FileManagement.listDirectory(objDir.DefaultPath());

		if (list != null) {
			for (int i = 0; i < list.length; i++) {
				name = errorList.returnerrorName(list[i].toString());

				if (name == null)
					name = list[i].toString().replace("_", " ");

				if (errorModel_Siel.contains(name)) {
				} else {
					File path = objDir.FilePath(list[i].toString() + "/Parameters.siel");

					if (path.exists()) {

						errorModel_Siel.addElement(name);
					}

				}
			}
		}

		if (errorModel_Siel.size() > 0) {
			btnEdit_Siel.setEnabled(true);
			btnDelete_Siel.setEnabled(true);
		} else {
			btnEdit_Siel.setEnabled(false);
			btnDelete_Siel.setEnabled(false);
		}

	}

	private void SetValues_Siel() {

		filename_Siel = jlFiles_Siel.getSelectedValue().toString();

		if (jlFiles_Siel.getSelectedIndex() > -1) {

			loadSielWriter();

			for (int i = 0; i < jlParameters_Siel.getModel().getSize(); i++) {
				txtParameter_Siel.setText(
						txtParameter_Siel.getText() + jlParameters_Siel.getModel().getElementAt(i).toString() + ", ");
			}

			cmbErrorList_Siel.setSelectedItem(filename_Siel);
			txtErrorName_Siel.setVisible(true);
			txtErrorName_Siel.setText(filename_Siel);
			txtErrorName_Siel.setEnabled(false);

			try {
				txtParameter_Siel
						.setText(txtParameter_Siel.getText(0, txtParameter_Siel.getText().length() - 1).toString());
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else
			JOptionPane.showMessageDialog(null, "You must to select an Error.", "Required",
					JOptionPane.WARNING_MESSAGE);
	}

	public void loadFiles_Sepia() {
		ErrorList errorList = new ErrorList();
		FileManagement objDir = new FileManagement();

		// Lista nome dos erros referente as pastas
		String folderName = null;
		String[] fileName;
		filesModel_Sepia.removeAllElements();

		if (filesModel_Sepia.size() == 0) {

			folderName = errorList.returnPath(jlErrors_Sepia.getSelectedValue());

			if (folderName != null) {
				// Procura os arquivos dentro do diretório
				for (String file : objDir.FilePath(folderName).list()) {

					if (file.endsWith(".sepia")) {

						fileName = file.split(".sepia");

						filesModel_Sepia.addElement(fileName[0].toString());

					}
				}
			}
			if (filesModel_Sepia.size() > 0) {
				btnEdit_Sepia.setEnabled(true);
			}
		}
	}

	boolean validateSepia() {

		boolean flag = true;

		if (cmbErrorList_Sepia.getSelectedIndex() == -1) {
			tbSepia.setSelectedIndex(0);
			cmbErrorList_Sepia.requestFocus();
			flag = false;
			JOptionPane.showMessageDialog(frame, "'Error Name' are required.", "Required", JOptionPane.WARNING_MESSAGE);

		} else if (txtExplanation_Sepia.getText().equals("")) {
			txtExplanation_Sepia.requestFocus(true);
			tbSepia.setSelectedIndex(0);
			flag = false;
			JOptionPane.showMessageDialog(frame, "'Explanation' are required.", "Required",
					JOptionPane.WARNING_MESSAGE);

		} else if (jlistCauses_Sepia.getModel().getSize() < 0 && txtCause_Sepia.getText().equals("")) {
			txtCause_Sepia.requestFocus();
			tbSepia.setSelectedIndex(1);
			flag = false;
			JOptionPane.showMessageDialog(frame, "'Cause' are required.", "Required", JOptionPane.WARNING_MESSAGE);

		} else if (jlistSource_Sepia.getModel().getSize() < 0 && txtSource_Sepia.getText().equals("")) {
			txtSource_Sepia.requestFocus();
			tbSepia.setSelectedIndex(2);
			flag = false;
			JOptionPane.showMessageDialog(frame, "Example are required.", "Required", JOptionPane.WARNING_MESSAGE);
		}
		return flag;
	}

	public void SaveSepia() {

		sepiaWriter objsepia = new sepiaWriter();
		try {

			if (validateSepia()) {

				int sizesource = jlistSource_Sepia.getModel().getSize();
				int sizecause = jlistCauses_Sepia.getModel().getSize();
				int size = jlParameters_Sepia1.getModel().getSize();
				String cause = null;

				// Verifica se existe mais de um exemplo, se houver add
				// todos ao array, senão add o que está na cx de texto

				if (sizesource > 0) {
					for (int i = 0; i < sizesource; i++) {
						sourceCodeArray_Sepia
								.add(new SourceCode(jlistSource_Sepia.getModel().getElementAt(i).toCharArray()));
					}
				} else
					sourceCodeArray_Sepia.add(new SourceCode(txtSource_Sepia.getText().toCharArray()));

				if (sizecause > 0) {
					for (int i = 0; i < sizecause; i++) {
						explanationArray2_Sepia
								.add(new Explanation2(jlistCauses_Sepia.getModel().getElementAt(i).toCharArray()));
					}
				} else {
					cause = txtCauselabel_Sepia.getText() + "-" + txtCause_Sepia.getText();
					explanationArray2_Sepia.add(new Explanation2(cause.toCharArray()));
				}
				if (size > 0) {
					for (int i = 0; i < size; i++) {
						parametersArray_Sepia
								.add(new String(jlParameters_Sepia1.getModel().getElementAt(i).toString()));
					}
				}

				explanationArray_Sepia.add(new Explanation(txtExplanation_Sepia.getText().toCharArray()));

				// objsepia.setCauseLabel(txtCauselabel_Sepia.getText().toString());
				// objsepia.setCause(causeArray_Sepia);
				objsepia.setText(explanationArray_Sepia);
				objsepia.setSourceCodeArray(sourceCodeArray_Sepia);
				objsepia.setParametersArray(parametersArray_Sepia);
				objsepia.setUseThis(Boolean.valueOf(chckbxUse_Sepia.isSelected()));
				objsepia.setFileName(txtFileName_Sepia.getText());

				objsepia.setExplanation2(explanationArray2_Sepia);

				objsepia.Save2(fileName_Sepia, objsepia);

				ClearSepia();
				JOptionPane.showMessageDialog(null, "Message saved sucessfully", "Save",
						JOptionPane.INFORMATION_MESSAGE);

			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error saving message: " + ex.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);

		}

	}

	public void addParameter() {

		String Parameter = null;

		switch (Focus_Sepia) {

		case 1:
			Parameter = " #" + parametersModel_Sepia.getElementAt(jlParameters_Sepia1.getSelectedIndex()).toString();
			Parameter = Parameter.replace("\n", "");
			txtExplanation_Sepia.setText(txtExplanation_Sepia.getText() + Parameter);
			break;
		case 2:
			Parameter = " #" + parametersModel_Sepia.getElementAt(jlParameters_Sepia2.getSelectedIndex()).toString();
			Parameter = Parameter.replace("\n", "");
			txtCause_Sepia.setText(txtCause_Sepia.getText() + Parameter);
			break;
		case 3:
			Parameter = " #" + parametersModel_Sepia.getElementAt(jlParameters_Sepia3.getSelectedIndex()).toString();
			Parameter = Parameter.replace("\n", "");
			txtSource_Sepia.setText(txtSource_Sepia.getText() + Parameter);
			break;
		}

	}

	public void loadParametersSepia() {
		// limpa list

		parametersModel_Sepia.removeAllElements();
		sielManagement objsiel = new sielManagement();

		if (cmbErrorList_Sepia.getSelectedIndex() > -1) {
			fileName_Sepia = cmbErrorList_Sepia.getSelectedItem().toString();

			ArrayList<String> content = null;
			try {
				content = objsiel.openFile(fileName_Sepia);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (int i = 0; i < content.size(); i++) {
				parametersModel_Sepia.addElement(content.get(i));
			}
		}

	}

	public void SetFileName(JComboBox errorList, JTextField FileName) {
		try {
			String ErrorName;
			if (errorList.getSelectedItem().toString().length() > 30) {
				ErrorName = errorList.getSelectedItem().toString().substring(0, 30).replace("'", "").replace("´", "")
						.replace(" ", "_").replace(":", "");
			} else {
				ErrorName = errorList.getSelectedItem().toString().replace("'", "").replace("´", "").replace(" ", "_")
						.replace(":", "");
			}

			FileName.setText(ErrorName + InetAddress.getLocalHost().getHostName());
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void ClearSepia() {
		txtExplanation_Sepia.setText("");
		txtCauselabel_Sepia.setText("");
		txtCause_Sepia.setText("");
		txtSource_Sepia.setText("");
		cmbErrorList_Sepia.setSelectedIndex(-1);
		list_Sepia.removeAllElements();
		list_Sepia2.removeAllElements();
		txtFileName_Sepia.setText("");
		chckbxUse_Sepia.setSelected(false);
	}

	private void errorList_Royal() {
		FileManagement objDir = new FileManagement();
		ErrorList errorList = new ErrorList();

		errorModel_Royal.removeAllElements();

		// Lista nome dos erros referente as pastas
		String[] list;
		String name = null;

		// Lista as pastas com nome do erro
		list = FileManagement.listDirectory(objDir.DefaultPath());

		if (list != null) {
			for (int i = 0; i < list.length; i++) {
				name = errorList.returnerrorName(list[i].toString());
				if (name != null) {

					// Procura por arquivos dentro do diretório
					for (String path : objDir.FilePath(list[i].toString()).list()) {

						if (path.endsWith(".royal")) {
							errorModel_Royal.addElement(name);
							break;
						}
					}

				}
			}
		}

		if (errorModel_Royal.size() > 0) {
			btnEdit_Royal.setEnabled(true);
			btnDelete_Royal.setEnabled(true);
		} else {
			btnEdit_Royal.setEnabled(false);
			btnDelete_Royal.setEnabled(false);

		}
	}

	public void loadFiles_Royal() {
		// Lista nome dos erros referente as pastas
		ErrorList errorList = new ErrorList();
		FileManagement objDir = new FileManagement();

		String folderName = null;
		String[] fileName;

		filesModel_Royal.removeAllElements();

		folderName = errorList.returnPath(jlErrors_Royal.getSelectedValue().toString());

		// Procura os arquivos dentro do diretório
		for (String file : objDir.FilePath(folderName).list()) {

			if (file.endsWith(".royal")) {

				fileName = file.split(".royal");
				filesModel_Royal.addElement(fileName[0].toString());

			}
		}

	}

	public void setValuesRoyal(String errorName, String language, String languageInEnglish, String errorDescription,
			String explanation, String fileName, boolean useThis,
			ArrayList<compiler.royal.ast.SourceCode> sourceCodeArray) {

		this.errorName_Royal = errorName;
		this.language = language;
		this.languageInEnglish = languageInEnglish;
		this.errorDescription = errorDescription;
		this.explanation = explanation;
		this.fileName_Royal = fileName;
		this.useThis_Royal = useThis;
		this.sourceCodeArrayRoyal = sourceCodeArray;

	}

	private void setValues_Royal() {

		chckbxUseThis_Royal.setSelected(useThis_Royal);

		txtLanguage_Royal.setText(language);

		txtlanguageinEnglish_Royal.setText(languageInEnglish);

		txtErrorDescription_Royal.setText(errorDescription.replace("\0", ""));

		txtExplanation_Royal.setText(explanation.replace("\0", ""));

		cmbErrorList_Royal.setSelectedItem(errorName_Royal);
		cmbErrorList_Royal.setEnabled(false);

		btnSave_Royal.setVisible(false);
		btnUpdate_Royal.setVisible(true);
		btnDelete_Royal.setVisible(true);
		list_Royal.removeAllElements();

		if (sourceCodeArrayRoyal.size() > 0) {

			for (compiler.royal.ast.SourceCode s : sourceCodeArrayRoyal) {
				list_Royal.addElement(s.get());
			}
		}

	}

	boolean validateRoyal() {
		boolean flag = true;

		if (cmbErrorList_Royal.getSelectedItem().equals("")) {
			flag = false;
			JOptionPane.showMessageDialog(frame, "Error Name are required.", "Required", JOptionPane.WARNING_MESSAGE);
		} else if (txtLanguage_Royal.getText().equals("")) {
			flag = false;
			JOptionPane.showMessageDialog(frame, "Language are required.", "Required", JOptionPane.WARNING_MESSAGE);
		} else if (txtlanguageinEnglish_Royal.getText().equals("")) {
			flag = false;
			JOptionPane.showMessageDialog(frame, "Language in English are required.", "Required",
					JOptionPane.WARNING_MESSAGE);
		}

		else if (txtErrorDescription_Royal.getText().equals("")) {
			flag = false;
			JOptionPane.showMessageDialog(frame, "Error Description are required.", "Required",
					JOptionPane.WARNING_MESSAGE);

		} else if (txtExplanation_Royal.getText().equals("")) {
			flag = false;
			JOptionPane.showMessageDialog(frame, "Explanation are required.", "Required", JOptionPane.WARNING_MESSAGE);
		}

		else if (jlistSource_Royal.getModel().getSize() <= 0 && txtSource_Royal.getText().equals("")) {
			flag = false;
			JOptionPane.showMessageDialog(frame, "Example fields are required.", "Required",
					JOptionPane.WARNING_MESSAGE);
		}

		return flag;

	}

	public void SaveRoyal() {
		royalWriter objexE = new royalWriter();
		sourceCodeArray_Royal = new ArrayList<compiler.royal.ast.SourceCode>();
		ExplanationArray_Royal = new ArrayList<compiler.royal.ast.Explanation>();

		if (validateRoyal()) {
			int tamanho = jlistSource_Royal.getModel().getSize();

			// Verifica se existe mais de um exemplo, se houver add
			// todos ao array, senão add o que está na cx de texto
			if (tamanho > 0) {
				for (int i = 0; i < tamanho; i++) {
					sourceCodeArray_Royal.add(new compiler.royal.ast.SourceCode(
							jlistSource_Royal.getModel().getElementAt(i).toCharArray()));
				}
			} else
				sourceCodeArray_Royal.add(new compiler.royal.ast.SourceCode(txtSource_Royal.getText().toCharArray()));

			tamanho = jlistExplanation_Royal.getModel().getSize();

			if (tamanho > 0) {
				for (int i = 0; i < tamanho; i++) {
					ExplanationArray_Royal.add(new compiler.royal.ast.Explanation(
							jlistExplanation_Royal.getModel().getElementAt(i).toCharArray()));
				}
			} else
				ExplanationArray_Royal
						.add(new compiler.royal.ast.Explanation(txtExplanation_Royal.getText().toCharArray()));

			// objexE.setText(txtExplanation_Royal.getText().toString());
			objexE.setExplanationArray(ExplanationArray_Royal);

			objexE.setLanguage(txtLanguage_Royal.getText());
			objexE.setLanguageInEnglish(txtlanguageinEnglish_Royal.getText());
			objexE.setErrorDescription(txtErrorDescription_Royal.getText().toString());

			objexE.setSourceCodeArray(sourceCodeArray_Royal);
			objexE.setUseThis(chckbxUseThis_Royal.isSelected());
			objexE.setFileName(txtFileName_Royal.getText());

			objexE.Save(cmbErrorList_Royal.getSelectedItem().toString(), objexE);

			ClearRoyal();

			loadRoyalManagement();

		}

	}

	public void setValuesSepia(String errorName, Boolean useThis, String fileName,
			ArrayList<SourceCode> sourceCodeArray, ArrayList<Cause> causeArray, ArrayList<Explanation> explanationArray,
			ArrayList<Explanation2> explanationArray2) {
		frmEMS_new.errorName = errorName;
		frmEMS_new.useThis = useThis;
		frmEMS_new.fileName = fileName;
		frmEMS_new.sourceCodeArray = sourceCodeArray;
		frmEMS_new.causeArray = causeArray;
		frmEMS_new.explanationArray = explanationArray;
		frmEMS_new.explanationArray2 = explanationArray2;

	}

	public void ClearRoyal() {
		txtErrorDescription_Royal.setText("");
		txtExplanation_Royal.setText("");
		txtLanguage_Royal.setText("");
		txtlanguageinEnglish_Royal.setText("");
		txtSource_Royal.setText("");
		cmbErrorList_Royal.setSelectedIndex(-1);
		list_Royal.removeAllElements();
		txtFileName_Royal.setText("");
		chckbxUseThis_Royal.setSelected(false);
	}

	void delete_Siel() {
		sielManagement objSielMan = new sielManagement();
		objSielMan.deleteError(jlFiles_Sepia.getSelectedValue().toString());

		errorModel_Siel.removeAllElements();
		parametersModel_Siel.removeAllElements();
		errorList_Siel();

	}

	void setvalues_Sepia() {
		cmbErrorList_Sepia.setSelectedItem(errorName);

		cmbErrorList_Sepia.setEnabled(false);

		chckbxUse_Sepia.setSelected(useThis);

		txtFileName_Sepia.setText(fileName.toString());

		if (sourceCodeArray.size() > 0) {

			for (SourceCode s : sourceCodeArray) {
				list_Sepia.addElement(s.get());
			}
		}

		if (explanationArray2.size() > 0) {

			for (Explanation2 c : explanationArray2) {
				list_Sepia2.addElement(c.get());

			}
		}

		if (explanationArray.size() > 0) {

			for (Explanation e : explanationArray) {
				txtExplanation_Sepia.setText(e.get());
			}
		}

		btnSave_Sepia.setVisible(false);
		btnUpdate_Sepia.setVisible(true);
		btnDelete_Sepia.setVisible(true);

	}
}
