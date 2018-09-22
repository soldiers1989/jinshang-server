package project.jinshang.mod_product.bean;

import java.util.ArrayList;
import java.util.List;

public class ShippingTemplateGroupExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ShippingTemplateGroupExample() {
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

        public Criteria andSelfliftingIsNull() {
            addCriterion("selflifting is null");
            return (Criteria) this;
        }

        public Criteria andSelfliftingIsNotNull() {
            addCriterion("selflifting is not null");
            return (Criteria) this;
        }

        public Criteria andSelfliftingEqualTo(Boolean value) {
            addCriterion("selflifting =", value, "selflifting");
            return (Criteria) this;
        }

        public Criteria andSelfliftingNotEqualTo(Boolean value) {
            addCriterion("selflifting <>", value, "selflifting");
            return (Criteria) this;
        }

        public Criteria andSelfliftingGreaterThan(Boolean value) {
            addCriterion("selflifting >", value, "selflifting");
            return (Criteria) this;
        }

        public Criteria andSelfliftingGreaterThanOrEqualTo(Boolean value) {
            addCriterion("selflifting >=", value, "selflifting");
            return (Criteria) this;
        }

        public Criteria andSelfliftingLessThan(Boolean value) {
            addCriterion("selflifting <", value, "selflifting");
            return (Criteria) this;
        }

        public Criteria andSelfliftingLessThanOrEqualTo(Boolean value) {
            addCriterion("selflifting <=", value, "selflifting");
            return (Criteria) this;
        }

        public Criteria andSelfliftingIn(List<Boolean> values) {
            addCriterion("selflifting in", values, "selflifting");
            return (Criteria) this;
        }

        public Criteria andSelfliftingNotIn(List<Boolean> values) {
            addCriterion("selflifting not in", values, "selflifting");
            return (Criteria) this;
        }

        public Criteria andSelfliftingBetween(Boolean value1, Boolean value2) {
            addCriterion("selflifting between", value1, value2, "selflifting");
            return (Criteria) this;
        }

        public Criteria andSelfliftingNotBetween(Boolean value1, Boolean value2) {
            addCriterion("selflifting not between", value1, value2, "selflifting");
            return (Criteria) this;
        }

        public Criteria andSfarrivepayIsNull() {
            addCriterion("sfarrivepay is null");
            return (Criteria) this;
        }

        public Criteria andSfarrivepayIsNotNull() {
            addCriterion("sfarrivepay is not null");
            return (Criteria) this;
        }

        public Criteria andSfarrivepayEqualTo(Boolean value) {
            addCriterion("sfarrivepay =", value, "sfarrivepay");
            return (Criteria) this;
        }

        public Criteria andSfarrivepayNotEqualTo(Boolean value) {
            addCriterion("sfarrivepay <>", value, "sfarrivepay");
            return (Criteria) this;
        }

        public Criteria andSfarrivepayGreaterThan(Boolean value) {
            addCriterion("sfarrivepay >", value, "sfarrivepay");
            return (Criteria) this;
        }

        public Criteria andSfarrivepayGreaterThanOrEqualTo(Boolean value) {
            addCriterion("sfarrivepay >=", value, "sfarrivepay");
            return (Criteria) this;
        }

        public Criteria andSfarrivepayLessThan(Boolean value) {
            addCriterion("sfarrivepay <", value, "sfarrivepay");
            return (Criteria) this;
        }

        public Criteria andSfarrivepayLessThanOrEqualTo(Boolean value) {
            addCriterion("sfarrivepay <=", value, "sfarrivepay");
            return (Criteria) this;
        }

        public Criteria andSfarrivepayIn(List<Boolean> values) {
            addCriterion("sfarrivepay in", values, "sfarrivepay");
            return (Criteria) this;
        }

        public Criteria andSfarrivepayNotIn(List<Boolean> values) {
            addCriterion("sfarrivepay not in", values, "sfarrivepay");
            return (Criteria) this;
        }

        public Criteria andSfarrivepayBetween(Boolean value1, Boolean value2) {
            addCriterion("sfarrivepay between", value1, value2, "sfarrivepay");
            return (Criteria) this;
        }

        public Criteria andSfarrivepayNotBetween(Boolean value1, Boolean value2) {
            addCriterion("sfarrivepay not between", value1, value2, "sfarrivepay");
            return (Criteria) this;
        }

        public Criteria andExpresspayIsNull() {
            addCriterion("expresspay is null");
            return (Criteria) this;
        }

        public Criteria andExpresspayIsNotNull() {
            addCriterion("expresspay is not null");
            return (Criteria) this;
        }

        public Criteria andExpresspayEqualTo(Boolean value) {
            addCriterion("expresspay =", value, "expresspay");
            return (Criteria) this;
        }

        public Criteria andExpresspayNotEqualTo(Boolean value) {
            addCriterion("expresspay <>", value, "expresspay");
            return (Criteria) this;
        }

        public Criteria andExpresspayGreaterThan(Boolean value) {
            addCriterion("expresspay >", value, "expresspay");
            return (Criteria) this;
        }

        public Criteria andExpresspayGreaterThanOrEqualTo(Boolean value) {
            addCriterion("expresspay >=", value, "expresspay");
            return (Criteria) this;
        }

        public Criteria andExpresspayLessThan(Boolean value) {
            addCriterion("expresspay <", value, "expresspay");
            return (Criteria) this;
        }

        public Criteria andExpresspayLessThanOrEqualTo(Boolean value) {
            addCriterion("expresspay <=", value, "expresspay");
            return (Criteria) this;
        }

        public Criteria andExpresspayIn(List<Boolean> values) {
            addCriterion("expresspay in", values, "expresspay");
            return (Criteria) this;
        }

        public Criteria andExpresspayNotIn(List<Boolean> values) {
            addCriterion("expresspay not in", values, "expresspay");
            return (Criteria) this;
        }

        public Criteria andExpresspayBetween(Boolean value1, Boolean value2) {
            addCriterion("expresspay between", value1, value2, "expresspay");
            return (Criteria) this;
        }

        public Criteria andExpresspayNotBetween(Boolean value1, Boolean value2) {
            addCriterion("expresspay not between", value1, value2, "expresspay");
            return (Criteria) this;
        }

        public Criteria andExpreselfliftingIsNull() {
            addCriterion("expreselflifting is null");
            return (Criteria) this;
        }

        public Criteria andExpreselfliftingIsNotNull() {
            addCriterion("expreselflifting is not null");
            return (Criteria) this;
        }

        public Criteria andExpreselfliftingEqualTo(Boolean value) {
            addCriterion("expreselflifting =", value, "expreselflifting");
            return (Criteria) this;
        }

        public Criteria andExpreselfliftingNotEqualTo(Boolean value) {
            addCriterion("expreselflifting <>", value, "expreselflifting");
            return (Criteria) this;
        }

        public Criteria andExpreselfliftingGreaterThan(Boolean value) {
            addCriterion("expreselflifting >", value, "expreselflifting");
            return (Criteria) this;
        }

        public Criteria andExpreselfliftingGreaterThanOrEqualTo(Boolean value) {
            addCriterion("expreselflifting >=", value, "expreselflifting");
            return (Criteria) this;
        }

        public Criteria andExpreselfliftingLessThan(Boolean value) {
            addCriterion("expreselflifting <", value, "expreselflifting");
            return (Criteria) this;
        }

        public Criteria andExpreselfliftingLessThanOrEqualTo(Boolean value) {
            addCriterion("expreselflifting <=", value, "expreselflifting");
            return (Criteria) this;
        }

        public Criteria andExpreselfliftingIn(List<Boolean> values) {
            addCriterion("expreselflifting in", values, "expreselflifting");
            return (Criteria) this;
        }

        public Criteria andExpreselfliftingNotIn(List<Boolean> values) {
            addCriterion("expreselflifting not in", values, "expreselflifting");
            return (Criteria) this;
        }

        public Criteria andExpreselfliftingBetween(Boolean value1, Boolean value2) {
            addCriterion("expreselflifting between", value1, value2, "expreselflifting");
            return (Criteria) this;
        }

        public Criteria andExpreselfliftingNotBetween(Boolean value1, Boolean value2) {
            addCriterion("expreselflifting not between", value1, value2, "expreselflifting");
            return (Criteria) this;
        }

        public Criteria andExprehousehoidIsNull() {
            addCriterion("exprehousehoid is null");
            return (Criteria) this;
        }

        public Criteria andExprehousehoidIsNotNull() {
            addCriterion("exprehousehoid is not null");
            return (Criteria) this;
        }

        public Criteria andExprehousehoidEqualTo(Boolean value) {
            addCriterion("exprehousehoid =", value, "exprehousehoid");
            return (Criteria) this;
        }

        public Criteria andExprehousehoidNotEqualTo(Boolean value) {
            addCriterion("exprehousehoid <>", value, "exprehousehoid");
            return (Criteria) this;
        }

        public Criteria andExprehousehoidGreaterThan(Boolean value) {
            addCriterion("exprehousehoid >", value, "exprehousehoid");
            return (Criteria) this;
        }

        public Criteria andExprehousehoidGreaterThanOrEqualTo(Boolean value) {
            addCriterion("exprehousehoid >=", value, "exprehousehoid");
            return (Criteria) this;
        }

        public Criteria andExprehousehoidLessThan(Boolean value) {
            addCriterion("exprehousehoid <", value, "exprehousehoid");
            return (Criteria) this;
        }

        public Criteria andExprehousehoidLessThanOrEqualTo(Boolean value) {
            addCriterion("exprehousehoid <=", value, "exprehousehoid");
            return (Criteria) this;
        }

        public Criteria andExprehousehoidIn(List<Boolean> values) {
            addCriterion("exprehousehoid in", values, "exprehousehoid");
            return (Criteria) this;
        }

        public Criteria andExprehousehoidNotIn(List<Boolean> values) {
            addCriterion("exprehousehoid not in", values, "exprehousehoid");
            return (Criteria) this;
        }

        public Criteria andExprehousehoidBetween(Boolean value1, Boolean value2) {
            addCriterion("exprehousehoid between", value1, value2, "exprehousehoid");
            return (Criteria) this;
        }

        public Criteria andExprehousehoidNotBetween(Boolean value1, Boolean value2) {
            addCriterion("exprehousehoid not between", value1, value2, "exprehousehoid");
            return (Criteria) this;
        }

        public Criteria andExpretempIsNull() {
            addCriterion("expretemp is null");
            return (Criteria) this;
        }

        public Criteria andExpretempIsNotNull() {
            addCriterion("expretemp is not null");
            return (Criteria) this;
        }

        public Criteria andExpretempEqualTo(Long value) {
            addCriterion("expretemp =", value, "expretemp");
            return (Criteria) this;
        }

        public Criteria andExpretempNotEqualTo(Long value) {
            addCriterion("expretemp <>", value, "expretemp");
            return (Criteria) this;
        }

        public Criteria andExpretempGreaterThan(Long value) {
            addCriterion("expretemp >", value, "expretemp");
            return (Criteria) this;
        }

        public Criteria andExpretempGreaterThanOrEqualTo(Long value) {
            addCriterion("expretemp >=", value, "expretemp");
            return (Criteria) this;
        }

        public Criteria andExpretempLessThan(Long value) {
            addCriterion("expretemp <", value, "expretemp");
            return (Criteria) this;
        }

        public Criteria andExpretempLessThanOrEqualTo(Long value) {
            addCriterion("expretemp <=", value, "expretemp");
            return (Criteria) this;
        }

        public Criteria andExpretempIn(List<Long> values) {
            addCriterion("expretemp in", values, "expretemp");
            return (Criteria) this;
        }

        public Criteria andExpretempNotIn(List<Long> values) {
            addCriterion("expretemp not in", values, "expretemp");
            return (Criteria) this;
        }

        public Criteria andExpretempBetween(Long value1, Long value2) {
            addCriterion("expretemp between", value1, value2, "expretemp");
            return (Criteria) this;
        }

        public Criteria andExpretempNotBetween(Long value1, Long value2) {
            addCriterion("expretemp not between", value1, value2, "expretemp");
            return (Criteria) this;
        }

        public Criteria andExpreselftempIsNull() {
            addCriterion("expreselftemp is null");
            return (Criteria) this;
        }

        public Criteria andExpreselftempIsNotNull() {
            addCriterion("expreselftemp is not null");
            return (Criteria) this;
        }

        public Criteria andExpreselftempEqualTo(Long value) {
            addCriterion("expreselftemp =", value, "expreselftemp");
            return (Criteria) this;
        }

        public Criteria andExpreselftempNotEqualTo(Long value) {
            addCriterion("expreselftemp <>", value, "expreselftemp");
            return (Criteria) this;
        }

        public Criteria andExpreselftempGreaterThan(Long value) {
            addCriterion("expreselftemp >", value, "expreselftemp");
            return (Criteria) this;
        }

        public Criteria andExpreselftempGreaterThanOrEqualTo(Long value) {
            addCriterion("expreselftemp >=", value, "expreselftemp");
            return (Criteria) this;
        }

        public Criteria andExpreselftempLessThan(Long value) {
            addCriterion("expreselftemp <", value, "expreselftemp");
            return (Criteria) this;
        }

        public Criteria andExpreselftempLessThanOrEqualTo(Long value) {
            addCriterion("expreselftemp <=", value, "expreselftemp");
            return (Criteria) this;
        }

        public Criteria andExpreselftempIn(List<Long> values) {
            addCriterion("expreselftemp in", values, "expreselftemp");
            return (Criteria) this;
        }

        public Criteria andExpreselftempNotIn(List<Long> values) {
            addCriterion("expreselftemp not in", values, "expreselftemp");
            return (Criteria) this;
        }

        public Criteria andExpreselftempBetween(Long value1, Long value2) {
            addCriterion("expreselftemp between", value1, value2, "expreselftemp");
            return (Criteria) this;
        }

        public Criteria andExpreselftempNotBetween(Long value1, Long value2) {
            addCriterion("expreselftemp not between", value1, value2, "expreselftemp");
            return (Criteria) this;
        }

        public Criteria andExprehousetempIsNull() {
            addCriterion("exprehousetemp is null");
            return (Criteria) this;
        }

        public Criteria andExprehousetempIsNotNull() {
            addCriterion("exprehousetemp is not null");
            return (Criteria) this;
        }

        public Criteria andExprehousetempEqualTo(Long value) {
            addCriterion("exprehousetemp =", value, "exprehousetemp");
            return (Criteria) this;
        }

        public Criteria andExprehousetempNotEqualTo(Long value) {
            addCriterion("exprehousetemp <>", value, "exprehousetemp");
            return (Criteria) this;
        }

        public Criteria andExprehousetempGreaterThan(Long value) {
            addCriterion("exprehousetemp >", value, "exprehousetemp");
            return (Criteria) this;
        }

        public Criteria andExprehousetempGreaterThanOrEqualTo(Long value) {
            addCriterion("exprehousetemp >=", value, "exprehousetemp");
            return (Criteria) this;
        }

        public Criteria andExprehousetempLessThan(Long value) {
            addCriterion("exprehousetemp <", value, "exprehousetemp");
            return (Criteria) this;
        }

        public Criteria andExprehousetempLessThanOrEqualTo(Long value) {
            addCriterion("exprehousetemp <=", value, "exprehousetemp");
            return (Criteria) this;
        }

        public Criteria andExprehousetempIn(List<Long> values) {
            addCriterion("exprehousetemp in", values, "exprehousetemp");
            return (Criteria) this;
        }

        public Criteria andExprehousetempNotIn(List<Long> values) {
            addCriterion("exprehousetemp not in", values, "exprehousetemp");
            return (Criteria) this;
        }

        public Criteria andExprehousetempBetween(Long value1, Long value2) {
            addCriterion("exprehousetemp between", value1, value2, "exprehousetemp");
            return (Criteria) this;
        }

        public Criteria andExprehousetempNotBetween(Long value1, Long value2) {
            addCriterion("exprehousetemp not between", value1, value2, "exprehousetemp");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andSwitch1addressIsNull() {
            addCriterion("switch1address is null");
            return (Criteria) this;
        }

        public Criteria andSwitch1addressIsNotNull() {
            addCriterion("switch1address is not null");
            return (Criteria) this;
        }

        public Criteria andSwitch1addressEqualTo(String value) {
            addCriterion("switch1address =", value, "switch1address");
            return (Criteria) this;
        }

        public Criteria andSwitch1addressNotEqualTo(String value) {
            addCriterion("switch1address <>", value, "switch1address");
            return (Criteria) this;
        }

        public Criteria andSwitch1addressGreaterThan(String value) {
            addCriterion("switch1address >", value, "switch1address");
            return (Criteria) this;
        }

        public Criteria andSwitch1addressGreaterThanOrEqualTo(String value) {
            addCriterion("switch1address >=", value, "switch1address");
            return (Criteria) this;
        }

        public Criteria andSwitch1addressLessThan(String value) {
            addCriterion("switch1address <", value, "switch1address");
            return (Criteria) this;
        }

        public Criteria andSwitch1addressLessThanOrEqualTo(String value) {
            addCriterion("switch1address <=", value, "switch1address");
            return (Criteria) this;
        }

        public Criteria andSwitch1addressLike(String value) {
            addCriterion("switch1address like", value, "switch1address");
            return (Criteria) this;
        }

        public Criteria andSwitch1addressNotLike(String value) {
            addCriterion("switch1address not like", value, "switch1address");
            return (Criteria) this;
        }

        public Criteria andSwitch1addressIn(List<String> values) {
            addCriterion("switch1address in", values, "switch1address");
            return (Criteria) this;
        }

        public Criteria andSwitch1addressNotIn(List<String> values) {
            addCriterion("switch1address not in", values, "switch1address");
            return (Criteria) this;
        }

        public Criteria andSwitch1addressBetween(String value1, String value2) {
            addCriterion("switch1address between", value1, value2, "switch1address");
            return (Criteria) this;
        }

        public Criteria andSwitch1addressNotBetween(String value1, String value2) {
            addCriterion("switch1address not between", value1, value2, "switch1address");
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