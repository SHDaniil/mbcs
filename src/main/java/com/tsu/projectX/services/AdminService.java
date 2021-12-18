package com.tsu.projectX.services;

import com.tsu.projectX.entities.Role;
import com.tsu.projectX.entities.User;
import com.tsu.projectX.repositories.IRoleRepository;
import com.tsu.projectX.repositories.IUserRepository;
import com.tsu.projectX.services.interfaces.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AdminService implements IAdminService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public boolean approveUserRole(UUID id) {
        Optional<User> userById = userRepository.findById(id);
        if (userById.isPresent()) {
            User user = userById.get();
            Role wantedRole = roleRepository.findByName(user.getWantedRole());
            if (wantedRole != null) {
                user.setRole(wantedRole);
                user.setWantedRole(null);
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }
}
