package project.jinshang.mod_product.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BillingRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BillingRecordExample() {
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

        public Criteria andInvoiceheadupIsNull() {
            addCriterion("invoiceheadup is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceheadupIsNotNull() {
            addCriterion("invoiceheadup is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceheadupEqualTo(String value) {
            addCriterion("invoiceheadup =", value, "invoiceheadup");
            return (Criteria) this;
        }

        public Criteria andInvoiceheadupNotEqualTo(String value) {
            addCriterion("invoiceheadup <>", value, "invoiceheadup");
            return (Criteria) this;
        }

        public Criteria andInvoiceheadupGreaterThan(String value) {
            addCriterion("invoiceheadup >", value, "invoiceheadup");
            return (Criteria) this;
        }

        public Criteria andInvoiceheadupGreaterThanOrEqualTo(String value) {
            addCriterion("invoiceheadup >=", value, "invoiceheadup");
            return (Criteria) this;
        }

        public Criteria andInvoiceheadupLessThan(String value) {
            addCriterion("invoiceheadup <", value, "invoiceheadup");
            return (Criteria) this;
        }

        public Criteria andInvoiceheadupLessThanOrEqualTo(String value) {
            addCriterion("invoiceheadup <=", value, "invoiceheadup");
            return (Criteria) this;
        }

        public Criteria andInvoiceheadupLike(String value) {
            addCriterion("invoiceheadup like", value, "invoiceheadup");
            return (Criteria) this;
        }

        public Criteria andInvoiceheadupNotLike(String value) {
            addCriterion("invoiceheadup not like", value, "invoiceheadup");
            return (Criteria) this;
        }

        public Criteria andInvoiceheadupIn(List<String> values) {
            addCriterion("invoiceheadup in", values, "invoiceheadup");
            return (Criteria) this;
        }

        public Criteria andInvoiceheadupNotIn(List<String> values) {
            addCriterion("invoiceheadup not in", values, "invoiceheadup");
            return (Criteria) this;
        }

        public Criteria andInvoiceheadupBetween(String value1, String value2) {
            addCriterion("invoiceheadup between", value1, value2, "invoiceheadup");
            return (Criteria) this;
        }

        public Criteria andInvoiceheadupNotBetween(String value1, String value2) {
            addCriterion("invoiceheadup not between", value1, value2, "invoiceheadup");
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

        public Criteria andBankofaccountsIsNull() {
            addCriterion("bankofaccounts is null");
            return (Criteria) this;
        }

        public Criteria andBankofaccountsIsNotNull() {
            addCriterion("bankofaccounts is not null");
            return (Criteria) this;
        }

        public Criteria andBankofaccountsEqualTo(String value) {
            addCriterion("bankofaccounts =", value, "bankofaccounts");
            return (Criteria) this;
        }

        public Criteria andBankofaccountsNotEqualTo(String value) {
            addCriterion("bankofaccounts <>", value, "bankofaccounts");
            return (Criteria) this;
        }

        public Criteria andBankofaccountsGreaterThan(String value) {
            addCriterion("bankofaccounts >", value, "bankofaccounts");
            return (Criteria) this;
        }

        public Criteria andBankofaccountsGreaterThanOrEqualTo(String value) {
            addCriterion("bankofaccounts >=", value, "bankofaccounts");
            return (Criteria) this;
        }

        public Criteria andBankofaccountsLessThan(String value) {
            addCriterion("bankofaccounts <", value, "bankofaccounts");
            return (Criteria) this;
        }

        public Criteria andBankofaccountsLessThanOrEqualTo(String value) {
            addCriterion("bankofaccounts <=", value, "bankofaccounts");
            return (Criteria) this;
        }

        public Criteria andBankofaccountsLike(String value) {
            addCriterion("bankofaccounts like", value, "bankofaccounts");
            return (Criteria) this;
        }

        public Criteria andBankofaccountsNotLike(String value) {
            addCriterion("bankofaccounts not like", value, "bankofaccounts");
            return (Criteria) this;
        }

        public Criteria andBankofaccountsIn(List<String> values) {
            addCriterion("bankofaccounts in", values, "bankofaccounts");
            return (Criteria) this;
        }

        public Criteria andBankofaccountsNotIn(List<String> values) {
            addCriterion("bankofaccounts not in", values, "bankofaccounts");
            return (Criteria) this;
        }

        public Criteria andBankofaccountsBetween(String value1, String value2) {
            addCriterion("bankofaccounts between", value1, value2, "bankofaccounts");
            return (Criteria) this;
        }

        public Criteria andBankofaccountsNotBetween(String value1, String value2) {
            addCriterion("bankofaccounts not between", value1, value2, "bankofaccounts");
            return (Criteria) this;
        }

        public Criteria andAccountIsNull() {
            addCriterion("account is null");
            return (Criteria) this;
        }

        public Criteria andAccountIsNotNull() {
            addCriterion("account is not null");
            return (Criteria) this;
        }

        public Criteria andAccountEqualTo(String value) {
            addCriterion("account =", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotEqualTo(String value) {
            addCriterion("account <>", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThan(String value) {
            addCriterion("account >", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThanOrEqualTo(String value) {
            addCriterion("account >=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThan(String value) {
            addCriterion("account <", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThanOrEqualTo(String value) {
            addCriterion("account <=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLike(String value) {
            addCriterion("account like", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotLike(String value) {
            addCriterion("account not like", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountIn(List<String> values) {
            addCriterion("account in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotIn(List<String> values) {
            addCriterion("account not in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountBetween(String value1, String value2) {
            addCriterion("account between", value1, value2, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotBetween(String value1, String value2) {
            addCriterion("account not between", value1, value2, "account");
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

        public Criteria andExpressnoIsNull() {
            addCriterion("expressno is null");
            return (Criteria) this;
        }

        public Criteria andExpressnoIsNotNull() {
            addCriterion("expressno is not null");
            return (Criteria) this;
        }

        public Criteria andExpressnoEqualTo(String value) {
            addCriterion("expressno =", value, "expressno");
            return (Criteria) this;
        }

        public Criteria andExpressnoNotEqualTo(String value) {
            addCriterion("expressno <>", value, "expressno");
            return (Criteria) this;
        }

        public Criteria andExpressnoGreaterThan(String value) {
            addCriterion("expressno >", value, "expressno");
            return (Criteria) this;
        }

        public Criteria andExpressnoGreaterThanOrEqualTo(String value) {
            addCriterion("expressno >=", value, "expressno");
            return (Criteria) this;
        }

        public Criteria andExpressnoLessThan(String value) {
            addCriterion("expressno <", value, "expressno");
            return (Criteria) this;
        }

        public Criteria andExpressnoLessThanOrEqualTo(String value) {
            addCriterion("expressno <=", value, "expressno");
            return (Criteria) this;
        }

        public Criteria andExpressnoLike(String value) {
            addCriterion("expressno like", value, "expressno");
            return (Criteria) this;
        }

        public Criteria andExpressnoNotLike(String value) {
            addCriterion("expressno not like", value, "expressno");
            return (Criteria) this;
        }

        public Criteria andExpressnoIn(List<String> values) {
            addCriterion("expressno in", values, "expressno");
            return (Criteria) this;
        }

        public Criteria andExpressnoNotIn(List<String> values) {
            addCriterion("expressno not in", values, "expressno");
            return (Criteria) this;
        }

        public Criteria andExpressnoBetween(String value1, String value2) {
            addCriterion("expressno between", value1, value2, "expressno");
            return (Criteria) this;
        }

        public Criteria andExpressnoNotBetween(String value1, String value2) {
            addCriterion("expressno not between", value1, value2, "expressno");
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

        public Criteria andOrderuuidIsNull() {
            addCriterion("orderuuid is null");
            return (Criteria) this;
        }

        public Criteria andOrderuuidIsNotNull() {
            addCriterion("orderuuid is not null");
            return (Criteria) this;
        }

        public Criteria andOrderuuidEqualTo(String value) {
            addCriterion("orderuuid =", value, "orderuuid");
            return (Criteria) this;
        }

        public Criteria andOrderuuidNotEqualTo(String value) {
            addCriterion("orderuuid <>", value, "orderuuid");
            return (Criteria) this;
        }

        public Criteria andOrderuuidGreaterThan(String value) {
            addCriterion("orderuuid >", value, "orderuuid");
            return (Criteria) this;
        }

        public Criteria andOrderuuidGreaterThanOrEqualTo(String value) {
            addCriterion("orderuuid >=", value, "orderuuid");
            return (Criteria) this;
        }

        public Criteria andOrderuuidLessThan(String value) {
            addCriterion("orderuuid <", value, "orderuuid");
            return (Criteria) this;
        }

        public Criteria andOrderuuidLessThanOrEqualTo(String value) {
            addCriterion("orderuuid <=", value, "orderuuid");
            return (Criteria) this;
        }

        public Criteria andOrderuuidLike(String value) {
            addCriterion("orderuuid like", value, "orderuuid");
            return (Criteria) this;
        }

        public Criteria andOrderuuidNotLike(String value) {
            addCriterion("orderuuid not like", value, "orderuuid");
            return (Criteria) this;
        }

        public Criteria andOrderuuidIn(List<String> values) {
            addCriterion("orderuuid in", values, "orderuuid");
            return (Criteria) this;
        }

        public Criteria andOrderuuidNotIn(List<String> values) {
            addCriterion("orderuuid not in", values, "orderuuid");
            return (Criteria) this;
        }

        public Criteria andOrderuuidBetween(String value1, String value2) {
            addCriterion("orderuuid between", value1, value2, "orderuuid");
            return (Criteria) this;
        }

        public Criteria andOrderuuidNotBetween(String value1, String value2) {
            addCriterion("orderuuid not between", value1, value2, "orderuuid");
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

        public Criteria andInvoiceidIsNull() {
            addCriterion("invoiceid is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceidIsNotNull() {
            addCriterion("invoiceid is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceidEqualTo(Long value) {
            addCriterion("invoiceid =", value, "invoiceid");
            return (Criteria) this;
        }

        public Criteria andInvoiceidNotEqualTo(Long value) {
            addCriterion("invoiceid <>", value, "invoiceid");
            return (Criteria) this;
        }

        public Criteria andInvoiceidGreaterThan(Long value) {
            addCriterion("invoiceid >", value, "invoiceid");
            return (Criteria) this;
        }

        public Criteria andInvoiceidGreaterThanOrEqualTo(Long value) {
            addCriterion("invoiceid >=", value, "invoiceid");
            return (Criteria) this;
        }

        public Criteria andInvoiceidLessThan(Long value) {
            addCriterion("invoiceid <", value, "invoiceid");
            return (Criteria) this;
        }

        public Criteria andInvoiceidLessThanOrEqualTo(Long value) {
            addCriterion("invoiceid <=", value, "invoiceid");
            return (Criteria) this;
        }

        public Criteria andInvoiceidIn(List<Long> values) {
            addCriterion("invoiceid in", values, "invoiceid");
            return (Criteria) this;
        }

        public Criteria andInvoiceidNotIn(List<Long> values) {
            addCriterion("invoiceid not in", values, "invoiceid");
            return (Criteria) this;
        }

        public Criteria andInvoiceidBetween(Long value1, Long value2) {
            addCriterion("invoiceid between", value1, value2, "invoiceid");
            return (Criteria) this;
        }

        public Criteria andInvoiceidNotBetween(Long value1, Long value2) {
            addCriterion("invoiceid not between", value1, value2, "invoiceid");
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

        public Criteria andMembernameIsNull() {
            addCriterion("membername is null");
            return (Criteria) this;
        }

        public Criteria andMembernameIsNotNull() {
            addCriterion("membername is not null");
            return (Criteria) this;
        }

        public Criteria andMembernameEqualTo(String value) {
            addCriterion("membername =", value, "membername");
            return (Criteria) this;
        }

        public Criteria andMembernameNotEqualTo(String value) {
            addCriterion("membername <>", value, "membername");
            return (Criteria) this;
        }

        public Criteria andMembernameGreaterThan(String value) {
            addCriterion("membername >", value, "membername");
            return (Criteria) this;
        }

        public Criteria andMembernameGreaterThanOrEqualTo(String value) {
            addCriterion("membername >=", value, "membername");
            return (Criteria) this;
        }

        public Criteria andMembernameLessThan(String value) {
            addCriterion("membername <", value, "membername");
            return (Criteria) this;
        }

        public Criteria andMembernameLessThanOrEqualTo(String value) {
            addCriterion("membername <=", value, "membername");
            return (Criteria) this;
        }

        public Criteria andMembernameLike(String value) {
            addCriterion("membername like", value, "membername");
            return (Criteria) this;
        }

        public Criteria andMembernameNotLike(String value) {
            addCriterion("membername not like", value, "membername");
            return (Criteria) this;
        }

        public Criteria andMembernameIn(List<String> values) {
            addCriterion("membername in", values, "membername");
            return (Criteria) this;
        }

        public Criteria andMembernameNotIn(List<String> values) {
            addCriterion("membername not in", values, "membername");
            return (Criteria) this;
        }

        public Criteria andMembernameBetween(String value1, String value2) {
            addCriterion("membername between", value1, value2, "membername");
            return (Criteria) this;
        }

        public Criteria andMembernameNotBetween(String value1, String value2) {
            addCriterion("membername not between", value1, value2, "membername");
            return (Criteria) this;
        }

        public Criteria andBillcashIsNull() {
            addCriterion("billcash is null");
            return (Criteria) this;
        }

        public Criteria andBillcashIsNotNull() {
            addCriterion("billcash is not null");
            return (Criteria) this;
        }

        public Criteria andBillcashEqualTo(BigDecimal value) {
            addCriterion("billcash =", value, "billcash");
            return (Criteria) this;
        }

        public Criteria andBillcashNotEqualTo(BigDecimal value) {
            addCriterion("billcash <>", value, "billcash");
            return (Criteria) this;
        }

        public Criteria andBillcashGreaterThan(BigDecimal value) {
            addCriterion("billcash >", value, "billcash");
            return (Criteria) this;
        }

        public Criteria andBillcashGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("billcash >=", value, "billcash");
            return (Criteria) this;
        }

        public Criteria andBillcashLessThan(BigDecimal value) {
            addCriterion("billcash <", value, "billcash");
            return (Criteria) this;
        }

        public Criteria andBillcashLessThanOrEqualTo(BigDecimal value) {
            addCriterion("billcash <=", value, "billcash");
            return (Criteria) this;
        }

        public Criteria andBillcashIn(List<BigDecimal> values) {
            addCriterion("billcash in", values, "billcash");
            return (Criteria) this;
        }

        public Criteria andBillcashNotIn(List<BigDecimal> values) {
            addCriterion("billcash not in", values, "billcash");
            return (Criteria) this;
        }

        public Criteria andBillcashBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("billcash between", value1, value2, "billcash");
            return (Criteria) this;
        }

        public Criteria andBillcashNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("billcash not between", value1, value2, "billcash");
            return (Criteria) this;
        }

        public Criteria andExpresscomIsNull() {
            addCriterion("expresscom is null");
            return (Criteria) this;
        }

        public Criteria andExpresscomIsNotNull() {
            addCriterion("expresscom is not null");
            return (Criteria) this;
        }

        public Criteria andExpresscomEqualTo(String value) {
            addCriterion("expresscom =", value, "expresscom");
            return (Criteria) this;
        }

        public Criteria andExpresscomNotEqualTo(String value) {
            addCriterion("expresscom <>", value, "expresscom");
            return (Criteria) this;
        }

        public Criteria andExpresscomGreaterThan(String value) {
            addCriterion("expresscom >", value, "expresscom");
            return (Criteria) this;
        }

        public Criteria andExpresscomGreaterThanOrEqualTo(String value) {
            addCriterion("expresscom >=", value, "expresscom");
            return (Criteria) this;
        }

        public Criteria andExpresscomLessThan(String value) {
            addCriterion("expresscom <", value, "expresscom");
            return (Criteria) this;
        }

        public Criteria andExpresscomLessThanOrEqualTo(String value) {
            addCriterion("expresscom <=", value, "expresscom");
            return (Criteria) this;
        }

        public Criteria andExpresscomLike(String value) {
            addCriterion("expresscom like", value, "expresscom");
            return (Criteria) this;
        }

        public Criteria andExpresscomNotLike(String value) {
            addCriterion("expresscom not like", value, "expresscom");
            return (Criteria) this;
        }

        public Criteria andExpresscomIn(List<String> values) {
            addCriterion("expresscom in", values, "expresscom");
            return (Criteria) this;
        }

        public Criteria andExpresscomNotIn(List<String> values) {
            addCriterion("expresscom not in", values, "expresscom");
            return (Criteria) this;
        }

        public Criteria andExpresscomBetween(String value1, String value2) {
            addCriterion("expresscom between", value1, value2, "expresscom");
            return (Criteria) this;
        }

        public Criteria andExpresscomNotBetween(String value1, String value2) {
            addCriterion("expresscom not between", value1, value2, "expresscom");
            return (Criteria) this;
        }

        public Criteria andBillnoIsNull() {
            addCriterion("billno is null");
            return (Criteria) this;
        }

        public Criteria andBillnoIsNotNull() {
            addCriterion("billno is not null");
            return (Criteria) this;
        }

        public Criteria andBillnoEqualTo(String value) {
            addCriterion("billno =", value, "billno");
            return (Criteria) this;
        }

        public Criteria andBillnoNotEqualTo(String value) {
            addCriterion("billno <>", value, "billno");
            return (Criteria) this;
        }

        public Criteria andBillnoGreaterThan(String value) {
            addCriterion("billno >", value, "billno");
            return (Criteria) this;
        }

        public Criteria andBillnoGreaterThanOrEqualTo(String value) {
            addCriterion("billno >=", value, "billno");
            return (Criteria) this;
        }

        public Criteria andBillnoLessThan(String value) {
            addCriterion("billno <", value, "billno");
            return (Criteria) this;
        }

        public Criteria andBillnoLessThanOrEqualTo(String value) {
            addCriterion("billno <=", value, "billno");
            return (Criteria) this;
        }

        public Criteria andBillnoLike(String value) {
            addCriterion("billno like", value, "billno");
            return (Criteria) this;
        }

        public Criteria andBillnoNotLike(String value) {
            addCriterion("billno not like", value, "billno");
            return (Criteria) this;
        }

        public Criteria andBillnoIn(List<String> values) {
            addCriterion("billno in", values, "billno");
            return (Criteria) this;
        }

        public Criteria andBillnoNotIn(List<String> values) {
            addCriterion("billno not in", values, "billno");
            return (Criteria) this;
        }

        public Criteria andBillnoBetween(String value1, String value2) {
            addCriterion("billno between", value1, value2, "billno");
            return (Criteria) this;
        }

        public Criteria andBillnoNotBetween(String value1, String value2) {
            addCriterion("billno not between", value1, value2, "billno");
            return (Criteria) this;
        }

        public Criteria andBilltimeIsNull() {
            addCriterion("billtime is null");
            return (Criteria) this;
        }

        public Criteria andBilltimeIsNotNull() {
            addCriterion("billtime is not null");
            return (Criteria) this;
        }

        public Criteria andBilltimeEqualTo(Date value) {
            addCriterion("billtime =", value, "billtime");
            return (Criteria) this;
        }

        public Criteria andBilltimeNotEqualTo(Date value) {
            addCriterion("billtime <>", value, "billtime");
            return (Criteria) this;
        }

        public Criteria andBilltimeGreaterThan(Date value) {
            addCriterion("billtime >", value, "billtime");
            return (Criteria) this;
        }

        public Criteria andBilltimeGreaterThanOrEqualTo(Date value) {
            addCriterion("billtime >=", value, "billtime");
            return (Criteria) this;
        }

        public Criteria andBilltimeLessThan(Date value) {
            addCriterion("billtime <", value, "billtime");
            return (Criteria) this;
        }

        public Criteria andBilltimeLessThanOrEqualTo(Date value) {
            addCriterion("billtime <=", value, "billtime");
            return (Criteria) this;
        }

        public Criteria andBilltimeIn(List<Date> values) {
            addCriterion("billtime in", values, "billtime");
            return (Criteria) this;
        }

        public Criteria andBilltimeNotIn(List<Date> values) {
            addCriterion("billtime not in", values, "billtime");
            return (Criteria) this;
        }

        public Criteria andBilltimeBetween(Date value1, Date value2) {
            addCriterion("billtime between", value1, value2, "billtime");
            return (Criteria) this;
        }

        public Criteria andBilltimeNotBetween(Date value1, Date value2) {
            addCriterion("billtime not between", value1, value2, "billtime");
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

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(Short value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(Short value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(Short value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(Short value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(Short value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(Short value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<Short> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<Short> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(Short value1, Short value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(Short value1, Short value2) {
            addCriterion("remark not between", value1, value2, "remark");
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

        public Criteria andBillingrecordtypeIsNull() {
            addCriterion("billingrecordtype is null");
            return (Criteria) this;
        }

        public Criteria andBillingrecordtypeIsNotNull() {
            addCriterion("billingrecordtype is not null");
            return (Criteria) this;
        }

        public Criteria andBillingrecordtypeEqualTo(Short value) {
            addCriterion("billingrecordtype =", value, "billingrecordtype");
            return (Criteria) this;
        }

        public Criteria andBillingrecordtypeNotEqualTo(Short value) {
            addCriterion("billingrecordtype <>", value, "billingrecordtype");
            return (Criteria) this;
        }

        public Criteria andBillingrecordtypeGreaterThan(Short value) {
            addCriterion("billingrecordtype >", value, "billingrecordtype");
            return (Criteria) this;
        }

        public Criteria andBillingrecordtypeGreaterThanOrEqualTo(Short value) {
            addCriterion("billingrecordtype >=", value, "billingrecordtype");
            return (Criteria) this;
        }

        public Criteria andBillingrecordtypeLessThan(Short value) {
            addCriterion("billingrecordtype <", value, "billingrecordtype");
            return (Criteria) this;
        }

        public Criteria andBillingrecordtypeLessThanOrEqualTo(Short value) {
            addCriterion("billingrecordtype <=", value, "billingrecordtype");
            return (Criteria) this;
        }

        public Criteria andBillingrecordtypeIn(List<Short> values) {
            addCriterion("billingrecordtype in", values, "billingrecordtype");
            return (Criteria) this;
        }

        public Criteria andBillingrecordtypeNotIn(List<Short> values) {
            addCriterion("billingrecordtype not in", values, "billingrecordtype");
            return (Criteria) this;
        }

        public Criteria andBillingrecordtypeBetween(Short value1, Short value2) {
            addCriterion("billingrecordtype between", value1, value2, "billingrecordtype");
            return (Criteria) this;
        }

        public Criteria andBillingrecordtypeNotBetween(Short value1, Short value2) {
            addCriterion("billingrecordtype not between", value1, value2, "billingrecordtype");
            return (Criteria) this;
        }

        public Criteria andReceiveaddressIsNull() {
            addCriterion("receiveaddress is null");
            return (Criteria) this;
        }

        public Criteria andReceiveaddressIsNotNull() {
            addCriterion("receiveaddress is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveaddressEqualTo(String value) {
            addCriterion("receiveaddress =", value, "receiveaddress");
            return (Criteria) this;
        }

        public Criteria andReceiveaddressNotEqualTo(String value) {
            addCriterion("receiveaddress <>", value, "receiveaddress");
            return (Criteria) this;
        }

        public Criteria andReceiveaddressGreaterThan(String value) {
            addCriterion("receiveaddress >", value, "receiveaddress");
            return (Criteria) this;
        }

        public Criteria andReceiveaddressGreaterThanOrEqualTo(String value) {
            addCriterion("receiveaddress >=", value, "receiveaddress");
            return (Criteria) this;
        }

        public Criteria andReceiveaddressLessThan(String value) {
            addCriterion("receiveaddress <", value, "receiveaddress");
            return (Criteria) this;
        }

        public Criteria andReceiveaddressLessThanOrEqualTo(String value) {
            addCriterion("receiveaddress <=", value, "receiveaddress");
            return (Criteria) this;
        }

        public Criteria andReceiveaddressLike(String value) {
            addCriterion("receiveaddress like", value, "receiveaddress");
            return (Criteria) this;
        }

        public Criteria andReceiveaddressNotLike(String value) {
            addCriterion("receiveaddress not like", value, "receiveaddress");
            return (Criteria) this;
        }

        public Criteria andReceiveaddressIn(List<String> values) {
            addCriterion("receiveaddress in", values, "receiveaddress");
            return (Criteria) this;
        }

        public Criteria andReceiveaddressNotIn(List<String> values) {
            addCriterion("receiveaddress not in", values, "receiveaddress");
            return (Criteria) this;
        }

        public Criteria andReceiveaddressBetween(String value1, String value2) {
            addCriterion("receiveaddress between", value1, value2, "receiveaddress");
            return (Criteria) this;
        }

        public Criteria andReceiveaddressNotBetween(String value1, String value2) {
            addCriterion("receiveaddress not between", value1, value2, "receiveaddress");
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