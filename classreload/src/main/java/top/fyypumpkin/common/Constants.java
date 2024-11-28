package top.fyypumpkin.common;

/**
 * @author fyypumpkin on 6/28/19
 */
public interface Constants {
    // 监听的包
    String RELOAD_CLASS_PATH = "top.fyypumpkin.applications";

    // 当前编译文件的路径
    String BASE_COMPILE_PATH = Constants.class.getResource("/").getPath();

    String BASE_PROJECT_PATH = BASE_COMPILE_PATH.substring(0, BASE_COMPILE_PATH.indexOf("target"));

}
