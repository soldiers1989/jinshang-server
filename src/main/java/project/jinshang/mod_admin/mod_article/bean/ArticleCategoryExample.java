package project.jinshang.mod_admin.mod_article.bean;

import java.util.ArrayList;
import java.util.List;

public class ArticleCategoryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ArticleCategoryExample() {
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

        public Criteria andPraentidIsNull() {
            addCriterion("praentid is null");
            return (Criteria) this;
        }

        public Criteria andPraentidIsNotNull() {
            addCriterion("praentid is not null");
            return (Criteria) this;
        }

        public Criteria andPraentidEqualTo(Long value) {
            addCriterion("praentid =", value, "praentid");
            return (Criteria) this;
        }

        public Criteria andPraentidNotEqualTo(Long value) {
            addCriterion("praentid <>", value, "praentid");
            return (Criteria) this;
        }

        public Criteria andPraentidGreaterThan(Long value) {
            addCriterion("praentid >", value, "praentid");
            return (Criteria) this;
        }

        public Criteria andPraentidGreaterThanOrEqualTo(Long value) {
            addCriterion("praentid >=", value, "praentid");
            return (Criteria) this;
        }

        public Criteria andPraentidLessThan(Long value) {
            addCriterion("praentid <", value, "praentid");
            return (Criteria) this;
        }

        public Criteria andPraentidLessThanOrEqualTo(Long value) {
            addCriterion("praentid <=", value, "praentid");
            return (Criteria) this;
        }

        public Criteria andPraentidIn(List<Long> values) {
            addCriterion("praentid in", values, "praentid");
            return (Criteria) this;
        }

        public Criteria andPraentidNotIn(List<Long> values) {
            addCriterion("praentid not in", values, "praentid");
            return (Criteria) this;
        }

        public Criteria andPraentidBetween(Long value1, Long value2) {
            addCriterion("praentid between", value1, value2, "praentid");
            return (Criteria) this;
        }

        public Criteria andPraentidNotBetween(Long value1, Long value2) {
            addCriterion("praentid not between", value1, value2, "praentid");
            return (Criteria) this;
        }

        public Criteria andDocnameIsNull() {
            addCriterion("docname is null");
            return (Criteria) this;
        }

        public Criteria andDocnameIsNotNull() {
            addCriterion("docname is not null");
            return (Criteria) this;
        }

        public Criteria andDocnameEqualTo(String value) {
            addCriterion("docname =", value, "docname");
            return (Criteria) this;
        }

        public Criteria andDocnameNotEqualTo(String value) {
            addCriterion("docname <>", value, "docname");
            return (Criteria) this;
        }

        public Criteria andDocnameGreaterThan(String value) {
            addCriterion("docname >", value, "docname");
            return (Criteria) this;
        }

        public Criteria andDocnameGreaterThanOrEqualTo(String value) {
            addCriterion("docname >=", value, "docname");
            return (Criteria) this;
        }

        public Criteria andDocnameLessThan(String value) {
            addCriterion("docname <", value, "docname");
            return (Criteria) this;
        }

        public Criteria andDocnameLessThanOrEqualTo(String value) {
            addCriterion("docname <=", value, "docname");
            return (Criteria) this;
        }

        public Criteria andDocnameLike(String value) {
            addCriterion("docname like", value, "docname");
            return (Criteria) this;
        }

        public Criteria andDocnameNotLike(String value) {
            addCriterion("docname not like", value, "docname");
            return (Criteria) this;
        }

        public Criteria andDocnameIn(List<String> values) {
            addCriterion("docname in", values, "docname");
            return (Criteria) this;
        }

        public Criteria andDocnameNotIn(List<String> values) {
            addCriterion("docname not in", values, "docname");
            return (Criteria) this;
        }

        public Criteria andDocnameBetween(String value1, String value2) {
            addCriterion("docname between", value1, value2, "docname");
            return (Criteria) this;
        }

        public Criteria andDocnameNotBetween(String value1, String value2) {
            addCriterion("docname not between", value1, value2, "docname");
            return (Criteria) this;
        }

        public Criteria andDocorderIsNull() {
            addCriterion("docorder is null");
            return (Criteria) this;
        }

        public Criteria andDocorderIsNotNull() {
            addCriterion("docorder is not null");
            return (Criteria) this;
        }

        public Criteria andDocorderEqualTo(Integer value) {
            addCriterion("docorder =", value, "docorder");
            return (Criteria) this;
        }

        public Criteria andDocorderNotEqualTo(Integer value) {
            addCriterion("docorder <>", value, "docorder");
            return (Criteria) this;
        }

        public Criteria andDocorderGreaterThan(Integer value) {
            addCriterion("docorder >", value, "docorder");
            return (Criteria) this;
        }

        public Criteria andDocorderGreaterThanOrEqualTo(Integer value) {
            addCriterion("docorder >=", value, "docorder");
            return (Criteria) this;
        }

        public Criteria andDocorderLessThan(Integer value) {
            addCriterion("docorder <", value, "docorder");
            return (Criteria) this;
        }

        public Criteria andDocorderLessThanOrEqualTo(Integer value) {
            addCriterion("docorder <=", value, "docorder");
            return (Criteria) this;
        }

        public Criteria andDocorderIn(List<Integer> values) {
            addCriterion("docorder in", values, "docorder");
            return (Criteria) this;
        }

        public Criteria andDocorderNotIn(List<Integer> values) {
            addCriterion("docorder not in", values, "docorder");
            return (Criteria) this;
        }

        public Criteria andDocorderBetween(Integer value1, Integer value2) {
            addCriterion("docorder between", value1, value2, "docorder");
            return (Criteria) this;
        }

        public Criteria andDocorderNotBetween(Integer value1, Integer value2) {
            addCriterion("docorder not between", value1, value2, "docorder");
            return (Criteria) this;
        }

        public Criteria andDocislistIsNull() {
            addCriterion("docislist is null");
            return (Criteria) this;
        }

        public Criteria andDocislistIsNotNull() {
            addCriterion("docislist is not null");
            return (Criteria) this;
        }

        public Criteria andDocislistEqualTo(Short value) {
            addCriterion("docislist =", value, "docislist");
            return (Criteria) this;
        }

        public Criteria andDocislistNotEqualTo(Short value) {
            addCriterion("docislist <>", value, "docislist");
            return (Criteria) this;
        }

        public Criteria andDocislistGreaterThan(Short value) {
            addCriterion("docislist >", value, "docislist");
            return (Criteria) this;
        }

        public Criteria andDocislistGreaterThanOrEqualTo(Short value) {
            addCriterion("docislist >=", value, "docislist");
            return (Criteria) this;
        }

        public Criteria andDocislistLessThan(Short value) {
            addCriterion("docislist <", value, "docislist");
            return (Criteria) this;
        }

        public Criteria andDocislistLessThanOrEqualTo(Short value) {
            addCriterion("docislist <=", value, "docislist");
            return (Criteria) this;
        }

        public Criteria andDocislistIn(List<Short> values) {
            addCriterion("docislist in", values, "docislist");
            return (Criteria) this;
        }

        public Criteria andDocislistNotIn(List<Short> values) {
            addCriterion("docislist not in", values, "docislist");
            return (Criteria) this;
        }

        public Criteria andDocislistBetween(Short value1, Short value2) {
            addCriterion("docislist between", value1, value2, "docislist");
            return (Criteria) this;
        }

        public Criteria andDocislistNotBetween(Short value1, Short value2) {
            addCriterion("docislist not between", value1, value2, "docislist");
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