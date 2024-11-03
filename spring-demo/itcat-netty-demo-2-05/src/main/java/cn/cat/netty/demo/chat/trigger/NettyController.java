package cn.cat.netty.demo.chat.trigger;

import cn.cat.netty.demo.chat.server.NettyServer;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NettyController {

    @Resource
    private NettyServer nettyServer;

    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("name", "xiaohao");
        return "index";
    }

}