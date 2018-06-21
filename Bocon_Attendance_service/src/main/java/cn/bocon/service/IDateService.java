package cn.bocon.service;

import java.text.ParseException;
import java.util.List;

import cn.bocon.entity.ChinaDate;

import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;

public interface IDateService {

	public String getVocationName(DomNodeList<HtmlElement> htmlElements,
			String date) throws ParseException;

	public List<ChinaDate> getCurrentDateInfo();

	public ChinaDate getTodayInfo();

}