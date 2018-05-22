package project.jinshang.mod_bankaccount.bean;

import java.util.ArrayList;
import java.util.List;

public class BankAccountExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BankAccountExample() {
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

        public Criteria andBankaccountnameIsNull() {
            addCriterion("bankaccountname is null");
            return (Criteria) this;
        }

        public Criteria andBankaccountnameIsNotNull() {
            addCriterion("bankaccountname is not null");
            return (Criteria) this;
        }

        public Criteria andBankaccountnameEqualTo(String value) {
            addCriterion("bankaccountname =", value, "bankaccountname");
            return (Criteria) this;
        }

        public Criteria andBankaccountnameNotEqualTo(String value) {
            addCriterion("bankaccountname <>", value, "bankaccountname");
            return (Criteria) this;
        }

        public Criteria andBankaccountnameGreaterThan(String value) {
            addCriterion("bankaccountname >", value, "bankaccountname");
            return (Criteria) this;
        }

        public Criteria andBankaccountnameGreaterThanOrEqualTo(String value) {
            addCriterion("bankaccountname >=", value, "bankaccountname");
            return (Criteria) this;
        }

        public Criteria andBankaccountnameLessThan(String value) {
            addCriterion("bankaccountname <", value, "bankaccountname");
            return (Criteria) this;
        }

        public Criteria andBankaccountnameLessThanOrEqualTo(String value) {
            addCriterion("bankaccountname <=", value, "bankaccountname");
            return (Criteria) this;
        }

        public Criteria andBankaccountnameLike(String value) {
            addCriterion("bankaccountname like", value, "bankaccountname");
            return (Criteria) this;
        }

        public Criteria andBankaccountnameNotLike(String value) {
            addCriterion("bankaccountname not like", value, "bankaccountname");
            return (Criteria) this;
        }

        public Criteria andBankaccountnameIn(List<String> values) {
            addCriterion("bankaccountname in", values, "bankaccountname");
            return (Criteria) this;
        }

        public Criteria andBankaccountnameNotIn(List<String> values) {
            addCriterion("bankaccountname not in", values, "bankaccountname");
            return (Criteria) this;
        }

        public Criteria andBankaccountnameBetween(String value1, String value2) {
            addCriterion("bankaccountname between", value1, value2, "bankaccountname");
            return (Criteria) this;
        }

        public Criteria andBankaccountnameNotBetween(String value1, String value2) {
            addCriterion("bankaccountname not between", value1, value2, "bankaccountname");
            return (Criteria) this;
        }

        public Criteria andBankaccountnumberIsNull() {
            addCriterion("bankaccountnumber is null");
            return (Criteria) this;
        }

        public Criteria andBankaccountnumberIsNotNull() {
            addCriterion("bankaccountnumber is not null");
            return (Criteria) this;
        }

        public Criteria andBankaccountnumberEqualTo(String value) {
            addCriterion("bankaccountnumber =", value, "bankaccountnumber");
            return (Criteria) this;
        }

        public Criteria andBankaccountnumberNotEqualTo(String value) {
            addCriterion("bankaccountnumber <>", value, "bankaccountnumber");
            return (Criteria) this;
        }

        public Criteria andBankaccountnumberGreaterThan(String value) {
            addCriterion("bankaccountnumber >", value, "bankaccountnumber");
            return (Criteria) this;
        }

        public Criteria andBankaccountnumberGreaterThanOrEqualTo(String value) {
            addCriterion("bankaccountnumber >=", value, "bankaccountnumber");
            return (Criteria) this;
        }

        public Criteria andBankaccountnumberLessThan(String value) {
            addCriterion("bankaccountnumber <", value, "bankaccountnumber");
            return (Criteria) this;
        }

        public Criteria andBankaccountnumberLessThanOrEqualTo(String value) {
            addCriterion("bankaccountnumber <=", value, "bankaccountnumber");
            return (Criteria) this;
        }

        public Criteria andBankaccountnumberLike(String value) {
            addCriterion("bankaccountnumber like", value, "bankaccountnumber");
            return (Criteria) this;
        }

        public Criteria andBankaccountnumberNotLike(String value) {
            addCriterion("bankaccountnumber not like", value, "bankaccountnumber");
            return (Criteria) this;
        }

        public Criteria andBankaccountnumberIn(List<String> values) {
            addCriterion("bankaccountnumber in", values, "bankaccountnumber");
            return (Criteria) this;
        }

        public Criteria andBankaccountnumberNotIn(List<String> values) {
            addCriterion("bankaccountnumber not in", values, "bankaccountnumber");
            return (Criteria) this;
        }

        public Criteria andBankaccountnumberBetween(String value1, String value2) {
            addCriterion("bankaccountnumber between", value1, value2, "bankaccountnumber");
            return (Criteria) this;
        }

        public Criteria andBankaccountnumberNotBetween(String value1, String value2) {
            addCriterion("bankaccountnumber not between", value1, value2, "bankaccountnumber");
            return (Criteria) this;
        }

        public Criteria andBanknameIsNull() {
            addCriterion("bankname is null");
            return (Criteria) this;
        }

        public Criteria andBanknameIsNotNull() {
            addCriterion("bankname is not null");
            return (Criteria) this;
        }

        public Criteria andBanknameEqualTo(String value) {
            addCriterion("bankname =", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameNotEqualTo(String value) {
            addCriterion("bankname <>", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameGreaterThan(String value) {
            addCriterion("bankname >", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameGreaterThanOrEqualTo(String value) {
            addCriterion("bankname >=", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameLessThan(String value) {
            addCriterion("bankname <", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameLessThanOrEqualTo(String value) {
            addCriterion("bankname <=", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameLike(String value) {
            addCriterion("bankname like", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameNotLike(String value) {
            addCriterion("bankname not like", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameIn(List<String> values) {
            addCriterion("bankname in", values, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameNotIn(List<String> values) {
            addCriterion("bankname not in", values, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameBetween(String value1, String value2) {
            addCriterion("bankname between", value1, value2, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameNotBetween(String value1, String value2) {
            addCriterion("bankname not between", value1, value2, "bankname");
            return (Criteria) this;
        }

        public Criteria andBankusernameIsNull() {
            addCriterion("bankusername is null");
            return (Criteria) this;
        }

        public Criteria andBankusernameIsNotNull() {
            addCriterion("bankusername is not null");
            return (Criteria) this;
        }

        public Criteria andBankusernameEqualTo(String value) {
            addCriterion("bankusername =", value, "bankusername");
            return (Criteria) this;
        }

        public Criteria andBankusernameNotEqualTo(String value) {
            addCriterion("bankusername <>", value, "bankusername");
            return (Criteria) this;
        }

        public Criteria andBankusernameGreaterThan(String value) {
            addCriterion("bankusername >", value, "bankusername");
            return (Criteria) this;
        }

        public Criteria andBankusernameGreaterThanOrEqualTo(String value) {
            addCriterion("bankusername >=", value, "bankusername");
            return (Criteria) this;
        }

        public Criteria andBankusernameLessThan(String value) {
            addCriterion("bankusername <", value, "bankusername");
            return (Criteria) this;
        }

        public Criteria andBankusernameLessThanOrEqualTo(String value) {
            addCriterion("bankusername <=", value, "bankusername");
            return (Criteria) this;
        }

        public Criteria andBankusernameLike(String value) {
            addCriterion("bankusername like", value, "bankusername");
            return (Criteria) this;
        }

        public Criteria andBankusernameNotLike(String value) {
            addCriterion("bankusername not like", value, "bankusername");
            return (Criteria) this;
        }

        public Criteria andBankusernameIn(List<String> values) {
            addCriterion("bankusername in", values, "bankusername");
            return (Criteria) this;
        }

        public Criteria andBankusernameNotIn(List<String> values) {
            addCriterion("bankusername not in", values, "bankusername");
            return (Criteria) this;
        }

        public Criteria andBankusernameBetween(String value1, String value2) {
            addCriterion("bankusername between", value1, value2, "bankusername");
            return (Criteria) this;
        }

        public Criteria andBankusernameNotBetween(String value1, String value2) {
            addCriterion("bankusername not between", value1, value2, "bankusername");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationcertificateIsNull() {
            addCriterion("taxregistrationcertificate is null");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationcertificateIsNotNull() {
            addCriterion("taxregistrationcertificate is not null");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationcertificateEqualTo(String value) {
            addCriterion("taxregistrationcertificate =", value, "taxregistrationcertificate");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationcertificateNotEqualTo(String value) {
            addCriterion("taxregistrationcertificate <>", value, "taxregistrationcertificate");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationcertificateGreaterThan(String value) {
            addCriterion("taxregistrationcertificate >", value, "taxregistrationcertificate");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationcertificateGreaterThanOrEqualTo(String value) {
            addCriterion("taxregistrationcertificate >=", value, "taxregistrationcertificate");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationcertificateLessThan(String value) {
            addCriterion("taxregistrationcertificate <", value, "taxregistrationcertificate");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationcertificateLessThanOrEqualTo(String value) {
            addCriterion("taxregistrationcertificate <=", value, "taxregistrationcertificate");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationcertificateLike(String value) {
            addCriterion("taxregistrationcertificate like", value, "taxregistrationcertificate");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationcertificateNotLike(String value) {
            addCriterion("taxregistrationcertificate not like", value, "taxregistrationcertificate");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationcertificateIn(List<String> values) {
            addCriterion("taxregistrationcertificate in", values, "taxregistrationcertificate");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationcertificateNotIn(List<String> values) {
            addCriterion("taxregistrationcertificate not in", values, "taxregistrationcertificate");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationcertificateBetween(String value1, String value2) {
            addCriterion("taxregistrationcertificate between", value1, value2, "taxregistrationcertificate");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationcertificateNotBetween(String value1, String value2) {
            addCriterion("taxregistrationcertificate not between", value1, value2, "taxregistrationcertificate");
            return (Criteria) this;
        }

        public Criteria andIsdefaultIsNull() {
            addCriterion("isdefault is null");
            return (Criteria) this;
        }

        public Criteria andIsdefaultIsNotNull() {
            addCriterion("isdefault is not null");
            return (Criteria) this;
        }

        public Criteria andIsdefaultEqualTo(Short value) {
            addCriterion("isdefault =", value, "isdefault");
            return (Criteria) this;
        }

        public Criteria andIsdefaultNotEqualTo(Short value) {
            addCriterion("isdefault <>", value, "isdefault");
            return (Criteria) this;
        }

        public Criteria andIsdefaultGreaterThan(Short value) {
            addCriterion("isdefault >", value, "isdefault");
            return (Criteria) this;
        }

        public Criteria andIsdefaultGreaterThanOrEqualTo(Short value) {
            addCriterion("isdefault >=", value, "isdefault");
            return (Criteria) this;
        }

        public Criteria andIsdefaultLessThan(Short value) {
            addCriterion("isdefault <", value, "isdefault");
            return (Criteria) this;
        }

        public Criteria andIsdefaultLessThanOrEqualTo(Short value) {
            addCriterion("isdefault <=", value, "isdefault");
            return (Criteria) this;
        }

        public Criteria andIsdefaultIn(List<Short> values) {
            addCriterion("isdefault in", values, "isdefault");
            return (Criteria) this;
        }

        public Criteria andIsdefaultNotIn(List<Short> values) {
            addCriterion("isdefault not in", values, "isdefault");
            return (Criteria) this;
        }

        public Criteria andIsdefaultBetween(Short value1, Short value2) {
            addCriterion("isdefault between", value1, value2, "isdefault");
            return (Criteria) this;
        }

        public Criteria andIsdefaultNotBetween(Short value1, Short value2) {
            addCriterion("isdefault not between", value1, value2, "isdefault");
            return (Criteria) this;
        }

        public Criteria andBatypeIsNull() {
            addCriterion("batype is null");
            return (Criteria) this;
        }

        public Criteria andBatypeIsNotNull() {
            addCriterion("batype is not null");
            return (Criteria) this;
        }

        public Criteria andBatypeEqualTo(Short value) {
            addCriterion("batype =", value, "batype");
            return (Criteria) this;
        }

        public Criteria andBatypeNotEqualTo(Short value) {
            addCriterion("batype <>", value, "batype");
            return (Criteria) this;
        }

        public Criteria andBatypeGreaterThan(Short value) {
            addCriterion("batype >", value, "batype");
            return (Criteria) this;
        }

        public Criteria andBatypeGreaterThanOrEqualTo(Short value) {
            addCriterion("batype >=", value, "batype");
            return (Criteria) this;
        }

        public Criteria andBatypeLessThan(Short value) {
            addCriterion("batype <", value, "batype");
            return (Criteria) this;
        }

        public Criteria andBatypeLessThanOrEqualTo(Short value) {
            addCriterion("batype <=", value, "batype");
            return (Criteria) this;
        }

        public Criteria andBatypeIn(List<Short> values) {
            addCriterion("batype in", values, "batype");
            return (Criteria) this;
        }

        public Criteria andBatypeNotIn(List<Short> values) {
            addCriterion("batype not in", values, "batype");
            return (Criteria) this;
        }

        public Criteria andBatypeBetween(Short value1, Short value2) {
            addCriterion("batype between", value1, value2, "batype");
            return (Criteria) this;
        }

        public Criteria andBatypeNotBetween(Short value1, Short value2) {
            addCriterion("batype not between", value1, value2, "batype");
            return (Criteria) this;
        }

        public Criteria andIdCardIsNull() {
            addCriterion("id_card is null");
            return (Criteria) this;
        }

        public Criteria andIdCardIsNotNull() {
            addCriterion("id_card is not null");
            return (Criteria) this;
        }

        public Criteria andIdCardEqualTo(String value) {
            addCriterion("id_card =", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotEqualTo(String value) {
            addCriterion("id_card <>", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardGreaterThan(String value) {
            addCriterion("id_card >", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardGreaterThanOrEqualTo(String value) {
            addCriterion("id_card >=", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLessThan(String value) {
            addCriterion("id_card <", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLessThanOrEqualTo(String value) {
            addCriterion("id_card <=", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLike(String value) {
            addCriterion("id_card like", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotLike(String value) {
            addCriterion("id_card not like", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardIn(List<String> values) {
            addCriterion("id_card in", values, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotIn(List<String> values) {
            addCriterion("id_card not in", values, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardBetween(String value1, String value2) {
            addCriterion("id_card between", value1, value2, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotBetween(String value1, String value2) {
            addCriterion("id_card not between", value1, value2, "idCard");
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