package cn.business.backend.model;

/**
 * model:商品分类
 * @author TZ
 * @data 2016年5月15日
 */
public class Category {
    /**
     * 表：t_category
     * 字段：id
     * 注释：id
     */
    private Integer id;

    /**
     * 表：t_category
     * 字段：p_id
     * 注释：父类id
     */
    private Integer pId;

    /**
     * 表：t_category
     * 字段：name_zh
     * 注释：分类中文名
     */
    private String nameZh;

    /**
     * 表：t_category
     * 字段：name_en
     * 注释：分类英文名
     */
    private String nameEn;

    /**
     * 表：t_category
     * 字段：level
     * 注释：层级
     */
    private Integer level;

    /**
     * 表：t_category
     * 字段：templete_url
     * 注释：分类模版文件
     */
    private String templeteUrl;

    /**
     * 表：t_category
     * 字段：sort
     * 注释：排序
     */
    private Integer sort;

    /**
     * 表：t_category
     * 字段：is_show
     * 注释：是否显示(1-显示，-1-不显示)
     */
    private Byte isShow;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getTempleteUrl() {
        return templeteUrl;
    }

    public void setTempleteUrl(String templeteUrl) {
        this.templeteUrl = templeteUrl == null ? null : templeteUrl.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Byte getIsShow() {
        return isShow;
    }

    public void setIsShow(Byte isShow) {
        this.isShow = isShow;
    }
}