package project.jinshang.mod_admin.mod_transet.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TransactionSettingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TransactionSettingExample() {
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

        public Criteria andUnpaidtimeoutIsNull() {
            addCriterion("unpaidtimeout is null");
            return (Criteria) this;
        }

        public Criteria andUnpaidtimeoutIsNotNull() {
            addCriterion("unpaidtimeout is not null");
            return (Criteria) this;
        }

        public Criteria andUnpaidtimeoutEqualTo(BigDecimal value) {
            addCriterion("unpaidtimeout =", value, "unpaidtimeout");
            return (Criteria) this;
        }

        public Criteria andUnpaidtimeoutNotEqualTo(BigDecimal value) {
            addCriterion("unpaidtimeout <>", value, "unpaidtimeout");
            return (Criteria) this;
        }

        public Criteria andUnpaidtimeoutGreaterThan(BigDecimal value) {
            addCriterion("unpaidtimeout >", value, "unpaidtimeout");
            return (Criteria) this;
        }

        public Criteria andUnpaidtimeoutGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("unpaidtimeout >=", value, "unpaidtimeout");
            return (Criteria) this;
        }

        public Criteria andUnpaidtimeoutLessThan(BigDecimal value) {
            addCriterion("unpaidtimeout <", value, "unpaidtimeout");
            return (Criteria) this;
        }

        public Criteria andUnpaidtimeoutLessThanOrEqualTo(BigDecimal value) {
            addCriterion("unpaidtimeout <=", value, "unpaidtimeout");
            return (Criteria) this;
        }

        public Criteria andUnpaidtimeoutIn(List<BigDecimal> values) {
            addCriterion("unpaidtimeout in", values, "unpaidtimeout");
            return (Criteria) this;
        }

        public Criteria andUnpaidtimeoutNotIn(List<BigDecimal> values) {
            addCriterion("unpaidtimeout not in", values, "unpaidtimeout");
            return (Criteria) this;
        }

        public Criteria andUnpaidtimeoutBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("unpaidtimeout between", value1, value2, "unpaidtimeout");
            return (Criteria) this;
        }

        public Criteria andUnpaidtimeoutNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("unpaidtimeout not between", value1, value2, "unpaidtimeout");
            return (Criteria) this;
        }

        public Criteria andConfirmreceipttimeoutIsNull() {
            addCriterion("confirmreceipttimeout is null");
            return (Criteria) this;
        }

        public Criteria andConfirmreceipttimeoutIsNotNull() {
            addCriterion("confirmreceipttimeout is not null");
            return (Criteria) this;
        }

        public Criteria andConfirmreceipttimeoutEqualTo(BigDecimal value) {
            addCriterion("confirmreceipttimeout =", value, "confirmreceipttimeout");
            return (Criteria) this;
        }

        public Criteria andConfirmreceipttimeoutNotEqualTo(BigDecimal value) {
            addCriterion("confirmreceipttimeout <>", value, "confirmreceipttimeout");
            return (Criteria) this;
        }

        public Criteria andConfirmreceipttimeoutGreaterThan(BigDecimal value) {
            addCriterion("confirmreceipttimeout >", value, "confirmreceipttimeout");
            return (Criteria) this;
        }

        public Criteria andConfirmreceipttimeoutGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("confirmreceipttimeout >=", value, "confirmreceipttimeout");
            return (Criteria) this;
        }

        public Criteria andConfirmreceipttimeoutLessThan(BigDecimal value) {
            addCriterion("confirmreceipttimeout <", value, "confirmreceipttimeout");
            return (Criteria) this;
        }

        public Criteria andConfirmreceipttimeoutLessThanOrEqualTo(BigDecimal value) {
            addCriterion("confirmreceipttimeout <=", value, "confirmreceipttimeout");
            return (Criteria) this;
        }

        public Criteria andConfirmreceipttimeoutIn(List<BigDecimal> values) {
            addCriterion("confirmreceipttimeout in", values, "confirmreceipttimeout");
            return (Criteria) this;
        }

        public Criteria andConfirmreceipttimeoutNotIn(List<BigDecimal> values) {
            addCriterion("confirmreceipttimeout not in", values, "confirmreceipttimeout");
            return (Criteria) this;
        }

        public Criteria andConfirmreceipttimeoutBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("confirmreceipttimeout between", value1, value2, "confirmreceipttimeout");
            return (Criteria) this;
        }

        public Criteria andConfirmreceipttimeoutNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("confirmreceipttimeout not between", value1, value2, "confirmreceipttimeout");
            return (Criteria) this;
        }

        public Criteria andOrderreturnperiodIsNull() {
            addCriterion("orderreturnperiod is null");
            return (Criteria) this;
        }

        public Criteria andOrderreturnperiodIsNotNull() {
            addCriterion("orderreturnperiod is not null");
            return (Criteria) this;
        }

        public Criteria andOrderreturnperiodEqualTo(BigDecimal value) {
            addCriterion("orderreturnperiod =", value, "orderreturnperiod");
            return (Criteria) this;
        }

        public Criteria andOrderreturnperiodNotEqualTo(BigDecimal value) {
            addCriterion("orderreturnperiod <>", value, "orderreturnperiod");
            return (Criteria) this;
        }

        public Criteria andOrderreturnperiodGreaterThan(BigDecimal value) {
            addCriterion("orderreturnperiod >", value, "orderreturnperiod");
            return (Criteria) this;
        }

        public Criteria andOrderreturnperiodGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("orderreturnperiod >=", value, "orderreturnperiod");
            return (Criteria) this;
        }

        public Criteria andOrderreturnperiodLessThan(BigDecimal value) {
            addCriterion("orderreturnperiod <", value, "orderreturnperiod");
            return (Criteria) this;
        }

        public Criteria andOrderreturnperiodLessThanOrEqualTo(BigDecimal value) {
            addCriterion("orderreturnperiod <=", value, "orderreturnperiod");
            return (Criteria) this;
        }

        public Criteria andOrderreturnperiodIn(List<BigDecimal> values) {
            addCriterion("orderreturnperiod in", values, "orderreturnperiod");
            return (Criteria) this;
        }

        public Criteria andOrderreturnperiodNotIn(List<BigDecimal> values) {
            addCriterion("orderreturnperiod not in", values, "orderreturnperiod");
            return (Criteria) this;
        }

        public Criteria andOrderreturnperiodBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("orderreturnperiod between", value1, value2, "orderreturnperiod");
            return (Criteria) this;
        }

        public Criteria andOrderreturnperiodNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("orderreturnperiod not between", value1, value2, "orderreturnperiod");
            return (Criteria) this;
        }

        public Criteria andSpotsalesmarginIsNull() {
            addCriterion("spotsalesmargin is null");
            return (Criteria) this;
        }

        public Criteria andSpotsalesmarginIsNotNull() {
            addCriterion("spotsalesmargin is not null");
            return (Criteria) this;
        }

        public Criteria andSpotsalesmarginEqualTo(BigDecimal value) {
            addCriterion("spotsalesmargin =", value, "spotsalesmargin");
            return (Criteria) this;
        }

        public Criteria andSpotsalesmarginNotEqualTo(BigDecimal value) {
            addCriterion("spotsalesmargin <>", value, "spotsalesmargin");
            return (Criteria) this;
        }

        public Criteria andSpotsalesmarginGreaterThan(BigDecimal value) {
            addCriterion("spotsalesmargin >", value, "spotsalesmargin");
            return (Criteria) this;
        }

        public Criteria andSpotsalesmarginGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("spotsalesmargin >=", value, "spotsalesmargin");
            return (Criteria) this;
        }

        public Criteria andSpotsalesmarginLessThan(BigDecimal value) {
            addCriterion("spotsalesmargin <", value, "spotsalesmargin");
            return (Criteria) this;
        }

        public Criteria andSpotsalesmarginLessThanOrEqualTo(BigDecimal value) {
            addCriterion("spotsalesmargin <=", value, "spotsalesmargin");
            return (Criteria) this;
        }

        public Criteria andSpotsalesmarginIn(List<BigDecimal> values) {
            addCriterion("spotsalesmargin in", values, "spotsalesmargin");
            return (Criteria) this;
        }

        public Criteria andSpotsalesmarginNotIn(List<BigDecimal> values) {
            addCriterion("spotsalesmargin not in", values, "spotsalesmargin");
            return (Criteria) this;
        }

        public Criteria andSpotsalesmarginBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("spotsalesmargin between", value1, value2, "spotsalesmargin");
            return (Criteria) this;
        }

        public Criteria andSpotsalesmarginNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("spotsalesmargin not between", value1, value2, "spotsalesmargin");
            return (Criteria) this;
        }

        public Criteria andForwardsalesmarginIsNull() {
            addCriterion("forwardsalesmargin is null");
            return (Criteria) this;
        }

        public Criteria andForwardsalesmarginIsNotNull() {
            addCriterion("forwardsalesmargin is not null");
            return (Criteria) this;
        }

        public Criteria andForwardsalesmarginEqualTo(BigDecimal value) {
            addCriterion("forwardsalesmargin =", value, "forwardsalesmargin");
            return (Criteria) this;
        }

        public Criteria andForwardsalesmarginNotEqualTo(BigDecimal value) {
            addCriterion("forwardsalesmargin <>", value, "forwardsalesmargin");
            return (Criteria) this;
        }

        public Criteria andForwardsalesmarginGreaterThan(BigDecimal value) {
            addCriterion("forwardsalesmargin >", value, "forwardsalesmargin");
            return (Criteria) this;
        }

        public Criteria andForwardsalesmarginGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("forwardsalesmargin >=", value, "forwardsalesmargin");
            return (Criteria) this;
        }

        public Criteria andForwardsalesmarginLessThan(BigDecimal value) {
            addCriterion("forwardsalesmargin <", value, "forwardsalesmargin");
            return (Criteria) this;
        }

        public Criteria andForwardsalesmarginLessThanOrEqualTo(BigDecimal value) {
            addCriterion("forwardsalesmargin <=", value, "forwardsalesmargin");
            return (Criteria) this;
        }

        public Criteria andForwardsalesmarginIn(List<BigDecimal> values) {
            addCriterion("forwardsalesmargin in", values, "forwardsalesmargin");
            return (Criteria) this;
        }

        public Criteria andForwardsalesmarginNotIn(List<BigDecimal> values) {
            addCriterion("forwardsalesmargin not in", values, "forwardsalesmargin");
            return (Criteria) this;
        }

        public Criteria andForwardsalesmarginBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("forwardsalesmargin between", value1, value2, "forwardsalesmargin");
            return (Criteria) this;
        }

        public Criteria andForwardsalesmarginNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("forwardsalesmargin not between", value1, value2, "forwardsalesmargin");
            return (Criteria) this;
        }

        public Criteria andGetliquidatedIsNull() {
            addCriterion("getliquidated is null");
            return (Criteria) this;
        }

        public Criteria andGetliquidatedIsNotNull() {
            addCriterion("getliquidated is not null");
            return (Criteria) this;
        }

        public Criteria andGetliquidatedEqualTo(BigDecimal value) {
            addCriterion("getliquidated =", value, "getliquidated");
            return (Criteria) this;
        }

        public Criteria andGetliquidatedNotEqualTo(BigDecimal value) {
            addCriterion("getliquidated <>", value, "getliquidated");
            return (Criteria) this;
        }

        public Criteria andGetliquidatedGreaterThan(BigDecimal value) {
            addCriterion("getliquidated >", value, "getliquidated");
            return (Criteria) this;
        }

        public Criteria andGetliquidatedGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("getliquidated >=", value, "getliquidated");
            return (Criteria) this;
        }

        public Criteria andGetliquidatedLessThan(BigDecimal value) {
            addCriterion("getliquidated <", value, "getliquidated");
            return (Criteria) this;
        }

        public Criteria andGetliquidatedLessThanOrEqualTo(BigDecimal value) {
            addCriterion("getliquidated <=", value, "getliquidated");
            return (Criteria) this;
        }

        public Criteria andGetliquidatedIn(List<BigDecimal> values) {
            addCriterion("getliquidated in", values, "getliquidated");
            return (Criteria) this;
        }

        public Criteria andGetliquidatedNotIn(List<BigDecimal> values) {
            addCriterion("getliquidated not in", values, "getliquidated");
            return (Criteria) this;
        }

        public Criteria andGetliquidatedBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("getliquidated between", value1, value2, "getliquidated");
            return (Criteria) this;
        }

        public Criteria andGetliquidatedNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("getliquidated not between", value1, value2, "getliquidated");
            return (Criteria) this;
        }

        public Criteria andRemotepurchasingmarginIsNull() {
            addCriterion("remotepurchasingmargin is null");
            return (Criteria) this;
        }

        public Criteria andRemotepurchasingmarginIsNotNull() {
            addCriterion("remotepurchasingmargin is not null");
            return (Criteria) this;
        }

        public Criteria andRemotepurchasingmarginEqualTo(BigDecimal value) {
            addCriterion("remotepurchasingmargin =", value, "remotepurchasingmargin");
            return (Criteria) this;
        }

        public Criteria andRemotepurchasingmarginNotEqualTo(BigDecimal value) {
            addCriterion("remotepurchasingmargin <>", value, "remotepurchasingmargin");
            return (Criteria) this;
        }

        public Criteria andRemotepurchasingmarginGreaterThan(BigDecimal value) {
            addCriterion("remotepurchasingmargin >", value, "remotepurchasingmargin");
            return (Criteria) this;
        }

        public Criteria andRemotepurchasingmarginGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("remotepurchasingmargin >=", value, "remotepurchasingmargin");
            return (Criteria) this;
        }

        public Criteria andRemotepurchasingmarginLessThan(BigDecimal value) {
            addCriterion("remotepurchasingmargin <", value, "remotepurchasingmargin");
            return (Criteria) this;
        }

        public Criteria andRemotepurchasingmarginLessThanOrEqualTo(BigDecimal value) {
            addCriterion("remotepurchasingmargin <=", value, "remotepurchasingmargin");
            return (Criteria) this;
        }

        public Criteria andRemotepurchasingmarginIn(List<BigDecimal> values) {
            addCriterion("remotepurchasingmargin in", values, "remotepurchasingmargin");
            return (Criteria) this;
        }

        public Criteria andRemotepurchasingmarginNotIn(List<BigDecimal> values) {
            addCriterion("remotepurchasingmargin not in", values, "remotepurchasingmargin");
            return (Criteria) this;
        }

        public Criteria andRemotepurchasingmarginBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("remotepurchasingmargin between", value1, value2, "remotepurchasingmargin");
            return (Criteria) this;
        }

        public Criteria andRemotepurchasingmarginNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("remotepurchasingmargin not between", value1, value2, "remotepurchasingmargin");
            return (Criteria) this;
        }

        public Criteria andUpperlimitamountIsNull() {
            addCriterion("upperlimitamount is null");
            return (Criteria) this;
        }

        public Criteria andUpperlimitamountIsNotNull() {
            addCriterion("upperlimitamount is not null");
            return (Criteria) this;
        }

        public Criteria andUpperlimitamountEqualTo(BigDecimal value) {
            addCriterion("upperlimitamount =", value, "upperlimitamount");
            return (Criteria) this;
        }

        public Criteria andUpperlimitamountNotEqualTo(BigDecimal value) {
            addCriterion("upperlimitamount <>", value, "upperlimitamount");
            return (Criteria) this;
        }

        public Criteria andUpperlimitamountGreaterThan(BigDecimal value) {
            addCriterion("upperlimitamount >", value, "upperlimitamount");
            return (Criteria) this;
        }

        public Criteria andUpperlimitamountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("upperlimitamount >=", value, "upperlimitamount");
            return (Criteria) this;
        }

        public Criteria andUpperlimitamountLessThan(BigDecimal value) {
            addCriterion("upperlimitamount <", value, "upperlimitamount");
            return (Criteria) this;
        }

        public Criteria andUpperlimitamountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("upperlimitamount <=", value, "upperlimitamount");
            return (Criteria) this;
        }

        public Criteria andUpperlimitamountIn(List<BigDecimal> values) {
            addCriterion("upperlimitamount in", values, "upperlimitamount");
            return (Criteria) this;
        }

        public Criteria andUpperlimitamountNotIn(List<BigDecimal> values) {
            addCriterion("upperlimitamount not in", values, "upperlimitamount");
            return (Criteria) this;
        }

        public Criteria andUpperlimitamountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("upperlimitamount between", value1, value2, "upperlimitamount");
            return (Criteria) this;
        }

        public Criteria andUpperlimitamountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("upperlimitamount not between", value1, value2, "upperlimitamount");
            return (Criteria) this;
        }

        public Criteria andDelivery1of10IsNull() {
            addCriterion("delivery1of10 is null");
            return (Criteria) this;
        }

        public Criteria andDelivery1of10IsNotNull() {
            addCriterion("delivery1of10 is not null");
            return (Criteria) this;
        }

        public Criteria andDelivery1of10EqualTo(BigDecimal value) {
            addCriterion("delivery1of10 =", value, "delivery1of10");
            return (Criteria) this;
        }

        public Criteria andDelivery1of10NotEqualTo(BigDecimal value) {
            addCriterion("delivery1of10 <>", value, "delivery1of10");
            return (Criteria) this;
        }

        public Criteria andDelivery1of10GreaterThan(BigDecimal value) {
            addCriterion("delivery1of10 >", value, "delivery1of10");
            return (Criteria) this;
        }

        public Criteria andDelivery1of10GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("delivery1of10 >=", value, "delivery1of10");
            return (Criteria) this;
        }

        public Criteria andDelivery1of10LessThan(BigDecimal value) {
            addCriterion("delivery1of10 <", value, "delivery1of10");
            return (Criteria) this;
        }

        public Criteria andDelivery1of10LessThanOrEqualTo(BigDecimal value) {
            addCriterion("delivery1of10 <=", value, "delivery1of10");
            return (Criteria) this;
        }

        public Criteria andDelivery1of10In(List<BigDecimal> values) {
            addCriterion("delivery1of10 in", values, "delivery1of10");
            return (Criteria) this;
        }

        public Criteria andDelivery1of10NotIn(List<BigDecimal> values) {
            addCriterion("delivery1of10 not in", values, "delivery1of10");
            return (Criteria) this;
        }

        public Criteria andDelivery1of10Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("delivery1of10 between", value1, value2, "delivery1of10");
            return (Criteria) this;
        }

        public Criteria andDelivery1of10NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("delivery1of10 not between", value1, value2, "delivery1of10");
            return (Criteria) this;
        }

        public Criteria andDelivery11of20IsNull() {
            addCriterion("delivery11of20 is null");
            return (Criteria) this;
        }

        public Criteria andDelivery11of20IsNotNull() {
            addCriterion("delivery11of20 is not null");
            return (Criteria) this;
        }

        public Criteria andDelivery11of20EqualTo(BigDecimal value) {
            addCriterion("delivery11of20 =", value, "delivery11of20");
            return (Criteria) this;
        }

        public Criteria andDelivery11of20NotEqualTo(BigDecimal value) {
            addCriterion("delivery11of20 <>", value, "delivery11of20");
            return (Criteria) this;
        }

        public Criteria andDelivery11of20GreaterThan(BigDecimal value) {
            addCriterion("delivery11of20 >", value, "delivery11of20");
            return (Criteria) this;
        }

        public Criteria andDelivery11of20GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("delivery11of20 >=", value, "delivery11of20");
            return (Criteria) this;
        }

        public Criteria andDelivery11of20LessThan(BigDecimal value) {
            addCriterion("delivery11of20 <", value, "delivery11of20");
            return (Criteria) this;
        }

        public Criteria andDelivery11of20LessThanOrEqualTo(BigDecimal value) {
            addCriterion("delivery11of20 <=", value, "delivery11of20");
            return (Criteria) this;
        }

        public Criteria andDelivery11of20In(List<BigDecimal> values) {
            addCriterion("delivery11of20 in", values, "delivery11of20");
            return (Criteria) this;
        }

        public Criteria andDelivery11of20NotIn(List<BigDecimal> values) {
            addCriterion("delivery11of20 not in", values, "delivery11of20");
            return (Criteria) this;
        }

        public Criteria andDelivery11of20Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("delivery11of20 between", value1, value2, "delivery11of20");
            return (Criteria) this;
        }

        public Criteria andDelivery11of20NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("delivery11of20 not between", value1, value2, "delivery11of20");
            return (Criteria) this;
        }

        public Criteria andDelivery21of30IsNull() {
            addCriterion("delivery21of30 is null");
            return (Criteria) this;
        }

        public Criteria andDelivery21of30IsNotNull() {
            addCriterion("delivery21of30 is not null");
            return (Criteria) this;
        }

        public Criteria andDelivery21of30EqualTo(BigDecimal value) {
            addCriterion("delivery21of30 =", value, "delivery21of30");
            return (Criteria) this;
        }

        public Criteria andDelivery21of30NotEqualTo(BigDecimal value) {
            addCriterion("delivery21of30 <>", value, "delivery21of30");
            return (Criteria) this;
        }

        public Criteria andDelivery21of30GreaterThan(BigDecimal value) {
            addCriterion("delivery21of30 >", value, "delivery21of30");
            return (Criteria) this;
        }

        public Criteria andDelivery21of30GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("delivery21of30 >=", value, "delivery21of30");
            return (Criteria) this;
        }

        public Criteria andDelivery21of30LessThan(BigDecimal value) {
            addCriterion("delivery21of30 <", value, "delivery21of30");
            return (Criteria) this;
        }

        public Criteria andDelivery21of30LessThanOrEqualTo(BigDecimal value) {
            addCriterion("delivery21of30 <=", value, "delivery21of30");
            return (Criteria) this;
        }

        public Criteria andDelivery21of30In(List<BigDecimal> values) {
            addCriterion("delivery21of30 in", values, "delivery21of30");
            return (Criteria) this;
        }

        public Criteria andDelivery21of30NotIn(List<BigDecimal> values) {
            addCriterion("delivery21of30 not in", values, "delivery21of30");
            return (Criteria) this;
        }

        public Criteria andDelivery21of30Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("delivery21of30 between", value1, value2, "delivery21of30");
            return (Criteria) this;
        }

        public Criteria andDelivery21of30NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("delivery21of30 not between", value1, value2, "delivery21of30");
            return (Criteria) this;
        }

        public Criteria andRemotedeliverydayIsNull() {
            addCriterion("remotedeliveryday is null");
            return (Criteria) this;
        }

        public Criteria andRemotedeliverydayIsNotNull() {
            addCriterion("remotedeliveryday is not null");
            return (Criteria) this;
        }

        public Criteria andRemotedeliverydayEqualTo(BigDecimal value) {
            addCriterion("remotedeliveryday =", value, "remotedeliveryday");
            return (Criteria) this;
        }

        public Criteria andRemotedeliverydayNotEqualTo(BigDecimal value) {
            addCriterion("remotedeliveryday <>", value, "remotedeliveryday");
            return (Criteria) this;
        }

        public Criteria andRemotedeliverydayGreaterThan(BigDecimal value) {
            addCriterion("remotedeliveryday >", value, "remotedeliveryday");
            return (Criteria) this;
        }

        public Criteria andRemotedeliverydayGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("remotedeliveryday >=", value, "remotedeliveryday");
            return (Criteria) this;
        }

        public Criteria andRemotedeliverydayLessThan(BigDecimal value) {
            addCriterion("remotedeliveryday <", value, "remotedeliveryday");
            return (Criteria) this;
        }

        public Criteria andRemotedeliverydayLessThanOrEqualTo(BigDecimal value) {
            addCriterion("remotedeliveryday <=", value, "remotedeliveryday");
            return (Criteria) this;
        }

        public Criteria andRemotedeliverydayIn(List<BigDecimal> values) {
            addCriterion("remotedeliveryday in", values, "remotedeliveryday");
            return (Criteria) this;
        }

        public Criteria andRemotedeliverydayNotIn(List<BigDecimal> values) {
            addCriterion("remotedeliveryday not in", values, "remotedeliveryday");
            return (Criteria) this;
        }

        public Criteria andRemotedeliverydayBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("remotedeliveryday between", value1, value2, "remotedeliveryday");
            return (Criteria) this;
        }

        public Criteria andRemotedeliverydayNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("remotedeliveryday not between", value1, value2, "remotedeliveryday");
            return (Criteria) this;
        }

        public Criteria andBuytimeaheadtimeIsNull() {
            addCriterion("buytimeaheadtime is null");
            return (Criteria) this;
        }

        public Criteria andBuytimeaheadtimeIsNotNull() {
            addCriterion("buytimeaheadtime is not null");
            return (Criteria) this;
        }

        public Criteria andBuytimeaheadtimeEqualTo(BigDecimal value) {
            addCriterion("buytimeaheadtime =", value, "buytimeaheadtime");
            return (Criteria) this;
        }

        public Criteria andBuytimeaheadtimeNotEqualTo(BigDecimal value) {
            addCriterion("buytimeaheadtime <>", value, "buytimeaheadtime");
            return (Criteria) this;
        }

        public Criteria andBuytimeaheadtimeGreaterThan(BigDecimal value) {
            addCriterion("buytimeaheadtime >", value, "buytimeaheadtime");
            return (Criteria) this;
        }

        public Criteria andBuytimeaheadtimeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("buytimeaheadtime >=", value, "buytimeaheadtime");
            return (Criteria) this;
        }

        public Criteria andBuytimeaheadtimeLessThan(BigDecimal value) {
            addCriterion("buytimeaheadtime <", value, "buytimeaheadtime");
            return (Criteria) this;
        }

        public Criteria andBuytimeaheadtimeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("buytimeaheadtime <=", value, "buytimeaheadtime");
            return (Criteria) this;
        }

        public Criteria andBuytimeaheadtimeIn(List<BigDecimal> values) {
            addCriterion("buytimeaheadtime in", values, "buytimeaheadtime");
            return (Criteria) this;
        }

        public Criteria andBuytimeaheadtimeNotIn(List<BigDecimal> values) {
            addCriterion("buytimeaheadtime not in", values, "buytimeaheadtime");
            return (Criteria) this;
        }

        public Criteria andBuytimeaheadtimeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("buytimeaheadtime between", value1, value2, "buytimeaheadtime");
            return (Criteria) this;
        }

        public Criteria andBuytimeaheadtimeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("buytimeaheadtime not between", value1, value2, "buytimeaheadtime");
            return (Criteria) this;
        }

        public Criteria andTimedoutofpaymentIsNull() {
            addCriterion("timedoutofpayment is null");
            return (Criteria) this;
        }

        public Criteria andTimedoutofpaymentIsNotNull() {
            addCriterion("timedoutofpayment is not null");
            return (Criteria) this;
        }

        public Criteria andTimedoutofpaymentEqualTo(BigDecimal value) {
            addCriterion("timedoutofpayment =", value, "timedoutofpayment");
            return (Criteria) this;
        }

        public Criteria andTimedoutofpaymentNotEqualTo(BigDecimal value) {
            addCriterion("timedoutofpayment <>", value, "timedoutofpayment");
            return (Criteria) this;
        }

        public Criteria andTimedoutofpaymentGreaterThan(BigDecimal value) {
            addCriterion("timedoutofpayment >", value, "timedoutofpayment");
            return (Criteria) this;
        }

        public Criteria andTimedoutofpaymentGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("timedoutofpayment >=", value, "timedoutofpayment");
            return (Criteria) this;
        }

        public Criteria andTimedoutofpaymentLessThan(BigDecimal value) {
            addCriterion("timedoutofpayment <", value, "timedoutofpayment");
            return (Criteria) this;
        }

        public Criteria andTimedoutofpaymentLessThanOrEqualTo(BigDecimal value) {
            addCriterion("timedoutofpayment <=", value, "timedoutofpayment");
            return (Criteria) this;
        }

        public Criteria andTimedoutofpaymentIn(List<BigDecimal> values) {
            addCriterion("timedoutofpayment in", values, "timedoutofpayment");
            return (Criteria) this;
        }

        public Criteria andTimedoutofpaymentNotIn(List<BigDecimal> values) {
            addCriterion("timedoutofpayment not in", values, "timedoutofpayment");
            return (Criteria) this;
        }

        public Criteria andTimedoutofpaymentBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("timedoutofpayment between", value1, value2, "timedoutofpayment");
            return (Criteria) this;
        }

        public Criteria andTimedoutofpaymentNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("timedoutofpayment not between", value1, value2, "timedoutofpayment");
            return (Criteria) this;
        }

        public Criteria andInspectionperiodIsNull() {
            addCriterion("inspectionperiod is null");
            return (Criteria) this;
        }

        public Criteria andInspectionperiodIsNotNull() {
            addCriterion("inspectionperiod is not null");
            return (Criteria) this;
        }

        public Criteria andInspectionperiodEqualTo(BigDecimal value) {
            addCriterion("inspectionperiod =", value, "inspectionperiod");
            return (Criteria) this;
        }

        public Criteria andInspectionperiodNotEqualTo(BigDecimal value) {
            addCriterion("inspectionperiod <>", value, "inspectionperiod");
            return (Criteria) this;
        }

        public Criteria andInspectionperiodGreaterThan(BigDecimal value) {
            addCriterion("inspectionperiod >", value, "inspectionperiod");
            return (Criteria) this;
        }

        public Criteria andInspectionperiodGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("inspectionperiod >=", value, "inspectionperiod");
            return (Criteria) this;
        }

        public Criteria andInspectionperiodLessThan(BigDecimal value) {
            addCriterion("inspectionperiod <", value, "inspectionperiod");
            return (Criteria) this;
        }

        public Criteria andInspectionperiodLessThanOrEqualTo(BigDecimal value) {
            addCriterion("inspectionperiod <=", value, "inspectionperiod");
            return (Criteria) this;
        }

        public Criteria andInspectionperiodIn(List<BigDecimal> values) {
            addCriterion("inspectionperiod in", values, "inspectionperiod");
            return (Criteria) this;
        }

        public Criteria andInspectionperiodNotIn(List<BigDecimal> values) {
            addCriterion("inspectionperiod not in", values, "inspectionperiod");
            return (Criteria) this;
        }

        public Criteria andInspectionperiodBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("inspectionperiod between", value1, value2, "inspectionperiod");
            return (Criteria) this;
        }

        public Criteria andInspectionperiodNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("inspectionperiod not between", value1, value2, "inspectionperiod");
            return (Criteria) this;
        }

        public Criteria andPeriodoffreezingIsNull() {
            addCriterion("periodoffreezing is null");
            return (Criteria) this;
        }

        public Criteria andPeriodoffreezingIsNotNull() {
            addCriterion("periodoffreezing is not null");
            return (Criteria) this;
        }

        public Criteria andPeriodoffreezingEqualTo(BigDecimal value) {
            addCriterion("periodoffreezing =", value, "periodoffreezing");
            return (Criteria) this;
        }

        public Criteria andPeriodoffreezingNotEqualTo(BigDecimal value) {
            addCriterion("periodoffreezing <>", value, "periodoffreezing");
            return (Criteria) this;
        }

        public Criteria andPeriodoffreezingGreaterThan(BigDecimal value) {
            addCriterion("periodoffreezing >", value, "periodoffreezing");
            return (Criteria) this;
        }

        public Criteria andPeriodoffreezingGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("periodoffreezing >=", value, "periodoffreezing");
            return (Criteria) this;
        }

        public Criteria andPeriodoffreezingLessThan(BigDecimal value) {
            addCriterion("periodoffreezing <", value, "periodoffreezing");
            return (Criteria) this;
        }

        public Criteria andPeriodoffreezingLessThanOrEqualTo(BigDecimal value) {
            addCriterion("periodoffreezing <=", value, "periodoffreezing");
            return (Criteria) this;
        }

        public Criteria andPeriodoffreezingIn(List<BigDecimal> values) {
            addCriterion("periodoffreezing in", values, "periodoffreezing");
            return (Criteria) this;
        }

        public Criteria andPeriodoffreezingNotIn(List<BigDecimal> values) {
            addCriterion("periodoffreezing not in", values, "periodoffreezing");
            return (Criteria) this;
        }

        public Criteria andPeriodoffreezingBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("periodoffreezing between", value1, value2, "periodoffreezing");
            return (Criteria) this;
        }

        public Criteria andPeriodoffreezingNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("periodoffreezing not between", value1, value2, "periodoffreezing");
            return (Criteria) this;
        }

        public Criteria andFreezetotogoodspayIsNull() {
            addCriterion("freezetotogoodspay is null");
            return (Criteria) this;
        }

        public Criteria andFreezetotogoodspayIsNotNull() {
            addCriterion("freezetotogoodspay is not null");
            return (Criteria) this;
        }

        public Criteria andFreezetotogoodspayEqualTo(BigDecimal value) {
            addCriterion("freezetotogoodspay =", value, "freezetotogoodspay");
            return (Criteria) this;
        }

        public Criteria andFreezetotogoodspayNotEqualTo(BigDecimal value) {
            addCriterion("freezetotogoodspay <>", value, "freezetotogoodspay");
            return (Criteria) this;
        }

        public Criteria andFreezetotogoodspayGreaterThan(BigDecimal value) {
            addCriterion("freezetotogoodspay >", value, "freezetotogoodspay");
            return (Criteria) this;
        }

        public Criteria andFreezetotogoodspayGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("freezetotogoodspay >=", value, "freezetotogoodspay");
            return (Criteria) this;
        }

        public Criteria andFreezetotogoodspayLessThan(BigDecimal value) {
            addCriterion("freezetotogoodspay <", value, "freezetotogoodspay");
            return (Criteria) this;
        }

        public Criteria andFreezetotogoodspayLessThanOrEqualTo(BigDecimal value) {
            addCriterion("freezetotogoodspay <=", value, "freezetotogoodspay");
            return (Criteria) this;
        }

        public Criteria andFreezetotogoodspayIn(List<BigDecimal> values) {
            addCriterion("freezetotogoodspay in", values, "freezetotogoodspay");
            return (Criteria) this;
        }

        public Criteria andFreezetotogoodspayNotIn(List<BigDecimal> values) {
            addCriterion("freezetotogoodspay not in", values, "freezetotogoodspay");
            return (Criteria) this;
        }

        public Criteria andFreezetotogoodspayBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freezetotogoodspay between", value1, value2, "freezetotogoodspay");
            return (Criteria) this;
        }

        public Criteria andFreezetotogoodspayNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freezetotogoodspay not between", value1, value2, "freezetotogoodspay");
            return (Criteria) this;
        }

        public Criteria andCreditbreakrateIsNull() {
            addCriterion("creditbreakrate is null");
            return (Criteria) this;
        }

        public Criteria andCreditbreakrateIsNotNull() {
            addCriterion("creditbreakrate is not null");
            return (Criteria) this;
        }

        public Criteria andCreditbreakrateEqualTo(BigDecimal value) {
            addCriterion("creditbreakrate =", value, "creditbreakrate");
            return (Criteria) this;
        }

        public Criteria andCreditbreakrateNotEqualTo(BigDecimal value) {
            addCriterion("creditbreakrate <>", value, "creditbreakrate");
            return (Criteria) this;
        }

        public Criteria andCreditbreakrateGreaterThan(BigDecimal value) {
            addCriterion("creditbreakrate >", value, "creditbreakrate");
            return (Criteria) this;
        }

        public Criteria andCreditbreakrateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("creditbreakrate >=", value, "creditbreakrate");
            return (Criteria) this;
        }

        public Criteria andCreditbreakrateLessThan(BigDecimal value) {
            addCriterion("creditbreakrate <", value, "creditbreakrate");
            return (Criteria) this;
        }

        public Criteria andCreditbreakrateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("creditbreakrate <=", value, "creditbreakrate");
            return (Criteria) this;
        }

        public Criteria andCreditbreakrateIn(List<BigDecimal> values) {
            addCriterion("creditbreakrate in", values, "creditbreakrate");
            return (Criteria) this;
        }

        public Criteria andCreditbreakrateNotIn(List<BigDecimal> values) {
            addCriterion("creditbreakrate not in", values, "creditbreakrate");
            return (Criteria) this;
        }

        public Criteria andCreditbreakrateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("creditbreakrate between", value1, value2, "creditbreakrate");
            return (Criteria) this;
        }

        public Criteria andCreditbreakrateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("creditbreakrate not between", value1, value2, "creditbreakrate");
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