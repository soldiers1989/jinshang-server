package project.jinshang.test;

/**
 * Test1
 *
 * @author xiazy
 * @create 2018-09-14 11:18
 **/
public class Test1 {
    public static void main(String[] args) {
//            String typename="充值";
//            System.out.println(type.transfortype(typename));
        testquer();
    }

    private enum  type
    {
        TYPE01("收款",1),
        TYPE02("发货",2),
        TYPE03("退货",3),
        TYPE04("违约金",4),
        TYPE05("退款",5),
        TYPE06("充值",6),
        TYPE07("提现",7),
        TYPE08("开票",8);
        int tyep ;
        String typename;

        type(String typename,int tyep) {
            this.tyep = tyep;
            this.typename = typename;
        }


        private  static  int transfortype(String typename){
            for (type one:type.values()){
                if (typename.equals(one.typename)){
                    return one.tyep;
                }
            }
            return 0;
        }


        public int getTyep() {
            return tyep;
        }

        public void setTyep(int tyep) {
            this.tyep = tyep;
        }

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }
    }


    private static void testquer(){

    }
}
