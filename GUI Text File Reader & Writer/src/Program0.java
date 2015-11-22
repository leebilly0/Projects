/**
 * Billy Lee
 * Program 0 - Read Write Text Files
 */


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class Program0 extends JPanel implements ActionListener {
	private JFileChooser fileChooser;
	private JButton	open;
	private File file;
	private BufferedReader br;
	int returnValue;
	String currentLine;

	public Program0 () {
		fileChooser = new JFileChooser(System.getProperty("user.dir"));
		open = new JButton("Open");

		setPreferredSize(new Dimension(250, 150));
		setLayout(null);

		add(open);

		open.setBounds(75, 100, 100, 25);
		open.addActionListener(this);

	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == open) 
		{
			returnValue = fileChooser.showOpenDialog(null);
			if(returnValue == JFileChooser.APPROVE_OPTION) 
			{
				file = fileChooser.getSelectedFile();

				//Read file and print to console
				try{
					br = new BufferedReader(new FileReader(file));

					while((currentLine = br.readLine()) != null) {
						System.out.println(currentLine);
					}
				}catch(Exception error){
					error.printStackTrace();
				}
				
				//writes text to new text and saves
				try{
					// pulled this short piece of code to remove the text extension at the end of the file so
					// I can add the "_out" to the end of the file name being saved
					// http://stackoverflow.com/questions/8393849/how-to-get-name-of-file-object-without-its-extension-in-java
					// I've modified it a bit to suit my needs
					String name = file.getPath();
					int pos = name.lastIndexOf(".");
					if (pos > 0) {
					    name = name.substring(0, pos);
					}
					
					//creates new file to be written out
					BufferedWriter out = new BufferedWriter(new FileWriter(name.concat("_out.txt")));
					
					br = new BufferedReader(new FileReader(file));
					
					//reads file and writes it to new file
					while((currentLine = br.readLine()) != null) {
						out.write(currentLine);
						out.newLine();
					}
					out.close();

				}catch(Exception error){

				}
			}
		}
	}

	public static void main(String[] args) {
		//Create and set up the window.
		JFrame frame = new JFrame("COPY TEXT FILE");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Add content to the window.
		frame.add(new Program0());
		//Display the window.
		frame.pack();
		frame.setVisible(true);

	}

}
