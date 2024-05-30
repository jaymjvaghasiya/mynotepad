package mynotepad;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class MyNotepad extends JFrame implements ActionListener
{
	JTextArea area;
	StringBuffer sb;
	
	MyNotepad()
	{
		setTitle("MyNotepad");
		
		ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("icons/notepad.png"));
		Image nimg = img.getImage();
		setIconImage(nimg);
		
		JMenuBar menu = new JMenuBar();
		menu.setBackground(Color.WHITE);
		
		JMenu file  = new JMenu("File");
		file.setFont(new Font("Aerial", Font.PLAIN, 15));
		menu.add(file);
		
		JMenuItem newdoc = new JMenuItem("New");
		newdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		newdoc.addActionListener(this);
		file.add(newdoc);
		
		JMenuItem open = new JMenuItem("Open");
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		open.addActionListener(this);
		file.add(open);
		
		JMenuItem save = new JMenuItem("Save");
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		save.addActionListener(this);
		file.add(save);
		
		JMenuItem print = new JMenuItem("Print");
		print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		print.addActionListener(this);
		file.add(print);
		
		JMenuItem exit = new JMenuItem("Exit");
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, ActionEvent.CTRL_MASK));
		exit.addActionListener(this);
		file.add(exit);
		
		JMenu edit = new JMenu("Edit");
		edit.setFont(new Font("Aerial", Font.PLAIN, 15));
		menu.add(edit);
		
		JMenuItem copy = new JMenuItem("Copy");
		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		copy.addActionListener(this);
		edit.add(copy);
		
		JMenuItem pest = new JMenuItem("Pest");
		pest.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		pest.addActionListener(this);
		edit.add(pest);
		
		JMenuItem cut = new JMenuItem("Cut");
		cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		cut.addActionListener(this);
		edit.add(cut);
		
		JMenuItem selectAll = new JMenuItem("Select All");
		selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		selectAll.addActionListener(this);
		edit.add(selectAll);
		
		JMenu help = new JMenu("Help");
		help.setFont(new Font("Aerial", Font.PLAIN, 15));
		menu.add(help);
		
		JMenuItem aboutnotepad = new JMenuItem("About Notepad");
		aboutnotepad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
		aboutnotepad.addActionListener(this);
		help.add(aboutnotepad);
		
		setJMenuBar(menu);
		
		area = new JTextArea();
		area.setFont(new Font("SAN_SERIF", Font.PLAIN, 18));
		area.setLineWrap(true);
		area.setWrapStyleWord(true);
		add(area);
		
		JScrollPane pane = new JScrollPane(area);
		pane.setBorder(BorderFactory.createEmptyBorder());
		add(pane);
		
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getActionCommand().equals("New"))
		{
			area.setText("");
		}
		else if(ae.getActionCommand().equals("Open"))
		{
			JFileChooser ch = new JFileChooser();
			ch.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("Only .txt files", "txt");
			ch.addChoosableFileFilter(fileFilter);
			int action = ch.showOpenDialog(this);
			
			if(action != JFileChooser.APPROVE_OPTION)
			{
				return;
			}
			
			File file = ch.getSelectedFile();
			
			try
			{
				BufferedReader reader = new BufferedReader(new FileReader(file));
				area.read(reader, null);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}	
		}
		else if(ae.getActionCommand().equals("Save"))
		{
			JFileChooser saveas = new JFileChooser();
			saveas.setApproveButtonText("Save");
			
			int action = saveas.showOpenDialog(this);
			
			if(action != JFileChooser.APPROVE_OPTION)
			{
				return;
			}
			
			File file = new File(saveas.getSelectedFile() + ".txt");
			BufferedWriter writer = null;
			try
			{
				writer = new BufferedWriter(new FileWriter(file));
				area.write(writer);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}	
		}
		else if(ae.getActionCommand().equals("Print"))
		{
			try {
				area.print();
			} catch (PrinterException e) {
				e.printStackTrace();
			}
		}
		else if(ae.getActionCommand().equals("Exit"))
		{
			System.exit(0);
		}
		else if(ae.getActionCommand().equals("Copy"))
		{
			sb = new StringBuffer(area.getSelectedText());
		}
		else if(ae.getActionCommand().equals("Pest"))
		{
			area.insert(sb.toString(), area.getCaretPosition());
		}
		else if(ae.getActionCommand().equals("Cut"))
		{
			area.replaceRange("", area.getSelectionStart(), area.getSelectionEnd());
		}
		else if(ae.getActionCommand().equals("Select All"))
		{
			area.selectAll();
		}
		else
		{
			new About();
		}
	}
	
	public static void main(String args[])
	{
		new MyNotepad();
	}
}
