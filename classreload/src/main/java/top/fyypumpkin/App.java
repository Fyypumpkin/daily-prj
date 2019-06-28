package top.fyypumpkin;

import top.fyypumpkin.manager.ApplicationManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ExecutorService service = Executors.newSingleThreadExecutor();

        service.execute(() -> {
            ApplicationManager applicationManager = new ApplicationManager();
            applicationManager.init();
        });
    }
}
