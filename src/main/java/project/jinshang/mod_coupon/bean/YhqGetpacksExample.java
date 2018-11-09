package project.jinshang.mod_coupon.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class YhqGetpacksExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public YhqGetpacksExample() {
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

        public Criteria andPacksidIsNull() {
            addCriterion("packsid is null");
            return (Criteria) this;
        }

        public Criteria andPacksidIsNotNull() {
            addCriterion("packsid is not null");
            return (Criteria) this;
        }

        public Criteria andPacksidEqualTo(Long value) {
            addCriterion("packsid =", value, "packsid");
            return (Criteria) this;
        }

        public Criteria andPacksidNotEqualTo(Long value) {
            addCriterion("packsid <>", value, "packsid");
            return (Criteria) this;
        }

        public Criteria andPacksidGreaterThan(Long value) {
            addCriterion("packsid >", value, "packsid");
            return (Criteria) this;
        }

        public Criteria andPacksidGreaterThanOrEqualTo(Long value) {
            addCriterion("packsid >=", value, "packsid");
            return (Criteria) this;
        }

        public Criteria andPacksidLessThan(Long value) {
            addCriterion("packsid <", value, "packsid");
            return (Criteria) this;
        }

        public Criteria andPacksidLessThanOrEqualTo(Long value) {
            addCriterion("packsid <=", value, "packsid");
            return (Criteria) this;
        }

        public Criteria andPacksidIn(List<Long> values) {
            addCriterion("packsid in", values, "packsid");
            return (Criteria) this;
        }

        public Criteria andPacksidNotIn(List<Long> values) {
            addCriterion("packsid not in", values, "packsid");
            return (Criteria) this;
        }

        public Criteria andPacksidBetween(Long value1, Long value2) {
            addCriterion("packsid between", value1, value2, "packsid");
            return (Criteria) this;
        }

        public Criteria andPacksidNotBetween(Long value1, Long value2) {
            addCriterion("packsid not between", value1, value2, "packsid");
            return (Criteria) this;
        }

        public Criteria andPacksnoIsNull() {
            addCriterion("packsno is null");
            return (Criteria) this;
        }

        public Criteria andPacksnoIsNotNull() {
            addCriterion("packsno is not null");
            return (Criteria) this;
        }

        public Criteria andPacksnoEqualTo(String value) {
            addCriterion("packsno =", value, "packsno");
            return (Criteria) this;
        }

        public Criteria andPacksnoNotEqualTo(String value) {
            addCriterion("packsno <>", value, "packsno");
            return (Criteria) this;
        }

        public Criteria andPacksnoGreaterThan(String value) {
            addCriterion("packsno >", value, "packsno");
            return (Criteria) this;
        }

        public Criteria andPacksnoGreaterThanOrEqualTo(String value) {
            addCriterion("packsno >=", value, "packsno");
            return (Criteria) this;
        }

        public Criteria andPacksnoLessThan(String value) {
            addCriterion("packsno <", value, "packsno");
            return (Criteria) this;
        }

        public Criteria andPacksnoLessThanOrEqualTo(String value) {
            addCriterion("packsno <=", value, "packsno");
            return (Criteria) this;
        }

        public Criteria andPacksnoLike(String value) {
            addCriterion("packsno like", value, "packsno");
            return (Criteria) this;
        }

        public Criteria andPacksnoNotLike(String value) {
            addCriterion("packsno not like", value, "packsno");
            return (Criteria) this;
        }

        public Criteria andPacksnoIn(List<String> values) {
            addCriterion("packsno in", values, "packsno");
            return (Criteria) this;
        }

        public Criteria andPacksnoNotIn(List<String> values) {
            addCriterion("packsno not in", values, "packsno");
            return (Criteria) this;
        }

        public Criteria andPacksnoBetween(String value1, String value2) {
            addCriterion("packsno between", value1, value2, "packsno");
            return (Criteria) this;
        }

        public Criteria andPacksnoNotBetween(String value1, String value2) {
            addCriterion("packsno not between", value1, value2, "packsno");
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

        public Criteria andGettimeIsNull() {
            addCriterion("gettime is null");
            return (Criteria) this;
        }

        public Criteria andGettimeIsNotNull() {
            addCriterion("gettime is not null");
            return (Criteria) this;
        }

        public Criteria andGettimeEqualTo(Date value) {
            addCriterion("gettime =", value, "gettime");
            return (Criteria) this;
        }

        public Criteria andGettimeNotEqualTo(Date value) {
            addCriterion("gettime <>", value, "gettime");
            return (Criteria) this;
        }

        public Criteria andGettimeGreaterThan(Date value) {
            addCriterion("gettime >", value, "gettime");
            return (Criteria) this;
        }

        public Criteria andGettimeGreaterThanOrEqualTo(Date value) {
            addCriterion("gettime >=", value, "gettime");
            return (Criteria) this;
        }

        public Criteria andGettimeLessThan(Date value) {
            addCriterion("gettime <", value, "gettime");
            return (Criteria) this;
        }

        public Criteria andGettimeLessThanOrEqualTo(Date value) {
            addCriterion("gettime <=", value, "gettime");
            return (Criteria) this;
        }

        public Criteria andGettimeIn(List<Date> values) {
            addCriterion("gettime in", values, "gettime");
            return (Criteria) this;
        }

        public Criteria andGettimeNotIn(List<Date> values) {
            addCriterion("gettime not in", values, "gettime");
            return (Criteria) this;
        }

        public Criteria andGettimeBetween(Date value1, Date value2) {
            addCriterion("gettime between", value1, value2, "gettime");
            return (Criteria) this;
        }

        public Criteria andGettimeNotBetween(Date value1, Date value2) {
            addCriterion("gettime not between", value1, value2, "gettime");
            return (Criteria) this;
        }

        public Criteria andTicketlistIsNull() {
            addCriterion("ticketlist is null");
            return (Criteria) this;
        }

        public Criteria andTicketlistIsNotNull() {
            addCriterion("ticketlist is not null");
            return (Criteria) this;
        }

        public Criteria andTicketlistEqualTo(String value) {
            addCriterion("ticketlist =", value, "ticketlist");
            return (Criteria) this;
        }

        public Criteria andTicketlistNotEqualTo(String value) {
            addCriterion("ticketlist <>", value, "ticketlist");
            return (Criteria) this;
        }

        public Criteria andTicketlistGreaterThan(String value) {
            addCriterion("ticketlist >", value, "ticketlist");
            return (Criteria) this;
        }

        public Criteria andTicketlistGreaterThanOrEqualTo(String value) {
            addCriterion("ticketlist >=", value, "ticketlist");
            return (Criteria) this;
        }

        public Criteria andTicketlistLessThan(String value) {
            addCriterion("ticketlist <", value, "ticketlist");
            return (Criteria) this;
        }

        public Criteria andTicketlistLessThanOrEqualTo(String value) {
            addCriterion("ticketlist <=", value, "ticketlist");
            return (Criteria) this;
        }

        public Criteria andTicketlistLike(String value) {
            addCriterion("ticketlist like", value, "ticketlist");
            return (Criteria) this;
        }

        public Criteria andTicketlistNotLike(String value) {
            addCriterion("ticketlist not like", value, "ticketlist");
            return (Criteria) this;
        }

        public Criteria andTicketlistIn(List<String> values) {
            addCriterion("ticketlist in", values, "ticketlist");
            return (Criteria) this;
        }

        public Criteria andTicketlistNotIn(List<String> values) {
            addCriterion("ticketlist not in", values, "ticketlist");
            return (Criteria) this;
        }

        public Criteria andTicketlistBetween(String value1, String value2) {
            addCriterion("ticketlist between", value1, value2, "ticketlist");
            return (Criteria) this;
        }

        public Criteria andTicketlistNotBetween(String value1, String value2) {
            addCriterion("ticketlist not between", value1, value2, "ticketlist");
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