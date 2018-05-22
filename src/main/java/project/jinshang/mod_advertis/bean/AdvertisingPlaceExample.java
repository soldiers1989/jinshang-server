package project.jinshang.mod_advertis.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdvertisingPlaceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AdvertisingPlaceExample() {
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

        public Criteria andPositionIsNull() {
            addCriterion("position is null");
            return (Criteria) this;
        }

        public Criteria andPositionIsNotNull() {
            addCriterion("position is not null");
            return (Criteria) this;
        }

        public Criteria andPositionEqualTo(String value) {
            addCriterion("position =", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotEqualTo(String value) {
            addCriterion("position <>", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionGreaterThan(String value) {
            addCriterion("position >", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionGreaterThanOrEqualTo(String value) {
            addCriterion("position >=", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLessThan(String value) {
            addCriterion("position <", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLessThanOrEqualTo(String value) {
            addCriterion("position <=", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLike(String value) {
            addCriterion("position like", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotLike(String value) {
            addCriterion("position not like", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionIn(List<String> values) {
            addCriterion("position in", values, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotIn(List<String> values) {
            addCriterion("position not in", values, "position");
            return (Criteria) this;
        }

        public Criteria andPositionBetween(String value1, String value2) {
            addCriterion("position between", value1, value2, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotBetween(String value1, String value2) {
            addCriterion("position not between", value1, value2, "position");
            return (Criteria) this;
        }

        public Criteria andDescribeIsNull() {
            addCriterion("describe is null");
            return (Criteria) this;
        }

        public Criteria andDescribeIsNotNull() {
            addCriterion("describe is not null");
            return (Criteria) this;
        }

        public Criteria andDescribeEqualTo(String value) {
            addCriterion("describe =", value, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeNotEqualTo(String value) {
            addCriterion("describe <>", value, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeGreaterThan(String value) {
            addCriterion("describe >", value, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeGreaterThanOrEqualTo(String value) {
            addCriterion("describe >=", value, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeLessThan(String value) {
            addCriterion("describe <", value, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeLessThanOrEqualTo(String value) {
            addCriterion("describe <=", value, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeLike(String value) {
            addCriterion("describe like", value, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeNotLike(String value) {
            addCriterion("describe not like", value, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeIn(List<String> values) {
            addCriterion("describe in", values, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeNotIn(List<String> values) {
            addCriterion("describe not in", values, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeBetween(String value1, String value2) {
            addCriterion("describe between", value1, value2, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeNotBetween(String value1, String value2) {
            addCriterion("describe not between", value1, value2, "describe");
            return (Criteria) this;
        }

        public Criteria andAdvtypeIsNull() {
            addCriterion("advtype is null");
            return (Criteria) this;
        }

        public Criteria andAdvtypeIsNotNull() {
            addCriterion("advtype is not null");
            return (Criteria) this;
        }

        public Criteria andAdvtypeEqualTo(String value) {
            addCriterion("advtype =", value, "advtype");
            return (Criteria) this;
        }

        public Criteria andAdvtypeNotEqualTo(String value) {
            addCriterion("advtype <>", value, "advtype");
            return (Criteria) this;
        }

        public Criteria andAdvtypeGreaterThan(String value) {
            addCriterion("advtype >", value, "advtype");
            return (Criteria) this;
        }

        public Criteria andAdvtypeGreaterThanOrEqualTo(String value) {
            addCriterion("advtype >=", value, "advtype");
            return (Criteria) this;
        }

        public Criteria andAdvtypeLessThan(String value) {
            addCriterion("advtype <", value, "advtype");
            return (Criteria) this;
        }

        public Criteria andAdvtypeLessThanOrEqualTo(String value) {
            addCriterion("advtype <=", value, "advtype");
            return (Criteria) this;
        }

        public Criteria andAdvtypeLike(String value) {
            addCriterion("advtype like", value, "advtype");
            return (Criteria) this;
        }

        public Criteria andAdvtypeNotLike(String value) {
            addCriterion("advtype not like", value, "advtype");
            return (Criteria) this;
        }

        public Criteria andAdvtypeIn(List<String> values) {
            addCriterion("advtype in", values, "advtype");
            return (Criteria) this;
        }

        public Criteria andAdvtypeNotIn(List<String> values) {
            addCriterion("advtype not in", values, "advtype");
            return (Criteria) this;
        }

        public Criteria andAdvtypeBetween(String value1, String value2) {
            addCriterion("advtype between", value1, value2, "advtype");
            return (Criteria) this;
        }

        public Criteria andAdvtypeNotBetween(String value1, String value2) {
            addCriterion("advtype not between", value1, value2, "advtype");
            return (Criteria) this;
        }

        public Criteria andShowtypeIsNull() {
            addCriterion("showtype is null");
            return (Criteria) this;
        }

        public Criteria andShowtypeIsNotNull() {
            addCriterion("showtype is not null");
            return (Criteria) this;
        }

        public Criteria andShowtypeEqualTo(String value) {
            addCriterion("showtype =", value, "showtype");
            return (Criteria) this;
        }

        public Criteria andShowtypeNotEqualTo(String value) {
            addCriterion("showtype <>", value, "showtype");
            return (Criteria) this;
        }

        public Criteria andShowtypeGreaterThan(String value) {
            addCriterion("showtype >", value, "showtype");
            return (Criteria) this;
        }

        public Criteria andShowtypeGreaterThanOrEqualTo(String value) {
            addCriterion("showtype >=", value, "showtype");
            return (Criteria) this;
        }

        public Criteria andShowtypeLessThan(String value) {
            addCriterion("showtype <", value, "showtype");
            return (Criteria) this;
        }

        public Criteria andShowtypeLessThanOrEqualTo(String value) {
            addCriterion("showtype <=", value, "showtype");
            return (Criteria) this;
        }

        public Criteria andShowtypeLike(String value) {
            addCriterion("showtype like", value, "showtype");
            return (Criteria) this;
        }

        public Criteria andShowtypeNotLike(String value) {
            addCriterion("showtype not like", value, "showtype");
            return (Criteria) this;
        }

        public Criteria andShowtypeIn(List<String> values) {
            addCriterion("showtype in", values, "showtype");
            return (Criteria) this;
        }

        public Criteria andShowtypeNotIn(List<String> values) {
            addCriterion("showtype not in", values, "showtype");
            return (Criteria) this;
        }

        public Criteria andShowtypeBetween(String value1, String value2) {
            addCriterion("showtype between", value1, value2, "showtype");
            return (Criteria) this;
        }

        public Criteria andShowtypeNotBetween(String value1, String value2) {
            addCriterion("showtype not between", value1, value2, "showtype");
            return (Criteria) this;
        }

        public Criteria andGettagIsNull() {
            addCriterion("gettag is null");
            return (Criteria) this;
        }

        public Criteria andGettagIsNotNull() {
            addCriterion("gettag is not null");
            return (Criteria) this;
        }

        public Criteria andGettagEqualTo(String value) {
            addCriterion("gettag =", value, "gettag");
            return (Criteria) this;
        }

        public Criteria andGettagNotEqualTo(String value) {
            addCriterion("gettag <>", value, "gettag");
            return (Criteria) this;
        }

        public Criteria andGettagGreaterThan(String value) {
            addCriterion("gettag >", value, "gettag");
            return (Criteria) this;
        }

        public Criteria andGettagGreaterThanOrEqualTo(String value) {
            addCriterion("gettag >=", value, "gettag");
            return (Criteria) this;
        }

        public Criteria andGettagLessThan(String value) {
            addCriterion("gettag <", value, "gettag");
            return (Criteria) this;
        }

        public Criteria andGettagLessThanOrEqualTo(String value) {
            addCriterion("gettag <=", value, "gettag");
            return (Criteria) this;
        }

        public Criteria andGettagLike(String value) {
            addCriterion("gettag like", value, "gettag");
            return (Criteria) this;
        }

        public Criteria andGettagNotLike(String value) {
            addCriterion("gettag not like", value, "gettag");
            return (Criteria) this;
        }

        public Criteria andGettagIn(List<String> values) {
            addCriterion("gettag in", values, "gettag");
            return (Criteria) this;
        }

        public Criteria andGettagNotIn(List<String> values) {
            addCriterion("gettag not in", values, "gettag");
            return (Criteria) this;
        }

        public Criteria andGettagBetween(String value1, String value2) {
            addCriterion("gettag between", value1, value2, "gettag");
            return (Criteria) this;
        }

        public Criteria andGettagNotBetween(String value1, String value2) {
            addCriterion("gettag not between", value1, value2, "gettag");
            return (Criteria) this;
        }

        public Criteria andStopIsNull() {
            addCriterion("stop is null");
            return (Criteria) this;
        }

        public Criteria andStopIsNotNull() {
            addCriterion("stop is not null");
            return (Criteria) this;
        }

        public Criteria andStopEqualTo(Short value) {
            addCriterion("stop =", value, "stop");
            return (Criteria) this;
        }

        public Criteria andStopNotEqualTo(Short value) {
            addCriterion("stop <>", value, "stop");
            return (Criteria) this;
        }

        public Criteria andStopGreaterThan(Short value) {
            addCriterion("stop >", value, "stop");
            return (Criteria) this;
        }

        public Criteria andStopGreaterThanOrEqualTo(Short value) {
            addCriterion("stop >=", value, "stop");
            return (Criteria) this;
        }

        public Criteria andStopLessThan(Short value) {
            addCriterion("stop <", value, "stop");
            return (Criteria) this;
        }

        public Criteria andStopLessThanOrEqualTo(Short value) {
            addCriterion("stop <=", value, "stop");
            return (Criteria) this;
        }

        public Criteria andStopIn(List<Short> values) {
            addCriterion("stop in", values, "stop");
            return (Criteria) this;
        }

        public Criteria andStopNotIn(List<Short> values) {
            addCriterion("stop not in", values, "stop");
            return (Criteria) this;
        }

        public Criteria andStopBetween(Short value1, Short value2) {
            addCriterion("stop between", value1, value2, "stop");
            return (Criteria) this;
        }

        public Criteria andStopNotBetween(Short value1, Short value2) {
            addCriterion("stop not between", value1, value2, "stop");
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

        public Criteria andWeightEqualTo(Float value) {
            addCriterion("weight =", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotEqualTo(Float value) {
            addCriterion("weight <>", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThan(Float value) {
            addCriterion("weight >", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThanOrEqualTo(Float value) {
            addCriterion("weight >=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThan(Float value) {
            addCriterion("weight <", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThanOrEqualTo(Float value) {
            addCriterion("weight <=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightIn(List<Float> values) {
            addCriterion("weight in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotIn(List<Float> values) {
            addCriterion("weight not in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightBetween(Float value1, Float value2) {
            addCriterion("weight between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotBetween(Float value1, Float value2) {
            addCriterion("weight not between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andHeightIsNull() {
            addCriterion("height is null");
            return (Criteria) this;
        }

        public Criteria andHeightIsNotNull() {
            addCriterion("height is not null");
            return (Criteria) this;
        }

        public Criteria andHeightEqualTo(Float value) {
            addCriterion("height =", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightNotEqualTo(Float value) {
            addCriterion("height <>", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightGreaterThan(Float value) {
            addCriterion("height >", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightGreaterThanOrEqualTo(Float value) {
            addCriterion("height >=", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightLessThan(Float value) {
            addCriterion("height <", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightLessThanOrEqualTo(Float value) {
            addCriterion("height <=", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightIn(List<Float> values) {
            addCriterion("height in", values, "height");
            return (Criteria) this;
        }

        public Criteria andHeightNotIn(List<Float> values) {
            addCriterion("height not in", values, "height");
            return (Criteria) this;
        }

        public Criteria andHeightBetween(Float value1, Float value2) {
            addCriterion("height between", value1, value2, "height");
            return (Criteria) this;
        }

        public Criteria andHeightNotBetween(Float value1, Float value2) {
            addCriterion("height not between", value1, value2, "height");
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

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table advertisingplace
     *
     * @mbggenerated do_not_delete_during_merge Tue Dec 05 16:56:27 CST 2017
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table advertisingplace
     *
     * @mbggenerated Tue Dec 05 16:56:27 CST 2017
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