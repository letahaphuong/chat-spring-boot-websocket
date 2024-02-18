package com.phuonglth.websocket.chat;

import com.phuonglth.websocket.chatroom.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository repository;
    private final ChatRoomService chatRoomService;

    public ChatMessage save(final ChatMessage chatMessage) {
        var chatId = chatRoomService
                .getChatRoomId(chatMessage.getSenderId(), chatMessage.getRecipientId(),
                        true)
                .orElseThrow();
        chatMessage.setChatId(chatId);
        repository.save(chatMessage);

        return chatMessage;
    }

    public List<ChatMessage> findChatMessages(
            final String senderId,
            final String recipientId
    ) {
        var chatId = chatRoomService.getChatRoomId(senderId,recipientId, false);

        return chatId.map(repository::findByChatId).orElse(new ArrayList<>());
    }
}
