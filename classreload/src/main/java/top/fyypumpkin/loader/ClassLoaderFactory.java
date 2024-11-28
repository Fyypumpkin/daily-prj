package top.fyypumpkin.loader;

/**
 * @author fyypumpkin on 6/28/19
 */
public class ClassLoaderFactory implements IClassLoader {
    @Override
    public ClassLoader createClassLoader(ClassLoader parent) {
        return new ReloadClassLoader(parent);
    }
}
