package com.eyeieye.melody.demo.domain;

import java.util.Locale;

import com.eyeieye.melody.web.locale.velocity.LocaleRender;

/**
 * 演示如何在页面上自动根据当前访问折的locale输出
 * 
 * @author fish
 * 
 */
public class CommonBean {
	// 中文名称
	private String nameGb;

	// 英文名称
	private String nameEn;

	/**
	 * 得到自动输出的包装类,demo中只区分了语言,没有区分国家
	 * 
	 * @return
	 */
	public LocaleRender getNameLocale() {
		return new LocaleRender() {
			@Override
			public String getByLocale(Locale locale) {
				// 如果当前语言为英语则输出nameEn
				if (locale.getLanguage().equals("en")) {
					return nameEn;
				}
				// 如果是汉语,并且缺省也是输出中文
				return nameGb;
			}
		};
	}

	public String getNameGb() {
		return nameGb;
	}

	public void setNameGb(String nameGb) {
		this.nameGb = nameGb;
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}
}
