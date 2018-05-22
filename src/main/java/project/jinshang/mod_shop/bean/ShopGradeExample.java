package project.jinshang.mod_shop.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ShopGradeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ShopGradeExample() {
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

        public Criteria andGradeIsNull() {
            addCriterion("grade is null");
            return (Criteria) this;
        }

        public Criteria andGradeIsNotNull() {
            addCriterion("grade is not null");
            return (Criteria) this;
        }

        public Criteria andGradeEqualTo(Integer value) {
            addCriterion("grade =", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotEqualTo(Integer value) {
            addCriterion("grade <>", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeGreaterThan(Integer value) {
            addCriterion("grade >", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeGreaterThanOrEqualTo(Integer value) {
            addCriterion("grade >=", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLessThan(Integer value) {
            addCriterion("grade <", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLessThanOrEqualTo(Integer value) {
            addCriterion("grade <=", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeIn(List<Integer> values) {
            addCriterion("grade in", values, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotIn(List<Integer> values) {
            addCriterion("grade not in", values, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeBetween(Integer value1, Integer value2) {
            addCriterion("grade between", value1, value2, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotBetween(Integer value1, Integer value2) {
            addCriterion("grade not between", value1, value2, "grade");
            return (Criteria) this;
        }

        public Criteria andGradenameIsNull() {
            addCriterion("gradename is null");
            return (Criteria) this;
        }

        public Criteria andGradenameIsNotNull() {
            addCriterion("gradename is not null");
            return (Criteria) this;
        }

        public Criteria andGradenameEqualTo(String value) {
            addCriterion("gradename =", value, "gradename");
            return (Criteria) this;
        }

        public Criteria andGradenameNotEqualTo(String value) {
            addCriterion("gradename <>", value, "gradename");
            return (Criteria) this;
        }

        public Criteria andGradenameGreaterThan(String value) {
            addCriterion("gradename >", value, "gradename");
            return (Criteria) this;
        }

        public Criteria andGradenameGreaterThanOrEqualTo(String value) {
            addCriterion("gradename >=", value, "gradename");
            return (Criteria) this;
        }

        public Criteria andGradenameLessThan(String value) {
            addCriterion("gradename <", value, "gradename");
            return (Criteria) this;
        }

        public Criteria andGradenameLessThanOrEqualTo(String value) {
            addCriterion("gradename <=", value, "gradename");
            return (Criteria) this;
        }

        public Criteria andGradenameLike(String value) {
            addCriterion("gradename like", value, "gradename");
            return (Criteria) this;
        }

        public Criteria andGradenameNotLike(String value) {
            addCriterion("gradename not like", value, "gradename");
            return (Criteria) this;
        }

        public Criteria andGradenameIn(List<String> values) {
            addCriterion("gradename in", values, "gradename");
            return (Criteria) this;
        }

        public Criteria andGradenameNotIn(List<String> values) {
            addCriterion("gradename not in", values, "gradename");
            return (Criteria) this;
        }

        public Criteria andGradenameBetween(String value1, String value2) {
            addCriterion("gradename between", value1, value2, "gradename");
            return (Criteria) this;
        }

        public Criteria andGradenameNotBetween(String value1, String value2) {
            addCriterion("gradename not between", value1, value2, "gradename");
            return (Criteria) this;
        }

        public Criteria andProductlimitIsNull() {
            addCriterion("productlimit is null");
            return (Criteria) this;
        }

        public Criteria andProductlimitIsNotNull() {
            addCriterion("productlimit is not null");
            return (Criteria) this;
        }

        public Criteria andProductlimitEqualTo(Integer value) {
            addCriterion("productlimit =", value, "productlimit");
            return (Criteria) this;
        }

        public Criteria andProductlimitNotEqualTo(Integer value) {
            addCriterion("productlimit <>", value, "productlimit");
            return (Criteria) this;
        }

        public Criteria andProductlimitGreaterThan(Integer value) {
            addCriterion("productlimit >", value, "productlimit");
            return (Criteria) this;
        }

        public Criteria andProductlimitGreaterThanOrEqualTo(Integer value) {
            addCriterion("productlimit >=", value, "productlimit");
            return (Criteria) this;
        }

        public Criteria andProductlimitLessThan(Integer value) {
            addCriterion("productlimit <", value, "productlimit");
            return (Criteria) this;
        }

        public Criteria andProductlimitLessThanOrEqualTo(Integer value) {
            addCriterion("productlimit <=", value, "productlimit");
            return (Criteria) this;
        }

        public Criteria andProductlimitIn(List<Integer> values) {
            addCriterion("productlimit in", values, "productlimit");
            return (Criteria) this;
        }

        public Criteria andProductlimitNotIn(List<Integer> values) {
            addCriterion("productlimit not in", values, "productlimit");
            return (Criteria) this;
        }

        public Criteria andProductlimitBetween(Integer value1, Integer value2) {
            addCriterion("productlimit between", value1, value2, "productlimit");
            return (Criteria) this;
        }

        public Criteria andProductlimitNotBetween(Integer value1, Integer value2) {
            addCriterion("productlimit not between", value1, value2, "productlimit");
            return (Criteria) this;
        }

        public Criteria andChargestandardIsNull() {
            addCriterion("chargestandard is null");
            return (Criteria) this;
        }

        public Criteria andChargestandardIsNotNull() {
            addCriterion("chargestandard is not null");
            return (Criteria) this;
        }

        public Criteria andChargestandardEqualTo(BigDecimal value) {
            addCriterion("chargestandard =", value, "chargestandard");
            return (Criteria) this;
        }

        public Criteria andChargestandardNotEqualTo(BigDecimal value) {
            addCriterion("chargestandard <>", value, "chargestandard");
            return (Criteria) this;
        }

        public Criteria andChargestandardGreaterThan(BigDecimal value) {
            addCriterion("chargestandard >", value, "chargestandard");
            return (Criteria) this;
        }

        public Criteria andChargestandardGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("chargestandard >=", value, "chargestandard");
            return (Criteria) this;
        }

        public Criteria andChargestandardLessThan(BigDecimal value) {
            addCriterion("chargestandard <", value, "chargestandard");
            return (Criteria) this;
        }

        public Criteria andChargestandardLessThanOrEqualTo(BigDecimal value) {
            addCriterion("chargestandard <=", value, "chargestandard");
            return (Criteria) this;
        }

        public Criteria andChargestandardIn(List<BigDecimal> values) {
            addCriterion("chargestandard in", values, "chargestandard");
            return (Criteria) this;
        }

        public Criteria andChargestandardNotIn(List<BigDecimal> values) {
            addCriterion("chargestandard not in", values, "chargestandard");
            return (Criteria) this;
        }

        public Criteria andChargestandardBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("chargestandard between", value1, value2, "chargestandard");
            return (Criteria) this;
        }

        public Criteria andChargestandardNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("chargestandard not between", value1, value2, "chargestandard");
            return (Criteria) this;
        }

        public Criteria andRateIsNull() {
            addCriterion("rate is null");
            return (Criteria) this;
        }

        public Criteria andRateIsNotNull() {
            addCriterion("rate is not null");
            return (Criteria) this;
        }

        public Criteria andRateEqualTo(BigDecimal value) {
            addCriterion("rate =", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotEqualTo(BigDecimal value) {
            addCriterion("rate <>", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateGreaterThan(BigDecimal value) {
            addCriterion("rate >", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("rate >=", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateLessThan(BigDecimal value) {
            addCriterion("rate <", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("rate <=", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateIn(List<BigDecimal> values) {
            addCriterion("rate in", values, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotIn(List<BigDecimal> values) {
            addCriterion("rate not in", values, "rate");
            return (Criteria) this;
        }

        public Criteria andRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rate between", value1, value2, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rate not between", value1, value2, "rate");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andIdefaultIsNull() {
            addCriterion("idefault is null");
            return (Criteria) this;
        }

        public Criteria andIdefaultIsNotNull() {
            addCriterion("idefault is not null");
            return (Criteria) this;
        }

        public Criteria andIdefaultEqualTo(Integer value) {
            addCriterion("idefault =", value, "idefault");
            return (Criteria) this;
        }

        public Criteria andIdefaultNotEqualTo(Integer value) {
            addCriterion("idefault <>", value, "idefault");
            return (Criteria) this;
        }

        public Criteria andIdefaultGreaterThan(Integer value) {
            addCriterion("idefault >", value, "idefault");
            return (Criteria) this;
        }

        public Criteria andIdefaultGreaterThanOrEqualTo(Integer value) {
            addCriterion("idefault >=", value, "idefault");
            return (Criteria) this;
        }

        public Criteria andIdefaultLessThan(Integer value) {
            addCriterion("idefault <", value, "idefault");
            return (Criteria) this;
        }

        public Criteria andIdefaultLessThanOrEqualTo(Integer value) {
            addCriterion("idefault <=", value, "idefault");
            return (Criteria) this;
        }

        public Criteria andIdefaultIn(List<Integer> values) {
            addCriterion("idefault in", values, "idefault");
            return (Criteria) this;
        }

        public Criteria andIdefaultNotIn(List<Integer> values) {
            addCriterion("idefault not in", values, "idefault");
            return (Criteria) this;
        }

        public Criteria andIdefaultBetween(Integer value1, Integer value2) {
            addCriterion("idefault between", value1, value2, "idefault");
            return (Criteria) this;
        }

        public Criteria andIdefaultNotBetween(Integer value1, Integer value2) {
            addCriterion("idefault not between", value1, value2, "idefault");
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