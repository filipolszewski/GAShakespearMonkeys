/**
 * 
 * @author Filip
 *
 *         The performer of the genetic algorithm. Defines the target string and
 *         algorithms properties.
 *
 */

public class GeneticAlgorhitmPerformer {

	private static final String TARGET = "To be or not to be";
	private static final Integer populationSize = 1000;
	private static final Integer elementSize = TARGET.length();
	private static final double mutationRate = 0.01;
	private GenerationData genData;
	private ControllerListener listener;

	public GeneticAlgorhitmPerformer(ControllerListener listener) {
		this.listener = listener;
	}

	/**
	 * Performs the algorithm starting with a randomly generated population.
	 * Informs the listener with new GenerationData after each cycle. Introduces
	 * a delay for each cycle for presentation purposes.
	 */
	public void start() {
		Population population = new Population(populationSize, elementSize, mutationRate, TARGET);
		genData = population.getGenerationData();

		Element bestElement;
		do {
			listener.newGenerationData(genData);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			population.draw();
			genData = population.getGenerationData();
			bestElement = genData.getBestElement();
		} while (!bestElement.getDna().equals(TARGET));

		listener.newGenerationData(genData);
	}

}
