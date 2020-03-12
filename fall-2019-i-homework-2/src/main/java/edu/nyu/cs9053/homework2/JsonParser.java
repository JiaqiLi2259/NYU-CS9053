package edu.nyu.cs9053.homework2;

import edu.nyu.cs9053.homework2.model.EngineLightAlert;
import edu.nyu.cs9053.homework2.model.DiagnosticTroubleCode;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
/**
 * User: blangel
 *
 * @see {@literal https://www.json.org/}
 * @see {@literal https://en.wikipedia.org/wiki/JSON}
 */
public class JsonParser {

    /**
     * @param alert to serialize into {@literal JSON}
     * @implNote a null value should be an {@linkplain IllegalArgumentException}; i.e. {@code throw new IllegalArgumentException}
     * @return the serialized {@literal JSON} representation of {@code alert}
     */
    public static String toJson(EngineLightAlert alert) {
        if(alert == null){
            throw new IllegalArgumentException();
        }
        final String vehicleId = alert.getVehicleId();
        final long dateTime = alert.getDateTime();
        final DiagnosticTroubleCode[] codes = alert.getCodes();
        StringBuilder strJson = new StringBuilder("{");
        boolean flagVehicleId = (vehicleId != null);
        boolean flagCodes = (codes != null && codes.length != 0);
        if (flagVehicleId){
            String vehicleIdJson = String.format("\"vehicleId\":\"%s\",", vehicleId.replaceAll(Pattern.quote("\""), Matcher.quoteReplacement("\\\"")));
            strJson.append(vehicleIdJson);
        }
        String dataTimeJson = String.format("\"dateTime\":%d", Long.valueOf(dateTime));
        strJson.append(dataTimeJson);
        if (flagCodes){
            strJson.append(",\"codes\":[");
            boolean flagComma = false;
            for (DiagnosticTroubleCode code : codes){
                if (code != null ){
                    if (flagComma){
                        strJson.append(",");
                    }
                    if (code.getCode() != null){
                        String codeJson = String.format("{\"code\":\"%s\"}", code.getCode());
                        strJson.append(codeJson);
                    }else{
                        strJson.append("{}");
                    }
                    flagComma = true;
                }
            }
            strJson.append("]");
        }
        strJson.append("}");
        return strJson.toString();
    }

}
