package com.ld.poetize.utils;

import com.ld.poetize.utils.web.R;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;

import java.io.IOException;

/**
 * @Author zuosy
 * @Date 2024/1/24 20:02
 **/
public class Oauth2Response {

    public static void oauth2Exception(HttpServletResponse response, Integer code, String msg ) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(JacksonUtil.Data2Json(R.failForCodeMsg(code, msg)));
    }
}
