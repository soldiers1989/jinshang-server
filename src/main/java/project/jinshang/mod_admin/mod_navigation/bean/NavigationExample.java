package project.jinshang.mod_admin.mod_navigation.bean;

import java.util.ArrayList;
import java.util.List;

public class NavigationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NavigationExample() {
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

        public Criteria andNatypeIsNull() {
            addCriterion("natype is null");
            return (Criteria) this;
        }

        public Criteria andNatypeIsNotNull() {
            addCriterion("natype is not null");
            return (Criteria) this;
        }

        public Criteria andNatypeEqualTo(String value) {
            addCriterion("natype =", value, "natype");
            return (Criteria) this;
        }

        public Criteria andNatypeNotEqualTo(String value) {
            addCriterion("natype <>", value, "natype");
            return (Criteria) this;
        }

        public Criteria andNatypeGreaterThan(String value) {
            addCriterion("natype >", value, "natype");
            return (Criteria) this;
        }

        public Criteria andNatypeGreaterThanOrEqualTo(String value) {
            addCriterion("natype >=", value, "natype");
            return (Criteria) this;
        }

        public Criteria andNatypeLessThan(String value) {
            addCriterion("natype <", value, "natype");
            return (Criteria) this;
        }

        public Criteria andNatypeLessThanOrEqualTo(String value) {
            addCriterion("natype <=", value, "natype");
            return (Criteria) this;
        }

        public Criteria andNatypeLike(String value) {
            addCriterion("natype like", value, "natype");
            return (Criteria) this;
        }

        public Criteria andNatypeNotLike(String value) {
            addCriterion("natype not like", value, "natype");
            return (Criteria) this;
        }

        public Criteria andNatypeIn(List<String> values) {
            addCriterion("natype in", values, "natype");
            return (Criteria) this;
        }

        public Criteria andNatypeNotIn(List<String> values) {
            addCriterion("natype not in", values, "natype");
            return (Criteria) this;
        }

        public Criteria andNatypeBetween(String value1, String value2) {
            addCriterion("natype between", value1, value2, "natype");
            return (Criteria) this;
        }

        public Criteria andNatypeNotBetween(String value1, String value2) {
            addCriterion("natype not between", value1, value2, "natype");
            return (Criteria) this;
        }

        public Criteria andNatitleIsNull() {
            addCriterion("natitle is null");
            return (Criteria) this;
        }

        public Criteria andNatitleIsNotNull() {
            addCriterion("natitle is not null");
            return (Criteria) this;
        }

        public Criteria andNatitleEqualTo(String value) {
            addCriterion("natitle =", value, "natitle");
            return (Criteria) this;
        }

        public Criteria andNatitleNotEqualTo(String value) {
            addCriterion("natitle <>", value, "natitle");
            return (Criteria) this;
        }

        public Criteria andNatitleGreaterThan(String value) {
            addCriterion("natitle >", value, "natitle");
            return (Criteria) this;
        }

        public Criteria andNatitleGreaterThanOrEqualTo(String value) {
            addCriterion("natitle >=", value, "natitle");
            return (Criteria) this;
        }

        public Criteria andNatitleLessThan(String value) {
            addCriterion("natitle <", value, "natitle");
            return (Criteria) this;
        }

        public Criteria andNatitleLessThanOrEqualTo(String value) {
            addCriterion("natitle <=", value, "natitle");
            return (Criteria) this;
        }

        public Criteria andNatitleLike(String value) {
            addCriterion("natitle like", value, "natitle");
            return (Criteria) this;
        }

        public Criteria andNatitleNotLike(String value) {
            addCriterion("natitle not like", value, "natitle");
            return (Criteria) this;
        }

        public Criteria andNatitleIn(List<String> values) {
            addCriterion("natitle in", values, "natitle");
            return (Criteria) this;
        }

        public Criteria andNatitleNotIn(List<String> values) {
            addCriterion("natitle not in", values, "natitle");
            return (Criteria) this;
        }

        public Criteria andNatitleBetween(String value1, String value2) {
            addCriterion("natitle between", value1, value2, "natitle");
            return (Criteria) this;
        }

        public Criteria andNatitleNotBetween(String value1, String value2) {
            addCriterion("natitle not between", value1, value2, "natitle");
            return (Criteria) this;
        }

        public Criteria andNalinkIsNull() {
            addCriterion("nalink is null");
            return (Criteria) this;
        }

        public Criteria andNalinkIsNotNull() {
            addCriterion("nalink is not null");
            return (Criteria) this;
        }

        public Criteria andNalinkEqualTo(String value) {
            addCriterion("nalink =", value, "nalink");
            return (Criteria) this;
        }

        public Criteria andNalinkNotEqualTo(String value) {
            addCriterion("nalink <>", value, "nalink");
            return (Criteria) this;
        }

        public Criteria andNalinkGreaterThan(String value) {
            addCriterion("nalink >", value, "nalink");
            return (Criteria) this;
        }

        public Criteria andNalinkGreaterThanOrEqualTo(String value) {
            addCriterion("nalink >=", value, "nalink");
            return (Criteria) this;
        }

        public Criteria andNalinkLessThan(String value) {
            addCriterion("nalink <", value, "nalink");
            return (Criteria) this;
        }

        public Criteria andNalinkLessThanOrEqualTo(String value) {
            addCriterion("nalink <=", value, "nalink");
            return (Criteria) this;
        }

        public Criteria andNalinkLike(String value) {
            addCriterion("nalink like", value, "nalink");
            return (Criteria) this;
        }

        public Criteria andNalinkNotLike(String value) {
            addCriterion("nalink not like", value, "nalink");
            return (Criteria) this;
        }

        public Criteria andNalinkIn(List<String> values) {
            addCriterion("nalink in", values, "nalink");
            return (Criteria) this;
        }

        public Criteria andNalinkNotIn(List<String> values) {
            addCriterion("nalink not in", values, "nalink");
            return (Criteria) this;
        }

        public Criteria andNalinkBetween(String value1, String value2) {
            addCriterion("nalink between", value1, value2, "nalink");
            return (Criteria) this;
        }

        public Criteria andNalinkNotBetween(String value1, String value2) {
            addCriterion("nalink not between", value1, value2, "nalink");
            return (Criteria) this;
        }

        public Criteria andNalocationIsNull() {
            addCriterion("nalocation is null");
            return (Criteria) this;
        }

        public Criteria andNalocationIsNotNull() {
            addCriterion("nalocation is not null");
            return (Criteria) this;
        }

        public Criteria andNalocationEqualTo(String value) {
            addCriterion("nalocation =", value, "nalocation");
            return (Criteria) this;
        }

        public Criteria andNalocationNotEqualTo(String value) {
            addCriterion("nalocation <>", value, "nalocation");
            return (Criteria) this;
        }

        public Criteria andNalocationGreaterThan(String value) {
            addCriterion("nalocation >", value, "nalocation");
            return (Criteria) this;
        }

        public Criteria andNalocationGreaterThanOrEqualTo(String value) {
            addCriterion("nalocation >=", value, "nalocation");
            return (Criteria) this;
        }

        public Criteria andNalocationLessThan(String value) {
            addCriterion("nalocation <", value, "nalocation");
            return (Criteria) this;
        }

        public Criteria andNalocationLessThanOrEqualTo(String value) {
            addCriterion("nalocation <=", value, "nalocation");
            return (Criteria) this;
        }

        public Criteria andNalocationLike(String value) {
            addCriterion("nalocation like", value, "nalocation");
            return (Criteria) this;
        }

        public Criteria andNalocationNotLike(String value) {
            addCriterion("nalocation not like", value, "nalocation");
            return (Criteria) this;
        }

        public Criteria andNalocationIn(List<String> values) {
            addCriterion("nalocation in", values, "nalocation");
            return (Criteria) this;
        }

        public Criteria andNalocationNotIn(List<String> values) {
            addCriterion("nalocation not in", values, "nalocation");
            return (Criteria) this;
        }

        public Criteria andNalocationBetween(String value1, String value2) {
            addCriterion("nalocation between", value1, value2, "nalocation");
            return (Criteria) this;
        }

        public Criteria andNalocationNotBetween(String value1, String value2) {
            addCriterion("nalocation not between", value1, value2, "nalocation");
            return (Criteria) this;
        }

        public Criteria andNaterraceIsNull() {
            addCriterion("naterrace is null");
            return (Criteria) this;
        }

        public Criteria andNaterraceIsNotNull() {
            addCriterion("naterrace is not null");
            return (Criteria) this;
        }

        public Criteria andNaterraceEqualTo(String value) {
            addCriterion("naterrace =", value, "naterrace");
            return (Criteria) this;
        }

        public Criteria andNaterraceNotEqualTo(String value) {
            addCriterion("naterrace <>", value, "naterrace");
            return (Criteria) this;
        }

        public Criteria andNaterraceGreaterThan(String value) {
            addCriterion("naterrace >", value, "naterrace");
            return (Criteria) this;
        }

        public Criteria andNaterraceGreaterThanOrEqualTo(String value) {
            addCriterion("naterrace >=", value, "naterrace");
            return (Criteria) this;
        }

        public Criteria andNaterraceLessThan(String value) {
            addCriterion("naterrace <", value, "naterrace");
            return (Criteria) this;
        }

        public Criteria andNaterraceLessThanOrEqualTo(String value) {
            addCriterion("naterrace <=", value, "naterrace");
            return (Criteria) this;
        }

        public Criteria andNaterraceLike(String value) {
            addCriterion("naterrace like", value, "naterrace");
            return (Criteria) this;
        }

        public Criteria andNaterraceNotLike(String value) {
            addCriterion("naterrace not like", value, "naterrace");
            return (Criteria) this;
        }

        public Criteria andNaterraceIn(List<String> values) {
            addCriterion("naterrace in", values, "naterrace");
            return (Criteria) this;
        }

        public Criteria andNaterraceNotIn(List<String> values) {
            addCriterion("naterrace not in", values, "naterrace");
            return (Criteria) this;
        }

        public Criteria andNaterraceBetween(String value1, String value2) {
            addCriterion("naterrace between", value1, value2, "naterrace");
            return (Criteria) this;
        }

        public Criteria andNaterraceNotBetween(String value1, String value2) {
            addCriterion("naterrace not between", value1, value2, "naterrace");
            return (Criteria) this;
        }

        public Criteria andNaiconIsNull() {
            addCriterion("naicon is null");
            return (Criteria) this;
        }

        public Criteria andNaiconIsNotNull() {
            addCriterion("naicon is not null");
            return (Criteria) this;
        }

        public Criteria andNaiconEqualTo(String value) {
            addCriterion("naicon =", value, "naicon");
            return (Criteria) this;
        }

        public Criteria andNaiconNotEqualTo(String value) {
            addCriterion("naicon <>", value, "naicon");
            return (Criteria) this;
        }

        public Criteria andNaiconGreaterThan(String value) {
            addCriterion("naicon >", value, "naicon");
            return (Criteria) this;
        }

        public Criteria andNaiconGreaterThanOrEqualTo(String value) {
            addCriterion("naicon >=", value, "naicon");
            return (Criteria) this;
        }

        public Criteria andNaiconLessThan(String value) {
            addCriterion("naicon <", value, "naicon");
            return (Criteria) this;
        }

        public Criteria andNaiconLessThanOrEqualTo(String value) {
            addCriterion("naicon <=", value, "naicon");
            return (Criteria) this;
        }

        public Criteria andNaiconLike(String value) {
            addCriterion("naicon like", value, "naicon");
            return (Criteria) this;
        }

        public Criteria andNaiconNotLike(String value) {
            addCriterion("naicon not like", value, "naicon");
            return (Criteria) this;
        }

        public Criteria andNaiconIn(List<String> values) {
            addCriterion("naicon in", values, "naicon");
            return (Criteria) this;
        }

        public Criteria andNaiconNotIn(List<String> values) {
            addCriterion("naicon not in", values, "naicon");
            return (Criteria) this;
        }

        public Criteria andNaiconBetween(String value1, String value2) {
            addCriterion("naicon between", value1, value2, "naicon");
            return (Criteria) this;
        }

        public Criteria andNaiconNotBetween(String value1, String value2) {
            addCriterion("naicon not between", value1, value2, "naicon");
            return (Criteria) this;
        }

        public Criteria andIsnewIsNull() {
            addCriterion("isnew is null");
            return (Criteria) this;
        }

        public Criteria andIsnewIsNotNull() {
            addCriterion("isnew is not null");
            return (Criteria) this;
        }

        public Criteria andIsnewEqualTo(Integer value) {
            addCriterion("isnew =", value, "isnew");
            return (Criteria) this;
        }

        public Criteria andIsnewNotEqualTo(Integer value) {
            addCriterion("isnew <>", value, "isnew");
            return (Criteria) this;
        }

        public Criteria andIsnewGreaterThan(Integer value) {
            addCriterion("isnew >", value, "isnew");
            return (Criteria) this;
        }

        public Criteria andIsnewGreaterThanOrEqualTo(Integer value) {
            addCriterion("isnew >=", value, "isnew");
            return (Criteria) this;
        }

        public Criteria andIsnewLessThan(Integer value) {
            addCriterion("isnew <", value, "isnew");
            return (Criteria) this;
        }

        public Criteria andIsnewLessThanOrEqualTo(Integer value) {
            addCriterion("isnew <=", value, "isnew");
            return (Criteria) this;
        }

        public Criteria andIsnewIn(List<Integer> values) {
            addCriterion("isnew in", values, "isnew");
            return (Criteria) this;
        }

        public Criteria andIsnewNotIn(List<Integer> values) {
            addCriterion("isnew not in", values, "isnew");
            return (Criteria) this;
        }

        public Criteria andIsnewBetween(Integer value1, Integer value2) {
            addCriterion("isnew between", value1, value2, "isnew");
            return (Criteria) this;
        }

        public Criteria andIsnewNotBetween(Integer value1, Integer value2) {
            addCriterion("isnew not between", value1, value2, "isnew");
            return (Criteria) this;
        }

        public Criteria andNasortIsNull() {
            addCriterion("nasort is null");
            return (Criteria) this;
        }

        public Criteria andNasortIsNotNull() {
            addCriterion("nasort is not null");
            return (Criteria) this;
        }

        public Criteria andNasortEqualTo(Integer value) {
            addCriterion("nasort =", value, "nasort");
            return (Criteria) this;
        }

        public Criteria andNasortNotEqualTo(Integer value) {
            addCriterion("nasort <>", value, "nasort");
            return (Criteria) this;
        }

        public Criteria andNasortGreaterThan(Integer value) {
            addCriterion("nasort >", value, "nasort");
            return (Criteria) this;
        }

        public Criteria andNasortGreaterThanOrEqualTo(Integer value) {
            addCriterion("nasort >=", value, "nasort");
            return (Criteria) this;
        }

        public Criteria andNasortLessThan(Integer value) {
            addCriterion("nasort <", value, "nasort");
            return (Criteria) this;
        }

        public Criteria andNasortLessThanOrEqualTo(Integer value) {
            addCriterion("nasort <=", value, "nasort");
            return (Criteria) this;
        }

        public Criteria andNasortIn(List<Integer> values) {
            addCriterion("nasort in", values, "nasort");
            return (Criteria) this;
        }

        public Criteria andNasortNotIn(List<Integer> values) {
            addCriterion("nasort not in", values, "nasort");
            return (Criteria) this;
        }

        public Criteria andNasortBetween(Integer value1, Integer value2) {
            addCriterion("nasort between", value1, value2, "nasort");
            return (Criteria) this;
        }

        public Criteria andNasortNotBetween(Integer value1, Integer value2) {
            addCriterion("nasort not between", value1, value2, "nasort");
            return (Criteria) this;
        }

        public Criteria andIsShowIsNull() {
            addCriterion("is_show is null");
            return (Criteria) this;
        }

        public Criteria andIsShowIsNotNull() {
            addCriterion("is_show is not null");
            return (Criteria) this;
        }

        public Criteria andIsShowEqualTo(Integer value) {
            addCriterion("is_show =", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowNotEqualTo(Integer value) {
            addCriterion("is_show <>", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowGreaterThan(Integer value) {
            addCriterion("is_show >", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_show >=", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowLessThan(Integer value) {
            addCriterion("is_show <", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowLessThanOrEqualTo(Integer value) {
            addCriterion("is_show <=", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowIn(List<Integer> values) {
            addCriterion("is_show in", values, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowNotIn(List<Integer> values) {
            addCriterion("is_show not in", values, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowBetween(Integer value1, Integer value2) {
            addCriterion("is_show between", value1, value2, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowNotBetween(Integer value1, Integer value2) {
            addCriterion("is_show not between", value1, value2, "isShow");
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