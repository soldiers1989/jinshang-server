package project.jinshang.mod_cash.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SalerCapitalExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SalerCapitalExample() {
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

        public Criteria andTradenoIsNull() {
            addCriterion("tradeno is null");
            return (Criteria) this;
        }

        public Criteria andTradenoIsNotNull() {
            addCriterion("tradeno is not null");
            return (Criteria) this;
        }

        public Criteria andTradenoEqualTo(String value) {
            addCriterion("tradeno =", value, "tradeno");
            return (Criteria) this;
        }

        public Criteria andTradenoNotEqualTo(String value) {
            addCriterion("tradeno <>", value, "tradeno");
            return (Criteria) this;
        }

        public Criteria andTradenoGreaterThan(String value) {
            addCriterion("tradeno >", value, "tradeno");
            return (Criteria) this;
        }

        public Criteria andTradenoGreaterThanOrEqualTo(String value) {
            addCriterion("tradeno >=", value, "tradeno");
            return (Criteria) this;
        }

        public Criteria andTradenoLessThan(String value) {
            addCriterion("tradeno <", value, "tradeno");
            return (Criteria) this;
        }

        public Criteria andTradenoLessThanOrEqualTo(String value) {
            addCriterion("tradeno <=", value, "tradeno");
            return (Criteria) this;
        }

        public Criteria andTradenoLike(String value) {
            addCriterion("tradeno like", value, "tradeno");
            return (Criteria) this;
        }

        public Criteria andTradenoNotLike(String value) {
            addCriterion("tradeno not like", value, "tradeno");
            return (Criteria) this;
        }

        public Criteria andTradenoIn(List<String> values) {
            addCriterion("tradeno in", values, "tradeno");
            return (Criteria) this;
        }

        public Criteria andTradenoNotIn(List<String> values) {
            addCriterion("tradeno not in", values, "tradeno");
            return (Criteria) this;
        }

        public Criteria andTradenoBetween(String value1, String value2) {
            addCriterion("tradeno between", value1, value2, "tradeno");
            return (Criteria) this;
        }

        public Criteria andTradenoNotBetween(String value1, String value2) {
            addCriterion("tradeno not between", value1, value2, "tradeno");
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

        public Criteria andTradetimeIsNull() {
            addCriterion("tradetime is null");
            return (Criteria) this;
        }

        public Criteria andTradetimeIsNotNull() {
            addCriterion("tradetime is not null");
            return (Criteria) this;
        }

        public Criteria andTradetimeEqualTo(Date value) {
            addCriterion("tradetime =", value, "tradetime");
            return (Criteria) this;
        }

        public Criteria andTradetimeNotEqualTo(Date value) {
            addCriterion("tradetime <>", value, "tradetime");
            return (Criteria) this;
        }

        public Criteria andTradetimeGreaterThan(Date value) {
            addCriterion("tradetime >", value, "tradetime");
            return (Criteria) this;
        }

        public Criteria andTradetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("tradetime >=", value, "tradetime");
            return (Criteria) this;
        }

        public Criteria andTradetimeLessThan(Date value) {
            addCriterion("tradetime <", value, "tradetime");
            return (Criteria) this;
        }

        public Criteria andTradetimeLessThanOrEqualTo(Date value) {
            addCriterion("tradetime <=", value, "tradetime");
            return (Criteria) this;
        }

        public Criteria andTradetimeIn(List<Date> values) {
            addCriterion("tradetime in", values, "tradetime");
            return (Criteria) this;
        }

        public Criteria andTradetimeNotIn(List<Date> values) {
            addCriterion("tradetime not in", values, "tradetime");
            return (Criteria) this;
        }

        public Criteria andTradetimeBetween(Date value1, Date value2) {
            addCriterion("tradetime between", value1, value2, "tradetime");
            return (Criteria) this;
        }

        public Criteria andTradetimeNotBetween(Date value1, Date value2) {
            addCriterion("tradetime not between", value1, value2, "tradetime");
            return (Criteria) this;
        }

        public Criteria andOrdercapitalIsNull() {
            addCriterion("ordercapital is null");
            return (Criteria) this;
        }

        public Criteria andOrdercapitalIsNotNull() {
            addCriterion("ordercapital is not null");
            return (Criteria) this;
        }

        public Criteria andOrdercapitalEqualTo(BigDecimal value) {
            addCriterion("ordercapital =", value, "ordercapital");
            return (Criteria) this;
        }

        public Criteria andOrdercapitalNotEqualTo(BigDecimal value) {
            addCriterion("ordercapital <>", value, "ordercapital");
            return (Criteria) this;
        }

        public Criteria andOrdercapitalGreaterThan(BigDecimal value) {
            addCriterion("ordercapital >", value, "ordercapital");
            return (Criteria) this;
        }

        public Criteria andOrdercapitalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ordercapital >=", value, "ordercapital");
            return (Criteria) this;
        }

        public Criteria andOrdercapitalLessThan(BigDecimal value) {
            addCriterion("ordercapital <", value, "ordercapital");
            return (Criteria) this;
        }

        public Criteria andOrdercapitalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ordercapital <=", value, "ordercapital");
            return (Criteria) this;
        }

        public Criteria andOrdercapitalIn(List<BigDecimal> values) {
            addCriterion("ordercapital in", values, "ordercapital");
            return (Criteria) this;
        }

        public Criteria andOrdercapitalNotIn(List<BigDecimal> values) {
            addCriterion("ordercapital not in", values, "ordercapital");
            return (Criteria) this;
        }

        public Criteria andOrdercapitalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ordercapital between", value1, value2, "ordercapital");
            return (Criteria) this;
        }

        public Criteria andOrdercapitalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ordercapital not between", value1, value2, "ordercapital");
            return (Criteria) this;
        }

        public Criteria andBailIsNull() {
            addCriterion("bail is null");
            return (Criteria) this;
        }

        public Criteria andBailIsNotNull() {
            addCriterion("bail is not null");
            return (Criteria) this;
        }

        public Criteria andBailEqualTo(BigDecimal value) {
            addCriterion("bail =", value, "bail");
            return (Criteria) this;
        }

        public Criteria andBailNotEqualTo(BigDecimal value) {
            addCriterion("bail <>", value, "bail");
            return (Criteria) this;
        }

        public Criteria andBailGreaterThan(BigDecimal value) {
            addCriterion("bail >", value, "bail");
            return (Criteria) this;
        }

        public Criteria andBailGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("bail >=", value, "bail");
            return (Criteria) this;
        }

        public Criteria andBailLessThan(BigDecimal value) {
            addCriterion("bail <", value, "bail");
            return (Criteria) this;
        }

        public Criteria andBailLessThanOrEqualTo(BigDecimal value) {
            addCriterion("bail <=", value, "bail");
            return (Criteria) this;
        }

        public Criteria andBailIn(List<BigDecimal> values) {
            addCriterion("bail in", values, "bail");
            return (Criteria) this;
        }

        public Criteria andBailNotIn(List<BigDecimal> values) {
            addCriterion("bail not in", values, "bail");
            return (Criteria) this;
        }

        public Criteria andBailBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("bail between", value1, value2, "bail");
            return (Criteria) this;
        }

        public Criteria andBailNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("bail not between", value1, value2, "bail");
            return (Criteria) this;
        }

        public Criteria andPenaltyIsNull() {
            addCriterion("penalty is null");
            return (Criteria) this;
        }

        public Criteria andPenaltyIsNotNull() {
            addCriterion("penalty is not null");
            return (Criteria) this;
        }

        public Criteria andPenaltyEqualTo(BigDecimal value) {
            addCriterion("penalty =", value, "penalty");
            return (Criteria) this;
        }

        public Criteria andPenaltyNotEqualTo(BigDecimal value) {
            addCriterion("penalty <>", value, "penalty");
            return (Criteria) this;
        }

        public Criteria andPenaltyGreaterThan(BigDecimal value) {
            addCriterion("penalty >", value, "penalty");
            return (Criteria) this;
        }

        public Criteria andPenaltyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("penalty >=", value, "penalty");
            return (Criteria) this;
        }

        public Criteria andPenaltyLessThan(BigDecimal value) {
            addCriterion("penalty <", value, "penalty");
            return (Criteria) this;
        }

        public Criteria andPenaltyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("penalty <=", value, "penalty");
            return (Criteria) this;
        }

        public Criteria andPenaltyIn(List<BigDecimal> values) {
            addCriterion("penalty in", values, "penalty");
            return (Criteria) this;
        }

        public Criteria andPenaltyNotIn(List<BigDecimal> values) {
            addCriterion("penalty not in", values, "penalty");
            return (Criteria) this;
        }

        public Criteria andPenaltyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("penalty between", value1, value2, "penalty");
            return (Criteria) this;
        }

        public Criteria andPenaltyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("penalty not between", value1, value2, "penalty");
            return (Criteria) this;
        }

        public Criteria andRefundamountIsNull() {
            addCriterion("refundamount is null");
            return (Criteria) this;
        }

        public Criteria andRefundamountIsNotNull() {
            addCriterion("refundamount is not null");
            return (Criteria) this;
        }

        public Criteria andRefundamountEqualTo(BigDecimal value) {
            addCriterion("refundamount =", value, "refundamount");
            return (Criteria) this;
        }

        public Criteria andRefundamountNotEqualTo(BigDecimal value) {
            addCriterion("refundamount <>", value, "refundamount");
            return (Criteria) this;
        }

        public Criteria andRefundamountGreaterThan(BigDecimal value) {
            addCriterion("refundamount >", value, "refundamount");
            return (Criteria) this;
        }

        public Criteria andRefundamountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("refundamount >=", value, "refundamount");
            return (Criteria) this;
        }

        public Criteria andRefundamountLessThan(BigDecimal value) {
            addCriterion("refundamount <", value, "refundamount");
            return (Criteria) this;
        }

        public Criteria andRefundamountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("refundamount <=", value, "refundamount");
            return (Criteria) this;
        }

        public Criteria andRefundamountIn(List<BigDecimal> values) {
            addCriterion("refundamount in", values, "refundamount");
            return (Criteria) this;
        }

        public Criteria andRefundamountNotIn(List<BigDecimal> values) {
            addCriterion("refundamount not in", values, "refundamount");
            return (Criteria) this;
        }

        public Criteria andRefundamountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refundamount between", value1, value2, "refundamount");
            return (Criteria) this;
        }

        public Criteria andRefundamountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refundamount not between", value1, value2, "refundamount");
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

        public Criteria andCapitaltypeIsNull() {
            addCriterion("capitaltype is null");
            return (Criteria) this;
        }

        public Criteria andCapitaltypeIsNotNull() {
            addCriterion("capitaltype is not null");
            return (Criteria) this;
        }

        public Criteria andCapitaltypeEqualTo(Short value) {
            addCriterion("capitaltype =", value, "capitaltype");
            return (Criteria) this;
        }

        public Criteria andCapitaltypeNotEqualTo(Short value) {
            addCriterion("capitaltype <>", value, "capitaltype");
            return (Criteria) this;
        }

        public Criteria andCapitaltypeGreaterThan(Short value) {
            addCriterion("capitaltype >", value, "capitaltype");
            return (Criteria) this;
        }

        public Criteria andCapitaltypeGreaterThanOrEqualTo(Short value) {
            addCriterion("capitaltype >=", value, "capitaltype");
            return (Criteria) this;
        }

        public Criteria andCapitaltypeLessThan(Short value) {
            addCriterion("capitaltype <", value, "capitaltype");
            return (Criteria) this;
        }

        public Criteria andCapitaltypeLessThanOrEqualTo(Short value) {
            addCriterion("capitaltype <=", value, "capitaltype");
            return (Criteria) this;
        }

        public Criteria andCapitaltypeIn(List<Short> values) {
            addCriterion("capitaltype in", values, "capitaltype");
            return (Criteria) this;
        }

        public Criteria andCapitaltypeNotIn(List<Short> values) {
            addCriterion("capitaltype not in", values, "capitaltype");
            return (Criteria) this;
        }

        public Criteria andCapitaltypeBetween(Short value1, Short value2) {
            addCriterion("capitaltype between", value1, value2, "capitaltype");
            return (Criteria) this;
        }

        public Criteria andCapitaltypeNotBetween(Short value1, Short value2) {
            addCriterion("capitaltype not between", value1, value2, "capitaltype");
            return (Criteria) this;
        }

        public Criteria andRechargenumberIsNull() {
            addCriterion("rechargenumber is null");
            return (Criteria) this;
        }

        public Criteria andRechargenumberIsNotNull() {
            addCriterion("rechargenumber is not null");
            return (Criteria) this;
        }

        public Criteria andRechargenumberEqualTo(String value) {
            addCriterion("rechargenumber =", value, "rechargenumber");
            return (Criteria) this;
        }

        public Criteria andRechargenumberNotEqualTo(String value) {
            addCriterion("rechargenumber <>", value, "rechargenumber");
            return (Criteria) this;
        }

        public Criteria andRechargenumberGreaterThan(String value) {
            addCriterion("rechargenumber >", value, "rechargenumber");
            return (Criteria) this;
        }

        public Criteria andRechargenumberGreaterThanOrEqualTo(String value) {
            addCriterion("rechargenumber >=", value, "rechargenumber");
            return (Criteria) this;
        }

        public Criteria andRechargenumberLessThan(String value) {
            addCriterion("rechargenumber <", value, "rechargenumber");
            return (Criteria) this;
        }

        public Criteria andRechargenumberLessThanOrEqualTo(String value) {
            addCriterion("rechargenumber <=", value, "rechargenumber");
            return (Criteria) this;
        }

        public Criteria andRechargenumberLike(String value) {
            addCriterion("rechargenumber like", value, "rechargenumber");
            return (Criteria) this;
        }

        public Criteria andRechargenumberNotLike(String value) {
            addCriterion("rechargenumber not like", value, "rechargenumber");
            return (Criteria) this;
        }

        public Criteria andRechargenumberIn(List<String> values) {
            addCriterion("rechargenumber in", values, "rechargenumber");
            return (Criteria) this;
        }

        public Criteria andRechargenumberNotIn(List<String> values) {
            addCriterion("rechargenumber not in", values, "rechargenumber");
            return (Criteria) this;
        }

        public Criteria andRechargenumberBetween(String value1, String value2) {
            addCriterion("rechargenumber between", value1, value2, "rechargenumber");
            return (Criteria) this;
        }

        public Criteria andRechargenumberNotBetween(String value1, String value2) {
            addCriterion("rechargenumber not between", value1, value2, "rechargenumber");
            return (Criteria) this;
        }

        public Criteria andRechargeperformIsNull() {
            addCriterion("rechargeperform is null");
            return (Criteria) this;
        }

        public Criteria andRechargeperformIsNotNull() {
            addCriterion("rechargeperform is not null");
            return (Criteria) this;
        }

        public Criteria andRechargeperformEqualTo(Short value) {
            addCriterion("rechargeperform =", value, "rechargeperform");
            return (Criteria) this;
        }

        public Criteria andRechargeperformNotEqualTo(Short value) {
            addCriterion("rechargeperform <>", value, "rechargeperform");
            return (Criteria) this;
        }

        public Criteria andRechargeperformGreaterThan(Short value) {
            addCriterion("rechargeperform >", value, "rechargeperform");
            return (Criteria) this;
        }

        public Criteria andRechargeperformGreaterThanOrEqualTo(Short value) {
            addCriterion("rechargeperform >=", value, "rechargeperform");
            return (Criteria) this;
        }

        public Criteria andRechargeperformLessThan(Short value) {
            addCriterion("rechargeperform <", value, "rechargeperform");
            return (Criteria) this;
        }

        public Criteria andRechargeperformLessThanOrEqualTo(Short value) {
            addCriterion("rechargeperform <=", value, "rechargeperform");
            return (Criteria) this;
        }

        public Criteria andRechargeperformIn(List<Short> values) {
            addCriterion("rechargeperform in", values, "rechargeperform");
            return (Criteria) this;
        }

        public Criteria andRechargeperformNotIn(List<Short> values) {
            addCriterion("rechargeperform not in", values, "rechargeperform");
            return (Criteria) this;
        }

        public Criteria andRechargeperformBetween(Short value1, Short value2) {
            addCriterion("rechargeperform between", value1, value2, "rechargeperform");
            return (Criteria) this;
        }

        public Criteria andRechargeperformNotBetween(Short value1, Short value2) {
            addCriterion("rechargeperform not between", value1, value2, "rechargeperform");
            return (Criteria) this;
        }

        public Criteria andPresentationnumberIsNull() {
            addCriterion("presentationnumber is null");
            return (Criteria) this;
        }

        public Criteria andPresentationnumberIsNotNull() {
            addCriterion("presentationnumber is not null");
            return (Criteria) this;
        }

        public Criteria andPresentationnumberEqualTo(String value) {
            addCriterion("presentationnumber =", value, "presentationnumber");
            return (Criteria) this;
        }

        public Criteria andPresentationnumberNotEqualTo(String value) {
            addCriterion("presentationnumber <>", value, "presentationnumber");
            return (Criteria) this;
        }

        public Criteria andPresentationnumberGreaterThan(String value) {
            addCriterion("presentationnumber >", value, "presentationnumber");
            return (Criteria) this;
        }

        public Criteria andPresentationnumberGreaterThanOrEqualTo(String value) {
            addCriterion("presentationnumber >=", value, "presentationnumber");
            return (Criteria) this;
        }

        public Criteria andPresentationnumberLessThan(String value) {
            addCriterion("presentationnumber <", value, "presentationnumber");
            return (Criteria) this;
        }

        public Criteria andPresentationnumberLessThanOrEqualTo(String value) {
            addCriterion("presentationnumber <=", value, "presentationnumber");
            return (Criteria) this;
        }

        public Criteria andPresentationnumberLike(String value) {
            addCriterion("presentationnumber like", value, "presentationnumber");
            return (Criteria) this;
        }

        public Criteria andPresentationnumberNotLike(String value) {
            addCriterion("presentationnumber not like", value, "presentationnumber");
            return (Criteria) this;
        }

        public Criteria andPresentationnumberIn(List<String> values) {
            addCriterion("presentationnumber in", values, "presentationnumber");
            return (Criteria) this;
        }

        public Criteria andPresentationnumberNotIn(List<String> values) {
            addCriterion("presentationnumber not in", values, "presentationnumber");
            return (Criteria) this;
        }

        public Criteria andPresentationnumberBetween(String value1, String value2) {
            addCriterion("presentationnumber between", value1, value2, "presentationnumber");
            return (Criteria) this;
        }

        public Criteria andPresentationnumberNotBetween(String value1, String value2) {
            addCriterion("presentationnumber not between", value1, value2, "presentationnumber");
            return (Criteria) this;
        }

        public Criteria andRechargestateIsNull() {
            addCriterion("rechargestate is null");
            return (Criteria) this;
        }

        public Criteria andRechargestateIsNotNull() {
            addCriterion("rechargestate is not null");
            return (Criteria) this;
        }

        public Criteria andRechargestateEqualTo(Short value) {
            addCriterion("rechargestate =", value, "rechargestate");
            return (Criteria) this;
        }

        public Criteria andRechargestateNotEqualTo(Short value) {
            addCriterion("rechargestate <>", value, "rechargestate");
            return (Criteria) this;
        }

        public Criteria andRechargestateGreaterThan(Short value) {
            addCriterion("rechargestate >", value, "rechargestate");
            return (Criteria) this;
        }

        public Criteria andRechargestateGreaterThanOrEqualTo(Short value) {
            addCriterion("rechargestate >=", value, "rechargestate");
            return (Criteria) this;
        }

        public Criteria andRechargestateLessThan(Short value) {
            addCriterion("rechargestate <", value, "rechargestate");
            return (Criteria) this;
        }

        public Criteria andRechargestateLessThanOrEqualTo(Short value) {
            addCriterion("rechargestate <=", value, "rechargestate");
            return (Criteria) this;
        }

        public Criteria andRechargestateIn(List<Short> values) {
            addCriterion("rechargestate in", values, "rechargestate");
            return (Criteria) this;
        }

        public Criteria andRechargestateNotIn(List<Short> values) {
            addCriterion("rechargestate not in", values, "rechargestate");
            return (Criteria) this;
        }

        public Criteria andRechargestateBetween(Short value1, Short value2) {
            addCriterion("rechargestate between", value1, value2, "rechargestate");
            return (Criteria) this;
        }

        public Criteria andRechargestateNotBetween(Short value1, Short value2) {
            addCriterion("rechargestate not between", value1, value2, "rechargestate");
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

        public Criteria andOperationIsNull() {
            addCriterion("operation is null");
            return (Criteria) this;
        }

        public Criteria andOperationIsNotNull() {
            addCriterion("operation is not null");
            return (Criteria) this;
        }

        public Criteria andOperationEqualTo(String value) {
            addCriterion("operation =", value, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationNotEqualTo(String value) {
            addCriterion("operation <>", value, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationGreaterThan(String value) {
            addCriterion("operation >", value, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationGreaterThanOrEqualTo(String value) {
            addCriterion("operation >=", value, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationLessThan(String value) {
            addCriterion("operation <", value, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationLessThanOrEqualTo(String value) {
            addCriterion("operation <=", value, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationLike(String value) {
            addCriterion("operation like", value, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationNotLike(String value) {
            addCriterion("operation not like", value, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationIn(List<String> values) {
            addCriterion("operation in", values, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationNotIn(List<String> values) {
            addCriterion("operation not in", values, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationBetween(String value1, String value2) {
            addCriterion("operation between", value1, value2, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationNotBetween(String value1, String value2) {
            addCriterion("operation not between", value1, value2, "operation");
            return (Criteria) this;
        }

        public Criteria andVerifyIsNull() {
            addCriterion("verify is null");
            return (Criteria) this;
        }

        public Criteria andVerifyIsNotNull() {
            addCriterion("verify is not null");
            return (Criteria) this;
        }

        public Criteria andVerifyEqualTo(String value) {
            addCriterion("verify =", value, "verify");
            return (Criteria) this;
        }

        public Criteria andVerifyNotEqualTo(String value) {
            addCriterion("verify <>", value, "verify");
            return (Criteria) this;
        }

        public Criteria andVerifyGreaterThan(String value) {
            addCriterion("verify >", value, "verify");
            return (Criteria) this;
        }

        public Criteria andVerifyGreaterThanOrEqualTo(String value) {
            addCriterion("verify >=", value, "verify");
            return (Criteria) this;
        }

        public Criteria andVerifyLessThan(String value) {
            addCriterion("verify <", value, "verify");
            return (Criteria) this;
        }

        public Criteria andVerifyLessThanOrEqualTo(String value) {
            addCriterion("verify <=", value, "verify");
            return (Criteria) this;
        }

        public Criteria andVerifyLike(String value) {
            addCriterion("verify like", value, "verify");
            return (Criteria) this;
        }

        public Criteria andVerifyNotLike(String value) {
            addCriterion("verify not like", value, "verify");
            return (Criteria) this;
        }

        public Criteria andVerifyIn(List<String> values) {
            addCriterion("verify in", values, "verify");
            return (Criteria) this;
        }

        public Criteria andVerifyNotIn(List<String> values) {
            addCriterion("verify not in", values, "verify");
            return (Criteria) this;
        }

        public Criteria andVerifyBetween(String value1, String value2) {
            addCriterion("verify between", value1, value2, "verify");
            return (Criteria) this;
        }

        public Criteria andVerifyNotBetween(String value1, String value2) {
            addCriterion("verify not between", value1, value2, "verify");
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

        public Criteria andBanknamealiasIsNull() {
            addCriterion("banknamealias is null");
            return (Criteria) this;
        }

        public Criteria andBanknamealiasIsNotNull() {
            addCriterion("banknamealias is not null");
            return (Criteria) this;
        }

        public Criteria andBanknamealiasEqualTo(String value) {
            addCriterion("banknamealias =", value, "banknamealias");
            return (Criteria) this;
        }

        public Criteria andBanknamealiasNotEqualTo(String value) {
            addCriterion("banknamealias <>", value, "banknamealias");
            return (Criteria) this;
        }

        public Criteria andBanknamealiasGreaterThan(String value) {
            addCriterion("banknamealias >", value, "banknamealias");
            return (Criteria) this;
        }

        public Criteria andBanknamealiasGreaterThanOrEqualTo(String value) {
            addCriterion("banknamealias >=", value, "banknamealias");
            return (Criteria) this;
        }

        public Criteria andBanknamealiasLessThan(String value) {
            addCriterion("banknamealias <", value, "banknamealias");
            return (Criteria) this;
        }

        public Criteria andBanknamealiasLessThanOrEqualTo(String value) {
            addCriterion("banknamealias <=", value, "banknamealias");
            return (Criteria) this;
        }

        public Criteria andBanknamealiasLike(String value) {
            addCriterion("banknamealias like", value, "banknamealias");
            return (Criteria) this;
        }

        public Criteria andBanknamealiasNotLike(String value) {
            addCriterion("banknamealias not like", value, "banknamealias");
            return (Criteria) this;
        }

        public Criteria andBanknamealiasIn(List<String> values) {
            addCriterion("banknamealias in", values, "banknamealias");
            return (Criteria) this;
        }

        public Criteria andBanknamealiasNotIn(List<String> values) {
            addCriterion("banknamealias not in", values, "banknamealias");
            return (Criteria) this;
        }

        public Criteria andBanknamealiasBetween(String value1, String value2) {
            addCriterion("banknamealias between", value1, value2, "banknamealias");
            return (Criteria) this;
        }

        public Criteria andBanknamealiasNotBetween(String value1, String value2) {
            addCriterion("banknamealias not between", value1, value2, "banknamealias");
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

        public Criteria andWithdrawtypeIsNull() {
            addCriterion("withdrawtype is null");
            return (Criteria) this;
        }

        public Criteria andWithdrawtypeIsNotNull() {
            addCriterion("withdrawtype is not null");
            return (Criteria) this;
        }

        public Criteria andWithdrawtypeEqualTo(Short value) {
            addCriterion("withdrawtype =", value, "withdrawtype");
            return (Criteria) this;
        }

        public Criteria andWithdrawtypeNotEqualTo(Short value) {
            addCriterion("withdrawtype <>", value, "withdrawtype");
            return (Criteria) this;
        }

        public Criteria andWithdrawtypeGreaterThan(Short value) {
            addCriterion("withdrawtype >", value, "withdrawtype");
            return (Criteria) this;
        }

        public Criteria andWithdrawtypeGreaterThanOrEqualTo(Short value) {
            addCriterion("withdrawtype >=", value, "withdrawtype");
            return (Criteria) this;
        }

        public Criteria andWithdrawtypeLessThan(Short value) {
            addCriterion("withdrawtype <", value, "withdrawtype");
            return (Criteria) this;
        }

        public Criteria andWithdrawtypeLessThanOrEqualTo(Short value) {
            addCriterion("withdrawtype <=", value, "withdrawtype");
            return (Criteria) this;
        }

        public Criteria andWithdrawtypeIn(List<Short> values) {
            addCriterion("withdrawtype in", values, "withdrawtype");
            return (Criteria) this;
        }

        public Criteria andWithdrawtypeNotIn(List<Short> values) {
            addCriterion("withdrawtype not in", values, "withdrawtype");
            return (Criteria) this;
        }

        public Criteria andWithdrawtypeBetween(Short value1, Short value2) {
            addCriterion("withdrawtype between", value1, value2, "withdrawtype");
            return (Criteria) this;
        }

        public Criteria andWithdrawtypeNotBetween(Short value1, Short value2) {
            addCriterion("withdrawtype not between", value1, value2, "withdrawtype");
            return (Criteria) this;
        }

        public Criteria andAliasIsNull() {
            addCriterion("alias is null");
            return (Criteria) this;
        }

        public Criteria andAliasIsNotNull() {
            addCriterion("alias is not null");
            return (Criteria) this;
        }

        public Criteria andAliasEqualTo(String value) {
            addCriterion("alias =", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasNotEqualTo(String value) {
            addCriterion("alias <>", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasGreaterThan(String value) {
            addCriterion("alias >", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasGreaterThanOrEqualTo(String value) {
            addCriterion("alias >=", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasLessThan(String value) {
            addCriterion("alias <", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasLessThanOrEqualTo(String value) {
            addCriterion("alias <=", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasLike(String value) {
            addCriterion("alias like", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasNotLike(String value) {
            addCriterion("alias not like", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasIn(List<String> values) {
            addCriterion("alias in", values, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasNotIn(List<String> values) {
            addCriterion("alias not in", values, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasBetween(String value1, String value2) {
            addCriterion("alias between", value1, value2, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasNotBetween(String value1, String value2) {
            addCriterion("alias not between", value1, value2, "alias");
            return (Criteria) this;
        }

        public Criteria andSuccesstimeIsNull() {
            addCriterion("successtime is null");
            return (Criteria) this;
        }

        public Criteria andSuccesstimeIsNotNull() {
            addCriterion("successtime is not null");
            return (Criteria) this;
        }

        public Criteria andSuccesstimeEqualTo(Date value) {
            addCriterion("successtime =", value, "successtime");
            return (Criteria) this;
        }

        public Criteria andSuccesstimeNotEqualTo(Date value) {
            addCriterion("successtime <>", value, "successtime");
            return (Criteria) this;
        }

        public Criteria andSuccesstimeGreaterThan(Date value) {
            addCriterion("successtime >", value, "successtime");
            return (Criteria) this;
        }

        public Criteria andSuccesstimeGreaterThanOrEqualTo(Date value) {
            addCriterion("successtime >=", value, "successtime");
            return (Criteria) this;
        }

        public Criteria andSuccesstimeLessThan(Date value) {
            addCriterion("successtime <", value, "successtime");
            return (Criteria) this;
        }

        public Criteria andSuccesstimeLessThanOrEqualTo(Date value) {
            addCriterion("successtime <=", value, "successtime");
            return (Criteria) this;
        }

        public Criteria andSuccesstimeIn(List<Date> values) {
            addCriterion("successtime in", values, "successtime");
            return (Criteria) this;
        }

        public Criteria andSuccesstimeNotIn(List<Date> values) {
            addCriterion("successtime not in", values, "successtime");
            return (Criteria) this;
        }

        public Criteria andSuccesstimeBetween(Date value1, Date value2) {
            addCriterion("successtime between", value1, value2, "successtime");
            return (Criteria) this;
        }

        public Criteria andSuccesstimeNotBetween(Date value1, Date value2) {
            addCriterion("successtime not between", value1, value2, "successtime");
            return (Criteria) this;
        }

        public Criteria andBrokerageIsNull() {
            addCriterion("brokerage is null");
            return (Criteria) this;
        }

        public Criteria andBrokerageIsNotNull() {
            addCriterion("brokerage is not null");
            return (Criteria) this;
        }

        public Criteria andBrokerageEqualTo(BigDecimal value) {
            addCriterion("brokerage =", value, "brokerage");
            return (Criteria) this;
        }

        public Criteria andBrokerageNotEqualTo(BigDecimal value) {
            addCriterion("brokerage <>", value, "brokerage");
            return (Criteria) this;
        }

        public Criteria andBrokerageGreaterThan(BigDecimal value) {
            addCriterion("brokerage >", value, "brokerage");
            return (Criteria) this;
        }

        public Criteria andBrokerageGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("brokerage >=", value, "brokerage");
            return (Criteria) this;
        }

        public Criteria andBrokerageLessThan(BigDecimal value) {
            addCriterion("brokerage <", value, "brokerage");
            return (Criteria) this;
        }

        public Criteria andBrokerageLessThanOrEqualTo(BigDecimal value) {
            addCriterion("brokerage <=", value, "brokerage");
            return (Criteria) this;
        }

        public Criteria andBrokerageIn(List<BigDecimal> values) {
            addCriterion("brokerage in", values, "brokerage");
            return (Criteria) this;
        }

        public Criteria andBrokerageNotIn(List<BigDecimal> values) {
            addCriterion("brokerage not in", values, "brokerage");
            return (Criteria) this;
        }

        public Criteria andBrokerageBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("brokerage between", value1, value2, "brokerage");
            return (Criteria) this;
        }

        public Criteria andBrokerageNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("brokerage not between", value1, value2, "brokerage");
            return (Criteria) this;
        }

        public Criteria andSettlementIsNull() {
            addCriterion("settlement is null");
            return (Criteria) this;
        }

        public Criteria andSettlementIsNotNull() {
            addCriterion("settlement is not null");
            return (Criteria) this;
        }

        public Criteria andSettlementEqualTo(Short value) {
            addCriterion("settlement =", value, "settlement");
            return (Criteria) this;
        }

        public Criteria andSettlementNotEqualTo(Short value) {
            addCriterion("settlement <>", value, "settlement");
            return (Criteria) this;
        }

        public Criteria andSettlementGreaterThan(Short value) {
            addCriterion("settlement >", value, "settlement");
            return (Criteria) this;
        }

        public Criteria andSettlementGreaterThanOrEqualTo(Short value) {
            addCriterion("settlement >=", value, "settlement");
            return (Criteria) this;
        }

        public Criteria andSettlementLessThan(Short value) {
            addCriterion("settlement <", value, "settlement");
            return (Criteria) this;
        }

        public Criteria andSettlementLessThanOrEqualTo(Short value) {
            addCriterion("settlement <=", value, "settlement");
            return (Criteria) this;
        }

        public Criteria andSettlementIn(List<Short> values) {
            addCriterion("settlement in", values, "settlement");
            return (Criteria) this;
        }

        public Criteria andSettlementNotIn(List<Short> values) {
            addCriterion("settlement not in", values, "settlement");
            return (Criteria) this;
        }

        public Criteria andSettlementBetween(Short value1, Short value2) {
            addCriterion("settlement between", value1, value2, "settlement");
            return (Criteria) this;
        }

        public Criteria andSettlementNotBetween(Short value1, Short value2) {
            addCriterion("settlement not between", value1, value2, "settlement");
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

        public Criteria andMembermarkIsNull() {
            addCriterion("membermark is null");
            return (Criteria) this;
        }

        public Criteria andMembermarkIsNotNull() {
            addCriterion("membermark is not null");
            return (Criteria) this;
        }

        public Criteria andMembermarkEqualTo(String value) {
            addCriterion("membermark =", value, "membermark");
            return (Criteria) this;
        }

        public Criteria andMembermarkNotEqualTo(String value) {
            addCriterion("membermark <>", value, "membermark");
            return (Criteria) this;
        }

        public Criteria andMembermarkGreaterThan(String value) {
            addCriterion("membermark >", value, "membermark");
            return (Criteria) this;
        }

        public Criteria andMembermarkGreaterThanOrEqualTo(String value) {
            addCriterion("membermark >=", value, "membermark");
            return (Criteria) this;
        }

        public Criteria andMembermarkLessThan(String value) {
            addCriterion("membermark <", value, "membermark");
            return (Criteria) this;
        }

        public Criteria andMembermarkLessThanOrEqualTo(String value) {
            addCriterion("membermark <=", value, "membermark");
            return (Criteria) this;
        }

        public Criteria andMembermarkLike(String value) {
            addCriterion("membermark like", value, "membermark");
            return (Criteria) this;
        }

        public Criteria andMembermarkNotLike(String value) {
            addCriterion("membermark not like", value, "membermark");
            return (Criteria) this;
        }

        public Criteria andMembermarkIn(List<String> values) {
            addCriterion("membermark in", values, "membermark");
            return (Criteria) this;
        }

        public Criteria andMembermarkNotIn(List<String> values) {
            addCriterion("membermark not in", values, "membermark");
            return (Criteria) this;
        }

        public Criteria andMembermarkBetween(String value1, String value2) {
            addCriterion("membermark between", value1, value2, "membermark");
            return (Criteria) this;
        }

        public Criteria andMembermarkNotBetween(String value1, String value2) {
            addCriterion("membermark not between", value1, value2, "membermark");
            return (Criteria) this;
        }

        public Criteria andValidatemarkIsNull() {
            addCriterion("validatemark is null");
            return (Criteria) this;
        }

        public Criteria andValidatemarkIsNotNull() {
            addCriterion("validatemark is not null");
            return (Criteria) this;
        }

        public Criteria andValidatemarkEqualTo(String value) {
            addCriterion("validatemark =", value, "validatemark");
            return (Criteria) this;
        }

        public Criteria andValidatemarkNotEqualTo(String value) {
            addCriterion("validatemark <>", value, "validatemark");
            return (Criteria) this;
        }

        public Criteria andValidatemarkGreaterThan(String value) {
            addCriterion("validatemark >", value, "validatemark");
            return (Criteria) this;
        }

        public Criteria andValidatemarkGreaterThanOrEqualTo(String value) {
            addCriterion("validatemark >=", value, "validatemark");
            return (Criteria) this;
        }

        public Criteria andValidatemarkLessThan(String value) {
            addCriterion("validatemark <", value, "validatemark");
            return (Criteria) this;
        }

        public Criteria andValidatemarkLessThanOrEqualTo(String value) {
            addCriterion("validatemark <=", value, "validatemark");
            return (Criteria) this;
        }

        public Criteria andValidatemarkLike(String value) {
            addCriterion("validatemark like", value, "validatemark");
            return (Criteria) this;
        }

        public Criteria andValidatemarkNotLike(String value) {
            addCriterion("validatemark not like", value, "validatemark");
            return (Criteria) this;
        }

        public Criteria andValidatemarkIn(List<String> values) {
            addCriterion("validatemark in", values, "validatemark");
            return (Criteria) this;
        }

        public Criteria andValidatemarkNotIn(List<String> values) {
            addCriterion("validatemark not in", values, "validatemark");
            return (Criteria) this;
        }

        public Criteria andValidatemarkBetween(String value1, String value2) {
            addCriterion("validatemark between", value1, value2, "validatemark");
            return (Criteria) this;
        }

        public Criteria andValidatemarkNotBetween(String value1, String value2) {
            addCriterion("validatemark not between", value1, value2, "validatemark");
            return (Criteria) this;
        }

        public Criteria andOperatetimeIsNull() {
            addCriterion("operatetime is null");
            return (Criteria) this;
        }

        public Criteria andOperatetimeIsNotNull() {
            addCriterion("operatetime is not null");
            return (Criteria) this;
        }

        public Criteria andOperatetimeEqualTo(Date value) {
            addCriterion("operatetime =", value, "operatetime");
            return (Criteria) this;
        }

        public Criteria andOperatetimeNotEqualTo(Date value) {
            addCriterion("operatetime <>", value, "operatetime");
            return (Criteria) this;
        }

        public Criteria andOperatetimeGreaterThan(Date value) {
            addCriterion("operatetime >", value, "operatetime");
            return (Criteria) this;
        }

        public Criteria andOperatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("operatetime >=", value, "operatetime");
            return (Criteria) this;
        }

        public Criteria andOperatetimeLessThan(Date value) {
            addCriterion("operatetime <", value, "operatetime");
            return (Criteria) this;
        }

        public Criteria andOperatetimeLessThanOrEqualTo(Date value) {
            addCriterion("operatetime <=", value, "operatetime");
            return (Criteria) this;
        }

        public Criteria andOperatetimeIn(List<Date> values) {
            addCriterion("operatetime in", values, "operatetime");
            return (Criteria) this;
        }

        public Criteria andOperatetimeNotIn(List<Date> values) {
            addCriterion("operatetime not in", values, "operatetime");
            return (Criteria) this;
        }

        public Criteria andOperatetimeBetween(Date value1, Date value2) {
            addCriterion("operatetime between", value1, value2, "operatetime");
            return (Criteria) this;
        }

        public Criteria andOperatetimeNotBetween(Date value1, Date value2) {
            addCriterion("operatetime not between", value1, value2, "operatetime");
            return (Criteria) this;
        }

        public Criteria andTransactionidIsNull() {
            addCriterion("transactionid is null");
            return (Criteria) this;
        }

        public Criteria andTransactionidIsNotNull() {
            addCriterion("transactionid is not null");
            return (Criteria) this;
        }

        public Criteria andTransactionidEqualTo(String value) {
            addCriterion("transactionid =", value, "transactionid");
            return (Criteria) this;
        }

        public Criteria andTransactionidNotEqualTo(String value) {
            addCriterion("transactionid <>", value, "transactionid");
            return (Criteria) this;
        }

        public Criteria andTransactionidGreaterThan(String value) {
            addCriterion("transactionid >", value, "transactionid");
            return (Criteria) this;
        }

        public Criteria andTransactionidGreaterThanOrEqualTo(String value) {
            addCriterion("transactionid >=", value, "transactionid");
            return (Criteria) this;
        }

        public Criteria andTransactionidLessThan(String value) {
            addCriterion("transactionid <", value, "transactionid");
            return (Criteria) this;
        }

        public Criteria andTransactionidLessThanOrEqualTo(String value) {
            addCriterion("transactionid <=", value, "transactionid");
            return (Criteria) this;
        }

        public Criteria andTransactionidLike(String value) {
            addCriterion("transactionid like", value, "transactionid");
            return (Criteria) this;
        }

        public Criteria andTransactionidNotLike(String value) {
            addCriterion("transactionid not like", value, "transactionid");
            return (Criteria) this;
        }

        public Criteria andTransactionidIn(List<String> values) {
            addCriterion("transactionid in", values, "transactionid");
            return (Criteria) this;
        }

        public Criteria andTransactionidNotIn(List<String> values) {
            addCriterion("transactionid not in", values, "transactionid");
            return (Criteria) this;
        }

        public Criteria andTransactionidBetween(String value1, String value2) {
            addCriterion("transactionid between", value1, value2, "transactionid");
            return (Criteria) this;
        }

        public Criteria andTransactionidNotBetween(String value1, String value2) {
            addCriterion("transactionid not between", value1, value2, "transactionid");
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