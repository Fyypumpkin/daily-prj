package top.fyypumpkin.bridge;

/**
 * @author fyypumpkin on 6/28/19
 */
public interface IApplication {
    void init();

    void destroy();

    void execute();

    String getApplicationName();
}
