package com.jungle68.ibook.utils;

/**
 * 数据库驱动类, 从JSON文件<b>databaseDriver.json</b>中读取数据并封装为对象.<br>
 * <p/>
 * JSON文件格式如下:<br>
 * <p/>
 * <pre>
 * {"db": [
 *         {
 *             "name": "PostgreSQL",
 *             "driver": "org.postgresql.Driver",
 *             "connectStr": "jdbc:postgresql://"
 *         },
 *         {
 *             "name": "MySQL",
 *             "driver": "com.mysql.jdbc.Driver",
 *             "connectStr": "jdbc:mysql://"
 *         }
 *     ]
 * }
 * </pre>
 *
 * @author yangdm.fnst
 */
public class DatabaseDriver {
    /**
     * Database Name, <b>e.g.</b> MySQL.
     */
    private String name;
    /**
     * JDBC Database Driver, <b>e.g.</b> com.mysql.jdbc.Driver.
     */
    private String driver;
    /**
     * JDBC Connect String, <b>e.g.</b> jdbc:mysql://.
     */
    private String connectStr;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the driver
     */
    public String getDriver() {
        return driver;
    }

    /**
     * @param driver the driver to set
     */
    public void setDriver(String driver) {
        this.driver = driver;
    }

    /**
     * @return the connectStr
     */
    public String getConnectStr() {
        return connectStr;
    }

    /**
     * @param connectStr the connectStr to set
     */
    public void setConnectStr(String connectStr) {
        this.connectStr = connectStr;
    }

    /**
     * 获取databaseDriver.json中配置JDBC数据库信息，封装成DatabaseDriver对象.
     *
     * @return DatabaseDriver对象数据库名称和对象的Map
     */
//    public static Map<String, DatabaseDriver> getMap() {
//        // 获取json文件
//        File file = new File("q1.json");
//        Map<String, DatabaseDriver> map = new HashMap<String, DatabaseDriver>();
//        try {
//            String json = FileUtils.readFileToString(file);
//            Gson gson = new Gson();
//            JsonParser parser = new JsonParser();
//            JsonObject jsonObject = parser.parse(json).getAsJsonObject();
//            // 将db节点下的内容转为JsonArray
//            JsonArray jsonArray = jsonObject.getAsJsonArray("db");
//            for (int i = 0; i < jsonArray.size(); i++) {
//                JsonElement el = jsonArray.get(i);
//                // 映射为类实例
//                DatabaseDriver data = gson.fromJson(el, DatabaseDriver.class);
//                map.put(data.getName(), data);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return map;
//    }
}
