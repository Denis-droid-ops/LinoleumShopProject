package com.kuznetsov.linoleum.service;

import com.kuznetsov.linoleum.dao.FragmentDao;
import com.kuznetsov.linoleum.dao.RollDao;

import com.kuznetsov.linoleum.dto.*;

import com.kuznetsov.linoleum.entity.Fragment;
import com.kuznetsov.linoleum.entity.FragmentType;
import com.kuznetsov.linoleum.entity.LayoutName;
import com.kuznetsov.linoleum.entity.Roll;

import com.kuznetsov.linoleum.exception.StockException;
import com.kuznetsov.linoleum.mapper.CreateRollMapper;

import com.kuznetsov.linoleum.mapper.FragmentDtoMapper;
import com.kuznetsov.linoleum.mapper.RollDtoMapper;
import com.kuznetsov.linoleum.validator.Error;


import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RollService {
    private static final RollService INSTANCE = new RollService();
    private final RollDao rollDao = RollDao.getInstance();
    private final CreateRollMapper createRollMapper = CreateRollMapper.getInstance();
    private final RollDtoMapper rollDtoMapper = RollDtoMapper.getInstance();
    private final FragmentDao fragmentDao = FragmentDao.getInstance();
    private final FragmentDtoMapper fragmentDtoMapper = FragmentDtoMapper.getInstance();


    private RollService(){
    }

    public Roll save(CreateRollDto createRollDto){
        Roll roll = createRollMapper.mapFrom(createRollDto);
        return rollDao.save(roll);
    }

    public boolean delete(Integer id){
        return rollDao.delete(id);
    }

    public List<RollDto> findAll(){
        return rollDao.findAll().stream().map(r->rollDtoMapper.mapFrom(r)).collect(Collectors.toList());
    }

    public Optional<RollDto> findById(Integer id){
        return rollDao.findById(id)
                .map(r->new RollDto(r.getId(),r.getPartNum(),r.getWidth(),r.getLength(),r.isRemain(),r.getLinoleum()));
    }

    public RollDto cutRoll(FragmentWithoutLayoutDto fragmentWithoutLayoutDto,String linoleumName) {
        //Casting fragmentWithoutLayoutDto to fragmentDto
        FragmentDto fragmentDto = new FragmentDto(fragmentWithoutLayoutDto.getId()
                ,fragmentWithoutLayoutDto.getfWidth()
                ,fragmentWithoutLayoutDto.getfLength()
                //Creating stub objects
                , FragmentType.HALL
                ,new LayoutName());
        return cutRoll(fragmentDto,linoleumName);
    }

    public RollDto cutRoll(FragmentDto fragmentDto, String linoleumName){
        Fragment fragment = new Fragment(fragmentDto.getId(),fragmentDto.getWidth(),fragmentDto.getLength(),fragmentDto.getfType(),fragmentDto.getLayoutName());
        Roll resultRoll = null;
        List<Fragment> fragments = fragmentDao.findAll().stream().filter(f->!f.getLayoutName().getLnName().startsWith("CUSTOM")).collect(Collectors.toList());
        List<Roll> stock = rollDao.findAll().stream().filter(r->r.getLinoleum().getName().equals(linoleumName)).collect(Collectors.toList());

        float excess = 0.01f;

        List<Float> rollWidthList = stock.stream().map(s->s.getWidth()).collect(Collectors.toList());

        float fragW = fragment.getWidth();
        float fragL = fragment.getLength();
        if(stock.isEmpty()){ // checking stock emptiness
            System.out.println("Stock is empty!");
            throw new StockException(List.of(new Error("roll.empty","Stock is empty!")), fragmentDto);
        }else {
            if (rollWidthList.contains(fragW)) { //checking stock emptiness
                if(stock.stream().filter(r->r.getWidth()==fragW).map(r->r.getLength()).filter(length->length>=fragL).collect(Collectors.toList()).isEmpty()){
                    System.out.println("Stock is empty!Rolls for cutting does not exists yet, because all lengths are less than fragment");
                    throw new StockException(List.of(new Error("roll.empty","Stock is empty!Rolls for cutting does not exists yet, because all lengths are less than fragment")), fragmentDto);
                }else {
                    Optional<Roll> cuttableRoll = stock.stream().filter(r -> r.getWidth() == fragW && r.getLength() >= fragL && r.getLength() - (fragL + excess) <= 0.25).findFirst();
                    if (cuttableRoll.isPresent()) {
                        System.out.println("Roll is finished! Cuttable width: " + cuttableRoll.get().getWidth() + ",cuttable length: " + cuttableRoll.get().getLength());
                        rollDao.delete(cuttableRoll.get().getId());
                        resultRoll = cuttableRoll.get();
                    }else {
                        resultRoll = findRollForCutting(stock,fragments,fragW,fragL,excess);
                    }
                }
            } else {
                if(stock.stream().filter(r->r.getWidth()>fragW).count()==0){
                    System.out.println("Stock is empty!Rolls for cutting does not exists yet!");
                    throw new StockException(List.of(new Error("roll.empty","Stock is empty!Rolls for cutting does not exists yet!")), fragmentDto);
                }else {
                    Roll remainRollForCutting = findRemainRollForCutting(stock,fragments,fragW,fragL,excess);
                    if(remainRollForCutting==null){
                        Roll fragmentFromLargerWidthRoll = fragmentFromLargerWidthRollIsCreated(fragL,fragW,stock,fragments);
                        if(fragmentFromLargerWidthRoll!=null){
                            resultRoll = fragmentFromLargerWidthRoll;
                        }else {
                            System.out.println("Sorry, this fragment does not contained in stock!");
                            throw new StockException(List.of(new Error("roll.notContained","Sorry, this fragment does not contained in stock!")), fragmentDto);
                        }
                    }else {
                        resultRoll = remainRollForCutting;
                    }

                }

            }
        }
      if(resultRoll!=null) return rollDtoMapper.mapFrom(resultRoll);
      throw new StockException(List.of(new Error("roll.notContained","Sorry, this fragment does not contained in stock!")), fragmentDto);
    }

    private Roll fragmentFromLargerWidthRollIsCreated(float fragL,float fragW,List<Roll> stock,List<Fragment> fragments){
        Roll resultRoll = null;
        List<Roll> rollsWhereWidthLarger = stock.stream().filter(r->r.getWidth()>fragW && r.getLength()>fragL &&!r.isRemain())
                .sorted(Comparator.comparingDouble(Roll::getLength)).collect(Collectors.toList());
        outherloop:
        for (int i = 0;i<rollsWhereWidthLarger.size();i++) {
            Roll rollWhereWidthLarger = rollsWhereWidthLarger.get(i);
            float rollFirstPartWidth = rollWhereWidthLarger.getWidth();
            float rollFirstPartLength = fragL;
            float rollSecondPartWidth = rollFirstPartWidth;
            float rollSecondPartLength = rollWhereWidthLarger.getLength() - rollFirstPartLength;
            float remainWidth = rollFirstPartWidth - fragW;
            if (fragments.stream().anyMatch(f -> f.getWidth() == remainWidth && rollFirstPartLength - f.getLength() <= 0.25)) {
                if (rollSecondPartLength >= 6) {
                    System.out.println("Fragment is cutted by width and length! Cuttable width: " + rollWhereWidthLarger.getWidth() + ",cuttable length: " + rollWhereWidthLarger.getLength()
                            + ",roll id is " + rollWhereWidthLarger.getId() + ", roll remains: " + rollWhereWidthLarger.getWidth()
                            + " * " + rollSecondPartLength + " new roll created(remain): " + remainWidth + " * " + rollFirstPartLength);
                    rollWhereWidthLarger.setLength(rollSecondPartLength);
                    rollDao.update(rollWhereWidthLarger);
                    resultRoll = rollWhereWidthLarger;
                    //resultRoll = new Roll(null,rollWhereWidthLarger.getPartNum(),remainWidth, rollFirstPartLength,true,rollWhereWidthLarger.getLinoleum());
                    rollDao.save(new Roll(null,rollWhereWidthLarger.getPartNum(),remainWidth, rollFirstPartLength,true,rollWhereWidthLarger.getLinoleum()));
                    break;
                    //stock.add(new Roll(stock.size(), remainWidth, rollFirstPartLength, true));
                } else {
                    List<Fragment> fragmentsWithRollSecondPartLength = fragments.stream().filter(f -> f.getLength() <= rollSecondPartLength && rollSecondPartLength - f.getLength() <= 0.25)
                            .collect(Collectors.toList());
                    for (int k = 0; k < fragmentsWithRollSecondPartLength.size(); k++) {
                        float w = rollSecondPartWidth - fragmentsWithRollSecondPartLength.get(k).getWidth();
                        if (fragmentsWithRollSecondPartLength.stream().map(f -> f.getWidth()).collect(Collectors.toList())
                                .contains(rollSecondPartWidth - fragmentsWithRollSecondPartLength.get(k).getWidth())) {
                            float lengthBefore = rollSecondPartLength;
                            rollWhereWidthLarger.setLength(rollSecondPartLength);
                            rollDao.update(rollWhereWidthLarger);
                            //stock.add(new Roll(stock.size(), remainWidth, rollFirstPartLength, true));
                            resultRoll = new Roll(null,rollWhereWidthLarger.getPartNum(),remainWidth, rollFirstPartLength,true,rollWhereWidthLarger.getLinoleum());
                            rollDao.save(resultRoll);
                            System.out.println("Fragment is cutted by width and length! Cuttable width: " + rollWhereWidthLarger.getWidth() + ",cuttable length: " + rollWhereWidthLarger.getLength()
                                    + ",roll id is " + rollWhereWidthLarger.getId() + ", roll remains: " + rollWhereWidthLarger.getWidth()
                                    + " * " + rollSecondPartLength + " new roll created(remain): " + remainWidth + " * " + rollFirstPartLength);
                           break outherloop;
                        }
                    }
                }
            }
        }
        return resultRoll;
    }

    private Roll findRollForCutting(List<Roll> stock,List<Fragment> fragments,float fragW,float fragL,float excess){
        List<Roll> rollsInOrderByLength = stock.stream().filter(r -> r.getWidth() == fragW && r.getLength()>fragL)
                .sorted(Comparator.comparingDouble(Roll::getLength)).collect(Collectors.toList());
        Roll resultRoll = null;
        outerloop:
        for(int i = 0;i<rollsInOrderByLength.size();i++){
            Roll rollForCutting = rollsInOrderByLength.get(i);
            float remains = rollForCutting.getLength()-(fragL+excess);
            if(remains>=6){
                System.out.println("Fragment is cutted! Cuttable width: " + rollForCutting.getWidth() + ",cuttable length: " + rollForCutting.getLength()
                        +",roll id is "+rollForCutting.getId()+", roll remains: "+remains);
                Roll rollForUpdate = new Roll(rollForCutting.getId(),rollForCutting.getPartNum(),rollForCutting.getWidth(),remains,false,rollForCutting.getLinoleum());
                rollDao.update(rollForUpdate);
                resultRoll = rollForUpdate;
                break outerloop;
            }else {
                List<Fragment> fragmentsWithRemainsLength = fragments.stream().filter(f->f.getLength()<remains && remains-f.getLength()<=0.25)
                        .collect(Collectors.toList());
                for(int k = 0;k<fragmentsWithRemainsLength.size();k++){
                    float w = rollForCutting.getWidth()-fragmentsWithRemainsLength.get(k).getWidth();
                    List<Float> widthList = fragmentsWithRemainsLength.stream().map(f->f.getWidth()).collect(Collectors.toList());
                    if(widthList.contains(w) || widthList.contains(rollForCutting.getWidth())){
                        float lengthBefore = rollForCutting.getLength();
                        rollForCutting.setRemain(true);
                        rollForCutting.setLength(remains);
                        rollDao.update(rollForCutting);
                        System.out.println("Fragment is cutted! Cuttable roll width: " + rollForCutting.getWidth() + ",cuttable roll length: " + lengthBefore
                                +",roll id is "+rollForCutting.getId()+", roll remains: "+rollForCutting.getWidth()+" * "+remains);
                        resultRoll = rollForCutting;
                        break outerloop;

                    }
                }

            }
        }
        return resultRoll;
    }

    private Roll findRemainRollForCutting(List<Roll> stock,List<Fragment> fragments, float fragW,float fragL,float excess){
        Roll resultRoll = null;
        List<Roll> remainsRollsSortedByWidth = stock.stream()
                .filter(r->r.getLength()>fragL && r.getLength()-fragL<=0.25 && r.getWidth()>=2.5
                        && r.getWidth()>fragW && r.isRemain()).sorted(Comparator.comparingDouble(Roll::getWidth))
                .collect(Collectors.toList());
        for(int i = 0;i<remainsRollsSortedByWidth.size();i++){
            float remainRollRemainWidth = remainsRollsSortedByWidth.get(i).getWidth()-fragW;
            if(fragments.stream().anyMatch(f -> f.getWidth() == remainRollRemainWidth && f.getLength() - fragL <= 0.25)){
                System.out.println("Fragment is cutted by width! Cuttable roll width: " +remainsRollsSortedByWidth.get(i).getWidth()
                        +" ,cuttable roll length: " + remainsRollsSortedByWidth.get(i).getLength()
                        +" ,roll id is "+remainsRollsSortedByWidth.get(i).getId()+", roll remains: "
                        +remainRollRemainWidth+" * "+remainsRollsSortedByWidth.get(i).getLength());
                remainsRollsSortedByWidth.get(i).setWidth(remainRollRemainWidth);
                rollDao.update(remainsRollsSortedByWidth.get(i));
                resultRoll = remainsRollsSortedByWidth.get(i);
            }
        }
        return resultRoll;
    }


    public static RollService getInstance(){
        return INSTANCE;
    }
}
