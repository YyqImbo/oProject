package com.online.core.business;

import java.io.InputStream;
import java.util.List;

import com.online.core.course.domain.CourseSectionVO;

public interface ICourseSectionBusiness {

	/**
	 * 批量添加
	 * @param courseSections
	 */
	void batchAdd(List<CourseSectionVO> courseSections);
	
	/**
	 * 批量导入
	 */
	void batchImport(Long courseId, InputStream is);
	
}
