package br.edu.ifsp.conversortemperatura.model;

public class FahrenStrategy implements ConversorTemperatura{

    private static FahrenStrategy instance;

    private FahrenStrategy(){};

    public static FahrenStrategy getInstance(){
        if (instance == null){
            instance = new FahrenStrategy();
        }
        return instance;
    }

    @Override
    public double converter(double temperatura){
        return (1.8 * temperatura + 32);
    }

}
