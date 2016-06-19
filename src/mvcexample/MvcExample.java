package mvcexample;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MvcExample {

	private static void createAndShowGui() {
		MyView view = new MyView();
		MyMenuBar menuBar = new MyMenuBar();
		MyModel model = new MyModel();
		MyControl control = new MyControl(model);
		control.addProgressMonitor(view);
		control.addView(view);
		control.addView(menuBar);

		model.setState(MyState.STOP);

		JFrame frame = new JFrame("MVC Example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(view.getMainPanel());
		frame.setJMenuBar(menuBar.getMenuBar());
		frame.pack();
//		frame.setLocationByPlatform(true);
		frame.setLocation(getCentreOfScreen());
		frame.setVisible(true);

	}
	
	private static Point getCentreOfScreen() {
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		Point p = new Point((int)(size.getWidth()/2), (int)(size.getHeight()/2));
		return(p);
		
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGui();
			}
		});
	}

	private static final byte[] DATA_ARRAY = { 0x43, 0x6f, 0x70, 0x79, 0x72,
			0x69, 0x67, 0x68, 0x74, 0x20, 0x46, 0x75, 0x62, 0x61, 0x72, 0x61,
			0x62, 0x6c, 0x65, 0x2c, 0x20, 0x30, 0x36, 0x2f, 0x31, 0x36, 0x2f,
			0x32, 0x30, 0x31, 0x32, 0x2e, 0x20, 0x46, 0x75, 0x62, 0x61, 0x72,
			0x61, 0x62, 0x6c, 0x65, 0x20, 0x52, 0x75, 0x6c, 0x65, 0x73, 0x21 };

}
