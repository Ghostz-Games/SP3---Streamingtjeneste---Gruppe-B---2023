package streaming.util;

public abstract class StringUtil {
    public static boolean containsIgnoreCase(String in, String find){
        return in.toLowerCase().contains(find.toLowerCase());
    }
}
