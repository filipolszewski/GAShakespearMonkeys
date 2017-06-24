import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Population {

	private Integer size;
	private Integer elementSize;
	private double mutationRate;
	private List<Element> elements;
	private Integer generation;
	private String target;

	public Population(Integer populationSize, Integer elementSize, double mutationRate, String target) {
		this.size = populationSize;
		this.elementSize = elementSize;
		this.mutationRate = mutationRate;
		this.target = target;
		generateRandom();
	}

	public void generateRandom() {
		elements = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			elements.add(new Element(elementSize));
		}
		generation = 0;
	}

	public void draw() {
		// calc fitness
		calculateFitness();
		// natural selection
		List<Element> matingPool = createMatingPool();
		// crossover + mutation
		elements = generateNewPopulation(matingPool);

		generation++;
	}

	private void calculateFitness() {
		for (Element element : elements) {
			element.calculateFitness(target);
		}
	}

	// algorytm accept-reject
	private List<Element> createMatingPool() {
		List<Element> matingPool = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			matingPool.add(getElementFromNaturalSelection());
		}
		return matingPool;
	}

	// return element with natural selection distribution - more fitness - more
	// likely to be picked
	private Element getElementFromNaturalSelection() {
		Random r = new Random();
		while (true) {
			Integer index = r.nextInt(size); 
			Integer random = r.nextInt(elementSize); // ElementSize == Max
														// possible fitness
			Element element = elements.get(index);
			if (random < element.getFitness()) {
				return element;
			}
		}

	}

	private List<Element> generateNewPopulation(List<Element> matingPool) {
		List<Element> newPopulation = new ArrayList<>();
		Random r = new Random();
		for (int i = 0; i < size; i++) {
			Element parentA = matingPool.get(r.nextInt(size));
			Element parentB = matingPool.get(r.nextInt(size));
			Element child = parentA.haveSexWith(parentB);
			if (r.nextFloat() < mutationRate) {
				child.mutate();
			}
			newPopulation.add(child);
		}
		return newPopulation;
	}

	public GenerationData getGenerationData() {
		calculateFitness();
		return new GenerationData(size, mutationRate, generation, calculateAvgFitness(), findFittestElement());
	}

	private Element findFittestElement() {
		Element bestElem = elements.get(0);
		for (int i = 1; i < elements.size(); i++) {
			Element elem = elements.get(i);
			if (elem.getFitness() > bestElem.getFitness()) {
				bestElem = elem;
			}
		}
		return bestElem;
	}

	private Integer calculateFitnessSum() {
		Integer fitnessSum = 0;
		for (Element element : elements) {
			fitnessSum += element.getFitness();
		}
		return fitnessSum;
	}

	private double calculateAvgFitness() {
		return calculateFitnessSum() / size;
	}
}
