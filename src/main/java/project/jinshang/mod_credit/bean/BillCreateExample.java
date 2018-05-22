package project.jinshang.mod_credit.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BillCreateExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BillCreateExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andBuyeridIsNull() {
            addCriterion("buyerid is null");
            return (Criteria) this;
        }

        public Criteria andBuyeridIsNotNull() {
            addCriterion("buyerid is not null");
            return (Criteria) this;
        }

        public Criteria andBuyeridEqualTo(Long value) {
            addCriterion("buyerid =", value, "buyerid");
            return (Criteria) this;
        }

        public Criteria andBuyeridNotEqualTo(Long value) {
            addCriterion("buyerid <>", value, "buyerid");
            return (Criteria) this;
        }

        public Criteria andBuyeridGreaterThan(Long value) {
            addCriterion("buyerid >", value, "buyerid");
            return (Criteria) this;
        }

        public Criteria andBuyeridGreaterThanOrEqualTo(Long value) {
            addCriterion("buyerid >=", value, "buyerid");
            return (Criteria) this;
        }

        public Criteria andBuyeridLessThan(Long value) {
            addCriterion("buyerid <", value, "buyerid");
            return (Criteria) this;
        }

        public Criteria andBuyeridLessThanOrEqualTo(Long value) {
            addCriterion("buyerid <=", value, "buyerid");
            return (Criteria) this;
        }

        public Criteria andBuyeridIn(List<Long> values) {
            addCriterion("buyerid in", values, "buyerid");
            return (Criteria) this;
        }

        public Criteria andBuyeridNotIn(List<Long> values) {
            addCriterion("buyerid not in", values, "buyerid");
            return (Criteria) this;
        }

        public Criteria andBuyeridBetween(Long value1, Long value2) {
            addCriterion("buyerid between", value1, value2, "buyerid");
            return (Criteria) this;
        }

        public Criteria andBuyeridNotBetween(Long value1, Long value2) {
            addCriterion("buyerid not between", value1, value2, "buyerid");
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

        public Criteria andMoneyIsNull() {
            addCriterion("money is null");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNotNull() {
            addCriterion("money is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyEqualTo(BigDecimal value) {
            addCriterion("money =", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotEqualTo(BigDecimal value) {
            addCriterion("money <>", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThan(BigDecimal value) {
            addCriterion("money >", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("money >=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThan(BigDecimal value) {
            addCriterion("money <", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("money <=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyIn(List<BigDecimal> values) {
            addCriterion("money in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotIn(List<BigDecimal> values) {
            addCriterion("money not in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("money between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("money not between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andAmountpaidIsNull() {
            addCriterion("amountpaid is null");
            return (Criteria) this;
        }

        public Criteria andAmountpaidIsNotNull() {
            addCriterion("amountpaid is not null");
            return (Criteria) this;
        }

        public Criteria andAmountpaidEqualTo(BigDecimal value) {
            addCriterion("amountpaid =", value, "amountpaid");
            return (Criteria) this;
        }

        public Criteria andAmountpaidNotEqualTo(BigDecimal value) {
            addCriterion("amountpaid <>", value, "amountpaid");
            return (Criteria) this;
        }

        public Criteria andAmountpaidGreaterThan(BigDecimal value) {
            addCriterion("amountpaid >", value, "amountpaid");
            return (Criteria) this;
        }

        public Criteria andAmountpaidGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("amountpaid >=", value, "amountpaid");
            return (Criteria) this;
        }

        public Criteria andAmountpaidLessThan(BigDecimal value) {
            addCriterion("amountpaid <", value, "amountpaid");
            return (Criteria) this;
        }

        public Criteria andAmountpaidLessThanOrEqualTo(BigDecimal value) {
            addCriterion("amountpaid <=", value, "amountpaid");
            return (Criteria) this;
        }

        public Criteria andAmountpaidIn(List<BigDecimal> values) {
            addCriterion("amountpaid in", values, "amountpaid");
            return (Criteria) this;
        }

        public Criteria andAmountpaidNotIn(List<BigDecimal> values) {
            addCriterion("amountpaid not in", values, "amountpaid");
            return (Criteria) this;
        }

        public Criteria andAmountpaidBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amountpaid between", value1, value2, "amountpaid");
            return (Criteria) this;
        }

        public Criteria andAmountpaidNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amountpaid not between", value1, value2, "amountpaid");
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

        public Criteria andSettlementtimeIsNull() {
            addCriterion("settlementtime is null");
            return (Criteria) this;
        }

        public Criteria andSettlementtimeIsNotNull() {
            addCriterion("settlementtime is not null");
            return (Criteria) this;
        }

        public Criteria andSettlementtimeEqualTo(String value) {
            addCriterion("settlementtime =", value, "settlementtime");
            return (Criteria) this;
        }

        public Criteria andSettlementtimeNotEqualTo(String value) {
            addCriterion("settlementtime <>", value, "settlementtime");
            return (Criteria) this;
        }

        public Criteria andSettlementtimeGreaterThan(String value) {
            addCriterion("settlementtime >", value, "settlementtime");
            return (Criteria) this;
        }

        public Criteria andSettlementtimeGreaterThanOrEqualTo(String value) {
            addCriterion("settlementtime >=", value, "settlementtime");
            return (Criteria) this;
        }

        public Criteria andSettlementtimeLessThan(String value) {
            addCriterion("settlementtime <", value, "settlementtime");
            return (Criteria) this;
        }

        public Criteria andSettlementtimeLessThanOrEqualTo(String value) {
            addCriterion("settlementtime <=", value, "settlementtime");
            return (Criteria) this;
        }

        public Criteria andSettlementtimeLike(String value) {
            addCriterion("settlementtime like", value, "settlementtime");
            return (Criteria) this;
        }

        public Criteria andSettlementtimeNotLike(String value) {
            addCriterion("settlementtime not like", value, "settlementtime");
            return (Criteria) this;
        }

        public Criteria andSettlementtimeIn(List<String> values) {
            addCriterion("settlementtime in", values, "settlementtime");
            return (Criteria) this;
        }

        public Criteria andSettlementtimeNotIn(List<String> values) {
            addCriterion("settlementtime not in", values, "settlementtime");
            return (Criteria) this;
        }

        public Criteria andSettlementtimeBetween(String value1, String value2) {
            addCriterion("settlementtime between", value1, value2, "settlementtime");
            return (Criteria) this;
        }

        public Criteria andSettlementtimeNotBetween(String value1, String value2) {
            addCriterion("settlementtime not between", value1, value2, "settlementtime");
            return (Criteria) this;
        }

        public Criteria andRecordsIsNull() {
            addCriterion("records is null");
            return (Criteria) this;
        }

        public Criteria andRecordsIsNotNull() {
            addCriterion("records is not null");
            return (Criteria) this;
        }

        public Criteria andRecordsEqualTo(Integer value) {
            addCriterion("records =", value, "records");
            return (Criteria) this;
        }

        public Criteria andRecordsNotEqualTo(Integer value) {
            addCriterion("records <>", value, "records");
            return (Criteria) this;
        }

        public Criteria andRecordsGreaterThan(Integer value) {
            addCriterion("records >", value, "records");
            return (Criteria) this;
        }

        public Criteria andRecordsGreaterThanOrEqualTo(Integer value) {
            addCriterion("records >=", value, "records");
            return (Criteria) this;
        }

        public Criteria andRecordsLessThan(Integer value) {
            addCriterion("records <", value, "records");
            return (Criteria) this;
        }

        public Criteria andRecordsLessThanOrEqualTo(Integer value) {
            addCriterion("records <=", value, "records");
            return (Criteria) this;
        }

        public Criteria andRecordsIn(List<Integer> values) {
            addCriterion("records in", values, "records");
            return (Criteria) this;
        }

        public Criteria andRecordsNotIn(List<Integer> values) {
            addCriterion("records not in", values, "records");
            return (Criteria) this;
        }

        public Criteria andRecordsBetween(Integer value1, Integer value2) {
            addCriterion("records between", value1, value2, "records");
            return (Criteria) this;
        }

        public Criteria andRecordsNotBetween(Integer value1, Integer value2) {
            addCriterion("records not between", value1, value2, "records");
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

        public Criteria andLastrepaymentdayIsNull() {
            addCriterion("lastrepaymentday is null");
            return (Criteria) this;
        }

        public Criteria andLastrepaymentdayIsNotNull() {
            addCriterion("lastrepaymentday is not null");
            return (Criteria) this;
        }

        public Criteria andLastrepaymentdayEqualTo(Date value) {
            addCriterion("lastrepaymentday =", value, "lastrepaymentday");
            return (Criteria) this;
        }

        public Criteria andLastrepaymentdayNotEqualTo(Date value) {
            addCriterion("lastrepaymentday <>", value, "lastrepaymentday");
            return (Criteria) this;
        }

        public Criteria andLastrepaymentdayGreaterThan(Date value) {
            addCriterion("lastrepaymentday >", value, "lastrepaymentday");
            return (Criteria) this;
        }

        public Criteria andLastrepaymentdayGreaterThanOrEqualTo(Date value) {
            addCriterion("lastrepaymentday >=", value, "lastrepaymentday");
            return (Criteria) this;
        }

        public Criteria andLastrepaymentdayLessThan(Date value) {
            addCriterion("lastrepaymentday <", value, "lastrepaymentday");
            return (Criteria) this;
        }

        public Criteria andLastrepaymentdayLessThanOrEqualTo(Date value) {
            addCriterion("lastrepaymentday <=", value, "lastrepaymentday");
            return (Criteria) this;
        }

        public Criteria andLastrepaymentdayIn(List<Date> values) {
            addCriterion("lastrepaymentday in", values, "lastrepaymentday");
            return (Criteria) this;
        }

        public Criteria andLastrepaymentdayNotIn(List<Date> values) {
            addCriterion("lastrepaymentday not in", values, "lastrepaymentday");
            return (Criteria) this;
        }

        public Criteria andLastrepaymentdayBetween(Date value1, Date value2) {
            addCriterion("lastrepaymentday between", value1, value2, "lastrepaymentday");
            return (Criteria) this;
        }

        public Criteria andLastrepaymentdayNotBetween(Date value1, Date value2) {
            addCriterion("lastrepaymentday not between", value1, value2, "lastrepaymentday");
            return (Criteria) this;
        }

        public Criteria andIllegalIsNull() {
            addCriterion("illegal is null");
            return (Criteria) this;
        }

        public Criteria andIllegalIsNotNull() {
            addCriterion("illegal is not null");
            return (Criteria) this;
        }

        public Criteria andIllegalEqualTo(Boolean value) {
            addCriterion("illegal =", value, "illegal");
            return (Criteria) this;
        }

        public Criteria andIllegalNotEqualTo(Boolean value) {
            addCriterion("illegal <>", value, "illegal");
            return (Criteria) this;
        }

        public Criteria andIllegalGreaterThan(Boolean value) {
            addCriterion("illegal >", value, "illegal");
            return (Criteria) this;
        }

        public Criteria andIllegalGreaterThanOrEqualTo(Boolean value) {
            addCriterion("illegal >=", value, "illegal");
            return (Criteria) this;
        }

        public Criteria andIllegalLessThan(Boolean value) {
            addCriterion("illegal <", value, "illegal");
            return (Criteria) this;
        }

        public Criteria andIllegalLessThanOrEqualTo(Boolean value) {
            addCriterion("illegal <=", value, "illegal");
            return (Criteria) this;
        }

        public Criteria andIllegalIn(List<Boolean> values) {
            addCriterion("illegal in", values, "illegal");
            return (Criteria) this;
        }

        public Criteria andIllegalNotIn(List<Boolean> values) {
            addCriterion("illegal not in", values, "illegal");
            return (Criteria) this;
        }

        public Criteria andIllegalBetween(Boolean value1, Boolean value2) {
            addCriterion("illegal between", value1, value2, "illegal");
            return (Criteria) this;
        }

        public Criteria andIllegalNotBetween(Boolean value1, Boolean value2) {
            addCriterion("illegal not between", value1, value2, "illegal");
            return (Criteria) this;
        }

        public Criteria andIllegaldaysIsNull() {
            addCriterion("illegaldays is null");
            return (Criteria) this;
        }

        public Criteria andIllegaldaysIsNotNull() {
            addCriterion("illegaldays is not null");
            return (Criteria) this;
        }

        public Criteria andIllegaldaysEqualTo(Integer value) {
            addCriterion("illegaldays =", value, "illegaldays");
            return (Criteria) this;
        }

        public Criteria andIllegaldaysNotEqualTo(Integer value) {
            addCriterion("illegaldays <>", value, "illegaldays");
            return (Criteria) this;
        }

        public Criteria andIllegaldaysGreaterThan(Integer value) {
            addCriterion("illegaldays >", value, "illegaldays");
            return (Criteria) this;
        }

        public Criteria andIllegaldaysGreaterThanOrEqualTo(Integer value) {
            addCriterion("illegaldays >=", value, "illegaldays");
            return (Criteria) this;
        }

        public Criteria andIllegaldaysLessThan(Integer value) {
            addCriterion("illegaldays <", value, "illegaldays");
            return (Criteria) this;
        }

        public Criteria andIllegaldaysLessThanOrEqualTo(Integer value) {
            addCriterion("illegaldays <=", value, "illegaldays");
            return (Criteria) this;
        }

        public Criteria andIllegaldaysIn(List<Integer> values) {
            addCriterion("illegaldays in", values, "illegaldays");
            return (Criteria) this;
        }

        public Criteria andIllegaldaysNotIn(List<Integer> values) {
            addCriterion("illegaldays not in", values, "illegaldays");
            return (Criteria) this;
        }

        public Criteria andIllegaldaysBetween(Integer value1, Integer value2) {
            addCriterion("illegaldays between", value1, value2, "illegaldays");
            return (Criteria) this;
        }

        public Criteria andIllegaldaysNotBetween(Integer value1, Integer value2) {
            addCriterion("illegaldays not between", value1, value2, "illegaldays");
            return (Criteria) this;
        }

        public Criteria andIllegalmoneyIsNull() {
            addCriterion("illegalmoney is null");
            return (Criteria) this;
        }

        public Criteria andIllegalmoneyIsNotNull() {
            addCriterion("illegalmoney is not null");
            return (Criteria) this;
        }

        public Criteria andIllegalmoneyEqualTo(BigDecimal value) {
            addCriterion("illegalmoney =", value, "illegalmoney");
            return (Criteria) this;
        }

        public Criteria andIllegalmoneyNotEqualTo(BigDecimal value) {
            addCriterion("illegalmoney <>", value, "illegalmoney");
            return (Criteria) this;
        }

        public Criteria andIllegalmoneyGreaterThan(BigDecimal value) {
            addCriterion("illegalmoney >", value, "illegalmoney");
            return (Criteria) this;
        }

        public Criteria andIllegalmoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("illegalmoney >=", value, "illegalmoney");
            return (Criteria) this;
        }

        public Criteria andIllegalmoneyLessThan(BigDecimal value) {
            addCriterion("illegalmoney <", value, "illegalmoney");
            return (Criteria) this;
        }

        public Criteria andIllegalmoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("illegalmoney <=", value, "illegalmoney");
            return (Criteria) this;
        }

        public Criteria andIllegalmoneyIn(List<BigDecimal> values) {
            addCriterion("illegalmoney in", values, "illegalmoney");
            return (Criteria) this;
        }

        public Criteria andIllegalmoneyNotIn(List<BigDecimal> values) {
            addCriterion("illegalmoney not in", values, "illegalmoney");
            return (Criteria) this;
        }

        public Criteria andIllegalmoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("illegalmoney between", value1, value2, "illegalmoney");
            return (Criteria) this;
        }

        public Criteria andIllegalmoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("illegalmoney not between", value1, value2, "illegalmoney");
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