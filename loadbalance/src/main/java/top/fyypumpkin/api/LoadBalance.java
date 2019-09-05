package top.fyypumpkin.api;

/**
 * @author fyypumpkin on 6/28/19
 */
public interface LoadBalance<T> {
    T select();
}
