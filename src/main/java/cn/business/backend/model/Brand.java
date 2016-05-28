package cn.business.backend.model;

/**
 * model:品牌
 * @author TZ
 * @data 2016年5月15日
 */
public class Brand {

	/**
	 * 表：t_brand
	 * 字段：id
	 * 注释：id
	 */
	private Integer id;

	/**
	 * 表：t_brand
	 * 字段：name_zh
	 * 注释：品牌中文名
	 */
	private String nameZh;

	/**
	 * 表：t_brand
	 * 字段：name_en
	 * 注释：品牌英文名
	 */
	private String nameEn;

	/**
	 * 表：t_brand
	 * 字段：logo
	 * 注释：品牌Logo
	 */
	private String logo;

	/**
	 * 表：t_brand
	 * 字段：desc_zh
	 * 注释：品牌描述中文
	 */
	private String descZh;

	/**
	 * 表：t_brand
	 * 字段：desc_en
	 * 注释：品牌英文描述
	 */
	private String descEn;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNameZh() {
		return nameZh;
	}

	public void setNameZh(String nameZh) {
		this.nameZh = nameZh == null ? null : nameZh.trim();
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn == null ? null : nameEn.trim();
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo == null ? null : logo.trim();
	}

	public String getDescZh() {
		return descZh;
	}

	public void setDescZh(String descZh) {
		this.descZh = descZh == null ? null : descZh.trim();
	}

	public String getDescEn() {
		return descEn;
	}

	public void setDescEn(String descEn) {
		this.descEn = descEn == null ? null : descEn.trim();
	}
}
