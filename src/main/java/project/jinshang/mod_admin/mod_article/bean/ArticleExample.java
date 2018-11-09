package project.jinshang.mod_admin.mod_article.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArticleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ArticleExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andDocidIsNull() {
            addCriterion("docid is null");
            return (Criteria) this;
        }

        public Criteria andDocidIsNotNull() {
            addCriterion("docid is not null");
            return (Criteria) this;
        }

        public Criteria andDocidEqualTo(Long value) {
            addCriterion("docid =", value, "docid");
            return (Criteria) this;
        }

        public Criteria andDocidNotEqualTo(Long value) {
            addCriterion("docid <>", value, "docid");
            return (Criteria) this;
        }

        public Criteria andDocidGreaterThan(Long value) {
            addCriterion("docid >", value, "docid");
            return (Criteria) this;
        }

        public Criteria andDocidGreaterThanOrEqualTo(Long value) {
            addCriterion("docid >=", value, "docid");
            return (Criteria) this;
        }

        public Criteria andDocidLessThan(Long value) {
            addCriterion("docid <", value, "docid");
            return (Criteria) this;
        }

        public Criteria andDocidLessThanOrEqualTo(Long value) {
            addCriterion("docid <=", value, "docid");
            return (Criteria) this;
        }

        public Criteria andDocidIn(List<Long> values) {
            addCriterion("docid in", values, "docid");
            return (Criteria) this;
        }

        public Criteria andDocidNotIn(List<Long> values) {
            addCriterion("docid not in", values, "docid");
            return (Criteria) this;
        }

        public Criteria andDocidBetween(Long value1, Long value2) {
            addCriterion("docid between", value1, value2, "docid");
            return (Criteria) this;
        }

        public Criteria andDocidNotBetween(Long value1, Long value2) {
            addCriterion("docid not between", value1, value2, "docid");
            return (Criteria) this;
        }

        public Criteria andDoctitleIsNull() {
            addCriterion("doctitle is null");
            return (Criteria) this;
        }

        public Criteria andDoctitleIsNotNull() {
            addCriterion("doctitle is not null");
            return (Criteria) this;
        }

        public Criteria andDoctitleEqualTo(String value) {
            addCriterion("doctitle =", value, "doctitle");
            return (Criteria) this;
        }

        public Criteria andDoctitleNotEqualTo(String value) {
            addCriterion("doctitle <>", value, "doctitle");
            return (Criteria) this;
        }

        public Criteria andDoctitleGreaterThan(String value) {
            addCriterion("doctitle >", value, "doctitle");
            return (Criteria) this;
        }

        public Criteria andDoctitleGreaterThanOrEqualTo(String value) {
            addCriterion("doctitle >=", value, "doctitle");
            return (Criteria) this;
        }

        public Criteria andDoctitleLessThan(String value) {
            addCriterion("doctitle <", value, "doctitle");
            return (Criteria) this;
        }

        public Criteria andDoctitleLessThanOrEqualTo(String value) {
            addCriterion("doctitle <=", value, "doctitle");
            return (Criteria) this;
        }

        public Criteria andDoctitleLike(String value) {
            addCriterion("doctitle like", value, "doctitle");
            return (Criteria) this;
        }

        public Criteria andDoctitleNotLike(String value) {
            addCriterion("doctitle not like", value, "doctitle");
            return (Criteria) this;
        }

        public Criteria andDoctitleIn(List<String> values) {
            addCriterion("doctitle in", values, "doctitle");
            return (Criteria) this;
        }

        public Criteria andDoctitleNotIn(List<String> values) {
            addCriterion("doctitle not in", values, "doctitle");
            return (Criteria) this;
        }

        public Criteria andDoctitleBetween(String value1, String value2) {
            addCriterion("doctitle between", value1, value2, "doctitle");
            return (Criteria) this;
        }

        public Criteria andDoctitleNotBetween(String value1, String value2) {
            addCriterion("doctitle not between", value1, value2, "doctitle");
            return (Criteria) this;
        }

        public Criteria andDoccontentIsNull() {
            addCriterion("doccontent is null");
            return (Criteria) this;
        }

        public Criteria andDoccontentIsNotNull() {
            addCriterion("doccontent is not null");
            return (Criteria) this;
        }

        public Criteria andDoccontentEqualTo(String value) {
            addCriterion("doccontent =", value, "doccontent");
            return (Criteria) this;
        }

        public Criteria andDoccontentNotEqualTo(String value) {
            addCriterion("doccontent <>", value, "doccontent");
            return (Criteria) this;
        }

        public Criteria andDoccontentGreaterThan(String value) {
            addCriterion("doccontent >", value, "doccontent");
            return (Criteria) this;
        }

        public Criteria andDoccontentGreaterThanOrEqualTo(String value) {
            addCriterion("doccontent >=", value, "doccontent");
            return (Criteria) this;
        }

        public Criteria andDoccontentLessThan(String value) {
            addCriterion("doccontent <", value, "doccontent");
            return (Criteria) this;
        }

        public Criteria andDoccontentLessThanOrEqualTo(String value) {
            addCriterion("doccontent <=", value, "doccontent");
            return (Criteria) this;
        }

        public Criteria andDoccontentLike(String value) {
            addCriterion("doccontent like", value, "doccontent");
            return (Criteria) this;
        }

        public Criteria andDoccontentNotLike(String value) {
            addCriterion("doccontent not like", value, "doccontent");
            return (Criteria) this;
        }

        public Criteria andDoccontentIn(List<String> values) {
            addCriterion("doccontent in", values, "doccontent");
            return (Criteria) this;
        }

        public Criteria andDoccontentNotIn(List<String> values) {
            addCriterion("doccontent not in", values, "doccontent");
            return (Criteria) this;
        }

        public Criteria andDoccontentBetween(String value1, String value2) {
            addCriterion("doccontent between", value1, value2, "doccontent");
            return (Criteria) this;
        }

        public Criteria andDoccontentNotBetween(String value1, String value2) {
            addCriterion("doccontent not between", value1, value2, "doccontent");
            return (Criteria) this;
        }

        public Criteria andDocorderIsNull() {
            addCriterion("docorder is null");
            return (Criteria) this;
        }

        public Criteria andDocorderIsNotNull() {
            addCriterion("docorder is not null");
            return (Criteria) this;
        }

        public Criteria andDocorderEqualTo(Integer value) {
            addCriterion("docorder =", value, "docorder");
            return (Criteria) this;
        }

        public Criteria andDocorderNotEqualTo(Integer value) {
            addCriterion("docorder <>", value, "docorder");
            return (Criteria) this;
        }

        public Criteria andDocorderGreaterThan(Integer value) {
            addCriterion("docorder >", value, "docorder");
            return (Criteria) this;
        }

        public Criteria andDocorderGreaterThanOrEqualTo(Integer value) {
            addCriterion("docorder >=", value, "docorder");
            return (Criteria) this;
        }

        public Criteria andDocorderLessThan(Integer value) {
            addCriterion("docorder <", value, "docorder");
            return (Criteria) this;
        }

        public Criteria andDocorderLessThanOrEqualTo(Integer value) {
            addCriterion("docorder <=", value, "docorder");
            return (Criteria) this;
        }

        public Criteria andDocorderIn(List<Integer> values) {
            addCriterion("docorder in", values, "docorder");
            return (Criteria) this;
        }

        public Criteria andDocorderNotIn(List<Integer> values) {
            addCriterion("docorder not in", values, "docorder");
            return (Criteria) this;
        }

        public Criteria andDocorderBetween(Integer value1, Integer value2) {
            addCriterion("docorder between", value1, value2, "docorder");
            return (Criteria) this;
        }

        public Criteria andDocorderNotBetween(Integer value1, Integer value2) {
            addCriterion("docorder not between", value1, value2, "docorder");
            return (Criteria) this;
        }

        public Criteria andDocaddressIsNull() {
            addCriterion("docaddress is null");
            return (Criteria) this;
        }

        public Criteria andDocaddressIsNotNull() {
            addCriterion("docaddress is not null");
            return (Criteria) this;
        }

        public Criteria andDocaddressEqualTo(String value) {
            addCriterion("docaddress =", value, "docaddress");
            return (Criteria) this;
        }

        public Criteria andDocaddressNotEqualTo(String value) {
            addCriterion("docaddress <>", value, "docaddress");
            return (Criteria) this;
        }

        public Criteria andDocaddressGreaterThan(String value) {
            addCriterion("docaddress >", value, "docaddress");
            return (Criteria) this;
        }

        public Criteria andDocaddressGreaterThanOrEqualTo(String value) {
            addCriterion("docaddress >=", value, "docaddress");
            return (Criteria) this;
        }

        public Criteria andDocaddressLessThan(String value) {
            addCriterion("docaddress <", value, "docaddress");
            return (Criteria) this;
        }

        public Criteria andDocaddressLessThanOrEqualTo(String value) {
            addCriterion("docaddress <=", value, "docaddress");
            return (Criteria) this;
        }

        public Criteria andDocaddressLike(String value) {
            addCriterion("docaddress like", value, "docaddress");
            return (Criteria) this;
        }

        public Criteria andDocaddressNotLike(String value) {
            addCriterion("docaddress not like", value, "docaddress");
            return (Criteria) this;
        }

        public Criteria andDocaddressIn(List<String> values) {
            addCriterion("docaddress in", values, "docaddress");
            return (Criteria) this;
        }

        public Criteria andDocaddressNotIn(List<String> values) {
            addCriterion("docaddress not in", values, "docaddress");
            return (Criteria) this;
        }

        public Criteria andDocaddressBetween(String value1, String value2) {
            addCriterion("docaddress between", value1, value2, "docaddress");
            return (Criteria) this;
        }

        public Criteria andDocaddressNotBetween(String value1, String value2) {
            addCriterion("docaddress not between", value1, value2, "docaddress");
            return (Criteria) this;
        }

        public Criteria andDocshowIsNull() {
            addCriterion("docshow is null");
            return (Criteria) this;
        }

        public Criteria andDocshowIsNotNull() {
            addCriterion("docshow is not null");
            return (Criteria) this;
        }

        public Criteria andDocshowEqualTo(Short value) {
            addCriterion("docshow =", value, "docshow");
            return (Criteria) this;
        }

        public Criteria andDocshowNotEqualTo(Short value) {
            addCriterion("docshow <>", value, "docshow");
            return (Criteria) this;
        }

        public Criteria andDocshowGreaterThan(Short value) {
            addCriterion("docshow >", value, "docshow");
            return (Criteria) this;
        }

        public Criteria andDocshowGreaterThanOrEqualTo(Short value) {
            addCriterion("docshow >=", value, "docshow");
            return (Criteria) this;
        }

        public Criteria andDocshowLessThan(Short value) {
            addCriterion("docshow <", value, "docshow");
            return (Criteria) this;
        }

        public Criteria andDocshowLessThanOrEqualTo(Short value) {
            addCriterion("docshow <=", value, "docshow");
            return (Criteria) this;
        }

        public Criteria andDocshowIn(List<Short> values) {
            addCriterion("docshow in", values, "docshow");
            return (Criteria) this;
        }

        public Criteria andDocshowNotIn(List<Short> values) {
            addCriterion("docshow not in", values, "docshow");
            return (Criteria) this;
        }

        public Criteria andDocshowBetween(Short value1, Short value2) {
            addCriterion("docshow between", value1, value2, "docshow");
            return (Criteria) this;
        }

        public Criteria andDocshowNotBetween(Short value1, Short value2) {
            addCriterion("docshow not between", value1, value2, "docshow");
            return (Criteria) this;
        }

        public Criteria andCreattimeIsNull() {
            addCriterion("creattime is null");
            return (Criteria) this;
        }

        public Criteria andCreattimeIsNotNull() {
            addCriterion("creattime is not null");
            return (Criteria) this;
        }

        public Criteria andCreattimeEqualTo(Date value) {
            addCriterion("creattime =", value, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeNotEqualTo(Date value) {
            addCriterion("creattime <>", value, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeGreaterThan(Date value) {
            addCriterion("creattime >", value, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeGreaterThanOrEqualTo(Date value) {
            addCriterion("creattime >=", value, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeLessThan(Date value) {
            addCriterion("creattime <", value, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeLessThanOrEqualTo(Date value) {
            addCriterion("creattime <=", value, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeIn(List<Date> values) {
            addCriterion("creattime in", values, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeNotIn(List<Date> values) {
            addCriterion("creattime not in", values, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeBetween(Date value1, Date value2) {
            addCriterion("creattime between", value1, value2, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeNotBetween(Date value1, Date value2) {
            addCriterion("creattime not between", value1, value2, "creattime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNull() {
            addCriterion("updatetime is null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNotNull() {
            addCriterion("updatetime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeEqualTo(Date value) {
            addCriterion("updatetime =", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotEqualTo(Date value) {
            addCriterion("updatetime <>", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThan(Date value) {
            addCriterion("updatetime >", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updatetime >=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThan(Date value) {
            addCriterion("updatetime <", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThanOrEqualTo(Date value) {
            addCriterion("updatetime <=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIn(List<Date> values) {
            addCriterion("updatetime in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotIn(List<Date> values) {
            addCriterion("updatetime not in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeBetween(Date value1, Date value2) {
            addCriterion("updatetime between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotBetween(Date value1, Date value2) {
            addCriterion("updatetime not between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andDocstatusIsNull() {
            addCriterion("docstatus is null");
            return (Criteria) this;
        }

        public Criteria andDocstatusIsNotNull() {
            addCriterion("docstatus is not null");
            return (Criteria) this;
        }

        public Criteria andDocstatusEqualTo(Short value) {
            addCriterion("docstatus =", value, "docstatus");
            return (Criteria) this;
        }

        public Criteria andDocstatusNotEqualTo(Short value) {
            addCriterion("docstatus <>", value, "docstatus");
            return (Criteria) this;
        }

        public Criteria andDocstatusGreaterThan(Short value) {
            addCriterion("docstatus >", value, "docstatus");
            return (Criteria) this;
        }

        public Criteria andDocstatusGreaterThanOrEqualTo(Short value) {
            addCriterion("docstatus >=", value, "docstatus");
            return (Criteria) this;
        }

        public Criteria andDocstatusLessThan(Short value) {
            addCriterion("docstatus <", value, "docstatus");
            return (Criteria) this;
        }

        public Criteria andDocstatusLessThanOrEqualTo(Short value) {
            addCriterion("docstatus <=", value, "docstatus");
            return (Criteria) this;
        }

        public Criteria andDocstatusIn(List<Short> values) {
            addCriterion("docstatus in", values, "docstatus");
            return (Criteria) this;
        }

        public Criteria andDocstatusNotIn(List<Short> values) {
            addCriterion("docstatus not in", values, "docstatus");
            return (Criteria) this;
        }

        public Criteria andDocstatusBetween(Short value1, Short value2) {
            addCriterion("docstatus between", value1, value2, "docstatus");
            return (Criteria) this;
        }

        public Criteria andDocstatusNotBetween(Short value1, Short value2) {
            addCriterion("docstatus not between", value1, value2, "docstatus");
            return (Criteria) this;
        }

        public Criteria andSketchIsNull() {
            addCriterion("sketch is null");
            return (Criteria) this;
        }

        public Criteria andSketchIsNotNull() {
            addCriterion("sketch is not null");
            return (Criteria) this;
        }

        public Criteria andSketchEqualTo(String value) {
            addCriterion("sketch =", value, "sketch");
            return (Criteria) this;
        }

        public Criteria andSketchNotEqualTo(String value) {
            addCriterion("sketch <>", value, "sketch");
            return (Criteria) this;
        }

        public Criteria andSketchGreaterThan(String value) {
            addCriterion("sketch >", value, "sketch");
            return (Criteria) this;
        }

        public Criteria andSketchGreaterThanOrEqualTo(String value) {
            addCriterion("sketch >=", value, "sketch");
            return (Criteria) this;
        }

        public Criteria andSketchLessThan(String value) {
            addCriterion("sketch <", value, "sketch");
            return (Criteria) this;
        }

        public Criteria andSketchLessThanOrEqualTo(String value) {
            addCriterion("sketch <=", value, "sketch");
            return (Criteria) this;
        }

        public Criteria andSketchLike(String value) {
            addCriterion("sketch like", value, "sketch");
            return (Criteria) this;
        }

        public Criteria andSketchNotLike(String value) {
            addCriterion("sketch not like", value, "sketch");
            return (Criteria) this;
        }

        public Criteria andSketchIn(List<String> values) {
            addCriterion("sketch in", values, "sketch");
            return (Criteria) this;
        }

        public Criteria andSketchNotIn(List<String> values) {
            addCriterion("sketch not in", values, "sketch");
            return (Criteria) this;
        }

        public Criteria andSketchBetween(String value1, String value2) {
            addCriterion("sketch between", value1, value2, "sketch");
            return (Criteria) this;
        }

        public Criteria andSketchNotBetween(String value1, String value2) {
            addCriterion("sketch not between", value1, value2, "sketch");
            return (Criteria) this;
        }

        public Criteria andPicIsNull() {
            addCriterion("pic is null");
            return (Criteria) this;
        }

        public Criteria andPicIsNotNull() {
            addCriterion("pic is not null");
            return (Criteria) this;
        }

        public Criteria andPicEqualTo(String value) {
            addCriterion("pic =", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotEqualTo(String value) {
            addCriterion("pic <>", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicGreaterThan(String value) {
            addCriterion("pic >", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicGreaterThanOrEqualTo(String value) {
            addCriterion("pic >=", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLessThan(String value) {
            addCriterion("pic <", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLessThanOrEqualTo(String value) {
            addCriterion("pic <=", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLike(String value) {
            addCriterion("pic like", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotLike(String value) {
            addCriterion("pic not like", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicIn(List<String> values) {
            addCriterion("pic in", values, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotIn(List<String> values) {
            addCriterion("pic not in", values, "pic");
            return (Criteria) this;
        }

        public Criteria andPicBetween(String value1, String value2) {
            addCriterion("pic between", value1, value2, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotBetween(String value1, String value2) {
            addCriterion("pic not between", value1, value2, "pic");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Short value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Short value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Short value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Short value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Short value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Short> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Short> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Short value1, Short value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Short value1, Short value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andIscarouselIsNull() {
            addCriterion("iscarousel is null");
            return (Criteria) this;
        }

        public Criteria andIscarouselIsNotNull() {
            addCriterion("iscarousel is not null");
            return (Criteria) this;
        }

        public Criteria andIscarouselEqualTo(Short value) {
            addCriterion("iscarousel =", value, "iscarousel");
            return (Criteria) this;
        }

        public Criteria andIscarouselNotEqualTo(Short value) {
            addCriterion("iscarousel <>", value, "iscarousel");
            return (Criteria) this;
        }

        public Criteria andIscarouselGreaterThan(Short value) {
            addCriterion("iscarousel >", value, "iscarousel");
            return (Criteria) this;
        }

        public Criteria andIscarouselGreaterThanOrEqualTo(Short value) {
            addCriterion("iscarousel >=", value, "iscarousel");
            return (Criteria) this;
        }

        public Criteria andIscarouselLessThan(Short value) {
            addCriterion("iscarousel <", value, "iscarousel");
            return (Criteria) this;
        }

        public Criteria andIscarouselLessThanOrEqualTo(Short value) {
            addCriterion("iscarousel <=", value, "iscarousel");
            return (Criteria) this;
        }

        public Criteria andIscarouselIn(List<Short> values) {
            addCriterion("iscarousel in", values, "iscarousel");
            return (Criteria) this;
        }

        public Criteria andIscarouselNotIn(List<Short> values) {
            addCriterion("iscarousel not in", values, "iscarousel");
            return (Criteria) this;
        }

        public Criteria andIscarouselBetween(Short value1, Short value2) {
            addCriterion("iscarousel between", value1, value2, "iscarousel");
            return (Criteria) this;
        }

        public Criteria andIscarouselNotBetween(Short value1, Short value2) {
            addCriterion("iscarousel not between", value1, value2, "iscarousel");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}