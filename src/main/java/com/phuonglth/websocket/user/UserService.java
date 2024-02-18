package com.phuonglth.websocket.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public void saveUser(final User user) {
        user.setStatus(STATUS.ONLINE);
        repository.save(user);
    }

    public void disconnect(final User user) {
        var storedUser = repository.findById(user.getNickname())
                .orElse(null);

        if (storedUser != null) {
            storedUser.setStatus(STATUS.OFFLINE);
            repository.save(storedUser);
        }
    }

    public List<User> findConnectedUsers() {
        return repository.findAllByStatus(STATUS.ONLINE);
    }
}
