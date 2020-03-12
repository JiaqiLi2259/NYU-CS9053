package edu.nyu.cs9053.homework2;

import edu.nyu.cs9053.homework2.model.DiagnosticTroubleCode;

/**
 * User: blangel
 */
public class Mechanic {

    /**
     * @see {@literal https://en.wikipedia.org/wiki/OBD-II_PIDs#Mode_3_(no_PID_required)}
     * @implNote For simplification of this homework, contrary to the Wikipedia article {@code data} is not in the ISO 15765-2 protocol.
     *           It is simply an array of data where the length should be equal to {@code expectedAmount} times 2.
     * @implNote If {code data} is null, empty or not equal to {@code expectedAmount} times 2 then throw
     *           an {@linkplain IllegalArgumentException}; i.e. {@code throw new IllegalArgumentException}
     * @param data to parse
     * @param expectedAmount of {@linkplain DiagnosticTroubleCode} to decode
     * @return the decoded {@linkplain DiagnosticTroubleCode} objects see {@linkplain DiagnosticTroubleCode#construct(String)} to create the object.
     */
    public static DiagnosticTroubleCode[] decode(byte[] data, int expectedAmount) {
        if (data == null || data.length == 0 || data.length != 2 * expectedAmount) {
            throw new IllegalArgumentException();
        }
        DiagnosticTroubleCode[] codes = new DiagnosticTroubleCode[expectedAmount];
        for (int i = 0; i < expectedAmount; i++){
            StringBuilder decodedString = new StringBuilder("");
            byte firstByte = data[2*i];
            byte secondByte = data[2*i+1];
            int firstCharacter = (firstByte >> 6) & 0x03;
            String secondCharacter = Integer.toHexString((firstByte >> 4) & 0x03).toUpperCase();
            String thirdCharacter = Integer.toHexString(firstByte & 0x0f).toUpperCase();
            String fourthCharacter = Integer.toHexString((secondByte >> 4) & 0x0f).toUpperCase();
            String fifthCharacter = Integer.toHexString(secondByte & 0x0f).toUpperCase();
            switch (firstCharacter){
                case 0:
                    decodedString.append("P");
                    break;
                case 1:
                    decodedString.append("C");
                    break;
                case 2:
                    decodedString.append("B");
                    break;
                case 3:
                    decodedString.append("U");
                    break;
                default :
                    break;
            }
            decodedString.append(secondCharacter);
            decodedString.append(thirdCharacter);
            decodedString.append(fourthCharacter);
            decodedString.append(fifthCharacter);
            codes[i] = new DiagnosticTroubleCode(decodedString.toString());
        }
        return codes;
    }

}
