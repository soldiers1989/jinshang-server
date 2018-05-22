package project.jinshang.mod_product.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProductsExample() {
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

        public Criteria andLevel1IsNull() {
            addCriterion("level1 is null");
            return (Criteria) this;
        }

        public Criteria andLevel1IsNotNull() {
            addCriterion("level1 is not null");
            return (Criteria) this;
        }

        public Criteria andLevel1EqualTo(String value) {
            addCriterion("level1 =", value, "level1");
            return (Criteria) this;
        }

        public Criteria andLevel1NotEqualTo(String value) {
            addCriterion("level1 <>", value, "level1");
            return (Criteria) this;
        }

        public Criteria andLevel1GreaterThan(String value) {
            addCriterion("level1 >", value, "level1");
            return (Criteria) this;
        }

        public Criteria andLevel1GreaterThanOrEqualTo(String value) {
            addCriterion("level1 >=", value, "level1");
            return (Criteria) this;
        }

        public Criteria andLevel1LessThan(String value) {
            addCriterion("level1 <", value, "level1");
            return (Criteria) this;
        }

        public Criteria andLevel1LessThanOrEqualTo(String value) {
            addCriterion("level1 <=", value, "level1");
            return (Criteria) this;
        }

        public Criteria andLevel1Like(String value) {
            addCriterion("level1 like", value, "level1");
            return (Criteria) this;
        }

        public Criteria andLevel1NotLike(String value) {
            addCriterion("level1 not like", value, "level1");
            return (Criteria) this;
        }

        public Criteria andLevel1In(List<String> values) {
            addCriterion("level1 in", values, "level1");
            return (Criteria) this;
        }

        public Criteria andLevel1NotIn(List<String> values) {
            addCriterion("level1 not in", values, "level1");
            return (Criteria) this;
        }

        public Criteria andLevel1Between(String value1, String value2) {
            addCriterion("level1 between", value1, value2, "level1");
            return (Criteria) this;
        }

        public Criteria andLevel1NotBetween(String value1, String value2) {
            addCriterion("level1 not between", value1, value2, "level1");
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

        public Criteria andLevel2IsNull() {
            addCriterion("level2 is null");
            return (Criteria) this;
        }

        public Criteria andLevel2IsNotNull() {
            addCriterion("level2 is not null");
            return (Criteria) this;
        }

        public Criteria andLevel2EqualTo(String value) {
            addCriterion("level2 =", value, "level2");
            return (Criteria) this;
        }

        public Criteria andLevel2NotEqualTo(String value) {
            addCriterion("level2 <>", value, "level2");
            return (Criteria) this;
        }

        public Criteria andLevel2GreaterThan(String value) {
            addCriterion("level2 >", value, "level2");
            return (Criteria) this;
        }

        public Criteria andLevel2GreaterThanOrEqualTo(String value) {
            addCriterion("level2 >=", value, "level2");
            return (Criteria) this;
        }

        public Criteria andLevel2LessThan(String value) {
            addCriterion("level2 <", value, "level2");
            return (Criteria) this;
        }

        public Criteria andLevel2LessThanOrEqualTo(String value) {
            addCriterion("level2 <=", value, "level2");
            return (Criteria) this;
        }

        public Criteria andLevel2Like(String value) {
            addCriterion("level2 like", value, "level2");
            return (Criteria) this;
        }

        public Criteria andLevel2NotLike(String value) {
            addCriterion("level2 not like", value, "level2");
            return (Criteria) this;
        }

        public Criteria andLevel2In(List<String> values) {
            addCriterion("level2 in", values, "level2");
            return (Criteria) this;
        }

        public Criteria andLevel2NotIn(List<String> values) {
            addCriterion("level2 not in", values, "level2");
            return (Criteria) this;
        }

        public Criteria andLevel2Between(String value1, String value2) {
            addCriterion("level2 between", value1, value2, "level2");
            return (Criteria) this;
        }

        public Criteria andLevel2NotBetween(String value1, String value2) {
            addCriterion("level2 not between", value1, value2, "level2");
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

        public Criteria andLevel3IsNull() {
            addCriterion("level3 is null");
            return (Criteria) this;
        }

        public Criteria andLevel3IsNotNull() {
            addCriterion("level3 is not null");
            return (Criteria) this;
        }

        public Criteria andLevel3EqualTo(String value) {
            addCriterion("level3 =", value, "level3");
            return (Criteria) this;
        }

        public Criteria andLevel3NotEqualTo(String value) {
            addCriterion("level3 <>", value, "level3");
            return (Criteria) this;
        }

        public Criteria andLevel3GreaterThan(String value) {
            addCriterion("level3 >", value, "level3");
            return (Criteria) this;
        }

        public Criteria andLevel3GreaterThanOrEqualTo(String value) {
            addCriterion("level3 >=", value, "level3");
            return (Criteria) this;
        }

        public Criteria andLevel3LessThan(String value) {
            addCriterion("level3 <", value, "level3");
            return (Criteria) this;
        }

        public Criteria andLevel3LessThanOrEqualTo(String value) {
            addCriterion("level3 <=", value, "level3");
            return (Criteria) this;
        }

        public Criteria andLevel3Like(String value) {
            addCriterion("level3 like", value, "level3");
            return (Criteria) this;
        }

        public Criteria andLevel3NotLike(String value) {
            addCriterion("level3 not like", value, "level3");
            return (Criteria) this;
        }

        public Criteria andLevel3In(List<String> values) {
            addCriterion("level3 in", values, "level3");
            return (Criteria) this;
        }

        public Criteria andLevel3NotIn(List<String> values) {
            addCriterion("level3 not in", values, "level3");
            return (Criteria) this;
        }

        public Criteria andLevel3Between(String value1, String value2) {
            addCriterion("level3 between", value1, value2, "level3");
            return (Criteria) this;
        }

        public Criteria andLevel3NotBetween(String value1, String value2) {
            addCriterion("level3 not between", value1, value2, "level3");
            return (Criteria) this;
        }

        public Criteria andLevel3idIsNull() {
            addCriterion("level3id is null");
            return (Criteria) this;
        }

        public Criteria andLevel3idIsNotNull() {
            addCriterion("level3id is not null");
            return (Criteria) this;
        }

        public Criteria andLevel3idEqualTo(Long value) {
            addCriterion("level3id =", value, "level3id");
            return (Criteria) this;
        }

        public Criteria andLevel3idNotEqualTo(Long value) {
            addCriterion("level3id <>", value, "level3id");
            return (Criteria) this;
        }

        public Criteria andLevel3idGreaterThan(Long value) {
            addCriterion("level3id >", value, "level3id");
            return (Criteria) this;
        }

        public Criteria andLevel3idGreaterThanOrEqualTo(Long value) {
            addCriterion("level3id >=", value, "level3id");
            return (Criteria) this;
        }

        public Criteria andLevel3idLessThan(Long value) {
            addCriterion("level3id <", value, "level3id");
            return (Criteria) this;
        }

        public Criteria andLevel3idLessThanOrEqualTo(Long value) {
            addCriterion("level3id <=", value, "level3id");
            return (Criteria) this;
        }

        public Criteria andLevel3idIn(List<Long> values) {
            addCriterion("level3id in", values, "level3id");
            return (Criteria) this;
        }

        public Criteria andLevel3idNotIn(List<Long> values) {
            addCriterion("level3id not in", values, "level3id");
            return (Criteria) this;
        }

        public Criteria andLevel3idBetween(Long value1, Long value2) {
            addCriterion("level3id between", value1, value2, "level3id");
            return (Criteria) this;
        }

        public Criteria andLevel3idNotBetween(Long value1, Long value2) {
            addCriterion("level3id not between", value1, value2, "level3id");
            return (Criteria) this;
        }

        public Criteria andProductnoIsNull() {
            addCriterion("productno is null");
            return (Criteria) this;
        }

        public Criteria andProductnoIsNotNull() {
            addCriterion("productno is not null");
            return (Criteria) this;
        }

        public Criteria andProductnoEqualTo(String value) {
            addCriterion("productno =", value, "productno");
            return (Criteria) this;
        }

        public Criteria andProductnoNotEqualTo(String value) {
            addCriterion("productno <>", value, "productno");
            return (Criteria) this;
        }

        public Criteria andProductnoGreaterThan(String value) {
            addCriterion("productno >", value, "productno");
            return (Criteria) this;
        }

        public Criteria andProductnoGreaterThanOrEqualTo(String value) {
            addCriterion("productno >=", value, "productno");
            return (Criteria) this;
        }

        public Criteria andProductnoLessThan(String value) {
            addCriterion("productno <", value, "productno");
            return (Criteria) this;
        }

        public Criteria andProductnoLessThanOrEqualTo(String value) {
            addCriterion("productno <=", value, "productno");
            return (Criteria) this;
        }

        public Criteria andProductnoLike(String value) {
            addCriterion("productno like", value, "productno");
            return (Criteria) this;
        }

        public Criteria andProductnoNotLike(String value) {
            addCriterion("productno not like", value, "productno");
            return (Criteria) this;
        }

        public Criteria andProductnoIn(List<String> values) {
            addCriterion("productno in", values, "productno");
            return (Criteria) this;
        }

        public Criteria andProductnoNotIn(List<String> values) {
            addCriterion("productno not in", values, "productno");
            return (Criteria) this;
        }

        public Criteria andProductnoBetween(String value1, String value2) {
            addCriterion("productno between", value1, value2, "productno");
            return (Criteria) this;
        }

        public Criteria andProductnoNotBetween(String value1, String value2) {
            addCriterion("productno not between", value1, value2, "productno");
            return (Criteria) this;
        }

        public Criteria andProductnameidIsNull() {
            addCriterion("productnameid is null");
            return (Criteria) this;
        }

        public Criteria andProductnameidIsNotNull() {
            addCriterion("productnameid is not null");
            return (Criteria) this;
        }

        public Criteria andProductnameidEqualTo(Long value) {
            addCriterion("productnameid =", value, "productnameid");
            return (Criteria) this;
        }

        public Criteria andProductnameidNotEqualTo(Long value) {
            addCriterion("productnameid <>", value, "productnameid");
            return (Criteria) this;
        }

        public Criteria andProductnameidGreaterThan(Long value) {
            addCriterion("productnameid >", value, "productnameid");
            return (Criteria) this;
        }

        public Criteria andProductnameidGreaterThanOrEqualTo(Long value) {
            addCriterion("productnameid >=", value, "productnameid");
            return (Criteria) this;
        }

        public Criteria andProductnameidLessThan(Long value) {
            addCriterion("productnameid <", value, "productnameid");
            return (Criteria) this;
        }

        public Criteria andProductnameidLessThanOrEqualTo(Long value) {
            addCriterion("productnameid <=", value, "productnameid");
            return (Criteria) this;
        }

        public Criteria andProductnameidIn(List<Long> values) {
            addCriterion("productnameid in", values, "productnameid");
            return (Criteria) this;
        }

        public Criteria andProductnameidNotIn(List<Long> values) {
            addCriterion("productnameid not in", values, "productnameid");
            return (Criteria) this;
        }

        public Criteria andProductnameidBetween(Long value1, Long value2) {
            addCriterion("productnameid between", value1, value2, "productnameid");
            return (Criteria) this;
        }

        public Criteria andProductnameidNotBetween(Long value1, Long value2) {
            addCriterion("productnameid not between", value1, value2, "productnameid");
            return (Criteria) this;
        }

        public Criteria andProductnameIsNull() {
            addCriterion("productname is null");
            return (Criteria) this;
        }

        public Criteria andProductnameIsNotNull() {
            addCriterion("productname is not null");
            return (Criteria) this;
        }

        public Criteria andProductnameEqualTo(String value) {
            addCriterion("productname =", value, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameNotEqualTo(String value) {
            addCriterion("productname <>", value, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameGreaterThan(String value) {
            addCriterion("productname >", value, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameGreaterThanOrEqualTo(String value) {
            addCriterion("productname >=", value, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameLessThan(String value) {
            addCriterion("productname <", value, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameLessThanOrEqualTo(String value) {
            addCriterion("productname <=", value, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameLike(String value) {
            addCriterion("productname like", value, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameNotLike(String value) {
            addCriterion("productname not like", value, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameIn(List<String> values) {
            addCriterion("productname in", values, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameNotIn(List<String> values) {
            addCriterion("productname not in", values, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameBetween(String value1, String value2) {
            addCriterion("productname between", value1, value2, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameNotBetween(String value1, String value2) {
            addCriterion("productname not between", value1, value2, "productname");
            return (Criteria) this;
        }

        public Criteria andProductaliasIsNull() {
            addCriterion("productalias is null");
            return (Criteria) this;
        }

        public Criteria andProductaliasIsNotNull() {
            addCriterion("productalias is not null");
            return (Criteria) this;
        }

        public Criteria andProductaliasEqualTo(String value) {
            addCriterion("productalias =", value, "productalias");
            return (Criteria) this;
        }

        public Criteria andProductaliasNotEqualTo(String value) {
            addCriterion("productalias <>", value, "productalias");
            return (Criteria) this;
        }

        public Criteria andProductaliasGreaterThan(String value) {
            addCriterion("productalias >", value, "productalias");
            return (Criteria) this;
        }

        public Criteria andProductaliasGreaterThanOrEqualTo(String value) {
            addCriterion("productalias >=", value, "productalias");
            return (Criteria) this;
        }

        public Criteria andProductaliasLessThan(String value) {
            addCriterion("productalias <", value, "productalias");
            return (Criteria) this;
        }

        public Criteria andProductaliasLessThanOrEqualTo(String value) {
            addCriterion("productalias <=", value, "productalias");
            return (Criteria) this;
        }

        public Criteria andProductaliasLike(String value) {
            addCriterion("productalias like", value, "productalias");
            return (Criteria) this;
        }

        public Criteria andProductaliasNotLike(String value) {
            addCriterion("productalias not like", value, "productalias");
            return (Criteria) this;
        }

        public Criteria andProductaliasIn(List<String> values) {
            addCriterion("productalias in", values, "productalias");
            return (Criteria) this;
        }

        public Criteria andProductaliasNotIn(List<String> values) {
            addCriterion("productalias not in", values, "productalias");
            return (Criteria) this;
        }

        public Criteria andProductaliasBetween(String value1, String value2) {
            addCriterion("productalias between", value1, value2, "productalias");
            return (Criteria) this;
        }

        public Criteria andProductaliasNotBetween(String value1, String value2) {
            addCriterion("productalias not between", value1, value2, "productalias");
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

        public Criteria andBrandidIsNull() {
            addCriterion("brandid is null");
            return (Criteria) this;
        }

        public Criteria andBrandidIsNotNull() {
            addCriterion("brandid is not null");
            return (Criteria) this;
        }

        public Criteria andBrandidEqualTo(Long value) {
            addCriterion("brandid =", value, "brandid");
            return (Criteria) this;
        }

        public Criteria andBrandidNotEqualTo(Long value) {
            addCriterion("brandid <>", value, "brandid");
            return (Criteria) this;
        }

        public Criteria andBrandidGreaterThan(Long value) {
            addCriterion("brandid >", value, "brandid");
            return (Criteria) this;
        }

        public Criteria andBrandidGreaterThanOrEqualTo(Long value) {
            addCriterion("brandid >=", value, "brandid");
            return (Criteria) this;
        }

        public Criteria andBrandidLessThan(Long value) {
            addCriterion("brandid <", value, "brandid");
            return (Criteria) this;
        }

        public Criteria andBrandidLessThanOrEqualTo(Long value) {
            addCriterion("brandid <=", value, "brandid");
            return (Criteria) this;
        }

        public Criteria andBrandidIn(List<Long> values) {
            addCriterion("brandid in", values, "brandid");
            return (Criteria) this;
        }

        public Criteria andBrandidNotIn(List<Long> values) {
            addCriterion("brandid not in", values, "brandid");
            return (Criteria) this;
        }

        public Criteria andBrandidBetween(Long value1, Long value2) {
            addCriterion("brandid between", value1, value2, "brandid");
            return (Criteria) this;
        }

        public Criteria andBrandidNotBetween(Long value1, Long value2) {
            addCriterion("brandid not between", value1, value2, "brandid");
            return (Criteria) this;
        }

        public Criteria andCardnumidIsNull() {
            addCriterion("cardnumid is null");
            return (Criteria) this;
        }

        public Criteria andCardnumidIsNotNull() {
            addCriterion("cardnumid is not null");
            return (Criteria) this;
        }

        public Criteria andCardnumidEqualTo(Long value) {
            addCriterion("cardnumid =", value, "cardnumid");
            return (Criteria) this;
        }

        public Criteria andCardnumidNotEqualTo(Long value) {
            addCriterion("cardnumid <>", value, "cardnumid");
            return (Criteria) this;
        }

        public Criteria andCardnumidGreaterThan(Long value) {
            addCriterion("cardnumid >", value, "cardnumid");
            return (Criteria) this;
        }

        public Criteria andCardnumidGreaterThanOrEqualTo(Long value) {
            addCriterion("cardnumid >=", value, "cardnumid");
            return (Criteria) this;
        }

        public Criteria andCardnumidLessThan(Long value) {
            addCriterion("cardnumid <", value, "cardnumid");
            return (Criteria) this;
        }

        public Criteria andCardnumidLessThanOrEqualTo(Long value) {
            addCriterion("cardnumid <=", value, "cardnumid");
            return (Criteria) this;
        }

        public Criteria andCardnumidIn(List<Long> values) {
            addCriterion("cardnumid in", values, "cardnumid");
            return (Criteria) this;
        }

        public Criteria andCardnumidNotIn(List<Long> values) {
            addCriterion("cardnumid not in", values, "cardnumid");
            return (Criteria) this;
        }

        public Criteria andCardnumidBetween(Long value1, Long value2) {
            addCriterion("cardnumid between", value1, value2, "cardnumid");
            return (Criteria) this;
        }

        public Criteria andCardnumidNotBetween(Long value1, Long value2) {
            addCriterion("cardnumid not between", value1, value2, "cardnumid");
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

        public Criteria andMaterialidIsNull() {
            addCriterion("materialid is null");
            return (Criteria) this;
        }

        public Criteria andMaterialidIsNotNull() {
            addCriterion("materialid is not null");
            return (Criteria) this;
        }

        public Criteria andMaterialidEqualTo(Long value) {
            addCriterion("materialid =", value, "materialid");
            return (Criteria) this;
        }

        public Criteria andMaterialidNotEqualTo(Long value) {
            addCriterion("materialid <>", value, "materialid");
            return (Criteria) this;
        }

        public Criteria andMaterialidGreaterThan(Long value) {
            addCriterion("materialid >", value, "materialid");
            return (Criteria) this;
        }

        public Criteria andMaterialidGreaterThanOrEqualTo(Long value) {
            addCriterion("materialid >=", value, "materialid");
            return (Criteria) this;
        }

        public Criteria andMaterialidLessThan(Long value) {
            addCriterion("materialid <", value, "materialid");
            return (Criteria) this;
        }

        public Criteria andMaterialidLessThanOrEqualTo(Long value) {
            addCriterion("materialid <=", value, "materialid");
            return (Criteria) this;
        }

        public Criteria andMaterialidIn(List<Long> values) {
            addCriterion("materialid in", values, "materialid");
            return (Criteria) this;
        }

        public Criteria andMaterialidNotIn(List<Long> values) {
            addCriterion("materialid not in", values, "materialid");
            return (Criteria) this;
        }

        public Criteria andMaterialidBetween(Long value1, Long value2) {
            addCriterion("materialid between", value1, value2, "materialid");
            return (Criteria) this;
        }

        public Criteria andMaterialidNotBetween(Long value1, Long value2) {
            addCriterion("materialid not between", value1, value2, "materialid");
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

        public Criteria andSurfacetreatmentIsNull() {
            addCriterion("surfacetreatment is null");
            return (Criteria) this;
        }

        public Criteria andSurfacetreatmentIsNotNull() {
            addCriterion("surfacetreatment is not null");
            return (Criteria) this;
        }

        public Criteria andSurfacetreatmentEqualTo(String value) {
            addCriterion("surfacetreatment =", value, "surfacetreatment");
            return (Criteria) this;
        }

        public Criteria andSurfacetreatmentNotEqualTo(String value) {
            addCriterion("surfacetreatment <>", value, "surfacetreatment");
            return (Criteria) this;
        }

        public Criteria andSurfacetreatmentGreaterThan(String value) {
            addCriterion("surfacetreatment >", value, "surfacetreatment");
            return (Criteria) this;
        }

        public Criteria andSurfacetreatmentGreaterThanOrEqualTo(String value) {
            addCriterion("surfacetreatment >=", value, "surfacetreatment");
            return (Criteria) this;
        }

        public Criteria andSurfacetreatmentLessThan(String value) {
            addCriterion("surfacetreatment <", value, "surfacetreatment");
            return (Criteria) this;
        }

        public Criteria andSurfacetreatmentLessThanOrEqualTo(String value) {
            addCriterion("surfacetreatment <=", value, "surfacetreatment");
            return (Criteria) this;
        }

        public Criteria andSurfacetreatmentLike(String value) {
            addCriterion("surfacetreatment like", value, "surfacetreatment");
            return (Criteria) this;
        }

        public Criteria andSurfacetreatmentNotLike(String value) {
            addCriterion("surfacetreatment not like", value, "surfacetreatment");
            return (Criteria) this;
        }

        public Criteria andSurfacetreatmentIn(List<String> values) {
            addCriterion("surfacetreatment in", values, "surfacetreatment");
            return (Criteria) this;
        }

        public Criteria andSurfacetreatmentNotIn(List<String> values) {
            addCriterion("surfacetreatment not in", values, "surfacetreatment");
            return (Criteria) this;
        }

        public Criteria andSurfacetreatmentBetween(String value1, String value2) {
            addCriterion("surfacetreatment between", value1, value2, "surfacetreatment");
            return (Criteria) this;
        }

        public Criteria andSurfacetreatmentNotBetween(String value1, String value2) {
            addCriterion("surfacetreatment not between", value1, value2, "surfacetreatment");
            return (Criteria) this;
        }

        public Criteria andPackagetypeIsNull() {
            addCriterion("packagetype is null");
            return (Criteria) this;
        }

        public Criteria andPackagetypeIsNotNull() {
            addCriterion("packagetype is not null");
            return (Criteria) this;
        }

        public Criteria andPackagetypeEqualTo(String value) {
            addCriterion("packagetype =", value, "packagetype");
            return (Criteria) this;
        }

        public Criteria andPackagetypeNotEqualTo(String value) {
            addCriterion("packagetype <>", value, "packagetype");
            return (Criteria) this;
        }

        public Criteria andPackagetypeGreaterThan(String value) {
            addCriterion("packagetype >", value, "packagetype");
            return (Criteria) this;
        }

        public Criteria andPackagetypeGreaterThanOrEqualTo(String value) {
            addCriterion("packagetype >=", value, "packagetype");
            return (Criteria) this;
        }

        public Criteria andPackagetypeLessThan(String value) {
            addCriterion("packagetype <", value, "packagetype");
            return (Criteria) this;
        }

        public Criteria andPackagetypeLessThanOrEqualTo(String value) {
            addCriterion("packagetype <=", value, "packagetype");
            return (Criteria) this;
        }

        public Criteria andPackagetypeLike(String value) {
            addCriterion("packagetype like", value, "packagetype");
            return (Criteria) this;
        }

        public Criteria andPackagetypeNotLike(String value) {
            addCriterion("packagetype not like", value, "packagetype");
            return (Criteria) this;
        }

        public Criteria andPackagetypeIn(List<String> values) {
            addCriterion("packagetype in", values, "packagetype");
            return (Criteria) this;
        }

        public Criteria andPackagetypeNotIn(List<String> values) {
            addCriterion("packagetype not in", values, "packagetype");
            return (Criteria) this;
        }

        public Criteria andPackagetypeBetween(String value1, String value2) {
            addCriterion("packagetype between", value1, value2, "packagetype");
            return (Criteria) this;
        }

        public Criteria andPackagetypeNotBetween(String value1, String value2) {
            addCriterion("packagetype not between", value1, value2, "packagetype");
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

        public Criteria andPddrawingIsNull() {
            addCriterion("pddrawing is null");
            return (Criteria) this;
        }

        public Criteria andPddrawingIsNotNull() {
            addCriterion("pddrawing is not null");
            return (Criteria) this;
        }

        public Criteria andPddrawingEqualTo(Object value) {
            addCriterion("pddrawing =", value, "pddrawing");
            return (Criteria) this;
        }

        public Criteria andPddrawingNotEqualTo(Object value) {
            addCriterion("pddrawing <>", value, "pddrawing");
            return (Criteria) this;
        }

        public Criteria andPddrawingGreaterThan(Object value) {
            addCriterion("pddrawing >", value, "pddrawing");
            return (Criteria) this;
        }

        public Criteria andPddrawingGreaterThanOrEqualTo(Object value) {
            addCriterion("pddrawing >=", value, "pddrawing");
            return (Criteria) this;
        }

        public Criteria andPddrawingLessThan(Object value) {
            addCriterion("pddrawing <", value, "pddrawing");
            return (Criteria) this;
        }

        public Criteria andPddrawingLessThanOrEqualTo(Object value) {
            addCriterion("pddrawing <=", value, "pddrawing");
            return (Criteria) this;
        }

        public Criteria andPddrawingIn(List<Object> values) {
            addCriterion("pddrawing in", values, "pddrawing");
            return (Criteria) this;
        }

        public Criteria andPddrawingNotIn(List<Object> values) {
            addCriterion("pddrawing not in", values, "pddrawing");
            return (Criteria) this;
        }

        public Criteria andPddrawingBetween(Object value1, Object value2) {
            addCriterion("pddrawing between", value1, value2, "pddrawing");
            return (Criteria) this;
        }

        public Criteria andPddrawingNotBetween(Object value1, Object value2) {
            addCriterion("pddrawing not between", value1, value2, "pddrawing");
            return (Criteria) this;
        }

        public Criteria andPdpictureIsNull() {
            addCriterion("pdpicture is null");
            return (Criteria) this;
        }

        public Criteria andPdpictureIsNotNull() {
            addCriterion("pdpicture is not null");
            return (Criteria) this;
        }

        public Criteria andPdpictureEqualTo(Object value) {
            addCriterion("pdpicture =", value, "pdpicture");
            return (Criteria) this;
        }

        public Criteria andPdpictureNotEqualTo(Object value) {
            addCriterion("pdpicture <>", value, "pdpicture");
            return (Criteria) this;
        }

        public Criteria andPdpictureGreaterThan(Object value) {
            addCriterion("pdpicture >", value, "pdpicture");
            return (Criteria) this;
        }

        public Criteria andPdpictureGreaterThanOrEqualTo(Object value) {
            addCriterion("pdpicture >=", value, "pdpicture");
            return (Criteria) this;
        }

        public Criteria andPdpictureLessThan(Object value) {
            addCriterion("pdpicture <", value, "pdpicture");
            return (Criteria) this;
        }

        public Criteria andPdpictureLessThanOrEqualTo(Object value) {
            addCriterion("pdpicture <=", value, "pdpicture");
            return (Criteria) this;
        }

        public Criteria andPdpictureIn(List<Object> values) {
            addCriterion("pdpicture in", values, "pdpicture");
            return (Criteria) this;
        }

        public Criteria andPdpictureNotIn(List<Object> values) {
            addCriterion("pdpicture not in", values, "pdpicture");
            return (Criteria) this;
        }

        public Criteria andPdpictureBetween(Object value1, Object value2) {
            addCriterion("pdpicture between", value1, value2, "pdpicture");
            return (Criteria) this;
        }

        public Criteria andPdpictureNotBetween(Object value1, Object value2) {
            addCriterion("pdpicture not between", value1, value2, "pdpicture");
            return (Criteria) this;
        }

        public Criteria andPddesIsNull() {
            addCriterion("pddes is null");
            return (Criteria) this;
        }

        public Criteria andPddesIsNotNull() {
            addCriterion("pddes is not null");
            return (Criteria) this;
        }

        public Criteria andPddesEqualTo(String value) {
            addCriterion("pddes =", value, "pddes");
            return (Criteria) this;
        }

        public Criteria andPddesNotEqualTo(String value) {
            addCriterion("pddes <>", value, "pddes");
            return (Criteria) this;
        }

        public Criteria andPddesGreaterThan(String value) {
            addCriterion("pddes >", value, "pddes");
            return (Criteria) this;
        }

        public Criteria andPddesGreaterThanOrEqualTo(String value) {
            addCriterion("pddes >=", value, "pddes");
            return (Criteria) this;
        }

        public Criteria andPddesLessThan(String value) {
            addCriterion("pddes <", value, "pddes");
            return (Criteria) this;
        }

        public Criteria andPddesLessThanOrEqualTo(String value) {
            addCriterion("pddes <=", value, "pddes");
            return (Criteria) this;
        }

        public Criteria andPddesLike(String value) {
            addCriterion("pddes like", value, "pddes");
            return (Criteria) this;
        }

        public Criteria andPddesNotLike(String value) {
            addCriterion("pddes not like", value, "pddes");
            return (Criteria) this;
        }

        public Criteria andPddesIn(List<String> values) {
            addCriterion("pddes in", values, "pddes");
            return (Criteria) this;
        }

        public Criteria andPddesNotIn(List<String> values) {
            addCriterion("pddes not in", values, "pddes");
            return (Criteria) this;
        }

        public Criteria andPddesBetween(String value1, String value2) {
            addCriterion("pddes between", value1, value2, "pddes");
            return (Criteria) this;
        }

        public Criteria andPddesNotBetween(String value1, String value2) {
            addCriterion("pddes not between", value1, value2, "pddes");
            return (Criteria) this;
        }

        public Criteria andCardnumIsNull() {
            addCriterion("cardnum is null");
            return (Criteria) this;
        }

        public Criteria andCardnumIsNotNull() {
            addCriterion("cardnum is not null");
            return (Criteria) this;
        }

        public Criteria andCardnumEqualTo(String value) {
            addCriterion("cardnum =", value, "cardnum");
            return (Criteria) this;
        }

        public Criteria andCardnumNotEqualTo(String value) {
            addCriterion("cardnum <>", value, "cardnum");
            return (Criteria) this;
        }

        public Criteria andCardnumGreaterThan(String value) {
            addCriterion("cardnum >", value, "cardnum");
            return (Criteria) this;
        }

        public Criteria andCardnumGreaterThanOrEqualTo(String value) {
            addCriterion("cardnum >=", value, "cardnum");
            return (Criteria) this;
        }

        public Criteria andCardnumLessThan(String value) {
            addCriterion("cardnum <", value, "cardnum");
            return (Criteria) this;
        }

        public Criteria andCardnumLessThanOrEqualTo(String value) {
            addCriterion("cardnum <=", value, "cardnum");
            return (Criteria) this;
        }

        public Criteria andCardnumLike(String value) {
            addCriterion("cardnum like", value, "cardnum");
            return (Criteria) this;
        }

        public Criteria andCardnumNotLike(String value) {
            addCriterion("cardnum not like", value, "cardnum");
            return (Criteria) this;
        }

        public Criteria andCardnumIn(List<String> values) {
            addCriterion("cardnum in", values, "cardnum");
            return (Criteria) this;
        }

        public Criteria andCardnumNotIn(List<String> values) {
            addCriterion("cardnum not in", values, "cardnum");
            return (Criteria) this;
        }

        public Criteria andCardnumBetween(String value1, String value2) {
            addCriterion("cardnum between", value1, value2, "cardnum");
            return (Criteria) this;
        }

        public Criteria andCardnumNotBetween(String value1, String value2) {
            addCriterion("cardnum not between", value1, value2, "cardnum");
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

        public Criteria andAttributeIsNull() {
            addCriterion("attribute is null");
            return (Criteria) this;
        }

        public Criteria andAttributeIsNotNull() {
            addCriterion("attribute is not null");
            return (Criteria) this;
        }

        public Criteria andAttributeEqualTo(String value) {
            addCriterion("attribute =", value, "attribute");
            return (Criteria) this;
        }

        public Criteria andAttributeNotEqualTo(String value) {
            addCriterion("attribute <>", value, "attribute");
            return (Criteria) this;
        }

        public Criteria andAttributeGreaterThan(String value) {
            addCriterion("attribute >", value, "attribute");
            return (Criteria) this;
        }

        public Criteria andAttributeGreaterThanOrEqualTo(String value) {
            addCriterion("attribute >=", value, "attribute");
            return (Criteria) this;
        }

        public Criteria andAttributeLessThan(String value) {
            addCriterion("attribute <", value, "attribute");
            return (Criteria) this;
        }

        public Criteria andAttributeLessThanOrEqualTo(String value) {
            addCriterion("attribute <=", value, "attribute");
            return (Criteria) this;
        }

        public Criteria andAttributeLike(String value) {
            addCriterion("attribute like", value, "attribute");
            return (Criteria) this;
        }

        public Criteria andAttributeNotLike(String value) {
            addCriterion("attribute not like", value, "attribute");
            return (Criteria) this;
        }

        public Criteria andAttributeIn(List<String> values) {
            addCriterion("attribute in", values, "attribute");
            return (Criteria) this;
        }

        public Criteria andAttributeNotIn(List<String> values) {
            addCriterion("attribute not in", values, "attribute");
            return (Criteria) this;
        }

        public Criteria andAttributeBetween(String value1, String value2) {
            addCriterion("attribute between", value1, value2, "attribute");
            return (Criteria) this;
        }

        public Criteria andAttributeNotBetween(String value1, String value2) {
            addCriterion("attribute not between", value1, value2, "attribute");
            return (Criteria) this;
        }

        public Criteria andProdstrIsNull() {
            addCriterion("prodstr is null");
            return (Criteria) this;
        }

        public Criteria andProdstrIsNotNull() {
            addCriterion("prodstr is not null");
            return (Criteria) this;
        }

        public Criteria andProdstrEqualTo(String value) {
            addCriterion("prodstr =", value, "prodstr");
            return (Criteria) this;
        }

        public Criteria andProdstrNotEqualTo(String value) {
            addCriterion("prodstr <>", value, "prodstr");
            return (Criteria) this;
        }

        public Criteria andProdstrGreaterThan(String value) {
            addCriterion("prodstr >", value, "prodstr");
            return (Criteria) this;
        }

        public Criteria andProdstrGreaterThanOrEqualTo(String value) {
            addCriterion("prodstr >=", value, "prodstr");
            return (Criteria) this;
        }

        public Criteria andProdstrLessThan(String value) {
            addCriterion("prodstr <", value, "prodstr");
            return (Criteria) this;
        }

        public Criteria andProdstrLessThanOrEqualTo(String value) {
            addCriterion("prodstr <=", value, "prodstr");
            return (Criteria) this;
        }

        public Criteria andProdstrLike(String value) {
            addCriterion("prodstr like", value, "prodstr");
            return (Criteria) this;
        }

        public Criteria andProdstrNotLike(String value) {
            addCriterion("prodstr not like", value, "prodstr");
            return (Criteria) this;
        }

        public Criteria andProdstrIn(List<String> values) {
            addCriterion("prodstr in", values, "prodstr");
            return (Criteria) this;
        }

        public Criteria andProdstrNotIn(List<String> values) {
            addCriterion("prodstr not in", values, "prodstr");
            return (Criteria) this;
        }

        public Criteria andProdstrBetween(String value1, String value2) {
            addCriterion("prodstr between", value1, value2, "prodstr");
            return (Criteria) this;
        }

        public Criteria andProdstrNotBetween(String value1, String value2) {
            addCriterion("prodstr not between", value1, value2, "prodstr");
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