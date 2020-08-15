package com.online.core.course.domain;

import java.util.ArrayList;
import java.util.List;

//课程的一个章节
public class CourseSectionVO extends CourseSection {

	private static final long serialVersionUID = -5917298165092596073L;

	//课程一章的所有小节
	private List<CourseSection> sections= new ArrayList<CourseSection>();

	public List<CourseSection> getSections() {
		return sections;
	}

	public void setSections(List<CourseSection> sections) {
		this.sections = sections;
	}

	
	
}
