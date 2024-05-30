package mynotepad;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class About extends JFrame implements ActionListener
{
	JButton ok;
	About()
	{
		setLayout(null);
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/windows.png"));
		Image img1 = i1.getImage().getScaledInstance(300, 60, Image.SCALE_DEFAULT);
		ImageIcon i2 = new ImageIcon(img1);
		JLabel window = new JLabel(i2);
		window.setBounds(150, 50, 300, 60);
		add(window);
		
		ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("icons/notepad.png"));
		Image img2 = i3.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
		ImageIcon i4 = new ImageIcon(img2);
		JLabel notepad = new JLabel(i4);
		notepad.setBounds(100, 160, 70, 70);
		add(notepad);
		
		JLabel text = new JLabel("<html><p>Jaykumar MJ Vaghasiya<br>Version 2023<br>All rights reserved.<br><br>Notepad is a word processing program, <br>which allows changing of text in a computer file. <br>Notepad is a simple text editor for basic text-editing program <br> which enables computer users to create documents.</p></html>");
		text.setFont(new Font("SAN_SERIF", Font.PLAIN, 13));
		text.setBounds(200, 165, 500, 150);
		add(text);
		
		ok = new JButton("Ok");
		ok.setFont(new Font("Aerial", Font.BOLD, 15));
		ok.addActionListener(this);
		ok.setBounds(420, 400, 110, 25);
		add(ok);
		
		getContentPane().setBackground(Color.WHITE);
 		setBounds(400, 100, 600, 500);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		setVisible(false);
	}
	
	public static void main(String args[])
	{
		new About();
	}
}
