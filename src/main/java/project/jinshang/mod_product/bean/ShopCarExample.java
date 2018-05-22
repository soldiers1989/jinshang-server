package project.jinshang.mod_product.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShopCarExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ShopCarExample() {
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

        public Criteria andMemberidIsNull() {
            addCriterion("memberid is null");
            return (Criteria) this;
        }

        public Criteria andMemberidIsNotNull() {
            addCriterion("memberid is not null");
            return (Criteria) this;
        }

        public Criteria andMemberidEqualTo(Long value) {
            addCriterion("memberid =", value, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidNotEqualTo(Long value) {
            addCriterion("memberid <>", value, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidGreaterThan(Long value) {
            addCriterion("memberid >", value, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidGreaterThanOrEqualTo(Long value) {
            addCriterion("memberid >=", value, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidLessThan(Long value) {
            addCriterion("memberid <", value, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidLessThanOrEqualTo(Long value) {
            addCriterion("memberid <=", value, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidIn(List<Long> values) {
            addCriterion("memberid in", values, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidNotIn(List<Long> values) {
            addCriterion("memberid not in", values, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidBetween(Long value1, Long value2) {
            addCriterion("memberid between", value1, value2, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidNotBetween(Long value1, Long value2) {
            addCriterion("memberid not between", value1, value2, "memberid");
            return (Criteria) this;
        }

        public Criteria andSaleidIsNull() {
            addCriterion("saleid is null");
            return (Criteria) this;
        }

        public Criteria andSaleidIsNotNull() {
            addCriterion("saleid is not null");
            return (Criteria) this;
        }

        public Criteria andSaleidEqualTo(Long value) {
            addCriterion("saleid =", value, "saleid");
            return (Criteria) this;
        }

        public Criteria andSaleidNotEqualTo(Long value) {
            addCriterion("saleid <>", value, "saleid");
            return (Criteria) this;
        }

        public Criteria andSaleidGreaterThan(Long value) {
            addCriterion("saleid >", value, "saleid");
            return (Criteria) this;
        }

        public Criteria andSaleidGreaterThanOrEqualTo(Long value) {
            addCriterion("saleid >=", value, "saleid");
            return (Criteria) this;
        }

        public Criteria andSaleidLessThan(Long value) {
            addCriterion("saleid <", value, "saleid");
            return (Criteria) this;
        }

        public Criteria andSaleidLessThanOrEqualTo(Long value) {
            addCriterion("saleid <=", value, "saleid");
            return (Criteria) this;
        }

        public Criteria andSaleidIn(List<Long> values) {
            addCriterion("saleid in", values, "saleid");
            return (Criteria) this;
        }

        public Criteria andSaleidNotIn(List<Long> values) {
            addCriterion("saleid not in", values, "saleid");
            return (Criteria) this;
        }

        public Criteria andSaleidBetween(Long value1, Long value2) {
            addCriterion("saleid between", value1, value2, "saleid");
            return (Criteria) this;
        }

        public Criteria andSaleidNotBetween(Long value1, Long value2) {
            addCriterion("saleid not between", value1, value2, "saleid");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createtime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createtime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createtime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createtime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createtime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createtime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createtime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createtime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createtime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createtime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createtime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andPdidIsNull() {
            addCriterion("pdid is null");
            return (Criteria) this;
        }

        public Criteria andPdidIsNotNull() {
            addCriterion("pdid is not null");
            return (Criteria) this;
        }

        public Criteria andPdidEqualTo(Long value) {
            addCriterion("pdid =", value, "pdid");
            return (Criteria) this;
        }

        public Criteria andPdidNotEqualTo(Long value) {
            addCriterion("pdid <>", value, "pdid");
            return (Criteria) this;
        }

        public Criteria andPdidGreaterThan(Long value) {
            addCriterion("pdid >", value, "pdid");
            return (Criteria) this;
        }

        public Criteria andPdidGreaterThanOrEqualTo(Long value) {
            addCriterion("pdid >=", value, "pdid");
            return (Criteria) this;
        }

        public Criteria andPdidLessThan(Long value) {
            addCriterion("pdid <", value, "pdid");
            return (Criteria) this;
        }

        public Criteria andPdidLessThanOrEqualTo(Long value) {
            addCriterion("pdid <=", value, "pdid");
            return (Criteria) this;
        }

        public Criteria andPdidIn(List<Long> values) {
            addCriterion("pdid in", values, "pdid");
            return (Criteria) this;
        }

        public Criteria andPdidNotIn(List<Long> values) {
            addCriterion("pdid not in", values, "pdid");
            return (Criteria) this;
        }

        public Criteria andPdidBetween(Long value1, Long value2) {
            addCriterion("pdid between", value1, value2, "pdid");
            return (Criteria) this;
        }

        public Criteria andPdidNotBetween(Long value1, Long value2) {
            addCriterion("pdid not between", value1, value2, "pdid");
            return (Criteria) this;
        }

        public Criteria andPdnoIsNull() {
            addCriterion("pdno is null");
            return (Criteria) this;
        }

        public Criteria andPdnoIsNotNull() {
            addCriterion("pdno is not null");
            return (Criteria) this;
        }

        public Criteria andPdnoEqualTo(String value) {
            addCriterion("pdno =", value, "pdno");
            return (Criteria) this;
        }

        public Criteria andPdnoNotEqualTo(String value) {
            addCriterion("pdno <>", value, "pdno");
            return (Criteria) this;
        }

        public Criteria andPdnoGreaterThan(String value) {
            addCriterion("pdno >", value, "pdno");
            return (Criteria) this;
        }

        public Criteria andPdnoGreaterThanOrEqualTo(String value) {
            addCriterion("pdno >=", value, "pdno");
            return (Criteria) this;
        }

        public Criteria andPdnoLessThan(String value) {
            addCriterion("pdno <", value, "pdno");
            return (Criteria) this;
        }

        public Criteria andPdnoLessThanOrEqualTo(String value) {
            addCriterion("pdno <=", value, "pdno");
            return (Criteria) this;
        }

        public Criteria andPdnoLike(String value) {
            addCriterion("pdno like", value, "pdno");
            return (Criteria) this;
        }

        public Criteria andPdnoNotLike(String value) {
            addCriterion("pdno not like", value, "pdno");
            return (Criteria) this;
        }

        public Criteria andPdnoIn(List<String> values) {
            addCriterion("pdno in", values, "pdno");
            return (Criteria) this;
        }

        public Criteria andPdnoNotIn(List<String> values) {
            addCriterion("pdno not in", values, "pdno");
            return (Criteria) this;
        }

        public Criteria andPdnoBetween(String value1, String value2) {
            addCriterion("pdno between", value1, value2, "pdno");
            return (Criteria) this;
        }

        public Criteria andPdnoNotBetween(String value1, String value2) {
            addCriterion("pdno not between", value1, value2, "pdno");
            return (Criteria) this;
        }

        public Criteria andPdnumberIsNull() {
            addCriterion("pdnumber is null");
            return (Criteria) this;
        }

        public Criteria andPdnumberIsNotNull() {
            addCriterion("pdnumber is not null");
            return (Criteria) this;
        }

        public Criteria andPdnumberEqualTo(BigDecimal value) {
            addCriterion("pdnumber =", value, "pdnumber");
            return (Criteria) this;
        }

        public Criteria andPdnumberNotEqualTo(BigDecimal value) {
            addCriterion("pdnumber <>", value, "pdnumber");
            return (Criteria) this;
        }

        public Criteria andPdnumberGreaterThan(BigDecimal value) {
            addCriterion("pdnumber >", value, "pdnumber");
            return (Criteria) this;
        }

        public Criteria andPdnumberGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("pdnumber >=", value, "pdnumber");
            return (Criteria) this;
        }

        public Criteria andPdnumberLessThan(BigDecimal value) {
            addCriterion("pdnumber <", value, "pdnumber");
            return (Criteria) this;
        }

        public Criteria andPdnumberLessThanOrEqualTo(BigDecimal value) {
            addCriterion("pdnumber <=", value, "pdnumber");
            return (Criteria) this;
        }

        public Criteria andPdnumberIn(List<BigDecimal> values) {
            addCriterion("pdnumber in", values, "pdnumber");
            return (Criteria) this;
        }

        public Criteria andPdnumberNotIn(List<BigDecimal> values) {
            addCriterion("pdnumber not in", values, "pdnumber");
            return (Criteria) this;
        }

        public Criteria andPdnumberBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pdnumber between", value1, value2, "pdnumber");
            return (Criteria) this;
        }

        public Criteria andPdnumberNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pdnumber not between", value1, value2, "pdnumber");
            return (Criteria) this;
        }

        public Criteria andStoreidIsNull() {
            addCriterion("storeid is null");
            return (Criteria) this;
        }

        public Criteria andStoreidIsNotNull() {
            addCriterion("storeid is not null");
            return (Criteria) this;
        }

        public Criteria andStoreidEqualTo(Long value) {
            addCriterion("storeid =", value, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidNotEqualTo(Long value) {
            addCriterion("storeid <>", value, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidGreaterThan(Long value) {
            addCriterion("storeid >", value, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidGreaterThanOrEqualTo(Long value) {
            addCriterion("storeid >=", value, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidLessThan(Long value) {
            addCriterion("storeid <", value, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidLessThanOrEqualTo(Long value) {
            addCriterion("storeid <=", value, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidIn(List<Long> values) {
            addCriterion("storeid in", values, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidNotIn(List<Long> values) {
            addCriterion("storeid not in", values, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidBetween(Long value1, Long value2) {
            addCriterion("storeid between", value1, value2, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidNotBetween(Long value1, Long value2) {
            addCriterion("storeid not between", value1, value2, "storeid");
            return (Criteria) this;
        }

        public Criteria andDelivertimeIsNull() {
            addCriterion("delivertime is null");
            return (Criteria) this;
        }

        public Criteria andDelivertimeIsNotNull() {
            addCriterion("delivertime is not null");
            return (Criteria) this;
        }

        public Criteria andDelivertimeEqualTo(String value) {
            addCriterion("delivertime =", value, "delivertime");
            return (Criteria) this;
        }

        public Criteria andDelivertimeNotEqualTo(String value) {
            addCriterion("delivertime <>", value, "delivertime");
            return (Criteria) this;
        }

        public Criteria andDelivertimeGreaterThan(String value) {
            addCriterion("delivertime >", value, "delivertime");
            return (Criteria) this;
        }

        public Criteria andDelivertimeGreaterThanOrEqualTo(String value) {
            addCriterion("delivertime >=", value, "delivertime");
            return (Criteria) this;
        }

        public Criteria andDelivertimeLessThan(String value) {
            addCriterion("delivertime <", value, "delivertime");
            return (Criteria) this;
        }

        public Criteria andDelivertimeLessThanOrEqualTo(String value) {
            addCriterion("delivertime <=", value, "delivertime");
            return (Criteria) this;
        }

        public Criteria andDelivertimeLike(String value) {
            addCriterion("delivertime like", value, "delivertime");
            return (Criteria) this;
        }

        public Criteria andDelivertimeNotLike(String value) {
            addCriterion("delivertime not like", value, "delivertime");
            return (Criteria) this;
        }

        public Criteria andDelivertimeIn(List<String> values) {
            addCriterion("delivertime in", values, "delivertime");
            return (Criteria) this;
        }

        public Criteria andDelivertimeNotIn(List<String> values) {
            addCriterion("delivertime not in", values, "delivertime");
            return (Criteria) this;
        }

        public Criteria andDelivertimeBetween(String value1, String value2) {
            addCriterion("delivertime between", value1, value2, "delivertime");
            return (Criteria) this;
        }

        public Criteria andDelivertimeNotBetween(String value1, String value2) {
            addCriterion("delivertime not between", value1, value2, "delivertime");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(BigDecimal value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(BigDecimal value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(BigDecimal value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(BigDecimal value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<BigDecimal> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<BigDecimal> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andUnitIsNull() {
            addCriterion("unit is null");
            return (Criteria) this;
        }

        public Criteria andUnitIsNotNull() {
            addCriterion("unit is not null");
            return (Criteria) this;
        }

        public Criteria andUnitEqualTo(String value) {
            addCriterion("unit =", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotEqualTo(String value) {
            addCriterion("unit <>", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThan(String value) {
            addCriterion("unit >", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThanOrEqualTo(String value) {
            addCriterion("unit >=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThan(String value) {
            addCriterion("unit <", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThanOrEqualTo(String value) {
            addCriterion("unit <=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLike(String value) {
            addCriterion("unit like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotLike(String value) {
            addCriterion("unit not like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitIn(List<String> values) {
            addCriterion("unit in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotIn(List<String> values) {
            addCriterion("unit not in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitBetween(String value1, String value2) {
            addCriterion("unit between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotBetween(String value1, String value2) {
            addCriterion("unit not between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andFrightmodeIsNull() {
            addCriterion("frightmode is null");
            return (Criteria) this;
        }

        public Criteria andFrightmodeIsNotNull() {
            addCriterion("frightmode is not null");
            return (Criteria) this;
        }

        public Criteria andFrightmodeEqualTo(Long value) {
            addCriterion("frightmode =", value, "frightmode");
            return (Criteria) this;
        }

        public Criteria andFrightmodeNotEqualTo(Long value) {
            addCriterion("frightmode <>", value, "frightmode");
            return (Criteria) this;
        }

        public Criteria andFrightmodeGreaterThan(Long value) {
            addCriterion("frightmode >", value, "frightmode");
            return (Criteria) this;
        }

        public Criteria andFrightmodeGreaterThanOrEqualTo(Long value) {
            addCriterion("frightmode >=", value, "frightmode");
            return (Criteria) this;
        }

        public Criteria andFrightmodeLessThan(Long value) {
            addCriterion("frightmode <", value, "frightmode");
            return (Criteria) this;
        }

        public Criteria andFrightmodeLessThanOrEqualTo(Long value) {
            addCriterion("frightmode <=", value, "frightmode");
            return (Criteria) this;
        }

        public Criteria andFrightmodeIn(List<Long> values) {
            addCriterion("frightmode in", values, "frightmode");
            return (Criteria) this;
        }

        public Criteria andFrightmodeNotIn(List<Long> values) {
            addCriterion("frightmode not in", values, "frightmode");
            return (Criteria) this;
        }

        public Criteria andFrightmodeBetween(Long value1, Long value2) {
            addCriterion("frightmode between", value1, value2, "frightmode");
            return (Criteria) this;
        }

        public Criteria andFrightmodeNotBetween(Long value1, Long value2) {
            addCriterion("frightmode not between", value1, value2, "frightmode");
            return (Criteria) this;
        }

        public Criteria andIsmailornotIsNull() {
            addCriterion("ismailornot is null");
            return (Criteria) this;
        }

        public Criteria andIsmailornotIsNotNull() {
            addCriterion("ismailornot is not null");
            return (Criteria) this;
        }

        public Criteria andIsmailornotEqualTo(Short value) {
            addCriterion("ismailornot =", value, "ismailornot");
            return (Criteria) this;
        }

        public Criteria andIsmailornotNotEqualTo(Short value) {
            addCriterion("ismailornot <>", value, "ismailornot");
            return (Criteria) this;
        }

        public Criteria andIsmailornotGreaterThan(Short value) {
            addCriterion("ismailornot >", value, "ismailornot");
            return (Criteria) this;
        }

        public Criteria andIsmailornotGreaterThanOrEqualTo(Short value) {
            addCriterion("ismailornot >=", value, "ismailornot");
            return (Criteria) this;
        }

        public Criteria andIsmailornotLessThan(Short value) {
            addCriterion("ismailornot <", value, "ismailornot");
            return (Criteria) this;
        }

        public Criteria andIsmailornotLessThanOrEqualTo(Short value) {
            addCriterion("ismailornot <=", value, "ismailornot");
            return (Criteria) this;
        }

        public Criteria andIsmailornotIn(List<Short> values) {
            addCriterion("ismailornot in", values, "ismailornot");
            return (Criteria) this;
        }

        public Criteria andIsmailornotNotIn(List<Short> values) {
            addCriterion("ismailornot not in", values, "ismailornot");
            return (Criteria) this;
        }

        public Criteria andIsmailornotBetween(Short value1, Short value2) {
            addCriterion("ismailornot between", value1, value2, "ismailornot");
            return (Criteria) this;
        }

        public Criteria andIsmailornotNotBetween(Short value1, Short value2) {
            addCriterion("ismailornot not between", value1, value2, "ismailornot");
            return (Criteria) this;
        }

        public Criteria andStorenameIsNull() {
            addCriterion("storename is null");
            return (Criteria) this;
        }

        public Criteria andStorenameIsNotNull() {
            addCriterion("storename is not null");
            return (Criteria) this;
        }

        public Criteria andStorenameEqualTo(String value) {
            addCriterion("storename =", value, "storename");
            return (Criteria) this;
        }

        public Criteria andStorenameNotEqualTo(String value) {
            addCriterion("storename <>", value, "storename");
            return (Criteria) this;
        }

        public Criteria andStorenameGreaterThan(String value) {
            addCriterion("storename >", value, "storename");
            return (Criteria) this;
        }

        public Criteria andStorenameGreaterThanOrEqualTo(String value) {
            addCriterion("storename >=", value, "storename");
            return (Criteria) this;
        }

        public Criteria andStorenameLessThan(String value) {
            addCriterion("storename <", value, "storename");
            return (Criteria) this;
        }

        public Criteria andStorenameLessThanOrEqualTo(String value) {
            addCriterion("storename <=", value, "storename");
            return (Criteria) this;
        }

        public Criteria andStorenameLike(String value) {
            addCriterion("storename like", value, "storename");
            return (Criteria) this;
        }

        public Criteria andStorenameNotLike(String value) {
            addCriterion("storename not like", value, "storename");
            return (Criteria) this;
        }

        public Criteria andStorenameIn(List<String> values) {
            addCriterion("storename in", values, "storename");
            return (Criteria) this;
        }

        public Criteria andStorenameNotIn(List<String> values) {
            addCriterion("storename not in", values, "storename");
            return (Criteria) this;
        }

        public Criteria andStorenameBetween(String value1, String value2) {
            addCriterion("storename between", value1, value2, "storename");
            return (Criteria) this;
        }

        public Criteria andStorenameNotBetween(String value1, String value2) {
            addCriterion("storename not between", value1, value2, "storename");
            return (Criteria) this;
        }

        public Criteria andPartpayIsNull() {
            addCriterion("partpay is null");
            return (Criteria) this;
        }

        public Criteria andPartpayIsNotNull() {
            addCriterion("partpay is not null");
            return (Criteria) this;
        }

        public Criteria andPartpayEqualTo(BigDecimal value) {
            addCriterion("partpay =", value, "partpay");
            return (Criteria) this;
        }

        public Criteria andPartpayNotEqualTo(BigDecimal value) {
            addCriterion("partpay <>", value, "partpay");
            return (Criteria) this;
        }

        public Criteria andPartpayGreaterThan(BigDecimal value) {
            addCriterion("partpay >", value, "partpay");
            return (Criteria) this;
        }

        public Criteria andPartpayGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("partpay >=", value, "partpay");
            return (Criteria) this;
        }

        public Criteria andPartpayLessThan(BigDecimal value) {
            addCriterion("partpay <", value, "partpay");
            return (Criteria) this;
        }

        public Criteria andPartpayLessThanOrEqualTo(BigDecimal value) {
            addCriterion("partpay <=", value, "partpay");
            return (Criteria) this;
        }

        public Criteria andPartpayIn(List<BigDecimal> values) {
            addCriterion("partpay in", values, "partpay");
            return (Criteria) this;
        }

        public Criteria andPartpayNotIn(List<BigDecimal> values) {
            addCriterion("partpay not in", values, "partpay");
            return (Criteria) this;
        }

        public Criteria andPartpayBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("partpay between", value1, value2, "partpay");
            return (Criteria) this;
        }

        public Criteria andPartpayNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("partpay not between", value1, value2, "partpay");
            return (Criteria) this;
        }

        public Criteria andYupayIsNull() {
            addCriterion("yupay is null");
            return (Criteria) this;
        }

        public Criteria andYupayIsNotNull() {
            addCriterion("yupay is not null");
            return (Criteria) this;
        }

        public Criteria andYupayEqualTo(BigDecimal value) {
            addCriterion("yupay =", value, "yupay");
            return (Criteria) this;
        }

        public Criteria andYupayNotEqualTo(BigDecimal value) {
            addCriterion("yupay <>", value, "yupay");
            return (Criteria) this;
        }

        public Criteria andYupayGreaterThan(BigDecimal value) {
            addCriterion("yupay >", value, "yupay");
            return (Criteria) this;
        }

        public Criteria andYupayGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("yupay >=", value, "yupay");
            return (Criteria) this;
        }

        public Criteria andYupayLessThan(BigDecimal value) {
            addCriterion("yupay <", value, "yupay");
            return (Criteria) this;
        }

        public Criteria andYupayLessThanOrEqualTo(BigDecimal value) {
            addCriterion("yupay <=", value, "yupay");
            return (Criteria) this;
        }

        public Criteria andYupayIn(List<BigDecimal> values) {
            addCriterion("yupay in", values, "yupay");
            return (Criteria) this;
        }

        public Criteria andYupayNotIn(List<BigDecimal> values) {
            addCriterion("yupay not in", values, "yupay");
            return (Criteria) this;
        }

        public Criteria andYupayBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("yupay between", value1, value2, "yupay");
            return (Criteria) this;
        }

        public Criteria andYupayNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("yupay not between", value1, value2, "yupay");
            return (Criteria) this;
        }

        public Criteria andAllpayIsNull() {
            addCriterion("allpay is null");
            return (Criteria) this;
        }

        public Criteria andAllpayIsNotNull() {
            addCriterion("allpay is not null");
            return (Criteria) this;
        }

        public Criteria andAllpayEqualTo(BigDecimal value) {
            addCriterion("allpay =", value, "allpay");
            return (Criteria) this;
        }

        public Criteria andAllpayNotEqualTo(BigDecimal value) {
            addCriterion("allpay <>", value, "allpay");
            return (Criteria) this;
        }

        public Criteria andAllpayGreaterThan(BigDecimal value) {
            addCriterion("allpay >", value, "allpay");
            return (Criteria) this;
        }

        public Criteria andAllpayGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("allpay >=", value, "allpay");
            return (Criteria) this;
        }

        public Criteria andAllpayLessThan(BigDecimal value) {
            addCriterion("allpay <", value, "allpay");
            return (Criteria) this;
        }

        public Criteria andAllpayLessThanOrEqualTo(BigDecimal value) {
            addCriterion("allpay <=", value, "allpay");
            return (Criteria) this;
        }

        public Criteria andAllpayIn(List<BigDecimal> values) {
            addCriterion("allpay in", values, "allpay");
            return (Criteria) this;
        }

        public Criteria andAllpayNotIn(List<BigDecimal> values) {
            addCriterion("allpay not in", values, "allpay");
            return (Criteria) this;
        }

        public Criteria andAllpayBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("allpay between", value1, value2, "allpay");
            return (Criteria) this;
        }

        public Criteria andAllpayNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("allpay not between", value1, value2, "allpay");
            return (Criteria) this;
        }

        public Criteria andProtypeIsNull() {
            addCriterion("protype is null");
            return (Criteria) this;
        }

        public Criteria andProtypeIsNotNull() {
            addCriterion("protype is not null");
            return (Criteria) this;
        }

        public Criteria andProtypeEqualTo(Short value) {
            addCriterion("protype =", value, "protype");
            return (Criteria) this;
        }

        public Criteria andProtypeNotEqualTo(Short value) {
            addCriterion("protype <>", value, "protype");
            return (Criteria) this;
        }

        public Criteria andProtypeGreaterThan(Short value) {
            addCriterion("protype >", value, "protype");
            return (Criteria) this;
        }

        public Criteria andProtypeGreaterThanOrEqualTo(Short value) {
            addCriterion("protype >=", value, "protype");
            return (Criteria) this;
        }

        public Criteria andProtypeLessThan(Short value) {
            addCriterion("protype <", value, "protype");
            return (Criteria) this;
        }

        public Criteria andProtypeLessThanOrEqualTo(Short value) {
            addCriterion("protype <=", value, "protype");
            return (Criteria) this;
        }

        public Criteria andProtypeIn(List<Short> values) {
            addCriterion("protype in", values, "protype");
            return (Criteria) this;
        }

        public Criteria andProtypeNotIn(List<Short> values) {
            addCriterion("protype not in", values, "protype");
            return (Criteria) this;
        }

        public Criteria andProtypeBetween(Short value1, Short value2) {
            addCriterion("protype between", value1, value2, "protype");
            return (Criteria) this;
        }

        public Criteria andProtypeNotBetween(Short value1, Short value2) {
            addCriterion("protype not between", value1, value2, "protype");
            return (Criteria) this;
        }

        public Criteria andProducttypeIsNull() {
            addCriterion("producttype is null");
            return (Criteria) this;
        }

        public Criteria andProducttypeIsNotNull() {
            addCriterion("producttype is not null");
            return (Criteria) this;
        }

        public Criteria andProducttypeEqualTo(Short value) {
            addCriterion("producttype =", value, "producttype");
            return (Criteria) this;
        }

        public Criteria andProducttypeNotEqualTo(Short value) {
            addCriterion("producttype <>", value, "producttype");
            return (Criteria) this;
        }

        public Criteria andProducttypeGreaterThan(Short value) {
            addCriterion("producttype >", value, "producttype");
            return (Criteria) this;
        }

        public Criteria andProducttypeGreaterThanOrEqualTo(Short value) {
            addCriterion("producttype >=", value, "producttype");
            return (Criteria) this;
        }

        public Criteria andProducttypeLessThan(Short value) {
            addCriterion("producttype <", value, "producttype");
            return (Criteria) this;
        }

        public Criteria andProducttypeLessThanOrEqualTo(Short value) {
            addCriterion("producttype <=", value, "producttype");
            return (Criteria) this;
        }

        public Criteria andProducttypeIn(List<Short> values) {
            addCriterion("producttype in", values, "producttype");
            return (Criteria) this;
        }

        public Criteria andProducttypeNotIn(List<Short> values) {
            addCriterion("producttype not in", values, "producttype");
            return (Criteria) this;
        }

        public Criteria andProducttypeBetween(Short value1, Short value2) {
            addCriterion("producttype between", value1, value2, "producttype");
            return (Criteria) this;
        }

        public Criteria andProducttypeNotBetween(Short value1, Short value2) {
            addCriterion("producttype not between", value1, value2, "producttype");
            return (Criteria) this;
        }

        public Criteria andAttrjsonIsNull() {
            addCriterion("attrjson is null");
            return (Criteria) this;
        }

        public Criteria andAttrjsonIsNotNull() {
            addCriterion("attrjson is not null");
            return (Criteria) this;
        }

        public Criteria andAttrjsonEqualTo(String value) {
            addCriterion("attrjson =", value, "attrjson");
            return (Criteria) this;
        }

        public Criteria andAttrjsonNotEqualTo(String value) {
            addCriterion("attrjson <>", value, "attrjson");
            return (Criteria) this;
        }

        public Criteria andAttrjsonGreaterThan(String value) {
            addCriterion("attrjson >", value, "attrjson");
            return (Criteria) this;
        }

        public Criteria andAttrjsonGreaterThanOrEqualTo(String value) {
            addCriterion("attrjson >=", value, "attrjson");
            return (Criteria) this;
        }

        public Criteria andAttrjsonLessThan(String value) {
            addCriterion("attrjson <", value, "attrjson");
            return (Criteria) this;
        }

        public Criteria andAttrjsonLessThanOrEqualTo(String value) {
            addCriterion("attrjson <=", value, "attrjson");
            return (Criteria) this;
        }

        public Criteria andAttrjsonLike(String value) {
            addCriterion("attrjson like", value, "attrjson");
            return (Criteria) this;
        }

        public Criteria andAttrjsonNotLike(String value) {
            addCriterion("attrjson not like", value, "attrjson");
            return (Criteria) this;
        }

        public Criteria andAttrjsonIn(List<String> values) {
            addCriterion("attrjson in", values, "attrjson");
            return (Criteria) this;
        }

        public Criteria andAttrjsonNotIn(List<String> values) {
            addCriterion("attrjson not in", values, "attrjson");
            return (Criteria) this;
        }

        public Criteria andAttrjsonBetween(String value1, String value2) {
            addCriterion("attrjson between", value1, value2, "attrjson");
            return (Criteria) this;
        }

        public Criteria andAttrjsonNotBetween(String value1, String value2) {
            addCriterion("attrjson not between", value1, value2, "attrjson");
            return (Criteria) this;
        }

        public Criteria andViewnumIsNull() {
            addCriterion("viewnum is null");
            return (Criteria) this;
        }

        public Criteria andViewnumIsNotNull() {
            addCriterion("viewnum is not null");
            return (Criteria) this;
        }

        public Criteria andViewnumEqualTo(BigDecimal value) {
            addCriterion("viewnum =", value, "viewnum");
            return (Criteria) this;
        }

        public Criteria andViewnumNotEqualTo(BigDecimal value) {
            addCriterion("viewnum <>", value, "viewnum");
            return (Criteria) this;
        }

        public Criteria andViewnumGreaterThan(BigDecimal value) {
            addCriterion("viewnum >", value, "viewnum");
            return (Criteria) this;
        }

        public Criteria andViewnumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("viewnum >=", value, "viewnum");
            return (Criteria) this;
        }

        public Criteria andViewnumLessThan(BigDecimal value) {
            addCriterion("viewnum <", value, "viewnum");
            return (Criteria) this;
        }

        public Criteria andViewnumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("viewnum <=", value, "viewnum");
            return (Criteria) this;
        }

        public Criteria andViewnumIn(List<BigDecimal> values) {
            addCriterion("viewnum in", values, "viewnum");
            return (Criteria) this;
        }

        public Criteria andViewnumNotIn(List<BigDecimal> values) {
            addCriterion("viewnum not in", values, "viewnum");
            return (Criteria) this;
        }

        public Criteria andViewnumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("viewnum between", value1, value2, "viewnum");
            return (Criteria) this;
        }

        public Criteria andViewnumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("viewnum not between", value1, value2, "viewnum");
            return (Criteria) this;
        }

        public Criteria andViewunitIsNull() {
            addCriterion("viewunit is null");
            return (Criteria) this;
        }

        public Criteria andViewunitIsNotNull() {
            addCriterion("viewunit is not null");
            return (Criteria) this;
        }

        public Criteria andViewunitEqualTo(String value) {
            addCriterion("viewunit =", value, "viewunit");
            return (Criteria) this;
        }

        public Criteria andViewunitNotEqualTo(String value) {
            addCriterion("viewunit <>", value, "viewunit");
            return (Criteria) this;
        }

        public Criteria andViewunitGreaterThan(String value) {
            addCriterion("viewunit >", value, "viewunit");
            return (Criteria) this;
        }

        public Criteria andViewunitGreaterThanOrEqualTo(String value) {
            addCriterion("viewunit >=", value, "viewunit");
            return (Criteria) this;
        }

        public Criteria andViewunitLessThan(String value) {
            addCriterion("viewunit <", value, "viewunit");
            return (Criteria) this;
        }

        public Criteria andViewunitLessThanOrEqualTo(String value) {
            addCriterion("viewunit <=", value, "viewunit");
            return (Criteria) this;
        }

        public Criteria andViewunitLike(String value) {
            addCriterion("viewunit like", value, "viewunit");
            return (Criteria) this;
        }

        public Criteria andViewunitNotLike(String value) {
            addCriterion("viewunit not like", value, "viewunit");
            return (Criteria) this;
        }

        public Criteria andViewunitIn(List<String> values) {
            addCriterion("viewunit in", values, "viewunit");
            return (Criteria) this;
        }

        public Criteria andViewunitNotIn(List<String> values) {
            addCriterion("viewunit not in", values, "viewunit");
            return (Criteria) this;
        }

        public Criteria andViewunitBetween(String value1, String value2) {
            addCriterion("viewunit between", value1, value2, "viewunit");
            return (Criteria) this;
        }

        public Criteria andViewunitNotBetween(String value1, String value2) {
            addCriterion("viewunit not between", value1, value2, "viewunit");
            return (Criteria) this;
        }

        public Criteria andIsonlineIsNull() {
            addCriterion("isonline is null");
            return (Criteria) this;
        }

        public Criteria andIsonlineIsNotNull() {
            addCriterion("isonline is not null");
            return (Criteria) this;
        }

        public Criteria andIsonlineEqualTo(Short value) {
            addCriterion("isonline =", value, "isonline");
            return (Criteria) this;
        }

        public Criteria andIsonlineNotEqualTo(Short value) {
            addCriterion("isonline <>", value, "isonline");
            return (Criteria) this;
        }

        public Criteria andIsonlineGreaterThan(Short value) {
            addCriterion("isonline >", value, "isonline");
            return (Criteria) this;
        }

        public Criteria andIsonlineGreaterThanOrEqualTo(Short value) {
            addCriterion("isonline >=", value, "isonline");
            return (Criteria) this;
        }

        public Criteria andIsonlineLessThan(Short value) {
            addCriterion("isonline <", value, "isonline");
            return (Criteria) this;
        }

        public Criteria andIsonlineLessThanOrEqualTo(Short value) {
            addCriterion("isonline <=", value, "isonline");
            return (Criteria) this;
        }

        public Criteria andIsonlineIn(List<Short> values) {
            addCriterion("isonline in", values, "isonline");
            return (Criteria) this;
        }

        public Criteria andIsonlineNotIn(List<Short> values) {
            addCriterion("isonline not in", values, "isonline");
            return (Criteria) this;
        }

        public Criteria andIsonlineBetween(Short value1, Short value2) {
            addCriterion("isonline between", value1, value2, "isonline");
            return (Criteria) this;
        }

        public Criteria andIsonlineNotBetween(Short value1, Short value2) {
            addCriterion("isonline not between", value1, value2, "isonline");
            return (Criteria) this;
        }

        public Criteria andLimitidIsNull() {
            addCriterion("limitid is null");
            return (Criteria) this;
        }

        public Criteria andLimitidIsNotNull() {
            addCriterion("limitid is not null");
            return (Criteria) this;
        }

        public Criteria andLimitidEqualTo(Long value) {
            addCriterion("limitid =", value, "limitid");
            return (Criteria) this;
        }

        public Criteria andLimitidNotEqualTo(Long value) {
            addCriterion("limitid <>", value, "limitid");
            return (Criteria) this;
        }

        public Criteria andLimitidGreaterThan(Long value) {
            addCriterion("limitid >", value, "limitid");
            return (Criteria) this;
        }

        public Criteria andLimitidGreaterThanOrEqualTo(Long value) {
            addCriterion("limitid >=", value, "limitid");
            return (Criteria) this;
        }

        public Criteria andLimitidLessThan(Long value) {
            addCriterion("limitid <", value, "limitid");
            return (Criteria) this;
        }

        public Criteria andLimitidLessThanOrEqualTo(Long value) {
            addCriterion("limitid <=", value, "limitid");
            return (Criteria) this;
        }

        public Criteria andLimitidIn(List<Long> values) {
            addCriterion("limitid in", values, "limitid");
            return (Criteria) this;
        }

        public Criteria andLimitidNotIn(List<Long> values) {
            addCriterion("limitid not in", values, "limitid");
            return (Criteria) this;
        }

        public Criteria andLimitidBetween(Long value1, Long value2) {
            addCriterion("limitid between", value1, value2, "limitid");
            return (Criteria) this;
        }

        public Criteria andLimitidNotBetween(Long value1, Long value2) {
            addCriterion("limitid not between", value1, value2, "limitid");
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