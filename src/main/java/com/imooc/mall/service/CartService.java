package com.imooc.mall.service;

import com.imooc.mall.exception.ImoocMallException;
import com.imooc.mall.model.pojo.User;
import com.imooc.mall.model.vo.CartVO;

import java.util.List;

/**
 * Shopping cart Service
 */
public interface CartService {


    List<CartVO> add(Integer userId, Integer productId, Integer count);

    List<CartVO> list(Integer userId);

    List<CartVO> update(Integer userId, Integer productId, Integer count);

    List<CartVO> delete(Integer userId, Integer productId);

    List<CartVO> selectOrNot(Integer productId, Integer userId, Integer selected);

    List<CartVO> selectAllOrNot(Integer userId, Integer selected);
}
