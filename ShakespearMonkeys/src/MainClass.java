import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * 
 * @author Filip Olszewski
 *
 *         Main Class launches the genetic algorithm by initializing the
 *         MainWindow instance and starting it.
 * 
 *         This is a first, basic project covering the genetic algorithms field
 *         to introduce the basic code construction, and to observe how the
 *         behaviour of population changes based on some parameters.
 */
public class MainClass {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				MainWindow mainWindow = new MainWindow();
				mainWindow.start();
			}
		});
	}

}
