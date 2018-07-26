package com.mail.common;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashSet;

import org.thymeleaf.TemplateProcessingParameters;
import org.thymeleaf.resourceresolver.IResourceResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import com.mail.dao.TemplateDao;
import com.mail.delegate.ApplicationContextProvider;
import com.mail.entity.Template;

public class ThymeleafTemplateResolver extends TemplateResolver {
	 private final static String PREFIX = ""; 

	    public ThymeleafTemplateResolver() {
	        setResourceResolver(new DbResourceResolver());
	        HashSet<String> set= new HashSet<String>();
	        set.add(PREFIX + "*");
	        setResolvablePatterns(set);
	    }

	    @Override
	    protected String computeResourceName(TemplateProcessingParameters params) {
	        String templateName = params.getTemplateName();
	        return templateName.substring(PREFIX.length());
	    }

	    private class DbResourceResolver implements IResourceResolver {

	        @Override
	        public InputStream getResourceAsStream(TemplateProcessingParameters params, String template) {
	        	TemplateDao thymeleaftemplateDao = ApplicationContextProvider.getApplicationContext().getBean(TemplateDao.class);
	            Template thymeleafTemplate = thymeleaftemplateDao.selectTemplateBySubject(template);
				if (thymeleafTemplate != null) {
	                return new ByteArrayInputStream(thymeleafTemplate.getTemplate_content().getBytes());
	            }
	            return null;
	        }

	        @Override
	        public String getName() {
	            return "dbResourceResolver";
	        }
	    }
}
