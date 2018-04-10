
package com.mail.dao;
import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mail.entity.Template;

public interface TemplateDao {

	public Template selectTemplateById(@Param("template_id") int tid);    
	public Template selectTemplateBySubject(@Param("template_subject") String template_subject);   

}
