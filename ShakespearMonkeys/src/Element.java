import java.util.Random;

public class Element {

	private char[] dna;
	private final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ abcdefghijklmnopqrstuvwxyz";
	private Integer elementSize;
	private Integer fitness;

	public Element(Integer elementSize) {
		this.elementSize = elementSize;
		dna = new char[elementSize];
		generateDna();
	}

	private void generateDna() {
		Random r = new Random();
		for (int i = 0; i < elementSize; i++) {
			dna[i] = alphabet.charAt(r.nextInt(alphabet.length()));
		}
	}


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

	public Element haveSexWith(Element parentB) {
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

	public void mutate() {
		Random r = new Random();
		dna[r.nextInt(elementSize)] = alphabet.charAt(r.nextInt(alphabet.length()));
	}

}
