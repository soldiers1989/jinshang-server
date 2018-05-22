package project.jinshang.mod_admin.mod_paymode.bean;

import java.util.ArrayList;
import java.util.List;

public class PayModeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PayModeExample() {
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

        public Criteria andPaytypeIsNull() {
            addCriterion("paytype is null");
            return (Criteria) this;
        }

        public Criteria andPaytypeIsNotNull() {
            addCriterion("paytype is not null");
            return (Criteria) this;
        }

        public Criteria andPaytypeEqualTo(String value) {
            addCriterion("paytype =", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeNotEqualTo(String value) {
            addCriterion("paytype <>", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeGreaterThan(String value) {
            addCriterion("paytype >", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeGreaterThanOrEqualTo(String value) {
            addCriterion("paytype >=", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeLessThan(String value) {
            addCriterion("paytype <", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeLessThanOrEqualTo(String value) {
            addCriterion("paytype <=", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeIn(List<String> values) {
            addCriterion("paytype in", values, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeNotIn(List<String> values) {
            addCriterion("paytype not in", values, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeBetween(String value1, String value2) {
            addCriterion("paytype between", value1, value2, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeNotBetween(String value1, String value2) {
            addCriterion("paytype not between", value1, value2, "paytype");
            return (Criteria) this;
        }

        public Criteria andIdentityidIsNull() {
            addCriterion("identityid is null");
            return (Criteria) this;
        }

        public Criteria andIdentityidIsNotNull() {
            addCriterion("identityid is not null");
            return (Criteria) this;
        }

        public Criteria andIdentityidEqualTo(String value) {
            addCriterion("identityid =", value, "identityid");
            return (Criteria) this;
        }

        public Criteria andIdentityidNotEqualTo(String value) {
            addCriterion("identityid <>", value, "identityid");
            return (Criteria) this;
        }

        public Criteria andIdentityidGreaterThan(String value) {
            addCriterion("identityid >", value, "identityid");
            return (Criteria) this;
        }

        public Criteria andIdentityidGreaterThanOrEqualTo(String value) {
            addCriterion("identityid >=", value, "identityid");
            return (Criteria) this;
        }

        public Criteria andIdentityidLessThan(String value) {
            addCriterion("identityid <", value, "identityid");
            return (Criteria) this;
        }

        public Criteria andIdentityidLessThanOrEqualTo(String value) {
            addCriterion("identityid <=", value, "identityid");
            return (Criteria) this;
        }

        public Criteria andIdentityidLike(String value) {
            addCriterion("identityid like", value, "identityid");
            return (Criteria) this;
        }

        public Criteria andIdentityidNotLike(String value) {
            addCriterion("identityid not like", value, "identityid");
            return (Criteria) this;
        }

        public Criteria andIdentityidIn(List<String> values) {
            addCriterion("identityid in", values, "identityid");
            return (Criteria) this;
        }

        public Criteria andIdentityidNotIn(List<String> values) {
            addCriterion("identityid not in", values, "identityid");
            return (Criteria) this;
        }

        public Criteria andIdentityidBetween(String value1, String value2) {
            addCriterion("identityid between", value1, value2, "identityid");
            return (Criteria) this;
        }

        public Criteria andIdentityidNotBetween(String value1, String value2) {
            addCriterion("identityid not between", value1, value2, "identityid");
            return (Criteria) this;
        }

        public Criteria andSaftycodeIsNull() {
            addCriterion("saftycode is null");
            return (Criteria) this;
        }

        public Criteria andSaftycodeIsNotNull() {
            addCriterion("saftycode is not null");
            return (Criteria) this;
        }

        public Criteria andSaftycodeEqualTo(String value) {
            addCriterion("saftycode =", value, "saftycode");
            return (Criteria) this;
        }

        public Criteria andSaftycodeNotEqualTo(String value) {
            addCriterion("saftycode <>", value, "saftycode");
            return (Criteria) this;
        }

        public Criteria andSaftycodeGreaterThan(String value) {
            addCriterion("saftycode >", value, "saftycode");
            return (Criteria) this;
        }

        public Criteria andSaftycodeGreaterThanOrEqualTo(String value) {
            addCriterion("saftycode >=", value, "saftycode");
            return (Criteria) this;
        }

        public Criteria andSaftycodeLessThan(String value) {
            addCriterion("saftycode <", value, "saftycode");
            return (Criteria) this;
        }

        public Criteria andSaftycodeLessThanOrEqualTo(String value) {
            addCriterion("saftycode <=", value, "saftycode");
            return (Criteria) this;
        }

        public Criteria andSaftycodeLike(String value) {
            addCriterion("saftycode like", value, "saftycode");
            return (Criteria) this;
        }

        public Criteria andSaftycodeNotLike(String value) {
            addCriterion("saftycode not like", value, "saftycode");
            return (Criteria) this;
        }

        public Criteria andSaftycodeIn(List<String> values) {
            addCriterion("saftycode in", values, "saftycode");
            return (Criteria) this;
        }

        public Criteria andSaftycodeNotIn(List<String> values) {
            addCriterion("saftycode not in", values, "saftycode");
            return (Criteria) this;
        }

        public Criteria andSaftycodeBetween(String value1, String value2) {
            addCriterion("saftycode between", value1, value2, "saftycode");
            return (Criteria) this;
        }

        public Criteria andSaftycodeNotBetween(String value1, String value2) {
            addCriterion("saftycode not between", value1, value2, "saftycode");
            return (Criteria) this;
        }

        public Criteria andAlipayaccountIsNull() {
            addCriterion("alipayaccount is null");
            return (Criteria) this;
        }

        public Criteria andAlipayaccountIsNotNull() {
            addCriterion("alipayaccount is not null");
            return (Criteria) this;
        }

        public Criteria andAlipayaccountEqualTo(String value) {
            addCriterion("alipayaccount =", value, "alipayaccount");
            return (Criteria) this;
        }

        public Criteria andAlipayaccountNotEqualTo(String value) {
            addCriterion("alipayaccount <>", value, "alipayaccount");
            return (Criteria) this;
        }

        public Criteria andAlipayaccountGreaterThan(String value) {
            addCriterion("alipayaccount >", value, "alipayaccount");
            return (Criteria) this;
        }

        public Criteria andAlipayaccountGreaterThanOrEqualTo(String value) {
            addCriterion("alipayaccount >=", value, "alipayaccount");
            return (Criteria) this;
        }

        public Criteria andAlipayaccountLessThan(String value) {
            addCriterion("alipayaccount <", value, "alipayaccount");
            return (Criteria) this;
        }

        public Criteria andAlipayaccountLessThanOrEqualTo(String value) {
            addCriterion("alipayaccount <=", value, "alipayaccount");
            return (Criteria) this;
        }

        public Criteria andAlipayaccountLike(String value) {
            addCriterion("alipayaccount like", value, "alipayaccount");
            return (Criteria) this;
        }

        public Criteria andAlipayaccountNotLike(String value) {
            addCriterion("alipayaccount not like", value, "alipayaccount");
            return (Criteria) this;
        }

        public Criteria andAlipayaccountIn(List<String> values) {
            addCriterion("alipayaccount in", values, "alipayaccount");
            return (Criteria) this;
        }

        public Criteria andAlipayaccountNotIn(List<String> values) {
            addCriterion("alipayaccount not in", values, "alipayaccount");
            return (Criteria) this;
        }

        public Criteria andAlipayaccountBetween(String value1, String value2) {
            addCriterion("alipayaccount between", value1, value2, "alipayaccount");
            return (Criteria) this;
        }

        public Criteria andAlipayaccountNotBetween(String value1, String value2) {
            addCriterion("alipayaccount not between", value1, value2, "alipayaccount");
            return (Criteria) this;
        }

        public Criteria andSaleralipayaccountIsNull() {
            addCriterion("saleralipayaccount is null");
            return (Criteria) this;
        }

        public Criteria andSaleralipayaccountIsNotNull() {
            addCriterion("saleralipayaccount is not null");
            return (Criteria) this;
        }

        public Criteria andSaleralipayaccountEqualTo(String value) {
            addCriterion("saleralipayaccount =", value, "saleralipayaccount");
            return (Criteria) this;
        }

        public Criteria andSaleralipayaccountNotEqualTo(String value) {
            addCriterion("saleralipayaccount <>", value, "saleralipayaccount");
            return (Criteria) this;
        }

        public Criteria andSaleralipayaccountGreaterThan(String value) {
            addCriterion("saleralipayaccount >", value, "saleralipayaccount");
            return (Criteria) this;
        }

        public Criteria andSaleralipayaccountGreaterThanOrEqualTo(String value) {
            addCriterion("saleralipayaccount >=", value, "saleralipayaccount");
            return (Criteria) this;
        }

        public Criteria andSaleralipayaccountLessThan(String value) {
            addCriterion("saleralipayaccount <", value, "saleralipayaccount");
            return (Criteria) this;
        }

        public Criteria andSaleralipayaccountLessThanOrEqualTo(String value) {
            addCriterion("saleralipayaccount <=", value, "saleralipayaccount");
            return (Criteria) this;
        }

        public Criteria andSaleralipayaccountLike(String value) {
            addCriterion("saleralipayaccount like", value, "saleralipayaccount");
            return (Criteria) this;
        }

        public Criteria andSaleralipayaccountNotLike(String value) {
            addCriterion("saleralipayaccount not like", value, "saleralipayaccount");
            return (Criteria) this;
        }

        public Criteria andSaleralipayaccountIn(List<String> values) {
            addCriterion("saleralipayaccount in", values, "saleralipayaccount");
            return (Criteria) this;
        }

        public Criteria andSaleralipayaccountNotIn(List<String> values) {
            addCriterion("saleralipayaccount not in", values, "saleralipayaccount");
            return (Criteria) this;
        }

        public Criteria andSaleralipayaccountBetween(String value1, String value2) {
            addCriterion("saleralipayaccount between", value1, value2, "saleralipayaccount");
            return (Criteria) this;
        }

        public Criteria andSaleralipayaccountNotBetween(String value1, String value2) {
            addCriterion("saleralipayaccount not between", value1, value2, "saleralipayaccount");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyIsNull() {
            addCriterion("privatekey is null");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyIsNotNull() {
            addCriterion("privatekey is not null");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyEqualTo(String value) {
            addCriterion("privatekey =", value, "privatekey");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyNotEqualTo(String value) {
            addCriterion("privatekey <>", value, "privatekey");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyGreaterThan(String value) {
            addCriterion("privatekey >", value, "privatekey");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyGreaterThanOrEqualTo(String value) {
            addCriterion("privatekey >=", value, "privatekey");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyLessThan(String value) {
            addCriterion("privatekey <", value, "privatekey");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyLessThanOrEqualTo(String value) {
            addCriterion("privatekey <=", value, "privatekey");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyLike(String value) {
            addCriterion("privatekey like", value, "privatekey");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyNotLike(String value) {
            addCriterion("privatekey not like", value, "privatekey");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyIn(List<String> values) {
            addCriterion("privatekey in", values, "privatekey");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyNotIn(List<String> values) {
            addCriterion("privatekey not in", values, "privatekey");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyBetween(String value1, String value2) {
            addCriterion("privatekey between", value1, value2, "privatekey");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyNotBetween(String value1, String value2) {
            addCriterion("privatekey not between", value1, value2, "privatekey");
            return (Criteria) this;
        }

        public Criteria andPublickeyIsNull() {
            addCriterion("publickey is null");
            return (Criteria) this;
        }

        public Criteria andPublickeyIsNotNull() {
            addCriterion("publickey is not null");
            return (Criteria) this;
        }

        public Criteria andPublickeyEqualTo(String value) {
            addCriterion("publickey =", value, "publickey");
            return (Criteria) this;
        }

        public Criteria andPublickeyNotEqualTo(String value) {
            addCriterion("publickey <>", value, "publickey");
            return (Criteria) this;
        }

        public Criteria andPublickeyGreaterThan(String value) {
            addCriterion("publickey >", value, "publickey");
            return (Criteria) this;
        }

        public Criteria andPublickeyGreaterThanOrEqualTo(String value) {
            addCriterion("publickey >=", value, "publickey");
            return (Criteria) this;
        }

        public Criteria andPublickeyLessThan(String value) {
            addCriterion("publickey <", value, "publickey");
            return (Criteria) this;
        }

        public Criteria andPublickeyLessThanOrEqualTo(String value) {
            addCriterion("publickey <=", value, "publickey");
            return (Criteria) this;
        }

        public Criteria andPublickeyLike(String value) {
            addCriterion("publickey like", value, "publickey");
            return (Criteria) this;
        }

        public Criteria andPublickeyNotLike(String value) {
            addCriterion("publickey not like", value, "publickey");
            return (Criteria) this;
        }

        public Criteria andPublickeyIn(List<String> values) {
            addCriterion("publickey in", values, "publickey");
            return (Criteria) this;
        }

        public Criteria andPublickeyNotIn(List<String> values) {
            addCriterion("publickey not in", values, "publickey");
            return (Criteria) this;
        }

        public Criteria andPublickeyBetween(String value1, String value2) {
            addCriterion("publickey between", value1, value2, "publickey");
            return (Criteria) this;
        }

        public Criteria andPublickeyNotBetween(String value1, String value2) {
            addCriterion("publickey not between", value1, value2, "publickey");
            return (Criteria) this;
        }

        public Criteria andWeixinappidIsNull() {
            addCriterion("weixinappid is null");
            return (Criteria) this;
        }

        public Criteria andWeixinappidIsNotNull() {
            addCriterion("weixinappid is not null");
            return (Criteria) this;
        }

        public Criteria andWeixinappidEqualTo(String value) {
            addCriterion("weixinappid =", value, "weixinappid");
            return (Criteria) this;
        }

        public Criteria andWeixinappidNotEqualTo(String value) {
            addCriterion("weixinappid <>", value, "weixinappid");
            return (Criteria) this;
        }

        public Criteria andWeixinappidGreaterThan(String value) {
            addCriterion("weixinappid >", value, "weixinappid");
            return (Criteria) this;
        }

        public Criteria andWeixinappidGreaterThanOrEqualTo(String value) {
            addCriterion("weixinappid >=", value, "weixinappid");
            return (Criteria) this;
        }

        public Criteria andWeixinappidLessThan(String value) {
            addCriterion("weixinappid <", value, "weixinappid");
            return (Criteria) this;
        }

        public Criteria andWeixinappidLessThanOrEqualTo(String value) {
            addCriterion("weixinappid <=", value, "weixinappid");
            return (Criteria) this;
        }

        public Criteria andWeixinappidLike(String value) {
            addCriterion("weixinappid like", value, "weixinappid");
            return (Criteria) this;
        }

        public Criteria andWeixinappidNotLike(String value) {
            addCriterion("weixinappid not like", value, "weixinappid");
            return (Criteria) this;
        }

        public Criteria andWeixinappidIn(List<String> values) {
            addCriterion("weixinappid in", values, "weixinappid");
            return (Criteria) this;
        }

        public Criteria andWeixinappidNotIn(List<String> values) {
            addCriterion("weixinappid not in", values, "weixinappid");
            return (Criteria) this;
        }

        public Criteria andWeixinappidBetween(String value1, String value2) {
            addCriterion("weixinappid between", value1, value2, "weixinappid");
            return (Criteria) this;
        }

        public Criteria andWeixinappidNotBetween(String value1, String value2) {
            addCriterion("weixinappid not between", value1, value2, "weixinappid");
            return (Criteria) this;
        }

        public Criteria andWeixinappsecretIsNull() {
            addCriterion("weixinappsecret is null");
            return (Criteria) this;
        }

        public Criteria andWeixinappsecretIsNotNull() {
            addCriterion("weixinappsecret is not null");
            return (Criteria) this;
        }

        public Criteria andWeixinappsecretEqualTo(String value) {
            addCriterion("weixinappsecret =", value, "weixinappsecret");
            return (Criteria) this;
        }

        public Criteria andWeixinappsecretNotEqualTo(String value) {
            addCriterion("weixinappsecret <>", value, "weixinappsecret");
            return (Criteria) this;
        }

        public Criteria andWeixinappsecretGreaterThan(String value) {
            addCriterion("weixinappsecret >", value, "weixinappsecret");
            return (Criteria) this;
        }

        public Criteria andWeixinappsecretGreaterThanOrEqualTo(String value) {
            addCriterion("weixinappsecret >=", value, "weixinappsecret");
            return (Criteria) this;
        }

        public Criteria andWeixinappsecretLessThan(String value) {
            addCriterion("weixinappsecret <", value, "weixinappsecret");
            return (Criteria) this;
        }

        public Criteria andWeixinappsecretLessThanOrEqualTo(String value) {
            addCriterion("weixinappsecret <=", value, "weixinappsecret");
            return (Criteria) this;
        }

        public Criteria andWeixinappsecretLike(String value) {
            addCriterion("weixinappsecret like", value, "weixinappsecret");
            return (Criteria) this;
        }

        public Criteria andWeixinappsecretNotLike(String value) {
            addCriterion("weixinappsecret not like", value, "weixinappsecret");
            return (Criteria) this;
        }

        public Criteria andWeixinappsecretIn(List<String> values) {
            addCriterion("weixinappsecret in", values, "weixinappsecret");
            return (Criteria) this;
        }

        public Criteria andWeixinappsecretNotIn(List<String> values) {
            addCriterion("weixinappsecret not in", values, "weixinappsecret");
            return (Criteria) this;
        }

        public Criteria andWeixinappsecretBetween(String value1, String value2) {
            addCriterion("weixinappsecret between", value1, value2, "weixinappsecret");
            return (Criteria) this;
        }

        public Criteria andWeixinappsecretNotBetween(String value1, String value2) {
            addCriterion("weixinappsecret not between", value1, value2, "weixinappsecret");
            return (Criteria) this;
        }

        public Criteria andWeixinkeyIsNull() {
            addCriterion("weixinkey is null");
            return (Criteria) this;
        }

        public Criteria andWeixinkeyIsNotNull() {
            addCriterion("weixinkey is not null");
            return (Criteria) this;
        }

        public Criteria andWeixinkeyEqualTo(String value) {
            addCriterion("weixinkey =", value, "weixinkey");
            return (Criteria) this;
        }

        public Criteria andWeixinkeyNotEqualTo(String value) {
            addCriterion("weixinkey <>", value, "weixinkey");
            return (Criteria) this;
        }

        public Criteria andWeixinkeyGreaterThan(String value) {
            addCriterion("weixinkey >", value, "weixinkey");
            return (Criteria) this;
        }

        public Criteria andWeixinkeyGreaterThanOrEqualTo(String value) {
            addCriterion("weixinkey >=", value, "weixinkey");
            return (Criteria) this;
        }

        public Criteria andWeixinkeyLessThan(String value) {
            addCriterion("weixinkey <", value, "weixinkey");
            return (Criteria) this;
        }

        public Criteria andWeixinkeyLessThanOrEqualTo(String value) {
            addCriterion("weixinkey <=", value, "weixinkey");
            return (Criteria) this;
        }

        public Criteria andWeixinkeyLike(String value) {
            addCriterion("weixinkey like", value, "weixinkey");
            return (Criteria) this;
        }

        public Criteria andWeixinkeyNotLike(String value) {
            addCriterion("weixinkey not like", value, "weixinkey");
            return (Criteria) this;
        }

        public Criteria andWeixinkeyIn(List<String> values) {
            addCriterion("weixinkey in", values, "weixinkey");
            return (Criteria) this;
        }

        public Criteria andWeixinkeyNotIn(List<String> values) {
            addCriterion("weixinkey not in", values, "weixinkey");
            return (Criteria) this;
        }

        public Criteria andWeixinkeyBetween(String value1, String value2) {
            addCriterion("weixinkey between", value1, value2, "weixinkey");
            return (Criteria) this;
        }

        public Criteria andWeixinkeyNotBetween(String value1, String value2) {
            addCriterion("weixinkey not between", value1, value2, "weixinkey");
            return (Criteria) this;
        }

        public Criteria andWeixinmchidIsNull() {
            addCriterion("weixinmchid is null");
            return (Criteria) this;
        }

        public Criteria andWeixinmchidIsNotNull() {
            addCriterion("weixinmchid is not null");
            return (Criteria) this;
        }

        public Criteria andWeixinmchidEqualTo(String value) {
            addCriterion("weixinmchid =", value, "weixinmchid");
            return (Criteria) this;
        }

        public Criteria andWeixinmchidNotEqualTo(String value) {
            addCriterion("weixinmchid <>", value, "weixinmchid");
            return (Criteria) this;
        }

        public Criteria andWeixinmchidGreaterThan(String value) {
            addCriterion("weixinmchid >", value, "weixinmchid");
            return (Criteria) this;
        }

        public Criteria andWeixinmchidGreaterThanOrEqualTo(String value) {
            addCriterion("weixinmchid >=", value, "weixinmchid");
            return (Criteria) this;
        }

        public Criteria andWeixinmchidLessThan(String value) {
            addCriterion("weixinmchid <", value, "weixinmchid");
            return (Criteria) this;
        }

        public Criteria andWeixinmchidLessThanOrEqualTo(String value) {
            addCriterion("weixinmchid <=", value, "weixinmchid");
            return (Criteria) this;
        }

        public Criteria andWeixinmchidLike(String value) {
            addCriterion("weixinmchid like", value, "weixinmchid");
            return (Criteria) this;
        }

        public Criteria andWeixinmchidNotLike(String value) {
            addCriterion("weixinmchid not like", value, "weixinmchid");
            return (Criteria) this;
        }

        public Criteria andWeixinmchidIn(List<String> values) {
            addCriterion("weixinmchid in", values, "weixinmchid");
            return (Criteria) this;
        }

        public Criteria andWeixinmchidNotIn(List<String> values) {
            addCriterion("weixinmchid not in", values, "weixinmchid");
            return (Criteria) this;
        }

        public Criteria andWeixinmchidBetween(String value1, String value2) {
            addCriterion("weixinmchid between", value1, value2, "weixinmchid");
            return (Criteria) this;
        }

        public Criteria andWeixinmchidNotBetween(String value1, String value2) {
            addCriterion("weixinmchid not between", value1, value2, "weixinmchid");
            return (Criteria) this;
        }

        public Criteria andCertificateIsNull() {
            addCriterion("certificate is null");
            return (Criteria) this;
        }

        public Criteria andCertificateIsNotNull() {
            addCriterion("certificate is not null");
            return (Criteria) this;
        }

        public Criteria andCertificateEqualTo(String value) {
            addCriterion("certificate =", value, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateNotEqualTo(String value) {
            addCriterion("certificate <>", value, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateGreaterThan(String value) {
            addCriterion("certificate >", value, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateGreaterThanOrEqualTo(String value) {
            addCriterion("certificate >=", value, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateLessThan(String value) {
            addCriterion("certificate <", value, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateLessThanOrEqualTo(String value) {
            addCriterion("certificate <=", value, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateLike(String value) {
            addCriterion("certificate like", value, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateNotLike(String value) {
            addCriterion("certificate not like", value, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateIn(List<String> values) {
            addCriterion("certificate in", values, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateNotIn(List<String> values) {
            addCriterion("certificate not in", values, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateBetween(String value1, String value2) {
            addCriterion("certificate between", value1, value2, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateNotBetween(String value1, String value2) {
            addCriterion("certificate not between", value1, value2, "certificate");
            return (Criteria) this;
        }

        public Criteria andOpenIsNull() {
            addCriterion("open is null");
            return (Criteria) this;
        }

        public Criteria andOpenIsNotNull() {
            addCriterion("open is not null");
            return (Criteria) this;
        }

        public Criteria andOpenEqualTo(Short value) {
            addCriterion("open =", value, "open");
            return (Criteria) this;
        }

        public Criteria andOpenNotEqualTo(Short value) {
            addCriterion("open <>", value, "open");
            return (Criteria) this;
        }

        public Criteria andOpenGreaterThan(Short value) {
            addCriterion("open >", value, "open");
            return (Criteria) this;
        }

        public Criteria andOpenGreaterThanOrEqualTo(Short value) {
            addCriterion("open >=", value, "open");
            return (Criteria) this;
        }

        public Criteria andOpenLessThan(Short value) {
            addCriterion("open <", value, "open");
            return (Criteria) this;
        }

        public Criteria andOpenLessThanOrEqualTo(Short value) {
            addCriterion("open <=", value, "open");
            return (Criteria) this;
        }

        public Criteria andOpenIn(List<Short> values) {
            addCriterion("open in", values, "open");
            return (Criteria) this;
        }

        public Criteria andOpenNotIn(List<Short> values) {
            addCriterion("open not in", values, "open");
            return (Criteria) this;
        }

        public Criteria andOpenBetween(Short value1, Short value2) {
            addCriterion("open between", value1, value2, "open");
            return (Criteria) this;
        }

        public Criteria andOpenNotBetween(Short value1, Short value2) {
            addCriterion("open not between", value1, value2, "open");
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