package code.GUI.Formatter;

/**
 * Created by DonutsDose-PC on 21.06.2017.
 */
public class Formatter {

    public static String formatDate(int days) {
        int years = days / 300;
        days %= 300;
        int months = days / 30;
        days %= 30;
        return String.format("%s years %s months %s days", years, months % 10, days % 30);
    }

    public static String formatDateShort(int days) {
        int years = days / 300;
        days %= 300;
        int months = days / 30;
        days %= 30;
        return String.format("%04d.%02d.%02d.", years, months % 10, days % 30);
    }

}
