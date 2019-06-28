package top.fyypumpkin.applications;

import top.fyypumpkin.bridge.IApplication;

/**
 * @author fyypumpkin on 6/28/19
 */
public class Application1 implements IApplication {
    @Override
    public void init() {
        System.out.println("application1 init change");
    }

    @Override
    public void destroy() {
        System.out.println("application1 destroy");
    }

    @Override
    public void execute() {

    }

    @Override
    public String getApplicationName() {
        return "Application1";
    }
}
