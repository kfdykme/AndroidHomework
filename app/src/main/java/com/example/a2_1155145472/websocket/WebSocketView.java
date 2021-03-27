package com.example.a2_1155145472.websocket;

/**
 * 需要接受WebSocket消息并更新的对象
 */
public interface WebSocketView<T> {

    void onWebSocketRender(T data);
}