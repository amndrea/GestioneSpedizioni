package start;
import gui.*;

/**
 * classe che contiene il main, crea un FrameIniziale e lo rende visibile
 * @author Andrea Bonfatti
 *
 */
public class StartApp {
	public static void main(String[] args) {
		FrameIniziale frame = new FrameIniziale();
		frame.setVisible(true);
		frame.pack();
		frame.setLocationRelativeTo(null);
	}
}
