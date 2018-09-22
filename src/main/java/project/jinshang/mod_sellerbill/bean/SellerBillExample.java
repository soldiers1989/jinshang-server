package project.jinshang.mod_sellerbill.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SellerBillExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SellerBillExample() {
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

        public Criteria andMoneyIsNull() {
            addCriterion("money is null");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNotNull() {
            addCriterion("money is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyEqualTo(BigDecimal value) {
            addCriterion("money =", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotEqualTo(BigDecimal value) {
            addCriterion("money <>", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThan(BigDecimal value) {
            addCriterion("money >", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("money >=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThan(BigDecimal value) {
            addCriterion("money <", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("money <=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyIn(List<BigDecimal> values) {
            addCriterion("money in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotIn(List<BigDecimal> values) {
            addCriterion("money not in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("money between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("money not between", value1, value2, "money");
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

        public Criteria andAdduserIsNull() {
            addCriterion("adduser is null");
            return (Criteria) this;
        }

        public Criteria andAdduserIsNotNull() {
            addCriterion("adduser is not null");
            return (Criteria) this;
        }

        public Criteria andAdduserEqualTo(String value) {
            addCriterion("adduser =", value, "adduser");
            return (Criteria) this;
        }

        public Criteria andAdduserNotEqualTo(String value) {
            addCriterion("adduser <>", value, "adduser");
            return (Criteria) this;
        }

        public Criteria andAdduserGreaterThan(String value) {
            addCriterion("adduser >", value, "adduser");
            return (Criteria) this;
        }

        public Criteria andAdduserGreaterThanOrEqualTo(String value) {
            addCriterion("adduser >=", value, "adduser");
            return (Criteria) this;
        }

        public Criteria andAdduserLessThan(String value) {
            addCriterion("adduser <", value, "adduser");
            return (Criteria) this;
        }

        public Criteria andAdduserLessThanOrEqualTo(String value) {
            addCriterion("adduser <=", value, "adduser");
            return (Criteria) this;
        }

        public Criteria andAdduserLike(String value) {
            addCriterion("adduser like", value, "adduser");
            return (Criteria) this;
        }

        public Criteria andAdduserNotLike(String value) {
            addCriterion("adduser not like", value, "adduser");
            return (Criteria) this;
        }

        public Criteria andAdduserIn(List<String> values) {
            addCriterion("adduser in", values, "adduser");
            return (Criteria) this;
        }

        public Criteria andAdduserNotIn(List<String> values) {
            addCriterion("adduser not in", values, "adduser");
            return (Criteria) this;
        }

        public Criteria andAdduserBetween(String value1, String value2) {
            addCriterion("adduser between", value1, value2, "adduser");
            return (Criteria) this;
        }

        public Criteria andAdduserNotBetween(String value1, String value2) {
            addCriterion("adduser not between", value1, value2, "adduser");
            return (Criteria) this;
        }

        public Criteria andAddtimeIsNull() {
            addCriterion("addtime is null");
            return (Criteria) this;
        }

        public Criteria andAddtimeIsNotNull() {
            addCriterion("addtime is not null");
            return (Criteria) this;
        }

        public Criteria andAddtimeEqualTo(Date value) {
            addCriterion("addtime =", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeNotEqualTo(Date value) {
            addCriterion("addtime <>", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeGreaterThan(Date value) {
            addCriterion("addtime >", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("addtime >=", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeLessThan(Date value) {
            addCriterion("addtime <", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeLessThanOrEqualTo(Date value) {
            addCriterion("addtime <=", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeIn(List<Date> values) {
            addCriterion("addtime in", values, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeNotIn(List<Date> values) {
            addCriterion("addtime not in", values, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeBetween(Date value1, Date value2) {
            addCriterion("addtime between", value1, value2, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeNotBetween(Date value1, Date value2) {
            addCriterion("addtime not between", value1, value2, "addtime");
            return (Criteria) this;
        }

        public Criteria andBilljsonIsNull() {
            addCriterion("billjson is null");
            return (Criteria) this;
        }

        public Criteria andBilljsonIsNotNull() {
            addCriterion("billjson is not null");
            return (Criteria) this;
        }

        public Criteria andBilljsonEqualTo(Object value) {
            addCriterion("billjson =", value, "billjson");
            return (Criteria) this;
        }

        public Criteria andBilljsonNotEqualTo(Object value) {
            addCriterion("billjson <>", value, "billjson");
            return (Criteria) this;
        }

        public Criteria andBilljsonGreaterThan(Object value) {
            addCriterion("billjson >", value, "billjson");
            return (Criteria) this;
        }

        public Criteria andBilljsonGreaterThanOrEqualTo(Object value) {
            addCriterion("billjson >=", value, "billjson");
            return (Criteria) this;
        }

        public Criteria andBilljsonLessThan(Object value) {
            addCriterion("billjson <", value, "billjson");
            return (Criteria) this;
        }

        public Criteria andBilljsonLessThanOrEqualTo(Object value) {
            addCriterion("billjson <=", value, "billjson");
            return (Criteria) this;
        }

        public Criteria andBilljsonIn(List<Object> values) {
            addCriterion("billjson in", values, "billjson");
            return (Criteria) this;
        }

        public Criteria andBilljsonNotIn(List<Object> values) {
            addCriterion("billjson not in", values, "billjson");
            return (Criteria) this;
        }

        public Criteria andBilljsonBetween(Object value1, Object value2) {
            addCriterion("billjson between", value1, value2, "billjson");
            return (Criteria) this;
        }

        public Criteria andBilljsonNotBetween(Object value1, Object value2) {
            addCriterion("billjson not between", value1, value2, "billjson");
            return (Criteria) this;
        }

        public Criteria andBilltypeIsNull() {
            addCriterion("billtype is null");
            return (Criteria) this;
        }

        public Criteria andBilltypeIsNotNull() {
            addCriterion("billtype is not null");
            return (Criteria) this;
        }

        public Criteria andBilltypeEqualTo(String value) {
            addCriterion("billtype =", value, "billtype");
            return (Criteria) this;
        }

        public Criteria andBilltypeNotEqualTo(String value) {
            addCriterion("billtype <>", value, "billtype");
            return (Criteria) this;
        }

        public Criteria andBilltypeGreaterThan(String value) {
            addCriterion("billtype >", value, "billtype");
            return (Criteria) this;
        }

        public Criteria andBilltypeGreaterThanOrEqualTo(String value) {
            addCriterion("billtype >=", value, "billtype");
            return (Criteria) this;
        }

        public Criteria andBilltypeLessThan(String value) {
            addCriterion("billtype <", value, "billtype");
            return (Criteria) this;
        }

        public Criteria andBilltypeLessThanOrEqualTo(String value) {
            addCriterion("billtype <=", value, "billtype");
            return (Criteria) this;
        }

        public Criteria andBilltypeLike(String value) {
            addCriterion("billtype like", value, "billtype");
            return (Criteria) this;
        }

        public Criteria andBilltypeNotLike(String value) {
            addCriterion("billtype not like", value, "billtype");
            return (Criteria) this;
        }

        public Criteria andBilltypeIn(List<String> values) {
            addCriterion("billtype in", values, "billtype");
            return (Criteria) this;
        }

        public Criteria andBilltypeNotIn(List<String> values) {
            addCriterion("billtype not in", values, "billtype");
            return (Criteria) this;
        }

        public Criteria andBilltypeBetween(String value1, String value2) {
            addCriterion("billtype between", value1, value2, "billtype");
            return (Criteria) this;
        }

        public Criteria andBilltypeNotBetween(String value1, String value2) {
            addCriterion("billtype not between", value1, value2, "billtype");
            return (Criteria) this;
        }

        public Criteria andBillcreatetimeIsNull() {
            addCriterion("billcreatetime is null");
            return (Criteria) this;
        }

        public Criteria andBillcreatetimeIsNotNull() {
            addCriterion("billcreatetime is not null");
            return (Criteria) this;
        }

        public Criteria andBillcreatetimeEqualTo(Date value) {
            addCriterion("billcreatetime =", value, "billcreatetime");
            return (Criteria) this;
        }

        public Criteria andBillcreatetimeNotEqualTo(Date value) {
            addCriterion("billcreatetime <>", value, "billcreatetime");
            return (Criteria) this;
        }

        public Criteria andBillcreatetimeGreaterThan(Date value) {
            addCriterion("billcreatetime >", value, "billcreatetime");
            return (Criteria) this;
        }

        public Criteria andBillcreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("billcreatetime >=", value, "billcreatetime");
            return (Criteria) this;
        }

        public Criteria andBillcreatetimeLessThan(Date value) {
            addCriterion("billcreatetime <", value, "billcreatetime");
            return (Criteria) this;
        }

        public Criteria andBillcreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("billcreatetime <=", value, "billcreatetime");
            return (Criteria) this;
        }

        public Criteria andBillcreatetimeIn(List<Date> values) {
            addCriterion("billcreatetime in", values, "billcreatetime");
            return (Criteria) this;
        }

        public Criteria andBillcreatetimeNotIn(List<Date> values) {
            addCriterion("billcreatetime not in", values, "billcreatetime");
            return (Criteria) this;
        }

        public Criteria andBillcreatetimeBetween(Date value1, Date value2) {
            addCriterion("billcreatetime between", value1, value2, "billcreatetime");
            return (Criteria) this;
        }

        public Criteria andBillcreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("billcreatetime not between", value1, value2, "billcreatetime");
            return (Criteria) this;
        }

        public Criteria andSellerusernameIsNull() {
            addCriterion("sellerusername is null");
            return (Criteria) this;
        }

        public Criteria andSellerusernameIsNotNull() {
            addCriterion("sellerusername is not null");
            return (Criteria) this;
        }

        public Criteria andSellerusernameEqualTo(String value) {
            addCriterion("sellerusername =", value, "sellerusername");
            return (Criteria) this;
        }

        public Criteria andSellerusernameNotEqualTo(String value) {
            addCriterion("sellerusername <>", value, "sellerusername");
            return (Criteria) this;
        }

        public Criteria andSellerusernameGreaterThan(String value) {
            addCriterion("sellerusername >", value, "sellerusername");
            return (Criteria) this;
        }

        public Criteria andSellerusernameGreaterThanOrEqualTo(String value) {
            addCriterion("sellerusername >=", value, "sellerusername");
            return (Criteria) this;
        }

        public Criteria andSellerusernameLessThan(String value) {
            addCriterion("sellerusername <", value, "sellerusername");
            return (Criteria) this;
        }

        public Criteria andSellerusernameLessThanOrEqualTo(String value) {
            addCriterion("sellerusername <=", value, "sellerusername");
            return (Criteria) this;
        }

        public Criteria andSellerusernameLike(String value) {
            addCriterion("sellerusername like", value, "sellerusername");
            return (Criteria) this;
        }

        public Criteria andSellerusernameNotLike(String value) {
            addCriterion("sellerusername not like", value, "sellerusername");
            return (Criteria) this;
        }

        public Criteria andSellerusernameIn(List<String> values) {
            addCriterion("sellerusername in", values, "sellerusername");
            return (Criteria) this;
        }

        public Criteria andSellerusernameNotIn(List<String> values) {
            addCriterion("sellerusername not in", values, "sellerusername");
            return (Criteria) this;
        }

        public Criteria andSellerusernameBetween(String value1, String value2) {
            addCriterion("sellerusername between", value1, value2, "sellerusername");
            return (Criteria) this;
        }

        public Criteria andSellerusernameNotBetween(String value1, String value2) {
            addCriterion("sellerusername not between", value1, value2, "sellerusername");
            return (Criteria) this;
        }

        public Criteria andSellercompanynameIsNull() {
            addCriterion("sellercompanyname is null");
            return (Criteria) this;
        }

        public Criteria andSellercompanynameIsNotNull() {
            addCriterion("sellercompanyname is not null");
            return (Criteria) this;
        }

        public Criteria andSellercompanynameEqualTo(String value) {
            addCriterion("sellercompanyname =", value, "sellercompanyname");
            return (Criteria) this;
        }

        public Criteria andSellercompanynameNotEqualTo(String value) {
            addCriterion("sellercompanyname <>", value, "sellercompanyname");
            return (Criteria) this;
        }

        public Criteria andSellercompanynameGreaterThan(String value) {
            addCriterion("sellercompanyname >", value, "sellercompanyname");
            return (Criteria) this;
        }

        public Criteria andSellercompanynameGreaterThanOrEqualTo(String value) {
            addCriterion("sellercompanyname >=", value, "sellercompanyname");
            return (Criteria) this;
        }

        public Criteria andSellercompanynameLessThan(String value) {
            addCriterion("sellercompanyname <", value, "sellercompanyname");
            return (Criteria) this;
        }

        public Criteria andSellercompanynameLessThanOrEqualTo(String value) {
            addCriterion("sellercompanyname <=", value, "sellercompanyname");
            return (Criteria) this;
        }

        public Criteria andSellercompanynameLike(String value) {
            addCriterion("sellercompanyname like", value, "sellercompanyname");
            return (Criteria) this;
        }

        public Criteria andSellercompanynameNotLike(String value) {
            addCriterion("sellercompanyname not like", value, "sellercompanyname");
            return (Criteria) this;
        }

        public Criteria andSellercompanynameIn(List<String> values) {
            addCriterion("sellercompanyname in", values, "sellercompanyname");
            return (Criteria) this;
        }

        public Criteria andSellercompanynameNotIn(List<String> values) {
            addCriterion("sellercompanyname not in", values, "sellercompanyname");
            return (Criteria) this;
        }

        public Criteria andSellercompanynameBetween(String value1, String value2) {
            addCriterion("sellercompanyname between", value1, value2, "sellercompanyname");
            return (Criteria) this;
        }

        public Criteria andSellercompanynameNotBetween(String value1, String value2) {
            addCriterion("sellercompanyname not between", value1, value2, "sellercompanyname");
            return (Criteria) this;
        }

        public Criteria andOrdernumIsNull() {
            addCriterion("ordernum is null");
            return (Criteria) this;
        }

        public Criteria andOrdernumIsNotNull() {
            addCriterion("ordernum is not null");
            return (Criteria) this;
        }

        public Criteria andOrdernumEqualTo(Integer value) {
            addCriterion("ordernum =", value, "ordernum");
            return (Criteria) this;
        }

        public Criteria andOrdernumNotEqualTo(Integer value) {
            addCriterion("ordernum <>", value, "ordernum");
            return (Criteria) this;
        }

        public Criteria andOrdernumGreaterThan(Integer value) {
            addCriterion("ordernum >", value, "ordernum");
            return (Criteria) this;
        }

        public Criteria andOrdernumGreaterThanOrEqualTo(Integer value) {
            addCriterion("ordernum >=", value, "ordernum");
            return (Criteria) this;
        }

        public Criteria andOrdernumLessThan(Integer value) {
            addCriterion("ordernum <", value, "ordernum");
            return (Criteria) this;
        }

        public Criteria andOrdernumLessThanOrEqualTo(Integer value) {
            addCriterion("ordernum <=", value, "ordernum");
            return (Criteria) this;
        }

        public Criteria andOrdernumIn(List<Integer> values) {
            addCriterion("ordernum in", values, "ordernum");
            return (Criteria) this;
        }

        public Criteria andOrdernumNotIn(List<Integer> values) {
            addCriterion("ordernum not in", values, "ordernum");
            return (Criteria) this;
        }

        public Criteria andOrdernumBetween(Integer value1, Integer value2) {
            addCriterion("ordernum between", value1, value2, "ordernum");
            return (Criteria) this;
        }

        public Criteria andOrdernumNotBetween(Integer value1, Integer value2) {
            addCriterion("ordernum not between", value1, value2, "ordernum");
            return (Criteria) this;
        }

        public Criteria andTotalordersIsNull() {
            addCriterion("totalorders is null");
            return (Criteria) this;
        }

        public Criteria andTotalordersIsNotNull() {
            addCriterion("totalorders is not null");
            return (Criteria) this;
        }

        public Criteria andTotalordersEqualTo(BigDecimal value) {
            addCriterion("totalorders =", value, "totalorders");
            return (Criteria) this;
        }

        public Criteria andTotalordersNotEqualTo(BigDecimal value) {
            addCriterion("totalorders <>", value, "totalorders");
            return (Criteria) this;
        }

        public Criteria andTotalordersGreaterThan(BigDecimal value) {
            addCriterion("totalorders >", value, "totalorders");
            return (Criteria) this;
        }

        public Criteria andTotalordersGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("totalorders >=", value, "totalorders");
            return (Criteria) this;
        }

        public Criteria andTotalordersLessThan(BigDecimal value) {
            addCriterion("totalorders <", value, "totalorders");
            return (Criteria) this;
        }

        public Criteria andTotalordersLessThanOrEqualTo(BigDecimal value) {
            addCriterion("totalorders <=", value, "totalorders");
            return (Criteria) this;
        }

        public Criteria andTotalordersIn(List<BigDecimal> values) {
            addCriterion("totalorders in", values, "totalorders");
            return (Criteria) this;
        }

        public Criteria andTotalordersNotIn(List<BigDecimal> values) {
            addCriterion("totalorders not in", values, "totalorders");
            return (Criteria) this;
        }

        public Criteria andTotalordersBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("totalorders between", value1, value2, "totalorders");
            return (Criteria) this;
        }

        public Criteria andTotalordersNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("totalorders not between", value1, value2, "totalorders");
            return (Criteria) this;
        }

        public Criteria andTotalbrokepayIsNull() {
            addCriterion("totalbrokepay is null");
            return (Criteria) this;
        }

        public Criteria andTotalbrokepayIsNotNull() {
            addCriterion("totalbrokepay is not null");
            return (Criteria) this;
        }

        public Criteria andTotalbrokepayEqualTo(BigDecimal value) {
            addCriterion("totalbrokepay =", value, "totalbrokepay");
            return (Criteria) this;
        }

        public Criteria andTotalbrokepayNotEqualTo(BigDecimal value) {
            addCriterion("totalbrokepay <>", value, "totalbrokepay");
            return (Criteria) this;
        }

        public Criteria andTotalbrokepayGreaterThan(BigDecimal value) {
            addCriterion("totalbrokepay >", value, "totalbrokepay");
            return (Criteria) this;
        }

        public Criteria andTotalbrokepayGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("totalbrokepay >=", value, "totalbrokepay");
            return (Criteria) this;
        }

        public Criteria andTotalbrokepayLessThan(BigDecimal value) {
            addCriterion("totalbrokepay <", value, "totalbrokepay");
            return (Criteria) this;
        }

        public Criteria andTotalbrokepayLessThanOrEqualTo(BigDecimal value) {
            addCriterion("totalbrokepay <=", value, "totalbrokepay");
            return (Criteria) this;
        }

        public Criteria andTotalbrokepayIn(List<BigDecimal> values) {
            addCriterion("totalbrokepay in", values, "totalbrokepay");
            return (Criteria) this;
        }

        public Criteria andTotalbrokepayNotIn(List<BigDecimal> values) {
            addCriterion("totalbrokepay not in", values, "totalbrokepay");
            return (Criteria) this;
        }

        public Criteria andTotalbrokepayBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("totalbrokepay between", value1, value2, "totalbrokepay");
            return (Criteria) this;
        }

        public Criteria andTotalbrokepayNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("totalbrokepay not between", value1, value2, "totalbrokepay");
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

        public Criteria andBreaknumIsNull() {
            addCriterion("breaknum is null");
            return (Criteria) this;
        }

        public Criteria andBreaknumIsNotNull() {
            addCriterion("breaknum is not null");
            return (Criteria) this;
        }

        public Criteria andBreaknumEqualTo(Integer value) {
            addCriterion("breaknum =", value, "breaknum");
            return (Criteria) this;
        }

        public Criteria andBreaknumNotEqualTo(Integer value) {
            addCriterion("breaknum <>", value, "breaknum");
            return (Criteria) this;
        }

        public Criteria andBreaknumGreaterThan(Integer value) {
            addCriterion("breaknum >", value, "breaknum");
            return (Criteria) this;
        }

        public Criteria andBreaknumGreaterThanOrEqualTo(Integer value) {
            addCriterion("breaknum >=", value, "breaknum");
            return (Criteria) this;
        }

        public Criteria andBreaknumLessThan(Integer value) {
            addCriterion("breaknum <", value, "breaknum");
            return (Criteria) this;
        }

        public Criteria andBreaknumLessThanOrEqualTo(Integer value) {
            addCriterion("breaknum <=", value, "breaknum");
            return (Criteria) this;
        }

        public Criteria andBreaknumIn(List<Integer> values) {
            addCriterion("breaknum in", values, "breaknum");
            return (Criteria) this;
        }

        public Criteria andBreaknumNotIn(List<Integer> values) {
            addCriterion("breaknum not in", values, "breaknum");
            return (Criteria) this;
        }

        public Criteria andBreaknumBetween(Integer value1, Integer value2) {
            addCriterion("breaknum between", value1, value2, "breaknum");
            return (Criteria) this;
        }

        public Criteria andBreaknumNotBetween(Integer value1, Integer value2) {
            addCriterion("breaknum not between", value1, value2, "breaknum");
            return (Criteria) this;
        }

        public Criteria andTotalbreakIsNull() {
            addCriterion("totalbreak is null");
            return (Criteria) this;
        }

        public Criteria andTotalbreakIsNotNull() {
            addCriterion("totalbreak is not null");
            return (Criteria) this;
        }

        public Criteria andTotalbreakEqualTo(BigDecimal value) {
            addCriterion("totalbreak =", value, "totalbreak");
            return (Criteria) this;
        }

        public Criteria andTotalbreakNotEqualTo(BigDecimal value) {
            addCriterion("totalbreak <>", value, "totalbreak");
            return (Criteria) this;
        }

        public Criteria andTotalbreakGreaterThan(BigDecimal value) {
            addCriterion("totalbreak >", value, "totalbreak");
            return (Criteria) this;
        }

        public Criteria andTotalbreakGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("totalbreak >=", value, "totalbreak");
            return (Criteria) this;
        }

        public Criteria andTotalbreakLessThan(BigDecimal value) {
            addCriterion("totalbreak <", value, "totalbreak");
            return (Criteria) this;
        }

        public Criteria andTotalbreakLessThanOrEqualTo(BigDecimal value) {
            addCriterion("totalbreak <=", value, "totalbreak");
            return (Criteria) this;
        }

        public Criteria andTotalbreakIn(List<BigDecimal> values) {
            addCriterion("totalbreak in", values, "totalbreak");
            return (Criteria) this;
        }

        public Criteria andTotalbreakNotIn(List<BigDecimal> values) {
            addCriterion("totalbreak not in", values, "totalbreak");
            return (Criteria) this;
        }

        public Criteria andTotalbreakBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("totalbreak between", value1, value2, "totalbreak");
            return (Criteria) this;
        }

        public Criteria andTotalbreakNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("totalbreak not between", value1, value2, "totalbreak");
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
     * @date 2018-08-29
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