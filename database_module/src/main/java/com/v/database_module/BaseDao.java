package com.v.database_module;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.v.database_module.annotion.DbFiled;
import com.v.database_module.annotion.DbTable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 中间层
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-05-07 14:46
 */
public class BaseDao<T> implements IBaseDao<T> {

    private SQLiteDatabase database;

    private Class<T> entityClass;

    private String tableName;

    private boolean isInit = false;

    private HashMap<String, Field> cacheMap;

    public synchronized boolean init(Class<T> entity, SQLiteDatabase sqLiteDatabase) {
        if (!isInit) {
            entityClass = entity;
            database = sqLiteDatabase;
            tableName = entity.getAnnotation(DbTable.class).value();

            //判断数据库是否打开
            if (!sqLiteDatabase.isOpen()) {
                return false;
            }

            //判断是否建表
            if (!autoCreateTable()) {
                return false;
            }
            isInit = true;
        }

        initCacheMap();

        return isInit;
    }

    private void initCacheMap() {
        //映射关系


        //情况1：版本升级，最新版本 在某个表中伤处了一个字段，由于数据库版本没有升级
        //情况2：如果其他开发者修改了表结构
        cacheMap = new HashMap<>();

        //查一次表 （空表）
        String sql = "select * from " + this.tableName + " limit 1,0";
        Cursor cursor = database.rawQuery(sql, null);

        //得到字段名数组
        String[] colmunNames = cursor.getColumnNames();
        Field[] colmunFields = entityClass.getDeclaredFields();

        for (String colmunName : colmunNames) {
            Field resultField = null;
            for (Field field : colmunFields) {
                if (colmunName.equals(field.getAnnotation(DbFiled.class).value())) {
                    resultField = field;
                    break;
                }
            }
            if (null != resultField) {
                cacheMap.put(colmunName, resultField);
            }
        }

        cursor.close();
    }

    private boolean autoCreateTable() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("CREATE TABLE IF NOT EXISTS ");
        stringBuffer.append(tableName + "(");
        Field[] fields = entityClass.getDeclaredFields();
        for (Field field : fields) {
            Class type = field.getType();
            if (type == String.class) {
                stringBuffer.append(field.getAnnotation(DbFiled.class).value() + " TEXT,");
            } else if (type == Double.class) {
                stringBuffer.append(field.getAnnotation(DbFiled.class).value() + " DOUBLE,");
            } else if (type == Integer.class || type == int.class) {
                stringBuffer.append(field.getAnnotation(DbFiled.class).value() + " INTEGER,");
            } else if (type == Long.class) {
                stringBuffer.append(field.getAnnotation(DbFiled.class).value() + " BIGINT,");
            } else if (type == byte[].class) {
                stringBuffer.append(field.getAnnotation(DbFiled.class).value() + " BLOB,");
            } else {
                //不支持的类型
                continue;
            }
        }
        //去除最后一个 ，
        if (stringBuffer.charAt(stringBuffer.length() - 1) == ',') {
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }

        stringBuffer.append(")");

        try {
            System.out.println("sql--->" + stringBuffer.toString());
            this.database.execSQL(stringBuffer.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    @Override
    public Long insert(T entity) {
        ContentValues contentValues = getValues(entity);
        database.insert(tableName, null, contentValues);

        return null;
    }

    @Override
    public List<T> queue(T where) {
        return query(where, null, null, null);
    }

    private List<T> query(T where, String orderBy, Integer startIndex, Integer limit) {
        String limitString = null;
        if (startIndex != null && limit != null) {
            limitString = startIndex + "," + limit;
        }

        Condition condition = new Condition(getContentValues(where));
        Cursor cursor = null;
        List<T> result = new ArrayList<>();

        try {
            //String table, String[] columns, String selection,String[] selectionArgs, String groupBy, String having, String orderB
            cursor = database.query(tableName, null, condition.getWhereClause(), condition.getWhereArgs(), null,null,null);
            result = getResult(cursor, where);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return result;
    }

    private List<T> getResult(Cursor cursor, T where) {
        ArrayList list = new ArrayList();
        Object item;
        while(cursor.moveToNext()) {
            try {
                item = where.getClass().newInstance();
                Iterator<Map.Entry<String, Field>> iterator=cacheMap.entrySet().iterator();
                while (iterator.hasNext()){
                    Map.Entry<String,Field> entry=iterator.next();

                    String colmunName=entry.getKey();
                    Field field=entry.getValue();

                    int columnIndex=cursor.getColumnIndex(colmunName);
                    Class type=field.getType();
                    if(columnIndex!=-1){
                        if (type == String.class) {
                            field.set(item,cursor.getString(columnIndex));
                        } else if (type == Double.class || type==double.class) {
                            field.set(item,cursor.getDouble(columnIndex));
                        } else if (type == Integer.class || type == int.class) {
                            field.set(item,cursor.getInt(columnIndex));
                        } else if (type == Long.class|| type==long.class) {
                            field.set(item,cursor.getLong(columnIndex));
                        } else if (type == byte[].class) {
                            field.set(item,cursor.getBlob(columnIndex));
                        } else {
                            //不支持的类型
                            continue;
                        }
                    }
                }

                list.add(item);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        return list;
    }


    class Condition {
        /**
         * 查询条件
         */

        private String whereClause;

        private String[] whereArgs;

        public Condition(ContentValues whereClause) {
            ArrayList list = new ArrayList();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("1==1");
            Set keys = whereClause.keySet();
            Iterator iterator = keys.iterator();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                String value = (String) whereClause.get(key);

                if (value != null) {
                    /**
                     * 拼接条件查询语句
                     */
                    stringBuilder.append(" and " + key + " =?");
                    /**
                     * ？--->value
                     */
                    list.add(value);
                }
            }
            this.whereClause = stringBuilder.toString();
            this.whereArgs = (String[]) list.toArray(new String[list.size()]);
        }

        public String[] getWhereArgs() {
            return whereArgs;
        }

        public String getWhereClause() {
            return whereClause;
        }
    }

    private ContentValues getContentValues(T entity) {
        ContentValues contentValues = new ContentValues();
        try {
            for (Map.Entry<String, Field> me : cacheMap.entrySet()) {
                if (me.getValue().get(entity) == null) {
                    continue;
                }
                contentValues.put(me.getKey(), me.getValue().get(entity).toString());
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return contentValues;
    }

    private ContentValues getValues(T entity) {
        ContentValues contentValues = new ContentValues();

        Iterator<Map.Entry<String, Field>> iterator = cacheMap.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, Field> fieldEntry = iterator.next();

            //name--->"david"
            Field field = fieldEntry.getValue();

            //tv_name
            String key = fieldEntry.getKey();

            //反射
            field.setAccessible(true);

            try {
                Object object = field.get(entity);

                Class type = field.getType();
                if (type == String.class) {
                    contentValues.put(key, (String) object);
                } else if (type == Double.class) {
                    contentValues.put(key, (Double) object);
                } else if (type == Integer.class || type == int.class) {
                    contentValues.put(key, (Integer) object);
                } else if (type == Long.class) {
                    contentValues.put(key, (Long) object);
                } else if (type == byte[].class) {
                    contentValues.put(key, (byte[]) object);
                } else {
                    //不支持的类型
                    continue;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return contentValues;
    }
}
