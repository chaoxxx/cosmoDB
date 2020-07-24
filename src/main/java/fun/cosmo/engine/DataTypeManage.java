package fun.cosmo.engine;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;
import fun.cosmo.util.ClassUtil;

import java.util.ArrayList;
import java.util.List;

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
 * @Date: 2020/7/24 17:42
 */
public enum  DataTypeManage {
    INSTANCE;

    private final List<DataTypeConvert> converts = new ArrayList<DataTypeConvert>(32);

    DataTypeManage() {
        ArrayList<Class> allClassByInterface = ClassUtil.getAllClassByInterface(DataTypeConvert.class);
        for(Class clazz:allClassByInterface){
            try {
                DataTypeConvert o = (DataTypeConvert) clazz.newInstance();
                converts.add(o);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public DataTypeConvert getConvert(FieldType fieldType){

        for(DataTypeConvert cvt:converts){
            if (cvt.support(fieldType))
                return cvt;
        }
        return null;
    }
}
