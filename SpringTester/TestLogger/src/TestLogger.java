import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Created by tianjiyuan on 16/3/29.
 */
public class TestLogger {

    private static Logger logger = Logger.getLogger(TestLogger.class);

    /**
     * @param args
     */
    public static void main(String[] args) {

        PropertyConfigurator.configure("log4j.properties");
        // System.out.println("This is println message.");

        // 记录debug级别的信息
        logger.debug("This is debug message.");
        // 记录info级别的信息
        logger.info("This is info message.");
        // 记录error级别的信息
        logger.error("This is error message.");
    }
}
