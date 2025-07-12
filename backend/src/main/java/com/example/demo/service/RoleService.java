package com.example.demo.service;

import com.example.demo.DTO.RoleRequestDTO;
import com.example.demo.model.RoleEntity;
import com.example.demo.model.Skill;
import com.example.demo.repo.RoleRepository;
import com.example.demo.repo.SkillRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SkillRepository skillRepository;

    public RoleEntity createRoleWithSkills(RoleRequestDTO dto) {
        List<Skill> skillEntities = new ArrayList<>();

        for (String skillName : dto.getSkills()) {
            Skill skill = skillRepository.findBySkillNameIgnoreCase(skillName.trim());

            if (skill == null) {
                skill = new Skill();
                skill.setSkillName(skillName.trim());
                skill = skillRepository.save(skill); // Save new skill
            }

            skillEntities.add(skill);
        }

        RoleEntity role = new RoleEntity();
        role.setRoleName(dto.getRoleName());
        role.setSkills(skillEntities);

        return roleRepository.save(role);
    }
    
    public Map<String, Object> getRoleWithSkills(String roleName) {
        RoleEntity role = roleRepository.findByRoleName(roleName);
        Map<String, Object> roleMap = new HashMap<>();

        if (role == null) return roleMap;

        roleMap.put("roleName", role.getRoleName());

        List<String> skillNames = role.getSkills()
                                      .stream()
                                      .map(skill -> skill.getSkillName())
                                      .collect(Collectors.toList());

        roleMap.put("skills", skillNames);
        return roleMap;
    }


}
