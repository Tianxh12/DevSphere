package com.devsphere.service;

import com.devsphere.dto.DishDTO;

public interface DishService {
    /**
     * 新增菜品和对应的口味
     *
     * @param dishDTO
     */
    public void saveWithFlavor(DishDTO dishDTO);
}
