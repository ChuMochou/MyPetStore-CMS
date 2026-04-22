package org.csu.petstore.persistence;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.csu.petstore.entity.CartItem;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemMapper extends BaseMapper<CartItem> {
}
