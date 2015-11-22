import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;


public class Assignment7 extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 800009642315637721L;
	private JPanel contentPane;
	private JTextField sortlimit;
	SortandFilter calculate = new SortandFilter();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Assignment7 frame = new Assignment7();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Assignment7()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JTextArea inputTextArea = new JTextArea();
		inputTextArea.setBounds(10, 35, 136, 183);
		inputTextArea.setWrapStyleWord(true);
		inputTextArea.setLineWrap(true);
		JScrollPane scrollpane = new JScrollPane(inputTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollpane.setBounds(10, 35, 136, 183);
		contentPane.add(scrollpane);	
		
		final JTextArea sortedWordsTextArea = new JTextArea();
		sortedWordsTextArea.setWrapStyleWord(true);
		sortedWordsTextArea.setLineWrap(true);
		sortedWordsTextArea.setBounds(156, 35, 129, 183);
		JScrollPane scrollpane3 = new JScrollPane(sortedWordsTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollpane3.setBounds(156, 35, 129, 183);
		contentPane.add(scrollpane3);
		
		final JTextArea filteredWordsTextArea = new JTextArea();
		filteredWordsTextArea.setWrapStyleWord(true);
		filteredWordsTextArea.setLineWrap(true);
		filteredWordsTextArea.setBounds(295, 35, 129, 183);
		JScrollPane scrollpane2 = new JScrollPane(filteredWordsTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollpane2.setBounds(295, 35, 129, 183);
		contentPane.add(scrollpane2);
		
		final JLabel inputText = new JLabel("Input_Text");
		inputText.setBounds(45, 11, 64, 14);
		contentPane.add(inputText);
		
		JLabel sortedWords = new JLabel("Sorted_Words");
		sortedWords.setBounds(167, 10, 87, 14);
		contentPane.add(sortedWords);
		
		JLabel filteredWords = new JLabel("Filtered_Words");
		filteredWords.setBounds(316, 11, 87, 14);
		contentPane.add(filteredWords);
		
		sortlimit = new JTextField();
		sortlimit.setBounds(258, 228, 27, 20);
		contentPane.add(sortlimit);
		sortlimit.setColumns(10);
		
		JButton sortButton = new JButton("Sort");
		sortButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sortedWordsTextArea.setText(calculate.sort(inputTextArea.getText()));
			}
		});
		sortButton.setBounds(20, 227, 89, 23);
		contentPane.add(sortButton);
		
		JButton filterButton = new JButton("Filter");
		filterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filteredWordsTextArea.setText(calculate.filter(sortedWordsTextArea.getText(), sortlimit.getText()));
			}
		});
		filterButton.setBounds(159, 227, 89, 23);
		contentPane.add(filterButton);
		
		
		
		JButton htmlButton = new JButton("HTML");
		htmlButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					  FileOutputStream fs = new FileOutputStream("TextToHTML.html");
					  OutputStreamWriter out = new OutputStreamWriter(fs);
					  out.write("<html>");  
					  out.write("<head>"); 
					  out.write("<title>");  
					  out.write("Convert text to html");
					  out.write("</title>");  
					  out.write("</head>");
					  out.write("<body>");
					  out.write(filteredWordsTextArea.getText());
					  out.write("</body>");
					  out.write("</html>");
					  out.close();
					  }
					  catch (IOException e1){
					  }
			}
		});
		htmlButton.setBounds(316, 227, 89, 23);
		contentPane.add(htmlButton);
	}
}
