package project.jinshang.mod_product.bean;

/**
 *紧商网对接中间件开放平台的类别代码
 * @author xiazy
 * @create 2018-06-05 11:35
 **/
public enum DockType {
    JS001("js001","订单下达(主动)"),
    JS003("js003","订单取消(主动)"),
    JS002("js002","订单状态回写(被动)"),
    JS004("js004","退货(主动)"),
    JS005("js005","库存同步(被动)"),
    JS006("js006","库存同步(主动)"),
    //以下的为订单取消的类型
    QX001("qx001","销售订单"),
    QX002("qx002","销售退款");

    public enum OrderStatusType{
        //以下为中间件管理平台返回的订单的状态
        ZT001("zt001","已确认",8,"备货中"),
        ZT002("zt002","拣货中",8,"备货中"),
        ZT003("zt003","待出库",9,"备货完成"),
        ZT004("zt004","已出库",3,"待收货");
        private String OriOrderType;
        private String OriOrderTypeDesc;
        private Short tranOrderType;
        private String tranOrderTypeDesc;

        OrderStatusType(String oriOrderType, String oriOrderTypeDesc, int tranOrderType, String tranOrderTypeDesc) {
            OriOrderType = oriOrderType;
            OriOrderTypeDesc = oriOrderTypeDesc;
            this.tranOrderType = (short)tranOrderType;
            this.tranOrderTypeDesc = tranOrderTypeDesc;
        }

        public  static  Short transOrderStatus(String orderStatus){
            for (OrderStatusType orderStatusType:OrderStatusType.values()) {
                if (orderStatusType.OriOrderType.equals(orderStatus)){
                    return orderStatusType.tranOrderType;
                }
            }
            return null;
        }

        public String getOriOrderType() {
            return OriOrderType;
        }

        public void setOriOrderType(String oriOrderType) {
            OriOrderType = oriOrderType;
        }

        public String getOriOrderTypeDesc() {
            return OriOrderTypeDesc;
        }

        public void setOriOrderTypeDesc(String oriOrderTypeDesc) {
            OriOrderTypeDesc = oriOrderTypeDesc;
        }

        public Short getTranOrderType() {
            return tranOrderType;
        }

        public void setTranOrderType(Short tranOrderType) {
            this.tranOrderType = tranOrderType;
        }

        public String getTranOrderTypeDesc() {
            return tranOrderTypeDesc;
        }

        public void setTranOrderTypeDesc(String tranOrderTypeDesc) {
            this.tranOrderTypeDesc = tranOrderTypeDesc;
        }
    }
    private String type;
    private String value;

    DockType(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
