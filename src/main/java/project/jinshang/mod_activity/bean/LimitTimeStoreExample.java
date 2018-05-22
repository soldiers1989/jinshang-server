package project.jinshang.mod_activity.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class LimitTimeStoreExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LimitTimeStoreExample() {
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

        public Criteria andLimittimeidIsNull() {
            addCriterion("limittimeid is null");
            return (Criteria) this;
        }

        public Criteria andLimittimeidIsNotNull() {
            addCriterion("limittimeid is not null");
            return (Criteria) this;
        }

        public Criteria andLimittimeidEqualTo(Long value) {
            addCriterion("limittimeid =", value, "limittimeid");
            return (Criteria) this;
        }

        public Criteria andLimittimeidNotEqualTo(Long value) {
            addCriterion("limittimeid <>", value, "limittimeid");
            return (Criteria) this;
        }

        public Criteria andLimittimeidGreaterThan(Long value) {
            addCriterion("limittimeid >", value, "limittimeid");
            return (Criteria) this;
        }

        public Criteria andLimittimeidGreaterThanOrEqualTo(Long value) {
            addCriterion("limittimeid >=", value, "limittimeid");
            return (Criteria) this;
        }

        public Criteria andLimittimeidLessThan(Long value) {
            addCriterion("limittimeid <", value, "limittimeid");
            return (Criteria) this;
        }

        public Criteria andLimittimeidLessThanOrEqualTo(Long value) {
            addCriterion("limittimeid <=", value, "limittimeid");
            return (Criteria) this;
        }

        public Criteria andLimittimeidIn(List<Long> values) {
            addCriterion("limittimeid in", values, "limittimeid");
            return (Criteria) this;
        }

        public Criteria andLimittimeidNotIn(List<Long> values) {
            addCriterion("limittimeid not in", values, "limittimeid");
            return (Criteria) this;
        }

        public Criteria andLimittimeidBetween(Long value1, Long value2) {
            addCriterion("limittimeid between", value1, value2, "limittimeid");
            return (Criteria) this;
        }

        public Criteria andLimittimeidNotBetween(Long value1, Long value2) {
            addCriterion("limittimeid not between", value1, value2, "limittimeid");
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

        public Criteria andOriginalpriceIsNull() {
            addCriterion("originalprice is null");
            return (Criteria) this;
        }

        public Criteria andOriginalpriceIsNotNull() {
            addCriterion("originalprice is not null");
            return (Criteria) this;
        }

        public Criteria andOriginalpriceEqualTo(BigDecimal value) {
            addCriterion("originalprice =", value, "originalprice");
            return (Criteria) this;
        }

        public Criteria andOriginalpriceNotEqualTo(BigDecimal value) {
            addCriterion("originalprice <>", value, "originalprice");
            return (Criteria) this;
        }

        public Criteria andOriginalpriceGreaterThan(BigDecimal value) {
            addCriterion("originalprice >", value, "originalprice");
            return (Criteria) this;
        }

        public Criteria andOriginalpriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("originalprice >=", value, "originalprice");
            return (Criteria) this;
        }

        public Criteria andOriginalpriceLessThan(BigDecimal value) {
            addCriterion("originalprice <", value, "originalprice");
            return (Criteria) this;
        }

        public Criteria andOriginalpriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("originalprice <=", value, "originalprice");
            return (Criteria) this;
        }

        public Criteria andOriginalpriceIn(List<BigDecimal> values) {
            addCriterion("originalprice in", values, "originalprice");
            return (Criteria) this;
        }

        public Criteria andOriginalpriceNotIn(List<BigDecimal> values) {
            addCriterion("originalprice not in", values, "originalprice");
            return (Criteria) this;
        }

        public Criteria andOriginalpriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("originalprice between", value1, value2, "originalprice");
            return (Criteria) this;
        }

        public Criteria andOriginalpriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("originalprice not between", value1, value2, "originalprice");
            return (Criteria) this;
        }

        public Criteria andLimitpriceIsNull() {
            addCriterion("limitprice is null");
            return (Criteria) this;
        }

        public Criteria andLimitpriceIsNotNull() {
            addCriterion("limitprice is not null");
            return (Criteria) this;
        }

        public Criteria andLimitpriceEqualTo(BigDecimal value) {
            addCriterion("limitprice =", value, "limitprice");
            return (Criteria) this;
        }

        public Criteria andLimitpriceNotEqualTo(BigDecimal value) {
            addCriterion("limitprice <>", value, "limitprice");
            return (Criteria) this;
        }

        public Criteria andLimitpriceGreaterThan(BigDecimal value) {
            addCriterion("limitprice >", value, "limitprice");
            return (Criteria) this;
        }

        public Criteria andLimitpriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("limitprice >=", value, "limitprice");
            return (Criteria) this;
        }

        public Criteria andLimitpriceLessThan(BigDecimal value) {
            addCriterion("limitprice <", value, "limitprice");
            return (Criteria) this;
        }

        public Criteria andLimitpriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("limitprice <=", value, "limitprice");
            return (Criteria) this;
        }

        public Criteria andLimitpriceIn(List<BigDecimal> values) {
            addCriterion("limitprice in", values, "limitprice");
            return (Criteria) this;
        }

        public Criteria andLimitpriceNotIn(List<BigDecimal> values) {
            addCriterion("limitprice not in", values, "limitprice");
            return (Criteria) this;
        }

        public Criteria andLimitpriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("limitprice between", value1, value2, "limitprice");
            return (Criteria) this;
        }

        public Criteria andLimitpriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("limitprice not between", value1, value2, "limitprice");
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

        public Criteria andSalesnumIsNull() {
            addCriterion("salesnum is null");
            return (Criteria) this;
        }

        public Criteria andSalesnumIsNotNull() {
            addCriterion("salesnum is not null");
            return (Criteria) this;
        }

        public Criteria andSalesnumEqualTo(BigDecimal value) {
            addCriterion("salesnum =", value, "salesnum");
            return (Criteria) this;
        }

        public Criteria andSalesnumNotEqualTo(BigDecimal value) {
            addCriterion("salesnum <>", value, "salesnum");
            return (Criteria) this;
        }

        public Criteria andSalesnumGreaterThan(BigDecimal value) {
            addCriterion("salesnum >", value, "salesnum");
            return (Criteria) this;
        }

        public Criteria andSalesnumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("salesnum >=", value, "salesnum");
            return (Criteria) this;
        }

        public Criteria andSalesnumLessThan(BigDecimal value) {
            addCriterion("salesnum <", value, "salesnum");
            return (Criteria) this;
        }

        public Criteria andSalesnumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("salesnum <=", value, "salesnum");
            return (Criteria) this;
        }

        public Criteria andSalesnumIn(List<BigDecimal> values) {
            addCriterion("salesnum in", values, "salesnum");
            return (Criteria) this;
        }

        public Criteria andSalesnumNotIn(List<BigDecimal> values) {
            addCriterion("salesnum not in", values, "salesnum");
            return (Criteria) this;
        }

        public Criteria andSalesnumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("salesnum between", value1, value2, "salesnum");
            return (Criteria) this;
        }

        public Criteria andSalesnumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("salesnum not between", value1, value2, "salesnum");
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