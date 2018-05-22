package project.jinshang.mod_product.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductStoreExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProductStoreExample() {
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

        public Criteria andStepwisepriceIsNull() {
            addCriterion("stepwiseprice is null");
            return (Criteria) this;
        }

        public Criteria andStepwisepriceIsNotNull() {
            addCriterion("stepwiseprice is not null");
            return (Criteria) this;
        }

        public Criteria andStepwisepriceEqualTo(Boolean value) {
            addCriterion("stepwiseprice =", value, "stepwiseprice");
            return (Criteria) this;
        }

        public Criteria andStepwisepriceNotEqualTo(Boolean value) {
            addCriterion("stepwiseprice <>", value, "stepwiseprice");
            return (Criteria) this;
        }

        public Criteria andStepwisepriceGreaterThan(Boolean value) {
            addCriterion("stepwiseprice >", value, "stepwiseprice");
            return (Criteria) this;
        }

        public Criteria andStepwisepriceGreaterThanOrEqualTo(Boolean value) {
            addCriterion("stepwiseprice >=", value, "stepwiseprice");
            return (Criteria) this;
        }

        public Criteria andStepwisepriceLessThan(Boolean value) {
            addCriterion("stepwiseprice <", value, "stepwiseprice");
            return (Criteria) this;
        }

        public Criteria andStepwisepriceLessThanOrEqualTo(Boolean value) {
            addCriterion("stepwiseprice <=", value, "stepwiseprice");
            return (Criteria) this;
        }

        public Criteria andStepwisepriceIn(List<Boolean> values) {
            addCriterion("stepwiseprice in", values, "stepwiseprice");
            return (Criteria) this;
        }

        public Criteria andStepwisepriceNotIn(List<Boolean> values) {
            addCriterion("stepwiseprice not in", values, "stepwiseprice");
            return (Criteria) this;
        }

        public Criteria andStepwisepriceBetween(Boolean value1, Boolean value2) {
            addCriterion("stepwiseprice between", value1, value2, "stepwiseprice");
            return (Criteria) this;
        }

        public Criteria andStepwisepriceNotBetween(Boolean value1, Boolean value2) {
            addCriterion("stepwiseprice not between", value1, value2, "stepwiseprice");
            return (Criteria) this;
        }

        public Criteria andProdpriceIsNull() {
            addCriterion("prodprice is null");
            return (Criteria) this;
        }

        public Criteria andProdpriceIsNotNull() {
            addCriterion("prodprice is not null");
            return (Criteria) this;
        }

        public Criteria andProdpriceEqualTo(BigDecimal value) {
            addCriterion("prodprice =", value, "prodprice");
            return (Criteria) this;
        }

        public Criteria andProdpriceNotEqualTo(BigDecimal value) {
            addCriterion("prodprice <>", value, "prodprice");
            return (Criteria) this;
        }

        public Criteria andProdpriceGreaterThan(BigDecimal value) {
            addCriterion("prodprice >", value, "prodprice");
            return (Criteria) this;
        }

        public Criteria andProdpriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("prodprice >=", value, "prodprice");
            return (Criteria) this;
        }

        public Criteria andProdpriceLessThan(BigDecimal value) {
            addCriterion("prodprice <", value, "prodprice");
            return (Criteria) this;
        }

        public Criteria andProdpriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("prodprice <=", value, "prodprice");
            return (Criteria) this;
        }

        public Criteria andProdpriceIn(List<BigDecimal> values) {
            addCriterion("prodprice in", values, "prodprice");
            return (Criteria) this;
        }

        public Criteria andProdpriceNotIn(List<BigDecimal> values) {
            addCriterion("prodprice not in", values, "prodprice");
            return (Criteria) this;
        }

        public Criteria andProdpriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("prodprice between", value1, value2, "prodprice");
            return (Criteria) this;
        }

        public Criteria andProdpriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("prodprice not between", value1, value2, "prodprice");
            return (Criteria) this;
        }

        public Criteria andThreepriceIsNull() {
            addCriterion("threeprice is null");
            return (Criteria) this;
        }

        public Criteria andThreepriceIsNotNull() {
            addCriterion("threeprice is not null");
            return (Criteria) this;
        }

        public Criteria andThreepriceEqualTo(BigDecimal value) {
            addCriterion("threeprice =", value, "threeprice");
            return (Criteria) this;
        }

        public Criteria andThreepriceNotEqualTo(BigDecimal value) {
            addCriterion("threeprice <>", value, "threeprice");
            return (Criteria) this;
        }

        public Criteria andThreepriceGreaterThan(BigDecimal value) {
            addCriterion("threeprice >", value, "threeprice");
            return (Criteria) this;
        }

        public Criteria andThreepriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("threeprice >=", value, "threeprice");
            return (Criteria) this;
        }

        public Criteria andThreepriceLessThan(BigDecimal value) {
            addCriterion("threeprice <", value, "threeprice");
            return (Criteria) this;
        }

        public Criteria andThreepriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("threeprice <=", value, "threeprice");
            return (Criteria) this;
        }

        public Criteria andThreepriceIn(List<BigDecimal> values) {
            addCriterion("threeprice in", values, "threeprice");
            return (Criteria) this;
        }

        public Criteria andThreepriceNotIn(List<BigDecimal> values) {
            addCriterion("threeprice not in", values, "threeprice");
            return (Criteria) this;
        }

        public Criteria andThreepriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("threeprice between", value1, value2, "threeprice");
            return (Criteria) this;
        }

        public Criteria andThreepriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("threeprice not between", value1, value2, "threeprice");
            return (Criteria) this;
        }

        public Criteria andNinetypriceIsNull() {
            addCriterion("ninetyprice is null");
            return (Criteria) this;
        }

        public Criteria andNinetypriceIsNotNull() {
            addCriterion("ninetyprice is not null");
            return (Criteria) this;
        }

        public Criteria andNinetypriceEqualTo(BigDecimal value) {
            addCriterion("ninetyprice =", value, "ninetyprice");
            return (Criteria) this;
        }

        public Criteria andNinetypriceNotEqualTo(BigDecimal value) {
            addCriterion("ninetyprice <>", value, "ninetyprice");
            return (Criteria) this;
        }

        public Criteria andNinetypriceGreaterThan(BigDecimal value) {
            addCriterion("ninetyprice >", value, "ninetyprice");
            return (Criteria) this;
        }

        public Criteria andNinetypriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ninetyprice >=", value, "ninetyprice");
            return (Criteria) this;
        }

        public Criteria andNinetypriceLessThan(BigDecimal value) {
            addCriterion("ninetyprice <", value, "ninetyprice");
            return (Criteria) this;
        }

        public Criteria andNinetypriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ninetyprice <=", value, "ninetyprice");
            return (Criteria) this;
        }

        public Criteria andNinetypriceIn(List<BigDecimal> values) {
            addCriterion("ninetyprice in", values, "ninetyprice");
            return (Criteria) this;
        }

        public Criteria andNinetypriceNotIn(List<BigDecimal> values) {
            addCriterion("ninetyprice not in", values, "ninetyprice");
            return (Criteria) this;
        }

        public Criteria andNinetypriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ninetyprice between", value1, value2, "ninetyprice");
            return (Criteria) this;
        }

        public Criteria andNinetypriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ninetyprice not between", value1, value2, "ninetyprice");
            return (Criteria) this;
        }

        public Criteria andThirtypriceIsNull() {
            addCriterion("thirtyprice is null");
            return (Criteria) this;
        }

        public Criteria andThirtypriceIsNotNull() {
            addCriterion("thirtyprice is not null");
            return (Criteria) this;
        }

        public Criteria andThirtypriceEqualTo(BigDecimal value) {
            addCriterion("thirtyprice =", value, "thirtyprice");
            return (Criteria) this;
        }

        public Criteria andThirtypriceNotEqualTo(BigDecimal value) {
            addCriterion("thirtyprice <>", value, "thirtyprice");
            return (Criteria) this;
        }

        public Criteria andThirtypriceGreaterThan(BigDecimal value) {
            addCriterion("thirtyprice >", value, "thirtyprice");
            return (Criteria) this;
        }

        public Criteria andThirtypriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("thirtyprice >=", value, "thirtyprice");
            return (Criteria) this;
        }

        public Criteria andThirtypriceLessThan(BigDecimal value) {
            addCriterion("thirtyprice <", value, "thirtyprice");
            return (Criteria) this;
        }

        public Criteria andThirtypriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("thirtyprice <=", value, "thirtyprice");
            return (Criteria) this;
        }

        public Criteria andThirtypriceIn(List<BigDecimal> values) {
            addCriterion("thirtyprice in", values, "thirtyprice");
            return (Criteria) this;
        }

        public Criteria andThirtypriceNotIn(List<BigDecimal> values) {
            addCriterion("thirtyprice not in", values, "thirtyprice");
            return (Criteria) this;
        }

        public Criteria andThirtypriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("thirtyprice between", value1, value2, "thirtyprice");
            return (Criteria) this;
        }

        public Criteria andThirtypriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("thirtyprice not between", value1, value2, "thirtyprice");
            return (Criteria) this;
        }

        public Criteria andSixtypriceIsNull() {
            addCriterion("sixtyprice is null");
            return (Criteria) this;
        }

        public Criteria andSixtypriceIsNotNull() {
            addCriterion("sixtyprice is not null");
            return (Criteria) this;
        }

        public Criteria andSixtypriceEqualTo(BigDecimal value) {
            addCriterion("sixtyprice =", value, "sixtyprice");
            return (Criteria) this;
        }

        public Criteria andSixtypriceNotEqualTo(BigDecimal value) {
            addCriterion("sixtyprice <>", value, "sixtyprice");
            return (Criteria) this;
        }

        public Criteria andSixtypriceGreaterThan(BigDecimal value) {
            addCriterion("sixtyprice >", value, "sixtyprice");
            return (Criteria) this;
        }

        public Criteria andSixtypriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sixtyprice >=", value, "sixtyprice");
            return (Criteria) this;
        }

        public Criteria andSixtypriceLessThan(BigDecimal value) {
            addCriterion("sixtyprice <", value, "sixtyprice");
            return (Criteria) this;
        }

        public Criteria andSixtypriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sixtyprice <=", value, "sixtyprice");
            return (Criteria) this;
        }

        public Criteria andSixtypriceIn(List<BigDecimal> values) {
            addCriterion("sixtyprice in", values, "sixtyprice");
            return (Criteria) this;
        }

        public Criteria andSixtypriceNotIn(List<BigDecimal> values) {
            addCriterion("sixtyprice not in", values, "sixtyprice");
            return (Criteria) this;
        }

        public Criteria andSixtypriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sixtyprice between", value1, value2, "sixtyprice");
            return (Criteria) this;
        }

        public Criteria andSixtypriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sixtyprice not between", value1, value2, "sixtyprice");
            return (Criteria) this;
        }

        public Criteria andIntervalpriceIsNull() {
            addCriterion("intervalprice is null");
            return (Criteria) this;
        }

        public Criteria andIntervalpriceIsNotNull() {
            addCriterion("intervalprice is not null");
            return (Criteria) this;
        }

        public Criteria andIntervalpriceEqualTo(String value) {
            addCriterion("intervalprice =", value, "intervalprice");
            return (Criteria) this;
        }

        public Criteria andIntervalpriceNotEqualTo(String value) {
            addCriterion("intervalprice <>", value, "intervalprice");
            return (Criteria) this;
        }

        public Criteria andIntervalpriceGreaterThan(String value) {
            addCriterion("intervalprice >", value, "intervalprice");
            return (Criteria) this;
        }

        public Criteria andIntervalpriceGreaterThanOrEqualTo(String value) {
            addCriterion("intervalprice >=", value, "intervalprice");
            return (Criteria) this;
        }

        public Criteria andIntervalpriceLessThan(String value) {
            addCriterion("intervalprice <", value, "intervalprice");
            return (Criteria) this;
        }

        public Criteria andIntervalpriceLessThanOrEqualTo(String value) {
            addCriterion("intervalprice <=", value, "intervalprice");
            return (Criteria) this;
        }

        public Criteria andIntervalpriceLike(String value) {
            addCriterion("intervalprice like", value, "intervalprice");
            return (Criteria) this;
        }

        public Criteria andIntervalpriceNotLike(String value) {
            addCriterion("intervalprice not like", value, "intervalprice");
            return (Criteria) this;
        }

        public Criteria andIntervalpriceIn(List<String> values) {
            addCriterion("intervalprice in", values, "intervalprice");
            return (Criteria) this;
        }

        public Criteria andIntervalpriceNotIn(List<String> values) {
            addCriterion("intervalprice not in", values, "intervalprice");
            return (Criteria) this;
        }

        public Criteria andIntervalpriceBetween(String value1, String value2) {
            addCriterion("intervalprice between", value1, value2, "intervalprice");
            return (Criteria) this;
        }

        public Criteria andIntervalpriceNotBetween(String value1, String value2) {
            addCriterion("intervalprice not between", value1, value2, "intervalprice");
            return (Criteria) this;
        }

        public Criteria andMarketpriceIsNull() {
            addCriterion("marketprice is null");
            return (Criteria) this;
        }

        public Criteria andMarketpriceIsNotNull() {
            addCriterion("marketprice is not null");
            return (Criteria) this;
        }

        public Criteria andMarketpriceEqualTo(BigDecimal value) {
            addCriterion("marketprice =", value, "marketprice");
            return (Criteria) this;
        }

        public Criteria andMarketpriceNotEqualTo(BigDecimal value) {
            addCriterion("marketprice <>", value, "marketprice");
            return (Criteria) this;
        }

        public Criteria andMarketpriceGreaterThan(BigDecimal value) {
            addCriterion("marketprice >", value, "marketprice");
            return (Criteria) this;
        }

        public Criteria andMarketpriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("marketprice >=", value, "marketprice");
            return (Criteria) this;
        }

        public Criteria andMarketpriceLessThan(BigDecimal value) {
            addCriterion("marketprice <", value, "marketprice");
            return (Criteria) this;
        }

        public Criteria andMarketpriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("marketprice <=", value, "marketprice");
            return (Criteria) this;
        }

        public Criteria andMarketpriceIn(List<BigDecimal> values) {
            addCriterion("marketprice in", values, "marketprice");
            return (Criteria) this;
        }

        public Criteria andMarketpriceNotIn(List<BigDecimal> values) {
            addCriterion("marketprice not in", values, "marketprice");
            return (Criteria) this;
        }

        public Criteria andMarketpriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("marketprice between", value1, value2, "marketprice");
            return (Criteria) this;
        }

        public Criteria andMarketpriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("marketprice not between", value1, value2, "marketprice");
            return (Criteria) this;
        }

        public Criteria andCostpriceIsNull() {
            addCriterion("costprice is null");
            return (Criteria) this;
        }

        public Criteria andCostpriceIsNotNull() {
            addCriterion("costprice is not null");
            return (Criteria) this;
        }

        public Criteria andCostpriceEqualTo(BigDecimal value) {
            addCriterion("costprice =", value, "costprice");
            return (Criteria) this;
        }

        public Criteria andCostpriceNotEqualTo(BigDecimal value) {
            addCriterion("costprice <>", value, "costprice");
            return (Criteria) this;
        }

        public Criteria andCostpriceGreaterThan(BigDecimal value) {
            addCriterion("costprice >", value, "costprice");
            return (Criteria) this;
        }

        public Criteria andCostpriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("costprice >=", value, "costprice");
            return (Criteria) this;
        }

        public Criteria andCostpriceLessThan(BigDecimal value) {
            addCriterion("costprice <", value, "costprice");
            return (Criteria) this;
        }

        public Criteria andCostpriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("costprice <=", value, "costprice");
            return (Criteria) this;
        }

        public Criteria andCostpriceIn(List<BigDecimal> values) {
            addCriterion("costprice in", values, "costprice");
            return (Criteria) this;
        }

        public Criteria andCostpriceNotIn(List<BigDecimal> values) {
            addCriterion("costprice not in", values, "costprice");
            return (Criteria) this;
        }

        public Criteria andCostpriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("costprice between", value1, value2, "costprice");
            return (Criteria) this;
        }

        public Criteria andCostpriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("costprice not between", value1, value2, "costprice");
            return (Criteria) this;
        }

        public Criteria andStoreunitIsNull() {
            addCriterion("storeunit is null");
            return (Criteria) this;
        }

        public Criteria andStoreunitIsNotNull() {
            addCriterion("storeunit is not null");
            return (Criteria) this;
        }

        public Criteria andStoreunitEqualTo(String value) {
            addCriterion("storeunit =", value, "storeunit");
            return (Criteria) this;
        }

        public Criteria andStoreunitNotEqualTo(String value) {
            addCriterion("storeunit <>", value, "storeunit");
            return (Criteria) this;
        }

        public Criteria andStoreunitGreaterThan(String value) {
            addCriterion("storeunit >", value, "storeunit");
            return (Criteria) this;
        }

        public Criteria andStoreunitGreaterThanOrEqualTo(String value) {
            addCriterion("storeunit >=", value, "storeunit");
            return (Criteria) this;
        }

        public Criteria andStoreunitLessThan(String value) {
            addCriterion("storeunit <", value, "storeunit");
            return (Criteria) this;
        }

        public Criteria andStoreunitLessThanOrEqualTo(String value) {
            addCriterion("storeunit <=", value, "storeunit");
            return (Criteria) this;
        }

        public Criteria andStoreunitLike(String value) {
            addCriterion("storeunit like", value, "storeunit");
            return (Criteria) this;
        }

        public Criteria andStoreunitNotLike(String value) {
            addCriterion("storeunit not like", value, "storeunit");
            return (Criteria) this;
        }

        public Criteria andStoreunitIn(List<String> values) {
            addCriterion("storeunit in", values, "storeunit");
            return (Criteria) this;
        }

        public Criteria andStoreunitNotIn(List<String> values) {
            addCriterion("storeunit not in", values, "storeunit");
            return (Criteria) this;
        }

        public Criteria andStoreunitBetween(String value1, String value2) {
            addCriterion("storeunit between", value1, value2, "storeunit");
            return (Criteria) this;
        }

        public Criteria andStoreunitNotBetween(String value1, String value2) {
            addCriterion("storeunit not between", value1, value2, "storeunit");
            return (Criteria) this;
        }

        public Criteria andAftersaleIsNull() {
            addCriterion("aftersale is null");
            return (Criteria) this;
        }

        public Criteria andAftersaleIsNotNull() {
            addCriterion("aftersale is not null");
            return (Criteria) this;
        }

        public Criteria andAftersaleEqualTo(String value) {
            addCriterion("aftersale =", value, "aftersale");
            return (Criteria) this;
        }

        public Criteria andAftersaleNotEqualTo(String value) {
            addCriterion("aftersale <>", value, "aftersale");
            return (Criteria) this;
        }

        public Criteria andAftersaleGreaterThan(String value) {
            addCriterion("aftersale >", value, "aftersale");
            return (Criteria) this;
        }

        public Criteria andAftersaleGreaterThanOrEqualTo(String value) {
            addCriterion("aftersale >=", value, "aftersale");
            return (Criteria) this;
        }

        public Criteria andAftersaleLessThan(String value) {
            addCriterion("aftersale <", value, "aftersale");
            return (Criteria) this;
        }

        public Criteria andAftersaleLessThanOrEqualTo(String value) {
            addCriterion("aftersale <=", value, "aftersale");
            return (Criteria) this;
        }

        public Criteria andAftersaleLike(String value) {
            addCriterion("aftersale like", value, "aftersale");
            return (Criteria) this;
        }

        public Criteria andAftersaleNotLike(String value) {
            addCriterion("aftersale not like", value, "aftersale");
            return (Criteria) this;
        }

        public Criteria andAftersaleIn(List<String> values) {
            addCriterion("aftersale in", values, "aftersale");
            return (Criteria) this;
        }

        public Criteria andAftersaleNotIn(List<String> values) {
            addCriterion("aftersale not in", values, "aftersale");
            return (Criteria) this;
        }

        public Criteria andAftersaleBetween(String value1, String value2) {
            addCriterion("aftersale between", value1, value2, "aftersale");
            return (Criteria) this;
        }

        public Criteria andAftersaleNotBetween(String value1, String value2) {
            addCriterion("aftersale not between", value1, value2, "aftersale");
            return (Criteria) this;
        }

        public Criteria andLocationIsNull() {
            addCriterion("location is null");
            return (Criteria) this;
        }

        public Criteria andLocationIsNotNull() {
            addCriterion("location is not null");
            return (Criteria) this;
        }

        public Criteria andLocationEqualTo(String value) {
            addCriterion("location =", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotEqualTo(String value) {
            addCriterion("location <>", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThan(String value) {
            addCriterion("location >", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThanOrEqualTo(String value) {
            addCriterion("location >=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThan(String value) {
            addCriterion("location <", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThanOrEqualTo(String value) {
            addCriterion("location <=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLike(String value) {
            addCriterion("location like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotLike(String value) {
            addCriterion("location not like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationIn(List<String> values) {
            addCriterion("location in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotIn(List<String> values) {
            addCriterion("location not in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationBetween(String value1, String value2) {
            addCriterion("location between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotBetween(String value1, String value2) {
            addCriterion("location not between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andFreightmodeIsNull() {
            addCriterion("freightmode is null");
            return (Criteria) this;
        }

        public Criteria andFreightmodeIsNotNull() {
            addCriterion("freightmode is not null");
            return (Criteria) this;
        }

        public Criteria andFreightmodeEqualTo(Long value) {
            addCriterion("freightmode =", value, "freightmode");
            return (Criteria) this;
        }

        public Criteria andFreightmodeNotEqualTo(Long value) {
            addCriterion("freightmode <>", value, "freightmode");
            return (Criteria) this;
        }

        public Criteria andFreightmodeGreaterThan(Long value) {
            addCriterion("freightmode >", value, "freightmode");
            return (Criteria) this;
        }

        public Criteria andFreightmodeGreaterThanOrEqualTo(Long value) {
            addCriterion("freightmode >=", value, "freightmode");
            return (Criteria) this;
        }

        public Criteria andFreightmodeLessThan(Long value) {
            addCriterion("freightmode <", value, "freightmode");
            return (Criteria) this;
        }

        public Criteria andFreightmodeLessThanOrEqualTo(Long value) {
            addCriterion("freightmode <=", value, "freightmode");
            return (Criteria) this;
        }

        public Criteria andFreightmodeIn(List<Long> values) {
            addCriterion("freightmode in", values, "freightmode");
            return (Criteria) this;
        }

        public Criteria andFreightmodeNotIn(List<Long> values) {
            addCriterion("freightmode not in", values, "freightmode");
            return (Criteria) this;
        }

        public Criteria andFreightmodeBetween(Long value1, Long value2) {
            addCriterion("freightmode between", value1, value2, "freightmode");
            return (Criteria) this;
        }

        public Criteria andFreightmodeNotBetween(Long value1, Long value2) {
            addCriterion("freightmode not between", value1, value2, "freightmode");
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

        public Criteria andPdstorenumIsNull() {
            addCriterion("pdstorenum is null");
            return (Criteria) this;
        }

        public Criteria andPdstorenumIsNotNull() {
            addCriterion("pdstorenum is not null");
            return (Criteria) this;
        }

        public Criteria andPdstorenumEqualTo(BigDecimal value) {
            addCriterion("pdstorenum =", value, "pdstorenum");
            return (Criteria) this;
        }

        public Criteria andPdstorenumNotEqualTo(BigDecimal value) {
            addCriterion("pdstorenum <>", value, "pdstorenum");
            return (Criteria) this;
        }

        public Criteria andPdstorenumGreaterThan(BigDecimal value) {
            addCriterion("pdstorenum >", value, "pdstorenum");
            return (Criteria) this;
        }

        public Criteria andPdstorenumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("pdstorenum >=", value, "pdstorenum");
            return (Criteria) this;
        }

        public Criteria andPdstorenumLessThan(BigDecimal value) {
            addCriterion("pdstorenum <", value, "pdstorenum");
            return (Criteria) this;
        }

        public Criteria andPdstorenumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("pdstorenum <=", value, "pdstorenum");
            return (Criteria) this;
        }

        public Criteria andPdstorenumIn(List<BigDecimal> values) {
            addCriterion("pdstorenum in", values, "pdstorenum");
            return (Criteria) this;
        }

        public Criteria andPdstorenumNotIn(List<BigDecimal> values) {
            addCriterion("pdstorenum not in", values, "pdstorenum");
            return (Criteria) this;
        }

        public Criteria andPdstorenumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pdstorenum between", value1, value2, "pdstorenum");
            return (Criteria) this;
        }

        public Criteria andPdstorenumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pdstorenum not between", value1, value2, "pdstorenum");
            return (Criteria) this;
        }

        public Criteria andStartnumIsNull() {
            addCriterion("startnum is null");
            return (Criteria) this;
        }

        public Criteria andStartnumIsNotNull() {
            addCriterion("startnum is not null");
            return (Criteria) this;
        }

        public Criteria andStartnumEqualTo(BigDecimal value) {
            addCriterion("startnum =", value, "startnum");
            return (Criteria) this;
        }

        public Criteria andStartnumNotEqualTo(BigDecimal value) {
            addCriterion("startnum <>", value, "startnum");
            return (Criteria) this;
        }

        public Criteria andStartnumGreaterThan(BigDecimal value) {
            addCriterion("startnum >", value, "startnum");
            return (Criteria) this;
        }

        public Criteria andStartnumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("startnum >=", value, "startnum");
            return (Criteria) this;
        }

        public Criteria andStartnumLessThan(BigDecimal value) {
            addCriterion("startnum <", value, "startnum");
            return (Criteria) this;
        }

        public Criteria andStartnumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("startnum <=", value, "startnum");
            return (Criteria) this;
        }

        public Criteria andStartnumIn(List<BigDecimal> values) {
            addCriterion("startnum in", values, "startnum");
            return (Criteria) this;
        }

        public Criteria andStartnumNotIn(List<BigDecimal> values) {
            addCriterion("startnum not in", values, "startnum");
            return (Criteria) this;
        }

        public Criteria andStartnumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("startnum between", value1, value2, "startnum");
            return (Criteria) this;
        }

        public Criteria andStartnumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("startnum not between", value1, value2, "startnum");
            return (Criteria) this;
        }

        public Criteria andWeightIsNull() {
            addCriterion("weight is null");
            return (Criteria) this;
        }

        public Criteria andWeightIsNotNull() {
            addCriterion("weight is not null");
            return (Criteria) this;
        }

        public Criteria andWeightEqualTo(BigDecimal value) {
            addCriterion("weight =", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotEqualTo(BigDecimal value) {
            addCriterion("weight <>", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThan(BigDecimal value) {
            addCriterion("weight >", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("weight >=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThan(BigDecimal value) {
            addCriterion("weight <", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("weight <=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightIn(List<BigDecimal> values) {
            addCriterion("weight in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotIn(List<BigDecimal> values) {
            addCriterion("weight not in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("weight between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("weight not between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andMinplusIsNull() {
            addCriterion("minplus is null");
            return (Criteria) this;
        }

        public Criteria andMinplusIsNotNull() {
            addCriterion("minplus is not null");
            return (Criteria) this;
        }

        public Criteria andMinplusEqualTo(BigDecimal value) {
            addCriterion("minplus =", value, "minplus");
            return (Criteria) this;
        }

        public Criteria andMinplusNotEqualTo(BigDecimal value) {
            addCriterion("minplus <>", value, "minplus");
            return (Criteria) this;
        }

        public Criteria andMinplusGreaterThan(BigDecimal value) {
            addCriterion("minplus >", value, "minplus");
            return (Criteria) this;
        }

        public Criteria andMinplusGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("minplus >=", value, "minplus");
            return (Criteria) this;
        }

        public Criteria andMinplusLessThan(BigDecimal value) {
            addCriterion("minplus <", value, "minplus");
            return (Criteria) this;
        }

        public Criteria andMinplusLessThanOrEqualTo(BigDecimal value) {
            addCriterion("minplus <=", value, "minplus");
            return (Criteria) this;
        }

        public Criteria andMinplusIn(List<BigDecimal> values) {
            addCriterion("minplus in", values, "minplus");
            return (Criteria) this;
        }

        public Criteria andMinplusNotIn(List<BigDecimal> values) {
            addCriterion("minplus not in", values, "minplus");
            return (Criteria) this;
        }

        public Criteria andMinplusBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("minplus between", value1, value2, "minplus");
            return (Criteria) this;
        }

        public Criteria andMinplusNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("minplus not between", value1, value2, "minplus");
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