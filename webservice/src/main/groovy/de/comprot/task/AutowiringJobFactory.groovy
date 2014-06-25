package de.comprot.task

import org.quartz.spi.TriggerFiredBundle
import org.springframework.beans.factory.config.AutowireCapableBeanFactory
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.scheduling.quartz.SpringBeanJobFactory

class AutowiringJobFactory extends SpringBeanJobFactory implements ApplicationContextAware {

    transient AutowireCapableBeanFactory beanFactory

    @Override void setApplicationContext(ApplicationContext context) {
        beanFactory = context.getAutowireCapableBeanFactory()
    }

    @Override def createJobInstance(TriggerFiredBundle bundle) {
        def job = super.createJobInstance bundle
        beanFactory.autowireBean job
        return job
    }

}