package top.fyypumpkin.watcher;

import org.apache.commons.vfs2.FileChangeEvent;
import org.apache.commons.vfs2.FileListener;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author fyypumpkin on 6/28/19
 */
public class FileChangeListener implements FileListener {
    ExecutorService service = Executors.newSingleThreadExecutor();
    IFileChangeCallBack changeCallBack;

    public FileChangeListener(IFileChangeCallBack changeCallBack) {
        this.changeCallBack = changeCallBack;
    }

    @Override
    public void fileCreated(FileChangeEvent fileChangeEvent) throws Exception {

    }

    @Override
    public void fileDeleted(FileChangeEvent fileChangeEvent) throws Exception {
        // 2
    }

    @Override
    public void fileChanged(FileChangeEvent fileChangeEvent) throws Exception {
        System.out.println(fileChangeEvent.getFile().getName().getBaseName() + " changed");
        service.execute(new HandleThread(fileChangeEvent.getFile().getName().getBaseName().split("\\.")[0]));

        // 1

    }

    class HandleThread implements Runnable {
        //简单处理 fileName 和 applicationName 一样
        private String fileName;

        public HandleThread(String fileName) {
            this.fileName = fileName;
            System.out.println(fileName);
        }

        @Override
        public void run() {
            changeCallBack.callback(fileName, 1);
        }
    }
}
