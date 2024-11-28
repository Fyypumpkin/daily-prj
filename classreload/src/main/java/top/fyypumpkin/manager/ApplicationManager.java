package top.fyypumpkin.manager;

import lombok.Getter;
import org.apache.commons.vfs2.FileListener;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.VFS;
import org.apache.commons.vfs2.impl.DefaultFileMonitor;
import top.fyypumpkin.bridge.IApplication;
import top.fyypumpkin.common.Constants;
import top.fyypumpkin.common.PathUtils;
import top.fyypumpkin.loader.ClassLoaderFactory;
import top.fyypumpkin.watcher.FileChangeListener;
import top.fyypumpkin.watcher.IFileChangeCallBack;

import java.io.File;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author fyypumpkin on 6/28/19
 */
@Getter
public class ApplicationManager implements IFileChangeCallBack {
    private Map<String, IApplication> apps = new ConcurrentHashMap<>();
    private final ClassLoaderFactory loaderFactory = new ClassLoaderFactory();
    private FileSystemManager fileManager;
    private DefaultFileMonitor fileMonitor;

    public void init() {
        loadApplication();
        regFileListener();
    }

    private void loadApplication() {
        // 将 app load 进来 (SPI)
        ServiceLoader<IApplication> loader = ServiceLoader.load(IApplication.class);
        for (IApplication iApplication : loader) {
            System.out.print(iApplication.getApplicationName() + ": use ");
            System.out.println(iApplication.getClass().getClassLoader().getClass().getSimpleName() + " load");
            iApplication.init();
            apps.put(iApplication.getApplicationName(), iApplication);
        }
    }

    private void regFileListener() {
        System.out.println("starting listen ...");

        try {
            fileManager = VFS.getManager();
            File file
                    = new File(PathUtils.packageToPath(Constants.BASE_COMPILE_PATH, Constants.RELOAD_CLASS_PATH));
            FileObject monitoredDir = this.fileManager.resolveFile(file.getAbsolutePath());
            FileListener fileMonitorListener = new FileChangeListener(this);
            this.fileMonitor = new DefaultFileMonitor(fileMonitorListener);
            this.fileMonitor.setRecursive(true);
            this.fileMonitor.addFile(monitoredDir);
            this.fileMonitor.start();
            System.out.println("started listen " + monitoredDir.getName().getPath());
        } catch (Exception e) {
            System.out.println("started failed ");
            e.printStackTrace();
        }

    }

    private void reloadApplication(String applicationName) {
        IApplication old = apps.remove(applicationName);

        if (old == null) {
            return;
        }

        old.destroy();

        ClassLoader classLoader
                = this.loaderFactory.createClassLoader(ApplicationManager.class.getClassLoader());

        try {
            Class<?> aClass = classLoader.loadClass(old.getClass().getName());

            IApplication newApp = (IApplication) aClass.newInstance();

            newApp.init();

            System.out.println(newApp.getApplicationName() + " reload success, use " + newApp.getClass().getClassLoader().getClass().getSimpleName() + " load");

            apps.put(newApp.getApplicationName(), newApp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void callback(String name, Integer action) {
        switch (action) {
            case 1:
                reloadApplication(name);
                break;
        }
    }
}
