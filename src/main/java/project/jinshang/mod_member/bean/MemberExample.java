package project.jinshang.mod_member.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemberExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MemberExample() {
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

        public Criteria andUsernameIsNull() {
            addCriterion("username is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("username is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("username =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("username <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("username >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("username >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("username <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("username <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("username like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("username not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("username in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("username not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("username between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("username not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordsaltIsNull() {
            addCriterion("passwordsalt is null");
            return (Criteria) this;
        }

        public Criteria andPasswordsaltIsNotNull() {
            addCriterion("passwordsalt is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordsaltEqualTo(String value) {
            addCriterion("passwordsalt =", value, "passwordsalt");
            return (Criteria) this;
        }

        public Criteria andPasswordsaltNotEqualTo(String value) {
            addCriterion("passwordsalt <>", value, "passwordsalt");
            return (Criteria) this;
        }

        public Criteria andPasswordsaltGreaterThan(String value) {
            addCriterion("passwordsalt >", value, "passwordsalt");
            return (Criteria) this;
        }

        public Criteria andPasswordsaltGreaterThanOrEqualTo(String value) {
            addCriterion("passwordsalt >=", value, "passwordsalt");
            return (Criteria) this;
        }

        public Criteria andPasswordsaltLessThan(String value) {
            addCriterion("passwordsalt <", value, "passwordsalt");
            return (Criteria) this;
        }

        public Criteria andPasswordsaltLessThanOrEqualTo(String value) {
            addCriterion("passwordsalt <=", value, "passwordsalt");
            return (Criteria) this;
        }

        public Criteria andPasswordsaltLike(String value) {
            addCriterion("passwordsalt like", value, "passwordsalt");
            return (Criteria) this;
        }

        public Criteria andPasswordsaltNotLike(String value) {
            addCriterion("passwordsalt not like", value, "passwordsalt");
            return (Criteria) this;
        }

        public Criteria andPasswordsaltIn(List<String> values) {
            addCriterion("passwordsalt in", values, "passwordsalt");
            return (Criteria) this;
        }

        public Criteria andPasswordsaltNotIn(List<String> values) {
            addCriterion("passwordsalt not in", values, "passwordsalt");
            return (Criteria) this;
        }

        public Criteria andPasswordsaltBetween(String value1, String value2) {
            addCriterion("passwordsalt between", value1, value2, "passwordsalt");
            return (Criteria) this;
        }

        public Criteria andPasswordsaltNotBetween(String value1, String value2) {
            addCriterion("passwordsalt not between", value1, value2, "passwordsalt");
            return (Criteria) this;
        }

        public Criteria andMobileIsNull() {
            addCriterion("mobile is null");
            return (Criteria) this;
        }

        public Criteria andMobileIsNotNull() {
            addCriterion("mobile is not null");
            return (Criteria) this;
        }

        public Criteria andMobileEqualTo(String value) {
            addCriterion("mobile =", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotEqualTo(String value) {
            addCriterion("mobile <>", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThan(String value) {
            addCriterion("mobile >", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThanOrEqualTo(String value) {
            addCriterion("mobile >=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThan(String value) {
            addCriterion("mobile <", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThanOrEqualTo(String value) {
            addCriterion("mobile <=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLike(String value) {
            addCriterion("mobile like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotLike(String value) {
            addCriterion("mobile not like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileIn(List<String> values) {
            addCriterion("mobile in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotIn(List<String> values) {
            addCriterion("mobile not in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileBetween(String value1, String value2) {
            addCriterion("mobile between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotBetween(String value1, String value2) {
            addCriterion("mobile not between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andPostcodeIsNull() {
            addCriterion("postcode is null");
            return (Criteria) this;
        }

        public Criteria andPostcodeIsNotNull() {
            addCriterion("postcode is not null");
            return (Criteria) this;
        }

        public Criteria andPostcodeEqualTo(String value) {
            addCriterion("postcode =", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeNotEqualTo(String value) {
            addCriterion("postcode <>", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeGreaterThan(String value) {
            addCriterion("postcode >", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeGreaterThanOrEqualTo(String value) {
            addCriterion("postcode >=", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeLessThan(String value) {
            addCriterion("postcode <", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeLessThanOrEqualTo(String value) {
            addCriterion("postcode <=", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeLike(String value) {
            addCriterion("postcode like", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeNotLike(String value) {
            addCriterion("postcode not like", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeIn(List<String> values) {
            addCriterion("postcode in", values, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeNotIn(List<String> values) {
            addCriterion("postcode not in", values, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeBetween(String value1, String value2) {
            addCriterion("postcode between", value1, value2, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeNotBetween(String value1, String value2) {
            addCriterion("postcode not between", value1, value2, "postcode");
            return (Criteria) this;
        }

        public Criteria andNickIsNull() {
            addCriterion("nick is null");
            return (Criteria) this;
        }

        public Criteria andNickIsNotNull() {
            addCriterion("nick is not null");
            return (Criteria) this;
        }

        public Criteria andNickEqualTo(String value) {
            addCriterion("nick =", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickNotEqualTo(String value) {
            addCriterion("nick <>", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickGreaterThan(String value) {
            addCriterion("nick >", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickGreaterThanOrEqualTo(String value) {
            addCriterion("nick >=", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickLessThan(String value) {
            addCriterion("nick <", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickLessThanOrEqualTo(String value) {
            addCriterion("nick <=", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickLike(String value) {
            addCriterion("nick like", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickNotLike(String value) {
            addCriterion("nick not like", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickIn(List<String> values) {
            addCriterion("nick in", values, "nick");
            return (Criteria) this;
        }

        public Criteria andNickNotIn(List<String> values) {
            addCriterion("nick not in", values, "nick");
            return (Criteria) this;
        }

        public Criteria andNickBetween(String value1, String value2) {
            addCriterion("nick between", value1, value2, "nick");
            return (Criteria) this;
        }

        public Criteria andNickNotBetween(String value1, String value2) {
            addCriterion("nick not between", value1, value2, "nick");
            return (Criteria) this;
        }

        public Criteria andRealnameIsNull() {
            addCriterion("realname is null");
            return (Criteria) this;
        }

        public Criteria andRealnameIsNotNull() {
            addCriterion("realname is not null");
            return (Criteria) this;
        }

        public Criteria andRealnameEqualTo(String value) {
            addCriterion("realname =", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotEqualTo(String value) {
            addCriterion("realname <>", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameGreaterThan(String value) {
            addCriterion("realname >", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameGreaterThanOrEqualTo(String value) {
            addCriterion("realname >=", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameLessThan(String value) {
            addCriterion("realname <", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameLessThanOrEqualTo(String value) {
            addCriterion("realname <=", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameLike(String value) {
            addCriterion("realname like", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotLike(String value) {
            addCriterion("realname not like", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameIn(List<String> values) {
            addCriterion("realname in", values, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotIn(List<String> values) {
            addCriterion("realname not in", values, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameBetween(String value1, String value2) {
            addCriterion("realname between", value1, value2, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotBetween(String value1, String value2) {
            addCriterion("realname not between", value1, value2, "realname");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andDisabledIsNull() {
            addCriterion("disabled is null");
            return (Criteria) this;
        }

        public Criteria andDisabledIsNotNull() {
            addCriterion("disabled is not null");
            return (Criteria) this;
        }

        public Criteria andDisabledEqualTo(Boolean value) {
            addCriterion("disabled =", value, "disabled");
            return (Criteria) this;
        }

        public Criteria andDisabledNotEqualTo(Boolean value) {
            addCriterion("disabled <>", value, "disabled");
            return (Criteria) this;
        }

        public Criteria andDisabledGreaterThan(Boolean value) {
            addCriterion("disabled >", value, "disabled");
            return (Criteria) this;
        }

        public Criteria andDisabledGreaterThanOrEqualTo(Boolean value) {
            addCriterion("disabled >=", value, "disabled");
            return (Criteria) this;
        }

        public Criteria andDisabledLessThan(Boolean value) {
            addCriterion("disabled <", value, "disabled");
            return (Criteria) this;
        }

        public Criteria andDisabledLessThanOrEqualTo(Boolean value) {
            addCriterion("disabled <=", value, "disabled");
            return (Criteria) this;
        }

        public Criteria andDisabledIn(List<Boolean> values) {
            addCriterion("disabled in", values, "disabled");
            return (Criteria) this;
        }

        public Criteria andDisabledNotIn(List<Boolean> values) {
            addCriterion("disabled not in", values, "disabled");
            return (Criteria) this;
        }

        public Criteria andDisabledBetween(Boolean value1, Boolean value2) {
            addCriterion("disabled between", value1, value2, "disabled");
            return (Criteria) this;
        }

        public Criteria andDisabledNotBetween(Boolean value1, Boolean value2) {
            addCriterion("disabled not between", value1, value2, "disabled");
            return (Criteria) this;
        }

        public Criteria andCreatedateIsNull() {
            addCriterion("createdate is null");
            return (Criteria) this;
        }

        public Criteria andCreatedateIsNotNull() {
            addCriterion("createdate is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedateEqualTo(Date value) {
            addCriterion("createdate =", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotEqualTo(Date value) {
            addCriterion("createdate <>", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateGreaterThan(Date value) {
            addCriterion("createdate >", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateGreaterThanOrEqualTo(Date value) {
            addCriterion("createdate >=", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateLessThan(Date value) {
            addCriterion("createdate <", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateLessThanOrEqualTo(Date value) {
            addCriterion("createdate <=", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateIn(List<Date> values) {
            addCriterion("createdate in", values, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotIn(List<Date> values) {
            addCriterion("createdate not in", values, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateBetween(Date value1, Date value2) {
            addCriterion("createdate between", value1, value2, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotBetween(Date value1, Date value2) {
            addCriterion("createdate not between", value1, value2, "createdate");
            return (Criteria) this;
        }

        public Criteria andLastlogindateIsNull() {
            addCriterion("lastlogindate is null");
            return (Criteria) this;
        }

        public Criteria andLastlogindateIsNotNull() {
            addCriterion("lastlogindate is not null");
            return (Criteria) this;
        }

        public Criteria andLastlogindateEqualTo(Date value) {
            addCriterion("lastlogindate =", value, "lastlogindate");
            return (Criteria) this;
        }

        public Criteria andLastlogindateNotEqualTo(Date value) {
            addCriterion("lastlogindate <>", value, "lastlogindate");
            return (Criteria) this;
        }

        public Criteria andLastlogindateGreaterThan(Date value) {
            addCriterion("lastlogindate >", value, "lastlogindate");
            return (Criteria) this;
        }

        public Criteria andLastlogindateGreaterThanOrEqualTo(Date value) {
            addCriterion("lastlogindate >=", value, "lastlogindate");
            return (Criteria) this;
        }

        public Criteria andLastlogindateLessThan(Date value) {
            addCriterion("lastlogindate <", value, "lastlogindate");
            return (Criteria) this;
        }

        public Criteria andLastlogindateLessThanOrEqualTo(Date value) {
            addCriterion("lastlogindate <=", value, "lastlogindate");
            return (Criteria) this;
        }

        public Criteria andLastlogindateIn(List<Date> values) {
            addCriterion("lastlogindate in", values, "lastlogindate");
            return (Criteria) this;
        }

        public Criteria andLastlogindateNotIn(List<Date> values) {
            addCriterion("lastlogindate not in", values, "lastlogindate");
            return (Criteria) this;
        }

        public Criteria andLastlogindateBetween(Date value1, Date value2) {
            addCriterion("lastlogindate between", value1, value2, "lastlogindate");
            return (Criteria) this;
        }

        public Criteria andLastlogindateNotBetween(Date value1, Date value2) {
            addCriterion("lastlogindate not between", value1, value2, "lastlogindate");
            return (Criteria) this;
        }

        public Criteria andPhotoIsNull() {
            addCriterion("photo is null");
            return (Criteria) this;
        }

        public Criteria andPhotoIsNotNull() {
            addCriterion("photo is not null");
            return (Criteria) this;
        }

        public Criteria andPhotoEqualTo(String value) {
            addCriterion("photo =", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoNotEqualTo(String value) {
            addCriterion("photo <>", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoGreaterThan(String value) {
            addCriterion("photo >", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoGreaterThanOrEqualTo(String value) {
            addCriterion("photo >=", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoLessThan(String value) {
            addCriterion("photo <", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoLessThanOrEqualTo(String value) {
            addCriterion("photo <=", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoLike(String value) {
            addCriterion("photo like", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoNotLike(String value) {
            addCriterion("photo not like", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoIn(List<String> values) {
            addCriterion("photo in", values, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoNotIn(List<String> values) {
            addCriterion("photo not in", values, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoBetween(String value1, String value2) {
            addCriterion("photo between", value1, value2, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoNotBetween(String value1, String value2) {
            addCriterion("photo not between", value1, value2, "photo");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Short value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Short value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Short value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Short value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Short value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Short> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Short> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Short value1, Short value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Short value1, Short value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andPaypasswordIsNull() {
            addCriterion("paypassword is null");
            return (Criteria) this;
        }

        public Criteria andPaypasswordIsNotNull() {
            addCriterion("paypassword is not null");
            return (Criteria) this;
        }

        public Criteria andPaypasswordEqualTo(String value) {
            addCriterion("paypassword =", value, "paypassword");
            return (Criteria) this;
        }

        public Criteria andPaypasswordNotEqualTo(String value) {
            addCriterion("paypassword <>", value, "paypassword");
            return (Criteria) this;
        }

        public Criteria andPaypasswordGreaterThan(String value) {
            addCriterion("paypassword >", value, "paypassword");
            return (Criteria) this;
        }

        public Criteria andPaypasswordGreaterThanOrEqualTo(String value) {
            addCriterion("paypassword >=", value, "paypassword");
            return (Criteria) this;
        }

        public Criteria andPaypasswordLessThan(String value) {
            addCriterion("paypassword <", value, "paypassword");
            return (Criteria) this;
        }

        public Criteria andPaypasswordLessThanOrEqualTo(String value) {
            addCriterion("paypassword <=", value, "paypassword");
            return (Criteria) this;
        }

        public Criteria andPaypasswordLike(String value) {
            addCriterion("paypassword like", value, "paypassword");
            return (Criteria) this;
        }

        public Criteria andPaypasswordNotLike(String value) {
            addCriterion("paypassword not like", value, "paypassword");
            return (Criteria) this;
        }

        public Criteria andPaypasswordIn(List<String> values) {
            addCriterion("paypassword in", values, "paypassword");
            return (Criteria) this;
        }

        public Criteria andPaypasswordNotIn(List<String> values) {
            addCriterion("paypassword not in", values, "paypassword");
            return (Criteria) this;
        }

        public Criteria andPaypasswordBetween(String value1, String value2) {
            addCriterion("paypassword between", value1, value2, "paypassword");
            return (Criteria) this;
        }

        public Criteria andPaypasswordNotBetween(String value1, String value2) {
            addCriterion("paypassword not between", value1, value2, "paypassword");
            return (Criteria) this;
        }

        public Criteria andPaypasswordsaltIsNull() {
            addCriterion("paypasswordsalt is null");
            return (Criteria) this;
        }

        public Criteria andPaypasswordsaltIsNotNull() {
            addCriterion("paypasswordsalt is not null");
            return (Criteria) this;
        }

        public Criteria andPaypasswordsaltEqualTo(String value) {
            addCriterion("paypasswordsalt =", value, "paypasswordsalt");
            return (Criteria) this;
        }

        public Criteria andPaypasswordsaltNotEqualTo(String value) {
            addCriterion("paypasswordsalt <>", value, "paypasswordsalt");
            return (Criteria) this;
        }

        public Criteria andPaypasswordsaltGreaterThan(String value) {
            addCriterion("paypasswordsalt >", value, "paypasswordsalt");
            return (Criteria) this;
        }

        public Criteria andPaypasswordsaltGreaterThanOrEqualTo(String value) {
            addCriterion("paypasswordsalt >=", value, "paypasswordsalt");
            return (Criteria) this;
        }

        public Criteria andPaypasswordsaltLessThan(String value) {
            addCriterion("paypasswordsalt <", value, "paypasswordsalt");
            return (Criteria) this;
        }

        public Criteria andPaypasswordsaltLessThanOrEqualTo(String value) {
            addCriterion("paypasswordsalt <=", value, "paypasswordsalt");
            return (Criteria) this;
        }

        public Criteria andPaypasswordsaltLike(String value) {
            addCriterion("paypasswordsalt like", value, "paypasswordsalt");
            return (Criteria) this;
        }

        public Criteria andPaypasswordsaltNotLike(String value) {
            addCriterion("paypasswordsalt not like", value, "paypasswordsalt");
            return (Criteria) this;
        }

        public Criteria andPaypasswordsaltIn(List<String> values) {
            addCriterion("paypasswordsalt in", values, "paypasswordsalt");
            return (Criteria) this;
        }

        public Criteria andPaypasswordsaltNotIn(List<String> values) {
            addCriterion("paypasswordsalt not in", values, "paypasswordsalt");
            return (Criteria) this;
        }

        public Criteria andPaypasswordsaltBetween(String value1, String value2) {
            addCriterion("paypasswordsalt between", value1, value2, "paypasswordsalt");
            return (Criteria) this;
        }

        public Criteria andPaypasswordsaltNotBetween(String value1, String value2) {
            addCriterion("paypasswordsalt not between", value1, value2, "paypasswordsalt");
            return (Criteria) this;
        }

        public Criteria andCompanyIsNull() {
            addCriterion("company is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIsNotNull() {
            addCriterion("company is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyEqualTo(Boolean value) {
            addCriterion("company =", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotEqualTo(Boolean value) {
            addCriterion("company <>", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyGreaterThan(Boolean value) {
            addCriterion("company >", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyGreaterThanOrEqualTo(Boolean value) {
            addCriterion("company >=", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLessThan(Boolean value) {
            addCriterion("company <", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLessThanOrEqualTo(Boolean value) {
            addCriterion("company <=", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyIn(List<Boolean> values) {
            addCriterion("company in", values, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotIn(List<Boolean> values) {
            addCriterion("company not in", values, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyBetween(Boolean value1, Boolean value2) {
            addCriterion("company between", value1, value2, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotBetween(Boolean value1, Boolean value2) {
            addCriterion("company not between", value1, value2, "company");
            return (Criteria) this;
        }

        public Criteria andReviewedIsNull() {
            addCriterion("reviewed is null");
            return (Criteria) this;
        }

        public Criteria andReviewedIsNotNull() {
            addCriterion("reviewed is not null");
            return (Criteria) this;
        }

        public Criteria andReviewedEqualTo(Boolean value) {
            addCriterion("reviewed =", value, "reviewed");
            return (Criteria) this;
        }

        public Criteria andReviewedNotEqualTo(Boolean value) {
            addCriterion("reviewed <>", value, "reviewed");
            return (Criteria) this;
        }

        public Criteria andReviewedGreaterThan(Boolean value) {
            addCriterion("reviewed >", value, "reviewed");
            return (Criteria) this;
        }

        public Criteria andReviewedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("reviewed >=", value, "reviewed");
            return (Criteria) this;
        }

        public Criteria andReviewedLessThan(Boolean value) {
            addCriterion("reviewed <", value, "reviewed");
            return (Criteria) this;
        }

        public Criteria andReviewedLessThanOrEqualTo(Boolean value) {
            addCriterion("reviewed <=", value, "reviewed");
            return (Criteria) this;
        }

        public Criteria andReviewedIn(List<Boolean> values) {
            addCriterion("reviewed in", values, "reviewed");
            return (Criteria) this;
        }

        public Criteria andReviewedNotIn(List<Boolean> values) {
            addCriterion("reviewed not in", values, "reviewed");
            return (Criteria) this;
        }

        public Criteria andReviewedBetween(Boolean value1, Boolean value2) {
            addCriterion("reviewed between", value1, value2, "reviewed");
            return (Criteria) this;
        }

        public Criteria andReviewedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("reviewed not between", value1, value2, "reviewed");
            return (Criteria) this;
        }

        public Criteria andGradleidIsNull() {
            addCriterion("gradleid is null");
            return (Criteria) this;
        }

        public Criteria andGradleidIsNotNull() {
            addCriterion("gradleid is not null");
            return (Criteria) this;
        }

        public Criteria andGradleidEqualTo(Long value) {
            addCriterion("gradleid =", value, "gradleid");
            return (Criteria) this;
        }

        public Criteria andGradleidNotEqualTo(Long value) {
            addCriterion("gradleid <>", value, "gradleid");
            return (Criteria) this;
        }

        public Criteria andGradleidGreaterThan(Long value) {
            addCriterion("gradleid >", value, "gradleid");
            return (Criteria) this;
        }

        public Criteria andGradleidGreaterThanOrEqualTo(Long value) {
            addCriterion("gradleid >=", value, "gradleid");
            return (Criteria) this;
        }

        public Criteria andGradleidLessThan(Long value) {
            addCriterion("gradleid <", value, "gradleid");
            return (Criteria) this;
        }

        public Criteria andGradleidLessThanOrEqualTo(Long value) {
            addCriterion("gradleid <=", value, "gradleid");
            return (Criteria) this;
        }

        public Criteria andGradleidIn(List<Long> values) {
            addCriterion("gradleid in", values, "gradleid");
            return (Criteria) this;
        }

        public Criteria andGradleidNotIn(List<Long> values) {
            addCriterion("gradleid not in", values, "gradleid");
            return (Criteria) this;
        }

        public Criteria andGradleidBetween(Long value1, Long value2) {
            addCriterion("gradleid between", value1, value2, "gradleid");
            return (Criteria) this;
        }

        public Criteria andGradleidNotBetween(Long value1, Long value2) {
            addCriterion("gradleid not between", value1, value2, "gradleid");
            return (Criteria) this;
        }

        public Criteria andDeliveryregionidIsNull() {
            addCriterion("deliveryregionid is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryregionidIsNotNull() {
            addCriterion("deliveryregionid is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryregionidEqualTo(String value) {
            addCriterion("deliveryregionid =", value, "deliveryregionid");
            return (Criteria) this;
        }

        public Criteria andDeliveryregionidNotEqualTo(String value) {
            addCriterion("deliveryregionid <>", value, "deliveryregionid");
            return (Criteria) this;
        }

        public Criteria andDeliveryregionidGreaterThan(String value) {
            addCriterion("deliveryregionid >", value, "deliveryregionid");
            return (Criteria) this;
        }

        public Criteria andDeliveryregionidGreaterThanOrEqualTo(String value) {
            addCriterion("deliveryregionid >=", value, "deliveryregionid");
            return (Criteria) this;
        }

        public Criteria andDeliveryregionidLessThan(String value) {
            addCriterion("deliveryregionid <", value, "deliveryregionid");
            return (Criteria) this;
        }

        public Criteria andDeliveryregionidLessThanOrEqualTo(String value) {
            addCriterion("deliveryregionid <=", value, "deliveryregionid");
            return (Criteria) this;
        }

        public Criteria andDeliveryregionidLike(String value) {
            addCriterion("deliveryregionid like", value, "deliveryregionid");
            return (Criteria) this;
        }

        public Criteria andDeliveryregionidNotLike(String value) {
            addCriterion("deliveryregionid not like", value, "deliveryregionid");
            return (Criteria) this;
        }

        public Criteria andDeliveryregionidIn(List<String> values) {
            addCriterion("deliveryregionid in", values, "deliveryregionid");
            return (Criteria) this;
        }

        public Criteria andDeliveryregionidNotIn(List<String> values) {
            addCriterion("deliveryregionid not in", values, "deliveryregionid");
            return (Criteria) this;
        }

        public Criteria andDeliveryregionidBetween(String value1, String value2) {
            addCriterion("deliveryregionid between", value1, value2, "deliveryregionid");
            return (Criteria) this;
        }

        public Criteria andDeliveryregionidNotBetween(String value1, String value2) {
            addCriterion("deliveryregionid not between", value1, value2, "deliveryregionid");
            return (Criteria) this;
        }

        public Criteria andDeliveryaddressIsNull() {
            addCriterion("deliveryaddress is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryaddressIsNotNull() {
            addCriterion("deliveryaddress is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryaddressEqualTo(String value) {
            addCriterion("deliveryaddress =", value, "deliveryaddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryaddressNotEqualTo(String value) {
            addCriterion("deliveryaddress <>", value, "deliveryaddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryaddressGreaterThan(String value) {
            addCriterion("deliveryaddress >", value, "deliveryaddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryaddressGreaterThanOrEqualTo(String value) {
            addCriterion("deliveryaddress >=", value, "deliveryaddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryaddressLessThan(String value) {
            addCriterion("deliveryaddress <", value, "deliveryaddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryaddressLessThanOrEqualTo(String value) {
            addCriterion("deliveryaddress <=", value, "deliveryaddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryaddressLike(String value) {
            addCriterion("deliveryaddress like", value, "deliveryaddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryaddressNotLike(String value) {
            addCriterion("deliveryaddress not like", value, "deliveryaddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryaddressIn(List<String> values) {
            addCriterion("deliveryaddress in", values, "deliveryaddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryaddressNotIn(List<String> values) {
            addCriterion("deliveryaddress not in", values, "deliveryaddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryaddressBetween(String value1, String value2) {
            addCriterion("deliveryaddress between", value1, value2, "deliveryaddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryaddressNotBetween(String value1, String value2) {
            addCriterion("deliveryaddress not between", value1, value2, "deliveryaddress");
            return (Criteria) this;
        }

        public Criteria andIntegralsIsNull() {
            addCriterion("integrals is null");
            return (Criteria) this;
        }

        public Criteria andIntegralsIsNotNull() {
            addCriterion("integrals is not null");
            return (Criteria) this;
        }

        public Criteria andIntegralsEqualTo(BigDecimal value) {
            addCriterion("integrals =", value, "integrals");
            return (Criteria) this;
        }

        public Criteria andIntegralsNotEqualTo(BigDecimal value) {
            addCriterion("integrals <>", value, "integrals");
            return (Criteria) this;
        }

        public Criteria andIntegralsGreaterThan(BigDecimal value) {
            addCriterion("integrals >", value, "integrals");
            return (Criteria) this;
        }

        public Criteria andIntegralsGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("integrals >=", value, "integrals");
            return (Criteria) this;
        }

        public Criteria andIntegralsLessThan(BigDecimal value) {
            addCriterion("integrals <", value, "integrals");
            return (Criteria) this;
        }

        public Criteria andIntegralsLessThanOrEqualTo(BigDecimal value) {
            addCriterion("integrals <=", value, "integrals");
            return (Criteria) this;
        }

        public Criteria andIntegralsIn(List<BigDecimal> values) {
            addCriterion("integrals in", values, "integrals");
            return (Criteria) this;
        }

        public Criteria andIntegralsNotIn(List<BigDecimal> values) {
            addCriterion("integrals not in", values, "integrals");
            return (Criteria) this;
        }

        public Criteria andIntegralsBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("integrals between", value1, value2, "integrals");
            return (Criteria) this;
        }

        public Criteria andIntegralsNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("integrals not between", value1, value2, "integrals");
            return (Criteria) this;
        }

        public Criteria andBalanceIsNull() {
            addCriterion("balance is null");
            return (Criteria) this;
        }

        public Criteria andBalanceIsNotNull() {
            addCriterion("balance is not null");
            return (Criteria) this;
        }

        public Criteria andBalanceEqualTo(BigDecimal value) {
            addCriterion("balance =", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotEqualTo(BigDecimal value) {
            addCriterion("balance <>", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThan(BigDecimal value) {
            addCriterion("balance >", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("balance >=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThan(BigDecimal value) {
            addCriterion("balance <", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("balance <=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceIn(List<BigDecimal> values) {
            addCriterion("balance in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotIn(List<BigDecimal> values) {
            addCriterion("balance not in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("balance between", value1, value2, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("balance not between", value1, value2, "balance");
            return (Criteria) this;
        }

        public Criteria andAlipayIsNull() {
            addCriterion("alipay is null");
            return (Criteria) this;
        }

        public Criteria andAlipayIsNotNull() {
            addCriterion("alipay is not null");
            return (Criteria) this;
        }

        public Criteria andAlipayEqualTo(String value) {
            addCriterion("alipay =", value, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayNotEqualTo(String value) {
            addCriterion("alipay <>", value, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayGreaterThan(String value) {
            addCriterion("alipay >", value, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayGreaterThanOrEqualTo(String value) {
            addCriterion("alipay >=", value, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayLessThan(String value) {
            addCriterion("alipay <", value, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayLessThanOrEqualTo(String value) {
            addCriterion("alipay <=", value, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayLike(String value) {
            addCriterion("alipay like", value, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayNotLike(String value) {
            addCriterion("alipay not like", value, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayIn(List<String> values) {
            addCriterion("alipay in", values, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayNotIn(List<String> values) {
            addCriterion("alipay not in", values, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayBetween(String value1, String value2) {
            addCriterion("alipay between", value1, value2, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayNotBetween(String value1, String value2) {
            addCriterion("alipay not between", value1, value2, "alipay");
            return (Criteria) this;
        }

        public Criteria andWxpayIsNull() {
            addCriterion("wxpay is null");
            return (Criteria) this;
        }

        public Criteria andWxpayIsNotNull() {
            addCriterion("wxpay is not null");
            return (Criteria) this;
        }

        public Criteria andWxpayEqualTo(String value) {
            addCriterion("wxpay =", value, "wxpay");
            return (Criteria) this;
        }

        public Criteria andWxpayNotEqualTo(String value) {
            addCriterion("wxpay <>", value, "wxpay");
            return (Criteria) this;
        }

        public Criteria andWxpayGreaterThan(String value) {
            addCriterion("wxpay >", value, "wxpay");
            return (Criteria) this;
        }

        public Criteria andWxpayGreaterThanOrEqualTo(String value) {
            addCriterion("wxpay >=", value, "wxpay");
            return (Criteria) this;
        }

        public Criteria andWxpayLessThan(String value) {
            addCriterion("wxpay <", value, "wxpay");
            return (Criteria) this;
        }

        public Criteria andWxpayLessThanOrEqualTo(String value) {
            addCriterion("wxpay <=", value, "wxpay");
            return (Criteria) this;
        }

        public Criteria andWxpayLike(String value) {
            addCriterion("wxpay like", value, "wxpay");
            return (Criteria) this;
        }

        public Criteria andWxpayNotLike(String value) {
            addCriterion("wxpay not like", value, "wxpay");
            return (Criteria) this;
        }

        public Criteria andWxpayIn(List<String> values) {
            addCriterion("wxpay in", values, "wxpay");
            return (Criteria) this;
        }

        public Criteria andWxpayNotIn(List<String> values) {
            addCriterion("wxpay not in", values, "wxpay");
            return (Criteria) this;
        }

        public Criteria andWxpayBetween(String value1, String value2) {
            addCriterion("wxpay between", value1, value2, "wxpay");
            return (Criteria) this;
        }

        public Criteria andWxpayNotBetween(String value1, String value2) {
            addCriterion("wxpay not between", value1, value2, "wxpay");
            return (Criteria) this;
        }

        public Criteria andWayIsNull() {
            addCriterion("way is null");
            return (Criteria) this;
        }

        public Criteria andWayIsNotNull() {
            addCriterion("way is not null");
            return (Criteria) this;
        }

        public Criteria andWayEqualTo(String value) {
            addCriterion("way =", value, "way");
            return (Criteria) this;
        }

        public Criteria andWayNotEqualTo(String value) {
            addCriterion("way <>", value, "way");
            return (Criteria) this;
        }

        public Criteria andWayGreaterThan(String value) {
            addCriterion("way >", value, "way");
            return (Criteria) this;
        }

        public Criteria andWayGreaterThanOrEqualTo(String value) {
            addCriterion("way >=", value, "way");
            return (Criteria) this;
        }

        public Criteria andWayLessThan(String value) {
            addCriterion("way <", value, "way");
            return (Criteria) this;
        }

        public Criteria andWayLessThanOrEqualTo(String value) {
            addCriterion("way <=", value, "way");
            return (Criteria) this;
        }

        public Criteria andWayLike(String value) {
            addCriterion("way like", value, "way");
            return (Criteria) this;
        }

        public Criteria andWayNotLike(String value) {
            addCriterion("way not like", value, "way");
            return (Criteria) this;
        }

        public Criteria andWayIn(List<String> values) {
            addCriterion("way in", values, "way");
            return (Criteria) this;
        }

        public Criteria andWayNotIn(List<String> values) {
            addCriterion("way not in", values, "way");
            return (Criteria) this;
        }

        public Criteria andWayBetween(String value1, String value2) {
            addCriterion("way between", value1, value2, "way");
            return (Criteria) this;
        }

        public Criteria andWayNotBetween(String value1, String value2) {
            addCriterion("way not between", value1, value2, "way");
            return (Criteria) this;
        }

        public Criteria andWaysalesmanIsNull() {
            addCriterion("waysalesman is null");
            return (Criteria) this;
        }

        public Criteria andWaysalesmanIsNotNull() {
            addCriterion("waysalesman is not null");
            return (Criteria) this;
        }

        public Criteria andWaysalesmanEqualTo(String value) {
            addCriterion("waysalesman =", value, "waysalesman");
            return (Criteria) this;
        }

        public Criteria andWaysalesmanNotEqualTo(String value) {
            addCriterion("waysalesman <>", value, "waysalesman");
            return (Criteria) this;
        }

        public Criteria andWaysalesmanGreaterThan(String value) {
            addCriterion("waysalesman >", value, "waysalesman");
            return (Criteria) this;
        }

        public Criteria andWaysalesmanGreaterThanOrEqualTo(String value) {
            addCriterion("waysalesman >=", value, "waysalesman");
            return (Criteria) this;
        }

        public Criteria andWaysalesmanLessThan(String value) {
            addCriterion("waysalesman <", value, "waysalesman");
            return (Criteria) this;
        }

        public Criteria andWaysalesmanLessThanOrEqualTo(String value) {
            addCriterion("waysalesman <=", value, "waysalesman");
            return (Criteria) this;
        }

        public Criteria andWaysalesmanLike(String value) {
            addCriterion("waysalesman like", value, "waysalesman");
            return (Criteria) this;
        }

        public Criteria andWaysalesmanNotLike(String value) {
            addCriterion("waysalesman not like", value, "waysalesman");
            return (Criteria) this;
        }

        public Criteria andWaysalesmanIn(List<String> values) {
            addCriterion("waysalesman in", values, "waysalesman");
            return (Criteria) this;
        }

        public Criteria andWaysalesmanNotIn(List<String> values) {
            addCriterion("waysalesman not in", values, "waysalesman");
            return (Criteria) this;
        }

        public Criteria andWaysalesmanBetween(String value1, String value2) {
            addCriterion("waysalesman between", value1, value2, "waysalesman");
            return (Criteria) this;
        }

        public Criteria andWaysalesmanNotBetween(String value1, String value2) {
            addCriterion("waysalesman not between", value1, value2, "waysalesman");
            return (Criteria) this;
        }

        public Criteria andInvitecodeIsNull() {
            addCriterion("invitecode is null");
            return (Criteria) this;
        }

        public Criteria andInvitecodeIsNotNull() {
            addCriterion("invitecode is not null");
            return (Criteria) this;
        }

        public Criteria andInvitecodeEqualTo(String value) {
            addCriterion("invitecode =", value, "invitecode");
            return (Criteria) this;
        }

        public Criteria andInvitecodeNotEqualTo(String value) {
            addCriterion("invitecode <>", value, "invitecode");
            return (Criteria) this;
        }

        public Criteria andInvitecodeGreaterThan(String value) {
            addCriterion("invitecode >", value, "invitecode");
            return (Criteria) this;
        }

        public Criteria andInvitecodeGreaterThanOrEqualTo(String value) {
            addCriterion("invitecode >=", value, "invitecode");
            return (Criteria) this;
        }

        public Criteria andInvitecodeLessThan(String value) {
            addCriterion("invitecode <", value, "invitecode");
            return (Criteria) this;
        }

        public Criteria andInvitecodeLessThanOrEqualTo(String value) {
            addCriterion("invitecode <=", value, "invitecode");
            return (Criteria) this;
        }

        public Criteria andInvitecodeLike(String value) {
            addCriterion("invitecode like", value, "invitecode");
            return (Criteria) this;
        }

        public Criteria andInvitecodeNotLike(String value) {
            addCriterion("invitecode not like", value, "invitecode");
            return (Criteria) this;
        }

        public Criteria andInvitecodeIn(List<String> values) {
            addCriterion("invitecode in", values, "invitecode");
            return (Criteria) this;
        }

        public Criteria andInvitecodeNotIn(List<String> values) {
            addCriterion("invitecode not in", values, "invitecode");
            return (Criteria) this;
        }

        public Criteria andInvitecodeBetween(String value1, String value2) {
            addCriterion("invitecode between", value1, value2, "invitecode");
            return (Criteria) this;
        }

        public Criteria andInvitecodeNotBetween(String value1, String value2) {
            addCriterion("invitecode not between", value1, value2, "invitecode");
            return (Criteria) this;
        }

        public Criteria andClerknameIsNull() {
            addCriterion("clerkname is null");
            return (Criteria) this;
        }

        public Criteria andClerknameIsNotNull() {
            addCriterion("clerkname is not null");
            return (Criteria) this;
        }

        public Criteria andClerknameEqualTo(String value) {
            addCriterion("clerkname =", value, "clerkname");
            return (Criteria) this;
        }

        public Criteria andClerknameNotEqualTo(String value) {
            addCriterion("clerkname <>", value, "clerkname");
            return (Criteria) this;
        }

        public Criteria andClerknameGreaterThan(String value) {
            addCriterion("clerkname >", value, "clerkname");
            return (Criteria) this;
        }

        public Criteria andClerknameGreaterThanOrEqualTo(String value) {
            addCriterion("clerkname >=", value, "clerkname");
            return (Criteria) this;
        }

        public Criteria andClerknameLessThan(String value) {
            addCriterion("clerkname <", value, "clerkname");
            return (Criteria) this;
        }

        public Criteria andClerknameLessThanOrEqualTo(String value) {
            addCriterion("clerkname <=", value, "clerkname");
            return (Criteria) this;
        }

        public Criteria andClerknameLike(String value) {
            addCriterion("clerkname like", value, "clerkname");
            return (Criteria) this;
        }

        public Criteria andClerknameNotLike(String value) {
            addCriterion("clerkname not like", value, "clerkname");
            return (Criteria) this;
        }

        public Criteria andClerknameIn(List<String> values) {
            addCriterion("clerkname in", values, "clerkname");
            return (Criteria) this;
        }

        public Criteria andClerknameNotIn(List<String> values) {
            addCriterion("clerkname not in", values, "clerkname");
            return (Criteria) this;
        }

        public Criteria andClerknameBetween(String value1, String value2) {
            addCriterion("clerkname between", value1, value2, "clerkname");
            return (Criteria) this;
        }

        public Criteria andClerknameNotBetween(String value1, String value2) {
            addCriterion("clerkname not between", value1, value2, "clerkname");
            return (Criteria) this;
        }

        public Criteria andLabelidIsNull() {
            addCriterion("labelid is null");
            return (Criteria) this;
        }

        public Criteria andLabelidIsNotNull() {
            addCriterion("labelid is not null");
            return (Criteria) this;
        }

        public Criteria andLabelidEqualTo(String value) {
            addCriterion("labelid =", value, "labelid");
            return (Criteria) this;
        }

        public Criteria andLabelidNotEqualTo(String value) {
            addCriterion("labelid <>", value, "labelid");
            return (Criteria) this;
        }

        public Criteria andLabelidGreaterThan(String value) {
            addCriterion("labelid >", value, "labelid");
            return (Criteria) this;
        }

        public Criteria andLabelidGreaterThanOrEqualTo(String value) {
            addCriterion("labelid >=", value, "labelid");
            return (Criteria) this;
        }

        public Criteria andLabelidLessThan(String value) {
            addCriterion("labelid <", value, "labelid");
            return (Criteria) this;
        }

        public Criteria andLabelidLessThanOrEqualTo(String value) {
            addCriterion("labelid <=", value, "labelid");
            return (Criteria) this;
        }

        public Criteria andLabelidLike(String value) {
            addCriterion("labelid like", value, "labelid");
            return (Criteria) this;
        }

        public Criteria andLabelidNotLike(String value) {
            addCriterion("labelid not like", value, "labelid");
            return (Criteria) this;
        }

        public Criteria andLabelidIn(List<String> values) {
            addCriterion("labelid in", values, "labelid");
            return (Criteria) this;
        }

        public Criteria andLabelidNotIn(List<String> values) {
            addCriterion("labelid not in", values, "labelid");
            return (Criteria) this;
        }

        public Criteria andLabelidBetween(String value1, String value2) {
            addCriterion("labelid between", value1, value2, "labelid");
            return (Criteria) this;
        }

        public Criteria andLabelidNotBetween(String value1, String value2) {
            addCriterion("labelid not between", value1, value2, "labelid");
            return (Criteria) this;
        }

        public Criteria andParentidIsNull() {
            addCriterion("parentid is null");
            return (Criteria) this;
        }

        public Criteria andParentidIsNotNull() {
            addCriterion("parentid is not null");
            return (Criteria) this;
        }

        public Criteria andParentidEqualTo(Long value) {
            addCriterion("parentid =", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotEqualTo(Long value) {
            addCriterion("parentid <>", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidGreaterThan(Long value) {
            addCriterion("parentid >", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidGreaterThanOrEqualTo(Long value) {
            addCriterion("parentid >=", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidLessThan(Long value) {
            addCriterion("parentid <", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidLessThanOrEqualTo(Long value) {
            addCriterion("parentid <=", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidIn(List<Long> values) {
            addCriterion("parentid in", values, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotIn(List<Long> values) {
            addCriterion("parentid not in", values, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidBetween(Long value1, Long value2) {
            addCriterion("parentid between", value1, value2, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotBetween(Long value1, Long value2) {
            addCriterion("parentid not between", value1, value2, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentnameIsNull() {
            addCriterion("parentname is null");
            return (Criteria) this;
        }

        public Criteria andParentnameIsNotNull() {
            addCriterion("parentname is not null");
            return (Criteria) this;
        }

        public Criteria andParentnameEqualTo(String value) {
            addCriterion("parentname =", value, "parentname");
            return (Criteria) this;
        }

        public Criteria andParentnameNotEqualTo(String value) {
            addCriterion("parentname <>", value, "parentname");
            return (Criteria) this;
        }

        public Criteria andParentnameGreaterThan(String value) {
            addCriterion("parentname >", value, "parentname");
            return (Criteria) this;
        }

        public Criteria andParentnameGreaterThanOrEqualTo(String value) {
            addCriterion("parentname >=", value, "parentname");
            return (Criteria) this;
        }

        public Criteria andParentnameLessThan(String value) {
            addCriterion("parentname <", value, "parentname");
            return (Criteria) this;
        }

        public Criteria andParentnameLessThanOrEqualTo(String value) {
            addCriterion("parentname <=", value, "parentname");
            return (Criteria) this;
        }

        public Criteria andParentnameLike(String value) {
            addCriterion("parentname like", value, "parentname");
            return (Criteria) this;
        }

        public Criteria andParentnameNotLike(String value) {
            addCriterion("parentname not like", value, "parentname");
            return (Criteria) this;
        }

        public Criteria andParentnameIn(List<String> values) {
            addCriterion("parentname in", values, "parentname");
            return (Criteria) this;
        }

        public Criteria andParentnameNotIn(List<String> values) {
            addCriterion("parentname not in", values, "parentname");
            return (Criteria) this;
        }

        public Criteria andParentnameBetween(String value1, String value2) {
            addCriterion("parentname between", value1, value2, "parentname");
            return (Criteria) this;
        }

        public Criteria andParentnameNotBetween(String value1, String value2) {
            addCriterion("parentname not between", value1, value2, "parentname");
            return (Criteria) this;
        }

        public Criteria andMenuIsNull() {
            addCriterion("menu is null");
            return (Criteria) this;
        }

        public Criteria andMenuIsNotNull() {
            addCriterion("menu is not null");
            return (Criteria) this;
        }

        public Criteria andMenuEqualTo(Object value) {
            addCriterion("menu =", value, "menu");
            return (Criteria) this;
        }

        public Criteria andMenuNotEqualTo(Object value) {
            addCriterion("menu <>", value, "menu");
            return (Criteria) this;
        }

        public Criteria andMenuGreaterThan(Object value) {
            addCriterion("menu >", value, "menu");
            return (Criteria) this;
        }

        public Criteria andMenuGreaterThanOrEqualTo(Object value) {
            addCriterion("menu >=", value, "menu");
            return (Criteria) this;
        }

        public Criteria andMenuLessThan(Object value) {
            addCriterion("menu <", value, "menu");
            return (Criteria) this;
        }

        public Criteria andMenuLessThanOrEqualTo(Object value) {
            addCriterion("menu <=", value, "menu");
            return (Criteria) this;
        }

        public Criteria andMenuIn(List<Object> values) {
            addCriterion("menu in", values, "menu");
            return (Criteria) this;
        }

        public Criteria andMenuNotIn(List<Object> values) {
            addCriterion("menu not in", values, "menu");
            return (Criteria) this;
        }

        public Criteria andMenuBetween(Object value1, Object value2) {
            addCriterion("menu between", value1, value2, "menu");
            return (Criteria) this;
        }

        public Criteria andMenuNotBetween(Object value1, Object value2) {
            addCriterion("menu not between", value1, value2, "menu");
            return (Criteria) this;
        }

        public Criteria andFlagIsNull() {
            addCriterion("flag is null");
            return (Criteria) this;
        }

        public Criteria andFlagIsNotNull() {
            addCriterion("flag is not null");
            return (Criteria) this;
        }

        public Criteria andFlagEqualTo(Boolean value) {
            addCriterion("flag =", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotEqualTo(Boolean value) {
            addCriterion("flag <>", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThan(Boolean value) {
            addCriterion("flag >", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThanOrEqualTo(Boolean value) {
            addCriterion("flag >=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThan(Boolean value) {
            addCriterion("flag <", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThanOrEqualTo(Boolean value) {
            addCriterion("flag <=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagIn(List<Boolean> values) {
            addCriterion("flag in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotIn(List<Boolean> values) {
            addCriterion("flag not in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagBetween(Boolean value1, Boolean value2) {
            addCriterion("flag between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotBetween(Boolean value1, Boolean value2) {
            addCriterion("flag not between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andQqIsNull() {
            addCriterion("qq is null");
            return (Criteria) this;
        }

        public Criteria andQqIsNotNull() {
            addCriterion("qq is not null");
            return (Criteria) this;
        }

        public Criteria andQqEqualTo(String value) {
            addCriterion("qq =", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotEqualTo(String value) {
            addCriterion("qq <>", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqGreaterThan(String value) {
            addCriterion("qq >", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqGreaterThanOrEqualTo(String value) {
            addCriterion("qq >=", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqLessThan(String value) {
            addCriterion("qq <", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqLessThanOrEqualTo(String value) {
            addCriterion("qq <=", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqLike(String value) {
            addCriterion("qq like", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotLike(String value) {
            addCriterion("qq not like", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqIn(List<String> values) {
            addCriterion("qq in", values, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotIn(List<String> values) {
            addCriterion("qq not in", values, "qq");
            return (Criteria) this;
        }

        public Criteria andQqBetween(String value1, String value2) {
            addCriterion("qq between", value1, value2, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotBetween(String value1, String value2) {
            addCriterion("qq not between", value1, value2, "qq");
            return (Criteria) this;
        }

        public Criteria andSexIsNull() {
            addCriterion("sex is null");
            return (Criteria) this;
        }

        public Criteria andSexIsNotNull() {
            addCriterion("sex is not null");
            return (Criteria) this;
        }

        public Criteria andSexEqualTo(String value) {
            addCriterion("sex =", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotEqualTo(String value) {
            addCriterion("sex <>", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThan(String value) {
            addCriterion("sex >", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThanOrEqualTo(String value) {
            addCriterion("sex >=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThan(String value) {
            addCriterion("sex <", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThanOrEqualTo(String value) {
            addCriterion("sex <=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLike(String value) {
            addCriterion("sex like", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotLike(String value) {
            addCriterion("sex not like", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexIn(List<String> values) {
            addCriterion("sex in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotIn(List<String> values) {
            addCriterion("sex not in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexBetween(String value1, String value2) {
            addCriterion("sex between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotBetween(String value1, String value2) {
            addCriterion("sex not between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andFaxesIsNull() {
            addCriterion("faxes is null");
            return (Criteria) this;
        }

        public Criteria andFaxesIsNotNull() {
            addCriterion("faxes is not null");
            return (Criteria) this;
        }

        public Criteria andFaxesEqualTo(String value) {
            addCriterion("faxes =", value, "faxes");
            return (Criteria) this;
        }

        public Criteria andFaxesNotEqualTo(String value) {
            addCriterion("faxes <>", value, "faxes");
            return (Criteria) this;
        }

        public Criteria andFaxesGreaterThan(String value) {
            addCriterion("faxes >", value, "faxes");
            return (Criteria) this;
        }

        public Criteria andFaxesGreaterThanOrEqualTo(String value) {
            addCriterion("faxes >=", value, "faxes");
            return (Criteria) this;
        }

        public Criteria andFaxesLessThan(String value) {
            addCriterion("faxes <", value, "faxes");
            return (Criteria) this;
        }

        public Criteria andFaxesLessThanOrEqualTo(String value) {
            addCriterion("faxes <=", value, "faxes");
            return (Criteria) this;
        }

        public Criteria andFaxesLike(String value) {
            addCriterion("faxes like", value, "faxes");
            return (Criteria) this;
        }

        public Criteria andFaxesNotLike(String value) {
            addCriterion("faxes not like", value, "faxes");
            return (Criteria) this;
        }

        public Criteria andFaxesIn(List<String> values) {
            addCriterion("faxes in", values, "faxes");
            return (Criteria) this;
        }

        public Criteria andFaxesNotIn(List<String> values) {
            addCriterion("faxes not in", values, "faxes");
            return (Criteria) this;
        }

        public Criteria andFaxesBetween(String value1, String value2) {
            addCriterion("faxes between", value1, value2, "faxes");
            return (Criteria) this;
        }

        public Criteria andFaxesNotBetween(String value1, String value2) {
            addCriterion("faxes not between", value1, value2, "faxes");
            return (Criteria) this;
        }

        public Criteria andTelephoneIsNull() {
            addCriterion("telephone is null");
            return (Criteria) this;
        }

        public Criteria andTelephoneIsNotNull() {
            addCriterion("telephone is not null");
            return (Criteria) this;
        }

        public Criteria andTelephoneEqualTo(String value) {
            addCriterion("telephone =", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotEqualTo(String value) {
            addCriterion("telephone <>", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneGreaterThan(String value) {
            addCriterion("telephone >", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneGreaterThanOrEqualTo(String value) {
            addCriterion("telephone >=", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLessThan(String value) {
            addCriterion("telephone <", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLessThanOrEqualTo(String value) {
            addCriterion("telephone <=", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLike(String value) {
            addCriterion("telephone like", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotLike(String value) {
            addCriterion("telephone not like", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneIn(List<String> values) {
            addCriterion("telephone in", values, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotIn(List<String> values) {
            addCriterion("telephone not in", values, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneBetween(String value1, String value2) {
            addCriterion("telephone between", value1, value2, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotBetween(String value1, String value2) {
            addCriterion("telephone not between", value1, value2, "telephone");
            return (Criteria) this;
        }

        public Criteria andHobbyIsNull() {
            addCriterion("hobby is null");
            return (Criteria) this;
        }

        public Criteria andHobbyIsNotNull() {
            addCriterion("hobby is not null");
            return (Criteria) this;
        }

        public Criteria andHobbyEqualTo(String value) {
            addCriterion("hobby =", value, "hobby");
            return (Criteria) this;
        }

        public Criteria andHobbyNotEqualTo(String value) {
            addCriterion("hobby <>", value, "hobby");
            return (Criteria) this;
        }

        public Criteria andHobbyGreaterThan(String value) {
            addCriterion("hobby >", value, "hobby");
            return (Criteria) this;
        }

        public Criteria andHobbyGreaterThanOrEqualTo(String value) {
            addCriterion("hobby >=", value, "hobby");
            return (Criteria) this;
        }

        public Criteria andHobbyLessThan(String value) {
            addCriterion("hobby <", value, "hobby");
            return (Criteria) this;
        }

        public Criteria andHobbyLessThanOrEqualTo(String value) {
            addCriterion("hobby <=", value, "hobby");
            return (Criteria) this;
        }

        public Criteria andHobbyLike(String value) {
            addCriterion("hobby like", value, "hobby");
            return (Criteria) this;
        }

        public Criteria andHobbyNotLike(String value) {
            addCriterion("hobby not like", value, "hobby");
            return (Criteria) this;
        }

        public Criteria andHobbyIn(List<String> values) {
            addCriterion("hobby in", values, "hobby");
            return (Criteria) this;
        }

        public Criteria andHobbyNotIn(List<String> values) {
            addCriterion("hobby not in", values, "hobby");
            return (Criteria) this;
        }

        public Criteria andHobbyBetween(String value1, String value2) {
            addCriterion("hobby between", value1, value2, "hobby");
            return (Criteria) this;
        }

        public Criteria andHobbyNotBetween(String value1, String value2) {
            addCriterion("hobby not between", value1, value2, "hobby");
            return (Criteria) this;
        }

        public Criteria andFaviconIsNull() {
            addCriterion("favicon is null");
            return (Criteria) this;
        }

        public Criteria andFaviconIsNotNull() {
            addCriterion("favicon is not null");
            return (Criteria) this;
        }

        public Criteria andFaviconEqualTo(String value) {
            addCriterion("favicon =", value, "favicon");
            return (Criteria) this;
        }

        public Criteria andFaviconNotEqualTo(String value) {
            addCriterion("favicon <>", value, "favicon");
            return (Criteria) this;
        }

        public Criteria andFaviconGreaterThan(String value) {
            addCriterion("favicon >", value, "favicon");
            return (Criteria) this;
        }

        public Criteria andFaviconGreaterThanOrEqualTo(String value) {
            addCriterion("favicon >=", value, "favicon");
            return (Criteria) this;
        }

        public Criteria andFaviconLessThan(String value) {
            addCriterion("favicon <", value, "favicon");
            return (Criteria) this;
        }

        public Criteria andFaviconLessThanOrEqualTo(String value) {
            addCriterion("favicon <=", value, "favicon");
            return (Criteria) this;
        }

        public Criteria andFaviconLike(String value) {
            addCriterion("favicon like", value, "favicon");
            return (Criteria) this;
        }

        public Criteria andFaviconNotLike(String value) {
            addCriterion("favicon not like", value, "favicon");
            return (Criteria) this;
        }

        public Criteria andFaviconIn(List<String> values) {
            addCriterion("favicon in", values, "favicon");
            return (Criteria) this;
        }

        public Criteria andFaviconNotIn(List<String> values) {
            addCriterion("favicon not in", values, "favicon");
            return (Criteria) this;
        }

        public Criteria andFaviconBetween(String value1, String value2) {
            addCriterion("favicon between", value1, value2, "favicon");
            return (Criteria) this;
        }

        public Criteria andFaviconNotBetween(String value1, String value2) {
            addCriterion("favicon not between", value1, value2, "favicon");
            return (Criteria) this;
        }

        public Criteria andSellerbanlanceIsNull() {
            addCriterion("sellerbanlance is null");
            return (Criteria) this;
        }

        public Criteria andSellerbanlanceIsNotNull() {
            addCriterion("sellerbanlance is not null");
            return (Criteria) this;
        }

        public Criteria andSellerbanlanceEqualTo(BigDecimal value) {
            addCriterion("sellerbanlance =", value, "sellerbanlance");
            return (Criteria) this;
        }

        public Criteria andSellerbanlanceNotEqualTo(BigDecimal value) {
            addCriterion("sellerbanlance <>", value, "sellerbanlance");
            return (Criteria) this;
        }

        public Criteria andSellerbanlanceGreaterThan(BigDecimal value) {
            addCriterion("sellerbanlance >", value, "sellerbanlance");
            return (Criteria) this;
        }

        public Criteria andSellerbanlanceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sellerbanlance >=", value, "sellerbanlance");
            return (Criteria) this;
        }

        public Criteria andSellerbanlanceLessThan(BigDecimal value) {
            addCriterion("sellerbanlance <", value, "sellerbanlance");
            return (Criteria) this;
        }

        public Criteria andSellerbanlanceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sellerbanlance <=", value, "sellerbanlance");
            return (Criteria) this;
        }

        public Criteria andSellerbanlanceIn(List<BigDecimal> values) {
            addCriterion("sellerbanlance in", values, "sellerbanlance");
            return (Criteria) this;
        }

        public Criteria andSellerbanlanceNotIn(List<BigDecimal> values) {
            addCriterion("sellerbanlance not in", values, "sellerbanlance");
            return (Criteria) this;
        }

        public Criteria andSellerbanlanceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sellerbanlance between", value1, value2, "sellerbanlance");
            return (Criteria) this;
        }

        public Criteria andSellerbanlanceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sellerbanlance not between", value1, value2, "sellerbanlance");
            return (Criteria) this;
        }

        public Criteria andSellerfreezebanlanceIsNull() {
            addCriterion("sellerfreezebanlance is null");
            return (Criteria) this;
        }

        public Criteria andSellerfreezebanlanceIsNotNull() {
            addCriterion("sellerfreezebanlance is not null");
            return (Criteria) this;
        }

        public Criteria andSellerfreezebanlanceEqualTo(BigDecimal value) {
            addCriterion("sellerfreezebanlance =", value, "sellerfreezebanlance");
            return (Criteria) this;
        }

        public Criteria andSellerfreezebanlanceNotEqualTo(BigDecimal value) {
            addCriterion("sellerfreezebanlance <>", value, "sellerfreezebanlance");
            return (Criteria) this;
        }

        public Criteria andSellerfreezebanlanceGreaterThan(BigDecimal value) {
            addCriterion("sellerfreezebanlance >", value, "sellerfreezebanlance");
            return (Criteria) this;
        }

        public Criteria andSellerfreezebanlanceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sellerfreezebanlance >=", value, "sellerfreezebanlance");
            return (Criteria) this;
        }

        public Criteria andSellerfreezebanlanceLessThan(BigDecimal value) {
            addCriterion("sellerfreezebanlance <", value, "sellerfreezebanlance");
            return (Criteria) this;
        }

        public Criteria andSellerfreezebanlanceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sellerfreezebanlance <=", value, "sellerfreezebanlance");
            return (Criteria) this;
        }

        public Criteria andSellerfreezebanlanceIn(List<BigDecimal> values) {
            addCriterion("sellerfreezebanlance in", values, "sellerfreezebanlance");
            return (Criteria) this;
        }

        public Criteria andSellerfreezebanlanceNotIn(List<BigDecimal> values) {
            addCriterion("sellerfreezebanlance not in", values, "sellerfreezebanlance");
            return (Criteria) this;
        }

        public Criteria andSellerfreezebanlanceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sellerfreezebanlance between", value1, value2, "sellerfreezebanlance");
            return (Criteria) this;
        }

        public Criteria andSellerfreezebanlanceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sellerfreezebanlance not between", value1, value2, "sellerfreezebanlance");
            return (Criteria) this;
        }

        public Criteria andCreditlimitIsNull() {
            addCriterion("creditlimit is null");
            return (Criteria) this;
        }

        public Criteria andCreditlimitIsNotNull() {
            addCriterion("creditlimit is not null");
            return (Criteria) this;
        }

        public Criteria andCreditlimitEqualTo(BigDecimal value) {
            addCriterion("creditlimit =", value, "creditlimit");
            return (Criteria) this;
        }

        public Criteria andCreditlimitNotEqualTo(BigDecimal value) {
            addCriterion("creditlimit <>", value, "creditlimit");
            return (Criteria) this;
        }

        public Criteria andCreditlimitGreaterThan(BigDecimal value) {
            addCriterion("creditlimit >", value, "creditlimit");
            return (Criteria) this;
        }

        public Criteria andCreditlimitGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("creditlimit >=", value, "creditlimit");
            return (Criteria) this;
        }

        public Criteria andCreditlimitLessThan(BigDecimal value) {
            addCriterion("creditlimit <", value, "creditlimit");
            return (Criteria) this;
        }

        public Criteria andCreditlimitLessThanOrEqualTo(BigDecimal value) {
            addCriterion("creditlimit <=", value, "creditlimit");
            return (Criteria) this;
        }

        public Criteria andCreditlimitIn(List<BigDecimal> values) {
            addCriterion("creditlimit in", values, "creditlimit");
            return (Criteria) this;
        }

        public Criteria andCreditlimitNotIn(List<BigDecimal> values) {
            addCriterion("creditlimit not in", values, "creditlimit");
            return (Criteria) this;
        }

        public Criteria andCreditlimitBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("creditlimit between", value1, value2, "creditlimit");
            return (Criteria) this;
        }

        public Criteria andCreditlimitNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("creditlimit not between", value1, value2, "creditlimit");
            return (Criteria) this;
        }

        public Criteria andUsedlimitIsNull() {
            addCriterion("usedlimit is null");
            return (Criteria) this;
        }

        public Criteria andUsedlimitIsNotNull() {
            addCriterion("usedlimit is not null");
            return (Criteria) this;
        }

        public Criteria andUsedlimitEqualTo(BigDecimal value) {
            addCriterion("usedlimit =", value, "usedlimit");
            return (Criteria) this;
        }

        public Criteria andUsedlimitNotEqualTo(BigDecimal value) {
            addCriterion("usedlimit <>", value, "usedlimit");
            return (Criteria) this;
        }

        public Criteria andUsedlimitGreaterThan(BigDecimal value) {
            addCriterion("usedlimit >", value, "usedlimit");
            return (Criteria) this;
        }

        public Criteria andUsedlimitGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("usedlimit >=", value, "usedlimit");
            return (Criteria) this;
        }

        public Criteria andUsedlimitLessThan(BigDecimal value) {
            addCriterion("usedlimit <", value, "usedlimit");
            return (Criteria) this;
        }

        public Criteria andUsedlimitLessThanOrEqualTo(BigDecimal value) {
            addCriterion("usedlimit <=", value, "usedlimit");
            return (Criteria) this;
        }

        public Criteria andUsedlimitIn(List<BigDecimal> values) {
            addCriterion("usedlimit in", values, "usedlimit");
            return (Criteria) this;
        }

        public Criteria andUsedlimitNotIn(List<BigDecimal> values) {
            addCriterion("usedlimit not in", values, "usedlimit");
            return (Criteria) this;
        }

        public Criteria andUsedlimitBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("usedlimit between", value1, value2, "usedlimit");
            return (Criteria) this;
        }

        public Criteria andUsedlimitNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("usedlimit not between", value1, value2, "usedlimit");
            return (Criteria) this;
        }

        public Criteria andAvailablelimitIsNull() {
            addCriterion("availablelimit is null");
            return (Criteria) this;
        }

        public Criteria andAvailablelimitIsNotNull() {
            addCriterion("availablelimit is not null");
            return (Criteria) this;
        }

        public Criteria andAvailablelimitEqualTo(BigDecimal value) {
            addCriterion("availablelimit =", value, "availablelimit");
            return (Criteria) this;
        }

        public Criteria andAvailablelimitNotEqualTo(BigDecimal value) {
            addCriterion("availablelimit <>", value, "availablelimit");
            return (Criteria) this;
        }

        public Criteria andAvailablelimitGreaterThan(BigDecimal value) {
            addCriterion("availablelimit >", value, "availablelimit");
            return (Criteria) this;
        }

        public Criteria andAvailablelimitGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("availablelimit >=", value, "availablelimit");
            return (Criteria) this;
        }

        public Criteria andAvailablelimitLessThan(BigDecimal value) {
            addCriterion("availablelimit <", value, "availablelimit");
            return (Criteria) this;
        }

        public Criteria andAvailablelimitLessThanOrEqualTo(BigDecimal value) {
            addCriterion("availablelimit <=", value, "availablelimit");
            return (Criteria) this;
        }

        public Criteria andAvailablelimitIn(List<BigDecimal> values) {
            addCriterion("availablelimit in", values, "availablelimit");
            return (Criteria) this;
        }

        public Criteria andAvailablelimitNotIn(List<BigDecimal> values) {
            addCriterion("availablelimit not in", values, "availablelimit");
            return (Criteria) this;
        }

        public Criteria andAvailablelimitBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("availablelimit between", value1, value2, "availablelimit");
            return (Criteria) this;
        }

        public Criteria andAvailablelimitNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("availablelimit not between", value1, value2, "availablelimit");
            return (Criteria) this;
        }

        public Criteria andMembersettingstateIsNull() {
            addCriterion("membersettingstate is null");
            return (Criteria) this;
        }

        public Criteria andMembersettingstateIsNotNull() {
            addCriterion("membersettingstate is not null");
            return (Criteria) this;
        }

        public Criteria andMembersettingstateEqualTo(Short value) {
            addCriterion("membersettingstate =", value, "membersettingstate");
            return (Criteria) this;
        }

        public Criteria andMembersettingstateNotEqualTo(Short value) {
            addCriterion("membersettingstate <>", value, "membersettingstate");
            return (Criteria) this;
        }

        public Criteria andMembersettingstateGreaterThan(Short value) {
            addCriterion("membersettingstate >", value, "membersettingstate");
            return (Criteria) this;
        }

        public Criteria andMembersettingstateGreaterThanOrEqualTo(Short value) {
            addCriterion("membersettingstate >=", value, "membersettingstate");
            return (Criteria) this;
        }

        public Criteria andMembersettingstateLessThan(Short value) {
            addCriterion("membersettingstate <", value, "membersettingstate");
            return (Criteria) this;
        }

        public Criteria andMembersettingstateLessThanOrEqualTo(Short value) {
            addCriterion("membersettingstate <=", value, "membersettingstate");
            return (Criteria) this;
        }

        public Criteria andMembersettingstateIn(List<Short> values) {
            addCriterion("membersettingstate in", values, "membersettingstate");
            return (Criteria) this;
        }

        public Criteria andMembersettingstateNotIn(List<Short> values) {
            addCriterion("membersettingstate not in", values, "membersettingstate");
            return (Criteria) this;
        }

        public Criteria andMembersettingstateBetween(Short value1, Short value2) {
            addCriterion("membersettingstate between", value1, value2, "membersettingstate");
            return (Criteria) this;
        }

        public Criteria andMembersettingstateNotBetween(Short value1, Short value2) {
            addCriterion("membersettingstate not between", value1, value2, "membersettingstate");
            return (Criteria) this;
        }

        public Criteria andBillmoneyIsNull() {
            addCriterion("billmoney is null");
            return (Criteria) this;
        }

        public Criteria andBillmoneyIsNotNull() {
            addCriterion("billmoney is not null");
            return (Criteria) this;
        }

        public Criteria andBillmoneyEqualTo(BigDecimal value) {
            addCriterion("billmoney =", value, "billmoney");
            return (Criteria) this;
        }

        public Criteria andBillmoneyNotEqualTo(BigDecimal value) {
            addCriterion("billmoney <>", value, "billmoney");
            return (Criteria) this;
        }

        public Criteria andBillmoneyGreaterThan(BigDecimal value) {
            addCriterion("billmoney >", value, "billmoney");
            return (Criteria) this;
        }

        public Criteria andBillmoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("billmoney >=", value, "billmoney");
            return (Criteria) this;
        }

        public Criteria andBillmoneyLessThan(BigDecimal value) {
            addCriterion("billmoney <", value, "billmoney");
            return (Criteria) this;
        }

        public Criteria andBillmoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("billmoney <=", value, "billmoney");
            return (Criteria) this;
        }

        public Criteria andBillmoneyIn(List<BigDecimal> values) {
            addCriterion("billmoney in", values, "billmoney");
            return (Criteria) this;
        }

        public Criteria andBillmoneyNotIn(List<BigDecimal> values) {
            addCriterion("billmoney not in", values, "billmoney");
            return (Criteria) this;
        }

        public Criteria andBillmoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("billmoney between", value1, value2, "billmoney");
            return (Criteria) this;
        }

        public Criteria andBillmoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("billmoney not between", value1, value2, "billmoney");
            return (Criteria) this;
        }

        public Criteria andEva1IsNull() {
            addCriterion("eva1 is null");
            return (Criteria) this;
        }

        public Criteria andEva1IsNotNull() {
            addCriterion("eva1 is not null");
            return (Criteria) this;
        }

        public Criteria andEva1EqualTo(BigDecimal value) {
            addCriterion("eva1 =", value, "eva1");
            return (Criteria) this;
        }

        public Criteria andEva1NotEqualTo(BigDecimal value) {
            addCriterion("eva1 <>", value, "eva1");
            return (Criteria) this;
        }

        public Criteria andEva1GreaterThan(BigDecimal value) {
            addCriterion("eva1 >", value, "eva1");
            return (Criteria) this;
        }

        public Criteria andEva1GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("eva1 >=", value, "eva1");
            return (Criteria) this;
        }

        public Criteria andEva1LessThan(BigDecimal value) {
            addCriterion("eva1 <", value, "eva1");
            return (Criteria) this;
        }

        public Criteria andEva1LessThanOrEqualTo(BigDecimal value) {
            addCriterion("eva1 <=", value, "eva1");
            return (Criteria) this;
        }

        public Criteria andEva1In(List<BigDecimal> values) {
            addCriterion("eva1 in", values, "eva1");
            return (Criteria) this;
        }

        public Criteria andEva1NotIn(List<BigDecimal> values) {
            addCriterion("eva1 not in", values, "eva1");
            return (Criteria) this;
        }

        public Criteria andEva1Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("eva1 between", value1, value2, "eva1");
            return (Criteria) this;
        }

        public Criteria andEva1NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("eva1 not between", value1, value2, "eva1");
            return (Criteria) this;
        }

        public Criteria andEva2IsNull() {
            addCriterion("eva2 is null");
            return (Criteria) this;
        }

        public Criteria andEva2IsNotNull() {
            addCriterion("eva2 is not null");
            return (Criteria) this;
        }

        public Criteria andEva2EqualTo(BigDecimal value) {
            addCriterion("eva2 =", value, "eva2");
            return (Criteria) this;
        }

        public Criteria andEva2NotEqualTo(BigDecimal value) {
            addCriterion("eva2 <>", value, "eva2");
            return (Criteria) this;
        }

        public Criteria andEva2GreaterThan(BigDecimal value) {
            addCriterion("eva2 >", value, "eva2");
            return (Criteria) this;
        }

        public Criteria andEva2GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("eva2 >=", value, "eva2");
            return (Criteria) this;
        }

        public Criteria andEva2LessThan(BigDecimal value) {
            addCriterion("eva2 <", value, "eva2");
            return (Criteria) this;
        }

        public Criteria andEva2LessThanOrEqualTo(BigDecimal value) {
            addCriterion("eva2 <=", value, "eva2");
            return (Criteria) this;
        }

        public Criteria andEva2In(List<BigDecimal> values) {
            addCriterion("eva2 in", values, "eva2");
            return (Criteria) this;
        }

        public Criteria andEva2NotIn(List<BigDecimal> values) {
            addCriterion("eva2 not in", values, "eva2");
            return (Criteria) this;
        }

        public Criteria andEva2Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("eva2 between", value1, value2, "eva2");
            return (Criteria) this;
        }

        public Criteria andEva2NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("eva2 not between", value1, value2, "eva2");
            return (Criteria) this;
        }

        public Criteria andEva3IsNull() {
            addCriterion("eva3 is null");
            return (Criteria) this;
        }

        public Criteria andEva3IsNotNull() {
            addCriterion("eva3 is not null");
            return (Criteria) this;
        }

        public Criteria andEva3EqualTo(BigDecimal value) {
            addCriterion("eva3 =", value, "eva3");
            return (Criteria) this;
        }

        public Criteria andEva3NotEqualTo(BigDecimal value) {
            addCriterion("eva3 <>", value, "eva3");
            return (Criteria) this;
        }

        public Criteria andEva3GreaterThan(BigDecimal value) {
            addCriterion("eva3 >", value, "eva3");
            return (Criteria) this;
        }

        public Criteria andEva3GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("eva3 >=", value, "eva3");
            return (Criteria) this;
        }

        public Criteria andEva3LessThan(BigDecimal value) {
            addCriterion("eva3 <", value, "eva3");
            return (Criteria) this;
        }

        public Criteria andEva3LessThanOrEqualTo(BigDecimal value) {
            addCriterion("eva3 <=", value, "eva3");
            return (Criteria) this;
        }

        public Criteria andEva3In(List<BigDecimal> values) {
            addCriterion("eva3 in", values, "eva3");
            return (Criteria) this;
        }

        public Criteria andEva3NotIn(List<BigDecimal> values) {
            addCriterion("eva3 not in", values, "eva3");
            return (Criteria) this;
        }

        public Criteria andEva3Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("eva3 between", value1, value2, "eva3");
            return (Criteria) this;
        }

        public Criteria andEva3NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("eva3 not between", value1, value2, "eva3");
            return (Criteria) this;
        }

        public Criteria andSellergroupidIsNull() {
            addCriterion("sellergroupid is null");
            return (Criteria) this;
        }

        public Criteria andSellergroupidIsNotNull() {
            addCriterion("sellergroupid is not null");
            return (Criteria) this;
        }

        public Criteria andSellergroupidEqualTo(Long value) {
            addCriterion("sellergroupid =", value, "sellergroupid");
            return (Criteria) this;
        }

        public Criteria andSellergroupidNotEqualTo(Long value) {
            addCriterion("sellergroupid <>", value, "sellergroupid");
            return (Criteria) this;
        }

        public Criteria andSellergroupidGreaterThan(Long value) {
            addCriterion("sellergroupid >", value, "sellergroupid");
            return (Criteria) this;
        }

        public Criteria andSellergroupidGreaterThanOrEqualTo(Long value) {
            addCriterion("sellergroupid >=", value, "sellergroupid");
            return (Criteria) this;
        }

        public Criteria andSellergroupidLessThan(Long value) {
            addCriterion("sellergroupid <", value, "sellergroupid");
            return (Criteria) this;
        }

        public Criteria andSellergroupidLessThanOrEqualTo(Long value) {
            addCriterion("sellergroupid <=", value, "sellergroupid");
            return (Criteria) this;
        }

        public Criteria andSellergroupidIn(List<Long> values) {
            addCriterion("sellergroupid in", values, "sellergroupid");
            return (Criteria) this;
        }

        public Criteria andSellergroupidNotIn(List<Long> values) {
            addCriterion("sellergroupid not in", values, "sellergroupid");
            return (Criteria) this;
        }

        public Criteria andSellergroupidBetween(Long value1, Long value2) {
            addCriterion("sellergroupid between", value1, value2, "sellergroupid");
            return (Criteria) this;
        }

        public Criteria andSellergroupidNotBetween(Long value1, Long value2) {
            addCriterion("sellergroupid not between", value1, value2, "sellergroupid");
            return (Criteria) this;
        }

        public Criteria andAvailableintegralIsNull() {
            addCriterion("availableintegral is null");
            return (Criteria) this;
        }

        public Criteria andAvailableintegralIsNotNull() {
            addCriterion("availableintegral is not null");
            return (Criteria) this;
        }

        public Criteria andAvailableintegralEqualTo(BigDecimal value) {
            addCriterion("availableintegral =", value, "availableintegral");
            return (Criteria) this;
        }

        public Criteria andAvailableintegralNotEqualTo(BigDecimal value) {
            addCriterion("availableintegral <>", value, "availableintegral");
            return (Criteria) this;
        }

        public Criteria andAvailableintegralGreaterThan(BigDecimal value) {
            addCriterion("availableintegral >", value, "availableintegral");
            return (Criteria) this;
        }

        public Criteria andAvailableintegralGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("availableintegral >=", value, "availableintegral");
            return (Criteria) this;
        }

        public Criteria andAvailableintegralLessThan(BigDecimal value) {
            addCriterion("availableintegral <", value, "availableintegral");
            return (Criteria) this;
        }

        public Criteria andAvailableintegralLessThanOrEqualTo(BigDecimal value) {
            addCriterion("availableintegral <=", value, "availableintegral");
            return (Criteria) this;
        }

        public Criteria andAvailableintegralIn(List<BigDecimal> values) {
            addCriterion("availableintegral in", values, "availableintegral");
            return (Criteria) this;
        }

        public Criteria andAvailableintegralNotIn(List<BigDecimal> values) {
            addCriterion("availableintegral not in", values, "availableintegral");
            return (Criteria) this;
        }

        public Criteria andAvailableintegralBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("availableintegral between", value1, value2, "availableintegral");
            return (Criteria) this;
        }

        public Criteria andAvailableintegralNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("availableintegral not between", value1, value2, "availableintegral");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNull() {
            addCriterion("province is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNotNull() {
            addCriterion("province is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceEqualTo(String value) {
            addCriterion("province =", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotEqualTo(String value) {
            addCriterion("province <>", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThan(String value) {
            addCriterion("province >", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("province >=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThan(String value) {
            addCriterion("province <", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThanOrEqualTo(String value) {
            addCriterion("province <=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLike(String value) {
            addCriterion("province like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotLike(String value) {
            addCriterion("province not like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceIn(List<String> values) {
            addCriterion("province in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotIn(List<String> values) {
            addCriterion("province not in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceBetween(String value1, String value2) {
            addCriterion("province between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotBetween(String value1, String value2) {
            addCriterion("province not between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("city like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("city not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("city not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCitysmallIsNull() {
            addCriterion("citysmall is null");
            return (Criteria) this;
        }

        public Criteria andCitysmallIsNotNull() {
            addCriterion("citysmall is not null");
            return (Criteria) this;
        }

        public Criteria andCitysmallEqualTo(String value) {
            addCriterion("citysmall =", value, "citysmall");
            return (Criteria) this;
        }

        public Criteria andCitysmallNotEqualTo(String value) {
            addCriterion("citysmall <>", value, "citysmall");
            return (Criteria) this;
        }

        public Criteria andCitysmallGreaterThan(String value) {
            addCriterion("citysmall >", value, "citysmall");
            return (Criteria) this;
        }

        public Criteria andCitysmallGreaterThanOrEqualTo(String value) {
            addCriterion("citysmall >=", value, "citysmall");
            return (Criteria) this;
        }

        public Criteria andCitysmallLessThan(String value) {
            addCriterion("citysmall <", value, "citysmall");
            return (Criteria) this;
        }

        public Criteria andCitysmallLessThanOrEqualTo(String value) {
            addCriterion("citysmall <=", value, "citysmall");
            return (Criteria) this;
        }

        public Criteria andCitysmallLike(String value) {
            addCriterion("citysmall like", value, "citysmall");
            return (Criteria) this;
        }

        public Criteria andCitysmallNotLike(String value) {
            addCriterion("citysmall not like", value, "citysmall");
            return (Criteria) this;
        }

        public Criteria andCitysmallIn(List<String> values) {
            addCriterion("citysmall in", values, "citysmall");
            return (Criteria) this;
        }

        public Criteria andCitysmallNotIn(List<String> values) {
            addCriterion("citysmall not in", values, "citysmall");
            return (Criteria) this;
        }

        public Criteria andCitysmallBetween(String value1, String value2) {
            addCriterion("citysmall between", value1, value2, "citysmall");
            return (Criteria) this;
        }

        public Criteria andCitysmallNotBetween(String value1, String value2) {
            addCriterion("citysmall not between", value1, value2, "citysmall");
            return (Criteria) this;
        }

        public Criteria andCreditstateIsNull() {
            addCriterion("creditstate is null");
            return (Criteria) this;
        }

        public Criteria andCreditstateIsNotNull() {
            addCriterion("creditstate is not null");
            return (Criteria) this;
        }

        public Criteria andCreditstateEqualTo(Short value) {
            addCriterion("creditstate =", value, "creditstate");
            return (Criteria) this;
        }

        public Criteria andCreditstateNotEqualTo(Short value) {
            addCriterion("creditstate <>", value, "creditstate");
            return (Criteria) this;
        }

        public Criteria andCreditstateGreaterThan(Short value) {
            addCriterion("creditstate >", value, "creditstate");
            return (Criteria) this;
        }

        public Criteria andCreditstateGreaterThanOrEqualTo(Short value) {
            addCriterion("creditstate >=", value, "creditstate");
            return (Criteria) this;
        }

        public Criteria andCreditstateLessThan(Short value) {
            addCriterion("creditstate <", value, "creditstate");
            return (Criteria) this;
        }

        public Criteria andCreditstateLessThanOrEqualTo(Short value) {
            addCriterion("creditstate <=", value, "creditstate");
            return (Criteria) this;
        }

        public Criteria andCreditstateIn(List<Short> values) {
            addCriterion("creditstate in", values, "creditstate");
            return (Criteria) this;
        }

        public Criteria andCreditstateNotIn(List<Short> values) {
            addCriterion("creditstate not in", values, "creditstate");
            return (Criteria) this;
        }

        public Criteria andCreditstateBetween(Short value1, Short value2) {
            addCriterion("creditstate between", value1, value2, "creditstate");
            return (Criteria) this;
        }

        public Criteria andCreditstateNotBetween(Short value1, Short value2) {
            addCriterion("creditstate not between", value1, value2, "creditstate");
            return (Criteria) this;
        }

        public Criteria andGoodsbanlanceIsNull() {
            addCriterion("goodsbanlance is null");
            return (Criteria) this;
        }

        public Criteria andGoodsbanlanceIsNotNull() {
            addCriterion("goodsbanlance is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsbanlanceEqualTo(BigDecimal value) {
            addCriterion("goodsbanlance =", value, "goodsbanlance");
            return (Criteria) this;
        }

        public Criteria andGoodsbanlanceNotEqualTo(BigDecimal value) {
            addCriterion("goodsbanlance <>", value, "goodsbanlance");
            return (Criteria) this;
        }

        public Criteria andGoodsbanlanceGreaterThan(BigDecimal value) {
            addCriterion("goodsbanlance >", value, "goodsbanlance");
            return (Criteria) this;
        }

        public Criteria andGoodsbanlanceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("goodsbanlance >=", value, "goodsbanlance");
            return (Criteria) this;
        }

        public Criteria andGoodsbanlanceLessThan(BigDecimal value) {
            addCriterion("goodsbanlance <", value, "goodsbanlance");
            return (Criteria) this;
        }

        public Criteria andGoodsbanlanceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("goodsbanlance <=", value, "goodsbanlance");
            return (Criteria) this;
        }

        public Criteria andGoodsbanlanceIn(List<BigDecimal> values) {
            addCriterion("goodsbanlance in", values, "goodsbanlance");
            return (Criteria) this;
        }

        public Criteria andGoodsbanlanceNotIn(List<BigDecimal> values) {
            addCriterion("goodsbanlance not in", values, "goodsbanlance");
            return (Criteria) this;
        }

        public Criteria andGoodsbanlanceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("goodsbanlance between", value1, value2, "goodsbanlance");
            return (Criteria) this;
        }

        public Criteria andGoodsbanlanceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("goodsbanlance not between", value1, value2, "goodsbanlance");
            return (Criteria) this;
        }

        public Criteria andServicesIsNull() {
            addCriterion("service is null");
            return (Criteria) this;
        }

        public Criteria andServicesIsNotNull() {
            addCriterion("service is not null");
            return (Criteria) this;
        }

        public Criteria andServicesEqualTo(Boolean value) {
            addCriterion("service =", value, "service");
            return (Criteria) this;
        }

        public Criteria andServicesNotEqualTo(Boolean value) {
            addCriterion("service <>", value, "service");
            return (Criteria) this;
        }

        public Criteria andServicesGreaterThan(Boolean value) {
            addCriterion("service >", value, "service");
            return (Criteria) this;
        }

        public Criteria andServicesGreaterThanOrEqualTo(Boolean value) {
            addCriterion("service >=", value, "service");
            return (Criteria) this;
        }

        public Criteria andServicesLessThan(Boolean value) {
            addCriterion("service <", value, "service");
            return (Criteria) this;
        }

        public Criteria andServicesLessThanOrEqualTo(Boolean value) {
            addCriterion("service <=", value, "service");
            return (Criteria) this;
        }

        public Criteria andServicesIn(List<Boolean> values) {
            addCriterion("service in", values, "service");
            return (Criteria) this;
        }

        public Criteria andServicesNotIn(List<Boolean> values) {
            addCriterion("service not in", values, "service");
            return (Criteria) this;
        }

        public Criteria andServicesBetween(Boolean value1, Boolean value2) {
            addCriterion("service between", value1, value2, "service");
            return (Criteria) this;
        }

        public Criteria andServicesNotBetween(Boolean value1, Boolean value2) {
            addCriterion("service not between", value1, value2, "service");
            return (Criteria) this;
        }

        public Criteria andIsbuyIsNull() {
            addCriterion("isbuy is null");
            return (Criteria) this;
        }

        public Criteria andIsbuyIsNotNull() {
            addCriterion("isbuy is not null");
            return (Criteria) this;
        }

        public Criteria andIsbuyEqualTo(Short value) {
            addCriterion("isbuy =", value, "isbuy");
            return (Criteria) this;
        }

        public Criteria andIsbuyNotEqualTo(Short value) {
            addCriterion("isbuy <>", value, "isbuy");
            return (Criteria) this;
        }

        public Criteria andIsbuyGreaterThan(Short value) {
            addCriterion("isbuy >", value, "isbuy");
            return (Criteria) this;
        }

        public Criteria andIsbuyGreaterThanOrEqualTo(Short value) {
            addCriterion("isbuy >=", value, "isbuy");
            return (Criteria) this;
        }

        public Criteria andIsbuyLessThan(Short value) {
            addCriterion("isbuy <", value, "isbuy");
            return (Criteria) this;
        }

        public Criteria andIsbuyLessThanOrEqualTo(Short value) {
            addCriterion("isbuy <=", value, "isbuy");
            return (Criteria) this;
        }

        public Criteria andIsbuyIn(List<Short> values) {
            addCriterion("isbuy in", values, "isbuy");
            return (Criteria) this;
        }

        public Criteria andIsbuyNotIn(List<Short> values) {
            addCriterion("isbuy not in", values, "isbuy");
            return (Criteria) this;
        }

        public Criteria andIsbuyBetween(Short value1, Short value2) {
            addCriterion("isbuy between", value1, value2, "isbuy");
            return (Criteria) this;
        }

        public Criteria andIsbuyNotBetween(Short value1, Short value2) {
            addCriterion("isbuy not between", value1, value2, "isbuy");
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