
package pbl4demo2;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


import java.awt.*;
import javax.swing.*;




public class Client1 extends JFrame implements ActionListener{

	public JPanel contentPane;
	public static JTextField txtYear;
	public Socket soc;
	public String Nickname;
	public JButton btnSearch;
	public JTextArea txtArea;
//	public SendActionListener sal = new SendActionListener(this);
	public DataInputStream is;
	public DataOutputStream os;
	public JFrame frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new Client1();
	}

	/**
	 * Create the frame.
	 */
	public Client1() {
		this.frame = new JFrame("chương trình in lịch thế kỷ");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(400, 100, 1000, 600);
		frame.setBackground(Color.DARK_GRAY);
		this.frame.setLayout(null);
		
		txtYear = new JTextField();
		txtYear.setFont(new Font("Arial", Font.BOLD, 14));
		txtYear.setForeground(new Color(0, 0, 0));
		txtYear.setBounds(634, 10, 160, 30);
		frame.add(txtYear);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBackground(Color.GRAY);
		btnSearch.setForeground(Color.RED);
		btnSearch.setFont(new Font("Arial", Font.BOLD, 14));
		btnSearch.setBounds(815, 9, 126, 30);
		btnSearch.addActionListener(this);
		frame.add(btnSearch);
		
		JTextArea txtArea = new JTextArea("");
		txtArea.setBounds(40, 50, 900, 500);
		txtArea.setBackground(Color.lightGray);
		txtArea.setForeground(Color.CYAN);
		frame.add(txtArea);
		
		JLabel lbYear = new JLabel("Year");
		lbYear.setFont(new Font("Arial", Font.BOLD, 14));
		lbYear.setBounds(575, 10, 84, 30);
		frame.add(lbYear);
		frame.setVisible(true);;
		
		try {
			soc = new Socket("localhost", 9999);
		    this.is= new DataInputStream(soc.getInputStream());
		    this.os= new DataOutputStream(soc.getOutputStream());
 
		} catch (Exception e) {
			System.out.println(e);
		}
		new Threads(soc).start();
	}
@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	try {
		while(true)
		{
			String date = Client1.txtYear.getText();
			os.writeUTF(date);
			os.flush();
		}
	} catch (Exception e1) {
		System.exit(0);
	}
	try {
		String s = is.readUTF();
		System.out.println(s);
		txtArea.setText(s);
	} catch (Exception e2) {
		// TODO: handle exception
		e2.printStackTrace();
	}
}
}
class Threads extends Thread
{
	Socket socket;
	public Threads(Socket socket)
	{
		this.socket = socket;
	}
}