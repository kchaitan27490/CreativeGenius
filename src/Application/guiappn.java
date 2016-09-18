package Application;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JButton;

import sun.jdbc.odbc.ee.DataSource;
import weka.core.Instances;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import krishna.examples.implementer;
import krishna.processing.reducts.Myreductalgorithm;
import krishna.structure.attribute.formats.HeaderFormatException;
import krishna.structure.data.formats.DataFormatException;
import krishna.structure.table.ArrayListDoubleDataTable;
import krishna.structure.table.DoubleDataTable;
import krishna.system.PropertyConfigurationException;
import krishna.system.progress.EmptyProgress;

import javax.swing.JTextField;

import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;

import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.JTextPane;

public class guiappn {

	private JFrame frmMultimediaFeatureExtraction;
	private JFrame frmAttributeViewer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					guiappn window = new guiappn();
					window.frmMultimediaFeatureExtraction.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public guiappn(Boolean bool)
	{
		if(bool==true)
		{
		Componentcaller();
		}
	}
	public void Componentcaller()
	{
		frmAttributeViewer = new JFrame();
		frmAttributeViewer.setTitle("Attribute Viewer");
		frmAttributeViewer.setBounds(100, 100, 450, 300);
		frmAttributeViewer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAttributeViewer.getContentPane().setLayout(null);
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnExit.setBounds(169, 203, 89, 23);
		
	
	}
	public guiappn() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	String filename;
	File finalfile;
	public String filechooser()
	{
		JFileChooser Jfilenew= new JFileChooser();
		Jfilenew.setDialogTitle("Open");
		Jfilenew.showOpenDialog(null);
		File file=Jfilenew.getSelectedFile();
		final String fname= file.getAbsolutePath().toString();
		filename=fname;
		finalfile=file;
		return fname;
	}
	
	
	private void initialize() {
		frmMultimediaFeatureExtraction = new JFrame();
		frmMultimediaFeatureExtraction.setTitle("Multimedia Feature Extraction");
		frmMultimediaFeatureExtraction.setBounds(100, 100, 450, 300);
		frmMultimediaFeatureExtraction.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMultimediaFeatureExtraction.getContentPane().setLayout(null);
		final implementer im=new implementer();
		
		
		JButton btnNewButton = new JButton("Load DataSet");
		btnNewButton.setToolTipText("Enables user to load data set to perform reduct construction operations.");
		btnNewButton.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent arg0) {
				
				im.Fileloader(filechooser());
			}
		});
		btnNewButton.setBounds(44, 61, 106, 23);
		frmMultimediaFeatureExtraction.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("View attributes");
				
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					guiappn window1=new guiappn(true);
					window1.frmAttributeViewer.setVisible(true);
					
					
					//System.out.println(im.Attributeviewer(filename));
				 //catch (IOException e) {
					// TODO Auto-generated catch block
			//		e.printStackTrace();
				//} catch (HeaderFormatException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
			//	} catch (DataFormatException e) {
					// TODO Auto-generated catch block
				//	e.printStackTrace();
			//	} catch (InterruptedException e) {
					// TODO Auto-generated catch block
				//	e.printStackTrace();
				//}
			}
		});
		btnNewButton_1.setBounds(44, 115, 106, 23);
		frmMultimediaFeatureExtraction.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Attribute Ranker");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
								
				try {
					int[] ranks=im.RankerCaller(filename);
					for(int i=0;i<ranks.length;i++)
					System.out.println(ranks[i]);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
		}
		});
		btnNewButton_2.setBounds(256, 61, 127, 23);
		frmMultimediaFeatureExtraction.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Reduct Constructor");
		String msg="inside reduct button";
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					//JTextPane txtpnHi = new JTextPane();
					//txtpnHi.setEditable(false);
					//txtpnHi.setBounds(268, 168, 106, 87);
					//frmMultimediaFeatureExtraction.getContentPane().add(txtpnHi);
					ArrayList<String> reductset;
					try {
						reductset = im.FinalReductProducer(filename);
					
					//txtpnHi.setText(reductset);
					}
					 catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton_3.setBounds(256, 115, 127, 23);
		frmMultimediaFeatureExtraction.getContentPane().add(btnNewButton_3);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnExit.setBounds(169, 203, 89, 23);
		frmMultimediaFeatureExtraction.getContentPane().add(btnExit);
		
		JButton btnNewButton_4 = new JButton("View Dataset");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					System.out.println(im.DataViewer(filename));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (HeaderFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DataFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton_4.setBounds(44, 165, 106, 23);
		frmMultimediaFeatureExtraction.getContentPane().add(btnNewButton_4);
		
		JLabel lblDataIntensiveTasks = new JLabel("Data Descriptive tasks");
		lblDataIntensiveTasks.setBounds(44, 25, 115, 14);
		frmMultimediaFeatureExtraction.getContentPane().add(lblDataIntensiveTasks);
		
		JLabel lblMainModules = new JLabel("Data Intensive tasks");
		lblMainModules.setBounds(256, 25, 127, 14);
		frmMultimediaFeatureExtraction.getContentPane().add(lblMainModules);
		
		
		
		
		
	}
}
