package project.jinshang.mod_admin.mod_top.bean;

import java.util.ArrayList;
import java.util.List;

public class TopActivityProductExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TopActivityProductExample() {
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

        public Criteria andActivityidIsNull() {
            addCriterion("activityid is null");
            return (Criteria) this;
        }

        public Criteria andActivityidIsNotNull() {
            addCriterion("activityid is not null");
            return (Criteria) this;
        }

        public Criteria andActivityidEqualTo(Long value) {
            addCriterion("activityid =", value, "activityid");
            return (Criteria) this;
        }

        public Criteria andActivityidNotEqualTo(Long value) {
            addCriterion("activityid <>", value, "activityid");
            return (Criteria) this;
        }

        public Criteria andActivityidGreaterThan(Long value) {
            addCriterion("activityid >", value, "activityid");
            return (Criteria) this;
        }

        public Criteria andActivityidGreaterThanOrEqualTo(Long value) {
            addCriterion("activityid >=", value, "activityid");
            return (Criteria) this;
        }

        public Criteria andActivityidLessThan(Long value) {
            addCriterion("activityid <", value, "activityid");
            return (Criteria) this;
        }

        public Criteria andActivityidLessThanOrEqualTo(Long value) {
            addCriterion("activityid <=", value, "activityid");
            return (Criteria) this;
        }

        public Criteria andActivityidIn(List<Long> values) {
            addCriterion("activityid in", values, "activityid");
            return (Criteria) this;
        }

        public Criteria andActivityidNotIn(List<Long> values) {
            addCriterion("activityid not in", values, "activityid");
            return (Criteria) this;
        }

        public Criteria andActivityidBetween(Long value1, Long value2) {
            addCriterion("activityid between", value1, value2, "activityid");
            return (Criteria) this;
        }

        public Criteria andActivityidNotBetween(Long value1, Long value2) {
            addCriterion("activityid not between", value1, value2, "activityid");
            return (Criteria) this;
        }

        public Criteria andTopidIsNull() {
            addCriterion("topid is null");
            return (Criteria) this;
        }

        public Criteria andTopidIsNotNull() {
            addCriterion("topid is not null");
            return (Criteria) this;
        }

        public Criteria andTopidEqualTo(Long value) {
            addCriterion("topid =", value, "topid");
            return (Criteria) this;
        }

        public Criteria andTopidNotEqualTo(Long value) {
            addCriterion("topid <>", value, "topid");
            return (Criteria) this;
        }

        public Criteria andTopidGreaterThan(Long value) {
            addCriterion("topid >", value, "topid");
            return (Criteria) this;
        }

        public Criteria andTopidGreaterThanOrEqualTo(Long value) {
            addCriterion("topid >=", value, "topid");
            return (Criteria) this;
        }

        public Criteria andTopidLessThan(Long value) {
            addCriterion("topid <", value, "topid");
            return (Criteria) this;
        }

        public Criteria andTopidLessThanOrEqualTo(Long value) {
            addCriterion("topid <=", value, "topid");
            return (Criteria) this;
        }

        public Criteria andTopidIn(List<Long> values) {
            addCriterion("topid in", values, "topid");
            return (Criteria) this;
        }

        public Criteria andTopidNotIn(List<Long> values) {
            addCriterion("topid not in", values, "topid");
            return (Criteria) this;
        }

        public Criteria andTopidBetween(Long value1, Long value2) {
            addCriterion("topid between", value1, value2, "topid");
            return (Criteria) this;
        }

        public Criteria andTopidNotBetween(Long value1, Long value2) {
            addCriterion("topid not between", value1, value2, "topid");
            return (Criteria) this;
        }

        public Criteria andActivitytypeIsNull() {
            addCriterion("activitytype is null");
            return (Criteria) this;
        }

        public Criteria andActivitytypeIsNotNull() {
            addCriterion("activitytype is not null");
            return (Criteria) this;
        }

        public Criteria andActivitytypeEqualTo(Short value) {
            addCriterion("activitytype =", value, "activitytype");
            return (Criteria) this;
        }

        public Criteria andActivitytypeNotEqualTo(Short value) {
            addCriterion("activitytype <>", value, "activitytype");
            return (Criteria) this;
        }

        public Criteria andActivitytypeGreaterThan(Short value) {
            addCriterion("activitytype >", value, "activitytype");
            return (Criteria) this;
        }

        public Criteria andActivitytypeGreaterThanOrEqualTo(Short value) {
            addCriterion("activitytype >=", value, "activitytype");
            return (Criteria) this;
        }

        public Criteria andActivitytypeLessThan(Short value) {
            addCriterion("activitytype <", value, "activitytype");
            return (Criteria) this;
        }

        public Criteria andActivitytypeLessThanOrEqualTo(Short value) {
            addCriterion("activitytype <=", value, "activitytype");
            return (Criteria) this;
        }

        public Criteria andActivitytypeIn(List<Short> values) {
            addCriterion("activitytype in", values, "activitytype");
            return (Criteria) this;
        }

        public Criteria andActivitytypeNotIn(List<Short> values) {
            addCriterion("activitytype not in", values, "activitytype");
            return (Criteria) this;
        }

        public Criteria andActivitytypeBetween(Short value1, Short value2) {
            addCriterion("activitytype between", value1, value2, "activitytype");
            return (Criteria) this;
        }

        public Criteria andActivitytypeNotBetween(Short value1, Short value2) {
            addCriterion("activitytype not between", value1, value2, "activitytype");
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