package project.jinshang.mod_admin.mod_display.bean;

import java.util.ArrayList;
import java.util.List;

public class DisplayExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DisplayExample() {
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

        public Criteria andPraentidIsNull() {
            addCriterion("praentid is null");
            return (Criteria) this;
        }

        public Criteria andPraentidIsNotNull() {
            addCriterion("praentid is not null");
            return (Criteria) this;
        }

        public Criteria andPraentidEqualTo(Long value) {
            addCriterion("praentid =", value, "praentid");
            return (Criteria) this;
        }

        public Criteria andPraentidNotEqualTo(Long value) {
            addCriterion("praentid <>", value, "praentid");
            return (Criteria) this;
        }

        public Criteria andPraentidGreaterThan(Long value) {
            addCriterion("praentid >", value, "praentid");
            return (Criteria) this;
        }

        public Criteria andPraentidGreaterThanOrEqualTo(Long value) {
            addCriterion("praentid >=", value, "praentid");
            return (Criteria) this;
        }

        public Criteria andPraentidLessThan(Long value) {
            addCriterion("praentid <", value, "praentid");
            return (Criteria) this;
        }

        public Criteria andPraentidLessThanOrEqualTo(Long value) {
            addCriterion("praentid <=", value, "praentid");
            return (Criteria) this;
        }

        public Criteria andPraentidIn(List<Long> values) {
            addCriterion("praentid in", values, "praentid");
            return (Criteria) this;
        }

        public Criteria andPraentidNotIn(List<Long> values) {
            addCriterion("praentid not in", values, "praentid");
            return (Criteria) this;
        }

        public Criteria andPraentidBetween(Long value1, Long value2) {
            addCriterion("praentid between", value1, value2, "praentid");
            return (Criteria) this;
        }

        public Criteria andPraentidNotBetween(Long value1, Long value2) {
            addCriterion("praentid not between", value1, value2, "praentid");
            return (Criteria) this;
        }

        public Criteria andDpclassIsNull() {
            addCriterion("dpclass is null");
            return (Criteria) this;
        }

        public Criteria andDpclassIsNotNull() {
            addCriterion("dpclass is not null");
            return (Criteria) this;
        }

        public Criteria andDpclassEqualTo(String value) {
            addCriterion("dpclass =", value, "dpclass");
            return (Criteria) this;
        }

        public Criteria andDpclassNotEqualTo(String value) {
            addCriterion("dpclass <>", value, "dpclass");
            return (Criteria) this;
        }

        public Criteria andDpclassGreaterThan(String value) {
            addCriterion("dpclass >", value, "dpclass");
            return (Criteria) this;
        }

        public Criteria andDpclassGreaterThanOrEqualTo(String value) {
            addCriterion("dpclass >=", value, "dpclass");
            return (Criteria) this;
        }

        public Criteria andDpclassLessThan(String value) {
            addCriterion("dpclass <", value, "dpclass");
            return (Criteria) this;
        }

        public Criteria andDpclassLessThanOrEqualTo(String value) {
            addCriterion("dpclass <=", value, "dpclass");
            return (Criteria) this;
        }

        public Criteria andDpclassLike(String value) {
            addCriterion("dpclass like", value, "dpclass");
            return (Criteria) this;
        }

        public Criteria andDpclassNotLike(String value) {
            addCriterion("dpclass not like", value, "dpclass");
            return (Criteria) this;
        }

        public Criteria andDpclassIn(List<String> values) {
            addCriterion("dpclass in", values, "dpclass");
            return (Criteria) this;
        }

        public Criteria andDpclassNotIn(List<String> values) {
            addCriterion("dpclass not in", values, "dpclass");
            return (Criteria) this;
        }

        public Criteria andDpclassBetween(String value1, String value2) {
            addCriterion("dpclass between", value1, value2, "dpclass");
            return (Criteria) this;
        }

        public Criteria andDpclassNotBetween(String value1, String value2) {
            addCriterion("dpclass not between", value1, value2, "dpclass");
            return (Criteria) this;
        }

        public Criteria andDpurlIsNull() {
            addCriterion("dpurl is null");
            return (Criteria) this;
        }

        public Criteria andDpurlIsNotNull() {
            addCriterion("dpurl is not null");
            return (Criteria) this;
        }

        public Criteria andDpurlEqualTo(String value) {
            addCriterion("dpurl =", value, "dpurl");
            return (Criteria) this;
        }

        public Criteria andDpurlNotEqualTo(String value) {
            addCriterion("dpurl <>", value, "dpurl");
            return (Criteria) this;
        }

        public Criteria andDpurlGreaterThan(String value) {
            addCriterion("dpurl >", value, "dpurl");
            return (Criteria) this;
        }

        public Criteria andDpurlGreaterThanOrEqualTo(String value) {
            addCriterion("dpurl >=", value, "dpurl");
            return (Criteria) this;
        }

        public Criteria andDpurlLessThan(String value) {
            addCriterion("dpurl <", value, "dpurl");
            return (Criteria) this;
        }

        public Criteria andDpurlLessThanOrEqualTo(String value) {
            addCriterion("dpurl <=", value, "dpurl");
            return (Criteria) this;
        }

        public Criteria andDpurlLike(String value) {
            addCriterion("dpurl like", value, "dpurl");
            return (Criteria) this;
        }

        public Criteria andDpurlNotLike(String value) {
            addCriterion("dpurl not like", value, "dpurl");
            return (Criteria) this;
        }

        public Criteria andDpurlIn(List<String> values) {
            addCriterion("dpurl in", values, "dpurl");
            return (Criteria) this;
        }

        public Criteria andDpurlNotIn(List<String> values) {
            addCriterion("dpurl not in", values, "dpurl");
            return (Criteria) this;
        }

        public Criteria andDpurlBetween(String value1, String value2) {
            addCriterion("dpurl between", value1, value2, "dpurl");
            return (Criteria) this;
        }

        public Criteria andDpurlNotBetween(String value1, String value2) {
            addCriterion("dpurl not between", value1, value2, "dpurl");
            return (Criteria) this;
        }

        public Criteria andDpsuperiorIsNull() {
            addCriterion("dpsuperior is null");
            return (Criteria) this;
        }

        public Criteria andDpsuperiorIsNotNull() {
            addCriterion("dpsuperior is not null");
            return (Criteria) this;
        }

        public Criteria andDpsuperiorEqualTo(String value) {
            addCriterion("dpsuperior =", value, "dpsuperior");
            return (Criteria) this;
        }

        public Criteria andDpsuperiorNotEqualTo(String value) {
            addCriterion("dpsuperior <>", value, "dpsuperior");
            return (Criteria) this;
        }

        public Criteria andDpsuperiorGreaterThan(String value) {
            addCriterion("dpsuperior >", value, "dpsuperior");
            return (Criteria) this;
        }

        public Criteria andDpsuperiorGreaterThanOrEqualTo(String value) {
            addCriterion("dpsuperior >=", value, "dpsuperior");
            return (Criteria) this;
        }

        public Criteria andDpsuperiorLessThan(String value) {
            addCriterion("dpsuperior <", value, "dpsuperior");
            return (Criteria) this;
        }

        public Criteria andDpsuperiorLessThanOrEqualTo(String value) {
            addCriterion("dpsuperior <=", value, "dpsuperior");
            return (Criteria) this;
        }

        public Criteria andDpsuperiorLike(String value) {
            addCriterion("dpsuperior like", value, "dpsuperior");
            return (Criteria) this;
        }

        public Criteria andDpsuperiorNotLike(String value) {
            addCriterion("dpsuperior not like", value, "dpsuperior");
            return (Criteria) this;
        }

        public Criteria andDpsuperiorIn(List<String> values) {
            addCriterion("dpsuperior in", values, "dpsuperior");
            return (Criteria) this;
        }

        public Criteria andDpsuperiorNotIn(List<String> values) {
            addCriterion("dpsuperior not in", values, "dpsuperior");
            return (Criteria) this;
        }

        public Criteria andDpsuperiorBetween(String value1, String value2) {
            addCriterion("dpsuperior between", value1, value2, "dpsuperior");
            return (Criteria) this;
        }

        public Criteria andDpsuperiorNotBetween(String value1, String value2) {
            addCriterion("dpsuperior not between", value1, value2, "dpsuperior");
            return (Criteria) this;
        }

        public Criteria andAdadvertIsNull() {
            addCriterion("adadvert is null");
            return (Criteria) this;
        }

        public Criteria andAdadvertIsNotNull() {
            addCriterion("adadvert is not null");
            return (Criteria) this;
        }

        public Criteria andAdadvertEqualTo(String value) {
            addCriterion("adadvert =", value, "adadvert");
            return (Criteria) this;
        }

        public Criteria andAdadvertNotEqualTo(String value) {
            addCriterion("adadvert <>", value, "adadvert");
            return (Criteria) this;
        }

        public Criteria andAdadvertGreaterThan(String value) {
            addCriterion("adadvert >", value, "adadvert");
            return (Criteria) this;
        }

        public Criteria andAdadvertGreaterThanOrEqualTo(String value) {
            addCriterion("adadvert >=", value, "adadvert");
            return (Criteria) this;
        }

        public Criteria andAdadvertLessThan(String value) {
            addCriterion("adadvert <", value, "adadvert");
            return (Criteria) this;
        }

        public Criteria andAdadvertLessThanOrEqualTo(String value) {
            addCriterion("adadvert <=", value, "adadvert");
            return (Criteria) this;
        }

        public Criteria andAdadvertLike(String value) {
            addCriterion("adadvert like", value, "adadvert");
            return (Criteria) this;
        }

        public Criteria andAdadvertNotLike(String value) {
            addCriterion("adadvert not like", value, "adadvert");
            return (Criteria) this;
        }

        public Criteria andAdadvertIn(List<String> values) {
            addCriterion("adadvert in", values, "adadvert");
            return (Criteria) this;
        }

        public Criteria andAdadvertNotIn(List<String> values) {
            addCriterion("adadvert not in", values, "adadvert");
            return (Criteria) this;
        }

        public Criteria andAdadvertBetween(String value1, String value2) {
            addCriterion("adadvert between", value1, value2, "adadvert");
            return (Criteria) this;
        }

        public Criteria andAdadvertNotBetween(String value1, String value2) {
            addCriterion("adadvert not between", value1, value2, "adadvert");
            return (Criteria) this;
        }

        public Criteria andIsshowIsNull() {
            addCriterion("isshow is null");
            return (Criteria) this;
        }

        public Criteria andIsshowIsNotNull() {
            addCriterion("isshow is not null");
            return (Criteria) this;
        }

        public Criteria andIsshowEqualTo(Integer value) {
            addCriterion("isshow =", value, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowNotEqualTo(Integer value) {
            addCriterion("isshow <>", value, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowGreaterThan(Integer value) {
            addCriterion("isshow >", value, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowGreaterThanOrEqualTo(Integer value) {
            addCriterion("isshow >=", value, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowLessThan(Integer value) {
            addCriterion("isshow <", value, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowLessThanOrEqualTo(Integer value) {
            addCriterion("isshow <=", value, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowIn(List<Integer> values) {
            addCriterion("isshow in", values, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowNotIn(List<Integer> values) {
            addCriterion("isshow not in", values, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowBetween(Integer value1, Integer value2) {
            addCriterion("isshow between", value1, value2, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowNotBetween(Integer value1, Integer value2) {
            addCriterion("isshow not between", value1, value2, "isshow");
            return (Criteria) this;
        }

        public Criteria andDpiconIsNull() {
            addCriterion("dpicon is null");
            return (Criteria) this;
        }

        public Criteria andDpiconIsNotNull() {
            addCriterion("dpicon is not null");
            return (Criteria) this;
        }

        public Criteria andDpiconEqualTo(String value) {
            addCriterion("dpicon =", value, "dpicon");
            return (Criteria) this;
        }

        public Criteria andDpiconNotEqualTo(String value) {
            addCriterion("dpicon <>", value, "dpicon");
            return (Criteria) this;
        }

        public Criteria andDpiconGreaterThan(String value) {
            addCriterion("dpicon >", value, "dpicon");
            return (Criteria) this;
        }

        public Criteria andDpiconGreaterThanOrEqualTo(String value) {
            addCriterion("dpicon >=", value, "dpicon");
            return (Criteria) this;
        }

        public Criteria andDpiconLessThan(String value) {
            addCriterion("dpicon <", value, "dpicon");
            return (Criteria) this;
        }

        public Criteria andDpiconLessThanOrEqualTo(String value) {
            addCriterion("dpicon <=", value, "dpicon");
            return (Criteria) this;
        }

        public Criteria andDpiconLike(String value) {
            addCriterion("dpicon like", value, "dpicon");
            return (Criteria) this;
        }

        public Criteria andDpiconNotLike(String value) {
            addCriterion("dpicon not like", value, "dpicon");
            return (Criteria) this;
        }

        public Criteria andDpiconIn(List<String> values) {
            addCriterion("dpicon in", values, "dpicon");
            return (Criteria) this;
        }

        public Criteria andDpiconNotIn(List<String> values) {
            addCriterion("dpicon not in", values, "dpicon");
            return (Criteria) this;
        }

        public Criteria andDpiconBetween(String value1, String value2) {
            addCriterion("dpicon between", value1, value2, "dpicon");
            return (Criteria) this;
        }

        public Criteria andDpiconNotBetween(String value1, String value2) {
            addCriterion("dpicon not between", value1, value2, "dpicon");
            return (Criteria) this;
        }

        public Criteria andDpsortIsNull() {
            addCriterion("dpsort is null");
            return (Criteria) this;
        }

        public Criteria andDpsortIsNotNull() {
            addCriterion("dpsort is not null");
            return (Criteria) this;
        }

        public Criteria andDpsortEqualTo(Integer value) {
            addCriterion("dpsort =", value, "dpsort");
            return (Criteria) this;
        }

        public Criteria andDpsortNotEqualTo(Integer value) {
            addCriterion("dpsort <>", value, "dpsort");
            return (Criteria) this;
        }

        public Criteria andDpsortGreaterThan(Integer value) {
            addCriterion("dpsort >", value, "dpsort");
            return (Criteria) this;
        }

        public Criteria andDpsortGreaterThanOrEqualTo(Integer value) {
            addCriterion("dpsort >=", value, "dpsort");
            return (Criteria) this;
        }

        public Criteria andDpsortLessThan(Integer value) {
            addCriterion("dpsort <", value, "dpsort");
            return (Criteria) this;
        }

        public Criteria andDpsortLessThanOrEqualTo(Integer value) {
            addCriterion("dpsort <=", value, "dpsort");
            return (Criteria) this;
        }

        public Criteria andDpsortIn(List<Integer> values) {
            addCriterion("dpsort in", values, "dpsort");
            return (Criteria) this;
        }

        public Criteria andDpsortNotIn(List<Integer> values) {
            addCriterion("dpsort not in", values, "dpsort");
            return (Criteria) this;
        }

        public Criteria andDpsortBetween(Integer value1, Integer value2) {
            addCriterion("dpsort between", value1, value2, "dpsort");
            return (Criteria) this;
        }

        public Criteria andDpsortNotBetween(Integer value1, Integer value2) {
            addCriterion("dpsort not between", value1, value2, "dpsort");
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