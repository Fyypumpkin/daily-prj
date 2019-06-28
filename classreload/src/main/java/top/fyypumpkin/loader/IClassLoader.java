package top.fyypumpkin.loader;

/**
 * @author fyypumpkin on 6/28/19
 */
public interface IClassLoader {
    ClassLoader createClassLoader(ClassLoader parent);
}
