package com.vieted.android.app.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import com.nttuyen.android.base.converter.JsonGenericConverter;
import com.nttuyen.android.base.converter.Json;
import com.nttuyen.android.base.utils.UIContextHelper;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 6/26/13
 * Time: 10:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class DemoActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static class Test {
        @Json(name = "result")
        private boolean resultABC;
        private int age;
        private Test1 test1;
        @Json(name = "values", isCollection = true, type = String.class)
        private List<String> values;

        public List<String> getValues() {
            return values;
        }

        public void setValues(List<String> values) {
            this.values = values;
        }

        public boolean isResultABC() {
            return resultABC;
        }

        public void setResultABC(boolean resultABC) {
            this.resultABC = resultABC;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Test1 getTest1() {
            return test1;
        }

        public void setTest1(Test1 test1) {
            this.test1 = test1;
        }
    }
    public static class Test1 {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
