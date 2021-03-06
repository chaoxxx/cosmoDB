package fun.cosmo;



import fun.cosmo.exception.DbException;
import fun.cosmo.transaction.TransactionAbortedException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class CosmoDbTestCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(CosmoDbTestCase.class);
    private int index = 1;

    @Test
    public void convertTextToDat() throws IOException, TransactionAbortedException, DbException {
        LOGGER.info("测试案例 [{}] 测试txt转换为dat.",index++);

        String fileName = this.getClass().getClassLoader().getResource("dummy_convert/dummy1.txt").getPath();
        String[] args = {"convert",fileName,"4"};
        CosmoDb.main(args);
    }
}
