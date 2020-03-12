package edu.nyu.cs9053.homework3;

/**
 * User: blangel
 */
public class AsciiArtPrinter {

    /**
     * @implNote should only print values within {@code asciiArt} and nothing else within this method
     * @param asciiArt to print
     */
    public void print(char[][] asciiArt) {
        StringBuilder sb = new StringBuilder("");
        for (char[] lineOfAsciiArt : asciiArt) {
            for (char ascii : lineOfAsciiArt) {
                sb.append(ascii);
            }
            sb.append("\n\r");
        }
        System.out.print(sb.toString());
    }

    protected void clearScreen() {
        System.out.printf("\u001B[51;1H");
    }

}
