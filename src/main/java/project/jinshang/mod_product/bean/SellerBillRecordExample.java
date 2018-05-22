package project.jinshang.mod_product.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SellerBillRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SellerBillRecordExample() {
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

        public Criteria andBilltitleIsNull() {
            addCriterion("billtitle is null");
            return (Criteria) this;
        }

        public Criteria andBilltitleIsNotNull() {
            addCriterion("billtitle is not null");
            return (Criteria) this;
        }

        public Criteria andBilltitleEqualTo(String value) {
            addCriterion("billtitle =", value, "billtitle");
            return (Criteria) this;
        }

        public Criteria andBilltitleNotEqualTo(String value) {
            addCriterion("billtitle <>", value, "billtitle");
            return (Criteria) this;
        }

        public Criteria andBilltitleGreaterThan(String value) {
            addCriterion("billtitle >", value, "billtitle");
            return (Criteria) this;
        }

        public Criteria andBilltitleGreaterThanOrEqualTo(String value) {
            addCriterion("billtitle >=", value, "billtitle");
            return (Criteria) this;
        }

        public Criteria andBilltitleLessThan(String value) {
            addCriterion("billtitle <", value, "billtitle");
            return (Criteria) this;
        }

        public Criteria andBilltitleLessThanOrEqualTo(String value) {
            addCriterion("billtitle <=", value, "billtitle");
            return (Criteria) this;
        }

        public Criteria andBilltitleLike(String value) {
            addCriterion("billtitle like", value, "billtitle");
            return (Criteria) this;
        }

        public Criteria andBilltitleNotLike(String value) {
            addCriterion("billtitle not like", value, "billtitle");
            return (Criteria) this;
        }

        public Criteria andBilltitleIn(List<String> values) {
            addCriterion("billtitle in", values, "billtitle");
            return (Criteria) this;
        }

        public Criteria andBilltitleNotIn(List<String> values) {
            addCriterion("billtitle not in", values, "billtitle");
            return (Criteria) this;
        }

        public Criteria andBilltitleBetween(String value1, String value2) {
            addCriterion("billtitle between", value1, value2, "billtitle");
            return (Criteria) this;
        }

        public Criteria andBilltitleNotBetween(String value1, String value2) {
            addCriterion("billtitle not between", value1, value2, "billtitle");
            return (Criteria) this;
        }

        public Criteria andTexnoIsNull() {
            addCriterion("texno is null");
            return (Criteria) this;
        }

        public Criteria andTexnoIsNotNull() {
            addCriterion("texno is not null");
            return (Criteria) this;
        }

        public Criteria andTexnoEqualTo(String value) {
            addCriterion("texno =", value, "texno");
            return (Criteria) this;
        }

        public Criteria andTexnoNotEqualTo(String value) {
            addCriterion("texno <>", value, "texno");
            return (Criteria) this;
        }

        public Criteria andTexnoGreaterThan(String value) {
            addCriterion("texno >", value, "texno");
            return (Criteria) this;
        }

        public Criteria andTexnoGreaterThanOrEqualTo(String value) {
            addCriterion("texno >=", value, "texno");
            return (Criteria) this;
        }

        public Criteria andTexnoLessThan(String value) {
            addCriterion("texno <", value, "texno");
            return (Criteria) this;
        }

        public Criteria andTexnoLessThanOrEqualTo(String value) {
            addCriterion("texno <=", value, "texno");
            return (Criteria) this;
        }

        public Criteria andTexnoLike(String value) {
            addCriterion("texno like", value, "texno");
            return (Criteria) this;
        }

        public Criteria andTexnoNotLike(String value) {
            addCriterion("texno not like", value, "texno");
            return (Criteria) this;
        }

        public Criteria andTexnoIn(List<String> values) {
            addCriterion("texno in", values, "texno");
            return (Criteria) this;
        }

        public Criteria andTexnoNotIn(List<String> values) {
            addCriterion("texno not in", values, "texno");
            return (Criteria) this;
        }

        public Criteria andTexnoBetween(String value1, String value2) {
            addCriterion("texno between", value1, value2, "texno");
            return (Criteria) this;
        }

        public Criteria andTexnoNotBetween(String value1, String value2) {
            addCriterion("texno not between", value1, value2, "texno");
            return (Criteria) this;
        }

        public Criteria andBillnoIsNull() {
            addCriterion("billno is null");
            return (Criteria) this;
        }

        public Criteria andBillnoIsNotNull() {
            addCriterion("billno is not null");
            return (Criteria) this;
        }

        public Criteria andBillnoEqualTo(String value) {
            addCriterion("billno =", value, "billno");
            return (Criteria) this;
        }

        public Criteria andBillnoNotEqualTo(String value) {
            addCriterion("billno <>", value, "billno");
            return (Criteria) this;
        }

        public Criteria andBillnoGreaterThan(String value) {
            addCriterion("billno >", value, "billno");
            return (Criteria) this;
        }

        public Criteria andBillnoGreaterThanOrEqualTo(String value) {
            addCriterion("billno >=", value, "billno");
            return (Criteria) this;
        }

        public Criteria andBillnoLessThan(String value) {
            addCriterion("billno <", value, "billno");
            return (Criteria) this;
        }

        public Criteria andBillnoLessThanOrEqualTo(String value) {
            addCriterion("billno <=", value, "billno");
            return (Criteria) this;
        }

        public Criteria andBillnoLike(String value) {
            addCriterion("billno like", value, "billno");
            return (Criteria) this;
        }

        public Criteria andBillnoNotLike(String value) {
            addCriterion("billno not like", value, "billno");
            return (Criteria) this;
        }

        public Criteria andBillnoIn(List<String> values) {
            addCriterion("billno in", values, "billno");
            return (Criteria) this;
        }

        public Criteria andBillnoNotIn(List<String> values) {
            addCriterion("billno not in", values, "billno");
            return (Criteria) this;
        }

        public Criteria andBillnoBetween(String value1, String value2) {
            addCriterion("billno between", value1, value2, "billno");
            return (Criteria) this;
        }

        public Criteria andBillnoNotBetween(String value1, String value2) {
            addCriterion("billno not between", value1, value2, "billno");
            return (Criteria) this;
        }

        public Criteria andDiliverycompanyIsNull() {
            addCriterion("diliverycompany is null");
            return (Criteria) this;
        }

        public Criteria andDiliverycompanyIsNotNull() {
            addCriterion("diliverycompany is not null");
            return (Criteria) this;
        }

        public Criteria andDiliverycompanyEqualTo(String value) {
            addCriterion("diliverycompany =", value, "diliverycompany");
            return (Criteria) this;
        }

        public Criteria andDiliverycompanyNotEqualTo(String value) {
            addCriterion("diliverycompany <>", value, "diliverycompany");
            return (Criteria) this;
        }

        public Criteria andDiliverycompanyGreaterThan(String value) {
            addCriterion("diliverycompany >", value, "diliverycompany");
            return (Criteria) this;
        }

        public Criteria andDiliverycompanyGreaterThanOrEqualTo(String value) {
            addCriterion("diliverycompany >=", value, "diliverycompany");
            return (Criteria) this;
        }

        public Criteria andDiliverycompanyLessThan(String value) {
            addCriterion("diliverycompany <", value, "diliverycompany");
            return (Criteria) this;
        }

        public Criteria andDiliverycompanyLessThanOrEqualTo(String value) {
            addCriterion("diliverycompany <=", value, "diliverycompany");
            return (Criteria) this;
        }

        public Criteria andDiliverycompanyLike(String value) {
            addCriterion("diliverycompany like", value, "diliverycompany");
            return (Criteria) this;
        }

        public Criteria andDiliverycompanyNotLike(String value) {
            addCriterion("diliverycompany not like", value, "diliverycompany");
            return (Criteria) this;
        }

        public Criteria andDiliverycompanyIn(List<String> values) {
            addCriterion("diliverycompany in", values, "diliverycompany");
            return (Criteria) this;
        }

        public Criteria andDiliverycompanyNotIn(List<String> values) {
            addCriterion("diliverycompany not in", values, "diliverycompany");
            return (Criteria) this;
        }

        public Criteria andDiliverycompanyBetween(String value1, String value2) {
            addCriterion("diliverycompany between", value1, value2, "diliverycompany");
            return (Criteria) this;
        }

        public Criteria andDiliverycompanyNotBetween(String value1, String value2) {
            addCriterion("diliverycompany not between", value1, value2, "diliverycompany");
            return (Criteria) this;
        }

        public Criteria andDiliverynoIsNull() {
            addCriterion("diliveryno is null");
            return (Criteria) this;
        }

        public Criteria andDiliverynoIsNotNull() {
            addCriterion("diliveryno is not null");
            return (Criteria) this;
        }

        public Criteria andDiliverynoEqualTo(String value) {
            addCriterion("diliveryno =", value, "diliveryno");
            return (Criteria) this;
        }

        public Criteria andDiliverynoNotEqualTo(String value) {
            addCriterion("diliveryno <>", value, "diliveryno");
            return (Criteria) this;
        }

        public Criteria andDiliverynoGreaterThan(String value) {
            addCriterion("diliveryno >", value, "diliveryno");
            return (Criteria) this;
        }

        public Criteria andDiliverynoGreaterThanOrEqualTo(String value) {
            addCriterion("diliveryno >=", value, "diliveryno");
            return (Criteria) this;
        }

        public Criteria andDiliverynoLessThan(String value) {
            addCriterion("diliveryno <", value, "diliveryno");
            return (Criteria) this;
        }

        public Criteria andDiliverynoLessThanOrEqualTo(String value) {
            addCriterion("diliveryno <=", value, "diliveryno");
            return (Criteria) this;
        }

        public Criteria andDiliverynoLike(String value) {
            addCriterion("diliveryno like", value, "diliveryno");
            return (Criteria) this;
        }

        public Criteria andDiliverynoNotLike(String value) {
            addCriterion("diliveryno not like", value, "diliveryno");
            return (Criteria) this;
        }

        public Criteria andDiliverynoIn(List<String> values) {
            addCriterion("diliveryno in", values, "diliveryno");
            return (Criteria) this;
        }

        public Criteria andDiliverynoNotIn(List<String> values) {
            addCriterion("diliveryno not in", values, "diliveryno");
            return (Criteria) this;
        }

        public Criteria andDiliverynoBetween(String value1, String value2) {
            addCriterion("diliveryno between", value1, value2, "diliveryno");
            return (Criteria) this;
        }

        public Criteria andDiliverynoNotBetween(String value1, String value2) {
            addCriterion("diliveryno not between", value1, value2, "diliveryno");
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

        public Criteria andApplytimeIsNull() {
            addCriterion("applytime is null");
            return (Criteria) this;
        }

        public Criteria andApplytimeIsNotNull() {
            addCriterion("applytime is not null");
            return (Criteria) this;
        }

        public Criteria andApplytimeEqualTo(Date value) {
            addCriterion("applytime =", value, "applytime");
            return (Criteria) this;
        }

        public Criteria andApplytimeNotEqualTo(Date value) {
            addCriterion("applytime <>", value, "applytime");
            return (Criteria) this;
        }

        public Criteria andApplytimeGreaterThan(Date value) {
            addCriterion("applytime >", value, "applytime");
            return (Criteria) this;
        }

        public Criteria andApplytimeGreaterThanOrEqualTo(Date value) {
            addCriterion("applytime >=", value, "applytime");
            return (Criteria) this;
        }

        public Criteria andApplytimeLessThan(Date value) {
            addCriterion("applytime <", value, "applytime");
            return (Criteria) this;
        }

        public Criteria andApplytimeLessThanOrEqualTo(Date value) {
            addCriterion("applytime <=", value, "applytime");
            return (Criteria) this;
        }

        public Criteria andApplytimeIn(List<Date> values) {
            addCriterion("applytime in", values, "applytime");
            return (Criteria) this;
        }

        public Criteria andApplytimeNotIn(List<Date> values) {
            addCriterion("applytime not in", values, "applytime");
            return (Criteria) this;
        }

        public Criteria andApplytimeBetween(Date value1, Date value2) {
            addCriterion("applytime between", value1, value2, "applytime");
            return (Criteria) this;
        }

        public Criteria andApplytimeNotBetween(Date value1, Date value2) {
            addCriterion("applytime not between", value1, value2, "applytime");
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