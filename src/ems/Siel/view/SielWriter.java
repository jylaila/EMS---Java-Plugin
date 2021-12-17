package ems.Siel.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;



import ems.Siel.model.sielManagement;
import ems.Siel.model.sielWriter;
import ems.Siel.view.*;
import ems.management.ErrorList;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class SielWriter {

	sielWriter objsiel = new sielWriter();

	JFrame frame;
	public JTextArea txtParameter;
	public JTextField txtErrorName;
	public JComboBox cmbErrorList;
	public JButton btnUpdate;
	public JButton btnDelete;
	public JButton btnSave;

	final ErrorList list = new ErrorList();
	private JButton btnExit;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SielWriter window = new SielWriter();
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
	public SielWriter() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Siel");
		frame.setBounds(100, 100, 450, 281);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 243);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblParameter = new JLabel("Parameters * (separe then with ',')");
		lblParameter.setBounds(10, 71, 221, 14);
		panel.add(lblParameter);

		txtParameter = new JTextArea();
		txtParameter.setText("");
		txtParameter.setBounds(10, 96, 414, 77);
		panel.add(txtParameter);
		txtParameter.setColumns(10);

		btnSave = new JButton("Save");
		btnSave.setBounds(241, 209, 89, 23);
		panel.add(btnSave);

		cmbErrorList = new JComboBox(list.returnError().toArray());
		cmbErrorList.setBounds(10, 40, 414, 20);
		cmbErrorList.setSelectedIndex(-1);
		panel.add(cmbErrorList);

		JLabel lblErrorName = new JLabel("Error name *");
		lblErrorName.setBounds(10, 15, 89, 14);
		panel.add(lblErrorName);

		txtErrorName = new JTextField();
		txtErrorName.setBounds(10, 40, 381, 20);
		panel.add(txtErrorName);
		txtErrorName.setColumns(10);

		btnUpdate = new JButton("Update");
		btnUpdate.setVisible(false);
		btnUpdate.setBounds(241, 209, 89, 23);
		panel.add(btnUpdate);

		btnDelete = new JButton("Delete");
		btnDelete.setVisible(false);
		btnDelete.setBounds(142, 209, 89, 23);
		panel.add(btnDelete);

		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnExit.setBounds(335, 209, 89, 23);
		panel.add(btnExit);
		txtErrorName.setVisible(false);

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (Validate()) {
			
					objsiel.setKey(txtParameter.getText());
					
					if(objsiel.checkParameter(cmbErrorList.getSelectedItem().toString(), objsiel))
					{
					objsiel.Save(cmbErrorList.getSelectedItem().toString(),
							objsiel);
					Clear();
					}
				}
			}
		});

		btnUpdate.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				objsiel.setKey(txtParameter.getText());
				try {
					objsiel.Update(txtErrorName.getText(), objsiel);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				frame.dispose();

			}
		});

		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					objsiel.Delete(txtErrorName.getText(), txtParameter.getText()
							.toString());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	
				frame.dispose();
			}
		});

	}

	private void Clear() {
		txtParameter.setText("");
		cmbErrorList.setSelectedIndex(-1);
	}

	private boolean Validate() {
		
		boolean value = true;

		if (txtParameter.getText().equals("")
			|| cmbErrorList.getSelectedItem().equals("")) 
			{
			JOptionPane.showMessageDialog(frame,
				    "All fields are required.",
				    "Warning",
				    JOptionPane.WARNING_MESSAGE);

			value = false;
		}
	
		return value;
	}
}
