package cn.business.backend.model;

/**
 * model:商品属性
 * @author TZ
 * @data 2016年5月15日
 */
public class Property {
    /**
     * 表：t_property
     * 字段：id
     * 注释：id
     */
    private Integer id;

    /**
     * 表：t_property
     * 字段：name_zh
     * 注释：属性中文名
     */
    private String nameZh;

    /**
     * 表：t_property
     * 字段：name_en
     * 注释：属性英文名
     */
    private String nameEn;

    /**
     * 表：t_property
     * 字段：category_id
     * 注释：分类id
     */
    private Integer categoryId;

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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}