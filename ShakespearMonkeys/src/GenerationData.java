
public class GenerationData {

	private Integer populationNumber;
	private double mutationRate;
	private Integer generation;
	private double avgFitness;
	private Element bestElement;

	public GenerationData(Integer populationNumber, double mutationRate, Integer generation, double avgFitness,
			Element bestElement) {
		super();
		this.populationNumber = populationNumber;
		this.mutationRate = mutationRate;
		this.generation = generation;
		this.avgFitness = avgFitness;
		this.bestElement = bestElement;
	}

	public Integer getGeneration() {
		return generation;
	}

	public void setGeneration(Integer generation) {
		this.generation = generation;
	}

	public double getAvgFitness() {
		return avgFitness;
	}

	public void setAvgFitness(double avgFitness) {
		this.avgFitness = avgFitness;
	}

	public Element getBestElement() {
		return bestElement;
	}

	public void setBestElement(Element bestElement) {
		this.bestElement = bestElement;
	}

	public Integer getPopulationnumber() {
		return populationNumber;
	}

	public double getMutationrate() {
		return mutationRate;
	}

}
