package com.ocean.dsgoods.entity;

public class SortModelInfo {

	private String name;   //��ʾ������
	private String num;   //��ʾ������
	private String kpi;   //��ʾ������
	private int dispatch;   //��ʾ������
	private String sortLetters;  //��ʾ����ƴ��������ĸ
	private String id;
	private String heading;

	public String getHeading() {
		return heading;
	}


	public void setHeading(String heading) {
		this.heading = heading;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getKpi() {
		return kpi;
	}

	public void setKpi(String kpi) {
		this.kpi = kpi;
	}

	public int getDispatch() {
		return dispatch;
	}

	public void setDispatch(int dispatch) {
		this.dispatch = dispatch;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSortLetters() {
		return sortLetters;
	}
	public void setSortLetters(String sortLetters) {
		this.sortLetters = sortLetters;
	}
}
