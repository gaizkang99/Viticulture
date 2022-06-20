package LogicException;

import java.util.Arrays;
import java.util.List;

public class LogicExceptions extends Exception {
    public static final int Parametros_Incorrectos = 0;


    private int value;

    public LogicExceptions(int value) {
        this.value = value;
    }

    private List<String> message = Arrays.asList(
            "<< Numero de parámetros incorrecto >>"
    );


    @Override
    public String getMessage() {
        return message.get(value);
    }
}