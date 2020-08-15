package com.online.core.consts.domain;

import java.util.ArrayList;
import java.util.List;

import com.online.core.course.domain.Course;

//父分类
public class ConstsClassifyVO extends ConstsClassify {

	private static final long serialVersionUID = 2871659443768879596L;

	//父分类下的所有子分类
	private List<ConstsClassify> subClassifyList= new ArrayList<ConstsClassify>();
	
	//首页轮播区课程推荐列表
	private List<Course> recomdCourseList ;

	public List<ConstsClassify> getSubClassifyList() {
		return subClassifyList;
	}

	public void setSubClassifyList(List<ConstsClassify> subClassifyList) {
		this.subClassifyList = subClassifyList;
	}

	public List<Course> getRecomdCourseList() {
		return recomdCourseList;
	}

	public void setRecomdCourseList(List<Course> recomdCourseList) {
		this.recomdCourseList = recomdCourseList;
	}
	
	
}
