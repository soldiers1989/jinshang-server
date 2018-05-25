package project.jinshang.mod_fx.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FxmoneylistExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FxmoneylistExample() {
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
     * @date 2018-05-05
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

        public Criteria andListtypeIsNull() {
            addCriterion("listtype is null");
            return (Criteria) this;
        }

        public Criteria andListtypeIsNotNull() {
            addCriterion("listtype is not null");
            return (Criteria) this;
        }

        public Criteria andListtypeEqualTo(Long value) {
            addCriterion("listtype =", value, "listtype");
            return (Criteria) this;
        }

        public Criteria andListtypeNotEqualTo(Long value) {
            addCriterion("listtype <>", value, "listtype");
            return (Criteria) this;
        }

        public Criteria andListtypeGreaterThan(Long value) {
            addCriterion("listtype >", value, "listtype");
            return (Criteria) this;
        }

        public Criteria andListtypeGreaterThanOrEqualTo(Long value) {
            addCriterion("listtype >=", value, "listtype");
            return (Criteria) this;
        }

        public Criteria andListtypeLessThan(Long value) {
            addCriterion("listtype <", value, "listtype");
            return (Criteria) this;
        }

        public Criteria andListtypeLessThanOrEqualTo(Long value) {
            addCriterion("listtype <=", value, "listtype");
            return (Criteria) this;
        }

        public Criteria andListtypeIn(List<Long> values) {
            addCriterion("listtype in", values, "listtype");
            return (Criteria) this;
        }

        public Criteria andListtypeNotIn(List<Long> values) {
            addCriterion("listtype not in", values, "listtype");
            return (Criteria) this;
        }

        public Criteria andListtypeBetween(Long value1, Long value2) {
            addCriterion("listtype between", value1, value2, "listtype");
            return (Criteria) this;
        }

        public Criteria andListtypeNotBetween(Long value1, Long value2) {
            addCriterion("listtype not between", value1, value2, "listtype");
            return (Criteria) this;
        }

        public Criteria andListtextIsNull() {
            addCriterion("listtext is null");
            return (Criteria) this;
        }

        public Criteria andListtextIsNotNull() {
            addCriterion("listtext is not null");
            return (Criteria) this;
        }

        public Criteria andListtextEqualTo(String value) {
            addCriterion("listtext =", value, "listtext");
            return (Criteria) this;
        }

        public Criteria andListtextNotEqualTo(String value) {
            addCriterion("listtext <>", value, "listtext");
            return (Criteria) this;
        }

        public Criteria andListtextGreaterThan(String value) {
            addCriterion("listtext >", value, "listtext");
            return (Criteria) this;
        }

        public Criteria andListtextGreaterThanOrEqualTo(String value) {
            addCriterion("listtext >=", value, "listtext");
            return (Criteria) this;
        }

        public Criteria andListtextLessThan(String value) {
            addCriterion("listtext <", value, "listtext");
            return (Criteria) this;
        }

        public Criteria andListtextLessThanOrEqualTo(String value) {
            addCriterion("listtext <=", value, "listtext");
            return (Criteria) this;
        }

        public Criteria andListtextLike(String value) {
            addCriterion("listtext like", value, "listtext");
            return (Criteria) this;
        }

        public Criteria andListtextNotLike(String value) {
            addCriterion("listtext not like", value, "listtext");
            return (Criteria) this;
        }

        public Criteria andListtextIn(List<String> values) {
            addCriterion("listtext in", values, "listtext");
            return (Criteria) this;
        }

        public Criteria andListtextNotIn(List<String> values) {
            addCriterion("listtext not in", values, "listtext");
            return (Criteria) this;
        }

        public Criteria andListtextBetween(String value1, String value2) {
            addCriterion("listtext between", value1, value2, "listtext");
            return (Criteria) this;
        }

        public Criteria andListtextNotBetween(String value1, String value2) {
            addCriterion("listtext not between", value1, value2, "listtext");
            return (Criteria) this;
        }

        public Criteria andMoneynumIsNull() {
            addCriterion("moneynum is null");
            return (Criteria) this;
        }

        public Criteria andMoneynumIsNotNull() {
            addCriterion("moneynum is not null");
            return (Criteria) this;
        }

        public Criteria andMoneynumEqualTo(BigDecimal value) {
            addCriterion("moneynum =", value, "moneynum");
            return (Criteria) this;
        }

        public Criteria andMoneynumNotEqualTo(BigDecimal value) {
            addCriterion("moneynum <>", value, "moneynum");
            return (Criteria) this;
        }

        public Criteria andMoneynumGreaterThan(BigDecimal value) {
            addCriterion("moneynum >", value, "moneynum");
            return (Criteria) this;
        }

        public Criteria andMoneynumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("moneynum >=", value, "moneynum");
            return (Criteria) this;
        }

        public Criteria andMoneynumLessThan(BigDecimal value) {
            addCriterion("moneynum <", value, "moneynum");
            return (Criteria) this;
        }

        public Criteria andMoneynumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("moneynum <=", value, "moneynum");
            return (Criteria) this;
        }

        public Criteria andMoneynumIn(List<BigDecimal> values) {
            addCriterion("moneynum in", values, "moneynum");
            return (Criteria) this;
        }

        public Criteria andMoneynumNotIn(List<BigDecimal> values) {
            addCriterion("moneynum not in", values, "moneynum");
            return (Criteria) this;
        }

        public Criteria andMoneynumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("moneynum between", value1, value2, "moneynum");
            return (Criteria) this;
        }

        public Criteria andMoneynumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("moneynum not between", value1, value2, "moneynum");
            return (Criteria) this;
        }

        public Criteria andMoneytotalIsNull() {
            addCriterion("moneytotal is null");
            return (Criteria) this;
        }

        public Criteria andMoneytotalIsNotNull() {
            addCriterion("moneytotal is not null");
            return (Criteria) this;
        }

        public Criteria andMoneytotalEqualTo(BigDecimal value) {
            addCriterion("moneytotal =", value, "moneytotal");
            return (Criteria) this;
        }

        public Criteria andMoneytotalNotEqualTo(BigDecimal value) {
            addCriterion("moneytotal <>", value, "moneytotal");
            return (Criteria) this;
        }

        public Criteria andMoneytotalGreaterThan(BigDecimal value) {
            addCriterion("moneytotal >", value, "moneytotal");
            return (Criteria) this;
        }

        public Criteria andMoneytotalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("moneytotal >=", value, "moneytotal");
            return (Criteria) this;
        }

        public Criteria andMoneytotalLessThan(BigDecimal value) {
            addCriterion("moneytotal <", value, "moneytotal");
            return (Criteria) this;
        }

        public Criteria andMoneytotalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("moneytotal <=", value, "moneytotal");
            return (Criteria) this;
        }

        public Criteria andMoneytotalIn(List<BigDecimal> values) {
            addCriterion("moneytotal in", values, "moneytotal");
            return (Criteria) this;
        }

        public Criteria andMoneytotalNotIn(List<BigDecimal> values) {
            addCriterion("moneytotal not in", values, "moneytotal");
            return (Criteria) this;
        }

        public Criteria andMoneytotalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("moneytotal between", value1, value2, "moneytotal");
            return (Criteria) this;
        }

        public Criteria andMoneytotalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("moneytotal not between", value1, value2, "moneytotal");
            return (Criteria) this;
        }

        public Criteria andMoneyprocessedIsNull() {
            addCriterion("moneyprocessed is null");
            return (Criteria) this;
        }

        public Criteria andMoneyprocessedIsNotNull() {
            addCriterion("moneyprocessed is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyprocessedEqualTo(BigDecimal value) {
            addCriterion("moneyprocessed =", value, "moneyprocessed");
            return (Criteria) this;
        }

        public Criteria andMoneyprocessedNotEqualTo(BigDecimal value) {
            addCriterion("moneyprocessed <>", value, "moneyprocessed");
            return (Criteria) this;
        }

        public Criteria andMoneyprocessedGreaterThan(BigDecimal value) {
            addCriterion("moneyprocessed >", value, "moneyprocessed");
            return (Criteria) this;
        }

        public Criteria andMoneyprocessedGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("moneyprocessed >=", value, "moneyprocessed");
            return (Criteria) this;
        }

        public Criteria andMoneyprocessedLessThan(BigDecimal value) {
            addCriterion("moneyprocessed <", value, "moneyprocessed");
            return (Criteria) this;
        }

        public Criteria andMoneyprocessedLessThanOrEqualTo(BigDecimal value) {
            addCriterion("moneyprocessed <=", value, "moneyprocessed");
            return (Criteria) this;
        }

        public Criteria andMoneyprocessedIn(List<BigDecimal> values) {
            addCriterion("moneyprocessed in", values, "moneyprocessed");
            return (Criteria) this;
        }

        public Criteria andMoneyprocessedNotIn(List<BigDecimal> values) {
            addCriterion("moneyprocessed not in", values, "moneyprocessed");
            return (Criteria) this;
        }

        public Criteria andMoneyprocessedBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("moneyprocessed between", value1, value2, "moneyprocessed");
            return (Criteria) this;
        }

        public Criteria andMoneyprocessedNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("moneyprocessed not between", value1, value2, "moneyprocessed");
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
     * @date 2018-05-05
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