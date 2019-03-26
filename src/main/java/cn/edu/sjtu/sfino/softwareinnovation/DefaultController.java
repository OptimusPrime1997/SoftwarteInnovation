package cn.edu.sjtu.sfino.softwareinnovation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Daniel
 * @since 2019/3/25 18:30
 */
@RestController
@RequestMapping("/hello")
public class DefaultController {

    @RequestMapping("")
    public String hello(@RequestParam(required = true) String name) {
        return "hello " + name;
    }

}
