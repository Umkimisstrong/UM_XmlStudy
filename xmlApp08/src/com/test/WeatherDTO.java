/*
	WeatherDTO.java
*/
package com.test;

public class WeatherDTO
{
	// 주요 속성 구성
	private String tmEf, wf, tmn, tmx, rnSt, img;
	// tmEf → 날짜와 시간 태그, 
	// wf   → 날씨 예보 태그        
	// tmn  → 최저온도 태그 
	// tmx  → 최고온도 태그
	// rnSt → 강수확률
	

	public String getTmEf()
	{
		return tmEf;
	}

	public String getImg()
	{
		return img;
	}

	public void setImg(String img)
	{
		this.img = img;
	}

	public void setTmEf(String tmEf)
	{
		this.tmEf = tmEf;
	}

	public String getWf()
	{
		return wf;
	}

	public void setWf(String wf)
	{
		this.wf = wf;
	}

	public String getTmn()
	{
		return tmn;
	}

	public void setTmn(String tmn)
	{
		this.tmn = tmn;
	}

	public String getTmx()
	{
		return tmx;
	}

	public void setTmx(String tmx)
	{
		this.tmx = tmx;
	}

	public String getRnSt()
	{
		return rnSt;
	}

	public void setRnSt(String rnSt)
	{
		this.rnSt = rnSt;
	}
	
}
