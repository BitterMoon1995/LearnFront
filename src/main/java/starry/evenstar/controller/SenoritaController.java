package starry.evenstar.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import starry.evenstar.entity.Senorita;
import starry.evenstar.service.ISenoritaService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 暮星公主周薇儿
 * @since 2020-04-23
 */
@RestController
@CrossOrigin
@RequestMapping("/evenstar/senorita")
public class SenoritaController {
    @Autowired
    ISenoritaService service;

    @GetMapping("/list")
    public List<Senorita> list() {
        return service.list();
    }
    //查
    @GetMapping("/getById/{id}")
    public Senorita getById(@PathVariable("id") String id, HttpServletResponse response) {
        //★★springMVC在响应头设置token
        response.addHeader("responsetoken","godzhouzhoushen");
        //★★★将该header暴露给前端！！！！！！！！
        response.setHeader("Access-Control-Expose-Headers","responsetoken");
        return service.getById(id);
    }

    @GetMapping("/getById")
    public Senorita getById2(@RequestParam("id") String id) {
        return service.getById(id);
    }
    //增
    @PostMapping("/addBatch")
    public void addBatch(@RequestBody List<Senorita> list) {
        service.saveBatch(list);
    }

    @PostMapping("/add")
    public void add(@RequestBody Senorita senorita) {
        service.save(senorita);
    }

    @PostMapping("/addInForm")
    public void add(@RequestParam("name") String name,
                    @RequestParam("age") Integer age,
                    @RequestParam("info") String info) {
        Senorita senorita = new Senorita();
        senorita.setName(name);
        senorita.setAge(age);
        senorita.setInfo(info);
        service.save(senorita);
    }
    //删
    @DeleteMapping("/delById/{id}")
    public void deleteById(@PathVariable("id") String id) {
        QueryWrapper<Senorita> wrapper = new QueryWrapper<>();
        service.removeById(id);
    }

    @DeleteMapping("/delById")
    public void deleteById2(@RequestParam("id") String id) {
        QueryWrapper<Senorita> wrapper = new QueryWrapper<>();
        service.removeById(id);
    }
    //改
    @PutMapping("/update")
    public void update(@RequestBody Senorita senorita){
        QueryWrapper<Senorita> wrapper = new QueryWrapper<>();
        wrapper.eq("name",senorita.getName());
        service.update(senorita,wrapper);
    }
}
