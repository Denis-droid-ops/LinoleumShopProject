package com.kuznetsov.linoleum.service;

import com.kuznetsov.linoleum.dao.FragmentDao;
import com.kuznetsov.linoleum.dao.LayoutsNamesDao;
import com.kuznetsov.linoleum.dto.*;
import com.kuznetsov.linoleum.entity.Apartment;
import com.kuznetsov.linoleum.entity.Fragment;
import com.kuznetsov.linoleum.mapper.CreateFragmentMapper;
import com.kuznetsov.linoleum.mapper.CreateLayoutNameMapper;
import com.kuznetsov.linoleum.mapper.FragmentDtoMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FragmentService {
    private static final FragmentService INSTANCE = new FragmentService();
    private FragmentDao fragmentDao = FragmentDao.getInstance();
    private CreateFragmentMapper createFragmentMapper = CreateFragmentMapper.getInstance();
    private FragmentDtoMapper fragmentDtoMapper = FragmentDtoMapper.getInstance();

    private FragmentService(){}

    public Fragment save(CreateFragmentDto createFragmentDto){
        Fragment fragment = createFragmentMapper.mapFrom(createFragmentDto);
        return fragmentDao.save(fragment);
    }

    public List<FragmentDto> findAllByLayoutNameId(Integer layoutNameId){
        return fragmentDao.findAllByLayoutNameId(layoutNameId).stream()
                .map(f->new FragmentDto(f.getId(),f.getWidth()
                        ,f.getLength(),f.getfType(),f.getLayoutName()))
                .collect(Collectors.toList());
    }
    public Optional<FragmentDto> findById(Integer id){
        return Optional.ofNullable(fragmentDtoMapper.mapFrom(fragmentDao.findById(id).get()));
    }

    public List<Fragment> findAll(){
        return fragmentDao.findAll();
    }

    public static FragmentService getInstance(){
        return INSTANCE;
    }

}
