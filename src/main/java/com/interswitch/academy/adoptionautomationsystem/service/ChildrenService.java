package com.interswitch.academy.adoptionautomationsystem.service;

import com.interswitch.academy.adoptionautomationsystem.dto.ChildrenDto;
import com.interswitch.academy.adoptionautomationsystem.entities.Children;
import javassist.NotFoundException;

import java.util.List;

public interface ChildrenService {

    List<ChildrenDto> findAllChildren();
    Children addChild(ChildrenDto childrenDto);
    ChildrenDto findChildById(String childId) throws NotFoundException;
    void deleteChild(String childId);
    List<ChildrenDto> searchChildren(String text);
    void updateChild(ChildrenDto childrenDto);
    List<ChildrenDto> findChildByGuardianId(String guardianId);
    ChildrenDto findChildByParentId(String parentId);
}
