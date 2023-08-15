package com.kuznetsov.linoleum.service;

import com.kuznetsov.linoleum.dao.LinoleumDao;
import com.kuznetsov.linoleum.dto.CreateLinoleumDto;
import com.kuznetsov.linoleum.dto.LinoleumDto;
import com.kuznetsov.linoleum.dto.LinoleumFilter;
import com.kuznetsov.linoleum.dto.UpdateLinoleumDto;
import com.kuznetsov.linoleum.entity.Linoleum;
import com.kuznetsov.linoleum.mapper.CreateLinoleumMapper;
import com.kuznetsov.linoleum.mapper.LinoleumDtoMapper;
import com.kuznetsov.linoleum.mapper.UpdateLinoleumMapper;



import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LinoleumService {
    private static final LinoleumService INSTANCE = new LinoleumService();
    private final LinoleumDtoMapper linoleumDtoMapper = LinoleumDtoMapper.getInstance();
    private final CreateLinoleumMapper createLinoleumMapper = CreateLinoleumMapper.getInstance();
    private final UpdateLinoleumMapper updateLinoleumMapper = UpdateLinoleumMapper.getInstance();
    private final LinoleumDao linoleumDao = LinoleumDao.getInstance();
    private final ImageService imageService = ImageService.getInstance();

    private LinoleumService(){}

    public List<LinoleumDto> findAll(){
        return linoleumDao.findAll().stream().map(linoleum -> new LinoleumDto(linoleum.getId()
        ,linoleum.getName(),linoleum.getProtect(),linoleum.getThickness(),linoleum.getPrice(),linoleum.getImagePath()))
                .collect(Collectors.toList());
    }

    public List<LinoleumDto> findAll(LinoleumFilter linoleumFilter, String field){
       return linoleumDao.findAll(linoleumFilter,field).stream()
               .map(l->new LinoleumDto(l.getId(),l.getName(),l.getProtect()
               ,l.getThickness(),l.getPrice(),l.getImagePath())).collect(Collectors.toList());
    }

    public Optional<LinoleumDto> findById(Integer id){
        return linoleumDao.findById(id).map(l->linoleumDtoMapper.mapFrom(l));
    }


    public Linoleum save(CreateLinoleumDto createLinoleumDto) throws IOException {
        //without validation , because this method will be used only by admin
        Linoleum linoleum = createLinoleumMapper.mapFrom(createLinoleumDto);
        imageService.upload(linoleum.getImagePath(),createLinoleumDto.getImagePath().getInputStream());
        return linoleumDao.save(linoleum);
    }

    public void update(UpdateLinoleumDto updateLinoleumDto) throws IOException {
        Linoleum linoleum = updateLinoleumMapper.mapFrom(updateLinoleumDto);
        if(updateLinoleumDto.getDefaultImagePath()==null) {
            //finding old linoleum and delete them
            imageService.deleteImage(findById(Integer.parseInt(updateLinoleumDto.getId())).get().getImagePath());
            imageService.upload(linoleum.getImagePath(), updateLinoleumDto.getImagePath().getInputStream());
        }
        linoleumDao.update(linoleum);
    }

    public boolean delete(Integer id) throws IOException {
        imageService.deleteImage(findById(id).get().getImagePath());
        return linoleumDao.delete(id);
    }

    public static LinoleumService getInstance(){
        return INSTANCE;
    }
}
