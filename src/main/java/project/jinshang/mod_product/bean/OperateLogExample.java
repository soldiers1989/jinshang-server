package project.jinshang.mod_product.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OperateLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OperateLogExample() {
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

        public Criteria andOpidIsNull() {
            addCriterion("opid is null");
            return (Criteria) this;
        }

        public Criteria andOpidIsNotNull() {
            addCriterion("opid is not null");
            return (Criteria) this;
        }

        public Criteria andOpidEqualTo(Long value) {
            addCriterion("opid =", value, "opid");
            return (Criteria) this;
        }

        public Criteria andOpidNotEqualTo(Long value) {
            addCriterion("opid <>", value, "opid");
            return (Criteria) this;
        }

        public Criteria andOpidGreaterThan(Long value) {
            addCriterion("opid >", value, "opid");
            return (Criteria) this;
        }

        public Criteria andOpidGreaterThanOrEqualTo(Long value) {
            addCriterion("opid >=", value, "opid");
            return (Criteria) this;
        }

        public Criteria andOpidLessThan(Long value) {
            addCriterion("opid <", value, "opid");
            return (Criteria) this;
        }

        public Criteria andOpidLessThanOrEqualTo(Long value) {
            addCriterion("opid <=", value, "opid");
            return (Criteria) this;
        }

        public Criteria andOpidIn(List<Long> values) {
            addCriterion("opid in", values, "opid");
            return (Criteria) this;
        }

        public Criteria andOpidNotIn(List<Long> values) {
            addCriterion("opid not in", values, "opid");
            return (Criteria) this;
        }

        public Criteria andOpidBetween(Long value1, Long value2) {
            addCriterion("opid between", value1, value2, "opid");
            return (Criteria) this;
        }

        public Criteria andOpidNotBetween(Long value1, Long value2) {
            addCriterion("opid not between", value1, value2, "opid");
            return (Criteria) this;
        }

        public Criteria andOpnameIsNull() {
            addCriterion("opname is null");
            return (Criteria) this;
        }

        public Criteria andOpnameIsNotNull() {
            addCriterion("opname is not null");
            return (Criteria) this;
        }

        public Criteria andOpnameEqualTo(String value) {
            addCriterion("opname =", value, "opname");
            return (Criteria) this;
        }

        public Criteria andOpnameNotEqualTo(String value) {
            addCriterion("opname <>", value, "opname");
            return (Criteria) this;
        }

        public Criteria andOpnameGreaterThan(String value) {
            addCriterion("opname >", value, "opname");
            return (Criteria) this;
        }

        public Criteria andOpnameGreaterThanOrEqualTo(String value) {
            addCriterion("opname >=", value, "opname");
            return (Criteria) this;
        }

        public Criteria andOpnameLessThan(String value) {
            addCriterion("opname <", value, "opname");
            return (Criteria) this;
        }

        public Criteria andOpnameLessThanOrEqualTo(String value) {
            addCriterion("opname <=", value, "opname");
            return (Criteria) this;
        }

        public Criteria andOpnameLike(String value) {
            addCriterion("opname like", value, "opname");
            return (Criteria) this;
        }

        public Criteria andOpnameNotLike(String value) {
            addCriterion("opname not like", value, "opname");
            return (Criteria) this;
        }

        public Criteria andOpnameIn(List<String> values) {
            addCriterion("opname in", values, "opname");
            return (Criteria) this;
        }

        public Criteria andOpnameNotIn(List<String> values) {
            addCriterion("opname not in", values, "opname");
            return (Criteria) this;
        }

        public Criteria andOpnameBetween(String value1, String value2) {
            addCriterion("opname between", value1, value2, "opname");
            return (Criteria) this;
        }

        public Criteria andOpnameNotBetween(String value1, String value2) {
            addCriterion("opname not between", value1, value2, "opname");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andOptypeIsNull() {
            addCriterion("optype is null");
            return (Criteria) this;
        }

        public Criteria andOptypeIsNotNull() {
            addCriterion("optype is not null");
            return (Criteria) this;
        }

        public Criteria andOptypeEqualTo(Short value) {
            addCriterion("optype =", value, "optype");
            return (Criteria) this;
        }

        public Criteria andOptypeNotEqualTo(Short value) {
            addCriterion("optype <>", value, "optype");
            return (Criteria) this;
        }

        public Criteria andOptypeGreaterThan(Short value) {
            addCriterion("optype >", value, "optype");
            return (Criteria) this;
        }

        public Criteria andOptypeGreaterThanOrEqualTo(Short value) {
            addCriterion("optype >=", value, "optype");
            return (Criteria) this;
        }

        public Criteria andOptypeLessThan(Short value) {
            addCriterion("optype <", value, "optype");
            return (Criteria) this;
        }

        public Criteria andOptypeLessThanOrEqualTo(Short value) {
            addCriterion("optype <=", value, "optype");
            return (Criteria) this;
        }

        public Criteria andOptypeIn(List<Short> values) {
            addCriterion("optype in", values, "optype");
            return (Criteria) this;
        }

        public Criteria andOptypeNotIn(List<Short> values) {
            addCriterion("optype not in", values, "optype");
            return (Criteria) this;
        }

        public Criteria andOptypeBetween(Short value1, Short value2) {
            addCriterion("optype between", value1, value2, "optype");
            return (Criteria) this;
        }

        public Criteria andOptypeNotBetween(Short value1, Short value2) {
            addCriterion("optype not between", value1, value2, "optype");
            return (Criteria) this;
        }

        public Criteria andOptimeIsNull() {
            addCriterion("optime is null");
            return (Criteria) this;
        }

        public Criteria andOptimeIsNotNull() {
            addCriterion("optime is not null");
            return (Criteria) this;
        }

        public Criteria andOptimeEqualTo(Date value) {
            addCriterion("optime =", value, "optime");
            return (Criteria) this;
        }

        public Criteria andOptimeNotEqualTo(Date value) {
            addCriterion("optime <>", value, "optime");
            return (Criteria) this;
        }

        public Criteria andOptimeGreaterThan(Date value) {
            addCriterion("optime >", value, "optime");
            return (Criteria) this;
        }

        public Criteria andOptimeGreaterThanOrEqualTo(Date value) {
            addCriterion("optime >=", value, "optime");
            return (Criteria) this;
        }

        public Criteria andOptimeLessThan(Date value) {
            addCriterion("optime <", value, "optime");
            return (Criteria) this;
        }

        public Criteria andOptimeLessThanOrEqualTo(Date value) {
            addCriterion("optime <=", value, "optime");
            return (Criteria) this;
        }

        public Criteria andOptimeIn(List<Date> values) {
            addCriterion("optime in", values, "optime");
            return (Criteria) this;
        }

        public Criteria andOptimeNotIn(List<Date> values) {
            addCriterion("optime not in", values, "optime");
            return (Criteria) this;
        }

        public Criteria andOptimeBetween(Date value1, Date value2) {
            addCriterion("optime between", value1, value2, "optime");
            return (Criteria) this;
        }

        public Criteria andOptimeNotBetween(Date value1, Date value2) {
            addCriterion("optime not between", value1, value2, "optime");
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

        public Criteria andOrderpdidIsNull() {
            addCriterion("orderpdid is null");
            return (Criteria) this;
        }

        public Criteria andOrderpdidIsNotNull() {
            addCriterion("orderpdid is not null");
            return (Criteria) this;
        }

        public Criteria andOrderpdidEqualTo(Long value) {
            addCriterion("orderpdid =", value, "orderpdid");
            return (Criteria) this;
        }

        public Criteria andOrderpdidNotEqualTo(Long value) {
            addCriterion("orderpdid <>", value, "orderpdid");
            return (Criteria) this;
        }

        public Criteria andOrderpdidGreaterThan(Long value) {
            addCriterion("orderpdid >", value, "orderpdid");
            return (Criteria) this;
        }

        public Criteria andOrderpdidGreaterThanOrEqualTo(Long value) {
            addCriterion("orderpdid >=", value, "orderpdid");
            return (Criteria) this;
        }

        public Criteria andOrderpdidLessThan(Long value) {
            addCriterion("orderpdid <", value, "orderpdid");
            return (Criteria) this;
        }

        public Criteria andOrderpdidLessThanOrEqualTo(Long value) {
            addCriterion("orderpdid <=", value, "orderpdid");
            return (Criteria) this;
        }

        public Criteria andOrderpdidIn(List<Long> values) {
            addCriterion("orderpdid in", values, "orderpdid");
            return (Criteria) this;
        }

        public Criteria andOrderpdidNotIn(List<Long> values) {
            addCriterion("orderpdid not in", values, "orderpdid");
            return (Criteria) this;
        }

        public Criteria andOrderpdidBetween(Long value1, Long value2) {
            addCriterion("orderpdid between", value1, value2, "orderpdid");
            return (Criteria) this;
        }

        public Criteria andOrderpdidNotBetween(Long value1, Long value2) {
            addCriterion("orderpdid not between", value1, value2, "orderpdid");
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