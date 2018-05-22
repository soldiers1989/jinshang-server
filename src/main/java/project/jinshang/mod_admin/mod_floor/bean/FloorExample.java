package project.jinshang.mod_admin.mod_floor.bean;

import java.util.ArrayList;
import java.util.List;

public class FloorExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FloorExample() {
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

        public Criteria andFloornameIsNull() {
            addCriterion("floorname is null");
            return (Criteria) this;
        }

        public Criteria andFloornameIsNotNull() {
            addCriterion("floorname is not null");
            return (Criteria) this;
        }

        public Criteria andFloornameEqualTo(String value) {
            addCriterion("floorname =", value, "floorname");
            return (Criteria) this;
        }

        public Criteria andFloornameNotEqualTo(String value) {
            addCriterion("floorname <>", value, "floorname");
            return (Criteria) this;
        }

        public Criteria andFloornameGreaterThan(String value) {
            addCriterion("floorname >", value, "floorname");
            return (Criteria) this;
        }

        public Criteria andFloornameGreaterThanOrEqualTo(String value) {
            addCriterion("floorname >=", value, "floorname");
            return (Criteria) this;
        }

        public Criteria andFloornameLessThan(String value) {
            addCriterion("floorname <", value, "floorname");
            return (Criteria) this;
        }

        public Criteria andFloornameLessThanOrEqualTo(String value) {
            addCriterion("floorname <=", value, "floorname");
            return (Criteria) this;
        }

        public Criteria andFloornameLike(String value) {
            addCriterion("floorname like", value, "floorname");
            return (Criteria) this;
        }

        public Criteria andFloornameNotLike(String value) {
            addCriterion("floorname not like", value, "floorname");
            return (Criteria) this;
        }

        public Criteria andFloornameIn(List<String> values) {
            addCriterion("floorname in", values, "floorname");
            return (Criteria) this;
        }

        public Criteria andFloornameNotIn(List<String> values) {
            addCriterion("floorname not in", values, "floorname");
            return (Criteria) this;
        }

        public Criteria andFloornameBetween(String value1, String value2) {
            addCriterion("floorname between", value1, value2, "floorname");
            return (Criteria) this;
        }

        public Criteria andFloornameNotBetween(String value1, String value2) {
            addCriterion("floorname not between", value1, value2, "floorname");
            return (Criteria) this;
        }

        public Criteria andLevel1idIsNull() {
            addCriterion("level1id is null");
            return (Criteria) this;
        }

        public Criteria andLevel1idIsNotNull() {
            addCriterion("level1id is not null");
            return (Criteria) this;
        }

        public Criteria andLevel1idEqualTo(Long value) {
            addCriterion("level1id =", value, "level1id");
            return (Criteria) this;
        }

        public Criteria andLevel1idNotEqualTo(Long value) {
            addCriterion("level1id <>", value, "level1id");
            return (Criteria) this;
        }

        public Criteria andLevel1idGreaterThan(Long value) {
            addCriterion("level1id >", value, "level1id");
            return (Criteria) this;
        }

        public Criteria andLevel1idGreaterThanOrEqualTo(Long value) {
            addCriterion("level1id >=", value, "level1id");
            return (Criteria) this;
        }

        public Criteria andLevel1idLessThan(Long value) {
            addCriterion("level1id <", value, "level1id");
            return (Criteria) this;
        }

        public Criteria andLevel1idLessThanOrEqualTo(Long value) {
            addCriterion("level1id <=", value, "level1id");
            return (Criteria) this;
        }

        public Criteria andLevel1idIn(List<Long> values) {
            addCriterion("level1id in", values, "level1id");
            return (Criteria) this;
        }

        public Criteria andLevel1idNotIn(List<Long> values) {
            addCriterion("level1id not in", values, "level1id");
            return (Criteria) this;
        }

        public Criteria andLevel1idBetween(Long value1, Long value2) {
            addCriterion("level1id between", value1, value2, "level1id");
            return (Criteria) this;
        }

        public Criteria andLevel1idNotBetween(Long value1, Long value2) {
            addCriterion("level1id not between", value1, value2, "level1id");
            return (Criteria) this;
        }

        public Criteria andLevel2idIsNull() {
            addCriterion("level2id is null");
            return (Criteria) this;
        }

        public Criteria andLevel2idIsNotNull() {
            addCriterion("level2id is not null");
            return (Criteria) this;
        }

        public Criteria andLevel2idEqualTo(Long value) {
            addCriterion("level2id =", value, "level2id");
            return (Criteria) this;
        }

        public Criteria andLevel2idNotEqualTo(Long value) {
            addCriterion("level2id <>", value, "level2id");
            return (Criteria) this;
        }

        public Criteria andLevel2idGreaterThan(Long value) {
            addCriterion("level2id >", value, "level2id");
            return (Criteria) this;
        }

        public Criteria andLevel2idGreaterThanOrEqualTo(Long value) {
            addCriterion("level2id >=", value, "level2id");
            return (Criteria) this;
        }

        public Criteria andLevel2idLessThan(Long value) {
            addCriterion("level2id <", value, "level2id");
            return (Criteria) this;
        }

        public Criteria andLevel2idLessThanOrEqualTo(Long value) {
            addCriterion("level2id <=", value, "level2id");
            return (Criteria) this;
        }

        public Criteria andLevel2idIn(List<Long> values) {
            addCriterion("level2id in", values, "level2id");
            return (Criteria) this;
        }

        public Criteria andLevel2idNotIn(List<Long> values) {
            addCriterion("level2id not in", values, "level2id");
            return (Criteria) this;
        }

        public Criteria andLevel2idBetween(Long value1, Long value2) {
            addCriterion("level2id between", value1, value2, "level2id");
            return (Criteria) this;
        }

        public Criteria andLevel2idNotBetween(Long value1, Long value2) {
            addCriterion("level2id not between", value1, value2, "level2id");
            return (Criteria) this;
        }

        public Criteria andFloorsubnameIsNull() {
            addCriterion("floorsubname is null");
            return (Criteria) this;
        }

        public Criteria andFloorsubnameIsNotNull() {
            addCriterion("floorsubname is not null");
            return (Criteria) this;
        }

        public Criteria andFloorsubnameEqualTo(String value) {
            addCriterion("floorsubname =", value, "floorsubname");
            return (Criteria) this;
        }

        public Criteria andFloorsubnameNotEqualTo(String value) {
            addCriterion("floorsubname <>", value, "floorsubname");
            return (Criteria) this;
        }

        public Criteria andFloorsubnameGreaterThan(String value) {
            addCriterion("floorsubname >", value, "floorsubname");
            return (Criteria) this;
        }

        public Criteria andFloorsubnameGreaterThanOrEqualTo(String value) {
            addCriterion("floorsubname >=", value, "floorsubname");
            return (Criteria) this;
        }

        public Criteria andFloorsubnameLessThan(String value) {
            addCriterion("floorsubname <", value, "floorsubname");
            return (Criteria) this;
        }

        public Criteria andFloorsubnameLessThanOrEqualTo(String value) {
            addCriterion("floorsubname <=", value, "floorsubname");
            return (Criteria) this;
        }

        public Criteria andFloorsubnameLike(String value) {
            addCriterion("floorsubname like", value, "floorsubname");
            return (Criteria) this;
        }

        public Criteria andFloorsubnameNotLike(String value) {
            addCriterion("floorsubname not like", value, "floorsubname");
            return (Criteria) this;
        }

        public Criteria andFloorsubnameIn(List<String> values) {
            addCriterion("floorsubname in", values, "floorsubname");
            return (Criteria) this;
        }

        public Criteria andFloorsubnameNotIn(List<String> values) {
            addCriterion("floorsubname not in", values, "floorsubname");
            return (Criteria) this;
        }

        public Criteria andFloorsubnameBetween(String value1, String value2) {
            addCriterion("floorsubname between", value1, value2, "floorsubname");
            return (Criteria) this;
        }

        public Criteria andFloorsubnameNotBetween(String value1, String value2) {
            addCriterion("floorsubname not between", value1, value2, "floorsubname");
            return (Criteria) this;
        }

        public Criteria andSubjectimgIsNull() {
            addCriterion("subjectimg is null");
            return (Criteria) this;
        }

        public Criteria andSubjectimgIsNotNull() {
            addCriterion("subjectimg is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectimgEqualTo(String value) {
            addCriterion("subjectimg =", value, "subjectimg");
            return (Criteria) this;
        }

        public Criteria andSubjectimgNotEqualTo(String value) {
            addCriterion("subjectimg <>", value, "subjectimg");
            return (Criteria) this;
        }

        public Criteria andSubjectimgGreaterThan(String value) {
            addCriterion("subjectimg >", value, "subjectimg");
            return (Criteria) this;
        }

        public Criteria andSubjectimgGreaterThanOrEqualTo(String value) {
            addCriterion("subjectimg >=", value, "subjectimg");
            return (Criteria) this;
        }

        public Criteria andSubjectimgLessThan(String value) {
            addCriterion("subjectimg <", value, "subjectimg");
            return (Criteria) this;
        }

        public Criteria andSubjectimgLessThanOrEqualTo(String value) {
            addCriterion("subjectimg <=", value, "subjectimg");
            return (Criteria) this;
        }

        public Criteria andSubjectimgLike(String value) {
            addCriterion("subjectimg like", value, "subjectimg");
            return (Criteria) this;
        }

        public Criteria andSubjectimgNotLike(String value) {
            addCriterion("subjectimg not like", value, "subjectimg");
            return (Criteria) this;
        }

        public Criteria andSubjectimgIn(List<String> values) {
            addCriterion("subjectimg in", values, "subjectimg");
            return (Criteria) this;
        }

        public Criteria andSubjectimgNotIn(List<String> values) {
            addCriterion("subjectimg not in", values, "subjectimg");
            return (Criteria) this;
        }

        public Criteria andSubjectimgBetween(String value1, String value2) {
            addCriterion("subjectimg between", value1, value2, "subjectimg");
            return (Criteria) this;
        }

        public Criteria andSubjectimgNotBetween(String value1, String value2) {
            addCriterion("subjectimg not between", value1, value2, "subjectimg");
            return (Criteria) this;
        }

        public Criteria andSubjectimgurlIsNull() {
            addCriterion("subjectimgurl is null");
            return (Criteria) this;
        }

        public Criteria andSubjectimgurlIsNotNull() {
            addCriterion("subjectimgurl is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectimgurlEqualTo(String value) {
            addCriterion("subjectimgurl =", value, "subjectimgurl");
            return (Criteria) this;
        }

        public Criteria andSubjectimgurlNotEqualTo(String value) {
            addCriterion("subjectimgurl <>", value, "subjectimgurl");
            return (Criteria) this;
        }

        public Criteria andSubjectimgurlGreaterThan(String value) {
            addCriterion("subjectimgurl >", value, "subjectimgurl");
            return (Criteria) this;
        }

        public Criteria andSubjectimgurlGreaterThanOrEqualTo(String value) {
            addCriterion("subjectimgurl >=", value, "subjectimgurl");
            return (Criteria) this;
        }

        public Criteria andSubjectimgurlLessThan(String value) {
            addCriterion("subjectimgurl <", value, "subjectimgurl");
            return (Criteria) this;
        }

        public Criteria andSubjectimgurlLessThanOrEqualTo(String value) {
            addCriterion("subjectimgurl <=", value, "subjectimgurl");
            return (Criteria) this;
        }

        public Criteria andSubjectimgurlLike(String value) {
            addCriterion("subjectimgurl like", value, "subjectimgurl");
            return (Criteria) this;
        }

        public Criteria andSubjectimgurlNotLike(String value) {
            addCriterion("subjectimgurl not like", value, "subjectimgurl");
            return (Criteria) this;
        }

        public Criteria andSubjectimgurlIn(List<String> values) {
            addCriterion("subjectimgurl in", values, "subjectimgurl");
            return (Criteria) this;
        }

        public Criteria andSubjectimgurlNotIn(List<String> values) {
            addCriterion("subjectimgurl not in", values, "subjectimgurl");
            return (Criteria) this;
        }

        public Criteria andSubjectimgurlBetween(String value1, String value2) {
            addCriterion("subjectimgurl between", value1, value2, "subjectimgurl");
            return (Criteria) this;
        }

        public Criteria andSubjectimgurlNotBetween(String value1, String value2) {
            addCriterion("subjectimgurl not between", value1, value2, "subjectimgurl");
            return (Criteria) this;
        }

        public Criteria andCategoryarrayIsNull() {
            addCriterion("categoryarray is null");
            return (Criteria) this;
        }

        public Criteria andCategoryarrayIsNotNull() {
            addCriterion("categoryarray is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryarrayEqualTo(Object value) {
            addCriterion("categoryarray =", value, "categoryarray");
            return (Criteria) this;
        }

        public Criteria andCategoryarrayNotEqualTo(Object value) {
            addCriterion("categoryarray <>", value, "categoryarray");
            return (Criteria) this;
        }

        public Criteria andCategoryarrayGreaterThan(Object value) {
            addCriterion("categoryarray >", value, "categoryarray");
            return (Criteria) this;
        }

        public Criteria andCategoryarrayGreaterThanOrEqualTo(Object value) {
            addCriterion("categoryarray >=", value, "categoryarray");
            return (Criteria) this;
        }

        public Criteria andCategoryarrayLessThan(Object value) {
            addCriterion("categoryarray <", value, "categoryarray");
            return (Criteria) this;
        }

        public Criteria andCategoryarrayLessThanOrEqualTo(Object value) {
            addCriterion("categoryarray <=", value, "categoryarray");
            return (Criteria) this;
        }

        public Criteria andCategoryarrayIn(List<Object> values) {
            addCriterion("categoryarray in", values, "categoryarray");
            return (Criteria) this;
        }

        public Criteria andCategoryarrayNotIn(List<Object> values) {
            addCriterion("categoryarray not in", values, "categoryarray");
            return (Criteria) this;
        }

        public Criteria andCategoryarrayBetween(Object value1, Object value2) {
            addCriterion("categoryarray between", value1, value2, "categoryarray");
            return (Criteria) this;
        }

        public Criteria andCategoryarrayNotBetween(Object value1, Object value2) {
            addCriterion("categoryarray not between", value1, value2, "categoryarray");
            return (Criteria) this;
        }

        public Criteria andFloorproductsIsNull() {
            addCriterion("floorproducts is null");
            return (Criteria) this;
        }

        public Criteria andFloorproductsIsNotNull() {
            addCriterion("floorproducts is not null");
            return (Criteria) this;
        }

        public Criteria andFloorproductsEqualTo(String value) {
            addCriterion("floorproducts =", value, "floorproducts");
            return (Criteria) this;
        }

        public Criteria andFloorproductsNotEqualTo(String value) {
            addCriterion("floorproducts <>", value, "floorproducts");
            return (Criteria) this;
        }

        public Criteria andFloorproductsGreaterThan(String value) {
            addCriterion("floorproducts >", value, "floorproducts");
            return (Criteria) this;
        }

        public Criteria andFloorproductsGreaterThanOrEqualTo(String value) {
            addCriterion("floorproducts >=", value, "floorproducts");
            return (Criteria) this;
        }

        public Criteria andFloorproductsLessThan(String value) {
            addCriterion("floorproducts <", value, "floorproducts");
            return (Criteria) this;
        }

        public Criteria andFloorproductsLessThanOrEqualTo(String value) {
            addCriterion("floorproducts <=", value, "floorproducts");
            return (Criteria) this;
        }

        public Criteria andFloorproductsLike(String value) {
            addCriterion("floorproducts like", value, "floorproducts");
            return (Criteria) this;
        }

        public Criteria andFloorproductsNotLike(String value) {
            addCriterion("floorproducts not like", value, "floorproducts");
            return (Criteria) this;
        }

        public Criteria andFloorproductsIn(List<String> values) {
            addCriterion("floorproducts in", values, "floorproducts");
            return (Criteria) this;
        }

        public Criteria andFloorproductsNotIn(List<String> values) {
            addCriterion("floorproducts not in", values, "floorproducts");
            return (Criteria) this;
        }

        public Criteria andFloorproductsBetween(String value1, String value2) {
            addCriterion("floorproducts between", value1, value2, "floorproducts");
            return (Criteria) this;
        }

        public Criteria andFloorproductsNotBetween(String value1, String value2) {
            addCriterion("floorproducts not between", value1, value2, "floorproducts");
            return (Criteria) this;
        }

        public Criteria andFloorproductsurlIsNull() {
            addCriterion("floorproductsurl is null");
            return (Criteria) this;
        }

        public Criteria andFloorproductsurlIsNotNull() {
            addCriterion("floorproductsurl is not null");
            return (Criteria) this;
        }

        public Criteria andFloorproductsurlEqualTo(String value) {
            addCriterion("floorproductsurl =", value, "floorproductsurl");
            return (Criteria) this;
        }

        public Criteria andFloorproductsurlNotEqualTo(String value) {
            addCriterion("floorproductsurl <>", value, "floorproductsurl");
            return (Criteria) this;
        }

        public Criteria andFloorproductsurlGreaterThan(String value) {
            addCriterion("floorproductsurl >", value, "floorproductsurl");
            return (Criteria) this;
        }

        public Criteria andFloorproductsurlGreaterThanOrEqualTo(String value) {
            addCriterion("floorproductsurl >=", value, "floorproductsurl");
            return (Criteria) this;
        }

        public Criteria andFloorproductsurlLessThan(String value) {
            addCriterion("floorproductsurl <", value, "floorproductsurl");
            return (Criteria) this;
        }

        public Criteria andFloorproductsurlLessThanOrEqualTo(String value) {
            addCriterion("floorproductsurl <=", value, "floorproductsurl");
            return (Criteria) this;
        }

        public Criteria andFloorproductsurlLike(String value) {
            addCriterion("floorproductsurl like", value, "floorproductsurl");
            return (Criteria) this;
        }

        public Criteria andFloorproductsurlNotLike(String value) {
            addCriterion("floorproductsurl not like", value, "floorproductsurl");
            return (Criteria) this;
        }

        public Criteria andFloorproductsurlIn(List<String> values) {
            addCriterion("floorproductsurl in", values, "floorproductsurl");
            return (Criteria) this;
        }

        public Criteria andFloorproductsurlNotIn(List<String> values) {
            addCriterion("floorproductsurl not in", values, "floorproductsurl");
            return (Criteria) this;
        }

        public Criteria andFloorproductsurlBetween(String value1, String value2) {
            addCriterion("floorproductsurl between", value1, value2, "floorproductsurl");
            return (Criteria) this;
        }

        public Criteria andFloorproductsurlNotBetween(String value1, String value2) {
            addCriterion("floorproductsurl not between", value1, value2, "floorproductsurl");
            return (Criteria) this;
        }

        public Criteria andRankingnameIsNull() {
            addCriterion("rankingname is null");
            return (Criteria) this;
        }

        public Criteria andRankingnameIsNotNull() {
            addCriterion("rankingname is not null");
            return (Criteria) this;
        }

        public Criteria andRankingnameEqualTo(String value) {
            addCriterion("rankingname =", value, "rankingname");
            return (Criteria) this;
        }

        public Criteria andRankingnameNotEqualTo(String value) {
            addCriterion("rankingname <>", value, "rankingname");
            return (Criteria) this;
        }

        public Criteria andRankingnameGreaterThan(String value) {
            addCriterion("rankingname >", value, "rankingname");
            return (Criteria) this;
        }

        public Criteria andRankingnameGreaterThanOrEqualTo(String value) {
            addCriterion("rankingname >=", value, "rankingname");
            return (Criteria) this;
        }

        public Criteria andRankingnameLessThan(String value) {
            addCriterion("rankingname <", value, "rankingname");
            return (Criteria) this;
        }

        public Criteria andRankingnameLessThanOrEqualTo(String value) {
            addCriterion("rankingname <=", value, "rankingname");
            return (Criteria) this;
        }

        public Criteria andRankingnameLike(String value) {
            addCriterion("rankingname like", value, "rankingname");
            return (Criteria) this;
        }

        public Criteria andRankingnameNotLike(String value) {
            addCriterion("rankingname not like", value, "rankingname");
            return (Criteria) this;
        }

        public Criteria andRankingnameIn(List<String> values) {
            addCriterion("rankingname in", values, "rankingname");
            return (Criteria) this;
        }

        public Criteria andRankingnameNotIn(List<String> values) {
            addCriterion("rankingname not in", values, "rankingname");
            return (Criteria) this;
        }

        public Criteria andRankingnameBetween(String value1, String value2) {
            addCriterion("rankingname between", value1, value2, "rankingname");
            return (Criteria) this;
        }

        public Criteria andRankingnameNotBetween(String value1, String value2) {
            addCriterion("rankingname not between", value1, value2, "rankingname");
            return (Criteria) this;
        }

        public Criteria andRankingstopIsNull() {
            addCriterion("rankingstop is null");
            return (Criteria) this;
        }

        public Criteria andRankingstopIsNotNull() {
            addCriterion("rankingstop is not null");
            return (Criteria) this;
        }

        public Criteria andRankingstopEqualTo(Short value) {
            addCriterion("rankingstop =", value, "rankingstop");
            return (Criteria) this;
        }

        public Criteria andRankingstopNotEqualTo(Short value) {
            addCriterion("rankingstop <>", value, "rankingstop");
            return (Criteria) this;
        }

        public Criteria andRankingstopGreaterThan(Short value) {
            addCriterion("rankingstop >", value, "rankingstop");
            return (Criteria) this;
        }

        public Criteria andRankingstopGreaterThanOrEqualTo(Short value) {
            addCriterion("rankingstop >=", value, "rankingstop");
            return (Criteria) this;
        }

        public Criteria andRankingstopLessThan(Short value) {
            addCriterion("rankingstop <", value, "rankingstop");
            return (Criteria) this;
        }

        public Criteria andRankingstopLessThanOrEqualTo(Short value) {
            addCriterion("rankingstop <=", value, "rankingstop");
            return (Criteria) this;
        }

        public Criteria andRankingstopIn(List<Short> values) {
            addCriterion("rankingstop in", values, "rankingstop");
            return (Criteria) this;
        }

        public Criteria andRankingstopNotIn(List<Short> values) {
            addCriterion("rankingstop not in", values, "rankingstop");
            return (Criteria) this;
        }

        public Criteria andRankingstopBetween(Short value1, Short value2) {
            addCriterion("rankingstop between", value1, value2, "rankingstop");
            return (Criteria) this;
        }

        public Criteria andRankingstopNotBetween(Short value1, Short value2) {
            addCriterion("rankingstop not between", value1, value2, "rankingstop");
            return (Criteria) this;
        }

        public Criteria andRankingprodjsonIsNull() {
            addCriterion("rankingprodjson is null");
            return (Criteria) this;
        }

        public Criteria andRankingprodjsonIsNotNull() {
            addCriterion("rankingprodjson is not null");
            return (Criteria) this;
        }

        public Criteria andRankingprodjsonEqualTo(String value) {
            addCriterion("rankingprodjson =", value, "rankingprodjson");
            return (Criteria) this;
        }

        public Criteria andRankingprodjsonNotEqualTo(String value) {
            addCriterion("rankingprodjson <>", value, "rankingprodjson");
            return (Criteria) this;
        }

        public Criteria andRankingprodjsonGreaterThan(String value) {
            addCriterion("rankingprodjson >", value, "rankingprodjson");
            return (Criteria) this;
        }

        public Criteria andRankingprodjsonGreaterThanOrEqualTo(String value) {
            addCriterion("rankingprodjson >=", value, "rankingprodjson");
            return (Criteria) this;
        }

        public Criteria andRankingprodjsonLessThan(String value) {
            addCriterion("rankingprodjson <", value, "rankingprodjson");
            return (Criteria) this;
        }

        public Criteria andRankingprodjsonLessThanOrEqualTo(String value) {
            addCriterion("rankingprodjson <=", value, "rankingprodjson");
            return (Criteria) this;
        }

        public Criteria andRankingprodjsonLike(String value) {
            addCriterion("rankingprodjson like", value, "rankingprodjson");
            return (Criteria) this;
        }

        public Criteria andRankingprodjsonNotLike(String value) {
            addCriterion("rankingprodjson not like", value, "rankingprodjson");
            return (Criteria) this;
        }

        public Criteria andRankingprodjsonIn(List<String> values) {
            addCriterion("rankingprodjson in", values, "rankingprodjson");
            return (Criteria) this;
        }

        public Criteria andRankingprodjsonNotIn(List<String> values) {
            addCriterion("rankingprodjson not in", values, "rankingprodjson");
            return (Criteria) this;
        }

        public Criteria andRankingprodjsonBetween(String value1, String value2) {
            addCriterion("rankingprodjson between", value1, value2, "rankingprodjson");
            return (Criteria) this;
        }

        public Criteria andRankingprodjsonNotBetween(String value1, String value2) {
            addCriterion("rankingprodjson not between", value1, value2, "rankingprodjson");
            return (Criteria) this;
        }

        public Criteria andFloorcolorIsNull() {
            addCriterion("floorcolor is null");
            return (Criteria) this;
        }

        public Criteria andFloorcolorIsNotNull() {
            addCriterion("floorcolor is not null");
            return (Criteria) this;
        }

        public Criteria andFloorcolorEqualTo(String value) {
            addCriterion("floorcolor =", value, "floorcolor");
            return (Criteria) this;
        }

        public Criteria andFloorcolorNotEqualTo(String value) {
            addCriterion("floorcolor <>", value, "floorcolor");
            return (Criteria) this;
        }

        public Criteria andFloorcolorGreaterThan(String value) {
            addCriterion("floorcolor >", value, "floorcolor");
            return (Criteria) this;
        }

        public Criteria andFloorcolorGreaterThanOrEqualTo(String value) {
            addCriterion("floorcolor >=", value, "floorcolor");
            return (Criteria) this;
        }

        public Criteria andFloorcolorLessThan(String value) {
            addCriterion("floorcolor <", value, "floorcolor");
            return (Criteria) this;
        }

        public Criteria andFloorcolorLessThanOrEqualTo(String value) {
            addCriterion("floorcolor <=", value, "floorcolor");
            return (Criteria) this;
        }

        public Criteria andFloorcolorLike(String value) {
            addCriterion("floorcolor like", value, "floorcolor");
            return (Criteria) this;
        }

        public Criteria andFloorcolorNotLike(String value) {
            addCriterion("floorcolor not like", value, "floorcolor");
            return (Criteria) this;
        }

        public Criteria andFloorcolorIn(List<String> values) {
            addCriterion("floorcolor in", values, "floorcolor");
            return (Criteria) this;
        }

        public Criteria andFloorcolorNotIn(List<String> values) {
            addCriterion("floorcolor not in", values, "floorcolor");
            return (Criteria) this;
        }

        public Criteria andFloorcolorBetween(String value1, String value2) {
            addCriterion("floorcolor between", value1, value2, "floorcolor");
            return (Criteria) this;
        }

        public Criteria andFloorcolorNotBetween(String value1, String value2) {
            addCriterion("floorcolor not between", value1, value2, "floorcolor");
            return (Criteria) this;
        }

        public Criteria andIsshowIsNull() {
            addCriterion("isshow is null");
            return (Criteria) this;
        }

        public Criteria andIsshowIsNotNull() {
            addCriterion("isshow is not null");
            return (Criteria) this;
        }

        public Criteria andIsshowEqualTo(Short value) {
            addCriterion("isshow =", value, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowNotEqualTo(Short value) {
            addCriterion("isshow <>", value, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowGreaterThan(Short value) {
            addCriterion("isshow >", value, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowGreaterThanOrEqualTo(Short value) {
            addCriterion("isshow >=", value, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowLessThan(Short value) {
            addCriterion("isshow <", value, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowLessThanOrEqualTo(Short value) {
            addCriterion("isshow <=", value, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowIn(List<Short> values) {
            addCriterion("isshow in", values, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowNotIn(List<Short> values) {
            addCriterion("isshow not in", values, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowBetween(Short value1, Short value2) {
            addCriterion("isshow between", value1, value2, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowNotBetween(Short value1, Short value2) {
            addCriterion("isshow not between", value1, value2, "isshow");
            return (Criteria) this;
        }

        public Criteria andSortIsNull() {
            addCriterion("sort is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("sort is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(Integer value) {
            addCriterion("sort =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(Integer value) {
            addCriterion("sort <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(Integer value) {
            addCriterion("sort >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("sort >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(Integer value) {
            addCriterion("sort <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(Integer value) {
            addCriterion("sort <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<Integer> values) {
            addCriterion("sort in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<Integer> values) {
            addCriterion("sort not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(Integer value1, Integer value2) {
            addCriterion("sort between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(Integer value1, Integer value2) {
            addCriterion("sort not between", value1, value2, "sort");
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