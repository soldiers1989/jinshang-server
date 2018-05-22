drop table products;

/*==============================================================*/
/* Table: products                                              */
/*==============================================================*/
create table products (
   id                   BIGSERIAL not null,
   level1               VARCHAR         not null default '',
   level1id             INT8                 not null,
   level2               VARCHAR         not null default '',
   level2id             INT8                 not null,
   level3               VARCHAR         not null default '',
   level3id             INT8                 not null,
   productno            VARCHAR              null,
   productnameid        INT8                 not null default 0,
   productname          VARCHAR         not null default '',
   productalias         VARCHAR              not null default '',
   brand                VARCHAR         not null default '',
   brandid              INT8                 not null default 0,
   cardnumid            INT8                 null default 0,
   createtime           TIMESTAMP            not null default CURRENT_TIMESTAMP,
   materialid           INT8                 not null default 0,
   material             VARCHAR              not null default '',
   mark                 VARCHAR         not null default '',
   unit                 VARCHAR        not null default '',
   surfacetreatment     VARCHAR         not null default '',
   packagetype          VARCHAR              null,
   weight               NUMERIC              not null default 0,
   pddrawing            VARCHAR[]             ,
   pdpicture            VARCHAR[]        ,
   pddes                VARCHAR         not null default '',
   chicunvalue          VARCHAR              null,
   changduvalue         VARCHAR              null,
   yajuvalue            VARCHAR              null,
   cardnum              VARCHAR              null default '',
   constraint PK_PRODUCTINFO primary key (id)
);

comment on table products is
'商品信息表';

comment on column products.id is
'主键';

comment on column products.level1 is
'一级分类';

comment on column products.level1id is
'一级分类id';

comment on column products.level2 is
'二级分类';

comment on column products.level2id is
'二级分类id';

comment on column products.level3 is
'三级分类';

comment on column products.level3id is
'三级分类id';

comment on column products.productno is
'商品编号（新增）';

comment on column products.productnameid is
'品名id';

comment on column products.productname is
'商品名称';

comment on column products.productalias is
'商品别名';

comment on column products.brand is
'品牌';

comment on column products.brandid is
'品牌id';

comment on column products.cardnumid is
'牌号id';

comment on column products.createtime is
'创建时间';

comment on column products.materialid is
'材质id';

comment on column products.material is
'材质';

comment on column products.mark is
'印记';

comment on column products.unit is
'计量单位';

comment on column products.surfacetreatment is
'表面处理';

comment on column products.packagetype is
'包装方式(新增)';

comment on column products.weight is
'重量';

comment on column products.pddrawing is
'商品图纸';

comment on column products.pdpicture is
'商品图片';

comment on column products.pddes is
'商品描述';

comment on column products.chicunvalue is
'尺寸值';

comment on column products.changduvalue is
'长度值';

comment on column products.yajuvalue is
'牙距值';

comment on column products.cardnum is
'牌号';

























drop table productinfo;

/*==============================================================*/
/* Table: productinfo                                           */
/*==============================================================*/
create table productinfo (
   id                   SERIAL not null,
   memberid             INT8                 not null,
   level1               VARCHAR         not null default '',
   level1id             INT8                 not null,
   level2               VARCHAR         not null default '',
   level2id             INT8                 not null,
   level3               VARCHAR         not null default '',
   level3id             INT8                 not null,
   pdno                 VARCHAR         not null default '',
   productnameid        INT8                 not null default 0,
   productname          VARCHAR         not null default '',
   productalias         VARCHAR              not null default '',
   subtitle             VARCHAR              not null default '',
   brand                VARCHAR         not null default '',
   brandid              INT8                 not null default 0,
   materialid           INT8                 not null default 0,
   material             VARCHAR              not null default '',
   mark                 VARCHAR         not null default '',
   producttype          VARCHAR         not null default '',
   unit                 VARCHAR         not null default '',
   surfacetreatment     VARCHAR         not null default '',
   weight               NUMERIC              not null default 0,
   packagetype          VARCHAR              null default '‘’',
   recommended          BOOL                 not null,
   pdstate              INT2                 not null default 0,
   pddrawing            VARCHAR[]             null,
   pdpicture            VARCHAR[]          null,
   pddes                VARCHAR         not null default '',
   specificationparam   VARCHAR         not null default '',
   seokey               VARCHAR        not null default '',
   seovalue             VARCHAR         not null default '',
   createtime           TIMESTAMP            not null default CURRENT_TIMESTAMP,
   audittime            TIMESTAMP            not null default CURRENT_TIMESTAMP,
   auditname            VARCHAR         not null default '',
   reason               VARCHAR         not null default '',
   uptime               TIMESTAMP            not null default CURRENT_TIMESTAMP,
   downtime             TIMESTAMP            not null default CURRENT_TIMESTAMP,
   tag                  VARCHAR         null,
   salesnum             INT8                 null default 0,
   constraint PK_PRODUCTINFO primary key (id)
);

comment on table productinfo is
'商品信息表';

comment on column productinfo.id is
'主键';

comment on column productinfo.memberid is
'卖家id';

comment on column productinfo.level1 is
'一级分类';

comment on column productinfo.level1id is
'一级分类id';

comment on column productinfo.level2 is
'二级分类';

comment on column productinfo.level2id is
'二级分类id';

comment on column productinfo.level3 is
'三级分类';

comment on column productinfo.level3id is
'三级分类id';

comment on column productinfo.pdno is
'商品货号';

comment on column productinfo.productnameid is
'品名id';

comment on column productinfo.productname is
'商品名称';

comment on column productinfo.productalias is
'商品别名';

comment on column productinfo.subtitle is
'商品副标题';

comment on column productinfo.brand is
'品牌';

comment on column productinfo.brandid is
'品牌id';

comment on column productinfo.materialid is
'材质id';

comment on column productinfo.material is
'材质';

comment on column productinfo.mark is
'印记';

comment on column productinfo.producttype is
'产品类型';

comment on column productinfo.unit is
'计量单位';

comment on column productinfo.surfacetreatment is
'表面处理';

comment on column productinfo.weight is
'重量';

comment on column productinfo.packagetype is
'包装方式';

comment on column productinfo.recommended is
'商品是否推荐';

comment on column productinfo.pdstate is
'商品状态0=放入仓库1=待审核2=审核通过3=未通过4=已上架5=下架6=删除7=违规下架';

comment on column productinfo.pddrawing is
'商品图纸';

comment on column productinfo.pdpicture is
'商品图片';

comment on column productinfo.pddes is
'商品描述';

comment on column productinfo.specificationparam is
'规格参数';

comment on column productinfo.seokey is
'seo关键字';

comment on column productinfo.seovalue is
'seo描述';

comment on column productinfo.createtime is
'创建时间';

comment on column productinfo.audittime is
'审核时间';

comment on column productinfo.auditname is
'审核人';

comment on column productinfo.reason is
'审核不通过原因';

comment on column productinfo.uptime is
'上架时间';

comment on column productinfo.downtime is
'下架时间';

comment on column productinfo.tag is
'标签';

comment on column productinfo.salesnum is
'销量';



drop table store;

/*==============================================================*/
/* Table: store     仓库表                                            */
/*==============================================================*/
create table store (
   id                   SERIAL               not null,
   name                 VARCHAR(100)         not null default '',
   memberid             INT8                 not null,
   createtime           TIMESTAMP            not null default CURRENT_TIMESTAMP,
   address              VARCHAR(100)         not null default '',
   constraint PK_STORE primary key (id)
);

comment on table store is
'仓库表';

comment on column store.id is
'主键';

comment on column store.name is
'仓库名称';

comment on column store.memberid is
'会员id';

comment on column store.createtime is
'创建时间';

comment on column store.address is
'仓库地址';



drop table brand;

/*==============================================================*/
/* Table: brand       品牌表                                          */
/*==============================================================*/
create table brand (
   id                   SERIAL               not null,
   name                 VARCHAR(100)         not null default '',
   category             VARCHAR(100)         not null default '',
   categoryid           INT8                 not null,
   pic                  VARCHAR(100)         not null default '',
   memberid             INT8                 not null,
   auditname            INT8                 not null,
   audittime            TIMESTAMP            not null default CURRENT_TIMESTAMP,
   reason               VARCHAR(100)         not null default '',
   auditstate           INT2                 not null default 0,
   ceatetime            TIMESTAMP            not null default CURRENT_TIMESTAMP,
   constraint PK_BRAND primary key (id)
);

comment on table brand is
'品牌表';

comment on column brand.id is
'主键';

comment on column brand.name is
'品牌名称';

comment on column brand.category is
'品牌分类';

comment on column brand.categoryid is
'分类id';

comment on column brand.pic is
'品牌图片';

comment on column brand.memberid is
'提交人id';

comment on column brand.auditname is
'审核人id';

comment on column brand.audittime is
'审核时间';

comment on column brand.reason is
'不通过原因';

comment on column brand.auditstate is
'审核状态';

comment on column brand.ceatetime is
'创建时间';



drop table productstore;

/*==============================================================*/
/* Table: productstore                                          */
/*==============================================================*/
create table productstore (
   id                   BIGSERIAL not null,
   pdid                 INT8                 not null,
   storeid              INT8                 not null,
   storename            varchar              null,
   stepwiseprice        BOOL                 not null,
   startnum             int                  not null default 0,
   prodprice            NUMERIC              null default 0,
   threeprice           NUMERIC              null default 0,
   ninetyprice           NUMERIC              null default 0,
   thirtyprice          NUMERIC              null default 0,
   sixtyprice           NUMERIC              null default 0,
   intervalprice        VARCHAR              null,
   marketprice          NUMERIC              not null default 0,
   costprice            NUMERIC              not null default 0,
   pdstorenum           int4                 not null default 0,
   storeunit            VARCHAR(100)         not null default '',
   aftersale            VARCHAR(100)         not null default '',
   location             VARCHAR(100)         not null default '',
   freightmode          int8         not null,
   constraint PK_PRODUCTSTROE primary key (id)
);

comment on table productstore is
'仓库商品表';

comment on column productstore.id is
'主键';

comment on column productstore.pdid is
'商品id';

comment on column productstore.storeid is
'仓库id';

comment on column productstore.storename is
'storename';

comment on column productstore.startnum is
'起定量';

comment on column productstore.prodprice is
'商品价格';

comment on column productstore.threeprice is
'3天价格';

comment on column productstore.ninetyprice is
'7天价格';

comment on column productstore.thirtyprice is
'30天价格';

comment on column productstore.sixtyprice is
'60天价格';

comment on column productstore.intervalprice is
'区间价格和折扣';

comment on column productstore.marketprice is
'市场价';

comment on column productstore.costprice is
'成本价';

comment on column productstore.pdstorenum is
'商品库存';

comment on column productstore.storeunit is
'商品库存单位';

comment on column productstore.aftersale is
'包装售后';

comment on column productstore.location is
'所在地';

comment on column productstore.freightmode is
'运费方式';



drop table orders;

/*==============================================================*/
/* Table: orders                                                */
/*==============================================================*/
create table orders (
   id                   BIGSERIAL               not null,
   memberid             INT8                 not null,
   saleid               INT8                 not null,
   createtime           TIMESTAMP            not null default CURRENT_TIMESTAMP,
   receivingaddress     VARCHAR(100)         not null default '',
   freight              NUMERIC              not null default 0,
   mailornot            BOOL                 not null default FALSE,
   totalprice           NUMERIC              not null default 0,
   integral             INT4                 not null default 0,
   orderno              VARCHAR(100)         not null default '',
   code                 varchar              not null,
   transactionnumber    VARCHAR(100)         not null default '',
   paymentmethod        INT2                 not null default 0,
   paymenttime          TIMESTAMP            not null default CURRENT_TIMESTAMP,
   deliverytime         TIMESTAMP            not null default CURRENT_TIMESTAMP,
   orderstatus          INT2                 not null default 0,
   actualpayment        NUMERIC              not null default 0,
   couriernumber        INT4                 not null default 0,
   logisticscompany     VARCHAR(100)         not null default '',
   deliverymode         INT2                 not null default 0,
   ordertype            INT2                 not null default 0,
   deliveryno           varchar              null default '',
   constraint PK_ORDERS primary key (id)
);

comment on table orders is
'订单表';

comment on column orders.id is
'主键';

comment on column orders.memberid is
'会员id';

comment on column orders.saleid is
'卖家id';

comment on column orders.createtime is
'创建时间';

comment on column orders.receivingaddress is
'收货地址';

comment on column orders.freight is
'运费';

comment on column orders.mailornot is
'是否包邮';

comment on column orders.totalprice is
'订单总价';

comment on column orders.integral is
'积分';

comment on column orders.orderno is
'订单编号';

comment on column orders.code is
'合同号';

comment on column orders.transactionnumber is
'交易号';

comment on column orders.paymentmethod is
'支付方式';

comment on column orders.paymenttime is
'付款时间';

comment on column orders.deliverytime is
'发货时间';

comment on column orders.orderstatus is
'订单状态';

comment on column orders.actualpayment is
'实付款';

comment on column orders.couriernumber is
'快递单号';

comment on column orders.logisticscompany is
'物流公司';

comment on column orders.deliverymode is
'提货方式';

comment on column orders.ordertype is
'订单类型0=线上订单1=线下订单2=第三方订单';

comment on column orders.deliveryno is
'发货号';



drop table productname;

/*==============================================================*/
/* Table: productname                                           */
/*==============================================================*/
create table productname (
   id                   BIGSERIAL               not null,
   name                 VARCHAR(100)         not null default '',
   typeid               INT8                 not null,
   unit                 varchar              not null,
   mark                 varchar              null,
   prodno               varchar              null,
   constraint PK_PRODUCTNAME primary key (id)
);

comment on table productname is
'品名表';

comment on column productname.id is
'主键';

comment on column productname.name is
'名称';

comment on column productname.typeid is
'分类id';

comment on column productname.unit is
'计量单位';

comment on column productname.mark is
'备注';

comment on column productname.prodno is
'品名编号';




drop table cardnum;

/*==============================================================*/
/* Table: cardnum                                               */
/*==============================================================*/
create table cardnum (
   id                   BIGSERIAL               not null,
   name                 VARCHAR(100)         not null default '',
   matialid             INT8                 not null,
   number               INT4                 null,
   constraint PK_CARDNUM primary key (id)
);

comment on table cardnum is
'牌号';

comment on column cardnum.id is
'主键';

comment on column cardnum.name is
'名称';

comment on column cardnum.matialid is
'材质id';

comment on column cardnum.number is
'编号';






drop table orderproduct;

/*==============================================================*/
/* Table: orderproduct                                          */
/*==============================================================*/
create table orderproduct (
   id                   BIGSERIAL               not null,
   orderid              INT8                 not null,
   pdid                 INT8                 not null,
   pdname               VARCHAR(100)         not null default '',
   pddesc               VARCHAR(100)         not null default '',
   constraint PK_ORDERPRODUCT primary key (id)
);

comment on column orderproduct.id is
'主键';

comment on column orderproduct.orderid is
'订单id';

comment on column orderproduct.pdid is
'商品id';

comment on column orderproduct.pdname is
'商口品名';

comment on column orderproduct.pddesc is
'商品详情';



drop table shopcar;

/*==============================================================*/
/* Table: shopcar                                               */
/*==============================================================*/
create table shopcar (
   id                   BIGSERIAL               not null,
   memberid             INT8                 not null,
   saleid               INT8                 not null,
   createtime           TIMESTAMP            not null default CURRENT_TIMESTAMP,
   pdid                 INT8                 not null,
   pdno                 VARCHAR(100)         not null default '',
   pdnumber             INT4                 not null default 0,
   constraint PK_SHOPCAR primary key (id)
);

comment on table shopcar is
'购物车表';

comment on column shopcar.id is
'主键';

comment on column shopcar.memberid is
'会员id';

comment on column shopcar.saleid is
'卖家id';

comment on column shopcar.createtime is
'创建时间';

comment on column shopcar.pdid is
'商品id';

comment on column shopcar.pdno is
'商品编号';

comment on column shopcar.pdnumber is
'数量';




drop table attributetbl;

/*==============================================================*/
/* Table: attributetbl                                          */
/*==============================================================*/
create table attributetbl (
   id                   BIGSERIAL               not null,
   name                 VARCHAR(100)         not null default '',
   productnameid        INT8                 not null,
   remark               VARCHAR(100)         default '',
   sort                 INT4                 null,
   connector            VARCHAR              null,
   constraint PK_ATTRIBUTETBL primary key (id)
);

comment on column attributetbl.id is
'主键';

comment on column attributetbl.name is
'属性名';

comment on column attributetbl.productnameid is
'品名id';

comment on column attributetbl.remark is
'备注';

comment on column attributetbl.connector is
'连接符';







drop table attvalue;

/*==============================================================*/
/* Table: attvalue                                              */
/*==============================================================*/
create table attvalue (
   id                   BIGSERIAL               not null,
   paramvalue           VARCHAR(100)         null default '',
   attid                INT8                 null,
   mark                 varchar              null,
   sort                 int                  null,
   constraint PK_ATTVALUE primary key (id)
);

comment on column attvalue.id is
'主键';

comment on column attvalue.paramvalue is
'值';

comment on column attvalue.attid is
'属性名id';

comment on column attvalue.mark is
'备注';

comment on column attvalue.sort is
'序号';


drop table orderproductback;

/*==============================================================*/
/* Table: orderproductback      退货订单表                                */
/*==============================================================*/
create table orderproductback (
   id                   BIGSERIAL               not null,
   orderid              INT8                 not null,
   pdno                 VARCHAR(100)         not null default '',
   pdid                 INT8                 not null,
   pdname               VARCHAR(100)         not null default '',
   state                INT2                 not null default 0,
   Logisticsno          VARCHAR(100)         not null default '',
   Logisticscompany     VARCHAR(100)         not null default '',
   constraint PK_ORDERPRODUCTBACK primary key (id)
);

comment on table orderproductback is
'退货商品订单表';

comment on column orderproductback.id is
'id';

comment on column orderproductback.orderid is
'订单id';

comment on column orderproductback.pdno is
'商品编号';

comment on column orderproductback.pdid is
'商品id';

comment on column orderproductback.pdname is
'商品名称';

comment on column orderproductback.state is
'退货状态';

comment on column orderproductback.Logisticsno is
'退货物流单号';

comment on column orderproductback.Logisticscompany is
'退货物流公司';




drop table operatelog;

/*==============================================================*/
/* Table: operatelog     操作日志表                                       */
/*==============================================================*/
create table operatelog (
   id                   BIGSERIAL               not null,
   opid                 INT8                 not null,
   opname               VARCHAR(100)         not null default '',
   content              VARCHAR(100)         not null default '',
   optype               INT2                 not null default 0,
   optime               TIMESTAMP            not null default CURRENT_TIMESTAMP,
   constraint PK_OPERATELOG primary key (id)
);

comment on table operatelog is
'操作日志表';

comment on column operatelog.id is
'主键';

comment on column operatelog.opid is
'操作人id';

comment on column operatelog.opname is
'操作人';

comment on column operatelog.content is
'操作内容';

comment on column operatelog.optype is
'操作类型';

comment on column operatelog.optime is
'操作时间';




drop table material;

/*==============================================================*/
/* Table: material                                              */
/*==============================================================*/
create table material (
   id                   BIGSERIAL               not null,
   name                 VARCHAR(100)         not null default '',
   number               INT4                 null,
   constraint PK_MATERIAL primary key (id)
);

comment on table material is
'材质表';

comment on column material.id is
'主键';

comment on column material.name is
'名称';

comment on column material.number is
'编号';




drop table productattr;

/*==============================================================*/
/* Table: productattr                                           */
/*==============================================================*/
create table productattr (
   id                   BIGSERIAL               not null,
   productid            INT8                 null,
   attributeid          INT8                 null,
   attribute            varchar              null,
   value                varchar              null,
   constraint PK_PRODUCTATTR primary key (id)
);





drop table areacost;

/*==============================================================*/
/* Table: areacost                                              */
/*==============================================================*/
create table areacost (
   id                   BIGSERIAL   PRIMARY KEY,
   temid                INT8                 null,
   province             VARCHAR              null default '',
   city                 VARCHAR              null default '',
   perkilogramcost      NUMERIC              null default 0,
   defaultfreight       NUMERIC              null default 0,
   defaultcost          NUMERIC              null default 0,
   perkilogramadded     NUMERIC              null default 0
);

comment on column areacost.id is
'主键';

comment on column areacost.temid is
'模板主键';

comment on column areacost.province is
'省';

comment on column areacost.city is
'市';

comment on column areacost.perkilogramcost is
'增加运费元';

comment on column areacost.defaultfreight is
'默认运费公斤';

comment on column areacost.defaultcost is
'默认运费元';

comment on column areacost.perkilogramadded is
'每增加公斤';






drop table shippingtemplates;

/*==============================================================*/
/* Table: shippingtemplates                                     */
/*==============================================================*/
create table shippingtemplates (
   id                   BIGSERIAL               not null,
   temname              VARCHAR              null default '',
   province             VARCHAR              null default '',
   city                 VARCHAR              null default '',
   area                 VARCHAR              null default '',
   address              VARCHAR              null default '',
   isfree               INT2                 null default 0,
   counttype            INT2                 null default 0,
   defaultfreight       NUMERIC              null default 0,
   defaultcost          NUMERIC              null default 0,
   perkilogramadded     NUMERIC              null default 0,
   perkilogramcost      NUMERIC              null default 0,
   constraint PK_SHIPPINGTEMPLATES primary key (id)
);

comment on column shippingtemplates.id is
'主键';

comment on column shippingtemplates.temname is
'模板名称';

comment on column shippingtemplates.province is
'商品地址省';

comment on column shippingtemplates.city is
'商品地址市';

comment on column shippingtemplates.area is
'商品地址区';

comment on column shippingtemplates.address is
'详细地址';

comment on column shippingtemplates.isfree is
'是否包邮';

comment on column shippingtemplates.counttype is
'计价方式';

comment on column shippingtemplates.defaultfreight is
'默认运费公斤';

comment on column shippingtemplates.defaultcost is
'默认运费元';

comment on column shippingtemplates.perkilogramadded is
'每增加公斤';

comment on column shippingtemplates.perkilogramcost is
'增加运费元';




-- wms 中间件同步时的 未发送表
create table wms_middleware_msg(
  id bigserial primary key,
  url varchar,
  params varchar,
  status int, -- 0-fail 1-success-暂没有
  createDt timestamp
);