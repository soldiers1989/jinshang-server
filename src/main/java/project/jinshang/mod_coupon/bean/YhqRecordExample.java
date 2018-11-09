package project.jinshang.mod_coupon.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class YhqRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public YhqRecordExample() {
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

    /**
     * null
     * 
     * @author wcyong
     * 
     * @date 2018-05-26
     */
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

        public Criteria andTicketidIsNull() {
            addCriterion("ticketid is null");
            return (Criteria) this;
        }

        public Criteria andTicketidIsNotNull() {
            addCriterion("ticketid is not null");
            return (Criteria) this;
        }

        public Criteria andTicketidEqualTo(Long value) {
            addCriterion("ticketid =", value, "ticketid");
            return (Criteria) this;
        }

        public Criteria andTicketidNotEqualTo(Long value) {
            addCriterion("ticketid <>", value, "ticketid");
            return (Criteria) this;
        }

        public Criteria andTicketidGreaterThan(Long value) {
            addCriterion("ticketid >", value, "ticketid");
            return (Criteria) this;
        }

        public Criteria andTicketidGreaterThanOrEqualTo(Long value) {
            addCriterion("ticketid >=", value, "ticketid");
            return (Criteria) this;
        }

        public Criteria andTicketidLessThan(Long value) {
            addCriterion("ticketid <", value, "ticketid");
            return (Criteria) this;
        }

        public Criteria andTicketidLessThanOrEqualTo(Long value) {
            addCriterion("ticketid <=", value, "ticketid");
            return (Criteria) this;
        }

        public Criteria andTicketidIn(List<Long> values) {
            addCriterion("ticketid in", values, "ticketid");
            return (Criteria) this;
        }

        public Criteria andTicketidNotIn(List<Long> values) {
            addCriterion("ticketid not in", values, "ticketid");
            return (Criteria) this;
        }

        public Criteria andTicketidBetween(Long value1, Long value2) {
            addCriterion("ticketid between", value1, value2, "ticketid");
            return (Criteria) this;
        }

        public Criteria andTicketidNotBetween(Long value1, Long value2) {
            addCriterion("ticketid not between", value1, value2, "ticketid");
            return (Criteria) this;
        }

        public Criteria andTicketnoIsNull() {
            addCriterion("ticketno is null");
            return (Criteria) this;
        }

        public Criteria andTicketnoIsNotNull() {
            addCriterion("ticketno is not null");
            return (Criteria) this;
        }

        public Criteria andTicketnoEqualTo(String value) {
            addCriterion("ticketno =", value, "ticketno");
            return (Criteria) this;
        }

        public Criteria andTicketnoNotEqualTo(String value) {
            addCriterion("ticketno <>", value, "ticketno");
            return (Criteria) this;
        }

        public Criteria andTicketnoGreaterThan(String value) {
            addCriterion("ticketno >", value, "ticketno");
            return (Criteria) this;
        }

        public Criteria andTicketnoGreaterThanOrEqualTo(String value) {
            addCriterion("ticketno >=", value, "ticketno");
            return (Criteria) this;
        }

        public Criteria andTicketnoLessThan(String value) {
            addCriterion("ticketno <", value, "ticketno");
            return (Criteria) this;
        }

        public Criteria andTicketnoLessThanOrEqualTo(String value) {
            addCriterion("ticketno <=", value, "ticketno");
            return (Criteria) this;
        }

        public Criteria andTicketnoLike(String value) {
            addCriterion("ticketno like", value, "ticketno");
            return (Criteria) this;
        }

        public Criteria andTicketnoNotLike(String value) {
            addCriterion("ticketno not like", value, "ticketno");
            return (Criteria) this;
        }

        public Criteria andTicketnoIn(List<String> values) {
            addCriterion("ticketno in", values, "ticketno");
            return (Criteria) this;
        }

        public Criteria andTicketnoNotIn(List<String> values) {
            addCriterion("ticketno not in", values, "ticketno");
            return (Criteria) this;
        }

        public Criteria andTicketnoBetween(String value1, String value2) {
            addCriterion("ticketno between", value1, value2, "ticketno");
            return (Criteria) this;
        }

        public Criteria andTicketnoNotBetween(String value1, String value2) {
            addCriterion("ticketno not between", value1, value2, "ticketno");
            return (Criteria) this;
        }

        public Criteria andTickettypeIsNull() {
            addCriterion("tickettype is null");
            return (Criteria) this;
        }

        public Criteria andTickettypeIsNotNull() {
            addCriterion("tickettype is not null");
            return (Criteria) this;
        }

        public Criteria andTickettypeEqualTo(Long value) {
            addCriterion("tickettype =", value, "tickettype");
            return (Criteria) this;
        }

        public Criteria andTickettypeNotEqualTo(Long value) {
            addCriterion("tickettype <>", value, "tickettype");
            return (Criteria) this;
        }

        public Criteria andTickettypeGreaterThan(Long value) {
            addCriterion("tickettype >", value, "tickettype");
            return (Criteria) this;
        }

        public Criteria andTickettypeGreaterThanOrEqualTo(Long value) {
            addCriterion("tickettype >=", value, "tickettype");
            return (Criteria) this;
        }

        public Criteria andTickettypeLessThan(Long value) {
            addCriterion("tickettype <", value, "tickettype");
            return (Criteria) this;
        }

        public Criteria andTickettypeLessThanOrEqualTo(Long value) {
            addCriterion("tickettype <=", value, "tickettype");
            return (Criteria) this;
        }

        public Criteria andTickettypeIn(List<Long> values) {
            addCriterion("tickettype in", values, "tickettype");
            return (Criteria) this;
        }

        public Criteria andTickettypeNotIn(List<Long> values) {
            addCriterion("tickettype not in", values, "tickettype");
            return (Criteria) this;
        }

        public Criteria andTickettypeBetween(Long value1, Long value2) {
            addCriterion("tickettype between", value1, value2, "tickettype");
            return (Criteria) this;
        }

        public Criteria andTickettypeNotBetween(Long value1, Long value2) {
            addCriterion("tickettype not between", value1, value2, "tickettype");
            return (Criteria) this;
        }

        public Criteria andDiscountmoneyIsNull() {
            addCriterion("discountmoney is null");
            return (Criteria) this;
        }

        public Criteria andDiscountmoneyIsNotNull() {
            addCriterion("discountmoney is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountmoneyEqualTo(BigDecimal value) {
            addCriterion("discountmoney =", value, "discountmoney");
            return (Criteria) this;
        }

        public Criteria andDiscountmoneyNotEqualTo(BigDecimal value) {
            addCriterion("discountmoney <>", value, "discountmoney");
            return (Criteria) this;
        }

        public Criteria andDiscountmoneyGreaterThan(BigDecimal value) {
            addCriterion("discountmoney >", value, "discountmoney");
            return (Criteria) this;
        }

        public Criteria andDiscountmoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("discountmoney >=", value, "discountmoney");
            return (Criteria) this;
        }

        public Criteria andDiscountmoneyLessThan(BigDecimal value) {
            addCriterion("discountmoney <", value, "discountmoney");
            return (Criteria) this;
        }

        public Criteria andDiscountmoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("discountmoney <=", value, "discountmoney");
            return (Criteria) this;
        }

        public Criteria andDiscountmoneyIn(List<BigDecimal> values) {
            addCriterion("discountmoney in", values, "discountmoney");
            return (Criteria) this;
        }

        public Criteria andDiscountmoneyNotIn(List<BigDecimal> values) {
            addCriterion("discountmoney not in", values, "discountmoney");
            return (Criteria) this;
        }

        public Criteria andDiscountmoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("discountmoney between", value1, value2, "discountmoney");
            return (Criteria) this;
        }

        public Criteria andDiscountmoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("discountmoney not between", value1, value2, "discountmoney");
            return (Criteria) this;
        }

        public Criteria andDiscountpercentIsNull() {
            addCriterion("discountpercent is null");
            return (Criteria) this;
        }

        public Criteria andDiscountpercentIsNotNull() {
            addCriterion("discountpercent is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountpercentEqualTo(BigDecimal value) {
            addCriterion("discountpercent =", value, "discountpercent");
            return (Criteria) this;
        }

        public Criteria andDiscountpercentNotEqualTo(BigDecimal value) {
            addCriterion("discountpercent <>", value, "discountpercent");
            return (Criteria) this;
        }

        public Criteria andDiscountpercentGreaterThan(BigDecimal value) {
            addCriterion("discountpercent >", value, "discountpercent");
            return (Criteria) this;
        }

        public Criteria andDiscountpercentGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("discountpercent >=", value, "discountpercent");
            return (Criteria) this;
        }

        public Criteria andDiscountpercentLessThan(BigDecimal value) {
            addCriterion("discountpercent <", value, "discountpercent");
            return (Criteria) this;
        }

        public Criteria andDiscountpercentLessThanOrEqualTo(BigDecimal value) {
            addCriterion("discountpercent <=", value, "discountpercent");
            return (Criteria) this;
        }

        public Criteria andDiscountpercentIn(List<BigDecimal> values) {
            addCriterion("discountpercent in", values, "discountpercent");
            return (Criteria) this;
        }

        public Criteria andDiscountpercentNotIn(List<BigDecimal> values) {
            addCriterion("discountpercent not in", values, "discountpercent");
            return (Criteria) this;
        }

        public Criteria andDiscountpercentBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("discountpercent between", value1, value2, "discountpercent");
            return (Criteria) this;
        }

        public Criteria andDiscountpercentNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("discountpercent not between", value1, value2, "discountpercent");
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

        public Criteria andOrdersidIsNull() {
            addCriterion("ordersid is null");
            return (Criteria) this;
        }

        public Criteria andOrdersidIsNotNull() {
            addCriterion("ordersid is not null");
            return (Criteria) this;
        }

        public Criteria andOrdersidEqualTo(Long value) {
            addCriterion("ordersid =", value, "ordersid");
            return (Criteria) this;
        }

        public Criteria andOrdersidNotEqualTo(Long value) {
            addCriterion("ordersid <>", value, "ordersid");
            return (Criteria) this;
        }

        public Criteria andOrdersidGreaterThan(Long value) {
            addCriterion("ordersid >", value, "ordersid");
            return (Criteria) this;
        }

        public Criteria andOrdersidGreaterThanOrEqualTo(Long value) {
            addCriterion("ordersid >=", value, "ordersid");
            return (Criteria) this;
        }

        public Criteria andOrdersidLessThan(Long value) {
            addCriterion("ordersid <", value, "ordersid");
            return (Criteria) this;
        }

        public Criteria andOrdersidLessThanOrEqualTo(Long value) {
            addCriterion("ordersid <=", value, "ordersid");
            return (Criteria) this;
        }

        public Criteria andOrdersidIn(List<Long> values) {
            addCriterion("ordersid in", values, "ordersid");
            return (Criteria) this;
        }

        public Criteria andOrdersidNotIn(List<Long> values) {
            addCriterion("ordersid not in", values, "ordersid");
            return (Criteria) this;
        }

        public Criteria andOrdersidBetween(Long value1, Long value2) {
            addCriterion("ordersid between", value1, value2, "ordersid");
            return (Criteria) this;
        }

        public Criteria andOrdersidNotBetween(Long value1, Long value2) {
            addCriterion("ordersid not between", value1, value2, "ordersid");
            return (Criteria) this;
        }

        public Criteria andOrdersnoIsNull() {
            addCriterion("ordersno is null");
            return (Criteria) this;
        }

        public Criteria andOrdersnoIsNotNull() {
            addCriterion("ordersno is not null");
            return (Criteria) this;
        }

        public Criteria andOrdersnoEqualTo(String value) {
            addCriterion("ordersno =", value, "ordersno");
            return (Criteria) this;
        }

        public Criteria andOrdersnoNotEqualTo(String value) {
            addCriterion("ordersno <>", value, "ordersno");
            return (Criteria) this;
        }

        public Criteria andOrdersnoGreaterThan(String value) {
            addCriterion("ordersno >", value, "ordersno");
            return (Criteria) this;
        }

        public Criteria andOrdersnoGreaterThanOrEqualTo(String value) {
            addCriterion("ordersno >=", value, "ordersno");
            return (Criteria) this;
        }

        public Criteria andOrdersnoLessThan(String value) {
            addCriterion("ordersno <", value, "ordersno");
            return (Criteria) this;
        }

        public Criteria andOrdersnoLessThanOrEqualTo(String value) {
            addCriterion("ordersno <=", value, "ordersno");
            return (Criteria) this;
        }

        public Criteria andOrdersnoLike(String value) {
            addCriterion("ordersno like", value, "ordersno");
            return (Criteria) this;
        }

        public Criteria andOrdersnoNotLike(String value) {
            addCriterion("ordersno not like", value, "ordersno");
            return (Criteria) this;
        }

        public Criteria andOrdersnoIn(List<String> values) {
            addCriterion("ordersno in", values, "ordersno");
            return (Criteria) this;
        }

        public Criteria andOrdersnoNotIn(List<String> values) {
            addCriterion("ordersno not in", values, "ordersno");
            return (Criteria) this;
        }

        public Criteria andOrdersnoBetween(String value1, String value2) {
            addCriterion("ordersno between", value1, value2, "ordersno");
            return (Criteria) this;
        }

        public Criteria andOrdersnoNotBetween(String value1, String value2) {
            addCriterion("ordersno not between", value1, value2, "ordersno");
            return (Criteria) this;
        }

        public Criteria andUsetimeIsNull() {
            addCriterion("usetime is null");
            return (Criteria) this;
        }

        public Criteria andUsetimeIsNotNull() {
            addCriterion("usetime is not null");
            return (Criteria) this;
        }

        public Criteria andUsetimeEqualTo(Date value) {
            addCriterion("usetime =", value, "usetime");
            return (Criteria) this;
        }

        public Criteria andUsetimeNotEqualTo(Date value) {
            addCriterion("usetime <>", value, "usetime");
            return (Criteria) this;
        }

        public Criteria andUsetimeGreaterThan(Date value) {
            addCriterion("usetime >", value, "usetime");
            return (Criteria) this;
        }

        public Criteria andUsetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("usetime >=", value, "usetime");
            return (Criteria) this;
        }

        public Criteria andUsetimeLessThan(Date value) {
            addCriterion("usetime <", value, "usetime");
            return (Criteria) this;
        }

        public Criteria andUsetimeLessThanOrEqualTo(Date value) {
            addCriterion("usetime <=", value, "usetime");
            return (Criteria) this;
        }

        public Criteria andUsetimeIn(List<Date> values) {
            addCriterion("usetime in", values, "usetime");
            return (Criteria) this;
        }

        public Criteria andUsetimeNotIn(List<Date> values) {
            addCriterion("usetime not in", values, "usetime");
            return (Criteria) this;
        }

        public Criteria andUsetimeBetween(Date value1, Date value2) {
            addCriterion("usetime between", value1, value2, "usetime");
            return (Criteria) this;
        }

        public Criteria andUsetimeNotBetween(Date value1, Date value2) {
            addCriterion("usetime not between", value1, value2, "usetime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * null
     * 
     * @author wcyong
     * 
     * @date 2018-05-26
     */
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