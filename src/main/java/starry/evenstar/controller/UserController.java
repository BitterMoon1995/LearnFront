package starry.evenstar.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import starry.evenstar.entity.User;
import starry.evenstar.service.IUserService;
import starry.evenstar.utils.Info;
import starry.evenstar.utils.Result;
import starry.evenstar.vo.UserVo;

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
    //登陆
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

    //分页查询所有用户
    //分页需要总条目数witch is extremely important
    @GetMapping("/getAll")
    public UserVo getAll(@RequestParam Integer pageNum, @RequestParam Integer pageSize
            , @RequestParam String condition){
        UserVo vo = new UserVo();
        if (condition.isEmpty()){
            Page<User> page = new Page<>(pageNum, pageSize,false);//第几页 每一页几个 不计数
            Page<User> userPage = service.page(page);
            vo.setUserList(userPage.getRecords());
            vo.setTotal(service.count());
        }
        else {
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.like("username",condition);
            Page<User> page = new Page<>(pageNum, pageSize,false);
            vo.setUserList(service.page(page,wrapper).getRecords());
            vo.setTotal(service.count(wrapper));
        }
        return vo;
    }


    //改
    @PostMapping("/update")
    public User update(@RequestBody User user){
        service.updateById(user);
        return service.getById(user.getId());
    }

}
