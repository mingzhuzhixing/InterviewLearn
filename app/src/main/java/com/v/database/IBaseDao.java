package com.v.database;

import java.util.List;

/**
 * Class description here
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-05-07 14:35
 */
public interface IBaseDao<T> {

    Long insert(T entity);

    /**
     * 查
     */
    List<T> queue(T where);

    /**
     * 删
     */
}
