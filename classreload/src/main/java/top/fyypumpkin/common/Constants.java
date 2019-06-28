package top.fyypumpkin.common;

/**
 * @author fyypumpkin on 6/28/19
 */
public interface Constants {
    String RELOAD_CLASS_PATH = "top.fyypumpkin.applications";

    String BASE_COMPILE_PATH = Class.class.getResource("/").getPath();

    String BASE_PROJECT_PATH = BASE_COMPILE_PATH.substring(0, BASE_COMPILE_PATH.indexOf("target"));

}
