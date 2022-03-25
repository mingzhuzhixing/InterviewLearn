package com.v.module_retrofit.http;

import retrofit2.Converter;

/**
 * Class description here
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-04-25 11:37
 */

public class FastJsonConverterFactory  extends Converter.Factory {

    public static FastJsonConverterFactory create(){
        return new FastJsonConverterFactory();
    }

    private FastJsonConverterFactory(){

    }

}
