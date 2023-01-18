package com.teacher.main.exception;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ExceptionMapping {

    private Map<String, String> exceptionMapping = new HashMap<>();

    private ExceptionMapping() {

        exceptionMapping.put("EX101", "Student Not Found");
        exceptionMapping.put("EX102", "Teacher Not Found");
        exceptionMapping.put("EX103", "Subject Not Found");
        exceptionMapping.put("EX104", "Subject Not Created");
        exceptionMapping.put("EX105", "Teacher Not Created");
        exceptionMapping.put("EX106", "Student Not Created");
        exceptionMapping.put("EX107", "Invalid Age! Must be 18 and above.");
        exceptionMapping.put("EX108", "Invalid ID");
        exceptionMapping.put("EX109", "File Type not supported");
        exceptionMapping.put("EX110", "Exception occured in upload method");
    }

    public String get(String code) {

        if (exceptionMapping.containsKey(code)) {
            return exceptionMapping.get(code);
        }
        return null;
    }

    public String get(String code, Map<String, String> spaceHolder) {

        if (exceptionMapping.containsKey(code)) {
            String res = exceptionMapping.get(code);
            if (!(spaceHolder.isEmpty())) {
                for (Map.Entry<String, String> entry : spaceHolder.entrySet()) {
                    if (res.contains("[$" + entry.getKey() + "$]")) {
                        res = res.replace("[$" + entry.getKey() + "$]", entry.getValue());
                    }
                }
            }
            return res;
        }
        return null;
    }
}
