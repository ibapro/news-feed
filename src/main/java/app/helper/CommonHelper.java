package app.helper;

public class CommonHelper {
    private static final String MESSAGE = "Approximately, it will take %.1f minutes to read this article";
    public static String countTimeForRead(String content) {
        int from = content.lastIndexOf("[+");
        int to = content.lastIndexOf(" chars]");
        String charsCount = content.substring(from + 2, to);
        double time = (double) Integer.parseInt(charsCount) / 1500;
        return String.format(MESSAGE, time);
    }
}
