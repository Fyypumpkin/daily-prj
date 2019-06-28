package top.fyypumpkin.common;

/**
 * @author fyypumpkin on 6/28/19
 */
public class PathUtils {
    public static String packageToPath(String prefix, String className) {
        return prefix + className.replaceAll("\\.", "\\/");
    }
}
