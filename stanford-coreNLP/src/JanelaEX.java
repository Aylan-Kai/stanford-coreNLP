import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import java.awt.BorderLayout;
import java.awt.Choice;
import javax.swing.JPanel;
import java.awt.List;
import javax.swing.JRadioButton;
import java.awt.Scrollbar;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileSystemView;
import java.awt.Window.Type;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;
import edu.stanford.nlp.io.*;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.util.*;
import javax.swing.JProgressBar;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

public class JanelaEX {

	private JFrame frmInterpretador;
	ArrayList<String> ind = new ArrayList<String>();
	ArrayList<String> rel = new ArrayList<String>();
	ArrayList<String> clas = new ArrayList<String>();
	private JTextField textField;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaEX window = new JanelaEX();
					window.frmInterpretador.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public JanelaEX() {
		initialize();
	}
	private void initialize() {
		frmInterpretador = new JFrame();
		frmInterpretador.setTitle("Interpretador");
		frmInterpretador.setBounds(100, 100, 650, 450);
		frmInterpretador.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmInterpretador.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(28, 48, 470, 20);
		frmInterpretador.getContentPane().add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 131, 171, 188);
		frmInterpretador.getContentPane().add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setFont(new Font("Arial", Font.PLAIN, 11));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(240, 131, 142, 188);
		frmInterpretador.getContentPane().add(scrollPane_1);
		
		JTextArea textArea_1 = new JTextArea();
		scrollPane_1.setViewportView(textArea_1);
		textArea_1.setFont(new Font("Arial", Font.PLAIN, 11));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(431, 131, 177, 188);
		frmInterpretador.getContentPane().add(scrollPane_2);
		
		JTextArea textArea_2 = new JTextArea();
		scrollPane_2.setViewportView(textArea_2);
		textArea_2.setFont(new Font("Arial", Font.PLAIN, 11));
		
		JButton btnNewButton_1 = new JButton("Diretorios");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File f = new File("C:\\Users\\pc\\Documents\\New folder\\"); 
		JFileChooser j = new JFileChooser(f, FileSystemView.getFileSystemView()); 
		j.showOpenDialog(null);
		textField.setText(j.getSelectedFile().getPath());
			}
		});
		btnNewButton_1.setBounds(508, 47, 100, 23);
		frmInterpretador.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Iniciar");
		btnNewButton.setFont(new Font("Arial Black", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] englishArgs = new String[]{"-file", textField.getText(), "-outputFormat", "text", "-props", "english.properties"};
				try {
				StanfordCoreNLP.main(englishArgs);
				} catch (IOException e) {
					e.printStackTrace();
				}
				try{
					  	PrintStream anotarObj = new PrintStream("inicial.txt");
						BufferedReader brr = new BufferedReader(new FileReader("C:\\Users\\Matheus\\eclipse-workspace\\stanford-coreNLP\\Exemplo.txt.out"));
						while(brr.ready()){
							String linha = brr.readLine();
							if(linha.startsWith("1.0	")) {
								anotarObj.print(linha);
							}
						}
						brr.close();
				}catch(IOException ioe){
						ioe.printStackTrace();
				}
				try {
				PrintStream anotarObj = new PrintStream("final.txt");
				BufferedReader brp = new BufferedReader(new FileReader("inicial.txt"));
				while(brp.ready()) {
					String linha1= brp.readLine();
					String rem = linha1.replace("1.0", "");
					String fim = rem.replaceFirst("	", "");
					String[] vet = fim.split("	");

						for(int i = 0;i<vet.length;i+=3) 
							textArea.append(vet[i]+"\n");
							
						for(int i = 1;i<vet.length;i+=3) 
							textArea_1.append(vet[i]+"\n");
						
						for(int i = 2;i<vet.length;i+=3) 
							textArea_2.append(vet[i]+"\n");
							
						
					anotarObj.println(fim);
					
				}
				brp.close();
				}catch(IOException ioe){
					ioe.printStackTrace();
				}}});
		
		btnNewButton.setBounds(227, 343, 182, 57);
		frmInterpretador.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Selecione o Arquivo");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(227, 11, 198, 26);
		frmInterpretador.getContentPane().add(lblNewLabel);
		
		Label label = new Label("Individuo");
		label.setFont(new Font("Arial Black", Font.BOLD, 14));
		label.setBounds(76, 103, 71, 22);
		frmInterpretador.getContentPane().add(label);
		
		Label label_1 = new Label("Relacionamento");
		label_1.setFont(new Font("Arial Black", Font.BOLD, 14));
		label_1.setBounds(251, 103, 122, 22);
		frmInterpretador.getContentPane().add(label_1);
		
		Label label_2 = new Label("Classe");
		label_2.setFont(new Font("Arial Black", Font.BOLD, 14));
		label_2.setBounds(488, 103, 62, 22);
		frmInterpretador.getContentPane().add(label_2);
	}
}
