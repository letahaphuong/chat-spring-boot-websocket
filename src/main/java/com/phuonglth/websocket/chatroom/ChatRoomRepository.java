package com.phuonglth.websocket.chatroom;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {

    Optional<ChatRoom> findBySenderIdAndRecipientId(final String senderId, final String recipientId);
}
