package com.kuznetsov.linoleum.service;


import com.kuznetsov.linoleum.dao.*;
import com.kuznetsov.linoleum.dto.*;
import com.kuznetsov.linoleum.entity.*;
import com.kuznetsov.linoleum.mapper.CreateOrderMapper;
import com.kuznetsov.linoleum.mapper.CreateUserMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderService {
    private static final OrderService INSTANCE = new OrderService();
    private OrderDao orderDao = OrderDao.getInstance();
    private final CreateOrderMapper createOrderMapper = CreateOrderMapper.getInstance();

    private final List<CreateFragmentWithoutLayoutDto> withoutLayoutFragments  =  new ArrayList<>();
    private final List<CreateFragmentDto> customLayoutFragments = new ArrayList<>();


    private OrderService(){}
    public List<OrderDto> findAll(){
        return null;
    }

    public Order save(CreateOrderDto createOrderDto){
        Order order = createOrderMapper.mapFrom(createOrderDto);
        return orderDao.save(order);
    }

    //calculate order cost (also for custom layout and without layout fragments)
    public int calcCost(LinoleumDto linoleumDto, List<FragmentDto> orderFragments){
        double area = orderFragments.stream().mapToDouble(f->f.getWidth()*f.getLength()).sum();
        return (int) (area*linoleumDto.getPrice());
    }
    public int calcCostForWithoutLayoutFragments(LinoleumDto linoleumDto){
        double area = withoutLayoutFragments.stream().mapToDouble(f->Float.parseFloat(f.getfWidth())*Float.parseFloat(f.getfLength())).sum();
        return (int) (area*linoleumDto.getPrice());
    }

    public int calcCostForCustomLayoutFragments(LinoleumDto linoleumDto){
        double area = customLayoutFragments.stream().mapToDouble(f->Float.parseFloat(f.getWidth())*Float.parseFloat(f.getLength())).sum();
        return (int) (area*linoleumDto.getPrice());
    }

    public List<CreateFragmentWithoutLayoutDto> getWithoutLayoutFragments(){
        return withoutLayoutFragments;
    }

    public List<CreateFragmentDto> getCustomLayoutFragments(){
        return customLayoutFragments;
    }

    public void clearWithoutLayoutFragments(){
        withoutLayoutFragments.clear();
    }

    public void clearCustomLayoutFragments(){
        customLayoutFragments.clear();
    }



   /* public static void cutRoll(List<Roll> stock,List<Fragment> fragments,Fragment fragment){
        float inaccurancy = 0.05f;
        float excess = 0.01f;

        List<Float> rollWidthList = stock.stream().map(s->s.getWidth()).collect(Collectors.toList());
        List<Float> rollLengthList = stock.stream().map(s->s.getLength()).collect(Collectors.toList());

        float fragW = fragment.getWidth();
        float fragL = fragment.getLength();
        if(stock.isEmpty()){ // checking stock emptiness
            System.out.println("Stock is empty!");
        }else {
            if (rollWidthList.contains(fragW)) { //checking stock emptiness
                if(stock.stream().filter(r->r.getWidth()==fragW).map(r->r.getLength()).filter(length->length>fragL+excess).collect(Collectors.toList()).isEmpty()){
                    System.out.println("Stock is empty!Rolls for cutting does not exists yet, because all lengths are less than fragment");
                }else {
                    Optional<Roll> cuttableRoll = stock.stream().filter(r -> r.getWidth() == fragW && r.getLength() >= fragL && r.getLength() - (fragL + excess) <= 0.25).findFirst();
                    if (cuttableRoll.isPresent()) {
                        System.out.println("Roll is finished! Cuttable width: " + cuttableRoll.get().getWidth() + ",cuttable length: " + cuttableRoll.get().getLength());
                        stock.remove(cuttableRoll.get().getId());
                    }else {
                        findRollForCutting(stock,fragments,fragW,fragL,excess);
                    }
                }
            } else {
                if(stock.stream().filter(r->r.getWidth()>fragW).count()==0){
                    System.out.println("Stock is empty!Rolls for cutting does not exists yet!");
                }else {
                    if(!findRemainRollForCutting(stock,fragments,fragW,fragL,excess)){
                        if(!fragmentFromLargerWidthRollIsCreated(fragL,fragW)){
                            System.out.println("Sorry, this fragment does not contained in stock!");
                        }
                    }

                }

            }
        }


    }

    public static boolean fragmentFromLargerWidthRollIsCreated(float fragL,float fragW){
        List<Roll> rollsWhereWidthLarger = stock.stream().filter(r->r.getWidth()>fragW && r.getLength()>fragL &&!r.isRemain())
                .sorted(Comparator.comparingDouble(Roll::getLength)).collect(Collectors.toList());
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
                    stock.add(new Roll(stock.size(), remainWidth, rollFirstPartLength, true));
                    return true;
                } else {
                    List<Fragment> fragmentsWithRollSecondPartLength = fragments.stream().filter(f -> f.getLength() <= rollSecondPartLength && rollSecondPartLength - f.getLength() <= 0.25)
                            .collect(Collectors.toList());
                    for (int k = 0; k < fragmentsWithRollSecondPartLength.size(); k++) {
                        float w = rollSecondPartWidth - fragmentsWithRollSecondPartLength.get(k).getWidth();
                        if (fragmentsWithRollSecondPartLength.stream().map(f -> f.getWidth()).collect(Collectors.toList())
                                .contains(rollSecondPartWidth - fragmentsWithRollSecondPartLength.get(k).getWidth())) {
                            float lengthBefore = rollSecondPartLength;
                            rollWhereWidthLarger.setLength(rollSecondPartLength);
                            stock.add(new Roll(stock.size(), remainWidth, rollFirstPartLength, true));
                            System.out.println("Fragment is cutted by width and length! Cuttable width: " + rollWhereWidthLarger.getWidth() + ",cuttable length: " + rollWhereWidthLarger.getLength()
                                    + ",roll id is " + rollWhereWidthLarger.getId() + ", roll remains: " + rollWhereWidthLarger.getWidth()
                                    + " * " + rollSecondPartLength + " new roll created(remain): " + remainWidth + " * " + rollFirstPartLength);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static void findRollForCutting(List<Roll> stock,List<Fragment> fragments,float fragW,float fragL,float excess){
        List<Roll> rollsInOrderByLength = stock.stream().filter(r -> r.getWidth() == fragW && r.getLength()>fragL)
                .sorted(Comparator.comparingDouble(Roll::getLength)).collect(Collectors.toList());
        outerloop:
        for(int i = 0;i<rollsInOrderByLength.size();i++){
            Roll rollForCutting = rollsInOrderByLength.get(i);
            float remains = rollForCutting.getLength()-(fragL+excess);
            if(remains>=6){
                System.out.println("Fragment is cutted! Cuttable width: " + rollForCutting.getWidth() + ",cuttable length: " + rollForCutting.getLength()
                        +",roll id is "+rollForCutting.getId()+", roll remains: "+remains);
                break outerloop;
            }else {
                List<Fragment> fragmentsWithRemainsLength = fragments.stream().filter(f->f.getLength()<remains && remains-f.getLength()<=0.25)
                        .collect(Collectors.toList());
                for(int k = 0;k<fragmentsWithRemainsLength.size();k++){
                    float w = rollForCutting.getWidth()-fragmentsWithRemainsLength.get(k).getWidth();
                    if(fragmentsWithRemainsLength.stream().map(f->f.getWidth()).collect(Collectors.toList())
                            .contains(rollForCutting.getWidth()-fragmentsWithRemainsLength.get(k).getWidth())){
                        float lengthBefore = rollForCutting.getLength();
                        rollForCutting.setRemain(true);
                        rollForCutting.setLength(remains);
                        System.out.println("Fragment is cutted! Cuttable roll width: " + rollForCutting.getWidth() + ",cuttable roll length: " + lengthBefore
                                +",roll id is "+rollForCutting.getId()+", roll remains: "+rollForCutting.getWidth()+" * "+remains);
                        break outerloop;
                    }
                }

            }
        }
    }

    public static boolean findRemainRollForCutting(List<Roll> stock,List<Fragment> fragments, float fragW,float fragL,float excess){
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
                return true;
            }
        }
        return false;
    }

    */

    public static OrderService getInstance(){
        return INSTANCE;
    }
}
