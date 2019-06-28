package top.fyypumpkin.loader;

import top.fyypumpkin.common.Constants;
import top.fyypumpkin.common.PathUtils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author fyypumpkin on 6/28/19
 */
public class ReloadClassLoader extends ClassLoader {
    public ReloadClassLoader(ClassLoader parent) {
        super(parent);
    }

    private static final String FILE_PATH_PREFIX;


    static {
        FILE_PATH_PREFIX = Constants.BASE_COMPILE_PATH;
    }

    @Override
    public Class<?> loadClass(String className) throws ClassNotFoundException {
        if (className.startsWith(Constants.RELOAD_CLASS_PATH)) {
            try {
                InputStream ins
                        = new FileInputStream(PathUtils.packageToPath(FILE_PATH_PREFIX, className) + ".class");
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                int bufferSize = 2048;
                byte[] buffer = new byte[bufferSize];
                int bytesNumRead;
                while ((bytesNumRead = ins.read(buffer)) != -1) {
                    baos.write(buffer, 0, bytesNumRead);
                }

                return defineClass(className, baos.toByteArray(), 0, baos.toByteArray().length);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            return super.loadClass(className);
        }

        return null;
    }
}
