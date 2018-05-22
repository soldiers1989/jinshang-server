package project.jinshang.mod_feedback.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FeedbackExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FeedbackExample() {
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

        public Criteria andFeedbackTypeIdIsNull() {
            addCriterion("feedback_type_id is null");
            return (Criteria) this;
        }

        public Criteria andFeedbackTypeIdIsNotNull() {
            addCriterion("feedback_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andFeedbackTypeIdEqualTo(Integer value) {
            addCriterion("feedback_type_id =", value, "feedbackTypeId");
            return (Criteria) this;
        }

        public Criteria andFeedbackTypeIdNotEqualTo(Integer value) {
            addCriterion("feedback_type_id <>", value, "feedbackTypeId");
            return (Criteria) this;
        }

        public Criteria andFeedbackTypeIdGreaterThan(Integer value) {
            addCriterion("feedback_type_id >", value, "feedbackTypeId");
            return (Criteria) this;
        }

        public Criteria andFeedbackTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("feedback_type_id >=", value, "feedbackTypeId");
            return (Criteria) this;
        }

        public Criteria andFeedbackTypeIdLessThan(Integer value) {
            addCriterion("feedback_type_id <", value, "feedbackTypeId");
            return (Criteria) this;
        }

        public Criteria andFeedbackTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("feedback_type_id <=", value, "feedbackTypeId");
            return (Criteria) this;
        }

        public Criteria andFeedbackTypeIdIn(List<Integer> values) {
            addCriterion("feedback_type_id in", values, "feedbackTypeId");
            return (Criteria) this;
        }

        public Criteria andFeedbackTypeIdNotIn(List<Integer> values) {
            addCriterion("feedback_type_id not in", values, "feedbackTypeId");
            return (Criteria) this;
        }

        public Criteria andFeedbackTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("feedback_type_id between", value1, value2, "feedbackTypeId");
            return (Criteria) this;
        }

        public Criteria andFeedbackTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("feedback_type_id not between", value1, value2, "feedbackTypeId");
            return (Criteria) this;
        }

        public Criteria andFeedbackContentIsNull() {
            addCriterion("feedback_content is null");
            return (Criteria) this;
        }

        public Criteria andFeedbackContentIsNotNull() {
            addCriterion("feedback_content is not null");
            return (Criteria) this;
        }

        public Criteria andFeedbackContentEqualTo(String value) {
            addCriterion("feedback_content =", value, "feedbackContent");
            return (Criteria) this;
        }

        public Criteria andFeedbackContentNotEqualTo(String value) {
            addCriterion("feedback_content <>", value, "feedbackContent");
            return (Criteria) this;
        }

        public Criteria andFeedbackContentGreaterThan(String value) {
            addCriterion("feedback_content >", value, "feedbackContent");
            return (Criteria) this;
        }

        public Criteria andFeedbackContentGreaterThanOrEqualTo(String value) {
            addCriterion("feedback_content >=", value, "feedbackContent");
            return (Criteria) this;
        }

        public Criteria andFeedbackContentLessThan(String value) {
            addCriterion("feedback_content <", value, "feedbackContent");
            return (Criteria) this;
        }

        public Criteria andFeedbackContentLessThanOrEqualTo(String value) {
            addCriterion("feedback_content <=", value, "feedbackContent");
            return (Criteria) this;
        }

        public Criteria andFeedbackContentLike(String value) {
            addCriterion("feedback_content like", value, "feedbackContent");
            return (Criteria) this;
        }

        public Criteria andFeedbackContentNotLike(String value) {
            addCriterion("feedback_content not like", value, "feedbackContent");
            return (Criteria) this;
        }

        public Criteria andFeedbackContentIn(List<String> values) {
            addCriterion("feedback_content in", values, "feedbackContent");
            return (Criteria) this;
        }

        public Criteria andFeedbackContentNotIn(List<String> values) {
            addCriterion("feedback_content not in", values, "feedbackContent");
            return (Criteria) this;
        }

        public Criteria andFeedbackContentBetween(String value1, String value2) {
            addCriterion("feedback_content between", value1, value2, "feedbackContent");
            return (Criteria) this;
        }

        public Criteria andFeedbackContentNotBetween(String value1, String value2) {
            addCriterion("feedback_content not between", value1, value2, "feedbackContent");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNull() {
            addCriterion("member_id is null");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNotNull() {
            addCriterion("member_id is not null");
            return (Criteria) this;
        }

        public Criteria andMemberIdEqualTo(Long value) {
            addCriterion("member_id =", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotEqualTo(Long value) {
            addCriterion("member_id <>", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThan(Long value) {
            addCriterion("member_id >", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThanOrEqualTo(Long value) {
            addCriterion("member_id >=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThan(Long value) {
            addCriterion("member_id <", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThanOrEqualTo(Long value) {
            addCriterion("member_id <=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdIn(List<Long> values) {
            addCriterion("member_id in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotIn(List<Long> values) {
            addCriterion("member_id not in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdBetween(Long value1, Long value2) {
            addCriterion("member_id between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotBetween(Long value1, Long value2) {
            addCriterion("member_id not between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andDisposeIsNull() {
            addCriterion("dispose is null");
            return (Criteria) this;
        }

        public Criteria andDisposeIsNotNull() {
            addCriterion("dispose is not null");
            return (Criteria) this;
        }

        public Criteria andDisposeEqualTo(Short value) {
            addCriterion("dispose =", value, "dispose");
            return (Criteria) this;
        }

        public Criteria andDisposeNotEqualTo(Short value) {
            addCriterion("dispose <>", value, "dispose");
            return (Criteria) this;
        }

        public Criteria andDisposeGreaterThan(Short value) {
            addCriterion("dispose >", value, "dispose");
            return (Criteria) this;
        }

        public Criteria andDisposeGreaterThanOrEqualTo(Short value) {
            addCriterion("dispose >=", value, "dispose");
            return (Criteria) this;
        }

        public Criteria andDisposeLessThan(Short value) {
            addCriterion("dispose <", value, "dispose");
            return (Criteria) this;
        }

        public Criteria andDisposeLessThanOrEqualTo(Short value) {
            addCriterion("dispose <=", value, "dispose");
            return (Criteria) this;
        }

        public Criteria andDisposeIn(List<Short> values) {
            addCriterion("dispose in", values, "dispose");
            return (Criteria) this;
        }

        public Criteria andDisposeNotIn(List<Short> values) {
            addCriterion("dispose not in", values, "dispose");
            return (Criteria) this;
        }

        public Criteria andDisposeBetween(Short value1, Short value2) {
            addCriterion("dispose between", value1, value2, "dispose");
            return (Criteria) this;
        }

        public Criteria andDisposeNotBetween(Short value1, Short value2) {
            addCriterion("dispose not between", value1, value2, "dispose");
            return (Criteria) this;
        }

        public Criteria andDisposePeopleIdIsNull() {
            addCriterion("dispose_people_id is null");
            return (Criteria) this;
        }

        public Criteria andDisposePeopleIdIsNotNull() {
            addCriterion("dispose_people_id is not null");
            return (Criteria) this;
        }

        public Criteria andDisposePeopleIdEqualTo(Long value) {
            addCriterion("dispose_people_id =", value, "disposePeopleId");
            return (Criteria) this;
        }

        public Criteria andDisposePeopleIdNotEqualTo(Long value) {
            addCriterion("dispose_people_id <>", value, "disposePeopleId");
            return (Criteria) this;
        }

        public Criteria andDisposePeopleIdGreaterThan(Long value) {
            addCriterion("dispose_people_id >", value, "disposePeopleId");
            return (Criteria) this;
        }

        public Criteria andDisposePeopleIdGreaterThanOrEqualTo(Long value) {
            addCriterion("dispose_people_id >=", value, "disposePeopleId");
            return (Criteria) this;
        }

        public Criteria andDisposePeopleIdLessThan(Long value) {
            addCriterion("dispose_people_id <", value, "disposePeopleId");
            return (Criteria) this;
        }

        public Criteria andDisposePeopleIdLessThanOrEqualTo(Long value) {
            addCriterion("dispose_people_id <=", value, "disposePeopleId");
            return (Criteria) this;
        }

        public Criteria andDisposePeopleIdIn(List<Long> values) {
            addCriterion("dispose_people_id in", values, "disposePeopleId");
            return (Criteria) this;
        }

        public Criteria andDisposePeopleIdNotIn(List<Long> values) {
            addCriterion("dispose_people_id not in", values, "disposePeopleId");
            return (Criteria) this;
        }

        public Criteria andDisposePeopleIdBetween(Long value1, Long value2) {
            addCriterion("dispose_people_id between", value1, value2, "disposePeopleId");
            return (Criteria) this;
        }

        public Criteria andDisposePeopleIdNotBetween(Long value1, Long value2) {
            addCriterion("dispose_people_id not between", value1, value2, "disposePeopleId");
            return (Criteria) this;
        }

        public Criteria andDisposeContentIsNull() {
            addCriterion("dispose_content is null");
            return (Criteria) this;
        }

        public Criteria andDisposeContentIsNotNull() {
            addCriterion("dispose_content is not null");
            return (Criteria) this;
        }

        public Criteria andDisposeContentEqualTo(String value) {
            addCriterion("dispose_content =", value, "disposeContent");
            return (Criteria) this;
        }

        public Criteria andDisposeContentNotEqualTo(String value) {
            addCriterion("dispose_content <>", value, "disposeContent");
            return (Criteria) this;
        }

        public Criteria andDisposeContentGreaterThan(String value) {
            addCriterion("dispose_content >", value, "disposeContent");
            return (Criteria) this;
        }

        public Criteria andDisposeContentGreaterThanOrEqualTo(String value) {
            addCriterion("dispose_content >=", value, "disposeContent");
            return (Criteria) this;
        }

        public Criteria andDisposeContentLessThan(String value) {
            addCriterion("dispose_content <", value, "disposeContent");
            return (Criteria) this;
        }

        public Criteria andDisposeContentLessThanOrEqualTo(String value) {
            addCriterion("dispose_content <=", value, "disposeContent");
            return (Criteria) this;
        }

        public Criteria andDisposeContentLike(String value) {
            addCriterion("dispose_content like", value, "disposeContent");
            return (Criteria) this;
        }

        public Criteria andDisposeContentNotLike(String value) {
            addCriterion("dispose_content not like", value, "disposeContent");
            return (Criteria) this;
        }

        public Criteria andDisposeContentIn(List<String> values) {
            addCriterion("dispose_content in", values, "disposeContent");
            return (Criteria) this;
        }

        public Criteria andDisposeContentNotIn(List<String> values) {
            addCriterion("dispose_content not in", values, "disposeContent");
            return (Criteria) this;
        }

        public Criteria andDisposeContentBetween(String value1, String value2) {
            addCriterion("dispose_content between", value1, value2, "disposeContent");
            return (Criteria) this;
        }

        public Criteria andDisposeContentNotBetween(String value1, String value2) {
            addCriterion("dispose_content not between", value1, value2, "disposeContent");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
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