package project.jinshang.mod_fx.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FxcirculationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FxcirculationExample() {
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

        public Criteria andCommisionidIsNull() {
            addCriterion("commisionid is null");
            return (Criteria) this;
        }

        public Criteria andCommisionidIsNotNull() {
            addCriterion("commisionid is not null");
            return (Criteria) this;
        }

        public Criteria andCommisionidEqualTo(Long value) {
            addCriterion("commisionid =", value, "commisionid");
            return (Criteria) this;
        }

        public Criteria andCommisionidNotEqualTo(Long value) {
            addCriterion("commisionid <>", value, "commisionid");
            return (Criteria) this;
        }

        public Criteria andCommisionidGreaterThan(Long value) {
            addCriterion("commisionid >", value, "commisionid");
            return (Criteria) this;
        }

        public Criteria andCommisionidGreaterThanOrEqualTo(Long value) {
            addCriterion("commisionid >=", value, "commisionid");
            return (Criteria) this;
        }

        public Criteria andCommisionidLessThan(Long value) {
            addCriterion("commisionid <", value, "commisionid");
            return (Criteria) this;
        }

        public Criteria andCommisionidLessThanOrEqualTo(Long value) {
            addCriterion("commisionid <=", value, "commisionid");
            return (Criteria) this;
        }

        public Criteria andCommisionidIn(List<Long> values) {
            addCriterion("commisionid in", values, "commisionid");
            return (Criteria) this;
        }

        public Criteria andCommisionidNotIn(List<Long> values) {
            addCriterion("commisionid not in", values, "commisionid");
            return (Criteria) this;
        }

        public Criteria andCommisionidBetween(Long value1, Long value2) {
            addCriterion("commisionid between", value1, value2, "commisionid");
            return (Criteria) this;
        }

        public Criteria andCommisionidNotBetween(Long value1, Long value2) {
            addCriterion("commisionid not between", value1, value2, "commisionid");
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

        public Criteria andCirculationtextIsNull() {
            addCriterion("circulationtext is null");
            return (Criteria) this;
        }

        public Criteria andCirculationtextIsNotNull() {
            addCriterion("circulationtext is not null");
            return (Criteria) this;
        }

        public Criteria andCirculationtextEqualTo(String value) {
            addCriterion("circulationtext =", value, "circulationtext");
            return (Criteria) this;
        }

        public Criteria andCirculationtextNotEqualTo(String value) {
            addCriterion("circulationtext <>", value, "circulationtext");
            return (Criteria) this;
        }

        public Criteria andCirculationtextGreaterThan(String value) {
            addCriterion("circulationtext >", value, "circulationtext");
            return (Criteria) this;
        }

        public Criteria andCirculationtextGreaterThanOrEqualTo(String value) {
            addCriterion("circulationtext >=", value, "circulationtext");
            return (Criteria) this;
        }

        public Criteria andCirculationtextLessThan(String value) {
            addCriterion("circulationtext <", value, "circulationtext");
            return (Criteria) this;
        }

        public Criteria andCirculationtextLessThanOrEqualTo(String value) {
            addCriterion("circulationtext <=", value, "circulationtext");
            return (Criteria) this;
        }

        public Criteria andCirculationtextLike(String value) {
            addCriterion("circulationtext like", value, "circulationtext");
            return (Criteria) this;
        }

        public Criteria andCirculationtextNotLike(String value) {
            addCriterion("circulationtext not like", value, "circulationtext");
            return (Criteria) this;
        }

        public Criteria andCirculationtextIn(List<String> values) {
            addCriterion("circulationtext in", values, "circulationtext");
            return (Criteria) this;
        }

        public Criteria andCirculationtextNotIn(List<String> values) {
            addCriterion("circulationtext not in", values, "circulationtext");
            return (Criteria) this;
        }

        public Criteria andCirculationtextBetween(String value1, String value2) {
            addCriterion("circulationtext between", value1, value2, "circulationtext");
            return (Criteria) this;
        }

        public Criteria andCirculationtextNotBetween(String value1, String value2) {
            addCriterion("circulationtext not between", value1, value2, "circulationtext");
            return (Criteria) this;
        }

        public Criteria andCirculationoldIsNull() {
            addCriterion("circulationold is null");
            return (Criteria) this;
        }

        public Criteria andCirculationoldIsNotNull() {
            addCriterion("circulationold is not null");
            return (Criteria) this;
        }

        public Criteria andCirculationoldEqualTo(Long value) {
            addCriterion("circulationold =", value, "circulationold");
            return (Criteria) this;
        }

        public Criteria andCirculationoldNotEqualTo(Long value) {
            addCriterion("circulationold <>", value, "circulationold");
            return (Criteria) this;
        }

        public Criteria andCirculationoldGreaterThan(Long value) {
            addCriterion("circulationold >", value, "circulationold");
            return (Criteria) this;
        }

        public Criteria andCirculationoldGreaterThanOrEqualTo(Long value) {
            addCriterion("circulationold >=", value, "circulationold");
            return (Criteria) this;
        }

        public Criteria andCirculationoldLessThan(Long value) {
            addCriterion("circulationold <", value, "circulationold");
            return (Criteria) this;
        }

        public Criteria andCirculationoldLessThanOrEqualTo(Long value) {
            addCriterion("circulationold <=", value, "circulationold");
            return (Criteria) this;
        }

        public Criteria andCirculationoldIn(List<Long> values) {
            addCriterion("circulationold in", values, "circulationold");
            return (Criteria) this;
        }

        public Criteria andCirculationoldNotIn(List<Long> values) {
            addCriterion("circulationold not in", values, "circulationold");
            return (Criteria) this;
        }

        public Criteria andCirculationoldBetween(Long value1, Long value2) {
            addCriterion("circulationold between", value1, value2, "circulationold");
            return (Criteria) this;
        }

        public Criteria andCirculationoldNotBetween(Long value1, Long value2) {
            addCriterion("circulationold not between", value1, value2, "circulationold");
            return (Criteria) this;
        }

        public Criteria andCirculationnewIsNull() {
            addCriterion("circulationnew is null");
            return (Criteria) this;
        }

        public Criteria andCirculationnewIsNotNull() {
            addCriterion("circulationnew is not null");
            return (Criteria) this;
        }

        public Criteria andCirculationnewEqualTo(Long value) {
            addCriterion("circulationnew =", value, "circulationnew");
            return (Criteria) this;
        }

        public Criteria andCirculationnewNotEqualTo(Long value) {
            addCriterion("circulationnew <>", value, "circulationnew");
            return (Criteria) this;
        }

        public Criteria andCirculationnewGreaterThan(Long value) {
            addCriterion("circulationnew >", value, "circulationnew");
            return (Criteria) this;
        }

        public Criteria andCirculationnewGreaterThanOrEqualTo(Long value) {
            addCriterion("circulationnew >=", value, "circulationnew");
            return (Criteria) this;
        }

        public Criteria andCirculationnewLessThan(Long value) {
            addCriterion("circulationnew <", value, "circulationnew");
            return (Criteria) this;
        }

        public Criteria andCirculationnewLessThanOrEqualTo(Long value) {
            addCriterion("circulationnew <=", value, "circulationnew");
            return (Criteria) this;
        }

        public Criteria andCirculationnewIn(List<Long> values) {
            addCriterion("circulationnew in", values, "circulationnew");
            return (Criteria) this;
        }

        public Criteria andCirculationnewNotIn(List<Long> values) {
            addCriterion("circulationnew not in", values, "circulationnew");
            return (Criteria) this;
        }

        public Criteria andCirculationnewBetween(Long value1, Long value2) {
            addCriterion("circulationnew between", value1, value2, "circulationnew");
            return (Criteria) this;
        }

        public Criteria andCirculationnewNotBetween(Long value1, Long value2) {
            addCriterion("circulationnew not between", value1, value2, "circulationnew");
            return (Criteria) this;
        }

        public Criteria andOtherIsNull() {
            addCriterion("other is null");
            return (Criteria) this;
        }

        public Criteria andOtherIsNotNull() {
            addCriterion("other is not null");
            return (Criteria) this;
        }

        public Criteria andOtherEqualTo(String value) {
            addCriterion("other =", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherNotEqualTo(String value) {
            addCriterion("other <>", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherGreaterThan(String value) {
            addCriterion("other >", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherGreaterThanOrEqualTo(String value) {
            addCriterion("other >=", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherLessThan(String value) {
            addCriterion("other <", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherLessThanOrEqualTo(String value) {
            addCriterion("other <=", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherLike(String value) {
            addCriterion("other like", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherNotLike(String value) {
            addCriterion("other not like", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherIn(List<String> values) {
            addCriterion("other in", values, "other");
            return (Criteria) this;
        }

        public Criteria andOtherNotIn(List<String> values) {
            addCriterion("other not in", values, "other");
            return (Criteria) this;
        }

        public Criteria andOtherBetween(String value1, String value2) {
            addCriterion("other between", value1, value2, "other");
            return (Criteria) this;
        }

        public Criteria andOtherNotBetween(String value1, String value2) {
            addCriterion("other not between", value1, value2, "other");
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