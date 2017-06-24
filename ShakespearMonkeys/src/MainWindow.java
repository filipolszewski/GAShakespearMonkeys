import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class MainWindow extends JFrame implements ControllerListener {

	private static final String TITLE = "Genetic Algorithm - Shakespear Monkeys";
	private GeneticAlgorhitmController controller;
	private static final Font font = new Font("Verdana", Font.BOLD, 12);
	private JLabel bestElementLabel;
	private JLabel generationLabel;
	private JLabel avgFitnessLabel;
	private JLabel mutationRateLabel;

	public MainWindow() {

		controller = new GeneticAlgorhitmController(this);
		configureWindow();
		createComponents();
		setVisible(true);
		start();
	}

	public void start() {
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				controller.start();
			}
		});
		thread.start();

	}

	private void configureWindow() {
		setTitle(TITLE);
		setSize(400, 150);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void createComponents() {
		JPanel mainPanel = new JPanel(new GridLayout(5, 1, 10, 10));

		bestElementLabel = new JLabel("");
		bestElementLabel.setFont(font);
		generationLabel = new JLabel("");
		generationLabel.setFont(font);
		avgFitnessLabel = new JLabel("");
		avgFitnessLabel.setFont(font);
		mutationRateLabel = new JLabel("");
		mutationRateLabel.setFont(font);
		mainPanel.add(bestElementLabel);
		mainPanel.add(generationLabel);
		mainPanel.add(avgFitnessLabel);
		mainPanel.add(mutationRateLabel);
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(mainPanel);
	}

	@Override
	public void newGenerationData(GenerationData data) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				bestElementLabel.setText("Best element: " + data.getBestElement().getDna());
				generationLabel.setText("Generation: " + data.getGeneration());
				avgFitnessLabel.setText("Avg Fitness: " + data.getAvgFitness());
				mutationRateLabel.setText("Mutation Rate: " + data.getMutationrate());
			}
		});

	}

	@Override
	public void algorithmFinished() {
		bestElementLabel.setText(bestElementLabel.getText() + " (finished)");
	}

}
