import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 
 * @author Filip Object representing the population with given parameters.
 *         Provides methods for every step of the genetic algorithm and a method
 *         to perform a one genetic cycle.
 */
public class Population {

	private Integer size;
	private Integer elementSize;
	private double mutationRate;
	private List<Element> elements;
	private Integer generation;
	private String target;

	/**
	 * Construct new, randomly generated Population based on given parameters.
	 * 
	 * @param populationSize
	 * @param elementSize
	 * @param mutationRate
	 * @param target
	 */
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

	/**
	 * Performs a genetic cycle.
	 * 
	 * Theoretical info: Cycle consists of calculating the fitness of each
	 * element. Then 'mating pool' is constructed - the bigger fitness of an
	 * element, the more presence it has in the mating pool. Then the new
	 * population is generated based on crossovers of elements from the mating
	 * pool. Crossover process includes possible mutations of new elements.
	 * 
	 */
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

	/**
	 * 
	 * @return Mating Pool - population in which the probability of picking an
	 *         element (amount of this element instances in the pool) is
	 *         proportional to its fitness.
	 */
	private List<Element> createMatingPool() {
		List<Element> matingPool = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			matingPool.add(pickElement());
		}
		return matingPool;
	}

	/**
	 * Returns an element from population with a statistical distribution
	 * imitating the natural selection process.
	 * 
	 * There are couple of ways of programming that method. One is using an
	 * accept-reject algorithm where it picks random element, but accepts it
	 * with a chance based on its fitness. (Used earlier in this project)
	 * 
	 * Currently this method is using a faster algorithm. It calculates the sum
	 * of fitnesses, picks a random number from <0, maxFitness> and starts
	 * adding up values(fitness values of elements) to this number until it gets
	 * higher than maxFitness - it returns an element that was currently in the
	 * loop.
	 * 
	 * @return
	 */
	private Element pickElement() {
		Integer maxFitness = calculateFitnessSum();
		Random r = new Random();
		Integer point = r.nextInt(maxFitness);
		for (int i = 0; i < size; i++) {
			Element element = elements.get(i);
			point += element.getFitness();
			if (point >= maxFitness) {
				return element;
			}
		}
		System.out.println("Error - No element picked!");
		return null;
	}

	/**
	 * Generates new population from the mating pool, performing multiple
	 * crossovers and possible mutations.
	 * 
	 * @param matingPool
	 * @return
	 */
	private List<Element> generateNewPopulation(List<Element> matingPool) {
		List<Element> newPopulation = new ArrayList<>();
		Random r = new Random();
		for (int i = 0; i < size; i++) {
			Element parentA = matingPool.get(r.nextInt(size));
			Element parentB = matingPool.get(r.nextInt(size));
			Element child = parentA.crossoverWith(parentB);
			if (r.nextFloat() < mutationRate) {
				child.mutate();
			}
			newPopulation.add(child);
		}
		return newPopulation;
	}

	/**
	 * 
	 * @return information about current population(generation)
	 */
	public GenerationData getGenerationData() {
		calculateFitness();
		return new GenerationData(size, mutationRate, generation, calculateAvgFitness(), findFittestElement());
	}

	/**
	 * Complexity - O(n)
	 * 
	 * @return fittest element
	 */
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

	/**
	 * Complexity - O(n)
	 * 
	 * @return sum of fitness values of the population
	 */
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
