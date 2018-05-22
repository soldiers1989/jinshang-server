package project.jinshang.mod_checkstore.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductStoreCheckExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProductStoreCheckExample() {
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

        public Criteria andStoresiteIsNull() {
            addCriterion("storesite is null");
            return (Criteria) this;
        }

        public Criteria andStoresiteIsNotNull() {
            addCriterion("storesite is not null");
            return (Criteria) this;
        }

        public Criteria andStoresiteEqualTo(String value) {
            addCriterion("storesite =", value, "storesite");
            return (Criteria) this;
        }

        public Criteria andStoresiteNotEqualTo(String value) {
            addCriterion("storesite <>", value, "storesite");
            return (Criteria) this;
        }

        public Criteria andStoresiteGreaterThan(String value) {
            addCriterion("storesite >", value, "storesite");
            return (Criteria) this;
        }

        public Criteria andStoresiteGreaterThanOrEqualTo(String value) {
            addCriterion("storesite >=", value, "storesite");
            return (Criteria) this;
        }

        public Criteria andStoresiteLessThan(String value) {
            addCriterion("storesite <", value, "storesite");
            return (Criteria) this;
        }

        public Criteria andStoresiteLessThanOrEqualTo(String value) {
            addCriterion("storesite <=", value, "storesite");
            return (Criteria) this;
        }

        public Criteria andStoresiteLike(String value) {
            addCriterion("storesite like", value, "storesite");
            return (Criteria) this;
        }

        public Criteria andStoresiteNotLike(String value) {
            addCriterion("storesite not like", value, "storesite");
            return (Criteria) this;
        }

        public Criteria andStoresiteIn(List<String> values) {
            addCriterion("storesite in", values, "storesite");
            return (Criteria) this;
        }

        public Criteria andStoresiteNotIn(List<String> values) {
            addCriterion("storesite not in", values, "storesite");
            return (Criteria) this;
        }

        public Criteria andStoresiteBetween(String value1, String value2) {
            addCriterion("storesite between", value1, value2, "storesite");
            return (Criteria) this;
        }

        public Criteria andStoresiteNotBetween(String value1, String value2) {
            addCriterion("storesite not between", value1, value2, "storesite");
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

        public Criteria andStorenumIsNull() {
            addCriterion("storenum is null");
            return (Criteria) this;
        }

        public Criteria andStorenumIsNotNull() {
            addCriterion("storenum is not null");
            return (Criteria) this;
        }

        public Criteria andStorenumEqualTo(BigDecimal value) {
            addCriterion("storenum =", value, "storenum");
            return (Criteria) this;
        }

        public Criteria andStorenumNotEqualTo(BigDecimal value) {
            addCriterion("storenum <>", value, "storenum");
            return (Criteria) this;
        }

        public Criteria andStorenumGreaterThan(BigDecimal value) {
            addCriterion("storenum >", value, "storenum");
            return (Criteria) this;
        }

        public Criteria andStorenumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("storenum >=", value, "storenum");
            return (Criteria) this;
        }

        public Criteria andStorenumLessThan(BigDecimal value) {
            addCriterion("storenum <", value, "storenum");
            return (Criteria) this;
        }

        public Criteria andStorenumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("storenum <=", value, "storenum");
            return (Criteria) this;
        }

        public Criteria andStorenumIn(List<BigDecimal> values) {
            addCriterion("storenum in", values, "storenum");
            return (Criteria) this;
        }

        public Criteria andStorenumNotIn(List<BigDecimal> values) {
            addCriterion("storenum not in", values, "storenum");
            return (Criteria) this;
        }

        public Criteria andStorenumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("storenum between", value1, value2, "storenum");
            return (Criteria) this;
        }

        public Criteria andStorenumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("storenum not between", value1, value2, "storenum");
            return (Criteria) this;
        }

        public Criteria andCheckuserIsNull() {
            addCriterion("checkuser is null");
            return (Criteria) this;
        }

        public Criteria andCheckuserIsNotNull() {
            addCriterion("checkuser is not null");
            return (Criteria) this;
        }

        public Criteria andCheckuserEqualTo(String value) {
            addCriterion("checkuser =", value, "checkuser");
            return (Criteria) this;
        }

        public Criteria andCheckuserNotEqualTo(String value) {
            addCriterion("checkuser <>", value, "checkuser");
            return (Criteria) this;
        }

        public Criteria andCheckuserGreaterThan(String value) {
            addCriterion("checkuser >", value, "checkuser");
            return (Criteria) this;
        }

        public Criteria andCheckuserGreaterThanOrEqualTo(String value) {
            addCriterion("checkuser >=", value, "checkuser");
            return (Criteria) this;
        }

        public Criteria andCheckuserLessThan(String value) {
            addCriterion("checkuser <", value, "checkuser");
            return (Criteria) this;
        }

        public Criteria andCheckuserLessThanOrEqualTo(String value) {
            addCriterion("checkuser <=", value, "checkuser");
            return (Criteria) this;
        }

        public Criteria andCheckuserLike(String value) {
            addCriterion("checkuser like", value, "checkuser");
            return (Criteria) this;
        }

        public Criteria andCheckuserNotLike(String value) {
            addCriterion("checkuser not like", value, "checkuser");
            return (Criteria) this;
        }

        public Criteria andCheckuserIn(List<String> values) {
            addCriterion("checkuser in", values, "checkuser");
            return (Criteria) this;
        }

        public Criteria andCheckuserNotIn(List<String> values) {
            addCriterion("checkuser not in", values, "checkuser");
            return (Criteria) this;
        }

        public Criteria andCheckuserBetween(String value1, String value2) {
            addCriterion("checkuser between", value1, value2, "checkuser");
            return (Criteria) this;
        }

        public Criteria andCheckuserNotBetween(String value1, String value2) {
            addCriterion("checkuser not between", value1, value2, "checkuser");
            return (Criteria) this;
        }

        public Criteria andChecktimeIsNull() {
            addCriterion("checktime is null");
            return (Criteria) this;
        }

        public Criteria andChecktimeIsNotNull() {
            addCriterion("checktime is not null");
            return (Criteria) this;
        }

        public Criteria andChecktimeEqualTo(Date value) {
            addCriterion("checktime =", value, "checktime");
            return (Criteria) this;
        }

        public Criteria andChecktimeNotEqualTo(Date value) {
            addCriterion("checktime <>", value, "checktime");
            return (Criteria) this;
        }

        public Criteria andChecktimeGreaterThan(Date value) {
            addCriterion("checktime >", value, "checktime");
            return (Criteria) this;
        }

        public Criteria andChecktimeGreaterThanOrEqualTo(Date value) {
            addCriterion("checktime >=", value, "checktime");
            return (Criteria) this;
        }

        public Criteria andChecktimeLessThan(Date value) {
            addCriterion("checktime <", value, "checktime");
            return (Criteria) this;
        }

        public Criteria andChecktimeLessThanOrEqualTo(Date value) {
            addCriterion("checktime <=", value, "checktime");
            return (Criteria) this;
        }

        public Criteria andChecktimeIn(List<Date> values) {
            addCriterion("checktime in", values, "checktime");
            return (Criteria) this;
        }

        public Criteria andChecktimeNotIn(List<Date> values) {
            addCriterion("checktime not in", values, "checktime");
            return (Criteria) this;
        }

        public Criteria andChecktimeBetween(Date value1, Date value2) {
            addCriterion("checktime between", value1, value2, "checktime");
            return (Criteria) this;
        }

        public Criteria andChecktimeNotBetween(Date value1, Date value2) {
            addCriterion("checktime not between", value1, value2, "checktime");
            return (Criteria) this;
        }

        public Criteria andValidateuserIsNull() {
            addCriterion("validateuser is null");
            return (Criteria) this;
        }

        public Criteria andValidateuserIsNotNull() {
            addCriterion("validateuser is not null");
            return (Criteria) this;
        }

        public Criteria andValidateuserEqualTo(String value) {
            addCriterion("validateuser =", value, "validateuser");
            return (Criteria) this;
        }

        public Criteria andValidateuserNotEqualTo(String value) {
            addCriterion("validateuser <>", value, "validateuser");
            return (Criteria) this;
        }

        public Criteria andValidateuserGreaterThan(String value) {
            addCriterion("validateuser >", value, "validateuser");
            return (Criteria) this;
        }

        public Criteria andValidateuserGreaterThanOrEqualTo(String value) {
            addCriterion("validateuser >=", value, "validateuser");
            return (Criteria) this;
        }

        public Criteria andValidateuserLessThan(String value) {
            addCriterion("validateuser <", value, "validateuser");
            return (Criteria) this;
        }

        public Criteria andValidateuserLessThanOrEqualTo(String value) {
            addCriterion("validateuser <=", value, "validateuser");
            return (Criteria) this;
        }

        public Criteria andValidateuserLike(String value) {
            addCriterion("validateuser like", value, "validateuser");
            return (Criteria) this;
        }

        public Criteria andValidateuserNotLike(String value) {
            addCriterion("validateuser not like", value, "validateuser");
            return (Criteria) this;
        }

        public Criteria andValidateuserIn(List<String> values) {
            addCriterion("validateuser in", values, "validateuser");
            return (Criteria) this;
        }

        public Criteria andValidateuserNotIn(List<String> values) {
            addCriterion("validateuser not in", values, "validateuser");
            return (Criteria) this;
        }

        public Criteria andValidateuserBetween(String value1, String value2) {
            addCriterion("validateuser between", value1, value2, "validateuser");
            return (Criteria) this;
        }

        public Criteria andValidateuserNotBetween(String value1, String value2) {
            addCriterion("validateuser not between", value1, value2, "validateuser");
            return (Criteria) this;
        }

        public Criteria andValidatetimeIsNull() {
            addCriterion("validatetime is null");
            return (Criteria) this;
        }

        public Criteria andValidatetimeIsNotNull() {
            addCriterion("validatetime is not null");
            return (Criteria) this;
        }

        public Criteria andValidatetimeEqualTo(Date value) {
            addCriterion("validatetime =", value, "validatetime");
            return (Criteria) this;
        }

        public Criteria andValidatetimeNotEqualTo(Date value) {
            addCriterion("validatetime <>", value, "validatetime");
            return (Criteria) this;
        }

        public Criteria andValidatetimeGreaterThan(Date value) {
            addCriterion("validatetime >", value, "validatetime");
            return (Criteria) this;
        }

        public Criteria andValidatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("validatetime >=", value, "validatetime");
            return (Criteria) this;
        }

        public Criteria andValidatetimeLessThan(Date value) {
            addCriterion("validatetime <", value, "validatetime");
            return (Criteria) this;
        }

        public Criteria andValidatetimeLessThanOrEqualTo(Date value) {
            addCriterion("validatetime <=", value, "validatetime");
            return (Criteria) this;
        }

        public Criteria andValidatetimeIn(List<Date> values) {
            addCriterion("validatetime in", values, "validatetime");
            return (Criteria) this;
        }

        public Criteria andValidatetimeNotIn(List<Date> values) {
            addCriterion("validatetime not in", values, "validatetime");
            return (Criteria) this;
        }

        public Criteria andValidatetimeBetween(Date value1, Date value2) {
            addCriterion("validatetime between", value1, value2, "validatetime");
            return (Criteria) this;
        }

        public Criteria andValidatetimeNotBetween(Date value1, Date value2) {
            addCriterion("validatetime not between", value1, value2, "validatetime");
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