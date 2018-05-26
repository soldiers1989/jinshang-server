package project.jinshang.mod_member.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ApiRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ApiRecordExample() {
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

        public Criteria andAppidIsNull() {
            addCriterion("appId is null");
            return (Criteria) this;
        }

        public Criteria andAppidIsNotNull() {
            addCriterion("appId is not null");
            return (Criteria) this;
        }

        public Criteria andAppidEqualTo(String value) {
            addCriterion("appId =", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidNotEqualTo(String value) {
            addCriterion("appId <>", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidGreaterThan(String value) {
            addCriterion("appId >", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidGreaterThanOrEqualTo(String value) {
            addCriterion("appId >=", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidLessThan(String value) {
            addCriterion("appId <", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidLessThanOrEqualTo(String value) {
            addCriterion("appId <=", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidLike(String value) {
            addCriterion("appId like", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidNotLike(String value) {
            addCriterion("appId not like", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidIn(List<String> values) {
            addCriterion("appId in", values, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidNotIn(List<String> values) {
            addCriterion("appId not in", values, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidBetween(String value1, String value2) {
            addCriterion("appId between", value1, value2, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidNotBetween(String value1, String value2) {
            addCriterion("appId not between", value1, value2, "appid");
            return (Criteria) this;
        }

        public Criteria andAppurlIsNull() {
            addCriterion("appUrl is null");
            return (Criteria) this;
        }

        public Criteria andAppurlIsNotNull() {
            addCriterion("appUrl is not null");
            return (Criteria) this;
        }

        public Criteria andAppurlEqualTo(String value) {
            addCriterion("appUrl =", value, "appurl");
            return (Criteria) this;
        }

        public Criteria andAppurlNotEqualTo(String value) {
            addCriterion("appUrl <>", value, "appurl");
            return (Criteria) this;
        }

        public Criteria andAppurlGreaterThan(String value) {
            addCriterion("appUrl >", value, "appurl");
            return (Criteria) this;
        }

        public Criteria andAppurlGreaterThanOrEqualTo(String value) {
            addCriterion("appUrl >=", value, "appurl");
            return (Criteria) this;
        }

        public Criteria andAppurlLessThan(String value) {
            addCriterion("appUrl <", value, "appurl");
            return (Criteria) this;
        }

        public Criteria andAppurlLessThanOrEqualTo(String value) {
            addCriterion("appUrl <=", value, "appurl");
            return (Criteria) this;
        }

        public Criteria andAppurlLike(String value) {
            addCriterion("appUrl like", value, "appurl");
            return (Criteria) this;
        }

        public Criteria andAppurlNotLike(String value) {
            addCriterion("appUrl not like", value, "appurl");
            return (Criteria) this;
        }

        public Criteria andAppurlIn(List<String> values) {
            addCriterion("appUrl in", values, "appurl");
            return (Criteria) this;
        }

        public Criteria andAppurlNotIn(List<String> values) {
            addCriterion("appUrl not in", values, "appurl");
            return (Criteria) this;
        }

        public Criteria andAppurlBetween(String value1, String value2) {
            addCriterion("appUrl between", value1, value2, "appurl");
            return (Criteria) this;
        }

        public Criteria andAppurlNotBetween(String value1, String value2) {
            addCriterion("appUrl not between", value1, value2, "appurl");
            return (Criteria) this;
        }

        public Criteria andApicodeIsNull() {
            addCriterion("apiCode is null");
            return (Criteria) this;
        }

        public Criteria andApicodeIsNotNull() {
            addCriterion("apiCode is not null");
            return (Criteria) this;
        }

        public Criteria andApicodeEqualTo(String value) {
            addCriterion("apiCode =", value, "apicode");
            return (Criteria) this;
        }

        public Criteria andApicodeNotEqualTo(String value) {
            addCriterion("apiCode <>", value, "apicode");
            return (Criteria) this;
        }

        public Criteria andApicodeGreaterThan(String value) {
            addCriterion("apiCode >", value, "apicode");
            return (Criteria) this;
        }

        public Criteria andApicodeGreaterThanOrEqualTo(String value) {
            addCriterion("apiCode >=", value, "apicode");
            return (Criteria) this;
        }

        public Criteria andApicodeLessThan(String value) {
            addCriterion("apiCode <", value, "apicode");
            return (Criteria) this;
        }

        public Criteria andApicodeLessThanOrEqualTo(String value) {
            addCriterion("apiCode <=", value, "apicode");
            return (Criteria) this;
        }

        public Criteria andApicodeLike(String value) {
            addCriterion("apiCode like", value, "apicode");
            return (Criteria) this;
        }

        public Criteria andApicodeNotLike(String value) {
            addCriterion("apiCode not like", value, "apicode");
            return (Criteria) this;
        }

        public Criteria andApicodeIn(List<String> values) {
            addCriterion("apiCode in", values, "apicode");
            return (Criteria) this;
        }

        public Criteria andApicodeNotIn(List<String> values) {
            addCriterion("apiCode not in", values, "apicode");
            return (Criteria) this;
        }

        public Criteria andApicodeBetween(String value1, String value2) {
            addCriterion("apiCode between", value1, value2, "apicode");
            return (Criteria) this;
        }

        public Criteria andApicodeNotBetween(String value1, String value2) {
            addCriterion("apiCode not between", value1, value2, "apicode");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andReturnjsonIsNull() {
            addCriterion("returnJson is null");
            return (Criteria) this;
        }

        public Criteria andReturnjsonIsNotNull() {
            addCriterion("returnJson is not null");
            return (Criteria) this;
        }

        public Criteria andReturnjsonEqualTo(String value) {
            addCriterion("returnJson =", value, "returnjson");
            return (Criteria) this;
        }

        public Criteria andReturnjsonNotEqualTo(String value) {
            addCriterion("returnJson <>", value, "returnjson");
            return (Criteria) this;
        }

        public Criteria andReturnjsonGreaterThan(String value) {
            addCriterion("returnJson >", value, "returnjson");
            return (Criteria) this;
        }

        public Criteria andReturnjsonGreaterThanOrEqualTo(String value) {
            addCriterion("returnJson >=", value, "returnjson");
            return (Criteria) this;
        }

        public Criteria andReturnjsonLessThan(String value) {
            addCriterion("returnJson <", value, "returnjson");
            return (Criteria) this;
        }

        public Criteria andReturnjsonLessThanOrEqualTo(String value) {
            addCriterion("returnJson <=", value, "returnjson");
            return (Criteria) this;
        }

        public Criteria andReturnjsonLike(String value) {
            addCriterion("returnJson like", value, "returnjson");
            return (Criteria) this;
        }

        public Criteria andReturnjsonNotLike(String value) {
            addCriterion("returnJson not like", value, "returnjson");
            return (Criteria) this;
        }

        public Criteria andReturnjsonIn(List<String> values) {
            addCriterion("returnJson in", values, "returnjson");
            return (Criteria) this;
        }

        public Criteria andReturnjsonNotIn(List<String> values) {
            addCriterion("returnJson not in", values, "returnjson");
            return (Criteria) this;
        }

        public Criteria andReturnjsonBetween(String value1, String value2) {
            addCriterion("returnJson between", value1, value2, "returnjson");
            return (Criteria) this;
        }

        public Criteria andReturnjsonNotBetween(String value1, String value2) {
            addCriterion("returnJson not between", value1, value2, "returnjson");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createTime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createTime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createTime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createTime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createTime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createTime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createTime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createTime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createTime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createTime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createTime not between", value1, value2, "createtime");
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