import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * 
 * @author Filip Olszewski
 *
 *         Main Class launches the genetic algorithm by initializing the
 *         MainWindow instance and starting it.
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
