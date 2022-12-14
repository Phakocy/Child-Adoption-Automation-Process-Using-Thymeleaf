package com.interswitch.academy.adoptionautomationsystem.service.serviceImpl;

import com.interswitch.academy.adoptionautomationsystem.dto.AdoptiveParentDto;
import com.interswitch.academy.adoptionautomationsystem.entities.AdoptiveParent;
import com.interswitch.academy.adoptionautomationsystem.entities.User;
import com.interswitch.academy.adoptionautomationsystem.mapper.AdoptiveParentMapper;
import com.interswitch.academy.adoptionautomationsystem.repository.AdoptiveParentRepository;
import com.interswitch.academy.adoptionautomationsystem.repository.UserRepository;
import com.interswitch.academy.adoptionautomationsystem.service.AdoptiveParentService;
import com.interswitch.academy.adoptionautomationsystem.util.IdUtil;
import com.interswitch.academy.adoptionautomationsystem.util.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdoptiveParentServiceImpl implements AdoptiveParentService {

    private IdUtil idUtil;
    private AdoptiveParentRepository parentRepository;
    private UserRepository userRepository;


    public AdoptiveParentServiceImpl(IdUtil idUtil, AdoptiveParentRepository parentRepository,
                                     UserRepository userRepository) {
        this.idUtil = idUtil;
        this.parentRepository = parentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<AdoptiveParentDto> findAllRequest() {
        List<AdoptiveParent> parents = parentRepository.findAll();
        return parents.stream().map((parent) -> AdoptiveParentMapper.mapToAdoptiveParentDto(parent))
                .collect(Collectors.toList());

    }

    @Override
    public AdoptiveParent createParent(AdoptiveParentDto adoptiveParentDto) {
    String email = SecurityUtils.getCurrentUser().getUsername();
        User user = userRepository.findByEmail(email);
        String parentId = idUtil.generateId();
        adoptiveParentDto.setId(parentId);
        AdoptiveParent parent = AdoptiveParentMapper.mapToAdoptiveParent(adoptiveParentDto);
        parent.setCreatedBy(user);

        parentRepository.save(parent);
        return parent;
    }

    @Override
    public AdoptiveParentDto findParentById(String parentId) {
        AdoptiveParent parent = parentRepository.findById(parentId).get();
        return AdoptiveParentMapper.mapToAdoptiveParentDto(parent);
    }

    @Override
    public void updateParent(AdoptiveParentDto parentDto) {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User user = userRepository.findByEmail(email);
        AdoptiveParent parent = AdoptiveParentMapper.mapToAdoptiveParent(parentDto);
        parent.setUpdatedBy(user);
        parentRepository.save(parent);
    }

    @Override
    public void deleteParent(String parentId) {
        parentRepository.deleteById(parentId);
    }

    @Override
    public List<AdoptiveParentDto> searchParent(String text) {
        List<AdoptiveParent> parents = parentRepository.searchAdoptiveParent(text);
        return parents.stream()
                .map(AdoptiveParentMapper::mapToAdoptiveParentDto)
                .collect(Collectors.toList());
    }
}
