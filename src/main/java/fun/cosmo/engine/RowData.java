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
 * @Date: 2020/7/24 17:23
 */
public class RowData {
    Field[] fields;
    int size=0;

    private RowData(){}

    public RowData build(byte[] data,RowHead head){
        //TODO
        return null;
    }

    public byte[] toBytes(){
        byte[] rst = new byte[size];
        int idx=0;
        for(Field f:fields){
            DataTypeConvert convert = DataTypeManage.INSTANCE.getConvert(f.fieldType);
            byte[] bytes = convert.convertBytes(f.Value);
            for(byte b:bytes){
                rst[idx] = b;
                idx++;
            }
        }
        return rst;
    }
}
