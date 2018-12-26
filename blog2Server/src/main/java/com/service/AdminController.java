package com.service;

import com.domain.Admin;
import com.domain.AdminLoginLog;
import com.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AdminController {

    @Autowired
    private ArticleMapper articleMapper;

    @GetMapping("/api/main")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> toMain(HttpServletRequest request) {
        Map modelAndView = new HashMap<String, String>();
        String clientIp = request.getRemoteAddr();    //获取客户端IP，如：127.0.0.1
        String hostIp = request.getLocalAddr();
        int hostPort = request.getLocalPort();
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm");//设置日期格式
        String dates = df.format(date);
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        AdminLoginLog lastLoginLog = null;
        try {
           /* if (adminLoginLogService.selectRencent(admin.getId()) != null && adminLoginLogService.selectRencent(admin.getId()).size() == 2) {
                List<AdminLoginLog> adminLoginLogs = adminLoginLogService.selectRencent(admin.getId());
                lastLoginLog = adminLoginLogs.get(1);
            }*/

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            int articleCount = articleMapper.selectList(null).size();
            int commentCount = 1;//commentService.countAllNum();
            int loginNum = 1;//adminLoginLogService.selectCountByAdminId(admin.getId());
            modelAndView.put("clientIp", clientIp);
            modelAndView.put("hostIp", hostIp);
            modelAndView.put("hostPort", hostPort);
            modelAndView.put("date", dates);
            if (lastLoginLog != null) {
                modelAndView.put("loginLog", lastLoginLog);
            }
            modelAndView.put("articleCount", articleCount);
            modelAndView.put("commentCount", commentCount);
            modelAndView.put("loginNum", loginNum);
            return new ResponseEntity<Map<String, Object>>(modelAndView, HttpStatus.OK);
        }
    }
}
