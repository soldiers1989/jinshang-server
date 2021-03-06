package project.jinshang.mod_coupon.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class YhqTicketsetExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public YhqTicketsetExample() {
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
     * @date 2018-05-26
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

        public Criteria andNoIsNull() {
            addCriterion("no is null");
            return (Criteria) this;
        }

        public Criteria andNoIsNotNull() {
            addCriterion("no is not null");
            return (Criteria) this;
        }

        public Criteria andNoEqualTo(String value) {
            addCriterion("no =", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoNotEqualTo(String value) {
            addCriterion("no <>", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoGreaterThan(String value) {
            addCriterion("no >", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoGreaterThanOrEqualTo(String value) {
            addCriterion("no >=", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoLessThan(String value) {
            addCriterion("no <", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoLessThanOrEqualTo(String value) {
            addCriterion("no <=", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoLike(String value) {
            addCriterion("no like", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoNotLike(String value) {
            addCriterion("no not like", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoIn(List<String> values) {
            addCriterion("no in", values, "no");
            return (Criteria) this;
        }

        public Criteria andNoNotIn(List<String> values) {
            addCriterion("no not in", values, "no");
            return (Criteria) this;
        }

        public Criteria andNoBetween(String value1, String value2) {
            addCriterion("no between", value1, value2, "no");
            return (Criteria) this;
        }

        public Criteria andNoNotBetween(String value1, String value2) {
            addCriterion("no not between", value1, value2, "no");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
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

        public Criteria andCreatetypeIsNull() {
            addCriterion("createtype is null");
            return (Criteria) this;
        }

        public Criteria andCreatetypeIsNotNull() {
            addCriterion("createtype is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetypeEqualTo(Long value) {
            addCriterion("createtype =", value, "createtype");
            return (Criteria) this;
        }

        public Criteria andCreatetypeNotEqualTo(Long value) {
            addCriterion("createtype <>", value, "createtype");
            return (Criteria) this;
        }

        public Criteria andCreatetypeGreaterThan(Long value) {
            addCriterion("createtype >", value, "createtype");
            return (Criteria) this;
        }

        public Criteria andCreatetypeGreaterThanOrEqualTo(Long value) {
            addCriterion("createtype >=", value, "createtype");
            return (Criteria) this;
        }

        public Criteria andCreatetypeLessThan(Long value) {
            addCriterion("createtype <", value, "createtype");
            return (Criteria) this;
        }

        public Criteria andCreatetypeLessThanOrEqualTo(Long value) {
            addCriterion("createtype <=", value, "createtype");
            return (Criteria) this;
        }

        public Criteria andCreatetypeIn(List<Long> values) {
            addCriterion("createtype in", values, "createtype");
            return (Criteria) this;
        }

        public Criteria andCreatetypeNotIn(List<Long> values) {
            addCriterion("createtype not in", values, "createtype");
            return (Criteria) this;
        }

        public Criteria andCreatetypeBetween(Long value1, Long value2) {
            addCriterion("createtype between", value1, value2, "createtype");
            return (Criteria) this;
        }

        public Criteria andCreatetypeNotBetween(Long value1, Long value2) {
            addCriterion("createtype not between", value1, value2, "createtype");
            return (Criteria) this;
        }

        public Criteria andPlanidIsNull() {
            addCriterion("planid is null");
            return (Criteria) this;
        }

        public Criteria andPlanidIsNotNull() {
            addCriterion("planid is not null");
            return (Criteria) this;
        }

        public Criteria andPlanidEqualTo(Long value) {
            addCriterion("planid =", value, "planid");
            return (Criteria) this;
        }

        public Criteria andPlanidNotEqualTo(Long value) {
            addCriterion("planid <>", value, "planid");
            return (Criteria) this;
        }

        public Criteria andPlanidGreaterThan(Long value) {
            addCriterion("planid >", value, "planid");
            return (Criteria) this;
        }

        public Criteria andPlanidGreaterThanOrEqualTo(Long value) {
            addCriterion("planid >=", value, "planid");
            return (Criteria) this;
        }

        public Criteria andPlanidLessThan(Long value) {
            addCriterion("planid <", value, "planid");
            return (Criteria) this;
        }

        public Criteria andPlanidLessThanOrEqualTo(Long value) {
            addCriterion("planid <=", value, "planid");
            return (Criteria) this;
        }

        public Criteria andPlanidIn(List<Long> values) {
            addCriterion("planid in", values, "planid");
            return (Criteria) this;
        }

        public Criteria andPlanidNotIn(List<Long> values) {
            addCriterion("planid not in", values, "planid");
            return (Criteria) this;
        }

        public Criteria andPlanidBetween(Long value1, Long value2) {
            addCriterion("planid between", value1, value2, "planid");
            return (Criteria) this;
        }

        public Criteria andPlanidNotBetween(Long value1, Long value2) {
            addCriterion("planid not between", value1, value2, "planid");
            return (Criteria) this;
        }

        public Criteria andAboutIsNull() {
            addCriterion("about is null");
            return (Criteria) this;
        }

        public Criteria andAboutIsNotNull() {
            addCriterion("about is not null");
            return (Criteria) this;
        }

        public Criteria andAboutEqualTo(String value) {
            addCriterion("about =", value, "about");
            return (Criteria) this;
        }

        public Criteria andAboutNotEqualTo(String value) {
            addCriterion("about <>", value, "about");
            return (Criteria) this;
        }

        public Criteria andAboutGreaterThan(String value) {
            addCriterion("about >", value, "about");
            return (Criteria) this;
        }

        public Criteria andAboutGreaterThanOrEqualTo(String value) {
            addCriterion("about >=", value, "about");
            return (Criteria) this;
        }

        public Criteria andAboutLessThan(String value) {
            addCriterion("about <", value, "about");
            return (Criteria) this;
        }

        public Criteria andAboutLessThanOrEqualTo(String value) {
            addCriterion("about <=", value, "about");
            return (Criteria) this;
        }

        public Criteria andAboutLike(String value) {
            addCriterion("about like", value, "about");
            return (Criteria) this;
        }

        public Criteria andAboutNotLike(String value) {
            addCriterion("about not like", value, "about");
            return (Criteria) this;
        }

        public Criteria andAboutIn(List<String> values) {
            addCriterion("about in", values, "about");
            return (Criteria) this;
        }

        public Criteria andAboutNotIn(List<String> values) {
            addCriterion("about not in", values, "about");
            return (Criteria) this;
        }

        public Criteria andAboutBetween(String value1, String value2) {
            addCriterion("about between", value1, value2, "about");
            return (Criteria) this;
        }

        public Criteria andAboutNotBetween(String value1, String value2) {
            addCriterion("about not between", value1, value2, "about");
            return (Criteria) this;
        }

        public Criteria andRuleIsNull() {
            addCriterion("rule is null");
            return (Criteria) this;
        }

        public Criteria andRuleIsNotNull() {
            addCriterion("rule is not null");
            return (Criteria) this;
        }

        public Criteria andRuleEqualTo(String value) {
            addCriterion("rule =", value, "rule");
            return (Criteria) this;
        }

        public Criteria andRuleNotEqualTo(String value) {
            addCriterion("rule <>", value, "rule");
            return (Criteria) this;
        }

        public Criteria andRuleGreaterThan(String value) {
            addCriterion("rule >", value, "rule");
            return (Criteria) this;
        }

        public Criteria andRuleGreaterThanOrEqualTo(String value) {
            addCriterion("rule >=", value, "rule");
            return (Criteria) this;
        }

        public Criteria andRuleLessThan(String value) {
            addCriterion("rule <", value, "rule");
            return (Criteria) this;
        }

        public Criteria andRuleLessThanOrEqualTo(String value) {
            addCriterion("rule <=", value, "rule");
            return (Criteria) this;
        }

        public Criteria andRuleLike(String value) {
            addCriterion("rule like", value, "rule");
            return (Criteria) this;
        }

        public Criteria andRuleNotLike(String value) {
            addCriterion("rule not like", value, "rule");
            return (Criteria) this;
        }

        public Criteria andRuleIn(List<String> values) {
            addCriterion("rule in", values, "rule");
            return (Criteria) this;
        }

        public Criteria andRuleNotIn(List<String> values) {
            addCriterion("rule not in", values, "rule");
            return (Criteria) this;
        }

        public Criteria andRuleBetween(String value1, String value2) {
            addCriterion("rule between", value1, value2, "rule");
            return (Criteria) this;
        }

        public Criteria andRuleNotBetween(String value1, String value2) {
            addCriterion("rule not between", value1, value2, "rule");
            return (Criteria) this;
        }

        public Criteria andCategorieslistIsNull() {
            addCriterion("categorieslist is null");
            return (Criteria) this;
        }

        public Criteria andCategorieslistIsNotNull() {
            addCriterion("categorieslist is not null");
            return (Criteria) this;
        }

        public Criteria andCategorieslistEqualTo(String value) {
            addCriterion("categorieslist =", value, "categorieslist");
            return (Criteria) this;
        }

        public Criteria andCategorieslistNotEqualTo(String value) {
            addCriterion("categorieslist <>", value, "categorieslist");
            return (Criteria) this;
        }

        public Criteria andCategorieslistGreaterThan(String value) {
            addCriterion("categorieslist >", value, "categorieslist");
            return (Criteria) this;
        }

        public Criteria andCategorieslistGreaterThanOrEqualTo(String value) {
            addCriterion("categorieslist >=", value, "categorieslist");
            return (Criteria) this;
        }

        public Criteria andCategorieslistLessThan(String value) {
            addCriterion("categorieslist <", value, "categorieslist");
            return (Criteria) this;
        }

        public Criteria andCategorieslistLessThanOrEqualTo(String value) {
            addCriterion("categorieslist <=", value, "categorieslist");
            return (Criteria) this;
        }

        public Criteria andCategorieslistLike(String value) {
            addCriterion("categorieslist like", value, "categorieslist");
            return (Criteria) this;
        }

        public Criteria andCategorieslistNotLike(String value) {
            addCriterion("categorieslist not like", value, "categorieslist");
            return (Criteria) this;
        }

        public Criteria andCategorieslistIn(List<String> values) {
            addCriterion("categorieslist in", values, "categorieslist");
            return (Criteria) this;
        }

        public Criteria andCategorieslistNotIn(List<String> values) {
            addCriterion("categorieslist not in", values, "categorieslist");
            return (Criteria) this;
        }

        public Criteria andCategorieslistBetween(String value1, String value2) {
            addCriterion("categorieslist between", value1, value2, "categorieslist");
            return (Criteria) this;
        }

        public Criteria andCategorieslistNotBetween(String value1, String value2) {
            addCriterion("categorieslist not between", value1, value2, "categorieslist");
            return (Criteria) this;
        }

        public Criteria andStorelistIsNull() {
            addCriterion("storelist is null");
            return (Criteria) this;
        }

        public Criteria andStorelistIsNotNull() {
            addCriterion("storelist is not null");
            return (Criteria) this;
        }

        public Criteria andStorelistEqualTo(String value) {
            addCriterion("storelist =", value, "storelist");
            return (Criteria) this;
        }

        public Criteria andStorelistNotEqualTo(String value) {
            addCriterion("storelist <>", value, "storelist");
            return (Criteria) this;
        }

        public Criteria andStorelistGreaterThan(String value) {
            addCriterion("storelist >", value, "storelist");
            return (Criteria) this;
        }

        public Criteria andStorelistGreaterThanOrEqualTo(String value) {
            addCriterion("storelist >=", value, "storelist");
            return (Criteria) this;
        }

        public Criteria andStorelistLessThan(String value) {
            addCriterion("storelist <", value, "storelist");
            return (Criteria) this;
        }

        public Criteria andStorelistLessThanOrEqualTo(String value) {
            addCriterion("storelist <=", value, "storelist");
            return (Criteria) this;
        }

        public Criteria andStorelistLike(String value) {
            addCriterion("storelist like", value, "storelist");
            return (Criteria) this;
        }

        public Criteria andStorelistNotLike(String value) {
            addCriterion("storelist not like", value, "storelist");
            return (Criteria) this;
        }

        public Criteria andStorelistIn(List<String> values) {
            addCriterion("storelist in", values, "storelist");
            return (Criteria) this;
        }

        public Criteria andStorelistNotIn(List<String> values) {
            addCriterion("storelist not in", values, "storelist");
            return (Criteria) this;
        }

        public Criteria andStorelistBetween(String value1, String value2) {
            addCriterion("storelist between", value1, value2, "storelist");
            return (Criteria) this;
        }

        public Criteria andStorelistNotBetween(String value1, String value2) {
            addCriterion("storelist not between", value1, value2, "storelist");
            return (Criteria) this;
        }

        public Criteria andProductstorelistIsNull() {
            addCriterion("productstorelist is null");
            return (Criteria) this;
        }

        public Criteria andProductstorelistIsNotNull() {
            addCriterion("productstorelist is not null");
            return (Criteria) this;
        }

        public Criteria andProductstorelistEqualTo(String value) {
            addCriterion("productstorelist =", value, "productstorelist");
            return (Criteria) this;
        }

        public Criteria andProductstorelistNotEqualTo(String value) {
            addCriterion("productstorelist <>", value, "productstorelist");
            return (Criteria) this;
        }

        public Criteria andProductstorelistGreaterThan(String value) {
            addCriterion("productstorelist >", value, "productstorelist");
            return (Criteria) this;
        }

        public Criteria andProductstorelistGreaterThanOrEqualTo(String value) {
            addCriterion("productstorelist >=", value, "productstorelist");
            return (Criteria) this;
        }

        public Criteria andProductstorelistLessThan(String value) {
            addCriterion("productstorelist <", value, "productstorelist");
            return (Criteria) this;
        }

        public Criteria andProductstorelistLessThanOrEqualTo(String value) {
            addCriterion("productstorelist <=", value, "productstorelist");
            return (Criteria) this;
        }

        public Criteria andProductstorelistLike(String value) {
            addCriterion("productstorelist like", value, "productstorelist");
            return (Criteria) this;
        }

        public Criteria andProductstorelistNotLike(String value) {
            addCriterion("productstorelist not like", value, "productstorelist");
            return (Criteria) this;
        }

        public Criteria andProductstorelistIn(List<String> values) {
            addCriterion("productstorelist in", values, "productstorelist");
            return (Criteria) this;
        }

        public Criteria andProductstorelistNotIn(List<String> values) {
            addCriterion("productstorelist not in", values, "productstorelist");
            return (Criteria) this;
        }

        public Criteria andProductstorelistBetween(String value1, String value2) {
            addCriterion("productstorelist between", value1, value2, "productstorelist");
            return (Criteria) this;
        }

        public Criteria andProductstorelistNotBetween(String value1, String value2) {
            addCriterion("productstorelist not between", value1, value2, "productstorelist");
            return (Criteria) this;
        }

        public Criteria andMembergradelistIsNull() {
            addCriterion("membergradelist is null");
            return (Criteria) this;
        }

        public Criteria andMembergradelistIsNotNull() {
            addCriterion("membergradelist is not null");
            return (Criteria) this;
        }

        public Criteria andMembergradelistEqualTo(String value) {
            addCriterion("membergradelist =", value, "membergradelist");
            return (Criteria) this;
        }

        public Criteria andMembergradelistNotEqualTo(String value) {
            addCriterion("membergradelist <>", value, "membergradelist");
            return (Criteria) this;
        }

        public Criteria andMembergradelistGreaterThan(String value) {
            addCriterion("membergradelist >", value, "membergradelist");
            return (Criteria) this;
        }

        public Criteria andMembergradelistGreaterThanOrEqualTo(String value) {
            addCriterion("membergradelist >=", value, "membergradelist");
            return (Criteria) this;
        }

        public Criteria andMembergradelistLessThan(String value) {
            addCriterion("membergradelist <", value, "membergradelist");
            return (Criteria) this;
        }

        public Criteria andMembergradelistLessThanOrEqualTo(String value) {
            addCriterion("membergradelist <=", value, "membergradelist");
            return (Criteria) this;
        }

        public Criteria andMembergradelistLike(String value) {
            addCriterion("membergradelist like", value, "membergradelist");
            return (Criteria) this;
        }

        public Criteria andMembergradelistNotLike(String value) {
            addCriterion("membergradelist not like", value, "membergradelist");
            return (Criteria) this;
        }

        public Criteria andMembergradelistIn(List<String> values) {
            addCriterion("membergradelist in", values, "membergradelist");
            return (Criteria) this;
        }

        public Criteria andMembergradelistNotIn(List<String> values) {
            addCriterion("membergradelist not in", values, "membergradelist");
            return (Criteria) this;
        }

        public Criteria andMembergradelistBetween(String value1, String value2) {
            addCriterion("membergradelist between", value1, value2, "membergradelist");
            return (Criteria) this;
        }

        public Criteria andMembergradelistNotBetween(String value1, String value2) {
            addCriterion("membergradelist not between", value1, value2, "membergradelist");
            return (Criteria) this;
        }

        public Criteria andTicketcountIsNull() {
            addCriterion("ticketcount is null");
            return (Criteria) this;
        }

        public Criteria andTicketcountIsNotNull() {
            addCriterion("ticketcount is not null");
            return (Criteria) this;
        }

        public Criteria andTicketcountEqualTo(Long value) {
            addCriterion("ticketcount =", value, "ticketcount");
            return (Criteria) this;
        }

        public Criteria andTicketcountNotEqualTo(Long value) {
            addCriterion("ticketcount <>", value, "ticketcount");
            return (Criteria) this;
        }

        public Criteria andTicketcountGreaterThan(Long value) {
            addCriterion("ticketcount >", value, "ticketcount");
            return (Criteria) this;
        }

        public Criteria andTicketcountGreaterThanOrEqualTo(Long value) {
            addCriterion("ticketcount >=", value, "ticketcount");
            return (Criteria) this;
        }

        public Criteria andTicketcountLessThan(Long value) {
            addCriterion("ticketcount <", value, "ticketcount");
            return (Criteria) this;
        }

        public Criteria andTicketcountLessThanOrEqualTo(Long value) {
            addCriterion("ticketcount <=", value, "ticketcount");
            return (Criteria) this;
        }

        public Criteria andTicketcountIn(List<Long> values) {
            addCriterion("ticketcount in", values, "ticketcount");
            return (Criteria) this;
        }

        public Criteria andTicketcountNotIn(List<Long> values) {
            addCriterion("ticketcount not in", values, "ticketcount");
            return (Criteria) this;
        }

        public Criteria andTicketcountBetween(Long value1, Long value2) {
            addCriterion("ticketcount between", value1, value2, "ticketcount");
            return (Criteria) this;
        }

        public Criteria andTicketcountNotBetween(Long value1, Long value2) {
            addCriterion("ticketcount not between", value1, value2, "ticketcount");
            return (Criteria) this;
        }

        public Criteria andSurpluscountIsNull() {
            addCriterion("surpluscount is null");
            return (Criteria) this;
        }

        public Criteria andSurpluscountIsNotNull() {
            addCriterion("surpluscount is not null");
            return (Criteria) this;
        }

        public Criteria andSurpluscountEqualTo(Long value) {
            addCriterion("surpluscount =", value, "surpluscount");
            return (Criteria) this;
        }

        public Criteria andSurpluscountNotEqualTo(Long value) {
            addCriterion("surpluscount <>", value, "surpluscount");
            return (Criteria) this;
        }

        public Criteria andSurpluscountGreaterThan(Long value) {
            addCriterion("surpluscount >", value, "surpluscount");
            return (Criteria) this;
        }

        public Criteria andSurpluscountGreaterThanOrEqualTo(Long value) {
            addCriterion("surpluscount >=", value, "surpluscount");
            return (Criteria) this;
        }

        public Criteria andSurpluscountLessThan(Long value) {
            addCriterion("surpluscount <", value, "surpluscount");
            return (Criteria) this;
        }

        public Criteria andSurpluscountLessThanOrEqualTo(Long value) {
            addCriterion("surpluscount <=", value, "surpluscount");
            return (Criteria) this;
        }

        public Criteria andSurpluscountIn(List<Long> values) {
            addCriterion("surpluscount in", values, "surpluscount");
            return (Criteria) this;
        }

        public Criteria andSurpluscountNotIn(List<Long> values) {
            addCriterion("surpluscount not in", values, "surpluscount");
            return (Criteria) this;
        }

        public Criteria andSurpluscountBetween(Long value1, Long value2) {
            addCriterion("surpluscount between", value1, value2, "surpluscount");
            return (Criteria) this;
        }

        public Criteria andSurpluscountNotBetween(Long value1, Long value2) {
            addCriterion("surpluscount not between", value1, value2, "surpluscount");
            return (Criteria) this;
        }

        public Criteria andStarttimeIsNull() {
            addCriterion("starttime is null");
            return (Criteria) this;
        }

        public Criteria andStarttimeIsNotNull() {
            addCriterion("starttime is not null");
            return (Criteria) this;
        }

        public Criteria andStarttimeEqualTo(Date value) {
            addCriterion("starttime =", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotEqualTo(Date value) {
            addCriterion("starttime <>", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeGreaterThan(Date value) {
            addCriterion("starttime >", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("starttime >=", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeLessThan(Date value) {
            addCriterion("starttime <", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeLessThanOrEqualTo(Date value) {
            addCriterion("starttime <=", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeIn(List<Date> values) {
            addCriterion("starttime in", values, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotIn(List<Date> values) {
            addCriterion("starttime not in", values, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeBetween(Date value1, Date value2) {
            addCriterion("starttime between", value1, value2, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotBetween(Date value1, Date value2) {
            addCriterion("starttime not between", value1, value2, "starttime");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNull() {
            addCriterion("endtime is null");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNotNull() {
            addCriterion("endtime is not null");
            return (Criteria) this;
        }

        public Criteria andEndtimeEqualTo(Date value) {
            addCriterion("endtime =", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotEqualTo(Date value) {
            addCriterion("endtime <>", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThan(Date value) {
            addCriterion("endtime >", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("endtime >=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThan(Date value) {
            addCriterion("endtime <", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThanOrEqualTo(Date value) {
            addCriterion("endtime <=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeIn(List<Date> values) {
            addCriterion("endtime in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotIn(List<Date> values) {
            addCriterion("endtime not in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeBetween(Date value1, Date value2) {
            addCriterion("endtime between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotBetween(Date value1, Date value2) {
            addCriterion("endtime not between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andValidityruleIsNull() {
            addCriterion("validityrule is null");
            return (Criteria) this;
        }

        public Criteria andValidityruleIsNotNull() {
            addCriterion("validityrule is not null");
            return (Criteria) this;
        }

        public Criteria andValidityruleEqualTo(String value) {
            addCriterion("validityrule =", value, "validityrule");
            return (Criteria) this;
        }

        public Criteria andValidityruleNotEqualTo(String value) {
            addCriterion("validityrule <>", value, "validityrule");
            return (Criteria) this;
        }

        public Criteria andValidityruleGreaterThan(String value) {
            addCriterion("validityrule >", value, "validityrule");
            return (Criteria) this;
        }

        public Criteria andValidityruleGreaterThanOrEqualTo(String value) {
            addCriterion("validityrule >=", value, "validityrule");
            return (Criteria) this;
        }

        public Criteria andValidityruleLessThan(String value) {
            addCriterion("validityrule <", value, "validityrule");
            return (Criteria) this;
        }

        public Criteria andValidityruleLessThanOrEqualTo(String value) {
            addCriterion("validityrule <=", value, "validityrule");
            return (Criteria) this;
        }

        public Criteria andValidityruleLike(String value) {
            addCriterion("validityrule like", value, "validityrule");
            return (Criteria) this;
        }

        public Criteria andValidityruleNotLike(String value) {
            addCriterion("validityrule not like", value, "validityrule");
            return (Criteria) this;
        }

        public Criteria andValidityruleIn(List<String> values) {
            addCriterion("validityrule in", values, "validityrule");
            return (Criteria) this;
        }

        public Criteria andValidityruleNotIn(List<String> values) {
            addCriterion("validityrule not in", values, "validityrule");
            return (Criteria) this;
        }

        public Criteria andValidityruleBetween(String value1, String value2) {
            addCriterion("validityrule between", value1, value2, "validityrule");
            return (Criteria) this;
        }

        public Criteria andValidityruleNotBetween(String value1, String value2) {
            addCriterion("validityrule not between", value1, value2, "validityrule");
            return (Criteria) this;
        }

        public Criteria andValiditystarttimeIsNull() {
            addCriterion("validitystarttime is null");
            return (Criteria) this;
        }

        public Criteria andValiditystarttimeIsNotNull() {
            addCriterion("validitystarttime is not null");
            return (Criteria) this;
        }

        public Criteria andValiditystarttimeEqualTo(Date value) {
            addCriterion("validitystarttime =", value, "validitystarttime");
            return (Criteria) this;
        }

        public Criteria andValiditystarttimeNotEqualTo(Date value) {
            addCriterion("validitystarttime <>", value, "validitystarttime");
            return (Criteria) this;
        }

        public Criteria andValiditystarttimeGreaterThan(Date value) {
            addCriterion("validitystarttime >", value, "validitystarttime");
            return (Criteria) this;
        }

        public Criteria andValiditystarttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("validitystarttime >=", value, "validitystarttime");
            return (Criteria) this;
        }

        public Criteria andValiditystarttimeLessThan(Date value) {
            addCriterion("validitystarttime <", value, "validitystarttime");
            return (Criteria) this;
        }

        public Criteria andValiditystarttimeLessThanOrEqualTo(Date value) {
            addCriterion("validitystarttime <=", value, "validitystarttime");
            return (Criteria) this;
        }

        public Criteria andValiditystarttimeIn(List<Date> values) {
            addCriterion("validitystarttime in", values, "validitystarttime");
            return (Criteria) this;
        }

        public Criteria andValiditystarttimeNotIn(List<Date> values) {
            addCriterion("validitystarttime not in", values, "validitystarttime");
            return (Criteria) this;
        }

        public Criteria andValiditystarttimeBetween(Date value1, Date value2) {
            addCriterion("validitystarttime between", value1, value2, "validitystarttime");
            return (Criteria) this;
        }

        public Criteria andValiditystarttimeNotBetween(Date value1, Date value2) {
            addCriterion("validitystarttime not between", value1, value2, "validitystarttime");
            return (Criteria) this;
        }

        public Criteria andValidityendtimeIsNull() {
            addCriterion("validityendtime is null");
            return (Criteria) this;
        }

        public Criteria andValidityendtimeIsNotNull() {
            addCriterion("validityendtime is not null");
            return (Criteria) this;
        }

        public Criteria andValidityendtimeEqualTo(Date value) {
            addCriterion("validityendtime =", value, "validityendtime");
            return (Criteria) this;
        }

        public Criteria andValidityendtimeNotEqualTo(Date value) {
            addCriterion("validityendtime <>", value, "validityendtime");
            return (Criteria) this;
        }

        public Criteria andValidityendtimeGreaterThan(Date value) {
            addCriterion("validityendtime >", value, "validityendtime");
            return (Criteria) this;
        }

        public Criteria andValidityendtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("validityendtime >=", value, "validityendtime");
            return (Criteria) this;
        }

        public Criteria andValidityendtimeLessThan(Date value) {
            addCriterion("validityendtime <", value, "validityendtime");
            return (Criteria) this;
        }

        public Criteria andValidityendtimeLessThanOrEqualTo(Date value) {
            addCriterion("validityendtime <=", value, "validityendtime");
            return (Criteria) this;
        }

        public Criteria andValidityendtimeIn(List<Date> values) {
            addCriterion("validityendtime in", values, "validityendtime");
            return (Criteria) this;
        }

        public Criteria andValidityendtimeNotIn(List<Date> values) {
            addCriterion("validityendtime not in", values, "validityendtime");
            return (Criteria) this;
        }

        public Criteria andValidityendtimeBetween(Date value1, Date value2) {
            addCriterion("validityendtime between", value1, value2, "validityendtime");
            return (Criteria) this;
        }

        public Criteria andValidityendtimeNotBetween(Date value1, Date value2) {
            addCriterion("validityendtime not between", value1, value2, "validityendtime");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Long value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Long value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Long value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Long value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Long value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Long value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Long> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Long> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Long value1, Long value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Long value1, Long value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andAuditingidIsNull() {
            addCriterion("auditingid is null");
            return (Criteria) this;
        }

        public Criteria andAuditingidIsNotNull() {
            addCriterion("auditingid is not null");
            return (Criteria) this;
        }

        public Criteria andAuditingidEqualTo(Long value) {
            addCriterion("auditingid =", value, "auditingid");
            return (Criteria) this;
        }

        public Criteria andAuditingidNotEqualTo(Long value) {
            addCriterion("auditingid <>", value, "auditingid");
            return (Criteria) this;
        }

        public Criteria andAuditingidGreaterThan(Long value) {
            addCriterion("auditingid >", value, "auditingid");
            return (Criteria) this;
        }

        public Criteria andAuditingidGreaterThanOrEqualTo(Long value) {
            addCriterion("auditingid >=", value, "auditingid");
            return (Criteria) this;
        }

        public Criteria andAuditingidLessThan(Long value) {
            addCriterion("auditingid <", value, "auditingid");
            return (Criteria) this;
        }

        public Criteria andAuditingidLessThanOrEqualTo(Long value) {
            addCriterion("auditingid <=", value, "auditingid");
            return (Criteria) this;
        }

        public Criteria andAuditingidIn(List<Long> values) {
            addCriterion("auditingid in", values, "auditingid");
            return (Criteria) this;
        }

        public Criteria andAuditingidNotIn(List<Long> values) {
            addCriterion("auditingid not in", values, "auditingid");
            return (Criteria) this;
        }

        public Criteria andAuditingidBetween(Long value1, Long value2) {
            addCriterion("auditingid between", value1, value2, "auditingid");
            return (Criteria) this;
        }

        public Criteria andAuditingidNotBetween(Long value1, Long value2) {
            addCriterion("auditingid not between", value1, value2, "auditingid");
            return (Criteria) this;
        }

        public Criteria andAuditingstrIsNull() {
            addCriterion("auditingstr is null");
            return (Criteria) this;
        }

        public Criteria andAuditingstrIsNotNull() {
            addCriterion("auditingstr is not null");
            return (Criteria) this;
        }

        public Criteria andAuditingstrEqualTo(String value) {
            addCriterion("auditingstr =", value, "auditingstr");
            return (Criteria) this;
        }

        public Criteria andAuditingstrNotEqualTo(String value) {
            addCriterion("auditingstr <>", value, "auditingstr");
            return (Criteria) this;
        }

        public Criteria andAuditingstrGreaterThan(String value) {
            addCriterion("auditingstr >", value, "auditingstr");
            return (Criteria) this;
        }

        public Criteria andAuditingstrGreaterThanOrEqualTo(String value) {
            addCriterion("auditingstr >=", value, "auditingstr");
            return (Criteria) this;
        }

        public Criteria andAuditingstrLessThan(String value) {
            addCriterion("auditingstr <", value, "auditingstr");
            return (Criteria) this;
        }

        public Criteria andAuditingstrLessThanOrEqualTo(String value) {
            addCriterion("auditingstr <=", value, "auditingstr");
            return (Criteria) this;
        }

        public Criteria andAuditingstrLike(String value) {
            addCriterion("auditingstr like", value, "auditingstr");
            return (Criteria) this;
        }

        public Criteria andAuditingstrNotLike(String value) {
            addCriterion("auditingstr not like", value, "auditingstr");
            return (Criteria) this;
        }

        public Criteria andAuditingstrIn(List<String> values) {
            addCriterion("auditingstr in", values, "auditingstr");
            return (Criteria) this;
        }

        public Criteria andAuditingstrNotIn(List<String> values) {
            addCriterion("auditingstr not in", values, "auditingstr");
            return (Criteria) this;
        }

        public Criteria andAuditingstrBetween(String value1, String value2) {
            addCriterion("auditingstr between", value1, value2, "auditingstr");
            return (Criteria) this;
        }

        public Criteria andAuditingstrNotBetween(String value1, String value2) {
            addCriterion("auditingstr not between", value1, value2, "auditingstr");
            return (Criteria) this;
        }

        public Criteria andUsersidIsNull() {
            addCriterion("usersid is null");
            return (Criteria) this;
        }

        public Criteria andUsersidIsNotNull() {
            addCriterion("usersid is not null");
            return (Criteria) this;
        }

        public Criteria andUsersidEqualTo(Long value) {
            addCriterion("usersid =", value, "usersid");
            return (Criteria) this;
        }

        public Criteria andUsersidNotEqualTo(Long value) {
            addCriterion("usersid <>", value, "usersid");
            return (Criteria) this;
        }

        public Criteria andUsersidGreaterThan(Long value) {
            addCriterion("usersid >", value, "usersid");
            return (Criteria) this;
        }

        public Criteria andUsersidGreaterThanOrEqualTo(Long value) {
            addCriterion("usersid >=", value, "usersid");
            return (Criteria) this;
        }

        public Criteria andUsersidLessThan(Long value) {
            addCriterion("usersid <", value, "usersid");
            return (Criteria) this;
        }

        public Criteria andUsersidLessThanOrEqualTo(Long value) {
            addCriterion("usersid <=", value, "usersid");
            return (Criteria) this;
        }

        public Criteria andUsersidIn(List<Long> values) {
            addCriterion("usersid in", values, "usersid");
            return (Criteria) this;
        }

        public Criteria andUsersidNotIn(List<Long> values) {
            addCriterion("usersid not in", values, "usersid");
            return (Criteria) this;
        }

        public Criteria andUsersidBetween(Long value1, Long value2) {
            addCriterion("usersid between", value1, value2, "usersid");
            return (Criteria) this;
        }

        public Criteria andUsersidNotBetween(Long value1, Long value2) {
            addCriterion("usersid not between", value1, value2, "usersid");
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
     * @date 2018-05-26
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