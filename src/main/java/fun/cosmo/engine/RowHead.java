package fun.cosmo.engine;

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
 * @Date: 2020/7/24 16:28
 */
public class RowHead {
    /**
     * 行首数据长度
     */
    private static final int HEAD_SIZE = 32;
    short nullFlag;
    long createTime;
    long updateTime;
    boolean delFlag;

    private RowHead(short nullFlag, long createTime, long updateTime, boolean delFlag) {
        this.nullFlag = nullFlag;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.delFlag = delFlag;
    }

    /**
     * 创建行首信息
     * @param head
     * @return
     */
    public RowHead build(byte[] head) throws RowException {

        if(head.length != HEAD_SIZE){
            throw new RowException("当前行首数据长度为："+head.length+",与系统规定的长度"+HEAD_SIZE+"不相等.");
        }

        nullFlag = (short) RowHead.bytes2Short(head, 0);
        createTime = RowHead.bytes2Long(head, 2, 8);
        updateTime = RowHead.bytes2Long(head, 10, 8);
        delFlag=RowHead.byte2Boolean(head[HEAD_SIZE-1]);
        return new RowHead(nullFlag,createTime,updateTime,delFlag);
    }

    /**
     * 字节数组转整形
     * @param b
     * @param start
     * @param len
     * @return
     */
    public static long bytes2Long(byte[] b, int start, int len) {
        long sum = 0;
        int end = start + len;
        for (int i = start; i < end; i++) {
            //在byte转int时，需要保持二进制补码的一致性，所以要& 0xff。见博客下源码分析：
            long n = ((long) b[i]) & 0xff;
            n <<= (--len) * 8;
            sum = n + sum;
        }
        return sum;
    }


    /**
     * 整数转字节数组
     * @param i
     * @return
     */
    public static byte[] long2Byte(long i) {
        byte[] b = new byte[8];
        for(byte ind=7;ind>0;ind--){
            b[7-ind] = (byte)((i >> (ind*8)) & 0xFF);
        }
        return b;
    }


    /**
     * 字节数组转短整形
     * @param b
     * @param start
     * @return
     */
    public static short bytes2Short(byte[] b, int start) {
        short sum = 0;
        int len = 2;
        int end = start + len;
        for (int i = start; i < end; i++) {
            //在byte转int时，需要保持二进制补码的一致性，所以要& 0xff。见博客下源码分析：
            short n = (short) (b[i] & 0xff);
            n <<= (--len) * 8;
            sum = (short) (n + sum);
        }
        return sum;
    }


    /**
     * 短整数转字节数组
     * @param i
     * @return
     */
    public static byte[] short2Byte(short i) {
        byte[] b = new byte[2];
        b[0] = (byte)((i >> 8) & 0xFF);
        b[1] = (byte)(i & 0xFF);
        return b;
    }
    
    /**
     * 将byte转换为boolean
     *
     * @param b byte
     * @return boolean数组
     */
    public static boolean byte2Boolean(byte b) {
        return 0==b?false:true;
    }


    /**
     * 将byte转换为boolean
     *
     * @param b byte
     * @return boolean数组
     */
    public static byte boolean2Byte(boolean b) {
        return (byte) (b?1:0);
    }

    /**
     * 将行首数据转换位字节数组
     * @return
     */
    public byte[] toBytes(){
        byte[] result = new byte[HEAD_SIZE];
        int index=0;
        byte[] bytes = RowHead.short2Byte(nullFlag);
        for (byte b:bytes){
            result[index] = b;
            index++;
        }
        bytes = RowHead.long2Byte(createTime);
        for (byte b:bytes){
            result[index] = b;
            index++;
        }
        bytes = RowHead.long2Byte(updateTime);
        for (byte b:bytes){
            result[index] = b;
            index++;
        }
        for(int i=result.length;i<HEAD_SIZE;i++){
            result[index] = 0;
            index++;
        }
        result[index] = RowHead.boolean2Byte(delFlag);;
        return result;
    }

}
