package com.interswitch.academy.adoptionautomationsystem.service;

import com.interswitch.academy.adoptionautomationsystem.dto.AdoptiveParentDto;
import com.interswitch.academy.adoptionautomationsystem.entities.AdoptiveParent;

import java.util.List;

public interface AdoptiveParentService {

    List<AdoptiveParentDto> findAllRequest();
    AdoptiveParent createParent(AdoptiveParentDto adoptiveParentDto);
    AdoptiveParentDto findParentById(String parentId);
    void updateParent(AdoptiveParentDto parentDto);
    void deleteParent(String parentId);
    List<AdoptiveParentDto> searchParent(String query);

}
