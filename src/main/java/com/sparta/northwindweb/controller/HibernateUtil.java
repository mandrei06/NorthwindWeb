package com.sparta.northwindweb.controller;

import com.fasterxml.classmate.AnnotationConfiguration;
import com.fasterxml.classmate.AnnotationInclusion;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.lang.annotation.Annotation;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    private static final Configuration configuration = new Configuration().configure();

    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml)
            // config file.
            sessionFactory = new AnnotationConfiguration() {
                public Configuration configure() {
                    return null;
                }

                @Override
                public AnnotationInclusion getInclusionForClass(Class<? extends Annotation> aClass) {
                    return null;
                }

                @Override
                public AnnotationInclusion getInclusionForConstructor(Class<? extends Annotation> aClass) {
                    return null;
                }

                @Override
                public AnnotationInclusion getInclusionForField(Class<? extends Annotation> aClass) {
                    return null;
                }

                @Override
                public AnnotationInclusion getInclusionForMethod(Class<? extends Annotation> aClass) {
                    return null;
                }

                @Override
                public AnnotationInclusion getInclusionForParameter(Class<? extends Annotation> aClass) {
                    return null;
                }
            }.configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception.
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}