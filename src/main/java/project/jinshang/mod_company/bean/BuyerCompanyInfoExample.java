package project.jinshang.mod_company.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BuyerCompanyInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BuyerCompanyInfoExample() {
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

        public Criteria andShortnameIsNull() {
            addCriterion("shortname is null");
            return (Criteria) this;
        }

        public Criteria andShortnameIsNotNull() {
            addCriterion("shortname is not null");
            return (Criteria) this;
        }

        public Criteria andShortnameEqualTo(String value) {
            addCriterion("shortname =", value, "shortname");
            return (Criteria) this;
        }

        public Criteria andShortnameNotEqualTo(String value) {
            addCriterion("shortname <>", value, "shortname");
            return (Criteria) this;
        }

        public Criteria andShortnameGreaterThan(String value) {
            addCriterion("shortname >", value, "shortname");
            return (Criteria) this;
        }

        public Criteria andShortnameGreaterThanOrEqualTo(String value) {
            addCriterion("shortname >=", value, "shortname");
            return (Criteria) this;
        }

        public Criteria andShortnameLessThan(String value) {
            addCriterion("shortname <", value, "shortname");
            return (Criteria) this;
        }

        public Criteria andShortnameLessThanOrEqualTo(String value) {
            addCriterion("shortname <=", value, "shortname");
            return (Criteria) this;
        }

        public Criteria andShortnameLike(String value) {
            addCriterion("shortname like", value, "shortname");
            return (Criteria) this;
        }

        public Criteria andShortnameNotLike(String value) {
            addCriterion("shortname not like", value, "shortname");
            return (Criteria) this;
        }

        public Criteria andShortnameIn(List<String> values) {
            addCriterion("shortname in", values, "shortname");
            return (Criteria) this;
        }

        public Criteria andShortnameNotIn(List<String> values) {
            addCriterion("shortname not in", values, "shortname");
            return (Criteria) this;
        }

        public Criteria andShortnameBetween(String value1, String value2) {
            addCriterion("shortname between", value1, value2, "shortname");
            return (Criteria) this;
        }

        public Criteria andShortnameNotBetween(String value1, String value2) {
            addCriterion("shortname not between", value1, value2, "shortname");
            return (Criteria) this;
        }

        public Criteria andCompanynameIsNull() {
            addCriterion("companyname is null");
            return (Criteria) this;
        }

        public Criteria andCompanynameIsNotNull() {
            addCriterion("companyname is not null");
            return (Criteria) this;
        }

        public Criteria andCompanynameEqualTo(String value) {
            addCriterion("companyname =", value, "companyname");
            return (Criteria) this;
        }

        public Criteria andCompanynameNotEqualTo(String value) {
            addCriterion("companyname <>", value, "companyname");
            return (Criteria) this;
        }

        public Criteria andCompanynameGreaterThan(String value) {
            addCriterion("companyname >", value, "companyname");
            return (Criteria) this;
        }

        public Criteria andCompanynameGreaterThanOrEqualTo(String value) {
            addCriterion("companyname >=", value, "companyname");
            return (Criteria) this;
        }

        public Criteria andCompanynameLessThan(String value) {
            addCriterion("companyname <", value, "companyname");
            return (Criteria) this;
        }

        public Criteria andCompanynameLessThanOrEqualTo(String value) {
            addCriterion("companyname <=", value, "companyname");
            return (Criteria) this;
        }

        public Criteria andCompanynameLike(String value) {
            addCriterion("companyname like", value, "companyname");
            return (Criteria) this;
        }

        public Criteria andCompanynameNotLike(String value) {
            addCriterion("companyname not like", value, "companyname");
            return (Criteria) this;
        }

        public Criteria andCompanynameIn(List<String> values) {
            addCriterion("companyname in", values, "companyname");
            return (Criteria) this;
        }

        public Criteria andCompanynameNotIn(List<String> values) {
            addCriterion("companyname not in", values, "companyname");
            return (Criteria) this;
        }

        public Criteria andCompanynameBetween(String value1, String value2) {
            addCriterion("companyname between", value1, value2, "companyname");
            return (Criteria) this;
        }

        public Criteria andCompanynameNotBetween(String value1, String value2) {
            addCriterion("companyname not between", value1, value2, "companyname");
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

        public Criteria andLegalpersonIsNull() {
            addCriterion("legalperson is null");
            return (Criteria) this;
        }

        public Criteria andLegalpersonIsNotNull() {
            addCriterion("legalperson is not null");
            return (Criteria) this;
        }

        public Criteria andLegalpersonEqualTo(String value) {
            addCriterion("legalperson =", value, "legalperson");
            return (Criteria) this;
        }

        public Criteria andLegalpersonNotEqualTo(String value) {
            addCriterion("legalperson <>", value, "legalperson");
            return (Criteria) this;
        }

        public Criteria andLegalpersonGreaterThan(String value) {
            addCriterion("legalperson >", value, "legalperson");
            return (Criteria) this;
        }

        public Criteria andLegalpersonGreaterThanOrEqualTo(String value) {
            addCriterion("legalperson >=", value, "legalperson");
            return (Criteria) this;
        }

        public Criteria andLegalpersonLessThan(String value) {
            addCriterion("legalperson <", value, "legalperson");
            return (Criteria) this;
        }

        public Criteria andLegalpersonLessThanOrEqualTo(String value) {
            addCriterion("legalperson <=", value, "legalperson");
            return (Criteria) this;
        }

        public Criteria andLegalpersonLike(String value) {
            addCriterion("legalperson like", value, "legalperson");
            return (Criteria) this;
        }

        public Criteria andLegalpersonNotLike(String value) {
            addCriterion("legalperson not like", value, "legalperson");
            return (Criteria) this;
        }

        public Criteria andLegalpersonIn(List<String> values) {
            addCriterion("legalperson in", values, "legalperson");
            return (Criteria) this;
        }

        public Criteria andLegalpersonNotIn(List<String> values) {
            addCriterion("legalperson not in", values, "legalperson");
            return (Criteria) this;
        }

        public Criteria andLegalpersonBetween(String value1, String value2) {
            addCriterion("legalperson between", value1, value2, "legalperson");
            return (Criteria) this;
        }

        public Criteria andLegalpersonNotBetween(String value1, String value2) {
            addCriterion("legalperson not between", value1, value2, "legalperson");
            return (Criteria) this;
        }

        public Criteria andMobileIsNull() {
            addCriterion("mobile is null");
            return (Criteria) this;
        }

        public Criteria andMobileIsNotNull() {
            addCriterion("mobile is not null");
            return (Criteria) this;
        }

        public Criteria andMobileEqualTo(String value) {
            addCriterion("mobile =", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotEqualTo(String value) {
            addCriterion("mobile <>", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThan(String value) {
            addCriterion("mobile >", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThanOrEqualTo(String value) {
            addCriterion("mobile >=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThan(String value) {
            addCriterion("mobile <", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThanOrEqualTo(String value) {
            addCriterion("mobile <=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLike(String value) {
            addCriterion("mobile like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotLike(String value) {
            addCriterion("mobile not like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileIn(List<String> values) {
            addCriterion("mobile in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotIn(List<String> values) {
            addCriterion("mobile not in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileBetween(String value1, String value2) {
            addCriterion("mobile between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotBetween(String value1, String value2) {
            addCriterion("mobile not between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andWorktelephoneIsNull() {
            addCriterion("worktelephone is null");
            return (Criteria) this;
        }

        public Criteria andWorktelephoneIsNotNull() {
            addCriterion("worktelephone is not null");
            return (Criteria) this;
        }

        public Criteria andWorktelephoneEqualTo(String value) {
            addCriterion("worktelephone =", value, "worktelephone");
            return (Criteria) this;
        }

        public Criteria andWorktelephoneNotEqualTo(String value) {
            addCriterion("worktelephone <>", value, "worktelephone");
            return (Criteria) this;
        }

        public Criteria andWorktelephoneGreaterThan(String value) {
            addCriterion("worktelephone >", value, "worktelephone");
            return (Criteria) this;
        }

        public Criteria andWorktelephoneGreaterThanOrEqualTo(String value) {
            addCriterion("worktelephone >=", value, "worktelephone");
            return (Criteria) this;
        }

        public Criteria andWorktelephoneLessThan(String value) {
            addCriterion("worktelephone <", value, "worktelephone");
            return (Criteria) this;
        }

        public Criteria andWorktelephoneLessThanOrEqualTo(String value) {
            addCriterion("worktelephone <=", value, "worktelephone");
            return (Criteria) this;
        }

        public Criteria andWorktelephoneLike(String value) {
            addCriterion("worktelephone like", value, "worktelephone");
            return (Criteria) this;
        }

        public Criteria andWorktelephoneNotLike(String value) {
            addCriterion("worktelephone not like", value, "worktelephone");
            return (Criteria) this;
        }

        public Criteria andWorktelephoneIn(List<String> values) {
            addCriterion("worktelephone in", values, "worktelephone");
            return (Criteria) this;
        }

        public Criteria andWorktelephoneNotIn(List<String> values) {
            addCriterion("worktelephone not in", values, "worktelephone");
            return (Criteria) this;
        }

        public Criteria andWorktelephoneBetween(String value1, String value2) {
            addCriterion("worktelephone between", value1, value2, "worktelephone");
            return (Criteria) this;
        }

        public Criteria andWorktelephoneNotBetween(String value1, String value2) {
            addCriterion("worktelephone not between", value1, value2, "worktelephone");
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

        public Criteria andBankaccountIsNull() {
            addCriterion("bankaccount is null");
            return (Criteria) this;
        }

        public Criteria andBankaccountIsNotNull() {
            addCriterion("bankaccount is not null");
            return (Criteria) this;
        }

        public Criteria andBankaccountEqualTo(String value) {
            addCriterion("bankaccount =", value, "bankaccount");
            return (Criteria) this;
        }

        public Criteria andBankaccountNotEqualTo(String value) {
            addCriterion("bankaccount <>", value, "bankaccount");
            return (Criteria) this;
        }

        public Criteria andBankaccountGreaterThan(String value) {
            addCriterion("bankaccount >", value, "bankaccount");
            return (Criteria) this;
        }

        public Criteria andBankaccountGreaterThanOrEqualTo(String value) {
            addCriterion("bankaccount >=", value, "bankaccount");
            return (Criteria) this;
        }

        public Criteria andBankaccountLessThan(String value) {
            addCriterion("bankaccount <", value, "bankaccount");
            return (Criteria) this;
        }

        public Criteria andBankaccountLessThanOrEqualTo(String value) {
            addCriterion("bankaccount <=", value, "bankaccount");
            return (Criteria) this;
        }

        public Criteria andBankaccountLike(String value) {
            addCriterion("bankaccount like", value, "bankaccount");
            return (Criteria) this;
        }

        public Criteria andBankaccountNotLike(String value) {
            addCriterion("bankaccount not like", value, "bankaccount");
            return (Criteria) this;
        }

        public Criteria andBankaccountIn(List<String> values) {
            addCriterion("bankaccount in", values, "bankaccount");
            return (Criteria) this;
        }

        public Criteria andBankaccountNotIn(List<String> values) {
            addCriterion("bankaccount not in", values, "bankaccount");
            return (Criteria) this;
        }

        public Criteria andBankaccountBetween(String value1, String value2) {
            addCriterion("bankaccount between", value1, value2, "bankaccount");
            return (Criteria) this;
        }

        public Criteria andBankaccountNotBetween(String value1, String value2) {
            addCriterion("bankaccount not between", value1, value2, "bankaccount");
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

        public Criteria andMethodsettingaccountIsNull() {
            addCriterion("methodsettingaccount is null");
            return (Criteria) this;
        }

        public Criteria andMethodsettingaccountIsNotNull() {
            addCriterion("methodsettingaccount is not null");
            return (Criteria) this;
        }

        public Criteria andMethodsettingaccountEqualTo(String value) {
            addCriterion("methodsettingaccount =", value, "methodsettingaccount");
            return (Criteria) this;
        }

        public Criteria andMethodsettingaccountNotEqualTo(String value) {
            addCriterion("methodsettingaccount <>", value, "methodsettingaccount");
            return (Criteria) this;
        }

        public Criteria andMethodsettingaccountGreaterThan(String value) {
            addCriterion("methodsettingaccount >", value, "methodsettingaccount");
            return (Criteria) this;
        }

        public Criteria andMethodsettingaccountGreaterThanOrEqualTo(String value) {
            addCriterion("methodsettingaccount >=", value, "methodsettingaccount");
            return (Criteria) this;
        }

        public Criteria andMethodsettingaccountLessThan(String value) {
            addCriterion("methodsettingaccount <", value, "methodsettingaccount");
            return (Criteria) this;
        }

        public Criteria andMethodsettingaccountLessThanOrEqualTo(String value) {
            addCriterion("methodsettingaccount <=", value, "methodsettingaccount");
            return (Criteria) this;
        }

        public Criteria andMethodsettingaccountLike(String value) {
            addCriterion("methodsettingaccount like", value, "methodsettingaccount");
            return (Criteria) this;
        }

        public Criteria andMethodsettingaccountNotLike(String value) {
            addCriterion("methodsettingaccount not like", value, "methodsettingaccount");
            return (Criteria) this;
        }

        public Criteria andMethodsettingaccountIn(List<String> values) {
            addCriterion("methodsettingaccount in", values, "methodsettingaccount");
            return (Criteria) this;
        }

        public Criteria andMethodsettingaccountNotIn(List<String> values) {
            addCriterion("methodsettingaccount not in", values, "methodsettingaccount");
            return (Criteria) this;
        }

        public Criteria andMethodsettingaccountBetween(String value1, String value2) {
            addCriterion("methodsettingaccount between", value1, value2, "methodsettingaccount");
            return (Criteria) this;
        }

        public Criteria andMethodsettingaccountNotBetween(String value1, String value2) {
            addCriterion("methodsettingaccount not between", value1, value2, "methodsettingaccount");
            return (Criteria) this;
        }

        public Criteria andCreatedateIsNull() {
            addCriterion("createdate is null");
            return (Criteria) this;
        }

        public Criteria andCreatedateIsNotNull() {
            addCriterion("createdate is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedateEqualTo(Date value) {
            addCriterion("createdate =", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotEqualTo(Date value) {
            addCriterion("createdate <>", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateGreaterThan(Date value) {
            addCriterion("createdate >", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateGreaterThanOrEqualTo(Date value) {
            addCriterion("createdate >=", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateLessThan(Date value) {
            addCriterion("createdate <", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateLessThanOrEqualTo(Date value) {
            addCriterion("createdate <=", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateIn(List<Date> values) {
            addCriterion("createdate in", values, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotIn(List<Date> values) {
            addCriterion("createdate not in", values, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateBetween(Date value1, Date value2) {
            addCriterion("createdate between", value1, value2, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotBetween(Date value1, Date value2) {
            addCriterion("createdate not between", value1, value2, "createdate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateIsNull() {
            addCriterion("updatedate is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedateIsNotNull() {
            addCriterion("updatedate is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedateEqualTo(Date value) {
            addCriterion("updatedate =", value, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateNotEqualTo(Date value) {
            addCriterion("updatedate <>", value, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateGreaterThan(Date value) {
            addCriterion("updatedate >", value, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateGreaterThanOrEqualTo(Date value) {
            addCriterion("updatedate >=", value, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateLessThan(Date value) {
            addCriterion("updatedate <", value, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateLessThanOrEqualTo(Date value) {
            addCriterion("updatedate <=", value, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateIn(List<Date> values) {
            addCriterion("updatedate in", values, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateNotIn(List<Date> values) {
            addCriterion("updatedate not in", values, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateBetween(Date value1, Date value2) {
            addCriterion("updatedate between", value1, value2, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateNotBetween(Date value1, Date value2) {
            addCriterion("updatedate not between", value1, value2, "updatedate");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNull() {
            addCriterion("province is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNotNull() {
            addCriterion("province is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceEqualTo(String value) {
            addCriterion("province =", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotEqualTo(String value) {
            addCriterion("province <>", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThan(String value) {
            addCriterion("province >", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("province >=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThan(String value) {
            addCriterion("province <", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThanOrEqualTo(String value) {
            addCriterion("province <=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLike(String value) {
            addCriterion("province like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotLike(String value) {
            addCriterion("province not like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceIn(List<String> values) {
            addCriterion("province in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotIn(List<String> values) {
            addCriterion("province not in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceBetween(String value1, String value2) {
            addCriterion("province between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotBetween(String value1, String value2) {
            addCriterion("province not between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("city like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("city not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("city not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCitysmallIsNull() {
            addCriterion("citysmall is null");
            return (Criteria) this;
        }

        public Criteria andCitysmallIsNotNull() {
            addCriterion("citysmall is not null");
            return (Criteria) this;
        }

        public Criteria andCitysmallEqualTo(String value) {
            addCriterion("citysmall =", value, "citysmall");
            return (Criteria) this;
        }

        public Criteria andCitysmallNotEqualTo(String value) {
            addCriterion("citysmall <>", value, "citysmall");
            return (Criteria) this;
        }

        public Criteria andCitysmallGreaterThan(String value) {
            addCriterion("citysmall >", value, "citysmall");
            return (Criteria) this;
        }

        public Criteria andCitysmallGreaterThanOrEqualTo(String value) {
            addCriterion("citysmall >=", value, "citysmall");
            return (Criteria) this;
        }

        public Criteria andCitysmallLessThan(String value) {
            addCriterion("citysmall <", value, "citysmall");
            return (Criteria) this;
        }

        public Criteria andCitysmallLessThanOrEqualTo(String value) {
            addCriterion("citysmall <=", value, "citysmall");
            return (Criteria) this;
        }

        public Criteria andCitysmallLike(String value) {
            addCriterion("citysmall like", value, "citysmall");
            return (Criteria) this;
        }

        public Criteria andCitysmallNotLike(String value) {
            addCriterion("citysmall not like", value, "citysmall");
            return (Criteria) this;
        }

        public Criteria andCitysmallIn(List<String> values) {
            addCriterion("citysmall in", values, "citysmall");
            return (Criteria) this;
        }

        public Criteria andCitysmallNotIn(List<String> values) {
            addCriterion("citysmall not in", values, "citysmall");
            return (Criteria) this;
        }

        public Criteria andCitysmallBetween(String value1, String value2) {
            addCriterion("citysmall between", value1, value2, "citysmall");
            return (Criteria) this;
        }

        public Criteria andCitysmallNotBetween(String value1, String value2) {
            addCriterion("citysmall not between", value1, value2, "citysmall");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencenumberphotoIsNull() {
            addCriterion("businesslicencenumberphoto is null");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencenumberphotoIsNotNull() {
            addCriterion("businesslicencenumberphoto is not null");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencenumberphotoEqualTo(String value) {
            addCriterion("businesslicencenumberphoto =", value, "businesslicencenumberphoto");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencenumberphotoNotEqualTo(String value) {
            addCriterion("businesslicencenumberphoto <>", value, "businesslicencenumberphoto");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencenumberphotoGreaterThan(String value) {
            addCriterion("businesslicencenumberphoto >", value, "businesslicencenumberphoto");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencenumberphotoGreaterThanOrEqualTo(String value) {
            addCriterion("businesslicencenumberphoto >=", value, "businesslicencenumberphoto");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencenumberphotoLessThan(String value) {
            addCriterion("businesslicencenumberphoto <", value, "businesslicencenumberphoto");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencenumberphotoLessThanOrEqualTo(String value) {
            addCriterion("businesslicencenumberphoto <=", value, "businesslicencenumberphoto");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencenumberphotoLike(String value) {
            addCriterion("businesslicencenumberphoto like", value, "businesslicencenumberphoto");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencenumberphotoNotLike(String value) {
            addCriterion("businesslicencenumberphoto not like", value, "businesslicencenumberphoto");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencenumberphotoIn(List<String> values) {
            addCriterion("businesslicencenumberphoto in", values, "businesslicencenumberphoto");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencenumberphotoNotIn(List<String> values) {
            addCriterion("businesslicencenumberphoto not in", values, "businesslicencenumberphoto");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencenumberphotoBetween(String value1, String value2) {
            addCriterion("businesslicencenumberphoto between", value1, value2, "businesslicencenumberphoto");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencenumberphotoNotBetween(String value1, String value2) {
            addCriterion("businesslicencenumberphoto not between", value1, value2, "businesslicencenumberphoto");
            return (Criteria) this;
        }

        public Criteria andBankdepositIsNull() {
            addCriterion("bankdeposit is null");
            return (Criteria) this;
        }

        public Criteria andBankdepositIsNotNull() {
            addCriterion("bankdeposit is not null");
            return (Criteria) this;
        }

        public Criteria andBankdepositEqualTo(String value) {
            addCriterion("bankdeposit =", value, "bankdeposit");
            return (Criteria) this;
        }

        public Criteria andBankdepositNotEqualTo(String value) {
            addCriterion("bankdeposit <>", value, "bankdeposit");
            return (Criteria) this;
        }

        public Criteria andBankdepositGreaterThan(String value) {
            addCriterion("bankdeposit >", value, "bankdeposit");
            return (Criteria) this;
        }

        public Criteria andBankdepositGreaterThanOrEqualTo(String value) {
            addCriterion("bankdeposit >=", value, "bankdeposit");
            return (Criteria) this;
        }

        public Criteria andBankdepositLessThan(String value) {
            addCriterion("bankdeposit <", value, "bankdeposit");
            return (Criteria) this;
        }

        public Criteria andBankdepositLessThanOrEqualTo(String value) {
            addCriterion("bankdeposit <=", value, "bankdeposit");
            return (Criteria) this;
        }

        public Criteria andBankdepositLike(String value) {
            addCriterion("bankdeposit like", value, "bankdeposit");
            return (Criteria) this;
        }

        public Criteria andBankdepositNotLike(String value) {
            addCriterion("bankdeposit not like", value, "bankdeposit");
            return (Criteria) this;
        }

        public Criteria andBankdepositIn(List<String> values) {
            addCriterion("bankdeposit in", values, "bankdeposit");
            return (Criteria) this;
        }

        public Criteria andBankdepositNotIn(List<String> values) {
            addCriterion("bankdeposit not in", values, "bankdeposit");
            return (Criteria) this;
        }

        public Criteria andBankdepositBetween(String value1, String value2) {
            addCriterion("bankdeposit between", value1, value2, "bankdeposit");
            return (Criteria) this;
        }

        public Criteria andBankdepositNotBetween(String value1, String value2) {
            addCriterion("bankdeposit not between", value1, value2, "bankdeposit");
            return (Criteria) this;
        }

        public Criteria andBankuserIsNull() {
            addCriterion("bankuser is null");
            return (Criteria) this;
        }

        public Criteria andBankuserIsNotNull() {
            addCriterion("bankuser is not null");
            return (Criteria) this;
        }

        public Criteria andBankuserEqualTo(String value) {
            addCriterion("bankuser =", value, "bankuser");
            return (Criteria) this;
        }

        public Criteria andBankuserNotEqualTo(String value) {
            addCriterion("bankuser <>", value, "bankuser");
            return (Criteria) this;
        }

        public Criteria andBankuserGreaterThan(String value) {
            addCriterion("bankuser >", value, "bankuser");
            return (Criteria) this;
        }

        public Criteria andBankuserGreaterThanOrEqualTo(String value) {
            addCriterion("bankuser >=", value, "bankuser");
            return (Criteria) this;
        }

        public Criteria andBankuserLessThan(String value) {
            addCriterion("bankuser <", value, "bankuser");
            return (Criteria) this;
        }

        public Criteria andBankuserLessThanOrEqualTo(String value) {
            addCriterion("bankuser <=", value, "bankuser");
            return (Criteria) this;
        }

        public Criteria andBankuserLike(String value) {
            addCriterion("bankuser like", value, "bankuser");
            return (Criteria) this;
        }

        public Criteria andBankuserNotLike(String value) {
            addCriterion("bankuser not like", value, "bankuser");
            return (Criteria) this;
        }

        public Criteria andBankuserIn(List<String> values) {
            addCriterion("bankuser in", values, "bankuser");
            return (Criteria) this;
        }

        public Criteria andBankuserNotIn(List<String> values) {
            addCriterion("bankuser not in", values, "bankuser");
            return (Criteria) this;
        }

        public Criteria andBankuserBetween(String value1, String value2) {
            addCriterion("bankuser between", value1, value2, "bankuser");
            return (Criteria) this;
        }

        public Criteria andBankuserNotBetween(String value1, String value2) {
            addCriterion("bankuser not between", value1, value2, "bankuser");
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