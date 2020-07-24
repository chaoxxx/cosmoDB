package fun.cosmo.engine;

import com.sun.corba.se.impl.encoding.CodeSetConversion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Copyright 2020 CosmoZhu
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @Author: CosmoZhu
 * @Date: 2020/7/24 14:55
 */
public class Page {
    private static final String dbFilePath="D:/page.db";
    private static final Logger LOGGER = LoggerFactory.getLogger(Page.class);

    private static File dbFile = new File(dbFilePath);

    public void write(Integer[] datas) throws IOException {
        FileOutputStream os = new FileOutputStream(dbFilePath);

        ByteArrayOutputStream  headerBos = new ByteArrayOutputStream(16);
        DataOutputStream headerDos = new DataOutputStream(headerBos);

        ByteArrayOutputStream rowBos = new ByteArrayOutputStream(datas.length * 4);
        DataOutputStream rowDos = new DataOutputStream(rowBos);

        for(Integer d:datas){
            rowDos.writeInt(d);
        }

        byte[] head = new byte[16];
        String msg = "helloworld";
        byte[] msgBytes = msg.getBytes();


        for(int index =0,len=head.length;index<len;index++){
            if(index<msgBytes.length){
                head[index] = msgBytes[index];
            }
        }


        headerDos.write(head);

        headerDos.flush();
        headerBos.writeTo(os);

        rowDos.flush();
        rowBos.writeTo(os);
    }

    public void read() throws IOException{
        byte[] headBuff = new byte[16];
        byte[] dataBuff = new byte[4];



        RandomAccessFile randomAccessFile = new RandomAccessFile(dbFilePath,"r");


        randomAccessFile.read(headBuff);
        LOGGER.info("head is {}",headBuff);
        while (true){
            if(randomAccessFile.read(dataBuff) == -1)
                break;

            LOGGER.info("data is {}",dataBuff);
        }
    }
}
