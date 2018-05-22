package project.jinshang.mod_product.service;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * for pgsql provider
 * 业务可能变更点：
 *      searchKeys: key.
 *      分组条件。
 */
public class ProductSearchProvider {

    public String search(@Param("query")String query, int start, int max){
        SQL sql = new SQL(){{
            SELECT("p.*","pst.storeid, pst.storename, pst.stepwiseprice, pst.startnum, pst.prodprice, pst.threeprice, pst.ninetyprice, pst.thirtyprice, pst.sixtyprice, pst.intervalprice, pst.marketprice, pst.costprice, pst.pdstorenum, pst.storeunit, pst.aftersale, pst.location, pst.freightmode",
                    "rank() over(partition by p.productnameid,p.brandid,p.mark order by p.id desc)");
            FROM("productinfo p","productSearch ps","productstore pst");
            WHERE("p.id=pst.pdid");
        }};
        search_params_where(sql,query);
        search_params_orderby(sql,query);
        return useFunction(sql.toString(),start,max,null);
    }


    /*
    public String searchKeys(String query, String level1, String level2, String level3,
                             String productname, String brand, String cardnum,
                             String material, Map<String,Object> attrs,Integer selfsupport,Integer havestore,Integer forwardtime){
        SQL sql = new SQL(){{
            SELECT_DISTINCT("p.level1","p.level2","p.level3","p.productname","p.brand","p.cardnum","p.material");
            FROM("productinfo p");

            //如果涉及库存和远期的搜索链接库存表
            if(havestore == 1 || forwardtime==1) {
                INNER_JOIN("productstore pst on p.id=pst.pdid");
            }
            WHERE("producttype='"+AppConstant.FASTENER_PRO_TYPE+"' and pdstate=4 ");
            if(StringUtils.hasText(level1)) WHERE("level1='"+level1+"'");
            if(StringUtils.hasText(level2)) WHERE("level2='"+level2+"'");
            if(StringUtils.hasText(level3)) WHERE("level3='"+level3+"'");
            if(StringUtils.hasText(productname)) WHERE("productname='"+productname+"'");
            if(StringUtils.hasText(brand)) WHERE("brand='"+brand+"'");
            if(StringUtils.hasText(cardnum)) WHERE("cardnum='"+cardnum+"'");
            if(StringUtils.hasText(material)) WHERE("material='"+material+"'");

            if(selfsupport == 1){
                WHERE("p.selfsupport=true");
            }

            if(havestore == 1){
                WHERE(" pst.pdstorenum>1 ");
            }

            if(forwardtime == 1){
                WHERE( " (ninetyprice >0 or thirtyprice>0 or sixtyprice>0 ) " );
            }

            if(attrs!=null && attrs.keySet().size()>0){
                LEFT_OUTER_JOIN("productattr attr on attr.productid=p.id");
                AND();
                StringBuilder tmp = new StringBuilder();
                for(String key: attrs.keySet()){
                    tmp.append("attribute='"+key+"' and value='"+attrs.get(key)+"'").append(" or ");
                }
                WHERE("("+tmp.substring(0,tmp.length()-4)+")");
            }
            GROUP_BY("p.level1","p.level2","p.level3","p.productname","p.brand","p.cardnum","p.material");
        }};
        if(query != null && StringUtils.hasText(query)) {
            sql.INNER_JOIN("productSearch ps on p.id=ps.productid");
            search_params_where(sql, query);
        }
        return sql.toString();
    }
*/

    public String searchKeys(String query, String level1, String level2, String level3,
                             String productname, String brand, String cardnum,
                             String material, String surfacetreatment,Map<String,Object> attrs,Integer selfsupport,Integer havestore,Integer forwardtime,String store){
        SQL sql = new SQL(){{
            SELECT_DISTINCT("p.level1","p.level2","p.level3","p.productname","p.brand","p.cardnum","p.material","p.surfacetreatment","pst.storename");
            FROM("productinfo p");

            //如果涉及库存和远期的搜索链接库存表
            //if(havestore == 1 || forwardtime==1 || StringUtils.hasText(store)) {
                INNER_JOIN("productstore pst on p.id=pst.pdid");
            //}
            WHERE("producttype='"+AppConstant.FASTENER_PRO_TYPE+"' and pdstate=4 ");
            if(StringUtils.hasText(level1)) WHERE("level1='"+level1+"'");
            if(StringUtils.hasText(level2)) WHERE("level2='"+level2+"'");
            if(StringUtils.hasText(level3)) WHERE("level3='"+level3+"'");
            if(StringUtils.hasText(productname)) WHERE("productname='"+productname+"'");
            if(StringUtils.hasText(brand)) WHERE("brand='"+brand+"'");
            if(StringUtils.hasText(cardnum)) WHERE("cardnum='"+cardnum+"'");
            if(StringUtils.hasText(material)) WHERE("material='"+material+"'");
            if(StringUtils.hasText(surfacetreatment)) WHERE("surfacetreatment='"+surfacetreatment+"'");
            if(StringUtils.hasText(store)) WHERE("pst.storename='"+store+"'");

            if(selfsupport == 1){
                WHERE("p.selfsupport=true");
            }

            if(havestore == 1){
                WHERE(" pst.pdstorenum>1 ");
            }

            if(forwardtime == 1){
                WHERE( " (ninetyprice >0 or thirtyprice>0 or sixtyprice>0 ) " );
            }

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
            GROUP_BY("p.level1","p.level2","p.level3","p.productname","p.brand","p.cardnum","p.material","p.surfacetreatment","pst.storename");
        }};
        if(query != null && StringUtils.hasText(query)) {
            sql.INNER_JOIN("productSearch ps on p.id=ps.productid");
            search_params_where(sql, query);
        }
        return sql.toString();
    }




    public String otherProdSearchKeys(String query, String level1, String level2, String level3,
                             String productname, String brand, Map<String,Object> attrs,int type){
        SQL sql = new SQL(){{
            SELECT_DISTINCT("p.level1","p.level2","p.level3","p.brand");
            FROM("productinfo p");
            WHERE("producttype='"+AppConstant.OTHER_PRO_TYPE+"'");

            if(type>0){
                WHERE("p.type="+type);
            }

            if(StringUtils.hasText(level1)) WHERE("level1='"+level1+"'");
            if(StringUtils.hasText(level2)) WHERE("level2='"+level2+"'");
            if(StringUtils.hasText(level3)) WHERE("level3='"+level3+"'");
            if(StringUtils.hasText(productname)) WHERE("productname='"+productname+"'");
            if(StringUtils.hasText(brand)) WHERE("brand='"+brand+"'");
            if(attrs!=null && attrs.keySet().size()>0){
                LEFT_OUTER_JOIN("productattr attr on attr.productid=p.id");
                AND();
                StringBuilder tmp = new StringBuilder();
                for(String key: attrs.keySet()){
                    tmp.append("attribute='"+key+"' and value='"+attrs.get(key)+"'").append(" or ");
                }
                WHERE("("+tmp.substring(0,tmp.length()-4)+")");
            }
        }};
        if(query != null && StringUtils.hasText(query)) {
            sql.INNER_JOIN("productSearch ps on p.id=ps.productid");
            search_params_where(sql, query);
        }
        return sql.toString();
    }






    public String searchAttrKeys(String query,String level1,String level2,String level3,
                             String productname,String brand,String cardnum,
                                 String material, String surfacetreatment,Map<String,Object> attrs,Integer selfsupport,Integer havestore,
                                 Integer forwardtime,String store){
        String[] keys = new String[]{"attr.attribute","attr.value"};
        SQL sql = new SQL(){{
            SELECT(keys);
            FROM("productinfo p");

            //如果涉及库存和远期的搜索链接库存表
            if(havestore == 1 || forwardtime==1 || StringUtils.hasText(store)) {
                INNER_JOIN("productstore pst on p.id=pst.pdid");
            }
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

            if(StringUtils.hasText(store)) WHERE("pst.storename='"+store+"'");



            if(selfsupport == 1){
                WHERE("p.selfsupport=true");
            }

            if(havestore == 1){
                WHERE(" pst.pdstorenum>1 ");
            }

            if(forwardtime == 1){
                WHERE( " (ninetyprice >0 or thirtyprice>0 or sixtyprice>0 ) " );
            }


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
                                 String material, String surfacetreatment,Map<String,Object> attrs,
                                         Integer selfsupport,Integer havestore,Integer forwardtime,String store){
        String[] keys = new String[]{"attr.attribute","attr.value"};
        SQL sql = new SQL(){{
            SELECT(keys);
            FROM("productinfo p");

            //如果涉及库存和远期的搜索链接库存表
            if(havestore == 1 || forwardtime==1 || StringUtils.hasText(store)) {
                INNER_JOIN("productstore pst on p.id=pst.pdid");
            }
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
            if(StringUtils.hasText(store)) WHERE("pst.storename='"+store+"'");


            if(selfsupport == 1){
                WHERE("p.selfsupport=true");
            }

            if(havestore == 1){
                WHERE(" pst.pdstorenum>1 ");
            }

            if(forwardtime == 1){
                WHERE( " (ninetyprice >0 or thirtyprice>0 or sixtyprice>0 ) " );
            }


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








    public String otherProdSearchAttrKeys(String query,String level1,String level2,String level3,
                                 String productname,String brand, Map<String,Object> attrs,int type){
        String[] keys = new String[]{"attr.attribute","attr.value"};
        SQL sql = new SQL(){{
            SELECT(keys);
            FROM("productinfo p");
            LEFT_OUTER_JOIN("productattr attr on attr.productid=p.id");
            WHERE(" p.producttype='"+AppConstant.OTHER_PRO_TYPE+"' ");

            if(type>0){
                WHERE("p.type="+type);
            }

            if(StringUtils.hasText(level1)) WHERE("level1='"+level1+"'");
            if(StringUtils.hasText(level2)) WHERE("level2='"+level2+"'");
            if(StringUtils.hasText(level3)) WHERE("level3='"+level3+"'");
            if(StringUtils.hasText(productname)) WHERE("productname='"+productname+"'");
            if(StringUtils.hasText(brand)) WHERE("brand='"+brand+"'");

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





/*
    public String searchWithKeys(
            String query,String level1,String level2,String level3,
            String productname,String brand,String cardnum,String material, Map<String,Object> attrs,
            int start,int max,String sorttype,Integer selfsupport,Integer havestore,Integer forwardtime
    ){
        SQL sql = new SQL(){{

            SELECT("p.*","pst.storeid, pst.storename, pst.stepwiseprice, pst.startnum, pst.prodprice, pst.threeprice, pst.ninetyprice, pst.thirtyprice, pst.sixtyprice, pst.intervalprice, pst.marketprice, pst.costprice, pst.pdstorenum, pst.storeunit, pst.aftersale, pst.location, pst.freightmode,pst.pdno,M.membersettingstate,B.pic");
            FROM("productinfo p").INNER_JOIN("productstore pst on p.id=pst.pdid").INNER_JOIN(" Member M on p.memberid=M.id ").LEFT_OUTER_JOIN(" Brand B on P.brandid=B.id ");

            WHERE("pdstate=4 and producttype='"+AppConstant.FASTENER_PRO_TYPE+"' ");

            if(StringUtils.hasText(level1)) WHERE("level1='"+level1+"'");
            if(StringUtils.hasText(level2)) WHERE("level2='"+level2+"'");
            if(StringUtils.hasText(level3)) WHERE("level3='"+level3+"'");
            if(StringUtils.hasText(productname)) WHERE("productname='"+productname+"'");
            if(StringUtils.hasText(brand)) WHERE("brand='"+brand+"'");
            if(StringUtils.hasText(cardnum)) WHERE("cardnum='"+cardnum+"'");
            if(StringUtils.hasText(material)) WHERE("material='"+material+"'");


            if(selfsupport == 1){
                WHERE("p.selfsupport=true");
            }

            if(havestore == 1){
                WHERE(" pst.pdstorenum>1 ");
            }

            if(forwardtime == 1){
                WHERE( " (ninetyprice >0 or thirtyprice>0 or sixtyprice>0 ) " );
            }



            if(attrs!=null && attrs.keySet().size()>0){
                LEFT_OUTER_JOIN("productattr attr on attr.productid=p.id");
                AND();
                StringBuilder tmp = new StringBuilder();
                for(String key: attrs.keySet()){
                    tmp.append("attribute='"+key+"' and value='"+attrs.get(key)+"'").append(" or ");
                }
                WHERE("("+tmp.substring(0,tmp.length()-4)+")");
            }
        }};

        if(query != null && StringUtils.hasText(query)) {
            sql.INNER_JOIN("productSearch ps on p.id=ps.productid");

            search_params_where(sql, query);
            search_params_orderby(sql,query);
        }

        if(StringUtils.hasText(sorttype)){
            sql.ORDER_BY(sorttype);
        }

        sql.ORDER_BY(" selfsupport desc");
        return  sql.toString()+" LIMIT "+max+" OFFSET "+start;

//        String searchSql =  "select foo.*, rank() over(partition by productnameid,brandid,mark,pdno order by id desc) from( "+sql.toString()+") foo ";
//        return useFunction(searchSql,start,max,sorttype);
    }
*/



    public String searchWithKeys(
            String query,String level1,String level2,String level3,
            String productname,String brand,String cardnum,String material,String surfacetreatment, Map<String,Object> attrs,
            int start,int max,String sorttype,Integer selfsupport,Integer havestore,Integer forwardtime,String store
    ){
        SQL sql = new SQL(){{

            SELECT("p.*","pst.storeid, pst.storename, pst.stepwiseprice, pst.startnum, pst.prodprice, pst.threeprice, pst.ninetyprice, pst.thirtyprice, pst.sixtyprice, pst.intervalprice, pst.marketprice, pst.costprice, pst.pdstorenum, pst.storeunit, pst.aftersale, pst.location, pst.freightmode,pst.pdno,pst.minplus,M.membersettingstate,B.pic");
            FROM("productinfo p").INNER_JOIN("productstore pst on p.id=pst.pdid").INNER_JOIN(" Member M on p.memberid=M.id ").LEFT_OUTER_JOIN(" Brand B on P.brandid=B.id ");

            WHERE("pdstate=4 and producttype='"+AppConstant.FASTENER_PRO_TYPE+"' ");

            if(StringUtils.hasText(level1)) WHERE("level1='"+level1+"'");
            if(StringUtils.hasText(level2)) WHERE("level2='"+level2+"'");
            if(StringUtils.hasText(level3)) WHERE("level3='"+level3+"'");
            if(StringUtils.hasText(productname)) WHERE("productname='"+productname+"'");
            if(StringUtils.hasText(brand)) WHERE("brand='"+brand+"'");
            if(StringUtils.hasText(cardnum)) WHERE("cardnum='"+cardnum+"'");
            if(StringUtils.hasText(material)) WHERE("material='"+material+"'");
            if(StringUtils.hasText(surfacetreatment)) WHERE("surfacetreatment='"+surfacetreatment+"'");
            if(StringUtils.hasText(store)) WHERE("pst.storename='"+store+"'");

            if(selfsupport == 1){
                WHERE("p.selfsupport=true");
            }

            if(havestore == 1){
                WHERE(" pst.pdstorenum>1 ");
            }

            if(forwardtime == 1){
                WHERE( " (ninetyprice >0 or thirtyprice>0 or sixtyprice>0 ) " );
            }



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

//        if(StringUtils.hasText(sorttype)){
//            sql.ORDER_BY(sorttype);
//        }

        if(!StringUtils.hasText(sorttype)){
            sql.ORDER_BY(" selfsupport desc,stand asc");
        }else{
            sql.ORDER_BY(sorttype);
            sql.ORDER_BY(" selfsupport desc");
        }

       // System.out.println(sql.toString());
        return  sql.toString()+" LIMIT "+max+" OFFSET "+start;

//        String searchSql =  "select foo.*, rank() over(partition by productnameid,brandid,mark,pdno order by id desc) from( "+sql.toString()+") foo ";
//        return useFunction(searchSql,start,max,sorttype);
    }




/*
    public String searchWithKeys(
            String query,String level1,String level2,String level3,
            String productname,String brand,String cardnum,String material, Map<String,Object> attrs,
            int start,int max,String sorttype
    ){
        SQL sql = new SQL(){{

//            SELECT * FROM product_list_group_limit('select foo.*, rank() over(partition by productnameid,
//                    brandid,mark order by id desc) from( SELECT DISTINCT p.*,
//                    pst.storeid, pst.storename, pst.stepwiseprice, pst.startnum, pst.prodprice,
//                    pst.threeprice, pst.ninetyprice, pst.thirtyprice, pst.sixtyprice, pst.intervalprice,
//                    pst.marketprice, pst.costprice, pst.pdstorenum, pst.storeunit, pst.aftersale,
//                    pst.location, pst.freightmode FROM productinfo p
//                    INNER JOIN productstore pst on p.id=pst.pdid) foo',0,2);



            SELECT_DISTINCT("p.*","pst.storeid, pst.storename, pst.stepwiseprice, pst.startnum, pst.prodprice, pst.threeprice, pst.ninetyprice, pst.thirtyprice, pst.sixtyprice, pst.intervalprice, pst.marketprice, pst.costprice, pst.pdstorenum, pst.storeunit, pst.aftersale, pst.location, pst.freightmode,M.membersettingstate,B.pic");
            FROM("productinfo p").INNER_JOIN("productstore pst on p.id=pst.pdid").INNER_JOIN(" Member M on p.memberid=M.id ").LEFT_OUTER_JOIN(" Brand B on P.brandid=B.id ");

            WHERE("pdstate=4 and producttype='"+AppConstant.FASTENER_PRO_TYPE+"' ");

            if(StringUtils.hasText(level1)) WHERE("level1='"+level1+"'");
            if(StringUtils.hasText(level2)) WHERE("level2='"+level2+"'");
            if(StringUtils.hasText(level3)) WHERE("level3='"+level3+"'");
            if(StringUtils.hasText(productname)) WHERE("productname='"+productname+"'");
            if(StringUtils.hasText(brand)) WHERE("brand='"+brand+"'");
            if(StringUtils.hasText(cardnum)) WHERE("cardnum='"+cardnum+"'");
            if(StringUtils.hasText(material)) WHERE("material='"+material+"'");
            if(attrs!=null && attrs.keySet().size()>0){
                LEFT_OUTER_JOIN("productattr attr on attr.productid=p.id");
                AND();
                StringBuilder tmp = new StringBuilder();
                for(String key: attrs.keySet()){
                    tmp.append("attribute='"+key+"' and value='"+attrs.get(key)+"'").append(" or ");
                }
                WHERE("("+tmp.substring(0,tmp.length()-4)+")");
            }



        }};

        if(query != null && StringUtils.hasText(query)) {
            sql.INNER_JOIN("productSearch ps on p.id=ps.productid");

            search_params_where(sql, query);
        }

        String searchSql =  "select foo.*, rank() over(partition by productnameid,brandid,mark,pdno order by id desc) from( "+sql.toString()+") foo ";
        return useFunction(searchSql,start,max,sorttype);
    }

*/



    public String otherProdSearchWithKeys(
            String query,String level1,String level2,String level3,
            String productname,String brand, Map<String,Object> attrs,int start,int count,String sorttype,int type){
        SQL sql = new SQL(){{
            SELECT(" P.* ");
            FROM("productinfo p").INNER_JOIN(" Member M on p.memberid=M.id ").LEFT_OUTER_JOIN(" Brand B on P.brandid=B.id ");

            WHERE("pdstate=4 and producttype='"+ AppConstant.OTHER_PRO_TYPE+"' ");
            if(type>0){
                WHERE(" p.type="+type);
            }

            if(StringUtils.hasText(level1)) WHERE("level1='"+level1+"'");
            if(StringUtils.hasText(level2)) WHERE("level2='"+level2+"'");
            if(StringUtils.hasText(level3)) WHERE("level3='"+level3+"'");
            if(StringUtils.hasText(productname)) WHERE("productname='"+productname+"'");
            if(StringUtils.hasText(brand)) WHERE("brand='"+brand+"'");

            if(attrs!=null && attrs.keySet().size()>0){
                LEFT_OUTER_JOIN("productattr attr on attr.productid=p.id");
                AND();
                StringBuilder tmp = new StringBuilder();
                for(String key: attrs.keySet()){
                    tmp.append("attribute='"+key+"' and value='"+attrs.get(key)+"'").append(" or ");
                }
                WHERE("("+tmp.substring(0,tmp.length()-4)+")");
            }
        }};

        if(query != null && StringUtils.hasText(query)) {
            sql.INNER_JOIN("productSearch ps on p.id=ps.productid");

            search_params_where(sql, query);
        }


        String sql_final = sql.toString();
        if(StringUtils.hasText(sorttype)){
            sql_final += " order by  "+ sorttype +"limit  "+count+" OFFSET "+start;
        }else{
            sql_final += " order by p.updatetime desc limit  "+count+" OFFSET "+start;
        }

        return  sql_final;
    }








/*
    public String countSearchWithKeys(
            String query,String level1,String level2,String level3,
            String productname,String brand,String cardnum,String material, Map<String,Object> attrs,Integer selfsupport,Integer havestore,Integer forwardtime){
        SQL sql = new SQL(){{
            SELECT("count(p.id)");
            FROM("productinfo p").INNER_JOIN("productstore pst on p.id=pst.pdid");

            WHERE("pdstate=4 and producttype='"+AppConstant.FASTENER_PRO_TYPE+"' ");

            if(StringUtils.hasText(level1)) WHERE("level1='"+level1+"'");
            if(StringUtils.hasText(level2)) WHERE("level2='"+level2+"'");
            if(StringUtils.hasText(level3)) WHERE("level3='"+level3+"'");
            if(StringUtils.hasText(productname)) WHERE("productname='"+productname+"'");
            if(StringUtils.hasText(brand)) WHERE("brand='"+brand+"'");
            if(StringUtils.hasText(cardnum)) WHERE("cardnum='"+cardnum+"'");
            if(StringUtils.hasText(material)) WHERE("material='"+material+"'");

            if(selfsupport == 1){
                WHERE("p.selfsupport=true");
            }

            if(havestore == 1){
                WHERE(" pst.pdstorenum>1 ");
            }

            if(forwardtime == 1){
                WHERE( " (ninetyprice >0 or thirtyprice>0 or sixtyprice>0 ) " );
            }



            if(attrs!=null && attrs.keySet().size()>0){
                LEFT_OUTER_JOIN("productattr attr on attr.productid=p.id");
                AND();
                StringBuilder tmp = new StringBuilder();
                for(String key: attrs.keySet()){
                    tmp.append("attribute='"+key+"' and value='"+attrs.get(key)+"'").append(" or ");
                }
                WHERE("("+tmp.substring(0,tmp.length()-4)+")");
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
        return sql.toString();
    }

*/


    public String countSearchWithKeys(
            String query,String level1,String level2,String level3,
            String productname,String brand,String cardnum,String material,String surfacetreatment, Map<String,Object> attrs,
            Integer selfsupport,Integer havestore,Integer forwardtime,String store){
        SQL sql = new SQL(){{
            SELECT("count(p.id)");
            FROM("productinfo p").INNER_JOIN("productstore pst on p.id=pst.pdid");

            WHERE("pdstate=4 and producttype='"+AppConstant.FASTENER_PRO_TYPE+"' ");

            if(StringUtils.hasText(level1)) WHERE("level1='"+level1+"'");
            if(StringUtils.hasText(level2)) WHERE("level2='"+level2+"'");
            if(StringUtils.hasText(level3)) WHERE("level3='"+level3+"'");
            if(StringUtils.hasText(productname)) WHERE("productname='"+productname+"'");
            if(StringUtils.hasText(brand)) WHERE("brand='"+brand+"'");
            if(StringUtils.hasText(cardnum)) WHERE("cardnum='"+cardnum+"'");
            if(StringUtils.hasText(material)) WHERE("material='"+material+"'");
            if(StringUtils.hasText(surfacetreatment)) WHERE("surfacetreatment='"+surfacetreatment+"'");

            if(StringUtils.hasText(store)) WHERE("pst.storename='"+store+"'");


            if(selfsupport == 1){
                WHERE("p.selfsupport=true");
            }

            if(havestore == 1){
                WHERE(" pst.pdstorenum>1 ");
            }

            if(forwardtime == 1){
                WHERE( " (ninetyprice >0 or thirtyprice>0 or sixtyprice>0 ) " );
            }



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
        return sql.toString();
    }







//    public String countSearchWithKeys(
//            String query,String level1,String level2,String level3,
//            String productname,String brand,String cardnum,String material, Map<String,Object> attrs){
//        SQL sql = new SQL(){{
//            SELECT_DISTINCT("p.productnameid,p.brandid,p.mark,p.pdno");
//            FROM("productinfo p").INNER_JOIN("productstore pst on p.id=pst.pdid");
//
//            WHERE("pdstate=4 and producttype='"+AppConstant.FASTENER_PRO_TYPE+"' ");
//
//            if(StringUtils.hasText(level1)) WHERE("level1='"+level1+"'");
//            if(StringUtils.hasText(level2)) WHERE("level2='"+level2+"'");
//            if(StringUtils.hasText(level3)) WHERE("level3='"+level3+"'");
//            if(StringUtils.hasText(productname)) WHERE("productname='"+productname+"'");
//            if(StringUtils.hasText(brand)) WHERE("brand='"+brand+"'");
//            if(StringUtils.hasText(cardnum)) WHERE("cardnum='"+cardnum+"'");
//            if(StringUtils.hasText(material)) WHERE("material='"+material+"'");
//            if(attrs!=null && attrs.keySet().size()>0){
//                LEFT_OUTER_JOIN("productattr attr on attr.productid=p.id");
//                AND();
//                StringBuilder tmp = new StringBuilder();
//                for(String key: attrs.keySet()){
//                    tmp.append("attribute='"+key+"' and value='"+attrs.get(key)+"'").append(" or ");
//                }
//                WHERE("("+tmp.substring(0,tmp.length()-4)+")");
//            }
//            GROUP_BY("p.productnameid,p.brandid,p.mark,p.pdno");
//        }};
//        if(query != null && StringUtils.hasText(query)) {
//            sql.INNER_JOIN("productSearch ps on p.id=ps.productid");
//            search_params_where(sql, query);
//        }
//        SQL sql2 = new SQL(){{
//            SELECT("count(*)");
//            FROM("("+sql.toString()+") foo");
//        }};
//        return sql2.toString();
//    }



    public String otherProdCountSearchWithKeys(
            String query,String level1,String level2,String level3,
            String productname,String brand, Map<String,Object> attrs,int type){
        SQL sql = new SQL();
        sql.SELECT("count(p.id)");
        sql.FROM("productinfo p");
        sql.INNER_JOIN("Member M ON p.memberid = M.id");
        sql.WHERE("pdstate=4 and producttype='"+AppConstant.OTHER_PRO_TYPE+"' ");

        if(type>0){
            sql.WHERE(" p.type="+type);
        }

        if(StringUtils.hasText(level1)) sql.WHERE("level1='"+level1+"'");
        if(StringUtils.hasText(level2)) sql.WHERE("level2='"+level2+"'");
        if(StringUtils.hasText(level3)) sql.WHERE("level3='"+level3+"'");
        if(StringUtils.hasText(productname)) sql.WHERE("productname='"+productname+"'");
        if(StringUtils.hasText(brand)) sql.WHERE("brand='"+brand+"'");


        if(attrs!=null && attrs.keySet().size()>0){
            sql.LEFT_OUTER_JOIN("productattr attr on attr.productid=p.id");
            sql.AND();
            StringBuilder tmp = new StringBuilder();
            for(String key: attrs.keySet()){
                tmp.append("attribute='"+key+"' and value='"+attrs.get(key)+"'").append(" or ");
            }
            sql.WHERE("("+tmp.substring(0,tmp.length()-4)+")");
        }



        if(query != null && StringUtils.hasText(query)) {
            sql.INNER_JOIN("productSearch ps on p.id=ps.productid");
            search_params_where(sql, query);
        }

        return sql.toString();
    }



//    private void search_params_where(SQL sql){
//        sql.WHERE("p.id=ps.productid","ps.sindex @@ to_tsquery('${query}')");
//    }
//    private void search_params_orderby(SQL sql){
//        sql.ORDER_BY("ts_rank(ps.sindex, to_tsquery('${query}')) desc");
//    }

    private void search_params_where(SQL sql,String query){
        sql.WHERE("ps.sindex @@ '"+query+"'::tsquery");
    }

    private void search_params_orderby(SQL sql,String query){
        sql.ORDER_BY("ts_rank(ps.sindex, '"+query+"'::tsquery) desc");
    }

    /**
     * 转义 用于函数参数
     */
    private String useFunction(String sql,int start,int max,String sorttype){
        sql=sql.replace("'","''");
        SQL sql_final = new SQL().SELECT("*")
                .FROM("product_list_group_limit('"+sql+"'," +start+","+max+ ")");

        if(StringUtils.hasText(sorttype)){
            sql_final.ORDER_BY(sorttype);
        }

        return sql_final.toString();
    }


}
