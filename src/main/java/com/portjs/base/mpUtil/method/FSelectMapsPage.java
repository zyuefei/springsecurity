package com.portjs.base.mpUtil.method;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

import java.util.Map;

/**
 * @Author:zhangyuefei
 * @Date:2019/8/26 10:57
 */

public class FSelectMapsPage extends AbstractMethod {

    private String alias = "a";
    private String method = "fSelectMapsPage";
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
//        SqlMethod sqlMethod = SqlMethod.SELECT_PAGE;
        String sqlScript = "<script>\n SELECT\n" +
                "\t %s \n" +
                "FROM\n" +
                "\t %s a \n" +
                "\tLEFT JOIN (SELECT id,name FROM t_user) u ON a.create_id = u.id\n" +
                "\tJOIN (SELECT id,name FROM t_department) d ON a.dept_id = d.id\n" +
                "\tLEFT JOIN (SELECT id,name FROM t_user) u2 ON a.update_id = u2.id %s %s\n</script>";
        String sql = String.format(sqlScript, sqlSelectColumns2(tableInfo, true),
                tableInfo.getTableName(), sqlWhereEntityWrapper(true, tableInfo),
                sqlComment());
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return this.addSelectMappedStatementForOther(mapperClass, method, sqlSource, Map.class);
    }



    protected static String sqlSelectColumns2(TableInfo table, boolean queryWrapper) {
        /* 假设存在 resultMap 映射返回 */
        String selectColumns = "a."+ASTERISK;
        if (table.getResultMap() == null || (table.getResultMap() != null && table.isInitResultMap())) {
            /* 普通查询 */
            selectColumns = table.getAllSqlSelect();
            selectColumns = selectColumns.replace(",",",a.");
            selectColumns = "a."+selectColumns;
        }
        if (!queryWrapper) {
            return selectColumns;
        }
        return SqlScriptUtils.convertChoose(String.format("%s != null and %s != null", WRAPPER, Q_WRAPPER_SQL_SELECT),
                SqlScriptUtils.unSafeParam(Q_WRAPPER_SQL_SELECT), selectColumns);
    }

    @Override
    protected String sqlWhereEntityWrapper(boolean newLine, TableInfo table) {
        if (table.isLogicDelete()) {
            String sqlScript = table.getAllSqlWhere(true, true, WRAPPER_ENTITY_DOT);
            sqlScript = SqlScriptUtils.convertIf(sqlScript, String.format("%s != null", WRAPPER_ENTITY),
                    true);
            sqlScript += (NEWLINE + table.getLogicDeleteSql(true, false) + NEWLINE);
            String normalSqlScript = SqlScriptUtils.convertIf(String.format("AND ${%s}", WRAPPER_SQLSEGMENT),
                    String.format("%s != null and %s != '' and %s", WRAPPER_SQLSEGMENT, WRAPPER_SQLSEGMENT,
                            WRAPPER_NONEMPTYOFNORMAL), true);
            normalSqlScript += NEWLINE;
            normalSqlScript += SqlScriptUtils.convertIf(String.format(" ${%s}", WRAPPER_SQLSEGMENT),
                    String.format("%s != null and %s != '' and %s", WRAPPER_SQLSEGMENT, WRAPPER_SQLSEGMENT,
                            WRAPPER_EMPTYOFNORMAL), true);
            sqlScript += normalSqlScript;
            sqlScript = SqlScriptUtils.convertChoose(String.format("%s != null", WRAPPER), sqlScript,
                    table.getLogicDeleteSql(false, false));
            sqlScript = SqlScriptUtils.convertWhere(sqlScript);
            return newLine ? NEWLINE + sqlScript : sqlScript;
        } else {
            String sqlScript = table.getAllSqlWhere(false, true, WRAPPER_ENTITY_DOT);
            sqlScript = SqlScriptUtils.convertIf(sqlScript, String.format("%s != null", WRAPPER_ENTITY), true);
            sqlScript += NEWLINE;
            sqlScript += SqlScriptUtils.convertIf(String.format(SqlScriptUtils.convertIf(" AND", String.format("%s and %s", WRAPPER_NONEMPTYOFENTITY, WRAPPER_NONEMPTYOFNORMAL), false) + " ${%s}", WRAPPER_SQLSEGMENT),
                    String.format("%s != null and %s != '' and %s", WRAPPER_SQLSEGMENT, WRAPPER_SQLSEGMENT,
                            WRAPPER_NONEMPTYOFWHERE), true);
            sqlScript = SqlScriptUtils.convertWhere(sqlScript) + NEWLINE;
            sqlScript += SqlScriptUtils.convertIf(String.format(" ${%s}", WRAPPER_SQLSEGMENT),
                    String.format("%s != null and %s != '' and %s", WRAPPER_SQLSEGMENT, WRAPPER_SQLSEGMENT,
                            WRAPPER_EMPTYOFWHERE), true);
            sqlScript = SqlScriptUtils.convertIf(sqlScript, String.format("%s != null", WRAPPER), true);
            return newLine ? NEWLINE + sqlScript : sqlScript;
        }
    }

}
