import java.util.Scanner;

public class Config {
    private String inputFilename;
    private String outputFilename;
    private int bufferSize;
    private int filterWorkerAmount;
    private String filterName;
    private String[] filterNames = {"blur", "sharpen", "sobelvertical", "sobelhorizontal"};

    public String getInputFilename() {
        return inputFilename;
    }
    public void setInputFilename(String inputFilename) {
        this.inputFilename = inputFilename;
    }

    public String getOutputFilename() {
        return outputFilename;
    }
    public void setOutputFilename(String outputFilename) {
        this.outputFilename = outputFilename;
    }

    public int getBufferSize() {
        return bufferSize;
    }
    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public int getFilterWorkerAmount() {
        return filterWorkerAmount;
    }
    public void setFilterWorkerAmount(int filterWorkerAmount) {
        this.filterWorkerAmount = filterWorkerAmount;
    }

    public String getFilterName() {
        return filterName;
    }
    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }


    public void inputInFilename(Scanner scanner) {
		String inputFilename;

		do {
			System.out.println("Introduzca el nombre del archivo de entrada");
			inputFilename = scanner.nextLine();

			if (inputFilename == null){
                System.out.println("El nombre del archivo es invalido");
            }

		} while (inputFilename == null);

		this.setInputFilename(inputFilename);
	}

    public void inputOutputFilename(Scanner scanner) {
		String outputFilename;

		do {
            System.out.println("Introduzca el nombre del archivo de salida");
			outputFilename = scanner.nextLine();

			if (outputFilename == null){
                System.out.println("El nombre del archivo es invalido");
            }

		} while (outputFilename == null);

		this.setOutputFilename(outputFilename);
	}

    public void inputBufferSize(Scanner scanner) {
        int bufferSize;

        do {
            System.out.println("Introduzca el tamaño del buffer entre 1 y 64");
            bufferSize = Integer.parseInt(scanner.nextLine());

            if (bufferSize < 1){
                System.out.println("El tamaño del buffer es invalido");
            }

        } while (bufferSize < 1);

        this.setBufferSize(bufferSize);
    }

    public void inputFilterWorkerAmount(Scanner scanner) {
        int input;

        do {
            System.out.println("Introduzca la cantidad de threads entre 1 y 16");
            input = Integer.parseInt(scanner.nextLine());

            if (input < 1){
                System.out.println("La cantidad de threads ingresada es invalida");
            }

        } while (input < 1);

        this.setFilterWorkerAmount(input);
    }

    private boolean contains(String[] names, String name){
        boolean result = false;
        int cont = 0;
        while(!result && cont < names.length){
            result = names[cont].equals(name);
            cont ++;
        }
        return result;
    }

    public void inputFilterName(Scanner scanner) {
		String filterName;

		do {
            System.out.println("Introduzca el nombre del filtro a aplicar");
			filterName = scanner.nextLine().toLowerCase();

			if (! this.contains(filterNames, filterName)){
                System.out.println("El nombre del filtro es invalido");
            }

		} while (! this.contains(filterNames, filterName));

		this.setFilterName(filterName);
	}
}
