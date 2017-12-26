package com.zxtech.support.spring;

import org.springframework.beans.factory.config.BeanDefinition;

public class AnnotationBeanNameGenerator extends org.springframework.context.annotation.AnnotationBeanNameGenerator {

	@Override
	protected String buildDefaultBeanName(BeanDefinition definition) {
		return definition.getBeanClassName();
	}

}
