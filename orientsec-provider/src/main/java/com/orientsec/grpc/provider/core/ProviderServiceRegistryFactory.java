/*
 * Copyright 2019 Orient Securities Co., Ltd.
 * Copyright 2019 BoCloud Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.orientsec.grpc.provider.core;

import com.orientsec.grpc.common.constant.GlobalConstants;
import com.orientsec.grpc.common.resource.SystemConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;



/**
 * 服务注册工厂类
 * <p>
 * 为将来出现多种不同的注册方法提供支持。
 * </p>
 *
 * @author sxp
 * @since V1.0 2017/3/21
 */
public class ProviderServiceRegistryFactory {
  private static final Logger logger = LoggerFactory.getLogger(ProviderServiceRegistryFactory.class);

  /**
   * 获取注册实现类
   *
   * @author sxp
   * @since V1.0 2017/3/21
   */
  public static ProviderServiceRegistry getRegistry() {
    String strategy = getProviderServiceStrategy();

    ProviderServiceRegistry registry;

    if ("default".equals(strategy)) {
      registry = new ProviderServiceRegistryImpl();
    } else {
      registry = new ProviderServiceRegistryImpl();
    }

    return registry;
  }

  /**
   * 获取注册策略
   * <p>
   * 以后如有需要提供其它注册策略，可以通过读取配置文件的方式来实现。
   * </p>
   *
   * @author sxp
   * @since V1.0 2017/3/21
   */
  private static String getProviderServiceStrategy() {
    String strategy = null;

    try {
      Properties pros = SystemConfig.getProperties();

      if (pros != null && pros.containsKey(GlobalConstants.PROVIDER_REGISTRY_STRATEGY)) {
        strategy = pros.getProperty(GlobalConstants.PROVIDER_REGISTRY_STRATEGY);
        if (strategy != null) {
          strategy = strategy.trim();
        }
      }
    } catch (Exception e) {
      logger.warn(e.getMessage());
    }

    return strategy;
  }
}
