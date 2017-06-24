public class GeneticAlgorhitmController {

	private static final String TARGET = "To be or not to be";
	private static final Integer populationSize = 1000;
	private static final Integer elementSize = TARGET.length();
	private static final double mutationRate = 0.01;
	private GenerationData genData;
	private ControllerListener listener;

	public GeneticAlgorhitmController(ControllerListener listener) {
		this.listener = listener;
	}

	public void start() {
		Population population = new Population(populationSize, elementSize, mutationRate, TARGET);

		genData = population.getGenerationData();

		Element bestElement;
		do {
			listener.newGenerationData(genData);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			population.draw();
			genData = population.getGenerationData();
			bestElement = genData.getBestElement();
			System.out.println(bestElement.getDna());
			System.out.println(genData.getGeneration());
		} while (!bestElement.getDna().equals(TARGET));

		listener.newGenerationData(genData);
	}

}
