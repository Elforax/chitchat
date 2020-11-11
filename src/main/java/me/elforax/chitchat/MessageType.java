package me.elforax.chitchat;

/**
 * MassageType is used to quickly find and add messages in combination with
 * \link Messager.java
 */
public enum MessageType {
    START,
    STOP,
    CONFIG_INIT,
    CONFIG_VALUES,
    SCHEDULER,
    EVENTS,
    STARTCHAT,
    ENDCHAT,
    PLAYER_NOT_FOUND,
    PLAYER_FOUND,
    PLAYER_ADDED,
    PLAYER_REMOVED
}
