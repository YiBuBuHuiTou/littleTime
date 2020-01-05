package com.littletime.authentication.common;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 不使用
 */
@Deprecated
public class JpaEntityManagerFactoryUtils {

    // 实体管理器
    private static EntityManagerFactory entityManagerFactory;

    /**
     * 获取jpa 实体管理器
     * @return jpa 实体管理器
     */
    public static EntityManager getEntityManagerFactory() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("authentication");
        }
        return entityManagerFactory.createEntityManager();
    }
}
