import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Calculadora implements ICalc {

    private ICustom<Integer> stack = new CustomStack<>();

    public Calculadora(ICustom<Integer> stack) {
        this.stack = stack;
    }

    public Calculadora(){
    }

    public void calculate() {
        try {
    
            String expresion = readTXT();
            String[] tokens = expresion.split(" ");

          
            if (tokens.length < 3) {
                throw new IllegalArgumentException("Se necesitan al menos dos operandos y un operador.");
            }
            for (String token : tokens) {
                if (isNumeric(token)) {
                    stack.push(Integer.parseInt(token));
                } else {
                    switch (token) {
                        case "+":
                            stack.push(sumar());
                            break;
                        case "-":
                            stack.push(resta());
                            break;
                        case "*":
                            stack.push(multiplicacion());
                            break;
                        case "/":
                            division();
                            break;
                        default:
                            throw new IllegalArgumentException("Operador no válido: " + token);
                    }
                }
            }

           
            Integer resultado = stack.pop();

         
            if (stack.pop() != null) {
                throw new IllegalArgumentException("La expresión no tiene suficientes operadores.");
            }

          
            System.out.println("Expresion: " + expresion);
            System.out.println("Resultado: " + resultado);

        } catch (ArithmeticException e) {
       
            System.out.println("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
 
            System.out.println("Error: " + e.getMessage());
        }
    }






    public int sumar() {
        Integer operandoB = stack.pop();
        Integer operandoA = stack.pop();
        if (operandoA != null && operandoB != null) {
            return operandoA + operandoB;
        } else {
            throw new IllegalArgumentException("Faltan operandos para la suma.");
        }
    }

    
    public int resta() {
        Integer operandoB = stack.pop();
        Integer operandoA = stack.pop();
        if (operandoA != null && operandoB != null) {
            return operandoA - operandoB;
        } else {
            throw new IllegalArgumentException("Faltan operandos para la resta.");
        }
    }
  
    public int multiplicacion() {
        Integer operandoB = stack.pop();
        Integer operandoA = stack.pop();
        if (operandoA != null && operandoB != null) {
            return operandoA * operandoB;
        } else {
            throw new IllegalArgumentException("Faltan operandos para la multiplicación.");
        }
    }
    
  
    public boolean division() {
        try {
            Object operandoB = stack.pop();
            Object operandoA = stack.pop();
            if (operandoA instanceof Integer && operandoB instanceof Integer) {
                Integer a = (Integer) operandoA;
                Integer b = (Integer) operandoB;
                if (b != 0) {
                    stack.push(a / b);
                    return true;
                } else {
                    throw new ArithmeticException("División por cero.");
                }
            } else {
                throw new IllegalArgumentException("Operandos no válidos para la operación de división.");
            }
        } catch (NullPointerException e) {
            throw new IllegalStateException("La pila está vacía, no hay suficientes operandos para la operación de división.");
        }
    }

    @Override
    public String readTXT() {
        try (BufferedReader br = new BufferedReader(new FileReader("datos.txt"))) {
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean isNumeric(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
