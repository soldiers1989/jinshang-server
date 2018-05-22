package project.jinshang.mod_admin.mod_station.bean;

import java.util.ArrayList;
import java.util.List;

public class StationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StationExample() {
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

        public Criteria andStnameIsNull() {
            addCriterion("stname is null");
            return (Criteria) this;
        }

        public Criteria andStnameIsNotNull() {
            addCriterion("stname is not null");
            return (Criteria) this;
        }

        public Criteria andStnameEqualTo(String value) {
            addCriterion("stname =", value, "stname");
            return (Criteria) this;
        }

        public Criteria andStnameNotEqualTo(String value) {
            addCriterion("stname <>", value, "stname");
            return (Criteria) this;
        }

        public Criteria andStnameGreaterThan(String value) {
            addCriterion("stname >", value, "stname");
            return (Criteria) this;
        }

        public Criteria andStnameGreaterThanOrEqualTo(String value) {
            addCriterion("stname >=", value, "stname");
            return (Criteria) this;
        }

        public Criteria andStnameLessThan(String value) {
            addCriterion("stname <", value, "stname");
            return (Criteria) this;
        }

        public Criteria andStnameLessThanOrEqualTo(String value) {
            addCriterion("stname <=", value, "stname");
            return (Criteria) this;
        }

        public Criteria andStnameLike(String value) {
            addCriterion("stname like", value, "stname");
            return (Criteria) this;
        }

        public Criteria andStnameNotLike(String value) {
            addCriterion("stname not like", value, "stname");
            return (Criteria) this;
        }

        public Criteria andStnameIn(List<String> values) {
            addCriterion("stname in", values, "stname");
            return (Criteria) this;
        }

        public Criteria andStnameNotIn(List<String> values) {
            addCriterion("stname not in", values, "stname");
            return (Criteria) this;
        }

        public Criteria andStnameBetween(String value1, String value2) {
            addCriterion("stname between", value1, value2, "stname");
            return (Criteria) this;
        }

        public Criteria andStnameNotBetween(String value1, String value2) {
            addCriterion("stname not between", value1, value2, "stname");
            return (Criteria) this;
        }

        public Criteria andSttitleIsNull() {
            addCriterion("sttitle is null");
            return (Criteria) this;
        }

        public Criteria andSttitleIsNotNull() {
            addCriterion("sttitle is not null");
            return (Criteria) this;
        }

        public Criteria andSttitleEqualTo(String value) {
            addCriterion("sttitle =", value, "sttitle");
            return (Criteria) this;
        }

        public Criteria andSttitleNotEqualTo(String value) {
            addCriterion("sttitle <>", value, "sttitle");
            return (Criteria) this;
        }

        public Criteria andSttitleGreaterThan(String value) {
            addCriterion("sttitle >", value, "sttitle");
            return (Criteria) this;
        }

        public Criteria andSttitleGreaterThanOrEqualTo(String value) {
            addCriterion("sttitle >=", value, "sttitle");
            return (Criteria) this;
        }

        public Criteria andSttitleLessThan(String value) {
            addCriterion("sttitle <", value, "sttitle");
            return (Criteria) this;
        }

        public Criteria andSttitleLessThanOrEqualTo(String value) {
            addCriterion("sttitle <=", value, "sttitle");
            return (Criteria) this;
        }

        public Criteria andSttitleLike(String value) {
            addCriterion("sttitle like", value, "sttitle");
            return (Criteria) this;
        }

        public Criteria andSttitleNotLike(String value) {
            addCriterion("sttitle not like", value, "sttitle");
            return (Criteria) this;
        }

        public Criteria andSttitleIn(List<String> values) {
            addCriterion("sttitle in", values, "sttitle");
            return (Criteria) this;
        }

        public Criteria andSttitleNotIn(List<String> values) {
            addCriterion("sttitle not in", values, "sttitle");
            return (Criteria) this;
        }

        public Criteria andSttitleBetween(String value1, String value2) {
            addCriterion("sttitle between", value1, value2, "sttitle");
            return (Criteria) this;
        }

        public Criteria andSttitleNotBetween(String value1, String value2) {
            addCriterion("sttitle not between", value1, value2, "sttitle");
            return (Criteria) this;
        }

        public Criteria andStkeywordIsNull() {
            addCriterion("stkeyword is null");
            return (Criteria) this;
        }

        public Criteria andStkeywordIsNotNull() {
            addCriterion("stkeyword is not null");
            return (Criteria) this;
        }

        public Criteria andStkeywordEqualTo(String value) {
            addCriterion("stkeyword =", value, "stkeyword");
            return (Criteria) this;
        }

        public Criteria andStkeywordNotEqualTo(String value) {
            addCriterion("stkeyword <>", value, "stkeyword");
            return (Criteria) this;
        }

        public Criteria andStkeywordGreaterThan(String value) {
            addCriterion("stkeyword >", value, "stkeyword");
            return (Criteria) this;
        }

        public Criteria andStkeywordGreaterThanOrEqualTo(String value) {
            addCriterion("stkeyword >=", value, "stkeyword");
            return (Criteria) this;
        }

        public Criteria andStkeywordLessThan(String value) {
            addCriterion("stkeyword <", value, "stkeyword");
            return (Criteria) this;
        }

        public Criteria andStkeywordLessThanOrEqualTo(String value) {
            addCriterion("stkeyword <=", value, "stkeyword");
            return (Criteria) this;
        }

        public Criteria andStkeywordLike(String value) {
            addCriterion("stkeyword like", value, "stkeyword");
            return (Criteria) this;
        }

        public Criteria andStkeywordNotLike(String value) {
            addCriterion("stkeyword not like", value, "stkeyword");
            return (Criteria) this;
        }

        public Criteria andStkeywordIn(List<String> values) {
            addCriterion("stkeyword in", values, "stkeyword");
            return (Criteria) this;
        }

        public Criteria andStkeywordNotIn(List<String> values) {
            addCriterion("stkeyword not in", values, "stkeyword");
            return (Criteria) this;
        }

        public Criteria andStkeywordBetween(String value1, String value2) {
            addCriterion("stkeyword between", value1, value2, "stkeyword");
            return (Criteria) this;
        }

        public Criteria andStkeywordNotBetween(String value1, String value2) {
            addCriterion("stkeyword not between", value1, value2, "stkeyword");
            return (Criteria) this;
        }

        public Criteria andStdescribeIsNull() {
            addCriterion("stdescribe is null");
            return (Criteria) this;
        }

        public Criteria andStdescribeIsNotNull() {
            addCriterion("stdescribe is not null");
            return (Criteria) this;
        }

        public Criteria andStdescribeEqualTo(String value) {
            addCriterion("stdescribe =", value, "stdescribe");
            return (Criteria) this;
        }

        public Criteria andStdescribeNotEqualTo(String value) {
            addCriterion("stdescribe <>", value, "stdescribe");
            return (Criteria) this;
        }

        public Criteria andStdescribeGreaterThan(String value) {
            addCriterion("stdescribe >", value, "stdescribe");
            return (Criteria) this;
        }

        public Criteria andStdescribeGreaterThanOrEqualTo(String value) {
            addCriterion("stdescribe >=", value, "stdescribe");
            return (Criteria) this;
        }

        public Criteria andStdescribeLessThan(String value) {
            addCriterion("stdescribe <", value, "stdescribe");
            return (Criteria) this;
        }

        public Criteria andStdescribeLessThanOrEqualTo(String value) {
            addCriterion("stdescribe <=", value, "stdescribe");
            return (Criteria) this;
        }

        public Criteria andStdescribeLike(String value) {
            addCriterion("stdescribe like", value, "stdescribe");
            return (Criteria) this;
        }

        public Criteria andStdescribeNotLike(String value) {
            addCriterion("stdescribe not like", value, "stdescribe");
            return (Criteria) this;
        }

        public Criteria andStdescribeIn(List<String> values) {
            addCriterion("stdescribe in", values, "stdescribe");
            return (Criteria) this;
        }

        public Criteria andStdescribeNotIn(List<String> values) {
            addCriterion("stdescribe not in", values, "stdescribe");
            return (Criteria) this;
        }

        public Criteria andStdescribeBetween(String value1, String value2) {
            addCriterion("stdescribe between", value1, value2, "stdescribe");
            return (Criteria) this;
        }

        public Criteria andStdescribeNotBetween(String value1, String value2) {
            addCriterion("stdescribe not between", value1, value2, "stdescribe");
            return (Criteria) this;
        }

        public Criteria andStlogoIsNull() {
            addCriterion("stlogo is null");
            return (Criteria) this;
        }

        public Criteria andStlogoIsNotNull() {
            addCriterion("stlogo is not null");
            return (Criteria) this;
        }

        public Criteria andStlogoEqualTo(String value) {
            addCriterion("stlogo =", value, "stlogo");
            return (Criteria) this;
        }

        public Criteria andStlogoNotEqualTo(String value) {
            addCriterion("stlogo <>", value, "stlogo");
            return (Criteria) this;
        }

        public Criteria andStlogoGreaterThan(String value) {
            addCriterion("stlogo >", value, "stlogo");
            return (Criteria) this;
        }

        public Criteria andStlogoGreaterThanOrEqualTo(String value) {
            addCriterion("stlogo >=", value, "stlogo");
            return (Criteria) this;
        }

        public Criteria andStlogoLessThan(String value) {
            addCriterion("stlogo <", value, "stlogo");
            return (Criteria) this;
        }

        public Criteria andStlogoLessThanOrEqualTo(String value) {
            addCriterion("stlogo <=", value, "stlogo");
            return (Criteria) this;
        }

        public Criteria andStlogoLike(String value) {
            addCriterion("stlogo like", value, "stlogo");
            return (Criteria) this;
        }

        public Criteria andStlogoNotLike(String value) {
            addCriterion("stlogo not like", value, "stlogo");
            return (Criteria) this;
        }

        public Criteria andStlogoIn(List<String> values) {
            addCriterion("stlogo in", values, "stlogo");
            return (Criteria) this;
        }

        public Criteria andStlogoNotIn(List<String> values) {
            addCriterion("stlogo not in", values, "stlogo");
            return (Criteria) this;
        }

        public Criteria andStlogoBetween(String value1, String value2) {
            addCriterion("stlogo between", value1, value2, "stlogo");
            return (Criteria) this;
        }

        public Criteria andStlogoNotBetween(String value1, String value2) {
            addCriterion("stlogo not between", value1, value2, "stlogo");
            return (Criteria) this;
        }

        public Criteria andAftertimeIsNull() {
            addCriterion("aftertime is null");
            return (Criteria) this;
        }

        public Criteria andAftertimeIsNotNull() {
            addCriterion("aftertime is not null");
            return (Criteria) this;
        }

        public Criteria andAftertimeEqualTo(Integer value) {
            addCriterion("aftertime =", value, "aftertime");
            return (Criteria) this;
        }

        public Criteria andAftertimeNotEqualTo(Integer value) {
            addCriterion("aftertime <>", value, "aftertime");
            return (Criteria) this;
        }

        public Criteria andAftertimeGreaterThan(Integer value) {
            addCriterion("aftertime >", value, "aftertime");
            return (Criteria) this;
        }

        public Criteria andAftertimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("aftertime >=", value, "aftertime");
            return (Criteria) this;
        }

        public Criteria andAftertimeLessThan(Integer value) {
            addCriterion("aftertime <", value, "aftertime");
            return (Criteria) this;
        }

        public Criteria andAftertimeLessThanOrEqualTo(Integer value) {
            addCriterion("aftertime <=", value, "aftertime");
            return (Criteria) this;
        }

        public Criteria andAftertimeIn(List<Integer> values) {
            addCriterion("aftertime in", values, "aftertime");
            return (Criteria) this;
        }

        public Criteria andAftertimeNotIn(List<Integer> values) {
            addCriterion("aftertime not in", values, "aftertime");
            return (Criteria) this;
        }

        public Criteria andAftertimeBetween(Integer value1, Integer value2) {
            addCriterion("aftertime between", value1, value2, "aftertime");
            return (Criteria) this;
        }

        public Criteria andAftertimeNotBetween(Integer value1, Integer value2) {
            addCriterion("aftertime not between", value1, value2, "aftertime");
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