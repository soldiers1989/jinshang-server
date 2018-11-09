package project.jinshang.mod_product.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderProductExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderProductExample() {
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
     * @date 2018-08-16
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

        public Criteria andPddescIsNull() {
            addCriterion("pddesc is null");
            return (Criteria) this;
        }

        public Criteria andPddescIsNotNull() {
            addCriterion("pddesc is not null");
            return (Criteria) this;
        }

        public Criteria andPddescEqualTo(String value) {
            addCriterion("pddesc =", value, "pddesc");
            return (Criteria) this;
        }

        public Criteria andPddescNotEqualTo(String value) {
            addCriterion("pddesc <>", value, "pddesc");
            return (Criteria) this;
        }

        public Criteria andPddescGreaterThan(String value) {
            addCriterion("pddesc >", value, "pddesc");
            return (Criteria) this;
        }

        public Criteria andPddescGreaterThanOrEqualTo(String value) {
            addCriterion("pddesc >=", value, "pddesc");
            return (Criteria) this;
        }

        public Criteria andPddescLessThan(String value) {
            addCriterion("pddesc <", value, "pddesc");
            return (Criteria) this;
        }

        public Criteria andPddescLessThanOrEqualTo(String value) {
            addCriterion("pddesc <=", value, "pddesc");
            return (Criteria) this;
        }

        public Criteria andPddescLike(String value) {
            addCriterion("pddesc like", value, "pddesc");
            return (Criteria) this;
        }

        public Criteria andPddescNotLike(String value) {
            addCriterion("pddesc not like", value, "pddesc");
            return (Criteria) this;
        }

        public Criteria andPddescIn(List<String> values) {
            addCriterion("pddesc in", values, "pddesc");
            return (Criteria) this;
        }

        public Criteria andPddescNotIn(List<String> values) {
            addCriterion("pddesc not in", values, "pddesc");
            return (Criteria) this;
        }

        public Criteria andPddescBetween(String value1, String value2) {
            addCriterion("pddesc between", value1, value2, "pddesc");
            return (Criteria) this;
        }

        public Criteria andPddescNotBetween(String value1, String value2) {
            addCriterion("pddesc not between", value1, value2, "pddesc");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(BigDecimal value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(BigDecimal value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(BigDecimal value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(BigDecimal value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<BigDecimal> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<BigDecimal> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andUnitIsNull() {
            addCriterion("unit is null");
            return (Criteria) this;
        }

        public Criteria andUnitIsNotNull() {
            addCriterion("unit is not null");
            return (Criteria) this;
        }

        public Criteria andUnitEqualTo(String value) {
            addCriterion("unit =", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotEqualTo(String value) {
            addCriterion("unit <>", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThan(String value) {
            addCriterion("unit >", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThanOrEqualTo(String value) {
            addCriterion("unit >=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThan(String value) {
            addCriterion("unit <", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThanOrEqualTo(String value) {
            addCriterion("unit <=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLike(String value) {
            addCriterion("unit like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotLike(String value) {
            addCriterion("unit not like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitIn(List<String> values) {
            addCriterion("unit in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotIn(List<String> values) {
            addCriterion("unit not in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitBetween(String value1, String value2) {
            addCriterion("unit between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotBetween(String value1, String value2) {
            addCriterion("unit not between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andNumIsNull() {
            addCriterion("num is null");
            return (Criteria) this;
        }

        public Criteria andNumIsNotNull() {
            addCriterion("num is not null");
            return (Criteria) this;
        }

        public Criteria andNumEqualTo(BigDecimal value) {
            addCriterion("num =", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotEqualTo(BigDecimal value) {
            addCriterion("num <>", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThan(BigDecimal value) {
            addCriterion("num >", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("num >=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThan(BigDecimal value) {
            addCriterion("num <", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("num <=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumIn(List<BigDecimal> values) {
            addCriterion("num in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotIn(List<BigDecimal> values) {
            addCriterion("num not in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("num between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("num not between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andStorenameIsNull() {
            addCriterion("storename is null");
            return (Criteria) this;
        }

        public Criteria andStorenameIsNotNull() {
            addCriterion("storename is not null");
            return (Criteria) this;
        }

        public Criteria andStorenameEqualTo(String value) {
            addCriterion("storename =", value, "storename");
            return (Criteria) this;
        }

        public Criteria andStorenameNotEqualTo(String value) {
            addCriterion("storename <>", value, "storename");
            return (Criteria) this;
        }

        public Criteria andStorenameGreaterThan(String value) {
            addCriterion("storename >", value, "storename");
            return (Criteria) this;
        }

        public Criteria andStorenameGreaterThanOrEqualTo(String value) {
            addCriterion("storename >=", value, "storename");
            return (Criteria) this;
        }

        public Criteria andStorenameLessThan(String value) {
            addCriterion("storename <", value, "storename");
            return (Criteria) this;
        }

        public Criteria andStorenameLessThanOrEqualTo(String value) {
            addCriterion("storename <=", value, "storename");
            return (Criteria) this;
        }

        public Criteria andStorenameLike(String value) {
            addCriterion("storename like", value, "storename");
            return (Criteria) this;
        }

        public Criteria andStorenameNotLike(String value) {
            addCriterion("storename not like", value, "storename");
            return (Criteria) this;
        }

        public Criteria andStorenameIn(List<String> values) {
            addCriterion("storename in", values, "storename");
            return (Criteria) this;
        }

        public Criteria andStorenameNotIn(List<String> values) {
            addCriterion("storename not in", values, "storename");
            return (Criteria) this;
        }

        public Criteria andStorenameBetween(String value1, String value2) {
            addCriterion("storename between", value1, value2, "storename");
            return (Criteria) this;
        }

        public Criteria andStorenameNotBetween(String value1, String value2) {
            addCriterion("storename not between", value1, value2, "storename");
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

        public Criteria andMailornotIsNull() {
            addCriterion("mailornot is null");
            return (Criteria) this;
        }

        public Criteria andMailornotIsNotNull() {
            addCriterion("mailornot is not null");
            return (Criteria) this;
        }

        public Criteria andMailornotEqualTo(Boolean value) {
            addCriterion("mailornot =", value, "mailornot");
            return (Criteria) this;
        }

        public Criteria andMailornotNotEqualTo(Boolean value) {
            addCriterion("mailornot <>", value, "mailornot");
            return (Criteria) this;
        }

        public Criteria andMailornotGreaterThan(Boolean value) {
            addCriterion("mailornot >", value, "mailornot");
            return (Criteria) this;
        }

        public Criteria andMailornotGreaterThanOrEqualTo(Boolean value) {
            addCriterion("mailornot >=", value, "mailornot");
            return (Criteria) this;
        }

        public Criteria andMailornotLessThan(Boolean value) {
            addCriterion("mailornot <", value, "mailornot");
            return (Criteria) this;
        }

        public Criteria andMailornotLessThanOrEqualTo(Boolean value) {
            addCriterion("mailornot <=", value, "mailornot");
            return (Criteria) this;
        }

        public Criteria andMailornotIn(List<Boolean> values) {
            addCriterion("mailornot in", values, "mailornot");
            return (Criteria) this;
        }

        public Criteria andMailornotNotIn(List<Boolean> values) {
            addCriterion("mailornot not in", values, "mailornot");
            return (Criteria) this;
        }

        public Criteria andMailornotBetween(Boolean value1, Boolean value2) {
            addCriterion("mailornot between", value1, value2, "mailornot");
            return (Criteria) this;
        }

        public Criteria andMailornotNotBetween(Boolean value1, Boolean value2) {
            addCriterion("mailornot not between", value1, value2, "mailornot");
            return (Criteria) this;
        }

        public Criteria andBackstateIsNull() {
            addCriterion("backstate is null");
            return (Criteria) this;
        }

        public Criteria andBackstateIsNotNull() {
            addCriterion("backstate is not null");
            return (Criteria) this;
        }

        public Criteria andBackstateEqualTo(Short value) {
            addCriterion("backstate =", value, "backstate");
            return (Criteria) this;
        }

        public Criteria andBackstateNotEqualTo(Short value) {
            addCriterion("backstate <>", value, "backstate");
            return (Criteria) this;
        }

        public Criteria andBackstateGreaterThan(Short value) {
            addCriterion("backstate >", value, "backstate");
            return (Criteria) this;
        }

        public Criteria andBackstateGreaterThanOrEqualTo(Short value) {
            addCriterion("backstate >=", value, "backstate");
            return (Criteria) this;
        }

        public Criteria andBackstateLessThan(Short value) {
            addCriterion("backstate <", value, "backstate");
            return (Criteria) this;
        }

        public Criteria andBackstateLessThanOrEqualTo(Short value) {
            addCriterion("backstate <=", value, "backstate");
            return (Criteria) this;
        }

        public Criteria andBackstateIn(List<Short> values) {
            addCriterion("backstate in", values, "backstate");
            return (Criteria) this;
        }

        public Criteria andBackstateNotIn(List<Short> values) {
            addCriterion("backstate not in", values, "backstate");
            return (Criteria) this;
        }

        public Criteria andBackstateBetween(Short value1, Short value2) {
            addCriterion("backstate between", value1, value2, "backstate");
            return (Criteria) this;
        }

        public Criteria andBackstateNotBetween(Short value1, Short value2) {
            addCriterion("backstate not between", value1, value2, "backstate");
            return (Criteria) this;
        }

        public Criteria andStandardIsNull() {
            addCriterion("standard is null");
            return (Criteria) this;
        }

        public Criteria andStandardIsNotNull() {
            addCriterion("standard is not null");
            return (Criteria) this;
        }

        public Criteria andStandardEqualTo(String value) {
            addCriterion("standard =", value, "standard");
            return (Criteria) this;
        }

        public Criteria andStandardNotEqualTo(String value) {
            addCriterion("standard <>", value, "standard");
            return (Criteria) this;
        }

        public Criteria andStandardGreaterThan(String value) {
            addCriterion("standard >", value, "standard");
            return (Criteria) this;
        }

        public Criteria andStandardGreaterThanOrEqualTo(String value) {
            addCriterion("standard >=", value, "standard");
            return (Criteria) this;
        }

        public Criteria andStandardLessThan(String value) {
            addCriterion("standard <", value, "standard");
            return (Criteria) this;
        }

        public Criteria andStandardLessThanOrEqualTo(String value) {
            addCriterion("standard <=", value, "standard");
            return (Criteria) this;
        }

        public Criteria andStandardLike(String value) {
            addCriterion("standard like", value, "standard");
            return (Criteria) this;
        }

        public Criteria andStandardNotLike(String value) {
            addCriterion("standard not like", value, "standard");
            return (Criteria) this;
        }

        public Criteria andStandardIn(List<String> values) {
            addCriterion("standard in", values, "standard");
            return (Criteria) this;
        }

        public Criteria andStandardNotIn(List<String> values) {
            addCriterion("standard not in", values, "standard");
            return (Criteria) this;
        }

        public Criteria andStandardBetween(String value1, String value2) {
            addCriterion("standard between", value1, value2, "standard");
            return (Criteria) this;
        }

        public Criteria andStandardNotBetween(String value1, String value2) {
            addCriterion("standard not between", value1, value2, "standard");
            return (Criteria) this;
        }

        public Criteria andMarkIsNull() {
            addCriterion("mark is null");
            return (Criteria) this;
        }

        public Criteria andMarkIsNotNull() {
            addCriterion("mark is not null");
            return (Criteria) this;
        }

        public Criteria andMarkEqualTo(String value) {
            addCriterion("mark =", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkNotEqualTo(String value) {
            addCriterion("mark <>", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkGreaterThan(String value) {
            addCriterion("mark >", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkGreaterThanOrEqualTo(String value) {
            addCriterion("mark >=", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkLessThan(String value) {
            addCriterion("mark <", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkLessThanOrEqualTo(String value) {
            addCriterion("mark <=", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkLike(String value) {
            addCriterion("mark like", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkNotLike(String value) {
            addCriterion("mark not like", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkIn(List<String> values) {
            addCriterion("mark in", values, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkNotIn(List<String> values) {
            addCriterion("mark not in", values, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkBetween(String value1, String value2) {
            addCriterion("mark between", value1, value2, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkNotBetween(String value1, String value2) {
            addCriterion("mark not between", value1, value2, "mark");
            return (Criteria) this;
        }

        public Criteria andBrandIsNull() {
            addCriterion("brand is null");
            return (Criteria) this;
        }

        public Criteria andBrandIsNotNull() {
            addCriterion("brand is not null");
            return (Criteria) this;
        }

        public Criteria andBrandEqualTo(String value) {
            addCriterion("brand =", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotEqualTo(String value) {
            addCriterion("brand <>", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandGreaterThan(String value) {
            addCriterion("brand >", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandGreaterThanOrEqualTo(String value) {
            addCriterion("brand >=", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandLessThan(String value) {
            addCriterion("brand <", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandLessThanOrEqualTo(String value) {
            addCriterion("brand <=", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandLike(String value) {
            addCriterion("brand like", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotLike(String value) {
            addCriterion("brand not like", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandIn(List<String> values) {
            addCriterion("brand in", values, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotIn(List<String> values) {
            addCriterion("brand not in", values, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandBetween(String value1, String value2) {
            addCriterion("brand between", value1, value2, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotBetween(String value1, String value2) {
            addCriterion("brand not between", value1, value2, "brand");
            return (Criteria) this;
        }

        public Criteria andIsmailornotIsNull() {
            addCriterion("ismailornot is null");
            return (Criteria) this;
        }

        public Criteria andIsmailornotIsNotNull() {
            addCriterion("ismailornot is not null");
            return (Criteria) this;
        }

        public Criteria andIsmailornotEqualTo(Short value) {
            addCriterion("ismailornot =", value, "ismailornot");
            return (Criteria) this;
        }

        public Criteria andIsmailornotNotEqualTo(Short value) {
            addCriterion("ismailornot <>", value, "ismailornot");
            return (Criteria) this;
        }

        public Criteria andIsmailornotGreaterThan(Short value) {
            addCriterion("ismailornot >", value, "ismailornot");
            return (Criteria) this;
        }

        public Criteria andIsmailornotGreaterThanOrEqualTo(Short value) {
            addCriterion("ismailornot >=", value, "ismailornot");
            return (Criteria) this;
        }

        public Criteria andIsmailornotLessThan(Short value) {
            addCriterion("ismailornot <", value, "ismailornot");
            return (Criteria) this;
        }

        public Criteria andIsmailornotLessThanOrEqualTo(Short value) {
            addCriterion("ismailornot <=", value, "ismailornot");
            return (Criteria) this;
        }

        public Criteria andIsmailornotIn(List<Short> values) {
            addCriterion("ismailornot in", values, "ismailornot");
            return (Criteria) this;
        }

        public Criteria andIsmailornotNotIn(List<Short> values) {
            addCriterion("ismailornot not in", values, "ismailornot");
            return (Criteria) this;
        }

        public Criteria andIsmailornotBetween(Short value1, Short value2) {
            addCriterion("ismailornot between", value1, value2, "ismailornot");
            return (Criteria) this;
        }

        public Criteria andIsmailornotNotBetween(Short value1, Short value2) {
            addCriterion("ismailornot not between", value1, value2, "ismailornot");
            return (Criteria) this;
        }

        public Criteria andEvaluatestateIsNull() {
            addCriterion("evaluatestate is null");
            return (Criteria) this;
        }

        public Criteria andEvaluatestateIsNotNull() {
            addCriterion("evaluatestate is not null");
            return (Criteria) this;
        }

        public Criteria andEvaluatestateEqualTo(Short value) {
            addCriterion("evaluatestate =", value, "evaluatestate");
            return (Criteria) this;
        }

        public Criteria andEvaluatestateNotEqualTo(Short value) {
            addCriterion("evaluatestate <>", value, "evaluatestate");
            return (Criteria) this;
        }

        public Criteria andEvaluatestateGreaterThan(Short value) {
            addCriterion("evaluatestate >", value, "evaluatestate");
            return (Criteria) this;
        }

        public Criteria andEvaluatestateGreaterThanOrEqualTo(Short value) {
            addCriterion("evaluatestate >=", value, "evaluatestate");
            return (Criteria) this;
        }

        public Criteria andEvaluatestateLessThan(Short value) {
            addCriterion("evaluatestate <", value, "evaluatestate");
            return (Criteria) this;
        }

        public Criteria andEvaluatestateLessThanOrEqualTo(Short value) {
            addCriterion("evaluatestate <=", value, "evaluatestate");
            return (Criteria) this;
        }

        public Criteria andEvaluatestateIn(List<Short> values) {
            addCriterion("evaluatestate in", values, "evaluatestate");
            return (Criteria) this;
        }

        public Criteria andEvaluatestateNotIn(List<Short> values) {
            addCriterion("evaluatestate not in", values, "evaluatestate");
            return (Criteria) this;
        }

        public Criteria andEvaluatestateBetween(Short value1, Short value2) {
            addCriterion("evaluatestate between", value1, value2, "evaluatestate");
            return (Criteria) this;
        }

        public Criteria andEvaluatestateNotBetween(Short value1, Short value2) {
            addCriterion("evaluatestate not between", value1, value2, "evaluatestate");
            return (Criteria) this;
        }

        public Criteria andEva1IsNull() {
            addCriterion("eva1 is null");
            return (Criteria) this;
        }

        public Criteria andEva1IsNotNull() {
            addCriterion("eva1 is not null");
            return (Criteria) this;
        }

        public Criteria andEva1EqualTo(Short value) {
            addCriterion("eva1 =", value, "eva1");
            return (Criteria) this;
        }

        public Criteria andEva1NotEqualTo(Short value) {
            addCriterion("eva1 <>", value, "eva1");
            return (Criteria) this;
        }

        public Criteria andEva1GreaterThan(Short value) {
            addCriterion("eva1 >", value, "eva1");
            return (Criteria) this;
        }

        public Criteria andEva1GreaterThanOrEqualTo(Short value) {
            addCriterion("eva1 >=", value, "eva1");
            return (Criteria) this;
        }

        public Criteria andEva1LessThan(Short value) {
            addCriterion("eva1 <", value, "eva1");
            return (Criteria) this;
        }

        public Criteria andEva1LessThanOrEqualTo(Short value) {
            addCriterion("eva1 <=", value, "eva1");
            return (Criteria) this;
        }

        public Criteria andEva1In(List<Short> values) {
            addCriterion("eva1 in", values, "eva1");
            return (Criteria) this;
        }

        public Criteria andEva1NotIn(List<Short> values) {
            addCriterion("eva1 not in", values, "eva1");
            return (Criteria) this;
        }

        public Criteria andEva1Between(Short value1, Short value2) {
            addCriterion("eva1 between", value1, value2, "eva1");
            return (Criteria) this;
        }

        public Criteria andEva1NotBetween(Short value1, Short value2) {
            addCriterion("eva1 not between", value1, value2, "eva1");
            return (Criteria) this;
        }

        public Criteria andEva2IsNull() {
            addCriterion("eva2 is null");
            return (Criteria) this;
        }

        public Criteria andEva2IsNotNull() {
            addCriterion("eva2 is not null");
            return (Criteria) this;
        }

        public Criteria andEva2EqualTo(Short value) {
            addCriterion("eva2 =", value, "eva2");
            return (Criteria) this;
        }

        public Criteria andEva2NotEqualTo(Short value) {
            addCriterion("eva2 <>", value, "eva2");
            return (Criteria) this;
        }

        public Criteria andEva2GreaterThan(Short value) {
            addCriterion("eva2 >", value, "eva2");
            return (Criteria) this;
        }

        public Criteria andEva2GreaterThanOrEqualTo(Short value) {
            addCriterion("eva2 >=", value, "eva2");
            return (Criteria) this;
        }

        public Criteria andEva2LessThan(Short value) {
            addCriterion("eva2 <", value, "eva2");
            return (Criteria) this;
        }

        public Criteria andEva2LessThanOrEqualTo(Short value) {
            addCriterion("eva2 <=", value, "eva2");
            return (Criteria) this;
        }

        public Criteria andEva2In(List<Short> values) {
            addCriterion("eva2 in", values, "eva2");
            return (Criteria) this;
        }

        public Criteria andEva2NotIn(List<Short> values) {
            addCriterion("eva2 not in", values, "eva2");
            return (Criteria) this;
        }

        public Criteria andEva2Between(Short value1, Short value2) {
            addCriterion("eva2 between", value1, value2, "eva2");
            return (Criteria) this;
        }

        public Criteria andEva2NotBetween(Short value1, Short value2) {
            addCriterion("eva2 not between", value1, value2, "eva2");
            return (Criteria) this;
        }

        public Criteria andEva3IsNull() {
            addCriterion("eva3 is null");
            return (Criteria) this;
        }

        public Criteria andEva3IsNotNull() {
            addCriterion("eva3 is not null");
            return (Criteria) this;
        }

        public Criteria andEva3EqualTo(Short value) {
            addCriterion("eva3 =", value, "eva3");
            return (Criteria) this;
        }

        public Criteria andEva3NotEqualTo(Short value) {
            addCriterion("eva3 <>", value, "eva3");
            return (Criteria) this;
        }

        public Criteria andEva3GreaterThan(Short value) {
            addCriterion("eva3 >", value, "eva3");
            return (Criteria) this;
        }

        public Criteria andEva3GreaterThanOrEqualTo(Short value) {
            addCriterion("eva3 >=", value, "eva3");
            return (Criteria) this;
        }

        public Criteria andEva3LessThan(Short value) {
            addCriterion("eva3 <", value, "eva3");
            return (Criteria) this;
        }

        public Criteria andEva3LessThanOrEqualTo(Short value) {
            addCriterion("eva3 <=", value, "eva3");
            return (Criteria) this;
        }

        public Criteria andEva3In(List<Short> values) {
            addCriterion("eva3 in", values, "eva3");
            return (Criteria) this;
        }

        public Criteria andEva3NotIn(List<Short> values) {
            addCriterion("eva3 not in", values, "eva3");
            return (Criteria) this;
        }

        public Criteria andEva3Between(Short value1, Short value2) {
            addCriterion("eva3 between", value1, value2, "eva3");
            return (Criteria) this;
        }

        public Criteria andEva3NotBetween(Short value1, Short value2) {
            addCriterion("eva3 not between", value1, value2, "eva3");
            return (Criteria) this;
        }

        public Criteria andBuyersexperienceIsNull() {
            addCriterion("buyersexperience is null");
            return (Criteria) this;
        }

        public Criteria andBuyersexperienceIsNotNull() {
            addCriterion("buyersexperience is not null");
            return (Criteria) this;
        }

        public Criteria andBuyersexperienceEqualTo(String value) {
            addCriterion("buyersexperience =", value, "buyersexperience");
            return (Criteria) this;
        }

        public Criteria andBuyersexperienceNotEqualTo(String value) {
            addCriterion("buyersexperience <>", value, "buyersexperience");
            return (Criteria) this;
        }

        public Criteria andBuyersexperienceGreaterThan(String value) {
            addCriterion("buyersexperience >", value, "buyersexperience");
            return (Criteria) this;
        }

        public Criteria andBuyersexperienceGreaterThanOrEqualTo(String value) {
            addCriterion("buyersexperience >=", value, "buyersexperience");
            return (Criteria) this;
        }

        public Criteria andBuyersexperienceLessThan(String value) {
            addCriterion("buyersexperience <", value, "buyersexperience");
            return (Criteria) this;
        }

        public Criteria andBuyersexperienceLessThanOrEqualTo(String value) {
            addCriterion("buyersexperience <=", value, "buyersexperience");
            return (Criteria) this;
        }

        public Criteria andBuyersexperienceLike(String value) {
            addCriterion("buyersexperience like", value, "buyersexperience");
            return (Criteria) this;
        }

        public Criteria andBuyersexperienceNotLike(String value) {
            addCriterion("buyersexperience not like", value, "buyersexperience");
            return (Criteria) this;
        }

        public Criteria andBuyersexperienceIn(List<String> values) {
            addCriterion("buyersexperience in", values, "buyersexperience");
            return (Criteria) this;
        }

        public Criteria andBuyersexperienceNotIn(List<String> values) {
            addCriterion("buyersexperience not in", values, "buyersexperience");
            return (Criteria) this;
        }

        public Criteria andBuyersexperienceBetween(String value1, String value2) {
            addCriterion("buyersexperience between", value1, value2, "buyersexperience");
            return (Criteria) this;
        }

        public Criteria andBuyersexperienceNotBetween(String value1, String value2) {
            addCriterion("buyersexperience not between", value1, value2, "buyersexperience");
            return (Criteria) this;
        }

        public Criteria andFreightIsNull() {
            addCriterion("freight is null");
            return (Criteria) this;
        }

        public Criteria andFreightIsNotNull() {
            addCriterion("freight is not null");
            return (Criteria) this;
        }

        public Criteria andFreightEqualTo(BigDecimal value) {
            addCriterion("freight =", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightNotEqualTo(BigDecimal value) {
            addCriterion("freight <>", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightGreaterThan(BigDecimal value) {
            addCriterion("freight >", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("freight >=", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightLessThan(BigDecimal value) {
            addCriterion("freight <", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("freight <=", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightIn(List<BigDecimal> values) {
            addCriterion("freight in", values, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightNotIn(List<BigDecimal> values) {
            addCriterion("freight not in", values, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freight between", value1, value2, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freight not between", value1, value2, "freight");
            return (Criteria) this;
        }

        public Criteria andProtypeIsNull() {
            addCriterion("protype is null");
            return (Criteria) this;
        }

        public Criteria andProtypeIsNotNull() {
            addCriterion("protype is not null");
            return (Criteria) this;
        }

        public Criteria andProtypeEqualTo(Short value) {
            addCriterion("protype =", value, "protype");
            return (Criteria) this;
        }

        public Criteria andProtypeNotEqualTo(Short value) {
            addCriterion("protype <>", value, "protype");
            return (Criteria) this;
        }

        public Criteria andProtypeGreaterThan(Short value) {
            addCriterion("protype >", value, "protype");
            return (Criteria) this;
        }

        public Criteria andProtypeGreaterThanOrEqualTo(Short value) {
            addCriterion("protype >=", value, "protype");
            return (Criteria) this;
        }

        public Criteria andProtypeLessThan(Short value) {
            addCriterion("protype <", value, "protype");
            return (Criteria) this;
        }

        public Criteria andProtypeLessThanOrEqualTo(Short value) {
            addCriterion("protype <=", value, "protype");
            return (Criteria) this;
        }

        public Criteria andProtypeIn(List<Short> values) {
            addCriterion("protype in", values, "protype");
            return (Criteria) this;
        }

        public Criteria andProtypeNotIn(List<Short> values) {
            addCriterion("protype not in", values, "protype");
            return (Criteria) this;
        }

        public Criteria andProtypeBetween(Short value1, Short value2) {
            addCriterion("protype between", value1, value2, "protype");
            return (Criteria) this;
        }

        public Criteria andProtypeNotBetween(Short value1, Short value2) {
            addCriterion("protype not between", value1, value2, "protype");
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

        public Criteria andPartpayIsNull() {
            addCriterion("partpay is null");
            return (Criteria) this;
        }

        public Criteria andPartpayIsNotNull() {
            addCriterion("partpay is not null");
            return (Criteria) this;
        }

        public Criteria andPartpayEqualTo(BigDecimal value) {
            addCriterion("partpay =", value, "partpay");
            return (Criteria) this;
        }

        public Criteria andPartpayNotEqualTo(BigDecimal value) {
            addCriterion("partpay <>", value, "partpay");
            return (Criteria) this;
        }

        public Criteria andPartpayGreaterThan(BigDecimal value) {
            addCriterion("partpay >", value, "partpay");
            return (Criteria) this;
        }

        public Criteria andPartpayGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("partpay >=", value, "partpay");
            return (Criteria) this;
        }

        public Criteria andPartpayLessThan(BigDecimal value) {
            addCriterion("partpay <", value, "partpay");
            return (Criteria) this;
        }

        public Criteria andPartpayLessThanOrEqualTo(BigDecimal value) {
            addCriterion("partpay <=", value, "partpay");
            return (Criteria) this;
        }

        public Criteria andPartpayIn(List<BigDecimal> values) {
            addCriterion("partpay in", values, "partpay");
            return (Criteria) this;
        }

        public Criteria andPartpayNotIn(List<BigDecimal> values) {
            addCriterion("partpay not in", values, "partpay");
            return (Criteria) this;
        }

        public Criteria andPartpayBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("partpay between", value1, value2, "partpay");
            return (Criteria) this;
        }

        public Criteria andPartpayNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("partpay not between", value1, value2, "partpay");
            return (Criteria) this;
        }

        public Criteria andYupayIsNull() {
            addCriterion("yupay is null");
            return (Criteria) this;
        }

        public Criteria andYupayIsNotNull() {
            addCriterion("yupay is not null");
            return (Criteria) this;
        }

        public Criteria andYupayEqualTo(BigDecimal value) {
            addCriterion("yupay =", value, "yupay");
            return (Criteria) this;
        }

        public Criteria andYupayNotEqualTo(BigDecimal value) {
            addCriterion("yupay <>", value, "yupay");
            return (Criteria) this;
        }

        public Criteria andYupayGreaterThan(BigDecimal value) {
            addCriterion("yupay >", value, "yupay");
            return (Criteria) this;
        }

        public Criteria andYupayGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("yupay >=", value, "yupay");
            return (Criteria) this;
        }

        public Criteria andYupayLessThan(BigDecimal value) {
            addCriterion("yupay <", value, "yupay");
            return (Criteria) this;
        }

        public Criteria andYupayLessThanOrEqualTo(BigDecimal value) {
            addCriterion("yupay <=", value, "yupay");
            return (Criteria) this;
        }

        public Criteria andYupayIn(List<BigDecimal> values) {
            addCriterion("yupay in", values, "yupay");
            return (Criteria) this;
        }

        public Criteria andYupayNotIn(List<BigDecimal> values) {
            addCriterion("yupay not in", values, "yupay");
            return (Criteria) this;
        }

        public Criteria andYupayBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("yupay between", value1, value2, "yupay");
            return (Criteria) this;
        }

        public Criteria andYupayNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("yupay not between", value1, value2, "yupay");
            return (Criteria) this;
        }

        public Criteria andDeliverytimeIsNull() {
            addCriterion("deliverytime is null");
            return (Criteria) this;
        }

        public Criteria andDeliverytimeIsNotNull() {
            addCriterion("deliverytime is not null");
            return (Criteria) this;
        }

        public Criteria andDeliverytimeEqualTo(String value) {
            addCriterion("deliverytime =", value, "deliverytime");
            return (Criteria) this;
        }

        public Criteria andDeliverytimeNotEqualTo(String value) {
            addCriterion("deliverytime <>", value, "deliverytime");
            return (Criteria) this;
        }

        public Criteria andDeliverytimeGreaterThan(String value) {
            addCriterion("deliverytime >", value, "deliverytime");
            return (Criteria) this;
        }

        public Criteria andDeliverytimeGreaterThanOrEqualTo(String value) {
            addCriterion("deliverytime >=", value, "deliverytime");
            return (Criteria) this;
        }

        public Criteria andDeliverytimeLessThan(String value) {
            addCriterion("deliverytime <", value, "deliverytime");
            return (Criteria) this;
        }

        public Criteria andDeliverytimeLessThanOrEqualTo(String value) {
            addCriterion("deliverytime <=", value, "deliverytime");
            return (Criteria) this;
        }

        public Criteria andDeliverytimeLike(String value) {
            addCriterion("deliverytime like", value, "deliverytime");
            return (Criteria) this;
        }

        public Criteria andDeliverytimeNotLike(String value) {
            addCriterion("deliverytime not like", value, "deliverytime");
            return (Criteria) this;
        }

        public Criteria andDeliverytimeIn(List<String> values) {
            addCriterion("deliverytime in", values, "deliverytime");
            return (Criteria) this;
        }

        public Criteria andDeliverytimeNotIn(List<String> values) {
            addCriterion("deliverytime not in", values, "deliverytime");
            return (Criteria) this;
        }

        public Criteria andDeliverytimeBetween(String value1, String value2) {
            addCriterion("deliverytime between", value1, value2, "deliverytime");
            return (Criteria) this;
        }

        public Criteria andDeliverytimeNotBetween(String value1, String value2) {
            addCriterion("deliverytime not between", value1, value2, "deliverytime");
            return (Criteria) this;
        }

        public Criteria andPaystateIsNull() {
            addCriterion("paystate is null");
            return (Criteria) this;
        }

        public Criteria andPaystateIsNotNull() {
            addCriterion("paystate is not null");
            return (Criteria) this;
        }

        public Criteria andPaystateEqualTo(Short value) {
            addCriterion("paystate =", value, "paystate");
            return (Criteria) this;
        }

        public Criteria andPaystateNotEqualTo(Short value) {
            addCriterion("paystate <>", value, "paystate");
            return (Criteria) this;
        }

        public Criteria andPaystateGreaterThan(Short value) {
            addCriterion("paystate >", value, "paystate");
            return (Criteria) this;
        }

        public Criteria andPaystateGreaterThanOrEqualTo(Short value) {
            addCriterion("paystate >=", value, "paystate");
            return (Criteria) this;
        }

        public Criteria andPaystateLessThan(Short value) {
            addCriterion("paystate <", value, "paystate");
            return (Criteria) this;
        }

        public Criteria andPaystateLessThanOrEqualTo(Short value) {
            addCriterion("paystate <=", value, "paystate");
            return (Criteria) this;
        }

        public Criteria andPaystateIn(List<Short> values) {
            addCriterion("paystate in", values, "paystate");
            return (Criteria) this;
        }

        public Criteria andPaystateNotIn(List<Short> values) {
            addCriterion("paystate not in", values, "paystate");
            return (Criteria) this;
        }

        public Criteria andPaystateBetween(Short value1, Short value2) {
            addCriterion("paystate between", value1, value2, "paystate");
            return (Criteria) this;
        }

        public Criteria andPaystateNotBetween(Short value1, Short value2) {
            addCriterion("paystate not between", value1, value2, "paystate");
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

        public Criteria andClassifyIsNull() {
            addCriterion("classify is null");
            return (Criteria) this;
        }

        public Criteria andClassifyIsNotNull() {
            addCriterion("classify is not null");
            return (Criteria) this;
        }

        public Criteria andClassifyEqualTo(String value) {
            addCriterion("classify =", value, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyNotEqualTo(String value) {
            addCriterion("classify <>", value, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyGreaterThan(String value) {
            addCriterion("classify >", value, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyGreaterThanOrEqualTo(String value) {
            addCriterion("classify >=", value, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyLessThan(String value) {
            addCriterion("classify <", value, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyLessThanOrEqualTo(String value) {
            addCriterion("classify <=", value, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyLike(String value) {
            addCriterion("classify like", value, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyNotLike(String value) {
            addCriterion("classify not like", value, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyIn(List<String> values) {
            addCriterion("classify in", values, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyNotIn(List<String> values) {
            addCriterion("classify not in", values, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyBetween(String value1, String value2) {
            addCriterion("classify between", value1, value2, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyNotBetween(String value1, String value2) {
            addCriterion("classify not between", value1, value2, "classify");
            return (Criteria) this;
        }

        public Criteria andMaterialIsNull() {
            addCriterion("material is null");
            return (Criteria) this;
        }

        public Criteria andMaterialIsNotNull() {
            addCriterion("material is not null");
            return (Criteria) this;
        }

        public Criteria andMaterialEqualTo(String value) {
            addCriterion("material =", value, "material");
            return (Criteria) this;
        }

        public Criteria andMaterialNotEqualTo(String value) {
            addCriterion("material <>", value, "material");
            return (Criteria) this;
        }

        public Criteria andMaterialGreaterThan(String value) {
            addCriterion("material >", value, "material");
            return (Criteria) this;
        }

        public Criteria andMaterialGreaterThanOrEqualTo(String value) {
            addCriterion("material >=", value, "material");
            return (Criteria) this;
        }

        public Criteria andMaterialLessThan(String value) {
            addCriterion("material <", value, "material");
            return (Criteria) this;
        }

        public Criteria andMaterialLessThanOrEqualTo(String value) {
            addCriterion("material <=", value, "material");
            return (Criteria) this;
        }

        public Criteria andMaterialLike(String value) {
            addCriterion("material like", value, "material");
            return (Criteria) this;
        }

        public Criteria andMaterialNotLike(String value) {
            addCriterion("material not like", value, "material");
            return (Criteria) this;
        }

        public Criteria andMaterialIn(List<String> values) {
            addCriterion("material in", values, "material");
            return (Criteria) this;
        }

        public Criteria andMaterialNotIn(List<String> values) {
            addCriterion("material not in", values, "material");
            return (Criteria) this;
        }

        public Criteria andMaterialBetween(String value1, String value2) {
            addCriterion("material between", value1, value2, "material");
            return (Criteria) this;
        }

        public Criteria andMaterialNotBetween(String value1, String value2) {
            addCriterion("material not between", value1, value2, "material");
            return (Criteria) this;
        }

        public Criteria andGradenoIsNull() {
            addCriterion("gradeno is null");
            return (Criteria) this;
        }

        public Criteria andGradenoIsNotNull() {
            addCriterion("gradeno is not null");
            return (Criteria) this;
        }

        public Criteria andGradenoEqualTo(String value) {
            addCriterion("gradeno =", value, "gradeno");
            return (Criteria) this;
        }

        public Criteria andGradenoNotEqualTo(String value) {
            addCriterion("gradeno <>", value, "gradeno");
            return (Criteria) this;
        }

        public Criteria andGradenoGreaterThan(String value) {
            addCriterion("gradeno >", value, "gradeno");
            return (Criteria) this;
        }

        public Criteria andGradenoGreaterThanOrEqualTo(String value) {
            addCriterion("gradeno >=", value, "gradeno");
            return (Criteria) this;
        }

        public Criteria andGradenoLessThan(String value) {
            addCriterion("gradeno <", value, "gradeno");
            return (Criteria) this;
        }

        public Criteria andGradenoLessThanOrEqualTo(String value) {
            addCriterion("gradeno <=", value, "gradeno");
            return (Criteria) this;
        }

        public Criteria andGradenoLike(String value) {
            addCriterion("gradeno like", value, "gradeno");
            return (Criteria) this;
        }

        public Criteria andGradenoNotLike(String value) {
            addCriterion("gradeno not like", value, "gradeno");
            return (Criteria) this;
        }

        public Criteria andGradenoIn(List<String> values) {
            addCriterion("gradeno in", values, "gradeno");
            return (Criteria) this;
        }

        public Criteria andGradenoNotIn(List<String> values) {
            addCriterion("gradeno not in", values, "gradeno");
            return (Criteria) this;
        }

        public Criteria andGradenoBetween(String value1, String value2) {
            addCriterion("gradeno between", value1, value2, "gradeno");
            return (Criteria) this;
        }

        public Criteria andGradenoNotBetween(String value1, String value2) {
            addCriterion("gradeno not between", value1, value2, "gradeno");
            return (Criteria) this;
        }

        public Criteria andActualpaymentIsNull() {
            addCriterion("actualpayment is null");
            return (Criteria) this;
        }

        public Criteria andActualpaymentIsNotNull() {
            addCriterion("actualpayment is not null");
            return (Criteria) this;
        }

        public Criteria andActualpaymentEqualTo(BigDecimal value) {
            addCriterion("actualpayment =", value, "actualpayment");
            return (Criteria) this;
        }

        public Criteria andActualpaymentNotEqualTo(BigDecimal value) {
            addCriterion("actualpayment <>", value, "actualpayment");
            return (Criteria) this;
        }

        public Criteria andActualpaymentGreaterThan(BigDecimal value) {
            addCriterion("actualpayment >", value, "actualpayment");
            return (Criteria) this;
        }

        public Criteria andActualpaymentGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("actualpayment >=", value, "actualpayment");
            return (Criteria) this;
        }

        public Criteria andActualpaymentLessThan(BigDecimal value) {
            addCriterion("actualpayment <", value, "actualpayment");
            return (Criteria) this;
        }

        public Criteria andActualpaymentLessThanOrEqualTo(BigDecimal value) {
            addCriterion("actualpayment <=", value, "actualpayment");
            return (Criteria) this;
        }

        public Criteria andActualpaymentIn(List<BigDecimal> values) {
            addCriterion("actualpayment in", values, "actualpayment");
            return (Criteria) this;
        }

        public Criteria andActualpaymentNotIn(List<BigDecimal> values) {
            addCriterion("actualpayment not in", values, "actualpayment");
            return (Criteria) this;
        }

        public Criteria andActualpaymentBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("actualpayment between", value1, value2, "actualpayment");
            return (Criteria) this;
        }

        public Criteria andActualpaymentNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("actualpayment not between", value1, value2, "actualpayment");
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

        public Criteria andPdpicIsNull() {
            addCriterion("pdpic is null");
            return (Criteria) this;
        }

        public Criteria andPdpicIsNotNull() {
            addCriterion("pdpic is not null");
            return (Criteria) this;
        }

        public Criteria andPdpicEqualTo(String value) {
            addCriterion("pdpic =", value, "pdpic");
            return (Criteria) this;
        }

        public Criteria andPdpicNotEqualTo(String value) {
            addCriterion("pdpic <>", value, "pdpic");
            return (Criteria) this;
        }

        public Criteria andPdpicGreaterThan(String value) {
            addCriterion("pdpic >", value, "pdpic");
            return (Criteria) this;
        }

        public Criteria andPdpicGreaterThanOrEqualTo(String value) {
            addCriterion("pdpic >=", value, "pdpic");
            return (Criteria) this;
        }

        public Criteria andPdpicLessThan(String value) {
            addCriterion("pdpic <", value, "pdpic");
            return (Criteria) this;
        }

        public Criteria andPdpicLessThanOrEqualTo(String value) {
            addCriterion("pdpic <=", value, "pdpic");
            return (Criteria) this;
        }

        public Criteria andPdpicLike(String value) {
            addCriterion("pdpic like", value, "pdpic");
            return (Criteria) this;
        }

        public Criteria andPdpicNotLike(String value) {
            addCriterion("pdpic not like", value, "pdpic");
            return (Criteria) this;
        }

        public Criteria andPdpicIn(List<String> values) {
            addCriterion("pdpic in", values, "pdpic");
            return (Criteria) this;
        }

        public Criteria andPdpicNotIn(List<String> values) {
            addCriterion("pdpic not in", values, "pdpic");
            return (Criteria) this;
        }

        public Criteria andPdpicBetween(String value1, String value2) {
            addCriterion("pdpic between", value1, value2, "pdpic");
            return (Criteria) this;
        }

        public Criteria andPdpicNotBetween(String value1, String value2) {
            addCriterion("pdpic not between", value1, value2, "pdpic");
            return (Criteria) this;
        }

        public Criteria andEvatimeIsNull() {
            addCriterion("evatime is null");
            return (Criteria) this;
        }

        public Criteria andEvatimeIsNotNull() {
            addCriterion("evatime is not null");
            return (Criteria) this;
        }

        public Criteria andEvatimeEqualTo(Date value) {
            addCriterion("evatime =", value, "evatime");
            return (Criteria) this;
        }

        public Criteria andEvatimeNotEqualTo(Date value) {
            addCriterion("evatime <>", value, "evatime");
            return (Criteria) this;
        }

        public Criteria andEvatimeGreaterThan(Date value) {
            addCriterion("evatime >", value, "evatime");
            return (Criteria) this;
        }

        public Criteria andEvatimeGreaterThanOrEqualTo(Date value) {
            addCriterion("evatime >=", value, "evatime");
            return (Criteria) this;
        }

        public Criteria andEvatimeLessThan(Date value) {
            addCriterion("evatime <", value, "evatime");
            return (Criteria) this;
        }

        public Criteria andEvatimeLessThanOrEqualTo(Date value) {
            addCriterion("evatime <=", value, "evatime");
            return (Criteria) this;
        }

        public Criteria andEvatimeIn(List<Date> values) {
            addCriterion("evatime in", values, "evatime");
            return (Criteria) this;
        }

        public Criteria andEvatimeNotIn(List<Date> values) {
            addCriterion("evatime not in", values, "evatime");
            return (Criteria) this;
        }

        public Criteria andEvatimeBetween(Date value1, Date value2) {
            addCriterion("evatime between", value1, value2, "evatime");
            return (Criteria) this;
        }

        public Criteria andEvatimeNotBetween(Date value1, Date value2) {
            addCriterion("evatime not between", value1, value2, "evatime");
            return (Criteria) this;
        }

        public Criteria andSellernameIsNull() {
            addCriterion("sellername is null");
            return (Criteria) this;
        }

        public Criteria andSellernameIsNotNull() {
            addCriterion("sellername is not null");
            return (Criteria) this;
        }

        public Criteria andSellernameEqualTo(String value) {
            addCriterion("sellername =", value, "sellername");
            return (Criteria) this;
        }

        public Criteria andSellernameNotEqualTo(String value) {
            addCriterion("sellername <>", value, "sellername");
            return (Criteria) this;
        }

        public Criteria andSellernameGreaterThan(String value) {
            addCriterion("sellername >", value, "sellername");
            return (Criteria) this;
        }

        public Criteria andSellernameGreaterThanOrEqualTo(String value) {
            addCriterion("sellername >=", value, "sellername");
            return (Criteria) this;
        }

        public Criteria andSellernameLessThan(String value) {
            addCriterion("sellername <", value, "sellername");
            return (Criteria) this;
        }

        public Criteria andSellernameLessThanOrEqualTo(String value) {
            addCriterion("sellername <=", value, "sellername");
            return (Criteria) this;
        }

        public Criteria andSellernameLike(String value) {
            addCriterion("sellername like", value, "sellername");
            return (Criteria) this;
        }

        public Criteria andSellernameNotLike(String value) {
            addCriterion("sellername not like", value, "sellername");
            return (Criteria) this;
        }

        public Criteria andSellernameIn(List<String> values) {
            addCriterion("sellername in", values, "sellername");
            return (Criteria) this;
        }

        public Criteria andSellernameNotIn(List<String> values) {
            addCriterion("sellername not in", values, "sellername");
            return (Criteria) this;
        }

        public Criteria andSellernameBetween(String value1, String value2) {
            addCriterion("sellername between", value1, value2, "sellername");
            return (Criteria) this;
        }

        public Criteria andSellernameNotBetween(String value1, String value2) {
            addCriterion("sellername not between", value1, value2, "sellername");
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

        public Criteria andIsanonymousIsNull() {
            addCriterion("isanonymous is null");
            return (Criteria) this;
        }

        public Criteria andIsanonymousIsNotNull() {
            addCriterion("isanonymous is not null");
            return (Criteria) this;
        }

        public Criteria andIsanonymousEqualTo(Short value) {
            addCriterion("isanonymous =", value, "isanonymous");
            return (Criteria) this;
        }

        public Criteria andIsanonymousNotEqualTo(Short value) {
            addCriterion("isanonymous <>", value, "isanonymous");
            return (Criteria) this;
        }

        public Criteria andIsanonymousGreaterThan(Short value) {
            addCriterion("isanonymous >", value, "isanonymous");
            return (Criteria) this;
        }

        public Criteria andIsanonymousGreaterThanOrEqualTo(Short value) {
            addCriterion("isanonymous >=", value, "isanonymous");
            return (Criteria) this;
        }

        public Criteria andIsanonymousLessThan(Short value) {
            addCriterion("isanonymous <", value, "isanonymous");
            return (Criteria) this;
        }

        public Criteria andIsanonymousLessThanOrEqualTo(Short value) {
            addCriterion("isanonymous <=", value, "isanonymous");
            return (Criteria) this;
        }

        public Criteria andIsanonymousIn(List<Short> values) {
            addCriterion("isanonymous in", values, "isanonymous");
            return (Criteria) this;
        }

        public Criteria andIsanonymousNotIn(List<Short> values) {
            addCriterion("isanonymous not in", values, "isanonymous");
            return (Criteria) this;
        }

        public Criteria andIsanonymousBetween(Short value1, Short value2) {
            addCriterion("isanonymous between", value1, value2, "isanonymous");
            return (Criteria) this;
        }

        public Criteria andIsanonymousNotBetween(Short value1, Short value2) {
            addCriterion("isanonymous not between", value1, value2, "isanonymous");
            return (Criteria) this;
        }

        public Criteria andAttrjsonIsNull() {
            addCriterion("attrjson is null");
            return (Criteria) this;
        }

        public Criteria andAttrjsonIsNotNull() {
            addCriterion("attrjson is not null");
            return (Criteria) this;
        }

        public Criteria andAttrjsonEqualTo(String value) {
            addCriterion("attrjson =", value, "attrjson");
            return (Criteria) this;
        }

        public Criteria andAttrjsonNotEqualTo(String value) {
            addCriterion("attrjson <>", value, "attrjson");
            return (Criteria) this;
        }

        public Criteria andAttrjsonGreaterThan(String value) {
            addCriterion("attrjson >", value, "attrjson");
            return (Criteria) this;
        }

        public Criteria andAttrjsonGreaterThanOrEqualTo(String value) {
            addCriterion("attrjson >=", value, "attrjson");
            return (Criteria) this;
        }

        public Criteria andAttrjsonLessThan(String value) {
            addCriterion("attrjson <", value, "attrjson");
            return (Criteria) this;
        }

        public Criteria andAttrjsonLessThanOrEqualTo(String value) {
            addCriterion("attrjson <=", value, "attrjson");
            return (Criteria) this;
        }

        public Criteria andAttrjsonLike(String value) {
            addCriterion("attrjson like", value, "attrjson");
            return (Criteria) this;
        }

        public Criteria andAttrjsonNotLike(String value) {
            addCriterion("attrjson not like", value, "attrjson");
            return (Criteria) this;
        }

        public Criteria andAttrjsonIn(List<String> values) {
            addCriterion("attrjson in", values, "attrjson");
            return (Criteria) this;
        }

        public Criteria andAttrjsonNotIn(List<String> values) {
            addCriterion("attrjson not in", values, "attrjson");
            return (Criteria) this;
        }

        public Criteria andAttrjsonBetween(String value1, String value2) {
            addCriterion("attrjson between", value1, value2, "attrjson");
            return (Criteria) this;
        }

        public Criteria andAttrjsonNotBetween(String value1, String value2) {
            addCriterion("attrjson not between", value1, value2, "attrjson");
            return (Criteria) this;
        }

        public Criteria andViewnumIsNull() {
            addCriterion("viewnum is null");
            return (Criteria) this;
        }

        public Criteria andViewnumIsNotNull() {
            addCriterion("viewnum is not null");
            return (Criteria) this;
        }

        public Criteria andViewnumEqualTo(BigDecimal value) {
            addCriterion("viewnum =", value, "viewnum");
            return (Criteria) this;
        }

        public Criteria andViewnumNotEqualTo(BigDecimal value) {
            addCriterion("viewnum <>", value, "viewnum");
            return (Criteria) this;
        }

        public Criteria andViewnumGreaterThan(BigDecimal value) {
            addCriterion("viewnum >", value, "viewnum");
            return (Criteria) this;
        }

        public Criteria andViewnumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("viewnum >=", value, "viewnum");
            return (Criteria) this;
        }

        public Criteria andViewnumLessThan(BigDecimal value) {
            addCriterion("viewnum <", value, "viewnum");
            return (Criteria) this;
        }

        public Criteria andViewnumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("viewnum <=", value, "viewnum");
            return (Criteria) this;
        }

        public Criteria andViewnumIn(List<BigDecimal> values) {
            addCriterion("viewnum in", values, "viewnum");
            return (Criteria) this;
        }

        public Criteria andViewnumNotIn(List<BigDecimal> values) {
            addCriterion("viewnum not in", values, "viewnum");
            return (Criteria) this;
        }

        public Criteria andViewnumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("viewnum between", value1, value2, "viewnum");
            return (Criteria) this;
        }

        public Criteria andViewnumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("viewnum not between", value1, value2, "viewnum");
            return (Criteria) this;
        }

        public Criteria andViewunitIsNull() {
            addCriterion("viewunit is null");
            return (Criteria) this;
        }

        public Criteria andViewunitIsNotNull() {
            addCriterion("viewunit is not null");
            return (Criteria) this;
        }

        public Criteria andViewunitEqualTo(String value) {
            addCriterion("viewunit =", value, "viewunit");
            return (Criteria) this;
        }

        public Criteria andViewunitNotEqualTo(String value) {
            addCriterion("viewunit <>", value, "viewunit");
            return (Criteria) this;
        }

        public Criteria andViewunitGreaterThan(String value) {
            addCriterion("viewunit >", value, "viewunit");
            return (Criteria) this;
        }

        public Criteria andViewunitGreaterThanOrEqualTo(String value) {
            addCriterion("viewunit >=", value, "viewunit");
            return (Criteria) this;
        }

        public Criteria andViewunitLessThan(String value) {
            addCriterion("viewunit <", value, "viewunit");
            return (Criteria) this;
        }

        public Criteria andViewunitLessThanOrEqualTo(String value) {
            addCriterion("viewunit <=", value, "viewunit");
            return (Criteria) this;
        }

        public Criteria andViewunitLike(String value) {
            addCriterion("viewunit like", value, "viewunit");
            return (Criteria) this;
        }

        public Criteria andViewunitNotLike(String value) {
            addCriterion("viewunit not like", value, "viewunit");
            return (Criteria) this;
        }

        public Criteria andViewunitIn(List<String> values) {
            addCriterion("viewunit in", values, "viewunit");
            return (Criteria) this;
        }

        public Criteria andViewunitNotIn(List<String> values) {
            addCriterion("viewunit not in", values, "viewunit");
            return (Criteria) this;
        }

        public Criteria andViewunitBetween(String value1, String value2) {
            addCriterion("viewunit between", value1, value2, "viewunit");
            return (Criteria) this;
        }

        public Criteria andViewunitNotBetween(String value1, String value2) {
            addCriterion("viewunit not between", value1, value2, "viewunit");
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

        public Criteria andClassifyidIsNull() {
            addCriterion("classifyid is null");
            return (Criteria) this;
        }

        public Criteria andClassifyidIsNotNull() {
            addCriterion("classifyid is not null");
            return (Criteria) this;
        }

        public Criteria andClassifyidEqualTo(Long value) {
            addCriterion("classifyid =", value, "classifyid");
            return (Criteria) this;
        }

        public Criteria andClassifyidNotEqualTo(Long value) {
            addCriterion("classifyid <>", value, "classifyid");
            return (Criteria) this;
        }

        public Criteria andClassifyidGreaterThan(Long value) {
            addCriterion("classifyid >", value, "classifyid");
            return (Criteria) this;
        }

        public Criteria andClassifyidGreaterThanOrEqualTo(Long value) {
            addCriterion("classifyid >=", value, "classifyid");
            return (Criteria) this;
        }

        public Criteria andClassifyidLessThan(Long value) {
            addCriterion("classifyid <", value, "classifyid");
            return (Criteria) this;
        }

        public Criteria andClassifyidLessThanOrEqualTo(Long value) {
            addCriterion("classifyid <=", value, "classifyid");
            return (Criteria) this;
        }

        public Criteria andClassifyidIn(List<Long> values) {
            addCriterion("classifyid in", values, "classifyid");
            return (Criteria) this;
        }

        public Criteria andClassifyidNotIn(List<Long> values) {
            addCriterion("classifyid not in", values, "classifyid");
            return (Criteria) this;
        }

        public Criteria andClassifyidBetween(Long value1, Long value2) {
            addCriterion("classifyid between", value1, value2, "classifyid");
            return (Criteria) this;
        }

        public Criteria andClassifyidNotBetween(Long value1, Long value2) {
            addCriterion("classifyid not between", value1, value2, "classifyid");
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

        public Criteria andBrokepayIsNull() {
            addCriterion("brokepay is null");
            return (Criteria) this;
        }

        public Criteria andBrokepayIsNotNull() {
            addCriterion("brokepay is not null");
            return (Criteria) this;
        }

        public Criteria andBrokepayEqualTo(BigDecimal value) {
            addCriterion("brokepay =", value, "brokepay");
            return (Criteria) this;
        }

        public Criteria andBrokepayNotEqualTo(BigDecimal value) {
            addCriterion("brokepay <>", value, "brokepay");
            return (Criteria) this;
        }

        public Criteria andBrokepayGreaterThan(BigDecimal value) {
            addCriterion("brokepay >", value, "brokepay");
            return (Criteria) this;
        }

        public Criteria andBrokepayGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("brokepay >=", value, "brokepay");
            return (Criteria) this;
        }

        public Criteria andBrokepayLessThan(BigDecimal value) {
            addCriterion("brokepay <", value, "brokepay");
            return (Criteria) this;
        }

        public Criteria andBrokepayLessThanOrEqualTo(BigDecimal value) {
            addCriterion("brokepay <=", value, "brokepay");
            return (Criteria) this;
        }

        public Criteria andBrokepayIn(List<BigDecimal> values) {
            addCriterion("brokepay in", values, "brokepay");
            return (Criteria) this;
        }

        public Criteria andBrokepayNotIn(List<BigDecimal> values) {
            addCriterion("brokepay not in", values, "brokepay");
            return (Criteria) this;
        }

        public Criteria andBrokepayBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("brokepay between", value1, value2, "brokepay");
            return (Criteria) this;
        }

        public Criteria andBrokepayNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("brokepay not between", value1, value2, "brokepay");
            return (Criteria) this;
        }

        public Criteria andSinglebrokepayIsNull() {
            addCriterion("singlebrokepay is null");
            return (Criteria) this;
        }

        public Criteria andSinglebrokepayIsNotNull() {
            addCriterion("singlebrokepay is not null");
            return (Criteria) this;
        }

        public Criteria andSinglebrokepayEqualTo(BigDecimal value) {
            addCriterion("singlebrokepay =", value, "singlebrokepay");
            return (Criteria) this;
        }

        public Criteria andSinglebrokepayNotEqualTo(BigDecimal value) {
            addCriterion("singlebrokepay <>", value, "singlebrokepay");
            return (Criteria) this;
        }

        public Criteria andSinglebrokepayGreaterThan(BigDecimal value) {
            addCriterion("singlebrokepay >", value, "singlebrokepay");
            return (Criteria) this;
        }

        public Criteria andSinglebrokepayGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("singlebrokepay >=", value, "singlebrokepay");
            return (Criteria) this;
        }

        public Criteria andSinglebrokepayLessThan(BigDecimal value) {
            addCriterion("singlebrokepay <", value, "singlebrokepay");
            return (Criteria) this;
        }

        public Criteria andSinglebrokepayLessThanOrEqualTo(BigDecimal value) {
            addCriterion("singlebrokepay <=", value, "singlebrokepay");
            return (Criteria) this;
        }

        public Criteria andSinglebrokepayIn(List<BigDecimal> values) {
            addCriterion("singlebrokepay in", values, "singlebrokepay");
            return (Criteria) this;
        }

        public Criteria andSinglebrokepayNotIn(List<BigDecimal> values) {
            addCriterion("singlebrokepay not in", values, "singlebrokepay");
            return (Criteria) this;
        }

        public Criteria andSinglebrokepayBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("singlebrokepay between", value1, value2, "singlebrokepay");
            return (Criteria) this;
        }

        public Criteria andSinglebrokepayNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("singlebrokepay not between", value1, value2, "singlebrokepay");
            return (Criteria) this;
        }

        public Criteria andDeliveryidIsNull() {
            addCriterion("deliveryid is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryidIsNotNull() {
            addCriterion("deliveryid is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryidEqualTo(Long value) {
            addCriterion("deliveryid =", value, "deliveryid");
            return (Criteria) this;
        }

        public Criteria andDeliveryidNotEqualTo(Long value) {
            addCriterion("deliveryid <>", value, "deliveryid");
            return (Criteria) this;
        }

        public Criteria andDeliveryidGreaterThan(Long value) {
            addCriterion("deliveryid >", value, "deliveryid");
            return (Criteria) this;
        }

        public Criteria andDeliveryidGreaterThanOrEqualTo(Long value) {
            addCriterion("deliveryid >=", value, "deliveryid");
            return (Criteria) this;
        }

        public Criteria andDeliveryidLessThan(Long value) {
            addCriterion("deliveryid <", value, "deliveryid");
            return (Criteria) this;
        }

        public Criteria andDeliveryidLessThanOrEqualTo(Long value) {
            addCriterion("deliveryid <=", value, "deliveryid");
            return (Criteria) this;
        }

        public Criteria andDeliveryidIn(List<Long> values) {
            addCriterion("deliveryid in", values, "deliveryid");
            return (Criteria) this;
        }

        public Criteria andDeliveryidNotIn(List<Long> values) {
            addCriterion("deliveryid not in", values, "deliveryid");
            return (Criteria) this;
        }

        public Criteria andDeliveryidBetween(Long value1, Long value2) {
            addCriterion("deliveryid between", value1, value2, "deliveryid");
            return (Criteria) this;
        }

        public Criteria andDeliveryidNotBetween(Long value1, Long value2) {
            addCriterion("deliveryid not between", value1, value2, "deliveryid");
            return (Criteria) this;
        }

        public Criteria andDiscountpayIsNull() {
            addCriterion("discountpay is null");
            return (Criteria) this;
        }

        public Criteria andDiscountpayIsNotNull() {
            addCriterion("discountpay is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountpayEqualTo(BigDecimal value) {
            addCriterion("discountpay =", value, "discountpay");
            return (Criteria) this;
        }

        public Criteria andDiscountpayNotEqualTo(BigDecimal value) {
            addCriterion("discountpay <>", value, "discountpay");
            return (Criteria) this;
        }

        public Criteria andDiscountpayGreaterThan(BigDecimal value) {
            addCriterion("discountpay >", value, "discountpay");
            return (Criteria) this;
        }

        public Criteria andDiscountpayGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("discountpay >=", value, "discountpay");
            return (Criteria) this;
        }

        public Criteria andDiscountpayLessThan(BigDecimal value) {
            addCriterion("discountpay <", value, "discountpay");
            return (Criteria) this;
        }

        public Criteria andDiscountpayLessThanOrEqualTo(BigDecimal value) {
            addCriterion("discountpay <=", value, "discountpay");
            return (Criteria) this;
        }

        public Criteria andDiscountpayIn(List<BigDecimal> values) {
            addCriterion("discountpay in", values, "discountpay");
            return (Criteria) this;
        }

        public Criteria andDiscountpayNotIn(List<BigDecimal> values) {
            addCriterion("discountpay not in", values, "discountpay");
            return (Criteria) this;
        }

        public Criteria andDiscountpayBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("discountpay between", value1, value2, "discountpay");
            return (Criteria) this;
        }

        public Criteria andDiscountpayNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("discountpay not between", value1, value2, "discountpay");
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
     * @date 2018-08-16
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