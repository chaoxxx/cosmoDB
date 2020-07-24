package fun.cosmo;

import com.sun.xml.internal.ws.util.StringUtils;
import fun.cosmo.core.dbs.tables.BufferPool;
import fun.cosmo.core.dbs.tables.HeapFileEncoder;
import fun.cosmo.core.dbs.tables.tuples.field.Type;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.kohsuke.args4j.OptionHandlerFilter.ALL;

public class CosmoDb {
    private static final Logger LOGGER = LoggerFactory.getLogger(CosmoDb.class);

    @Option(name="-convert", usage="将txt文件转换为cosmoDB的数据文件,参数为txt路径")
    public String convert;

    @Option(name="-col", usage="数据文件列数")
    public int col;

    private static final char FIELDSEPARATOR = ',';

    public static void main(String args[]){
        new CosmoDb().doMain(args);
    }

    private void doMain(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
            
            if(null !=convert && !"".equals(convert)){
                if(col != 0){
                    File sourceTxtFile = new File(convert);
                    File targetDatFile = new File(convert.replaceAll(".txt", ".dat"));
                    Type[] ts = new Type[col];

                    for (int i = 0; i < col; i++)
                        ts[i] = Type.INT_TYPE;

                    HeapFileEncoder.convert(sourceTxtFile, targetDatFile,
                            BufferPool.PAGE_SIZE, col, ts, FIELDSEPARATOR);

                }
            }

        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java CosmoDb [options...] arguments...");
            parser.printUsage(System.err);
            System.err.println();
            System.err.println("  Example: java CosmoDb" + parser.printExample(ALL));
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
