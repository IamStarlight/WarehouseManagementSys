package com.bjtu.warehousemanagebackend.controller;

import com.bjtu.warehousemanagebackend.entity.Buy;
import com.bjtu.warehousemanagebackend.entity.Provide;
import com.bjtu.warehousemanagebackend.entity.User;
import com.bjtu.warehousemanagebackend.service.impl.BuyServiceImpl;
import com.bjtu.warehousemanagebackend.service.impl.ProvideServiceImpl;
import com.bjtu.warehousemanagebackend.utils.Result;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bjtu.warehousemanagebackend.service.impl.UserServiceImpl;

import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Jinxuan Chen
 * @since 2024-04-09
 */
@RestController
//@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ProvideServiceImpl provideService;

    @Autowired
    private BuyServiceImpl buyService;

    //注册用户
    @PostMapping
    public ResponseEntity<Result> register(@RequestBody @Valid User newUser){
        userService.register(newUser);
        return new ResponseEntity<>((Result.success()),HttpStatus.OK);
    }

    //更新用户数据
    @PutMapping("/{id}")
    public ResponseEntity<Result> updateUserInfo(@PathVariable String id,@RequestBody @Valid User info){
        info.setId(id);
        userService.updateById(info);
        return new ResponseEntity<>((Result.success()),HttpStatus.OK);
    }

    //todo:修改密码怎么办

    //获取用户列表
    @GetMapping
    public ResponseEntity<Result> getAll(){
        return new ResponseEntity<>(Result.success(userService.list()), HttpStatus.OK);
    }

    //获取单一类型的user
    @GetMapping("/{id}/type")
    public ResponseEntity<Result> getOneType(@PathVariable String id, @RequestParam String type){
        return new ResponseEntity<>(Result.success(userService.getById(id)), HttpStatus.OK);
    }

    //获取用户信息
    //todo:分页
    @GetMapping("/{id}")
    public ResponseEntity<Result> getOne(@PathVariable String id){
        return new ResponseEntity<>(Result.success(userService.getById(id)), HttpStatus.OK);
    }

    // Search provides by uId
    @GetMapping("/{uid}/provide")
    public ResponseEntity<Result> searchProvideByuId(@PathVariable String uid) {
        List<Provide> provides = provideService.getByuId(uid);
        return new ResponseEntity<>(Result.success(provides), HttpStatus.OK);
    }

    // 具体一个人的购买记录
    @GetMapping("/{uid}/purchase")
    public ResponseEntity<Result> getOrderByUid(@PathVariable String uid) {
        List<Buy> buy = buyService.getOrderByUid(uid);
        return new ResponseEntity<>(Result.success(buy), HttpStatus.OK);
    }

}
