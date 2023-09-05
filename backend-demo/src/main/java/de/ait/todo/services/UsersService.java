package de.ait.todo.services;


import de.ait.todo.dto.ProfileDto;
import de.ait.todo.dto.HaircutsPage;

public interface UsersService {

    ProfileDto getProfile(Long currentUserId);

    HaircutsPage getTasksByUser(Long currentUserId);
}
