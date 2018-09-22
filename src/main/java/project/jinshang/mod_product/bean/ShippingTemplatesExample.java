package project.jinshang.mod_product.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ShippingTemplatesExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ShippingTemplatesExample() {
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

        public Criteria andTemnameIsNull() {
            addCriterion("temname is null");
            return (Criteria) this;
        }

        public Criteria andTemnameIsNotNull() {
            addCriterion("temname is not null");
            return (Criteria) this;
        }

        public Criteria andTemnameEqualTo(String value) {
            addCriterion("temname =", value, "temname");
            return (Criteria) this;
        }

        public Criteria andTemnameNotEqualTo(String value) {
            addCriterion("temname <>", value, "temname");
            return (Criteria) this;
        }

        public Criteria andTemnameGreaterThan(String value) {
            addCriterion("temname >", value, "temname");
            return (Criteria) this;
        }

        public Criteria andTemnameGreaterThanOrEqualTo(String value) {
            addCriterion("temname >=", value, "temname");
            return (Criteria) this;
        }

        public Criteria andTemnameLessThan(String value) {
            addCriterion("temname <", value, "temname");
            return (Criteria) this;
        }

        public Criteria andTemnameLessThanOrEqualTo(String value) {
            addCriterion("temname <=", value, "temname");
            return (Criteria) this;
        }

        public Criteria andTemnameLike(String value) {
            addCriterion("temname like", value, "temname");
            return (Criteria) this;
        }

        public Criteria andTemnameNotLike(String value) {
            addCriterion("temname not like", value, "temname");
            return (Criteria) this;
        }

        public Criteria andTemnameIn(List<String> values) {
            addCriterion("temname in", values, "temname");
            return (Criteria) this;
        }

        public Criteria andTemnameNotIn(List<String> values) {
            addCriterion("temname not in", values, "temname");
            return (Criteria) this;
        }

        public Criteria andTemnameBetween(String value1, String value2) {
            addCriterion("temname between", value1, value2, "temname");
            return (Criteria) this;
        }

        public Criteria andTemnameNotBetween(String value1, String value2) {
            addCriterion("temname not between", value1, value2, "temname");
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

        public Criteria andIsfreeIsNull() {
            addCriterion("isfree is null");
            return (Criteria) this;
        }

        public Criteria andIsfreeIsNotNull() {
            addCriterion("isfree is not null");
            return (Criteria) this;
        }

        public Criteria andIsfreeEqualTo(Short value) {
            addCriterion("isfree =", value, "isfree");
            return (Criteria) this;
        }

        public Criteria andIsfreeNotEqualTo(Short value) {
            addCriterion("isfree <>", value, "isfree");
            return (Criteria) this;
        }

        public Criteria andIsfreeGreaterThan(Short value) {
            addCriterion("isfree >", value, "isfree");
            return (Criteria) this;
        }

        public Criteria andIsfreeGreaterThanOrEqualTo(Short value) {
            addCriterion("isfree >=", value, "isfree");
            return (Criteria) this;
        }

        public Criteria andIsfreeLessThan(Short value) {
            addCriterion("isfree <", value, "isfree");
            return (Criteria) this;
        }

        public Criteria andIsfreeLessThanOrEqualTo(Short value) {
            addCriterion("isfree <=", value, "isfree");
            return (Criteria) this;
        }

        public Criteria andIsfreeIn(List<Short> values) {
            addCriterion("isfree in", values, "isfree");
            return (Criteria) this;
        }

        public Criteria andIsfreeNotIn(List<Short> values) {
            addCriterion("isfree not in", values, "isfree");
            return (Criteria) this;
        }

        public Criteria andIsfreeBetween(Short value1, Short value2) {
            addCriterion("isfree between", value1, value2, "isfree");
            return (Criteria) this;
        }

        public Criteria andIsfreeNotBetween(Short value1, Short value2) {
            addCriterion("isfree not between", value1, value2, "isfree");
            return (Criteria) this;
        }

        public Criteria andCounttypeIsNull() {
            addCriterion("counttype is null");
            return (Criteria) this;
        }

        public Criteria andCounttypeIsNotNull() {
            addCriterion("counttype is not null");
            return (Criteria) this;
        }

        public Criteria andCounttypeEqualTo(Short value) {
            addCriterion("counttype =", value, "counttype");
            return (Criteria) this;
        }

        public Criteria andCounttypeNotEqualTo(Short value) {
            addCriterion("counttype <>", value, "counttype");
            return (Criteria) this;
        }

        public Criteria andCounttypeGreaterThan(Short value) {
            addCriterion("counttype >", value, "counttype");
            return (Criteria) this;
        }

        public Criteria andCounttypeGreaterThanOrEqualTo(Short value) {
            addCriterion("counttype >=", value, "counttype");
            return (Criteria) this;
        }

        public Criteria andCounttypeLessThan(Short value) {
            addCriterion("counttype <", value, "counttype");
            return (Criteria) this;
        }

        public Criteria andCounttypeLessThanOrEqualTo(Short value) {
            addCriterion("counttype <=", value, "counttype");
            return (Criteria) this;
        }

        public Criteria andCounttypeIn(List<Short> values) {
            addCriterion("counttype in", values, "counttype");
            return (Criteria) this;
        }

        public Criteria andCounttypeNotIn(List<Short> values) {
            addCriterion("counttype not in", values, "counttype");
            return (Criteria) this;
        }

        public Criteria andCounttypeBetween(Short value1, Short value2) {
            addCriterion("counttype between", value1, value2, "counttype");
            return (Criteria) this;
        }

        public Criteria andCounttypeNotBetween(Short value1, Short value2) {
            addCriterion("counttype not between", value1, value2, "counttype");
            return (Criteria) this;
        }

        public Criteria andDefaultfreightIsNull() {
            addCriterion("defaultfreight is null");
            return (Criteria) this;
        }

        public Criteria andDefaultfreightIsNotNull() {
            addCriterion("defaultfreight is not null");
            return (Criteria) this;
        }

        public Criteria andDefaultfreightEqualTo(BigDecimal value) {
            addCriterion("defaultfreight =", value, "defaultfreight");
            return (Criteria) this;
        }

        public Criteria andDefaultfreightNotEqualTo(BigDecimal value) {
            addCriterion("defaultfreight <>", value, "defaultfreight");
            return (Criteria) this;
        }

        public Criteria andDefaultfreightGreaterThan(BigDecimal value) {
            addCriterion("defaultfreight >", value, "defaultfreight");
            return (Criteria) this;
        }

        public Criteria andDefaultfreightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("defaultfreight >=", value, "defaultfreight");
            return (Criteria) this;
        }

        public Criteria andDefaultfreightLessThan(BigDecimal value) {
            addCriterion("defaultfreight <", value, "defaultfreight");
            return (Criteria) this;
        }

        public Criteria andDefaultfreightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("defaultfreight <=", value, "defaultfreight");
            return (Criteria) this;
        }

        public Criteria andDefaultfreightIn(List<BigDecimal> values) {
            addCriterion("defaultfreight in", values, "defaultfreight");
            return (Criteria) this;
        }

        public Criteria andDefaultfreightNotIn(List<BigDecimal> values) {
            addCriterion("defaultfreight not in", values, "defaultfreight");
            return (Criteria) this;
        }

        public Criteria andDefaultfreightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("defaultfreight between", value1, value2, "defaultfreight");
            return (Criteria) this;
        }

        public Criteria andDefaultfreightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("defaultfreight not between", value1, value2, "defaultfreight");
            return (Criteria) this;
        }

        public Criteria andDefaultcostIsNull() {
            addCriterion("defaultcost is null");
            return (Criteria) this;
        }

        public Criteria andDefaultcostIsNotNull() {
            addCriterion("defaultcost is not null");
            return (Criteria) this;
        }

        public Criteria andDefaultcostEqualTo(BigDecimal value) {
            addCriterion("defaultcost =", value, "defaultcost");
            return (Criteria) this;
        }

        public Criteria andDefaultcostNotEqualTo(BigDecimal value) {
            addCriterion("defaultcost <>", value, "defaultcost");
            return (Criteria) this;
        }

        public Criteria andDefaultcostGreaterThan(BigDecimal value) {
            addCriterion("defaultcost >", value, "defaultcost");
            return (Criteria) this;
        }

        public Criteria andDefaultcostGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("defaultcost >=", value, "defaultcost");
            return (Criteria) this;
        }

        public Criteria andDefaultcostLessThan(BigDecimal value) {
            addCriterion("defaultcost <", value, "defaultcost");
            return (Criteria) this;
        }

        public Criteria andDefaultcostLessThanOrEqualTo(BigDecimal value) {
            addCriterion("defaultcost <=", value, "defaultcost");
            return (Criteria) this;
        }

        public Criteria andDefaultcostIn(List<BigDecimal> values) {
            addCriterion("defaultcost in", values, "defaultcost");
            return (Criteria) this;
        }

        public Criteria andDefaultcostNotIn(List<BigDecimal> values) {
            addCriterion("defaultcost not in", values, "defaultcost");
            return (Criteria) this;
        }

        public Criteria andDefaultcostBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("defaultcost between", value1, value2, "defaultcost");
            return (Criteria) this;
        }

        public Criteria andDefaultcostNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("defaultcost not between", value1, value2, "defaultcost");
            return (Criteria) this;
        }

        public Criteria andPerkilogramaddedIsNull() {
            addCriterion("perkilogramadded is null");
            return (Criteria) this;
        }

        public Criteria andPerkilogramaddedIsNotNull() {
            addCriterion("perkilogramadded is not null");
            return (Criteria) this;
        }

        public Criteria andPerkilogramaddedEqualTo(BigDecimal value) {
            addCriterion("perkilogramadded =", value, "perkilogramadded");
            return (Criteria) this;
        }

        public Criteria andPerkilogramaddedNotEqualTo(BigDecimal value) {
            addCriterion("perkilogramadded <>", value, "perkilogramadded");
            return (Criteria) this;
        }

        public Criteria andPerkilogramaddedGreaterThan(BigDecimal value) {
            addCriterion("perkilogramadded >", value, "perkilogramadded");
            return (Criteria) this;
        }

        public Criteria andPerkilogramaddedGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("perkilogramadded >=", value, "perkilogramadded");
            return (Criteria) this;
        }

        public Criteria andPerkilogramaddedLessThan(BigDecimal value) {
            addCriterion("perkilogramadded <", value, "perkilogramadded");
            return (Criteria) this;
        }

        public Criteria andPerkilogramaddedLessThanOrEqualTo(BigDecimal value) {
            addCriterion("perkilogramadded <=", value, "perkilogramadded");
            return (Criteria) this;
        }

        public Criteria andPerkilogramaddedIn(List<BigDecimal> values) {
            addCriterion("perkilogramadded in", values, "perkilogramadded");
            return (Criteria) this;
        }

        public Criteria andPerkilogramaddedNotIn(List<BigDecimal> values) {
            addCriterion("perkilogramadded not in", values, "perkilogramadded");
            return (Criteria) this;
        }

        public Criteria andPerkilogramaddedBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("perkilogramadded between", value1, value2, "perkilogramadded");
            return (Criteria) this;
        }

        public Criteria andPerkilogramaddedNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("perkilogramadded not between", value1, value2, "perkilogramadded");
            return (Criteria) this;
        }

        public Criteria andPerkilogramcostIsNull() {
            addCriterion("perkilogramcost is null");
            return (Criteria) this;
        }

        public Criteria andPerkilogramcostIsNotNull() {
            addCriterion("perkilogramcost is not null");
            return (Criteria) this;
        }

        public Criteria andPerkilogramcostEqualTo(BigDecimal value) {
            addCriterion("perkilogramcost =", value, "perkilogramcost");
            return (Criteria) this;
        }

        public Criteria andPerkilogramcostNotEqualTo(BigDecimal value) {
            addCriterion("perkilogramcost <>", value, "perkilogramcost");
            return (Criteria) this;
        }

        public Criteria andPerkilogramcostGreaterThan(BigDecimal value) {
            addCriterion("perkilogramcost >", value, "perkilogramcost");
            return (Criteria) this;
        }

        public Criteria andPerkilogramcostGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("perkilogramcost >=", value, "perkilogramcost");
            return (Criteria) this;
        }

        public Criteria andPerkilogramcostLessThan(BigDecimal value) {
            addCriterion("perkilogramcost <", value, "perkilogramcost");
            return (Criteria) this;
        }

        public Criteria andPerkilogramcostLessThanOrEqualTo(BigDecimal value) {
            addCriterion("perkilogramcost <=", value, "perkilogramcost");
            return (Criteria) this;
        }

        public Criteria andPerkilogramcostIn(List<BigDecimal> values) {
            addCriterion("perkilogramcost in", values, "perkilogramcost");
            return (Criteria) this;
        }

        public Criteria andPerkilogramcostNotIn(List<BigDecimal> values) {
            addCriterion("perkilogramcost not in", values, "perkilogramcost");
            return (Criteria) this;
        }

        public Criteria andPerkilogramcostBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("perkilogramcost between", value1, value2, "perkilogramcost");
            return (Criteria) this;
        }

        public Criteria andPerkilogramcostNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("perkilogramcost not between", value1, value2, "perkilogramcost");
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

        public Criteria andTemtypeIsNull() {
            addCriterion("temtype is null");
            return (Criteria) this;
        }

        public Criteria andTemtypeIsNotNull() {
            addCriterion("temtype is not null");
            return (Criteria) this;
        }

        public Criteria andTemtypeEqualTo(Short value) {
            addCriterion("temtype =", value, "temtype");
            return (Criteria) this;
        }

        public Criteria andTemtypeNotEqualTo(Short value) {
            addCriterion("temtype <>", value, "temtype");
            return (Criteria) this;
        }

        public Criteria andTemtypeGreaterThan(Short value) {
            addCriterion("temtype >", value, "temtype");
            return (Criteria) this;
        }

        public Criteria andTemtypeGreaterThanOrEqualTo(Short value) {
            addCriterion("temtype >=", value, "temtype");
            return (Criteria) this;
        }

        public Criteria andTemtypeLessThan(Short value) {
            addCriterion("temtype <", value, "temtype");
            return (Criteria) this;
        }

        public Criteria andTemtypeLessThanOrEqualTo(Short value) {
            addCriterion("temtype <=", value, "temtype");
            return (Criteria) this;
        }

        public Criteria andTemtypeIn(List<Short> values) {
            addCriterion("temtype in", values, "temtype");
            return (Criteria) this;
        }

        public Criteria andTemtypeNotIn(List<Short> values) {
            addCriterion("temtype not in", values, "temtype");
            return (Criteria) this;
        }

        public Criteria andTemtypeBetween(Short value1, Short value2) {
            addCriterion("temtype between", value1, value2, "temtype");
            return (Criteria) this;
        }

        public Criteria andTemtypeNotBetween(Short value1, Short value2) {
            addCriterion("temtype not between", value1, value2, "temtype");
            return (Criteria) this;
        }

        public Criteria andDefaultfreepriceIsNull() {
            addCriterion("defaultfreeprice is null");
            return (Criteria) this;
        }

        public Criteria andDefaultfreepriceIsNotNull() {
            addCriterion("defaultfreeprice is not null");
            return (Criteria) this;
        }

        public Criteria andDefaultfreepriceEqualTo(BigDecimal value) {
            addCriterion("defaultfreeprice =", value, "defaultfreeprice");
            return (Criteria) this;
        }

        public Criteria andDefaultfreepriceNotEqualTo(BigDecimal value) {
            addCriterion("defaultfreeprice <>", value, "defaultfreeprice");
            return (Criteria) this;
        }

        public Criteria andDefaultfreepriceGreaterThan(BigDecimal value) {
            addCriterion("defaultfreeprice >", value, "defaultfreeprice");
            return (Criteria) this;
        }

        public Criteria andDefaultfreepriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("defaultfreeprice >=", value, "defaultfreeprice");
            return (Criteria) this;
        }

        public Criteria andDefaultfreepriceLessThan(BigDecimal value) {
            addCriterion("defaultfreeprice <", value, "defaultfreeprice");
            return (Criteria) this;
        }

        public Criteria andDefaultfreepriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("defaultfreeprice <=", value, "defaultfreeprice");
            return (Criteria) this;
        }

        public Criteria andDefaultfreepriceIn(List<BigDecimal> values) {
            addCriterion("defaultfreeprice in", values, "defaultfreeprice");
            return (Criteria) this;
        }

        public Criteria andDefaultfreepriceNotIn(List<BigDecimal> values) {
            addCriterion("defaultfreeprice not in", values, "defaultfreeprice");
            return (Criteria) this;
        }

        public Criteria andDefaultfreepriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("defaultfreeprice between", value1, value2, "defaultfreeprice");
            return (Criteria) this;
        }

        public Criteria andDefaultfreepriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("defaultfreeprice not between", value1, value2, "defaultfreeprice");
            return (Criteria) this;
        }

        public Criteria andShopgroupidIsNull() {
            addCriterion("shopgroupid is null");
            return (Criteria) this;
        }

        public Criteria andShopgroupidIsNotNull() {
            addCriterion("shopgroupid is not null");
            return (Criteria) this;
        }

        public Criteria andShopgroupidEqualTo(Long value) {
            addCriterion("shopgroupid =", value, "shopgroupid");
            return (Criteria) this;
        }

        public Criteria andShopgroupidNotEqualTo(Long value) {
            addCriterion("shopgroupid <>", value, "shopgroupid");
            return (Criteria) this;
        }

        public Criteria andShopgroupidGreaterThan(Long value) {
            addCriterion("shopgroupid >", value, "shopgroupid");
            return (Criteria) this;
        }

        public Criteria andShopgroupidGreaterThanOrEqualTo(Long value) {
            addCriterion("shopgroupid >=", value, "shopgroupid");
            return (Criteria) this;
        }

        public Criteria andShopgroupidLessThan(Long value) {
            addCriterion("shopgroupid <", value, "shopgroupid");
            return (Criteria) this;
        }

        public Criteria andShopgroupidLessThanOrEqualTo(Long value) {
            addCriterion("shopgroupid <=", value, "shopgroupid");
            return (Criteria) this;
        }

        public Criteria andShopgroupidIn(List<Long> values) {
            addCriterion("shopgroupid in", values, "shopgroupid");
            return (Criteria) this;
        }

        public Criteria andShopgroupidNotIn(List<Long> values) {
            addCriterion("shopgroupid not in", values, "shopgroupid");
            return (Criteria) this;
        }

        public Criteria andShopgroupidBetween(Long value1, Long value2) {
            addCriterion("shopgroupid between", value1, value2, "shopgroupid");
            return (Criteria) this;
        }

        public Criteria andShopgroupidNotBetween(Long value1, Long value2) {
            addCriterion("shopgroupid not between", value1, value2, "shopgroupid");
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