package project.jinshang.mod_fx.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FxcommisionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FxcommisionExample() {
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

    /**
     * null
     * 
     * @author wcyong
     * 
     * @date 2018-05-23
     */
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

        public Criteria andOrderidIsNull() {
            addCriterion("orderid is null");
            return (Criteria) this;
        }

        public Criteria andOrderidIsNotNull() {
            addCriterion("orderid is not null");
            return (Criteria) this;
        }

        public Criteria andOrderidEqualTo(Long value) {
            addCriterion("orderid =", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotEqualTo(Long value) {
            addCriterion("orderid <>", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidGreaterThan(Long value) {
            addCriterion("orderid >", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidGreaterThanOrEqualTo(Long value) {
            addCriterion("orderid >=", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidLessThan(Long value) {
            addCriterion("orderid <", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidLessThanOrEqualTo(Long value) {
            addCriterion("orderid <=", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidIn(List<Long> values) {
            addCriterion("orderid in", values, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotIn(List<Long> values) {
            addCriterion("orderid not in", values, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidBetween(Long value1, Long value2) {
            addCriterion("orderid between", value1, value2, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotBetween(Long value1, Long value2) {
            addCriterion("orderid not between", value1, value2, "orderid");
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

        public Criteria andSaleidIsNull() {
            addCriterion("saleid is null");
            return (Criteria) this;
        }

        public Criteria andSaleidIsNotNull() {
            addCriterion("saleid is not null");
            return (Criteria) this;
        }

        public Criteria andSaleidEqualTo(Long value) {
            addCriterion("saleid =", value, "saleid");
            return (Criteria) this;
        }

        public Criteria andSaleidNotEqualTo(Long value) {
            addCriterion("saleid <>", value, "saleid");
            return (Criteria) this;
        }

        public Criteria andSaleidGreaterThan(Long value) {
            addCriterion("saleid >", value, "saleid");
            return (Criteria) this;
        }

        public Criteria andSaleidGreaterThanOrEqualTo(Long value) {
            addCriterion("saleid >=", value, "saleid");
            return (Criteria) this;
        }

        public Criteria andSaleidLessThan(Long value) {
            addCriterion("saleid <", value, "saleid");
            return (Criteria) this;
        }

        public Criteria andSaleidLessThanOrEqualTo(Long value) {
            addCriterion("saleid <=", value, "saleid");
            return (Criteria) this;
        }

        public Criteria andSaleidIn(List<Long> values) {
            addCriterion("saleid in", values, "saleid");
            return (Criteria) this;
        }

        public Criteria andSaleidNotIn(List<Long> values) {
            addCriterion("saleid not in", values, "saleid");
            return (Criteria) this;
        }

        public Criteria andSaleidBetween(Long value1, Long value2) {
            addCriterion("saleid between", value1, value2, "saleid");
            return (Criteria) this;
        }

        public Criteria andSaleidNotBetween(Long value1, Long value2) {
            addCriterion("saleid not between", value1, value2, "saleid");
            return (Criteria) this;
        }

        public Criteria andCmemberidIsNull() {
            addCriterion("cmemberid is null");
            return (Criteria) this;
        }

        public Criteria andCmemberidIsNotNull() {
            addCriterion("cmemberid is not null");
            return (Criteria) this;
        }

        public Criteria andCmemberidEqualTo(Long value) {
            addCriterion("cmemberid =", value, "cmemberid");
            return (Criteria) this;
        }

        public Criteria andCmemberidNotEqualTo(Long value) {
            addCriterion("cmemberid <>", value, "cmemberid");
            return (Criteria) this;
        }

        public Criteria andCmemberidGreaterThan(Long value) {
            addCriterion("cmemberid >", value, "cmemberid");
            return (Criteria) this;
        }

        public Criteria andCmemberidGreaterThanOrEqualTo(Long value) {
            addCriterion("cmemberid >=", value, "cmemberid");
            return (Criteria) this;
        }

        public Criteria andCmemberidLessThan(Long value) {
            addCriterion("cmemberid <", value, "cmemberid");
            return (Criteria) this;
        }

        public Criteria andCmemberidLessThanOrEqualTo(Long value) {
            addCriterion("cmemberid <=", value, "cmemberid");
            return (Criteria) this;
        }

        public Criteria andCmemberidIn(List<Long> values) {
            addCriterion("cmemberid in", values, "cmemberid");
            return (Criteria) this;
        }

        public Criteria andCmemberidNotIn(List<Long> values) {
            addCriterion("cmemberid not in", values, "cmemberid");
            return (Criteria) this;
        }

        public Criteria andCmemberidBetween(Long value1, Long value2) {
            addCriterion("cmemberid between", value1, value2, "cmemberid");
            return (Criteria) this;
        }

        public Criteria andCmemberidNotBetween(Long value1, Long value2) {
            addCriterion("cmemberid not between", value1, value2, "cmemberid");
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

        public Criteria andTypeEqualTo(Long value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Long value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Long value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Long value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Long value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Long value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Long> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Long> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Long value1, Long value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Long value1, Long value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andOrdercreatetimeIsNull() {
            addCriterion("ordercreatetime is null");
            return (Criteria) this;
        }

        public Criteria andOrdercreatetimeIsNotNull() {
            addCriterion("ordercreatetime is not null");
            return (Criteria) this;
        }

        public Criteria andOrdercreatetimeEqualTo(Date value) {
            addCriterion("ordercreatetime =", value, "ordercreatetime");
            return (Criteria) this;
        }

        public Criteria andOrdercreatetimeNotEqualTo(Date value) {
            addCriterion("ordercreatetime <>", value, "ordercreatetime");
            return (Criteria) this;
        }

        public Criteria andOrdercreatetimeGreaterThan(Date value) {
            addCriterion("ordercreatetime >", value, "ordercreatetime");
            return (Criteria) this;
        }

        public Criteria andOrdercreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("ordercreatetime >=", value, "ordercreatetime");
            return (Criteria) this;
        }

        public Criteria andOrdercreatetimeLessThan(Date value) {
            addCriterion("ordercreatetime <", value, "ordercreatetime");
            return (Criteria) this;
        }

        public Criteria andOrdercreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("ordercreatetime <=", value, "ordercreatetime");
            return (Criteria) this;
        }

        public Criteria andOrdercreatetimeIn(List<Date> values) {
            addCriterion("ordercreatetime in", values, "ordercreatetime");
            return (Criteria) this;
        }

        public Criteria andOrdercreatetimeNotIn(List<Date> values) {
            addCriterion("ordercreatetime not in", values, "ordercreatetime");
            return (Criteria) this;
        }

        public Criteria andOrdercreatetimeBetween(Date value1, Date value2) {
            addCriterion("ordercreatetime between", value1, value2, "ordercreatetime");
            return (Criteria) this;
        }

        public Criteria andOrdercreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("ordercreatetime not between", value1, value2, "ordercreatetime");
            return (Criteria) this;
        }

        public Criteria andBuyerinspectiontimeIsNull() {
            addCriterion("buyerinspectiontime is null");
            return (Criteria) this;
        }

        public Criteria andBuyerinspectiontimeIsNotNull() {
            addCriterion("buyerinspectiontime is not null");
            return (Criteria) this;
        }

        public Criteria andBuyerinspectiontimeEqualTo(Date value) {
            addCriterion("buyerinspectiontime =", value, "buyerinspectiontime");
            return (Criteria) this;
        }

        public Criteria andBuyerinspectiontimeNotEqualTo(Date value) {
            addCriterion("buyerinspectiontime <>", value, "buyerinspectiontime");
            return (Criteria) this;
        }

        public Criteria andBuyerinspectiontimeGreaterThan(Date value) {
            addCriterion("buyerinspectiontime >", value, "buyerinspectiontime");
            return (Criteria) this;
        }

        public Criteria andBuyerinspectiontimeGreaterThanOrEqualTo(Date value) {
            addCriterion("buyerinspectiontime >=", value, "buyerinspectiontime");
            return (Criteria) this;
        }

        public Criteria andBuyerinspectiontimeLessThan(Date value) {
            addCriterion("buyerinspectiontime <", value, "buyerinspectiontime");
            return (Criteria) this;
        }

        public Criteria andBuyerinspectiontimeLessThanOrEqualTo(Date value) {
            addCriterion("buyerinspectiontime <=", value, "buyerinspectiontime");
            return (Criteria) this;
        }

        public Criteria andBuyerinspectiontimeIn(List<Date> values) {
            addCriterion("buyerinspectiontime in", values, "buyerinspectiontime");
            return (Criteria) this;
        }

        public Criteria andBuyerinspectiontimeNotIn(List<Date> values) {
            addCriterion("buyerinspectiontime not in", values, "buyerinspectiontime");
            return (Criteria) this;
        }

        public Criteria andBuyerinspectiontimeBetween(Date value1, Date value2) {
            addCriterion("buyerinspectiontime between", value1, value2, "buyerinspectiontime");
            return (Criteria) this;
        }

        public Criteria andBuyerinspectiontimeNotBetween(Date value1, Date value2) {
            addCriterion("buyerinspectiontime not between", value1, value2, "buyerinspectiontime");
            return (Criteria) this;
        }

        public Criteria andTotalpriceIsNull() {
            addCriterion("totalprice is null");
            return (Criteria) this;
        }

        public Criteria andTotalpriceIsNotNull() {
            addCriterion("totalprice is not null");
            return (Criteria) this;
        }

        public Criteria andTotalpriceEqualTo(BigDecimal value) {
            addCriterion("totalprice =", value, "totalprice");
            return (Criteria) this;
        }

        public Criteria andTotalpriceNotEqualTo(BigDecimal value) {
            addCriterion("totalprice <>", value, "totalprice");
            return (Criteria) this;
        }

        public Criteria andTotalpriceGreaterThan(BigDecimal value) {
            addCriterion("totalprice >", value, "totalprice");
            return (Criteria) this;
        }

        public Criteria andTotalpriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("totalprice >=", value, "totalprice");
            return (Criteria) this;
        }

        public Criteria andTotalpriceLessThan(BigDecimal value) {
            addCriterion("totalprice <", value, "totalprice");
            return (Criteria) this;
        }

        public Criteria andTotalpriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("totalprice <=", value, "totalprice");
            return (Criteria) this;
        }

        public Criteria andTotalpriceIn(List<BigDecimal> values) {
            addCriterion("totalprice in", values, "totalprice");
            return (Criteria) this;
        }

        public Criteria andTotalpriceNotIn(List<BigDecimal> values) {
            addCriterion("totalprice not in", values, "totalprice");
            return (Criteria) this;
        }

        public Criteria andTotalpriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("totalprice between", value1, value2, "totalprice");
            return (Criteria) this;
        }

        public Criteria andTotalpriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("totalprice not between", value1, value2, "totalprice");
            return (Criteria) this;
        }

        public Criteria andCommisionpriceIsNull() {
            addCriterion("commisionprice is null");
            return (Criteria) this;
        }

        public Criteria andCommisionpriceIsNotNull() {
            addCriterion("commisionprice is not null");
            return (Criteria) this;
        }

        public Criteria andCommisionpriceEqualTo(BigDecimal value) {
            addCriterion("commisionprice =", value, "commisionprice");
            return (Criteria) this;
        }

        public Criteria andCommisionpriceNotEqualTo(BigDecimal value) {
            addCriterion("commisionprice <>", value, "commisionprice");
            return (Criteria) this;
        }

        public Criteria andCommisionpriceGreaterThan(BigDecimal value) {
            addCriterion("commisionprice >", value, "commisionprice");
            return (Criteria) this;
        }

        public Criteria andCommisionpriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("commisionprice >=", value, "commisionprice");
            return (Criteria) this;
        }

        public Criteria andCommisionpriceLessThan(BigDecimal value) {
            addCriterion("commisionprice <", value, "commisionprice");
            return (Criteria) this;
        }

        public Criteria andCommisionpriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("commisionprice <=", value, "commisionprice");
            return (Criteria) this;
        }

        public Criteria andCommisionpriceIn(List<BigDecimal> values) {
            addCriterion("commisionprice in", values, "commisionprice");
            return (Criteria) this;
        }

        public Criteria andCommisionpriceNotIn(List<BigDecimal> values) {
            addCriterion("commisionprice not in", values, "commisionprice");
            return (Criteria) this;
        }

        public Criteria andCommisionpriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("commisionprice between", value1, value2, "commisionprice");
            return (Criteria) this;
        }

        public Criteria andCommisionpriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("commisionprice not between", value1, value2, "commisionprice");
            return (Criteria) this;
        }

        public Criteria andProgressnumIsNull() {
            addCriterion("progressnum is null");
            return (Criteria) this;
        }

        public Criteria andProgressnumIsNotNull() {
            addCriterion("progressnum is not null");
            return (Criteria) this;
        }

        public Criteria andProgressnumEqualTo(Long value) {
            addCriterion("progressnum =", value, "progressnum");
            return (Criteria) this;
        }

        public Criteria andProgressnumNotEqualTo(Long value) {
            addCriterion("progressnum <>", value, "progressnum");
            return (Criteria) this;
        }

        public Criteria andProgressnumGreaterThan(Long value) {
            addCriterion("progressnum >", value, "progressnum");
            return (Criteria) this;
        }

        public Criteria andProgressnumGreaterThanOrEqualTo(Long value) {
            addCriterion("progressnum >=", value, "progressnum");
            return (Criteria) this;
        }

        public Criteria andProgressnumLessThan(Long value) {
            addCriterion("progressnum <", value, "progressnum");
            return (Criteria) this;
        }

        public Criteria andProgressnumLessThanOrEqualTo(Long value) {
            addCriterion("progressnum <=", value, "progressnum");
            return (Criteria) this;
        }

        public Criteria andProgressnumIn(List<Long> values) {
            addCriterion("progressnum in", values, "progressnum");
            return (Criteria) this;
        }

        public Criteria andProgressnumNotIn(List<Long> values) {
            addCriterion("progressnum not in", values, "progressnum");
            return (Criteria) this;
        }

        public Criteria andProgressnumBetween(Long value1, Long value2) {
            addCriterion("progressnum between", value1, value2, "progressnum");
            return (Criteria) this;
        }

        public Criteria andProgressnumNotBetween(Long value1, Long value2) {
            addCriterion("progressnum not between", value1, value2, "progressnum");
            return (Criteria) this;
        }

        public Criteria andProductslistIsNull() {
            addCriterion("productslist is null");
            return (Criteria) this;
        }

        public Criteria andProductslistIsNotNull() {
            addCriterion("productslist is not null");
            return (Criteria) this;
        }

        public Criteria andProductslistEqualTo(String value) {
            addCriterion("productslist =", value, "productslist");
            return (Criteria) this;
        }

        public Criteria andProductslistNotEqualTo(String value) {
            addCriterion("productslist <>", value, "productslist");
            return (Criteria) this;
        }

        public Criteria andProductslistGreaterThan(String value) {
            addCriterion("productslist >", value, "productslist");
            return (Criteria) this;
        }

        public Criteria andProductslistGreaterThanOrEqualTo(String value) {
            addCriterion("productslist >=", value, "productslist");
            return (Criteria) this;
        }

        public Criteria andProductslistLessThan(String value) {
            addCriterion("productslist <", value, "productslist");
            return (Criteria) this;
        }

        public Criteria andProductslistLessThanOrEqualTo(String value) {
            addCriterion("productslist <=", value, "productslist");
            return (Criteria) this;
        }

        public Criteria andProductslistLike(String value) {
            addCriterion("productslist like", value, "productslist");
            return (Criteria) this;
        }

        public Criteria andProductslistNotLike(String value) {
            addCriterion("productslist not like", value, "productslist");
            return (Criteria) this;
        }

        public Criteria andProductslistIn(List<String> values) {
            addCriterion("productslist in", values, "productslist");
            return (Criteria) this;
        }

        public Criteria andProductslistNotIn(List<String> values) {
            addCriterion("productslist not in", values, "productslist");
            return (Criteria) this;
        }

        public Criteria andProductslistBetween(String value1, String value2) {
            addCriterion("productslist between", value1, value2, "productslist");
            return (Criteria) this;
        }

        public Criteria andProductslistNotBetween(String value1, String value2) {
            addCriterion("productslist not between", value1, value2, "productslist");
            return (Criteria) this;
        }

        public Criteria andOtherIsNull() {
            addCriterion("other is null");
            return (Criteria) this;
        }

        public Criteria andOtherIsNotNull() {
            addCriterion("other is not null");
            return (Criteria) this;
        }

        public Criteria andOtherEqualTo(String value) {
            addCriterion("other =", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherNotEqualTo(String value) {
            addCriterion("other <>", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherGreaterThan(String value) {
            addCriterion("other >", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherGreaterThanOrEqualTo(String value) {
            addCriterion("other >=", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherLessThan(String value) {
            addCriterion("other <", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherLessThanOrEqualTo(String value) {
            addCriterion("other <=", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherLike(String value) {
            addCriterion("other like", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherNotLike(String value) {
            addCriterion("other not like", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherIn(List<String> values) {
            addCriterion("other in", values, "other");
            return (Criteria) this;
        }

        public Criteria andOtherNotIn(List<String> values) {
            addCriterion("other not in", values, "other");
            return (Criteria) this;
        }

        public Criteria andOtherBetween(String value1, String value2) {
            addCriterion("other between", value1, value2, "other");
            return (Criteria) this;
        }

        public Criteria andOtherNotBetween(String value1, String value2) {
            addCriterion("other not between", value1, value2, "other");
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

        public Criteria andCommisiontimeIsNull() {
            addCriterion("commisiontime is null");
            return (Criteria) this;
        }

        public Criteria andCommisiontimeIsNotNull() {
            addCriterion("commisiontime is not null");
            return (Criteria) this;
        }

        public Criteria andCommisiontimeEqualTo(Date value) {
            addCriterion("commisiontime =", value, "commisiontime");
            return (Criteria) this;
        }

        public Criteria andCommisiontimeNotEqualTo(Date value) {
            addCriterion("commisiontime <>", value, "commisiontime");
            return (Criteria) this;
        }

        public Criteria andCommisiontimeGreaterThan(Date value) {
            addCriterion("commisiontime >", value, "commisiontime");
            return (Criteria) this;
        }

        public Criteria andCommisiontimeGreaterThanOrEqualTo(Date value) {
            addCriterion("commisiontime >=", value, "commisiontime");
            return (Criteria) this;
        }

        public Criteria andCommisiontimeLessThan(Date value) {
            addCriterion("commisiontime <", value, "commisiontime");
            return (Criteria) this;
        }

        public Criteria andCommisiontimeLessThanOrEqualTo(Date value) {
            addCriterion("commisiontime <=", value, "commisiontime");
            return (Criteria) this;
        }

        public Criteria andCommisiontimeIn(List<Date> values) {
            addCriterion("commisiontime in", values, "commisiontime");
            return (Criteria) this;
        }

        public Criteria andCommisiontimeNotIn(List<Date> values) {
            addCriterion("commisiontime not in", values, "commisiontime");
            return (Criteria) this;
        }

        public Criteria andCommisiontimeBetween(Date value1, Date value2) {
            addCriterion("commisiontime between", value1, value2, "commisiontime");
            return (Criteria) this;
        }

        public Criteria andCommisiontimeNotBetween(Date value1, Date value2) {
            addCriterion("commisiontime not between", value1, value2, "commisiontime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * null
     * 
     * @author wcyong
     * 
     * @date 2018-05-23
     */
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