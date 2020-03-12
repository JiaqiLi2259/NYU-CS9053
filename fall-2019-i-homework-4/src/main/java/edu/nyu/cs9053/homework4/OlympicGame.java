package edu.nyu.cs9053.homework4;

public enum  OlympicGame {

    first("France"),
    second("Switzerland"),
    third("United States"),
    fourth("Germany"),
    fifth("Switzerland"),
    sixth("Norway"),
    seventh("Italy"),
    eighth("United States"),
    ninth("Austria"),
    tenth("France"),
    eleventh("Japan"),
    twelfth("Austria"),
    thirteenth("United States"),
    fourteenth("Yugoslavia"),
    fifteenth("Canada"),
    sixteenth("France"),
    seventeenth("Norway"),
    eighteenth("Japan"),
    nineteenth("United States"),
    twentieth("Italy"),
    twentyFirst("Canada"),
    twentySecond("Russia"),
    twentyThird("South Korea");

    public static void print(OlympicGame ... arguments) {
        for (OlympicGame argument : arguments) {
            System.out.printf("The host country is: %s",argument.getHostCountry());
        }
    }

    private final String  hostCountry;

    private OlympicGame(String hostCountry) {
        this.hostCountry = hostCountry;
    }

     public String getHostCountry() {
        return hostCountry;
     }

}
