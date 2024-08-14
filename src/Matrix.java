import java.util.Locale;

public class Matrix {
    private final double[][] cells;

    //Método Construtor
    public Matrix(double[][] cells) {
        // Verificação de null
        if (cells == null || cells.length == 0) {
            throw new IllegalArgumentException("O array não pode ser nulo ou vazio.");
        }
        
        // Cópia defensiva do array 2D
        this.cells = new double[cells.length][];
        for (int i = 0; i < cells.length; i++) {
            this.cells[i] = cells[i].clone(); // Clone da linha
        }
    }

    //Método ToArray
    public double[][] toArray() {
        double[][] copy = new double[cells.length][];
        for (int i = 0; i < cells.length; i++) {
            // Fazendo uma cópia da linha para garantir encapsulamento
            copy[i] = cells[i].clone();
        }
        return copy;
    }

    //Retorna a quantidade de linhas da matriz
    public int getRows() {
        return cells.length;
    }

    //Retorna a quantidade de colunas da matriz
    public int getColumns() {
        if (cells.length > 0) {
            return cells[0].length;
        }
        return 0; // Retorna 0 se a matriz não tiver linhas
    }

    //Metodo para retornar elemento especifico
    public double getAt(int row, int column) {
        // Verificando se a linha e a coluna estão dentro dos limites da matriz
        if (row < 0 || row >= getRows() || column < 0 || column >= getColumns()) {
            throw new IllegalArgumentException("Posição fora dos limites da matriz.");
        }
        
        return cells[row][column];
    }

    //Metodo da adição
    public Matrix plus(Matrix other) {
        // Verificando se as dimensões das duas matrizes são compatíveis
        if (this.getRows() != other.getRows() || this.getColumns() != other.getColumns()) {
            throw new IllegalArgumentException("As matrizes devem ter as mesmas dimensões para a adição.");
        }
        
        // Criando uma nova matriz para armazenar o resultado
        double[][] result = new double[this.getRows()][this.getColumns()];
        
        // Somando os elementos correspondentes das duas matrizes
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getColumns(); j++) {
                result[i][j] = this.cells[i][j] + other.getAt(i, j);
            }
        }
        
        // Retornando uma nova instância de Matrix com o resultado da adição
        return new Matrix(result);
    }

    //Metodo da subtraçãp
    public Matrix minus(Matrix other) {
        // Verificando se as dimensões das duas matrizes são compatíveis
        if (this.getRows() != other.getRows() || this.getColumns() != other.getColumns()) {
            throw new IllegalArgumentException("As matrizes devem ter as mesmas dimensões para a subtração.");
        }
        
        // Criando uma nova matriz para armazenar o resultado
        double[][] result = new double[this.getRows()][this.getColumns()];
        
        // Subtraindo os elementos correspondentes das duas matrizes
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getColumns(); j++) {
                result[i][j] = this.cells[i][j] - other.getAt(i, j);
            }
        }
        
        // Retornando uma nova instância de Matrix com o resultado da subtração
        return new Matrix(result);
    }

    //Metodo da multiplicação por escalar
    public Matrix times(double scalar) {
        // Criando uma nova matriz para armazenar o resultado
        double[][] result = new double[this.getRows()][this.getColumns()];
        
        // Multiplicando cada elemento da matriz pelo escalar
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getColumns(); j++) {
                result[i][j] = this.cells[i][j] * scalar;
            }
        }
        
        // Retornando uma nova instância de Matrix com o resultado da multiplicação
        return new Matrix(result);
    }
    
    //Metodo da multiplicação por matriz
    public Matrix times(Matrix other) {
        // Verificando se as dimensões são compatíveis para multiplicação
        if (this.getColumns() != other.getRows()) {
            throw new IllegalArgumentException("O número de colunas da primeira matriz deve ser igual ao número de linhas da segunda matriz.");
        }
        
        int rows = this.getRows();
        int columns = other.getColumns();
        int commonDim = this.getColumns();
        
        // Criando a matriz resultado com as dimensões apropriadas
        double[][] result = new double[rows][columns];
        
        // Calculando o produto das matrizes
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[i][j] = 0;
                for (int k = 0; k < commonDim; k++) {
                    result[i][j] += this.cells[i][k] * other.getAt(k, j);
                }
            }
        }
        
        // Retornando uma nova instância de Matrix com o resultado da multiplicação
        return new Matrix(result);
    }

    //Metodo que retorna a transposta
    public Matrix getTranspose() {
        int rows = this.getRows();
        int columns = this.getColumns();
        
        // Criando a matriz transposta com dimensões invertidas
        double[][] transposed = new double[columns][rows];
        
        // Preenchendo a matriz transposta
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                transposed[j][i] = this.cells[i][j];
            }
        }
        
        // Retornando uma nova instância de Matrix com a matriz transposta
        return new Matrix(transposed);
    }
    
    //Metodo verifica se é quadrada
    public boolean isSquare() {
        // Verifica se o número de linhas é igual ao número de colunas
        return this.getRows() == this.getColumns();
    }

    //Metodo verifica se é simetrico
    public boolean isSymmetric() {
        // Verifica se a matriz é quadrada
        if (!this.isSquare()) {
            return false;
        }
        
        int size = this.getRows();
        
        // Compara os elementos correspondentes na matriz e na sua transposta
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (this.getAt(i, j) != this.getAt(j, i)) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    //toString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
    
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                // Formatando cada número com 6 casas decimais e largura de 10 posições
             sb.append(String.format(Locale.US, "%10.6f", cells[i][j]));
            }
            sb.append("\n"); // Nova linha após cada linha da matriz
        }
    
        return sb.toString();
    }
}