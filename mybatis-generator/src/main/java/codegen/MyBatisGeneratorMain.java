package codegen;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author andy.an
 * @since 2018/2/4
 */
public class MyBatisGeneratorMain {

    private static final boolean OVERWRITE = true;
    private static final String GENCFG_PATH = "/generatorConfig.xml";

    public static void main(String[] args) {
        List<String> warnings = new ArrayList<>();

        DefaultShellCallback callback = new DefaultShellCallback(OVERWRITE);

        ConfigurationParser parser = new ConfigurationParser(warnings);

        MyBatisGenerator generator;
        Configuration configuration;
        try {
            File configFile = new File(MyBatisGeneratorMain.class.getResource(GENCFG_PATH).getFile());
            System.out.println(configFile.getAbsolutePath());
            configuration = parser.parseConfiguration(configFile);

            generator = new MyBatisGenerator(configuration, callback, warnings);
            generator.generate(null);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (warnings.size() > 0) {
            System.out.println("------warnings-------");
            for (String warning : warnings) {
                System.out.printf("%s %n", warning);
            }
        }

    }
}
