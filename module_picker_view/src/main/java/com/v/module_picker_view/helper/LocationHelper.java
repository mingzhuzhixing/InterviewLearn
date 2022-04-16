package com.v.module_picker_view.helper;

import com.v.module_utils.FileUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LocationHelper {
    private LocationHelper() {
        initDataByJson();
    }

    public static LocationHelper getInstance() {
        return ClassHolder.instance;
    }

    private static class ClassHolder {
        private static final LocationHelper instance = new LocationHelper();
    }

    /**
     * 所有省
     */
    private String[] mProvinceDatas;
    /**
     * key - 省名称 value - 省id
     */
    private Map<String, String> mProvinceMaps = new HashMap<>();
    /**
     * key - 市名称 value - 市id
     */
    private Map<String, String> mCityMaps = new HashMap<>();

    /**
     * key - 省 value - 市s
     */
    private Map<String, String[]> mCitisDatasMap = new HashMap<>();

    private void initDataByJson() {
        try {
            JSONObject jsonObject = new JSONObject(FileUtils.getFromAssets("location"));
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            mProvinceDatas = new String[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonP = jsonArray.getJSONObject(i);// 每个省的json对象
                String province_area_id = jsonP.getString("area_id");// 省ID
                String province = jsonP.getString("title");// 省名字
                mProvinceDatas[i] = province;
                mProvinceMaps.put(province_area_id, province);
                JSONArray jsonCs = null;
                try {
                    jsonCs = jsonP.getJSONArray("city");
                } catch (Exception e1) {
                    e1.printStackTrace();
                    continue;
                }
                String[] mCitiesDatas = new String[jsonCs.length()];
                for (int j = 0; j < jsonCs.length(); j++) {
                    JSONObject jsonCity = jsonCs.getJSONObject(j);
                    String city = jsonCity.getString("title");// 市名字
                    String city_area_id = jsonCity.getString("area_id");// 省ID
                    mCitiesDatas[j] = city;
                    mCityMaps.put(city, city_area_id);
                }
                mCitisDatasMap.put(province, mCitiesDatas);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public List<String> getProvinceList() {
        return Arrays.asList(mProvinceDatas);
    }

    public List<List<String>> getCityList() {
        List<List<String>> listAll = new ArrayList<>();
        for (String province : getProvinceList()) {
            if (mCitisDatasMap.containsKey(province)) {
                listAll.add(Arrays.asList(mCitisDatasMap.get(province)));
            }
        }
        return listAll;
    }
}
