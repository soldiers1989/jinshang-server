package project.jinshang.mod_product.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProductInfoExample() {
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

        public Criteria andProductsnoIsNull() {
            addCriterion("productsno is null");
            return (Criteria) this;
        }

        public Criteria andProductsnoIsNotNull() {
            addCriterion("productsno is not null");
            return (Criteria) this;
        }

        public Criteria andProductsnoEqualTo(String value) {
            addCriterion("productsno =", value, "productsno");
            return (Criteria) this;
        }

        public Criteria andProductsnoNotEqualTo(String value) {
            addCriterion("productsno <>", value, "productsno");
            return (Criteria) this;
        }

        public Criteria andProductsnoGreaterThan(String value) {
            addCriterion("productsno >", value, "productsno");
            return (Criteria) this;
        }

        public Criteria andProductsnoGreaterThanOrEqualTo(String value) {
            addCriterion("productsno >=", value, "productsno");
            return (Criteria) this;
        }

        public Criteria andProductsnoLessThan(String value) {
            addCriterion("productsno <", value, "productsno");
            return (Criteria) this;
        }

        public Criteria andProductsnoLessThanOrEqualTo(String value) {
            addCriterion("productsno <=", value, "productsno");
            return (Criteria) this;
        }

        public Criteria andProductsnoLike(String value) {
            addCriterion("productsno like", value, "productsno");
            return (Criteria) this;
        }

        public Criteria andProductsnoNotLike(String value) {
            addCriterion("productsno not like", value, "productsno");
            return (Criteria) this;
        }

        public Criteria andProductsnoIn(List<String> values) {
            addCriterion("productsno in", values, "productsno");
            return (Criteria) this;
        }

        public Criteria andProductsnoNotIn(List<String> values) {
            addCriterion("productsno not in", values, "productsno");
            return (Criteria) this;
        }

        public Criteria andProductsnoBetween(String value1, String value2) {
            addCriterion("productsno between", value1, value2, "productsno");
            return (Criteria) this;
        }

        public Criteria andProductsnoNotBetween(String value1, String value2) {
            addCriterion("productsno not between", value1, value2, "productsno");
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

        public Criteria andSubtitleIsNull() {
            addCriterion("subtitle is null");
            return (Criteria) this;
        }

        public Criteria andSubtitleIsNotNull() {
            addCriterion("subtitle is not null");
            return (Criteria) this;
        }

        public Criteria andSubtitleEqualTo(String value) {
            addCriterion("subtitle =", value, "subtitle");
            return (Criteria) this;
        }

        public Criteria andSubtitleNotEqualTo(String value) {
            addCriterion("subtitle <>", value, "subtitle");
            return (Criteria) this;
        }

        public Criteria andSubtitleGreaterThan(String value) {
            addCriterion("subtitle >", value, "subtitle");
            return (Criteria) this;
        }

        public Criteria andSubtitleGreaterThanOrEqualTo(String value) {
            addCriterion("subtitle >=", value, "subtitle");
            return (Criteria) this;
        }

        public Criteria andSubtitleLessThan(String value) {
            addCriterion("subtitle <", value, "subtitle");
            return (Criteria) this;
        }

        public Criteria andSubtitleLessThanOrEqualTo(String value) {
            addCriterion("subtitle <=", value, "subtitle");
            return (Criteria) this;
        }

        public Criteria andSubtitleLike(String value) {
            addCriterion("subtitle like", value, "subtitle");
            return (Criteria) this;
        }

        public Criteria andSubtitleNotLike(String value) {
            addCriterion("subtitle not like", value, "subtitle");
            return (Criteria) this;
        }

        public Criteria andSubtitleIn(List<String> values) {
            addCriterion("subtitle in", values, "subtitle");
            return (Criteria) this;
        }

        public Criteria andSubtitleNotIn(List<String> values) {
            addCriterion("subtitle not in", values, "subtitle");
            return (Criteria) this;
        }

        public Criteria andSubtitleBetween(String value1, String value2) {
            addCriterion("subtitle between", value1, value2, "subtitle");
            return (Criteria) this;
        }

        public Criteria andSubtitleNotBetween(String value1, String value2) {
            addCriterion("subtitle not between", value1, value2, "subtitle");
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

        public Criteria andProducttypeIsNull() {
            addCriterion("producttype is null");
            return (Criteria) this;
        }

        public Criteria andProducttypeIsNotNull() {
            addCriterion("producttype is not null");
            return (Criteria) this;
        }

        public Criteria andProducttypeEqualTo(String value) {
            addCriterion("producttype =", value, "producttype");
            return (Criteria) this;
        }

        public Criteria andProducttypeNotEqualTo(String value) {
            addCriterion("producttype <>", value, "producttype");
            return (Criteria) this;
        }

        public Criteria andProducttypeGreaterThan(String value) {
            addCriterion("producttype >", value, "producttype");
            return (Criteria) this;
        }

        public Criteria andProducttypeGreaterThanOrEqualTo(String value) {
            addCriterion("producttype >=", value, "producttype");
            return (Criteria) this;
        }

        public Criteria andProducttypeLessThan(String value) {
            addCriterion("producttype <", value, "producttype");
            return (Criteria) this;
        }

        public Criteria andProducttypeLessThanOrEqualTo(String value) {
            addCriterion("producttype <=", value, "producttype");
            return (Criteria) this;
        }

        public Criteria andProducttypeLike(String value) {
            addCriterion("producttype like", value, "producttype");
            return (Criteria) this;
        }

        public Criteria andProducttypeNotLike(String value) {
            addCriterion("producttype not like", value, "producttype");
            return (Criteria) this;
        }

        public Criteria andProducttypeIn(List<String> values) {
            addCriterion("producttype in", values, "producttype");
            return (Criteria) this;
        }

        public Criteria andProducttypeNotIn(List<String> values) {
            addCriterion("producttype not in", values, "producttype");
            return (Criteria) this;
        }

        public Criteria andProducttypeBetween(String value1, String value2) {
            addCriterion("producttype between", value1, value2, "producttype");
            return (Criteria) this;
        }

        public Criteria andProducttypeNotBetween(String value1, String value2) {
            addCriterion("producttype not between", value1, value2, "producttype");
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

        public Criteria andRecommendedIsNull() {
            addCriterion("recommended is null");
            return (Criteria) this;
        }

        public Criteria andRecommendedIsNotNull() {
            addCriterion("recommended is not null");
            return (Criteria) this;
        }

        public Criteria andRecommendedEqualTo(Boolean value) {
            addCriterion("recommended =", value, "recommended");
            return (Criteria) this;
        }

        public Criteria andRecommendedNotEqualTo(Boolean value) {
            addCriterion("recommended <>", value, "recommended");
            return (Criteria) this;
        }

        public Criteria andRecommendedGreaterThan(Boolean value) {
            addCriterion("recommended >", value, "recommended");
            return (Criteria) this;
        }

        public Criteria andRecommendedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("recommended >=", value, "recommended");
            return (Criteria) this;
        }

        public Criteria andRecommendedLessThan(Boolean value) {
            addCriterion("recommended <", value, "recommended");
            return (Criteria) this;
        }

        public Criteria andRecommendedLessThanOrEqualTo(Boolean value) {
            addCriterion("recommended <=", value, "recommended");
            return (Criteria) this;
        }

        public Criteria andRecommendedIn(List<Boolean> values) {
            addCriterion("recommended in", values, "recommended");
            return (Criteria) this;
        }

        public Criteria andRecommendedNotIn(List<Boolean> values) {
            addCriterion("recommended not in", values, "recommended");
            return (Criteria) this;
        }

        public Criteria andRecommendedBetween(Boolean value1, Boolean value2) {
            addCriterion("recommended between", value1, value2, "recommended");
            return (Criteria) this;
        }

        public Criteria andRecommendedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("recommended not between", value1, value2, "recommended");
            return (Criteria) this;
        }

        public Criteria andPdstateIsNull() {
            addCriterion("pdstate is null");
            return (Criteria) this;
        }

        public Criteria andPdstateIsNotNull() {
            addCriterion("pdstate is not null");
            return (Criteria) this;
        }

        public Criteria andPdstateEqualTo(Short value) {
            addCriterion("pdstate =", value, "pdstate");
            return (Criteria) this;
        }

        public Criteria andPdstateNotEqualTo(Short value) {
            addCriterion("pdstate <>", value, "pdstate");
            return (Criteria) this;
        }

        public Criteria andPdstateGreaterThan(Short value) {
            addCriterion("pdstate >", value, "pdstate");
            return (Criteria) this;
        }

        public Criteria andPdstateGreaterThanOrEqualTo(Short value) {
            addCriterion("pdstate >=", value, "pdstate");
            return (Criteria) this;
        }

        public Criteria andPdstateLessThan(Short value) {
            addCriterion("pdstate <", value, "pdstate");
            return (Criteria) this;
        }

        public Criteria andPdstateLessThanOrEqualTo(Short value) {
            addCriterion("pdstate <=", value, "pdstate");
            return (Criteria) this;
        }

        public Criteria andPdstateIn(List<Short> values) {
            addCriterion("pdstate in", values, "pdstate");
            return (Criteria) this;
        }

        public Criteria andPdstateNotIn(List<Short> values) {
            addCriterion("pdstate not in", values, "pdstate");
            return (Criteria) this;
        }

        public Criteria andPdstateBetween(Short value1, Short value2) {
            addCriterion("pdstate between", value1, value2, "pdstate");
            return (Criteria) this;
        }

        public Criteria andPdstateNotBetween(Short value1, Short value2) {
            addCriterion("pdstate not between", value1, value2, "pdstate");
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

        public Criteria andSpecificationparamIsNull() {
            addCriterion("specificationparam is null");
            return (Criteria) this;
        }

        public Criteria andSpecificationparamIsNotNull() {
            addCriterion("specificationparam is not null");
            return (Criteria) this;
        }

        public Criteria andSpecificationparamEqualTo(String value) {
            addCriterion("specificationparam =", value, "specificationparam");
            return (Criteria) this;
        }

        public Criteria andSpecificationparamNotEqualTo(String value) {
            addCriterion("specificationparam <>", value, "specificationparam");
            return (Criteria) this;
        }

        public Criteria andSpecificationparamGreaterThan(String value) {
            addCriterion("specificationparam >", value, "specificationparam");
            return (Criteria) this;
        }

        public Criteria andSpecificationparamGreaterThanOrEqualTo(String value) {
            addCriterion("specificationparam >=", value, "specificationparam");
            return (Criteria) this;
        }

        public Criteria andSpecificationparamLessThan(String value) {
            addCriterion("specificationparam <", value, "specificationparam");
            return (Criteria) this;
        }

        public Criteria andSpecificationparamLessThanOrEqualTo(String value) {
            addCriterion("specificationparam <=", value, "specificationparam");
            return (Criteria) this;
        }

        public Criteria andSpecificationparamLike(String value) {
            addCriterion("specificationparam like", value, "specificationparam");
            return (Criteria) this;
        }

        public Criteria andSpecificationparamNotLike(String value) {
            addCriterion("specificationparam not like", value, "specificationparam");
            return (Criteria) this;
        }

        public Criteria andSpecificationparamIn(List<String> values) {
            addCriterion("specificationparam in", values, "specificationparam");
            return (Criteria) this;
        }

        public Criteria andSpecificationparamNotIn(List<String> values) {
            addCriterion("specificationparam not in", values, "specificationparam");
            return (Criteria) this;
        }

        public Criteria andSpecificationparamBetween(String value1, String value2) {
            addCriterion("specificationparam between", value1, value2, "specificationparam");
            return (Criteria) this;
        }

        public Criteria andSpecificationparamNotBetween(String value1, String value2) {
            addCriterion("specificationparam not between", value1, value2, "specificationparam");
            return (Criteria) this;
        }

        public Criteria andSeokeyIsNull() {
            addCriterion("seokey is null");
            return (Criteria) this;
        }

        public Criteria andSeokeyIsNotNull() {
            addCriterion("seokey is not null");
            return (Criteria) this;
        }

        public Criteria andSeokeyEqualTo(String value) {
            addCriterion("seokey =", value, "seokey");
            return (Criteria) this;
        }

        public Criteria andSeokeyNotEqualTo(String value) {
            addCriterion("seokey <>", value, "seokey");
            return (Criteria) this;
        }

        public Criteria andSeokeyGreaterThan(String value) {
            addCriterion("seokey >", value, "seokey");
            return (Criteria) this;
        }

        public Criteria andSeokeyGreaterThanOrEqualTo(String value) {
            addCriterion("seokey >=", value, "seokey");
            return (Criteria) this;
        }

        public Criteria andSeokeyLessThan(String value) {
            addCriterion("seokey <", value, "seokey");
            return (Criteria) this;
        }

        public Criteria andSeokeyLessThanOrEqualTo(String value) {
            addCriterion("seokey <=", value, "seokey");
            return (Criteria) this;
        }

        public Criteria andSeokeyLike(String value) {
            addCriterion("seokey like", value, "seokey");
            return (Criteria) this;
        }

        public Criteria andSeokeyNotLike(String value) {
            addCriterion("seokey not like", value, "seokey");
            return (Criteria) this;
        }

        public Criteria andSeokeyIn(List<String> values) {
            addCriterion("seokey in", values, "seokey");
            return (Criteria) this;
        }

        public Criteria andSeokeyNotIn(List<String> values) {
            addCriterion("seokey not in", values, "seokey");
            return (Criteria) this;
        }

        public Criteria andSeokeyBetween(String value1, String value2) {
            addCriterion("seokey between", value1, value2, "seokey");
            return (Criteria) this;
        }

        public Criteria andSeokeyNotBetween(String value1, String value2) {
            addCriterion("seokey not between", value1, value2, "seokey");
            return (Criteria) this;
        }

        public Criteria andSeovalueIsNull() {
            addCriterion("seovalue is null");
            return (Criteria) this;
        }

        public Criteria andSeovalueIsNotNull() {
            addCriterion("seovalue is not null");
            return (Criteria) this;
        }

        public Criteria andSeovalueEqualTo(String value) {
            addCriterion("seovalue =", value, "seovalue");
            return (Criteria) this;
        }

        public Criteria andSeovalueNotEqualTo(String value) {
            addCriterion("seovalue <>", value, "seovalue");
            return (Criteria) this;
        }

        public Criteria andSeovalueGreaterThan(String value) {
            addCriterion("seovalue >", value, "seovalue");
            return (Criteria) this;
        }

        public Criteria andSeovalueGreaterThanOrEqualTo(String value) {
            addCriterion("seovalue >=", value, "seovalue");
            return (Criteria) this;
        }

        public Criteria andSeovalueLessThan(String value) {
            addCriterion("seovalue <", value, "seovalue");
            return (Criteria) this;
        }

        public Criteria andSeovalueLessThanOrEqualTo(String value) {
            addCriterion("seovalue <=", value, "seovalue");
            return (Criteria) this;
        }

        public Criteria andSeovalueLike(String value) {
            addCriterion("seovalue like", value, "seovalue");
            return (Criteria) this;
        }

        public Criteria andSeovalueNotLike(String value) {
            addCriterion("seovalue not like", value, "seovalue");
            return (Criteria) this;
        }

        public Criteria andSeovalueIn(List<String> values) {
            addCriterion("seovalue in", values, "seovalue");
            return (Criteria) this;
        }

        public Criteria andSeovalueNotIn(List<String> values) {
            addCriterion("seovalue not in", values, "seovalue");
            return (Criteria) this;
        }

        public Criteria andSeovalueBetween(String value1, String value2) {
            addCriterion("seovalue between", value1, value2, "seovalue");
            return (Criteria) this;
        }

        public Criteria andSeovalueNotBetween(String value1, String value2) {
            addCriterion("seovalue not between", value1, value2, "seovalue");
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

        public Criteria andAudittimeIsNull() {
            addCriterion("audittime is null");
            return (Criteria) this;
        }

        public Criteria andAudittimeIsNotNull() {
            addCriterion("audittime is not null");
            return (Criteria) this;
        }

        public Criteria andAudittimeEqualTo(Date value) {
            addCriterion("audittime =", value, "audittime");
            return (Criteria) this;
        }

        public Criteria andAudittimeNotEqualTo(Date value) {
            addCriterion("audittime <>", value, "audittime");
            return (Criteria) this;
        }

        public Criteria andAudittimeGreaterThan(Date value) {
            addCriterion("audittime >", value, "audittime");
            return (Criteria) this;
        }

        public Criteria andAudittimeGreaterThanOrEqualTo(Date value) {
            addCriterion("audittime >=", value, "audittime");
            return (Criteria) this;
        }

        public Criteria andAudittimeLessThan(Date value) {
            addCriterion("audittime <", value, "audittime");
            return (Criteria) this;
        }

        public Criteria andAudittimeLessThanOrEqualTo(Date value) {
            addCriterion("audittime <=", value, "audittime");
            return (Criteria) this;
        }

        public Criteria andAudittimeIn(List<Date> values) {
            addCriterion("audittime in", values, "audittime");
            return (Criteria) this;
        }

        public Criteria andAudittimeNotIn(List<Date> values) {
            addCriterion("audittime not in", values, "audittime");
            return (Criteria) this;
        }

        public Criteria andAudittimeBetween(Date value1, Date value2) {
            addCriterion("audittime between", value1, value2, "audittime");
            return (Criteria) this;
        }

        public Criteria andAudittimeNotBetween(Date value1, Date value2) {
            addCriterion("audittime not between", value1, value2, "audittime");
            return (Criteria) this;
        }

        public Criteria andAuditnameIsNull() {
            addCriterion("auditname is null");
            return (Criteria) this;
        }

        public Criteria andAuditnameIsNotNull() {
            addCriterion("auditname is not null");
            return (Criteria) this;
        }

        public Criteria andAuditnameEqualTo(String value) {
            addCriterion("auditname =", value, "auditname");
            return (Criteria) this;
        }

        public Criteria andAuditnameNotEqualTo(String value) {
            addCriterion("auditname <>", value, "auditname");
            return (Criteria) this;
        }

        public Criteria andAuditnameGreaterThan(String value) {
            addCriterion("auditname >", value, "auditname");
            return (Criteria) this;
        }

        public Criteria andAuditnameGreaterThanOrEqualTo(String value) {
            addCriterion("auditname >=", value, "auditname");
            return (Criteria) this;
        }

        public Criteria andAuditnameLessThan(String value) {
            addCriterion("auditname <", value, "auditname");
            return (Criteria) this;
        }

        public Criteria andAuditnameLessThanOrEqualTo(String value) {
            addCriterion("auditname <=", value, "auditname");
            return (Criteria) this;
        }

        public Criteria andAuditnameLike(String value) {
            addCriterion("auditname like", value, "auditname");
            return (Criteria) this;
        }

        public Criteria andAuditnameNotLike(String value) {
            addCriterion("auditname not like", value, "auditname");
            return (Criteria) this;
        }

        public Criteria andAuditnameIn(List<String> values) {
            addCriterion("auditname in", values, "auditname");
            return (Criteria) this;
        }

        public Criteria andAuditnameNotIn(List<String> values) {
            addCriterion("auditname not in", values, "auditname");
            return (Criteria) this;
        }

        public Criteria andAuditnameBetween(String value1, String value2) {
            addCriterion("auditname between", value1, value2, "auditname");
            return (Criteria) this;
        }

        public Criteria andAuditnameNotBetween(String value1, String value2) {
            addCriterion("auditname not between", value1, value2, "auditname");
            return (Criteria) this;
        }

        public Criteria andReasonIsNull() {
            addCriterion("reason is null");
            return (Criteria) this;
        }

        public Criteria andReasonIsNotNull() {
            addCriterion("reason is not null");
            return (Criteria) this;
        }

        public Criteria andReasonEqualTo(String value) {
            addCriterion("reason =", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotEqualTo(String value) {
            addCriterion("reason <>", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThan(String value) {
            addCriterion("reason >", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThanOrEqualTo(String value) {
            addCriterion("reason >=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThan(String value) {
            addCriterion("reason <", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThanOrEqualTo(String value) {
            addCriterion("reason <=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLike(String value) {
            addCriterion("reason like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotLike(String value) {
            addCriterion("reason not like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonIn(List<String> values) {
            addCriterion("reason in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotIn(List<String> values) {
            addCriterion("reason not in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonBetween(String value1, String value2) {
            addCriterion("reason between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotBetween(String value1, String value2) {
            addCriterion("reason not between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andUptimeIsNull() {
            addCriterion("uptime is null");
            return (Criteria) this;
        }

        public Criteria andUptimeIsNotNull() {
            addCriterion("uptime is not null");
            return (Criteria) this;
        }

        public Criteria andUptimeEqualTo(Date value) {
            addCriterion("uptime =", value, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeNotEqualTo(Date value) {
            addCriterion("uptime <>", value, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeGreaterThan(Date value) {
            addCriterion("uptime >", value, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeGreaterThanOrEqualTo(Date value) {
            addCriterion("uptime >=", value, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeLessThan(Date value) {
            addCriterion("uptime <", value, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeLessThanOrEqualTo(Date value) {
            addCriterion("uptime <=", value, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeIn(List<Date> values) {
            addCriterion("uptime in", values, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeNotIn(List<Date> values) {
            addCriterion("uptime not in", values, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeBetween(Date value1, Date value2) {
            addCriterion("uptime between", value1, value2, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeNotBetween(Date value1, Date value2) {
            addCriterion("uptime not between", value1, value2, "uptime");
            return (Criteria) this;
        }

        public Criteria andDowntimeIsNull() {
            addCriterion("downtime is null");
            return (Criteria) this;
        }

        public Criteria andDowntimeIsNotNull() {
            addCriterion("downtime is not null");
            return (Criteria) this;
        }

        public Criteria andDowntimeEqualTo(Date value) {
            addCriterion("downtime =", value, "downtime");
            return (Criteria) this;
        }

        public Criteria andDowntimeNotEqualTo(Date value) {
            addCriterion("downtime <>", value, "downtime");
            return (Criteria) this;
        }

        public Criteria andDowntimeGreaterThan(Date value) {
            addCriterion("downtime >", value, "downtime");
            return (Criteria) this;
        }

        public Criteria andDowntimeGreaterThanOrEqualTo(Date value) {
            addCriterion("downtime >=", value, "downtime");
            return (Criteria) this;
        }

        public Criteria andDowntimeLessThan(Date value) {
            addCriterion("downtime <", value, "downtime");
            return (Criteria) this;
        }

        public Criteria andDowntimeLessThanOrEqualTo(Date value) {
            addCriterion("downtime <=", value, "downtime");
            return (Criteria) this;
        }

        public Criteria andDowntimeIn(List<Date> values) {
            addCriterion("downtime in", values, "downtime");
            return (Criteria) this;
        }

        public Criteria andDowntimeNotIn(List<Date> values) {
            addCriterion("downtime not in", values, "downtime");
            return (Criteria) this;
        }

        public Criteria andDowntimeBetween(Date value1, Date value2) {
            addCriterion("downtime between", value1, value2, "downtime");
            return (Criteria) this;
        }

        public Criteria andDowntimeNotBetween(Date value1, Date value2) {
            addCriterion("downtime not between", value1, value2, "downtime");
            return (Criteria) this;
        }

        public Criteria andSalesnumIsNull() {
            addCriterion("salesnum is null");
            return (Criteria) this;
        }

        public Criteria andSalesnumIsNotNull() {
            addCriterion("salesnum is not null");
            return (Criteria) this;
        }

        public Criteria andSalesnumEqualTo(Long value) {
            addCriterion("salesnum =", value, "salesnum");
            return (Criteria) this;
        }

        public Criteria andSalesnumNotEqualTo(Long value) {
            addCriterion("salesnum <>", value, "salesnum");
            return (Criteria) this;
        }

        public Criteria andSalesnumGreaterThan(Long value) {
            addCriterion("salesnum >", value, "salesnum");
            return (Criteria) this;
        }

        public Criteria andSalesnumGreaterThanOrEqualTo(Long value) {
            addCriterion("salesnum >=", value, "salesnum");
            return (Criteria) this;
        }

        public Criteria andSalesnumLessThan(Long value) {
            addCriterion("salesnum <", value, "salesnum");
            return (Criteria) this;
        }

        public Criteria andSalesnumLessThanOrEqualTo(Long value) {
            addCriterion("salesnum <=", value, "salesnum");
            return (Criteria) this;
        }

        public Criteria andSalesnumIn(List<Long> values) {
            addCriterion("salesnum in", values, "salesnum");
            return (Criteria) this;
        }

        public Criteria andSalesnumNotIn(List<Long> values) {
            addCriterion("salesnum not in", values, "salesnum");
            return (Criteria) this;
        }

        public Criteria andSalesnumBetween(Long value1, Long value2) {
            addCriterion("salesnum between", value1, value2, "salesnum");
            return (Criteria) this;
        }

        public Criteria andSalesnumNotBetween(Long value1, Long value2) {
            addCriterion("salesnum not between", value1, value2, "salesnum");
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

        public Criteria andStandIsNull() {
            addCriterion("stand is null");
            return (Criteria) this;
        }

        public Criteria andStandIsNotNull() {
            addCriterion("stand is not null");
            return (Criteria) this;
        }

        public Criteria andStandEqualTo(String value) {
            addCriterion("stand =", value, "stand");
            return (Criteria) this;
        }

        public Criteria andStandNotEqualTo(String value) {
            addCriterion("stand <>", value, "stand");
            return (Criteria) this;
        }

        public Criteria andStandGreaterThan(String value) {
            addCriterion("stand >", value, "stand");
            return (Criteria) this;
        }

        public Criteria andStandGreaterThanOrEqualTo(String value) {
            addCriterion("stand >=", value, "stand");
            return (Criteria) this;
        }

        public Criteria andStandLessThan(String value) {
            addCriterion("stand <", value, "stand");
            return (Criteria) this;
        }

        public Criteria andStandLessThanOrEqualTo(String value) {
            addCriterion("stand <=", value, "stand");
            return (Criteria) this;
        }

        public Criteria andStandLike(String value) {
            addCriterion("stand like", value, "stand");
            return (Criteria) this;
        }

        public Criteria andStandNotLike(String value) {
            addCriterion("stand not like", value, "stand");
            return (Criteria) this;
        }

        public Criteria andStandIn(List<String> values) {
            addCriterion("stand in", values, "stand");
            return (Criteria) this;
        }

        public Criteria andStandNotIn(List<String> values) {
            addCriterion("stand not in", values, "stand");
            return (Criteria) this;
        }

        public Criteria andStandBetween(String value1, String value2) {
            addCriterion("stand between", value1, value2, "stand");
            return (Criteria) this;
        }

        public Criteria andStandNotBetween(String value1, String value2) {
            addCriterion("stand not between", value1, value2, "stand");
            return (Criteria) this;
        }

        public Criteria andSeotitleIsNull() {
            addCriterion("seotitle is null");
            return (Criteria) this;
        }

        public Criteria andSeotitleIsNotNull() {
            addCriterion("seotitle is not null");
            return (Criteria) this;
        }

        public Criteria andSeotitleEqualTo(String value) {
            addCriterion("seotitle =", value, "seotitle");
            return (Criteria) this;
        }

        public Criteria andSeotitleNotEqualTo(String value) {
            addCriterion("seotitle <>", value, "seotitle");
            return (Criteria) this;
        }

        public Criteria andSeotitleGreaterThan(String value) {
            addCriterion("seotitle >", value, "seotitle");
            return (Criteria) this;
        }

        public Criteria andSeotitleGreaterThanOrEqualTo(String value) {
            addCriterion("seotitle >=", value, "seotitle");
            return (Criteria) this;
        }

        public Criteria andSeotitleLessThan(String value) {
            addCriterion("seotitle <", value, "seotitle");
            return (Criteria) this;
        }

        public Criteria andSeotitleLessThanOrEqualTo(String value) {
            addCriterion("seotitle <=", value, "seotitle");
            return (Criteria) this;
        }

        public Criteria andSeotitleLike(String value) {
            addCriterion("seotitle like", value, "seotitle");
            return (Criteria) this;
        }

        public Criteria andSeotitleNotLike(String value) {
            addCriterion("seotitle not like", value, "seotitle");
            return (Criteria) this;
        }

        public Criteria andSeotitleIn(List<String> values) {
            addCriterion("seotitle in", values, "seotitle");
            return (Criteria) this;
        }

        public Criteria andSeotitleNotIn(List<String> values) {
            addCriterion("seotitle not in", values, "seotitle");
            return (Criteria) this;
        }

        public Criteria andSeotitleBetween(String value1, String value2) {
            addCriterion("seotitle between", value1, value2, "seotitle");
            return (Criteria) this;
        }

        public Criteria andSeotitleNotBetween(String value1, String value2) {
            addCriterion("seotitle not between", value1, value2, "seotitle");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNull() {
            addCriterion("updatetime is null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNotNull() {
            addCriterion("updatetime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeEqualTo(Date value) {
            addCriterion("updatetime =", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotEqualTo(Date value) {
            addCriterion("updatetime <>", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThan(Date value) {
            addCriterion("updatetime >", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updatetime >=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThan(Date value) {
            addCriterion("updatetime <", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThanOrEqualTo(Date value) {
            addCriterion("updatetime <=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIn(List<Date> values) {
            addCriterion("updatetime in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotIn(List<Date> values) {
            addCriterion("updatetime not in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeBetween(Date value1, Date value2) {
            addCriterion("updatetime between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotBetween(Date value1, Date value2) {
            addCriterion("updatetime not between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andTagIsNull() {
            addCriterion("tag is null");
            return (Criteria) this;
        }

        public Criteria andTagIsNotNull() {
            addCriterion("tag is not null");
            return (Criteria) this;
        }

        public Criteria andTagEqualTo(Object value) {
            addCriterion("tag =", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotEqualTo(Object value) {
            addCriterion("tag <>", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagGreaterThan(Object value) {
            addCriterion("tag >", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagGreaterThanOrEqualTo(Object value) {
            addCriterion("tag >=", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLessThan(Object value) {
            addCriterion("tag <", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLessThanOrEqualTo(Object value) {
            addCriterion("tag <=", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagIn(List<Object> values) {
            addCriterion("tag in", values, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotIn(List<Object> values) {
            addCriterion("tag not in", values, "tag");
            return (Criteria) this;
        }

        public Criteria andTagBetween(Object value1, Object value2) {
            addCriterion("tag between", value1, value2, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotBetween(Object value1, Object value2) {
            addCriterion("tag not between", value1, value2, "tag");
            return (Criteria) this;
        }

        public Criteria andProductidIsNull() {
            addCriterion("productid is null");
            return (Criteria) this;
        }

        public Criteria andProductidIsNotNull() {
            addCriterion("productid is not null");
            return (Criteria) this;
        }

        public Criteria andProductidEqualTo(Long value) {
            addCriterion("productid =", value, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidNotEqualTo(Long value) {
            addCriterion("productid <>", value, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidGreaterThan(Long value) {
            addCriterion("productid >", value, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidGreaterThanOrEqualTo(Long value) {
            addCriterion("productid >=", value, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidLessThan(Long value) {
            addCriterion("productid <", value, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidLessThanOrEqualTo(Long value) {
            addCriterion("productid <=", value, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidIn(List<Long> values) {
            addCriterion("productid in", values, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidNotIn(List<Long> values) {
            addCriterion("productid not in", values, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidBetween(Long value1, Long value2) {
            addCriterion("productid between", value1, value2, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidNotBetween(Long value1, Long value2) {
            addCriterion("productid not between", value1, value2, "productid");
            return (Criteria) this;
        }

        public Criteria andProdstoreunitIsNull() {
            addCriterion("prodstoreunit is null");
            return (Criteria) this;
        }

        public Criteria andProdstoreunitIsNotNull() {
            addCriterion("prodstoreunit is not null");
            return (Criteria) this;
        }

        public Criteria andProdstoreunitEqualTo(String value) {
            addCriterion("prodstoreunit =", value, "prodstoreunit");
            return (Criteria) this;
        }

        public Criteria andProdstoreunitNotEqualTo(String value) {
            addCriterion("prodstoreunit <>", value, "prodstoreunit");
            return (Criteria) this;
        }

        public Criteria andProdstoreunitGreaterThan(String value) {
            addCriterion("prodstoreunit >", value, "prodstoreunit");
            return (Criteria) this;
        }

        public Criteria andProdstoreunitGreaterThanOrEqualTo(String value) {
            addCriterion("prodstoreunit >=", value, "prodstoreunit");
            return (Criteria) this;
        }

        public Criteria andProdstoreunitLessThan(String value) {
            addCriterion("prodstoreunit <", value, "prodstoreunit");
            return (Criteria) this;
        }

        public Criteria andProdstoreunitLessThanOrEqualTo(String value) {
            addCriterion("prodstoreunit <=", value, "prodstoreunit");
            return (Criteria) this;
        }

        public Criteria andProdstoreunitLike(String value) {
            addCriterion("prodstoreunit like", value, "prodstoreunit");
            return (Criteria) this;
        }

        public Criteria andProdstoreunitNotLike(String value) {
            addCriterion("prodstoreunit not like", value, "prodstoreunit");
            return (Criteria) this;
        }

        public Criteria andProdstoreunitIn(List<String> values) {
            addCriterion("prodstoreunit in", values, "prodstoreunit");
            return (Criteria) this;
        }

        public Criteria andProdstoreunitNotIn(List<String> values) {
            addCriterion("prodstoreunit not in", values, "prodstoreunit");
            return (Criteria) this;
        }

        public Criteria andProdstoreunitBetween(String value1, String value2) {
            addCriterion("prodstoreunit between", value1, value2, "prodstoreunit");
            return (Criteria) this;
        }

        public Criteria andProdstoreunitNotBetween(String value1, String value2) {
            addCriterion("prodstoreunit not between", value1, value2, "prodstoreunit");
            return (Criteria) this;
        }

        public Criteria andUnitrateIsNull() {
            addCriterion("unitrate is null");
            return (Criteria) this;
        }

        public Criteria andUnitrateIsNotNull() {
            addCriterion("unitrate is not null");
            return (Criteria) this;
        }

        public Criteria andUnitrateEqualTo(BigDecimal value) {
            addCriterion("unitrate =", value, "unitrate");
            return (Criteria) this;
        }

        public Criteria andUnitrateNotEqualTo(BigDecimal value) {
            addCriterion("unitrate <>", value, "unitrate");
            return (Criteria) this;
        }

        public Criteria andUnitrateGreaterThan(BigDecimal value) {
            addCriterion("unitrate >", value, "unitrate");
            return (Criteria) this;
        }

        public Criteria andUnitrateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("unitrate >=", value, "unitrate");
            return (Criteria) this;
        }

        public Criteria andUnitrateLessThan(BigDecimal value) {
            addCriterion("unitrate <", value, "unitrate");
            return (Criteria) this;
        }

        public Criteria andUnitrateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("unitrate <=", value, "unitrate");
            return (Criteria) this;
        }

        public Criteria andUnitrateIn(List<BigDecimal> values) {
            addCriterion("unitrate in", values, "unitrate");
            return (Criteria) this;
        }

        public Criteria andUnitrateNotIn(List<BigDecimal> values) {
            addCriterion("unitrate not in", values, "unitrate");
            return (Criteria) this;
        }

        public Criteria andUnitrateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("unitrate between", value1, value2, "unitrate");
            return (Criteria) this;
        }

        public Criteria andUnitrateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("unitrate not between", value1, value2, "unitrate");
            return (Criteria) this;
        }

        public Criteria andMinpriceIsNull() {
            addCriterion("minprice is null");
            return (Criteria) this;
        }

        public Criteria andMinpriceIsNotNull() {
            addCriterion("minprice is not null");
            return (Criteria) this;
        }

        public Criteria andMinpriceEqualTo(BigDecimal value) {
            addCriterion("minprice =", value, "minprice");
            return (Criteria) this;
        }

        public Criteria andMinpriceNotEqualTo(BigDecimal value) {
            addCriterion("minprice <>", value, "minprice");
            return (Criteria) this;
        }

        public Criteria andMinpriceGreaterThan(BigDecimal value) {
            addCriterion("minprice >", value, "minprice");
            return (Criteria) this;
        }

        public Criteria andMinpriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("minprice >=", value, "minprice");
            return (Criteria) this;
        }

        public Criteria andMinpriceLessThan(BigDecimal value) {
            addCriterion("minprice <", value, "minprice");
            return (Criteria) this;
        }

        public Criteria andMinpriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("minprice <=", value, "minprice");
            return (Criteria) this;
        }

        public Criteria andMinpriceIn(List<BigDecimal> values) {
            addCriterion("minprice in", values, "minprice");
            return (Criteria) this;
        }

        public Criteria andMinpriceNotIn(List<BigDecimal> values) {
            addCriterion("minprice not in", values, "minprice");
            return (Criteria) this;
        }

        public Criteria andMinpriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("minprice between", value1, value2, "minprice");
            return (Criteria) this;
        }

        public Criteria andMinpriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("minprice not between", value1, value2, "minprice");
            return (Criteria) this;
        }

        public Criteria andHeightpriceIsNull() {
            addCriterion("heightprice is null");
            return (Criteria) this;
        }

        public Criteria andHeightpriceIsNotNull() {
            addCriterion("heightprice is not null");
            return (Criteria) this;
        }

        public Criteria andHeightpriceEqualTo(BigDecimal value) {
            addCriterion("heightprice =", value, "heightprice");
            return (Criteria) this;
        }

        public Criteria andHeightpriceNotEqualTo(BigDecimal value) {
            addCriterion("heightprice <>", value, "heightprice");
            return (Criteria) this;
        }

        public Criteria andHeightpriceGreaterThan(BigDecimal value) {
            addCriterion("heightprice >", value, "heightprice");
            return (Criteria) this;
        }

        public Criteria andHeightpriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("heightprice >=", value, "heightprice");
            return (Criteria) this;
        }

        public Criteria andHeightpriceLessThan(BigDecimal value) {
            addCriterion("heightprice <", value, "heightprice");
            return (Criteria) this;
        }

        public Criteria andHeightpriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("heightprice <=", value, "heightprice");
            return (Criteria) this;
        }

        public Criteria andHeightpriceIn(List<BigDecimal> values) {
            addCriterion("heightprice in", values, "heightprice");
            return (Criteria) this;
        }

        public Criteria andHeightpriceNotIn(List<BigDecimal> values) {
            addCriterion("heightprice not in", values, "heightprice");
            return (Criteria) this;
        }

        public Criteria andHeightpriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("heightprice between", value1, value2, "heightprice");
            return (Criteria) this;
        }

        public Criteria andHeightpriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("heightprice not between", value1, value2, "heightprice");
            return (Criteria) this;
        }

        public Criteria andSelfsupportIsNull() {
            addCriterion("selfsupport is null");
            return (Criteria) this;
        }

        public Criteria andSelfsupportIsNotNull() {
            addCriterion("selfsupport is not null");
            return (Criteria) this;
        }

        public Criteria andSelfsupportEqualTo(Boolean value) {
            addCriterion("selfsupport =", value, "selfsupport");
            return (Criteria) this;
        }

        public Criteria andSelfsupportNotEqualTo(Boolean value) {
            addCriterion("selfsupport <>", value, "selfsupport");
            return (Criteria) this;
        }

        public Criteria andSelfsupportGreaterThan(Boolean value) {
            addCriterion("selfsupport >", value, "selfsupport");
            return (Criteria) this;
        }

        public Criteria andSelfsupportGreaterThanOrEqualTo(Boolean value) {
            addCriterion("selfsupport >=", value, "selfsupport");
            return (Criteria) this;
        }

        public Criteria andSelfsupportLessThan(Boolean value) {
            addCriterion("selfsupport <", value, "selfsupport");
            return (Criteria) this;
        }

        public Criteria andSelfsupportLessThanOrEqualTo(Boolean value) {
            addCriterion("selfsupport <=", value, "selfsupport");
            return (Criteria) this;
        }

        public Criteria andSelfsupportIn(List<Boolean> values) {
            addCriterion("selfsupport in", values, "selfsupport");
            return (Criteria) this;
        }

        public Criteria andSelfsupportNotIn(List<Boolean> values) {
            addCriterion("selfsupport not in", values, "selfsupport");
            return (Criteria) this;
        }

        public Criteria andSelfsupportBetween(Boolean value1, Boolean value2) {
            addCriterion("selfsupport between", value1, value2, "selfsupport");
            return (Criteria) this;
        }

        public Criteria andSelfsupportNotBetween(Boolean value1, Boolean value2) {
            addCriterion("selfsupport not between", value1, value2, "selfsupport");
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

        public Criteria andTypeEqualTo(Short value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Short value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Short value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Short value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Short value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Short> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Short> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Short value1, Short value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Short value1, Short value2) {
            addCriterion("type not between", value1, value2, "type");
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