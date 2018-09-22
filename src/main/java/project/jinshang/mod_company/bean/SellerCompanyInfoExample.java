package project.jinshang.mod_company.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SellerCompanyInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SellerCompanyInfoExample() {
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

        public Criteria andCompanynameIsNull() {
            addCriterion("companyname is null");
            return (Criteria) this;
        }

        public Criteria andCompanynameIsNotNull() {
            addCriterion("companyname is not null");
            return (Criteria) this;
        }

        public Criteria andCompanynameEqualTo(String value) {
            addCriterion("companyname =", value, "companyname");
            return (Criteria) this;
        }

        public Criteria andCompanynameNotEqualTo(String value) {
            addCriterion("companyname <>", value, "companyname");
            return (Criteria) this;
        }

        public Criteria andCompanynameGreaterThan(String value) {
            addCriterion("companyname >", value, "companyname");
            return (Criteria) this;
        }

        public Criteria andCompanynameGreaterThanOrEqualTo(String value) {
            addCriterion("companyname >=", value, "companyname");
            return (Criteria) this;
        }

        public Criteria andCompanynameLessThan(String value) {
            addCriterion("companyname <", value, "companyname");
            return (Criteria) this;
        }

        public Criteria andCompanynameLessThanOrEqualTo(String value) {
            addCriterion("companyname <=", value, "companyname");
            return (Criteria) this;
        }

        public Criteria andCompanynameLike(String value) {
            addCriterion("companyname like", value, "companyname");
            return (Criteria) this;
        }

        public Criteria andCompanynameNotLike(String value) {
            addCriterion("companyname not like", value, "companyname");
            return (Criteria) this;
        }

        public Criteria andCompanynameIn(List<String> values) {
            addCriterion("companyname in", values, "companyname");
            return (Criteria) this;
        }

        public Criteria andCompanynameNotIn(List<String> values) {
            addCriterion("companyname not in", values, "companyname");
            return (Criteria) this;
        }

        public Criteria andCompanynameBetween(String value1, String value2) {
            addCriterion("companyname between", value1, value2, "companyname");
            return (Criteria) this;
        }

        public Criteria andCompanynameNotBetween(String value1, String value2) {
            addCriterion("companyname not between", value1, value2, "companyname");
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

        public Criteria andCompanytelIsNull() {
            addCriterion("companytel is null");
            return (Criteria) this;
        }

        public Criteria andCompanytelIsNotNull() {
            addCriterion("companytel is not null");
            return (Criteria) this;
        }

        public Criteria andCompanytelEqualTo(String value) {
            addCriterion("companytel =", value, "companytel");
            return (Criteria) this;
        }

        public Criteria andCompanytelNotEqualTo(String value) {
            addCriterion("companytel <>", value, "companytel");
            return (Criteria) this;
        }

        public Criteria andCompanytelGreaterThan(String value) {
            addCriterion("companytel >", value, "companytel");
            return (Criteria) this;
        }

        public Criteria andCompanytelGreaterThanOrEqualTo(String value) {
            addCriterion("companytel >=", value, "companytel");
            return (Criteria) this;
        }

        public Criteria andCompanytelLessThan(String value) {
            addCriterion("companytel <", value, "companytel");
            return (Criteria) this;
        }

        public Criteria andCompanytelLessThanOrEqualTo(String value) {
            addCriterion("companytel <=", value, "companytel");
            return (Criteria) this;
        }

        public Criteria andCompanytelLike(String value) {
            addCriterion("companytel like", value, "companytel");
            return (Criteria) this;
        }

        public Criteria andCompanytelNotLike(String value) {
            addCriterion("companytel not like", value, "companytel");
            return (Criteria) this;
        }

        public Criteria andCompanytelIn(List<String> values) {
            addCriterion("companytel in", values, "companytel");
            return (Criteria) this;
        }

        public Criteria andCompanytelNotIn(List<String> values) {
            addCriterion("companytel not in", values, "companytel");
            return (Criteria) this;
        }

        public Criteria andCompanytelBetween(String value1, String value2) {
            addCriterion("companytel between", value1, value2, "companytel");
            return (Criteria) this;
        }

        public Criteria andCompanytelNotBetween(String value1, String value2) {
            addCriterion("companytel not between", value1, value2, "companytel");
            return (Criteria) this;
        }

        public Criteria andLinkmanIsNull() {
            addCriterion("linkman is null");
            return (Criteria) this;
        }

        public Criteria andLinkmanIsNotNull() {
            addCriterion("linkman is not null");
            return (Criteria) this;
        }

        public Criteria andLinkmanEqualTo(String value) {
            addCriterion("linkman =", value, "linkman");
            return (Criteria) this;
        }

        public Criteria andLinkmanNotEqualTo(String value) {
            addCriterion("linkman <>", value, "linkman");
            return (Criteria) this;
        }

        public Criteria andLinkmanGreaterThan(String value) {
            addCriterion("linkman >", value, "linkman");
            return (Criteria) this;
        }

        public Criteria andLinkmanGreaterThanOrEqualTo(String value) {
            addCriterion("linkman >=", value, "linkman");
            return (Criteria) this;
        }

        public Criteria andLinkmanLessThan(String value) {
            addCriterion("linkman <", value, "linkman");
            return (Criteria) this;
        }

        public Criteria andLinkmanLessThanOrEqualTo(String value) {
            addCriterion("linkman <=", value, "linkman");
            return (Criteria) this;
        }

        public Criteria andLinkmanLike(String value) {
            addCriterion("linkman like", value, "linkman");
            return (Criteria) this;
        }

        public Criteria andLinkmanNotLike(String value) {
            addCriterion("linkman not like", value, "linkman");
            return (Criteria) this;
        }

        public Criteria andLinkmanIn(List<String> values) {
            addCriterion("linkman in", values, "linkman");
            return (Criteria) this;
        }

        public Criteria andLinkmanNotIn(List<String> values) {
            addCriterion("linkman not in", values, "linkman");
            return (Criteria) this;
        }

        public Criteria andLinkmanBetween(String value1, String value2) {
            addCriterion("linkman between", value1, value2, "linkman");
            return (Criteria) this;
        }

        public Criteria andLinkmanNotBetween(String value1, String value2) {
            addCriterion("linkman not between", value1, value2, "linkman");
            return (Criteria) this;
        }

        public Criteria andLinkmantelIsNull() {
            addCriterion("linkmantel is null");
            return (Criteria) this;
        }

        public Criteria andLinkmantelIsNotNull() {
            addCriterion("linkmantel is not null");
            return (Criteria) this;
        }

        public Criteria andLinkmantelEqualTo(String value) {
            addCriterion("linkmantel =", value, "linkmantel");
            return (Criteria) this;
        }

        public Criteria andLinkmantelNotEqualTo(String value) {
            addCriterion("linkmantel <>", value, "linkmantel");
            return (Criteria) this;
        }

        public Criteria andLinkmantelGreaterThan(String value) {
            addCriterion("linkmantel >", value, "linkmantel");
            return (Criteria) this;
        }

        public Criteria andLinkmantelGreaterThanOrEqualTo(String value) {
            addCriterion("linkmantel >=", value, "linkmantel");
            return (Criteria) this;
        }

        public Criteria andLinkmantelLessThan(String value) {
            addCriterion("linkmantel <", value, "linkmantel");
            return (Criteria) this;
        }

        public Criteria andLinkmantelLessThanOrEqualTo(String value) {
            addCriterion("linkmantel <=", value, "linkmantel");
            return (Criteria) this;
        }

        public Criteria andLinkmantelLike(String value) {
            addCriterion("linkmantel like", value, "linkmantel");
            return (Criteria) this;
        }

        public Criteria andLinkmantelNotLike(String value) {
            addCriterion("linkmantel not like", value, "linkmantel");
            return (Criteria) this;
        }

        public Criteria andLinkmantelIn(List<String> values) {
            addCriterion("linkmantel in", values, "linkmantel");
            return (Criteria) this;
        }

        public Criteria andLinkmantelNotIn(List<String> values) {
            addCriterion("linkmantel not in", values, "linkmantel");
            return (Criteria) this;
        }

        public Criteria andLinkmantelBetween(String value1, String value2) {
            addCriterion("linkmantel between", value1, value2, "linkmantel");
            return (Criteria) this;
        }

        public Criteria andLinkmantelNotBetween(String value1, String value2) {
            addCriterion("linkmantel not between", value1, value2, "linkmantel");
            return (Criteria) this;
        }

        public Criteria andEmployeenumIsNull() {
            addCriterion("employeenum is null");
            return (Criteria) this;
        }

        public Criteria andEmployeenumIsNotNull() {
            addCriterion("employeenum is not null");
            return (Criteria) this;
        }

        public Criteria andEmployeenumEqualTo(Integer value) {
            addCriterion("employeenum =", value, "employeenum");
            return (Criteria) this;
        }

        public Criteria andEmployeenumNotEqualTo(Integer value) {
            addCriterion("employeenum <>", value, "employeenum");
            return (Criteria) this;
        }

        public Criteria andEmployeenumGreaterThan(Integer value) {
            addCriterion("employeenum >", value, "employeenum");
            return (Criteria) this;
        }

        public Criteria andEmployeenumGreaterThanOrEqualTo(Integer value) {
            addCriterion("employeenum >=", value, "employeenum");
            return (Criteria) this;
        }

        public Criteria andEmployeenumLessThan(Integer value) {
            addCriterion("employeenum <", value, "employeenum");
            return (Criteria) this;
        }

        public Criteria andEmployeenumLessThanOrEqualTo(Integer value) {
            addCriterion("employeenum <=", value, "employeenum");
            return (Criteria) this;
        }

        public Criteria andEmployeenumIn(List<Integer> values) {
            addCriterion("employeenum in", values, "employeenum");
            return (Criteria) this;
        }

        public Criteria andEmployeenumNotIn(List<Integer> values) {
            addCriterion("employeenum not in", values, "employeenum");
            return (Criteria) this;
        }

        public Criteria andEmployeenumBetween(Integer value1, Integer value2) {
            addCriterion("employeenum between", value1, value2, "employeenum");
            return (Criteria) this;
        }

        public Criteria andEmployeenumNotBetween(Integer value1, Integer value2) {
            addCriterion("employeenum not between", value1, value2, "employeenum");
            return (Criteria) this;
        }

        public Criteria andRegfoundIsNull() {
            addCriterion("regfound is null");
            return (Criteria) this;
        }

        public Criteria andRegfoundIsNotNull() {
            addCriterion("regfound is not null");
            return (Criteria) this;
        }

        public Criteria andRegfoundEqualTo(Integer value) {
            addCriterion("regfound =", value, "regfound");
            return (Criteria) this;
        }

        public Criteria andRegfoundNotEqualTo(Integer value) {
            addCriterion("regfound <>", value, "regfound");
            return (Criteria) this;
        }

        public Criteria andRegfoundGreaterThan(Integer value) {
            addCriterion("regfound >", value, "regfound");
            return (Criteria) this;
        }

        public Criteria andRegfoundGreaterThanOrEqualTo(Integer value) {
            addCriterion("regfound >=", value, "regfound");
            return (Criteria) this;
        }

        public Criteria andRegfoundLessThan(Integer value) {
            addCriterion("regfound <", value, "regfound");
            return (Criteria) this;
        }

        public Criteria andRegfoundLessThanOrEqualTo(Integer value) {
            addCriterion("regfound <=", value, "regfound");
            return (Criteria) this;
        }

        public Criteria andRegfoundIn(List<Integer> values) {
            addCriterion("regfound in", values, "regfound");
            return (Criteria) this;
        }

        public Criteria andRegfoundNotIn(List<Integer> values) {
            addCriterion("regfound not in", values, "regfound");
            return (Criteria) this;
        }

        public Criteria andRegfoundBetween(Integer value1, Integer value2) {
            addCriterion("regfound between", value1, value2, "regfound");
            return (Criteria) this;
        }

        public Criteria andRegfoundNotBetween(Integer value1, Integer value2) {
            addCriterion("regfound not between", value1, value2, "regfound");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencenumberIsNull() {
            addCriterion("businesslicencenumber is null");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencenumberIsNotNull() {
            addCriterion("businesslicencenumber is not null");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencenumberEqualTo(String value) {
            addCriterion("businesslicencenumber =", value, "businesslicencenumber");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencenumberNotEqualTo(String value) {
            addCriterion("businesslicencenumber <>", value, "businesslicencenumber");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencenumberGreaterThan(String value) {
            addCriterion("businesslicencenumber >", value, "businesslicencenumber");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencenumberGreaterThanOrEqualTo(String value) {
            addCriterion("businesslicencenumber >=", value, "businesslicencenumber");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencenumberLessThan(String value) {
            addCriterion("businesslicencenumber <", value, "businesslicencenumber");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencenumberLessThanOrEqualTo(String value) {
            addCriterion("businesslicencenumber <=", value, "businesslicencenumber");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencenumberLike(String value) {
            addCriterion("businesslicencenumber like", value, "businesslicencenumber");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencenumberNotLike(String value) {
            addCriterion("businesslicencenumber not like", value, "businesslicencenumber");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencenumberIn(List<String> values) {
            addCriterion("businesslicencenumber in", values, "businesslicencenumber");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencenumberNotIn(List<String> values) {
            addCriterion("businesslicencenumber not in", values, "businesslicencenumber");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencenumberBetween(String value1, String value2) {
            addCriterion("businesslicencenumber between", value1, value2, "businesslicencenumber");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencenumberNotBetween(String value1, String value2) {
            addCriterion("businesslicencenumber not between", value1, value2, "businesslicencenumber");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencenumberphotoIsNull() {
            addCriterion("businesslicencenumberphoto is null");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencenumberphotoIsNotNull() {
            addCriterion("businesslicencenumberphoto is not null");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencenumberphotoEqualTo(String value) {
            addCriterion("businesslicencenumberphoto =", value, "businesslicencenumberphoto");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencenumberphotoNotEqualTo(String value) {
            addCriterion("businesslicencenumberphoto <>", value, "businesslicencenumberphoto");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencenumberphotoGreaterThan(String value) {
            addCriterion("businesslicencenumberphoto >", value, "businesslicencenumberphoto");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencenumberphotoGreaterThanOrEqualTo(String value) {
            addCriterion("businesslicencenumberphoto >=", value, "businesslicencenumberphoto");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencenumberphotoLessThan(String value) {
            addCriterion("businesslicencenumberphoto <", value, "businesslicencenumberphoto");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencenumberphotoLessThanOrEqualTo(String value) {
            addCriterion("businesslicencenumberphoto <=", value, "businesslicencenumberphoto");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencenumberphotoLike(String value) {
            addCriterion("businesslicencenumberphoto like", value, "businesslicencenumberphoto");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencenumberphotoNotLike(String value) {
            addCriterion("businesslicencenumberphoto not like", value, "businesslicencenumberphoto");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencenumberphotoIn(List<String> values) {
            addCriterion("businesslicencenumberphoto in", values, "businesslicencenumberphoto");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencenumberphotoNotIn(List<String> values) {
            addCriterion("businesslicencenumberphoto not in", values, "businesslicencenumberphoto");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencenumberphotoBetween(String value1, String value2) {
            addCriterion("businesslicencenumberphoto between", value1, value2, "businesslicencenumberphoto");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencenumberphotoNotBetween(String value1, String value2) {
            addCriterion("businesslicencenumberphoto not between", value1, value2, "businesslicencenumberphoto");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencestartIsNull() {
            addCriterion("businesslicencestart is null");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencestartIsNotNull() {
            addCriterion("businesslicencestart is not null");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencestartEqualTo(Date value) {
            addCriterion("businesslicencestart =", value, "businesslicencestart");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencestartNotEqualTo(Date value) {
            addCriterion("businesslicencestart <>", value, "businesslicencestart");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencestartGreaterThan(Date value) {
            addCriterion("businesslicencestart >", value, "businesslicencestart");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencestartGreaterThanOrEqualTo(Date value) {
            addCriterion("businesslicencestart >=", value, "businesslicencestart");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencestartLessThan(Date value) {
            addCriterion("businesslicencestart <", value, "businesslicencestart");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencestartLessThanOrEqualTo(Date value) {
            addCriterion("businesslicencestart <=", value, "businesslicencestart");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencestartIn(List<Date> values) {
            addCriterion("businesslicencestart in", values, "businesslicencestart");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencestartNotIn(List<Date> values) {
            addCriterion("businesslicencestart not in", values, "businesslicencestart");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencestartBetween(Date value1, Date value2) {
            addCriterion("businesslicencestart between", value1, value2, "businesslicencestart");
            return (Criteria) this;
        }

        public Criteria andBusinesslicencestartNotBetween(Date value1, Date value2) {
            addCriterion("businesslicencestart not between", value1, value2, "businesslicencestart");
            return (Criteria) this;
        }

        public Criteria andBusinesslicenceendIsNull() {
            addCriterion("businesslicenceend is null");
            return (Criteria) this;
        }

        public Criteria andBusinesslicenceendIsNotNull() {
            addCriterion("businesslicenceend is not null");
            return (Criteria) this;
        }

        public Criteria andBusinesslicenceendEqualTo(Date value) {
            addCriterion("businesslicenceend =", value, "businesslicenceend");
            return (Criteria) this;
        }

        public Criteria andBusinesslicenceendNotEqualTo(Date value) {
            addCriterion("businesslicenceend <>", value, "businesslicenceend");
            return (Criteria) this;
        }

        public Criteria andBusinesslicenceendGreaterThan(Date value) {
            addCriterion("businesslicenceend >", value, "businesslicenceend");
            return (Criteria) this;
        }

        public Criteria andBusinesslicenceendGreaterThanOrEqualTo(Date value) {
            addCriterion("businesslicenceend >=", value, "businesslicenceend");
            return (Criteria) this;
        }

        public Criteria andBusinesslicenceendLessThan(Date value) {
            addCriterion("businesslicenceend <", value, "businesslicenceend");
            return (Criteria) this;
        }

        public Criteria andBusinesslicenceendLessThanOrEqualTo(Date value) {
            addCriterion("businesslicenceend <=", value, "businesslicenceend");
            return (Criteria) this;
        }

        public Criteria andBusinesslicenceendIn(List<Date> values) {
            addCriterion("businesslicenceend in", values, "businesslicenceend");
            return (Criteria) this;
        }

        public Criteria andBusinesslicenceendNotIn(List<Date> values) {
            addCriterion("businesslicenceend not in", values, "businesslicenceend");
            return (Criteria) this;
        }

        public Criteria andBusinesslicenceendBetween(Date value1, Date value2) {
            addCriterion("businesslicenceend between", value1, value2, "businesslicenceend");
            return (Criteria) this;
        }

        public Criteria andBusinesslicenceendNotBetween(Date value1, Date value2) {
            addCriterion("businesslicenceend not between", value1, value2, "businesslicenceend");
            return (Criteria) this;
        }

        public Criteria andBusinessscopeIsNull() {
            addCriterion("businessscope is null");
            return (Criteria) this;
        }

        public Criteria andBusinessscopeIsNotNull() {
            addCriterion("businessscope is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessscopeEqualTo(String value) {
            addCriterion("businessscope =", value, "businessscope");
            return (Criteria) this;
        }

        public Criteria andBusinessscopeNotEqualTo(String value) {
            addCriterion("businessscope <>", value, "businessscope");
            return (Criteria) this;
        }

        public Criteria andBusinessscopeGreaterThan(String value) {
            addCriterion("businessscope >", value, "businessscope");
            return (Criteria) this;
        }

        public Criteria andBusinessscopeGreaterThanOrEqualTo(String value) {
            addCriterion("businessscope >=", value, "businessscope");
            return (Criteria) this;
        }

        public Criteria andBusinessscopeLessThan(String value) {
            addCriterion("businessscope <", value, "businessscope");
            return (Criteria) this;
        }

        public Criteria andBusinessscopeLessThanOrEqualTo(String value) {
            addCriterion("businessscope <=", value, "businessscope");
            return (Criteria) this;
        }

        public Criteria andBusinessscopeLike(String value) {
            addCriterion("businessscope like", value, "businessscope");
            return (Criteria) this;
        }

        public Criteria andBusinessscopeNotLike(String value) {
            addCriterion("businessscope not like", value, "businessscope");
            return (Criteria) this;
        }

        public Criteria andBusinessscopeIn(List<String> values) {
            addCriterion("businessscope in", values, "businessscope");
            return (Criteria) this;
        }

        public Criteria andBusinessscopeNotIn(List<String> values) {
            addCriterion("businessscope not in", values, "businessscope");
            return (Criteria) this;
        }

        public Criteria andBusinessscopeBetween(String value1, String value2) {
            addCriterion("businessscope between", value1, value2, "businessscope");
            return (Criteria) this;
        }

        public Criteria andBusinessscopeNotBetween(String value1, String value2) {
            addCriterion("businessscope not between", value1, value2, "businessscope");
            return (Criteria) this;
        }

        public Criteria andBanknameIsNull() {
            addCriterion("bankname is null");
            return (Criteria) this;
        }

        public Criteria andBanknameIsNotNull() {
            addCriterion("bankname is not null");
            return (Criteria) this;
        }

        public Criteria andBanknameEqualTo(String value) {
            addCriterion("bankname =", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameNotEqualTo(String value) {
            addCriterion("bankname <>", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameGreaterThan(String value) {
            addCriterion("bankname >", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameGreaterThanOrEqualTo(String value) {
            addCriterion("bankname >=", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameLessThan(String value) {
            addCriterion("bankname <", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameLessThanOrEqualTo(String value) {
            addCriterion("bankname <=", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameLike(String value) {
            addCriterion("bankname like", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameNotLike(String value) {
            addCriterion("bankname not like", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameIn(List<String> values) {
            addCriterion("bankname in", values, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameNotIn(List<String> values) {
            addCriterion("bankname not in", values, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameBetween(String value1, String value2) {
            addCriterion("bankname between", value1, value2, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameNotBetween(String value1, String value2) {
            addCriterion("bankname not between", value1, value2, "bankname");
            return (Criteria) this;
        }

        public Criteria andBankaccountIsNull() {
            addCriterion("bankaccount is null");
            return (Criteria) this;
        }

        public Criteria andBankaccountIsNotNull() {
            addCriterion("bankaccount is not null");
            return (Criteria) this;
        }

        public Criteria andBankaccountEqualTo(String value) {
            addCriterion("bankaccount =", value, "bankaccount");
            return (Criteria) this;
        }

        public Criteria andBankaccountNotEqualTo(String value) {
            addCriterion("bankaccount <>", value, "bankaccount");
            return (Criteria) this;
        }

        public Criteria andBankaccountGreaterThan(String value) {
            addCriterion("bankaccount >", value, "bankaccount");
            return (Criteria) this;
        }

        public Criteria andBankaccountGreaterThanOrEqualTo(String value) {
            addCriterion("bankaccount >=", value, "bankaccount");
            return (Criteria) this;
        }

        public Criteria andBankaccountLessThan(String value) {
            addCriterion("bankaccount <", value, "bankaccount");
            return (Criteria) this;
        }

        public Criteria andBankaccountLessThanOrEqualTo(String value) {
            addCriterion("bankaccount <=", value, "bankaccount");
            return (Criteria) this;
        }

        public Criteria andBankaccountLike(String value) {
            addCriterion("bankaccount like", value, "bankaccount");
            return (Criteria) this;
        }

        public Criteria andBankaccountNotLike(String value) {
            addCriterion("bankaccount not like", value, "bankaccount");
            return (Criteria) this;
        }

        public Criteria andBankaccountIn(List<String> values) {
            addCriterion("bankaccount in", values, "bankaccount");
            return (Criteria) this;
        }

        public Criteria andBankaccountNotIn(List<String> values) {
            addCriterion("bankaccount not in", values, "bankaccount");
            return (Criteria) this;
        }

        public Criteria andBankaccountBetween(String value1, String value2) {
            addCriterion("bankaccount between", value1, value2, "bankaccount");
            return (Criteria) this;
        }

        public Criteria andBankaccountNotBetween(String value1, String value2) {
            addCriterion("bankaccount not between", value1, value2, "bankaccount");
            return (Criteria) this;
        }

        public Criteria andBankbrachnameIsNull() {
            addCriterion("bankbrachname is null");
            return (Criteria) this;
        }

        public Criteria andBankbrachnameIsNotNull() {
            addCriterion("bankbrachname is not null");
            return (Criteria) this;
        }

        public Criteria andBankbrachnameEqualTo(String value) {
            addCriterion("bankbrachname =", value, "bankbrachname");
            return (Criteria) this;
        }

        public Criteria andBankbrachnameNotEqualTo(String value) {
            addCriterion("bankbrachname <>", value, "bankbrachname");
            return (Criteria) this;
        }

        public Criteria andBankbrachnameGreaterThan(String value) {
            addCriterion("bankbrachname >", value, "bankbrachname");
            return (Criteria) this;
        }

        public Criteria andBankbrachnameGreaterThanOrEqualTo(String value) {
            addCriterion("bankbrachname >=", value, "bankbrachname");
            return (Criteria) this;
        }

        public Criteria andBankbrachnameLessThan(String value) {
            addCriterion("bankbrachname <", value, "bankbrachname");
            return (Criteria) this;
        }

        public Criteria andBankbrachnameLessThanOrEqualTo(String value) {
            addCriterion("bankbrachname <=", value, "bankbrachname");
            return (Criteria) this;
        }

        public Criteria andBankbrachnameLike(String value) {
            addCriterion("bankbrachname like", value, "bankbrachname");
            return (Criteria) this;
        }

        public Criteria andBankbrachnameNotLike(String value) {
            addCriterion("bankbrachname not like", value, "bankbrachname");
            return (Criteria) this;
        }

        public Criteria andBankbrachnameIn(List<String> values) {
            addCriterion("bankbrachname in", values, "bankbrachname");
            return (Criteria) this;
        }

        public Criteria andBankbrachnameNotIn(List<String> values) {
            addCriterion("bankbrachname not in", values, "bankbrachname");
            return (Criteria) this;
        }

        public Criteria andBankbrachnameBetween(String value1, String value2) {
            addCriterion("bankbrachname between", value1, value2, "bankbrachname");
            return (Criteria) this;
        }

        public Criteria andBankbrachnameNotBetween(String value1, String value2) {
            addCriterion("bankbrachname not between", value1, value2, "bankbrachname");
            return (Criteria) this;
        }

        public Criteria andBankbrachaccountIsNull() {
            addCriterion("bankbrachaccount is null");
            return (Criteria) this;
        }

        public Criteria andBankbrachaccountIsNotNull() {
            addCriterion("bankbrachaccount is not null");
            return (Criteria) this;
        }

        public Criteria andBankbrachaccountEqualTo(String value) {
            addCriterion("bankbrachaccount =", value, "bankbrachaccount");
            return (Criteria) this;
        }

        public Criteria andBankbrachaccountNotEqualTo(String value) {
            addCriterion("bankbrachaccount <>", value, "bankbrachaccount");
            return (Criteria) this;
        }

        public Criteria andBankbrachaccountGreaterThan(String value) {
            addCriterion("bankbrachaccount >", value, "bankbrachaccount");
            return (Criteria) this;
        }

        public Criteria andBankbrachaccountGreaterThanOrEqualTo(String value) {
            addCriterion("bankbrachaccount >=", value, "bankbrachaccount");
            return (Criteria) this;
        }

        public Criteria andBankbrachaccountLessThan(String value) {
            addCriterion("bankbrachaccount <", value, "bankbrachaccount");
            return (Criteria) this;
        }

        public Criteria andBankbrachaccountLessThanOrEqualTo(String value) {
            addCriterion("bankbrachaccount <=", value, "bankbrachaccount");
            return (Criteria) this;
        }

        public Criteria andBankbrachaccountLike(String value) {
            addCriterion("bankbrachaccount like", value, "bankbrachaccount");
            return (Criteria) this;
        }

        public Criteria andBankbrachaccountNotLike(String value) {
            addCriterion("bankbrachaccount not like", value, "bankbrachaccount");
            return (Criteria) this;
        }

        public Criteria andBankbrachaccountIn(List<String> values) {
            addCriterion("bankbrachaccount in", values, "bankbrachaccount");
            return (Criteria) this;
        }

        public Criteria andBankbrachaccountNotIn(List<String> values) {
            addCriterion("bankbrachaccount not in", values, "bankbrachaccount");
            return (Criteria) this;
        }

        public Criteria andBankbrachaccountBetween(String value1, String value2) {
            addCriterion("bankbrachaccount between", value1, value2, "bankbrachaccount");
            return (Criteria) this;
        }

        public Criteria andBankbrachaccountNotBetween(String value1, String value2) {
            addCriterion("bankbrachaccount not between", value1, value2, "bankbrachaccount");
            return (Criteria) this;
        }

        public Criteria andBankprovinceIsNull() {
            addCriterion("bankprovince is null");
            return (Criteria) this;
        }

        public Criteria andBankprovinceIsNotNull() {
            addCriterion("bankprovince is not null");
            return (Criteria) this;
        }

        public Criteria andBankprovinceEqualTo(String value) {
            addCriterion("bankprovince =", value, "bankprovince");
            return (Criteria) this;
        }

        public Criteria andBankprovinceNotEqualTo(String value) {
            addCriterion("bankprovince <>", value, "bankprovince");
            return (Criteria) this;
        }

        public Criteria andBankprovinceGreaterThan(String value) {
            addCriterion("bankprovince >", value, "bankprovince");
            return (Criteria) this;
        }

        public Criteria andBankprovinceGreaterThanOrEqualTo(String value) {
            addCriterion("bankprovince >=", value, "bankprovince");
            return (Criteria) this;
        }

        public Criteria andBankprovinceLessThan(String value) {
            addCriterion("bankprovince <", value, "bankprovince");
            return (Criteria) this;
        }

        public Criteria andBankprovinceLessThanOrEqualTo(String value) {
            addCriterion("bankprovince <=", value, "bankprovince");
            return (Criteria) this;
        }

        public Criteria andBankprovinceLike(String value) {
            addCriterion("bankprovince like", value, "bankprovince");
            return (Criteria) this;
        }

        public Criteria andBankprovinceNotLike(String value) {
            addCriterion("bankprovince not like", value, "bankprovince");
            return (Criteria) this;
        }

        public Criteria andBankprovinceIn(List<String> values) {
            addCriterion("bankprovince in", values, "bankprovince");
            return (Criteria) this;
        }

        public Criteria andBankprovinceNotIn(List<String> values) {
            addCriterion("bankprovince not in", values, "bankprovince");
            return (Criteria) this;
        }

        public Criteria andBankprovinceBetween(String value1, String value2) {
            addCriterion("bankprovince between", value1, value2, "bankprovince");
            return (Criteria) this;
        }

        public Criteria andBankprovinceNotBetween(String value1, String value2) {
            addCriterion("bankprovince not between", value1, value2, "bankprovince");
            return (Criteria) this;
        }

        public Criteria andBankcityIsNull() {
            addCriterion("bankcity is null");
            return (Criteria) this;
        }

        public Criteria andBankcityIsNotNull() {
            addCriterion("bankcity is not null");
            return (Criteria) this;
        }

        public Criteria andBankcityEqualTo(String value) {
            addCriterion("bankcity =", value, "bankcity");
            return (Criteria) this;
        }

        public Criteria andBankcityNotEqualTo(String value) {
            addCriterion("bankcity <>", value, "bankcity");
            return (Criteria) this;
        }

        public Criteria andBankcityGreaterThan(String value) {
            addCriterion("bankcity >", value, "bankcity");
            return (Criteria) this;
        }

        public Criteria andBankcityGreaterThanOrEqualTo(String value) {
            addCriterion("bankcity >=", value, "bankcity");
            return (Criteria) this;
        }

        public Criteria andBankcityLessThan(String value) {
            addCriterion("bankcity <", value, "bankcity");
            return (Criteria) this;
        }

        public Criteria andBankcityLessThanOrEqualTo(String value) {
            addCriterion("bankcity <=", value, "bankcity");
            return (Criteria) this;
        }

        public Criteria andBankcityLike(String value) {
            addCriterion("bankcity like", value, "bankcity");
            return (Criteria) this;
        }

        public Criteria andBankcityNotLike(String value) {
            addCriterion("bankcity not like", value, "bankcity");
            return (Criteria) this;
        }

        public Criteria andBankcityIn(List<String> values) {
            addCriterion("bankcity in", values, "bankcity");
            return (Criteria) this;
        }

        public Criteria andBankcityNotIn(List<String> values) {
            addCriterion("bankcity not in", values, "bankcity");
            return (Criteria) this;
        }

        public Criteria andBankcityBetween(String value1, String value2) {
            addCriterion("bankcity between", value1, value2, "bankcity");
            return (Criteria) this;
        }

        public Criteria andBankcityNotBetween(String value1, String value2) {
            addCriterion("bankcity not between", value1, value2, "bankcity");
            return (Criteria) this;
        }

        public Criteria andBankcitysmallIsNull() {
            addCriterion("bankcitysmall is null");
            return (Criteria) this;
        }

        public Criteria andBankcitysmallIsNotNull() {
            addCriterion("bankcitysmall is not null");
            return (Criteria) this;
        }

        public Criteria andBankcitysmallEqualTo(String value) {
            addCriterion("bankcitysmall =", value, "bankcitysmall");
            return (Criteria) this;
        }

        public Criteria andBankcitysmallNotEqualTo(String value) {
            addCriterion("bankcitysmall <>", value, "bankcitysmall");
            return (Criteria) this;
        }

        public Criteria andBankcitysmallGreaterThan(String value) {
            addCriterion("bankcitysmall >", value, "bankcitysmall");
            return (Criteria) this;
        }

        public Criteria andBankcitysmallGreaterThanOrEqualTo(String value) {
            addCriterion("bankcitysmall >=", value, "bankcitysmall");
            return (Criteria) this;
        }

        public Criteria andBankcitysmallLessThan(String value) {
            addCriterion("bankcitysmall <", value, "bankcitysmall");
            return (Criteria) this;
        }

        public Criteria andBankcitysmallLessThanOrEqualTo(String value) {
            addCriterion("bankcitysmall <=", value, "bankcitysmall");
            return (Criteria) this;
        }

        public Criteria andBankcitysmallLike(String value) {
            addCriterion("bankcitysmall like", value, "bankcitysmall");
            return (Criteria) this;
        }

        public Criteria andBankcitysmallNotLike(String value) {
            addCriterion("bankcitysmall not like", value, "bankcitysmall");
            return (Criteria) this;
        }

        public Criteria andBankcitysmallIn(List<String> values) {
            addCriterion("bankcitysmall in", values, "bankcitysmall");
            return (Criteria) this;
        }

        public Criteria andBankcitysmallNotIn(List<String> values) {
            addCriterion("bankcitysmall not in", values, "bankcitysmall");
            return (Criteria) this;
        }

        public Criteria andBankcitysmallBetween(String value1, String value2) {
            addCriterion("bankcitysmall between", value1, value2, "bankcitysmall");
            return (Criteria) this;
        }

        public Criteria andBankcitysmallNotBetween(String value1, String value2) {
            addCriterion("bankcitysmall not between", value1, value2, "bankcitysmall");
            return (Criteria) this;
        }

        public Criteria andBankorgnumpicIsNull() {
            addCriterion("bankorgnumpic is null");
            return (Criteria) this;
        }

        public Criteria andBankorgnumpicIsNotNull() {
            addCriterion("bankorgnumpic is not null");
            return (Criteria) this;
        }

        public Criteria andBankorgnumpicEqualTo(String value) {
            addCriterion("bankorgnumpic =", value, "bankorgnumpic");
            return (Criteria) this;
        }

        public Criteria andBankorgnumpicNotEqualTo(String value) {
            addCriterion("bankorgnumpic <>", value, "bankorgnumpic");
            return (Criteria) this;
        }

        public Criteria andBankorgnumpicGreaterThan(String value) {
            addCriterion("bankorgnumpic >", value, "bankorgnumpic");
            return (Criteria) this;
        }

        public Criteria andBankorgnumpicGreaterThanOrEqualTo(String value) {
            addCriterion("bankorgnumpic >=", value, "bankorgnumpic");
            return (Criteria) this;
        }

        public Criteria andBankorgnumpicLessThan(String value) {
            addCriterion("bankorgnumpic <", value, "bankorgnumpic");
            return (Criteria) this;
        }

        public Criteria andBankorgnumpicLessThanOrEqualTo(String value) {
            addCriterion("bankorgnumpic <=", value, "bankorgnumpic");
            return (Criteria) this;
        }

        public Criteria andBankorgnumpicLike(String value) {
            addCriterion("bankorgnumpic like", value, "bankorgnumpic");
            return (Criteria) this;
        }

        public Criteria andBankorgnumpicNotLike(String value) {
            addCriterion("bankorgnumpic not like", value, "bankorgnumpic");
            return (Criteria) this;
        }

        public Criteria andBankorgnumpicIn(List<String> values) {
            addCriterion("bankorgnumpic in", values, "bankorgnumpic");
            return (Criteria) this;
        }

        public Criteria andBankorgnumpicNotIn(List<String> values) {
            addCriterion("bankorgnumpic not in", values, "bankorgnumpic");
            return (Criteria) this;
        }

        public Criteria andBankorgnumpicBetween(String value1, String value2) {
            addCriterion("bankorgnumpic between", value1, value2, "bankorgnumpic");
            return (Criteria) this;
        }

        public Criteria andBankorgnumpicNotBetween(String value1, String value2) {
            addCriterion("bankorgnumpic not between", value1, value2, "bankorgnumpic");
            return (Criteria) this;
        }

        public Criteria andAlipaynameIsNull() {
            addCriterion("alipayname is null");
            return (Criteria) this;
        }

        public Criteria andAlipaynameIsNotNull() {
            addCriterion("alipayname is not null");
            return (Criteria) this;
        }

        public Criteria andAlipaynameEqualTo(String value) {
            addCriterion("alipayname =", value, "alipayname");
            return (Criteria) this;
        }

        public Criteria andAlipaynameNotEqualTo(String value) {
            addCriterion("alipayname <>", value, "alipayname");
            return (Criteria) this;
        }

        public Criteria andAlipaynameGreaterThan(String value) {
            addCriterion("alipayname >", value, "alipayname");
            return (Criteria) this;
        }

        public Criteria andAlipaynameGreaterThanOrEqualTo(String value) {
            addCriterion("alipayname >=", value, "alipayname");
            return (Criteria) this;
        }

        public Criteria andAlipaynameLessThan(String value) {
            addCriterion("alipayname <", value, "alipayname");
            return (Criteria) this;
        }

        public Criteria andAlipaynameLessThanOrEqualTo(String value) {
            addCriterion("alipayname <=", value, "alipayname");
            return (Criteria) this;
        }

        public Criteria andAlipaynameLike(String value) {
            addCriterion("alipayname like", value, "alipayname");
            return (Criteria) this;
        }

        public Criteria andAlipaynameNotLike(String value) {
            addCriterion("alipayname not like", value, "alipayname");
            return (Criteria) this;
        }

        public Criteria andAlipaynameIn(List<String> values) {
            addCriterion("alipayname in", values, "alipayname");
            return (Criteria) this;
        }

        public Criteria andAlipaynameNotIn(List<String> values) {
            addCriterion("alipayname not in", values, "alipayname");
            return (Criteria) this;
        }

        public Criteria andAlipaynameBetween(String value1, String value2) {
            addCriterion("alipayname between", value1, value2, "alipayname");
            return (Criteria) this;
        }

        public Criteria andAlipaynameNotBetween(String value1, String value2) {
            addCriterion("alipayname not between", value1, value2, "alipayname");
            return (Criteria) this;
        }

        public Criteria andAlipaynoIsNull() {
            addCriterion("alipayno is null");
            return (Criteria) this;
        }

        public Criteria andAlipaynoIsNotNull() {
            addCriterion("alipayno is not null");
            return (Criteria) this;
        }

        public Criteria andAlipaynoEqualTo(String value) {
            addCriterion("alipayno =", value, "alipayno");
            return (Criteria) this;
        }

        public Criteria andAlipaynoNotEqualTo(String value) {
            addCriterion("alipayno <>", value, "alipayno");
            return (Criteria) this;
        }

        public Criteria andAlipaynoGreaterThan(String value) {
            addCriterion("alipayno >", value, "alipayno");
            return (Criteria) this;
        }

        public Criteria andAlipaynoGreaterThanOrEqualTo(String value) {
            addCriterion("alipayno >=", value, "alipayno");
            return (Criteria) this;
        }

        public Criteria andAlipaynoLessThan(String value) {
            addCriterion("alipayno <", value, "alipayno");
            return (Criteria) this;
        }

        public Criteria andAlipaynoLessThanOrEqualTo(String value) {
            addCriterion("alipayno <=", value, "alipayno");
            return (Criteria) this;
        }

        public Criteria andAlipaynoLike(String value) {
            addCriterion("alipayno like", value, "alipayno");
            return (Criteria) this;
        }

        public Criteria andAlipaynoNotLike(String value) {
            addCriterion("alipayno not like", value, "alipayno");
            return (Criteria) this;
        }

        public Criteria andAlipaynoIn(List<String> values) {
            addCriterion("alipayno in", values, "alipayno");
            return (Criteria) this;
        }

        public Criteria andAlipaynoNotIn(List<String> values) {
            addCriterion("alipayno not in", values, "alipayno");
            return (Criteria) this;
        }

        public Criteria andAlipaynoBetween(String value1, String value2) {
            addCriterion("alipayno between", value1, value2, "alipayno");
            return (Criteria) this;
        }

        public Criteria andAlipaynoNotBetween(String value1, String value2) {
            addCriterion("alipayno not between", value1, value2, "alipayno");
            return (Criteria) this;
        }

        public Criteria andWxnameIsNull() {
            addCriterion("wxname is null");
            return (Criteria) this;
        }

        public Criteria andWxnameIsNotNull() {
            addCriterion("wxname is not null");
            return (Criteria) this;
        }

        public Criteria andWxnameEqualTo(String value) {
            addCriterion("wxname =", value, "wxname");
            return (Criteria) this;
        }

        public Criteria andWxnameNotEqualTo(String value) {
            addCriterion("wxname <>", value, "wxname");
            return (Criteria) this;
        }

        public Criteria andWxnameGreaterThan(String value) {
            addCriterion("wxname >", value, "wxname");
            return (Criteria) this;
        }

        public Criteria andWxnameGreaterThanOrEqualTo(String value) {
            addCriterion("wxname >=", value, "wxname");
            return (Criteria) this;
        }

        public Criteria andWxnameLessThan(String value) {
            addCriterion("wxname <", value, "wxname");
            return (Criteria) this;
        }

        public Criteria andWxnameLessThanOrEqualTo(String value) {
            addCriterion("wxname <=", value, "wxname");
            return (Criteria) this;
        }

        public Criteria andWxnameLike(String value) {
            addCriterion("wxname like", value, "wxname");
            return (Criteria) this;
        }

        public Criteria andWxnameNotLike(String value) {
            addCriterion("wxname not like", value, "wxname");
            return (Criteria) this;
        }

        public Criteria andWxnameIn(List<String> values) {
            addCriterion("wxname in", values, "wxname");
            return (Criteria) this;
        }

        public Criteria andWxnameNotIn(List<String> values) {
            addCriterion("wxname not in", values, "wxname");
            return (Criteria) this;
        }

        public Criteria andWxnameBetween(String value1, String value2) {
            addCriterion("wxname between", value1, value2, "wxname");
            return (Criteria) this;
        }

        public Criteria andWxnameNotBetween(String value1, String value2) {
            addCriterion("wxname not between", value1, value2, "wxname");
            return (Criteria) this;
        }

        public Criteria andWxnoIsNull() {
            addCriterion("wxno is null");
            return (Criteria) this;
        }

        public Criteria andWxnoIsNotNull() {
            addCriterion("wxno is not null");
            return (Criteria) this;
        }

        public Criteria andWxnoEqualTo(String value) {
            addCriterion("wxno =", value, "wxno");
            return (Criteria) this;
        }

        public Criteria andWxnoNotEqualTo(String value) {
            addCriterion("wxno <>", value, "wxno");
            return (Criteria) this;
        }

        public Criteria andWxnoGreaterThan(String value) {
            addCriterion("wxno >", value, "wxno");
            return (Criteria) this;
        }

        public Criteria andWxnoGreaterThanOrEqualTo(String value) {
            addCriterion("wxno >=", value, "wxno");
            return (Criteria) this;
        }

        public Criteria andWxnoLessThan(String value) {
            addCriterion("wxno <", value, "wxno");
            return (Criteria) this;
        }

        public Criteria andWxnoLessThanOrEqualTo(String value) {
            addCriterion("wxno <=", value, "wxno");
            return (Criteria) this;
        }

        public Criteria andWxnoLike(String value) {
            addCriterion("wxno like", value, "wxno");
            return (Criteria) this;
        }

        public Criteria andWxnoNotLike(String value) {
            addCriterion("wxno not like", value, "wxno");
            return (Criteria) this;
        }

        public Criteria andWxnoIn(List<String> values) {
            addCriterion("wxno in", values, "wxno");
            return (Criteria) this;
        }

        public Criteria andWxnoNotIn(List<String> values) {
            addCriterion("wxno not in", values, "wxno");
            return (Criteria) this;
        }

        public Criteria andWxnoBetween(String value1, String value2) {
            addCriterion("wxno between", value1, value2, "wxno");
            return (Criteria) this;
        }

        public Criteria andWxnoNotBetween(String value1, String value2) {
            addCriterion("wxno not between", value1, value2, "wxno");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationnoIsNull() {
            addCriterion("taxregistrationno is null");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationnoIsNotNull() {
            addCriterion("taxregistrationno is not null");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationnoEqualTo(String value) {
            addCriterion("taxregistrationno =", value, "taxregistrationno");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationnoNotEqualTo(String value) {
            addCriterion("taxregistrationno <>", value, "taxregistrationno");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationnoGreaterThan(String value) {
            addCriterion("taxregistrationno >", value, "taxregistrationno");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationnoGreaterThanOrEqualTo(String value) {
            addCriterion("taxregistrationno >=", value, "taxregistrationno");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationnoLessThan(String value) {
            addCriterion("taxregistrationno <", value, "taxregistrationno");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationnoLessThanOrEqualTo(String value) {
            addCriterion("taxregistrationno <=", value, "taxregistrationno");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationnoLike(String value) {
            addCriterion("taxregistrationno like", value, "taxregistrationno");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationnoNotLike(String value) {
            addCriterion("taxregistrationno not like", value, "taxregistrationno");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationnoIn(List<String> values) {
            addCriterion("taxregistrationno in", values, "taxregistrationno");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationnoNotIn(List<String> values) {
            addCriterion("taxregistrationno not in", values, "taxregistrationno");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationnoBetween(String value1, String value2) {
            addCriterion("taxregistrationno between", value1, value2, "taxregistrationno");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationnoNotBetween(String value1, String value2) {
            addCriterion("taxregistrationno not between", value1, value2, "taxregistrationno");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationcertificateIsNull() {
            addCriterion("taxregistrationcertificate is null");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationcertificateIsNotNull() {
            addCriterion("taxregistrationcertificate is not null");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationcertificateEqualTo(String value) {
            addCriterion("taxregistrationcertificate =", value, "taxregistrationcertificate");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationcertificateNotEqualTo(String value) {
            addCriterion("taxregistrationcertificate <>", value, "taxregistrationcertificate");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationcertificateGreaterThan(String value) {
            addCriterion("taxregistrationcertificate >", value, "taxregistrationcertificate");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationcertificateGreaterThanOrEqualTo(String value) {
            addCriterion("taxregistrationcertificate >=", value, "taxregistrationcertificate");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationcertificateLessThan(String value) {
            addCriterion("taxregistrationcertificate <", value, "taxregistrationcertificate");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationcertificateLessThanOrEqualTo(String value) {
            addCriterion("taxregistrationcertificate <=", value, "taxregistrationcertificate");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationcertificateLike(String value) {
            addCriterion("taxregistrationcertificate like", value, "taxregistrationcertificate");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationcertificateNotLike(String value) {
            addCriterion("taxregistrationcertificate not like", value, "taxregistrationcertificate");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationcertificateIn(List<String> values) {
            addCriterion("taxregistrationcertificate in", values, "taxregistrationcertificate");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationcertificateNotIn(List<String> values) {
            addCriterion("taxregistrationcertificate not in", values, "taxregistrationcertificate");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationcertificateBetween(String value1, String value2) {
            addCriterion("taxregistrationcertificate between", value1, value2, "taxregistrationcertificate");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationcertificateNotBetween(String value1, String value2) {
            addCriterion("taxregistrationcertificate not between", value1, value2, "taxregistrationcertificate");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationnopicIsNull() {
            addCriterion("taxregistrationnopic is null");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationnopicIsNotNull() {
            addCriterion("taxregistrationnopic is not null");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationnopicEqualTo(String value) {
            addCriterion("taxregistrationnopic =", value, "taxregistrationnopic");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationnopicNotEqualTo(String value) {
            addCriterion("taxregistrationnopic <>", value, "taxregistrationnopic");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationnopicGreaterThan(String value) {
            addCriterion("taxregistrationnopic >", value, "taxregistrationnopic");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationnopicGreaterThanOrEqualTo(String value) {
            addCriterion("taxregistrationnopic >=", value, "taxregistrationnopic");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationnopicLessThan(String value) {
            addCriterion("taxregistrationnopic <", value, "taxregistrationnopic");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationnopicLessThanOrEqualTo(String value) {
            addCriterion("taxregistrationnopic <=", value, "taxregistrationnopic");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationnopicLike(String value) {
            addCriterion("taxregistrationnopic like", value, "taxregistrationnopic");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationnopicNotLike(String value) {
            addCriterion("taxregistrationnopic not like", value, "taxregistrationnopic");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationnopicIn(List<String> values) {
            addCriterion("taxregistrationnopic in", values, "taxregistrationnopic");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationnopicNotIn(List<String> values) {
            addCriterion("taxregistrationnopic not in", values, "taxregistrationnopic");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationnopicBetween(String value1, String value2) {
            addCriterion("taxregistrationnopic between", value1, value2, "taxregistrationnopic");
            return (Criteria) this;
        }

        public Criteria andTaxregistrationnopicNotBetween(String value1, String value2) {
            addCriterion("taxregistrationnopic not between", value1, value2, "taxregistrationnopic");
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

        public Criteria andUpdatedateIsNull() {
            addCriterion("updatedate is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedateIsNotNull() {
            addCriterion("updatedate is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedateEqualTo(Date value) {
            addCriterion("updatedate =", value, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateNotEqualTo(Date value) {
            addCriterion("updatedate <>", value, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateGreaterThan(Date value) {
            addCriterion("updatedate >", value, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateGreaterThanOrEqualTo(Date value) {
            addCriterion("updatedate >=", value, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateLessThan(Date value) {
            addCriterion("updatedate <", value, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateLessThanOrEqualTo(Date value) {
            addCriterion("updatedate <=", value, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateIn(List<Date> values) {
            addCriterion("updatedate in", values, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateNotIn(List<Date> values) {
            addCriterion("updatedate not in", values, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateBetween(Date value1, Date value2) {
            addCriterion("updatedate between", value1, value2, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateNotBetween(Date value1, Date value2) {
            addCriterion("updatedate not between", value1, value2, "updatedate");
            return (Criteria) this;
        }

        public Criteria andValidateIsNull() {
            addCriterion("validate is null");
            return (Criteria) this;
        }

        public Criteria andValidateIsNotNull() {
            addCriterion("validate is not null");
            return (Criteria) this;
        }

        public Criteria andValidateEqualTo(Short value) {
            addCriterion("validate =", value, "validate");
            return (Criteria) this;
        }

        public Criteria andValidateNotEqualTo(Short value) {
            addCriterion("validate <>", value, "validate");
            return (Criteria) this;
        }

        public Criteria andValidateGreaterThan(Short value) {
            addCriterion("validate >", value, "validate");
            return (Criteria) this;
        }

        public Criteria andValidateGreaterThanOrEqualTo(Short value) {
            addCriterion("validate >=", value, "validate");
            return (Criteria) this;
        }

        public Criteria andValidateLessThan(Short value) {
            addCriterion("validate <", value, "validate");
            return (Criteria) this;
        }

        public Criteria andValidateLessThanOrEqualTo(Short value) {
            addCriterion("validate <=", value, "validate");
            return (Criteria) this;
        }

        public Criteria andValidateIn(List<Short> values) {
            addCriterion("validate in", values, "validate");
            return (Criteria) this;
        }

        public Criteria andValidateNotIn(List<Short> values) {
            addCriterion("validate not in", values, "validate");
            return (Criteria) this;
        }

        public Criteria andValidateBetween(Short value1, Short value2) {
            addCriterion("validate between", value1, value2, "validate");
            return (Criteria) this;
        }

        public Criteria andValidateNotBetween(Short value1, Short value2) {
            addCriterion("validate not between", value1, value2, "validate");
            return (Criteria) this;
        }

        public Criteria andShopnameIsNull() {
            addCriterion("shopname is null");
            return (Criteria) this;
        }

        public Criteria andShopnameIsNotNull() {
            addCriterion("shopname is not null");
            return (Criteria) this;
        }

        public Criteria andShopnameEqualTo(String value) {
            addCriterion("shopname =", value, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameNotEqualTo(String value) {
            addCriterion("shopname <>", value, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameGreaterThan(String value) {
            addCriterion("shopname >", value, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameGreaterThanOrEqualTo(String value) {
            addCriterion("shopname >=", value, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameLessThan(String value) {
            addCriterion("shopname <", value, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameLessThanOrEqualTo(String value) {
            addCriterion("shopname <=", value, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameLike(String value) {
            addCriterion("shopname like", value, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameNotLike(String value) {
            addCriterion("shopname not like", value, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameIn(List<String> values) {
            addCriterion("shopname in", values, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameNotIn(List<String> values) {
            addCriterion("shopname not in", values, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameBetween(String value1, String value2) {
            addCriterion("shopname between", value1, value2, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameNotBetween(String value1, String value2) {
            addCriterion("shopname not between", value1, value2, "shopname");
            return (Criteria) this;
        }

        public Criteria andBusinesscategoryIsNull() {
            addCriterion("businesscategory is null");
            return (Criteria) this;
        }

        public Criteria andBusinesscategoryIsNotNull() {
            addCriterion("businesscategory is not null");
            return (Criteria) this;
        }

        public Criteria andBusinesscategoryEqualTo(Object value) {
            addCriterion("businesscategory =", value, "businesscategory");
            return (Criteria) this;
        }

        public Criteria andBusinesscategoryNotEqualTo(Object value) {
            addCriterion("businesscategory <>", value, "businesscategory");
            return (Criteria) this;
        }

        public Criteria andBusinesscategoryGreaterThan(Object value) {
            addCriterion("businesscategory >", value, "businesscategory");
            return (Criteria) this;
        }

        public Criteria andBusinesscategoryGreaterThanOrEqualTo(Object value) {
            addCriterion("businesscategory >=", value, "businesscategory");
            return (Criteria) this;
        }

        public Criteria andBusinesscategoryLessThan(Object value) {
            addCriterion("businesscategory <", value, "businesscategory");
            return (Criteria) this;
        }

        public Criteria andBusinesscategoryLessThanOrEqualTo(Object value) {
            addCriterion("businesscategory <=", value, "businesscategory");
            return (Criteria) this;
        }

        public Criteria andBusinesscategoryIn(List<Object> values) {
            addCriterion("businesscategory in", values, "businesscategory");
            return (Criteria) this;
        }

        public Criteria andBusinesscategoryNotIn(List<Object> values) {
            addCriterion("businesscategory not in", values, "businesscategory");
            return (Criteria) this;
        }

        public Criteria andBusinesscategoryBetween(Object value1, Object value2) {
            addCriterion("businesscategory between", value1, value2, "businesscategory");
            return (Criteria) this;
        }

        public Criteria andBusinesscategoryNotBetween(Object value1, Object value2) {
            addCriterion("businesscategory not between", value1, value2, "businesscategory");
            return (Criteria) this;
        }

        public Criteria andShopgradeidIsNull() {
            addCriterion("shopgradeid is null");
            return (Criteria) this;
        }

        public Criteria andShopgradeidIsNotNull() {
            addCriterion("shopgradeid is not null");
            return (Criteria) this;
        }

        public Criteria andShopgradeidEqualTo(Integer value) {
            addCriterion("shopgradeid =", value, "shopgradeid");
            return (Criteria) this;
        }

        public Criteria andShopgradeidNotEqualTo(Integer value) {
            addCriterion("shopgradeid <>", value, "shopgradeid");
            return (Criteria) this;
        }

        public Criteria andShopgradeidGreaterThan(Integer value) {
            addCriterion("shopgradeid >", value, "shopgradeid");
            return (Criteria) this;
        }

        public Criteria andShopgradeidGreaterThanOrEqualTo(Integer value) {
            addCriterion("shopgradeid >=", value, "shopgradeid");
            return (Criteria) this;
        }

        public Criteria andShopgradeidLessThan(Integer value) {
            addCriterion("shopgradeid <", value, "shopgradeid");
            return (Criteria) this;
        }

        public Criteria andShopgradeidLessThanOrEqualTo(Integer value) {
            addCriterion("shopgradeid <=", value, "shopgradeid");
            return (Criteria) this;
        }

        public Criteria andShopgradeidIn(List<Integer> values) {
            addCriterion("shopgradeid in", values, "shopgradeid");
            return (Criteria) this;
        }

        public Criteria andShopgradeidNotIn(List<Integer> values) {
            addCriterion("shopgradeid not in", values, "shopgradeid");
            return (Criteria) this;
        }

        public Criteria andShopgradeidBetween(Integer value1, Integer value2) {
            addCriterion("shopgradeid between", value1, value2, "shopgradeid");
            return (Criteria) this;
        }

        public Criteria andShopgradeidNotBetween(Integer value1, Integer value2) {
            addCriterion("shopgradeid not between", value1, value2, "shopgradeid");
            return (Criteria) this;
        }

        public Criteria andShopperiodIsNull() {
            addCriterion("shopperiod is null");
            return (Criteria) this;
        }

        public Criteria andShopperiodIsNotNull() {
            addCriterion("shopperiod is not null");
            return (Criteria) this;
        }

        public Criteria andShopperiodEqualTo(String value) {
            addCriterion("shopperiod =", value, "shopperiod");
            return (Criteria) this;
        }

        public Criteria andShopperiodNotEqualTo(String value) {
            addCriterion("shopperiod <>", value, "shopperiod");
            return (Criteria) this;
        }

        public Criteria andShopperiodGreaterThan(String value) {
            addCriterion("shopperiod >", value, "shopperiod");
            return (Criteria) this;
        }

        public Criteria andShopperiodGreaterThanOrEqualTo(String value) {
            addCriterion("shopperiod >=", value, "shopperiod");
            return (Criteria) this;
        }

        public Criteria andShopperiodLessThan(String value) {
            addCriterion("shopperiod <", value, "shopperiod");
            return (Criteria) this;
        }

        public Criteria andShopperiodLessThanOrEqualTo(String value) {
            addCriterion("shopperiod <=", value, "shopperiod");
            return (Criteria) this;
        }

        public Criteria andShopperiodLike(String value) {
            addCriterion("shopperiod like", value, "shopperiod");
            return (Criteria) this;
        }

        public Criteria andShopperiodNotLike(String value) {
            addCriterion("shopperiod not like", value, "shopperiod");
            return (Criteria) this;
        }

        public Criteria andShopperiodIn(List<String> values) {
            addCriterion("shopperiod in", values, "shopperiod");
            return (Criteria) this;
        }

        public Criteria andShopperiodNotIn(List<String> values) {
            addCriterion("shopperiod not in", values, "shopperiod");
            return (Criteria) this;
        }

        public Criteria andShopperiodBetween(String value1, String value2) {
            addCriterion("shopperiod between", value1, value2, "shopperiod");
            return (Criteria) this;
        }

        public Criteria andShopperiodNotBetween(String value1, String value2) {
            addCriterion("shopperiod not between", value1, value2, "shopperiod");
            return (Criteria) this;
        }

        public Criteria andShopstateIsNull() {
            addCriterion("shopstate is null");
            return (Criteria) this;
        }

        public Criteria andShopstateIsNotNull() {
            addCriterion("shopstate is not null");
            return (Criteria) this;
        }

        public Criteria andShopstateEqualTo(Short value) {
            addCriterion("shopstate =", value, "shopstate");
            return (Criteria) this;
        }

        public Criteria andShopstateNotEqualTo(Short value) {
            addCriterion("shopstate <>", value, "shopstate");
            return (Criteria) this;
        }

        public Criteria andShopstateGreaterThan(Short value) {
            addCriterion("shopstate >", value, "shopstate");
            return (Criteria) this;
        }

        public Criteria andShopstateGreaterThanOrEqualTo(Short value) {
            addCriterion("shopstate >=", value, "shopstate");
            return (Criteria) this;
        }

        public Criteria andShopstateLessThan(Short value) {
            addCriterion("shopstate <", value, "shopstate");
            return (Criteria) this;
        }

        public Criteria andShopstateLessThanOrEqualTo(Short value) {
            addCriterion("shopstate <=", value, "shopstate");
            return (Criteria) this;
        }

        public Criteria andShopstateIn(List<Short> values) {
            addCriterion("shopstate in", values, "shopstate");
            return (Criteria) this;
        }

        public Criteria andShopstateNotIn(List<Short> values) {
            addCriterion("shopstate not in", values, "shopstate");
            return (Criteria) this;
        }

        public Criteria andShopstateBetween(Short value1, Short value2) {
            addCriterion("shopstate between", value1, value2, "shopstate");
            return (Criteria) this;
        }

        public Criteria andShopstateNotBetween(Short value1, Short value2) {
            addCriterion("shopstate not between", value1, value2, "shopstate");
            return (Criteria) this;
        }

        public Criteria andIsrecommendIsNull() {
            addCriterion("isrecommend is null");
            return (Criteria) this;
        }

        public Criteria andIsrecommendIsNotNull() {
            addCriterion("isrecommend is not null");
            return (Criteria) this;
        }

        public Criteria andIsrecommendEqualTo(Short value) {
            addCriterion("isrecommend =", value, "isrecommend");
            return (Criteria) this;
        }

        public Criteria andIsrecommendNotEqualTo(Short value) {
            addCriterion("isrecommend <>", value, "isrecommend");
            return (Criteria) this;
        }

        public Criteria andIsrecommendGreaterThan(Short value) {
            addCriterion("isrecommend >", value, "isrecommend");
            return (Criteria) this;
        }

        public Criteria andIsrecommendGreaterThanOrEqualTo(Short value) {
            addCriterion("isrecommend >=", value, "isrecommend");
            return (Criteria) this;
        }

        public Criteria andIsrecommendLessThan(Short value) {
            addCriterion("isrecommend <", value, "isrecommend");
            return (Criteria) this;
        }

        public Criteria andIsrecommendLessThanOrEqualTo(Short value) {
            addCriterion("isrecommend <=", value, "isrecommend");
            return (Criteria) this;
        }

        public Criteria andIsrecommendIn(List<Short> values) {
            addCriterion("isrecommend in", values, "isrecommend");
            return (Criteria) this;
        }

        public Criteria andIsrecommendNotIn(List<Short> values) {
            addCriterion("isrecommend not in", values, "isrecommend");
            return (Criteria) this;
        }

        public Criteria andIsrecommendBetween(Short value1, Short value2) {
            addCriterion("isrecommend between", value1, value2, "isrecommend");
            return (Criteria) this;
        }

        public Criteria andIsrecommendNotBetween(Short value1, Short value2) {
            addCriterion("isrecommend not between", value1, value2, "isrecommend");
            return (Criteria) this;
        }

        public Criteria andDeliverymodeIsNull() {
            addCriterion("deliverymode is null");
            return (Criteria) this;
        }

        public Criteria andDeliverymodeIsNotNull() {
            addCriterion("deliverymode is not null");
            return (Criteria) this;
        }

        public Criteria andDeliverymodeEqualTo(Short value) {
            addCriterion("deliverymode =", value, "deliverymode");
            return (Criteria) this;
        }

        public Criteria andDeliverymodeNotEqualTo(Short value) {
            addCriterion("deliverymode <>", value, "deliverymode");
            return (Criteria) this;
        }

        public Criteria andDeliverymodeGreaterThan(Short value) {
            addCriterion("deliverymode >", value, "deliverymode");
            return (Criteria) this;
        }

        public Criteria andDeliverymodeGreaterThanOrEqualTo(Short value) {
            addCriterion("deliverymode >=", value, "deliverymode");
            return (Criteria) this;
        }

        public Criteria andDeliverymodeLessThan(Short value) {
            addCriterion("deliverymode <", value, "deliverymode");
            return (Criteria) this;
        }

        public Criteria andDeliverymodeLessThanOrEqualTo(Short value) {
            addCriterion("deliverymode <=", value, "deliverymode");
            return (Criteria) this;
        }

        public Criteria andDeliverymodeIn(List<Short> values) {
            addCriterion("deliverymode in", values, "deliverymode");
            return (Criteria) this;
        }

        public Criteria andDeliverymodeNotIn(List<Short> values) {
            addCriterion("deliverymode not in", values, "deliverymode");
            return (Criteria) this;
        }

        public Criteria andDeliverymodeBetween(Short value1, Short value2) {
            addCriterion("deliverymode between", value1, value2, "deliverymode");
            return (Criteria) this;
        }

        public Criteria andDeliverymodeNotBetween(Short value1, Short value2) {
            addCriterion("deliverymode not between", value1, value2, "deliverymode");
            return (Criteria) this;
        }

        public Criteria andSmsnotifyIsNull() {
            addCriterion("smsnotify is null");
            return (Criteria) this;
        }

        public Criteria andSmsnotifyIsNotNull() {
            addCriterion("smsnotify is not null");
            return (Criteria) this;
        }

        public Criteria andSmsnotifyEqualTo(Short value) {
            addCriterion("smsnotify =", value, "smsnotify");
            return (Criteria) this;
        }

        public Criteria andSmsnotifyNotEqualTo(Short value) {
            addCriterion("smsnotify <>", value, "smsnotify");
            return (Criteria) this;
        }

        public Criteria andSmsnotifyGreaterThan(Short value) {
            addCriterion("smsnotify >", value, "smsnotify");
            return (Criteria) this;
        }

        public Criteria andSmsnotifyGreaterThanOrEqualTo(Short value) {
            addCriterion("smsnotify >=", value, "smsnotify");
            return (Criteria) this;
        }

        public Criteria andSmsnotifyLessThan(Short value) {
            addCriterion("smsnotify <", value, "smsnotify");
            return (Criteria) this;
        }

        public Criteria andSmsnotifyLessThanOrEqualTo(Short value) {
            addCriterion("smsnotify <=", value, "smsnotify");
            return (Criteria) this;
        }

        public Criteria andSmsnotifyIn(List<Short> values) {
            addCriterion("smsnotify in", values, "smsnotify");
            return (Criteria) this;
        }

        public Criteria andSmsnotifyNotIn(List<Short> values) {
            addCriterion("smsnotify not in", values, "smsnotify");
            return (Criteria) this;
        }

        public Criteria andSmsnotifyBetween(Short value1, Short value2) {
            addCriterion("smsnotify between", value1, value2, "smsnotify");
            return (Criteria) this;
        }

        public Criteria andSmsnotifyNotBetween(Short value1, Short value2) {
            addCriterion("smsnotify not between", value1, value2, "smsnotify");
            return (Criteria) this;
        }

        public Criteria andAppidIsNull() {
            addCriterion("appid is null");
            return (Criteria) this;
        }

        public Criteria andAppidIsNotNull() {
            addCriterion("appid is not null");
            return (Criteria) this;
        }

        public Criteria andAppidEqualTo(String value) {
            addCriterion("appid =", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidNotEqualTo(String value) {
            addCriterion("appid <>", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidGreaterThan(String value) {
            addCriterion("appid >", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidGreaterThanOrEqualTo(String value) {
            addCriterion("appid >=", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidLessThan(String value) {
            addCriterion("appid <", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidLessThanOrEqualTo(String value) {
            addCriterion("appid <=", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidLike(String value) {
            addCriterion("appid like", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidNotLike(String value) {
            addCriterion("appid not like", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidIn(List<String> values) {
            addCriterion("appid in", values, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidNotIn(List<String> values) {
            addCriterion("appid not in", values, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidBetween(String value1, String value2) {
            addCriterion("appid between", value1, value2, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidNotBetween(String value1, String value2) {
            addCriterion("appid not between", value1, value2, "appid");
            return (Criteria) this;
        }

        public Criteria andAppsecretIsNull() {
            addCriterion("appsecret is null");
            return (Criteria) this;
        }

        public Criteria andAppsecretIsNotNull() {
            addCriterion("appsecret is not null");
            return (Criteria) this;
        }

        public Criteria andAppsecretEqualTo(String value) {
            addCriterion("appsecret =", value, "appsecret");
            return (Criteria) this;
        }

        public Criteria andAppsecretNotEqualTo(String value) {
            addCriterion("appsecret <>", value, "appsecret");
            return (Criteria) this;
        }

        public Criteria andAppsecretGreaterThan(String value) {
            addCriterion("appsecret >", value, "appsecret");
            return (Criteria) this;
        }

        public Criteria andAppsecretGreaterThanOrEqualTo(String value) {
            addCriterion("appsecret >=", value, "appsecret");
            return (Criteria) this;
        }

        public Criteria andAppsecretLessThan(String value) {
            addCriterion("appsecret <", value, "appsecret");
            return (Criteria) this;
        }

        public Criteria andAppsecretLessThanOrEqualTo(String value) {
            addCriterion("appsecret <=", value, "appsecret");
            return (Criteria) this;
        }

        public Criteria andAppsecretLike(String value) {
            addCriterion("appsecret like", value, "appsecret");
            return (Criteria) this;
        }

        public Criteria andAppsecretNotLike(String value) {
            addCriterion("appsecret not like", value, "appsecret");
            return (Criteria) this;
        }

        public Criteria andAppsecretIn(List<String> values) {
            addCriterion("appsecret in", values, "appsecret");
            return (Criteria) this;
        }

        public Criteria andAppsecretNotIn(List<String> values) {
            addCriterion("appsecret not in", values, "appsecret");
            return (Criteria) this;
        }

        public Criteria andAppsecretBetween(String value1, String value2) {
            addCriterion("appsecret between", value1, value2, "appsecret");
            return (Criteria) this;
        }

        public Criteria andAppsecretNotBetween(String value1, String value2) {
            addCriterion("appsecret not between", value1, value2, "appsecret");
            return (Criteria) this;
        }

        public Criteria andAppurlIsNull() {
            addCriterion("appurl is null");
            return (Criteria) this;
        }

        public Criteria andAppurlIsNotNull() {
            addCriterion("appurl is not null");
            return (Criteria) this;
        }

        public Criteria andAppurlEqualTo(String value) {
            addCriterion("appurl =", value, "appurl");
            return (Criteria) this;
        }

        public Criteria andAppurlNotEqualTo(String value) {
            addCriterion("appurl <>", value, "appurl");
            return (Criteria) this;
        }

        public Criteria andAppurlGreaterThan(String value) {
            addCriterion("appurl >", value, "appurl");
            return (Criteria) this;
        }

        public Criteria andAppurlGreaterThanOrEqualTo(String value) {
            addCriterion("appurl >=", value, "appurl");
            return (Criteria) this;
        }

        public Criteria andAppurlLessThan(String value) {
            addCriterion("appurl <", value, "appurl");
            return (Criteria) this;
        }

        public Criteria andAppurlLessThanOrEqualTo(String value) {
            addCriterion("appurl <=", value, "appurl");
            return (Criteria) this;
        }

        public Criteria andAppurlLike(String value) {
            addCriterion("appurl like", value, "appurl");
            return (Criteria) this;
        }

        public Criteria andAppurlNotLike(String value) {
            addCriterion("appurl not like", value, "appurl");
            return (Criteria) this;
        }

        public Criteria andAppurlIn(List<String> values) {
            addCriterion("appurl in", values, "appurl");
            return (Criteria) this;
        }

        public Criteria andAppurlNotIn(List<String> values) {
            addCriterion("appurl not in", values, "appurl");
            return (Criteria) this;
        }

        public Criteria andAppurlBetween(String value1, String value2) {
            addCriterion("appurl between", value1, value2, "appurl");
            return (Criteria) this;
        }

        public Criteria andAppurlNotBetween(String value1, String value2) {
            addCriterion("appurl not between", value1, value2, "appurl");
            return (Criteria) this;
        }

        public Criteria andDisableIsNull() {
            addCriterion("disable is null");
            return (Criteria) this;
        }

        public Criteria andDisableIsNotNull() {
            addCriterion("disable is not null");
            return (Criteria) this;
        }

        public Criteria andDisableEqualTo(Boolean value) {
            addCriterion("disable =", value, "disable");
            return (Criteria) this;
        }

        public Criteria andDisableNotEqualTo(Boolean value) {
            addCriterion("disable <>", value, "disable");
            return (Criteria) this;
        }

        public Criteria andDisableGreaterThan(Boolean value) {
            addCriterion("disable >", value, "disable");
            return (Criteria) this;
        }

        public Criteria andDisableGreaterThanOrEqualTo(Boolean value) {
            addCriterion("disable >=", value, "disable");
            return (Criteria) this;
        }

        public Criteria andDisableLessThan(Boolean value) {
            addCriterion("disable <", value, "disable");
            return (Criteria) this;
        }

        public Criteria andDisableLessThanOrEqualTo(Boolean value) {
            addCriterion("disable <=", value, "disable");
            return (Criteria) this;
        }

        public Criteria andDisableIn(List<Boolean> values) {
            addCriterion("disable in", values, "disable");
            return (Criteria) this;
        }

        public Criteria andDisableNotIn(List<Boolean> values) {
            addCriterion("disable not in", values, "disable");
            return (Criteria) this;
        }

        public Criteria andDisableBetween(Boolean value1, Boolean value2) {
            addCriterion("disable between", value1, value2, "disable");
            return (Criteria) this;
        }

        public Criteria andDisableNotBetween(Boolean value1, Boolean value2) {
            addCriterion("disable not between", value1, value2, "disable");
            return (Criteria) this;
        }

        public Criteria andIsselfliftingIsNull() {
            addCriterion("isselflifting is null");
            return (Criteria) this;
        }

        public Criteria andIsselfliftingIsNotNull() {
            addCriterion("isselflifting is not null");
            return (Criteria) this;
        }

        public Criteria andIsselfliftingEqualTo(Boolean value) {
            addCriterion("isselflifting =", value, "isselflifting");
            return (Criteria) this;
        }

        public Criteria andIsselfliftingNotEqualTo(Boolean value) {
            addCriterion("isselflifting <>", value, "isselflifting");
            return (Criteria) this;
        }

        public Criteria andIsselfliftingGreaterThan(Boolean value) {
            addCriterion("isselflifting >", value, "isselflifting");
            return (Criteria) this;
        }

        public Criteria andIsselfliftingGreaterThanOrEqualTo(Boolean value) {
            addCriterion("isselflifting >=", value, "isselflifting");
            return (Criteria) this;
        }

        public Criteria andIsselfliftingLessThan(Boolean value) {
            addCriterion("isselflifting <", value, "isselflifting");
            return (Criteria) this;
        }

        public Criteria andIsselfliftingLessThanOrEqualTo(Boolean value) {
            addCriterion("isselflifting <=", value, "isselflifting");
            return (Criteria) this;
        }

        public Criteria andIsselfliftingIn(List<Boolean> values) {
            addCriterion("isselflifting in", values, "isselflifting");
            return (Criteria) this;
        }

        public Criteria andIsselfliftingNotIn(List<Boolean> values) {
            addCriterion("isselflifting not in", values, "isselflifting");
            return (Criteria) this;
        }

        public Criteria andIsselfliftingBetween(Boolean value1, Boolean value2) {
            addCriterion("isselflifting between", value1, value2, "isselflifting");
            return (Criteria) this;
        }

        public Criteria andIsselfliftingNotBetween(Boolean value1, Boolean value2) {
            addCriterion("isselflifting not between", value1, value2, "isselflifting");
            return (Criteria) this;
        }

        public Criteria andFreightmodeIsNull() {
            addCriterion("freightmode is null");
            return (Criteria) this;
        }

        public Criteria andFreightmodeIsNotNull() {
            addCriterion("freightmode is not null");
            return (Criteria) this;
        }

        public Criteria andFreightmodeEqualTo(Short value) {
            addCriterion("freightmode =", value, "freightmode");
            return (Criteria) this;
        }

        public Criteria andFreightmodeNotEqualTo(Short value) {
            addCriterion("freightmode <>", value, "freightmode");
            return (Criteria) this;
        }

        public Criteria andFreightmodeGreaterThan(Short value) {
            addCriterion("freightmode >", value, "freightmode");
            return (Criteria) this;
        }

        public Criteria andFreightmodeGreaterThanOrEqualTo(Short value) {
            addCriterion("freightmode >=", value, "freightmode");
            return (Criteria) this;
        }

        public Criteria andFreightmodeLessThan(Short value) {
            addCriterion("freightmode <", value, "freightmode");
            return (Criteria) this;
        }

        public Criteria andFreightmodeLessThanOrEqualTo(Short value) {
            addCriterion("freightmode <=", value, "freightmode");
            return (Criteria) this;
        }

        public Criteria andFreightmodeIn(List<Short> values) {
            addCriterion("freightmode in", values, "freightmode");
            return (Criteria) this;
        }

        public Criteria andFreightmodeNotIn(List<Short> values) {
            addCriterion("freightmode not in", values, "freightmode");
            return (Criteria) this;
        }

        public Criteria andFreightmodeBetween(Short value1, Short value2) {
            addCriterion("freightmode between", value1, value2, "freightmode");
            return (Criteria) this;
        }

        public Criteria andFreightmodeNotBetween(Short value1, Short value2) {
            addCriterion("freightmode not between", value1, value2, "freightmode");
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