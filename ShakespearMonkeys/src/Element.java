import java.util.Random;

/**
 * 
 * @author Filip
 *
 *         Single element of the whole population.
 *
 */
public class Element {

	private char[] dna;
	private final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ abcdefghijklmnopqrstuvwxyz";
	private Integer elementSize;
	private Integer fitness;

	/**
	 * The only constructor for Element. Generates instance with randomized DNA.
	 * 
	 * @param elementSize
	 */
	public Element(Integer elementSize) {
		this.elementSize = elementSize;
		dna = new char[elementSize];
		generateDna();
	}

	/**
	 * Generating random DNA based on the alphabet constant.
	 */
	private void generateDna() {
		Random r = new Random();
		for (int i = 0; i < elementSize; i++) {
			dna[i] = alphabet.charAt(r.nextInt(alphabet.length()));
		}
	}

	/**
	 * Basic linear fitness calculation
	 * 
	 * @param target
	 *            Target string. The maximal fitness is returned if DNA is equal
	 *            in characters to the target string.
	 */
	public void calculateFitness(String target) {
		fitness = 1;
		for (int i = 0; i < target.length(); i++) {
			if (dna[i] == target.charAt(i)) {
				fitness++;
			}
		}
		// fitness = (int) Math.pow(fitness, 2);
	}

	public String getDna() {
		return String.valueOf(dna);
	}

	public void setDna(char[] dna) {
		this.dna = dna;
	}

	public Integer getElementSize() {
		return elementSize;
	}

	public void setElementSize(Integer elementSize) {
		this.elementSize = elementSize;
	}

	public Integer getFitness() {
		return fitness;
	}

	public void setFitness(Integer fitness) {
		this.fitness = fitness;
	}

	/**
	 * Performs a two-elements crossover, statistically taking half of the
	 * objects DNA and half of the second elements DNA.
	 * 
	 * @param parentB
	 *            Second element that takes part in the crossover process.
	 * @return child Element
	 */

	public Element crossoverWith(Element parentB) {
		Element child = new Element(elementSize);
		char[] crossoverDna = new char[elementSize];
		Random r = new Random();
		Integer distributionPoint = r.nextInt(elementSize);

		for (int i = 0; i < distributionPoint; i++) {
			crossoverDna[i] = dna[i];
		}
		for (int i = distributionPoint; i < elementSize; i++) {
			crossoverDna[i] = parentB.dna[i];
		}
		child.setDna(crossoverDna);
		return child;
	}

	/**
	 * Performs elements mutation by changing one DNA's char to a new, random
	 * one.
	 */
	public void mutate() {
		Random r = new Random();
		dna[r.nextInt(elementSize)] = alphabet.charAt(r.nextInt(alphabet.length()));
	}

}
