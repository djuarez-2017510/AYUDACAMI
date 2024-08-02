
public class Main {

    public static void main(String[] args) {
        ICustom<Integer> pila = new CustomStack<>();
        ICalc calculadora = new Calculadora(pila);

        calculadora.calculate();
    }
}

