package project.jinshang.mod_admin.mod_showcategory.bean;

import java.util.ArrayList;
import java.util.List;

public class ShowCatedetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ShowCatedetailExample() {
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

        public Criteria andShowidIsNull() {
            addCriterion("showid is null");
            return (Criteria) this;
        }

        public Criteria andShowidIsNotNull() {
            addCriterion("showid is not null");
            return (Criteria) this;
        }

        public Criteria andShowidEqualTo(Long value) {
            addCriterion("showid =", value, "showid");
            return (Criteria) this;
        }

        public Criteria andShowidNotEqualTo(Long value) {
            addCriterion("showid <>", value, "showid");
            return (Criteria) this;
        }

        public Criteria andShowidGreaterThan(Long value) {
            addCriterion("showid >", value, "showid");
            return (Criteria) this;
        }

        public Criteria andShowidGreaterThanOrEqualTo(Long value) {
            addCriterion("showid >=", value, "showid");
            return (Criteria) this;
        }

        public Criteria andShowidLessThan(Long value) {
            addCriterion("showid <", value, "showid");
            return (Criteria) this;
        }

        public Criteria andShowidLessThanOrEqualTo(Long value) {
            addCriterion("showid <=", value, "showid");
            return (Criteria) this;
        }

        public Criteria andShowidIn(List<Long> values) {
            addCriterion("showid in", values, "showid");
            return (Criteria) this;
        }

        public Criteria andShowidNotIn(List<Long> values) {
            addCriterion("showid not in", values, "showid");
            return (Criteria) this;
        }

        public Criteria andShowidBetween(Long value1, Long value2) {
            addCriterion("showid between", value1, value2, "showid");
            return (Criteria) this;
        }

        public Criteria andShowidNotBetween(Long value1, Long value2) {
            addCriterion("showid not between", value1, value2, "showid");
            return (Criteria) this;
        }

        public Criteria andLevel2categoryIsNull() {
            addCriterion("level2category is null");
            return (Criteria) this;
        }

        public Criteria andLevel2categoryIsNotNull() {
            addCriterion("level2category is not null");
            return (Criteria) this;
        }

        public Criteria andLevel2categoryEqualTo(String value) {
            addCriterion("level2category =", value, "level2category");
            return (Criteria) this;
        }

        public Criteria andLevel2categoryNotEqualTo(String value) {
            addCriterion("level2category <>", value, "level2category");
            return (Criteria) this;
        }

        public Criteria andLevel2categoryGreaterThan(String value) {
            addCriterion("level2category >", value, "level2category");
            return (Criteria) this;
        }

        public Criteria andLevel2categoryGreaterThanOrEqualTo(String value) {
            addCriterion("level2category >=", value, "level2category");
            return (Criteria) this;
        }

        public Criteria andLevel2categoryLessThan(String value) {
            addCriterion("level2category <", value, "level2category");
            return (Criteria) this;
        }

        public Criteria andLevel2categoryLessThanOrEqualTo(String value) {
            addCriterion("level2category <=", value, "level2category");
            return (Criteria) this;
        }

        public Criteria andLevel2categoryLike(String value) {
            addCriterion("level2category like", value, "level2category");
            return (Criteria) this;
        }

        public Criteria andLevel2categoryNotLike(String value) {
            addCriterion("level2category not like", value, "level2category");
            return (Criteria) this;
        }

        public Criteria andLevel2categoryIn(List<String> values) {
            addCriterion("level2category in", values, "level2category");
            return (Criteria) this;
        }

        public Criteria andLevel2categoryNotIn(List<String> values) {
            addCriterion("level2category not in", values, "level2category");
            return (Criteria) this;
        }

        public Criteria andLevel2categoryBetween(String value1, String value2) {
            addCriterion("level2category between", value1, value2, "level2category");
            return (Criteria) this;
        }

        public Criteria andLevel2categoryNotBetween(String value1, String value2) {
            addCriterion("level2category not between", value1, value2, "level2category");
            return (Criteria) this;
        }

        public Criteria andLevel2idIsNull() {
            addCriterion("level2id is null");
            return (Criteria) this;
        }

        public Criteria andLevel2idIsNotNull() {
            addCriterion("level2id is not null");
            return (Criteria) this;
        }

        public Criteria andLevel2idEqualTo(Long value) {
            addCriterion("level2id =", value, "level2id");
            return (Criteria) this;
        }

        public Criteria andLevel2idNotEqualTo(Long value) {
            addCriterion("level2id <>", value, "level2id");
            return (Criteria) this;
        }

        public Criteria andLevel2idGreaterThan(Long value) {
            addCriterion("level2id >", value, "level2id");
            return (Criteria) this;
        }

        public Criteria andLevel2idGreaterThanOrEqualTo(Long value) {
            addCriterion("level2id >=", value, "level2id");
            return (Criteria) this;
        }

        public Criteria andLevel2idLessThan(Long value) {
            addCriterion("level2id <", value, "level2id");
            return (Criteria) this;
        }

        public Criteria andLevel2idLessThanOrEqualTo(Long value) {
            addCriterion("level2id <=", value, "level2id");
            return (Criteria) this;
        }

        public Criteria andLevel2idIn(List<Long> values) {
            addCriterion("level2id in", values, "level2id");
            return (Criteria) this;
        }

        public Criteria andLevel2idNotIn(List<Long> values) {
            addCriterion("level2id not in", values, "level2id");
            return (Criteria) this;
        }

        public Criteria andLevel2idBetween(Long value1, Long value2) {
            addCriterion("level2id between", value1, value2, "level2id");
            return (Criteria) this;
        }

        public Criteria andLevel2idNotBetween(Long value1, Long value2) {
            addCriterion("level2id not between", value1, value2, "level2id");
            return (Criteria) this;
        }

        public Criteria andMaterialIsNull() {
            addCriterion("material is null");
            return (Criteria) this;
        }

        public Criteria andMaterialIsNotNull() {
            addCriterion("material is not null");
            return (Criteria) this;
        }

        public Criteria andMaterialEqualTo(String value) {
            addCriterion("material =", value, "material");
            return (Criteria) this;
        }

        public Criteria andMaterialNotEqualTo(String value) {
            addCriterion("material <>", value, "material");
            return (Criteria) this;
        }

        public Criteria andMaterialGreaterThan(String value) {
            addCriterion("material >", value, "material");
            return (Criteria) this;
        }

        public Criteria andMaterialGreaterThanOrEqualTo(String value) {
            addCriterion("material >=", value, "material");
            return (Criteria) this;
        }

        public Criteria andMaterialLessThan(String value) {
            addCriterion("material <", value, "material");
            return (Criteria) this;
        }

        public Criteria andMaterialLessThanOrEqualTo(String value) {
            addCriterion("material <=", value, "material");
            return (Criteria) this;
        }

        public Criteria andMaterialLike(String value) {
            addCriterion("material like", value, "material");
            return (Criteria) this;
        }

        public Criteria andMaterialNotLike(String value) {
            addCriterion("material not like", value, "material");
            return (Criteria) this;
        }

        public Criteria andMaterialIn(List<String> values) {
            addCriterion("material in", values, "material");
            return (Criteria) this;
        }

        public Criteria andMaterialNotIn(List<String> values) {
            addCriterion("material not in", values, "material");
            return (Criteria) this;
        }

        public Criteria andMaterialBetween(String value1, String value2) {
            addCriterion("material between", value1, value2, "material");
            return (Criteria) this;
        }

        public Criteria andMaterialNotBetween(String value1, String value2) {
            addCriterion("material not between", value1, value2, "material");
            return (Criteria) this;
        }

        public Criteria andCategoryidsIsNull() {
            addCriterion("categoryids is null");
            return (Criteria) this;
        }

        public Criteria andCategoryidsIsNotNull() {
            addCriterion("categoryids is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryidsEqualTo(String value) {
            addCriterion("categoryids =", value, "categoryids");
            return (Criteria) this;
        }

        public Criteria andCategoryidsNotEqualTo(String value) {
            addCriterion("categoryids <>", value, "categoryids");
            return (Criteria) this;
        }

        public Criteria andCategoryidsGreaterThan(String value) {
            addCriterion("categoryids >", value, "categoryids");
            return (Criteria) this;
        }

        public Criteria andCategoryidsGreaterThanOrEqualTo(String value) {
            addCriterion("categoryids >=", value, "categoryids");
            return (Criteria) this;
        }

        public Criteria andCategoryidsLessThan(String value) {
            addCriterion("categoryids <", value, "categoryids");
            return (Criteria) this;
        }

        public Criteria andCategoryidsLessThanOrEqualTo(String value) {
            addCriterion("categoryids <=", value, "categoryids");
            return (Criteria) this;
        }

        public Criteria andCategoryidsLike(String value) {
            addCriterion("categoryids like", value, "categoryids");
            return (Criteria) this;
        }

        public Criteria andCategoryidsNotLike(String value) {
            addCriterion("categoryids not like", value, "categoryids");
            return (Criteria) this;
        }

        public Criteria andCategoryidsIn(List<String> values) {
            addCriterion("categoryids in", values, "categoryids");
            return (Criteria) this;
        }

        public Criteria andCategoryidsNotIn(List<String> values) {
            addCriterion("categoryids not in", values, "categoryids");
            return (Criteria) this;
        }

        public Criteria andCategoryidsBetween(String value1, String value2) {
            addCriterion("categoryids between", value1, value2, "categoryids");
            return (Criteria) this;
        }

        public Criteria andCategoryidsNotBetween(String value1, String value2) {
            addCriterion("categoryids not between", value1, value2, "categoryids");
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