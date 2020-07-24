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
 * @Date: 2020/7/24 17:35
 */
public class IntConvert implements DataTypeConvert<Integer> {
    @Override
    public boolean support(FieldType fieldType) {
        return fieldType == FieldType.INT;
    }

    @Override
    public byte[] convertBytes(Integer integer) {
        return new byte[0];
    }

    @Override
    public Integer convertType(byte[] v) {
        return null;
    }
}
