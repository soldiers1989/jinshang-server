package project.jinshang.mod_product.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrdersExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrdersExample() {
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

        public Criteria andOrdernoIsNull() {
            addCriterion("orderno is null");
            return (Criteria) this;
        }

        public Criteria andOrdernoIsNotNull() {
            addCriterion("orderno is not null");
            return (Criteria) this;
        }

        public Criteria andOrdernoEqualTo(String value) {
            addCriterion("orderno =", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoNotEqualTo(String value) {
            addCriterion("orderno <>", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoGreaterThan(String value) {
            addCriterion("orderno >", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoGreaterThanOrEqualTo(String value) {
            addCriterion("orderno >=", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoLessThan(String value) {
            addCriterion("orderno <", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoLessThanOrEqualTo(String value) {
            addCriterion("orderno <=", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoLike(String value) {
            addCriterion("orderno like", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoNotLike(String value) {
            addCriterion("orderno not like", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoIn(List<String> values) {
            addCriterion("orderno in", values, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoNotIn(List<String> values) {
            addCriterion("orderno not in", values, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoBetween(String value1, String value2) {
            addCriterion("orderno between", value1, value2, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoNotBetween(String value1, String value2) {
            addCriterion("orderno not between", value1, value2, "orderno");
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

        public Criteria andProvinceIsNull() {
            addCriterion("province is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNotNull() {
            addCriterion("province is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceEqualTo(String value) {
            addCriterion("province =", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotEqualTo(String value) {
            addCriterion("province <>", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThan(String value) {
            addCriterion("province >", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("province >=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThan(String value) {
            addCriterion("province <", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThanOrEqualTo(String value) {
            addCriterion("province <=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLike(String value) {
            addCriterion("province like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotLike(String value) {
            addCriterion("province not like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceIn(List<String> values) {
            addCriterion("province in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotIn(List<String> values) {
            addCriterion("province not in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceBetween(String value1, String value2) {
            addCriterion("province between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotBetween(String value1, String value2) {
            addCriterion("province not between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("city like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("city not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("city not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andAreaIsNull() {
            addCriterion("area is null");
            return (Criteria) this;
        }

        public Criteria andAreaIsNotNull() {
            addCriterion("area is not null");
            return (Criteria) this;
        }

        public Criteria andAreaEqualTo(String value) {
            addCriterion("area =", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotEqualTo(String value) {
            addCriterion("area <>", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThan(String value) {
            addCriterion("area >", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThanOrEqualTo(String value) {
            addCriterion("area >=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThan(String value) {
            addCriterion("area <", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThanOrEqualTo(String value) {
            addCriterion("area <=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLike(String value) {
            addCriterion("area like", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotLike(String value) {
            addCriterion("area not like", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaIn(List<String> values) {
            addCriterion("area in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotIn(List<String> values) {
            addCriterion("area not in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaBetween(String value1, String value2) {
            addCriterion("area between", value1, value2, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotBetween(String value1, String value2) {
            addCriterion("area not between", value1, value2, "area");
            return (Criteria) this;
        }

        public Criteria andReceivingaddressIsNull() {
            addCriterion("receivingaddress is null");
            return (Criteria) this;
        }

        public Criteria andReceivingaddressIsNotNull() {
            addCriterion("receivingaddress is not null");
            return (Criteria) this;
        }

        public Criteria andReceivingaddressEqualTo(String value) {
            addCriterion("receivingaddress =", value, "receivingaddress");
            return (Criteria) this;
        }

        public Criteria andReceivingaddressNotEqualTo(String value) {
            addCriterion("receivingaddress <>", value, "receivingaddress");
            return (Criteria) this;
        }

        public Criteria andReceivingaddressGreaterThan(String value) {
            addCriterion("receivingaddress >", value, "receivingaddress");
            return (Criteria) this;
        }

        public Criteria andReceivingaddressGreaterThanOrEqualTo(String value) {
            addCriterion("receivingaddress >=", value, "receivingaddress");
            return (Criteria) this;
        }

        public Criteria andReceivingaddressLessThan(String value) {
            addCriterion("receivingaddress <", value, "receivingaddress");
            return (Criteria) this;
        }

        public Criteria andReceivingaddressLessThanOrEqualTo(String value) {
            addCriterion("receivingaddress <=", value, "receivingaddress");
            return (Criteria) this;
        }

        public Criteria andReceivingaddressLike(String value) {
            addCriterion("receivingaddress like", value, "receivingaddress");
            return (Criteria) this;
        }

        public Criteria andReceivingaddressNotLike(String value) {
            addCriterion("receivingaddress not like", value, "receivingaddress");
            return (Criteria) this;
        }

        public Criteria andReceivingaddressIn(List<String> values) {
            addCriterion("receivingaddress in", values, "receivingaddress");
            return (Criteria) this;
        }

        public Criteria andReceivingaddressNotIn(List<String> values) {
            addCriterion("receivingaddress not in", values, "receivingaddress");
            return (Criteria) this;
        }

        public Criteria andReceivingaddressBetween(String value1, String value2) {
            addCriterion("receivingaddress between", value1, value2, "receivingaddress");
            return (Criteria) this;
        }

        public Criteria andReceivingaddressNotBetween(String value1, String value2) {
            addCriterion("receivingaddress not between", value1, value2, "receivingaddress");
            return (Criteria) this;
        }

        public Criteria andFreightIsNull() {
            addCriterion("freight is null");
            return (Criteria) this;
        }

        public Criteria andFreightIsNotNull() {
            addCriterion("freight is not null");
            return (Criteria) this;
        }

        public Criteria andFreightEqualTo(BigDecimal value) {
            addCriterion("freight =", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightNotEqualTo(BigDecimal value) {
            addCriterion("freight <>", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightGreaterThan(BigDecimal value) {
            addCriterion("freight >", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("freight >=", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightLessThan(BigDecimal value) {
            addCriterion("freight <", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("freight <=", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightIn(List<BigDecimal> values) {
            addCriterion("freight in", values, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightNotIn(List<BigDecimal> values) {
            addCriterion("freight not in", values, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freight between", value1, value2, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freight not between", value1, value2, "freight");
            return (Criteria) this;
        }

        public Criteria andMailornotIsNull() {
            addCriterion("mailornot is null");
            return (Criteria) this;
        }

        public Criteria andMailornotIsNotNull() {
            addCriterion("mailornot is not null");
            return (Criteria) this;
        }

        public Criteria andMailornotEqualTo(Boolean value) {
            addCriterion("mailornot =", value, "mailornot");
            return (Criteria) this;
        }

        public Criteria andMailornotNotEqualTo(Boolean value) {
            addCriterion("mailornot <>", value, "mailornot");
            return (Criteria) this;
        }

        public Criteria andMailornotGreaterThan(Boolean value) {
            addCriterion("mailornot >", value, "mailornot");
            return (Criteria) this;
        }

        public Criteria andMailornotGreaterThanOrEqualTo(Boolean value) {
            addCriterion("mailornot >=", value, "mailornot");
            return (Criteria) this;
        }

        public Criteria andMailornotLessThan(Boolean value) {
            addCriterion("mailornot <", value, "mailornot");
            return (Criteria) this;
        }

        public Criteria andMailornotLessThanOrEqualTo(Boolean value) {
            addCriterion("mailornot <=", value, "mailornot");
            return (Criteria) this;
        }

        public Criteria andMailornotIn(List<Boolean> values) {
            addCriterion("mailornot in", values, "mailornot");
            return (Criteria) this;
        }

        public Criteria andMailornotNotIn(List<Boolean> values) {
            addCriterion("mailornot not in", values, "mailornot");
            return (Criteria) this;
        }

        public Criteria andMailornotBetween(Boolean value1, Boolean value2) {
            addCriterion("mailornot between", value1, value2, "mailornot");
            return (Criteria) this;
        }

        public Criteria andMailornotNotBetween(Boolean value1, Boolean value2) {
            addCriterion("mailornot not between", value1, value2, "mailornot");
            return (Criteria) this;
        }

        public Criteria andDepositIsNull() {
            addCriterion("deposit is null");
            return (Criteria) this;
        }

        public Criteria andDepositIsNotNull() {
            addCriterion("deposit is not null");
            return (Criteria) this;
        }

        public Criteria andDepositEqualTo(BigDecimal value) {
            addCriterion("deposit =", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotEqualTo(BigDecimal value) {
            addCriterion("deposit <>", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositGreaterThan(BigDecimal value) {
            addCriterion("deposit >", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("deposit >=", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositLessThan(BigDecimal value) {
            addCriterion("deposit <", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositLessThanOrEqualTo(BigDecimal value) {
            addCriterion("deposit <=", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositIn(List<BigDecimal> values) {
            addCriterion("deposit in", values, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotIn(List<BigDecimal> values) {
            addCriterion("deposit not in", values, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deposit between", value1, value2, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deposit not between", value1, value2, "deposit");
            return (Criteria) this;
        }

        public Criteria andBalanceIsNull() {
            addCriterion("balance is null");
            return (Criteria) this;
        }

        public Criteria andBalanceIsNotNull() {
            addCriterion("balance is not null");
            return (Criteria) this;
        }

        public Criteria andBalanceEqualTo(BigDecimal value) {
            addCriterion("balance =", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotEqualTo(BigDecimal value) {
            addCriterion("balance <>", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThan(BigDecimal value) {
            addCriterion("balance >", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("balance >=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThan(BigDecimal value) {
            addCriterion("balance <", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("balance <=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceIn(List<BigDecimal> values) {
            addCriterion("balance in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotIn(List<BigDecimal> values) {
            addCriterion("balance not in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("balance between", value1, value2, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("balance not between", value1, value2, "balance");
            return (Criteria) this;
        }

        public Criteria andFuturetimeIsNull() {
            addCriterion("futuretime is null");
            return (Criteria) this;
        }

        public Criteria andFuturetimeIsNotNull() {
            addCriterion("futuretime is not null");
            return (Criteria) this;
        }

        public Criteria andFuturetimeEqualTo(Date value) {
            addCriterion("futuretime =", value, "futuretime");
            return (Criteria) this;
        }

        public Criteria andFuturetimeNotEqualTo(Date value) {
            addCriterion("futuretime <>", value, "futuretime");
            return (Criteria) this;
        }

        public Criteria andFuturetimeGreaterThan(Date value) {
            addCriterion("futuretime >", value, "futuretime");
            return (Criteria) this;
        }

        public Criteria andFuturetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("futuretime >=", value, "futuretime");
            return (Criteria) this;
        }

        public Criteria andFuturetimeLessThan(Date value) {
            addCriterion("futuretime <", value, "futuretime");
            return (Criteria) this;
        }

        public Criteria andFuturetimeLessThanOrEqualTo(Date value) {
            addCriterion("futuretime <=", value, "futuretime");
            return (Criteria) this;
        }

        public Criteria andFuturetimeIn(List<Date> values) {
            addCriterion("futuretime in", values, "futuretime");
            return (Criteria) this;
        }

        public Criteria andFuturetimeNotIn(List<Date> values) {
            addCriterion("futuretime not in", values, "futuretime");
            return (Criteria) this;
        }

        public Criteria andFuturetimeBetween(Date value1, Date value2) {
            addCriterion("futuretime between", value1, value2, "futuretime");
            return (Criteria) this;
        }

        public Criteria andFuturetimeNotBetween(Date value1, Date value2) {
            addCriterion("futuretime not between", value1, value2, "futuretime");
            return (Criteria) this;
        }

        public Criteria andTotalpriceIsNull() {
            addCriterion("totalprice is null");
            return (Criteria) this;
        }

        public Criteria andTotalpriceIsNotNull() {
            addCriterion("totalprice is not null");
            return (Criteria) this;
        }

        public Criteria andTotalpriceEqualTo(BigDecimal value) {
            addCriterion("totalprice =", value, "totalprice");
            return (Criteria) this;
        }

        public Criteria andTotalpriceNotEqualTo(BigDecimal value) {
            addCriterion("totalprice <>", value, "totalprice");
            return (Criteria) this;
        }

        public Criteria andTotalpriceGreaterThan(BigDecimal value) {
            addCriterion("totalprice >", value, "totalprice");
            return (Criteria) this;
        }

        public Criteria andTotalpriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("totalprice >=", value, "totalprice");
            return (Criteria) this;
        }

        public Criteria andTotalpriceLessThan(BigDecimal value) {
            addCriterion("totalprice <", value, "totalprice");
            return (Criteria) this;
        }

        public Criteria andTotalpriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("totalprice <=", value, "totalprice");
            return (Criteria) this;
        }

        public Criteria andTotalpriceIn(List<BigDecimal> values) {
            addCriterion("totalprice in", values, "totalprice");
            return (Criteria) this;
        }

        public Criteria andTotalpriceNotIn(List<BigDecimal> values) {
            addCriterion("totalprice not in", values, "totalprice");
            return (Criteria) this;
        }

        public Criteria andTotalpriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("totalprice between", value1, value2, "totalprice");
            return (Criteria) this;
        }

        public Criteria andTotalpriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("totalprice not between", value1, value2, "totalprice");
            return (Criteria) this;
        }

        public Criteria andIntegralIsNull() {
            addCriterion("integral is null");
            return (Criteria) this;
        }

        public Criteria andIntegralIsNotNull() {
            addCriterion("integral is not null");
            return (Criteria) this;
        }

        public Criteria andIntegralEqualTo(Integer value) {
            addCriterion("integral =", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralNotEqualTo(Integer value) {
            addCriterion("integral <>", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralGreaterThan(Integer value) {
            addCriterion("integral >", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralGreaterThanOrEqualTo(Integer value) {
            addCriterion("integral >=", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralLessThan(Integer value) {
            addCriterion("integral <", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralLessThanOrEqualTo(Integer value) {
            addCriterion("integral <=", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralIn(List<Integer> values) {
            addCriterion("integral in", values, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralNotIn(List<Integer> values) {
            addCriterion("integral not in", values, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralBetween(Integer value1, Integer value2) {
            addCriterion("integral between", value1, value2, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralNotBetween(Integer value1, Integer value2) {
            addCriterion("integral not between", value1, value2, "integral");
            return (Criteria) this;
        }

        public Criteria andCodeIsNull() {
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("code not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andDeliverymodeIsNull() {
            addCriterion("deliverymode is null");
            return (Criteria) this;
        }

        public Criteria andDeliverymodeIsNotNull() {
            addCriterion("deliverymode is not null");
            return (Criteria) this;
        }

        public Criteria andDeliverymodeEqualTo(Short value) {
            addCriterion("deliverymode =", value, "deliverymode");
            return (Criteria) this;
        }

        public Criteria andDeliverymodeNotEqualTo(Short value) {
            addCriterion("deliverymode <>", value, "deliverymode");
            return (Criteria) this;
        }

        public Criteria andDeliverymodeGreaterThan(Short value) {
            addCriterion("deliverymode >", value, "deliverymode");
            return (Criteria) this;
        }

        public Criteria andDeliverymodeGreaterThanOrEqualTo(Short value) {
            addCriterion("deliverymode >=", value, "deliverymode");
            return (Criteria) this;
        }

        public Criteria andDeliverymodeLessThan(Short value) {
            addCriterion("deliverymode <", value, "deliverymode");
            return (Criteria) this;
        }

        public Criteria andDeliverymodeLessThanOrEqualTo(Short value) {
            addCriterion("deliverymode <=", value, "deliverymode");
            return (Criteria) this;
        }

        public Criteria andDeliverymodeIn(List<Short> values) {
            addCriterion("deliverymode in", values, "deliverymode");
            return (Criteria) this;
        }

        public Criteria andDeliverymodeNotIn(List<Short> values) {
            addCriterion("deliverymode not in", values, "deliverymode");
            return (Criteria) this;
        }

        public Criteria andDeliverymodeBetween(Short value1, Short value2) {
            addCriterion("deliverymode between", value1, value2, "deliverymode");
            return (Criteria) this;
        }

        public Criteria andDeliverymodeNotBetween(Short value1, Short value2) {
            addCriterion("deliverymode not between", value1, value2, "deliverymode");
            return (Criteria) this;
        }

        public Criteria andOrdertypeIsNull() {
            addCriterion("ordertype is null");
            return (Criteria) this;
        }

        public Criteria andOrdertypeIsNotNull() {
            addCriterion("ordertype is not null");
            return (Criteria) this;
        }

        public Criteria andOrdertypeEqualTo(Short value) {
            addCriterion("ordertype =", value, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeNotEqualTo(Short value) {
            addCriterion("ordertype <>", value, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeGreaterThan(Short value) {
            addCriterion("ordertype >", value, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeGreaterThanOrEqualTo(Short value) {
            addCriterion("ordertype >=", value, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeLessThan(Short value) {
            addCriterion("ordertype <", value, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeLessThanOrEqualTo(Short value) {
            addCriterion("ordertype <=", value, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeIn(List<Short> values) {
            addCriterion("ordertype in", values, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeNotIn(List<Short> values) {
            addCriterion("ordertype not in", values, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeBetween(Short value1, Short value2) {
            addCriterion("ordertype between", value1, value2, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeNotBetween(Short value1, Short value2) {
            addCriterion("ordertype not between", value1, value2, "ordertype");
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

        public Criteria andTransactionnumberIsNull() {
            addCriterion("transactionnumber is null");
            return (Criteria) this;
        }

        public Criteria andTransactionnumberIsNotNull() {
            addCriterion("transactionnumber is not null");
            return (Criteria) this;
        }

        public Criteria andTransactionnumberEqualTo(String value) {
            addCriterion("transactionnumber =", value, "transactionnumber");
            return (Criteria) this;
        }

        public Criteria andTransactionnumberNotEqualTo(String value) {
            addCriterion("transactionnumber <>", value, "transactionnumber");
            return (Criteria) this;
        }

        public Criteria andTransactionnumberGreaterThan(String value) {
            addCriterion("transactionnumber >", value, "transactionnumber");
            return (Criteria) this;
        }

        public Criteria andTransactionnumberGreaterThanOrEqualTo(String value) {
            addCriterion("transactionnumber >=", value, "transactionnumber");
            return (Criteria) this;
        }

        public Criteria andTransactionnumberLessThan(String value) {
            addCriterion("transactionnumber <", value, "transactionnumber");
            return (Criteria) this;
        }

        public Criteria andTransactionnumberLessThanOrEqualTo(String value) {
            addCriterion("transactionnumber <=", value, "transactionnumber");
            return (Criteria) this;
        }

        public Criteria andTransactionnumberLike(String value) {
            addCriterion("transactionnumber like", value, "transactionnumber");
            return (Criteria) this;
        }

        public Criteria andTransactionnumberNotLike(String value) {
            addCriterion("transactionnumber not like", value, "transactionnumber");
            return (Criteria) this;
        }

        public Criteria andTransactionnumberIn(List<String> values) {
            addCriterion("transactionnumber in", values, "transactionnumber");
            return (Criteria) this;
        }

        public Criteria andTransactionnumberNotIn(List<String> values) {
            addCriterion("transactionnumber not in", values, "transactionnumber");
            return (Criteria) this;
        }

        public Criteria andTransactionnumberBetween(String value1, String value2) {
            addCriterion("transactionnumber between", value1, value2, "transactionnumber");
            return (Criteria) this;
        }

        public Criteria andTransactionnumberNotBetween(String value1, String value2) {
            addCriterion("transactionnumber not between", value1, value2, "transactionnumber");
            return (Criteria) this;
        }

        public Criteria andPaymentmethodIsNull() {
            addCriterion("paymentmethod is null");
            return (Criteria) this;
        }

        public Criteria andPaymentmethodIsNotNull() {
            addCriterion("paymentmethod is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentmethodEqualTo(Short value) {
            addCriterion("paymentmethod =", value, "paymentmethod");
            return (Criteria) this;
        }

        public Criteria andPaymentmethodNotEqualTo(Short value) {
            addCriterion("paymentmethod <>", value, "paymentmethod");
            return (Criteria) this;
        }

        public Criteria andPaymentmethodGreaterThan(Short value) {
            addCriterion("paymentmethod >", value, "paymentmethod");
            return (Criteria) this;
        }

        public Criteria andPaymentmethodGreaterThanOrEqualTo(Short value) {
            addCriterion("paymentmethod >=", value, "paymentmethod");
            return (Criteria) this;
        }

        public Criteria andPaymentmethodLessThan(Short value) {
            addCriterion("paymentmethod <", value, "paymentmethod");
            return (Criteria) this;
        }

        public Criteria andPaymentmethodLessThanOrEqualTo(Short value) {
            addCriterion("paymentmethod <=", value, "paymentmethod");
            return (Criteria) this;
        }

        public Criteria andPaymentmethodIn(List<Short> values) {
            addCriterion("paymentmethod in", values, "paymentmethod");
            return (Criteria) this;
        }

        public Criteria andPaymentmethodNotIn(List<Short> values) {
            addCriterion("paymentmethod not in", values, "paymentmethod");
            return (Criteria) this;
        }

        public Criteria andPaymentmethodBetween(Short value1, Short value2) {
            addCriterion("paymentmethod between", value1, value2, "paymentmethod");
            return (Criteria) this;
        }

        public Criteria andPaymentmethodNotBetween(Short value1, Short value2) {
            addCriterion("paymentmethod not between", value1, value2, "paymentmethod");
            return (Criteria) this;
        }

        public Criteria andPaymenttimeIsNull() {
            addCriterion("paymenttime is null");
            return (Criteria) this;
        }

        public Criteria andPaymenttimeIsNotNull() {
            addCriterion("paymenttime is not null");
            return (Criteria) this;
        }

        public Criteria andPaymenttimeEqualTo(Date value) {
            addCriterion("paymenttime =", value, "paymenttime");
            return (Criteria) this;
        }

        public Criteria andPaymenttimeNotEqualTo(Date value) {
            addCriterion("paymenttime <>", value, "paymenttime");
            return (Criteria) this;
        }

        public Criteria andPaymenttimeGreaterThan(Date value) {
            addCriterion("paymenttime >", value, "paymenttime");
            return (Criteria) this;
        }

        public Criteria andPaymenttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("paymenttime >=", value, "paymenttime");
            return (Criteria) this;
        }

        public Criteria andPaymenttimeLessThan(Date value) {
            addCriterion("paymenttime <", value, "paymenttime");
            return (Criteria) this;
        }

        public Criteria andPaymenttimeLessThanOrEqualTo(Date value) {
            addCriterion("paymenttime <=", value, "paymenttime");
            return (Criteria) this;
        }

        public Criteria andPaymenttimeIn(List<Date> values) {
            addCriterion("paymenttime in", values, "paymenttime");
            return (Criteria) this;
        }

        public Criteria andPaymenttimeNotIn(List<Date> values) {
            addCriterion("paymenttime not in", values, "paymenttime");
            return (Criteria) this;
        }

        public Criteria andPaymenttimeBetween(Date value1, Date value2) {
            addCriterion("paymenttime between", value1, value2, "paymenttime");
            return (Criteria) this;
        }

        public Criteria andPaymenttimeNotBetween(Date value1, Date value2) {
            addCriterion("paymenttime not between", value1, value2, "paymenttime");
            return (Criteria) this;
        }

        public Criteria andSellerdeliverytimeIsNull() {
            addCriterion("sellerdeliverytime is null");
            return (Criteria) this;
        }

        public Criteria andSellerdeliverytimeIsNotNull() {
            addCriterion("sellerdeliverytime is not null");
            return (Criteria) this;
        }

        public Criteria andSellerdeliverytimeEqualTo(Date value) {
            addCriterion("sellerdeliverytime =", value, "sellerdeliverytime");
            return (Criteria) this;
        }

        public Criteria andSellerdeliverytimeNotEqualTo(Date value) {
            addCriterion("sellerdeliverytime <>", value, "sellerdeliverytime");
            return (Criteria) this;
        }

        public Criteria andSellerdeliverytimeGreaterThan(Date value) {
            addCriterion("sellerdeliverytime >", value, "sellerdeliverytime");
            return (Criteria) this;
        }

        public Criteria andSellerdeliverytimeGreaterThanOrEqualTo(Date value) {
            addCriterion("sellerdeliverytime >=", value, "sellerdeliverytime");
            return (Criteria) this;
        }

        public Criteria andSellerdeliverytimeLessThan(Date value) {
            addCriterion("sellerdeliverytime <", value, "sellerdeliverytime");
            return (Criteria) this;
        }

        public Criteria andSellerdeliverytimeLessThanOrEqualTo(Date value) {
            addCriterion("sellerdeliverytime <=", value, "sellerdeliverytime");
            return (Criteria) this;
        }

        public Criteria andSellerdeliverytimeIn(List<Date> values) {
            addCriterion("sellerdeliverytime in", values, "sellerdeliverytime");
            return (Criteria) this;
        }

        public Criteria andSellerdeliverytimeNotIn(List<Date> values) {
            addCriterion("sellerdeliverytime not in", values, "sellerdeliverytime");
            return (Criteria) this;
        }

        public Criteria andSellerdeliverytimeBetween(Date value1, Date value2) {
            addCriterion("sellerdeliverytime between", value1, value2, "sellerdeliverytime");
            return (Criteria) this;
        }

        public Criteria andSellerdeliverytimeNotBetween(Date value1, Date value2) {
            addCriterion("sellerdeliverytime not between", value1, value2, "sellerdeliverytime");
            return (Criteria) this;
        }

        public Criteria andDeliverynoIsNull() {
            addCriterion("deliveryno is null");
            return (Criteria) this;
        }

        public Criteria andDeliverynoIsNotNull() {
            addCriterion("deliveryno is not null");
            return (Criteria) this;
        }

        public Criteria andDeliverynoEqualTo(String value) {
            addCriterion("deliveryno =", value, "deliveryno");
            return (Criteria) this;
        }

        public Criteria andDeliverynoNotEqualTo(String value) {
            addCriterion("deliveryno <>", value, "deliveryno");
            return (Criteria) this;
        }

        public Criteria andDeliverynoGreaterThan(String value) {
            addCriterion("deliveryno >", value, "deliveryno");
            return (Criteria) this;
        }

        public Criteria andDeliverynoGreaterThanOrEqualTo(String value) {
            addCriterion("deliveryno >=", value, "deliveryno");
            return (Criteria) this;
        }

        public Criteria andDeliverynoLessThan(String value) {
            addCriterion("deliveryno <", value, "deliveryno");
            return (Criteria) this;
        }

        public Criteria andDeliverynoLessThanOrEqualTo(String value) {
            addCriterion("deliveryno <=", value, "deliveryno");
            return (Criteria) this;
        }

        public Criteria andDeliverynoLike(String value) {
            addCriterion("deliveryno like", value, "deliveryno");
            return (Criteria) this;
        }

        public Criteria andDeliverynoNotLike(String value) {
            addCriterion("deliveryno not like", value, "deliveryno");
            return (Criteria) this;
        }

        public Criteria andDeliverynoIn(List<String> values) {
            addCriterion("deliveryno in", values, "deliveryno");
            return (Criteria) this;
        }

        public Criteria andDeliverynoNotIn(List<String> values) {
            addCriterion("deliveryno not in", values, "deliveryno");
            return (Criteria) this;
        }

        public Criteria andDeliverynoBetween(String value1, String value2) {
            addCriterion("deliveryno between", value1, value2, "deliveryno");
            return (Criteria) this;
        }

        public Criteria andDeliverynoNotBetween(String value1, String value2) {
            addCriterion("deliveryno not between", value1, value2, "deliveryno");
            return (Criteria) this;
        }

        public Criteria andBuyerdeliverytimeIsNull() {
            addCriterion("buyerdeliverytime is null");
            return (Criteria) this;
        }

        public Criteria andBuyerdeliverytimeIsNotNull() {
            addCriterion("buyerdeliverytime is not null");
            return (Criteria) this;
        }

        public Criteria andBuyerdeliverytimeEqualTo(Date value) {
            addCriterion("buyerdeliverytime =", value, "buyerdeliverytime");
            return (Criteria) this;
        }

        public Criteria andBuyerdeliverytimeNotEqualTo(Date value) {
            addCriterion("buyerdeliverytime <>", value, "buyerdeliverytime");
            return (Criteria) this;
        }

        public Criteria andBuyerdeliverytimeGreaterThan(Date value) {
            addCriterion("buyerdeliverytime >", value, "buyerdeliverytime");
            return (Criteria) this;
        }

        public Criteria andBuyerdeliverytimeGreaterThanOrEqualTo(Date value) {
            addCriterion("buyerdeliverytime >=", value, "buyerdeliverytime");
            return (Criteria) this;
        }

        public Criteria andBuyerdeliverytimeLessThan(Date value) {
            addCriterion("buyerdeliverytime <", value, "buyerdeliverytime");
            return (Criteria) this;
        }

        public Criteria andBuyerdeliverytimeLessThanOrEqualTo(Date value) {
            addCriterion("buyerdeliverytime <=", value, "buyerdeliverytime");
            return (Criteria) this;
        }

        public Criteria andBuyerdeliverytimeIn(List<Date> values) {
            addCriterion("buyerdeliverytime in", values, "buyerdeliverytime");
            return (Criteria) this;
        }

        public Criteria andBuyerdeliverytimeNotIn(List<Date> values) {
            addCriterion("buyerdeliverytime not in", values, "buyerdeliverytime");
            return (Criteria) this;
        }

        public Criteria andBuyerdeliverytimeBetween(Date value1, Date value2) {
            addCriterion("buyerdeliverytime between", value1, value2, "buyerdeliverytime");
            return (Criteria) this;
        }

        public Criteria andBuyerdeliverytimeNotBetween(Date value1, Date value2) {
            addCriterion("buyerdeliverytime not between", value1, value2, "buyerdeliverytime");
            return (Criteria) this;
        }

        public Criteria andBuyerinspectiontimeIsNull() {
            addCriterion("buyerinspectiontime is null");
            return (Criteria) this;
        }

        public Criteria andBuyerinspectiontimeIsNotNull() {
            addCriterion("buyerinspectiontime is not null");
            return (Criteria) this;
        }

        public Criteria andBuyerinspectiontimeEqualTo(Date value) {
            addCriterion("buyerinspectiontime =", value, "buyerinspectiontime");
            return (Criteria) this;
        }

        public Criteria andBuyerinspectiontimeNotEqualTo(Date value) {
            addCriterion("buyerinspectiontime <>", value, "buyerinspectiontime");
            return (Criteria) this;
        }

        public Criteria andBuyerinspectiontimeGreaterThan(Date value) {
            addCriterion("buyerinspectiontime >", value, "buyerinspectiontime");
            return (Criteria) this;
        }

        public Criteria andBuyerinspectiontimeGreaterThanOrEqualTo(Date value) {
            addCriterion("buyerinspectiontime >=", value, "buyerinspectiontime");
            return (Criteria) this;
        }

        public Criteria andBuyerinspectiontimeLessThan(Date value) {
            addCriterion("buyerinspectiontime <", value, "buyerinspectiontime");
            return (Criteria) this;
        }

        public Criteria andBuyerinspectiontimeLessThanOrEqualTo(Date value) {
            addCriterion("buyerinspectiontime <=", value, "buyerinspectiontime");
            return (Criteria) this;
        }

        public Criteria andBuyerinspectiontimeIn(List<Date> values) {
            addCriterion("buyerinspectiontime in", values, "buyerinspectiontime");
            return (Criteria) this;
        }

        public Criteria andBuyerinspectiontimeNotIn(List<Date> values) {
            addCriterion("buyerinspectiontime not in", values, "buyerinspectiontime");
            return (Criteria) this;
        }

        public Criteria andBuyerinspectiontimeBetween(Date value1, Date value2) {
            addCriterion("buyerinspectiontime between", value1, value2, "buyerinspectiontime");
            return (Criteria) this;
        }

        public Criteria andBuyerinspectiontimeNotBetween(Date value1, Date value2) {
            addCriterion("buyerinspectiontime not between", value1, value2, "buyerinspectiontime");
            return (Criteria) this;
        }

        public Criteria andOrderstatusIsNull() {
            addCriterion("orderstatus is null");
            return (Criteria) this;
        }

        public Criteria andOrderstatusIsNotNull() {
            addCriterion("orderstatus is not null");
            return (Criteria) this;
        }

        public Criteria andOrderstatusEqualTo(Short value) {
            addCriterion("orderstatus =", value, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andOrderstatusNotEqualTo(Short value) {
            addCriterion("orderstatus <>", value, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andOrderstatusGreaterThan(Short value) {
            addCriterion("orderstatus >", value, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andOrderstatusGreaterThanOrEqualTo(Short value) {
            addCriterion("orderstatus >=", value, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andOrderstatusLessThan(Short value) {
            addCriterion("orderstatus <", value, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andOrderstatusLessThanOrEqualTo(Short value) {
            addCriterion("orderstatus <=", value, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andOrderstatusIn(List<Short> values) {
            addCriterion("orderstatus in", values, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andOrderstatusNotIn(List<Short> values) {
            addCriterion("orderstatus not in", values, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andOrderstatusBetween(Short value1, Short value2) {
            addCriterion("orderstatus between", value1, value2, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andOrderstatusNotBetween(Short value1, Short value2) {
            addCriterion("orderstatus not between", value1, value2, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andActualpaymentIsNull() {
            addCriterion("actualpayment is null");
            return (Criteria) this;
        }

        public Criteria andActualpaymentIsNotNull() {
            addCriterion("actualpayment is not null");
            return (Criteria) this;
        }

        public Criteria andActualpaymentEqualTo(BigDecimal value) {
            addCriterion("actualpayment =", value, "actualpayment");
            return (Criteria) this;
        }

        public Criteria andActualpaymentNotEqualTo(BigDecimal value) {
            addCriterion("actualpayment <>", value, "actualpayment");
            return (Criteria) this;
        }

        public Criteria andActualpaymentGreaterThan(BigDecimal value) {
            addCriterion("actualpayment >", value, "actualpayment");
            return (Criteria) this;
        }

        public Criteria andActualpaymentGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("actualpayment >=", value, "actualpayment");
            return (Criteria) this;
        }

        public Criteria andActualpaymentLessThan(BigDecimal value) {
            addCriterion("actualpayment <", value, "actualpayment");
            return (Criteria) this;
        }

        public Criteria andActualpaymentLessThanOrEqualTo(BigDecimal value) {
            addCriterion("actualpayment <=", value, "actualpayment");
            return (Criteria) this;
        }

        public Criteria andActualpaymentIn(List<BigDecimal> values) {
            addCriterion("actualpayment in", values, "actualpayment");
            return (Criteria) this;
        }

        public Criteria andActualpaymentNotIn(List<BigDecimal> values) {
            addCriterion("actualpayment not in", values, "actualpayment");
            return (Criteria) this;
        }

        public Criteria andActualpaymentBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("actualpayment between", value1, value2, "actualpayment");
            return (Criteria) this;
        }

        public Criteria andActualpaymentNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("actualpayment not between", value1, value2, "actualpayment");
            return (Criteria) this;
        }

        public Criteria andCouriernumberIsNull() {
            addCriterion("couriernumber is null");
            return (Criteria) this;
        }

        public Criteria andCouriernumberIsNotNull() {
            addCriterion("couriernumber is not null");
            return (Criteria) this;
        }

        public Criteria andCouriernumberEqualTo(String value) {
            addCriterion("couriernumber =", value, "couriernumber");
            return (Criteria) this;
        }

        public Criteria andCouriernumberNotEqualTo(String value) {
            addCriterion("couriernumber <>", value, "couriernumber");
            return (Criteria) this;
        }

        public Criteria andCouriernumberGreaterThan(String value) {
            addCriterion("couriernumber >", value, "couriernumber");
            return (Criteria) this;
        }

        public Criteria andCouriernumberGreaterThanOrEqualTo(String value) {
            addCriterion("couriernumber >=", value, "couriernumber");
            return (Criteria) this;
        }

        public Criteria andCouriernumberLessThan(String value) {
            addCriterion("couriernumber <", value, "couriernumber");
            return (Criteria) this;
        }

        public Criteria andCouriernumberLessThanOrEqualTo(String value) {
            addCriterion("couriernumber <=", value, "couriernumber");
            return (Criteria) this;
        }

        public Criteria andCouriernumberLike(String value) {
            addCriterion("couriernumber like", value, "couriernumber");
            return (Criteria) this;
        }

        public Criteria andCouriernumberNotLike(String value) {
            addCriterion("couriernumber not like", value, "couriernumber");
            return (Criteria) this;
        }

        public Criteria andCouriernumberIn(List<String> values) {
            addCriterion("couriernumber in", values, "couriernumber");
            return (Criteria) this;
        }

        public Criteria andCouriernumberNotIn(List<String> values) {
            addCriterion("couriernumber not in", values, "couriernumber");
            return (Criteria) this;
        }

        public Criteria andCouriernumberBetween(String value1, String value2) {
            addCriterion("couriernumber between", value1, value2, "couriernumber");
            return (Criteria) this;
        }

        public Criteria andCouriernumberNotBetween(String value1, String value2) {
            addCriterion("couriernumber not between", value1, value2, "couriernumber");
            return (Criteria) this;
        }

        public Criteria andLogisticscompanyIsNull() {
            addCriterion("logisticscompany is null");
            return (Criteria) this;
        }

        public Criteria andLogisticscompanyIsNotNull() {
            addCriterion("logisticscompany is not null");
            return (Criteria) this;
        }

        public Criteria andLogisticscompanyEqualTo(String value) {
            addCriterion("logisticscompany =", value, "logisticscompany");
            return (Criteria) this;
        }

        public Criteria andLogisticscompanyNotEqualTo(String value) {
            addCriterion("logisticscompany <>", value, "logisticscompany");
            return (Criteria) this;
        }

        public Criteria andLogisticscompanyGreaterThan(String value) {
            addCriterion("logisticscompany >", value, "logisticscompany");
            return (Criteria) this;
        }

        public Criteria andLogisticscompanyGreaterThanOrEqualTo(String value) {
            addCriterion("logisticscompany >=", value, "logisticscompany");
            return (Criteria) this;
        }

        public Criteria andLogisticscompanyLessThan(String value) {
            addCriterion("logisticscompany <", value, "logisticscompany");
            return (Criteria) this;
        }

        public Criteria andLogisticscompanyLessThanOrEqualTo(String value) {
            addCriterion("logisticscompany <=", value, "logisticscompany");
            return (Criteria) this;
        }

        public Criteria andLogisticscompanyLike(String value) {
            addCriterion("logisticscompany like", value, "logisticscompany");
            return (Criteria) this;
        }

        public Criteria andLogisticscompanyNotLike(String value) {
            addCriterion("logisticscompany not like", value, "logisticscompany");
            return (Criteria) this;
        }

        public Criteria andLogisticscompanyIn(List<String> values) {
            addCriterion("logisticscompany in", values, "logisticscompany");
            return (Criteria) this;
        }

        public Criteria andLogisticscompanyNotIn(List<String> values) {
            addCriterion("logisticscompany not in", values, "logisticscompany");
            return (Criteria) this;
        }

        public Criteria andLogisticscompanyBetween(String value1, String value2) {
            addCriterion("logisticscompany between", value1, value2, "logisticscompany");
            return (Criteria) this;
        }

        public Criteria andLogisticscompanyNotBetween(String value1, String value2) {
            addCriterion("logisticscompany not between", value1, value2, "logisticscompany");
            return (Criteria) this;
        }

        public Criteria andIsbillingIsNull() {
            addCriterion("isbilling is null");
            return (Criteria) this;
        }

        public Criteria andIsbillingIsNotNull() {
            addCriterion("isbilling is not null");
            return (Criteria) this;
        }

        public Criteria andIsbillingEqualTo(Short value) {
            addCriterion("isbilling =", value, "isbilling");
            return (Criteria) this;
        }

        public Criteria andIsbillingNotEqualTo(Short value) {
            addCriterion("isbilling <>", value, "isbilling");
            return (Criteria) this;
        }

        public Criteria andIsbillingGreaterThan(Short value) {
            addCriterion("isbilling >", value, "isbilling");
            return (Criteria) this;
        }

        public Criteria andIsbillingGreaterThanOrEqualTo(Short value) {
            addCriterion("isbilling >=", value, "isbilling");
            return (Criteria) this;
        }

        public Criteria andIsbillingLessThan(Short value) {
            addCriterion("isbilling <", value, "isbilling");
            return (Criteria) this;
        }

        public Criteria andIsbillingLessThanOrEqualTo(Short value) {
            addCriterion("isbilling <=", value, "isbilling");
            return (Criteria) this;
        }

        public Criteria andIsbillingIn(List<Short> values) {
            addCriterion("isbilling in", values, "isbilling");
            return (Criteria) this;
        }

        public Criteria andIsbillingNotIn(List<Short> values) {
            addCriterion("isbilling not in", values, "isbilling");
            return (Criteria) this;
        }

        public Criteria andIsbillingBetween(Short value1, Short value2) {
            addCriterion("isbilling between", value1, value2, "isbilling");
            return (Criteria) this;
        }

        public Criteria andIsbillingNotBetween(Short value1, Short value2) {
            addCriterion("isbilling not between", value1, value2, "isbilling");
            return (Criteria) this;
        }

        public Criteria andBillingtypeIsNull() {
            addCriterion("billingtype is null");
            return (Criteria) this;
        }

        public Criteria andBillingtypeIsNotNull() {
            addCriterion("billingtype is not null");
            return (Criteria) this;
        }

        public Criteria andBillingtypeEqualTo(Short value) {
            addCriterion("billingtype =", value, "billingtype");
            return (Criteria) this;
        }

        public Criteria andBillingtypeNotEqualTo(Short value) {
            addCriterion("billingtype <>", value, "billingtype");
            return (Criteria) this;
        }

        public Criteria andBillingtypeGreaterThan(Short value) {
            addCriterion("billingtype >", value, "billingtype");
            return (Criteria) this;
        }

        public Criteria andBillingtypeGreaterThanOrEqualTo(Short value) {
            addCriterion("billingtype >=", value, "billingtype");
            return (Criteria) this;
        }

        public Criteria andBillingtypeLessThan(Short value) {
            addCriterion("billingtype <", value, "billingtype");
            return (Criteria) this;
        }

        public Criteria andBillingtypeLessThanOrEqualTo(Short value) {
            addCriterion("billingtype <=", value, "billingtype");
            return (Criteria) this;
        }

        public Criteria andBillingtypeIn(List<Short> values) {
            addCriterion("billingtype in", values, "billingtype");
            return (Criteria) this;
        }

        public Criteria andBillingtypeNotIn(List<Short> values) {
            addCriterion("billingtype not in", values, "billingtype");
            return (Criteria) this;
        }

        public Criteria andBillingtypeBetween(Short value1, Short value2) {
            addCriterion("billingtype between", value1, value2, "billingtype");
            return (Criteria) this;
        }

        public Criteria andBillingtypeNotBetween(Short value1, Short value2) {
            addCriterion("billingtype not between", value1, value2, "billingtype");
            return (Criteria) this;
        }

        public Criteria andUuidIsNull() {
            addCriterion("uuid is null");
            return (Criteria) this;
        }

        public Criteria andUuidIsNotNull() {
            addCriterion("uuid is not null");
            return (Criteria) this;
        }

        public Criteria andUuidEqualTo(String value) {
            addCriterion("uuid =", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotEqualTo(String value) {
            addCriterion("uuid <>", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThan(String value) {
            addCriterion("uuid >", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThanOrEqualTo(String value) {
            addCriterion("uuid >=", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLessThan(String value) {
            addCriterion("uuid <", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLessThanOrEqualTo(String value) {
            addCriterion("uuid <=", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLike(String value) {
            addCriterion("uuid like", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotLike(String value) {
            addCriterion("uuid not like", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidIn(List<String> values) {
            addCriterion("uuid in", values, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotIn(List<String> values) {
            addCriterion("uuid not in", values, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidBetween(String value1, String value2) {
            addCriterion("uuid between", value1, value2, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotBetween(String value1, String value2) {
            addCriterion("uuid not between", value1, value2, "uuid");
            return (Criteria) this;
        }

        public Criteria andMembercompanyIsNull() {
            addCriterion("membercompany is null");
            return (Criteria) this;
        }

        public Criteria andMembercompanyIsNotNull() {
            addCriterion("membercompany is not null");
            return (Criteria) this;
        }

        public Criteria andMembercompanyEqualTo(String value) {
            addCriterion("membercompany =", value, "membercompany");
            return (Criteria) this;
        }

        public Criteria andMembercompanyNotEqualTo(String value) {
            addCriterion("membercompany <>", value, "membercompany");
            return (Criteria) this;
        }

        public Criteria andMembercompanyGreaterThan(String value) {
            addCriterion("membercompany >", value, "membercompany");
            return (Criteria) this;
        }

        public Criteria andMembercompanyGreaterThanOrEqualTo(String value) {
            addCriterion("membercompany >=", value, "membercompany");
            return (Criteria) this;
        }

        public Criteria andMembercompanyLessThan(String value) {
            addCriterion("membercompany <", value, "membercompany");
            return (Criteria) this;
        }

        public Criteria andMembercompanyLessThanOrEqualTo(String value) {
            addCriterion("membercompany <=", value, "membercompany");
            return (Criteria) this;
        }

        public Criteria andMembercompanyLike(String value) {
            addCriterion("membercompany like", value, "membercompany");
            return (Criteria) this;
        }

        public Criteria andMembercompanyNotLike(String value) {
            addCriterion("membercompany not like", value, "membercompany");
            return (Criteria) this;
        }

        public Criteria andMembercompanyIn(List<String> values) {
            addCriterion("membercompany in", values, "membercompany");
            return (Criteria) this;
        }

        public Criteria andMembercompanyNotIn(List<String> values) {
            addCriterion("membercompany not in", values, "membercompany");
            return (Criteria) this;
        }

        public Criteria andMembercompanyBetween(String value1, String value2) {
            addCriterion("membercompany between", value1, value2, "membercompany");
            return (Criteria) this;
        }

        public Criteria andMembercompanyNotBetween(String value1, String value2) {
            addCriterion("membercompany not between", value1, value2, "membercompany");
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

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andShiptoIsNull() {
            addCriterion("shipto is null");
            return (Criteria) this;
        }

        public Criteria andShiptoIsNotNull() {
            addCriterion("shipto is not null");
            return (Criteria) this;
        }

        public Criteria andShiptoEqualTo(String value) {
            addCriterion("shipto =", value, "shipto");
            return (Criteria) this;
        }

        public Criteria andShiptoNotEqualTo(String value) {
            addCriterion("shipto <>", value, "shipto");
            return (Criteria) this;
        }

        public Criteria andShiptoGreaterThan(String value) {
            addCriterion("shipto >", value, "shipto");
            return (Criteria) this;
        }

        public Criteria andShiptoGreaterThanOrEqualTo(String value) {
            addCriterion("shipto >=", value, "shipto");
            return (Criteria) this;
        }

        public Criteria andShiptoLessThan(String value) {
            addCriterion("shipto <", value, "shipto");
            return (Criteria) this;
        }

        public Criteria andShiptoLessThanOrEqualTo(String value) {
            addCriterion("shipto <=", value, "shipto");
            return (Criteria) this;
        }

        public Criteria andShiptoLike(String value) {
            addCriterion("shipto like", value, "shipto");
            return (Criteria) this;
        }

        public Criteria andShiptoNotLike(String value) {
            addCriterion("shipto not like", value, "shipto");
            return (Criteria) this;
        }

        public Criteria andShiptoIn(List<String> values) {
            addCriterion("shipto in", values, "shipto");
            return (Criteria) this;
        }

        public Criteria andShiptoNotIn(List<String> values) {
            addCriterion("shipto not in", values, "shipto");
            return (Criteria) this;
        }

        public Criteria andShiptoBetween(String value1, String value2) {
            addCriterion("shipto between", value1, value2, "shipto");
            return (Criteria) this;
        }

        public Criteria andShiptoNotBetween(String value1, String value2) {
            addCriterion("shipto not between", value1, value2, "shipto");
            return (Criteria) this;
        }

        public Criteria andIsbackcreditIsNull() {
            addCriterion("isbackcredit is null");
            return (Criteria) this;
        }

        public Criteria andIsbackcreditIsNotNull() {
            addCriterion("isbackcredit is not null");
            return (Criteria) this;
        }

        public Criteria andIsbackcreditEqualTo(Short value) {
            addCriterion("isbackcredit =", value, "isbackcredit");
            return (Criteria) this;
        }

        public Criteria andIsbackcreditNotEqualTo(Short value) {
            addCriterion("isbackcredit <>", value, "isbackcredit");
            return (Criteria) this;
        }

        public Criteria andIsbackcreditGreaterThan(Short value) {
            addCriterion("isbackcredit >", value, "isbackcredit");
            return (Criteria) this;
        }

        public Criteria andIsbackcreditGreaterThanOrEqualTo(Short value) {
            addCriterion("isbackcredit >=", value, "isbackcredit");
            return (Criteria) this;
        }

        public Criteria andIsbackcreditLessThan(Short value) {
            addCriterion("isbackcredit <", value, "isbackcredit");
            return (Criteria) this;
        }

        public Criteria andIsbackcreditLessThanOrEqualTo(Short value) {
            addCriterion("isbackcredit <=", value, "isbackcredit");
            return (Criteria) this;
        }

        public Criteria andIsbackcreditIn(List<Short> values) {
            addCriterion("isbackcredit in", values, "isbackcredit");
            return (Criteria) this;
        }

        public Criteria andIsbackcreditNotIn(List<Short> values) {
            addCriterion("isbackcredit not in", values, "isbackcredit");
            return (Criteria) this;
        }

        public Criteria andIsbackcreditBetween(Short value1, Short value2) {
            addCriterion("isbackcredit between", value1, value2, "isbackcredit");
            return (Criteria) this;
        }

        public Criteria andIsbackcreditNotBetween(Short value1, Short value2) {
            addCriterion("isbackcredit not between", value1, value2, "isbackcredit");
            return (Criteria) this;
        }

        public Criteria andPaytypeIsNull() {
            addCriterion("paytype is null");
            return (Criteria) this;
        }

        public Criteria andPaytypeIsNotNull() {
            addCriterion("paytype is not null");
            return (Criteria) this;
        }

        public Criteria andPaytypeEqualTo(Short value) {
            addCriterion("paytype =", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeNotEqualTo(Short value) {
            addCriterion("paytype <>", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeGreaterThan(Short value) {
            addCriterion("paytype >", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeGreaterThanOrEqualTo(Short value) {
            addCriterion("paytype >=", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeLessThan(Short value) {
            addCriterion("paytype <", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeLessThanOrEqualTo(Short value) {
            addCriterion("paytype <=", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeIn(List<Short> values) {
            addCriterion("paytype in", values, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeNotIn(List<Short> values) {
            addCriterion("paytype not in", values, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeBetween(Short value1, Short value2) {
            addCriterion("paytype between", value1, value2, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeNotBetween(Short value1, Short value2) {
            addCriterion("paytype not between", value1, value2, "paytype");
            return (Criteria) this;
        }

        public Criteria andMembernameIsNull() {
            addCriterion("membername is null");
            return (Criteria) this;
        }

        public Criteria andMembernameIsNotNull() {
            addCriterion("membername is not null");
            return (Criteria) this;
        }

        public Criteria andMembernameEqualTo(String value) {
            addCriterion("membername =", value, "membername");
            return (Criteria) this;
        }

        public Criteria andMembernameNotEqualTo(String value) {
            addCriterion("membername <>", value, "membername");
            return (Criteria) this;
        }

        public Criteria andMembernameGreaterThan(String value) {
            addCriterion("membername >", value, "membername");
            return (Criteria) this;
        }

        public Criteria andMembernameGreaterThanOrEqualTo(String value) {
            addCriterion("membername >=", value, "membername");
            return (Criteria) this;
        }

        public Criteria andMembernameLessThan(String value) {
            addCriterion("membername <", value, "membername");
            return (Criteria) this;
        }

        public Criteria andMembernameLessThanOrEqualTo(String value) {
            addCriterion("membername <=", value, "membername");
            return (Criteria) this;
        }

        public Criteria andMembernameLike(String value) {
            addCriterion("membername like", value, "membername");
            return (Criteria) this;
        }

        public Criteria andMembernameNotLike(String value) {
            addCriterion("membername not like", value, "membername");
            return (Criteria) this;
        }

        public Criteria andMembernameIn(List<String> values) {
            addCriterion("membername in", values, "membername");
            return (Criteria) this;
        }

        public Criteria andMembernameNotIn(List<String> values) {
            addCriterion("membername not in", values, "membername");
            return (Criteria) this;
        }

        public Criteria andMembernameBetween(String value1, String value2) {
            addCriterion("membername between", value1, value2, "membername");
            return (Criteria) this;
        }

        public Criteria andMembernameNotBetween(String value1, String value2) {
            addCriterion("membername not between", value1, value2, "membername");
            return (Criteria) this;
        }

        public Criteria andSellernameIsNull() {
            addCriterion("sellername is null");
            return (Criteria) this;
        }

        public Criteria andSellernameIsNotNull() {
            addCriterion("sellername is not null");
            return (Criteria) this;
        }

        public Criteria andSellernameEqualTo(String value) {
            addCriterion("sellername =", value, "sellername");
            return (Criteria) this;
        }

        public Criteria andSellernameNotEqualTo(String value) {
            addCriterion("sellername <>", value, "sellername");
            return (Criteria) this;
        }

        public Criteria andSellernameGreaterThan(String value) {
            addCriterion("sellername >", value, "sellername");
            return (Criteria) this;
        }

        public Criteria andSellernameGreaterThanOrEqualTo(String value) {
            addCriterion("sellername >=", value, "sellername");
            return (Criteria) this;
        }

        public Criteria andSellernameLessThan(String value) {
            addCriterion("sellername <", value, "sellername");
            return (Criteria) this;
        }

        public Criteria andSellernameLessThanOrEqualTo(String value) {
            addCriterion("sellername <=", value, "sellername");
            return (Criteria) this;
        }

        public Criteria andSellernameLike(String value) {
            addCriterion("sellername like", value, "sellername");
            return (Criteria) this;
        }

        public Criteria andSellernameNotLike(String value) {
            addCriterion("sellername not like", value, "sellername");
            return (Criteria) this;
        }

        public Criteria andSellernameIn(List<String> values) {
            addCriterion("sellername in", values, "sellername");
            return (Criteria) this;
        }

        public Criteria andSellernameNotIn(List<String> values) {
            addCriterion("sellername not in", values, "sellername");
            return (Criteria) this;
        }

        public Criteria andSellernameBetween(String value1, String value2) {
            addCriterion("sellername between", value1, value2, "sellername");
            return (Criteria) this;
        }

        public Criteria andSellernameNotBetween(String value1, String value2) {
            addCriterion("sellername not between", value1, value2, "sellername");
            return (Criteria) this;
        }

        public Criteria andShopnameIsNull() {
            addCriterion("shopname is null");
            return (Criteria) this;
        }

        public Criteria andShopnameIsNotNull() {
            addCriterion("shopname is not null");
            return (Criteria) this;
        }

        public Criteria andShopnameEqualTo(String value) {
            addCriterion("shopname =", value, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameNotEqualTo(String value) {
            addCriterion("shopname <>", value, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameGreaterThan(String value) {
            addCriterion("shopname >", value, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameGreaterThanOrEqualTo(String value) {
            addCriterion("shopname >=", value, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameLessThan(String value) {
            addCriterion("shopname <", value, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameLessThanOrEqualTo(String value) {
            addCriterion("shopname <=", value, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameLike(String value) {
            addCriterion("shopname like", value, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameNotLike(String value) {
            addCriterion("shopname not like", value, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameIn(List<String> values) {
            addCriterion("shopname in", values, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameNotIn(List<String> values) {
            addCriterion("shopname not in", values, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameBetween(String value1, String value2) {
            addCriterion("shopname between", value1, value2, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameNotBetween(String value1, String value2) {
            addCriterion("shopname not between", value1, value2, "shopname");
            return (Criteria) this;
        }

        public Criteria andBrokepayIsNull() {
            addCriterion("brokepay is null");
            return (Criteria) this;
        }

        public Criteria andBrokepayIsNotNull() {
            addCriterion("brokepay is not null");
            return (Criteria) this;
        }

        public Criteria andBrokepayEqualTo(BigDecimal value) {
            addCriterion("brokepay =", value, "brokepay");
            return (Criteria) this;
        }

        public Criteria andBrokepayNotEqualTo(BigDecimal value) {
            addCriterion("brokepay <>", value, "brokepay");
            return (Criteria) this;
        }

        public Criteria andBrokepayGreaterThan(BigDecimal value) {
            addCriterion("brokepay >", value, "brokepay");
            return (Criteria) this;
        }

        public Criteria andBrokepayGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("brokepay >=", value, "brokepay");
            return (Criteria) this;
        }

        public Criteria andBrokepayLessThan(BigDecimal value) {
            addCriterion("brokepay <", value, "brokepay");
            return (Criteria) this;
        }

        public Criteria andBrokepayLessThanOrEqualTo(BigDecimal value) {
            addCriterion("brokepay <=", value, "brokepay");
            return (Criteria) this;
        }

        public Criteria andBrokepayIn(List<BigDecimal> values) {
            addCriterion("brokepay in", values, "brokepay");
            return (Criteria) this;
        }

        public Criteria andBrokepayNotIn(List<BigDecimal> values) {
            addCriterion("brokepay not in", values, "brokepay");
            return (Criteria) this;
        }

        public Criteria andBrokepayBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("brokepay between", value1, value2, "brokepay");
            return (Criteria) this;
        }

        public Criteria andBrokepayNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("brokepay not between", value1, value2, "brokepay");
            return (Criteria) this;
        }

        public Criteria andFrozepayIsNull() {
            addCriterion("frozepay is null");
            return (Criteria) this;
        }

        public Criteria andFrozepayIsNotNull() {
            addCriterion("frozepay is not null");
            return (Criteria) this;
        }

        public Criteria andFrozepayEqualTo(BigDecimal value) {
            addCriterion("frozepay =", value, "frozepay");
            return (Criteria) this;
        }

        public Criteria andFrozepayNotEqualTo(BigDecimal value) {
            addCriterion("frozepay <>", value, "frozepay");
            return (Criteria) this;
        }

        public Criteria andFrozepayGreaterThan(BigDecimal value) {
            addCriterion("frozepay >", value, "frozepay");
            return (Criteria) this;
        }

        public Criteria andFrozepayGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("frozepay >=", value, "frozepay");
            return (Criteria) this;
        }

        public Criteria andFrozepayLessThan(BigDecimal value) {
            addCriterion("frozepay <", value, "frozepay");
            return (Criteria) this;
        }

        public Criteria andFrozepayLessThanOrEqualTo(BigDecimal value) {
            addCriterion("frozepay <=", value, "frozepay");
            return (Criteria) this;
        }

        public Criteria andFrozepayIn(List<BigDecimal> values) {
            addCriterion("frozepay in", values, "frozepay");
            return (Criteria) this;
        }

        public Criteria andFrozepayNotIn(List<BigDecimal> values) {
            addCriterion("frozepay not in", values, "frozepay");
            return (Criteria) this;
        }

        public Criteria andFrozepayBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("frozepay between", value1, value2, "frozepay");
            return (Criteria) this;
        }

        public Criteria andFrozepayNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("frozepay not between", value1, value2, "frozepay");
            return (Criteria) this;
        }

        public Criteria andServerpayIsNull() {
            addCriterion("serverpay is null");
            return (Criteria) this;
        }

        public Criteria andServerpayIsNotNull() {
            addCriterion("serverpay is not null");
            return (Criteria) this;
        }

        public Criteria andServerpayEqualTo(BigDecimal value) {
            addCriterion("serverpay =", value, "serverpay");
            return (Criteria) this;
        }

        public Criteria andServerpayNotEqualTo(BigDecimal value) {
            addCriterion("serverpay <>", value, "serverpay");
            return (Criteria) this;
        }

        public Criteria andServerpayGreaterThan(BigDecimal value) {
            addCriterion("serverpay >", value, "serverpay");
            return (Criteria) this;
        }

        public Criteria andServerpayGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("serverpay >=", value, "serverpay");
            return (Criteria) this;
        }

        public Criteria andServerpayLessThan(BigDecimal value) {
            addCriterion("serverpay <", value, "serverpay");
            return (Criteria) this;
        }

        public Criteria andServerpayLessThanOrEqualTo(BigDecimal value) {
            addCriterion("serverpay <=", value, "serverpay");
            return (Criteria) this;
        }

        public Criteria andServerpayIn(List<BigDecimal> values) {
            addCriterion("serverpay in", values, "serverpay");
            return (Criteria) this;
        }

        public Criteria andServerpayNotIn(List<BigDecimal> values) {
            addCriterion("serverpay not in", values, "serverpay");
            return (Criteria) this;
        }

        public Criteria andServerpayBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("serverpay between", value1, value2, "serverpay");
            return (Criteria) this;
        }

        public Criteria andServerpayNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("serverpay not between", value1, value2, "serverpay");
            return (Criteria) this;
        }

        public Criteria andYuuuidIsNull() {
            addCriterion("yuuuid is null");
            return (Criteria) this;
        }

        public Criteria andYuuuidIsNotNull() {
            addCriterion("yuuuid is not null");
            return (Criteria) this;
        }

        public Criteria andYuuuidEqualTo(String value) {
            addCriterion("yuuuid =", value, "yuuuid");
            return (Criteria) this;
        }

        public Criteria andYuuuidNotEqualTo(String value) {
            addCriterion("yuuuid <>", value, "yuuuid");
            return (Criteria) this;
        }

        public Criteria andYuuuidGreaterThan(String value) {
            addCriterion("yuuuid >", value, "yuuuid");
            return (Criteria) this;
        }

        public Criteria andYuuuidGreaterThanOrEqualTo(String value) {
            addCriterion("yuuuid >=", value, "yuuuid");
            return (Criteria) this;
        }

        public Criteria andYuuuidLessThan(String value) {
            addCriterion("yuuuid <", value, "yuuuid");
            return (Criteria) this;
        }

        public Criteria andYuuuidLessThanOrEqualTo(String value) {
            addCriterion("yuuuid <=", value, "yuuuid");
            return (Criteria) this;
        }

        public Criteria andYuuuidLike(String value) {
            addCriterion("yuuuid like", value, "yuuuid");
            return (Criteria) this;
        }

        public Criteria andYuuuidNotLike(String value) {
            addCriterion("yuuuid not like", value, "yuuuid");
            return (Criteria) this;
        }

        public Criteria andYuuuidIn(List<String> values) {
            addCriterion("yuuuid in", values, "yuuuid");
            return (Criteria) this;
        }

        public Criteria andYuuuidNotIn(List<String> values) {
            addCriterion("yuuuid not in", values, "yuuuid");
            return (Criteria) this;
        }

        public Criteria andYuuuidBetween(String value1, String value2) {
            addCriterion("yuuuid between", value1, value2, "yuuuid");
            return (Criteria) this;
        }

        public Criteria andYuuuidNotBetween(String value1, String value2) {
            addCriterion("yuuuid not between", value1, value2, "yuuuid");
            return (Criteria) this;
        }

        public Criteria andDelaydaysIsNull() {
            addCriterion("delaydays is null");
            return (Criteria) this;
        }

        public Criteria andDelaydaysIsNotNull() {
            addCriterion("delaydays is not null");
            return (Criteria) this;
        }

        public Criteria andDelaydaysEqualTo(Integer value) {
            addCriterion("delaydays =", value, "delaydays");
            return (Criteria) this;
        }

        public Criteria andDelaydaysNotEqualTo(Integer value) {
            addCriterion("delaydays <>", value, "delaydays");
            return (Criteria) this;
        }

        public Criteria andDelaydaysGreaterThan(Integer value) {
            addCriterion("delaydays >", value, "delaydays");
            return (Criteria) this;
        }

        public Criteria andDelaydaysGreaterThanOrEqualTo(Integer value) {
            addCriterion("delaydays >=", value, "delaydays");
            return (Criteria) this;
        }

        public Criteria andDelaydaysLessThan(Integer value) {
            addCriterion("delaydays <", value, "delaydays");
            return (Criteria) this;
        }

        public Criteria andDelaydaysLessThanOrEqualTo(Integer value) {
            addCriterion("delaydays <=", value, "delaydays");
            return (Criteria) this;
        }

        public Criteria andDelaydaysIn(List<Integer> values) {
            addCriterion("delaydays in", values, "delaydays");
            return (Criteria) this;
        }

        public Criteria andDelaydaysNotIn(List<Integer> values) {
            addCriterion("delaydays not in", values, "delaydays");
            return (Criteria) this;
        }

        public Criteria andDelaydaysBetween(Integer value1, Integer value2) {
            addCriterion("delaydays between", value1, value2, "delaydays");
            return (Criteria) this;
        }

        public Criteria andDelaydaysNotBetween(Integer value1, Integer value2) {
            addCriterion("delaydays not between", value1, value2, "delaydays");
            return (Criteria) this;
        }

        public Criteria andIfdelayIsNull() {
            addCriterion("ifdelay is null");
            return (Criteria) this;
        }

        public Criteria andIfdelayIsNotNull() {
            addCriterion("ifdelay is not null");
            return (Criteria) this;
        }

        public Criteria andIfdelayEqualTo(Short value) {
            addCriterion("ifdelay =", value, "ifdelay");
            return (Criteria) this;
        }

        public Criteria andIfdelayNotEqualTo(Short value) {
            addCriterion("ifdelay <>", value, "ifdelay");
            return (Criteria) this;
        }

        public Criteria andIfdelayGreaterThan(Short value) {
            addCriterion("ifdelay >", value, "ifdelay");
            return (Criteria) this;
        }

        public Criteria andIfdelayGreaterThanOrEqualTo(Short value) {
            addCriterion("ifdelay >=", value, "ifdelay");
            return (Criteria) this;
        }

        public Criteria andIfdelayLessThan(Short value) {
            addCriterion("ifdelay <", value, "ifdelay");
            return (Criteria) this;
        }

        public Criteria andIfdelayLessThanOrEqualTo(Short value) {
            addCriterion("ifdelay <=", value, "ifdelay");
            return (Criteria) this;
        }

        public Criteria andIfdelayIn(List<Short> values) {
            addCriterion("ifdelay in", values, "ifdelay");
            return (Criteria) this;
        }

        public Criteria andIfdelayNotIn(List<Short> values) {
            addCriterion("ifdelay not in", values, "ifdelay");
            return (Criteria) this;
        }

        public Criteria andIfdelayBetween(Short value1, Short value2) {
            addCriterion("ifdelay between", value1, value2, "ifdelay");
            return (Criteria) this;
        }

        public Criteria andIfdelayNotBetween(Short value1, Short value2) {
            addCriterion("ifdelay not between", value1, value2, "ifdelay");
            return (Criteria) this;
        }

        public Criteria andDelaypenaltyIsNull() {
            addCriterion("delaypenalty is null");
            return (Criteria) this;
        }

        public Criteria andDelaypenaltyIsNotNull() {
            addCriterion("delaypenalty is not null");
            return (Criteria) this;
        }

        public Criteria andDelaypenaltyEqualTo(BigDecimal value) {
            addCriterion("delaypenalty =", value, "delaypenalty");
            return (Criteria) this;
        }

        public Criteria andDelaypenaltyNotEqualTo(BigDecimal value) {
            addCriterion("delaypenalty <>", value, "delaypenalty");
            return (Criteria) this;
        }

        public Criteria andDelaypenaltyGreaterThan(BigDecimal value) {
            addCriterion("delaypenalty >", value, "delaypenalty");
            return (Criteria) this;
        }

        public Criteria andDelaypenaltyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("delaypenalty >=", value, "delaypenalty");
            return (Criteria) this;
        }

        public Criteria andDelaypenaltyLessThan(BigDecimal value) {
            addCriterion("delaypenalty <", value, "delaypenalty");
            return (Criteria) this;
        }

        public Criteria andDelaypenaltyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("delaypenalty <=", value, "delaypenalty");
            return (Criteria) this;
        }

        public Criteria andDelaypenaltyIn(List<BigDecimal> values) {
            addCriterion("delaypenalty in", values, "delaypenalty");
            return (Criteria) this;
        }

        public Criteria andDelaypenaltyNotIn(List<BigDecimal> values) {
            addCriterion("delaypenalty not in", values, "delaypenalty");
            return (Criteria) this;
        }

        public Criteria andDelaypenaltyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("delaypenalty between", value1, value2, "delaypenalty");
            return (Criteria) this;
        }

        public Criteria andDelaypenaltyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("delaypenalty not between", value1, value2, "delaypenalty");
            return (Criteria) this;
        }

        public Criteria andOrdertimeIsNull() {
            addCriterion("ordertime is null");
            return (Criteria) this;
        }

        public Criteria andOrdertimeIsNotNull() {
            addCriterion("ordertime is not null");
            return (Criteria) this;
        }

        public Criteria andOrdertimeEqualTo(Long value) {
            addCriterion("ordertime =", value, "ordertime");
            return (Criteria) this;
        }

        public Criteria andOrdertimeNotEqualTo(Long value) {
            addCriterion("ordertime <>", value, "ordertime");
            return (Criteria) this;
        }

        public Criteria andOrdertimeGreaterThan(Long value) {
            addCriterion("ordertime >", value, "ordertime");
            return (Criteria) this;
        }

        public Criteria andOrdertimeGreaterThanOrEqualTo(Long value) {
            addCriterion("ordertime >=", value, "ordertime");
            return (Criteria) this;
        }

        public Criteria andOrdertimeLessThan(Long value) {
            addCriterion("ordertime <", value, "ordertime");
            return (Criteria) this;
        }

        public Criteria andOrdertimeLessThanOrEqualTo(Long value) {
            addCriterion("ordertime <=", value, "ordertime");
            return (Criteria) this;
        }

        public Criteria andOrdertimeIn(List<Long> values) {
            addCriterion("ordertime in", values, "ordertime");
            return (Criteria) this;
        }

        public Criteria andOrdertimeNotIn(List<Long> values) {
            addCriterion("ordertime not in", values, "ordertime");
            return (Criteria) this;
        }

        public Criteria andOrdertimeBetween(Long value1, Long value2) {
            addCriterion("ordertime between", value1, value2, "ordertime");
            return (Criteria) this;
        }

        public Criteria andOrdertimeNotBetween(Long value1, Long value2) {
            addCriterion("ordertime not between", value1, value2, "ordertime");
            return (Criteria) this;
        }

        public Criteria andYuordertimeIsNull() {
            addCriterion("yuordertime is null");
            return (Criteria) this;
        }

        public Criteria andYuordertimeIsNotNull() {
            addCriterion("yuordertime is not null");
            return (Criteria) this;
        }

        public Criteria andYuordertimeEqualTo(Long value) {
            addCriterion("yuordertime =", value, "yuordertime");
            return (Criteria) this;
        }

        public Criteria andYuordertimeNotEqualTo(Long value) {
            addCriterion("yuordertime <>", value, "yuordertime");
            return (Criteria) this;
        }

        public Criteria andYuordertimeGreaterThan(Long value) {
            addCriterion("yuordertime >", value, "yuordertime");
            return (Criteria) this;
        }

        public Criteria andYuordertimeGreaterThanOrEqualTo(Long value) {
            addCriterion("yuordertime >=", value, "yuordertime");
            return (Criteria) this;
        }

        public Criteria andYuordertimeLessThan(Long value) {
            addCriterion("yuordertime <", value, "yuordertime");
            return (Criteria) this;
        }

        public Criteria andYuordertimeLessThanOrEqualTo(Long value) {
            addCriterion("yuordertime <=", value, "yuordertime");
            return (Criteria) this;
        }

        public Criteria andYuordertimeIn(List<Long> values) {
            addCriterion("yuordertime in", values, "yuordertime");
            return (Criteria) this;
        }

        public Criteria andYuordertimeNotIn(List<Long> values) {
            addCriterion("yuordertime not in", values, "yuordertime");
            return (Criteria) this;
        }

        public Criteria andYuordertimeBetween(Long value1, Long value2) {
            addCriterion("yuordertime between", value1, value2, "yuordertime");
            return (Criteria) this;
        }

        public Criteria andYuordertimeNotBetween(Long value1, Long value2) {
            addCriterion("yuordertime not between", value1, value2, "yuordertime");
            return (Criteria) this;
        }

        public Criteria andDeliverybillIsNull() {
            addCriterion("deliverybill is null");
            return (Criteria) this;
        }

        public Criteria andDeliverybillIsNotNull() {
            addCriterion("deliverybill is not null");
            return (Criteria) this;
        }

        public Criteria andDeliverybillEqualTo(Short value) {
            addCriterion("deliverybill =", value, "deliverybill");
            return (Criteria) this;
        }

        public Criteria andDeliverybillNotEqualTo(Short value) {
            addCriterion("deliverybill <>", value, "deliverybill");
            return (Criteria) this;
        }

        public Criteria andDeliverybillGreaterThan(Short value) {
            addCriterion("deliverybill >", value, "deliverybill");
            return (Criteria) this;
        }

        public Criteria andDeliverybillGreaterThanOrEqualTo(Short value) {
            addCriterion("deliverybill >=", value, "deliverybill");
            return (Criteria) this;
        }

        public Criteria andDeliverybillLessThan(Short value) {
            addCriterion("deliverybill <", value, "deliverybill");
            return (Criteria) this;
        }

        public Criteria andDeliverybillLessThanOrEqualTo(Short value) {
            addCriterion("deliverybill <=", value, "deliverybill");
            return (Criteria) this;
        }

        public Criteria andDeliverybillIn(List<Short> values) {
            addCriterion("deliverybill in", values, "deliverybill");
            return (Criteria) this;
        }

        public Criteria andDeliverybillNotIn(List<Short> values) {
            addCriterion("deliverybill not in", values, "deliverybill");
            return (Criteria) this;
        }

        public Criteria andDeliverybillBetween(Short value1, Short value2) {
            addCriterion("deliverybill between", value1, value2, "deliverybill");
            return (Criteria) this;
        }

        public Criteria andDeliverybillNotBetween(Short value1, Short value2) {
            addCriterion("deliverybill not between", value1, value2, "deliverybill");
            return (Criteria) this;
        }

        public Criteria andTransactionidIsNull() {
            addCriterion("transactionid is null");
            return (Criteria) this;
        }

        public Criteria andTransactionidIsNotNull() {
            addCriterion("transactionid is not null");
            return (Criteria) this;
        }

        public Criteria andTransactionidEqualTo(String value) {
            addCriterion("transactionid =", value, "transactionid");
            return (Criteria) this;
        }

        public Criteria andTransactionidNotEqualTo(String value) {
            addCriterion("transactionid <>", value, "transactionid");
            return (Criteria) this;
        }

        public Criteria andTransactionidGreaterThan(String value) {
            addCriterion("transactionid >", value, "transactionid");
            return (Criteria) this;
        }

        public Criteria andTransactionidGreaterThanOrEqualTo(String value) {
            addCriterion("transactionid >=", value, "transactionid");
            return (Criteria) this;
        }

        public Criteria andTransactionidLessThan(String value) {
            addCriterion("transactionid <", value, "transactionid");
            return (Criteria) this;
        }

        public Criteria andTransactionidLessThanOrEqualTo(String value) {
            addCriterion("transactionid <=", value, "transactionid");
            return (Criteria) this;
        }

        public Criteria andTransactionidLike(String value) {
            addCriterion("transactionid like", value, "transactionid");
            return (Criteria) this;
        }

        public Criteria andTransactionidNotLike(String value) {
            addCriterion("transactionid not like", value, "transactionid");
            return (Criteria) this;
        }

        public Criteria andTransactionidIn(List<String> values) {
            addCriterion("transactionid in", values, "transactionid");
            return (Criteria) this;
        }

        public Criteria andTransactionidNotIn(List<String> values) {
            addCriterion("transactionid not in", values, "transactionid");
            return (Criteria) this;
        }

        public Criteria andTransactionidBetween(String value1, String value2) {
            addCriterion("transactionid between", value1, value2, "transactionid");
            return (Criteria) this;
        }

        public Criteria andTransactionidNotBetween(String value1, String value2) {
            addCriterion("transactionid not between", value1, value2, "transactionid");
            return (Criteria) this;
        }

        public Criteria andReasonIsNull() {
            addCriterion("reason is null");
            return (Criteria) this;
        }

        public Criteria andReasonIsNotNull() {
            addCriterion("reason is not null");
            return (Criteria) this;
        }

        public Criteria andReasonEqualTo(String value) {
            addCriterion("reason =", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotEqualTo(String value) {
            addCriterion("reason <>", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThan(String value) {
            addCriterion("reason >", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThanOrEqualTo(String value) {
            addCriterion("reason >=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThan(String value) {
            addCriterion("reason <", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThanOrEqualTo(String value) {
            addCriterion("reason <=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLike(String value) {
            addCriterion("reason like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotLike(String value) {
            addCriterion("reason not like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonIn(List<String> values) {
            addCriterion("reason in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotIn(List<String> values) {
            addCriterion("reason not in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonBetween(String value1, String value2) {
            addCriterion("reason between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotBetween(String value1, String value2) {
            addCriterion("reason not between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andDeliverytypeIsNull() {
            addCriterion("deliverytype is null");
            return (Criteria) this;
        }

        public Criteria andDeliverytypeIsNotNull() {
            addCriterion("deliverytype is not null");
            return (Criteria) this;
        }

        public Criteria andDeliverytypeEqualTo(Short value) {
            addCriterion("deliverytype =", value, "deliverytype");
            return (Criteria) this;
        }

        public Criteria andDeliverytypeNotEqualTo(Short value) {
            addCriterion("deliverytype <>", value, "deliverytype");
            return (Criteria) this;
        }

        public Criteria andDeliverytypeGreaterThan(Short value) {
            addCriterion("deliverytype >", value, "deliverytype");
            return (Criteria) this;
        }

        public Criteria andDeliverytypeGreaterThanOrEqualTo(Short value) {
            addCriterion("deliverytype >=", value, "deliverytype");
            return (Criteria) this;
        }

        public Criteria andDeliverytypeLessThan(Short value) {
            addCriterion("deliverytype <", value, "deliverytype");
            return (Criteria) this;
        }

        public Criteria andDeliverytypeLessThanOrEqualTo(Short value) {
            addCriterion("deliverytype <=", value, "deliverytype");
            return (Criteria) this;
        }

        public Criteria andDeliverytypeIn(List<Short> values) {
            addCriterion("deliverytype in", values, "deliverytype");
            return (Criteria) this;
        }

        public Criteria andDeliverytypeNotIn(List<Short> values) {
            addCriterion("deliverytype not in", values, "deliverytype");
            return (Criteria) this;
        }

        public Criteria andDeliverytypeBetween(Short value1, Short value2) {
            addCriterion("deliverytype between", value1, value2, "deliverytype");
            return (Criteria) this;
        }

        public Criteria andDeliverytypeNotBetween(Short value1, Short value2) {
            addCriterion("deliverytype not between", value1, value2, "deliverytype");
            return (Criteria) this;
        }

        public Criteria andBilltoserverIsNull() {
            addCriterion("billtoserver is null");
            return (Criteria) this;
        }

        public Criteria andBilltoserverIsNotNull() {
            addCriterion("billtoserver is not null");
            return (Criteria) this;
        }

        public Criteria andBilltoserverEqualTo(Short value) {
            addCriterion("billtoserver =", value, "billtoserver");
            return (Criteria) this;
        }

        public Criteria andBilltoserverNotEqualTo(Short value) {
            addCriterion("billtoserver <>", value, "billtoserver");
            return (Criteria) this;
        }

        public Criteria andBilltoserverGreaterThan(Short value) {
            addCriterion("billtoserver >", value, "billtoserver");
            return (Criteria) this;
        }

        public Criteria andBilltoserverGreaterThanOrEqualTo(Short value) {
            addCriterion("billtoserver >=", value, "billtoserver");
            return (Criteria) this;
        }

        public Criteria andBilltoserverLessThan(Short value) {
            addCriterion("billtoserver <", value, "billtoserver");
            return (Criteria) this;
        }

        public Criteria andBilltoserverLessThanOrEqualTo(Short value) {
            addCriterion("billtoserver <=", value, "billtoserver");
            return (Criteria) this;
        }

        public Criteria andBilltoserverIn(List<Short> values) {
            addCriterion("billtoserver in", values, "billtoserver");
            return (Criteria) this;
        }

        public Criteria andBilltoserverNotIn(List<Short> values) {
            addCriterion("billtoserver not in", values, "billtoserver");
            return (Criteria) this;
        }

        public Criteria andBilltoserverBetween(Short value1, Short value2) {
            addCriterion("billtoserver between", value1, value2, "billtoserver");
            return (Criteria) this;
        }

        public Criteria andBilltoserverNotBetween(Short value1, Short value2) {
            addCriterion("billtoserver not between", value1, value2, "billtoserver");
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