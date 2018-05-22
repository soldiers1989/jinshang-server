package project.jinshang.mod_product.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderProductBackExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderProductBackExample() {
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

        public Criteria andOrderpdidIsNull() {
            addCriterion("orderpdid is null");
            return (Criteria) this;
        }

        public Criteria andOrderpdidIsNotNull() {
            addCriterion("orderpdid is not null");
            return (Criteria) this;
        }

        public Criteria andOrderpdidEqualTo(Long value) {
            addCriterion("orderpdid =", value, "orderpdid");
            return (Criteria) this;
        }

        public Criteria andOrderpdidNotEqualTo(Long value) {
            addCriterion("orderpdid <>", value, "orderpdid");
            return (Criteria) this;
        }

        public Criteria andOrderpdidGreaterThan(Long value) {
            addCriterion("orderpdid >", value, "orderpdid");
            return (Criteria) this;
        }

        public Criteria andOrderpdidGreaterThanOrEqualTo(Long value) {
            addCriterion("orderpdid >=", value, "orderpdid");
            return (Criteria) this;
        }

        public Criteria andOrderpdidLessThan(Long value) {
            addCriterion("orderpdid <", value, "orderpdid");
            return (Criteria) this;
        }

        public Criteria andOrderpdidLessThanOrEqualTo(Long value) {
            addCriterion("orderpdid <=", value, "orderpdid");
            return (Criteria) this;
        }

        public Criteria andOrderpdidIn(List<Long> values) {
            addCriterion("orderpdid in", values, "orderpdid");
            return (Criteria) this;
        }

        public Criteria andOrderpdidNotIn(List<Long> values) {
            addCriterion("orderpdid not in", values, "orderpdid");
            return (Criteria) this;
        }

        public Criteria andOrderpdidBetween(Long value1, Long value2) {
            addCriterion("orderpdid between", value1, value2, "orderpdid");
            return (Criteria) this;
        }

        public Criteria andOrderpdidNotBetween(Long value1, Long value2) {
            addCriterion("orderpdid not between", value1, value2, "orderpdid");
            return (Criteria) this;
        }

        public Criteria andBacktypeIsNull() {
            addCriterion("backtype is null");
            return (Criteria) this;
        }

        public Criteria andBacktypeIsNotNull() {
            addCriterion("backtype is not null");
            return (Criteria) this;
        }

        public Criteria andBacktypeEqualTo(Short value) {
            addCriterion("backtype =", value, "backtype");
            return (Criteria) this;
        }

        public Criteria andBacktypeNotEqualTo(Short value) {
            addCriterion("backtype <>", value, "backtype");
            return (Criteria) this;
        }

        public Criteria andBacktypeGreaterThan(Short value) {
            addCriterion("backtype >", value, "backtype");
            return (Criteria) this;
        }

        public Criteria andBacktypeGreaterThanOrEqualTo(Short value) {
            addCriterion("backtype >=", value, "backtype");
            return (Criteria) this;
        }

        public Criteria andBacktypeLessThan(Short value) {
            addCriterion("backtype <", value, "backtype");
            return (Criteria) this;
        }

        public Criteria andBacktypeLessThanOrEqualTo(Short value) {
            addCriterion("backtype <=", value, "backtype");
            return (Criteria) this;
        }

        public Criteria andBacktypeIn(List<Short> values) {
            addCriterion("backtype in", values, "backtype");
            return (Criteria) this;
        }

        public Criteria andBacktypeNotIn(List<Short> values) {
            addCriterion("backtype not in", values, "backtype");
            return (Criteria) this;
        }

        public Criteria andBacktypeBetween(Short value1, Short value2) {
            addCriterion("backtype between", value1, value2, "backtype");
            return (Criteria) this;
        }

        public Criteria andBacktypeNotBetween(Short value1, Short value2) {
            addCriterion("backtype not between", value1, value2, "backtype");
            return (Criteria) this;
        }

        public Criteria andBacknoIsNull() {
            addCriterion("backno is null");
            return (Criteria) this;
        }

        public Criteria andBacknoIsNotNull() {
            addCriterion("backno is not null");
            return (Criteria) this;
        }

        public Criteria andBacknoEqualTo(String value) {
            addCriterion("backno =", value, "backno");
            return (Criteria) this;
        }

        public Criteria andBacknoNotEqualTo(String value) {
            addCriterion("backno <>", value, "backno");
            return (Criteria) this;
        }

        public Criteria andBacknoGreaterThan(String value) {
            addCriterion("backno >", value, "backno");
            return (Criteria) this;
        }

        public Criteria andBacknoGreaterThanOrEqualTo(String value) {
            addCriterion("backno >=", value, "backno");
            return (Criteria) this;
        }

        public Criteria andBacknoLessThan(String value) {
            addCriterion("backno <", value, "backno");
            return (Criteria) this;
        }

        public Criteria andBacknoLessThanOrEqualTo(String value) {
            addCriterion("backno <=", value, "backno");
            return (Criteria) this;
        }

        public Criteria andBacknoLike(String value) {
            addCriterion("backno like", value, "backno");
            return (Criteria) this;
        }

        public Criteria andBacknoNotLike(String value) {
            addCriterion("backno not like", value, "backno");
            return (Criteria) this;
        }

        public Criteria andBacknoIn(List<String> values) {
            addCriterion("backno in", values, "backno");
            return (Criteria) this;
        }

        public Criteria andBacknoNotIn(List<String> values) {
            addCriterion("backno not in", values, "backno");
            return (Criteria) this;
        }

        public Criteria andBacknoBetween(String value1, String value2) {
            addCriterion("backno between", value1, value2, "backno");
            return (Criteria) this;
        }

        public Criteria andBacknoNotBetween(String value1, String value2) {
            addCriterion("backno not between", value1, value2, "backno");
            return (Criteria) this;
        }

        public Criteria andBackmoneyIsNull() {
            addCriterion("backmoney is null");
            return (Criteria) this;
        }

        public Criteria andBackmoneyIsNotNull() {
            addCriterion("backmoney is not null");
            return (Criteria) this;
        }

        public Criteria andBackmoneyEqualTo(BigDecimal value) {
            addCriterion("backmoney =", value, "backmoney");
            return (Criteria) this;
        }

        public Criteria andBackmoneyNotEqualTo(BigDecimal value) {
            addCriterion("backmoney <>", value, "backmoney");
            return (Criteria) this;
        }

        public Criteria andBackmoneyGreaterThan(BigDecimal value) {
            addCriterion("backmoney >", value, "backmoney");
            return (Criteria) this;
        }

        public Criteria andBackmoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("backmoney >=", value, "backmoney");
            return (Criteria) this;
        }

        public Criteria andBackmoneyLessThan(BigDecimal value) {
            addCriterion("backmoney <", value, "backmoney");
            return (Criteria) this;
        }

        public Criteria andBackmoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("backmoney <=", value, "backmoney");
            return (Criteria) this;
        }

        public Criteria andBackmoneyIn(List<BigDecimal> values) {
            addCriterion("backmoney in", values, "backmoney");
            return (Criteria) this;
        }

        public Criteria andBackmoneyNotIn(List<BigDecimal> values) {
            addCriterion("backmoney not in", values, "backmoney");
            return (Criteria) this;
        }

        public Criteria andBackmoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("backmoney between", value1, value2, "backmoney");
            return (Criteria) this;
        }

        public Criteria andBackmoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("backmoney not between", value1, value2, "backmoney");
            return (Criteria) this;
        }

        public Criteria andPdnoIsNull() {
            addCriterion("pdno is null");
            return (Criteria) this;
        }

        public Criteria andPdnoIsNotNull() {
            addCriterion("pdno is not null");
            return (Criteria) this;
        }

        public Criteria andPdnoEqualTo(String value) {
            addCriterion("pdno =", value, "pdno");
            return (Criteria) this;
        }

        public Criteria andPdnoNotEqualTo(String value) {
            addCriterion("pdno <>", value, "pdno");
            return (Criteria) this;
        }

        public Criteria andPdnoGreaterThan(String value) {
            addCriterion("pdno >", value, "pdno");
            return (Criteria) this;
        }

        public Criteria andPdnoGreaterThanOrEqualTo(String value) {
            addCriterion("pdno >=", value, "pdno");
            return (Criteria) this;
        }

        public Criteria andPdnoLessThan(String value) {
            addCriterion("pdno <", value, "pdno");
            return (Criteria) this;
        }

        public Criteria andPdnoLessThanOrEqualTo(String value) {
            addCriterion("pdno <=", value, "pdno");
            return (Criteria) this;
        }

        public Criteria andPdnoLike(String value) {
            addCriterion("pdno like", value, "pdno");
            return (Criteria) this;
        }

        public Criteria andPdnoNotLike(String value) {
            addCriterion("pdno not like", value, "pdno");
            return (Criteria) this;
        }

        public Criteria andPdnoIn(List<String> values) {
            addCriterion("pdno in", values, "pdno");
            return (Criteria) this;
        }

        public Criteria andPdnoNotIn(List<String> values) {
            addCriterion("pdno not in", values, "pdno");
            return (Criteria) this;
        }

        public Criteria andPdnoBetween(String value1, String value2) {
            addCriterion("pdno between", value1, value2, "pdno");
            return (Criteria) this;
        }

        public Criteria andPdnoNotBetween(String value1, String value2) {
            addCriterion("pdno not between", value1, value2, "pdno");
            return (Criteria) this;
        }

        public Criteria andPdidIsNull() {
            addCriterion("pdid is null");
            return (Criteria) this;
        }

        public Criteria andPdidIsNotNull() {
            addCriterion("pdid is not null");
            return (Criteria) this;
        }

        public Criteria andPdidEqualTo(Long value) {
            addCriterion("pdid =", value, "pdid");
            return (Criteria) this;
        }

        public Criteria andPdidNotEqualTo(Long value) {
            addCriterion("pdid <>", value, "pdid");
            return (Criteria) this;
        }

        public Criteria andPdidGreaterThan(Long value) {
            addCriterion("pdid >", value, "pdid");
            return (Criteria) this;
        }

        public Criteria andPdidGreaterThanOrEqualTo(Long value) {
            addCriterion("pdid >=", value, "pdid");
            return (Criteria) this;
        }

        public Criteria andPdidLessThan(Long value) {
            addCriterion("pdid <", value, "pdid");
            return (Criteria) this;
        }

        public Criteria andPdidLessThanOrEqualTo(Long value) {
            addCriterion("pdid <=", value, "pdid");
            return (Criteria) this;
        }

        public Criteria andPdidIn(List<Long> values) {
            addCriterion("pdid in", values, "pdid");
            return (Criteria) this;
        }

        public Criteria andPdidNotIn(List<Long> values) {
            addCriterion("pdid not in", values, "pdid");
            return (Criteria) this;
        }

        public Criteria andPdidBetween(Long value1, Long value2) {
            addCriterion("pdid between", value1, value2, "pdid");
            return (Criteria) this;
        }

        public Criteria andPdidNotBetween(Long value1, Long value2) {
            addCriterion("pdid not between", value1, value2, "pdid");
            return (Criteria) this;
        }

        public Criteria andPdnameIsNull() {
            addCriterion("pdname is null");
            return (Criteria) this;
        }

        public Criteria andPdnameIsNotNull() {
            addCriterion("pdname is not null");
            return (Criteria) this;
        }

        public Criteria andPdnameEqualTo(String value) {
            addCriterion("pdname =", value, "pdname");
            return (Criteria) this;
        }

        public Criteria andPdnameNotEqualTo(String value) {
            addCriterion("pdname <>", value, "pdname");
            return (Criteria) this;
        }

        public Criteria andPdnameGreaterThan(String value) {
            addCriterion("pdname >", value, "pdname");
            return (Criteria) this;
        }

        public Criteria andPdnameGreaterThanOrEqualTo(String value) {
            addCriterion("pdname >=", value, "pdname");
            return (Criteria) this;
        }

        public Criteria andPdnameLessThan(String value) {
            addCriterion("pdname <", value, "pdname");
            return (Criteria) this;
        }

        public Criteria andPdnameLessThanOrEqualTo(String value) {
            addCriterion("pdname <=", value, "pdname");
            return (Criteria) this;
        }

        public Criteria andPdnameLike(String value) {
            addCriterion("pdname like", value, "pdname");
            return (Criteria) this;
        }

        public Criteria andPdnameNotLike(String value) {
            addCriterion("pdname not like", value, "pdname");
            return (Criteria) this;
        }

        public Criteria andPdnameIn(List<String> values) {
            addCriterion("pdname in", values, "pdname");
            return (Criteria) this;
        }

        public Criteria andPdnameNotIn(List<String> values) {
            addCriterion("pdname not in", values, "pdname");
            return (Criteria) this;
        }

        public Criteria andPdnameBetween(String value1, String value2) {
            addCriterion("pdname between", value1, value2, "pdname");
            return (Criteria) this;
        }

        public Criteria andPdnameNotBetween(String value1, String value2) {
            addCriterion("pdname not between", value1, value2, "pdname");
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

        public Criteria andLogisticsnoIsNull() {
            addCriterion("logisticsno is null");
            return (Criteria) this;
        }

        public Criteria andLogisticsnoIsNotNull() {
            addCriterion("logisticsno is not null");
            return (Criteria) this;
        }

        public Criteria andLogisticsnoEqualTo(String value) {
            addCriterion("logisticsno =", value, "logisticsno");
            return (Criteria) this;
        }

        public Criteria andLogisticsnoNotEqualTo(String value) {
            addCriterion("logisticsno <>", value, "logisticsno");
            return (Criteria) this;
        }

        public Criteria andLogisticsnoGreaterThan(String value) {
            addCriterion("logisticsno >", value, "logisticsno");
            return (Criteria) this;
        }

        public Criteria andLogisticsnoGreaterThanOrEqualTo(String value) {
            addCriterion("logisticsno >=", value, "logisticsno");
            return (Criteria) this;
        }

        public Criteria andLogisticsnoLessThan(String value) {
            addCriterion("logisticsno <", value, "logisticsno");
            return (Criteria) this;
        }

        public Criteria andLogisticsnoLessThanOrEqualTo(String value) {
            addCriterion("logisticsno <=", value, "logisticsno");
            return (Criteria) this;
        }

        public Criteria andLogisticsnoLike(String value) {
            addCriterion("logisticsno like", value, "logisticsno");
            return (Criteria) this;
        }

        public Criteria andLogisticsnoNotLike(String value) {
            addCriterion("logisticsno not like", value, "logisticsno");
            return (Criteria) this;
        }

        public Criteria andLogisticsnoIn(List<String> values) {
            addCriterion("logisticsno in", values, "logisticsno");
            return (Criteria) this;
        }

        public Criteria andLogisticsnoNotIn(List<String> values) {
            addCriterion("logisticsno not in", values, "logisticsno");
            return (Criteria) this;
        }

        public Criteria andLogisticsnoBetween(String value1, String value2) {
            addCriterion("logisticsno between", value1, value2, "logisticsno");
            return (Criteria) this;
        }

        public Criteria andLogisticsnoNotBetween(String value1, String value2) {
            addCriterion("logisticsno not between", value1, value2, "logisticsno");
            return (Criteria) this;
        }

        public Criteria andLogisticscompanyIsNull() {
            addCriterion("logisticscompany is null");
            return (Criteria) this;
        }

        public Criteria andLogisticscompanyIsNotNull() {
            addCriterion("logisticscompany is not null");
            return (Criteria) this;
        }

        public Criteria andLogisticscompanyEqualTo(String value) {
            addCriterion("logisticscompany =", value, "logisticscompany");
            return (Criteria) this;
        }

        public Criteria andLogisticscompanyNotEqualTo(String value) {
            addCriterion("logisticscompany <>", value, "logisticscompany");
            return (Criteria) this;
        }

        public Criteria andLogisticscompanyGreaterThan(String value) {
            addCriterion("logisticscompany >", value, "logisticscompany");
            return (Criteria) this;
        }

        public Criteria andLogisticscompanyGreaterThanOrEqualTo(String value) {
            addCriterion("logisticscompany >=", value, "logisticscompany");
            return (Criteria) this;
        }

        public Criteria andLogisticscompanyLessThan(String value) {
            addCriterion("logisticscompany <", value, "logisticscompany");
            return (Criteria) this;
        }

        public Criteria andLogisticscompanyLessThanOrEqualTo(String value) {
            addCriterion("logisticscompany <=", value, "logisticscompany");
            return (Criteria) this;
        }

        public Criteria andLogisticscompanyLike(String value) {
            addCriterion("logisticscompany like", value, "logisticscompany");
            return (Criteria) this;
        }

        public Criteria andLogisticscompanyNotLike(String value) {
            addCriterion("logisticscompany not like", value, "logisticscompany");
            return (Criteria) this;
        }

        public Criteria andLogisticscompanyIn(List<String> values) {
            addCriterion("logisticscompany in", values, "logisticscompany");
            return (Criteria) this;
        }

        public Criteria andLogisticscompanyNotIn(List<String> values) {
            addCriterion("logisticscompany not in", values, "logisticscompany");
            return (Criteria) this;
        }

        public Criteria andLogisticscompanyBetween(String value1, String value2) {
            addCriterion("logisticscompany between", value1, value2, "logisticscompany");
            return (Criteria) this;
        }

        public Criteria andLogisticscompanyNotBetween(String value1, String value2) {
            addCriterion("logisticscompany not between", value1, value2, "logisticscompany");
            return (Criteria) this;
        }

        public Criteria andReturnbackreasonIsNull() {
            addCriterion("returnbackreason is null");
            return (Criteria) this;
        }

        public Criteria andReturnbackreasonIsNotNull() {
            addCriterion("returnbackreason is not null");
            return (Criteria) this;
        }

        public Criteria andReturnbackreasonEqualTo(String value) {
            addCriterion("returnbackreason =", value, "returnbackreason");
            return (Criteria) this;
        }

        public Criteria andReturnbackreasonNotEqualTo(String value) {
            addCriterion("returnbackreason <>", value, "returnbackreason");
            return (Criteria) this;
        }

        public Criteria andReturnbackreasonGreaterThan(String value) {
            addCriterion("returnbackreason >", value, "returnbackreason");
            return (Criteria) this;
        }

        public Criteria andReturnbackreasonGreaterThanOrEqualTo(String value) {
            addCriterion("returnbackreason >=", value, "returnbackreason");
            return (Criteria) this;
        }

        public Criteria andReturnbackreasonLessThan(String value) {
            addCriterion("returnbackreason <", value, "returnbackreason");
            return (Criteria) this;
        }

        public Criteria andReturnbackreasonLessThanOrEqualTo(String value) {
            addCriterion("returnbackreason <=", value, "returnbackreason");
            return (Criteria) this;
        }

        public Criteria andReturnbackreasonLike(String value) {
            addCriterion("returnbackreason like", value, "returnbackreason");
            return (Criteria) this;
        }

        public Criteria andReturnbackreasonNotLike(String value) {
            addCriterion("returnbackreason not like", value, "returnbackreason");
            return (Criteria) this;
        }

        public Criteria andReturnbackreasonIn(List<String> values) {
            addCriterion("returnbackreason in", values, "returnbackreason");
            return (Criteria) this;
        }

        public Criteria andReturnbackreasonNotIn(List<String> values) {
            addCriterion("returnbackreason not in", values, "returnbackreason");
            return (Criteria) this;
        }

        public Criteria andReturnbackreasonBetween(String value1, String value2) {
            addCriterion("returnbackreason between", value1, value2, "returnbackreason");
            return (Criteria) this;
        }

        public Criteria andReturnbackreasonNotBetween(String value1, String value2) {
            addCriterion("returnbackreason not between", value1, value2, "returnbackreason");
            return (Criteria) this;
        }

        public Criteria andPicIsNull() {
            addCriterion("pic is null");
            return (Criteria) this;
        }

        public Criteria andPicIsNotNull() {
            addCriterion("pic is not null");
            return (Criteria) this;
        }

        public Criteria andPicEqualTo(String value) {
            addCriterion("pic =", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotEqualTo(String value) {
            addCriterion("pic <>", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicGreaterThan(String value) {
            addCriterion("pic >", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicGreaterThanOrEqualTo(String value) {
            addCriterion("pic >=", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLessThan(String value) {
            addCriterion("pic <", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLessThanOrEqualTo(String value) {
            addCriterion("pic <=", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLike(String value) {
            addCriterion("pic like", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotLike(String value) {
            addCriterion("pic not like", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicIn(List<String> values) {
            addCriterion("pic in", values, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotIn(List<String> values) {
            addCriterion("pic not in", values, "pic");
            return (Criteria) this;
        }

        public Criteria andPicBetween(String value1, String value2) {
            addCriterion("pic between", value1, value2, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotBetween(String value1, String value2) {
            addCriterion("pic not between", value1, value2, "pic");
            return (Criteria) this;
        }

        public Criteria andBackaddressIsNull() {
            addCriterion("backaddress is null");
            return (Criteria) this;
        }

        public Criteria andBackaddressIsNotNull() {
            addCriterion("backaddress is not null");
            return (Criteria) this;
        }

        public Criteria andBackaddressEqualTo(String value) {
            addCriterion("backaddress =", value, "backaddress");
            return (Criteria) this;
        }

        public Criteria andBackaddressNotEqualTo(String value) {
            addCriterion("backaddress <>", value, "backaddress");
            return (Criteria) this;
        }

        public Criteria andBackaddressGreaterThan(String value) {
            addCriterion("backaddress >", value, "backaddress");
            return (Criteria) this;
        }

        public Criteria andBackaddressGreaterThanOrEqualTo(String value) {
            addCriterion("backaddress >=", value, "backaddress");
            return (Criteria) this;
        }

        public Criteria andBackaddressLessThan(String value) {
            addCriterion("backaddress <", value, "backaddress");
            return (Criteria) this;
        }

        public Criteria andBackaddressLessThanOrEqualTo(String value) {
            addCriterion("backaddress <=", value, "backaddress");
            return (Criteria) this;
        }

        public Criteria andBackaddressLike(String value) {
            addCriterion("backaddress like", value, "backaddress");
            return (Criteria) this;
        }

        public Criteria andBackaddressNotLike(String value) {
            addCriterion("backaddress not like", value, "backaddress");
            return (Criteria) this;
        }

        public Criteria andBackaddressIn(List<String> values) {
            addCriterion("backaddress in", values, "backaddress");
            return (Criteria) this;
        }

        public Criteria andBackaddressNotIn(List<String> values) {
            addCriterion("backaddress not in", values, "backaddress");
            return (Criteria) this;
        }

        public Criteria andBackaddressBetween(String value1, String value2) {
            addCriterion("backaddress between", value1, value2, "backaddress");
            return (Criteria) this;
        }

        public Criteria andBackaddressNotBetween(String value1, String value2) {
            addCriterion("backaddress not between", value1, value2, "backaddress");
            return (Criteria) this;
        }

        public Criteria andBackexplainIsNull() {
            addCriterion("backexplain is null");
            return (Criteria) this;
        }

        public Criteria andBackexplainIsNotNull() {
            addCriterion("backexplain is not null");
            return (Criteria) this;
        }

        public Criteria andBackexplainEqualTo(String value) {
            addCriterion("backexplain =", value, "backexplain");
            return (Criteria) this;
        }

        public Criteria andBackexplainNotEqualTo(String value) {
            addCriterion("backexplain <>", value, "backexplain");
            return (Criteria) this;
        }

        public Criteria andBackexplainGreaterThan(String value) {
            addCriterion("backexplain >", value, "backexplain");
            return (Criteria) this;
        }

        public Criteria andBackexplainGreaterThanOrEqualTo(String value) {
            addCriterion("backexplain >=", value, "backexplain");
            return (Criteria) this;
        }

        public Criteria andBackexplainLessThan(String value) {
            addCriterion("backexplain <", value, "backexplain");
            return (Criteria) this;
        }

        public Criteria andBackexplainLessThanOrEqualTo(String value) {
            addCriterion("backexplain <=", value, "backexplain");
            return (Criteria) this;
        }

        public Criteria andBackexplainLike(String value) {
            addCriterion("backexplain like", value, "backexplain");
            return (Criteria) this;
        }

        public Criteria andBackexplainNotLike(String value) {
            addCriterion("backexplain not like", value, "backexplain");
            return (Criteria) this;
        }

        public Criteria andBackexplainIn(List<String> values) {
            addCriterion("backexplain in", values, "backexplain");
            return (Criteria) this;
        }

        public Criteria andBackexplainNotIn(List<String> values) {
            addCriterion("backexplain not in", values, "backexplain");
            return (Criteria) this;
        }

        public Criteria andBackexplainBetween(String value1, String value2) {
            addCriterion("backexplain between", value1, value2, "backexplain");
            return (Criteria) this;
        }

        public Criteria andBackexplainNotBetween(String value1, String value2) {
            addCriterion("backexplain not between", value1, value2, "backexplain");
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

        public Criteria andSelleridIsNull() {
            addCriterion("sellerid is null");
            return (Criteria) this;
        }

        public Criteria andSelleridIsNotNull() {
            addCriterion("sellerid is not null");
            return (Criteria) this;
        }

        public Criteria andSelleridEqualTo(Long value) {
            addCriterion("sellerid =", value, "sellerid");
            return (Criteria) this;
        }

        public Criteria andSelleridNotEqualTo(Long value) {
            addCriterion("sellerid <>", value, "sellerid");
            return (Criteria) this;
        }

        public Criteria andSelleridGreaterThan(Long value) {
            addCriterion("sellerid >", value, "sellerid");
            return (Criteria) this;
        }

        public Criteria andSelleridGreaterThanOrEqualTo(Long value) {
            addCriterion("sellerid >=", value, "sellerid");
            return (Criteria) this;
        }

        public Criteria andSelleridLessThan(Long value) {
            addCriterion("sellerid <", value, "sellerid");
            return (Criteria) this;
        }

        public Criteria andSelleridLessThanOrEqualTo(Long value) {
            addCriterion("sellerid <=", value, "sellerid");
            return (Criteria) this;
        }

        public Criteria andSelleridIn(List<Long> values) {
            addCriterion("sellerid in", values, "sellerid");
            return (Criteria) this;
        }

        public Criteria andSelleridNotIn(List<Long> values) {
            addCriterion("sellerid not in", values, "sellerid");
            return (Criteria) this;
        }

        public Criteria andSelleridBetween(Long value1, Long value2) {
            addCriterion("sellerid between", value1, value2, "sellerid");
            return (Criteria) this;
        }

        public Criteria andSelleridNotBetween(Long value1, Long value2) {
            addCriterion("sellerid not between", value1, value2, "sellerid");
            return (Criteria) this;
        }

        public Criteria andStoreidIsNull() {
            addCriterion("storeid is null");
            return (Criteria) this;
        }

        public Criteria andStoreidIsNotNull() {
            addCriterion("storeid is not null");
            return (Criteria) this;
        }

        public Criteria andStoreidEqualTo(Long value) {
            addCriterion("storeid =", value, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidNotEqualTo(Long value) {
            addCriterion("storeid <>", value, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidGreaterThan(Long value) {
            addCriterion("storeid >", value, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidGreaterThanOrEqualTo(Long value) {
            addCriterion("storeid >=", value, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidLessThan(Long value) {
            addCriterion("storeid <", value, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidLessThanOrEqualTo(Long value) {
            addCriterion("storeid <=", value, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidIn(List<Long> values) {
            addCriterion("storeid in", values, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidNotIn(List<Long> values) {
            addCriterion("storeid not in", values, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidBetween(Long value1, Long value2) {
            addCriterion("storeid between", value1, value2, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidNotBetween(Long value1, Long value2) {
            addCriterion("storeid not between", value1, value2, "storeid");
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

        public Criteria andPdnumIsNull() {
            addCriterion("pdnum is null");
            return (Criteria) this;
        }

        public Criteria andPdnumIsNotNull() {
            addCriterion("pdnum is not null");
            return (Criteria) this;
        }

        public Criteria andPdnumEqualTo(BigDecimal value) {
            addCriterion("pdnum =", value, "pdnum");
            return (Criteria) this;
        }

        public Criteria andPdnumNotEqualTo(BigDecimal value) {
            addCriterion("pdnum <>", value, "pdnum");
            return (Criteria) this;
        }

        public Criteria andPdnumGreaterThan(BigDecimal value) {
            addCriterion("pdnum >", value, "pdnum");
            return (Criteria) this;
        }

        public Criteria andPdnumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("pdnum >=", value, "pdnum");
            return (Criteria) this;
        }

        public Criteria andPdnumLessThan(BigDecimal value) {
            addCriterion("pdnum <", value, "pdnum");
            return (Criteria) this;
        }

        public Criteria andPdnumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("pdnum <=", value, "pdnum");
            return (Criteria) this;
        }

        public Criteria andPdnumIn(List<BigDecimal> values) {
            addCriterion("pdnum in", values, "pdnum");
            return (Criteria) this;
        }

        public Criteria andPdnumNotIn(List<BigDecimal> values) {
            addCriterion("pdnum not in", values, "pdnum");
            return (Criteria) this;
        }

        public Criteria andPdnumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pdnum between", value1, value2, "pdnum");
            return (Criteria) this;
        }

        public Criteria andPdnumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pdnum not between", value1, value2, "pdnum");
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

        public Criteria andIsreceivegoodsIsNull() {
            addCriterion("isreceivegoods is null");
            return (Criteria) this;
        }

        public Criteria andIsreceivegoodsIsNotNull() {
            addCriterion("isreceivegoods is not null");
            return (Criteria) this;
        }

        public Criteria andIsreceivegoodsEqualTo(Short value) {
            addCriterion("isreceivegoods =", value, "isreceivegoods");
            return (Criteria) this;
        }

        public Criteria andIsreceivegoodsNotEqualTo(Short value) {
            addCriterion("isreceivegoods <>", value, "isreceivegoods");
            return (Criteria) this;
        }

        public Criteria andIsreceivegoodsGreaterThan(Short value) {
            addCriterion("isreceivegoods >", value, "isreceivegoods");
            return (Criteria) this;
        }

        public Criteria andIsreceivegoodsGreaterThanOrEqualTo(Short value) {
            addCriterion("isreceivegoods >=", value, "isreceivegoods");
            return (Criteria) this;
        }

        public Criteria andIsreceivegoodsLessThan(Short value) {
            addCriterion("isreceivegoods <", value, "isreceivegoods");
            return (Criteria) this;
        }

        public Criteria andIsreceivegoodsLessThanOrEqualTo(Short value) {
            addCriterion("isreceivegoods <=", value, "isreceivegoods");
            return (Criteria) this;
        }

        public Criteria andIsreceivegoodsIn(List<Short> values) {
            addCriterion("isreceivegoods in", values, "isreceivegoods");
            return (Criteria) this;
        }

        public Criteria andIsreceivegoodsNotIn(List<Short> values) {
            addCriterion("isreceivegoods not in", values, "isreceivegoods");
            return (Criteria) this;
        }

        public Criteria andIsreceivegoodsBetween(Short value1, Short value2) {
            addCriterion("isreceivegoods between", value1, value2, "isreceivegoods");
            return (Criteria) this;
        }

        public Criteria andIsreceivegoodsNotBetween(Short value1, Short value2) {
            addCriterion("isreceivegoods not between", value1, value2, "isreceivegoods");
            return (Criteria) this;
        }

        public Criteria andSellnameIsNull() {
            addCriterion("sellname is null");
            return (Criteria) this;
        }

        public Criteria andSellnameIsNotNull() {
            addCriterion("sellname is not null");
            return (Criteria) this;
        }

        public Criteria andSellnameEqualTo(String value) {
            addCriterion("sellname =", value, "sellname");
            return (Criteria) this;
        }

        public Criteria andSellnameNotEqualTo(String value) {
            addCriterion("sellname <>", value, "sellname");
            return (Criteria) this;
        }

        public Criteria andSellnameGreaterThan(String value) {
            addCriterion("sellname >", value, "sellname");
            return (Criteria) this;
        }

        public Criteria andSellnameGreaterThanOrEqualTo(String value) {
            addCriterion("sellname >=", value, "sellname");
            return (Criteria) this;
        }

        public Criteria andSellnameLessThan(String value) {
            addCriterion("sellname <", value, "sellname");
            return (Criteria) this;
        }

        public Criteria andSellnameLessThanOrEqualTo(String value) {
            addCriterion("sellname <=", value, "sellname");
            return (Criteria) this;
        }

        public Criteria andSellnameLike(String value) {
            addCriterion("sellname like", value, "sellname");
            return (Criteria) this;
        }

        public Criteria andSellnameNotLike(String value) {
            addCriterion("sellname not like", value, "sellname");
            return (Criteria) this;
        }

        public Criteria andSellnameIn(List<String> values) {
            addCriterion("sellname in", values, "sellname");
            return (Criteria) this;
        }

        public Criteria andSellnameNotIn(List<String> values) {
            addCriterion("sellname not in", values, "sellname");
            return (Criteria) this;
        }

        public Criteria andSellnameBetween(String value1, String value2) {
            addCriterion("sellname between", value1, value2, "sellname");
            return (Criteria) this;
        }

        public Criteria andSellnameNotBetween(String value1, String value2) {
            addCriterion("sellname not between", value1, value2, "sellname");
            return (Criteria) this;
        }

        public Criteria andSellernotagreeIsNull() {
            addCriterion("sellernotagree is null");
            return (Criteria) this;
        }

        public Criteria andSellernotagreeIsNotNull() {
            addCriterion("sellernotagree is not null");
            return (Criteria) this;
        }

        public Criteria andSellernotagreeEqualTo(String value) {
            addCriterion("sellernotagree =", value, "sellernotagree");
            return (Criteria) this;
        }

        public Criteria andSellernotagreeNotEqualTo(String value) {
            addCriterion("sellernotagree <>", value, "sellernotagree");
            return (Criteria) this;
        }

        public Criteria andSellernotagreeGreaterThan(String value) {
            addCriterion("sellernotagree >", value, "sellernotagree");
            return (Criteria) this;
        }

        public Criteria andSellernotagreeGreaterThanOrEqualTo(String value) {
            addCriterion("sellernotagree >=", value, "sellernotagree");
            return (Criteria) this;
        }

        public Criteria andSellernotagreeLessThan(String value) {
            addCriterion("sellernotagree <", value, "sellernotagree");
            return (Criteria) this;
        }

        public Criteria andSellernotagreeLessThanOrEqualTo(String value) {
            addCriterion("sellernotagree <=", value, "sellernotagree");
            return (Criteria) this;
        }

        public Criteria andSellernotagreeLike(String value) {
            addCriterion("sellernotagree like", value, "sellernotagree");
            return (Criteria) this;
        }

        public Criteria andSellernotagreeNotLike(String value) {
            addCriterion("sellernotagree not like", value, "sellernotagree");
            return (Criteria) this;
        }

        public Criteria andSellernotagreeIn(List<String> values) {
            addCriterion("sellernotagree in", values, "sellernotagree");
            return (Criteria) this;
        }

        public Criteria andSellernotagreeNotIn(List<String> values) {
            addCriterion("sellernotagree not in", values, "sellernotagree");
            return (Criteria) this;
        }

        public Criteria andSellernotagreeBetween(String value1, String value2) {
            addCriterion("sellernotagree between", value1, value2, "sellernotagree");
            return (Criteria) this;
        }

        public Criteria andSellernotagreeNotBetween(String value1, String value2) {
            addCriterion("sellernotagree not between", value1, value2, "sellernotagree");
            return (Criteria) this;
        }

        public Criteria andAdminreasonIsNull() {
            addCriterion("adminreason is null");
            return (Criteria) this;
        }

        public Criteria andAdminreasonIsNotNull() {
            addCriterion("adminreason is not null");
            return (Criteria) this;
        }

        public Criteria andAdminreasonEqualTo(String value) {
            addCriterion("adminreason =", value, "adminreason");
            return (Criteria) this;
        }

        public Criteria andAdminreasonNotEqualTo(String value) {
            addCriterion("adminreason <>", value, "adminreason");
            return (Criteria) this;
        }

        public Criteria andAdminreasonGreaterThan(String value) {
            addCriterion("adminreason >", value, "adminreason");
            return (Criteria) this;
        }

        public Criteria andAdminreasonGreaterThanOrEqualTo(String value) {
            addCriterion("adminreason >=", value, "adminreason");
            return (Criteria) this;
        }

        public Criteria andAdminreasonLessThan(String value) {
            addCriterion("adminreason <", value, "adminreason");
            return (Criteria) this;
        }

        public Criteria andAdminreasonLessThanOrEqualTo(String value) {
            addCriterion("adminreason <=", value, "adminreason");
            return (Criteria) this;
        }

        public Criteria andAdminreasonLike(String value) {
            addCriterion("adminreason like", value, "adminreason");
            return (Criteria) this;
        }

        public Criteria andAdminreasonNotLike(String value) {
            addCriterion("adminreason not like", value, "adminreason");
            return (Criteria) this;
        }

        public Criteria andAdminreasonIn(List<String> values) {
            addCriterion("adminreason in", values, "adminreason");
            return (Criteria) this;
        }

        public Criteria andAdminreasonNotIn(List<String> values) {
            addCriterion("adminreason not in", values, "adminreason");
            return (Criteria) this;
        }

        public Criteria andAdminreasonBetween(String value1, String value2) {
            addCriterion("adminreason between", value1, value2, "adminreason");
            return (Criteria) this;
        }

        public Criteria andAdminreasonNotBetween(String value1, String value2) {
            addCriterion("adminreason not between", value1, value2, "adminreason");
            return (Criteria) this;
        }

        public Criteria andContactIsNull() {
            addCriterion("contact is null");
            return (Criteria) this;
        }

        public Criteria andContactIsNotNull() {
            addCriterion("contact is not null");
            return (Criteria) this;
        }

        public Criteria andContactEqualTo(String value) {
            addCriterion("contact =", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactNotEqualTo(String value) {
            addCriterion("contact <>", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactGreaterThan(String value) {
            addCriterion("contact >", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactGreaterThanOrEqualTo(String value) {
            addCriterion("contact >=", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactLessThan(String value) {
            addCriterion("contact <", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactLessThanOrEqualTo(String value) {
            addCriterion("contact <=", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactLike(String value) {
            addCriterion("contact like", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactNotLike(String value) {
            addCriterion("contact not like", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactIn(List<String> values) {
            addCriterion("contact in", values, "contact");
            return (Criteria) this;
        }

        public Criteria andContactNotIn(List<String> values) {
            addCriterion("contact not in", values, "contact");
            return (Criteria) this;
        }

        public Criteria andContactBetween(String value1, String value2) {
            addCriterion("contact between", value1, value2, "contact");
            return (Criteria) this;
        }

        public Criteria andContactNotBetween(String value1, String value2) {
            addCriterion("contact not between", value1, value2, "contact");
            return (Criteria) this;
        }

        public Criteria andContactphoneIsNull() {
            addCriterion("contactphone is null");
            return (Criteria) this;
        }

        public Criteria andContactphoneIsNotNull() {
            addCriterion("contactphone is not null");
            return (Criteria) this;
        }

        public Criteria andContactphoneEqualTo(String value) {
            addCriterion("contactphone =", value, "contactphone");
            return (Criteria) this;
        }

        public Criteria andContactphoneNotEqualTo(String value) {
            addCriterion("contactphone <>", value, "contactphone");
            return (Criteria) this;
        }

        public Criteria andContactphoneGreaterThan(String value) {
            addCriterion("contactphone >", value, "contactphone");
            return (Criteria) this;
        }

        public Criteria andContactphoneGreaterThanOrEqualTo(String value) {
            addCriterion("contactphone >=", value, "contactphone");
            return (Criteria) this;
        }

        public Criteria andContactphoneLessThan(String value) {
            addCriterion("contactphone <", value, "contactphone");
            return (Criteria) this;
        }

        public Criteria andContactphoneLessThanOrEqualTo(String value) {
            addCriterion("contactphone <=", value, "contactphone");
            return (Criteria) this;
        }

        public Criteria andContactphoneLike(String value) {
            addCriterion("contactphone like", value, "contactphone");
            return (Criteria) this;
        }

        public Criteria andContactphoneNotLike(String value) {
            addCriterion("contactphone not like", value, "contactphone");
            return (Criteria) this;
        }

        public Criteria andContactphoneIn(List<String> values) {
            addCriterion("contactphone in", values, "contactphone");
            return (Criteria) this;
        }

        public Criteria andContactphoneNotIn(List<String> values) {
            addCriterion("contactphone not in", values, "contactphone");
            return (Criteria) this;
        }

        public Criteria andContactphoneBetween(String value1, String value2) {
            addCriterion("contactphone between", value1, value2, "contactphone");
            return (Criteria) this;
        }

        public Criteria andContactphoneNotBetween(String value1, String value2) {
            addCriterion("contactphone not between", value1, value2, "contactphone");
            return (Criteria) this;
        }

        public Criteria andDissidencetimeIsNull() {
            addCriterion("dissidencetime is null");
            return (Criteria) this;
        }

        public Criteria andDissidencetimeIsNotNull() {
            addCriterion("dissidencetime is not null");
            return (Criteria) this;
        }

        public Criteria andDissidencetimeEqualTo(Date value) {
            addCriterion("dissidencetime =", value, "dissidencetime");
            return (Criteria) this;
        }

        public Criteria andDissidencetimeNotEqualTo(Date value) {
            addCriterion("dissidencetime <>", value, "dissidencetime");
            return (Criteria) this;
        }

        public Criteria andDissidencetimeGreaterThan(Date value) {
            addCriterion("dissidencetime >", value, "dissidencetime");
            return (Criteria) this;
        }

        public Criteria andDissidencetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("dissidencetime >=", value, "dissidencetime");
            return (Criteria) this;
        }

        public Criteria andDissidencetimeLessThan(Date value) {
            addCriterion("dissidencetime <", value, "dissidencetime");
            return (Criteria) this;
        }

        public Criteria andDissidencetimeLessThanOrEqualTo(Date value) {
            addCriterion("dissidencetime <=", value, "dissidencetime");
            return (Criteria) this;
        }

        public Criteria andDissidencetimeIn(List<Date> values) {
            addCriterion("dissidencetime in", values, "dissidencetime");
            return (Criteria) this;
        }

        public Criteria andDissidencetimeNotIn(List<Date> values) {
            addCriterion("dissidencetime not in", values, "dissidencetime");
            return (Criteria) this;
        }

        public Criteria andDissidencetimeBetween(Date value1, Date value2) {
            addCriterion("dissidencetime between", value1, value2, "dissidencetime");
            return (Criteria) this;
        }

        public Criteria andDissidencetimeNotBetween(Date value1, Date value2) {
            addCriterion("dissidencetime not between", value1, value2, "dissidencetime");
            return (Criteria) this;
        }

        public Criteria andAdminstateIsNull() {
            addCriterion("adminstate is null");
            return (Criteria) this;
        }

        public Criteria andAdminstateIsNotNull() {
            addCriterion("adminstate is not null");
            return (Criteria) this;
        }

        public Criteria andAdminstateEqualTo(Short value) {
            addCriterion("adminstate =", value, "adminstate");
            return (Criteria) this;
        }

        public Criteria andAdminstateNotEqualTo(Short value) {
            addCriterion("adminstate <>", value, "adminstate");
            return (Criteria) this;
        }

        public Criteria andAdminstateGreaterThan(Short value) {
            addCriterion("adminstate >", value, "adminstate");
            return (Criteria) this;
        }

        public Criteria andAdminstateGreaterThanOrEqualTo(Short value) {
            addCriterion("adminstate >=", value, "adminstate");
            return (Criteria) this;
        }

        public Criteria andAdminstateLessThan(Short value) {
            addCriterion("adminstate <", value, "adminstate");
            return (Criteria) this;
        }

        public Criteria andAdminstateLessThanOrEqualTo(Short value) {
            addCriterion("adminstate <=", value, "adminstate");
            return (Criteria) this;
        }

        public Criteria andAdminstateIn(List<Short> values) {
            addCriterion("adminstate in", values, "adminstate");
            return (Criteria) this;
        }

        public Criteria andAdminstateNotIn(List<Short> values) {
            addCriterion("adminstate not in", values, "adminstate");
            return (Criteria) this;
        }

        public Criteria andAdminstateBetween(Short value1, Short value2) {
            addCriterion("adminstate between", value1, value2, "adminstate");
            return (Criteria) this;
        }

        public Criteria andAdminstateNotBetween(Short value1, Short value2) {
            addCriterion("adminstate not between", value1, value2, "adminstate");
            return (Criteria) this;
        }

        public Criteria andProducttypeIsNull() {
            addCriterion("producttype is null");
            return (Criteria) this;
        }

        public Criteria andProducttypeIsNotNull() {
            addCriterion("producttype is not null");
            return (Criteria) this;
        }

        public Criteria andProducttypeEqualTo(Short value) {
            addCriterion("producttype =", value, "producttype");
            return (Criteria) this;
        }

        public Criteria andProducttypeNotEqualTo(Short value) {
            addCriterion("producttype <>", value, "producttype");
            return (Criteria) this;
        }

        public Criteria andProducttypeGreaterThan(Short value) {
            addCriterion("producttype >", value, "producttype");
            return (Criteria) this;
        }

        public Criteria andProducttypeGreaterThanOrEqualTo(Short value) {
            addCriterion("producttype >=", value, "producttype");
            return (Criteria) this;
        }

        public Criteria andProducttypeLessThan(Short value) {
            addCriterion("producttype <", value, "producttype");
            return (Criteria) this;
        }

        public Criteria andProducttypeLessThanOrEqualTo(Short value) {
            addCriterion("producttype <=", value, "producttype");
            return (Criteria) this;
        }

        public Criteria andProducttypeIn(List<Short> values) {
            addCriterion("producttype in", values, "producttype");
            return (Criteria) this;
        }

        public Criteria andProducttypeNotIn(List<Short> values) {
            addCriterion("producttype not in", values, "producttype");
            return (Criteria) this;
        }

        public Criteria andProducttypeBetween(Short value1, Short value2) {
            addCriterion("producttype between", value1, value2, "producttype");
            return (Criteria) this;
        }

        public Criteria andProducttypeNotBetween(Short value1, Short value2) {
            addCriterion("producttype not between", value1, value2, "producttype");
            return (Criteria) this;
        }

        public Criteria andLimitidIsNull() {
            addCriterion("limitid is null");
            return (Criteria) this;
        }

        public Criteria andLimitidIsNotNull() {
            addCriterion("limitid is not null");
            return (Criteria) this;
        }

        public Criteria andLimitidEqualTo(Long value) {
            addCriterion("limitid =", value, "limitid");
            return (Criteria) this;
        }

        public Criteria andLimitidNotEqualTo(Long value) {
            addCriterion("limitid <>", value, "limitid");
            return (Criteria) this;
        }

        public Criteria andLimitidGreaterThan(Long value) {
            addCriterion("limitid >", value, "limitid");
            return (Criteria) this;
        }

        public Criteria andLimitidGreaterThanOrEqualTo(Long value) {
            addCriterion("limitid >=", value, "limitid");
            return (Criteria) this;
        }

        public Criteria andLimitidLessThan(Long value) {
            addCriterion("limitid <", value, "limitid");
            return (Criteria) this;
        }

        public Criteria andLimitidLessThanOrEqualTo(Long value) {
            addCriterion("limitid <=", value, "limitid");
            return (Criteria) this;
        }

        public Criteria andLimitidIn(List<Long> values) {
            addCriterion("limitid in", values, "limitid");
            return (Criteria) this;
        }

        public Criteria andLimitidNotIn(List<Long> values) {
            addCriterion("limitid not in", values, "limitid");
            return (Criteria) this;
        }

        public Criteria andLimitidBetween(Long value1, Long value2) {
            addCriterion("limitid between", value1, value2, "limitid");
            return (Criteria) this;
        }

        public Criteria andLimitidNotBetween(Long value1, Long value2) {
            addCriterion("limitid not between", value1, value2, "limitid");
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