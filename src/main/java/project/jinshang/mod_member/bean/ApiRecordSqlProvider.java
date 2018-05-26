package project.jinshang.mod_member.bean;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.DELETE_FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.ORDER_BY;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT_DISTINCT;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import java.util.List;
import java.util.Map;
import project.jinshang.mod_member.bean.ApiRecord;
import project.jinshang.mod_member.bean.ApiRecordExample.Criteria;
import project.jinshang.mod_member.bean.ApiRecordExample.Criterion;
import project.jinshang.mod_member.bean.ApiRecordExample;

public class ApiRecordSqlProvider {

    public String countByExample(ApiRecordExample example) {
        BEGIN();
        SELECT("count(*)");
        FROM("api_record");
        applyWhere(example, false);
        return SQL();
    }

    public String deleteByExample(ApiRecordExample example) {
        BEGIN();
        DELETE_FROM("api_record");
        applyWhere(example, false);
        return SQL();
    }

    public String insertSelective(ApiRecord record) {
        BEGIN();
        INSERT_INTO("api_record");
        
        if (record.getId() != null) {
            VALUES("id", "#{id,jdbcType=BIGINT}");
        }
        
        if (record.getAppid() != null) {
            VALUES("appid", "#{appid,jdbcType=VARCHAR}");
        }
        
        if (record.getAppurl() != null) {
            VALUES("appurl", "#{appurl,jdbcType=VARCHAR}");
        }
        
        if (record.getApicode() != null) {
            VALUES("apicode", "#{apicode,jdbcType=VARCHAR}");
        }
        
        if (record.getContent() != null) {
            VALUES("content", "#{content,jdbcType=VARCHAR}");
        }
        
        if (record.getReturnjson() != null) {
            VALUES("returnjson", "#{returnjson,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatetime() != null) {
            VALUES("createtime", "#{createtime,jdbcType=TIMESTAMP}");
        }
        
        return SQL();
    }

    public String selectByExample(ApiRecordExample example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("id");
        } else {
            SELECT("id");
        }
        SELECT("appid");
        SELECT("appurl");
        SELECT("apicode");
        SELECT("content");
        SELECT("returnjson");
        SELECT("createtime");
        FROM("api_record");
        applyWhere(example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }
        
        return SQL();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        ApiRecord record = (ApiRecord) parameter.get("record");
        ApiRecordExample example = (ApiRecordExample) parameter.get("example");
        
        BEGIN();
        UPDATE("api_record");
        
        if (record.getId() != null) {
            SET("id = #{record.id,jdbcType=BIGINT}");
        }
        
        if (record.getAppid() != null) {
            SET("appid = #{record.appid,jdbcType=VARCHAR}");
        }
        
        if (record.getAppurl() != null) {
            SET("appurl = #{record.appurl,jdbcType=VARCHAR}");
        }
        
        if (record.getApicode() != null) {
            SET("apicode = #{record.apicode,jdbcType=VARCHAR}");
        }
        
        if (record.getContent() != null) {
            SET("content = #{record.content,jdbcType=VARCHAR}");
        }
        
        if (record.getReturnjson() != null) {
            SET("returnjson = #{record.returnjson,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatetime() != null) {
            SET("createtime = #{record.createtime,jdbcType=TIMESTAMP}");
        }
        
        applyWhere(example, true);
        return SQL();
    }

    public String updateByExample(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("api_record");
        
        SET("id = #{record.id,jdbcType=BIGINT}");
        SET("appid = #{record.appid,jdbcType=VARCHAR}");
        SET("appurl = #{record.appurl,jdbcType=VARCHAR}");
        SET("apicode = #{record.apicode,jdbcType=VARCHAR}");
        SET("content = #{record.content,jdbcType=VARCHAR}");
        SET("returnjson = #{record.returnjson,jdbcType=VARCHAR}");
        SET("createtime = #{record.createtime,jdbcType=TIMESTAMP}");
        
        ApiRecordExample example = (ApiRecordExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByPrimaryKeySelective(ApiRecord record) {
        BEGIN();
        UPDATE("api_record");
        
        if (record.getAppid() != null) {
            SET("appid = #{appid,jdbcType=VARCHAR}");
        }
        
        if (record.getAppurl() != null) {
            SET("appurl = #{appurl,jdbcType=VARCHAR}");
        }
        
        if (record.getApicode() != null) {
            SET("apicode = #{apicode,jdbcType=VARCHAR}");
        }
        
        if (record.getContent() != null) {
            SET("content = #{content,jdbcType=VARCHAR}");
        }
        
        if (record.getReturnjson() != null) {
            SET("returnjson = #{returnjson,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatetime() != null) {
            SET("createtime = #{createtime,jdbcType=TIMESTAMP}");
        }
        
        WHERE("id = #{id,jdbcType=BIGINT}");
        
        return SQL();
    }

    protected void applyWhere(ApiRecordExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            WHERE(sb.toString());
        }
    }
}