package project.jinshang.mod_fx.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FxstationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FxstationExample() {
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

        public Criteria andCycleIsNull() {
            addCriterion("cycle is null");
            return (Criteria) this;
        }

        public Criteria andCycleIsNotNull() {
            addCriterion("cycle is not null");
            return (Criteria) this;
        }

        public Criteria andCycleEqualTo(Long value) {
            addCriterion("cycle =", value, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleNotEqualTo(Long value) {
            addCriterion("cycle <>", value, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleGreaterThan(Long value) {
            addCriterion("cycle >", value, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleGreaterThanOrEqualTo(Long value) {
            addCriterion("cycle >=", value, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleLessThan(Long value) {
            addCriterion("cycle <", value, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleLessThanOrEqualTo(Long value) {
            addCriterion("cycle <=", value, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleIn(List<Long> values) {
            addCriterion("cycle in", values, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleNotIn(List<Long> values) {
            addCriterion("cycle not in", values, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleBetween(Long value1, Long value2) {
            addCriterion("cycle between", value1, value2, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleNotBetween(Long value1, Long value2) {
            addCriterion("cycle not between", value1, value2, "cycle");
            return (Criteria) this;
        }

        public Criteria andRatio2IsNull() {
            addCriterion("ratio2 is null");
            return (Criteria) this;
        }

        public Criteria andRatio2IsNotNull() {
            addCriterion("ratio2 is not null");
            return (Criteria) this;
        }

        public Criteria andRatio2EqualTo(BigDecimal value) {
            addCriterion("ratio2 =", value, "ratio2");
            return (Criteria) this;
        }

        public Criteria andRatio2NotEqualTo(BigDecimal value) {
            addCriterion("ratio2 <>", value, "ratio2");
            return (Criteria) this;
        }

        public Criteria andRatio2GreaterThan(BigDecimal value) {
            addCriterion("ratio2 >", value, "ratio2");
            return (Criteria) this;
        }

        public Criteria andRatio2GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ratio2 >=", value, "ratio2");
            return (Criteria) this;
        }

        public Criteria andRatio2LessThan(BigDecimal value) {
            addCriterion("ratio2 <", value, "ratio2");
            return (Criteria) this;
        }

        public Criteria andRatio2LessThanOrEqualTo(BigDecimal value) {
            addCriterion("ratio2 <=", value, "ratio2");
            return (Criteria) this;
        }

        public Criteria andRatio2In(List<BigDecimal> values) {
            addCriterion("ratio2 in", values, "ratio2");
            return (Criteria) this;
        }

        public Criteria andRatio2NotIn(List<BigDecimal> values) {
            addCriterion("ratio2 not in", values, "ratio2");
            return (Criteria) this;
        }

        public Criteria andRatio2Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("ratio2 between", value1, value2, "ratio2");
            return (Criteria) this;
        }

        public Criteria andRatio2NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ratio2 not between", value1, value2, "ratio2");
            return (Criteria) this;
        }

        public Criteria andDisableIsNull() {
            addCriterion("disable is null");
            return (Criteria) this;
        }

        public Criteria andDisableIsNotNull() {
            addCriterion("disable is not null");
            return (Criteria) this;
        }

        public Criteria andDisableEqualTo(Boolean value) {
            addCriterion("disable =", value, "disable");
            return (Criteria) this;
        }

        public Criteria andDisableNotEqualTo(Boolean value) {
            addCriterion("disable <>", value, "disable");
            return (Criteria) this;
        }

        public Criteria andDisableGreaterThan(Boolean value) {
            addCriterion("disable >", value, "disable");
            return (Criteria) this;
        }

        public Criteria andDisableGreaterThanOrEqualTo(Boolean value) {
            addCriterion("disable >=", value, "disable");
            return (Criteria) this;
        }

        public Criteria andDisableLessThan(Boolean value) {
            addCriterion("disable <", value, "disable");
            return (Criteria) this;
        }

        public Criteria andDisableLessThanOrEqualTo(Boolean value) {
            addCriterion("disable <=", value, "disable");
            return (Criteria) this;
        }

        public Criteria andDisableIn(List<Boolean> values) {
            addCriterion("disable in", values, "disable");
            return (Criteria) this;
        }

        public Criteria andDisableNotIn(List<Boolean> values) {
            addCriterion("disable not in", values, "disable");
            return (Criteria) this;
        }

        public Criteria andDisableBetween(Boolean value1, Boolean value2) {
            addCriterion("disable between", value1, value2, "disable");
            return (Criteria) this;
        }

        public Criteria andDisableNotBetween(Boolean value1, Boolean value2) {
            addCriterion("disable not between", value1, value2, "disable");
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