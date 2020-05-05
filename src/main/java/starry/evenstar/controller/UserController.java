package starry.evenstar.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import starry.evenstar.entity.User;
import starry.evenstar.service.IUserService;
import starry.evenstar.utils.Info;
import starry.evenstar.utils.Result;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 暮星公主周薇儿
 * @since 2020-05-02
 */
@RestController
@RequestMapping("/evenstar/user")
public class UserController {
    @Autowired
    IUserService service;
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        Result result = new Result();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", user.getUsername());
        List<User> list = service.list(wrapper);
        if (list.size()==0) {
            result.info= new Info("用户名不存在！", 404);
        }
        else {
            User user1 = list.get(0);
            if (!user.getPassword().equals(user1.getPassword())){
                result.info= new Info("密码错误！", 400);
            }
            else {
                UUID uuid = UUID.randomUUID();
                result.data= String.valueOf(uuid);
                result.info= new Info("登录成功！", 200);
            }
        }
        return result;

    }
}
