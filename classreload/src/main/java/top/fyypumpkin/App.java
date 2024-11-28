package top.fyypumpkin;

import top.fyypumpkin.bridge.IApplication;
import top.fyypumpkin.manager.ApplicationManager;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(2);

        ApplicationManager applicationManager = new ApplicationManager();
        service.execute(applicationManager::init);
        System.out.println("\n");

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            try {
                Map<String, IApplication> apps = applicationManager.getApps();
                System.out.println(apps);
                IApplication application2 = apps.get("Application2");
                System.out.println();
                application2.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 2, 2, TimeUnit.SECONDS);
    }
}
