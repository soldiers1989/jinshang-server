package project.jinshang.mod_transaction.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TransactionLogExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTrannoIsNull() {
            addCriterion("tranno is null");
            return (Criteria) this;
        }

        public Criteria andTrannoIsNotNull() {
            addCriterion("tranno is not null");
            return (Criteria) this;
        }

        public Criteria andTrannoEqualTo(String value) {
            addCriterion("tranno =", value, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoNotEqualTo(String value) {
            addCriterion("tranno <>", value, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoGreaterThan(String value) {
            addCriterion("tranno >", value, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoGreaterThanOrEqualTo(String value) {
            addCriterion("tranno >=", value, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoLessThan(String value) {
            addCriterion("tranno <", value, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoLessThanOrEqualTo(String value) {
            addCriterion("tranno <=", value, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoLike(String value) {
            addCriterion("tranno like", value, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoNotLike(String value) {
            addCriterion("tranno not like", value, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoIn(List<String> values) {
            addCriterion("tranno in", values, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoNotIn(List<String> values) {
            addCriterion("tranno not in", values, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoBetween(String value1, String value2) {
            addCriterion("tranno between", value1, value2, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoNotBetween(String value1, String value2) {
            addCriterion("tranno not between", value1, value2, "tranno");
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

        public Criteria andRejectednoIsNull() {
            addCriterion("rejectedno is null");
            return (Criteria) this;
        }

        public Criteria andRejectednoIsNotNull() {
            addCriterion("rejectedno is not null");
            return (Criteria) this;
        }

        public Criteria andRejectednoEqualTo(String value) {
            addCriterion("rejectedno =", value, "rejectedno");
            return (Criteria) this;
        }

        public Criteria andRejectednoNotEqualTo(String value) {
            addCriterion("rejectedno <>", value, "rejectedno");
            return (Criteria) this;
        }

        public Criteria andRejectednoGreaterThan(String value) {
            addCriterion("rejectedno >", value, "rejectedno");
            return (Criteria) this;
        }

        public Criteria andRejectednoGreaterThanOrEqualTo(String value) {
            addCriterion("rejectedno >=", value, "rejectedno");
            return (Criteria) this;
        }

        public Criteria andRejectednoLessThan(String value) {
            addCriterion("rejectedno <", value, "rejectedno");
            return (Criteria) this;
        }

        public Criteria andRejectednoLessThanOrEqualTo(String value) {
            addCriterion("rejectedno <=", value, "rejectedno");
            return (Criteria) this;
        }

        public Criteria andRejectednoLike(String value) {
            addCriterion("rejectedno like", value, "rejectedno");
            return (Criteria) this;
        }

        public Criteria andRejectednoNotLike(String value) {
            addCriterion("rejectedno not like", value, "rejectedno");
            return (Criteria) this;
        }

        public Criteria andRejectednoIn(List<String> values) {
            addCriterion("rejectedno in", values, "rejectedno");
            return (Criteria) this;
        }

        public Criteria andRejectednoNotIn(List<String> values) {
            addCriterion("rejectedno not in", values, "rejectedno");
            return (Criteria) this;
        }

        public Criteria andRejectednoBetween(String value1, String value2) {
            addCriterion("rejectedno between", value1, value2, "rejectedno");
            return (Criteria) this;
        }

        public Criteria andRejectednoNotBetween(String value1, String value2) {
            addCriterion("rejectedno not between", value1, value2, "rejectedno");
            return (Criteria) this;
        }

        public Criteria andTrantypeIsNull() {
            addCriterion("trantype is null");
            return (Criteria) this;
        }

        public Criteria andTrantypeIsNotNull() {
            addCriterion("trantype is not null");
            return (Criteria) this;
        }

        public Criteria andTrantypeEqualTo(Short value) {
            addCriterion("trantype =", value, "trantype");
            return (Criteria) this;
        }

        public Criteria andTrantypeNotEqualTo(Short value) {
            addCriterion("trantype <>", value, "trantype");
            return (Criteria) this;
        }

        public Criteria andTrantypeGreaterThan(Short value) {
            addCriterion("trantype >", value, "trantype");
            return (Criteria) this;
        }

        public Criteria andTrantypeGreaterThanOrEqualTo(Short value) {
            addCriterion("trantype >=", value, "trantype");
            return (Criteria) this;
        }

        public Criteria andTrantypeLessThan(Short value) {
            addCriterion("trantype <", value, "trantype");
            return (Criteria) this;
        }

        public Criteria andTrantypeLessThanOrEqualTo(Short value) {
            addCriterion("trantype <=", value, "trantype");
            return (Criteria) this;
        }

        public Criteria andTrantypeIn(List<Short> values) {
            addCriterion("trantype in", values, "trantype");
            return (Criteria) this;
        }

        public Criteria andTrantypeNotIn(List<Short> values) {
            addCriterion("trantype not in", values, "trantype");
            return (Criteria) this;
        }

        public Criteria andTrantypeBetween(Short value1, Short value2) {
            addCriterion("trantype between", value1, value2, "trantype");
            return (Criteria) this;
        }

        public Criteria andTrantypeNotBetween(Short value1, Short value2) {
            addCriterion("trantype not between", value1, value2, "trantype");
            return (Criteria) this;
        }

        public Criteria andTrannumIsNull() {
            addCriterion("trannum is null");
            return (Criteria) this;
        }

        public Criteria andTrannumIsNotNull() {
            addCriterion("trannum is not null");
            return (Criteria) this;
        }

        public Criteria andTrannumEqualTo(BigDecimal value) {
            addCriterion("trannum =", value, "trannum");
            return (Criteria) this;
        }

        public Criteria andTrannumNotEqualTo(BigDecimal value) {
            addCriterion("trannum <>", value, "trannum");
            return (Criteria) this;
        }

        public Criteria andTrannumGreaterThan(BigDecimal value) {
            addCriterion("trannum >", value, "trannum");
            return (Criteria) this;
        }

        public Criteria andTrannumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("trannum >=", value, "trannum");
            return (Criteria) this;
        }

        public Criteria andTrannumLessThan(BigDecimal value) {
            addCriterion("trannum <", value, "trannum");
            return (Criteria) this;
        }

        public Criteria andTrannumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("trannum <=", value, "trannum");
            return (Criteria) this;
        }

        public Criteria andTrannumIn(List<BigDecimal> values) {
            addCriterion("trannum in", values, "trannum");
            return (Criteria) this;
        }

        public Criteria andTrannumNotIn(List<BigDecimal> values) {
            addCriterion("trannum not in", values, "trannum");
            return (Criteria) this;
        }

        public Criteria andTrannumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("trannum between", value1, value2, "trannum");
            return (Criteria) this;
        }

        public Criteria andTrannumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("trannum not between", value1, value2, "trannum");
            return (Criteria) this;
        }

        public Criteria andTranstateIsNull() {
            addCriterion("transtate is null");
            return (Criteria) this;
        }

        public Criteria andTranstateIsNotNull() {
            addCriterion("transtate is not null");
            return (Criteria) this;
        }

        public Criteria andTranstateEqualTo(Short value) {
            addCriterion("transtate =", value, "transtate");
            return (Criteria) this;
        }

        public Criteria andTranstateNotEqualTo(Short value) {
            addCriterion("transtate <>", value, "transtate");
            return (Criteria) this;
        }

        public Criteria andTranstateGreaterThan(Short value) {
            addCriterion("transtate >", value, "transtate");
            return (Criteria) this;
        }

        public Criteria andTranstateGreaterThanOrEqualTo(Short value) {
            addCriterion("transtate >=", value, "transtate");
            return (Criteria) this;
        }

        public Criteria andTranstateLessThan(Short value) {
            addCriterion("transtate <", value, "transtate");
            return (Criteria) this;
        }

        public Criteria andTranstateLessThanOrEqualTo(Short value) {
            addCriterion("transtate <=", value, "transtate");
            return (Criteria) this;
        }

        public Criteria andTranstateIn(List<Short> values) {
            addCriterion("transtate in", values, "transtate");
            return (Criteria) this;
        }

        public Criteria andTranstateNotIn(List<Short> values) {
            addCriterion("transtate not in", values, "transtate");
            return (Criteria) this;
        }

        public Criteria andTranstateBetween(Short value1, Short value2) {
            addCriterion("transtate between", value1, value2, "transtate");
            return (Criteria) this;
        }

        public Criteria andTranstateNotBetween(Short value1, Short value2) {
            addCriterion("transtate not between", value1, value2, "transtate");
            return (Criteria) this;
        }

        public Criteria andGoodsnameIsNull() {
            addCriterion("goodsname is null");
            return (Criteria) this;
        }

        public Criteria andGoodsnameIsNotNull() {
            addCriterion("goodsname is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsnameEqualTo(String value) {
            addCriterion("goodsname =", value, "goodsname");
            return (Criteria) this;
        }

        public Criteria andGoodsnameNotEqualTo(String value) {
            addCriterion("goodsname <>", value, "goodsname");
            return (Criteria) this;
        }

        public Criteria andGoodsnameGreaterThan(String value) {
            addCriterion("goodsname >", value, "goodsname");
            return (Criteria) this;
        }

        public Criteria andGoodsnameGreaterThanOrEqualTo(String value) {
            addCriterion("goodsname >=", value, "goodsname");
            return (Criteria) this;
        }

        public Criteria andGoodsnameLessThan(String value) {
            addCriterion("goodsname <", value, "goodsname");
            return (Criteria) this;
        }

        public Criteria andGoodsnameLessThanOrEqualTo(String value) {
            addCriterion("goodsname <=", value, "goodsname");
            return (Criteria) this;
        }

        public Criteria andGoodsnameLike(String value) {
            addCriterion("goodsname like", value, "goodsname");
            return (Criteria) this;
        }

        public Criteria andGoodsnameNotLike(String value) {
            addCriterion("goodsname not like", value, "goodsname");
            return (Criteria) this;
        }

        public Criteria andGoodsnameIn(List<String> values) {
            addCriterion("goodsname in", values, "goodsname");
            return (Criteria) this;
        }

        public Criteria andGoodsnameNotIn(List<String> values) {
            addCriterion("goodsname not in", values, "goodsname");
            return (Criteria) this;
        }

        public Criteria andGoodsnameBetween(String value1, String value2) {
            addCriterion("goodsname between", value1, value2, "goodsname");
            return (Criteria) this;
        }

        public Criteria andGoodsnameNotBetween(String value1, String value2) {
            addCriterion("goodsname not between", value1, value2, "goodsname");
            return (Criteria) this;
        }

        public Criteria andTrantimeIsNull() {
            addCriterion("trantime is null");
            return (Criteria) this;
        }

        public Criteria andTrantimeIsNotNull() {
            addCriterion("trantime is not null");
            return (Criteria) this;
        }

        public Criteria andTrantimeEqualTo(Date value) {
            addCriterion("trantime =", value, "trantime");
            return (Criteria) this;
        }

        public Criteria andTrantimeNotEqualTo(Date value) {
            addCriterion("trantime <>", value, "trantime");
            return (Criteria) this;
        }

        public Criteria andTrantimeGreaterThan(Date value) {
            addCriterion("trantime >", value, "trantime");
            return (Criteria) this;
        }

        public Criteria andTrantimeGreaterThanOrEqualTo(Date value) {
            addCriterion("trantime >=", value, "trantime");
            return (Criteria) this;
        }

        public Criteria andTrantimeLessThan(Date value) {
            addCriterion("trantime <", value, "trantime");
            return (Criteria) this;
        }

        public Criteria andTrantimeLessThanOrEqualTo(Date value) {
            addCriterion("trantime <=", value, "trantime");
            return (Criteria) this;
        }

        public Criteria andTrantimeIn(List<Date> values) {
            addCriterion("trantime in", values, "trantime");
            return (Criteria) this;
        }

        public Criteria andTrantimeNotIn(List<Date> values) {
            addCriterion("trantime not in", values, "trantime");
            return (Criteria) this;
        }

        public Criteria andTrantimeBetween(Date value1, Date value2) {
            addCriterion("trantime between", value1, value2, "trantime");
            return (Criteria) this;
        }

        public Criteria andTrantimeNotBetween(Date value1, Date value2) {
            addCriterion("trantime not between", value1, value2, "trantime");
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