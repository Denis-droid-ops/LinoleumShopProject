package com.kuznetsov.linoleum.mapper;

import com.kuznetsov.linoleum.dao.OrderDao;
import com.kuznetsov.linoleum.dto.CreateFragmentWithoutLayoutDto;
import com.kuznetsov.linoleum.entity.FragmentWithoutLayout;

public class CreateFragmentWithoutLayoutMapper implements Mapper<CreateFragmentWithoutLayoutDto, FragmentWithoutLayout> {
    private static final CreateFragmentWithoutLayoutMapper INSTANCE = new CreateFragmentWithoutLayoutMapper();
    private final OrderDao orderDao = OrderDao.getInstance();
    @Override
    public FragmentWithoutLayout mapFrom(CreateFragmentWithoutLayoutDto object) {
        FragmentWithoutLayout fragmentWithoutLayout = new FragmentWithoutLayout();
        fragmentWithoutLayout.setfWidth(Float.valueOf(object.getfWidth()));
        fragmentWithoutLayout.setfLength(Float.valueOf(object.getfLength()));
        fragmentWithoutLayout.setOrder(orderDao.findById(Integer.valueOf(object.getOrderId())).get());
        return fragmentWithoutLayout;
    }

    public static CreateFragmentWithoutLayoutMapper getInstance(){
        return INSTANCE;
    }
}
