package cn.business.backend.model;

public class GoodsProperty {
    /**
     * 表：t_goods_property
     * 字段：id
     * 注释：id
     */
    private Integer id;

    /**
     * 表：t_goods_property
     * 字段：goods_id
     * 注释：商品id
     */
    private Integer goodsId;

    /**
     * 表：t_goods_property
     * 字段：name_zh
     * 注释：属性名（中文）
     */
    private String nameZh;

    /**
     * 表：t_goods_property
     * 字段：name_en
     * 注释：属性名（英文）
     */
    private String nameEn;

    /**
     * 表：t_goods_property
     * 字段：value_zh
     * 注释：属性值（中文）
     */
    private String valueZh;

    /**
     * 表：t_goods_property
     * 字段：value_en
     * 注释：属性值（英文）
     */
    private String valueEn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
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

    public String getValueZh() {
        return valueZh;
    }

    public void setValueZh(String valueZh) {
        this.valueZh = valueZh == null ? null : valueZh.trim();
    }

    public String getValueEn() {
        return valueEn;
    }

    public void setValueEn(String valueEn) {
        this.valueEn = valueEn == null ? null : valueEn.trim();
    }
}