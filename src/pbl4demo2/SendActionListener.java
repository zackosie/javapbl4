//package pbl4demo2;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.JFrame;
//
//public class SendActionListener implements ActionListener {
//	Client1 c1 ;
//	public SendActionListener(Client1 Client1) {
//		this.c1 = Client1;
//	}
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
//		// client gui yeu cau di
//		try {
//			while(true)
//			{
//				String date = Client1.txtYear.getText();
//				c1.os.writeUTF(date);
//				c1.os.flush();
//			}
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//		try {
//			String s = this.c1.is.readUTF();
//			System.out.println(s);
//			c1.txtArea.setText(s);
//		} catch (Exception e2) {
//			// TODO: handle exception
//			e2.printStackTrace();
//		}
//	}
//
//}