package fun.cosmo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Copyright [2020] [CosmoZhu]
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
 * @Date: 2020/7/23 10:52
 */
public class CaseSeq {
    private static final AtomicInteger ATOMICINTEGER=new AtomicInteger(1);
    public static Integer getAtomicInteger() {
        return ATOMICINTEGER.getAndAdd(1);
    }
}
