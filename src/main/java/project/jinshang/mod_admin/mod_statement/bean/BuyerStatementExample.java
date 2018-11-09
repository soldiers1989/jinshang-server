package project.jinshang.mod_admin.mod_statement.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BuyerStatementExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BuyerStatementExample() {
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

        public Criteria andBillrecoidIsNull() {
            addCriterion("billrecoid is null");
            return (Criteria) this;
        }

        public Criteria andBillrecoidIsNotNull() {
            addCriterion("billrecoid is not null");
            return (Criteria) this;
        }

        public Criteria andBillrecoidEqualTo(Long value) {
            addCriterion("billrecoid =", value, "billrecoid");
            return (Criteria) this;
        }

        public Criteria andBillrecoidNotEqualTo(Long value) {
            addCriterion("billrecoid <>", value, "billrecoid");
            return (Criteria) this;
        }

        public Criteria andBillrecoidGreaterThan(Long value) {
            addCriterion("billrecoid >", value, "billrecoid");
            return (Criteria) this;
        }

        public Criteria andBillrecoidGreaterThanOrEqualTo(Long value) {
            addCriterion("billrecoid >=", value, "billrecoid");
            return (Criteria) this;
        }

        public Criteria andBillrecoidLessThan(Long value) {
            addCriterion("billrecoid <", value, "billrecoid");
            return (Criteria) this;
        }

        public Criteria andBillrecoidLessThanOrEqualTo(Long value) {
            addCriterion("billrecoid <=", value, "billrecoid");
            return (Criteria) this;
        }

        public Criteria andBillrecoidIn(List<Long> values) {
            addCriterion("billrecoid in", values, "billrecoid");
            return (Criteria) this;
        }

        public Criteria andBillrecoidNotIn(List<Long> values) {
            addCriterion("billrecoid not in", values, "billrecoid");
            return (Criteria) this;
        }

        public Criteria andBillrecoidBetween(Long value1, Long value2) {
            addCriterion("billrecoid between", value1, value2, "billrecoid");
            return (Criteria) this;
        }

        public Criteria andBillrecoidNotBetween(Long value1, Long value2) {
            addCriterion("billrecoid not between", value1, value2, "billrecoid");
            return (Criteria) this;
        }

        public Criteria andContractnoIsNull() {
            addCriterion("contractno is null");
            return (Criteria) this;
        }

        public Criteria andContractnoIsNotNull() {
            addCriterion("contractno is not null");
            return (Criteria) this;
        }

        public Criteria andContractnoEqualTo(String value) {
            addCriterion("contractno =", value, "contractno");
            return (Criteria) this;
        }

        public Criteria andContractnoNotEqualTo(String value) {
            addCriterion("contractno <>", value, "contractno");
            return (Criteria) this;
        }

        public Criteria andContractnoGreaterThan(String value) {
            addCriterion("contractno >", value, "contractno");
            return (Criteria) this;
        }

        public Criteria andContractnoGreaterThanOrEqualTo(String value) {
            addCriterion("contractno >=", value, "contractno");
            return (Criteria) this;
        }

        public Criteria andContractnoLessThan(String value) {
            addCriterion("contractno <", value, "contractno");
            return (Criteria) this;
        }

        public Criteria andContractnoLessThanOrEqualTo(String value) {
            addCriterion("contractno <=", value, "contractno");
            return (Criteria) this;
        }

        public Criteria andContractnoLike(String value) {
            addCriterion("contractno like", value, "contractno");
            return (Criteria) this;
        }

        public Criteria andContractnoNotLike(String value) {
            addCriterion("contractno not like", value, "contractno");
            return (Criteria) this;
        }

        public Criteria andContractnoIn(List<String> values) {
            addCriterion("contractno in", values, "contractno");
            return (Criteria) this;
        }

        public Criteria andContractnoNotIn(List<String> values) {
            addCriterion("contractno not in", values, "contractno");
            return (Criteria) this;
        }

        public Criteria andContractnoBetween(String value1, String value2) {
            addCriterion("contractno between", value1, value2, "contractno");
            return (Criteria) this;
        }

        public Criteria andContractnoNotBetween(String value1, String value2) {
            addCriterion("contractno not between", value1, value2, "contractno");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Short value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Short value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Short value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Short value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Short value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Short> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Short> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Short value1, Short value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Short value1, Short value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andDeliveryamountIsNull() {
            addCriterion("deliveryamount is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryamountIsNotNull() {
            addCriterion("deliveryamount is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryamountEqualTo(BigDecimal value) {
            addCriterion("deliveryamount =", value, "deliveryamount");
            return (Criteria) this;
        }

        public Criteria andDeliveryamountNotEqualTo(BigDecimal value) {
            addCriterion("deliveryamount <>", value, "deliveryamount");
            return (Criteria) this;
        }

        public Criteria andDeliveryamountGreaterThan(BigDecimal value) {
            addCriterion("deliveryamount >", value, "deliveryamount");
            return (Criteria) this;
        }

        public Criteria andDeliveryamountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("deliveryamount >=", value, "deliveryamount");
            return (Criteria) this;
        }

        public Criteria andDeliveryamountLessThan(BigDecimal value) {
            addCriterion("deliveryamount <", value, "deliveryamount");
            return (Criteria) this;
        }

        public Criteria andDeliveryamountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("deliveryamount <=", value, "deliveryamount");
            return (Criteria) this;
        }

        public Criteria andDeliveryamountIn(List<BigDecimal> values) {
            addCriterion("deliveryamount in", values, "deliveryamount");
            return (Criteria) this;
        }

        public Criteria andDeliveryamountNotIn(List<BigDecimal> values) {
            addCriterion("deliveryamount not in", values, "deliveryamount");
            return (Criteria) this;
        }

        public Criteria andDeliveryamountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deliveryamount between", value1, value2, "deliveryamount");
            return (Criteria) this;
        }

        public Criteria andDeliveryamountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deliveryamount not between", value1, value2, "deliveryamount");
            return (Criteria) this;
        }

        public Criteria andReceiptamountIsNull() {
            addCriterion("receiptamount is null");
            return (Criteria) this;
        }

        public Criteria andReceiptamountIsNotNull() {
            addCriterion("receiptamount is not null");
            return (Criteria) this;
        }

        public Criteria andReceiptamountEqualTo(BigDecimal value) {
            addCriterion("receiptamount =", value, "receiptamount");
            return (Criteria) this;
        }

        public Criteria andReceiptamountNotEqualTo(BigDecimal value) {
            addCriterion("receiptamount <>", value, "receiptamount");
            return (Criteria) this;
        }

        public Criteria andReceiptamountGreaterThan(BigDecimal value) {
            addCriterion("receiptamount >", value, "receiptamount");
            return (Criteria) this;
        }

        public Criteria andReceiptamountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("receiptamount >=", value, "receiptamount");
            return (Criteria) this;
        }

        public Criteria andReceiptamountLessThan(BigDecimal value) {
            addCriterion("receiptamount <", value, "receiptamount");
            return (Criteria) this;
        }

        public Criteria andReceiptamountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("receiptamount <=", value, "receiptamount");
            return (Criteria) this;
        }

        public Criteria andReceiptamountIn(List<BigDecimal> values) {
            addCriterion("receiptamount in", values, "receiptamount");
            return (Criteria) this;
        }

        public Criteria andReceiptamountNotIn(List<BigDecimal> values) {
            addCriterion("receiptamount not in", values, "receiptamount");
            return (Criteria) this;
        }

        public Criteria andReceiptamountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("receiptamount between", value1, value2, "receiptamount");
            return (Criteria) this;
        }

        public Criteria andReceiptamountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("receiptamount not between", value1, value2, "receiptamount");
            return (Criteria) this;
        }

        public Criteria andReceivableamountIsNull() {
            addCriterion("receivableamount is null");
            return (Criteria) this;
        }

        public Criteria andReceivableamountIsNotNull() {
            addCriterion("receivableamount is not null");
            return (Criteria) this;
        }

        public Criteria andReceivableamountEqualTo(BigDecimal value) {
            addCriterion("receivableamount =", value, "receivableamount");
            return (Criteria) this;
        }

        public Criteria andReceivableamountNotEqualTo(BigDecimal value) {
            addCriterion("receivableamount <>", value, "receivableamount");
            return (Criteria) this;
        }

        public Criteria andReceivableamountGreaterThan(BigDecimal value) {
            addCriterion("receivableamount >", value, "receivableamount");
            return (Criteria) this;
        }

        public Criteria andReceivableamountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("receivableamount >=", value, "receivableamount");
            return (Criteria) this;
        }

        public Criteria andReceivableamountLessThan(BigDecimal value) {
            addCriterion("receivableamount <", value, "receivableamount");
            return (Criteria) this;
        }

        public Criteria andReceivableamountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("receivableamount <=", value, "receivableamount");
            return (Criteria) this;
        }

        public Criteria andReceivableamountIn(List<BigDecimal> values) {
            addCriterion("receivableamount in", values, "receivableamount");
            return (Criteria) this;
        }

        public Criteria andReceivableamountNotIn(List<BigDecimal> values) {
            addCriterion("receivableamount not in", values, "receivableamount");
            return (Criteria) this;
        }

        public Criteria andReceivableamountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("receivableamount between", value1, value2, "receivableamount");
            return (Criteria) this;
        }

        public Criteria andReceivableamountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("receivableamount not between", value1, value2, "receivableamount");
            return (Criteria) this;
        }

        public Criteria andInvoiceamountIsNull() {
            addCriterion("invoiceamount is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceamountIsNotNull() {
            addCriterion("invoiceamount is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceamountEqualTo(BigDecimal value) {
            addCriterion("invoiceamount =", value, "invoiceamount");
            return (Criteria) this;
        }

        public Criteria andInvoiceamountNotEqualTo(BigDecimal value) {
            addCriterion("invoiceamount <>", value, "invoiceamount");
            return (Criteria) this;
        }

        public Criteria andInvoiceamountGreaterThan(BigDecimal value) {
            addCriterion("invoiceamount >", value, "invoiceamount");
            return (Criteria) this;
        }

        public Criteria andInvoiceamountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("invoiceamount >=", value, "invoiceamount");
            return (Criteria) this;
        }

        public Criteria andInvoiceamountLessThan(BigDecimal value) {
            addCriterion("invoiceamount <", value, "invoiceamount");
            return (Criteria) this;
        }

        public Criteria andInvoiceamountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("invoiceamount <=", value, "invoiceamount");
            return (Criteria) this;
        }

        public Criteria andInvoiceamountIn(List<BigDecimal> values) {
            addCriterion("invoiceamount in", values, "invoiceamount");
            return (Criteria) this;
        }

        public Criteria andInvoiceamountNotIn(List<BigDecimal> values) {
            addCriterion("invoiceamount not in", values, "invoiceamount");
            return (Criteria) this;
        }

        public Criteria andInvoiceamountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("invoiceamount between", value1, value2, "invoiceamount");
            return (Criteria) this;
        }

        public Criteria andInvoiceamountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("invoiceamount not between", value1, value2, "invoiceamount");
            return (Criteria) this;
        }

        public Criteria andInvoicebalanceIsNull() {
            addCriterion("invoicebalance is null");
            return (Criteria) this;
        }

        public Criteria andInvoicebalanceIsNotNull() {
            addCriterion("invoicebalance is not null");
            return (Criteria) this;
        }

        public Criteria andInvoicebalanceEqualTo(BigDecimal value) {
            addCriterion("invoicebalance =", value, "invoicebalance");
            return (Criteria) this;
        }

        public Criteria andInvoicebalanceNotEqualTo(BigDecimal value) {
            addCriterion("invoicebalance <>", value, "invoicebalance");
            return (Criteria) this;
        }

        public Criteria andInvoicebalanceGreaterThan(BigDecimal value) {
            addCriterion("invoicebalance >", value, "invoicebalance");
            return (Criteria) this;
        }

        public Criteria andInvoicebalanceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("invoicebalance >=", value, "invoicebalance");
            return (Criteria) this;
        }

        public Criteria andInvoicebalanceLessThan(BigDecimal value) {
            addCriterion("invoicebalance <", value, "invoicebalance");
            return (Criteria) this;
        }

        public Criteria andInvoicebalanceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("invoicebalance <=", value, "invoicebalance");
            return (Criteria) this;
        }

        public Criteria andInvoicebalanceIn(List<BigDecimal> values) {
            addCriterion("invoicebalance in", values, "invoicebalance");
            return (Criteria) this;
        }

        public Criteria andInvoicebalanceNotIn(List<BigDecimal> values) {
            addCriterion("invoicebalance not in", values, "invoicebalance");
            return (Criteria) this;
        }

        public Criteria andInvoicebalanceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("invoicebalance between", value1, value2, "invoicebalance");
            return (Criteria) this;
        }

        public Criteria andInvoicebalanceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("invoicebalance not between", value1, value2, "invoicebalance");
            return (Criteria) this;
        }

        public Criteria andPaytypeIsNull() {
            addCriterion("paytype is null");
            return (Criteria) this;
        }

        public Criteria andPaytypeIsNotNull() {
            addCriterion("paytype is not null");
            return (Criteria) this;
        }

        public Criteria andPaytypeEqualTo(Short value) {
            addCriterion("paytype =", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeNotEqualTo(Short value) {
            addCriterion("paytype <>", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeGreaterThan(Short value) {
            addCriterion("paytype >", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeGreaterThanOrEqualTo(Short value) {
            addCriterion("paytype >=", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeLessThan(Short value) {
            addCriterion("paytype <", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeLessThanOrEqualTo(Short value) {
            addCriterion("paytype <=", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeIn(List<Short> values) {
            addCriterion("paytype in", values, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeNotIn(List<Short> values) {
            addCriterion("paytype not in", values, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeBetween(Short value1, Short value2) {
            addCriterion("paytype between", value1, value2, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeNotBetween(Short value1, Short value2) {
            addCriterion("paytype not between", value1, value2, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaynoIsNull() {
            addCriterion("payno is null");
            return (Criteria) this;
        }

        public Criteria andPaynoIsNotNull() {
            addCriterion("payno is not null");
            return (Criteria) this;
        }

        public Criteria andPaynoEqualTo(String value) {
            addCriterion("payno =", value, "payno");
            return (Criteria) this;
        }

        public Criteria andPaynoNotEqualTo(String value) {
            addCriterion("payno <>", value, "payno");
            return (Criteria) this;
        }

        public Criteria andPaynoGreaterThan(String value) {
            addCriterion("payno >", value, "payno");
            return (Criteria) this;
        }

        public Criteria andPaynoGreaterThanOrEqualTo(String value) {
            addCriterion("payno >=", value, "payno");
            return (Criteria) this;
        }

        public Criteria andPaynoLessThan(String value) {
            addCriterion("payno <", value, "payno");
            return (Criteria) this;
        }

        public Criteria andPaynoLessThanOrEqualTo(String value) {
            addCriterion("payno <=", value, "payno");
            return (Criteria) this;
        }

        public Criteria andPaynoLike(String value) {
            addCriterion("payno like", value, "payno");
            return (Criteria) this;
        }

        public Criteria andPaynoNotLike(String value) {
            addCriterion("payno not like", value, "payno");
            return (Criteria) this;
        }

        public Criteria andPaynoIn(List<String> values) {
            addCriterion("payno in", values, "payno");
            return (Criteria) this;
        }

        public Criteria andPaynoNotIn(List<String> values) {
            addCriterion("payno not in", values, "payno");
            return (Criteria) this;
        }

        public Criteria andPaynoBetween(String value1, String value2) {
            addCriterion("payno between", value1, value2, "payno");
            return (Criteria) this;
        }

        public Criteria andPaynoNotBetween(String value1, String value2) {
            addCriterion("payno not between", value1, value2, "payno");
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