package project.jinshang.mod_checkstore.mapper;

import org.apache.ibatis.jdbc.SQL;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.utils.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ProductStoreCheckSearchProvide {



    public String countSearchWithKeys(
            String query, String level1, String level2, String level3,
            String productname, String brand, String cardnum, String material, String surfacetreatment,
            Map<String,Object> attrs,String memberids){
        String memberidsr = memberids.replace("|",",");
        SQL sql = new SQL(){{
            SELECT("count(p.id)");
            FROM("productinfo p").INNER_JOIN("productstore pst on p.id=pst.pdid");

            WHERE("pdstate=6 and producttype='"+ AppConstant.FASTENER_PRO_TYPE+"' ");

//            for(int i=0;i<memberArr.length;i++){
//               /* if(i==0){
//                    append(" and (" );
//                }*/
//                OR();
//                WHERE(" p.memberid='"+memberArr[i]+"'");
//               /* if(i==memberArr.length){
//                    append(" )" );
//                }*/
//            }
            if(StringUtils.hasText(level1)) WHERE("level1='"+level1+"'");
            if(StringUtils.hasText(level2)) WHERE("level2='"+level2+"'");
            if(StringUtils.hasText(level3)) WHERE("level3='"+level3+"'");
            if(StringUtils.hasText(productname)) WHERE("productname='"+productname+"'");
            if(StringUtils.hasText(brand)) WHERE("brand='"+brand+"'");
            if(StringUtils.hasText(cardnum)) WHERE("cardnum='"+cardnum+"'");
            if(StringUtils.hasText(material)) WHERE("material='"+material+"'");
            if(StringUtils.hasText(surfacetreatment)) WHERE("surfacetreatment='"+surfacetreatment+"'");

            WHERE( "p.memberid in ("+memberidsr+")" );


            if(attrs!=null && attrs.keySet().size()>0){
//                LEFT_OUTER_JOIN("productattr attr on attr.productid=p.id");
//                AND();
//                StringBuilder tmp = new StringBuilder();
//                for(String key: attrs.keySet()){
//                    tmp.append("attribute='"+key+"' and value='"+attrs.get(key)+"'").append(" or ");
//                }
//                WHERE("("+tmp.substring(0,tmp.length()-4)+")");


                AND();
                String[] strArr = new String[]{"A","B","C","D","E","F","H","I","G"};
                List<String> list = Arrays.asList(strArr);
                StringBuilder tmp = new StringBuilder("p.id in (select DISTINCT(A.productid) from ");
                int i=0;


                int size = attrs.size();

                for (int s = 0; s < size; s++) {
                    tmp.append("productattr " + list.get(s));
                    if (s != (size - 1)) {
                        tmp.append(",");
                    }
                }

                tmp.append(" where ");

                for (String key : attrs.keySet()) {
                    if(size>1 && i<size-1){
                        tmp.append(list.get(i)+".productid=").append(list.get(i+1)+".productid ");
                        tmp.append(" and ");
                    }
                    i++;
                }

                i =0;

                for(String key : attrs.keySet()){
                    tmp.append(list.get(i)+".attribute='"+key+"' and "+list.get(i)+".value='"+attrs.get(key)+"'");
                    if(size>1 && i<size-1){
                        tmp.append(" and ");
                    }
                    i++;
                }

                tmp.append(")");

                WHERE(tmp.toString());


            }
        }};
        if(query != null && StringUtils.hasText(query)) {
            sql.INNER_JOIN("productSearch ps on p.id=ps.productid");
            search_params_where(sql, query);
        }
//        SQL sql2 = new SQL(){{
//            SELECT("count(*)");
//            FROM("("+sql.toString()+") foo");
//        }};
        //System.out.println(sql.toString());
        return sql.toString();
    }


    public String searchWithKeys(
            String query,String level1,String level2,String level3,
            String productname,String brand,String cardnum,String material,String surfacetreatment, Map<String,Object> attrs,
            int start,int max,String memberids){
        String memberidsr = memberids.replace("|",",");
        SQL sql = new SQL(){{

            SELECT("p.*","pst.storeid, pst.storename, pst.stepwiseprice, pst.startnum, pst.prodprice, pst.threeprice, pst.ninetyprice, pst.thirtyprice, pst.sixtyprice, pst.intervalprice, pst.marketprice, pst.costprice, pst.pdstorenum, pst.storeunit, pst.aftersale, pst.location, pst.freightmode,pst.pdno,M.membersettingstate,B.pic");
            FROM("productinfo p").INNER_JOIN("productstore pst on p.id=pst.pdid").INNER_JOIN(" Member M on p.memberid=M.id ").LEFT_OUTER_JOIN(" Brand B on P.brandid=B.id ");

            WHERE("pdstate=6 and producttype='"+AppConstant.FASTENER_PRO_TYPE+"' ");

            if(StringUtils.hasText(level1)) WHERE("level1='"+level1+"'");
            if(StringUtils.hasText(level2)) WHERE("level2='"+level2+"'");
            if(StringUtils.hasText(level3)) WHERE("level3='"+level3+"'");
            if(StringUtils.hasText(productname)) WHERE("productname='"+productname+"'");
            if(StringUtils.hasText(brand)) WHERE("brand='"+brand+"'");
            if(StringUtils.hasText(cardnum)) WHERE("cardnum='"+cardnum+"'");
            if(StringUtils.hasText(material)) WHERE("material='"+material+"'");
            if(StringUtils.hasText(surfacetreatment)) WHERE("surfacetreatment='"+surfacetreatment+"'");
            WHERE( "p.memberid in ("+memberidsr+")" );
            if(attrs!=null && attrs.keySet().size()>0){
//                LEFT_OUTER_JOIN("productattr attr on attr.productid=p.id");
//                AND();
//                StringBuilder tmp = new StringBuilder();
//                for(String key: attrs.keySet()){
//                    tmp.append("attribute='"+key+"' and value='"+attrs.get(key)+"'").append(" or ");
//                }
//                WHERE("("+tmp.substring(0,tmp.length()-4)+")");

                // p.id in (select DISTINCT(a.productid) from productattr a,productattr b where a.productid=b.productid and
                // a.attribute='长度' and a.value='100' and b.attribute='公称直径' and b.value='M10')
                AND();
                String[] strArr = new String[]{"A","B","C","D","E","F","H","I","G"};
                List<String> list = Arrays.asList(strArr);
                StringBuilder tmp = new StringBuilder("p.id in (select DISTINCT(A.productid) from ");
                int i=0;


                int size = attrs.size();

                for (int s = 0; s < size; s++) {
                    tmp.append("productattr " + list.get(s));
                    if (s != (size - 1)) {
                        tmp.append(",");
                    }
                }

                tmp.append(" where ");

                for (String key : attrs.keySet()) {
                    if(size>1 && i<size-1){
                        tmp.append(list.get(i)+".productid=").append(list.get(i+1)+".productid ");
                        tmp.append(" and ");
                    }
                    i++;
                }

                i =0;

                for(String key : attrs.keySet()){
                    tmp.append(list.get(i)+".attribute='"+key+"' and "+list.get(i)+".value='"+attrs.get(key)+"'");
                    if(size>1 && i<size-1){
                        tmp.append(" and ");
                    }
                    i++;
                }

                tmp.append(")");

                WHERE(tmp.toString());
            }
        }};

        if(query != null && StringUtils.hasText(query)) {
            sql.INNER_JOIN("productSearch ps on p.id=ps.productid");

            search_params_where(sql, query);
            search_params_orderby(sql,query);
        }

      /*  if(StringUtils.hasText(sorttype)){
            sql.ORDER_BY(sorttype);
        }*/

        sql.ORDER_BY(" selfsupport desc");
        //return  sql.toString()+" LIMIT "+max+" OFFSET "+start;
        return  sql.toString();

//        String searchSql =  "select foo.*, rank() over(partition by productnameid,brandid,mark,pdno order by id desc) from( "+sql.toString()+") foo ";
//        return useFunction(searchSql,start,max,sorttype);
    }


    private void search_params_where(SQL sql,String query){
        sql.WHERE("ps.sindex @@ '"+query+"'::tsquery");
    }

    private void search_params_orderby(SQL sql,String query){
        sql.ORDER_BY("ts_rank(ps.sindex, '"+query+"'::tsquery) desc");
    }

    public String searchKeys(String query, String level1, String level2, String level3,
                             String productname, String brand, String cardnum,
                             String material, String surfacetreatment,Map<String,Object> attrs){
        SQL sql = new SQL(){{
            SELECT_DISTINCT("p.level1","p.level2","p.level3","p.productname","p.brand","p.cardnum","p.material","p.surfacetreatment");
            FROM("productinfo p");

            //如果涉及库存和远期的搜索链接库存表
           /* if(havestore == 1 || forwardtime==1) {
                INNER_JOIN("productstore pst on p.id=pst.pdid");
            }*/
            WHERE("producttype='"+AppConstant.FASTENER_PRO_TYPE+"' and pdstate=6 ");
            if(StringUtils.hasText(level1)) WHERE("level1='"+level1+"'");
            if(StringUtils.hasText(level2)) WHERE("level2='"+level2+"'");
            if(StringUtils.hasText(level3)) WHERE("level3='"+level3+"'");
            if(StringUtils.hasText(productname)) WHERE("productname='"+productname+"'");
            if(StringUtils.hasText(brand)) WHERE("brand='"+brand+"'");
            if(StringUtils.hasText(cardnum)) WHERE("cardnum='"+cardnum+"'");
            if(StringUtils.hasText(material)) WHERE("material='"+material+"'");
            if(StringUtils.hasText(surfacetreatment)) WHERE("surfacetreatment='"+surfacetreatment+"'");

            if(attrs!=null && attrs.keySet().size()>0){
//                LEFT_OUTER_JOIN("productattr attr on attr.productid=p.id");
//                AND();
//                StringBuilder tmp = new StringBuilder();
//                for(String key: attrs.keySet()){
//                    tmp.append("attribute='"+key+"' and value='"+attrs.get(key)+"'").append(" or ");
//                }
//                WHERE("("+tmp.substring(0,tmp.length()-4)+")");

                AND();
                String[] strArr = new String[]{"A","B","C","D","E","F","H","I","G"};
                List<String> list = Arrays.asList(strArr);
                StringBuilder tmp = new StringBuilder("p.id in (select DISTINCT(A.productid) from ");
                int i=0;


                int size = attrs.size();

                for (int s = 0; s < size; s++) {
                    tmp.append("productattr " + list.get(s));
                    if (s != (size - 1)) {
                        tmp.append(",");
                    }
                }

                tmp.append(" where ");

                for (String key : attrs.keySet()) {
                    if(size>1 && i<size-1){
                        tmp.append(list.get(i)+".productid=").append(list.get(i+1)+".productid ");
                        tmp.append(" and ");
                    }
                    i++;
                }

                i =0;

                for(String key : attrs.keySet()){
                    tmp.append(list.get(i)+".attribute='"+key+"' and "+list.get(i)+".value='"+attrs.get(key)+"'");
                    if(size>1 && i<size-1){
                        tmp.append(" and ");
                    }
                    i++;
                }

                tmp.append(")");

                WHERE(tmp.toString());

            }
            GROUP_BY("p.level1","p.level2","p.level3","p.productname","p.brand","p.cardnum","p.material","p.surfacetreatment");
        }};
        if(query != null && StringUtils.hasText(query)) {
            sql.INNER_JOIN("productSearch ps on p.id=ps.productid");
            search_params_where(sql, query);
        }
        return sql.toString();
    }

    public String searchAttrKeys(String query,String level1,String level2,String level3,
                                 String productname,String brand,String cardnum,
                                 String material, String surfacetreatment,Map<String,Object> attrs){
        String[] keys = new String[]{"attr.attribute","attr.value"};
        SQL sql = new SQL(){{
            SELECT(keys);
            FROM("productinfo p");

           /* //如果涉及库存和远期的搜索链接库存表
            if(havestore == 1 || forwardtime==1) {
                INNER_JOIN("productstore pst on p.id=pst.pdid");
            }*/
            LEFT_OUTER_JOIN("productattr attr on attr.productid=p.id");

            WHERE(" p.producttype='"+AppConstant.FASTENER_PRO_TYPE+"' ");
            if(StringUtils.hasText(level1)) WHERE("level1='"+level1+"'");
            if(StringUtils.hasText(level2)) WHERE("level2='"+level2+"'");
            if(StringUtils.hasText(level3)) WHERE("level3='"+level3+"'");
            if(StringUtils.hasText(productname)) WHERE("productname='"+productname+"'");
            if(StringUtils.hasText(brand)) WHERE("brand='"+brand+"'");
            if(StringUtils.hasText(cardnum)) WHERE("cardnum='"+cardnum+"'");
            if(StringUtils.hasText(material)) WHERE("material='"+material+"'");
            if(StringUtils.hasText(surfacetreatment)) WHERE("surfacetreatment='"+surfacetreatment+"'");


            if(attrs!=null && attrs.keySet().size()>0){
                AND();
                StringBuilder tmp = new StringBuilder();
                for(String key: attrs.keySet()){
                    tmp.append("attribute='"+key+"' and value='"+attrs.get(key)+"'").append(" or ");
                }
                WHERE("("+tmp.substring(0,tmp.length()-4)+")");
            }
            GROUP_BY(keys);
        }};
        if(query != null && StringUtils.hasText(query)) {
            sql.INNER_JOIN("productSearch ps on p.id=ps.productid");
            search_params_where(sql, query);
        }
        return sql.toString();
    }

    public String searchAttrKeysHashAttr(String query,String level1,String level2,String level3,
                                         String productname,String brand,String cardnum,
                                         String material, String surfacetreatment,Map<String,Object> attrs){
        String[] keys = new String[]{"attr.attribute","attr.value"};
        SQL sql = new SQL(){{
            SELECT(keys);
            FROM("productinfo p");

            //如果涉及库存和远期的搜索链接库存表
            /*if(havestore == 1 || forwardtime==1) {
                INNER_JOIN("productstore pst on p.id=pst.pdid");
            }*/
            LEFT_OUTER_JOIN("productattr attr on attr.productid=p.id");

            WHERE(" p.producttype='"+AppConstant.FASTENER_PRO_TYPE+"' ");
            if(StringUtils.hasText(level1)) WHERE("level1='"+level1+"'");
            if(StringUtils.hasText(level2)) WHERE("level2='"+level2+"'");
            if(StringUtils.hasText(level3)) WHERE("level3='"+level3+"'");
            if(StringUtils.hasText(productname)) WHERE("productname='"+productname+"'");
            if(StringUtils.hasText(brand)) WHERE("brand='"+brand+"'");
            if(StringUtils.hasText(cardnum)) WHERE("cardnum='"+cardnum+"'");
            if(StringUtils.hasText(material)) WHERE("material='"+material+"'");
            if(StringUtils.hasText(surfacetreatment)) WHERE("surfacetreatment='"+surfacetreatment+"'");


            if(attrs!=null && attrs.keySet().size()>0){
//                AND();
//                StringBuilder tmp = new StringBuilder();
//                for(String key: attrs.keySet()){
//                    tmp.append("attribute !='"+key+"' and value !='"+attrs.get(key)+"'").append(" and ");
//                }
//                WHERE("("+tmp.substring(0,tmp.length()-4)+")");


                AND();
                String[] strArr = new String[]{"A","B","C","D","E","F","H","I","G"};
                List<String> list = Arrays.asList(strArr);
                StringBuilder tmp = new StringBuilder("p.id in (select DISTINCT(A.productid) from ");
                int i=0;


                int size = attrs.size();

                for (int s = 0; s < size; s++) {
                    tmp.append("productattr " + list.get(s));
                    if (s != (size - 1)) {
                        tmp.append(",");
                    }
                }

                tmp.append(" where ");

                for (String key : attrs.keySet()) {
                    if(size>1 && i<size-1){
                        tmp.append(list.get(i)+".productid=").append(list.get(i+1)+".productid ");
                        tmp.append(" and ");
                    }
                    i++;
                }

                i =0;

                for(String key : attrs.keySet()){
                    tmp.append(list.get(i)+".attribute='"+key+"' and "+list.get(i)+".value='"+attrs.get(key)+"'");
                    if(size>1 && i<size-1){
                        tmp.append(" and ");
                    }
                    i++;
                }

                tmp.append(")");

                WHERE(tmp.toString());



            }
            GROUP_BY(keys);
        }};
        if(query != null && StringUtils.hasText(query)) {
            sql.INNER_JOIN("productSearch ps on p.id=ps.productid");
            search_params_where(sql, query);
        }
        return sql.toString();
    }

}
