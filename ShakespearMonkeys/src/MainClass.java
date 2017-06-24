import javax.swing.SwingUtilities;
import javax.swing.UIManager;

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
			}
		});
	}

}
