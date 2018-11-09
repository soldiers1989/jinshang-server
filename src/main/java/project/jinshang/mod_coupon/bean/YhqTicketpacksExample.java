package project.jinshang.mod_coupon.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class YhqTicketpacksExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public YhqTicketpacksExample() {
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

        public Criteria andTagIsNull() {
            addCriterion("tag is null");
            return (Criteria) this;
        }

        public Criteria andTagIsNotNull() {
            addCriterion("tag is not null");
            return (Criteria) this;
        }

        public Criteria andTagEqualTo(String value) {
            addCriterion("tag =", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotEqualTo(String value) {
            addCriterion("tag <>", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagGreaterThan(String value) {
            addCriterion("tag >", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagGreaterThanOrEqualTo(String value) {
            addCriterion("tag >=", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLessThan(String value) {
            addCriterion("tag <", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLessThanOrEqualTo(String value) {
            addCriterion("tag <=", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLike(String value) {
            addCriterion("tag like", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotLike(String value) {
            addCriterion("tag not like", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagIn(List<String> values) {
            addCriterion("tag in", values, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotIn(List<String> values) {
            addCriterion("tag not in", values, "tag");
            return (Criteria) this;
        }

        public Criteria andTagBetween(String value1, String value2) {
            addCriterion("tag between", value1, value2, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotBetween(String value1, String value2) {
            addCriterion("tag not between", value1, value2, "tag");
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

        public Criteria andPackscountIsNull() {
            addCriterion("packscount is null");
            return (Criteria) this;
        }

        public Criteria andPackscountIsNotNull() {
            addCriterion("packscount is not null");
            return (Criteria) this;
        }

        public Criteria andPackscountEqualTo(Long value) {
            addCriterion("packscount =", value, "packscount");
            return (Criteria) this;
        }

        public Criteria andPackscountNotEqualTo(Long value) {
            addCriterion("packscount <>", value, "packscount");
            return (Criteria) this;
        }

        public Criteria andPackscountGreaterThan(Long value) {
            addCriterion("packscount >", value, "packscount");
            return (Criteria) this;
        }

        public Criteria andPackscountGreaterThanOrEqualTo(Long value) {
            addCriterion("packscount >=", value, "packscount");
            return (Criteria) this;
        }

        public Criteria andPackscountLessThan(Long value) {
            addCriterion("packscount <", value, "packscount");
            return (Criteria) this;
        }

        public Criteria andPackscountLessThanOrEqualTo(Long value) {
            addCriterion("packscount <=", value, "packscount");
            return (Criteria) this;
        }

        public Criteria andPackscountIn(List<Long> values) {
            addCriterion("packscount in", values, "packscount");
            return (Criteria) this;
        }

        public Criteria andPackscountNotIn(List<Long> values) {
            addCriterion("packscount not in", values, "packscount");
            return (Criteria) this;
        }

        public Criteria andPackscountBetween(Long value1, Long value2) {
            addCriterion("packscount between", value1, value2, "packscount");
            return (Criteria) this;
        }

        public Criteria andPackscountNotBetween(Long value1, Long value2) {
            addCriterion("packscount not between", value1, value2, "packscount");
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