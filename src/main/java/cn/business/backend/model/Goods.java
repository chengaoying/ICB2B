package cn.business.backend.model;

import java.util.Date;

public class Goods {
    /**
     * 表：t_goods
     * 字段：id
     * 注释：id
     */
    private Integer id;

    /**
     * 表：t_goods
     * 字段：model
     * 注释：型号
     */
    private String model;

    /**
     * 表：t_goods
     * 字段：category_id
     * 注释：分类id
     */
    private Integer categoryId;

    /**
     * 表：t_goods
     * 字段：brand_id
     * 注释：品牌id
     */
    private Integer brandId;

    /**
     * 表：t_goods
     * 字段：seller_id
     * 注释：商家id
     */
    private Integer sellerId;

    /**
     * 表：t_goods
     * 字段：seller_name
     * 注释：商家名称
     */
    private String sellerName;

    /**
     * 表：t_goods
     * 字段：pack
     * 注释：包装
     */
    private String pack;

    /**
     * 表：t_goods
     * 字段：wrap
     * 注释：封装
     */
    private String wrap;

    /**
     * 表：t_goods
     * 字段：is_in_stock
     * 注释：是否现货(1-现货，-1-期货)
     */
    private Byte isInStock;

    /**
     * 表：t_goods
     * 字段：batch
     * 注释：批次
     */
    private String batch;

    /**
     * 表：t_goods
     * 字段：img_url
     * 注释：商品图片
     */
    private String imgUrl;

    /**
     * 表：t_goods
     * 字段：doc_url
     * 注释：参数文档
     */
    private String docUrl;

    /**
     * 表：t_goods
     * 字段：specs_url
     * 注释：规格文档
     */
    private String specsUrl;

    /**
     * 表：t_goods
     * 字段：rohs
     * 注释：无铅情况
     */
    private String rohs;

    /**
     * 表：t_goods
     * 字段：normal_price
     * 注释：原价
     */
    private Float normalPrice;

    /**
     * 表：t_goods
     * 字段：type
     * 注释：商品类型
     */
    private Byte type;

    /**
     * 表：t_goods
     * 字段：inventory
     * 注释：库存
     */
    private Integer inventory;

    /**
     * 表：t_goods
     * 字段：keywords
     * 注释：商品关键字
     */
    private String keywords;

    /**
     * 表：t_goods
     * 字段：is_delete
     * 注释：是否已删除（-1-已删除，1-正常）
     */
    private Byte isDelete;

    /**
     * 表：t_goods
     * 字段：is_new
     * 注释：是否新品（1-是，-1-不是）
     */
    private Byte isNew;

    /**
     * 表：t_goods
     * 字段：is_hot
     * 注释：是否热卖（1-是，-1-不是）
     */
    private Byte isHot;

    /**
     * 表：t_goods
     * 字段：give_points
     * 注释：购买送的积分数
     */
    private Integer givePoints;

    /**
     * 表：t_goods
     * 字段：sort
     * 注释：排序
     */
    private Byte sort;

    /**
     * 表：t_goods
     * 字段：add_time
     * 注释：添加时间
     */
    private Date addTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model == null ? null : model.trim();
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName == null ? null : sellerName.trim();
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack == null ? null : pack.trim();
    }

    public String getWrap() {
        return wrap;
    }

    public void setWrap(String wrap) {
        this.wrap = wrap == null ? null : wrap.trim();
    }

    public Byte getIsInStock() {
        return isInStock;
    }

    public void setIsInStock(Byte isInStock) {
        this.isInStock = isInStock;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch == null ? null : batch.trim();
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public String getDocUrl() {
        return docUrl;
    }

    public void setDocUrl(String docUrl) {
        this.docUrl = docUrl == null ? null : docUrl.trim();
    }

    public String getSpecsUrl() {
        return specsUrl;
    }

    public void setSpecsUrl(String specsUrl) {
        this.specsUrl = specsUrl == null ? null : specsUrl.trim();
    }

    public String getRohs() {
        return rohs;
    }

    public void setRohs(String rohs) {
        this.rohs = rohs == null ? null : rohs.trim();
    }

    public Float getNormalPrice() {
        return normalPrice;
    }

    public void setNormalPrice(Float normalPrice) {
        this.normalPrice = normalPrice;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public Byte getIsNew() {
        return isNew;
    }

    public void setIsNew(Byte isNew) {
        this.isNew = isNew;
    }

    public Byte getIsHot() {
        return isHot;
    }

    public void setIsHot(Byte isHot) {
        this.isHot = isHot;
    }

    public Integer getGivePoints() {
        return givePoints;
    }

    public void setGivePoints(Integer givePoints) {
        this.givePoints = givePoints;
    }

    public Byte getSort() {
        return sort;
    }

    public void setSort(Byte sort) {
        this.sort = sort;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}