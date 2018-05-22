package project.jinshang.mod_sellerbill.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SellerBillOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SellerBillOrderExample() {
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

        public Criteria andSellerbillidIsNull() {
            addCriterion("sellerbillid is null");
            return (Criteria) this;
        }

        public Criteria andSellerbillidIsNotNull() {
            addCriterion("sellerbillid is not null");
            return (Criteria) this;
        }

        public Criteria andSellerbillidEqualTo(Long value) {
            addCriterion("sellerbillid =", value, "sellerbillid");
            return (Criteria) this;
        }

        public Criteria andSellerbillidNotEqualTo(Long value) {
            addCriterion("sellerbillid <>", value, "sellerbillid");
            return (Criteria) this;
        }

        public Criteria andSellerbillidGreaterThan(Long value) {
            addCriterion("sellerbillid >", value, "sellerbillid");
            return (Criteria) this;
        }

        public Criteria andSellerbillidGreaterThanOrEqualTo(Long value) {
            addCriterion("sellerbillid >=", value, "sellerbillid");
            return (Criteria) this;
        }

        public Criteria andSellerbillidLessThan(Long value) {
            addCriterion("sellerbillid <", value, "sellerbillid");
            return (Criteria) this;
        }

        public Criteria andSellerbillidLessThanOrEqualTo(Long value) {
            addCriterion("sellerbillid <=", value, "sellerbillid");
            return (Criteria) this;
        }

        public Criteria andSellerbillidIn(List<Long> values) {
            addCriterion("sellerbillid in", values, "sellerbillid");
            return (Criteria) this;
        }

        public Criteria andSellerbillidNotIn(List<Long> values) {
            addCriterion("sellerbillid not in", values, "sellerbillid");
            return (Criteria) this;
        }

        public Criteria andSellerbillidBetween(Long value1, Long value2) {
            addCriterion("sellerbillid between", value1, value2, "sellerbillid");
            return (Criteria) this;
        }

        public Criteria andSellerbillidNotBetween(Long value1, Long value2) {
            addCriterion("sellerbillid not between", value1, value2, "sellerbillid");
            return (Criteria) this;
        }

        public Criteria andOrderidIsNull() {
            addCriterion("orderid is null");
            return (Criteria) this;
        }

        public Criteria andOrderidIsNotNull() {
            addCriterion("orderid is not null");
            return (Criteria) this;
        }

        public Criteria andOrderidEqualTo(Long value) {
            addCriterion("orderid =", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotEqualTo(Long value) {
            addCriterion("orderid <>", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidGreaterThan(Long value) {
            addCriterion("orderid >", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidGreaterThanOrEqualTo(Long value) {
            addCriterion("orderid >=", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidLessThan(Long value) {
            addCriterion("orderid <", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidLessThanOrEqualTo(Long value) {
            addCriterion("orderid <=", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidIn(List<Long> values) {
            addCriterion("orderid in", values, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotIn(List<Long> values) {
            addCriterion("orderid not in", values, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidBetween(Long value1, Long value2) {
            addCriterion("orderid between", value1, value2, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotBetween(Long value1, Long value2) {
            addCriterion("orderid not between", value1, value2, "orderid");
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

        public Criteria andOrdermoneyIsNull() {
            addCriterion("ordermoney is null");
            return (Criteria) this;
        }

        public Criteria andOrdermoneyIsNotNull() {
            addCriterion("ordermoney is not null");
            return (Criteria) this;
        }

        public Criteria andOrdermoneyEqualTo(BigDecimal value) {
            addCriterion("ordermoney =", value, "ordermoney");
            return (Criteria) this;
        }

        public Criteria andOrdermoneyNotEqualTo(BigDecimal value) {
            addCriterion("ordermoney <>", value, "ordermoney");
            return (Criteria) this;
        }

        public Criteria andOrdermoneyGreaterThan(BigDecimal value) {
            addCriterion("ordermoney >", value, "ordermoney");
            return (Criteria) this;
        }

        public Criteria andOrdermoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ordermoney >=", value, "ordermoney");
            return (Criteria) this;
        }

        public Criteria andOrdermoneyLessThan(BigDecimal value) {
            addCriterion("ordermoney <", value, "ordermoney");
            return (Criteria) this;
        }

        public Criteria andOrdermoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ordermoney <=", value, "ordermoney");
            return (Criteria) this;
        }

        public Criteria andOrdermoneyIn(List<BigDecimal> values) {
            addCriterion("ordermoney in", values, "ordermoney");
            return (Criteria) this;
        }

        public Criteria andOrdermoneyNotIn(List<BigDecimal> values) {
            addCriterion("ordermoney not in", values, "ordermoney");
            return (Criteria) this;
        }

        public Criteria andOrdermoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ordermoney between", value1, value2, "ordermoney");
            return (Criteria) this;
        }

        public Criteria andOrdermoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ordermoney not between", value1, value2, "ordermoney");
            return (Criteria) this;
        }

        public Criteria andBuyernameIsNull() {
            addCriterion("buyername is null");
            return (Criteria) this;
        }

        public Criteria andBuyernameIsNotNull() {
            addCriterion("buyername is not null");
            return (Criteria) this;
        }

        public Criteria andBuyernameEqualTo(String value) {
            addCriterion("buyername =", value, "buyername");
            return (Criteria) this;
        }

        public Criteria andBuyernameNotEqualTo(String value) {
            addCriterion("buyername <>", value, "buyername");
            return (Criteria) this;
        }

        public Criteria andBuyernameGreaterThan(String value) {
            addCriterion("buyername >", value, "buyername");
            return (Criteria) this;
        }

        public Criteria andBuyernameGreaterThanOrEqualTo(String value) {
            addCriterion("buyername >=", value, "buyername");
            return (Criteria) this;
        }

        public Criteria andBuyernameLessThan(String value) {
            addCriterion("buyername <", value, "buyername");
            return (Criteria) this;
        }

        public Criteria andBuyernameLessThanOrEqualTo(String value) {
            addCriterion("buyername <=", value, "buyername");
            return (Criteria) this;
        }

        public Criteria andBuyernameLike(String value) {
            addCriterion("buyername like", value, "buyername");
            return (Criteria) this;
        }

        public Criteria andBuyernameNotLike(String value) {
            addCriterion("buyername not like", value, "buyername");
            return (Criteria) this;
        }

        public Criteria andBuyernameIn(List<String> values) {
            addCriterion("buyername in", values, "buyername");
            return (Criteria) this;
        }

        public Criteria andBuyernameNotIn(List<String> values) {
            addCriterion("buyername not in", values, "buyername");
            return (Criteria) this;
        }

        public Criteria andBuyernameBetween(String value1, String value2) {
            addCriterion("buyername between", value1, value2, "buyername");
            return (Criteria) this;
        }

        public Criteria andBuyernameNotBetween(String value1, String value2) {
            addCriterion("buyername not between", value1, value2, "buyername");
            return (Criteria) this;
        }

        public Criteria andFishtimeIsNull() {
            addCriterion("fishtime is null");
            return (Criteria) this;
        }

        public Criteria andFishtimeIsNotNull() {
            addCriterion("fishtime is not null");
            return (Criteria) this;
        }

        public Criteria andFishtimeEqualTo(Date value) {
            addCriterion("fishtime =", value, "fishtime");
            return (Criteria) this;
        }

        public Criteria andFishtimeNotEqualTo(Date value) {
            addCriterion("fishtime <>", value, "fishtime");
            return (Criteria) this;
        }

        public Criteria andFishtimeGreaterThan(Date value) {
            addCriterion("fishtime >", value, "fishtime");
            return (Criteria) this;
        }

        public Criteria andFishtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("fishtime >=", value, "fishtime");
            return (Criteria) this;
        }

        public Criteria andFishtimeLessThan(Date value) {
            addCriterion("fishtime <", value, "fishtime");
            return (Criteria) this;
        }

        public Criteria andFishtimeLessThanOrEqualTo(Date value) {
            addCriterion("fishtime <=", value, "fishtime");
            return (Criteria) this;
        }

        public Criteria andFishtimeIn(List<Date> values) {
            addCriterion("fishtime in", values, "fishtime");
            return (Criteria) this;
        }

        public Criteria andFishtimeNotIn(List<Date> values) {
            addCriterion("fishtime not in", values, "fishtime");
            return (Criteria) this;
        }

        public Criteria andFishtimeBetween(Date value1, Date value2) {
            addCriterion("fishtime between", value1, value2, "fishtime");
            return (Criteria) this;
        }

        public Criteria andFishtimeNotBetween(Date value1, Date value2) {
            addCriterion("fishtime not between", value1, value2, "fishtime");
            return (Criteria) this;
        }

        public Criteria andBreakpaymoneyIsNull() {
            addCriterion("breakpaymoney is null");
            return (Criteria) this;
        }

        public Criteria andBreakpaymoneyIsNotNull() {
            addCriterion("breakpaymoney is not null");
            return (Criteria) this;
        }

        public Criteria andBreakpaymoneyEqualTo(BigDecimal value) {
            addCriterion("breakpaymoney =", value, "breakpaymoney");
            return (Criteria) this;
        }

        public Criteria andBreakpaymoneyNotEqualTo(BigDecimal value) {
            addCriterion("breakpaymoney <>", value, "breakpaymoney");
            return (Criteria) this;
        }

        public Criteria andBreakpaymoneyGreaterThan(BigDecimal value) {
            addCriterion("breakpaymoney >", value, "breakpaymoney");
            return (Criteria) this;
        }

        public Criteria andBreakpaymoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("breakpaymoney >=", value, "breakpaymoney");
            return (Criteria) this;
        }

        public Criteria andBreakpaymoneyLessThan(BigDecimal value) {
            addCriterion("breakpaymoney <", value, "breakpaymoney");
            return (Criteria) this;
        }

        public Criteria andBreakpaymoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("breakpaymoney <=", value, "breakpaymoney");
            return (Criteria) this;
        }

        public Criteria andBreakpaymoneyIn(List<BigDecimal> values) {
            addCriterion("breakpaymoney in", values, "breakpaymoney");
            return (Criteria) this;
        }

        public Criteria andBreakpaymoneyNotIn(List<BigDecimal> values) {
            addCriterion("breakpaymoney not in", values, "breakpaymoney");
            return (Criteria) this;
        }

        public Criteria andBreakpaymoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("breakpaymoney between", value1, value2, "breakpaymoney");
            return (Criteria) this;
        }

        public Criteria andBreakpaymoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("breakpaymoney not between", value1, value2, "breakpaymoney");
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