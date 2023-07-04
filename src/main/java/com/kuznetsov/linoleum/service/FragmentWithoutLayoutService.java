package com.kuznetsov.linoleum.service;

import com.kuznetsov.linoleum.dao.FragmentDao;
import com.kuznetsov.linoleum.dao.FragmentWithoutLayoutDao;
import com.kuznetsov.linoleum.dto.CreateFragmentDto;
import com.kuznetsov.linoleum.dto.CreateFragmentWithoutLayoutDto;
import com.kuznetsov.linoleum.dto.CreateLayoutNameDto;
import com.kuznetsov.linoleum.dto.FragmentDto;
import com.kuznetsov.linoleum.entity.Fragment;
import com.kuznetsov.linoleum.entity.FragmentWithoutLayout;
import com.kuznetsov.linoleum.mapper.CreateFragmentMapper;
import com.kuznetsov.linoleum.mapper.CreateFragmentWithoutLayoutMapper;
import com.kuznetsov.linoleum.mapper.FragmentDtoMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FragmentWithoutLayoutService {
    private static final FragmentWithoutLayoutService INSTANCE = new FragmentWithoutLayoutService();
    private final FragmentWithoutLayoutDao fragmentWithoutLayoutDao = FragmentWithoutLayoutDao.getInstance();
    private final CreateFragmentWithoutLayoutMapper createFragmentWithoutLayoutMapper = CreateFragmentWithoutLayoutMapper.getInstance();


    private FragmentWithoutLayoutService(){}

    public FragmentWithoutLayout save(CreateFragmentWithoutLayoutDto createFragmentWithoutLayoutDto){
        FragmentWithoutLayout fragmentWithoutLayout = createFragmentWithoutLayoutMapper.mapFrom(createFragmentWithoutLayoutDto);
        return fragmentWithoutLayoutDao.save(fragmentWithoutLayout);
    }

    public List<FragmentDto> findAllByLayoutNameId(Integer layoutNameId){
        return null;
    }
    public Optional<FragmentDto> findById(Integer id){
       return Optional.empty();
    }

    public List<Fragment> findAll(){
        return null;
    }

    public static FragmentWithoutLayoutService getInstance(){
        return INSTANCE;
    }
}
