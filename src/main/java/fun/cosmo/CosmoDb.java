package fun.cosmo;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import static org.kohsuke.args4j.OptionHandlerFilter.ALL;

public class CosmoDb {
    private static final Logger LOGGER = LoggerFactory.getLogger(CosmoDb.class);

    @Option(name="-convert", usage="将txt文件转换为cosmoDB的数据文件,参数为txt路径")
    public String convert;

    public static void main(String args[]){
        new CosmoDb().doMain(args);
    }

    private void doMain(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);


        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java CosmoDb [options...] arguments...");
            parser.printUsage(System.err);
            System.err.println();
            System.err.println("  Example: java CosmoDb" + parser.printExample(ALL));
            return;
        }
    }

}
