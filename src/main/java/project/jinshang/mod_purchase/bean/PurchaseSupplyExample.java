package project.jinshang.mod_purchase.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PurchaseSupplyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PurchaseSupplyExample() {
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

        public Criteria andUsernameIsNull() {
            addCriterion("username is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("username is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("username =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("username <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("username >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("username >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("username <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("username <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("username like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("username not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("username in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("username not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("username between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("username not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andAreaIsNull() {
            addCriterion("area is null");
            return (Criteria) this;
        }

        public Criteria andAreaIsNotNull() {
            addCriterion("area is not null");
            return (Criteria) this;
        }

        public Criteria andAreaEqualTo(String value) {
            addCriterion("area =", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotEqualTo(String value) {
            addCriterion("area <>", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThan(String value) {
            addCriterion("area >", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThanOrEqualTo(String value) {
            addCriterion("area >=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThan(String value) {
            addCriterion("area <", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThanOrEqualTo(String value) {
            addCriterion("area <=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLike(String value) {
            addCriterion("area like", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotLike(String value) {
            addCriterion("area not like", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaIn(List<String> values) {
            addCriterion("area in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotIn(List<String> values) {
            addCriterion("area not in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaBetween(String value1, String value2) {
            addCriterion("area between", value1, value2, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotBetween(String value1, String value2) {
            addCriterion("area not between", value1, value2, "area");
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

        public Criteria andSupplynameIsNull() {
            addCriterion("supplyname is null");
            return (Criteria) this;
        }

        public Criteria andSupplynameIsNotNull() {
            addCriterion("supplyname is not null");
            return (Criteria) this;
        }

        public Criteria andSupplynameEqualTo(String value) {
            addCriterion("supplyname =", value, "supplyname");
            return (Criteria) this;
        }

        public Criteria andSupplynameNotEqualTo(String value) {
            addCriterion("supplyname <>", value, "supplyname");
            return (Criteria) this;
        }

        public Criteria andSupplynameGreaterThan(String value) {
            addCriterion("supplyname >", value, "supplyname");
            return (Criteria) this;
        }

        public Criteria andSupplynameGreaterThanOrEqualTo(String value) {
            addCriterion("supplyname >=", value, "supplyname");
            return (Criteria) this;
        }

        public Criteria andSupplynameLessThan(String value) {
            addCriterion("supplyname <", value, "supplyname");
            return (Criteria) this;
        }

        public Criteria andSupplynameLessThanOrEqualTo(String value) {
            addCriterion("supplyname <=", value, "supplyname");
            return (Criteria) this;
        }

        public Criteria andSupplynameLike(String value) {
            addCriterion("supplyname like", value, "supplyname");
            return (Criteria) this;
        }

        public Criteria andSupplynameNotLike(String value) {
            addCriterion("supplyname not like", value, "supplyname");
            return (Criteria) this;
        }

        public Criteria andSupplynameIn(List<String> values) {
            addCriterion("supplyname in", values, "supplyname");
            return (Criteria) this;
        }

        public Criteria andSupplynameNotIn(List<String> values) {
            addCriterion("supplyname not in", values, "supplyname");
            return (Criteria) this;
        }

        public Criteria andSupplynameBetween(String value1, String value2) {
            addCriterion("supplyname between", value1, value2, "supplyname");
            return (Criteria) this;
        }

        public Criteria andSupplynameNotBetween(String value1, String value2) {
            addCriterion("supplyname not between", value1, value2, "supplyname");
            return (Criteria) this;
        }

        public Criteria andLegalIsNull() {
            addCriterion("legal is null");
            return (Criteria) this;
        }

        public Criteria andLegalIsNotNull() {
            addCriterion("legal is not null");
            return (Criteria) this;
        }

        public Criteria andLegalEqualTo(String value) {
            addCriterion("legal =", value, "legal");
            return (Criteria) this;
        }

        public Criteria andLegalNotEqualTo(String value) {
            addCriterion("legal <>", value, "legal");
            return (Criteria) this;
        }

        public Criteria andLegalGreaterThan(String value) {
            addCriterion("legal >", value, "legal");
            return (Criteria) this;
        }

        public Criteria andLegalGreaterThanOrEqualTo(String value) {
            addCriterion("legal >=", value, "legal");
            return (Criteria) this;
        }

        public Criteria andLegalLessThan(String value) {
            addCriterion("legal <", value, "legal");
            return (Criteria) this;
        }

        public Criteria andLegalLessThanOrEqualTo(String value) {
            addCriterion("legal <=", value, "legal");
            return (Criteria) this;
        }

        public Criteria andLegalLike(String value) {
            addCriterion("legal like", value, "legal");
            return (Criteria) this;
        }

        public Criteria andLegalNotLike(String value) {
            addCriterion("legal not like", value, "legal");
            return (Criteria) this;
        }

        public Criteria andLegalIn(List<String> values) {
            addCriterion("legal in", values, "legal");
            return (Criteria) this;
        }

        public Criteria andLegalNotIn(List<String> values) {
            addCriterion("legal not in", values, "legal");
            return (Criteria) this;
        }

        public Criteria andLegalBetween(String value1, String value2) {
            addCriterion("legal between", value1, value2, "legal");
            return (Criteria) this;
        }

        public Criteria andLegalNotBetween(String value1, String value2) {
            addCriterion("legal not between", value1, value2, "legal");
            return (Criteria) this;
        }

        public Criteria andTransportIsNull() {
            addCriterion("transport is null");
            return (Criteria) this;
        }

        public Criteria andTransportIsNotNull() {
            addCriterion("transport is not null");
            return (Criteria) this;
        }

        public Criteria andTransportEqualTo(String value) {
            addCriterion("transport =", value, "transport");
            return (Criteria) this;
        }

        public Criteria andTransportNotEqualTo(String value) {
            addCriterion("transport <>", value, "transport");
            return (Criteria) this;
        }

        public Criteria andTransportGreaterThan(String value) {
            addCriterion("transport >", value, "transport");
            return (Criteria) this;
        }

        public Criteria andTransportGreaterThanOrEqualTo(String value) {
            addCriterion("transport >=", value, "transport");
            return (Criteria) this;
        }

        public Criteria andTransportLessThan(String value) {
            addCriterion("transport <", value, "transport");
            return (Criteria) this;
        }

        public Criteria andTransportLessThanOrEqualTo(String value) {
            addCriterion("transport <=", value, "transport");
            return (Criteria) this;
        }

        public Criteria andTransportLike(String value) {
            addCriterion("transport like", value, "transport");
            return (Criteria) this;
        }

        public Criteria andTransportNotLike(String value) {
            addCriterion("transport not like", value, "transport");
            return (Criteria) this;
        }

        public Criteria andTransportIn(List<String> values) {
            addCriterion("transport in", values, "transport");
            return (Criteria) this;
        }

        public Criteria andTransportNotIn(List<String> values) {
            addCriterion("transport not in", values, "transport");
            return (Criteria) this;
        }

        public Criteria andTransportBetween(String value1, String value2) {
            addCriterion("transport between", value1, value2, "transport");
            return (Criteria) this;
        }

        public Criteria andTransportNotBetween(String value1, String value2) {
            addCriterion("transport not between", value1, value2, "transport");
            return (Criteria) this;
        }

        public Criteria andMobilephoneIsNull() {
            addCriterion("mobilephone is null");
            return (Criteria) this;
        }

        public Criteria andMobilephoneIsNotNull() {
            addCriterion("mobilephone is not null");
            return (Criteria) this;
        }

        public Criteria andMobilephoneEqualTo(String value) {
            addCriterion("mobilephone =", value, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneNotEqualTo(String value) {
            addCriterion("mobilephone <>", value, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneGreaterThan(String value) {
            addCriterion("mobilephone >", value, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneGreaterThanOrEqualTo(String value) {
            addCriterion("mobilephone >=", value, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneLessThan(String value) {
            addCriterion("mobilephone <", value, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneLessThanOrEqualTo(String value) {
            addCriterion("mobilephone <=", value, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneLike(String value) {
            addCriterion("mobilephone like", value, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneNotLike(String value) {
            addCriterion("mobilephone not like", value, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneIn(List<String> values) {
            addCriterion("mobilephone in", values, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneNotIn(List<String> values) {
            addCriterion("mobilephone not in", values, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneBetween(String value1, String value2) {
            addCriterion("mobilephone between", value1, value2, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneNotBetween(String value1, String value2) {
            addCriterion("mobilephone not between", value1, value2, "mobilephone");
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

        public Criteria andCodeIsNull() {
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("code not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andBankIsNull() {
            addCriterion("bank is null");
            return (Criteria) this;
        }

        public Criteria andBankIsNotNull() {
            addCriterion("bank is not null");
            return (Criteria) this;
        }

        public Criteria andBankEqualTo(String value) {
            addCriterion("bank =", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankNotEqualTo(String value) {
            addCriterion("bank <>", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankGreaterThan(String value) {
            addCriterion("bank >", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankGreaterThanOrEqualTo(String value) {
            addCriterion("bank >=", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankLessThan(String value) {
            addCriterion("bank <", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankLessThanOrEqualTo(String value) {
            addCriterion("bank <=", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankLike(String value) {
            addCriterion("bank like", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankNotLike(String value) {
            addCriterion("bank not like", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankIn(List<String> values) {
            addCriterion("bank in", values, "bank");
            return (Criteria) this;
        }

        public Criteria andBankNotIn(List<String> values) {
            addCriterion("bank not in", values, "bank");
            return (Criteria) this;
        }

        public Criteria andBankBetween(String value1, String value2) {
            addCriterion("bank between", value1, value2, "bank");
            return (Criteria) this;
        }

        public Criteria andBankNotBetween(String value1, String value2) {
            addCriterion("bank not between", value1, value2, "bank");
            return (Criteria) this;
        }

        public Criteria andBanknoIsNull() {
            addCriterion("bankno is null");
            return (Criteria) this;
        }

        public Criteria andBanknoIsNotNull() {
            addCriterion("bankno is not null");
            return (Criteria) this;
        }

        public Criteria andBanknoEqualTo(String value) {
            addCriterion("bankno =", value, "bankno");
            return (Criteria) this;
        }

        public Criteria andBanknoNotEqualTo(String value) {
            addCriterion("bankno <>", value, "bankno");
            return (Criteria) this;
        }

        public Criteria andBanknoGreaterThan(String value) {
            addCriterion("bankno >", value, "bankno");
            return (Criteria) this;
        }

        public Criteria andBanknoGreaterThanOrEqualTo(String value) {
            addCriterion("bankno >=", value, "bankno");
            return (Criteria) this;
        }

        public Criteria andBanknoLessThan(String value) {
            addCriterion("bankno <", value, "bankno");
            return (Criteria) this;
        }

        public Criteria andBanknoLessThanOrEqualTo(String value) {
            addCriterion("bankno <=", value, "bankno");
            return (Criteria) this;
        }

        public Criteria andBanknoLike(String value) {
            addCriterion("bankno like", value, "bankno");
            return (Criteria) this;
        }

        public Criteria andBanknoNotLike(String value) {
            addCriterion("bankno not like", value, "bankno");
            return (Criteria) this;
        }

        public Criteria andBanknoIn(List<String> values) {
            addCriterion("bankno in", values, "bankno");
            return (Criteria) this;
        }

        public Criteria andBanknoNotIn(List<String> values) {
            addCriterion("bankno not in", values, "bankno");
            return (Criteria) this;
        }

        public Criteria andBanknoBetween(String value1, String value2) {
            addCriterion("bankno between", value1, value2, "bankno");
            return (Criteria) this;
        }

        public Criteria andBanknoNotBetween(String value1, String value2) {
            addCriterion("bankno not between", value1, value2, "bankno");
            return (Criteria) this;
        }

        public Criteria andTexnoIsNull() {
            addCriterion("texno is null");
            return (Criteria) this;
        }

        public Criteria andTexnoIsNotNull() {
            addCriterion("texno is not null");
            return (Criteria) this;
        }

        public Criteria andTexnoEqualTo(String value) {
            addCriterion("texno =", value, "texno");
            return (Criteria) this;
        }

        public Criteria andTexnoNotEqualTo(String value) {
            addCriterion("texno <>", value, "texno");
            return (Criteria) this;
        }

        public Criteria andTexnoGreaterThan(String value) {
            addCriterion("texno >", value, "texno");
            return (Criteria) this;
        }

        public Criteria andTexnoGreaterThanOrEqualTo(String value) {
            addCriterion("texno >=", value, "texno");
            return (Criteria) this;
        }

        public Criteria andTexnoLessThan(String value) {
            addCriterion("texno <", value, "texno");
            return (Criteria) this;
        }

        public Criteria andTexnoLessThanOrEqualTo(String value) {
            addCriterion("texno <=", value, "texno");
            return (Criteria) this;
        }

        public Criteria andTexnoLike(String value) {
            addCriterion("texno like", value, "texno");
            return (Criteria) this;
        }

        public Criteria andTexnoNotLike(String value) {
            addCriterion("texno not like", value, "texno");
            return (Criteria) this;
        }

        public Criteria andTexnoIn(List<String> values) {
            addCriterion("texno in", values, "texno");
            return (Criteria) this;
        }

        public Criteria andTexnoNotIn(List<String> values) {
            addCriterion("texno not in", values, "texno");
            return (Criteria) this;
        }

        public Criteria andTexnoBetween(String value1, String value2) {
            addCriterion("texno between", value1, value2, "texno");
            return (Criteria) this;
        }

        public Criteria andTexnoNotBetween(String value1, String value2) {
            addCriterion("texno not between", value1, value2, "texno");
            return (Criteria) this;
        }

        public Criteria andBilltypeIsNull() {
            addCriterion("billtype is null");
            return (Criteria) this;
        }

        public Criteria andBilltypeIsNotNull() {
            addCriterion("billtype is not null");
            return (Criteria) this;
        }

        public Criteria andBilltypeEqualTo(String value) {
            addCriterion("billtype =", value, "billtype");
            return (Criteria) this;
        }

        public Criteria andBilltypeNotEqualTo(String value) {
            addCriterion("billtype <>", value, "billtype");
            return (Criteria) this;
        }

        public Criteria andBilltypeGreaterThan(String value) {
            addCriterion("billtype >", value, "billtype");
            return (Criteria) this;
        }

        public Criteria andBilltypeGreaterThanOrEqualTo(String value) {
            addCriterion("billtype >=", value, "billtype");
            return (Criteria) this;
        }

        public Criteria andBilltypeLessThan(String value) {
            addCriterion("billtype <", value, "billtype");
            return (Criteria) this;
        }

        public Criteria andBilltypeLessThanOrEqualTo(String value) {
            addCriterion("billtype <=", value, "billtype");
            return (Criteria) this;
        }

        public Criteria andBilltypeLike(String value) {
            addCriterion("billtype like", value, "billtype");
            return (Criteria) this;
        }

        public Criteria andBilltypeNotLike(String value) {
            addCriterion("billtype not like", value, "billtype");
            return (Criteria) this;
        }

        public Criteria andBilltypeIn(List<String> values) {
            addCriterion("billtype in", values, "billtype");
            return (Criteria) this;
        }

        public Criteria andBilltypeNotIn(List<String> values) {
            addCriterion("billtype not in", values, "billtype");
            return (Criteria) this;
        }

        public Criteria andBilltypeBetween(String value1, String value2) {
            addCriterion("billtype between", value1, value2, "billtype");
            return (Criteria) this;
        }

        public Criteria andBilltypeNotBetween(String value1, String value2) {
            addCriterion("billtype not between", value1, value2, "billtype");
            return (Criteria) this;
        }

        public Criteria andSettletypeIsNull() {
            addCriterion("settletype is null");
            return (Criteria) this;
        }

        public Criteria andSettletypeIsNotNull() {
            addCriterion("settletype is not null");
            return (Criteria) this;
        }

        public Criteria andSettletypeEqualTo(String value) {
            addCriterion("settletype =", value, "settletype");
            return (Criteria) this;
        }

        public Criteria andSettletypeNotEqualTo(String value) {
            addCriterion("settletype <>", value, "settletype");
            return (Criteria) this;
        }

        public Criteria andSettletypeGreaterThan(String value) {
            addCriterion("settletype >", value, "settletype");
            return (Criteria) this;
        }

        public Criteria andSettletypeGreaterThanOrEqualTo(String value) {
            addCriterion("settletype >=", value, "settletype");
            return (Criteria) this;
        }

        public Criteria andSettletypeLessThan(String value) {
            addCriterion("settletype <", value, "settletype");
            return (Criteria) this;
        }

        public Criteria andSettletypeLessThanOrEqualTo(String value) {
            addCriterion("settletype <=", value, "settletype");
            return (Criteria) this;
        }

        public Criteria andSettletypeLike(String value) {
            addCriterion("settletype like", value, "settletype");
            return (Criteria) this;
        }

        public Criteria andSettletypeNotLike(String value) {
            addCriterion("settletype not like", value, "settletype");
            return (Criteria) this;
        }

        public Criteria andSettletypeIn(List<String> values) {
            addCriterion("settletype in", values, "settletype");
            return (Criteria) this;
        }

        public Criteria andSettletypeNotIn(List<String> values) {
            addCriterion("settletype not in", values, "settletype");
            return (Criteria) this;
        }

        public Criteria andSettletypeBetween(String value1, String value2) {
            addCriterion("settletype between", value1, value2, "settletype");
            return (Criteria) this;
        }

        public Criteria andSettletypeNotBetween(String value1, String value2) {
            addCriterion("settletype not between", value1, value2, "settletype");
            return (Criteria) this;
        }

        public Criteria andCurrencyIsNull() {
            addCriterion("currency is null");
            return (Criteria) this;
        }

        public Criteria andCurrencyIsNotNull() {
            addCriterion("currency is not null");
            return (Criteria) this;
        }

        public Criteria andCurrencyEqualTo(String value) {
            addCriterion("currency =", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotEqualTo(String value) {
            addCriterion("currency <>", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyGreaterThan(String value) {
            addCriterion("currency >", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyGreaterThanOrEqualTo(String value) {
            addCriterion("currency >=", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLessThan(String value) {
            addCriterion("currency <", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLessThanOrEqualTo(String value) {
            addCriterion("currency <=", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLike(String value) {
            addCriterion("currency like", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotLike(String value) {
            addCriterion("currency not like", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyIn(List<String> values) {
            addCriterion("currency in", values, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotIn(List<String> values) {
            addCriterion("currency not in", values, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyBetween(String value1, String value2) {
            addCriterion("currency between", value1, value2, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotBetween(String value1, String value2) {
            addCriterion("currency not between", value1, value2, "currency");
            return (Criteria) this;
        }

        public Criteria andPrepayIsNull() {
            addCriterion("prepay is null");
            return (Criteria) this;
        }

        public Criteria andPrepayIsNotNull() {
            addCriterion("prepay is not null");
            return (Criteria) this;
        }

        public Criteria andPrepayEqualTo(BigDecimal value) {
            addCriterion("prepay =", value, "prepay");
            return (Criteria) this;
        }

        public Criteria andPrepayNotEqualTo(BigDecimal value) {
            addCriterion("prepay <>", value, "prepay");
            return (Criteria) this;
        }

        public Criteria andPrepayGreaterThan(BigDecimal value) {
            addCriterion("prepay >", value, "prepay");
            return (Criteria) this;
        }

        public Criteria andPrepayGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("prepay >=", value, "prepay");
            return (Criteria) this;
        }

        public Criteria andPrepayLessThan(BigDecimal value) {
            addCriterion("prepay <", value, "prepay");
            return (Criteria) this;
        }

        public Criteria andPrepayLessThanOrEqualTo(BigDecimal value) {
            addCriterion("prepay <=", value, "prepay");
            return (Criteria) this;
        }

        public Criteria andPrepayIn(List<BigDecimal> values) {
            addCriterion("prepay in", values, "prepay");
            return (Criteria) this;
        }

        public Criteria andPrepayNotIn(List<BigDecimal> values) {
            addCriterion("prepay not in", values, "prepay");
            return (Criteria) this;
        }

        public Criteria andPrepayBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("prepay between", value1, value2, "prepay");
            return (Criteria) this;
        }

        public Criteria andPrepayNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("prepay not between", value1, value2, "prepay");
            return (Criteria) this;
        }

        public Criteria andSpayIsNull() {
            addCriterion("spay is null");
            return (Criteria) this;
        }

        public Criteria andSpayIsNotNull() {
            addCriterion("spay is not null");
            return (Criteria) this;
        }

        public Criteria andSpayEqualTo(BigDecimal value) {
            addCriterion("spay =", value, "spay");
            return (Criteria) this;
        }

        public Criteria andSpayNotEqualTo(BigDecimal value) {
            addCriterion("spay <>", value, "spay");
            return (Criteria) this;
        }

        public Criteria andSpayGreaterThan(BigDecimal value) {
            addCriterion("spay >", value, "spay");
            return (Criteria) this;
        }

        public Criteria andSpayGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("spay >=", value, "spay");
            return (Criteria) this;
        }

        public Criteria andSpayLessThan(BigDecimal value) {
            addCriterion("spay <", value, "spay");
            return (Criteria) this;
        }

        public Criteria andSpayLessThanOrEqualTo(BigDecimal value) {
            addCriterion("spay <=", value, "spay");
            return (Criteria) this;
        }

        public Criteria andSpayIn(List<BigDecimal> values) {
            addCriterion("spay in", values, "spay");
            return (Criteria) this;
        }

        public Criteria andSpayNotIn(List<BigDecimal> values) {
            addCriterion("spay not in", values, "spay");
            return (Criteria) this;
        }

        public Criteria andSpayBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("spay between", value1, value2, "spay");
            return (Criteria) this;
        }

        public Criteria andSpayNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("spay not between", value1, value2, "spay");
            return (Criteria) this;
        }

        public Criteria andAllpayIsNull() {
            addCriterion("allpay is null");
            return (Criteria) this;
        }

        public Criteria andAllpayIsNotNull() {
            addCriterion("allpay is not null");
            return (Criteria) this;
        }

        public Criteria andAllpayEqualTo(BigDecimal value) {
            addCriterion("allpay =", value, "allpay");
            return (Criteria) this;
        }

        public Criteria andAllpayNotEqualTo(BigDecimal value) {
            addCriterion("allpay <>", value, "allpay");
            return (Criteria) this;
        }

        public Criteria andAllpayGreaterThan(BigDecimal value) {
            addCriterion("allpay >", value, "allpay");
            return (Criteria) this;
        }

        public Criteria andAllpayGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("allpay >=", value, "allpay");
            return (Criteria) this;
        }

        public Criteria andAllpayLessThan(BigDecimal value) {
            addCriterion("allpay <", value, "allpay");
            return (Criteria) this;
        }

        public Criteria andAllpayLessThanOrEqualTo(BigDecimal value) {
            addCriterion("allpay <=", value, "allpay");
            return (Criteria) this;
        }

        public Criteria andAllpayIn(List<BigDecimal> values) {
            addCriterion("allpay in", values, "allpay");
            return (Criteria) this;
        }

        public Criteria andAllpayNotIn(List<BigDecimal> values) {
            addCriterion("allpay not in", values, "allpay");
            return (Criteria) this;
        }

        public Criteria andAllpayBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("allpay between", value1, value2, "allpay");
            return (Criteria) this;
        }

        public Criteria andAllpayNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("allpay not between", value1, value2, "allpay");
            return (Criteria) this;
        }

        public Criteria andSettletimeIsNull() {
            addCriterion("settletime is null");
            return (Criteria) this;
        }

        public Criteria andSettletimeIsNotNull() {
            addCriterion("settletime is not null");
            return (Criteria) this;
        }

        public Criteria andSettletimeEqualTo(String value) {
            addCriterion("settletime =", value, "settletime");
            return (Criteria) this;
        }

        public Criteria andSettletimeNotEqualTo(String value) {
            addCriterion("settletime <>", value, "settletime");
            return (Criteria) this;
        }

        public Criteria andSettletimeGreaterThan(String value) {
            addCriterion("settletime >", value, "settletime");
            return (Criteria) this;
        }

        public Criteria andSettletimeGreaterThanOrEqualTo(String value) {
            addCriterion("settletime >=", value, "settletime");
            return (Criteria) this;
        }

        public Criteria andSettletimeLessThan(String value) {
            addCriterion("settletime <", value, "settletime");
            return (Criteria) this;
        }

        public Criteria andSettletimeLessThanOrEqualTo(String value) {
            addCriterion("settletime <=", value, "settletime");
            return (Criteria) this;
        }

        public Criteria andSettletimeLike(String value) {
            addCriterion("settletime like", value, "settletime");
            return (Criteria) this;
        }

        public Criteria andSettletimeNotLike(String value) {
            addCriterion("settletime not like", value, "settletime");
            return (Criteria) this;
        }

        public Criteria andSettletimeIn(List<String> values) {
            addCriterion("settletime in", values, "settletime");
            return (Criteria) this;
        }

        public Criteria andSettletimeNotIn(List<String> values) {
            addCriterion("settletime not in", values, "settletime");
            return (Criteria) this;
        }

        public Criteria andSettletimeBetween(String value1, String value2) {
            addCriterion("settletime between", value1, value2, "settletime");
            return (Criteria) this;
        }

        public Criteria andSettletimeNotBetween(String value1, String value2) {
            addCriterion("settletime not between", value1, value2, "settletime");
            return (Criteria) this;
        }

        public Criteria andBusinessIsNull() {
            addCriterion("business is null");
            return (Criteria) this;
        }

        public Criteria andBusinessIsNotNull() {
            addCriterion("business is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessEqualTo(String value) {
            addCriterion("business =", value, "business");
            return (Criteria) this;
        }

        public Criteria andBusinessNotEqualTo(String value) {
            addCriterion("business <>", value, "business");
            return (Criteria) this;
        }

        public Criteria andBusinessGreaterThan(String value) {
            addCriterion("business >", value, "business");
            return (Criteria) this;
        }

        public Criteria andBusinessGreaterThanOrEqualTo(String value) {
            addCriterion("business >=", value, "business");
            return (Criteria) this;
        }

        public Criteria andBusinessLessThan(String value) {
            addCriterion("business <", value, "business");
            return (Criteria) this;
        }

        public Criteria andBusinessLessThanOrEqualTo(String value) {
            addCriterion("business <=", value, "business");
            return (Criteria) this;
        }

        public Criteria andBusinessLike(String value) {
            addCriterion("business like", value, "business");
            return (Criteria) this;
        }

        public Criteria andBusinessNotLike(String value) {
            addCriterion("business not like", value, "business");
            return (Criteria) this;
        }

        public Criteria andBusinessIn(List<String> values) {
            addCriterion("business in", values, "business");
            return (Criteria) this;
        }

        public Criteria andBusinessNotIn(List<String> values) {
            addCriterion("business not in", values, "business");
            return (Criteria) this;
        }

        public Criteria andBusinessBetween(String value1, String value2) {
            addCriterion("business between", value1, value2, "business");
            return (Criteria) this;
        }

        public Criteria andBusinessNotBetween(String value1, String value2) {
            addCriterion("business not between", value1, value2, "business");
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