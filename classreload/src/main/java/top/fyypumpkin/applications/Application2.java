package top.fyypumpkin.applications;

import top.fyypumpkin.bridge.IApplication;

/**
 * @author fyypumpkin on 6/28/19
 */
public class Application2 implements IApplication {
    @Override
    public void init() {
        System.out.println("application2 init");
    }

    @Override
    public void destroy() {
        System.out.println("application2 destroy");
    }

    @Override
    public void execute() {
        System.out.println("Application2 success execute");
    }

    @Override
    public String getApplicationName() {
        return "Application2";
    }
}
