package pers.zhixilang.sc.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author zhixilang
 * @version 1.0
 * date 2020-01-07 11:09
 */
@RestController
public class SelfErrorController implements ErrorController {

    private final ErrorAttributes errorAttributes;


    @Autowired
    public SelfErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping(value = "/error", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String error(HttpServletRequest request) {
        WebRequest webRequest = new ServletWebRequest(request);
        Map<String, Object> errorAttributes = this.errorAttributes.getErrorAttributes(webRequest, true);
        String msg = errorAttributes.getOrDefault("error", "服务异常").toString();
        String code = errorAttributes.getOrDefault("status", -1).toString();
        return "{\"code\":" + code + ",\"msg\":\"" + msg + "\",\"data\":\"\"}";
    }

}
