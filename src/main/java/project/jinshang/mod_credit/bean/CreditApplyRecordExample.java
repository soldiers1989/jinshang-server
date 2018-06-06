package project.jinshang.mod_credit.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreditApplyRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CreditApplyRecordExample() {
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

        public Criteria andLimitmoneyIsNull() {
            addCriterion("limitmoney is null");
            return (Criteria) this;
        }

        public Criteria andLimitmoneyIsNotNull() {
            addCriterion("limitmoney is not null");
            return (Criteria) this;
        }

        public Criteria andLimitmoneyEqualTo(BigDecimal value) {
            addCriterion("limitmoney =", value, "limitmoney");
            return (Criteria) this;
        }

        public Criteria andLimitmoneyNotEqualTo(BigDecimal value) {
            addCriterion("limitmoney <>", value, "limitmoney");
            return (Criteria) this;
        }

        public Criteria andLimitmoneyGreaterThan(BigDecimal value) {
            addCriterion("limitmoney >", value, "limitmoney");
            return (Criteria) this;
        }

        public Criteria andLimitmoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("limitmoney >=", value, "limitmoney");
            return (Criteria) this;
        }

        public Criteria andLimitmoneyLessThan(BigDecimal value) {
            addCriterion("limitmoney <", value, "limitmoney");
            return (Criteria) this;
        }

        public Criteria andLimitmoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("limitmoney <=", value, "limitmoney");
            return (Criteria) this;
        }

        public Criteria andLimitmoneyIn(List<BigDecimal> values) {
            addCriterion("limitmoney in", values, "limitmoney");
            return (Criteria) this;
        }

        public Criteria andLimitmoneyNotIn(List<BigDecimal> values) {
            addCriterion("limitmoney not in", values, "limitmoney");
            return (Criteria) this;
        }

        public Criteria andLimitmoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("limitmoney between", value1, value2, "limitmoney");
            return (Criteria) this;
        }

        public Criteria andLimitmoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("limitmoney not between", value1, value2, "limitmoney");
            return (Criteria) this;
        }

        public Criteria andCompanyIsNull() {
            addCriterion("company is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIsNotNull() {
            addCriterion("company is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyEqualTo(String value) {
            addCriterion("company =", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotEqualTo(String value) {
            addCriterion("company <>", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyGreaterThan(String value) {
            addCriterion("company >", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("company >=", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLessThan(String value) {
            addCriterion("company <", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLessThanOrEqualTo(String value) {
            addCriterion("company <=", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLike(String value) {
            addCriterion("company like", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotLike(String value) {
            addCriterion("company not like", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyIn(List<String> values) {
            addCriterion("company in", values, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotIn(List<String> values) {
            addCriterion("company not in", values, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyBetween(String value1, String value2) {
            addCriterion("company between", value1, value2, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotBetween(String value1, String value2) {
            addCriterion("company not between", value1, value2, "company");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andContractIsNull() {
            addCriterion("contract is null");
            return (Criteria) this;
        }

        public Criteria andContractIsNotNull() {
            addCriterion("contract is not null");
            return (Criteria) this;
        }

        public Criteria andContractEqualTo(String value) {
            addCriterion("contract =", value, "contract");
            return (Criteria) this;
        }

        public Criteria andContractNotEqualTo(String value) {
            addCriterion("contract <>", value, "contract");
            return (Criteria) this;
        }

        public Criteria andContractGreaterThan(String value) {
            addCriterion("contract >", value, "contract");
            return (Criteria) this;
        }

        public Criteria andContractGreaterThanOrEqualTo(String value) {
            addCriterion("contract >=", value, "contract");
            return (Criteria) this;
        }

        public Criteria andContractLessThan(String value) {
            addCriterion("contract <", value, "contract");
            return (Criteria) this;
        }

        public Criteria andContractLessThanOrEqualTo(String value) {
            addCriterion("contract <=", value, "contract");
            return (Criteria) this;
        }

        public Criteria andContractLike(String value) {
            addCriterion("contract like", value, "contract");
            return (Criteria) this;
        }

        public Criteria andContractNotLike(String value) {
            addCriterion("contract not like", value, "contract");
            return (Criteria) this;
        }

        public Criteria andContractIn(List<String> values) {
            addCriterion("contract in", values, "contract");
            return (Criteria) this;
        }

        public Criteria andContractNotIn(List<String> values) {
            addCriterion("contract not in", values, "contract");
            return (Criteria) this;
        }

        public Criteria andContractBetween(String value1, String value2) {
            addCriterion("contract between", value1, value2, "contract");
            return (Criteria) this;
        }

        public Criteria andContractNotBetween(String value1, String value2) {
            addCriterion("contract not between", value1, value2, "contract");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andLicenseIsNull() {
            addCriterion("license is null");
            return (Criteria) this;
        }

        public Criteria andLicenseIsNotNull() {
            addCriterion("license is not null");
            return (Criteria) this;
        }

        public Criteria andLicenseEqualTo(String value) {
            addCriterion("license =", value, "license");
            return (Criteria) this;
        }

        public Criteria andLicenseNotEqualTo(String value) {
            addCriterion("license <>", value, "license");
            return (Criteria) this;
        }

        public Criteria andLicenseGreaterThan(String value) {
            addCriterion("license >", value, "license");
            return (Criteria) this;
        }

        public Criteria andLicenseGreaterThanOrEqualTo(String value) {
            addCriterion("license >=", value, "license");
            return (Criteria) this;
        }

        public Criteria andLicenseLessThan(String value) {
            addCriterion("license <", value, "license");
            return (Criteria) this;
        }

        public Criteria andLicenseLessThanOrEqualTo(String value) {
            addCriterion("license <=", value, "license");
            return (Criteria) this;
        }

        public Criteria andLicenseLike(String value) {
            addCriterion("license like", value, "license");
            return (Criteria) this;
        }

        public Criteria andLicenseNotLike(String value) {
            addCriterion("license not like", value, "license");
            return (Criteria) this;
        }

        public Criteria andLicenseIn(List<String> values) {
            addCriterion("license in", values, "license");
            return (Criteria) this;
        }

        public Criteria andLicenseNotIn(List<String> values) {
            addCriterion("license not in", values, "license");
            return (Criteria) this;
        }

        public Criteria andLicenseBetween(String value1, String value2) {
            addCriterion("license between", value1, value2, "license");
            return (Criteria) this;
        }

        public Criteria andLicenseNotBetween(String value1, String value2) {
            addCriterion("license not between", value1, value2, "license");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Short value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Short value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Short value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Short value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Short value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Short value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Short> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Short> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Short value1, Short value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Short value1, Short value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andAuditnameIsNull() {
            addCriterion("auditname is null");
            return (Criteria) this;
        }

        public Criteria andAuditnameIsNotNull() {
            addCriterion("auditname is not null");
            return (Criteria) this;
        }

        public Criteria andAuditnameEqualTo(String value) {
            addCriterion("auditname =", value, "auditname");
            return (Criteria) this;
        }

        public Criteria andAuditnameNotEqualTo(String value) {
            addCriterion("auditname <>", value, "auditname");
            return (Criteria) this;
        }

        public Criteria andAuditnameGreaterThan(String value) {
            addCriterion("auditname >", value, "auditname");
            return (Criteria) this;
        }

        public Criteria andAuditnameGreaterThanOrEqualTo(String value) {
            addCriterion("auditname >=", value, "auditname");
            return (Criteria) this;
        }

        public Criteria andAuditnameLessThan(String value) {
            addCriterion("auditname <", value, "auditname");
            return (Criteria) this;
        }

        public Criteria andAuditnameLessThanOrEqualTo(String value) {
            addCriterion("auditname <=", value, "auditname");
            return (Criteria) this;
        }

        public Criteria andAuditnameLike(String value) {
            addCriterion("auditname like", value, "auditname");
            return (Criteria) this;
        }

        public Criteria andAuditnameNotLike(String value) {
            addCriterion("auditname not like", value, "auditname");
            return (Criteria) this;
        }

        public Criteria andAuditnameIn(List<String> values) {
            addCriterion("auditname in", values, "auditname");
            return (Criteria) this;
        }

        public Criteria andAuditnameNotIn(List<String> values) {
            addCriterion("auditname not in", values, "auditname");
            return (Criteria) this;
        }

        public Criteria andAuditnameBetween(String value1, String value2) {
            addCriterion("auditname between", value1, value2, "auditname");
            return (Criteria) this;
        }

        public Criteria andAuditnameNotBetween(String value1, String value2) {
            addCriterion("auditname not between", value1, value2, "auditname");
            return (Criteria) this;
        }

        public Criteria andAuditidIsNull() {
            addCriterion("auditid is null");
            return (Criteria) this;
        }

        public Criteria andAuditidIsNotNull() {
            addCriterion("auditid is not null");
            return (Criteria) this;
        }

        public Criteria andAuditidEqualTo(Long value) {
            addCriterion("auditid =", value, "auditid");
            return (Criteria) this;
        }

        public Criteria andAuditidNotEqualTo(Long value) {
            addCriterion("auditid <>", value, "auditid");
            return (Criteria) this;
        }

        public Criteria andAuditidGreaterThan(Long value) {
            addCriterion("auditid >", value, "auditid");
            return (Criteria) this;
        }

        public Criteria andAuditidGreaterThanOrEqualTo(Long value) {
            addCriterion("auditid >=", value, "auditid");
            return (Criteria) this;
        }

        public Criteria andAuditidLessThan(Long value) {
            addCriterion("auditid <", value, "auditid");
            return (Criteria) this;
        }

        public Criteria andAuditidLessThanOrEqualTo(Long value) {
            addCriterion("auditid <=", value, "auditid");
            return (Criteria) this;
        }

        public Criteria andAuditidIn(List<Long> values) {
            addCriterion("auditid in", values, "auditid");
            return (Criteria) this;
        }

        public Criteria andAuditidNotIn(List<Long> values) {
            addCriterion("auditid not in", values, "auditid");
            return (Criteria) this;
        }

        public Criteria andAuditidBetween(Long value1, Long value2) {
            addCriterion("auditid between", value1, value2, "auditid");
            return (Criteria) this;
        }

        public Criteria andAuditidNotBetween(Long value1, Long value2) {
            addCriterion("auditid not between", value1, value2, "auditid");
            return (Criteria) this;
        }

        public Criteria andOfficerIsNull() {
            addCriterion("officer is null");
            return (Criteria) this;
        }

        public Criteria andOfficerIsNotNull() {
            addCriterion("officer is not null");
            return (Criteria) this;
        }

        public Criteria andOfficerEqualTo(String value) {
            addCriterion("officer =", value, "officer");
            return (Criteria) this;
        }

        public Criteria andOfficerNotEqualTo(String value) {
            addCriterion("officer <>", value, "officer");
            return (Criteria) this;
        }

        public Criteria andOfficerGreaterThan(String value) {
            addCriterion("officer >", value, "officer");
            return (Criteria) this;
        }

        public Criteria andOfficerGreaterThanOrEqualTo(String value) {
            addCriterion("officer >=", value, "officer");
            return (Criteria) this;
        }

        public Criteria andOfficerLessThan(String value) {
            addCriterion("officer <", value, "officer");
            return (Criteria) this;
        }

        public Criteria andOfficerLessThanOrEqualTo(String value) {
            addCriterion("officer <=", value, "officer");
            return (Criteria) this;
        }

        public Criteria andOfficerLike(String value) {
            addCriterion("officer like", value, "officer");
            return (Criteria) this;
        }

        public Criteria andOfficerNotLike(String value) {
            addCriterion("officer not like", value, "officer");
            return (Criteria) this;
        }

        public Criteria andOfficerIn(List<String> values) {
            addCriterion("officer in", values, "officer");
            return (Criteria) this;
        }

        public Criteria andOfficerNotIn(List<String> values) {
            addCriterion("officer not in", values, "officer");
            return (Criteria) this;
        }

        public Criteria andOfficerBetween(String value1, String value2) {
            addCriterion("officer between", value1, value2, "officer");
            return (Criteria) this;
        }

        public Criteria andOfficerNotBetween(String value1, String value2) {
            addCriterion("officer not between", value1, value2, "officer");
            return (Criteria) this;
        }

        public Criteria andOfficeridIsNull() {
            addCriterion("officerid is null");
            return (Criteria) this;
        }

        public Criteria andOfficeridIsNotNull() {
            addCriterion("officerid is not null");
            return (Criteria) this;
        }

        public Criteria andOfficeridEqualTo(Long value) {
            addCriterion("officerid =", value, "officerid");
            return (Criteria) this;
        }

        public Criteria andOfficeridNotEqualTo(Long value) {
            addCriterion("officerid <>", value, "officerid");
            return (Criteria) this;
        }

        public Criteria andOfficeridGreaterThan(Long value) {
            addCriterion("officerid >", value, "officerid");
            return (Criteria) this;
        }

        public Criteria andOfficeridGreaterThanOrEqualTo(Long value) {
            addCriterion("officerid >=", value, "officerid");
            return (Criteria) this;
        }

        public Criteria andOfficeridLessThan(Long value) {
            addCriterion("officerid <", value, "officerid");
            return (Criteria) this;
        }

        public Criteria andOfficeridLessThanOrEqualTo(Long value) {
            addCriterion("officerid <=", value, "officerid");
            return (Criteria) this;
        }

        public Criteria andOfficeridIn(List<Long> values) {
            addCriterion("officerid in", values, "officerid");
            return (Criteria) this;
        }

        public Criteria andOfficeridNotIn(List<Long> values) {
            addCriterion("officerid not in", values, "officerid");
            return (Criteria) this;
        }

        public Criteria andOfficeridBetween(Long value1, Long value2) {
            addCriterion("officerid between", value1, value2, "officerid");
            return (Criteria) this;
        }

        public Criteria andOfficeridNotBetween(Long value1, Long value2) {
            addCriterion("officerid not between", value1, value2, "officerid");
            return (Criteria) this;
        }

        public Criteria andReviewerIsNull() {
            addCriterion("reviewer is null");
            return (Criteria) this;
        }

        public Criteria andReviewerIsNotNull() {
            addCriterion("reviewer is not null");
            return (Criteria) this;
        }

        public Criteria andReviewerEqualTo(String value) {
            addCriterion("reviewer =", value, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerNotEqualTo(String value) {
            addCriterion("reviewer <>", value, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerGreaterThan(String value) {
            addCriterion("reviewer >", value, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerGreaterThanOrEqualTo(String value) {
            addCriterion("reviewer >=", value, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerLessThan(String value) {
            addCriterion("reviewer <", value, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerLessThanOrEqualTo(String value) {
            addCriterion("reviewer <=", value, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerLike(String value) {
            addCriterion("reviewer like", value, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerNotLike(String value) {
            addCriterion("reviewer not like", value, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerIn(List<String> values) {
            addCriterion("reviewer in", values, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerNotIn(List<String> values) {
            addCriterion("reviewer not in", values, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerBetween(String value1, String value2) {
            addCriterion("reviewer between", value1, value2, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerNotBetween(String value1, String value2) {
            addCriterion("reviewer not between", value1, value2, "reviewer");
            return (Criteria) this;
        }

        public Criteria andRevieweridIsNull() {
            addCriterion("reviewerid is null");
            return (Criteria) this;
        }

        public Criteria andRevieweridIsNotNull() {
            addCriterion("reviewerid is not null");
            return (Criteria) this;
        }

        public Criteria andRevieweridEqualTo(Long value) {
            addCriterion("reviewerid =", value, "reviewerid");
            return (Criteria) this;
        }

        public Criteria andRevieweridNotEqualTo(Long value) {
            addCriterion("reviewerid <>", value, "reviewerid");
            return (Criteria) this;
        }

        public Criteria andRevieweridGreaterThan(Long value) {
            addCriterion("reviewerid >", value, "reviewerid");
            return (Criteria) this;
        }

        public Criteria andRevieweridGreaterThanOrEqualTo(Long value) {
            addCriterion("reviewerid >=", value, "reviewerid");
            return (Criteria) this;
        }

        public Criteria andRevieweridLessThan(Long value) {
            addCriterion("reviewerid <", value, "reviewerid");
            return (Criteria) this;
        }

        public Criteria andRevieweridLessThanOrEqualTo(Long value) {
            addCriterion("reviewerid <=", value, "reviewerid");
            return (Criteria) this;
        }

        public Criteria andRevieweridIn(List<Long> values) {
            addCriterion("reviewerid in", values, "reviewerid");
            return (Criteria) this;
        }

        public Criteria andRevieweridNotIn(List<Long> values) {
            addCriterion("reviewerid not in", values, "reviewerid");
            return (Criteria) this;
        }

        public Criteria andRevieweridBetween(Long value1, Long value2) {
            addCriterion("reviewerid between", value1, value2, "reviewerid");
            return (Criteria) this;
        }

        public Criteria andRevieweridNotBetween(Long value1, Long value2) {
            addCriterion("reviewerid not between", value1, value2, "reviewerid");
            return (Criteria) this;
        }

        public Criteria andAudittimeIsNull() {
            addCriterion("audittime is null");
            return (Criteria) this;
        }

        public Criteria andAudittimeIsNotNull() {
            addCriterion("audittime is not null");
            return (Criteria) this;
        }

        public Criteria andAudittimeEqualTo(Date value) {
            addCriterion("audittime =", value, "audittime");
            return (Criteria) this;
        }

        public Criteria andAudittimeNotEqualTo(Date value) {
            addCriterion("audittime <>", value, "audittime");
            return (Criteria) this;
        }

        public Criteria andAudittimeGreaterThan(Date value) {
            addCriterion("audittime >", value, "audittime");
            return (Criteria) this;
        }

        public Criteria andAudittimeGreaterThanOrEqualTo(Date value) {
            addCriterion("audittime >=", value, "audittime");
            return (Criteria) this;
        }

        public Criteria andAudittimeLessThan(Date value) {
            addCriterion("audittime <", value, "audittime");
            return (Criteria) this;
        }

        public Criteria andAudittimeLessThanOrEqualTo(Date value) {
            addCriterion("audittime <=", value, "audittime");
            return (Criteria) this;
        }

        public Criteria andAudittimeIn(List<Date> values) {
            addCriterion("audittime in", values, "audittime");
            return (Criteria) this;
        }

        public Criteria andAudittimeNotIn(List<Date> values) {
            addCriterion("audittime not in", values, "audittime");
            return (Criteria) this;
        }

        public Criteria andAudittimeBetween(Date value1, Date value2) {
            addCriterion("audittime between", value1, value2, "audittime");
            return (Criteria) this;
        }

        public Criteria andAudittimeNotBetween(Date value1, Date value2) {
            addCriterion("audittime not between", value1, value2, "audittime");
            return (Criteria) this;
        }

        public Criteria andConfirmofficetimeIsNull() {
            addCriterion("confirmofficetime is null");
            return (Criteria) this;
        }

        public Criteria andConfirmofficetimeIsNotNull() {
            addCriterion("confirmofficetime is not null");
            return (Criteria) this;
        }

        public Criteria andConfirmofficetimeEqualTo(Date value) {
            addCriterion("confirmofficetime =", value, "confirmofficetime");
            return (Criteria) this;
        }

        public Criteria andConfirmofficetimeNotEqualTo(Date value) {
            addCriterion("confirmofficetime <>", value, "confirmofficetime");
            return (Criteria) this;
        }

        public Criteria andConfirmofficetimeGreaterThan(Date value) {
            addCriterion("confirmofficetime >", value, "confirmofficetime");
            return (Criteria) this;
        }

        public Criteria andConfirmofficetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("confirmofficetime >=", value, "confirmofficetime");
            return (Criteria) this;
        }

        public Criteria andConfirmofficetimeLessThan(Date value) {
            addCriterion("confirmofficetime <", value, "confirmofficetime");
            return (Criteria) this;
        }

        public Criteria andConfirmofficetimeLessThanOrEqualTo(Date value) {
            addCriterion("confirmofficetime <=", value, "confirmofficetime");
            return (Criteria) this;
        }

        public Criteria andConfirmofficetimeIn(List<Date> values) {
            addCriterion("confirmofficetime in", values, "confirmofficetime");
            return (Criteria) this;
        }

        public Criteria andConfirmofficetimeNotIn(List<Date> values) {
            addCriterion("confirmofficetime not in", values, "confirmofficetime");
            return (Criteria) this;
        }

        public Criteria andConfirmofficetimeBetween(Date value1, Date value2) {
            addCriterion("confirmofficetime between", value1, value2, "confirmofficetime");
            return (Criteria) this;
        }

        public Criteria andConfirmofficetimeNotBetween(Date value1, Date value2) {
            addCriterion("confirmofficetime not between", value1, value2, "confirmofficetime");
            return (Criteria) this;
        }

        public Criteria andOfficetimeIsNull() {
            addCriterion("officetime is null");
            return (Criteria) this;
        }

        public Criteria andOfficetimeIsNotNull() {
            addCriterion("officetime is not null");
            return (Criteria) this;
        }

        public Criteria andOfficetimeEqualTo(Date value) {
            addCriterion("officetime =", value, "officetime");
            return (Criteria) this;
        }

        public Criteria andOfficetimeNotEqualTo(Date value) {
            addCriterion("officetime <>", value, "officetime");
            return (Criteria) this;
        }

        public Criteria andOfficetimeGreaterThan(Date value) {
            addCriterion("officetime >", value, "officetime");
            return (Criteria) this;
        }

        public Criteria andOfficetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("officetime >=", value, "officetime");
            return (Criteria) this;
        }

        public Criteria andOfficetimeLessThan(Date value) {
            addCriterion("officetime <", value, "officetime");
            return (Criteria) this;
        }

        public Criteria andOfficetimeLessThanOrEqualTo(Date value) {
            addCriterion("officetime <=", value, "officetime");
            return (Criteria) this;
        }

        public Criteria andOfficetimeIn(List<Date> values) {
            addCriterion("officetime in", values, "officetime");
            return (Criteria) this;
        }

        public Criteria andOfficetimeNotIn(List<Date> values) {
            addCriterion("officetime not in", values, "officetime");
            return (Criteria) this;
        }

        public Criteria andOfficetimeBetween(Date value1, Date value2) {
            addCriterion("officetime between", value1, value2, "officetime");
            return (Criteria) this;
        }

        public Criteria andOfficetimeNotBetween(Date value1, Date value2) {
            addCriterion("officetime not between", value1, value2, "officetime");
            return (Criteria) this;
        }

        public Criteria andReviewtimeIsNull() {
            addCriterion("reviewtime is null");
            return (Criteria) this;
        }

        public Criteria andReviewtimeIsNotNull() {
            addCriterion("reviewtime is not null");
            return (Criteria) this;
        }

        public Criteria andReviewtimeEqualTo(Date value) {
            addCriterion("reviewtime =", value, "reviewtime");
            return (Criteria) this;
        }

        public Criteria andReviewtimeNotEqualTo(Date value) {
            addCriterion("reviewtime <>", value, "reviewtime");
            return (Criteria) this;
        }

        public Criteria andReviewtimeGreaterThan(Date value) {
            addCriterion("reviewtime >", value, "reviewtime");
            return (Criteria) this;
        }

        public Criteria andReviewtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("reviewtime >=", value, "reviewtime");
            return (Criteria) this;
        }

        public Criteria andReviewtimeLessThan(Date value) {
            addCriterion("reviewtime <", value, "reviewtime");
            return (Criteria) this;
        }

        public Criteria andReviewtimeLessThanOrEqualTo(Date value) {
            addCriterion("reviewtime <=", value, "reviewtime");
            return (Criteria) this;
        }

        public Criteria andReviewtimeIn(List<Date> values) {
            addCriterion("reviewtime in", values, "reviewtime");
            return (Criteria) this;
        }

        public Criteria andReviewtimeNotIn(List<Date> values) {
            addCriterion("reviewtime not in", values, "reviewtime");
            return (Criteria) this;
        }

        public Criteria andReviewtimeBetween(Date value1, Date value2) {
            addCriterion("reviewtime between", value1, value2, "reviewtime");
            return (Criteria) this;
        }

        public Criteria andReviewtimeNotBetween(Date value1, Date value2) {
            addCriterion("reviewtime not between", value1, value2, "reviewtime");
            return (Criteria) this;
        }

        public Criteria andMainserverIsNull() {
            addCriterion("mainserver is null");
            return (Criteria) this;
        }

        public Criteria andMainserverIsNotNull() {
            addCriterion("mainserver is not null");
            return (Criteria) this;
        }

        public Criteria andMainserverEqualTo(String value) {
            addCriterion("mainserver =", value, "mainserver");
            return (Criteria) this;
        }

        public Criteria andMainserverNotEqualTo(String value) {
            addCriterion("mainserver <>", value, "mainserver");
            return (Criteria) this;
        }

        public Criteria andMainserverGreaterThan(String value) {
            addCriterion("mainserver >", value, "mainserver");
            return (Criteria) this;
        }

        public Criteria andMainserverGreaterThanOrEqualTo(String value) {
            addCriterion("mainserver >=", value, "mainserver");
            return (Criteria) this;
        }

        public Criteria andMainserverLessThan(String value) {
            addCriterion("mainserver <", value, "mainserver");
            return (Criteria) this;
        }

        public Criteria andMainserverLessThanOrEqualTo(String value) {
            addCriterion("mainserver <=", value, "mainserver");
            return (Criteria) this;
        }

        public Criteria andMainserverLike(String value) {
            addCriterion("mainserver like", value, "mainserver");
            return (Criteria) this;
        }

        public Criteria andMainserverNotLike(String value) {
            addCriterion("mainserver not like", value, "mainserver");
            return (Criteria) this;
        }

        public Criteria andMainserverIn(List<String> values) {
            addCriterion("mainserver in", values, "mainserver");
            return (Criteria) this;
        }

        public Criteria andMainserverNotIn(List<String> values) {
            addCriterion("mainserver not in", values, "mainserver");
            return (Criteria) this;
        }

        public Criteria andMainserverBetween(String value1, String value2) {
            addCriterion("mainserver between", value1, value2, "mainserver");
            return (Criteria) this;
        }

        public Criteria andMainserverNotBetween(String value1, String value2) {
            addCriterion("mainserver not between", value1, value2, "mainserver");
            return (Criteria) this;
        }

        public Criteria andAssistserverIsNull() {
            addCriterion("assistserver is null");
            return (Criteria) this;
        }

        public Criteria andAssistserverIsNotNull() {
            addCriterion("assistserver is not null");
            return (Criteria) this;
        }

        public Criteria andAssistserverEqualTo(String value) {
            addCriterion("assistserver =", value, "assistserver");
            return (Criteria) this;
        }

        public Criteria andAssistserverNotEqualTo(String value) {
            addCriterion("assistserver <>", value, "assistserver");
            return (Criteria) this;
        }

        public Criteria andAssistserverGreaterThan(String value) {
            addCriterion("assistserver >", value, "assistserver");
            return (Criteria) this;
        }

        public Criteria andAssistserverGreaterThanOrEqualTo(String value) {
            addCriterion("assistserver >=", value, "assistserver");
            return (Criteria) this;
        }

        public Criteria andAssistserverLessThan(String value) {
            addCriterion("assistserver <", value, "assistserver");
            return (Criteria) this;
        }

        public Criteria andAssistserverLessThanOrEqualTo(String value) {
            addCriterion("assistserver <=", value, "assistserver");
            return (Criteria) this;
        }

        public Criteria andAssistserverLike(String value) {
            addCriterion("assistserver like", value, "assistserver");
            return (Criteria) this;
        }

        public Criteria andAssistserverNotLike(String value) {
            addCriterion("assistserver not like", value, "assistserver");
            return (Criteria) this;
        }

        public Criteria andAssistserverIn(List<String> values) {
            addCriterion("assistserver in", values, "assistserver");
            return (Criteria) this;
        }

        public Criteria andAssistserverNotIn(List<String> values) {
            addCriterion("assistserver not in", values, "assistserver");
            return (Criteria) this;
        }

        public Criteria andAssistserverBetween(String value1, String value2) {
            addCriterion("assistserver between", value1, value2, "assistserver");
            return (Criteria) this;
        }

        public Criteria andAssistserverNotBetween(String value1, String value2) {
            addCriterion("assistserver not between", value1, value2, "assistserver");
            return (Criteria) this;
        }

        public Criteria andMainserveridIsNull() {
            addCriterion("mainserverid is null");
            return (Criteria) this;
        }

        public Criteria andMainserveridIsNotNull() {
            addCriterion("mainserverid is not null");
            return (Criteria) this;
        }

        public Criteria andMainserveridEqualTo(Long value) {
            addCriterion("mainserverid =", value, "mainserverid");
            return (Criteria) this;
        }

        public Criteria andMainserveridNotEqualTo(Long value) {
            addCriterion("mainserverid <>", value, "mainserverid");
            return (Criteria) this;
        }

        public Criteria andMainserveridGreaterThan(Long value) {
            addCriterion("mainserverid >", value, "mainserverid");
            return (Criteria) this;
        }

        public Criteria andMainserveridGreaterThanOrEqualTo(Long value) {
            addCriterion("mainserverid >=", value, "mainserverid");
            return (Criteria) this;
        }

        public Criteria andMainserveridLessThan(Long value) {
            addCriterion("mainserverid <", value, "mainserverid");
            return (Criteria) this;
        }

        public Criteria andMainserveridLessThanOrEqualTo(Long value) {
            addCriterion("mainserverid <=", value, "mainserverid");
            return (Criteria) this;
        }

        public Criteria andMainserveridIn(List<Long> values) {
            addCriterion("mainserverid in", values, "mainserverid");
            return (Criteria) this;
        }

        public Criteria andMainserveridNotIn(List<Long> values) {
            addCriterion("mainserverid not in", values, "mainserverid");
            return (Criteria) this;
        }

        public Criteria andMainserveridBetween(Long value1, Long value2) {
            addCriterion("mainserverid between", value1, value2, "mainserverid");
            return (Criteria) this;
        }

        public Criteria andMainserveridNotBetween(Long value1, Long value2) {
            addCriterion("mainserverid not between", value1, value2, "mainserverid");
            return (Criteria) this;
        }

        public Criteria andAssistserveridIsNull() {
            addCriterion("assistserverid is null");
            return (Criteria) this;
        }

        public Criteria andAssistserveridIsNotNull() {
            addCriterion("assistserverid is not null");
            return (Criteria) this;
        }

        public Criteria andAssistserveridEqualTo(Long value) {
            addCriterion("assistserverid =", value, "assistserverid");
            return (Criteria) this;
        }

        public Criteria andAssistserveridNotEqualTo(Long value) {
            addCriterion("assistserverid <>", value, "assistserverid");
            return (Criteria) this;
        }

        public Criteria andAssistserveridGreaterThan(Long value) {
            addCriterion("assistserverid >", value, "assistserverid");
            return (Criteria) this;
        }

        public Criteria andAssistserveridGreaterThanOrEqualTo(Long value) {
            addCriterion("assistserverid >=", value, "assistserverid");
            return (Criteria) this;
        }

        public Criteria andAssistserveridLessThan(Long value) {
            addCriterion("assistserverid <", value, "assistserverid");
            return (Criteria) this;
        }

        public Criteria andAssistserveridLessThanOrEqualTo(Long value) {
            addCriterion("assistserverid <=", value, "assistserverid");
            return (Criteria) this;
        }

        public Criteria andAssistserveridIn(List<Long> values) {
            addCriterion("assistserverid in", values, "assistserverid");
            return (Criteria) this;
        }

        public Criteria andAssistserveridNotIn(List<Long> values) {
            addCriterion("assistserverid not in", values, "assistserverid");
            return (Criteria) this;
        }

        public Criteria andAssistserveridBetween(Long value1, Long value2) {
            addCriterion("assistserverid between", value1, value2, "assistserverid");
            return (Criteria) this;
        }

        public Criteria andAssistserveridNotBetween(Long value1, Long value2) {
            addCriterion("assistserverid not between", value1, value2, "assistserverid");
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

        public Criteria andExpectarrivaltimeIsNull() {
            addCriterion("expectarrivaltime is null");
            return (Criteria) this;
        }

        public Criteria andExpectarrivaltimeIsNotNull() {
            addCriterion("expectarrivaltime is not null");
            return (Criteria) this;
        }

        public Criteria andExpectarrivaltimeEqualTo(Date value) {
            addCriterion("expectarrivaltime =", value, "expectarrivaltime");
            return (Criteria) this;
        }

        public Criteria andExpectarrivaltimeNotEqualTo(Date value) {
            addCriterion("expectarrivaltime <>", value, "expectarrivaltime");
            return (Criteria) this;
        }

        public Criteria andExpectarrivaltimeGreaterThan(Date value) {
            addCriterion("expectarrivaltime >", value, "expectarrivaltime");
            return (Criteria) this;
        }

        public Criteria andExpectarrivaltimeGreaterThanOrEqualTo(Date value) {
            addCriterion("expectarrivaltime >=", value, "expectarrivaltime");
            return (Criteria) this;
        }

        public Criteria andExpectarrivaltimeLessThan(Date value) {
            addCriterion("expectarrivaltime <", value, "expectarrivaltime");
            return (Criteria) this;
        }

        public Criteria andExpectarrivaltimeLessThanOrEqualTo(Date value) {
            addCriterion("expectarrivaltime <=", value, "expectarrivaltime");
            return (Criteria) this;
        }

        public Criteria andExpectarrivaltimeIn(List<Date> values) {
            addCriterion("expectarrivaltime in", values, "expectarrivaltime");
            return (Criteria) this;
        }

        public Criteria andExpectarrivaltimeNotIn(List<Date> values) {
            addCriterion("expectarrivaltime not in", values, "expectarrivaltime");
            return (Criteria) this;
        }

        public Criteria andExpectarrivaltimeBetween(Date value1, Date value2) {
            addCriterion("expectarrivaltime between", value1, value2, "expectarrivaltime");
            return (Criteria) this;
        }

        public Criteria andExpectarrivaltimeNotBetween(Date value1, Date value2) {
            addCriterion("expectarrivaltime not between", value1, value2, "expectarrivaltime");
            return (Criteria) this;
        }

        public Criteria andAcceptdaysIsNull() {
            addCriterion("acceptdays is null");
            return (Criteria) this;
        }

        public Criteria andAcceptdaysIsNotNull() {
            addCriterion("acceptdays is not null");
            return (Criteria) this;
        }

        public Criteria andAcceptdaysEqualTo(Integer value) {
            addCriterion("acceptdays =", value, "acceptdays");
            return (Criteria) this;
        }

        public Criteria andAcceptdaysNotEqualTo(Integer value) {
            addCriterion("acceptdays <>", value, "acceptdays");
            return (Criteria) this;
        }

        public Criteria andAcceptdaysGreaterThan(Integer value) {
            addCriterion("acceptdays >", value, "acceptdays");
            return (Criteria) this;
        }

        public Criteria andAcceptdaysGreaterThanOrEqualTo(Integer value) {
            addCriterion("acceptdays >=", value, "acceptdays");
            return (Criteria) this;
        }

        public Criteria andAcceptdaysLessThan(Integer value) {
            addCriterion("acceptdays <", value, "acceptdays");
            return (Criteria) this;
        }

        public Criteria andAcceptdaysLessThanOrEqualTo(Integer value) {
            addCriterion("acceptdays <=", value, "acceptdays");
            return (Criteria) this;
        }

        public Criteria andAcceptdaysIn(List<Integer> values) {
            addCriterion("acceptdays in", values, "acceptdays");
            return (Criteria) this;
        }

        public Criteria andAcceptdaysNotIn(List<Integer> values) {
            addCriterion("acceptdays not in", values, "acceptdays");
            return (Criteria) this;
        }

        public Criteria andAcceptdaysBetween(Integer value1, Integer value2) {
            addCriterion("acceptdays between", value1, value2, "acceptdays");
            return (Criteria) this;
        }

        public Criteria andAcceptdaysNotBetween(Integer value1, Integer value2) {
            addCriterion("acceptdays not between", value1, value2, "acceptdays");
            return (Criteria) this;
        }

        public Criteria andEnteringIsNull() {
            addCriterion("entering is null");
            return (Criteria) this;
        }

        public Criteria andEnteringIsNotNull() {
            addCriterion("entering is not null");
            return (Criteria) this;
        }

        public Criteria andEnteringEqualTo(String value) {
            addCriterion("entering =", value, "entering");
            return (Criteria) this;
        }

        public Criteria andEnteringNotEqualTo(String value) {
            addCriterion("entering <>", value, "entering");
            return (Criteria) this;
        }

        public Criteria andEnteringGreaterThan(String value) {
            addCriterion("entering >", value, "entering");
            return (Criteria) this;
        }

        public Criteria andEnteringGreaterThanOrEqualTo(String value) {
            addCriterion("entering >=", value, "entering");
            return (Criteria) this;
        }

        public Criteria andEnteringLessThan(String value) {
            addCriterion("entering <", value, "entering");
            return (Criteria) this;
        }

        public Criteria andEnteringLessThanOrEqualTo(String value) {
            addCriterion("entering <=", value, "entering");
            return (Criteria) this;
        }

        public Criteria andEnteringLike(String value) {
            addCriterion("entering like", value, "entering");
            return (Criteria) this;
        }

        public Criteria andEnteringNotLike(String value) {
            addCriterion("entering not like", value, "entering");
            return (Criteria) this;
        }

        public Criteria andEnteringIn(List<String> values) {
            addCriterion("entering in", values, "entering");
            return (Criteria) this;
        }

        public Criteria andEnteringNotIn(List<String> values) {
            addCriterion("entering not in", values, "entering");
            return (Criteria) this;
        }

        public Criteria andEnteringBetween(String value1, String value2) {
            addCriterion("entering between", value1, value2, "entering");
            return (Criteria) this;
        }

        public Criteria andEnteringNotBetween(String value1, String value2) {
            addCriterion("entering not between", value1, value2, "entering");
            return (Criteria) this;
        }

        public Criteria andEnteringidIsNull() {
            addCriterion("enteringid is null");
            return (Criteria) this;
        }

        public Criteria andEnteringidIsNotNull() {
            addCriterion("enteringid is not null");
            return (Criteria) this;
        }

        public Criteria andEnteringidEqualTo(Long value) {
            addCriterion("enteringid =", value, "enteringid");
            return (Criteria) this;
        }

        public Criteria andEnteringidNotEqualTo(Long value) {
            addCriterion("enteringid <>", value, "enteringid");
            return (Criteria) this;
        }

        public Criteria andEnteringidGreaterThan(Long value) {
            addCriterion("enteringid >", value, "enteringid");
            return (Criteria) this;
        }

        public Criteria andEnteringidGreaterThanOrEqualTo(Long value) {
            addCriterion("enteringid >=", value, "enteringid");
            return (Criteria) this;
        }

        public Criteria andEnteringidLessThan(Long value) {
            addCriterion("enteringid <", value, "enteringid");
            return (Criteria) this;
        }

        public Criteria andEnteringidLessThanOrEqualTo(Long value) {
            addCriterion("enteringid <=", value, "enteringid");
            return (Criteria) this;
        }

        public Criteria andEnteringidIn(List<Long> values) {
            addCriterion("enteringid in", values, "enteringid");
            return (Criteria) this;
        }

        public Criteria andEnteringidNotIn(List<Long> values) {
            addCriterion("enteringid not in", values, "enteringid");
            return (Criteria) this;
        }

        public Criteria andEnteringidBetween(Long value1, Long value2) {
            addCriterion("enteringid between", value1, value2, "enteringid");
            return (Criteria) this;
        }

        public Criteria andEnteringidNotBetween(Long value1, Long value2) {
            addCriterion("enteringid not between", value1, value2, "enteringid");
            return (Criteria) this;
        }

        public Criteria andAgreementnoIsNull() {
            addCriterion("agreementno is null");
            return (Criteria) this;
        }

        public Criteria andAgreementnoIsNotNull() {
            addCriterion("agreementno is not null");
            return (Criteria) this;
        }

        public Criteria andAgreementnoEqualTo(String value) {
            addCriterion("agreementno =", value, "agreementno");
            return (Criteria) this;
        }

        public Criteria andAgreementnoNotEqualTo(String value) {
            addCriterion("agreementno <>", value, "agreementno");
            return (Criteria) this;
        }

        public Criteria andAgreementnoGreaterThan(String value) {
            addCriterion("agreementno >", value, "agreementno");
            return (Criteria) this;
        }

        public Criteria andAgreementnoGreaterThanOrEqualTo(String value) {
            addCriterion("agreementno >=", value, "agreementno");
            return (Criteria) this;
        }

        public Criteria andAgreementnoLessThan(String value) {
            addCriterion("agreementno <", value, "agreementno");
            return (Criteria) this;
        }

        public Criteria andAgreementnoLessThanOrEqualTo(String value) {
            addCriterion("agreementno <=", value, "agreementno");
            return (Criteria) this;
        }

        public Criteria andAgreementnoLike(String value) {
            addCriterion("agreementno like", value, "agreementno");
            return (Criteria) this;
        }

        public Criteria andAgreementnoNotLike(String value) {
            addCriterion("agreementno not like", value, "agreementno");
            return (Criteria) this;
        }

        public Criteria andAgreementnoIn(List<String> values) {
            addCriterion("agreementno in", values, "agreementno");
            return (Criteria) this;
        }

        public Criteria andAgreementnoNotIn(List<String> values) {
            addCriterion("agreementno not in", values, "agreementno");
            return (Criteria) this;
        }

        public Criteria andAgreementnoBetween(String value1, String value2) {
            addCriterion("agreementno between", value1, value2, "agreementno");
            return (Criteria) this;
        }

        public Criteria andAgreementnoNotBetween(String value1, String value2) {
            addCriterion("agreementno not between", value1, value2, "agreementno");
            return (Criteria) this;
        }

        public Criteria andSigntimeIsNull() {
            addCriterion("signtime is null");
            return (Criteria) this;
        }

        public Criteria andSigntimeIsNotNull() {
            addCriterion("signtime is not null");
            return (Criteria) this;
        }

        public Criteria andSigntimeEqualTo(Date value) {
            addCriterion("signtime =", value, "signtime");
            return (Criteria) this;
        }

        public Criteria andSigntimeNotEqualTo(Date value) {
            addCriterion("signtime <>", value, "signtime");
            return (Criteria) this;
        }

        public Criteria andSigntimeGreaterThan(Date value) {
            addCriterion("signtime >", value, "signtime");
            return (Criteria) this;
        }

        public Criteria andSigntimeGreaterThanOrEqualTo(Date value) {
            addCriterion("signtime >=", value, "signtime");
            return (Criteria) this;
        }

        public Criteria andSigntimeLessThan(Date value) {
            addCriterion("signtime <", value, "signtime");
            return (Criteria) this;
        }

        public Criteria andSigntimeLessThanOrEqualTo(Date value) {
            addCriterion("signtime <=", value, "signtime");
            return (Criteria) this;
        }

        public Criteria andSigntimeIn(List<Date> values) {
            addCriterion("signtime in", values, "signtime");
            return (Criteria) this;
        }

        public Criteria andSigntimeNotIn(List<Date> values) {
            addCriterion("signtime not in", values, "signtime");
            return (Criteria) this;
        }

        public Criteria andSigntimeBetween(Date value1, Date value2) {
            addCriterion("signtime between", value1, value2, "signtime");
            return (Criteria) this;
        }

        public Criteria andSigntimeNotBetween(Date value1, Date value2) {
            addCriterion("signtime not between", value1, value2, "signtime");
            return (Criteria) this;
        }

        public Criteria andSignaddrIsNull() {
            addCriterion("signaddr is null");
            return (Criteria) this;
        }

        public Criteria andSignaddrIsNotNull() {
            addCriterion("signaddr is not null");
            return (Criteria) this;
        }

        public Criteria andSignaddrEqualTo(String value) {
            addCriterion("signaddr =", value, "signaddr");
            return (Criteria) this;
        }

        public Criteria andSignaddrNotEqualTo(String value) {
            addCriterion("signaddr <>", value, "signaddr");
            return (Criteria) this;
        }

        public Criteria andSignaddrGreaterThan(String value) {
            addCriterion("signaddr >", value, "signaddr");
            return (Criteria) this;
        }

        public Criteria andSignaddrGreaterThanOrEqualTo(String value) {
            addCriterion("signaddr >=", value, "signaddr");
            return (Criteria) this;
        }

        public Criteria andSignaddrLessThan(String value) {
            addCriterion("signaddr <", value, "signaddr");
            return (Criteria) this;
        }

        public Criteria andSignaddrLessThanOrEqualTo(String value) {
            addCriterion("signaddr <=", value, "signaddr");
            return (Criteria) this;
        }

        public Criteria andSignaddrLike(String value) {
            addCriterion("signaddr like", value, "signaddr");
            return (Criteria) this;
        }

        public Criteria andSignaddrNotLike(String value) {
            addCriterion("signaddr not like", value, "signaddr");
            return (Criteria) this;
        }

        public Criteria andSignaddrIn(List<String> values) {
            addCriterion("signaddr in", values, "signaddr");
            return (Criteria) this;
        }

        public Criteria andSignaddrNotIn(List<String> values) {
            addCriterion("signaddr not in", values, "signaddr");
            return (Criteria) this;
        }

        public Criteria andSignaddrBetween(String value1, String value2) {
            addCriterion("signaddr between", value1, value2, "signaddr");
            return (Criteria) this;
        }

        public Criteria andSignaddrNotBetween(String value1, String value2) {
            addCriterion("signaddr not between", value1, value2, "signaddr");
            return (Criteria) this;
        }

        public Criteria andAgreementfileIsNull() {
            addCriterion("agreementfile is null");
            return (Criteria) this;
        }

        public Criteria andAgreementfileIsNotNull() {
            addCriterion("agreementfile is not null");
            return (Criteria) this;
        }

        public Criteria andAgreementfileEqualTo(String value) {
            addCriterion("agreementfile =", value, "agreementfile");
            return (Criteria) this;
        }

        public Criteria andAgreementfileNotEqualTo(String value) {
            addCriterion("agreementfile <>", value, "agreementfile");
            return (Criteria) this;
        }

        public Criteria andAgreementfileGreaterThan(String value) {
            addCriterion("agreementfile >", value, "agreementfile");
            return (Criteria) this;
        }

        public Criteria andAgreementfileGreaterThanOrEqualTo(String value) {
            addCriterion("agreementfile >=", value, "agreementfile");
            return (Criteria) this;
        }

        public Criteria andAgreementfileLessThan(String value) {
            addCriterion("agreementfile <", value, "agreementfile");
            return (Criteria) this;
        }

        public Criteria andAgreementfileLessThanOrEqualTo(String value) {
            addCriterion("agreementfile <=", value, "agreementfile");
            return (Criteria) this;
        }

        public Criteria andAgreementfileLike(String value) {
            addCriterion("agreementfile like", value, "agreementfile");
            return (Criteria) this;
        }

        public Criteria andAgreementfileNotLike(String value) {
            addCriterion("agreementfile not like", value, "agreementfile");
            return (Criteria) this;
        }

        public Criteria andAgreementfileIn(List<String> values) {
            addCriterion("agreementfile in", values, "agreementfile");
            return (Criteria) this;
        }

        public Criteria andAgreementfileNotIn(List<String> values) {
            addCriterion("agreementfile not in", values, "agreementfile");
            return (Criteria) this;
        }

        public Criteria andAgreementfileBetween(String value1, String value2) {
            addCriterion("agreementfile between", value1, value2, "agreementfile");
            return (Criteria) this;
        }

        public Criteria andAgreementfileNotBetween(String value1, String value2) {
            addCriterion("agreementfile not between", value1, value2, "agreementfile");
            return (Criteria) this;
        }

        public Criteria andApplymoneyIsNull() {
            addCriterion("applymoney is null");
            return (Criteria) this;
        }

        public Criteria andApplymoneyIsNotNull() {
            addCriterion("applymoney is not null");
            return (Criteria) this;
        }

        public Criteria andApplymoneyEqualTo(BigDecimal value) {
            addCriterion("applymoney =", value, "applymoney");
            return (Criteria) this;
        }

        public Criteria andApplymoneyNotEqualTo(BigDecimal value) {
            addCriterion("applymoney <>", value, "applymoney");
            return (Criteria) this;
        }

        public Criteria andApplymoneyGreaterThan(BigDecimal value) {
            addCriterion("applymoney >", value, "applymoney");
            return (Criteria) this;
        }

        public Criteria andApplymoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("applymoney >=", value, "applymoney");
            return (Criteria) this;
        }

        public Criteria andApplymoneyLessThan(BigDecimal value) {
            addCriterion("applymoney <", value, "applymoney");
            return (Criteria) this;
        }

        public Criteria andApplymoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("applymoney <=", value, "applymoney");
            return (Criteria) this;
        }

        public Criteria andApplymoneyIn(List<BigDecimal> values) {
            addCriterion("applymoney in", values, "applymoney");
            return (Criteria) this;
        }

        public Criteria andApplymoneyNotIn(List<BigDecimal> values) {
            addCriterion("applymoney not in", values, "applymoney");
            return (Criteria) this;
        }

        public Criteria andApplymoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("applymoney between", value1, value2, "applymoney");
            return (Criteria) this;
        }

        public Criteria andApplymoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("applymoney not between", value1, value2, "applymoney");
            return (Criteria) this;
        }

        public Criteria andCancelreasonIsNull() {
            addCriterion("cancelreason is null");
            return (Criteria) this;
        }

        public Criteria andCancelreasonIsNotNull() {
            addCriterion("cancelreason is not null");
            return (Criteria) this;
        }

        public Criteria andCancelreasonEqualTo(String value) {
            addCriterion("cancelreason =", value, "cancelreason");
            return (Criteria) this;
        }

        public Criteria andCancelreasonNotEqualTo(String value) {
            addCriterion("cancelreason <>", value, "cancelreason");
            return (Criteria) this;
        }

        public Criteria andCancelreasonGreaterThan(String value) {
            addCriterion("cancelreason >", value, "cancelreason");
            return (Criteria) this;
        }

        public Criteria andCancelreasonGreaterThanOrEqualTo(String value) {
            addCriterion("cancelreason >=", value, "cancelreason");
            return (Criteria) this;
        }

        public Criteria andCancelreasonLessThan(String value) {
            addCriterion("cancelreason <", value, "cancelreason");
            return (Criteria) this;
        }

        public Criteria andCancelreasonLessThanOrEqualTo(String value) {
            addCriterion("cancelreason <=", value, "cancelreason");
            return (Criteria) this;
        }

        public Criteria andCancelreasonLike(String value) {
            addCriterion("cancelreason like", value, "cancelreason");
            return (Criteria) this;
        }

        public Criteria andCancelreasonNotLike(String value) {
            addCriterion("cancelreason not like", value, "cancelreason");
            return (Criteria) this;
        }

        public Criteria andCancelreasonIn(List<String> values) {
            addCriterion("cancelreason in", values, "cancelreason");
            return (Criteria) this;
        }

        public Criteria andCancelreasonNotIn(List<String> values) {
            addCriterion("cancelreason not in", values, "cancelreason");
            return (Criteria) this;
        }

        public Criteria andCancelreasonBetween(String value1, String value2) {
            addCriterion("cancelreason between", value1, value2, "cancelreason");
            return (Criteria) this;
        }

        public Criteria andCancelreasonNotBetween(String value1, String value2) {
            addCriterion("cancelreason not between", value1, value2, "cancelreason");
            return (Criteria) this;
        }

        public Criteria andReviewnotesIsNull() {
            addCriterion("reviewnotes is null");
            return (Criteria) this;
        }

        public Criteria andReviewnotesIsNotNull() {
            addCriterion("reviewnotes is not null");
            return (Criteria) this;
        }

        public Criteria andReviewnotesEqualTo(String value) {
            addCriterion("reviewnotes =", value, "reviewnotes");
            return (Criteria) this;
        }

        public Criteria andReviewnotesNotEqualTo(String value) {
            addCriterion("reviewnotes <>", value, "reviewnotes");
            return (Criteria) this;
        }

        public Criteria andReviewnotesGreaterThan(String value) {
            addCriterion("reviewnotes >", value, "reviewnotes");
            return (Criteria) this;
        }

        public Criteria andReviewnotesGreaterThanOrEqualTo(String value) {
            addCriterion("reviewnotes >=", value, "reviewnotes");
            return (Criteria) this;
        }

        public Criteria andReviewnotesLessThan(String value) {
            addCriterion("reviewnotes <", value, "reviewnotes");
            return (Criteria) this;
        }

        public Criteria andReviewnotesLessThanOrEqualTo(String value) {
            addCriterion("reviewnotes <=", value, "reviewnotes");
            return (Criteria) this;
        }

        public Criteria andReviewnotesLike(String value) {
            addCriterion("reviewnotes like", value, "reviewnotes");
            return (Criteria) this;
        }

        public Criteria andReviewnotesNotLike(String value) {
            addCriterion("reviewnotes not like", value, "reviewnotes");
            return (Criteria) this;
        }

        public Criteria andReviewnotesIn(List<String> values) {
            addCriterion("reviewnotes in", values, "reviewnotes");
            return (Criteria) this;
        }

        public Criteria andReviewnotesNotIn(List<String> values) {
            addCriterion("reviewnotes not in", values, "reviewnotes");
            return (Criteria) this;
        }

        public Criteria andReviewnotesBetween(String value1, String value2) {
            addCriterion("reviewnotes between", value1, value2, "reviewnotes");
            return (Criteria) this;
        }

        public Criteria andReviewnotesNotBetween(String value1, String value2) {
            addCriterion("reviewnotes not between", value1, value2, "reviewnotes");
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