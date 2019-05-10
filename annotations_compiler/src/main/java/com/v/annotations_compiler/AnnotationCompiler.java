package com.v.annotations_compiler;


import com.google.auto.service.AutoService;
import com.v.annotations.BindPath;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

/**
 * 注解处理器  处理我们定义的注解
 */

@AutoService(Processor.class) //注册我们的注解处理器
public class AnnotationCompiler extends AbstractProcessor {
    /**
     * 生成文件对象
     */
    private Filer mFiler;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mFiler=processingEnvironment.getFiler();
    }

    /**
     * 声明我的注解处理器要处理的注解
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types=new HashSet<>();
        types.add(BindPath.class.getCanonicalName());
        return types;
    }

    /**
     * 声明注解处理器支持的原版本
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return processingEnv.getSourceVersion();
    }

    /**
     * 核心方法
     */
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        return false;
    }
}
