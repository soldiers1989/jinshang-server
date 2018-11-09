``
# table - example


#用户表
create table member(
  id bigserial primary key,
  userName varchar unique default '',  --用户名
  password  varchar default '',
  passwordSalt varchar default '',
  mobile varchar default '',  --手机号
  email varchar default '',  --邮箱
  postCode varchar default '',--邮编
  nick varchar default '', --昵称
  realName varchar default '', --真实姓名
  address varchar default '',  --地址
  disabled boolean default false,  --是否停用
  createDate timestamp,  --创建时间
  lastLoginDate timestamp, --最后登录时间
  photo  varchar default '', --头像
  type  smallint default 0 not null,--类型 0-买家、1-卖家
  payPassword varchar default '', --支付密码
  payPasswordSalt varchar default '', --支付密码盐
  company boolean default false not null, --是否是公司
  reviewed boolean default false not null,--是否需要审核
  gradleId int default null, --会员等级，关联memberGradle.id
  deliveryRegionId varchar default '',   
  deliveryAddress varchar default '', --地址
  integrals int default 0,--会员积分
  balance numeric,  --买家余额
  alipay varchar default '',  --支付宝帐号
  wxpay varchar default '',   --微信帐号
  way varchar default '', --注册途径
  waySalesman varchar default '',--介绍人
  inviteCode varchar default '', --邀请码
  clerkName varchar default '',--业务员
  labelid smallint,   --对应memberLabel.id
  parentid bigint ,
  parentName varchar default '',--父username 
  menu varchar[], --权限
  flag boolean, --是否是主帐号， true--主帐号，false--子帐号
  remark varchar, --备注
  qq  varchar,--QQ
  sex varchar,--性别
  faxes varchar,--传真
  telephone varchar,--固定电话
  hobby varchar, --爱好
  Favicon varchar,--头像照片路径地址
  creditstate int2 --买家授信状态 0=未开通，1=已开通，2=禁用授信
)




#买家企业信息
create table buyerCompanyInfo(
id bigserial primary key,
memberId  bigint not null,
shortName varchar not null default '', --公司简称
companyName varchar not null default '',--公司名称
address varchar not null default '',--所在地区
legalPerson varchar not null default '',--法人代表
mobile varchar default '',--联系手机
workTelephone varchar default '',--单位电话
postalAddress varchar default '',--通讯地址
bankName varchar default '', --开户银行
bankAccount varchar default '',--银行帐号
taxRegistrationCertificate varchar default '',--纳税号
methodSettingAccount varchar default '',--结算方式
createDate timestamp, --添加时间
updateDate timestamp,--修改时间
bankuser varchar, --开户人
bankdeposit varchar --开户行
)


#卖家企业信息
create table sellerCompanyInfo(
id bigserial primary key,
memberId bigint not null,
companyName varchar not null  default '',
province varchar not null  default '', --省
city varchar not null  default '', --市
citysmall varchar not null  default '', --区
address varchar not null  default '',--详细地址
email varchar not null  default '',
companyTel varchar not null  default '',--公司电话
linkman varchar not null  default '', --联系人
linkmanTel varchar not null default '',--联系人电话 
employeeNum int default 0,--员工人数
regfound int not null, --注册资本（万元）
businessLicenceNumber varchar not null  default '',--营业执照
businessLicenceNumberPhoto varchar  default '', --营业执照图片地址
businessLicenceStart timestamp , --营业执照开始时间
businessLicenceEnd timestamp, --营业执照结束时间
businessScope varchar  default '', --经营范围
bankName varchar not null   default '', --银行开户行
bankAccount varchar not null  default '',--公司银行帐号
bankBrachName varchar  default '', --开户行支行名称
bankBrachAccount varchar not null  default '', --支行联行号
bankProvince varchar not null  default '', --开会行所在地
bankCity varchar not null  default '', --开会行所在地
bankCitySmall varchar not null  default '', --开会行所在地
bankOrgNumPic varchar not null  default '',--开户银行许可证图片
alipayName varchar not null  default '', --支付宝姓名
alipayNo varchar not null  default '',  --支付宝帐号
wxName varchar  default '',
wxNo varchar  default '',
taxRegistrationNo varchar  default '', --税务登记证
taxRegistrationCertificate varchar  default '', --纳税人识别号
taxRegistrationNoPic varchar  default '',--税务登记证电子图片
createDate timestamp, --添加时间
updateDate timestamp, --修改时间
validate smallint default 0, --审核状态 0-未审核，1-通过，2-未通过，3-删除
shopName varchar not null, --商铺名称
businessCategory varchar[] not null --经营分类
)








#后台管理员
create table admin(
 id bigserial primary key,
 username varchar not null,
 password varchar not null,
 passwordSalt varchar not null,
 realName varchar,  --真实姓名
 mobile varchar not null, --手机
 groupId int,--权限组id
 groupname varchar,--组名
 disable boolean not null default false,  --是否禁用
 createDate timestamp,
 parentId int,
 roles varchar[] --权限数组，定义该组有哪些权限
)



#管理员组
create table adminGroup(
  id bigserial primary key,
  name varchar not null,
  createDate timestamp,
  roles varchar[] --权限数组， 模板，不做实际权限判断
)


#卖家权限组   
create table sellerGroup(
 id bigserial primary key,
 memberid bigint not null,
 groupName varchar, --组名
 roles varchar[] --权限数组， 模板，不做实际权限判断
)




#会员等级表（买家）  
create table memberGrade(
   id serial primary key,
   iserial int not null,--序号
   gradeName varchar not null, --名称
   remark varchar, --备注
   idefault int default 0 not null --是否是默认的等级
)




#卖家级别(店铺等级)
create table shopGrade(
   id bigserial primary key,
   grade int not null, --级别
   gradeName varchar not null, --名称
   productLimit int not null,--可发布产品数
   chargeStandard decimal not null,--定金
   rate decimal not null,--佣金比率
   remark varchar, --备注
   idefault int default 0 not null --是否是默认的等级
)




#会员标签
create table memberLabel(
   id bigserial primary key,
   labelName varchar,
   salesman varchar --业务员
)

#产品分类表
create table categories(
  id serial primary key,
  name varchar not null default '',
  parentid int not null default 0,
  title varchar default '',
  keywords varchar default '',
  description varchar default '',
  img varchar default '',
  uprate decimal,--加价率
  sort int default 0,
  goldmemberrate decimal,--金牌优惠率
  serverrate decimal,--服务商优惠率
  thirdrate decimal,--三级批发价优惠率
  secondrate decimal,--二级批发价优惠率
  firstrate decimal,--一级批发价优惠率
  freerate decimal, --计费率（财务）
  businessrate decimal,--运营费用（财务）
  payrate decimal,--支付费用率（财务）
  servernetrate decimal --服务商净利率（财务）
)

#文章类型表
create table  articlecategory(
id   serial primary key,	--文档ID
praentid int,--父级ID
docname varchar,	--分类名称
docorder	int
)

#文章内容表
create table  article(
id	 serial primary key,		--自增主键
docid	int，--关联文章类型表praentid
doctitle	varchar,	--文章标题
doccontent	varchar,--文章内容
docorder	int,--文章排序
docaddress	varchar,--文章链接地址
docshow		smallint--文章是否显{0:不显示;1显示}
creattime	timestamp,--文章创建时间	
updatetime  timestamp,--文章修改时间
docstatus	smallint--文章是否被删除{0:未删除;1.已删除}
)

#标签管理表
create table pdlabel(
id serial primary key,	
pdlableid	int,--关联商品id
labelname varchar,--商品栏位名称
recommend varchar,--推荐商品栏目描述
isuse int	--是否启用
)
#网站导航表
create table navigation(
id serial primary key,
natype varchar,--导航类型
natitle varchar,--标题
nalink varchar,--链接
nalocation varchar,--所在位置
naterrace varchar,--平台标识
naicon varchar,--图标
isnew int,--是否新窗口打开
nasort int--排序
)
#站点设置 表

create table station(
id serial primary key,
stname varchar,--网站名称
sttitle varchar,--网站标题
stkeyword varchar,--关键词
stdescribe varchar,--描述
stLogo varchar,-- Logo
Aftertime int--售后时间
)




#广告分类表
create table advertiscategory(
id	serial primary key,
praentid int,--父级ID
adsense varchar,  --广告位置名
adintro	varchar,--广告简介
adtype varchar,--广告种类
addisplay varchar,--广告展示方式
adclsaa varchar, --广告标记
show int2 --是否显示广告
adwide int,--广告宽度
adheight int--广告高度
)

#广告内容表
create table advertisContent(
id serial primary key,
acid int,--关联广告位表 praentid
acname varchar, --广告位名称
actitle varchar,--广告标题
resource varchar,--资源
acurl varchar,--广告url
startime timestamp,--开始时间
endtime timestamp,--结束时间
sort int --排序
)

#展示类目表
create table  display(
id serial primary key,
praentid int,--父级ID
dpclass varchar,--分类管理
dpurl varchar ,--url
dpsuperior varchar,--上级分类
adadvert varchar,--选着广告
isshow int, --是否显示广告
dpicon varchar,--分类图标
dpsort int --排序
)


#买家收货地址表
create table shippingaddress(
  id bigserial primary key,
  memberid int not null,
  shipto varchar not null, --收货人
  regionid int, --地区代码
  address varchar not null,--地址
  zipcode varchar,--邮编
  phone varchar not null,--手机号码
  isdefault int2 ,--是否默认使用该地址
)
#银行帐号表
create table bankAccount(
 id serial primary key,
 memberId int not null ,--关联menber表
 bankAccountName varchar,--开户银行 
 bankAccountNumber varchar,--开户帐户
 bankName varchar, --开户行
 bankUserName varchar, --开户名
 taxRegistrationCertificate varchar --税号
 isdefault int2 ,--是否默认使用该地址
 batype int2 --买家银行卡1；卖家银行卡0

)



# 同义词管理

```sql
create table synonym(
  id serial primary key,
  words varchar[] default ARRAY[]::varchar[],
  createDt timestamp
);
```











# table - 搜索相关

## 产品检索表

```sql
CREATE TABLE productSearch(
  productid bigserial primary key, -- product id
  sindex tsvector
);

create index index_productSearch_sindex on productSearch using gin(sindex);
```

## 搜索筛选相关

```sql
drop function product_list_group_limit;
drop view product_store_rank_view;

-- 用于下面的plsql
create or replace view product_store_rank_view as 
select p.*, pst.storeid, pst.storename, pst.stepwiseprice, pst.startnum, pst.prodprice, pst.threeprice, pst.ninetyprice, pst.thirtyprice, pst.sixtyprice, pst.intervalprice, pst.marketprice, pst.costprice, pst.pdstorenum, pst.storeunit, pst.aftersale, pst.location, pst.freightmode,m.membersettingstate,B.pic,rank() 
over(partition by p.productnameid,p.brandid,p.mark order by p.id desc) 
from productinfo p,productstore pst,member m ,brand B limit 0;

-- 从start个开始（0开始），取max个, max=-1 则全部
create or replace function product_list_group_limit(query_sql text, start int, max int) returns setof product_store_rank_view as $$
declare
  sum int := 0; -- 总组数
  flag int := -1;  -- 第几组
  result product_store_rank_view;
  cursor refcursor;
begin
  open cursor for execute query_sql;
  while(true) loop
    fetch cursor into result;
    if found=false then exit; end if;
    if result.rank=1 then
      flag:=flag+1;
    end if;
    if (max>-1 and flag>=start and flag<start+max) or (max=-1 and flag>=start) then
      RETURN NEXT result; -- 返回 SELECT 的当前行
    ELSIF (flag<start) then
      continue;
    ELSE
      exit;
    end if;
  end loop;
  close cursor;
  return;
end;
$$ LANGUAGE plpgsql;
```

## es product doc mapping

```json
{
    "info": {
        "properties": {
            "id": { "type":  "long" },
            "level1": {"type":  "keyword"},
            "level1id": {"type":  "long"},
            "level2": {"type":  "keyword"},
            "level2id": {"type":  "long"},
            "level3": {"type":  "keyword"},
            "level3id": {"type":  "long"},
            "productsno": {"type":  "keyword"},
            "productnameid": {"type":  "long"},
            "productname": {"type":  "keyword"},
            "productalias": {"type":  "text"},
            "subtitle": {"type":  "text"},
            "brand": {"type":  "keyword"},
            "brandid": {"type":  "long"},
            "materialid": {"type":  "long"},
            "material": {"type":  "keyword"},
            "mark": {"type":  "text"},
            "producttype": {"type":  "keyword"},
            "unit": {"type":  "keyword"},
            "surfacetreatment": {"type":  "keyword"},
            "weight": {"type":  "double"},
            "packagetype": {"type":  "keyword"},
            "recommended": {"type":  "boolean"},
            "pdstate": {"type":  "long"},
            "pddrawing": {"type":  "keyword"},
            "pdpicture": {"type":  "text"},
            "pddes": {"type":  "keyword"},
            "specificationparam": {"type":  "keyword"},
            "seokey": {"type":  "text"},
            "seovalue": {"type":  "text"},
            "createtime": {"type":  "date"},
            "audittime": {"type":  "date"},
            "auditname": {"type":  "keyword"},
            "reason": {"type":  "text"},
            "uptime": {"type":  "date"},
            "downtime": {"type":  "date"},
            "salesnum": {"type":  "long"},
            "cardnumid": {"type":  "long"},
            "cardnum": {"type":  "keyword"},
            "stand": {"type":  "keyword"},
            "seotitle": {"type":  "text"},
            "updatetime": {"type":  "date"},
            "productid": {"type":  "long"},
            "prodstoreunit": {"type":  "keyword"},
            "unitrate": {"type":  "double"},
            "minprice": {"type":  "double"},
            "heightprice": {"type":  "double"},
            "selfsupport": {"type":  "boolean"},
            "type": {"type":  "long"},
            "indexes":{"type": "text", "analyzer": "whitespace"},
            "membersettingstate":{"type":  "long"},
            "pic":{"type":"keyword"},
            "stores":{
                "type": "nested",
                "properties":{
                    "storeid": {"type":  "long"},
                    "storename": {"type":  "keyword"},
                    "stepwiseprice": {"type":  "boolean"},
                    "startnum": {"type":  "double"},
                    "prodprice": {"type":  "double"},
                    "threeprice": {"type":  "double"},
                    "ninetyprice": {"type":  "double"},
                    "thirtyprice": {"type":  "double"},
                    "sixtyprice": {"type":  "double"},
                    "intervalprice": {"type":  "keyword"},
                    "marketprice": {"type":  "double"},
                    "costprice": {"type":  "double"},
                    "pdstorenum": {"type":  "double"},
                    "storeunit": {"type":  "keyword"},
                    "aftersale": {"type":  "keyword"},
                    "location": {"type":  "text"},
                    "freightmode":{"type":"long"},
                    "minplus": {"type":  "double"},
                    "pdno": {"type":  "keyword"},
                    "attrlist":{
                        "type": "nested",
                        "properties":{
                            "attribute":{"type":"keyword"},
                            "value":{"type":"keyword"}
                        }
                    }
                }
            }
        }
    }
}
```



#table 短信日志

```sql
create table smsLog(
id bigserial primary key,
memberId int, --会员id
mobile varchar,
type varchar,
createDate timestamp,  --发送时间
ip varchar,
verifyCode varchar, --验证码
description varchar --发送内容
)
```
#table 发票信息
create table invoiceinfo(
id bigserial primary key,
memberId  bigint not null,--用户id
invoiceheadup varchar not null,--发票抬头
bankofaccounts varchar not null,--开户行
texno varchar not null,--税号
account varchar not null,--账号
address varchar not null,--地址
linkman varchar not null,--联系人
phone varchar not null, --电话
defaultbill smallint default 0 not null,--类型 0-不是默认、1-默认 
available smallint default 0 not null,-- 企业级发票 0：不是 1：是
createDate timestamp, --添加时间
updateDate timestamp --修改时间
)



# 分单相关

## 母单

```sql
create table ordermain(
    id bigserial primary key,
    ordermoney decimal(12,2), --- 订单总金额
    freight decimal(12,2), -- 运费
    couponid int,	-- 优惠券id
    couponmoney decimal(12,2),
    type int,  --- 1-普通，2-商家合单
    createDt timestamp
);
```

